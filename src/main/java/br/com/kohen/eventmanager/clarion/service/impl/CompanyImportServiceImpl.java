package br.com.kohen.eventmanager.clarion.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.FieldNamesEnum;
import br.com.kohen.eventmanager.clarion.repository.ClarionCompanyRepository;
import br.com.kohen.eventmanager.clarion.service.CompanyImportService;
import br.com.kohen.eventmanager.clarion.ws.service.CompanyWsService;
import br.com.kohen.eventmanager.clarion.ws.utils.MapUtils;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Event;
import br.com.kohen.eventmanager.commons.repository.CommonEventRepository;

@Component
@Transactional
public class CompanyImportServiceImpl implements CompanyImportService {

	private Log log = LogFactory.getLog(CompanyImportServiceImpl.class);
	
	private static Boolean running = false;
	
	@Autowired
	@Qualifier("commonEventRepository")
	private CommonEventRepository eventRepository;
	
	@Autowired
	private ClarionCompanyRepository companyRepository;
	
	@Autowired
	private CompanyWsService companyWsService;
	
	@Scheduled(fixedDelay=30000)
	public void importCompanySchedule() {
		
		try {
			
			if (running)
				return;
			
			running = true;
			
			Iterable<Event> events = eventRepository.findAll();
			
			for (Event event : events) {
				Map<String, String> settings = event.getSettings();
				
				if (MapUtils.isValid(settings)) {
					log.debug("########################## As configuracoes são invalidas[COMPANY] "+ event.getName() +": " + settings);
				}
				
				String isActive = settings.get(FieldNamesEnum.ATIVE.get());
				
				if (new Boolean(isActive)) {
					log.debug("########################## As importacoes estao desativadas [COMPANY] "+ event.getName() +": " + isActive);
				}
				
				List<Company> list = companyRepository.getAllCompanyNotImported(event);
				
				for (Company company : list) {
					try {
						companyWsService.sendToWs(company);
					}catch(Exception e) {
						log.error("########################## Erro no processo de envio da empresa: " + company.getId() +" Event:"+ event.getName()  +" --" + e.getMessage());
					}
				}
			}
			
			
		} finally {
			running = false;
		}
	}
	
}
