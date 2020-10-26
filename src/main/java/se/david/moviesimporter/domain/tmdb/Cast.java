package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cast {
	@JsonProperty("cast_id")
	private long castId;
	private String character;
	@JsonProperty("credit_id")
	private String creditId;
	private int gender;
	private long id;
	private String name;
	private int order;
	@JsonProperty("profile_path")
	private String profilePath;

	public Cast() {
	}

	public Cast(long castId, String character, String creditId, int gender, long id, String name, int order, String profilePath) {
		this.castId = castId;
		this.character = character;
		this.creditId = creditId;
		this.gender = gender;
		this.id = id;
		this.name = name;
		this.order = order;
		this.profilePath = profilePath;
	}

	public long getCastId() {
		return castId;
	}

	public void setCastId(long castId) {
		this.castId = castId;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Cast cast = (Cast) o;
		return castId == cast.castId &&
				id == cast.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(castId, id);
	}

	@Override
	public String toString() {
		return "Cast{" +
				"castId=" + castId +
				", character='" + character + '\'' +
				", creditId='" + creditId + '\'' +
				", gender=" + gender +
				", id=" + id +
				", name='" + name + '\'' +
				", order=" + order +
				", profilePath='" + profilePath + '\'' +
				'}';
	}
}
