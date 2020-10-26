package se.david.moviesimporter;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import java.io.IOException;
import java.nio.file.Files;

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

import se.david.moviesimporter.domain.entities.KeywordEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;
import se.david.moviesimporter.domain.entities.PersonEntity;
import se.david.moviesimporter.repository.KeywordRepository;
import se.david.moviesimporter.repository.MovieRepository;
import se.david.moviesimporter.repository.PersonRepository;

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

	@BeforeEach
	public void setup() {
		personRepository.deleteAll();
		keywordRepository.deleteAll();
		movieRepository.deleteAll();
	}

	@Nested
	public class IdImports {
		@Test
		void importProductionCompanies() throws IOException {
			ClassPathResource resource = new ClassPathResource("production_companies.json.gz");

			String url = "/p/exports/production_company_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/production-company/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
		}

		@Test
		void importProductionCompaniesTwice() throws IOException {
			ClassPathResource resource = new ClassPathResource("production_companies.json.gz");

			String url = "/p/exports/production_company_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/production-company/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			webClient.get().uri("/import/production-company/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();
		}

		@Test
		void importKeywordIds() throws IOException {
			ClassPathResource resource = new ClassPathResource("keywords.json.gz");

			String url = "/p/exports/keyword_ids_.*.json.gz";
			stubEndpoint(resource, url);

			webClient.get().uri("/import/keyword/ids")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
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
		}
	}

	@Nested
	public class UnprocessedImports {

		@Test
		void importPersons() throws IOException {
			personRepository.saveAndFlush(new PersonEntity(1, false, "name", 0.0, false));

			ClassPathResource resource = new ClassPathResource("person.json");

			String url = "/3/person/1\\?api_key=.+&language=en-US&append_to_response=images,movie_credits,external_ids";
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

			String url = "/3/movie/1\\?api_key=API_KEY&language=en-US&append_to_response=alternative_titles,keywords,external_ids,images,credits";
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
			//stubEndpointForJson(resource2, url2);

			webClient.get().uri("/import/keyword/unprocessed")
					.exchange()
					.expectStatus().is2xxSuccessful();

			verify(WireMock.getRequestedFor(WireMock.urlMatching(url)));
			//verify(WireMock.getRequestedFor(WireMock.urlMatching(url2)));
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
