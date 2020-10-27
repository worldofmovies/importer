package se.david.moviesimporter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
	@Transactional
	default List<MovieEntity> saveAllWithTransaction(List<MovieEntity> movieEntities) {
		return saveAll(movieEntities);
	}

	@Query(value = "select m.id from Movie m where m.processed = false")
	List<Long> findAllUnprocessed();

	@Transactional
	@Modifying
	default void deleteByIdWithTransaction(long id) {
		deleteById(id);
	}
}
