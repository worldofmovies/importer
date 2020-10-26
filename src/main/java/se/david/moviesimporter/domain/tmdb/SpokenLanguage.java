package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpokenLanguage {
	@JsonProperty("iso_639_1")
	private String iso_639_1;
	private String name;

	public SpokenLanguage() {
	}

	public SpokenLanguage(String iso_639_1, String name) {
		this.iso_639_1 = iso_639_1;
		this.name = name;
	}

	public String getIso_639_1() {
		return iso_639_1;
	}

	public void setIso_639_1(String iso_639_1) {
		this.iso_639_1 = iso_639_1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SpokenLanguage that = (SpokenLanguage) o;
		return Objects.equals(iso_639_1, that.iso_639_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_639_1);
	}

	@Override
	public String toString() {
		return "SpokenLanguage{" +
				"iso_639_1='" + iso_639_1 + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
