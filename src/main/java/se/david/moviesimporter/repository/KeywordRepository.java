package se.david.moviesimporter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.KeywordEntity;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
	@Transactional
	default List<KeywordEntity> saveAllWithTransaction(List<KeywordEntity> keywordEntities) {
		return saveAll(keywordEntities);
	}

	@Query(value = "select k.id from Keyword k where k.processed = false")
	List<Long> findAllUnprocessed();

	@Transactional
	@Modifying
	default void deleteByIdWithTransaction(long id) {
		deleteById(id);
	}

	@Query(value = "select k.id from Keyword k where k.processed = false and k.id in (:ids)")
	List<Long> findAllUnprocessed(@Param("ids") List<Long> ids);
}
