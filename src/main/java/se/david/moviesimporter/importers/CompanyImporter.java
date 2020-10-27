package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.tmdb.ProductionCompany;
import se.david.moviesimporter.repository.ProductionCompanyRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class CompanyImporter extends BaseImporter {
	@Autowired
	private ProductionCompanyRepository productionCompanyRepository;

	public String processEntity(long companyId) {
		String url = String.format("%s/3/company/%s?api_key=%s", tmdbApiUrl, companyId, apiKey);
		try {
			RestTemplateFetcher.fetchCompany(url)
					.ifPresentOrElse(handleProcessed(companyId), handleDeleted(companyId));
			return String.format("Processed company: %s", companyId);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Runnable handleDeleted(long personId) {
		return () -> productionCompanyRepository.deleteByIdWithTransaction(personId);
	}

	private Consumer<ProductionCompany> handleProcessed(long personId) {
		return result -> {
			productionCompanyRepository.findById(personId)
					.ifPresent(company -> {
						company.processInfo(result);
						productionCompanyRepository.saveAndFlush(company);
					});
		};
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return productionCompanyRepository.findAllUnprocessed();
	}
}
