package br.com.kohen.eventmanager.clarion.ws.utils;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.City;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Country;
import br.com.kohen.eventmanager.commons.entity.State;
import br.com.kohen.eventmanager.commons.enums.AddressType;

@RunWith(MockitoJUnitRunner.class)
public class CompanyUtilsTest {

	
	@Test
	public void shouldReturnTrueWhenCompanyIsInternational() {
		Company company = new Company();
		company.addAddress(internationalAddressStub());
		
		Boolean international = CompanyUtils.isInternational(company);
		
		assertEquals(Boolean.TRUE, international);
	}
	
	@Test
	public void shouldReturnFalseWhenCompanyNotIsInternatinal() {
		Company company = new Company();
		company.addAddress(nationalAddressStub());
		
		Boolean international = CompanyUtils.isInternational(company);
		
		assertEquals(Boolean.FALSE, international);
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
}
