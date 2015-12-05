package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CcTblcertificado generated by hbm2java
 */
@Entity
@Table(name = "CC_TBLCERTIFICADO", schema = "CERTREG")
public class CcTblcertificado implements java.io.Serializable {

	private String numcertificado;
	private String numtarjeta;
	private BigDecimal valor;
	private String aplicado;
	private String idusuario;
	private Serializable fechacarga;
	private String region;

	public CcTblcertificado() {
	}

	public CcTblcertificado(String numcertificado, String numtarjeta, BigDecimal valor, String aplicado,
			String idusuario, Serializable fechacarga) {
		this.numcertificado = numcertificado;
		this.numtarjeta = numtarjeta;
		this.valor = valor;
		this.aplicado = aplicado;
		this.idusuario = idusuario;
		this.fechacarga = fechacarga;
	}

	public CcTblcertificado(String numcertificado, String numtarjeta, BigDecimal valor, String aplicado,
			String idusuario, Serializable fechacarga, String region) {
		this.numcertificado = numcertificado;
		this.numtarjeta = numtarjeta;
		this.valor = valor;
		this.aplicado = aplicado;
		this.idusuario = idusuario;
		this.fechacarga = fechacarga;
		this.region = region;
	}

	@Id

	@Column(name = "NUMCERTIFICADO", unique = true, nullable = false, length = 16)
	public String getNumcertificado() {
		return this.numcertificado;
	}

	public void setNumcertificado(String numcertificado) {
		this.numcertificado = numcertificado;
	}

	@Column(name = "NUMTARJETA", nullable = false, length = 16)
	public String getNumtarjeta() {
		return this.numtarjeta;
	}

	public void setNumtarjeta(String numtarjeta) {
		this.numtarjeta = numtarjeta;
	}

	@Column(name = "VALOR", nullable = false, precision = 12)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column(name = "APLICADO", nullable = false, length = 1)
	public String getAplicado() {
		return this.aplicado;
	}

	public void setAplicado(String aplicado) {
		this.aplicado = aplicado;
	}

	@Column(name = "IDUSUARIO", nullable = false, length = 10)
	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	@Column(name = "FECHACARGA", nullable = false)
	public Serializable getFechacarga() {
		return this.fechacarga;
	}

	public void setFechacarga(Serializable fechacarga) {
		this.fechacarga = fechacarga;
	}

	@Column(name = "REGION", length = 4)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
