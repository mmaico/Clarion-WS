package br.com.kohen.eventmanager.clarion.ws.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.kohen.eventmanager.clarion.service.LogService;
import br.com.kohen.eventmanager.clarion.ws.converter.PurchaseToWsObject;
import br.com.kohen.eventmanager.clarion.ws.dao.PurchaseWsDAO;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportPurchaseError;
import br.com.kohen.eventmanager.clarion.ws.service.PurchaseWsService;
import br.com.kohen.eventmanager.clarion.ws.utils.ClarionWsResponse;
import br.com.kohen.eventmanager.clarion.ws.validator.PurchaseValidator;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Purchase;

@SuppressWarnings("rawtypes")
@Service("purchaseWsService")
public class PurchaseWsServiceImpl implements PurchaseWsService {

	private @Autowired PurchaseToWsObject converter;
	private @Autowired PurchaseValidator validator;
	private @Autowired LogService logService;
	private @Autowired PurchaseWsDAO dao;
	
	@Autowired
	@Qualifier("commonBaseDAO")
	private CommonBaseDAO baseDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public void sendToWs(Purchase purchase) {
		
		Set<String> errors = validator.validator(purchase);
		
		if (!errors.isEmpty()) {
			ImportPurchaseError error = ImportPurchaseError.exError(errors.toString())
						.purchaseId(purchase.getId());
			this.logService.savePurchaseError(error);
			return;
		}
		
		APEDIDO apedido = converter.convert(purchase);
		
		ClarionWsResponse response = dao.send(apedido);
		
		if (!response.isValid()) {
			ImportPurchaseError error = ImportPurchaseError.exError(response.getError())
					.purchaseId(purchase.getId());
			
			this.logService.savePurchaseError(error);
			return;
		}
		
		purchase.setCode(response.getValueReturned());
		
		logService.deleteError(purchase);
		baseDAO.saveOrUpdate(purchase);
	}

}
