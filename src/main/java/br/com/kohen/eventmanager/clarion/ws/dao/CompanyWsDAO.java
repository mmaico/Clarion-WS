package br.com.kohen.eventmanager.clarion.ws.dao;

import br.com.kohen.eventmanager.clarion.ws.utils.ClarionWsResponse;
import br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS;

public interface CompanyWsDAO {

	ClarionWsResponse send(GRAVADADOS gravados);
}
