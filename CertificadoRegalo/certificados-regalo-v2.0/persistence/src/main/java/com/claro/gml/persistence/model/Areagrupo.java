package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Areagrupo generated by hbm2java
 */
@Entity
@Table(name = "AREAGRUPO", schema = "CERTREG")
public class Areagrupo implements java.io.Serializable {

	private AreagrupoId id;

	public Areagrupo() {
	}

	public Areagrupo(AreagrupoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idgrupo", column = @Column(name = "IDGRUPO", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "idarea", column = @Column(name = "IDAREA", nullable = false, precision = 2, scale = 0) ) })
	public AreagrupoId getId() {
		return this.id;
	}

	public void setId(AreagrupoId id) {
		this.id = id;
	}

}
