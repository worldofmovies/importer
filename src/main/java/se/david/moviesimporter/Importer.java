package se.david.moviesimporter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.importers.CollectionImporter;
import se.david.moviesimporter.importers.CompanyImporter;
import se.david.moviesimporter.importers.KeywordImporter;
import se.david.moviesimporter.importers.MovieImporter;
import se.david.moviesimporter.importers.PersonImporter;

@Service
public class Importer {
	@Value("${tmdb.api.url:https://api.themoviedb.org}")
	private String tmdbApiUrl;
	@Value("${tmdb.api.key}")
	private String apiKey;

	@Autowired
	private PersonImporter personImporter;
	@Autowired
	private KeywordImporter keywordImporter;
	@Autowired
	private MovieImporter movieImporter;
	@Autowired
	private CompanyImporter companyImporter;
	@Autowired
	private CollectionImporter collectionImporter;

	public List<Long> findUnprocessedMovies() {
		return movieImporter.findAllUnprocessed();
	}

	public String importMovie(long movieId) {
		return movieImporter.processEntity(movieId);
	}

	public String importPerson(long personId) {
		return personImporter.processEntity(personId);
	}

	public List<Long> findUnprocessedPersons() {
		return personImporter.findAllUnprocessed();
	}

	public String importKeywords(long keywordId) {
		return keywordImporter.processEntity(keywordId);
	}

	public List<Long> findUnprocessedKeywords() {
		return keywordImporter.findAllUnprocessed();
	}

	public String importCompany(long companyId) {
		return companyImporter.processEntity(companyId);
	}

	public List<Long> findUnprocessedCompanies() {
		return companyImporter.findAllUnprocessed();
	}

	public String importCollection(long id) {
		return collectionImporter.processEntity(id);
	}

	public List<Long> findUnprocessedCollections() {
		return collectionImporter.findAllUnprocessed();
	}

}
