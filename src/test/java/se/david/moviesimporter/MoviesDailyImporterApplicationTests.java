package se.david.moviesimporter;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.tomakehurst.wiremock.client.WireMock;

import se.david.moviesimporter.domain.entities.CollectionEntity;
import se.david.moviesimporter.domain.entities.KeywordEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;
import se.david.moviesimporter.domain.entities.PersonEntity;
import se.david.moviesimporter.domain.entities.CompanyEntity;
import se.david.moviesimporter.repository.CollectionRepository;
import se.david.moviesimporter.repository.KeywordRepository;
import se.david.moviesimporter.repository.MovieRepository;
import se.david.moviesimporter.repository.PersonRepository;
import se.david.moviesimporter.repository.CompanyRepository;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = MoviesImporterApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 9999)
class MoviesDailyImporterApplicationTests {
	@Autowired
	private WebTestClient webClient;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private KeywordRepository keywordRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@BeforeEach
	public void setup() {
		personRepository.deleteAll();
		keywordRepository.deleteAll();
		movieRepository.deleteAll();
		collectionRepository.deleteAll();
		companyRepository.deleteAll();
	}

	@Nested
	public class IdImports {
		@Test
		void importProductionCompanies() throws IOException {
			ClassPathResource resource = new ClassPathResource("production_companies.json.gz");

			String url = "/p/exports/production_company_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/company/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
			assertEquals(10, companyRepository.findAllUnprocessed().size());
		}

		@Test
		void importProductionCompaniesTwice() throws IOException {
			ClassPathResource resource = new ClassPathResource("production_companies.json.gz");

			String url = "/p/exports/production_company_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/company/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			webClient.get().uri("/import/company/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			assertEquals(10, companyRepository.findAllUnprocessed().size());
		}

		@Test
		void importKeywordIds() throws IOException {
			ClassPathResource resource = new ClassPathResource("keywords.json.gz");

			String url = "/p/exports/keyword_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/keyword/ids")
					.exchange()
					.expectStatus().is2xxSuccessful()
					.returnResult(String.class)
					.getResponseBody()
					.collectList()
					.block();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));

			assertEquals(10, keywordRepository.findAllUnprocessed().size());
			;
		}

		@Test
		void importKeywordIdsTwice() throws IOException {
			ClassPathResource resource = new ClassPathResource("keywords.json.gz");

			String url = "/p/exports/keyword_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/keyword/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			webClient.get().uri("/import/keyword/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
			assertEquals(10, keywordRepository.findAllUnprocessed().size());
		}

		@Test
		void importMovieIds() throws IOException {
			ClassPathResource resource = new ClassPathResource("movies.json.gz");

			String url = "/p/exports/movie_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/movie/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
			assertEquals(10, movieRepository.findAllUnprocessed().size());
		}

		@Test
		void importPersonIds() throws IOException {
			ClassPathResource resource = new ClassPathResource("persons.json.gz");

			String url = "/p/exports/person_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/person/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
			assertEquals(10, personRepository.findAllUnprocessed().size());
		}

		@Test
		void importCollectionIds() throws IOException {
			ClassPathResource resource = new ClassPathResource("collection.json.gz");

			String url = "/p/exports/collection_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/collection/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
		}

		@Test
		public void importAllIds() throws IOException {
			String personUrl = "/p/exports/person_ids_.*.json.gz";
			stubEndpoint(new ClassPathResource("persons.json.gz"), personUrl);

			String companyUrl = "/p/exports/production_company_ids_.*.json.gz";
			stubEndpoint(new ClassPathResource("production_companies.json.gz"), companyUrl);

			String collectionUrl = "/p/exports/collection_ids_.*.json.gz";
			stubEndpoint(new ClassPathResource("collection.json.gz"), collectionUrl);

			String movieUrl = "/p/exports/movie_ids_.*.json.gz";
			stubEndpoint(new ClassPathResource("movies.json.gz"), movieUrl);

			String keywordUrl = "/p/exports/keyword_ids_.*.json.gz";
			stubEndpoint(new ClassPathResource("keywords.json.gz"), keywordUrl);

			List<String> result = webClient.get().uri("/import/ids")
					.exchange()
					.expectStatus().is2xxSuccessful()
					.returnResult(String.class)
					.getResponseBody()
					.collectList()
					.block(Duration.ofSeconds(3));

			assertNotNull(result, "Result must not be null");
			//assertFalse(result.isEmpty(), "Result must not be empty");

			verify(WireMock.getRequestedFor(WireMock.urlMatching(keywordUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(personUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(companyUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(collectionUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(movieUrl)));
			assertEquals(10, keywordRepository.findAllUnprocessed().size());
			assertEquals(10, personRepository.findAllUnprocessed().size());
			assertEquals(10, collectionRepository.findAllUnprocessed().size());
			assertEquals(10, movieRepository.findAllUnprocessed().size());
			assertEquals(10, companyRepository.findAllUnprocessed().size());
		}
	}

	@Nested
	public class UnprocessedImports {
		@Test
		void importPersons() throws IOException {
			personRepository.saveAndFlush(new PersonEntity(1, false, "name", 0.0, false));

			ClassPathResource resource = new ClassPathResource("person.json");

			String url = "/3/person/1\\?api_key=.+&language=en-US&append_to_response=images,external_ids";
			stubEndpointForJson(resource, url);

			webClient.get().uri("/import/person/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
		}

		@Test
		void importMovies() throws IOException {
			movieRepository.saveAndFlush(new MovieEntity(1, false, "name", 0.0, false, false));

			ClassPathResource resource = new ClassPathResource("movie.json");

			String url = "/3/movie/1\\?api_key=API_KEY&language=en-US&append_to_response=alternative_titles,external_ids,images,credits";
			stubEndpointForJson(resource, url);

			webClient.get().uri("/import/movie/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
		}

		@Test
		void importKeywords() throws IOException {
			keywordRepository.saveAndFlush(new KeywordEntity(378, "name", false));

			ClassPathResource resource = new ClassPathResource("keyword_378_page1.json");

			String url = "/3/discover/movie\\?api_key=API_KEY&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_keywords=378";
			stubEndpointForJson(resource, url);

			ClassPathResource resource2 = new ClassPathResource("keyword_378_page2.json");
			String url2 = "/3/discover/movie\\?api_key=API_KEY&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=2&with_keywords=378";
			stubEndpointForJson(resource2, url2);

			webClient.get().uri("/import/keyword/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(url2)));
		}

		@Test
		void importCompanies() throws IOException {
			companyRepository.saveAndFlush(new CompanyEntity(378, false));

			ClassPathResource resource = new ClassPathResource("production_company.json");

			String url = "/3/company/378\\?api_key=API_KEY";
			stubEndpointForJson(resource, url);

			webClient.get().uri("/import/company/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
		}

		@Test
		void importCollections() throws IOException {
			collectionRepository.saveAndFlush(new CollectionEntity(1, "name", false));

			ClassPathResource resource = new ClassPathResource("collection.json");

			String url = "/3/collection/1\\?api_key=API_KEY&language=en-US";
			stubEndpointForJson(resource, url);

			webClient.get().uri("/import/collection/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
		}

		@Test
		void importAll() throws IOException {
			personRepository.saveAndFlush(new PersonEntity(1, false, "name", 0.0, false));
			movieRepository.saveAndFlush(new MovieEntity(1, false, "name", 0.0, false, false));
			keywordRepository.saveAndFlush(new KeywordEntity(378, "name", false));
			companyRepository.saveAndFlush(new CompanyEntity(378, false));
			collectionRepository.saveAndFlush(new CollectionEntity(1, "name", false));

			String personUrl = "/3/person/1\\?api_key=.+&language=en-US&append_to_response=images,external_ids";
			stubEndpointForJson(new ClassPathResource("person.json"), personUrl);

			String movieUrl = "/3/movie/1\\?api_key=API_KEY&language=en-US&append_to_response=alternative_titles,external_ids,images,credits";
			stubEndpointForJson(new ClassPathResource("movie.json"), movieUrl);

			String keywordUrl = "/3/discover/movie\\?api_key=API_KEY&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_keywords=378";
			stubEndpointForJson(new ClassPathResource("keyword_378_page1.json"), keywordUrl);
			String keywordUrl2 = "/3/discover/movie\\?api_key=API_KEY&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=2&with_keywords=378";
			stubEndpointForJson(new ClassPathResource("keyword_378_page2.json"), keywordUrl2);

			String companyUrl = "/3/company/378\\?api_key=API_KEY";
			stubEndpointForJson(new ClassPathResource("production_company.json"), companyUrl);

			String collectionUrl = "/3/collection/1\\?api_key=API_KEY&language=en-US";
			stubEndpointForJson(new ClassPathResource("collection.json"), collectionUrl);

			List<String> result = webClient.get().uri("/import/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful()
					.returnResult(String.class)
					.getResponseBody()
					.collectList()
					.block(Duration.ofSeconds(3));

			assertNotNull(result);
			System.out.println(result);

			verify(WireMock.getRequestedFor(WireMock.urlMatching(collectionUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(personUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(movieUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(keywordUrl)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(keywordUrl2)));
			verify(WireMock.getRequestedFor(WireMock.urlMatching(companyUrl)));
		}

	}

	private void stubEndpoint(ClassPathResource resource, String url) throws IOException {
		stubFor(WireMock.get(WireMock.urlMatching(url)).willReturn(
				aResponse()
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
						.withBody(Files.readAllBytes(resource.getFile().toPath())))
		);
	}

	private void stubEndpointForJson(ClassPathResource resource, String url) throws IOException {
		stubFor(WireMock.get(WireMock.urlMatching(url)).willReturn(
				aResponse()
						.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withBody(Files.readAllBytes(resource.getFile().toPath())))
		);
	}

}
