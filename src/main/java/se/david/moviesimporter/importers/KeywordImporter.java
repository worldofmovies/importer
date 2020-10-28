package se.david.moviesimporter.importers;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.entities.MovieEntity;
import se.david.moviesimporter.domain.tmdb.KeywordData;
import se.david.moviesimporter.repository.KeywordRepository;
import se.david.moviesimporter.repository.MovieRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class KeywordImporter extends BaseImporter {
	private static final String urlFormat = "%s/3/discover/movie?api_key=%s&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=%s&with_keywords=%s";
	private static final Logger log = getLogger(KeywordImporter.class);

	@Autowired
	private KeywordRepository keywordRepository;
	@Autowired
	private MovieRepository movieRepository;

	public String processEntity(long keywordId) {
		int page = 1;
		return handlePagination(keywordId, page)
				.map(processedPages -> String.format("Fetched keyword: %s pages: %s", keywordId, processedPages))
				.orElse(String.format("Error fetching keyword: %s.", keywordId));
	}

	private Optional<Integer> handlePagination(long keywordId, int page) {
		String url = String.format(urlFormat, tmdbApiUrl, apiKey, page, keywordId);
		try {
			return RestTemplateFetcher.fetch(url, KeywordData.class)
					.flatMap(result -> keywordRepository.findById(keywordId)
							.map(keyword -> {
								List<MovieEntity> movies = movieRepository.findAllById(result.getResults()
										.stream()
										.map(KeywordData.Result::getId)
										.collect(Collectors.toList()));
								keyword.processInfo(movies);
								keywordRepository.saveAndFlush(keyword);
								if (result.getPage() < result.getTotalPages()) {
									return handlePagination(keywordId, result.getPage() + 1);
								} else {
									return Optional.of(result.getPage());
								}
							}))
					.orElseGet(() -> {
						keywordRepository.deleteByIdWithTransaction(keywordId);
						return Optional.of(0);
					});
		} catch (IOException e) {
			log.error("IOException calling url: {} with message: {}", url, e.getMessage(), e);
			return Optional.empty();
		}
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return keywordRepository.findAllUnprocessed();
	}
}
