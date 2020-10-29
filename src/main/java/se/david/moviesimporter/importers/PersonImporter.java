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
	private final PersonRepository personRepository;

	public PersonImporter(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public String processEntity(long personId) {
		String url = String.format("%s/3/person/%s?api_key=%s&language=en-US&append_to_response=images,external_ids", tmdbApiUrl, personId, apiKey);
		try {
			RestTemplateFetcher.fetch(url, Person.class)
					.ifPresentOrElse(handleProcessedPerson(personId), handleDeletedPerson(personId));
			return String.format("Fetched person: %s", personId);
		} catch (IOException e) {
			e.printStackTrace();
			return String.format("Error fetching person: %s. %s", personId, e.getMessage());
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
