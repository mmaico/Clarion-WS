package br.com.kohen.eventmanager.clarion.dao;

import java.util.List;

import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.commons.entity.Purchase;

public interface ClarionPurchaseDAO {

	public List<Purchase> getAllPurchaseNotImported();
	
	public ImportPurchaseError getPurchaseErrorById(Long id);
}
