/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.transfer.AlianzasTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.ProductosTO;
import java.io.Serializable;
import java.util.ArrayList;

public class AlianzasTipoTO
extends MensajeTO
implements Serializable {
    private static final long serialVersionUID = 1;
    private ArrayList<AlianzasTO> alianzasArray;
    private ArrayList<ProductosTO> productosArray;
    private AlianzasTO alianzas;

    public ArrayList<AlianzasTO> getAlianzasArray() {
        return this.alianzasArray;
    }

    public void setAlianzasArray(ArrayList<AlianzasTO> alianzasArray) {
        this.alianzasArray = alianzasArray;
    }

    public ArrayList<ProductosTO> getProductosArray() {
        return this.productosArray;
    }

    public void setProductosArray(ArrayList<ProductosTO> productosArray) {
        this.productosArray = productosArray;
    }

    public AlianzasTO getAlianzas() {
        return this.alianzas;
    }

    public void setAlianzas(AlianzasTO alianzas) {
        this.alianzas = alianzas;
    }
}

