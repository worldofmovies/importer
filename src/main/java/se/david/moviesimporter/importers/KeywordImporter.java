package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.tmdb.Keyword;
import se.david.moviesimporter.repository.KeywordRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class KeywordImporter extends BaseImporter {
	@Autowired
	private KeywordRepository keywordRepository;

	public String processEntity(long keywordId) {
		int page = 1;
		String url = String.format("%s/3/discover/movie?api_key=%s&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=%s&with_keywords=%s", tmdbApiUrl, apiKey, page, keywordId);
		try {
			RestTemplateFetcher.fetchKeyword(url)
					.ifPresentOrElse(handleProcessedKeyword(keywordId), handleDeletedKeyword(keywordId));
			return "Done";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Runnable handleDeletedKeyword(long keywordId) {
		return () -> keywordRepository.deleteByIdWithTransaction(keywordId);
	}

	private Consumer<Keyword> handleProcessedKeyword(long keywordId) {
		return result -> {
			keywordRepository.findById(keywordId)
					.ifPresent(keyword -> {
						keyword.processInfo(result);
						keywordRepository.saveAndFlush(keyword);
					});
		};
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return keywordRepository.findAllUnprocessed();
	}
}
