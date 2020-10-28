package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import se.david.moviesimporter.domain.tmdb.CountryData;
import se.david.moviesimporter.domain.tmdb.GenreData;
import se.david.moviesimporter.domain.tmdb.LanguageData;
import se.david.moviesimporter.repository.CountryRepository;
import se.david.moviesimporter.repository.GenreRepository;
import se.david.moviesimporter.repository.LanguageRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class ConfigurationsImporter {
	@Value("${tmdb.api.url:https://api.themoviedb.org}")
	protected String tmdbApiUrl;
	@Value("${tmdb.api.key}")
	protected String apiKey;

	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private GenreRepository genreRepository;

	public Flux<String> importAllConfigurations() {
		return Flux.concat(
				Mono.fromCallable(this::importCountries),
				Mono.fromCallable(this::importLanguages),
				Mono.fromCallable(this::importGenres))
				.parallel(3)
				.runOn(Schedulers.elastic())
				.sequential();
	}

	private String importCountries() {
		String url = String.format("%s/3/configuration/countries?api_key=%s", tmdbApiUrl, apiKey);
		try {
			RestTemplateFetcher.fetch(url, CountryData[].class)
					.ifPresent(countries -> countryRepository.saveAll(Arrays.stream(countries)
							.map(CountryData::createEntity)
							.collect(Collectors.toList())));
			return "Done importing countries";
		} catch (IOException e) {
			return String.format("Error importing countries: %s", e.getMessage());
		}
	}

	private String importLanguages() {
		String url = String.format("%s/3/configuration/languages?api_key=%s", tmdbApiUrl, apiKey);
		try {
			RestTemplateFetcher.fetch(url, LanguageData[].class)
					.ifPresent(countries -> languageRepository.saveAll(Arrays.stream(countries)
							.map(LanguageData::createEntity)
							.collect(Collectors.toList())));
			return "Done importing languages";
		} catch (IOException e) {
			return String.format("Error importing languages: %s", e.getMessage());
		}
	}

	private String importGenres() {
		String url = String.format("%s/3/genre/movie/list?api_key=%s&language=en-US", tmdbApiUrl, apiKey);
		try {
			RestTemplateFetcher.fetch(url, GenreData.class)
					.ifPresent(countries -> genreRepository.saveAll(countries.getGenres().stream()
							.map(GenreData.Genre::createEntity)
							.collect(Collectors.toList())));
			return "Done importing genres";
		} catch (IOException e) {
			return String.format("Error importing genres: %s", e.getMessage());
		}
	}
}
