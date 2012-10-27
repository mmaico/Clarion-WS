package br.com.kohen.eventmanager.clarion.ws.aspect;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.kohen.eventmanager.clarion.email.Messages;
import br.com.kohen.eventmanager.clarion.ws.service.CompanyImportServiceImpl;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.CompanyRelationship;

@RunWith(MockitoJUnitRunner.class)
public class ImportCompanyAspectTest {

	@InjectMocks 
	private ImportCompanyAspect importCompany;
	
	@Mock
	private CompanyImportServiceImpl companyImportService;
	
	@Mock
	private ProceedingJoinPoint join;
	
	@Mock
	private Log log;
	
	@Test
	public void shouldVerifyIfNumParametersIsValid() throws Throwable {
		Object[] args = new Object[]{};
		
		Mockito.when(join.getArgs()).thenReturn(args);
		
		importCompany.importCompanyToClarionSystem(join);
		
		verify(log).debug(Messages.INVALID_PARAMS);
		verify(companyImportService, never()).importCompany(Mockito.any(Company.class));
		
	}
	
	@Test
	public void shouldVerifyIfCompanyRelationIsValid() throws Throwable {
		
		
		Object[] args = new Object[]{null};
		
		when(join.getArgs()).thenReturn(args);
		
		importCompany.importCompanyToClarionSystem(join);
		
		verify(join).proceed();
		verify(companyImportService, never()).importCompany(Mockito.any(Company.class));
	}
	
	@Test
	public void shouldVerifyIfLoggedError() throws Throwable {
		CompanyRelationship relationship = getRelationStub();
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("error", "Um erro ocorreu");
		
		Object[] args = new Object[]{relationship};
		
		when(join.getArgs()).thenReturn(args);
		when(companyImportService.importCompany(Mockito.any(Company.class))).thenReturn(errors);
		
		importCompany.importCompanyToClarionSystem(join);
		
		verify(log).error(Messages.BAD_RETURN);
		
		verify(companyImportService, Mockito.times(1)).importCompany(Mockito.any(Company.class));
	}
	
	@Test
	public void shouldVerifyIfTargeMethodReturnErrorsWhenHappenDoNothing() throws Throwable {
		CompanyRelationship relationship = getRelationStub();
		
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("error", "Um erro ocorreu");
		
		Object[] args = new Object[]{relationship};
		
		when(join.getArgs()).thenReturn(args);
		
		when(join.proceed()).thenReturn(errors);
		
		importCompany.importCompanyToClarionSystem(join);
		
		verify(companyImportService, never()).importCompany(Mockito.any(Company.class));
	}
	
	
	private CompanyRelationship getRelationStub() {
		
		CompanyRelationship companyRelationship = new CompanyRelationship();
		Company partner = new Company(1l);
		companyRelationship.setPartner(partner);
		
		return companyRelationship;
		
	}
	
	
}
