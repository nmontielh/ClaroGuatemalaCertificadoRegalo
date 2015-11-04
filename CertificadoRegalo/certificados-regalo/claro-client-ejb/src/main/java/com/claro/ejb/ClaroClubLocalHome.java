/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.CreateException
 *  javax.ejb.EJBLocalHome
 */
package com.claro.ejb;

import com.claro.ejb.ClaroClubLocal;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface ClaroClubLocalHome
extends EJBLocalHome {
    public ClaroClubLocal create() throws CreateException;
}

