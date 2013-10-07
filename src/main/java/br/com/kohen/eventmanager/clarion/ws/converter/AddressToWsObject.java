package br.com.kohen.eventmanager.clarion.ws.converter;

import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;
import static br.com.kohen.eventmanager.commons.enums.AddressType.BUSINESS;
import br.com.kohen.eventmanager.clarion.CountryEnum;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Address;

public class AddressToWsObject {

	
	
	public void merge(Address address, GRAVADADOS gravados) {
		
		if (!BUSINESS.equals(address.getAddressType())) {
			return;
		}
		
		gravados.setBAIRRO(safeNull(address.getNeighborhood()).toUpperCase());
		gravados.setCEP(safeNull(address.getZipCode()).toUpperCase());
		gravados.setCOMPLTO(safeNull(address.getComplement()).toUpperCase());
		gravados.setNUMERO(safeNull(address.getNumber()));
		gravados.setENDERECO(safeNull(address.getStreet()).toUpperCase());
		gravados.setPAIS(safeNull(address.getCountry().getCodeBacen()));
		
		if (CountryEnum.BRAZIL_CODE.getCode().equals(address.getCountry().getId())) {
			gravados.setUF(safeNull(address.getState().getName()).toUpperCase());
			gravados.setCIDADE(safeNull(address.getCityy().getCode()));
		}
		
	}
}
