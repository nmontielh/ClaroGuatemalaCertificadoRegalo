package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ClUsuariosCertificadosId generated by hbm2java
 */
@Embeddable
public class ClUsuariosCertificadosId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long clUsuariosCertificadosId;
	private String usuarioNombre;
	private String usuarioPass;
	private String usuarioRol;
	private Date fechaCreacion;

	public ClUsuariosCertificadosId() {
	}

	public ClUsuariosCertificadosId(Long clUsuariosCertificadosId, String usuarioNombre, String usuarioPass,
			String usuarioRol, Date fechaCreacion) {
		this.clUsuariosCertificadosId = clUsuariosCertificadosId;
		this.usuarioNombre = usuarioNombre;
		this.usuarioPass = usuarioPass;
		this.usuarioRol = usuarioRol;
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "CL_USUARIOS_CERTIFICADOS_ID", precision = 15, scale = 0)
	public Long getClUsuariosCertificadosId() {
		return this.clUsuariosCertificadosId;
	}

	public void setClUsuariosCertificadosId(Long clUsuariosCertificadosId) {
		this.clUsuariosCertificadosId = clUsuariosCertificadosId;
	}

	@Column(name = "USUARIO_NOMBRE", length = 40)
	public String getUsuarioNombre() {
		return this.usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	@Column(name = "USUARIO_PASS", length = 200)
	public String getUsuarioPass() {
		return this.usuarioPass;
	}

	public void setUsuarioPass(String usuarioPass) {
		this.usuarioPass = usuarioPass;
	}

	@Column(name = "USUARIO_ROL", length = 10)
	public String getUsuarioRol() {
		return this.usuarioRol;
	}

	public void setUsuarioRol(String usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	@Column(name = "FECHA_CREACION", length = 7)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClUsuariosCertificadosId))
			return false;
		ClUsuariosCertificadosId castOther = (ClUsuariosCertificadosId) other;

		return ((this.getClUsuariosCertificadosId() == castOther.getClUsuariosCertificadosId())
				|| (this.getClUsuariosCertificadosId() != null && castOther.getClUsuariosCertificadosId() != null
						&& this.getClUsuariosCertificadosId().equals(castOther.getClUsuariosCertificadosId())))
				&& ((this.getUsuarioNombre() == castOther.getUsuarioNombre())
						|| (this.getUsuarioNombre() != null && castOther.getUsuarioNombre() != null
								&& this.getUsuarioNombre().equals(castOther.getUsuarioNombre())))
				&& ((this.getUsuarioPass() == castOther.getUsuarioPass())
						|| (this.getUsuarioPass() != null && castOther.getUsuarioPass() != null
								&& this.getUsuarioPass().equals(castOther.getUsuarioPass())))
				&& ((this.getUsuarioRol() == castOther.getUsuarioRol()) || (this.getUsuarioRol() != null
						&& castOther.getUsuarioRol() != null && this.getUsuarioRol().equals(castOther.getUsuarioRol())))
				&& ((this.getFechaCreacion() == castOther.getFechaCreacion())
						|| (this.getFechaCreacion() != null && castOther.getFechaCreacion() != null
								&& this.getFechaCreacion().equals(castOther.getFechaCreacion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClUsuariosCertificadosId() == null ? 0 : this.getClUsuariosCertificadosId().hashCode());
		result = 37 * result + (getUsuarioNombre() == null ? 0 : this.getUsuarioNombre().hashCode());
		result = 37 * result + (getUsuarioPass() == null ? 0 : this.getUsuarioPass().hashCode());
		result = 37 * result + (getUsuarioRol() == null ? 0 : this.getUsuarioRol().hashCode());
		result = 37 * result + (getFechaCreacion() == null ? 0 : this.getFechaCreacion().hashCode());
		return result;
	}

}
