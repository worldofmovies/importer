package se.david.moviesimporter.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.ProductionCompanyEntity;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompanyEntity, Long> {
	@Transactional
	default List<ProductionCompanyEntity> saveAllWithTransaction(List<ProductionCompanyEntity> productionCompanies) {
		return saveAll(productionCompanies);
	}

	@Query(value = "select pc.id from ProductionCompany pc where pc.processed = false")
	List<Long> findAllUnprocessed();

}
