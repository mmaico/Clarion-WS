package br.com.kohen.eventmanager.clarion.ws.service;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kohen.eventmanager.clarion.ws.dao.PurchaseImportDAO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.APEDIDO;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.ARRAYOFDADOSITENS;
import br.com.kohen.eventmanager.clarion.ws.wsdl.purchase.DADOSITENS;
import br.com.kohen.eventmanager.commons.config.PropertiesAcessor;
import br.com.kohen.eventmanager.commons.dao.CommonBaseDAO;
import br.com.kohen.eventmanager.commons.entity.Company;
import br.com.kohen.eventmanager.commons.entity.Product;
import br.com.kohen.eventmanager.commons.enums.Language;
import br.com.kohen.eventmanager.commons.service.impl.ServiceLocator;
import br.com.kohen.eventmanager.commons.web.vo.ShoppingCartItemVO;

@Component
@Transactional
public class PurchaseImportServiceImpl {

	private static final Log LOG = LogFactory.getLog(PurchaseImportServiceImpl.class);
	
	@Autowired(required=false)
	private PurchaseImportDAO purchaseImportDAO;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String importPurchase(List<ShoppingCartItemVO> itens, Company responsible) {
		LOG.debug("############### ------------ >> Iniciando processo de importacao de produtos");
		
		Boolean enabled = new PropertiesAcessor().loadSystemProperty().get("import.clarion.enabled", Boolean.class);
		if (!enabled) {
			LOG.debug("################## ---------> Sistema de exportacao de empresas esta desativado" );
			return "Processo de Importacao nao esta habilitado";
		}	
		
		CommonBaseDAO baseDAO = (CommonBaseDAO)ServiceLocator.getBean("commonBaseDAO");
		
		
		APEDIDO pedido = new APEDIDO();
		pedido.setCHAVE("ABC1000");
		pedido.setCODPVBOX("33");
		pedido.setMOEDA("1");
		//pedido.setCUSTO("2312");
		//pedido.setRECISS("2");
		pedido.setRECISS("1");
		pedido.setCUSTO("0812");
			
		Company companyResponsible = (Company) baseDAO.findById(Company.class, responsible.getId());
			
		if (companyResponsible.getCode() == null) {
			LOG.debug("O responsavel pelo pagamento ainda nao tem o codigo protheus, nome: " + responsible.getName() + "-- codigo bluebox:" + responsible.getId());
				
			return "O responsavel pelo pagamento ainda nao tem o codigo protheus, nome: " + responsible.getName() + "-- codigo bluebox:" + responsible.getId();
		}
			
		LOG.debug("Empresa responvel pelo pagamento tem o codigo protheus: " + companyResponsible.getCode());
		pedido.setCODPROTHEUS(companyResponsible.getCode());
			
		ARRAYOFDADOSITENS arrayDados = new ARRAYOFDADOSITENS();
			
		for (ShoppingCartItemVO cartItem : itens) {
			DADOSITENS dadosItens = new DADOSITENS();
				
			Product productLoaded = (Product) baseDAO.findById(Product.class, cartItem.getIdProduct());
				
			if (productLoaded.getExternalCode() == null) {
				LOG.debug("Existe produto na venda sem o codigo protheus, nome:" + productLoaded.getName().get(Language.PT) + "-- codigo bluebox:" + productLoaded.getId());
					
				return "Existe produto na venda sem o codigo protheus, nome:" + productLoaded.getName().get(Language.PT) + "-- codigo bluebox:" + productLoaded.getId();
					
			}
				
			dadosItens.setCODIGO(productLoaded.getExternalCode().toString());
			dadosItens.setQUANTIDADE(cartItem.getQtd().toString());
			dadosItens.setVALORUNIT(productLoaded.getRealPrice().toString());
			//dadosItens.setTES("503");
			dadosItens.setTES("501");
			
			arrayDados.getDADOSITENS().add(dadosItens);
		}	
				
			
		pedido.setITENS(arrayDados);
		
		return callExternalServiceAndWaitReturn(pedido);
		
	}
	
	
	private String callExternalServiceAndWaitReturn(APEDIDO pedido) {
		
		String importPurchase = purchaseImportDAO.importPurchase(pedido);
		
		if (!NumberUtils.isNumber(importPurchase)) {
			LOG.debug("Erro ao importar o pedido : " + importPurchase);
			
			return "Erro na importacao do pedido:" + importPurchase;
		}	
					
		return 	importPurchase;	
		
	}
	
	
	
}
