package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import se.david.moviesimporter.domain.entities.CompanyEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyId {
	private long id;

	public CompanyId() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
	public CompanyEntity createEntity() {
		return new CompanyEntity(id, false);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CompanyId that = (CompanyId) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ProductionCompany{" +
				"id=" + id +
				'}';
	}
}
