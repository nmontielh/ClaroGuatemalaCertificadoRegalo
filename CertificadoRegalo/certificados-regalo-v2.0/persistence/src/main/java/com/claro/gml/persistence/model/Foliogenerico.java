package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Foliogenerico generated by hbm2java
 */
@Entity
@Table(name = "FOLIOGENERICO")
public class Foliogenerico implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private FoliogenericoId id;

	public Foliogenerico() {
	}

	public Foliogenerico(FoliogenericoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "foliogenerico", column = @Column(name = "FOLIOGENERICO", nullable = false, length = 17) ),
			@AttributeOverride(name = "folio", column = @Column(name = "FOLIO", nullable = false, length = 13) ),
			@AttributeOverride(name = "tipofolio", column = @Column(name = "TIPOFOLIO", nullable = false, length = 30) ),
			@AttributeOverride(name = "cveoficinadeventas", column = @Column(name = "CVEOFICINADEVENTAS", length = 4) ),
			@AttributeOverride(name = "fechaalta", column = @Column(name = "FECHAALTA", length = 7) ),
			@AttributeOverride(name = "usuarioalta", column = @Column(name = "USUARIOALTA", length = 7) ),
			@AttributeOverride(name = "comentario", column = @Column(name = "COMENTARIO") ) })
	public FoliogenericoId getId() {
		return this.id;
	}

	public void setId(FoliogenericoId id) {
		this.id = id;
	}

}
