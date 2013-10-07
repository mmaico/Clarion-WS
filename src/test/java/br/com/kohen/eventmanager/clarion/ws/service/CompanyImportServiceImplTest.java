package br.com.kohen.eventmanager.clarion.ws.service;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kohen.eventmanager.clarion.service.impl.CompanyImportServiceImpl;
import br.com.kohen.eventmanager.commons.config.KProperties;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;

@RunWith(MockitoJUnitRunner.class)
public class CompanyImportServiceImplTest {

	
	@InjectMocks
	private CompanyImportServiceImpl importService;
	
	@Mock
	private CompanyWsService companyWsService;
	
	@Mock
	private Log log;
	
	@Mock
	private PropertiesAcessor properties;
	
	@Before
	public void setUp() {
		
		when(properties.loadSystemProperty()).thenReturn(Mockito.mock(KProperties.class));
	}
	
	
	@Test
	public void shouldDoNothingWhenDisabled() {
		
		when(properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class)).thenReturn(false);
		
		importService.importCompany(new Company());
		
		verifyNoMoreInteractions(companyWsService);
		
	}
	

	@Test
	public void shouldDoNothingWhenNull() {
		
		when(properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class)).thenReturn(null);
		
		importService.importCompany(new Company());
		
		verifyNoMoreInteractions(companyWsService);
	}
	

}
