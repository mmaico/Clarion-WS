package br.com.kohen.eventmanager.clarion.ws.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

	private static final Log LOG = LogFactory.getLog(CompanyImportServiceImpl.class);
	
	@Autowired(required=false)
	private CompanyImportDAO companyImportDAO;
	
	public String importCompany(Company company) {
		
		Boolean enabled = new PropertiesAcessor().loadSystemProperty().get("import.clarion.enabled", Boolean.class);
		
		if (enabled == null || !enabled) {
			LOG.debug("################## ---------> Sistema de exportacao de empresas esta desativado" );
			return "Processo de Importacao nao esta habilitado";
		}	
		
		Map<String, String> hasErrors = company.hasErrors();
		
		if (hasErrors.isEmpty()) {
			LOG.debug("################## ---------> Company validado, sem erros :" + company.getName());
			
		} else {
			LOG.debug("################## ---------> Ocorreram erros de validacao :" + company.getName());
			String errors = logImportErrors(hasErrors);
			
			return errors;
		}
		
		return callExternalServiceAndWaitReturn(company);
		
	}
	
	
	private String callExternalServiceAndWaitReturn(Company company) {
		
		GRAVADADOS convertToGravados = GRAVADADOS.convertToGravados(company);
		LOG.debug("################### ----------> Parse finalizado...Chamando o servico da Clarion...");
		
		String importCompany = companyImportDAO.importCompany(convertToGravados);
		
		LOG.debug("################### ----------> Servico chamado, o codigo de retorno : " + importCompany);
		
		String codeFixed = fixCodeReturned(importCompany);
		
		if (!NumberUtils.isNumber(codeFixed)) {
			LOG.error("################### ----------> Erro no codigo de retorno... : " + importCompany);
			company.setCode(null);
			return "Erro na importacao " + codeFixed;
		}
		LOG.error("################### ----------> Empresa importada com SUCESSO: " + importCompany);
		company.setCode(codeFixed);
		
		return "Sucesso na importacao: " + codeFixed;
		
	}
	
	private String logImportErrors(Map<String, String> hasErrors) {
		
		Set<Entry<String, String>> errors = hasErrors.entrySet();
		
		StringBuilder errorsMessages = new StringBuilder();
		
		for (Entry<String, String> entry : errors) {
			errorsMessages.append(entry.getKey() +" "+ entry.getValue());
		}
		
		LOG.error("###################### ----> Erro na validacao da company -> messages:" + errorsMessages);
		
		return errorsMessages.toString(); 
	}


	private String fixCodeReturned(String importCompany) {
		if (importCompany == null)
			return null;
		
		return importCompany.replace(":", "").trim();
		
	}


	
	
	
	
}
