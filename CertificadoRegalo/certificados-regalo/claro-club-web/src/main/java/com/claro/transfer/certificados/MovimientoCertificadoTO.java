
package com.claro.transfer.certificados;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MovimientoCertificadoTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MovimientoCertificadoTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMotivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puntoVenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valorAnterior" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="valorAplicado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="valorRestante" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovimientoCertificadoTO", propOrder = {
    "estatus",
    "fechaOperacion",
    "idMensaje",
    "idMotivo",
    "idUsuario",
    "mensaje",
    "numeroCertificado",
    "numeroTarjeta",
    "puntoVenta",
    "referencia",
    "valorAnterior",
    "valorAplicado",
    "valorRestante"
})
public class MovimientoCertificadoTO {

    @XmlElement(required = true, nillable = true)
    protected String estatus;
    @XmlElement(required = true, nillable = true)
    protected String fechaOperacion;
    @XmlElement(required = true, nillable = true)
    protected String idMensaje;
    @XmlElement(required = true, nillable = true)
    protected String idMotivo;
    @XmlElement(required = true, nillable = true)
    protected String idUsuario;
    @XmlElement(required = true, nillable = true)
    protected String mensaje;
    @XmlElement(required = true, nillable = true)
    protected String numeroCertificado;
    @XmlElement(required = true, nillable = true)
    protected String numeroTarjeta;
    @XmlElement(required = true, nillable = true)
    protected String puntoVenta;
    @XmlElement(required = true, nillable = true)
    protected String referencia;
    protected float valorAnterior;
    protected float valorAplicado;
    protected float valorRestante;

    /**
     * Gets the value of the estatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Sets the value of the estatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstatus(String value) {
        this.estatus = value;
    }

    /**
     * Gets the value of the fechaOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaOperacion() {
        return fechaOperacion;
    }

    /**
     * Sets the value of the fechaOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaOperacion(String value) {
        this.fechaOperacion = value;
    }

    /**
     * Gets the value of the idMensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMensaje() {
        return idMensaje;
    }

    /**
     * Sets the value of the idMensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMensaje(String value) {
        this.idMensaje = value;
    }

    /**
     * Gets the value of the idMotivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMotivo() {
        return idMotivo;
    }

    /**
     * Sets the value of the idMotivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMotivo(String value) {
        this.idMotivo = value;
    }

    /**
     * Gets the value of the idUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets the value of the idUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUsuario(String value) {
        this.idUsuario = value;
    }

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the numeroCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCertificado() {
        return numeroCertificado;
    }

    /**
     * Sets the value of the numeroCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCertificado(String value) {
        this.numeroCertificado = value;
    }

    /**
     * Gets the value of the numeroTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Sets the value of the numeroTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTarjeta(String value) {
        this.numeroTarjeta = value;
    }

    /**
     * Gets the value of the puntoVenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuntoVenta() {
        return puntoVenta;
    }

    /**
     * Sets the value of the puntoVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuntoVenta(String value) {
        this.puntoVenta = value;
    }

    /**
     * Gets the value of the referencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Sets the value of the referencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

    /**
     * Gets the value of the valorAnterior property.
     * 
     */
    public float getValorAnterior() {
        return valorAnterior;
    }

    /**
     * Sets the value of the valorAnterior property.
     * 
     */
    public void setValorAnterior(float value) {
        this.valorAnterior = value;
    }

    /**
     * Gets the value of the valorAplicado property.
     * 
     */
    public float getValorAplicado() {
        return valorAplicado;
    }

    /**
     * Sets the value of the valorAplicado property.
     * 
     */
    public void setValorAplicado(float value) {
        this.valorAplicado = value;
    }

    /**
     * Gets the value of the valorRestante property.
     * 
     */
    public float getValorRestante() {
        return valorRestante;
    }

    /**
     * Sets the value of the valorRestante property.
     * 
     */
    public void setValorRestante(float value) {
        this.valorRestante = value;
    }

}
