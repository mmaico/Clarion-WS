package br.com.kohen.eventmanager.clarion.ws.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.ws.dao.CompanyImportDAO;
import br.com.kohen.eventmanager.commons.config.KProperties;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;

@RunWith(MockitoJUnitRunner.class)
public class CompanyImportServiceImplTest {

	
	@InjectMocks
	private CompanyImportServiceImpl importService;
	
	@Mock
	private CompanyImportDAO companyImportDAO;
	
	@Mock
	private Log log;
	
	@Mock
	private PropertiesAcessor properties;
	
	@Before
	public void setUp() {
		
		when(properties.loadSystemProperty()).thenReturn(Mockito.mock(KProperties.class));
	}
	
	
	@Test
	public void shouldReturnErrorsWithPropertiesDisabled() {
		
		when(properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class)).thenReturn(false);
		
		Map<String, String> errors = importService.importCompany(new Company());
		
		assertTrue(errors.containsKey("not.available"));
	}
	

	@Test
	public void shouldReturnErrorsWithPropertiesNull() {
		
		when(properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class)).thenReturn(null);
		
		Map<String, String> errors = importService.importCompany(new Company());
		
		assertTrue(errors.containsKey("not.available"));
	}
	
	
	

}
