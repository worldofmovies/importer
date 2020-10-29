package se.david.moviesimporter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import se.david.moviesimporter.domain.entities.PersonEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;

	@Test
	public void duplicatesShouldNotThrowException() {
		PersonEntity personEntity = new PersonEntity(1, false, "name", 0.0, false);
		personRepository.saveAndFlush(personEntity);
		personRepository.saveAndFlush(personEntity);
		List<PersonEntity> result = personRepository.findAllById(Collections.singletonList(personEntity.getId()));

		assertEquals(1, result.size(), result.toString());
	}

	@Test
	public void findAllUnprocessed() {
		personRepository.saveAndFlush(new PersonEntity(1, false, "name1", 0.0, false));
		personRepository.saveAndFlush(new PersonEntity(2, false, "name2", 0.0, true));

		List<Long> allUnprocessed = personRepository.findAllUnprocessed();

		assertTrue(allUnprocessed.stream().anyMatch(a -> a == 1));
		assertFalse(allUnprocessed.stream().anyMatch(a -> a == 2));
	}
}
