package se.david.moviesimporter;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import se.david.moviesimporter.domain.entities.CollectionEntity;
import se.david.moviesimporter.domain.entities.CompanyEntity;
import se.david.moviesimporter.domain.entities.KeywordEntity;
import se.david.moviesimporter.domain.entities.MovieEntity;
import se.david.moviesimporter.domain.entities.PersonEntity;
import se.david.moviesimporter.domain.tmdb.CollectionId;
import se.david.moviesimporter.domain.tmdb.CompanyId;
import se.david.moviesimporter.domain.tmdb.KeywordId;
import se.david.moviesimporter.domain.tmdb.Movie;
import se.david.moviesimporter.domain.tmdb.Person;
import se.david.moviesimporter.repository.CollectionRepository;
import se.david.moviesimporter.repository.CompanyRepository;
import se.david.moviesimporter.repository.KeywordRepository;
import se.david.moviesimporter.repository.MovieRepository;
import se.david.moviesimporter.repository.PersonRepository;
import se.david.moviesimporter.util.FileReader;
import se.david.moviesimporter.util.JsonMapper;
import se.david.moviesimporter.util.RestTemplateFetcher;

/*
 * https://api.themoviedb.org/3/genre/movie/list?api_key={api_key}&language=en-US
 */
@Service
public class DailyImporter {
	private static final Logger log = getLogger(DailyImporter.class);
	private static final DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("MM_dd_yyyy");

	private final LocalDate localDate;
	private final KeywordRepository keywordRepository;
	private final MovieRepository movieRepository;
	private final CollectionRepository collectionRepository;
	private final PersonRepository personRepository;
	private final CompanyRepository companyRepository;

	@Value("${tmdb.files.url:http://files.tmdb.org}")
	private String tmdbFilesUrl;

	@Autowired
	public DailyImporter(KeywordRepository keywordRepository,
			MovieRepository movieRepository,
			CollectionRepository collectionRepository,
			PersonRepository personRepository,
			CompanyRepository companyRepository) {
		this.localDate = LocalDate.now(ZoneId.of("Europe/Stockholm")).minusDays(1);
		this.keywordRepository = keywordRepository;
		this.movieRepository = movieRepository;
		this.collectionRepository = collectionRepository;
		this.personRepository = personRepository;
		this.companyRepository = companyRepository;
	}

	public Flux<String> getCompanyIds() {
		String date = datetimeFormatter.format(localDate);
		String url = String.format("%s/p/exports/production_company_ids_%s.json.gz", tmdbFilesUrl, date);
		File file = RestTemplateFetcher.downloadFile(url);

		log.info("File downloaded");

		AtomicInteger counter = new AtomicInteger();

		int buffer = 1000;
		return Flux.fromStream(FileReader.readFile(file))
				.onBackpressureBuffer()
				.map(JsonMapper.mapProductionCompany())
				.filter(Objects::nonNull)
				.buffer(buffer)
				.parallel(5)
				.runOn(Schedulers.elastic())
				.map(companyDataList -> {
					List<CompanyEntity> found = companyRepository.findAllById(companyDataList.stream()
							.map(CompanyId::getId)
							.collect(Collectors.toList()));
					return companyDataList.stream()
							.filter(b -> found.stream().noneMatch(c -> c.getId() == b.getId()))
							.map(CompanyId::createEntity)
							.collect(Collectors.toList());
				})
				.map(productionCompanies -> Flux.fromIterable(companyRepository.saveAllWithTransaction(productionCompanies)))
				.filter(a -> counter.addAndGet(buffer) % 10_000 == 0)
				.map(a -> String.format("Processed: %s", counter.get()))
				.doOnNext(a -> log.info("Processed: {} production companies", counter.get()))
				.sequential();
	}

	public Flux<String> getKeywordIds() {
		String date = datetimeFormatter.format(localDate);
		String url = String.format("%s/p/exports/keyword_ids_%s.json.gz", tmdbFilesUrl, date);
		File file = RestTemplateFetcher.downloadFile(url);

		log.info("File downloaded");

		AtomicInteger counter = new AtomicInteger();

		int buffer = 100;
		return Flux.fromStream(FileReader.readFile(file))
				.onBackpressureBuffer()
				.map(JsonMapper.mapKeyword())
				.filter(Objects::nonNull)
				.buffer(buffer)
				.parallel(5)
				.runOn(Schedulers.elastic())
				.map(keywordDataList -> {
					List<KeywordEntity> found = keywordRepository.findAllById(keywordDataList.stream()
							.map(KeywordId::getId)
							.collect(Collectors.toList()));
					return keywordDataList.stream()
							.filter(b -> found.stream().noneMatch(c -> c.getId() == b.getId()))
							.map(KeywordId::createEntity)
							.collect(Collectors.toList());
				})
				.map(keywordRepository::saveAllWithTransaction)
				.filter(a -> counter.addAndGet(buffer) % 10_000 == 0)
				.map(a -> String.format("Processed: %s", counter.get()))
				.doOnNext(a -> log.info("Processed: {} keywords", counter.get()))
				.sequential();
	}

	public Flux<String> getPersonIds() {
		String date = datetimeFormatter.format(localDate);
		String url = String.format("%s/p/exports/person_ids_%s.json.gz", tmdbFilesUrl, date);
		File file = RestTemplateFetcher.downloadFile(url);

		log.info("File downloaded");

		AtomicInteger counter = new AtomicInteger();

		int buffer = 100;
		return Flux.fromStream(FileReader.readFile(file))
				.onBackpressureBuffer()
				.map(JsonMapper.mapPerson())
				.filter(Objects::nonNull)
				.filter(person -> !person.getAdult())
				.buffer(buffer)
				.parallel(5)
				.runOn(Schedulers.elastic())
				.map(personDataList -> {
					List<PersonEntity> found = personRepository.findAllById(personDataList.stream()
							.map(Person::getId)
							.collect(Collectors.toList()));
					return personDataList.stream()
							.filter(b -> found.stream().noneMatch(c -> c.getId() == b.getId()))
							.map(Person::createEntity)
							.collect(Collectors.toList());
				})
				.map(persons -> Flux.fromIterable(personRepository.saveAllWithTransaction(persons)))
				.filter(a -> counter.addAndGet(buffer) % 10_000 == 0)
				.map(a -> String.format("Processed: %s", counter.get()))
				.doOnNext(a -> log.info("Processed: {} persons", counter.get()))
				.sequential();
	}

	public Flux<String> getMovieIds() {
		String date = datetimeFormatter.format(localDate);
		String url = String.format("%s/p/exports/movie_ids_%s.json.gz", tmdbFilesUrl, date);
		File file = RestTemplateFetcher.downloadFile(url);

		log.info("File downloaded");

		AtomicInteger counter = new AtomicInteger();

		int buffer = 1000;
		return Flux.fromStream(FileReader.readFile(file))
				.onBackpressureBuffer()
				.map(JsonMapper.mapMovie())
				.filter(Objects::nonNull)
				.filter(movie -> !movie.isAdult())
				.buffer(buffer)
				.parallel(5)
				.runOn(Schedulers.elastic())
				.map(movieDataList -> {
					List<MovieEntity> found = movieRepository.findAllById(movieDataList.stream()
							.map(Movie::getId)
							.collect(Collectors.toList()));
					return movieDataList.stream()
							.filter(b -> found.stream().noneMatch(c -> c.getId() == b.getId()))
							.map(Movie::createEntity)
							.collect(Collectors.toList());
				})
				.map(movies -> Flux.fromIterable(movieRepository.saveAllWithTransaction(movies)))
				.filter(a -> counter.addAndGet(buffer) % 10_000 == 0)
				.map(movie -> String.format("Processed: %s", counter.get()))
				.doOnNext(a -> log.info("Processed: {} movies", counter.get()))
				.sequential();
	}

	public Flux<String> getCollectionIds() {
		String date = datetimeFormatter.format(localDate);
		String url = String.format("%s/p/exports/collection_ids_%s.json.gz", tmdbFilesUrl, date);
		File file = RestTemplateFetcher.downloadFile(url);

		log.info("File downloaded");

		AtomicInteger counter = new AtomicInteger();

		int buffer = 1000;
		return Flux.fromStream(FileReader.readFile(file))
				.onBackpressureBuffer()
				.map(JsonMapper.mapCollection())
				.filter(Objects::nonNull)
				.buffer(buffer)
				.parallel(5)
				.runOn(Schedulers.elastic())
				.map(collectionDataList -> {
					List<CollectionEntity> found = collectionRepository.findAllById(collectionDataList.stream()
							.map(CollectionId::getId)
							.collect(Collectors.toList()));
					return collectionDataList.stream()
							.filter(b -> found.stream().noneMatch(c -> c.getId() == b.getId()))
							.map(CollectionId::createEntity)
							.collect(Collectors.toList());
				})
				.map(collections -> Flux.fromIterable(collectionRepository.saveAllWithTransaction(collections)))
				.sequential()
				.filter(a -> counter.addAndGet(buffer) % 1000 == 0)
				.map(movie -> String.format("Processed: %s", counter.get()))
				.doOnNext(a -> log.info("Processed: {} collections", counter.get()));
	}
}
