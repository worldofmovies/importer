package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.entities.CountryEntity;
import se.david.moviesimporter.domain.entities.GenreEntity;
import se.david.moviesimporter.domain.entities.LanguageEntity;
import se.david.moviesimporter.domain.tmdb.Genre;
import se.david.moviesimporter.domain.tmdb.Movie;
import se.david.moviesimporter.domain.tmdb.ProductionCountry;
import se.david.moviesimporter.domain.tmdb.SpokenLanguage;
import se.david.moviesimporter.repository.CountryRepository;
import se.david.moviesimporter.repository.GenreRepository;
import se.david.moviesimporter.repository.LanguageRepository;
import se.david.moviesimporter.repository.MovieRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class MovieImporter extends BaseImporter {
	private final MovieRepository movieRepository;
	private final CountryRepository countryRepository;
	private final LanguageRepository languageRepository;
	private final GenreRepository genreRepository;

	private Map<String, CountryEntity> countriesCache;
	private Map<String, LanguageEntity> languagesCache;
	private Map<Long, GenreEntity> genresCache;

	public MovieImporter(MovieRepository movieRepository,
			CountryRepository countryRepository,
			LanguageRepository languageRepository,
			GenreRepository genreRepository) {
		this.movieRepository = movieRepository;
		this.countryRepository = countryRepository;
		this.languageRepository = languageRepository;
		this.genreRepository = genreRepository;
	}

	@PostConstruct
	public void initialLoad() {
		this.countriesCache = countryRepository.findAll().stream().collect(Collectors.toMap(CountryEntity::getIso_3166_1, entity -> entity));
		this.languagesCache = languageRepository.findAll().stream().collect(Collectors.toMap(LanguageEntity::getIso_639_1, entity -> entity));
		this.genresCache = genreRepository.findAll().stream().collect(Collectors.toMap(GenreEntity::getId, entity -> entity));
	}

	public List<Long> findAllUnprocessed() {
		return movieRepository.findAllUnprocessed();
	}

	public String processEntity(long movieId) {
		String additionals = "alternative_titles,external_ids,images,credits";
		String url = String.format("%s/3/movie/%s?api_key=%s&language=en-US&append_to_response=%s", tmdbApiUrl, movieId, apiKey, additionals);
		try {
			RestTemplateFetcher.fetch(url, Movie.class)
					.ifPresentOrElse(handleProcessedMovie(movieId), handleDeletedMovie(movieId));
			return String.format("Fetched movie: %s", movieId);
		} catch (IOException e) {
			e.printStackTrace();
			return String.format("Error fetching movie: %s. %s", movieId, e.getMessage());
		}
	}

	private Runnable handleDeletedMovie(long movieId) {
		return () -> movieRepository.deleteByIdWithTransaction(movieId);
	}

	private Consumer<Movie> handleProcessedMovie(long movieId) {
		return result -> movieRepository.findById(movieId)
				.ifPresent(movie -> {
					movie.processInfo(result);
					movie.processLanguages(languageEntities(result));
					movie.processCountries(countryEntities(result));
					movie.addGenres(genres(result));
					movieRepository.saveAndFlush(movie);
				});
	}

	private List<LanguageEntity> languageEntities(Movie movieData) {
		return movieData.getSpokenLanguages().stream()
				.map(SpokenLanguage::getIso_639_1)
				.map(languagesCache::get)
				.collect(Collectors.toList());
	}

	private List<CountryEntity> countryEntities(Movie movieData) {
		return movieData.getProductionCountries().stream()
				.map(ProductionCountry::getIso_3166_1)
				.map(countriesCache::get)
				.collect(Collectors.toList());
	}

	private List<GenreEntity> genres(Movie movieData) {
		return movieData.getGenres().stream()
				.map(Genre::getId)
				.map(genresCache::get)
				.collect(Collectors.toList());
	}
}
