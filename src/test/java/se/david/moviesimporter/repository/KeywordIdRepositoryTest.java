package se.david.moviesimporter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import se.david.moviesimporter.domain.entities.KeywordEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class KeywordIdRepositoryTest {
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
	public void testKeywordMovieRelation() {
		KeywordEntity unprocessedKeyword = keywordRepository.saveAndFlush(new KeywordEntity(1, "name", false));

		MovieEntity unprocessedMovie = movieRepository.saveAndFlush(new MovieEntity(1, false, "title", 0.0, false, false));

		unprocessedKeyword.processInfo(Arrays.asList(unprocessedMovie));
		keywordRepository.saveAndFlush(unprocessedKeyword);

		keywordRepository.getOne(1L)
				.getMovies()
				.stream()
				.filter(a -> a.getId() == 1L)
				.findAny()
				.orElseThrow(() -> new AssertionFailedError("Movie relation not found"));
	}
}
