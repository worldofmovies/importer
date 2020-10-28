package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.david.moviesimporter.domain.entities.CountryEntity;
import se.david.moviesimporter.domain.entities.LanguageEntity;

public class CountryData {
	@JsonProperty("iso_3166_1")
	private String iso_3166_1;
	@JsonProperty("english_name")
	private String englishName;

	public CountryData() {
	}

	public CountryData(String iso_3166_1, String englishName) {
		this.iso_3166_1 = iso_3166_1;
		this.englishName = englishName;
	}

	public String getIso_3166_1() {
		return iso_3166_1;
	}

	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CountryData that = (CountryData) o;
		return Objects.equals(iso_3166_1, that.iso_3166_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_3166_1);
	}

	@Override
	public String toString() {
		return "CountryData{" +
				"iso_3166_1='" + iso_3166_1 + '\'' +
				", englishName='" + englishName + '\'' +
				'}';
	}

	public CountryEntity createEntity(List<LanguageEntity> languages) {
		return new CountryEntity(iso_3166_1, englishName, languages);
	}
}
