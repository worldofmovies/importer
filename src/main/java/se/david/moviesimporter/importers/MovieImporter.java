package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.tmdb.Movie;
import se.david.moviesimporter.repository.MovieRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class MovieImporter extends BaseImporter {
	@Autowired
	private MovieRepository movieRepository;

	public MovieImporter() {
	}

	public List<Long> findAllUnprocessed() {
		return movieRepository.findAllUnprocessed();
	}

	public String processEntity(long movieId) {
		String additionals = "alternative_titles,keywords,external_ids,images,credits";
		String url = String.format("%s/3/movie/%s?api_key=%s&language=en-US&append_to_response=%s", tmdbApiUrl, movieId, apiKey, additionals);
		try {
			RestTemplateFetcher.fetchMovie(url)
					.ifPresentOrElse(handleProcessedMovie(movieId), handleDeletedMovie(movieId));
			return "Done";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Runnable handleDeletedMovie(long movieId) {
		return () -> movieRepository.deleteByIdWithTransaction(movieId);
	}

	private Consumer<Movie> handleProcessedMovie(long movieId) {
		return result -> movieRepository.findById(movieId)
				.ifPresent(movie -> {
					movie.processInfo(result);
					movieRepository.saveAndFlush(movie);
				});
	}
}
