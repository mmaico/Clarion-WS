package br.com.kohen.eventmanager.clarion.ws.validator;

import static br.com.kohen.eventmanager.clarion.CountryEnum.BRAZIL_CODE;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.commons.entity.Address;

@Component
public class AddressValidator {

	public Set<String> validator(Address address) {
		Set<String> errors = new HashSet<String>();
		
		if (address.getCountry() == null || isBlank(address.getCountry().getCodeBacen())) {
			errors.add("Endereco de cobranca sem pais --");
			return errors;
		}
		
		if (BRAZIL_CODE.getCode().equals(address.getCountry().getId())) {
			if (address.getCityy() == null || isBlank(address.getCityy().getCode())) {
				errors.add("Empresa brasileira sem cidade --");
			}
			
			if (address.getState() == null || isBlank(address.getState().getName())) {
				errors.add("Empresa brasileira sem estado --");
			}
		}
		
		return errors;
	}
}
