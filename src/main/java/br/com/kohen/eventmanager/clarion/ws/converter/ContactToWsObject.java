package br.com.kohen.eventmanager.clarion.ws.converter;

import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;
import static br.com.kohen.eventmanager.commons.enums.ContactType.PRIMARY;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Contact;

public class ContactToWsObject {

	public void merge(Contact contact, GRAVADADOS gravados) {
	
		if (PRIMARY.equals(contact.getContactType())) {
			gravados.setCONTATO(safeNull(contact.getName()).toUpperCase());
			gravados.setCARGO(safeNull(contact.getPosition()).toUpperCase());
			gravados.setTEL(safeNull(contact.getPhone()).toUpperCase());
			gravados.setTELCEL(safeNull(contact.getCellPhone()).toUpperCase());
			gravados.setEMAIL(safeNull(contact.getEmail()).toUpperCase());
		}	
	}
}
