package br.com.kohen.eventmanager.clarion.ws.converter;

import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.WsInfoEnum;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.ARRAYOFDADOSCR;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.ARRAYOFDADOSITENS;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSCR;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.enums.Language;
import br.com.kohen.eventmanager.commons.utils.DateUtil;

@Component
public class PurchaseToWsObject {

	private PropertiesAcessor properties = new PropertiesAcessor();
	
	private PurchaseItemToWsObject purchaseItemConverter;
	
	public PurchaseToWsObject() {
		this.purchaseItemConverter = new PurchaseItemToWsObject();
	}
	
	public APEDIDO convert(Purchase purchase) {
		
		APEDIDO apedido = new APEDIDO();
		
		String custo = properties.loadSystemProperty().get("import.clarion.centro.custo", String.class);
		String reciss = properties.loadSystemProperty().get("import.clarion.client.sp", String.class);
		
		apedido.setCODPROTHEUS(purchase.getResponsible().getCode());
		apedido.setCHAVE(WsInfoEnum.WS_KEY.getValue());
		apedido.setCODPVBOX(purchase.getId().toString());
		apedido.setMOEDA(purchase.getLang() == Language.PT ? "1" : "2");
		apedido.setRECISS(safeNull(reciss));
		apedido.setCUSTO(safeNull(custo));
		apedido.setEMPRESA("01");
		
		ARRAYOFDADOSCR plots = createPlots(purchase);
		apedido.setPARCELAS(plots);
		
		List<PurchaseItem> purchseItems = purchase.getPurchseItems();
		ARRAYOFDADOSITENS arrayDados = new ARRAYOFDADOSITENS();
		
		for (PurchaseItem purchaseItem : purchseItems) {
			DADOSITENS itemConverted = purchaseItemConverter.convert(purchaseItem);
			arrayDados.getDADOSITENS().add(itemConverted);
		}
		
		apedido.setITENS(arrayDados);
		
		return apedido;
	}

	private ARRAYOFDADOSCR createPlots(Purchase purchase) {
		
		ARRAYOFDADOSCR parcela = new ARRAYOFDADOSCR();
		DADOSCR dadoscr = new DADOSCR();
		dadoscr.setNVALPARC(purchase.getTotal().toString());
		
		Date paymentDue = getCurrentDate();
		String quantity = WsInfoEnum.QUANTITY_DAYS_TO_PAY.getValue();
		
		Date dateToPay = DateUtil.addDayToDate(Integer.valueOf(quantity), paymentDue);
		
		dadoscr.setDVENCPARC(new SimpleDateFormat("dd-MM-yyyy").format(dateToPay));
		
		parcela.getDADOSCR().add(dadoscr);
		
		return parcela;
	}

	Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
}
