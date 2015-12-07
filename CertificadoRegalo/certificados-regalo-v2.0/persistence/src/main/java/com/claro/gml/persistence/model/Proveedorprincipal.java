package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Proveedorprincipal generated by hbm2java
 */
@Entity
@Table(name = "PROVEEDORPRINCIPAL", schema = "CERTREG")
public class Proveedorprincipal implements java.io.Serializable {

	private ProveedorprincipalId id;

	public Proveedorprincipal() {
	}

	public Proveedorprincipal(ProveedorprincipalId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idproveedor", column = @Column(name = "IDPROVEEDOR", nullable = false, precision = 3, scale = 0) ),
			@AttributeOverride(name = "cveprincipal", column = @Column(name = "CVEPRINCIPAL", length = 4) ),
			@AttributeOverride(name = "tipo", column = @Column(name = "TIPO", length = 3) ),
			@AttributeOverride(name = "clave", column = @Column(name = "CLAVE", length = 4) ),
			@AttributeOverride(name = "region", column = @Column(name = "REGION", length = 2) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 1, scale = 0) ) })
	public ProveedorprincipalId getId() {
		return this.id;
	}

	public void setId(ProveedorprincipalId id) {
		this.id = id;
	}

}