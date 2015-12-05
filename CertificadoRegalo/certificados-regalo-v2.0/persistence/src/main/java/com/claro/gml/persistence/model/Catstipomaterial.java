package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catstipomaterial generated by hbm2java
 */
@Entity
@Table(name = "CATSTIPOMATERIAL", schema = "CERTREG")
public class Catstipomaterial implements java.io.Serializable {

	private CatstipomaterialId id;

	public Catstipomaterial() {
	}

	public Catstipomaterial(CatstipomaterialId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cvetipomaterial", column = @Column(name = "CVETIPOMATERIAL", nullable = false, length = 9) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ) })
	public CatstipomaterialId getId() {
		return this.id;
	}

	public void setId(CatstipomaterialId id) {
		this.id = id;
	}

}
