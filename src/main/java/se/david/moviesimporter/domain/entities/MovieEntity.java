package se.david.moviesimporter.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private String backdrop_path;
	@JsonProperty("")
	private long collectionId;


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
		return "Movie{" +
				"id=" + id +
				", adult=" + adult +
				", originalTitle='" + originalTitle + '\'' +
				", popularity=" + popularity +
				", video=" + video +
				'}';
	}
}
