package se.david.moviesimporter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
	@Transactional
	default List<CompanyEntity> saveAllWithTransaction(List<CompanyEntity> productionCompanies) {
		return saveAll(productionCompanies);
	}

	@Query(value = "select pc.id from ProductionCompany pc where pc.processed = false")
	List<Long> findAllUnprocessed();

	@Transactional
	@Modifying
	default void deleteByIdWithTransaction(long id) {
		deleteById(id);
	}
}
