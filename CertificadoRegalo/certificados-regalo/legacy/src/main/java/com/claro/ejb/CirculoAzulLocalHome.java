package com.claro.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public abstract interface CirculoAzulLocalHome
  extends EJBLocalHome
{
  public abstract CirculoAzulLocal create()
    throws CreateException;
}


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/ejb/CirculoAzulLocalHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */