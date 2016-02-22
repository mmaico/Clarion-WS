
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ITENS" type="{http://179.191.78.118:81/}ARRAYOFDADOSITENS"/>
 *         &lt;element name="MOEDA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PARCELAS" type="{http://179.191.78.118:81/}ARRAYOFDADOSCR"/>
 *         &lt;element name="RECISS" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "empresa",
    "itens",
    "moeda",
    "parcelas",
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
    @XmlElement(name = "EMPRESA", required = true)
    protected String empresa;
    @XmlElement(name = "ITENS", required = true)
    protected ARRAYOFDADOSITENS itens;
    @XmlElement(name = "MOEDA", required = true)
    protected String moeda;
    @XmlElement(name = "PARCELAS", required = true)
    protected ARRAYOFDADOSCR parcelas;
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

    /**
     * Gets the value of the parcelas property.
     * 
     * @return
     *     possible object is
     *     {@link ARRAYOFDADOSCR }
     *     
     */
    public ARRAYOFDADOSCR getPARCELAS() {
        return parcelas;
    }

    /**
     * Sets the value of the parcelas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ARRAYOFDADOSCR }
     *     
     */
    public void setPARCELAS(ARRAYOFDADOSCR value) {
        this.parcelas = value;
    }

    /**
     * Gets the value of the reciss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRECISS() {
        return reciss;
    }

    /**
     * Sets the value of the reciss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRECISS(String value) {
        this.reciss = value;
    }
    
    public String getEMPRESA() {
    	return this.empresa;
    }

    public void setEMPRESA(String empresa) {
    	this.empresa = empresa;
    }
    
    
    
}
