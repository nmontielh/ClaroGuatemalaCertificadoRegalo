package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catestatusprestamo generated by hbm2java
 */
@Entity
@Table(name = "CATESTATUSPRESTAMO", schema = "CERTREG")
public class Catestatusprestamo implements java.io.Serializable {

	private CatestatusprestamoId id;

	public Catestatusprestamo() {
	}

	public Catestatusprestamo(CatestatusprestamoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idestatusprestamo", column = @Column(name = "IDESTATUSPRESTAMO", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 60) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 2, scale = 0) ) })
	public CatestatusprestamoId getId() {
		return this.id;
	}

	public void setId(CatestatusprestamoId id) {
		this.id = id;
	}

}