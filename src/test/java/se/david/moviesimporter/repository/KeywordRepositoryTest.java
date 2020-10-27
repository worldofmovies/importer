package se.david.moviesimporter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class KeywordRepositoryTest {
	@Autowired
	private KeywordRepository keywordRepository;

	@Before
	public void setup() {
		keywordRepository.deleteAll();
	}

	@Test
	public void asd() {
		List<Long> insertedIds = keywordRepository.batchInsertUnprocessed(Arrays.asList(1L));

		assertEquals(Arrays.asList(1L), insertedIds);
		assertEquals(Arrays.asList(1L), keywordRepository.findAllUnprocessed());
	}

	@Test
	public void asd2() {
		keywordRepository.insertUnprocessed(2L);

		assertEquals(Arrays.asList(2L), keywordRepository.findAllUnprocessed());
	}
}
