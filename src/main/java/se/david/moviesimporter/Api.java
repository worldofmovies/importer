package se.david.moviesimporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class Api {

	@Autowired
	private DailyImporter dailyImporter;
	@Autowired
	private Importer importer;

	@GetMapping(path = "/import/company/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> dailyFileOnCompanies() {
		return dailyImporter.getCompanyIds();
	}

	@GetMapping(path = "/import/keyword/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> dailyFileOnKeywordIds() {
		return dailyImporter.getKeywordIds();
	}

	@GetMapping(path = "/import/movie/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> dailyFileOnMovieIds() {
		return dailyImporter.getMovieIds();
	}

	@GetMapping(path = "/import/person/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> dailyFileOnPersonIds() {
		return dailyImporter.getPersonIds();
	}

	@GetMapping(path = "/import/collection/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> dailyFileOnCollectionIds() {
		return dailyImporter.getCollectionIds();
	}

	@GetMapping(path = "/import/person/unprocessed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchPersons() {
		return Flux.fromIterable(importer.findUnprocessedPersons())
				.parallel(5)
				.map(id -> importer.importPerson(id))
				.sequential();
	}

	@GetMapping(path = "/import/movie/unprocessed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchMovies() {
		return Flux.fromIterable(importer.findUnprocessedMovies())
				.parallel(5)
				.map(id -> importer.importMovie(id))
				.sequential();
	}

	@GetMapping(path = "/import/keyword/unprocessed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchKeywords() {
		return Flux.fromIterable(importer.findUnprocessedKeywords())
				.parallel(5)
				.map(id -> importer.importKeywords(id))
				.sequential();
	}

	@GetMapping(path = "/import/collection/unprocessed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchCollections() {
		return Flux.fromIterable(importer.findUnprocessedCollections())
				.parallel(5)
				.map(id -> importer.importCollection(id))
				.sequential();
	}

	@GetMapping(path = "/import/company/unprocessed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchCompanies() {
		return Flux.fromIterable(importer.findUnprocessedCompanies())
				.parallel(5)
				.map(id -> importer.importCompany(id))
				.sequential();
	}

	@GetMapping(path = "/import/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchAllIds() {
		return Flux.concat(dailyFileOnKeywordIds(),
				dailyFileOnCompanies(),
				dailyFileOnCollectionIds(),
				dailyFileOnPersonIds(),
				dailyFileOnMovieIds())
				.onBackpressureBuffer();
	}

	@GetMapping(path = "/import/unprocessed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> fetchAllUnprocesseds() {
		return Flux.concat(fetchCompanies(),
				fetchPersons(),
				fetchMovies(),
				fetchKeywords(),
				fetchCollections())
				.onBackpressureBuffer();
	}
}
