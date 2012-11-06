package br.com.kohen.eventmanager.clarion.ws.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kohen.eventmanager.clarion.ws.service.PurchaseImportServiceImpl;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.web.vo.ShoppingCartItemVO;

@SuppressWarnings("unchecked")
@Aspect
@Component
public class ImportPurchaseAspect {

	private static final Log LOG = LogFactory.getLog(ImportPurchaseAspect.class);
	
	private static final Integer ITENS = 0;
	private static final Integer RESPONSIBLE = 3;
	
	
	@Autowired
	private PurchaseImportServiceImpl purchaseImportService;
	
	//@Around("methodImporPurchase()")
	public Object importCompanyToClarionSystem(ProceedingJoinPoint join) throws Throwable {
		String messageReturned = "";
		try {	
			LOG.debug("############### -------> Iniciando o aspecto sobre o salvamento da Compra");
			
			
			Object[] args = join.getArgs();
			
			if (args == null || args.length < 1) {
				LOG.debug("############### -------> O metodo aspectado nao tem parametro----> abortado o processo.");
			}
			
			Company company = (Company) args[RESPONSIBLE];
			
			Set<ShoppingCartItemVO> itens = (Set<ShoppingCartItemVO>) args[ITENS];
			
			if (company == null) {
				LOG.debug("############### -------> A empresa responsavel pelo pagamento esta nula ----> abortado o processo");
			}
			
			messageReturned =  null; //purchaseImportService.importPurchase(new ArrayList<ShoppingCartItemVO>(itens), company);
			
			if (!NumberUtils.isNumber(messageReturned)) {
				LOG.debug("############### -------> Houve erro na importacao e o metodo de servico nao sera chamado " + messageReturned );
				
				return getMapMessages(messageReturned);
			}
			
			company.setClarionId(messageReturned);
			
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
	
	
//	@Pointcut("execution(* br.com.kohen.eventmanager.onsite.service.impl.OnSitePurchaseServiceImpl.purchaseFinalize(..))")
//	public void methodImporPurchase(){}
//	
	
	
	
	
}
