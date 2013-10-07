package br.com.kohen.eventmanager.clarion.ws.validator;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.enums.Language;

@Component
public class PurchaseItemValidator {

	private PropertiesAcessor properties = new PropertiesAcessor();
	
	
	public Set<String> validator(PurchaseItem item) {
		Set<String> errors = new HashSet<String>();
		
		if (item.getProduct().getExternalCode() == null) {
			String message = "Existe produto na venda sem o codigo protheus, nome:" 
					+ item.getProduct().getName().get(Language.PT) 
					+ "-- codigo bluebox:" + item.getProduct().getId();
			
			errors.add(message);
		}
		
		String tes = properties.loadSystemProperty().get("import.clarion.codigo.tes", String.class);
		
		if (isBlank(tes)) {
			errors.add("Tes nao definido no arquivodo de propriedade");
		}
		
		return errors;
	}
}
