package br.com.kohen.eventmanager.clarion.service;

import br.com.kohen.eventmanager.commons.entity.Company;

public interface CompanyImportService {

	void importCompanySchedule();
	void importCompany(Company company);
}
