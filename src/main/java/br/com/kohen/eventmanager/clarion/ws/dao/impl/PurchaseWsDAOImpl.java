package br.com.kohen.eventmanager.clarion.ws.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.ws.dao.PurchaseWsDAO;
import br.com.kohen.eventmanager.clarion.ws.utils.ClarionWsResponse;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.WSCLSC5;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.WSCLSC5SOAP;

@Component("purchaseWsDAO")
public class PurchaseWsDAOImpl implements PurchaseWsDAO {

	private static Logger logger = Logger.getLogger(PurchaseWsDAOImpl.class);

	private WSCLSC5 wsclsc5;
	
	public ClarionWsResponse send(APEDIDO pedido) {
		try {
			wsclsc5 = new WSCLSC5();	
			
			WSCLSC5SOAP wsclsc5soap = wsclsc5.getWSCLSC5SOAP();
			String code = wsclsc5soap.gravadados(pedido);
			
			return ClarionWsResponse.build().withValueReturned(code);
		}catch(Exception e) {
			logger.error("Erro ao iniciar o servico do PurchaseWS" + e.getMessage());
			return ClarionWsResponse.build().withError(e.getMessage());
		}
	}
	
}
