
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
 *         &lt;element name="aplicaCertificadoReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "aplicaCertificadoReturn"
})
@XmlRootElement(name = "aplicaCertificadoResponse")
public class AplicaCertificadoResponse {

    @XmlElement(required = true)
    protected String aplicaCertificadoReturn;

    /**
     * Gets the value of the aplicaCertificadoReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicaCertificadoReturn() {
        return aplicaCertificadoReturn;
    }

    /**
     * Sets the value of the aplicaCertificadoReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicaCertificadoReturn(String value) {
        this.aplicaCertificadoReturn = value;
    }

}
