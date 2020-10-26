package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BelongsToCollection {
	private long id;
	private String name;
	@JsonProperty("poster_path")
	private String posterPath;
	@JsonProperty("backdrop_path")
	private String backdropPath;

	public BelongsToCollection() {
	}

	public BelongsToCollection(long id, String name, String posterPath, String backdropPath) {
		this.id = id;
		this.name = name;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BelongsToCollection that = (BelongsToCollection) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "BelongsToCollection{" +
				"id=" + id +
				", name='" + name + '\'' +
				", posterPath='" + posterPath + '\'' +
				", backdropPath='" + backdropPath + '\'' +
				'}';
	}
}
