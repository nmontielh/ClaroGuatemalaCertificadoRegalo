
package com.claro.ws.transfer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.claro.transfer.certificados.MovimientoCertificadoTO;


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
 *         &lt;element name="movimientoCertificadoTO" type="{http://certificados.transfer.claro.com}MovimientoCertificadoTO"/>
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
    "movimientoCertificadoTO"
})
@XmlRootElement(name = "aplicaCertificado")
public class AplicaCertificado {

    @XmlElement(required = true)
    protected MovimientoCertificadoTO movimientoCertificadoTO;

    /**
     * Gets the value of the movimientoCertificadoTO property.
     * 
     * @return
     *     possible object is
     *     {@link MovimientoCertificadoTO }
     *     
     */
    public MovimientoCertificadoTO getMovimientoCertificadoTO() {
        return movimientoCertificadoTO;
    }

    /**
     * Sets the value of the movimientoCertificadoTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link MovimientoCertificadoTO }
     *     
     */
    public void setMovimientoCertificadoTO(MovimientoCertificadoTO value) {
        this.movimientoCertificadoTO = value;
    }

}
