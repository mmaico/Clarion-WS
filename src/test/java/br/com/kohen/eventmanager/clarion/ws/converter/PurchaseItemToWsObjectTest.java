package br.com.kohen.eventmanager.clarion.ws.converter;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.KProperties;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Product;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseItemToWsObjectTest {

	private @InjectMocks PurchaseItemToWsObject convert;
	
	private @Mock PropertiesAcessor properties;
	
	@Test
	public void shouldConvertPurchaseItemToWsObject() {
		PurchaseItem purchaseItemStub = purchaseItemStub();
		
		KProperties kpropMock = mock(KProperties.class);
		
		given(properties.loadSystemProperty()).willReturn(kpropMock);
		given(kpropMock.get("import.clarion.codigo.tes", String.class)).willReturn("tes");
	
		
		DADOSITENS dadositem = null;//convert.convert(purchaseItemStub);
		
		assertEquals("tes", dadositem.getTES());
		assertEquals(purchaseItemStub.getQtd().toString(), dadositem.getQUANTIDADE());
		assertEquals(purchaseItemStub.getPrice().toString(), dadositem.getVALORUNIT());
		assertEquals(purchaseItemStub.getProduct().getExternalCode().toString(), dadositem.getCODIGO());
	}

	
	private PurchaseItem purchaseItemStub() {
		PurchaseItem  purchaseItem = new PurchaseItem();
		Product product = new Product(2l);
		product.setExternalCode(3l);
		
		purchaseItem.setProduct(product);
		purchaseItem.setQtd(3);
		purchaseItem.setPrice(new BigDecimal(34.0));
		
		return purchaseItem;
	}
}
