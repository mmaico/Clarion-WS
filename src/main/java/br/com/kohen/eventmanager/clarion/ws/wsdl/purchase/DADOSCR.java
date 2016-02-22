
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DADOSCR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DADOSCR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DVENCPARC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NVALPARC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DADOSCR", propOrder = {
    "dvencparc",
    "nvalparc"
})
public class DADOSCR {

    @XmlElement(name = "DVENCPARC", required = true)
    protected String dvencparc;
    @XmlElement(name = "NVALPARC", required = true)
    protected String nvalparc;

    /**
     * Gets the value of the dvencparc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDVENCPARC() {
        return dvencparc;
    }

    /**
     * Sets the value of the dvencparc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDVENCPARC(String value) {
        this.dvencparc = value;
    }

    /**
     * Gets the value of the nvalparc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNVALPARC() {
        return nvalparc;
    }

    /**
     * Sets the value of the nvalparc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNVALPARC(String value) {
        this.nvalparc = value;
    }

}
