package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.tmdb.Person;
import se.david.moviesimporter.repository.PersonRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class PersonImporter extends BaseImporter {
	@Autowired
	private PersonRepository personRepository;

	public String processEntity(long personId) {
		String url = String.format("%s/3/person/%s?api_key=%s&language=en-US&append_to_response=images,movie_credits,external_ids", tmdbApiUrl, personId, apiKey);
		try {
			RestTemplateFetcher.fetchPerson(url)
					.ifPresentOrElse(handleProcessedPerson(personId), handleDeletedPerson(personId));
			return "Done";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Runnable handleDeletedPerson(long personId) {
		return () -> personRepository.deleteByIdWithTransaction(personId);
	}

	private Consumer<Person> handleProcessedPerson(long personId) {
		return result -> personRepository.findById(personId)
				.ifPresent(person -> {
					person.processInfo(result);
					personRepository.saveAndFlush(person);
				});
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return personRepository.findAllUnprocessed();
	}
}
