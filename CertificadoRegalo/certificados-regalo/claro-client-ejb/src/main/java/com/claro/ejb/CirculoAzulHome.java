/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.CreateException
 *  javax.ejb.EJBHome
 */
package com.claro.ejb;

import com.claro.ejb.CirculoAzul;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface CirculoAzulHome
extends EJBHome {
    public CirculoAzul create() throws CreateException, RemoteException;
}

