package br.com.kohen.eventmanager.clarion.ws.utils;

import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.enums.AddressType;

public class CompanyUtils {

	private static final Long BRAZIL_CODE = 31l;
	
	public static Boolean isInternational(Company company) {
		
		Address addresss = company.getAddresss(AddressType.BUSINESS);
		
		if (BRAZIL_CODE.equals(addresss.getCountry().getId())) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
