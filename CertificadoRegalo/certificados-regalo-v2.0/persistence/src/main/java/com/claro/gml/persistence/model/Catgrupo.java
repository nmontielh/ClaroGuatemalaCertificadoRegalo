package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catgrupo generated by hbm2java
 */
@Entity
@Table(name = "CATGRUPO")
public class Catgrupo implements java.io.Serializable {

	private static final long serialVersionUID = 1L; 
	
	private CatgrupoId id;

	public Catgrupo() {
	}

	public Catgrupo(CatgrupoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idgrupo", column = @Column(name = "IDGRUPO", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ),
			@AttributeOverride(name = "permisoNecesario", column = @Column(name = "PERMISO_NECESARIO", precision = 3, scale = 0) ) })
	public CatgrupoId getId() {
		return this.id;
	}

	public void setId(CatgrupoId id) {
		this.id = id;
	}

}
