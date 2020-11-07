package se.david.moviesimporter.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.david.moviesimporter.domain.tmdb.Movie;

/**
 * {"adult":false,"id":3924,"original_title":"Blondie","popularity":2.405,"video":false}
 */
@Entity(name = "Movie")
@Table(name = "movie")
public class MovieEntity {
	@Id
	private long id;
	@Transient
	private boolean adult;
	@JsonProperty("original_title")
	@Column(length = 512)
	private String originalTitle;
	private double popularity;
	private boolean video;
	private boolean processed;
	private String backdropPath;
	private Long budget;
	private String homepage;
	private String imdbId;
	@Column(length = 2048)
	private String overview;
	private String posterPath;
	private LocalDate releaseDate;
	private Long revenue;
	private Long runtime;
	private String status;
	private String tagline;
	private String title;
	private Double voteAverage;
	private Integer voteCount;
	//private List<Title> alternativeTitles;
	//private List<Image> images; // posters and backdrops
	//private List<Credit> credits; // crews or cast
	//private List<CompanyEntity> productionCompanies;

	@ManyToMany(targetEntity = CountryEntity.class)
	@JoinTable(name = "movie_country",
			joinColumns = { @JoinColumn(name = "fk_movie") },
			inverseJoinColumns = { @JoinColumn(name = "fk_country") })
	private List<CountryEntity> productionCountries = new ArrayList<>();

	@OneToOne(targetEntity = LanguageEntity.class)
	@JoinColumn(name = "original_language_id")
	private LanguageEntity originalLanguage;

	@ManyToMany(targetEntity = LanguageEntity.class)
	@JoinTable(name = "movie_language",
			joinColumns = { @JoinColumn(name = "fk_movie") },
			inverseJoinColumns = { @JoinColumn(name = "fk_language") })
	private List<LanguageEntity> spokenLanguages = new ArrayList<>();

	@ManyToMany(targetEntity = KeywordEntity.class, mappedBy = "movies")
	private List<KeywordEntity> keywords = new ArrayList<>();

	@ManyToMany(targetEntity = GenreEntity.class)
	@JoinTable(name = "movie_genre",
			joinColumns = { @JoinColumn(name = "fk_movie") },
			inverseJoinColumns = { @JoinColumn(name = "fk_genre") })
	private List<GenreEntity> genres = new ArrayList<>();

	public MovieEntity() {
	}

	public MovieEntity(long id, boolean adult, String originalTitle, double popularity, boolean video, boolean processed) {
		this.id = id;
		this.adult = adult;
		this.originalTitle = originalTitle;
		this.popularity = popularity;
		this.video = video;
		this.processed = processed;
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

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
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

	/*
	public LanguageEntity getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(LanguageEntity originalLanguage) {
		this.originalLanguage = originalLanguage;
	}
	*/

	public List<LanguageEntity> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(List<LanguageEntity> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public List<KeywordEntity> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<KeywordEntity> keywords) {
		this.keywords = keywords;
	}

	public List<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreEntity> genres) {
		this.genres = genres;
	}

	public List<CountryEntity> getProductionCountries() {
		return productionCountries;
	}

	public void setProductionCountries(List<CountryEntity> productionCountries) {
		this.productionCountries = productionCountries;
	}

	public LanguageEntity getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(LanguageEntity originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MovieEntity movieEntity = (MovieEntity) o;
		return id == movieEntity.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "MovieEntity{" +
				"id=" + id +
				", adult=" + adult +
				", originalTitle='" + originalTitle + '\'' +
				", popularity=" + popularity +
				", video=" + video +
				", processed=" + processed +
				", backdropPath='" + backdropPath + '\'' +
				", budget=" + budget +
				", homepage='" + homepage + '\'' +
				", imdbId='" + imdbId + '\'' +
				", overview='" + overview + '\'' +
				", posterPath='" + posterPath + '\'' +
				", releaseDate=" + releaseDate +
				", revenue=" + revenue +
				", runtime=" + runtime +
				", status='" + status + '\'' +
				", tagline='" + tagline + '\'' +
				", title='" + title + '\'' +
				", voteAverage=" + voteAverage +
				", voteCount=" + voteCount +
				", originalLanguage=" + originalLanguage +
				", spokenLanguages=" + spokenLanguages +
				", keywords=" + keywords +
				", genres=" + genres +
				'}';
	}

	public void processInfo(Movie movie) {
		this.originalTitle = movie.getOriginalTitle();
		this.popularity = movie.getPopularity();
		this.backdropPath = movie.getBackdropPath();
		this.budget = movie.getBudget();
		this.homepage = movie.getHomepage();
		this.imdbId = movie.getImdbId();
		this.overview = movie.getOverview();
		this.posterPath = movie.getPosterPath();
		this.releaseDate = movie.getReleaseDate();
		this.revenue = movie.getRevenue();
		this.runtime = movie.getRuntime();
		this.status = movie.getStatus();
		this.tagline = movie.getTagline();
		this.title = movie.getTitle();
		this.voteAverage = movie.getVoteAverage();
		this.voteCount = movie.getVoteCount();
		this.processed = true;
		/*
		 	private LanguageEntity originalLanguage;
			private List<Title> alternativeTitles;
			private List<Image> images; // posters and backdrops
			private List<Credit> credits; // crews or cast
			private List<CompanyEntity> productionCompanies;
			private List<CountryEntity> productionCountries;
			private List<LanguageEntity> spokenLanguages;
		*/
	}

	public void processGenres(List<GenreEntity> genres) {
		genres.forEach(genre -> {
			genre.getMovies().add(this);
			this.genres.add(genre);
		});
	}

	public void processLanguages(List<LanguageEntity> languages, LanguageEntity originalLanguage) {
		this.originalLanguage = originalLanguage;
		languages.forEach(language -> {
			language.getMovies().add(this);
			this.spokenLanguages.add(language);
		});
	}

	public void processCountries(List<CountryEntity> countries) {
		countries.forEach(country -> {
			country.getMovies().add(this);
			this.productionCountries.add(country);
		});
	}
}
