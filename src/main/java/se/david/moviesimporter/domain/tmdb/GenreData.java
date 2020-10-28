package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

import se.david.moviesimporter.domain.entities.GenreEntity;

/*
    {
      "id": 10402,
      "name": "Music"
    },
 */
public class GenreData {
	private List<Genre> genres;

	public GenreData() {
	}

	public GenreData(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GenreData genreData = (GenreData) o;
		return Objects.equals(genres, genreData.genres);
	}

	@Override
	public int hashCode() {
		return Objects.hash(genres);
	}

	@Override
	public String toString() {
		return "GenreData{" +
				"genres=" + genres +
				'}';
	}

	public static class Genre {
		private long id;
		private String name;

		public Genre() {
		}

		public Genre(long id, String name) {
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

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Genre genreData = (Genre) o;
			return id == genreData.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String toString() {
			return "Genre{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}

		public GenreEntity createEntity() {
			return new GenreEntity(id, name);
		}
	}
}
