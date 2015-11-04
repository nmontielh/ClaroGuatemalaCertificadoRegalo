/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.FacturaTO;
import com.claro.transfer.MensajeTO;
import com.claro.util.Constantes;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class MobileTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private String telefono;
    private String region;
    private String cuenta;
    private String status;
    private String motivo;
    private String secuencia;
    private String addM2K;
    private String ciclo;
    private String planM2K;
    private String fecAltaUser;
    private String fecAddM2K;
    private String estCobranza;
    private String nombre;
    private String fecEfectiva;
    private String claseCredit;
    private String mesesCareg;
    private String addCareg;
    private String fechaSuspension;
    private String firstName;
    private String lastName;
    private String tecnologia;
    private String idTecnologia;
    private ArrayList<FacturaTO> facturas;
    private String esn;
    private String imei;
    private String iccid;
    private String cuentaPadre;
    private String marca;
    private String modelo;
    private String TelContacto1;
    private String TelContacto2;
    private String ExtContacto1;
    private String ExtContacto2;
    private String cuentaCorreo;
    private String sPromFacturaAV;
    private double promedio;
    private int NoBajas;
    private String rfc;

    public int getNoBajas() {
        return this.NoBajas;
    }

    public void setNoBajas(int noBajas) {
        this.NoBajas = noBajas;
    }

    public double getPromedio() {
        if (this.facturas != null && this.facturas.size() > 0) {
            try {
                ListIterator<FacturaTO> iterator = this.facturas.listIterator();
                double suma = 0.0;
                while (iterator.hasNext()) {
                    FacturaTO facturaTO = iterator.next();
                    suma+=facturaTO.getMonto();
                }
                this.promedio = suma / (double)this.facturas.size();
            }
            catch (Exception iterator) {
                // empty catch block
            }
        }
        return this.promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFechaSuspension() {
        return this.fechaSuspension;
    }

    public void setFechaSuspension(String fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSecuencia() {
        return this.secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getAddM2K() {
        return this.addM2K;
    }

    public void setAddM2K(String addM2K) {
        this.addM2K = addM2K;
    }

    public String getCiclo() {
        return this.ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getPlanM2K() {
        return this.planM2K;
    }

    public void setPlanM2K(String planM2K) {
        this.planM2K = planM2K;
    }

    public String getFecAltaUser() {
        return this.fecAltaUser;
    }

    public void setFecAltaUser(String fecAltaUser) {
        this.fecAltaUser = fecAltaUser;
    }

    public String getFecAddM2K() {
        return this.fecAddM2K;
    }

    public void setFecAddM2K(String fecAddM2K) {
        this.fecAddM2K = fecAddM2K;
    }

    public String getEstCobranza() {
        return this.estCobranza;
    }

    public void setEstCobranza(String estCobranza) {
        this.estCobranza = estCobranza;
    }

    public String getNombre() {
        this.nombre = this.lastName != null ? String.valueOf(this.lastName.trim()) + " " : "";
        if (this.firstName != null) {
            this.nombre = String.valueOf(this.nombre) + this.firstName.trim() + " ";
        }
        return this.nombre.trim();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecEfectiva() {
        return this.fecEfectiva;
    }

    public void setFecEfectiva(String fecEfectiva) {
        this.fecEfectiva = fecEfectiva;
    }

    public String getClaseCredit() {
        return this.claseCredit;
    }

    public void setClaseCredit(String claseCredit) {
        this.claseCredit = claseCredit;
    }

    public String getMesesCareg() {
        return this.mesesCareg;
    }

    public void setMesesCareg(String mesesCareg) {
        this.mesesCareg = mesesCareg;
    }

    public String getAddCareg() {
        return this.addCareg;
    }

    public void setAddCareg(String addCareg) {
        this.addCareg = addCareg;
    }

    public String getSPromFacturaAV() {
        return this.sPromFacturaAV;
    }

    public void setSPromFacturaAV(String promFacturaAV) {
        this.sPromFacturaAV = promFacturaAV;
    }

    public ArrayList<FacturaTO> getFacturas() {
        return this.facturas;
    }

    public void setFacturas(ArrayList<FacturaTO> facturas) {
        this.facturas = facturas;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTecnologia() {
        this.tecnologia = this.idTecnologia.equals("0") ? "TDMA" : "GSM";
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getIdTecnologia() {
        return this.idTecnologia;
    }

    public void setIdTecnologia(String idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    public String getEsn() {
        return this.esn;
    }

    public void setEsn(String esn) {
        this.esn = esn;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getCuentaPadre() {
        return this.cuentaPadre;
    }

    public void setCuentaPadre(String cuentaPadre) {
        this.cuentaPadre = cuentaPadre;
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

    public Date getFechaAddendum() {
        try {
            return Constantes.DATEFORMATyyyy_MM_dd.parse(this.fecAddM2K);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Date getFechaEfectiva() {
        try {
            return Constantes.DATEFORMATyyyy_MM_dd.parse(this.fecEfectiva);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Date getFechaAltaUser() {
        try {
            return Constantes.DATEFORMATyyyy_MM_dd.parse(this.fecAltaUser);
        }
        catch (Exception e) {
            return null;
        }
    }

    public String getTelContacto1() {
        return this.TelContacto1;
    }

    public void setTelContacto1(String telContacto1) {
        this.TelContacto1 = telContacto1;
    }

    public String getTelContacto2() {
        return this.TelContacto2;
    }

    public void setTelContacto2(String telContacto2) {
        this.TelContacto2 = telContacto2;
    }

    public String getExtContacto1() {
        return this.ExtContacto1;
    }

    public void setExtContacto1(String extContacto1) {
        this.ExtContacto1 = extContacto1;
    }

    public String getExtContacto2() {
        return this.ExtContacto2;
    }

    public void setExtContacto2(String extContacto2) {
        this.ExtContacto2 = extContacto2;
    }

    public String getCuentaCorreo() {
        return this.cuentaCorreo;
    }

    public void setCuentaCorreo(String cuentaCorreo) {
        this.cuentaCorreo = cuentaCorreo;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}

