package se.david.moviesimporter.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import junit.framework.AssertionFailedError;
import se.david.moviesimporter.domain.entities.GenreEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;
import se.david.moviesimporter.domain.tmdb.Movie;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GenreRepository genreRepository;

	@BeforeEach
	public void setup() {
		movieRepository.deleteAll();
		genreRepository.deleteAll();
	}

	@Test
	public void genreRelationTest() {
		List<GenreEntity> genres = genreRepository.saveAll(Arrays.asList(
				new GenreEntity(1L, "name"),
				new GenreEntity(2L, "name")));

		MovieEntity movie = new MovieEntity(1L, false, "title", 0.0, false, true);
		Movie movieData = new Movie();
		movieData.setPopularity(0.0);
		movie.processInfo(movieData);
		movie.processGenres(genres);
		MovieEntity movie2 = movieRepository.saveAndFlush(movie);

		GenreEntity genreEntity = genreRepository.findById(2L).orElseThrow(() -> new AssertionFailedError("Could not find genre"));
		assertTrue(movie2.getGenres().stream().anyMatch(a -> a.getId() == 2L), "Could not get genre from movie");
		assertTrue(genreEntity.getMovies().stream().anyMatch(a -> a.getId() == 1L), "Could not get movie from genre");
	}
}
