package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Reparacion generated by hbm2java
 */
@Entity
@Table(name = "REPARACION")
public class Reparacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private ReparacionId id;

	public Reparacion() {
	}

	public Reparacion(ReparacionId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "folio", column = @Column(name = "FOLIO", nullable = false, length = 13) ),
			@AttributeOverride(name = "cveproducto", column = @Column(name = "CVEPRODUCTO", nullable = false, precision = 6, scale = 0) ) })
	public ReparacionId getId() {
		return this.id;
	}

	public void setId(ReparacionId id) {
		this.id = id;
	}

}
