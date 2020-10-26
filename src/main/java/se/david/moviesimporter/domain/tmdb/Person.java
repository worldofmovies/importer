package se.david.moviesimporter.domain.tmdb;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.david.moviesimporter.domain.entities.PersonEntity;

public class Person {
	private long id;
	private LocalDate birthday;
	@JsonProperty("known_for_department")
	private String knownForDepartment;
	@JsonProperty("place_of_birth")
	private String placeOfBirth;
	private String homepage;
	@JsonProperty("profile_path")
	private String profilePath;
	@JsonProperty("imdb_id")
	private String imdbId;
	private LocalDate deathday;
	private PersonImages images;
	@JsonProperty("external_ids")
	private ExternalIds externalIds;
	private String name;
	@JsonProperty("also_known_as")
	private List<String> alsoKnownAs;
	private String biography;
	@JsonProperty("movie_credits")
	private Credits movieCredits;
	private Integer gender;
	private Boolean adult;
	private Double popularity;

	public Person() {
	}

	public Person(long id, LocalDate birthday, String knownForDepartment, String placeOfBirth, String homepage, String profilePath, String imdbId, LocalDate deathday, PersonImages images,
			ExternalIds externalIds, String name, List<String> alsoKnownAs, String biography, Credits movieCredits, Integer gender, Boolean adult, Double popularity) {
		this.id = id;
		this.birthday = birthday;
		this.knownForDepartment = knownForDepartment;
		this.placeOfBirth = placeOfBirth;
		this.homepage = homepage;
		this.profilePath = profilePath;
		this.imdbId = imdbId;
		this.deathday = deathday;
		this.images = images;
		this.externalIds = externalIds;
		this.name = name;
		this.alsoKnownAs = alsoKnownAs;
		this.biography = biography;
		this.movieCredits = movieCredits;
		this.gender = gender;
		this.adult = adult;
		this.popularity = popularity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getKnownForDepartment() {
		return knownForDepartment;
	}

	public void setKnownForDepartment(String knownForDepartment) {
		this.knownForDepartment = knownForDepartment;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public LocalDate getDeathday() {
		return deathday;
	}

	public void setDeathday(LocalDate deathday) {
		this.deathday = deathday;
	}

	public PersonImages getImages() {
		return images;
	}

	public void setImages(PersonImages images) {
		this.images = images;
	}

	public ExternalIds getExternalIds() {
		return externalIds;
	}

	public void setExternalIds(ExternalIds externalIds) {
		this.externalIds = externalIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAlsoKnownAs() {
		return alsoKnownAs;
	}

	public void setAlsoKnownAs(List<String> alsoKnownAs) {
		this.alsoKnownAs = alsoKnownAs;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Credits getMovieCredits() {
		return movieCredits;
	}

	public void setMovieCredits(Credits movieCredits) {
		this.movieCredits = movieCredits;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person person = (Person) o;
		return id == person.id &&
				Objects.equals(birthday, person.birthday) &&
				Objects.equals(knownForDepartment, person.knownForDepartment) &&
				Objects.equals(placeOfBirth, person.placeOfBirth) &&
				Objects.equals(homepage, person.homepage) &&
				Objects.equals(profilePath, person.profilePath) &&
				Objects.equals(imdbId, person.imdbId) &&
				Objects.equals(deathday, person.deathday) &&
				Objects.equals(images, person.images) &&
				Objects.equals(externalIds, person.externalIds) &&
				Objects.equals(name, person.name) &&
				Objects.equals(alsoKnownAs, person.alsoKnownAs) &&
				Objects.equals(biography, person.biography) &&
				Objects.equals(movieCredits, person.movieCredits) &&
				Objects.equals(gender, person.gender) &&
				Objects.equals(adult, person.adult) &&
				Objects.equals(popularity, person.popularity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, birthday, knownForDepartment, placeOfBirth, homepage, profilePath, imdbId, deathday, images, externalIds, name, alsoKnownAs, biography, movieCredits, gender, adult, popularity);
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", birthday=" + birthday +
				", knownForDepartment='" + knownForDepartment + '\'' +
				", placeOfBirth='" + placeOfBirth + '\'' +
				", homepage='" + homepage + '\'' +
				", profilePath='" + profilePath + '\'' +
				", imdbId='" + imdbId + '\'' +
				", deathday=" + deathday +
				", images=" + images +
				", externalIds=" + externalIds +
				", name='" + name + '\'' +
				", alsoKnownAs=" + alsoKnownAs +
				", biography='" + biography + '\'' +
				", movieCredits=" + movieCredits +
				", gender=" + gender +
				", adult=" + adult +
				", popularity=" + popularity +
				'}';
	}

	public PersonEntity createEntity() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(id);
		personEntity.setAdult(adult);
		personEntity.setName(name);
		personEntity.setPopularity(popularity);
		personEntity.setProcessed(false);
		return personEntity;
	}
}
