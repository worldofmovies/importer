package se.david.moviesimporter.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * {"id":378,"name":"prison"}
 */
@Entity(name = "Keyword")
@Table(name = "keyword")
public class KeywordEntity {
	@Id
	private long id;
	private String name;
	private boolean processed;

	@ManyToMany(targetEntity = MovieEntity.class)
	@JoinTable(name = "keyword_movie",
			joinColumns = { @JoinColumn(name = "fk_keyword") },
			inverseJoinColumns = { @JoinColumn(name = "fk_movie") })
	private List<MovieEntity> movies = new ArrayList<>();

	public KeywordEntity() {
	}

	public KeywordEntity(long id, String name, boolean processed) {
		this.id = id;
		this.name = name;
		this.processed = processed;
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

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
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
		KeywordEntity keywordEntity = (KeywordEntity) o;
		return id == keywordEntity.id;
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

	public void processInfo(List<MovieEntity> movies) {
		this.processed = true;
		this.movies.addAll(movies);
	}
}
