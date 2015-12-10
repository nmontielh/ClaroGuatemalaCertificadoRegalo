package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catstatus generated by hbm2java
 */
@Entity
@Table(name = "CATSTATUS")
public class Catstatus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatstatusId id;

	public Catstatus() {
	}

	public Catstatus(CatstatusId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idstatus", column = @Column(name = "IDSTATUS", nullable = false, length = 2) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ) })
	public CatstatusId getId() {
		return this.id;
	}

	public void setId(CatstatusId id) {
		this.id = id;
	}

}
