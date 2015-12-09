package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Listavalores generated by hbm2java
 */
@Entity
@Table(name = "LISTAVALORES", schema = "CERTREG")
public class Listavalores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private ListavaloresId id;

	public Listavalores() {
	}

	public Listavalores(ListavaloresId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idlistavalor", column = @Column(name = "IDLISTAVALOR", nullable = false, precision = 3, scale = 0) ),
			@AttributeOverride(name = "llave", column = @Column(name = "LLAVE", length = 30) ),
			@AttributeOverride(name = "valor", column = @Column(name = "VALOR", length = 100) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 2, scale = 0) ),
			@AttributeOverride(name = "usuariomod", column = @Column(name = "USUARIOMOD", length = 7) ),
			@AttributeOverride(name = "fechamod", column = @Column(name = "FECHAMOD", length = 7) ),
			@AttributeOverride(name = "editable", column = @Column(name = "EDITABLE", precision = 1, scale = 0) ) })
	public ListavaloresId getId() {
		return this.id;
	}

	public void setId(ListavaloresId id) {
		this.id = id;
	}

}
