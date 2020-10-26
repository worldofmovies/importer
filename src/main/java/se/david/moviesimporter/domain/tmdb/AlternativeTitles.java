package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

public class AlternativeTitles {
	private List<Title> titles;

	public AlternativeTitles() {
	}

	public AlternativeTitles(List<Title> titles) {
		this.titles = titles;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AlternativeTitles that = (AlternativeTitles) o;
		return Objects.equals(titles, that.titles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(titles);
	}

	@Override
	public String toString() {
		return "AlternativeTitles{" +
				"titles=" + titles +
				'}';
	}
}
