
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
 *         &lt;element name="SALDOTITULORESULT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "saldotituloresult"
})
@XmlRootElement(name = "SALDOTITULORESPONSE")
public class SALDOTITULORESPONSE {

    @XmlElement(name = "SALDOTITULORESULT", required = true)
    protected String saldotituloresult;

    /**
     * Gets the value of the saldotituloresult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALDOTITULORESULT() {
        return saldotituloresult;
    }

    /**
     * Sets the value of the saldotituloresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALDOTITULORESULT(String value) {
        this.saldotituloresult = value;
    }

}
