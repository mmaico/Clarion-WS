package br.com.kohen.eventmanager.clarion.ws.service;

import static br.com.kohen.eventmanager.commons.utils.ValidationUtils.error;
import static br.com.kohen.eventmanager.commons.utils.ValidationUtils.noErrors;

import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.ws.dao.CompanyImportDAO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;

@Component
@Transactional
public class CompanyImportServiceImpl {

	private Log log = LogFactory.getLog(CompanyImportServiceImpl.class);
	
	@Autowired(required=false)
	private CompanyImportDAO companyImportDAO;
	
	private PropertiesAcessor properties = new PropertiesAcessor();
	
	
	public Map<String, String> importCompany(Company company) {
		
		Boolean enabled = properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class);
		
		if (enabled == null || !enabled) {
			log.debug("################## ---------> Sistema de exportacao de empresas esta desativado" );
			return error("not.available", "Processo de Importacao nao esta habilitado") ;
		}	
		
		return callExternalServiceAndWaitReturn(company);
		
	}
	
	
	private Map<String, String> callExternalServiceAndWaitReturn(Company company) {
		
		GRAVADADOS convertToGravados = GRAVADADOS.convertToGravados(company);
		log.debug("################### ----------> Parse finalizado...Chamando o servico da Clarion...");
		
		String importCompany = companyImportDAO.importCompany(convertToGravados);
		
		log.debug("################### ----------> Servico chamado, o codigo de retorno : " + importCompany);
		
		String codeFixed = fixCodeReturned(importCompany);
		
		if (!NumberUtils.isNumber(codeFixed)) {
			log.error("################### ----------> Erro no codigo de retorno... : " + importCompany);
			company.setCode(null);
			return error("invalid.code.returned", "Erro na importacao " + codeFixed);
		}
		log.error("################### ----------> Empresa importada com SUCESSO: " + importCompany);
		company.setCode(codeFixed);
		
		return noErrors();
		
	}

	private String fixCodeReturned(String importCompany) {
		if (importCompany == null)
			return null;
		
		return importCompany.replace(":", "").trim();
		
	}


	
	
	
	
}
