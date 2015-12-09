package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProveedorprincipalId generated by hbm2java
 */
@Embeddable
public class ProveedorprincipalId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private short idproveedor;
	private String cveprincipal;
	private String tipo;
	private String clave;
	private String region;
	private Boolean activo;

	public ProveedorprincipalId() {
	}

	public ProveedorprincipalId(short idproveedor) {
		this.idproveedor = idproveedor;
	}

	public ProveedorprincipalId(short idproveedor, String cveprincipal, String tipo, String clave, String region,
			Boolean activo) {
		this.idproveedor = idproveedor;
		this.cveprincipal = cveprincipal;
		this.tipo = tipo;
		this.clave = clave;
		this.region = region;
		this.activo = activo;
	}

	@Column(name = "IDPROVEEDOR", nullable = false, precision = 3, scale = 0)
	public short getIdproveedor() {
		return this.idproveedor;
	}

	public void setIdproveedor(short idproveedor) {
		this.idproveedor = idproveedor;
	}

	@Column(name = "CVEPRINCIPAL", length = 4)
	public String getCveprincipal() {
		return this.cveprincipal;
	}

	public void setCveprincipal(String cveprincipal) {
		this.cveprincipal = cveprincipal;
	}

	@Column(name = "TIPO", length = 3)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "CLAVE", length = 4)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "REGION", length = 2)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProveedorprincipalId))
			return false;
		ProveedorprincipalId castOther = (ProveedorprincipalId) other;

		return (this.getIdproveedor() == castOther.getIdproveedor())
				&& ((this.getCveprincipal() == castOther.getCveprincipal())
						|| (this.getCveprincipal() != null && castOther.getCveprincipal() != null
								&& this.getCveprincipal().equals(castOther.getCveprincipal())))
				&& ((this.getTipo() == castOther.getTipo()) || (this.getTipo() != null && castOther.getTipo() != null
						&& this.getTipo().equals(castOther.getTipo())))
				&& ((this.getClave() == castOther.getClave()) || (this.getClave() != null
						&& castOther.getClave() != null && this.getClave().equals(castOther.getClave())))
				&& ((this.getRegion() == castOther.getRegion()) || (this.getRegion() != null
						&& castOther.getRegion() != null && this.getRegion().equals(castOther.getRegion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdproveedor();
		result = 37 * result + (getCveprincipal() == null ? 0 : this.getCveprincipal().hashCode());
		result = 37 * result + (getTipo() == null ? 0 : this.getTipo().hashCode());
		result = 37 * result + (getClave() == null ? 0 : this.getClave().hashCode());
		result = 37 * result + (getRegion() == null ? 0 : this.getRegion().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		return result;
	}

}
