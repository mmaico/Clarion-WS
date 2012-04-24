
package br.com.kohen.eventmanager.clarion.ws.wsdl.purchase;

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
 *         &lt;element name="PEDIDO" type="{http://189.2.170.19:81/}APEDIDO"/>
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
    "pedido"
})
@XmlRootElement(name = "GRAVADADOS")
public class GRAVADADOS {

    @XmlElement(name = "PEDIDO", required = true)
    protected APEDIDO pedido;

    /**
     * Gets the value of the pedido property.
     * 
     * @return
     *     possible object is
     *     {@link APEDIDO }
     *     
     */
    public APEDIDO getPEDIDO() {
        return pedido;
    }

    /**
     * Sets the value of the pedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link APEDIDO }
     *     
     */
    public void setPEDIDO(APEDIDO value) {
        this.pedido = value;
    }

}
