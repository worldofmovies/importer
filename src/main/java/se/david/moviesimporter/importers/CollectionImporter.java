package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.tmdb.CollectionData;
import se.david.moviesimporter.repository.CollectionRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class CollectionImporter extends BaseImporter {
	@Autowired
	private CollectionRepository collectionRepository;

	public String processEntity(long collectionId) {
		String url = String.format("%s/3/collection/%s?api_key=%s&language=en-US", tmdbApiUrl, collectionId, apiKey);
		try {
			RestTemplateFetcher.fetchCollection(url)
					.ifPresentOrElse(handleProcessed(collectionId), handleDeleted(collectionId));
			return String.format("Fetched collection: %s", collectionId);
		} catch (IOException e) {
			e.printStackTrace();
			return String.format("Error fetching collection: %s. %s", collectionId, e.getMessage());
		}
	}

	private Runnable handleDeleted(long personId) {
		return () -> collectionRepository.deleteByIdWithTransaction(personId);
	}

	private Consumer<CollectionData> handleProcessed(long personId) {
		return result -> collectionRepository.findById(personId)
				.ifPresent(collection -> {
					collection.processInfo(result);
					collectionRepository.saveAndFlush(collection);
				});
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return collectionRepository.findAllUnprocessed();
	}
}
