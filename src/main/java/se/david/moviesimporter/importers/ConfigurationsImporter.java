package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.david.moviesimporter.domain.entities.CountryEntity;
import se.david.moviesimporter.domain.entities.LanguageEntity;
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
				doCoolStuff(),
				Mono.fromCallable(this::importGenres));
	}

	private Mono<String> doCoolStuff() {
		Map<String, List<String>> countryLanguageMap = Arrays.stream(Locale.getAvailableLocales())
				.collect(Collectors.groupingBy(Locale::getCountry, Collectors.mapping(Locale::getLanguage, Collectors.toList())));

		return Mono.zip(importCountries(), importLanguages())
				.map(tuple -> {
					List<CountryData> countries = tuple.getT1();
					List<LanguageData> languages = tuple.getT2();

					Map<String, LanguageEntity> persistedLanguages = languageRepository.saveAll(languages.stream()
							.map(LanguageData::createEntity)
							.collect(Collectors.toList()))
							.stream()
							.collect(Collectors.toMap(LanguageEntity::getIso_639_1, entity -> entity));

					List<CountryEntity> countryEntities = countries.stream()
							.map(country -> {
								List<LanguageEntity> languageCodes = Optional.ofNullable(countryLanguageMap.get(country.getIso_3166_1()))
										.map(languageCode -> languageCode
												.stream()
												.map(persistedLanguages::get)
												.collect(Collectors.toList()))
										.orElse(Collections.emptyList());
								return country.createEntity(languageCodes);
							})
							.collect(Collectors.toList());

					countryRepository.saveAll(countryEntities);

					return "DONE";
				});
	}

	private Mono<List<CountryData>> importCountries() {
		String url = String.format("%s/3/configuration/countries?api_key=%s", tmdbApiUrl, apiKey);
		return Mono.fromCallable(() -> RestTemplateFetcher.fetch(url, CountryData[].class))
				.map(a -> a.map(b -> Stream.of(b).collect(Collectors.toList()))
						.orElse(Collections.emptyList()));
	}

	private Mono<List<LanguageData>> importLanguages() {
		String url = String.format("%s/3/configuration/languages?api_key=%s", tmdbApiUrl, apiKey);
		return Mono.fromCallable(() -> RestTemplateFetcher.fetch(url, LanguageData[].class))
				.map(a -> a.map(b -> Stream.of(b).collect(Collectors.toList()))
						.orElse(Collections.emptyList()));
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
