package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

public class PersonImages {
	private List<Image> profiles;

	public PersonImages() {
	}

	public PersonImages(List<Image> profiles) {
		this.profiles = profiles;
	}

	public List<Image> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Image> profiles) {
		this.profiles = profiles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonImages that = (PersonImages) o;
		return Objects.equals(profiles, that.profiles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(profiles);
	}

	@Override
	public String toString() {
		return "PersonImages{" +
				"profiles=" + profiles +
				'}';
	}
}
