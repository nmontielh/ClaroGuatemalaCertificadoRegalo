package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Estatuspermiso generated by hbm2java
 */
@Entity
@Table(name = "ESTATUSPERMISO", schema = "CERTREG")
public class Estatuspermiso implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private EstatuspermisoId id;

	public Estatuspermiso() {
	}

	public Estatuspermiso(EstatuspermisoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idestatus", column = @Column(name = "IDESTATUS", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "idpermiso", column = @Column(name = "IDPERMISO", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 1, scale = 0) ) })
	public EstatuspermisoId getId() {
		return this.id;
	}

	public void setId(EstatuspermisoId id) {
		this.id = id;
	}

}
