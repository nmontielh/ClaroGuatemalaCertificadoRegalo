/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJBLocalHome
 */
package com.claro.catalogo;

import com.claro.ejb.CirculoAzulLocal;
import com.claro.ejb.CirculoAzulLocalHome;
import com.claro.transfer.CatalogoTO;
import com.claro.util.ServiceLocator;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJBLocalHome;

public class Catalogo {
    private ArrayList<CatalogoTO> catalogo;
    private String tabla;
    private HashMap<String, String> mapa;
    private ArrayList<String> keys;

    public void cargaCatalogo() throws Exception {
        CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome)ServiceLocator.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
        CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
        if (this.tabla.equals("propiedades")) {
            this.catalogo = circuloAzulLocal.obtienePropiedades();
        } else if (this.tabla.equals("grupoPromocion")) {
            this.catalogo = circuloAzulLocal.obtieneGrupoPromocion();
        }
        this.iniciaMapa();
    }

    private void iniciaMapa() {
        if (this.catalogo != null) {
            this.mapa = new HashMap();
            this.keys = new ArrayList();
            for (CatalogoTO catalogoTO : this.catalogo) {
                this.mapa.put(catalogoTO.getIdVariable(), catalogoTO.getValor());
                this.keys.add(catalogoTO.getIdVariable());
            }
        }
    }

    public ArrayList<CatalogoTO> getCatalogo() {
        return this.catalogo;
    }

    public void setCatalogo(ArrayList<CatalogoTO> catalogo) {
        this.catalogo = catalogo;
    }

    public String getTabla() {
        return this.tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getPropiedad(String id) {
        return this.mapa.get(id);
    }

    public int getPropiedadInt(String id) {
        if (this.mapa.get(id) != null) {
            return Integer.parseInt(this.mapa.get(id));
        }
        return 0;
    }

    public boolean contieneValorEnPropiedad(String propiedad, String valor) {
        return this.keys.contains(propiedad);
    }
}

