package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ClUsuariosCertificados generated by hbm2java
 */
@Entity
@Table(name = "CL_USUARIOS_CERTIFICADOS", schema = "CERTREG")
public class ClUsuariosCertificados implements java.io.Serializable {

	private ClUsuariosCertificadosId id;

	public ClUsuariosCertificados() {
	}

	public ClUsuariosCertificados(ClUsuariosCertificadosId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "clUsuariosCertificadosId", column = @Column(name = "CL_USUARIOS_CERTIFICADOS_ID", precision = 15, scale = 0) ),
			@AttributeOverride(name = "usuarioNombre", column = @Column(name = "USUARIO_NOMBRE", length = 40) ),
			@AttributeOverride(name = "usuarioPass", column = @Column(name = "USUARIO_PASS", length = 200) ),
			@AttributeOverride(name = "usuarioRol", column = @Column(name = "USUARIO_ROL", length = 10) ),
			@AttributeOverride(name = "fechaCreacion", column = @Column(name = "FECHA_CREACION", length = 7) ) })
	public ClUsuariosCertificadosId getId() {
		return this.id;
	}

	public void setId(ClUsuariosCertificadosId id) {
		this.id = id;
	}

}
