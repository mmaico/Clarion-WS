package br.com.kohen.eventmanager.clarion.ws.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.WSCLSC5;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.WSCLSC5SOAP;

@Component("purchaseImportDAO")
public class PurchaseImportDAO {

	private static Logger logger = Logger.getLogger(PurchaseImportDAO.class);

	private WSCLSC5 wsclsc5;
	
	public PurchaseImportDAO() {
		try {
			
		} catch (Exception e) {
			logger.error("Erro ao iniciar o servico do PurchaseWS" + e.getMessage());
		}
	}
	
	
	public String importPurchase(APEDIDO pedido) {
		try {
			wsclsc5 = new WSCLSC5();	
			
			WSCLSC5SOAP wsclsc5soap = wsclsc5.getWSCLSC5SOAP();
			
			return wsclsc5soap.gravadados(pedido);
		}catch(Exception e) {
			logger.error("Erro ao iniciar o servico do PurchaseWS" + e.getMessage());
			return e.getMessage();
		}
		
	}
	
	
	
}
