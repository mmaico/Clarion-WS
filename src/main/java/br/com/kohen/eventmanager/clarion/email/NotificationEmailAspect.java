package br.com.kohen.eventmanager.clarion.email;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotificationEmailAspect {

//	private static final Log LOG = LogFactory.getLog(NotificationEmailAspect.class);
//	
//	private static final Integer ITENS = 0;
//	private static final Integer EXPOSITOR = 3;
//	
//	@Autowired
//	private NotificationEmailSend emailSend;
//	
//	
//	@SuppressWarnings("unchecked")
//	@Around("sendMail()")
//	public Object sendMailAfterPurchase(ProceedingJoinPoint joinPoint) throws Throwable {
//		Object proceed = null;
//		try {	
//			LOG.debug("############### -------> Iniciando o envio de email");
//			proceed = joinPoint.proceed();
//			
//			Object[] args = joinPoint.getArgs();
//			
//			if (args == null || args.length < 1) {
//				LOG.debug("############### -------> O metodo aspectado nao tem parametro----> abortado o processo.");
//			}
//			
//			Company company = (Company) args[EXPOSITOR];
//			
//			Set<ShoppingCartItemVO> itens = ((Set<ShoppingCartItemVO>) args[ITENS]);
//			
//			if (company == null) {
//				LOG.debug("############### -------> O Expositor esta nulo, o processo de envio de email sera abortado!");
//			}
//			
//			emailSend.prepareAndSendEmail(company, itens);
//			
//		}catch(Throwable e) {
//			LOG.error("Houve algum erro ao tentar enviar e-mail.", e);
//		}
//		
//		return proceed;
//		
//	}
//	
//	
//	
//	
//	
//	@Pointcut("execution(* br.com.kohen.eventmanager.onsite.service.impl.OnSitePurchaseServiceImpl.purchaseFinalize(..))")
//	public void sendMail() {}
}
