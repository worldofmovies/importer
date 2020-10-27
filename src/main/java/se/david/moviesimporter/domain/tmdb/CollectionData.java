package se.david.moviesimporter.domain.tmdb;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionData {
	private long id;
	private String name;
	private String overview;
	@JsonProperty("poster_path")
	private String posterPath;
	@JsonProperty("backdrop_path")
	private String backdropPath;
	private List<Part> parts;

	public CollectionData() {
	}

	public CollectionData(long id, String name, String overview, String posterPath, String backdropPath, List<Part> parts) {
		this.id = id;
		this.name = name;
		this.overview = overview;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.parts = parts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CollectionData that = (CollectionData) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "CollectionData{" +
				"id=" + id +
				", name='" + name + '\'' +
				", overview='" + overview + '\'' +
				", posterPath='" + posterPath + '\'' +
				", backdropPath='" + backdropPath + '\'' +
				", parts=" + parts +
				'}';
	}

	public static class Part {
		private long id;
		private boolean video;
		@JsonProperty("vote_count")
		private int voteCount;
		@JsonProperty("vote_average")
		private double voteAverage;
		private String title;
		@JsonProperty("release_date")
		private LocalDate releaseDate;
		@JsonProperty("original_language")
		private String originalLanguage;
		@JsonProperty("original_title")
		private String originalTitle;
		@JsonProperty("genre_ids")
		private List<Integer> genreIds;
		@JsonProperty("backdrop_path")
		private String backdropPath;
		private boolean adult;
		private String overview;
		@JsonProperty("poster_path")
		private String posterPath;
		private Double popularity;

		public Part() {
		}

		public Part(long id, boolean video, int voteCount, double voteAverage, String title, LocalDate releaseDate, String originalLanguage, String originalTitle, List<Integer> genreIds, String backdropPath, boolean adult,
				String overview, String posterPath, Double popularity) {
			this.id = id;
			this.video = video;
			this.voteCount = voteCount;
			this.voteAverage = voteAverage;
			this.title = title;
			this.releaseDate = releaseDate;
			this.originalLanguage = originalLanguage;
			this.originalTitle = originalTitle;
			this.genreIds = genreIds;
			this.backdropPath = backdropPath;
			this.adult = adult;
			this.overview = overview;
			this.posterPath = posterPath;
			this.popularity = popularity;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public boolean isVideo() {
			return video;
		}

		public void setVideo(boolean video) {
			this.video = video;
		}

		public int getVoteCount() {
			return voteCount;
		}

		public void setVoteCount(int voteCount) {
			this.voteCount = voteCount;
		}

		public double getVoteAverage() {
			return voteAverage;
		}

		public void setVoteAverage(double voteAverage) {
			this.voteAverage = voteAverage;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public LocalDate getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(LocalDate releaseDate) {
			this.releaseDate = releaseDate;
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

		public List<Integer> getGenreIds() {
			return genreIds;
		}

		public void setGenreIds(List<Integer> genreIds) {
			this.genreIds = genreIds;
		}

		public String getBackdropPath() {
			return backdropPath;
		}

		public void setBackdropPath(String backdropPath) {
			this.backdropPath = backdropPath;
		}

		public boolean isAdult() {
			return adult;
		}

		public void setAdult(boolean adult) {
			this.adult = adult;
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

		public Double getPopularity() {
			return popularity;
		}

		public void setPopularity(Double popularity) {
			this.popularity = popularity;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Part part = (Part) o;
			return id == part.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String
		toString() {
			return "Part{" +
					"id=" + id +
					", video=" + video +
					", voteCount=" + voteCount +
					", voteAverage=" + voteAverage +
					", title='" + title + '\'' +
					", releaseDate=" + releaseDate +
					", originalLanguage='" + originalLanguage + '\'' +
					", originalTitle='" + originalTitle + '\'' +
					", genreIds=" + genreIds +
					", backdropPath='" + backdropPath + '\'' +
					", adult=" + adult +
					", overview='" + overview + '\'' +
					", posterPath='" + posterPath + '\'' +
					", popularity=" + popularity +
					'}';
		}
	}
}
