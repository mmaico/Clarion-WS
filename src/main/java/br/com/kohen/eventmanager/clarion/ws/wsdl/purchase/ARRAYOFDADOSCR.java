
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ARRAYOFDADOSCR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ARRAYOFDADOSCR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DADOSCR" type="{http://189.2.170.19:81/}DADOSCR" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ARRAYOFDADOSCR", propOrder = {
    "dadoscr"
})
public class ARRAYOFDADOSCR {

    @XmlElement(name = "DADOSCR")
    protected List<DADOSCR> dadoscr;

    /**
     * Gets the value of the dadoscr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dadoscr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDADOSCR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DADOSCR }
     * 
     * 
     */
    public List<DADOSCR> getDADOSCR() {
        if (dadoscr == null) {
            dadoscr = new ArrayList<DADOSCR>();
        }
        return this.dadoscr;
    }

}
