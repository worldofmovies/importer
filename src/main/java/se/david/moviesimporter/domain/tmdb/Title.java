package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Title {
	@JsonProperty("iso_3166_1")
	private String iso_3166_1;
	private String title;
	private String type;

	public Title() {
	}

	public Title(String iso_3166_1, String title, String type) {
		this.iso_3166_1 = iso_3166_1;
		this.title = title;
		this.type = type;
	}

	public String getIso_3166_1() {
		return iso_3166_1;
	}

	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Title that = (Title) o;
		return Objects.equals(iso_3166_1, that.iso_3166_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_3166_1);
	}

	@Override
	public String toString() {
		return "AlternativeTitle{" +
				"iso_3166_1='" + iso_3166_1 + '\'' +
				", title='" + title + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
