package br.com.kohen.eventmanager.clarion.ws.validator;

import static junit.framework.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.commons.config.KProperties;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.enums.Language;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseValidatorTest {

	private @InjectMocks PurchaseValidator validator;
	
	private @Mock PurchaseItemValidator purchaseItemValidator;
	
	private @Mock PropertiesAcessor properties;
	
	@Test
	public void shouldValidatePurchaseInfo() {
		Purchase purchase = new Purchase();
		Company responsible = companyStub();
		responsible.setCode(null);
		
		purchase.setResponsible(responsible);
		purchase.setPurchseItems(new ArrayList<PurchaseItem>());
		KProperties propMock = mock(KProperties.class);
		
		given(properties.loadSystemProperty()).willReturn(propMock);
		given(propMock.get("import.clarion.centro.custo", String.class)).willReturn("");
		given(propMock.get("import.clarion.client.sp", String.class)).willReturn("");
		
		Set<String> errors = validator.validator(purchase);
		
		assertTrue(errors.contains("Venda sem id --"));
		assertTrue(errors.contains("Nao foi possivel determinar a moeda da venda --"));
		assertTrue(errors.contains("Centro de custo nao definido --"));
		assertTrue(errors.contains("reciss nao definido --"));
		assertTrue(errors.contains("O responsavel pelo pagamento ainda nao tem o codigo protheus, nome: name-- codigo bluebox:2"));
	}
	
	@Test
	public void shouldNotReturnErrors() {
		Purchase purchase = new Purchase(2l);
		purchase.setLang(Language.PT);
		purchase.setResponsible(companyStub());
		purchase.setPurchseItems(new ArrayList<PurchaseItem>());
		KProperties propMock = mock(KProperties.class);
		
		given(properties.loadSystemProperty()).willReturn(propMock);
		given(propMock.get("import.clarion.centro.custo", String.class)).willReturn("custo");
		given(propMock.get("import.clarion.client.sp", String.class)).willReturn("sp");
		
		Set<String> errors = validator.validator(purchase);
		
		assertTrue(errors.isEmpty());
	}

	@Test
	public void shouldCallPurchaseItemValidator() {
		PurchaseItem purchaseItemMock = mock(PurchaseItem.class);
		
		Purchase purchase = new Purchase(2l);
		purchase.setLang(Language.PT);
		purchase.setResponsible(companyStub());
		purchase.addPuchaseItem(purchaseItemMock);
		
		KProperties propMock = mock(KProperties.class);
		Set<String> errorsItems = new HashSet<String>();
		errorsItems.add("error-item");
		
		given(properties.loadSystemProperty()).willReturn(propMock);
		given(propMock.get("import.clarion.centro.custo", String.class)).willReturn("custo");
		given(propMock.get("import.clarion.client.sp", String.class)).willReturn("sp");
		given(this.purchaseItemValidator.validator(purchaseItemMock)).willReturn(errorsItems);
		
		Set<String> errors = validator.validator(purchase);
		
		assertTrue(errors.contains("error-item"));
		verify(this.purchaseItemValidator).validator(purchaseItemMock);
	}

	private Company companyStub() {
		Company company = new Company(2l);
		company.setName("name");
		company.setCode("code");
		
		return company;
	}

}

