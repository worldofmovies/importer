package se.david.moviesimporter.repository;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import junit.framework.AssertionFailedError;
import se.david.moviesimporter.domain.entities.KeywordEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class KeywordRepositoryTest {
	@Autowired
	private KeywordRepository keywordRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Before
	public void setup() {
		keywordRepository.deleteAll();
		movieRepository.deleteAll();
	}

	@Test
	public void keywordMovieRelation() {
		KeywordEntity unprocessedKeyword = keywordRepository.saveAndFlush(new KeywordEntity(1, "name", false));

		MovieEntity unprocessedMovie = movieRepository.saveAndFlush(new MovieEntity(1, false, "title", 0.0, false, false));

		unprocessedKeyword.processInfo(Collections.singletonList(unprocessedMovie));
		keywordRepository.saveAndFlush(unprocessedKeyword);

		keywordRepository.getOne(1L)
				.getMovies()
				.stream()
				.filter(a -> a.getId() == 1L)
				.findAny()
				.orElseThrow(() -> new AssertionFailedError("Movie relation not found"));

		MovieEntity foundMovie = movieRepository.findById(1L).orElseThrow(() -> new AssertionFailedError("No movie found"));
		assertTrue(foundMovie.getKeywords().stream().anyMatch(a -> a.getId() == 1L), "Missing reverse connection movie -> keyword");
	}
}
