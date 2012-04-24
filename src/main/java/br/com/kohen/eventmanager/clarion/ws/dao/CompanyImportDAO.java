package br.com.kohen.eventmanager.clarion.ws.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.WSCLSA1;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.WSCLSA1SOAP;

@Component("companyImportDAO")
public class CompanyImportDAO {

	private static Logger logger = Logger.getLogger(CompanyImportDAO.class);
	
	private WSCLSA1 wsclsa1; 
	
	public CompanyImportDAO(){
		
		try {
			wsclsa1 = new WSCLSA1();
		} catch (Exception e) {
			logger.error("Erro ao iniciar o servico do CompanyWS" + e.getMessage());
		}
		
	}
	
	
	public String importCompany(GRAVADADOS gravados) {
			try {
				
				WSCLSA1SOAP client = wsclsa1.getWSCLSA1SOAP();
				String code = client.gravadados(   
						gravados.getCHAVE(), 
						gravados.getCODIGO(), 
						gravados.getNOME(), 
						gravados.getNOMEFANT(), 
						gravados.getENDERECO(), 
						gravados.getNUMERO(), 
						gravados.getCEP(), 
						gravados.getBAIRRO(), 
						gravados.getCIDADE(), 
						gravados.getUF(), 
						gravados.getCOMPLTO(), 
						gravados.getCNPJ(), 
						gravados.getIE(), 
						gravados.getIM(), 
						gravados.getTEL(), 
						gravados.getCONTATO(), 
						gravados.getEMAIL(), 
						gravados.getCARGO(), 
						gravados.getTELCEL(), 
						gravados.getPAIS()
						);
				
				return code;
			} catch(Exception e) {
				logger.error("Erro ao iniciar o servico do CompanyWS" + e.getMessage());
				return e.getMessage();
			}
	}
	
	
}
