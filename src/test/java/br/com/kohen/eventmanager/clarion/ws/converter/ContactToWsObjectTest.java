package br.com.kohen.eventmanager.clarion.ws.converter;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Contact;
import br.com.kohen.eventmanager.commons.enums.ContactType;

@RunWith(MockitoJUnitRunner.class)
public class ContactToWsObjectTest {

	private @InjectMocks ContactToWsObject contactWs;
	
	
	@Test
	public void shouldConvertContact() {
		GRAVADADOS gravados = new GRAVADADOS();
		Contact contactStub = contactStub();
		
		contactWs.merge(contactStub, gravados);
		
		assertEquals(contactStub.getName().toUpperCase(), gravados.getCONTATO());
		assertEquals(contactStub.getPosition().toUpperCase(), gravados.getCARGO());
		assertEquals(contactStub.getPhone().toUpperCase(), gravados.getTEL());
		assertEquals(contactStub.getCellPhone().toUpperCase(), gravados.getTELCEL());
		assertEquals(contactStub.getEmail().toUpperCase(), gravados.getEMAIL());
	}

	@Test
	public void shouldDoNothingWhenContactNotIsAPrimaryContact() {
		GRAVADADOS gravados = new GRAVADADOS();
		Contact contactMock = mock(Contact.class);
		
		given(contactMock.getContactType()).willReturn(ContactType.SECONDARY);
		
		contactWs.merge(contactMock, gravados);
		
		verify(contactMock, never()).getName();
		verify(contactMock, never()).getEmail();
		verify(contactMock, never()).getPhone();
		verify(contactMock, never()).getCellPhone();
		
	}

	
	
	private Contact contactStub() {
		Contact contact = new Contact();
		contact.setName("Name");
		contact.setPosition("Position");
		contact.setPhone("Phone");
		contact.setCellPhone("Cell phone");
		contact.setEmail("email");
		contact.setContactType(ContactType.PRIMARY);
		
		return contact;
	}
}
