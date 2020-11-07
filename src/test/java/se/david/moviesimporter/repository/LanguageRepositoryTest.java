package se.david.moviesimporter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import junit.framework.AssertionFailedError;
import se.david.moviesimporter.domain.entities.CountryEntity;
import se.david.moviesimporter.domain.entities.LanguageEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LanguageRepositoryTest {
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CountryRepository countryRepository;

	@Before
	public void setup() {
		countryRepository.deleteAll();
		languageRepository.deleteAll();
		movieRepository.deleteAll();
	}

	@Test
	public void languageMovieRelation() {
		countryRepository.saveAndFlush(new CountryEntity("SE", "Sweden", new ArrayList<>()));
		LanguageEntity language = languageRepository.saveAndFlush(new LanguageEntity("sv", "Swedish", "Svenska", new ArrayList<>()));

		MovieEntity unprocessedMovie = movieRepository.saveAndFlush(new MovieEntity(1, false, "title", 0.0, false, false));

		unprocessedMovie.processLanguages(Arrays.asList(language), language);

		movieRepository.saveAndFlush(unprocessedMovie);

		languageRepository.getOne("sv")
				.getMovies()
				.stream()
				.filter(a -> a.getId() == 1L)
				.findAny()
				.orElseThrow(() -> new AssertionFailedError("Movie relation not found"));

		MovieEntity foundMovie = movieRepository.findById(1L).orElseThrow(() -> new AssertionFailedError("No movie found"));
		assertTrue(foundMovie.getSpokenLanguages().stream().anyMatch(a -> "sv".equals(a.getIso_639_1())), "Missing reverse connection movie -> language");
		assertEquals("sv", foundMovie.getOriginalLanguage().getIso_639_1(), "Missing reverse connection movie -> orig_language");

		assertTrue(language.getMovies().stream().anyMatch(a -> a.getId() == 1));
	}
}
