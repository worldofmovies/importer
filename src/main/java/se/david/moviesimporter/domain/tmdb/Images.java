package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

public class Images {
	private List<Image> backdrops;
	private List<Image> posters;

	public Images() {
	}

	public Images(List<Image> backdrops, List<Image> posters) {
		this.backdrops = backdrops;
		this.posters = posters;
	}

	public List<Image> getBackdrops() {
		return backdrops;
	}

	public void setBackdrops(List<Image> backdrops) {
		this.backdrops = backdrops;
	}

	public List<Image> getPosters() {
		return posters;
	}

	public void setPosters(List<Image> posters) {
		this.posters = posters;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Images images = (Images) o;
		return Objects.equals(backdrops, images.backdrops) &&
				Objects.equals(posters, images.posters);
	}

	@Override
	public int hashCode() {
		return Objects.hash(backdrops, posters);
	}

	@Override
	public String toString() {
		return "Images{" +
				"backdrops=" + backdrops +
				", posters=" + posters +
				'}';
	}
}
