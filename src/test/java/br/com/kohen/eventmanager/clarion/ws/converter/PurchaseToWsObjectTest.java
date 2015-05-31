package br.com.kohen.eventmanager.clarion.ws.converter;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.WsInfoEnum;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSCR;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.KProperties;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.entity.PurchaseItem;
import br.com.kohen.eventmanager.commons.enums.Language;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseToWsObjectTest {

	private @InjectMocks PurchaseToWsObject converter;
	
	private @Mock  PurchaseItemToWsObject purchaseItemConverter;
	
	private @Mock PropertiesAcessor properties;
	
	private Date currentDate;
	private Date dateDue;
	
	@Before
	public void setUp() throws ParseException {
		
		this.currentDate = new SimpleDateFormat("dd-MM-yyyy").parse("06-10-2013");
		this.dateDue = new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2013");
	}
	
	@Test
	public void shouldConvertPurchaseToWsObject() throws ParseException {
		this.converter = spy(this.converter);
		
		Purchase purchase = new Purchase(2l);
		purchase.setPurchseItems(new ArrayList<PurchaseItem>());
		purchase.setLang(Language.PT);
		purchase.setResponsible(companyStub());
		purchase.setPaymentDue(this.dateDue);
		purchase.setTotal(BigDecimal.TEN);
		
		KProperties kpropMock = mock(KProperties.class);
		
		doReturn(this.currentDate).when(this.converter).getCurrentDate();
		given(properties.loadSystemProperty()).willReturn(kpropMock);
		given(kpropMock.get("import.clarion.centro.custo", String.class)).willReturn("custo");
		given(kpropMock.get("import.clarion.client.sp", String.class)).willReturn("sp");
		
		APEDIDO converted = null;//converter.convert(purchase);
		DADOSCR dadoscr = converted.getPARCELAS().getDADOSCR().get(0);
		
		assertEquals("custo", converted.getCUSTO());
		assertEquals("sp", converted.getRECISS());
		assertEquals(WsInfoEnum.WS_KEY.getValue(), converted.getCHAVE());
		assertEquals("custo2", converted.getCODPVBOX());
		assertEquals("1", converted.getMOEDA());
		assertEquals("10", dadoscr.getNVALPARC());
		assertEquals("11-10-2013", dadoscr.getDVENCPARC());
	}
	
	@Test
	public void shouldConvertPurchaseItem() {
		PurchaseItem purchaseItemMock = mock(PurchaseItem.class);
		DADOSITENS dadositensMock = mock(DADOSITENS.class);
		
		Purchase purchase = new Purchase(2l);
		purchase.setResponsible(companyStub());
		purchase.setPurchseItems(asList(purchaseItemMock));
		purchase.setLang(Language.PT);
		purchase.setPaymentDue(this.dateDue);
		purchase.setTotal(BigDecimal.TEN);
		
		KProperties kpropMock = mock(KProperties.class);
		
		given(properties.loadSystemProperty()).willReturn(kpropMock);
		given(kpropMock.get("import.clarion.centro.custo", String.class)).willReturn("custo");
		given(kpropMock.get("import.clarion.client.sp", String.class)).willReturn("sp");
		//given(this.purchaseItemConverter.convert(purchaseItemMock)).willReturn(dadositensMock);
		
		APEDIDO converted = null;//converter.convert(purchase);

		DADOSITENS dadositens = converted.getITENS().getDADOSITENS().get(0);
		
		//verify(this.purchaseItemConverter).convert(purchaseItemMock);
		Assert.assertSame(dadositensMock, dadositens);
	}
	
	private Company companyStub() {
		Company company = new Company(2l);
		company.setName("name");
		company.setCode("code");
		
		return company;
	}

}
