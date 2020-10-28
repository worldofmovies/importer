package se.david.moviesimporter.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Language")
@Table(name = "language")
public class LanguageEntity {
	@Id
	@Column(name = "iso_639_1", nullable = false)
	private String iso_639_1;
	@Column(name = "english_name", nullable = false)
	private String englishName;
	private String name;

	public LanguageEntity() {
	}

	public LanguageEntity(String iso_639_1, String englishName, String name) {
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
		LanguageEntity that = (LanguageEntity) o;
		return Objects.equals(iso_639_1, that.iso_639_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_639_1);
	}

	@Override
	public String toString() {
		return "LanguageEntity{" +
				"iso_639_1='" + iso_639_1 + '\'' +
				", englishName='" + englishName + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
