package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

public class Keywords {
	private List<KeywordId> keywords;

	public Keywords() {
	}

	public Keywords(List<KeywordId> keywords) {
		this.keywords = keywords;
	}

	public List<KeywordId> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<KeywordId> keywords) {
		this.keywords = keywords;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Keywords keywords1 = (Keywords) o;
		return Objects.equals(keywords, keywords1.keywords);
	}

	@Override
	public int hashCode() {
		return Objects.hash(keywords);
	}

	@Override
	public String toString() {
		return "Keywords{" +
				"keywords=" + keywords +
				'}';
	}
}
