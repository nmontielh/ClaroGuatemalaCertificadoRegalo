package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Respuestas generated by hbm2java
 */
@Entity
@Table(name = "RESPUESTAS", schema = "CERTREG")
public class Respuestas implements java.io.Serializable {

	private RespuestasId id;

	public Respuestas() {
	}

	public Respuestas(RespuestasId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idevaluacion", column = @Column(name = "IDEVALUACION", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "cvemarca", column = @Column(name = "CVEMARCA", length = 30) ),
			@AttributeOverride(name = "idcsa", column = @Column(name = "IDCSA", precision = 2, scale = 0) ),
			@AttributeOverride(name = "idpregunta", column = @Column(name = "IDPREGUNTA", nullable = false, precision = 2, scale = 0) ),
			@AttributeOverride(name = "idsubpregunta", column = @Column(name = "IDSUBPREGUNTA", nullable = false, precision = 2, scale = 0) ),
			@AttributeOverride(name = "valor", column = @Column(name = "VALOR", precision = 6, scale = 0) ),
			@AttributeOverride(name = "idrespuesta", column = @Column(name = "IDRESPUESTA", nullable = false, precision = 6, scale = 0) ) })
	public RespuestasId getId() {
		return this.id;
	}

	public void setId(RespuestasId id) {
		this.id = id;
	}

}
