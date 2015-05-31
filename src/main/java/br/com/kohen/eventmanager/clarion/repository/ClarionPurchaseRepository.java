package br.com.kohen.eventmanager.clarion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kohen.eventmanager.commons.entity.Event;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.repository.BaseRepository;

public interface ClarionPurchaseRepository extends BaseRepository<Purchase, Long> {

	@Query("SELECT p FROM Purchase AS p WHERE p.status ='ACTIVE' AND p.code is null AND p.event = :event")
	public List<Purchase> getAllPurchaseNotImported(@Param("event")Event event);
	
}
