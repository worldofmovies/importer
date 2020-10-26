package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountry {
	@JsonProperty("iso_3166_1")
	private String iso_3166_1;
	private String name;

	public ProductionCountry() {
	}

	public ProductionCountry(String iso_3166_1, String name) {
		this.iso_3166_1 = iso_3166_1;
		this.name = name;
	}

	public String getIso_3166_1() {
		return iso_3166_1;
	}

	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
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
		ProductionCountry that = (ProductionCountry) o;
		return Objects.equals(iso_3166_1, that.iso_3166_1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(iso_3166_1);
	}

	@Override
	public String toString() {
		return "ProductionCountry{" +
				"iso_3166_1='" + iso_3166_1 + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
