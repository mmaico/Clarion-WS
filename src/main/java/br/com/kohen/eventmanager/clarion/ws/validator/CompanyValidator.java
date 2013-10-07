package br.com.kohen.eventmanager.clarion.ws.validator;

import static br.com.kohen.eventmanager.commons.enums.AddressType.BUSINESS;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.ws.utils.CompanyUtils;
import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.Company;

@Component
public class CompanyValidator {

	private @Autowired AddressValidator addressValidator;
	
	
	public Set<String> validator(Company company) {
		Set<String> errors = new HashSet<String>();
		
		if (isBlank(company.getName())) {
			errors.add("Nome da empresa esta vazio --");
		}
		
		if (isBlank(company.getTradingName())) {
			errors.add("Nome Fantasia esta vazio --");
		}
		
		Address businessAddress = company.getAddresss(BUSINESS);
		
		if (businessAddress == null) {
			errors.add("Nao tem informacao de endereco de cobranca --");
			return errors;
		}
		
		Set<String> errorsAddress = this.addressValidator.validator(businessAddress);
		
		errors.addAll(errorsAddress);
		
		Boolean international = CompanyUtils.isInternational(company);
		
		if (!international) {
			if (isBlank(company.getCnpj())) {
				errors.add("Empresa brasileira sem cnpj --");
			}
		}
		
		return errors;
	}
}
