package br.com.kohen.eventmanager.clarion.ws.service;

import static br.com.kohen.eventmanager.commons.utils.ValidationUtils.error;
import static br.com.kohen.eventmanager.commons.utils.ValidationUtils.noErrors;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.dao.ClarionCompanyDAO;
import br.com.kohen.eventmanager.clarion.ws.dao.CompanyImportDAO;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Company;

@Component
@Transactional
@SuppressWarnings({"unchecked", "rawtypes"})
public class CompanyImportServiceImpl {

	private Log log = LogFactory.getLog(CompanyImportServiceImpl.class);
	
	private static Boolean running = false;
	
	@Autowired(required=false)
	private CompanyImportDAO companyImportDAO;
	
	@Autowired
	@Qualifier("commonBaseDAO")
	private CommonBaseDAO baseDAO;
	
	private PropertiesAcessor properties = new PropertiesAcessor();
	
	@Autowired
	@Qualifier("clarionCompanyDAO")
	private ClarionCompanyDAO companyDAO;
	
	@Scheduled(fixedDelay=30000)
	public void importCompanySchedule() {
		
		try {
			
			if (running)
				return;
			
			running = true;
			
			List<Company> list = companyDAO.getAllCompanyNotImported();
			for (Company company : list) {
				try {
				 importCompany(company);
				}catch(Exception e) {
					log.error("########################## Erro no processo de envio da empresa: " + company.getId() +" --" + e.getMessage());
				}
			}
		} finally {
			running = false;
		}
	}
	
	
	public Map<String, String> importCompany(Company company) {
		
		Boolean enabled = properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class);
		
		if (enabled == null || !enabled) {
			log.debug("################## ---------> Sistema de exportacao de empresas esta desativado" );
			return error("not.available", "Processo de Importacao nao esta habilitado") ;
		}	
		
		return callExternalServiceAndWaitReturn(company);
		
	}
	
	private Map<String, String> callExternalServiceAndWaitReturn(Company company) {
		try {
			GRAVADADOS convertToGravados = GRAVADADOS.convertToGravados(company);
			
			String validateMessage = GRAVADADOS.validate(company);
			if (validateMessage.length() > 0) {
				ImportError importError = ImportError.internalError(validateMessage).companyId(company.getId());
				saveErrorInDatabase(importError);
				
				return error("validation.error", validateMessage) ;
			}
			
			log.debug("################### ----------> Parse finalizado...Chamando o servico da Clarion...");
			
			String importCompany = companyImportDAO.importCompany(convertToGravados);
			
			log.debug("################### ----------> Servico chamado, o codigo de retorno : " + importCompany);
			
			String codeFixed = fixCodeReturned(importCompany);
			
			if (!NumberUtils.isNumber(codeFixed)) {
				company.setCode(null);
				ImportError importError = ImportError.exError(importCompany).companyId(company.getId());
				saveErrorInDatabase(importError);
				
				return error("invalid.code.returned", "Erro na importacao " + codeFixed);
			}
			log.error("################### ----------> Empresa importada com SUCESSO: " + importCompany);
			
			Company companyLoaded = (Company)baseDAO.findById(Company.class, company.getId());
			
			if (companyLoaded != null) {
				companyLoaded.setCode(codeFixed);
				baseDAO.saveOrUpdate(companyLoaded);
			}	
			
			return noErrors();
		} catch (Exception e) {
			return error("unexpected.error", e.getMessage());
		}
		
	}

	
	private void saveErrorInDatabase(ImportError importError) {
		ImportError error = (ImportError) companyDAO.getCompanyErrorById(importError.getId());
		
		if (error != null) {
			error.setCause(importError.getCause());
			error.setCauseEx(importError.getCauseEx());
			baseDAO.saveOrUpdate(error);
		} else {
			baseDAO.saveOrUpdate(importError);
		}
		
	}

	private String fixCodeReturned(String importCompany) {
		if (importCompany == null)
			return null;
		
		return importCompany.replace(":", "").trim();
		
	}


	
	
	
	
}
