package br.com.kohen.eventmanager.clarion.ws.aspect;

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

import br.com.kohen.eventmanager.clarion.ws.service.CompanyImportServiceImpl;
import br.com.kohen.eventmanager.commons.entity.Company;

@Aspect
@Component
public class ImportCompanyAspect {

	private static final Log LOG = LogFactory.getLog(ImportCompanyAspect.class);
	
	private static final Integer COMPANY = 0;
	
	@Autowired
	private CompanyImportServiceImpl companyImportService;
	
	
	
	@Around("methodImporCompany()")
	public Object importCompanyToClarionSystem(ProceedingJoinPoint join) throws Throwable {
		String messageReturned = "";
		try {	
			LOG.debug("############### -------> Iniciando o aspecto sobre o salvamento da Company");
			
			
			Object[] args = join.getArgs();
			
			if (args == null || args.length < 1) {
				LOG.debug("############### -------> O metodo aspectado nao tem parametro----> abortado o processo.");
			}
			
			Company company = (Company) args[COMPANY];
			
			if (company == null) {
				LOG.debug("############### -------> A empresa passada no parametro esta nula ----> abortado o processo");
			}
			
			messageReturned = companyImportService.importCompany(company);
			
			if (company.getCode() == null) {
				LOG.debug("############### -------> Houve erro na importacao e o metodo de servico nao sera chamado");
				
				return getMapMessages(messageReturned);
			}
			
			return join.proceed();
		}catch(Throwable e) {
			return getMapMessages(messageReturned);
		}
	}
	
	
	private Map<String, String> getMapMessages(String message) {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		errors.put("erro.na.sincronizacao", message);
		
		return errors;
	}
	
	
	@Pointcut("execution(* br.com.kohen.eventmanager.onsite.service.impl.OnSiteCompanyServiceImpl.save(..))")
	public void methodImporCompany(){}
	
	
	
	
	
}
