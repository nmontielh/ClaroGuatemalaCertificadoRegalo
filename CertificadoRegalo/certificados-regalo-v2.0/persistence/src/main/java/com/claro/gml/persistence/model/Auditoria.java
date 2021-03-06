package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Auditoria generated by hbm2java
 */
@Entity
@Table(name = "AUDITORIA")
public class Auditoria implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private AuditoriaId id;

	public Auditoria() {
	}

	public Auditoria(AuditoriaId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idMovimiento", column = @Column(name = "ID_MOVIMIENTO", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "idusuario", column = @Column(name = "IDUSUARIO", precision = 6, scale = 0) ),
			@AttributeOverride(name = "idtipomovimiento", column = @Column(name = "IDTIPOMOVIMIENTO", precision = 6, scale = 0) ),
			@AttributeOverride(name = "fechainicio", column = @Column(name = "FECHAINICIO", length = 7) ),
			@AttributeOverride(name = "ipaddress", column = @Column(name = "IPADDRESS", length = 15) ),
			@AttributeOverride(name = "referencia", column = @Column(name = "REFERENCIA", length = 30) ),
			@AttributeOverride(name = "fechafin", column = @Column(name = "FECHAFIN", length = 7) ) })
	public AuditoriaId getId() {
		return this.id;
	}

	public void setId(AuditoriaId id) {
		this.id = id;
	}

}
