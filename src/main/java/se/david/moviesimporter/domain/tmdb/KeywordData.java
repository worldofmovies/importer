package se.david.moviesimporter.domain.tmdb;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeywordData {
	private int page;
	@JsonProperty("total_results")
	private int totalResults;
	@JsonProperty("total_pages")
	private int totalPages;
	private List<Result> results;

	public KeywordData() {
	}

	public KeywordData(int page, int totalResults, int totalPages, List<Result> results) {
		this.page = page;
		this.totalResults = totalResults;
		this.totalPages = totalPages;
		this.results = results;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		KeywordData that = (KeywordData) o;
		return page == that.page &&
				totalResults == that.totalResults &&
				totalPages == that.totalPages &&
				Objects.equals(results, that.results);
	}

	@Override
	public int hashCode() {
		return Objects.hash(page, totalResults, totalPages, results);
	}

	@Override
	public String toString() {
		return "KeywordData{" +
				"page=" + page +
				", totalResults=" + totalResults +
				", totalPages=" + totalPages +
				", results=" + results +
				'}';
	}

	public static class Result {
		private double popularity;
		@JsonProperty("vote_count")
		private int voteCount;
		private boolean video;
		@JsonProperty("poster_path")
		private String posterPath;
		private long id;
		private boolean adult;
		@JsonProperty("backdrop_path")
		private String backdropPath;
		@JsonProperty("original_language")
		private String originalLanguage;
		@JsonProperty("original_title")
		private String originalTitle;
		@JsonProperty("genre_ids")
		private List<Long> genreIds;
		private String title;
		@JsonProperty("vote_average")
		private double voteAverage;
		private String overView;
		@JsonProperty("release_date")
		private LocalDate releaseDate;

		public Result() {
		}

		public Result(double popularity, int voteCount, boolean video, String posterPath, long id, boolean adult, String backdropPath, String originalLanguage, String originalTitle, List<Long> genreIds, String title, double voteAverage,
				String overView, LocalDate releaseDate) {
			this.popularity = popularity;
			this.voteCount = voteCount;
			this.video = video;
			this.posterPath = posterPath;
			this.id = id;
			this.adult = adult;
			this.backdropPath = backdropPath;
			this.originalLanguage = originalLanguage;
			this.originalTitle = originalTitle;
			this.genreIds = genreIds;
			this.title = title;
			this.voteAverage = voteAverage;
			this.overView = overView;
			this.releaseDate = releaseDate;
		}

		public double getPopularity() {
			return popularity;
		}

		public void setPopularity(double popularity) {
			this.popularity = popularity;
		}

		public int getVoteCount() {
			return voteCount;
		}

		public void setVoteCount(int voteCount) {
			this.voteCount = voteCount;
		}

		public boolean isVideo() {
			return video;
		}

		public void setVideo(boolean video) {
			this.video = video;
		}

		public String getPosterPath() {
			return posterPath;
		}

		public void setPosterPath(String posterPath) {
			this.posterPath = posterPath;
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

		public List<Long> getGenreIds() {
			return genreIds;
		}

		public void setGenreIds(List<Long> genreIds) {
			this.genreIds = genreIds;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public double getVoteAverage() {
			return voteAverage;
		}

		public void setVoteAverage(double voteAverage) {
			this.voteAverage = voteAverage;
		}

		public String getOverView() {
			return overView;
		}

		public void setOverView(String overView) {
			this.overView = overView;
		}

		public LocalDate getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(LocalDate releaseDate) {
			this.releaseDate = releaseDate;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Result result = (Result) o;
			return id == result.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String toString() {
			return "Result{" +
					"popularity=" + popularity +
					", voteCount=" + voteCount +
					", video=" + video +
					", posterPath='" + posterPath + '\'' +
					", id=" + id +
					", adult=" + adult +
					", backdropPath='" + backdropPath + '\'' +
					", originalLanguage='" + originalLanguage + '\'' +
					", originalTitle='" + originalTitle + '\'' +
					", genreIds=" + genreIds +
					", title='" + title + '\'' +
					", voteAverage=" + voteAverage +
					", overView='" + overView + '\'' +
					", releaseDate=" + releaseDate +
					'}';
		}
	}
}
