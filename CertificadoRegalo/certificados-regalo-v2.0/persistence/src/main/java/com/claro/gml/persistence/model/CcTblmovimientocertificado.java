package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CcTblmovimientocertificado generated by hbm2java
 */
@Entity
@Table(name = "CC_TBLMOVIMIENTOCERTIFICADO", schema = "CERTREG")
public class CcTblmovimientocertificado implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
	private CcTblmovimientocertificadoId id;
	private String idmovto;
	private String idusuario;
	private String estatus;
	private BigDecimal valoraplicado;
	private BigDecimal valoranterior;
	private BigDecimal valorrestante;
	private String referencia;
		
	public CcTblmovimientocertificado() {
	}

	public CcTblmovimientocertificado(CcTblmovimientocertificadoId id, String idmovto, String idusuario, String estatus,
			BigDecimal valoraplicado, BigDecimal valoranterior, BigDecimal valorrestante, String referencia) {
		this.id = id;
		this.idmovto = idmovto;
		this.idusuario = idusuario;
		this.estatus = estatus;
		this.valoraplicado = valoraplicado;
		this.valoranterior = valoranterior;
		this.valorrestante = valorrestante;
		this.referencia = referencia;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "folio", column = @Column(name = "FOLIO", nullable = false, length = 20) ),
			@AttributeOverride(name = "numcertificado", column = @Column(name = "NUMCERTIFICADO", nullable = false, length = 16) ),
			@AttributeOverride(name = "numtarjeta", column = @Column(name = "NUMTARJETA", nullable = false, length = 16) ),
			@AttributeOverride(name = "fechaoper", column = @Column(name = "FECHAOPER", nullable = false) ),
			@AttributeOverride(name = "puntovta", column = @Column(name = "PUNTOVTA", nullable = false, length = 24) ) })
	public CcTblmovimientocertificadoId getId() {
		return this.id;
	}

	public void setId(CcTblmovimientocertificadoId id) {
		this.id = id;
	}

	@Column(name = "IDMOVTO", nullable = false, length = 1)
	public String getIdmovto() {
		return this.idmovto;
	}

	public void setIdmovto(String idmovto) {
		this.idmovto = idmovto;
	}

	@Column(name = "IDUSUARIO", nullable = false, length = 10)
	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	@Column(name = "ESTATUS", nullable = false, length = 1)
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Column(name = "VALORAPLICADO", nullable = false, precision = 12)
	public BigDecimal getValoraplicado() {
		return this.valoraplicado;
	}

	public void setValoraplicado(BigDecimal valoraplicado) {
		this.valoraplicado = valoraplicado;
	}

	@Column(name = "VALORANTERIOR", nullable = false, precision = 12)
	public BigDecimal getValoranterior() {
		return this.valoranterior;
	}

	public void setValoranterior(BigDecimal valoranterior) {
		this.valoranterior = valoranterior;
	}

	@Column(name = "VALORRESTANTE", nullable = false, precision = 12)
	public BigDecimal getValorrestante() {
		return this.valorrestante;
	}

	public void setValorrestante(BigDecimal valorrestante) {
		this.valorrestante = valorrestante;
	}

	@Column(name = "REFERENCIA", nullable = false, length = 300)
	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
