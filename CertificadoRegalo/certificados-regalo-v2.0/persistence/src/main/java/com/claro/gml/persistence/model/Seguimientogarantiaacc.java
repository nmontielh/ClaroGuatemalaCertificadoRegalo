package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Seguimientogarantiaacc generated by hbm2java
 */
@Entity
@Table(name = "SEGUIMIENTOGARANTIAACC", schema = "CERTREG")
public class Seguimientogarantiaacc implements java.io.Serializable {

	private SeguimientogarantiaaccId id;

	public Seguimientogarantiaacc() {
	}

	public Seguimientogarantiaacc(SeguimientogarantiaaccId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "foliogenerico", column = @Column(name = "FOLIOGENERICO", nullable = false, length = 17) ),
			@AttributeOverride(name = "material", column = @Column(name = "MATERIAL", nullable = false, length = 20) ),
			@AttributeOverride(name = "cveoficinadeventas", column = @Column(name = "CVEOFICINADEVENTAS", nullable = false, length = 4) ),
			@AttributeOverride(name = "piezas", column = @Column(name = "PIEZAS", precision = 3, scale = 0) ),
			@AttributeOverride(name = "modelo", column = @Column(name = "MODELO", length = 100) ),
			@AttributeOverride(name = "componentes", column = @Column(name = "COMPONENTES") ),
			@AttributeOverride(name = "comentarios", column = @Column(name = "COMENTARIOS") ),
			@AttributeOverride(name = "reemplazo", column = @Column(name = "REEMPLAZO", precision = 2, scale = 0) ) })
	public SeguimientogarantiaaccId getId() {
		return this.id;
	}

	public void setId(SeguimientogarantiaaccId id) {
		this.id = id;
	}

}
