package br.com.kohen.eventmanager.clarion.ws.utils;

import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;

import org.apache.commons.lang.StringUtils;

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
	
	public static String treatName(String name) {
		
		return safeNull(name).replaceAll("(:|\\|/|-|\\+|\\*|%|\\$|#|@|!|=)", StringUtils.EMPTY)
				.toUpperCase().trim();
	}
	
	public static Boolean isValidProtheusCode(String value) {
		
		String valueTreat = value.replaceAll("[:-]", "").trim();
		
		try {
			Long.valueOf(valueTreat);
		} catch (NumberFormatException e) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
	public static String treatProtheusCode(String value) {
		
		return value.replaceAll("[:-]", "").trim();
	}
}
