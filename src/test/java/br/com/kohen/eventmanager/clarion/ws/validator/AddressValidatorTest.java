package br.com.kohen.eventmanager.clarion.ws.validator;

import static junit.framework.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.City;
import br.com.kohen.eventmanager.commons.entity.Country;
import br.com.kohen.eventmanager.commons.entity.State;
import br.com.kohen.eventmanager.commons.enums.AddressType;

@RunWith(MockitoJUnitRunner.class)
public class AddressValidatorTest {

	private @InjectMocks AddressValidator validator;
	
	@Test
	public void shouldValidateStateIfBrazilianCompany() {
		Address addressStub = nationalAddressStub();
		addressStub.setState(null);
		
		Set<String> errors = validator.validator(addressStub);
		
		assertTrue(!errors.isEmpty());
		assertTrue(errors.contains("Empresa brasileira sem estado --"));
	}
	
	@Test
	public void shouldValidateCityIfBrazilianCompany() {
		Address addressStub = nationalAddressStub();
		addressStub.setCityy(null);
		
		Set<String> errors = validator.validator(addressStub);
		
		assertTrue(!errors.isEmpty());
		assertTrue(errors.contains("Empresa brasileira sem cidade --"));
	}

	@Test
	public void shouldValidateIfValidCountry() {
		Address addressStub = nationalAddressStub();
		addressStub.setCountry(null);
		
		Set<String> errors = validator.validator(addressStub);
		
		assertTrue(!errors.isEmpty());
		assertTrue(errors.contains("Endereco de cobranca sem pais --"));
	}
	
	@Test
	public void shouldNotValidateCityWhenCompanyIsInternational() {
		Address addressStub = internationalAddressStub();
		addressStub.setCityy(null);
		addressStub.setState(null);
		addressStub.getCountry().setId(10l);
		
		Set<String> errors = validator.validator(addressStub);
		
		assertTrue(!errors.contains("Empresa brasileira sem cidade --"));
	}
	
	@Test
	public void shouldNotValidateStateWhenCompanyIsInternational() {
		Address addressStub = internationalAddressStub();
		addressStub.setCityy(null);
		addressStub.setState(null);
		addressStub.getCountry().setId(10l);
		
		Set<String> errors = validator.validator(addressStub);
		
		assertTrue(!errors.contains("Empresa brasileira sem estado --"));
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
