package se.david.moviesimporter.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Genre")
@Table(name = "genre")
public class GenreEntity {
	@Id
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false)
	private String name;

	@ManyToMany(targetEntity = MovieEntity.class, mappedBy = "genres")
	private List<MovieEntity> movies = new ArrayList<>();


	public GenreEntity() {
	}

	public GenreEntity(Long id, String name) {
		this.id = Objects.requireNonNull(id);
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
		GenreEntity that = (GenreEntity) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "GenreEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
