/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.PrivilegioTO;
import java.io.Serializable;
import java.util.Hashtable;

public class PerfilTO
implements Serializable {
    private static final long serialVersionUID = -8828266038303257532L;
    private int idPerfilN;
    private String idPuesto;
    private String descripcion;
    private int region;
    private int nivelAutorizacion;
    private Hashtable<String, PrivilegioTO> privilegiosCa;
    private Hashtable<String, PrivilegioTO> privilegiosEcac;

    public Hashtable<String, PrivilegioTO> getPrivilegiosCa() {
        return this.privilegiosCa;
    }

    public void setPrivilegiosCa(Hashtable<String, PrivilegioTO> privilegiosCa) {
        this.privilegiosCa = privilegiosCa;
    }

    public int getIdPerfilN() {
        return this.idPerfilN;
    }

    public void setIdPerfilN(int idPerfilN) {
        this.idPerfilN = idPerfilN;
    }

    public String getIdPuesto() {
        return this.idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getNivelAutorizacion() {
        return this.nivelAutorizacion;
    }

    public void setNivelAutorizacion(int nivelAutorizacion) {
        this.nivelAutorizacion = nivelAutorizacion;
    }

    public Hashtable<String, PrivilegioTO> getPrivilegiosEcac() {
        return this.privilegiosEcac;
    }

    public void setPrivilegiosEcac(Hashtable<String, PrivilegioTO> privilegiosEcac) {
        this.privilegiosEcac = privilegiosEcac;
    }
}

