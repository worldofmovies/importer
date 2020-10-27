package se.david.moviesimporter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.CollectionEntity;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {
	@Transactional
	default List<CollectionEntity> saveAllWithTransaction(List<CollectionEntity> collectionEntities) {
		return saveAll(collectionEntities);
	}

	@Query(value = "select pc.id from Collection pc where pc.processed = false")
	List<Long> findAllUnprocessed();

	@Transactional
	@Modifying
	default void deleteByIdWithTransaction(long id) {
		deleteById(id);
	}
}
