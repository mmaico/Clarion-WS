package br.com.kohen.eventmanager.clarion.ws.converter;

import static br.com.kohen.eventmanager.clarion.WsInfoEnum.INTERNATIONAL_CITY;
import static br.com.kohen.eventmanager.clarion.WsInfoEnum.INTERNATIONAL_CNPJ;
import static br.com.kohen.eventmanager.clarion.WsInfoEnum.INTERNATIONAL_UF;
import static br.com.kohen.eventmanager.clarion.WsInfoEnum.WS_KEY;
import static br.com.kohen.eventmanager.clarion.ws.utils.CompanyUtils.isInternational;
import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;
import static br.com.kohen.eventmanager.commons.utils.StringUtils.removeEspecialChars;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Address;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Contact;

@Component
public class CompanyToWsObject {

	private AddressToWsObject addressConverter;
	private ContactToWsObject contactConverter;
	
	public CompanyToWsObject() {
		this.addressConverter = new AddressToWsObject();
		this.contactConverter = new ContactToWsObject();
	}
	
	public GRAVADADOS convert(Company company) {
		
		GRAVADADOS gravadosCompany = createInstanceGravados();
		gravadosCompany.setCODIGO(company.getId().toString());
		gravadosCompany.setCHAVE(WS_KEY.getValue());
		gravadosCompany.setIE(safeNull(company.getIe()));
		gravadosCompany.setIM("");
		gravadosCompany.setNOME(safeNull(company.getName()).toUpperCase());
		gravadosCompany.setNOMEFANT(safeNull(company.getTradingName()).toUpperCase());
		gravadosCompany.setCNPJ(removeEspecialChars(safeNull(company.getCnpj())));
		
		if (isInternational(company)) {
			gravadosCompany.setCNPJ(INTERNATIONAL_CNPJ.getValue());
			gravadosCompany.setUF(INTERNATIONAL_UF.getValue());
			gravadosCompany.setCIDADE(INTERNATIONAL_CITY.getValue());
		}
		
		List<Address> addresss = company.getAddresss();
		
		for (Address address : addresss) {
			this.addressConverter.merge(address, gravadosCompany);
		}
		
		List<Contact> contacts = company.getContacts();
		
		for (Contact contact : contacts) {
			this.contactConverter.merge(contact, gravadosCompany);
		}
		
		return gravadosCompany;
	}

	GRAVADADOS createInstanceGravados() {
		return new GRAVADADOS();
	}
}
