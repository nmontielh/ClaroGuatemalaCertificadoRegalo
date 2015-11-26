
package com.claro.ws.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.claro.exception.ClaroException;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.claro.ws.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Fault_QNAME = new QName("http://service.ws.claro.com", "fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.claro.ws.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaMovimientosTajetaCertificado }
     * 
     */
    public ConsultaMovimientosTajetaCertificado createConsultaMovimientosTajetaCertificado() {
        return new ConsultaMovimientosTajetaCertificado();
    }

    /**
     * Create an instance of {@link ConsultaMovimientosTajetaCertificadoResponse }
     * 
     */
    public ConsultaMovimientosTajetaCertificadoResponse createConsultaMovimientosTajetaCertificadoResponse() {
        return new ConsultaMovimientosTajetaCertificadoResponse();
    }

    /**
     * Create an instance of {@link ConsultaTajetaResponse }
     * 
     */
    public ConsultaTajetaResponse createConsultaTajetaResponse() {
        return new ConsultaTajetaResponse();
    }

    /**
     * Create an instance of {@link ActivaTarjetaCertificado }
     * 
     */
    public ActivaTarjetaCertificado createActivaTarjetaCertificado() {
        return new ActivaTarjetaCertificado();
    }

    /**
     * Create an instance of {@link CancelaAplicaCertificado }
     * 
     */
    public CancelaAplicaCertificado createCancelaAplicaCertificado() {
        return new CancelaAplicaCertificado();
    }

    /**
     * Create an instance of {@link CancelaTarjetaCertificadoResponse }
     * 
     */
    public CancelaTarjetaCertificadoResponse createCancelaTarjetaCertificadoResponse() {
        return new CancelaTarjetaCertificadoResponse();
    }

    /**
     * Create an instance of {@link ActivaTarjetaCertificadoResponse }
     * 
     */
    public ActivaTarjetaCertificadoResponse createActivaTarjetaCertificadoResponse() {
        return new ActivaTarjetaCertificadoResponse();
    }

    /**
     * Create an instance of {@link ConsultaTajetaVendidaResponse }
     * 
     */
    public ConsultaTajetaVendidaResponse createConsultaTajetaVendidaResponse() {
        return new ConsultaTajetaVendidaResponse();
    }

    /**
     * Create an instance of {@link ConsultaSaldo }
     * 
     */
    public ConsultaSaldo createConsultaSaldo() {
        return new ConsultaSaldo();
    }

    /**
     * Create an instance of {@link ConsultaSaldoCertificadoResponse }
     * 
     */
    public ConsultaSaldoCertificadoResponse createConsultaSaldoCertificadoResponse() {
        return new ConsultaSaldoCertificadoResponse();
    }

    /**
     * Create an instance of {@link ConsultaMovimientosCertificadoResponse }
     * 
     */
    public ConsultaMovimientosCertificadoResponse createConsultaMovimientosCertificadoResponse() {
        return new ConsultaMovimientosCertificadoResponse();
    }

    /**
     * Create an instance of {@link ConsultaSaldoCertificado }
     * 
     */
    public ConsultaSaldoCertificado createConsultaSaldoCertificado() {
        return new ConsultaSaldoCertificado();
    }

    /**
     * Create an instance of {@link AplicaCertificadoResponse }
     * 
     */
    public AplicaCertificadoResponse createAplicaCertificadoResponse() {
        return new AplicaCertificadoResponse();
    }

    /**
     * Create an instance of {@link ConsultaMovimientosCertificado }
     * 
     */
    public ConsultaMovimientosCertificado createConsultaMovimientosCertificado() {
        return new ConsultaMovimientosCertificado();
    }

    /**
     * Create an instance of {@link CancelaAplicaCertificadoResponse }
     * 
     */
    public CancelaAplicaCertificadoResponse createCancelaAplicaCertificadoResponse() {
        return new CancelaAplicaCertificadoResponse();
    }

    /**
     * Create an instance of {@link ConsultaTajeta }
     * 
     */
    public ConsultaTajeta createConsultaTajeta() {
        return new ConsultaTajeta();
    }

    /**
     * Create an instance of {@link ConsultaSaldoResponse }
     * 
     */
    public ConsultaSaldoResponse createConsultaSaldoResponse() {
        return new ConsultaSaldoResponse();
    }

    /**
     * Create an instance of {@link CancelaTarjetaCertificado }
     * 
     */
    public CancelaTarjetaCertificado createCancelaTarjetaCertificado() {
        return new CancelaTarjetaCertificado();
    }

    /**
     * Create an instance of {@link ConsultaTajetaVendida }
     * 
     */
    public ConsultaTajetaVendida createConsultaTajetaVendida() {
        return new ConsultaTajetaVendida();
    }

    /**
     * Create an instance of {@link AplicaCertificado }
     * 
     */
    public AplicaCertificado createAplicaCertificado() {
        return new AplicaCertificado();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClaroException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.claro.com", name = "fault")
    public JAXBElement<ClaroException> createFault(ClaroException value) {
        return new JAXBElement<ClaroException>(_Fault_QNAME, ClaroException.class, null, value);
    }

}
