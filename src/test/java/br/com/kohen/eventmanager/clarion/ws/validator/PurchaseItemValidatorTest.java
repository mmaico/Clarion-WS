package br.com.kohen.eventmanager.clarion.ws.validator;

import static junit.framework.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.commons.config.KProperties;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Product;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.enums.Language;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseItemValidatorTest {

	private @InjectMocks PurchaseItemValidator validator;
	
	private @Mock PropertiesAcessor properties;
	
	@Test
	public void shouldValidatePurchaseItem() {
		PurchaseItem item = new PurchaseItem();
		Product product = new Product(3l);
		product.addName(Language.PT, "name");
		item.setProduct(product);
		
		KProperties propMock = mock(KProperties.class);
		
		given(properties.loadSystemProperty()).willReturn(propMock);
		given(propMock.get("import.clarion.codigo.tes", String.class)).willReturn("");
		
		Set<String> errors = validator.validator(item);
		
		assertTrue(errors.contains("Existe produto na venda sem o codigo protheus, nome:name-- codigo bluebox:3"));
		assertTrue(errors.contains("Tes nao definido no arquivodo de propriedade"));
	}

}
