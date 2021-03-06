
package br.com.kohen.eventmanager.clarion.ws.wsdl.company;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "WSCLSA1SOAP", targetNamespace = "http://189.2.170.19:81/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSCLSA1SOAP {


    /**
     * 
     * @param contato
     * @param complto
     * @param pais
     * @param uf
     * @param tel
     * @param chave
     * @param cnpj
     * @param telcel
     * @param im
     * @param cargo
     * @param numero
     * @param codigo
     * @param bairro
     * @param cidade
     * @param cep
     * @param email
     * @param ie
     * @param nome
     * @param nomefant
     * @param endereco
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GRAVADADOS", action = "http://189.2.170.19:81/GRAVADADOS")
    @WebResult(name = "GRAVADADOSRESULT", targetNamespace = "http://189.2.170.19:81/")
    @RequestWrapper(localName = "GRAVADADOS", targetNamespace = "http://189.2.170.19:81/", className = "br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS")
    @ResponseWrapper(localName = "GRAVADADOSRESPONSE", targetNamespace = "http://189.2.170.19:81/", className = "br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOSRESPONSE")
    public String gravadados(
        @WebParam(name = "CHAVE", targetNamespace = "http://189.2.170.19:81/")
        String chave,
        @WebParam(name = "CODIGO", targetNamespace = "http://189.2.170.19:81/")
        String codigo,
        @WebParam(name = "NOME", targetNamespace = "http://189.2.170.19:81/")
        String nome,
        @WebParam(name = "NOMEFANT", targetNamespace = "http://189.2.170.19:81/")
        String nomefant,
        @WebParam(name = "ENDERECO", targetNamespace = "http://189.2.170.19:81/")
        String endereco,
        @WebParam(name = "NUMERO", targetNamespace = "http://189.2.170.19:81/")
        String numero,
        @WebParam(name = "CEP", targetNamespace = "http://189.2.170.19:81/")
        String cep,
        @WebParam(name = "BAIRRO", targetNamespace = "http://189.2.170.19:81/")
        String bairro,
        @WebParam(name = "CIDADE", targetNamespace = "http://189.2.170.19:81/")
        String cidade,
        @WebParam(name = "UF", targetNamespace = "http://189.2.170.19:81/")
        String uf,
        @WebParam(name = "COMPLTO", targetNamespace = "http://189.2.170.19:81/")
        String complto,
        @WebParam(name = "CNPJ", targetNamespace = "http://189.2.170.19:81/")
        String cnpj,
        @WebParam(name = "IE", targetNamespace = "http://189.2.170.19:81/")
        String ie,
        @WebParam(name = "IM", targetNamespace = "http://189.2.170.19:81/")
        String im,
        @WebParam(name = "TEL", targetNamespace = "http://189.2.170.19:81/")
        String tel,
        @WebParam(name = "CONTATO", targetNamespace = "http://189.2.170.19:81/")
        String contato,
        @WebParam(name = "EMAIL", targetNamespace = "http://189.2.170.19:81/")
        String email,
        @WebParam(name = "CARGO", targetNamespace = "http://189.2.170.19:81/")
        String cargo,
        @WebParam(name = "TELCEL", targetNamespace = "http://189.2.170.19:81/")
        String telcel,
        @WebParam(name = "PAIS", targetNamespace = "http://189.2.170.19:81/")
        String pais);

}
