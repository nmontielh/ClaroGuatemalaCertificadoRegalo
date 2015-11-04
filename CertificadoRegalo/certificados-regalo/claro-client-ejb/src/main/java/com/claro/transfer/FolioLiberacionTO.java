/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.MensajeTO;
import java.io.Serializable;

public class FolioLiberacionTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = -8627320171353134978L;
    private String folio;

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}

