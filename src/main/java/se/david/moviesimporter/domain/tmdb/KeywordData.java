package se.david.moviesimporter.domain.tmdb;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KeywordData {
	private int page;
	@JsonProperty("total_results")
	private int totalResults;
	@JsonProperty("total_pages")
	private int totalPages;
	private List<Result> results;

	public KeywordData() {
	}

	public KeywordData(int page, int totalResults, int totalPages, List<Result> results) {
		this.page = page;
		this.totalResults = totalResults;
		this.totalPages = totalPages;
		this.results = results;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		KeywordData that = (KeywordData) o;
		return page == that.page &&
				totalResults == that.totalResults &&
				totalPages == that.totalPages &&
				Objects.equals(results, that.results);
	}

	@Override
	public int hashCode() {
		return Objects.hash(page, totalResults, totalPages, results);
	}

	@Override
	public String toString() {
		return "KeywordData{" +
				"page=" + page +
				", totalResults=" + totalResults +
				", totalPages=" + totalPages +
				", results=" + results +
				'}';
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Result {
		private long id;

		public Result() {
		}

		public Result(long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Result result = (Result) o;
			return id == result.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String toString() {
			return "Result{" +
					"id=" + id +
					'}';
		}
	}
}
