package se.david.moviesimporter.importers;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.david.moviesimporter.domain.tmdb.ProductionCompany;
import se.david.moviesimporter.repository.CompanyRepository;
import se.david.moviesimporter.util.RestTemplateFetcher;

@Service
public class CompanyImporter extends BaseImporter {
	private final CompanyRepository companyRepository;

	public CompanyImporter(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public String processEntity(long companyId) {
		String url = String.format("%s/3/company/%s?api_key=%s", tmdbApiUrl, companyId, apiKey);
		try {
			RestTemplateFetcher.fetch(url, ProductionCompany.class)
					.ifPresentOrElse(handleProcessed(companyId), handleDeleted(companyId));
			return String.format("Fetched company: %s", companyId);
		} catch (IOException e) {
			e.printStackTrace();
			return String.format("Error fetching company: %s. %s", companyId, e.getMessage());
		}
	}

	private Runnable handleDeleted(long personId) {
		return () -> companyRepository.deleteByIdWithTransaction(personId);
	}

	private Consumer<ProductionCompany> handleProcessed(long personId) {
		return result -> companyRepository.findById(personId)
				.ifPresent(company -> {
					company.processInfo(result);
					companyRepository.saveAndFlush(company);
				});
	}

	@Override
	public List<Long> findAllUnprocessed() {
		return companyRepository.findAllUnprocessed();
	}
}
