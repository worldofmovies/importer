package se.david.moviesimporter.importers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseImporter {
	@Value("${tmdb.api.url:https://api.themoviedb.org}")
	protected String tmdbApiUrl;
	@Value("${tmdb.api.key}")
	protected String apiKey;

	public abstract String processEntity(long id);

	public abstract List<Long> findAllUnprocessed();
}
