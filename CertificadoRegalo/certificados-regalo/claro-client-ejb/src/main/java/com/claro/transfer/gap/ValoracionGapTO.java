/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.gap;

import com.claro.transfer.gap.InfoPromocionGapTO;
import java.io.Serializable;
import java.util.ArrayList;

public class ValoracionGapTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String codigoMensaje;
    private String numeroTelefonico;
    private String idUsuario;
    private String region;
    private String marca;
    private String modelo;
    private String valoracion;
    private String nivelValorCliente;
    private String contadorPromociones;
    private String nivelChurnCliente;
    private String segmentacionCliente;
    private String codigoErrorMensaje;
    private String descripcionErrorMensaje;
    private ArrayList<InfoPromocionGapTO> promocionesList;

    public String getCodigoMensaje() {
        return this.codigoMensaje;
    }

    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getNumeroTelefonico() {
        return this.numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getValoracion() {
        return this.valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getNivelValorCliente() {
        return this.nivelValorCliente;
    }

    public void setNivelValorCliente(String nivelValorCliente) {
        this.nivelValorCliente = nivelValorCliente;
    }

    public String getContadorPromociones() {
        return this.contadorPromociones;
    }

    public void setContadorPromociones(String contadorPromociones) {
        this.contadorPromociones = contadorPromociones;
    }

    public String getNivelChurnCliente() {
        return this.nivelChurnCliente;
    }

    public void setNivelChurnCliente(String nivelChurnCliente) {
        this.nivelChurnCliente = nivelChurnCliente;
    }

    public String getSegmentacionCliente() {
        return this.segmentacionCliente;
    }

    public void setSegmentacionCliente(String segmentacionCliente) {
        this.segmentacionCliente = segmentacionCliente;
    }

    public String getCodigoErrorMensaje() {
        return this.codigoErrorMensaje;
    }

    public void setCodigoErrorMensaje(String codigoErrorMensaje) {
        this.codigoErrorMensaje = codigoErrorMensaje;
    }

    public String getDescripcionErrorMensaje() {
        return this.descripcionErrorMensaje;
    }

    public void setDescripcionErrorMensaje(String descripcionErrorMensaje) {
        this.descripcionErrorMensaje = descripcionErrorMensaje;
    }

    public String toString() {
        return "\nCodigoMensaje: " + this.codigoMensaje + "\numeroTelefonico: " + this.numeroTelefonico + "\nidUsuario: " + this.idUsuario + "\nregion: " + this.region + "\nmarca: " + this.marca + "\nmodelo: " + this.modelo + "\nvaloracion: " + this.valoracion + "\nnivelValorCliente: " + this.nivelValorCliente + "\ncontadorPromociones: " + this.contadorPromociones + "\nnivelChurnCliente: " + this.nivelChurnCliente + "\nsegmentacionCliente: " + this.segmentacionCliente + "\ncodigoErrorMensaje: " + this.codigoErrorMensaje + "\ndescripcionErrorMensaje: " + this.descripcionErrorMensaje;
    }

    public ArrayList<InfoPromocionGapTO> getPromocionesList() {
        return this.promocionesList;
    }

    public void setPromocionesList(ArrayList<InfoPromocionGapTO> promocionesList) {
        this.promocionesList = promocionesList;
    }
}

