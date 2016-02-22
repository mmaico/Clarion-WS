
package br.com.kohen.eventmanager.clarion.ws.wsdl.company;

import br.com.kohen.eventmanager.clarion.ws.WSConfig;

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
@WebService(name = "WSCLSA1SOAP", targetNamespace = WSConfig.WS_END_POINT)
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
    @WebMethod(operationName = "GRAVADADOS", action = WSConfig.WS_END_POINT + "GRAVADADOS")
    @WebResult(name = "GRAVADADOSRESULT", targetNamespace = WSConfig.WS_END_POINT)
    @RequestWrapper(localName = "GRAVADADOS", targetNamespace = WSConfig.WS_END_POINT, className = "br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOS")
    @ResponseWrapper(localName = "GRAVADADOSRESPONSE", targetNamespace = WSConfig.WS_END_POINT, className = "br.com.kohen.eventmanager.clarion.ws.wsdl.company.GRAVADADOSRESPONSE")
    public String gravadados(
        @WebParam(name = "CHAVE", targetNamespace = WSConfig.WS_END_POINT)
        String chave,
        @WebParam(name = "CODIGO", targetNamespace = WSConfig.WS_END_POINT)
        String codigo,
        @WebParam(name = "NOME", targetNamespace = WSConfig.WS_END_POINT)
        String nome,
        @WebParam(name = "NOMEFANT", targetNamespace = WSConfig.WS_END_POINT)
        String nomefant,
        @WebParam(name = "ENDERECO", targetNamespace = WSConfig.WS_END_POINT)
        String endereco,
        @WebParam(name = "NUMERO", targetNamespace = WSConfig.WS_END_POINT)
        String numero,
        @WebParam(name = "CEP", targetNamespace = WSConfig.WS_END_POINT)
        String cep,
        @WebParam(name = "BAIRRO", targetNamespace = WSConfig.WS_END_POINT)
        String bairro,
        @WebParam(name = "CIDADE", targetNamespace = WSConfig.WS_END_POINT)
        String cidade,
        @WebParam(name = "UF", targetNamespace = WSConfig.WS_END_POINT)
        String uf,
        @WebParam(name = "COMPLTO", targetNamespace = WSConfig.WS_END_POINT)
        String complto,
        @WebParam(name = "CNPJ", targetNamespace = WSConfig.WS_END_POINT)
        String cnpj,
        @WebParam(name = "IE", targetNamespace = WSConfig.WS_END_POINT)
        String ie,
        @WebParam(name = "IM", targetNamespace = WSConfig.WS_END_POINT)
        String im,
        @WebParam(name = "TEL", targetNamespace = WSConfig.WS_END_POINT)
        String tel,
        @WebParam(name = "CONTATO", targetNamespace = WSConfig.WS_END_POINT)
        String contato,
        @WebParam(name = "EMAIL", targetNamespace = WSConfig.WS_END_POINT)
        String email,
        @WebParam(name = "CARGO", targetNamespace = WSConfig.WS_END_POINT)
        String cargo,
        @WebParam(name = "TELCEL", targetNamespace = WSConfig.WS_END_POINT)
        String telcel,
        @WebParam(name = "PAIS", targetNamespace = WSConfig.WS_END_POINT)
        String pais);

}
