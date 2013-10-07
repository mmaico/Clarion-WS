
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
 *         &lt;element name="CHAVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODIGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NOME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NOMEFANT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ENDERECO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMERO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CEP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BAIRRO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CIDADE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UF" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COMPLTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CNPJ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CONTATO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CARGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TELCEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PAIS" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "codigo",
    "nome",
    "nomefant",
    "endereco",
    "numero",
    "cep",
    "bairro",
    "cidade",
    "uf",
    "complto",
    "cnpj",
    "ie",
    "im",
    "tel",
    "contato",
    "email",
    "cargo",
    "telcel",
    "pais"
})
@XmlRootElement(name = "GRAVADADOS")
public class GRAVADADOS {

    @XmlElement(name = "CHAVE", required = true)
    protected String chave;
    @XmlElement(name = "CODIGO", required = true)
    protected String codigo;
    @XmlElement(name = "NOME", required = true)
    protected String nome;
    @XmlElement(name = "NOMEFANT", required = true)
    protected String nomefant;
    @XmlElement(name = "ENDERECO", required = true)
    protected String endereco;
    @XmlElement(name = "NUMERO", required = true)
    protected String numero;
    @XmlElement(name = "CEP", required = true)
    protected String cep;
    @XmlElement(name = "BAIRRO", required = true)
    protected String bairro;
    @XmlElement(name = "CIDADE", required = true)
    protected String cidade;
    @XmlElement(name = "UF", required = true)
    protected String uf;
    @XmlElement(name = "COMPLTO", required = true)
    protected String complto;
    @XmlElement(name = "CNPJ", required = true)
    protected String cnpj;
    @XmlElement(name = "IE", required = true)
    protected String ie;
    @XmlElement(name = "IM", required = true)
    protected String im;
    @XmlElement(name = "TEL", required = true)
    protected String tel;
    @XmlElement(name = "CONTATO", required = true)
    protected String contato;
    @XmlElement(name = "EMAIL", required = true)
    protected String email;
    @XmlElement(name = "CARGO", required = true)
    protected String cargo;
    @XmlElement(name = "TELCEL", required = true)
    protected String telcel;
    @XmlElement(name = "PAIS", required = true)
    protected String pais;

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
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOME() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOME(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the nomefant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMEFANT() {
        return nomefant;
    }

    /**
     * Sets the value of the nomefant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMEFANT(String value) {
        this.nomefant = value;
    }

    /**
     * Gets the value of the endereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENDERECO() {
        return endereco;
    }

    /**
     * Sets the value of the endereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENDERECO(String value) {
        this.endereco = value;
    }

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMERO() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMERO(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the cep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCEP() {
        return cep;
    }

    /**
     * Sets the value of the cep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCEP(String value) {
        this.cep = value;
    }

    /**
     * Gets the value of the bairro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBAIRRO() {
        return bairro;
    }

    /**
     * Sets the value of the bairro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBAIRRO(String value) {
        this.bairro = value;
    }

    /**
     * Gets the value of the cidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIDADE() {
        return cidade;
    }

    /**
     * Sets the value of the cidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIDADE(String value) {
        this.cidade = value;
    }

    /**
     * Gets the value of the uf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUF() {
        return uf;
    }

    /**
     * Sets the value of the uf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUF(String value) {
        this.uf = value;
    }

    /**
     * Gets the value of the complto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMPLTO() {
        return complto;
    }

    /**
     * Sets the value of the complto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPLTO(String value) {
        this.complto = value;
    }

    /**
     * Gets the value of the cnpj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNPJ() {
        return cnpj;
    }

    /**
     * Sets the value of the cnpj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNPJ(String value) {
        this.cnpj = value;
    }

    /**
     * Gets the value of the ie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIE() {
        return ie;
    }

    /**
     * Sets the value of the ie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIE(String value) {
        this.ie = value;
    }

    /**
     * Gets the value of the im property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIM() {
        return im;
    }

    /**
     * Sets the value of the im property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIM(String value) {
        this.im = value;
    }

    /**
     * Gets the value of the tel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEL() {
        return tel;
    }

    /**
     * Sets the value of the tel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEL(String value) {
        this.tel = value;
    }

    /**
     * Gets the value of the contato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTATO() {
        return contato;
    }

    /**
     * Sets the value of the contato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTATO(String value) {
        this.contato = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMAIL() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMAIL(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the cargo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCARGO() {
        return cargo;
    }

    /**
     * Sets the value of the cargo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCARGO(String value) {
        this.cargo = value;
    }

    /**
     * Gets the value of the telcel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTELCEL() {
        return telcel;
    }

    /**
     * Sets the value of the telcel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTELCEL(String value) {
        this.telcel = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAIS() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAIS(String value) {
        this.pais = value;
    }

    
}
