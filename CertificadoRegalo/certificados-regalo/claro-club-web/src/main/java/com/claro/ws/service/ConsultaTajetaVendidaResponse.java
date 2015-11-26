
package com.claro.ws.service;

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
 *         &lt;element name="consultaTajetaVendidaReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "consultaTajetaVendidaReturn"
})
@XmlRootElement(name = "consultaTajetaVendidaResponse")
public class ConsultaTajetaVendidaResponse {

    @XmlElement(required = true)
    protected String consultaTajetaVendidaReturn;

    /**
     * Gets the value of the consultaTajetaVendidaReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsultaTajetaVendidaReturn() {
        return consultaTajetaVendidaReturn;
    }

    /**
     * Sets the value of the consultaTajetaVendidaReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsultaTajetaVendidaReturn(String value) {
        this.consultaTajetaVendidaReturn = value;
    }

}
