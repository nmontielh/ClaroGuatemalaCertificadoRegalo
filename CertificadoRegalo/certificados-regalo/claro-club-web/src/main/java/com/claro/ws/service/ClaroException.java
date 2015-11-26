
package com.claro.ws.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "fault", targetNamespace = "http://service.ws.claro.com")
public class ClaroException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private com.claro.exception.ClaroException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ClaroException(String message, com.claro.exception.ClaroException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ClaroException(String message, com.claro.exception.ClaroException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.claro.exception.ClaroException
     */
    public com.claro.exception.ClaroException getFaultInfo() {
        return faultInfo;
    }

}
