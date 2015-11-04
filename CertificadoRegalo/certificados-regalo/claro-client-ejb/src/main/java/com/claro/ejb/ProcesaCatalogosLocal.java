/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJBLocalObject
 */
package com.claro.ejb;

import com.claro.catalogo.Catalogo;
import com.claro.transfer.FuerzaVentasTO;
import com.claro.transfer.promociones.CtesExcelentesTO;
import com.claro.transfer.promociones.GrupoTO;
import com.claro.transfer.promociones.PlanTO;
import com.claro.transfer.promociones.ProductosSmsTO;
import com.claro.transfer.promociones.PromocionTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
import java.util.Map;
import javax.ejb.EJBLocalObject;

public interface ProcesaCatalogosLocal
extends EJBLocalObject {
    public void guardaArchivo(DocumentoTO var1, FileDataTO var2) throws Exception;

    public boolean agregaPromociones(FileDataTO var1, int var2, String var3) throws Exception;

    public boolean agregaPlanes(FileDataTO var1, int var2) throws Exception;

    public boolean agregaGrupos(FileDataTO var1, int var2) throws Exception;

    public boolean agregaFuerzasVentas(FileDataTO var1, int var2) throws Exception;

    public boolean actualizaGpo(FileDataTO var1, int var2) throws Exception;

    public boolean actualizaGpo(GrupoTO var1) throws Exception;

    public boolean actualizaPlan(PlanTO var1) throws Exception;

    public boolean actualizaPlanes(FileDataTO var1, int var2) throws Exception;

    public boolean actualizaPromocion(PromocionTO var1) throws Exception;

    public boolean actualizaFzaVentas(FuerzaVentasTO var1) throws Exception;

    public boolean actualizaPromociones(FileDataTO var1, int var2) throws Exception;

    public boolean actualizaFzaVentas(FileDataTO var1, int var2) throws Exception;

    public boolean eliminaPromociones(FileDataTO var1, int var2) throws Exception;

    public boolean eliminaGruposPromociones(FileDataTO var1, int var2) throws Exception;

    public boolean eliminaPlanes(FileDataTO var1, int var2) throws Exception;

    public boolean eliminaFzaVentas(FileDataTO var1, int var2) throws Exception;

    public String consultaHorarioProductivoInicio() throws Exception;

    public String consultaHorarioProductivoFin() throws Exception;

    public String consultaLimiteRegistrosHorarioProductivo() throws Exception;

    public boolean agregaPromocionesSms(FileDataTO var1) throws Exception;

    public boolean actualizaPromocionSms(FileDataTO var1) throws Exception;

    public boolean eliminaPromocionesSms(FileDataTO var1) throws Exception;

    public boolean actualizaPromocionesSms(ProductosSmsTO var1) throws Exception;

    public boolean procesaPromocionesDeur(String var1, String var2, Catalogo var3) throws Exception;

    public boolean agregaClienteExcelente(FileDataTO var1, int var2) throws Exception;

    public boolean actualizaCteExcelente(FileDataTO var1, int var2) throws Exception;

    public boolean eliminaCteExcelente(FileDataTO var1, int var2) throws Exception;

    public boolean actualizaCteExcelente(CtesExcelentesTO var1) throws Exception;

    public int insertaDetalleReplicaPromoFTP(String var1, String var2) throws Exception;

    public Map<String, String> obtieneCuentasCorreo(int var1);

    public boolean actualizaDetalleReplicaPromoFTPR1R8(int var1, String var2, String var3) throws Exception;
}

