
package com.claro.ws.transfer;

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
 *         &lt;element name="consultaMovimientosCertificadoReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "consultaMovimientosCertificadoReturn"
})
@XmlRootElement(name = "consultaMovimientosCertificadoResponse")
public class ConsultaMovimientosCertificadoResponse {

    @XmlElement(required = true)
    protected String consultaMovimientosCertificadoReturn;

    /**
     * Gets the value of the consultaMovimientosCertificadoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsultaMovimientosCertificadoReturn() {
        return consultaMovimientosCertificadoReturn;
    }

    /**
     * Sets the value of the consultaMovimientosCertificadoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsultaMovimientosCertificadoReturn(String value) {
        this.consultaMovimientosCertificadoReturn = value;
    }

}
