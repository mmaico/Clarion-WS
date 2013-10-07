package br.com.kohen.eventmanager.clarion.ws.converter;

import static br.com.kohen.eventmanager.clarion.WsInfoEnum.INTERNATIONAL_CITY;
import static br.com.kohen.eventmanager.clarion.WsInfoEnum.INTERNATIONAL_CNPJ;
import static br.com.kohen.eventmanager.clarion.WsInfoEnum.INTERNATIONAL_UF;
import static br.com.kohen.eventmanager.clarion.WsInfoEnum.WS_KEY;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.City;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Contact;
import br.com.kohen.eventmanager.commons.entity.Country;
import br.com.kohen.eventmanager.commons.entity.State;
import br.com.kohen.eventmanager.commons.enums.AddressType;

@RunWith(MockitoJUnitRunner.class)
public class CompanyToWsObjectTest {

	private @InjectMocks CompanyToWsObject companyToWs;
	private @Mock AddressToWsObject addressConverter;
	private @Mock ContactToWsObject contactConverter;
	
	@Test
	public void shouldConvertCompanyToWsObject() {
		Company companyStub = getNationalCompanyStub();
		companyStub.setAddresss(asList(nationalAddressStub()));
		
		GRAVADADOS gravadados = companyToWs.convert(companyStub);
		
		assertEquals(companyStub.getIe(), gravadados.getIE());
		assertEquals(companyStub.getName().toUpperCase(), gravadados.getNOME());
		assertEquals(companyStub.getTradingName().toUpperCase(), gravadados.getNOMEFANT());
		assertEquals(companyStub.getId().toString(), gravadados.getCODIGO());
		assertEquals(WS_KEY.getValue(), gravadados.getCHAVE());
		
	}
	
	@Test
	public void shouldAddCNPJWhenNationalCompany() {
		Company companyStub = getNationalCompanyStub();
		companyStub.setAddresss(asList(nationalAddressStub()));
		
		GRAVADADOS gravadados = companyToWs.convert(companyStub);
		
		assertEquals("10910578000130", gravadados.getCNPJ());
	}
	
	@Test
	public void shouldAddAdditionalInformationWhenInternationalCompany() {
		Company companyStub = getInternationalCompanyStub();
		GRAVADADOS gravadados = companyToWs.convert(companyStub);
		
		assertEquals(INTERNATIONAL_CNPJ.getValue(), gravadados.getCNPJ());
		assertEquals(INTERNATIONAL_UF.getValue(), gravadados.getUF());
		assertEquals(INTERNATIONAL_CITY.getValue(), gravadados.getCIDADE());
	}
	
	@Test
	public void shouldCallAddressMerge() {
		this.companyToWs = spy(this.companyToWs);
		GRAVADADOS gravadosMock = mock(GRAVADADOS.class);
		Company companyStub = getNationalCompanyStub();
		companyStub.setAddresss(asList(nationalAddressStub()));
		
		doReturn(gravadosMock).when(companyToWs).createInstanceGravados();
		
		companyToWs.convert(companyStub);
		
		verify(this.addressConverter).merge(companyStub.getAddresss().get(0), gravadosMock);
	}
	
	@Test
	public void shouldCallContactMerge() {
		this.companyToWs = spy(this.companyToWs);
		GRAVADADOS gravadosMock = mock(GRAVADADOS.class);
		Company companyStub = getNationalCompanyStub();
		companyStub.setAddresss(asList(nationalAddressStub()));
		
		doReturn(gravadosMock).when(companyToWs).createInstanceGravados();
		
		companyToWs.convert(companyStub);
		
		verify(this.contactConverter).merge(companyStub.getContacts().get(0), gravadosMock);
	}

	private Company getNationalCompanyStub() {
		Company company = new Company(2l);
		company.setIe("ie");
		company.setName("Company name");
		company.setTradingName("Company trading name");
		company.setCnpj("10.910.578/0001-30");
		company.setAddresss(asList(mock(Address.class)));
		company.setContacts(asList(mock(Contact.class)));
		
		return company;
	}
	
	private Company getInternationalCompanyStub() {
		Company company = new Company(2l);
		company.setName("Company name");
		company.setTradingName("Company trading name");
		company.addAddress(internationalAddressStub());
		company.setContacts(asList(mock(Contact.class)));
		
		return company;
	}
	
	private Address internationalAddressStub() {
		Address address = new Address();
		address.setAddressType(AddressType.BUSINESS);
		address.setNeighborhood("Oxyo");
		address.setZipCode("zipcode");
		address.setComplement("Complement");
		address.setNumber("number");
		address.setStreet("Street");
		
		Country country = new Country(8l);
		country.setCodeBacen("bacen");
		
		State state = new State();
		state.setId(1l);
		state.setName("Foreign company");
		
		address.setCountry(country);
		address.setState(state);
		
		return address;
	}
	
	private Address nationalAddressStub() {
		Address address = new Address();
		address.setAddressType(AddressType.BUSINESS);
		address.setNeighborhood("Santo Amaro");
		address.setZipCode("cep");
		address.setComplement("Complement");
		address.setNumber("number");
		address.setStreet("Street");
		
		Country country = new Country(31l);
		country.setCodeBacen("bacen");
		
		State state = new State();
		state.setId(2l);
		state.setName("State");
		
		City city = new City();
		city.setCode("code");
		
		address.setCountry(country);
		address.setCityy(city);
		address.setState(state);
		
		return address;
	}
}
