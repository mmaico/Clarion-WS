package br.com.kohen.eventmanager.clarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.repository.BaseRepository;

public interface ClarionPurchaseRepository extends BaseRepository<Purchase, Long> {

	@Query("SELECT p FROM Purchase AS p WHERE p.status ='ACTIVE' AND p.code is null")
	public List<Purchase> getAllPurchaseNotImported();
	
	@Query("SELECT ipe FROM ImportPurchaseError AS ipe WHERE ipe.id =:idParam")
	public ImportPurchaseError getPurchaseErrorById(@Param("idParam")Long id);
}
