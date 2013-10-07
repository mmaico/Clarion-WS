package br.com.kohen.eventmanager.clarion.ws.converter;

import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;

public class PurchaseItemToWsObject {

	private PropertiesAcessor properties = new PropertiesAcessor();
	
	public DADOSITENS convert(PurchaseItem item) {
		
		DADOSITENS itemWs = new DADOSITENS();
		
		String tes = properties.loadSystemProperty().get("import.clarion.codigo.tes", String.class);
		Long externalCode = item.getProduct().getExternalCode();
		
		itemWs.setCODIGO(externalCode == null ? "" : externalCode.toString());
		itemWs.setQUANTIDADE(item.getQtd().toString());
		itemWs.setVALORUNIT(item.getPrice().toString());
		itemWs.setTES(tes);
		
		
		return itemWs;
	}
}
