package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import se.david.moviesimporter.domain.entities.KeywordEntity;

public class Keyword {
	private long id;
	private String name;

	public Keyword() {
	}

	public Keyword(long id, String name) {
		this.id = id;
		this.name = name;
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

	@JsonIgnore
	public KeywordEntity createEntity() {
		return new KeywordEntity(id, name, false);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Keyword keyword = (Keyword) o;
		return id == keyword.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Keyword{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
