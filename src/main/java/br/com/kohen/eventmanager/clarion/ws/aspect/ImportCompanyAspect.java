package br.com.kohen.eventmanager.clarion.ws.aspect;

import static br.com.kohen.eventmanager.clarion.email.Messages.INVALID_PARAM;
import static br.com.kohen.eventmanager.clarion.email.Messages.INVALID_PARAMS;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.email.Messages;
import br.com.kohen.eventmanager.clarion.ws.service.CompanyImportServiceImpl;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.CompanyRelationship;

@Aspect
@Component
public class ImportCompanyAspect {

	private Log log = LogFactory.getLog(ImportCompanyAspect.class);
	
	private static final Integer COMPANY = 0;
	
	private static final Integer COMPANY_RELATION = 0;
	
	
	@Autowired
	private CompanyImportServiceImpl companyImportService;
	
	
	
	@Around("methodImporCompany()")
	public Object importCompanyToClarionSystem(ProceedingJoinPoint join) throws Throwable {
		Map<String, String> errors = new HashMap<String, String>();
		Object proceed = null;
		
		try {	
			log.debug(Messages.START_OP);
			Object[] args = join.getArgs();
			
			if (!isValidParams(args, CompanyRelationship.class, COMPANY_RELATION)) {
				return join.proceed();
			}
			
			proceed = join.proceed();
			if (hasError(proceed)) {
				return proceed;
			}
			
			CompanyRelationship relation = (CompanyRelationship) args[COMPANY_RELATION];
			
			errors = companyImportService.importCompany(relation.getPartner());
			
			if (!errors.isEmpty()) {
				log.error(Messages.BAD_RETURN);
			}
			
		}catch(Throwable e) {
			errors.put("error", e.getMessage());
		}
		
		return proceed;
	}


	@SuppressWarnings("rawtypes")
	private boolean hasError(Object proceed) {
		
		if (proceed instanceof Map) {
			Map errors = (Map) proceed;
			
			if (errors != null && !errors.isEmpty()) {
				return true;
			}
		}
		
		return false;
	}


	private Boolean isValidParams(Object[] args, Class clazz, Integer positionObject) {
		
		if (args == null || args.length < 1) {
			log.debug(INVALID_PARAMS);
			return false;
		}
		
		Object object = (Object) args[positionObject];
		
		if (object == null) {
			log.debug(INVALID_PARAM);
			return false;
		}
		
		boolean equals = object.getClass().equals(clazz);
		
		if (!equals) {
			return false;
		}
		
		return true;
	}
	
	
	private Map<String, String> getMapMessages(String message) {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		errors.put("erro.na.sincronizacao", message);
		
		return errors;
	}
	
	
	@Pointcut("execution(* br.com.kohen.eventmanager.ecommerce.service.impl.ViewCompanyRelationshipServiceImpl.saveRelationProvider(..))")
	public void methodImporCompany(){}
	
	
	
	
	
}
