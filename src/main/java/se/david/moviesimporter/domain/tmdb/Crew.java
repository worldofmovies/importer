package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crew {
	@JsonProperty("credit_id")
	private String creditId;
	private String department;
	private int gender;
	private long id;
	private String job;
	private String name;
	@JsonProperty("profile_path")
	private String profilePath;

	public Crew() {
	}

	public Crew(String creditId, String department, int gender, long id, String job, String name, String profilePath) {
		this.creditId = creditId;
		this.department = department;
		this.gender = gender;
		this.id = id;
		this.job = job;
		this.name = name;
		this.profilePath = profilePath;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Crew crew = (Crew) o;
		return id == crew.id &&
				Objects.equals(creditId, crew.creditId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditId, id);
	}

	@Override
	public String toString() {
		return "Crew{" +
				"creditId='" + creditId + '\'' +
				", department='" + department + '\'' +
				", gender=" + gender +
				", id=" + id +
				", job='" + job + '\'' +
				", name='" + name + '\'' +
				", profilePath='" + profilePath + '\'' +
				'}';
	}
}
