package br.com.kohen.eventmanager.clarion.ws.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.kohen.eventmanager.clarion.service.impl.PurchaseImportServiceImpl;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;

public class PurchaseImportServiceImplTest {

	PurchaseImportServiceImpl purchaseImport = new PurchaseImportServiceImpl();
	
	@Test
	public void shouldSortTheList() {
		PurchaseItem item1 = new PurchaseItem();
		item1.setId(1l);
		item1.setQtd(2);
		item1.setPrice(new BigDecimal(10));
		
		PurchaseItem item2 = new PurchaseItem();
		item2.setId(2l);
		item2.setQtd(2);
		item2.setPrice(new BigDecimal(5));
		
		PurchaseItem item3 = new PurchaseItem();
		item3.setId(3l);
		item3.setQtd(2);
		item3.setPrice(new BigDecimal(30));
		
		List<PurchaseItem> list = java.util.Arrays.asList(item3, item2, item1);
		
		purchaseImport.sortItems(list);
		
		assertTrue(list.get(0).getId().equals(3l));
		assertTrue(list.get(1).getId().equals(1l));
		assertTrue(list.get(2).getId().equals(2l));
		
	}

}
