package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Evaluacion generated by hbm2java
 */
@Entity
@Table(name = "EVALUACION", schema = "CERTREG")
public class Evaluacion implements java.io.Serializable {

	private EvaluacionId id;

	public Evaluacion() {
	}

	public Evaluacion(EvaluacionId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idevaluacion", column = @Column(name = "IDEVALUACION", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "cveoficinadeventas", column = @Column(name = "CVEOFICINADEVENTAS", length = 4) ),
			@AttributeOverride(name = "mes", column = @Column(name = "MES", precision = 2, scale = 0) ),
			@AttributeOverride(name = "anio", column = @Column(name = "ANIO", precision = 6, scale = 0) ),
			@AttributeOverride(name = "usersup", column = @Column(name = "USERSUP", length = 7) ),
			@AttributeOverride(name = "usercalif", column = @Column(name = "USERCALIF", length = 7) ),
			@AttributeOverride(name = "intentos", column = @Column(name = "INTENTOS", precision = 2, scale = 0) ),
			@AttributeOverride(name = "fechaevaluacion", column = @Column(name = "FECHAEVALUACION", length = 7) ) })
	public EvaluacionId getId() {
		return this.id;
	}

	public void setId(EvaluacionId id) {
		this.id = id;
	}

}