package br.com.kohen.eventmanager.clarion.ws.validator;

import static junit.framework.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.City;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Contact;
import br.com.kohen.eventmanager.commons.entity.Country;
import br.com.kohen.eventmanager.commons.entity.State;
import br.com.kohen.eventmanager.commons.enums.AddressType;

@RunWith(MockitoJUnitRunner.class)
public class CompanyValidatorTest {

	private @InjectMocks CompanyValidator validator;
	private @Mock AddressValidator addressValidator;
	private @Mock ContactValidator contactValidator;
	
	@Test
	public void shouldVerifyIfNameIsNotEmpty() {
		Company companyStub = getCompanyStub();
		companyStub.setName(null);
		
		Set<String> errors = validator.validator(companyStub);
		
		assertTrue(!errors.isEmpty());
		assertTrue(errors.contains("Nome da empresa esta vazio --"));
	}

	@Test
	public void shouldVerifyIfTradingNameIsNotEmpty() {
		Company companyStub = getCompanyStub();
		companyStub.setTradingName(null);
		
		Set<String> errors = validator.validator(companyStub);
		
		assertTrue(!errors.isEmpty());
		assertTrue(errors.contains("Nome Fantasia esta vazio --"));
	}
	
	@Test
	public void shouldVerifyIfExistBusinessAddress() {
		Company companyStub = getCompanyStub();
		companyStub.setAddresss(null);
		
		Set<String> errors = validator.validator(companyStub);
		
		assertTrue(!errors.isEmpty());
		assertTrue(errors.contains("Nao tem informacao de endereco de cobranca --"));
	}
	
	@Test
	public void shouldCallValidatorAddress() {
		Address nationalAddressStub = nationalAddressStub();
		nationalAddressStub.setId(3l);
		
		Company companyStub = getCompanyStub();
		companyStub.addAddress(nationalAddressStub);
		
		validator.validator(companyStub);
		
		verify(this.addressValidator).validator(nationalAddressStub);
	}
	
	@Test
	public void shouldMakeMargeWithAddressValidator() {
		Address nationalAddressStub = nationalAddressStub();
		nationalAddressStub.setId(3l);
		
		Company companyStub = getCompanyStub();
		companyStub.addAddress(nationalAddressStub);
		
		Set<String> addressErrors = new HashSet<String>();
		addressErrors.add("error-address");
		
		given(this.addressValidator.validator(nationalAddressStub)).willReturn(addressErrors);
		
		Set<String> errors = validator.validator(companyStub);
		
		assertTrue(errors.contains("error-address"));
	}
	
	@Test
	public void shouldValidateCnpjIfNationalCompany() {
		Company companyStub = getCompanyStub();
		companyStub.setCnpj(null);
		companyStub.addAddress(nationalAddressStub());
		
		Set<String> errors = validator.validator(companyStub);
		
		assertTrue(errors.contains("Empresa brasileira sem cnpj --"));
	}
	
	@Test
	public void shouldNotValidateCnpjWhenCompanyNotIsNational() {
		Company companyStub = getCompanyStub();
		companyStub.setCnpj(null);
		companyStub.addAddress(internationalAddressStub());
		
		Set<String> errors = validator.validator(companyStub);
		
		assertTrue(errors.isEmpty());
	}
	
	private Company getCompanyStub() {
		Company company = new Company();
		company.setName("Company Name");
		company.setTradingName("trading name");
		company.setCnpj("8839938839938");
		company.addAddress(nationalAddressStub());
		company.setContacts(new ArrayList<Contact>());
		
		return company;
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
