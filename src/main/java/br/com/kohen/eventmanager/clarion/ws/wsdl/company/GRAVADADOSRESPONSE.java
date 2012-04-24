
package br.com.kohen.eventmanager.clarion.ws.wsdl.company;

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
 *         &lt;element name="GRAVADADOSRESULT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "gravadadosresult"
})
@XmlRootElement(name = "GRAVADADOSRESPONSE")
public class GRAVADADOSRESPONSE {

    @XmlElement(name = "GRAVADADOSRESULT", required = true)
    protected String gravadadosresult;

    /**
     * Gets the value of the gravadadosresult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRAVADADOSRESULT() {
        return gravadadosresult;
    }

    /**
     * Sets the value of the gravadadosresult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRAVADADOSRESULT(String value) {
        this.gravadadosresult = value;
    }

}
