/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.CreateException
 *  javax.ejb.EJBHome
 */
package com.claro.ejb;

import com.claro.ejb.ClaroClub;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ClaroClubHome
extends EJBHome {
    public ClaroClub create() throws CreateException, RemoteException;
}

