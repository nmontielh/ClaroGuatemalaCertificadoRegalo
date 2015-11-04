/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.CreateException
 *  javax.ejb.EJBLocalHome
 */
package com.claro.ejb;

import com.claro.ejb.ProcesaCatalogosLocal;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface ProcesaCatalogosLocalHome
extends EJBLocalHome {
    public ProcesaCatalogosLocal create() throws CreateException;
}

