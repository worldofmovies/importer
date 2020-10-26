package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
	@JsonProperty("aspect_ratio")
	private Double aspectRatio;
	@JsonProperty("file_path")
	private String filePath;
	@JsonProperty("iso_639_1")
	private String iso_639_1;
	@JsonProperty("vote_average")
	private Double voteAverage;
	@JsonProperty("vote_count")
	private int voteCount;
	private int width;
	private int height;

	public Image() {
	}

	public Image(Double aspectRatio, String filePath, String iso_639_1, Double voteAverage, int voteCount, int width, int height) {
		this.aspectRatio = aspectRatio;
		this.filePath = filePath;
		this.iso_639_1 = iso_639_1;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.width = width;
		this.height = height;
	}

	public Double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getIso_639_1() {
		return iso_639_1;
	}

	public void setIso_639_1(String iso_639_1) {
		this.iso_639_1 = iso_639_1;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Image backdrop = (Image) o;
		return Objects.equals(filePath, backdrop.filePath) &&
				Objects.equals(iso_639_1, backdrop.iso_639_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filePath, iso_639_1);
	}

	@Override
	public String toString() {
		return "Backdrop{" +
				"aspectRatio=" + aspectRatio +
				", filePath='" + filePath + '\'' +
				", iso_639_1='" + iso_639_1 + '\'' +
				", voteAverage=" + voteAverage +
				", voteCount=" + voteCount +
				", width=" + width +
				", height=" + height +
				'}';
	}
}
