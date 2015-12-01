
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
 *         &lt;element name="activaTarjetaCertificadoReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "activaTarjetaCertificadoReturn"
})
@XmlRootElement(name = "activaTarjetaCertificadoResponse")
public class ActivaTarjetaCertificadoResponse {

    @XmlElement(required = true)
    protected String activaTarjetaCertificadoReturn;

    /**
     * Gets the value of the activaTarjetaCertificadoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivaTarjetaCertificadoReturn() {
        return activaTarjetaCertificadoReturn;
    }

    /**
     * Sets the value of the activaTarjetaCertificadoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivaTarjetaCertificadoReturn(String value) {
        this.activaTarjetaCertificadoReturn = value;
    }

}
