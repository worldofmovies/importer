package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import se.david.moviesimporter.domain.entities.CompanyEntity;

public class ProductionCompany {
	private long id;
	@JsonProperty("logo_path")
	private String logoPath;
	private String name;
	@JsonProperty("origin_country")
	private String originCountry;
	private String description;
	private String headquarters;
	private String homepage;
	@JsonProperty("parent_company")
	private String parentCompany;

	public ProductionCompany() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeadquarters() {
		return headquarters;
	}

	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(String parentCompany) {
		this.parentCompany = parentCompany;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductionCompany that = (ProductionCompany) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ProductionCompany{" +
				"id=" + id +
				", logoPath='" + logoPath + '\'' +
				", name='" + name + '\'' +
				", originCountry='" + originCountry + '\'' +
				'}';
	}
}
