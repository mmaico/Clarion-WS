package br.com.kohen.eventmanager.clarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.repository.BaseRepository;

public interface ClarionCompanyRepository extends BaseRepository<Company, Long> {

	
	@Query("SELECT c FROM Company AS c WHERE c.code is null AND c.companyCategory.id != 17")
	public List<Company> getAllCompanyNotImported();
	
	
}
