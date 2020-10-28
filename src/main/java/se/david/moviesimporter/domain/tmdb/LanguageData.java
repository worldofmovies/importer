package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.david.moviesimporter.domain.entities.LanguageEntity;

/**
 *   {
 * 	"iso_639_1": "xx",
 * 	"english_name": "No Language",
 * 	"name": "No Language"
 *   },
 */
public class LanguageData {
	@JsonProperty("iso_639_1")
	private String iso_639_1;
	@JsonProperty("english_name")
	private String englishName;
	private String name;

	public LanguageData() {
	}

	public LanguageData(String iso_639_1, String englishName, String name) {
		this.iso_639_1 = iso_639_1;
		this.englishName = englishName;
		this.name = name;
	}

	public String getIso_639_1() {
		return iso_639_1;
	}

	public void setIso_639_1(String iso_639_1) {
		this.iso_639_1 = iso_639_1;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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
		LanguageData that = (LanguageData) o;
		return Objects.equals(iso_639_1, that.iso_639_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_639_1);
	}

	@Override
	public String toString() {
		return "LanguagesData{" +
				"iso_639_1='" + iso_639_1 + '\'' +
				", englishName='" + englishName + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public LanguageEntity createEntity() {
		return new LanguageEntity(iso_639_1, englishName, name);
	}
}
