package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PtoCtlusuarios generated by hbm2java
 */
@Entity
@Table(name = "PTO_CTLUSUARIOS")
public class PtoCtlusuarios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numempleado;
	private String idusuario;
	private String nombre;
	private String idperfil;
	private String password;
	private String status;
	private Long contadorpmp;
	private String idusuariocaptura;
	private Timestamp fechaupdate;
	private String sistemaadmin;
	private Timestamp fechaadmin;
	private BigDecimal idperfiln;
	private String passwdenc;
	private Character bancambiapass;
	private Timestamp fechaacceso;

	public PtoCtlusuarios() {
	}

	public PtoCtlusuarios(String numempleado, String idusuario) {
		this.numempleado = numempleado;
		this.idusuario = idusuario;
	}

	public PtoCtlusuarios(String numempleado, String idusuario, String nombre, String idperfil, String password,
			String status, Long contadorpmp, String idusuariocaptura, Timestamp fechaupdate, String sistemaadmin,
			Timestamp fechaadmin, BigDecimal idperfiln, String passwdenc, Character bancambiapass,
			Timestamp fechaacceso) {
		this.numempleado = numempleado;
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.idperfil = idperfil;
		this.password = password;
		this.status = status;
		this.contadorpmp = contadorpmp;
		this.idusuariocaptura = idusuariocaptura;
		this.fechaupdate = fechaupdate;
		this.sistemaadmin = sistemaadmin;
		this.fechaadmin = fechaadmin;
		this.idperfiln = idperfiln;
		this.passwdenc = passwdenc;
		this.bancambiapass = bancambiapass;
		this.fechaacceso = fechaacceso;
	}

	@Id

	@Column(name = "NUMEMPLEADO", unique = true, nullable = false, length = 10)
	public String getNumempleado() {
		return this.numempleado;
	}

	public void setNumempleado(String numempleado) {
		this.numempleado = numempleado;
	}

	@Column(name = "IDUSUARIO", nullable = false, length = 10)
	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	@Column(name = "NOMBRE", length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "IDPERFIL", length = 8)
	public String getIdperfil() {
		return this.idperfil;
	}

	public void setIdperfil(String idperfil) {
		this.idperfil = idperfil;
	}

	@Column(name = "PASSWORD", length = 8)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CONTADORPMP", precision = 10, scale = 0)
	public Long getContadorpmp() {
		return this.contadorpmp;
	}

	public void setContadorpmp(Long contadorpmp) {
		this.contadorpmp = contadorpmp;
	}

	@Column(name = "IDUSUARIOCAPTURA", length = 10)
	public String getIdusuariocaptura() {
		return this.idusuariocaptura;
	}

	public void setIdusuariocaptura(String idusuariocaptura) {
		this.idusuariocaptura = idusuariocaptura;
	}

	@Column(name = "FECHAUPDATE")
	public Timestamp getFechaupdate() {
		return this.fechaupdate;
	}

	public void setFechaupdate(Timestamp fechaupdate) {
		this.fechaupdate = fechaupdate;
	}

	@Column(name = "SISTEMAADMIN", length = 10)
	public String getSistemaadmin() {
		return this.sistemaadmin;
	}

	public void setSistemaadmin(String sistemaadmin) {
		this.sistemaadmin = sistemaadmin;
	}

	@Column(name = "FECHAADMIN")
	public Timestamp getFechaadmin() {
		return this.fechaadmin;
	}

	public void setFechaadmin(Timestamp fechaadmin) {
		this.fechaadmin = fechaadmin;
	}

	@Column(name = "IDPERFILN", precision = 22, scale = 0)
	public BigDecimal getIdperfiln() {
		return this.idperfiln;
	}

	public void setIdperfiln(BigDecimal idperfiln) {
		this.idperfiln = idperfiln;
	}

	@Column(name = "PASSWDENC", length = 32)
	public String getPasswdenc() {
		return this.passwdenc;
	}

	public void setPasswdenc(String passwdenc) {
		this.passwdenc = passwdenc;
	}

	@Column(name = "BANCAMBIAPASS", length = 1)
	public Character getBancambiapass() {
		return this.bancambiapass;
	}

	public void setBancambiapass(Character bancambiapass) {
		this.bancambiapass = bancambiapass;
	}

	@Column(name = "FECHAACCESO")
	public Timestamp getFechaacceso() {
		return this.fechaacceso;
	}

	public void setFechaacceso(Timestamp fechaacceso) {
		this.fechaacceso = fechaacceso;
	}

}
