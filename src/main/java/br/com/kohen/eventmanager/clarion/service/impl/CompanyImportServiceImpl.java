package br.com.kohen.eventmanager.clarion.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.dao.ClarionCompanyDAO;
import br.com.kohen.eventmanager.clarion.ws.service.CompanyWsService;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Company;

@Component
@Transactional
@SuppressWarnings({"rawtypes"})
public class CompanyImportServiceImpl {

	private Log log = LogFactory.getLog(CompanyImportServiceImpl.class);
	
	private static Boolean running = false;
	
	@Autowired
	@Qualifier("commonBaseDAO")
	private CommonBaseDAO baseDAO;
	
	private PropertiesAcessor properties = new PropertiesAcessor();
	
	@Autowired
	@Qualifier("clarionCompanyDAO")
	private ClarionCompanyDAO companyDAO;
	
	@Autowired
	private CompanyWsService companyWsService;
	
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
	
	public void importCompany(Company company) {
		
		Boolean enabled = properties.loadSystemProperty().get("import.clarion.enabled", Boolean.class);
		
		if (enabled == null || !enabled) {
			log.debug("################## ---------> Sistema de exportacao de empresas esta desativado" );
			return;
		}
		
		companyWsService.sendToWs(company);
	}
	
}
