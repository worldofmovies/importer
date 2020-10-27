package se.david.moviesimporter.domain.tmdb;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import se.david.moviesimporter.domain.entities.MovieEntity;

public class Movie {
	private long id;
	private boolean adult;
	@JsonProperty("backdrop_path")
	private String backdropPath;
	private CollectionId belongsToCollection;
	private Long budget;
	private List<Genre> genres;
	private String homepage;
	@JsonProperty("imdb_id")
	private String imdbId;
	@JsonProperty("original_language")
	private String originalLanguage;
	@JsonProperty("original_title")
	private String originalTitle;
	private String overview;
	private Double popularity;
	@JsonProperty("poster_path")
	private String posterPath;
	@JsonProperty("production_companies")
	private List<CompanyId> productionCompanies;
	@JsonProperty("production_countries")
	private List<ProductionCountry> productionCountries;
	@JsonProperty("release_date")
	private LocalDate releaseDate;
	private Long revenue;
	private Long runtime;
	@JsonProperty("spoken_languages")
	private List<SpokenLanguage> spokenLanguages;
	private String status; // TODO Enum?
	private String tagline;
	private String title;
	private Boolean video;
	@JsonProperty("vote_average")
	private Double voteAverage;
	@JsonProperty("vote_count")
	private Integer voteCount;
	@JsonProperty("alternative_titles")
	private AlternativeTitles alternativeTitles;
	private Keywords keywords;
	@JsonProperty("external_ids")
	private ExternalIds externalIds;
	private Images images;
	private Credits credits;

	public Movie() {
	}

	public Movie(long id, boolean adult, String backdropPath, CollectionId belongsToCollection, Long budget, List<Genre> genres, String homepage,
			String imdbId, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, List<CompanyId> productionCompanies,
			List<ProductionCountry> productionCountries, LocalDate releaseDate, Long revenue, Long runtime, List<SpokenLanguage> spokenLanguages, String status,
			String tagline, String title, Boolean video, Double voteAverage, Integer voteCount, AlternativeTitles alternativeTitles, Keywords keywords,
			ExternalIds externalIds, Images images, Credits credits) {
		this.id = id;
		this.adult = adult;
		this.backdropPath = backdropPath;
		this.belongsToCollection = belongsToCollection;
		this.budget = budget;
		this.genres = genres;
		this.homepage = homepage;
		this.imdbId = imdbId;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.popularity = popularity;
		this.posterPath = posterPath;
		this.productionCompanies = productionCompanies;
		this.productionCountries = productionCountries;
		this.releaseDate = releaseDate;
		this.revenue = revenue;
		this.runtime = runtime;
		this.spokenLanguages = spokenLanguages;
		this.status = status;
		this.tagline = tagline;
		this.title = title;
		this.video = video;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.alternativeTitles = alternativeTitles;
		this.keywords = keywords;
		this.externalIds = externalIds;
		this.images = images;
		this.credits = credits;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public CollectionId getBelongsToCollection() {
		return belongsToCollection;
	}

	public void setBelongsToCollection(CollectionId belongsToCollection) {
		this.belongsToCollection = belongsToCollection;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public List<CompanyId> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<CompanyId> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	public List<ProductionCountry> getProductionCountries() {
		return productionCountries;
	}

	public void setProductionCountries(List<ProductionCountry> productionCountries) {
		this.productionCountries = productionCountries;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public Long getRuntime() {
		return runtime;
	}

	public void setRuntime(Long runtime) {
		this.runtime = runtime;
	}

	public List<SpokenLanguage> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getVideo() {
		return video;
	}

	public void setVideo(Boolean video) {
		this.video = video;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public AlternativeTitles getAlternativeTitles() {
		return alternativeTitles;
	}

	public void setAlternativeTitles(AlternativeTitles alternativeTitles) {
		this.alternativeTitles = alternativeTitles;
	}

	public Keywords getKeywords() {
		return keywords;
	}

	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public ExternalIds getExternalIds() {
		return externalIds;
	}

	public void setExternalIds(ExternalIds externalIds) {
		this.externalIds = externalIds;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public Credits getCredits() {
		return credits;
	}

	public void setCredits(Credits credits) {
		this.credits = credits;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Movie movie = (Movie) o;
		return id == movie.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@JsonIgnore
	public MovieEntity createEntity() {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setAdult(adult);
		movieEntity.setId(id);
		movieEntity.setOriginalTitle(originalTitle);
		movieEntity.setPopularity(popularity);
		movieEntity.setProcessed(false);
		movieEntity.setVideo(video);
		return movieEntity;
	}
}
