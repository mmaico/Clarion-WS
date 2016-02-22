
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ARRAYOFDADOSITENS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ARRAYOFDADOSITENS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DADOSITENS" type="{http://179.191.78.118:81/}DADOSITENS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ARRAYOFDADOSITENS", propOrder = {
    "dadositens"
})
public class ARRAYOFDADOSITENS {

    @XmlElement(name = "DADOSITENS")
    protected List<DADOSITENS> dadositens;

    /**
     * Gets the value of the dadositens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dadositens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDADOSITENS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DADOSITENS }
     * 
     * 
     */
    public List<DADOSITENS> getDADOSITENS() {
        if (dadositens == null) {
            dadositens = new ArrayList<DADOSITENS>();
        }
        return this.dadositens;
    }

}
