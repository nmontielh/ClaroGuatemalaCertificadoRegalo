/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.transfer.FuerzaVentasTO
 *  com.claro.transfer.promociones.CtesExcelentesTO
 *  com.claro.transfer.promociones.GrupoTO
 *  com.claro.transfer.promociones.PlanTO
 *  com.claro.transfer.promociones.ProductosSmsTO
 *  com.claro.transfer.promociones.PromocionTO
 *  com.claro.transfer.service.DocumentoTO
 *  com.claro.transfer.service.FileDataTO
 *  javax.ejb.CreateException
 *  javax.ejb.EJBException
 *  javax.ejb.SessionBean
 *  javax.ejb.SessionContext
 */
package com.claro.ejb;

import com.claro.catalogo.Catalogo;
import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.promociones.dao.ActualizaCteExcelenteDAO;
import com.claro.promociones.dao.ActualizaFuerzasVentaDAO;
import com.claro.promociones.dao.ActualizaGruposDAO;
import com.claro.promociones.dao.ActualizaPlanesDAO;
import com.claro.promociones.dao.ActualizaPromocionesDAO;
import com.claro.promociones.dao.ActualizaPromocionesSmsDAO;
import com.claro.promociones.dao.AgregaCteExcelenteDAO;
import com.claro.promociones.dao.AgregaFuerzasVentaDAO;
import com.claro.promociones.dao.AgregaGruposDAO;
import com.claro.promociones.dao.AgregaPlanesDAO;
import com.claro.promociones.dao.AgregaPromocionesDAO;
import com.claro.promociones.dao.AgregaPromocionesSmsDAO;
import com.claro.promociones.dao.EliminaCteExcelenteDAO;
import com.claro.promociones.dao.EliminaFuerzasVentaDAO;
import com.claro.promociones.dao.EliminaGpoPromocionesDAO;
import com.claro.promociones.dao.EliminaPlanesDAO;
import com.claro.promociones.dao.EliminaPromocionesDAO;
import com.claro.promociones.dao.EliminaPromocionesSmsDAO;
import com.claro.promociones.dao.HorarioCargaPromocionesDAO;
import com.claro.promociones.dao.ReplicaPromoDAO;
import com.claro.promociones.deur.DeurThread;
import com.claro.transfer.FuerzaVentasTO;
import com.claro.transfer.promociones.CtesExcelentesTO;
import com.claro.transfer.promociones.GrupoTO;
import com.claro.transfer.promociones.PlanTO;
import com.claro.transfer.promociones.ProductosSmsTO;
import com.claro.transfer.promociones.PromocionTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
import java.rmi.RemoteException;
import java.util.Map;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ProcesaCatalogosBean
implements SessionBean {
    static final long serialVersionUID = 3206093459760846163L;
    private SessionContext mySessionCtx;

    public SessionContext getSessionContext() {
        return this.mySessionCtx;
    }

    public String foo(String param) {
        return null;
    }

    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
        this.mySessionCtx = ctx;
    }

    public void ejbCreate() throws CreateException {
    }

    public void ejbActivate() throws EJBException, RemoteException {
    }

    public void ejbPassivate() throws EJBException, RemoteException {
    }

    public void ejbRemove() throws EJBException, RemoteException {
    }

    public void guardaArchivo(DocumentoTO documentoTO, FileDataTO fileDataTO) throws Exception {
        AgregaPromocionesDAO cargaCatalogosDAO = new AgregaPromocionesDAO();
        cargaCatalogosDAO.guardaArchivo(documentoTO, fileDataTO);
    }

    public boolean agregaPromociones(FileDataTO fileDataTO, int idRegion, String usuario) throws Exception {
        Thread t = new Thread(new AgregaPromocionesDAO(fileDataTO, idRegion, usuario));
        t.start();
        return true;
    }

    public boolean agregaPlanes(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new AgregaPlanesDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean agregaGrupos(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new AgregaGruposDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean agregaFuerzasVentas(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new AgregaFuerzasVentaDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean agregaClienteExcelente(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new AgregaCteExcelenteDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean actualizaGpo(GrupoTO grupoTO) throws Exception {
        AgregaGruposDAO procesaGruposDAO = new AgregaGruposDAO();
        return procesaGruposDAO.actualizaGpo(grupoTO);
    }

    public boolean actualizaGpo(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new ActualizaGruposDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean actualizaPlan(PlanTO planTO) throws Exception {
        AgregaPlanesDAO procesaPlanesDAO = new AgregaPlanesDAO();
        return procesaPlanesDAO.actualizaPlan(planTO);
    }

    public boolean actualizaPlanes(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new ActualizaPlanesDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean actualizaPromocion(PromocionTO promocionTO) throws Exception {
        AgregaPromocionesDAO procesaPromocionesDAO = new AgregaPromocionesDAO();
        return procesaPromocionesDAO.actualizaPromocion(promocionTO);
    }

    public boolean actualizaFzaVentas(FuerzaVentasTO fuerzaVentasTO) throws Exception {
        AgregaFuerzasVentaDAO agregaFuerzasVentaDAO = new AgregaFuerzasVentaDAO();
        return agregaFuerzasVentaDAO.actualizaFuerzaVentas(fuerzaVentasTO);
    }

    public boolean actualizaPromociones(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new ActualizaPromocionesDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean actualizaFzaVentas(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new ActualizaFuerzasVentaDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean actualizaCteExcelente(CtesExcelentesTO ctesExcelentesTO) throws Exception {
        AgregaCteExcelenteDAO agregaCteExcelenteDAO = new AgregaCteExcelenteDAO();
        return agregaCteExcelenteDAO.actualizaCteExcelente(ctesExcelentesTO);
    }

    public boolean actualizaCteExcelente(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new ActualizaCteExcelenteDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean eliminaPromociones(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new EliminaPromocionesDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean eliminaGruposPromociones(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new EliminaGpoPromocionesDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean eliminaPlanes(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new EliminaPlanesDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean eliminaFzaVentas(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new EliminaFuerzasVentaDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public boolean eliminaCteExcelente(FileDataTO fileDataTO, int idRegion) throws Exception {
        Thread t = new Thread(new EliminaCteExcelenteDAO(fileDataTO, idRegion));
        t.start();
        return true;
    }

    public String consultaHorarioProductivoInicio() throws Exception {
        HorarioCargaPromocionesDAO horarioCargaPromocionesDAO = new HorarioCargaPromocionesDAO();
        return horarioCargaPromocionesDAO.consultaHorarioProductivoInicio();
    }

    public String consultaHorarioProductivoFin() throws Exception {
        HorarioCargaPromocionesDAO horarioCargaPromocionesDAO = new HorarioCargaPromocionesDAO();
        return horarioCargaPromocionesDAO.consultaHorarioProductivoFin();
    }

    public String consultaLimiteRegistrosHorarioProductivo() throws Exception {
        HorarioCargaPromocionesDAO horarioCargaPromocionesDAO = new HorarioCargaPromocionesDAO();
        return horarioCargaPromocionesDAO.consultaLimiteRegistrosHorarioProductivo();
    }

    public boolean agregaPromocionesSms(FileDataTO fileDataTO) throws Exception {
        Thread t = new Thread(new AgregaPromocionesSmsDAO(fileDataTO));
        t.start();
        return true;
    }

    public boolean actualizaPromocionSms(FileDataTO fileDataTO) throws Exception {
        Thread t = new Thread(new ActualizaPromocionesSmsDAO(fileDataTO));
        t.start();
        return true;
    }

    public boolean eliminaPromocionesSms(FileDataTO fileDataTO) throws Exception {
        Thread t = new Thread(new EliminaPromocionesSmsDAO(fileDataTO));
        t.start();
        return true;
    }

    public boolean actualizaPromocionesSms(ProductosSmsTO productosSmsTO) throws Exception {
        AgregaPromocionesSmsDAO agregaPromocionesSmsDAO = new AgregaPromocionesSmsDAO();
        return agregaPromocionesSmsDAO.actualizaPromocionSms(productosSmsTO);
    }

    public boolean procesaPromocionesDeur(String nombreArchivo, String usuario, Catalogo propiedades) throws Exception {
        Thread t = new Thread(new DeurThread(nombreArchivo, usuario, propiedades));
        t.start();
        return true;
    }

    public int insertaDetalleReplicaPromoFTP(String nombreArchivoProcesar, String operacion) throws Exception {
        ReplicaPromoDAO replicaPromoDAO = new ReplicaPromoDAO();
        int idArchivo = replicaPromoDAO.verificaExisteDetalleReplicaPromoFTPR1R8(nombreArchivoProcesar);
        if (idArchivo != 0) {
            replicaPromoDAO.actualizaDetalleReplicaPromoFTPR1R8(idArchivo, "T", operacion);
        } else {
            idArchivo = replicaPromoDAO.insertaDetalleReplicaPromoFTPR1R8(nombreArchivoProcesar, operacion);
        }
        return idArchivo;
    }

    public Map<String, String> obtieneCuentasCorreo(int idReporte) {
        ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
        return consultaCuentasCorreoDAO.obtieneCuentasCorreo(idReporte);
    }

    public boolean actualizaDetalleReplicaPromoFTPR1R8(int idArchivo, String estatus, String operacion) throws Exception {
        ReplicaPromoDAO replicaPromoDAO = new ReplicaPromoDAO();
        return replicaPromoDAO.actualizaDetalleReplicaPromoFTPR1R8(idArchivo, estatus, operacion);
    }
}

