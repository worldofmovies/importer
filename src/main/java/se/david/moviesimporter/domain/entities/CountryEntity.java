package se.david.moviesimporter.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Country")
@Table(name = "country")
public class CountryEntity {
	@Id
	@Column(name = "iso_3166_1", nullable = false)
	private String iso_3166_1;
	@Column(name = "english_name", nullable = false)
	private String englishName;

	public CountryEntity() {
	}

	public CountryEntity(String iso_3166_1, String englishName) {
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
		CountryEntity that = (CountryEntity) o;
		return Objects.equals(iso_3166_1, that.iso_3166_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_3166_1);
	}

	@Override
	public String toString() {
		return "CountryEntity{" +
				"iso_3166_1='" + iso_3166_1 + '\'' +
				", englishName='" + englishName + '\'' +
				'}';
	}
}
