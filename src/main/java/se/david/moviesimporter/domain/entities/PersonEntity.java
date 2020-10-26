package se.david.moviesimporter.domain.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
{"adult":false,"id":1293830,"name":"Santiago  Bertolino","popularity":0.6}
 */
@Entity(name = "Person")
@Table(name = "person")
public class PersonEntity {
	@Id
	private long id;
	@Transient
	private boolean adult;
	private String name;
	private double popularity;
	private boolean processed;

	public PersonEntity() {
	}

	public PersonEntity(long id, boolean adult, String name, double popularity, boolean processed) {
		this.id = id;
		this.adult = adult;
		this.name = name;
		this.popularity = popularity;
		this.processed = processed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonEntity personEntity = (PersonEntity) o;
		return id == personEntity.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", adult=" + adult +
				", name='" + name + '\'' +
				", popularity=" + popularity +
				", processed=" + processed +
				'}';
	}
}
