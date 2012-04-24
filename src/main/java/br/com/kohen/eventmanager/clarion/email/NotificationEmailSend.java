package br.com.kohen.eventmanager.clarion.email;

import org.springframework.stereotype.Component;

@Component
public class NotificationEmailSend {

//	private static final String COMPANY = "company";
//	
//	private static final String TEMPLATE = "email";
//	
//	private static final String CART_ITENS = "cartItens";
//	
//	
//	@Autowired
//	private CommonEmailMounting commonEmailMounting;
//	
//	@Autowired
//	@Qualifier("commonBaseDAO") 
//	private CommonBaseDAO<Email> baseDAO;
//	
//	
//	public void prepareAndSendEmail(Company company, Set<ShoppingCartItemVO> cartItens) {
//		
//		Email email = new Email();
//		email.setSubject(company.getName());
//		
//		String bodyEmail = commonEmailMounting.buildBodyEmail("/email", Language.PT, prepareArguments(company, cartItens));
//		
//		email.setBody(bodyEmail);
//		email.setFromMail(PropertyRead.getProperty("clarion.email.from"));
//		email.setTo(PropertyRead.getProperty("clarion.email.to"));
//		email.setCoreName(PropertyRead.getProperty("clarion.send.email.core"));
//		
//		baseDAO.saveOrUpdate(email);
//		
//	}
//	
//	private Map<String, Object> prepareArguments(Company company,Set<ShoppingCartItemVO> cartItens) {
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		
//		params.put(CART_ITENS, cartItens);
//		params.put(COMPANY, company);
//
//		return params;
//	}
//	
	
	
}
