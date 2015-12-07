package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Comentarios generated by hbm2java
 */
@Entity
@Table(name = "COMENTARIOS", schema = "CERTREG")
public class Comentarios implements java.io.Serializable {

	private ComentariosId id;

	public Comentarios() {
	}

	public Comentarios(ComentariosId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "folio", column = @Column(name = "FOLIO", nullable = false, length = 13) ),
			@AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false, length = 7) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION") ),
			@AttributeOverride(name = "usuariocrea", column = @Column(name = "USUARIOCREA", length = 7) ) })
	public ComentariosId getId() {
		return this.id;
	}

	public void setId(ComentariosId id) {
		this.id = id;
	}

}