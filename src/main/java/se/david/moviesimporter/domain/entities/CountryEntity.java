package se.david.moviesimporter.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Country")
@Table(name = "country")
public class CountryEntity {
	@Id
	@Column(name = "iso_3166_1", nullable = false)
	private String iso_3166_1;
	@Column(name = "english_name", nullable = false)
	private String englishName;

	@ManyToMany(targetEntity = LanguageEntity.class, fetch = FetchType.EAGER)
	@JoinTable(name = "country_language",
			joinColumns = { @JoinColumn(name = "fk_country") },
			inverseJoinColumns = { @JoinColumn(name = "fk_language") })
	private List<LanguageEntity> languages;

	@ManyToMany(targetEntity = MovieEntity.class, mappedBy = "productionCountries")
	private List<MovieEntity> movies = new ArrayList<>();

	public CountryEntity() {
	}

	public CountryEntity(String iso_3166_1, String englishName, List<LanguageEntity> languageEntities) {
		this.iso_3166_1 = iso_3166_1;
		this.englishName = englishName;
		this.languages = languageEntities;
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

	public List<LanguageEntity> getLanguages() {
		return languages;
	}

	public void setLanguages(List<LanguageEntity> languages) {
		this.languages = languages;
	}

	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
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
				", languages=" + languages +
				'}';
	}
}
