
package com.claro.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.claro.transfer.certificados.MovimientoCertificadoTO;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ClaroService", targetNamespace = "http://service.ws.claro.com")
@XmlSeeAlso({
    com.claro.exception.ObjectFactory.class,
    com.claro.transfer.certificados.ObjectFactory.class,
    com.claro.ws.service.ObjectFactory.class
})
public interface ClaroService {


    /**
     * 
     * @param numeroCertificado
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "consultaSaldoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "consultaSaldo", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaSaldo")
    @ResponseWrapper(localName = "consultaSaldoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaSaldoResponse")
    public String consultaSaldo(
        @WebParam(name = "numeroCertificado", targetNamespace = "http://service.ws.claro.com")
        String numeroCertificado)
        throws ClaroException
    ;

    /**
     * 
     * @param montoCertificado
     * @param idUsuario
     * @param numeroTarjeta
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "activaTarjetaCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "activaTarjetaCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ActivaTarjetaCertificado")
    @ResponseWrapper(localName = "activaTarjetaCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ActivaTarjetaCertificadoResponse")
    public String activaTarjetaCertificado(
        @WebParam(name = "numeroTarjeta", targetNamespace = "http://service.ws.claro.com")
        String numeroTarjeta,
        @WebParam(name = "montoCertificado", targetNamespace = "http://service.ws.claro.com")
        long montoCertificado,
        @WebParam(name = "idUsuario", targetNamespace = "http://service.ws.claro.com")
        String idUsuario)
        throws ClaroException
    ;

    /**
     * 
     * @param movimientoCertificadoTO
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "aplicaCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "aplicaCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.AplicaCertificado")
    @ResponseWrapper(localName = "aplicaCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.AplicaCertificadoResponse")
    public String aplicaCertificado(
        @WebParam(name = "movimientoCertificadoTO", targetNamespace = "http://service.ws.claro.com")
        MovimientoCertificadoTO movimientoCertificadoTO)
        throws ClaroException
    ;

    /**
     * 
     * @param idUsuario
     * @param numeroCertificado
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "cancelaTarjetaCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "cancelaTarjetaCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.CancelaTarjetaCertificado")
    @ResponseWrapper(localName = "cancelaTarjetaCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.CancelaTarjetaCertificadoResponse")
    public String cancelaTarjetaCertificado(
        @WebParam(name = "numeroCertificado", targetNamespace = "http://service.ws.claro.com")
        String numeroCertificado,
        @WebParam(name = "idUsuario", targetNamespace = "http://service.ws.claro.com")
        String idUsuario)
        throws ClaroException
    ;

    /**
     * 
     * @param idUsuario
     * @param folio
     * @param idpuntoVta
     * @param referencia
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "cancelaAplicaCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "cancelaAplicaCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.CancelaAplicaCertificado")
    @ResponseWrapper(localName = "cancelaAplicaCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.CancelaAplicaCertificadoResponse")
    public String cancelaAplicaCertificado(
        @WebParam(name = "folio", targetNamespace = "http://service.ws.claro.com")
        String folio,
        @WebParam(name = "idUsuario", targetNamespace = "http://service.ws.claro.com")
        String idUsuario,
        @WebParam(name = "idpuntoVta", targetNamespace = "http://service.ws.claro.com")
        String idpuntoVta,
        @WebParam(name = "referencia", targetNamespace = "http://service.ws.claro.com")
        String referencia)
        throws ClaroException
    ;

    /**
     * 
     * @param numeroCertificado
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "consultaMovimientosTajetaCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "consultaMovimientosTajetaCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaMovimientosTajetaCertificado")
    @ResponseWrapper(localName = "consultaMovimientosTajetaCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaMovimientosTajetaCertificadoResponse")
    public String consultaMovimientosTajetaCertificado(
        @WebParam(name = "numeroCertificado", targetNamespace = "http://service.ws.claro.com")
        String numeroCertificado)
        throws ClaroException
    ;

    /**
     * 
     * @param montoCertificado
     * @param numeroTarjeta
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "consultaTajetaReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "consultaTajeta", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaTajeta")
    @ResponseWrapper(localName = "consultaTajetaResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaTajetaResponse")
    public String consultaTajeta(
        @WebParam(name = "numeroTarjeta", targetNamespace = "http://service.ws.claro.com")
        String numeroTarjeta,
        @WebParam(name = "montoCertificado", targetNamespace = "http://service.ws.claro.com")
        long montoCertificado)
        throws ClaroException
    ;

    /**
     * 
     * @param numeroTarjeta
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "consultaSaldoCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "consultaSaldoCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaSaldoCertificado")
    @ResponseWrapper(localName = "consultaSaldoCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaSaldoCertificadoResponse")
    public String consultaSaldoCertificado(
        @WebParam(name = "numeroTarjeta", targetNamespace = "http://service.ws.claro.com")
        String numeroTarjeta)
        throws ClaroException
    ;

    /**
     * 
     * @param numeroTarjeta
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "consultaMovimientosCertificadoReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "consultaMovimientosCertificado", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaMovimientosCertificado")
    @ResponseWrapper(localName = "consultaMovimientosCertificadoResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaMovimientosCertificadoResponse")
    public String consultaMovimientosCertificado(
        @WebParam(name = "numeroTarjeta", targetNamespace = "http://service.ws.claro.com")
        String numeroTarjeta)
        throws ClaroException
    ;

    /**
     * 
     * @param montoCertificado
     * @param numeroTarjeta
     * @return
     *     returns java.lang.String
     * @throws ClaroException
     */
    @WebMethod
    @WebResult(name = "consultaTajetaVendidaReturn", targetNamespace = "http://service.ws.claro.com")
    @RequestWrapper(localName = "consultaTajetaVendida", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaTajetaVendida")
    @ResponseWrapper(localName = "consultaTajetaVendidaResponse", targetNamespace = "http://service.ws.claro.com", className = "com.claro.ws.service.ConsultaTajetaVendidaResponse")
    public String consultaTajetaVendida(
        @WebParam(name = "numeroTarjeta", targetNamespace = "http://service.ws.claro.com")
        String numeroTarjeta,
        @WebParam(name = "montoCertificado", targetNamespace = "http://service.ws.claro.com")
        long montoCertificado)
        throws ClaroException
    ;

}
