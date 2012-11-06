
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import br.com.kohen.eventmanager.clarion.ws.service.PurchaseImportServiceImpl.InfoExport;
import br.com.kohen.eventmanager.commons.entity.Purchase;
import br.com.kohen.eventmanager.commons.enums.Language;


/**
 * <p>Java class for APEDIDO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APEDIDO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CHAVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODPROTHEUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODPVBOX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CUSTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ITENS" type="{http://189.2.170.19:81/}ARRAYOFDADOSITENS"/>
 *         &lt;element name="MOEDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APEDIDO", propOrder = {
    "chave",
    "codprotheus",
    "codpvbox",
    "custo",
    "itens",
    "moeda",
    "reciss"
})
public class APEDIDO {

    @XmlElement(name = "CHAVE", required = true)
    protected String chave;
    @XmlElement(name = "CODPROTHEUS", required = true)
    protected String codprotheus;
    @XmlElement(name = "CODPVBOX", required = true)
    protected String codpvbox;
    @XmlElement(name = "CUSTO", required = true)
    protected String custo;
    @XmlElement(name = "ITENS", required = true)
    protected ARRAYOFDADOSITENS itens;
    @XmlElement(name = "MOEDA", required = true)
    protected String moeda;
    @XmlElement(name = "RECISS", required = true)
    protected String reciss;
    
    /**
     * Gets the value of the chave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHAVE() {
        return chave;
    }

    /**
     * Sets the value of the chave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHAVE(String value) {
        this.chave = value;
    }

    /**
     * Gets the value of the codprotheus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODPROTHEUS() {
        return codprotheus;
    }

    /**
     * Sets the value of the codprotheus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODPROTHEUS(String value) {
        this.codprotheus = value;
    }

    /**
     * Gets the value of the codpvbox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODPVBOX() {
        return codpvbox;
    }

    /**
     * Sets the value of the codpvbox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODPVBOX(String value) {
        this.codpvbox = value;
    }

    /**
     * Gets the value of the custo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTO() {
        return custo;
    }

    /**
     * Sets the value of the custo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTO(String value) {
        this.custo = value;
    }

    /**
     * Gets the value of the itens property.
     * 
     * @return
     *     possible object is
     *     {@link ARRAYOFDADOSITENS }
     *     
     */
    public ARRAYOFDADOSITENS getITENS() {
        return itens;
    }

    /**
     * Sets the value of the itens property.
     * 
     * @param value
     *     allowed object is
     *     {@link ARRAYOFDADOSITENS }
     *     
     */
    public void setITENS(ARRAYOFDADOSITENS value) {
        this.itens = value;
    }

    /**
     * Gets the value of the moeda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOEDA() {
        return moeda;
    }

    /**
     * Sets the value of the moeda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOEDA(String value) {
        this.moeda = value;
    }

	public String getReciss() {
		return reciss;
	}

	public void setRECISS(String reciss) {
		this.reciss = reciss;
	}

	public static APEDIDO build(InfoExport info, Purchase purchase) {
		APEDIDO pedido = new APEDIDO();
		
		pedido.setCHAVE(info.chave);
		pedido.setCODPVBOX(purchase.getId().toString());
		pedido.setMOEDA(purchase.getLang() == Language.PT ? "1" : "2");
		pedido.setCUSTO(info.custo);
		pedido.setRECISS(info.reciss);
		
		return pedido;
		
	}
    
    
}
