package br.com.kohen.eventmanager.clarion.ws.service;

import java.util.Map;

import br.com.kohen.eventmanager.commons.entity.Purchase;

public interface PurchaseWsService {

	void sendToWs(Purchase purchase, Map<String, String> settings);
}
