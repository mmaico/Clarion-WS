package br.com.kohen.eventmanager.clarion.ws.validator;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;

@Component
public class PurchaseValidator {

	private @Autowired PurchaseItemValidator purchaseItemValidator;
	
	private PropertiesAcessor properties = new PropertiesAcessor();
	
	
	public Set<String> validator(Purchase purchase) {
		Set<String> errors = new HashSet<String>();
		
		if (purchase.getId() == null) {
			errors.add("Venda sem id --");
		}
		
		if (purchase.getLang() == null) {
			errors.add("Nao foi possivel determinar a moeda da venda --");
		}
		
		String custo = properties.loadSystemProperty().get("import.clarion.centro.custo", String.class);
		String reciss = properties.loadSystemProperty().get("import.clarion.client.sp", String.class);
		
		if (isBlank(custo)) {
			errors.add("Centro de custo nao definido --");
		}
		
		if (isBlank(reciss)) {
			errors.add("reciss nao definido --");
		}
		Company responsible = purchase.getResponsible();
		
		if (isBlank(responsible.getCode())) {
			String message = "O responsavel pelo pagamento ainda nao tem o codigo protheus, nome: " + responsible.getName() + "-- codigo bluebox:" + responsible.getId();
			errors.add(message);
		}
		
		
		List<PurchaseItem> items = purchase.getPurchseItems();
		
		for (PurchaseItem purchaseItem : items) {
			Set<String> errorsPurchaseItem = this.purchaseItemValidator.validator(purchaseItem);
			errors.addAll(errorsPurchaseItem);
		}
		
		return errors;
	}
}
