package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catsestado generated by hbm2java
 */
@Entity
@Table(name = "CATSESTADO")
public class Catsestado implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatsestadoId id;

	public Catsestado() {
	}

	public Catsestado(CatsestadoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cveestado", column = @Column(name = "CVEESTADO", nullable = false, length = 3) ),
			@AttributeOverride(name = "cvepais", column = @Column(name = "CVEPAIS", nullable = false, length = 3) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ),
			@AttributeOverride(name = "cveestadom2k", column = @Column(name = "CVEESTADOM2K", length = 3) ) })
	public CatsestadoId getId() {
		return this.id;
	}

	public void setId(CatsestadoId id) {
		this.id = id;
	}

}
