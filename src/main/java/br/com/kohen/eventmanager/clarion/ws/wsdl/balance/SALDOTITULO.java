
package br.com.kohen.eventmanager.clarion.ws.wsdl.balance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CHAVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMPRESA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PEDIDO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PARCELA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chave",
    "empresa",
    "pedido",
    "parcela"
})
@XmlRootElement(name = "SALDOTITULO")
public class SALDOTITULO {

    @XmlElement(name = "CHAVE", required = true)
    protected String chave;
    @XmlElement(name = "EMPRESA", required = true)
    protected String empresa;
    @XmlElement(name = "PEDIDO", required = true)
    protected String pedido;
    @XmlElement(name = "PARCELA", required = true)
    protected String parcela;

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
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMPRESA() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMPRESA(String value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the pedido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPEDIDO() {
        return pedido;
    }

    /**
     * Sets the value of the pedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPEDIDO(String value) {
        this.pedido = value;
    }

    /**
     * Gets the value of the parcela property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARCELA() {
        return parcela;
    }

    /**
     * Sets the value of the parcela property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARCELA(String value) {
        this.parcela = value;
    }

}
