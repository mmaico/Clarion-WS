package br.com.kohen.eventmanager.clarion.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;

public interface ClarionCompanyImportErrorRepository extends CrudRepository<ImportError, Long> {

	
}
