package br.com.kohen.eventmanager.clarion.ws.dao;

import br.com.kohen.eventmanager.clarion.ws.utils.ClarionWsResponse;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;

public interface PurchaseWsDAO {

	ClarionWsResponse send(APEDIDO pedido);
}
