package br.com.kohen.eventmanager.clarion.ws.service;

import br.com.kohen.eventmanager.commons.entity.Purchase;

public interface PurchaseWsService {

	void sendToWs(Purchase purchase);
}
