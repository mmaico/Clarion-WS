package br.com.kohen.eventmanager.clarion.ws.converter;

import java.util.Map;

import br.com.kohen.eventmanager.clarion.FieldNamesEnum;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;

public class PurchaseItemToWsObject {

	public DADOSITENS convert(PurchaseItem item, Map<String, String> settings) {
		
		DADOSITENS itemWs = new DADOSITENS();
		
		String tes = settings.get(FieldNamesEnum.TES.get());
		Long externalCode = item.getProduct().getExternalCode();
		
		itemWs.setCODIGO(externalCode == null ? "" : externalCode.toString());
		itemWs.setQUANTIDADE(item.getQtd().toString());
		itemWs.setVALORUNIT(item.getPrice().toString());
		itemWs.setTES(tes);
		
		
		return itemWs;
	}
}
