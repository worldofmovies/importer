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

	@GetMapping(path = "/import/production-company/ids", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<String> dailyFileOnProductionCompanies() {
		return dailyImporter.getProductionCompanyIds();
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
}
