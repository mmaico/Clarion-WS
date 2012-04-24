
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DADOSITENS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DADOSITENS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODIGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QUANTIDADE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TES" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VALOR_UNIT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DADOSITENS", propOrder = {
    "codigo",
    "quantidade",
    "tes",
    "valorunit"
})
public class DADOSITENS {

    @XmlElement(name = "CODIGO", required = true)
    protected String codigo;
    @XmlElement(name = "QUANTIDADE", required = true)
    protected String quantidade;
    @XmlElement(name = "TES", required = true)
    protected String tes;
    @XmlElement(name = "VALOR_UNIT", required = true)
    protected String valorunit;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGO() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGO(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the quantidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUANTIDADE() {
        return quantidade;
    }

    /**
     * Sets the value of the quantidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUANTIDADE(String value) {
        this.quantidade = value;
    }

    /**
     * Gets the value of the tes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTES() {
        return tes;
    }

    /**
     * Sets the value of the tes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTES(String value) {
        this.tes = value;
    }

    /**
     * Gets the value of the valorunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALORUNIT() {
        return valorunit;
    }

    /**
     * Sets the value of the valorunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALORUNIT(String value) {
        this.valorunit = value;
    }

}
