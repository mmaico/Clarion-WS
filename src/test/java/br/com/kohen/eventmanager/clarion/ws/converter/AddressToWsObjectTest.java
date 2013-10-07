package br.com.kohen.eventmanager.clarion.ws.converter;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.City;
import br.com.kohen.eventmanager.commons.entity.Country;
import br.com.kohen.eventmanager.commons.entity.State;
import br.com.kohen.eventmanager.commons.enums.AddressType;

@RunWith(MockitoJUnitRunner.class)
public class AddressToWsObjectTest {

	private @InjectMocks AddressToWsObject addressToWs;
	
	
	@Test
	public void shouldMergeAddressWithGravados() {
		Address addressStub = nationalAddressStub();
		GRAVADADOS gravados = new GRAVADADOS();
		
		addressToWs.merge(addressStub, gravados);
		
		assertEquals(addressStub.getNeighborhood().toUpperCase(), gravados.getBAIRRO());
		assertEquals(addressStub.getZipCode().toUpperCase(), gravados.getCEP());
		assertEquals(addressStub.getComplement().toUpperCase(), gravados.getCOMPLTO());
		assertEquals(addressStub.getNumber(), gravados.getNUMERO());
		assertEquals(addressStub.getStreet().toUpperCase(), gravados.getENDERECO());
		assertEquals(addressStub.getCountry().getCodeBacen(), gravados.getPAIS());
		assertEquals(addressStub.getCityy().getCode(), gravados.getCIDADE());
		assertEquals(addressStub.getState().getName().toUpperCase(), gravados.getUF());
	}
	
	@Test
	public void shouldMergeInternationalAddressWithGravados() {
		Address addressStub = internationalAddressStub();
		GRAVADADOS gravados = new GRAVADADOS();
		
		addressToWs.merge(addressStub, gravados);
		
		assertEquals(addressStub.getNeighborhood().toUpperCase(), gravados.getBAIRRO());
		assertEquals(addressStub.getZipCode().toUpperCase(), gravados.getCEP());
		assertEquals(addressStub.getComplement().toUpperCase(), gravados.getCOMPLTO());
		assertEquals(addressStub.getNumber(), gravados.getNUMERO());
		assertEquals(addressStub.getStreet().toUpperCase(), gravados.getENDERECO());
		assertEquals(addressStub.getCountry().getCodeBacen(), gravados.getPAIS());
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
