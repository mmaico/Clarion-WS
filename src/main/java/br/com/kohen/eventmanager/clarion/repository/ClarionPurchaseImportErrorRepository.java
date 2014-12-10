package br.com.kohen.eventmanager.clarion.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;

public interface ClarionPurchaseImportErrorRepository extends CrudRepository<ImportPurchaseError, Long> {

	
	
}
