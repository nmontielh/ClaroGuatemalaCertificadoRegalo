/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.CreateException
 *  javax.ejb.EJBHome
 */
package com.claro.ejb;

import com.claro.ejb.ProcesaCatalogos;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ProcesaCatalogosHome
extends EJBHome {
    public ProcesaCatalogos create() throws CreateException, RemoteException;
}

