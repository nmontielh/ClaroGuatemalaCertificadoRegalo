/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJBObject
 */
package com.claro.ejb;

import com.claro.catalogo.Catalogo;
import com.claro.transfer.FuerzaVentasTO;
import com.claro.transfer.promociones.GrupoTO;
import com.claro.transfer.promociones.PlanTO;
import com.claro.transfer.promociones.ProductosSmsTO;
import com.claro.transfer.promociones.PromocionTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
import java.rmi.RemoteException;
import java.util.Map;
import javax.ejb.EJBObject;

public interface ProcesaCatalogos
extends EJBObject {
    public void guardaArchivo(DocumentoTO var1, FileDataTO var2) throws Exception, RemoteException;

    public boolean agregaPromociones(FileDataTO var1, int var2, String var3) throws Exception, RemoteException;

    public boolean agregaPlanes(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean agregaGrupos(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean agregaFuerzasVentas(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean actualizaGpo(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean actualizaGpo(GrupoTO var1) throws Exception, RemoteException;

    public boolean actualizaPlan(PlanTO var1) throws Exception, RemoteException;

    public boolean actualizaPlanes(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean actualizaPromocion(PromocionTO var1) throws Exception, RemoteException;

    public boolean actualizaFzaVentas(FuerzaVentasTO var1) throws Exception, RemoteException;

    public boolean actualizaPromociones(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean actualizaFzaVentas(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean eliminaPromociones(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean eliminaGruposPromociones(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean eliminaPlanes(FileDataTO var1, int var2) throws Exception, RemoteException;

    public boolean eliminaFzaVentas(FileDataTO var1, int var2) throws Exception, RemoteException;

    public String consultaHorarioProductivoInicio() throws Exception, RemoteException;

    public String consultaHorarioProductivoFin() throws Exception, RemoteException;

    public String consultaLimiteRegistrosHorarioProductivo() throws Exception, RemoteException;

    public boolean agregaPromocionesSms(FileDataTO var1) throws Exception, RemoteException;

    public boolean actualizaPromocionSms(FileDataTO var1) throws Exception, RemoteException;

    public boolean eliminaPromocionesSms(FileDataTO var1) throws Exception, RemoteException;

    public boolean actualizaPromocionesSms(ProductosSmsTO var1) throws Exception, RemoteException;

    public boolean procesaPromocionesDeur(String var1, String var2, Catalogo var3) throws Exception, RemoteException;

    public int insertaDetalleReplicaPromoFTP(String var1, String var2) throws Exception;

    public Map<String, String> obtieneCuentasCorreo(int var1) throws RemoteException;

    public boolean actualizaDetalleReplicaPromoFTPR1R8(int var1, String var2, String var3) throws Exception, RemoteException;
}

