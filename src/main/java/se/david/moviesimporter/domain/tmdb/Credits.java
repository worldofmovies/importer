package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

public class Credits {
	private List<Cast> cast;
	private List<Crew> crew;

	public Credits() {
	}

	public Credits(List<Cast> cast, List<Crew> crew) {
		this.cast = cast;
		this.crew = crew;
	}

	public List<Cast> getCast() {
		return cast;
	}

	public void setCast(List<Cast> cast) {
		this.cast = cast;
	}

	public List<Crew> getCrew() {
		return crew;
	}

	public void setCrew(List<Crew> crew) {
		this.crew = crew;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Credits credits = (Credits) o;
		return Objects.equals(cast, credits.cast) &&
				Objects.equals(crew, credits.crew);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cast, crew);
	}

	@Override
	public String toString() {
		return "Credits{" +
				"cast=" + cast +
				", crew=" + crew +
				'}';
	}
}
