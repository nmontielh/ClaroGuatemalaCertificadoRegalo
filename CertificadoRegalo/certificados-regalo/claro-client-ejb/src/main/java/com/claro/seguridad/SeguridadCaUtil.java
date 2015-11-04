/*
 * Decompiled with CFR 0_102.
 */
package com.claro.seguridad;

import com.claro.transfer.PerfilTO;
import com.claro.transfer.PrivilegioTO;
import java.util.Hashtable;

public class SeguridadCaUtil {
    private static SeguridadCaUtil seguridadCaUtil;

    private SeguridadCaUtil() {
    }

    public static SeguridadCaUtil getInstance() {
        if (seguridadCaUtil == null) {
            seguridadCaUtil = new SeguridadCaUtil();
        }
        return seguridadCaUtil;
    }

    public boolean validaPerfilProcesoCa(PerfilTO perfilTO, String idProceso) {
        Hashtable<String, PrivilegioTO> privilegios = perfilTO.getPrivilegiosCa();
        return privilegios.containsKey(idProceso);
    }

    public boolean validaPerfilProcesoEcac(PerfilTO perfilTO, String idProceso) {
        return true;
    }
}

