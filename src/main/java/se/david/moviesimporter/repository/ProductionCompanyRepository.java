package se.david.moviesimporter.repository;

import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.ProductionCompany;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Long> {
	@Transactional
	default List<ProductionCompany> saveAllWithTransaction(List<ProductionCompany> productionCompanies) {
		return saveAll(productionCompanies);
	}

	@Query(value = "select pc.id from ProductionCompany pc where pc.processed = false")
	List<Long> findAllUnprocessed();

}
