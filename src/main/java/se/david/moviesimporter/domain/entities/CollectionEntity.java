package se.david.moviesimporter.domain.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import se.david.moviesimporter.domain.tmdb.CollectionData;

@Entity(name = "Collection")
@Table(name = "collection")
public class CollectionEntity {
	@Id
	private long id;
	private String name;
	private boolean processed;

	public CollectionEntity() {
	}

	public CollectionEntity(long id, String name, boolean processed) {
		this.id = id;
		this.name = name;
		this.processed = processed;
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
		CollectionEntity keywordEntity = (CollectionEntity) o;
		return id == keywordEntity.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "CollectionEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", processed=" + processed +
				'}';
	}

	public void processInfo(CollectionData collection) {
		this.name = collection.getName();
		this.processed = true;
	}
}
