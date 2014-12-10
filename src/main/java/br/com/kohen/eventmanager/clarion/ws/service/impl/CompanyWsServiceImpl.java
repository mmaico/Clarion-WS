package br.com.kohen.eventmanager.clarion.ws.service.impl;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kohen.eventmanager.clarion.repository.ClarionCompanyRepository;
import br.com.kohen.eventmanager.clarion.service.LogService;
import br.com.kohen.eventmanager.clarion.ws.converter.CompanyToWsObject;
import br.com.kohen.eventmanager.clarion.ws.dao.CompanyWsDAO;
import br.com.kohen.eventmanager.clarion.ws.entity.ImportError;
import br.com.kohen.eventmanager.clarion.ws.service.CompanyWsService;
import br.com.kohen.eventmanager.clarion.ws.utils.ClarionWsResponse;
import br.com.kohen.eventmanager.clarion.ws.validator.CompanyValidator;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.repository.CommonCompanyRepository;

@SuppressWarnings("rawtypes")
@Service("companyWsService")
public class CompanyWsServiceImpl implements CompanyWsService {
	
	private Log log = LogFactory.getLog(CompanyWsServiceImpl.class);
	
	private @Autowired(required=false) CompanyWsDAO companyWsDAO;
	private @Autowired CompanyValidator companyValidator;
	private @Autowired CompanyToWsObject companyToObject;
	private @Autowired LogService logService;
	
	@Autowired
	private ClarionCompanyRepository companyRepository;
	
	
	@Override
	public void sendToWs(Company company) {
		
		Set<String> errors = companyValidator.validator(company);
		
		if (!errors.isEmpty()) {
			ImportError importError = ImportError
						.internalError(errors.toString()).companyId(company.getId());
			logService.saveCompanyError(importError);
			return;
		}
		
		GRAVADADOS gravadados = companyToObject.convert(company);
		
		ClarionWsResponse response = companyWsDAO.send(gravadados);
		
		if (!response.isValid()) {
			this.log.error("Erro ao exportar a empresa: " + company.getId() + " " + response.getError());
			
			ImportError importError = ImportError
					.exError(response.getError()).companyId(company.getId());
			logService.saveCompanyError(importError);
			return;
		}
		
		company.setCode(response.getValueReturned());
		
		logService.deleteError(company);
		companyRepository.save(company);
	}

}
