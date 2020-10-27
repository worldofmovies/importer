package se.david.moviesimporter.importers;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.repository.KeywordRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class KeywordImporter extends BaseImporter {
	private static final String urlFormat = "%s/3/discover/movie?api_key=%s&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=%s&with_keywords=%s";
	private static final Logger log = getLogger(KeywordImporter.class);

	@Autowired
	private KeywordRepository keywordRepository;

	public String processEntity(long keywordId) {
		int page = 1;
		int processedPages = handlePagination(keywordId, page);
		return String.format("Processed: %s pages for keyword: %s", processedPages, keywordId);
	}

	private Integer handlePagination(long keywordId, int page) {
		String url = String.format(urlFormat, tmdbApiUrl, apiKey, page, keywordId);
		try {
			return RestTemplateFetcher.fetchKeyword(url)
					.flatMap(result -> keywordRepository.findById(keywordId)
							.map(keyword -> {
								keyword.processInfo(result);
								keywordRepository.saveAndFlush(keyword);
								if (result.getPage() < result.getTotalPages()) {
									return handlePagination(keywordId, result.getPage() + 1);
								} else {
									return result.getPage();
								}
							}))
					.orElseGet(() -> {
						keywordRepository.deleteByIdWithTransaction(keywordId);
						return 0;
					});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return keywordRepository.findAllUnprocessed();
	}
}
