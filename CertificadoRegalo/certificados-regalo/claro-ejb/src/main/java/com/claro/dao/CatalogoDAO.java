/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.exception.CAException
 *  com.claro.transfer.AlianzasTO
 *  com.claro.transfer.BeneficioTO
 *  com.claro.transfer.CatalogoTO
 *  com.claro.transfer.CiudadEdoTO
 *  com.claro.transfer.DescuentoTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.MotivoTO
 *  com.claro.transfer.PlanTO
 *  com.claro.transfer.ProductosSmsTO
 *  com.claro.transfer.ProductosTO
 *  com.claro.transfer.PromoBeneficiosTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.transfer.gap.InfoPromocionGapTO
 *  com.claro.transfer.gap.PromocionCaTO
 *  com.claro.transfer.gap.ValoracionGapTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.catalogo.Catalogo;
import com.claro.dao.ConsultasGapDAO;
import com.claro.exception.CAException;
import com.claro.services.acr.ClientePromocionesAcr;
import com.claro.services.gap.RespuestaConsultasGap;
import com.claro.transfer.AlianzasTO;
import com.claro.transfer.BeneficioTO;
import com.claro.transfer.CiudadEdoTO;
import com.claro.transfer.DescuentoTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.MotivoTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.ProductosSmsTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PromoBeneficiosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.transfer.gap.InfoPromocionGapTO;
import com.claro.transfer.gap.PromocionCaTO;
import com.claro.transfer.gap.ValoracionGapTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class CatalogoDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;
    private ConsultasGapDAO consultasGapDAO = null;

    public CatalogoDAO() {
        try {
            this.consultasGapDAO = new ConsultasGapDAO();
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"CatalogoDAO", (Throwable)e);
        }
    }

    public ArrayList<com.claro.transfer.CatalogoTO> getMarcas(int region, int grupo) throws CAException {
        ArrayList<com.claro.transfer.CatalogoTO> marcas;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT DISTINCT MARCA FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES A ");
        query.append(" WHERE A.ESTATUS = ? ");
        query.append(" AND A.IDREGION = ? ");
        if (grupo != -1) {
            query.append(" AND A.IDGRUPOPROMOCION = ? ");
        }
        if (region == 9) {
            query.append(" AND A.FZAVENTAS IN (?,?) ");
        } else {
            query.append(" AND A.FZAVENTAS IN (?) ");
        }
        query.append(" ORDER BY MARCA ASC ");
        marcas = new ArrayList<com.claro.transfer.CatalogoTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, "A");
                statement.setInt(2, region);
                if (grupo != -1) {
                    statement.setInt(3, grupo);
                    statement.setString(4, "TELCEL");
                    if (region == 9) {
                        statement.setString(5, "TODOS");
                    }
                } else {
                    statement.setString(3, "TELCEL");
                    if (region == 9) {
                        statement.setString(4, "TODOS");
                    }
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    com.claro.transfer.CatalogoTO catalogoTO = new com.claro.transfer.CatalogoTO();
                    catalogoTO.setDescripcion(resultSet.getString(1));
                    marcas.add(catalogoTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getMarcas[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getMarcas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_15) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_16) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_17) {}
            }
        }
        return marcas;
    }

    public ArrayList<com.claro.transfer.CatalogoTO> getModelos(String marca, int region, int grupo, String fzaVentas) throws CAException {
    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT DISTINCT MODELO FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES A ");
        query.append("  WHERE A.ESTATUS = ? AND");
        query.append("        A.IDREGION = ? AND");
        query.append("        A.MARCA = ? ");
        if (grupo != -1) {
            query.append("    AND A.IDGRUPOPROMOCION = ? ");
        }
        if (region == 9) {
            if (fzaVentas != null) {
                query.append("    AND A.FZAVENTAS IN (?,?) ");
            } else {
                query.append("    AND A.FZAVENTAS IN (?,?)");
            }
        } else {
            query.append("    AND A.FZAVENTAS IN (?)");
        }
        query.append(" ORDER BY MODELO ASC ");
        ArrayList<com.claro.transfer.CatalogoTO> modelos = new ArrayList<com.claro.transfer.CatalogoTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, "A");
                statement.setInt(2, region);
                statement.setString(3, marca);
                if (grupo != -1) {
                    statement.setInt(4, grupo);
                    if (region == 9) {
                        statement.setString(5, "TODOS");
                        if (fzaVentas != null) {
                            statement.setString(6, fzaVentas);
                        } else {
                            statement.setString(6, "TELCEL");
                        }
                    } else {
                        statement.setString(5, "TELCEL");
                    }
                } else if (region == 9) {
                    statement.setString(4, "TODOS");
                    if (fzaVentas != null) {
                        statement.setString(5, fzaVentas);
                    } else {
                        statement.setString(5, "TELCEL");
                    }
                } else {
                    statement.setString(4, "TELCEL");
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    com.claro.transfer.CatalogoTO catalogoTO = new com.claro.transfer.CatalogoTO();
                    catalogoTO.setDescripcion(resultSet.getString(1));
                    modelos.add(catalogoTO);
                }
                if (modelos.size() <= 0) {
                    throw new CAException(1, "No existen MODELOS que mostrar");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getModelos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getModelos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_17) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_18) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var12_19) {}
            }
        }
        return modelos;
    }

    private ArrayList<ProductosTO> obtienePromosAmigoChip(String tecnologia, String lPtsDisp, String lPtsExact, String lPorcentaje, int region, double costoPuntos, String cacsFzaVenta, String todosFzaVenta, PromocionCaTO gapCaTO) throws CAException {
        String sPorcentaje = lPorcentaje;
        BigDecimal fIVA = new BigDecimal(sPorcentaje);
        BigDecimal precio = new BigDecimal(0.0);
        BigDecimal dPuntosMax = new BigDecimal(0.0);
        BigDecimal valorPuntos = new BigDecimal(0.0);
        BigDecimal precioIVA = new BigDecimal(0.0);
        ArrayList<ProductosTO> productos = null;
        ArrayList<ProductosTO> productosAmigoChip = new ArrayList<ProductosTO>();
        if (Long.parseLong(lPtsExact) > Long.parseLong(lPtsDisp)) {
            throw new CAException(-1, "Favor de capturar un valor en puntos menor o igual al numero de puntos disponibles que tiene la linea.");
        }
        if (Long.parseLong(lPtsExact) < 0) {
            throw new CAException(-1, "Favor de capturar un valor en puntos positivo.");
        }
        productos = region != 9 ? this.listaPromociones(region, 0, null, 1084, cacsFzaVenta, cacsFzaVenta, null, null) : this.listaPromociones(region, 0, null, 1178, cacsFzaVenta, todosFzaVenta, null, null);
        if (gapCaTO == null) {
            gapCaTO = new PromocionCaTO();
            gapCaTO.setAplicaPromoGap("NO");
            gapCaTO.setBonoDescuento("NO");
            gapCaTO.setProductoM2K("NO");
            gapCaTO.setNombrePromocion("");
        } else {
            gapCaTO.setAplicaPromoGap("SI");
        }
        Iterator<ProductosTO> list = productos.iterator();
        while (list.hasNext()) {
            ProductosTO productosAChip = new ProductosTO();
            productosAChip = list.next();
            dPuntosMax = productosAChip.getPrecioLista().divide(new BigDecimal(costoPuntos), 3, 4);
            valorPuntos = dPuntosMax.compareTo(BigDecimal.valueOf(Long.parseLong(lPtsExact))) < 0 ? dPuntosMax.setScale(0, 4) : BigDecimal.valueOf(Long.parseLong(lPtsExact)).setScale(0, 4);
            precioIVA = dPuntosMax.subtract(BigDecimal.valueOf(Long.parseLong(lPtsExact)));
            precioIVA = precioIVA.multiply(new BigDecimal(costoPuntos)).setScale(3, 4);
            precio = precioIVA.divide(fIVA, 3);
            if (precioIVA.compareTo(BigDecimal.valueOf(0)) < 0) {
                precioIVA = new BigDecimal(0.0);
                precio = new BigDecimal(0.0);
            }
            productosAChip.setPuntos(lPtsExact);
            productosAChip.setPtosARedimir(Long.parseLong(String.valueOf(valorPuntos)));
            productosAChip.setPrecio(precio.setScale(2, 4));
            productosAChip.setPrecioBD(productosAChip.getPrecio().setScale(2, 4));
            productosAChip.setPrecioIva(precioIVA.setScale(2, 4));
            productosAChip.setDescuento(productosAChip.getDescuento().setScale(2, 4));
            productosAChip.setTipoPromocion("AP");
            productosAChip.setAplicaPromocionGap(gapCaTO.getAplicaPromoGap());
            productosAChip.setNombrePromocionGap(gapCaTO.getNombrePromocion());
            productosAChip.setBonoDescuentoGap(gapCaTO.getBonoDescuento());
            productosAChip.setProductoM2KGap(gapCaTO.getProductoM2K());
            productosAChip.setIdPromocionGap(gapCaTO.getIdPromocion());
            productosAChip.setIdPromocionGapCA(gapCaTO.getIdPromocionCA());
            productosAChip.setVerPromocionGap(gapCaTO.getVersionPromocion());
            productosAmigoChip.add(productosAChip);
        }
        return productosAmigoChip;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Lifted jumps to return sites
     */
    public ArrayList<ProductosTO> consultaProductos(String ptsExactos, String marca, String modelo, TelefonoTO telefonoTO, UsuarioTO usuarioTO, String tipoRed, String formaRed, String planNuevo, double costoPuntos, String cacsFzaVenta, String todosFzaVenta, String fzaVentasDistribuidores, int mesAdendum, int diaAdendum, int diasMesAdendum, int adendumNuevo, String endpointGap) throws CAException {
        try {
            boolean aplicaSiebel;
			Object gapCaTO;
			block27 : {
                Long nPtsaRedimir = Long.parseLong(ptsExactos);
                DescuentoTO descuentoTO = new DescuentoTO();
                Object valoracionGapTO = null;
                Object infoPromocionGapTO = null;
                gapCaTO = null;
                aplicaSiebel = false;
                Long nPtsDisponibles = Long.parseLong(Integer.toString(telefonoTO.getPuntosTO().getPtosTotalesTemp()));
                if (nPtsDisponibles < nPtsaRedimir) {
                    throw new CAException(-1, "El numero de puntos capturado debe ser menor o igual al numero de puntos disponibles para que la redencion proceda.");
                }
                if (nPtsaRedimir < 0) {
                    throw new CAException(-1, "El numero de puntos capturado debe ser positivo.");
                }
                if (tipoRed.trim().equals("ACA")) {
                    return this.obtienePromosAmigoChip(telefonoTO.getTecnologia(), Integer.toString(telefonoTO.getPuntosTO().getPtosTotalesTemp()), ptsExactos, usuarioTO.getPuntoVentaTO().getPorcentajeIva(), telefonoTO.getRegion(), costoPuntos, cacsFzaVenta, todosFzaVenta, (PromocionCaTO) gapCaTO);
                }
                if (tipoRed.trim().equals("T3G")) {
                    return this.obtienePromosTarjetas3G(ptsExactos, marca, modelo, Integer.toString(telefonoTO.getPuntosTO().getPtosTotalesTemp()), usuarioTO.getPuntoVentaTO().getPorcentajeIva(), telefonoTO.getRegion(), costoPuntos, cacsFzaVenta, todosFzaVenta, (PromocionCaTO) gapCaTO);
                }
                if (telefonoTO.getRegion() == 9 && telefonoTO.getMobileTO().getStatus().trim().equals("SU") && telefonoTO.getMobileTO().getMotivo().trim().equals("ROEXT") && fzaVentasDistribuidores == null) {
                    String fechaSuspension = telefonoTO.getMobileTO().getFechaSuspension();
                    this.aplicaBonoRoext(fechaSuspension, telefonoTO.getCuenta().trim(), telefonoTO.getSecuencia(), telefonoTO.getMobileTO().getPlanM2K().trim(), descuentoTO);
                }
                String sValidaPlanNuevoBono;
				if (telefonoTO.getRegion() == 9 && telefonoTO.getMobileTO().getStatus().trim().equals("AC") && fzaVentasDistribuidores == null && (sValidaPlanNuevoBono = this.validaPlanBonoAltoValor(planNuevo.trim(), telefonoTO.getRegion())) != null && sValidaPlanNuevoBono.trim().equals("1")) {
                    this.aplicaBonoAltoValor(telefonoTO.getCuenta().trim(), Integer.parseInt(telefonoTO.getSecuencia()), telefonoTO.getRegion(), telefonoTO.getMobileTO().getSPromFacturaAV(), adendumNuevo, descuentoTO);
                }
                if (telefonoTO.isAceptaBonoInbursa()) {
                    this.aplicaBonoInbursa(marca, modelo, planNuevo, descuentoTO);
                }
                if ((tipoRed.trim().equals("CON") || tipoRed.trim().endsWith("CAREG")) && telefonoTO.getMobileTO().getStatus().trim().equals("AC")) {
                    try {
                        Catalogo properties = new Catalogo();
                        properties.setTabla("propiedades");
                        properties.cargaCatalogo();
                        aplicaSiebel = new Boolean(properties.getPropiedad("aplica.promo.siebel"));
                        if (aplicaSiebel) {
                            ClientePromocionesAcr promosACR = new ClientePromocionesAcr();
                            try {
                                gapCaTO = promosACR.consultaPromocionACR(properties.getPropiedad("endpoint.obtiene.promo.siebel"), telefonoTO.getTelefono(), telefonoTO.getRegion(), marca, modelo, planNuevo);
                                if (gapCaTO != null) {
                                    descuentoTO.setBonoDescuentoPromocion(((PromocionCaTO) gapCaTO).getCantidadDescuento());
                                    descuentoTO.setAplicaDescuentoPromocion(1);
                                } else {
                                    //** GOTO lbl59
                                }
                            }
                            catch (Exception e) {
                                System.out.println(e.getMessage());
                                gapCaTO = null;
                            }
                            break block27;
                        }
                        valoracionGapTO = this.consultaValoracionGap(endpointGap, telefonoTO.getTelefono(), usuarioTO.getIdUsuario());
                        if (valoracionGapTO != null && ((ValoracionGapTO) valoracionGapTO).getPromocionesList() != null) {
                            infoPromocionGapTO = this.consutaPromocionGapCa(((ValoracionGapTO) valoracionGapTO).getPromocionesList());
                        }
                        if (infoPromocionGapTO != null && (gapCaTO = this.consultasGapDAO.consultaPromocionCA((InfoPromocionGapTO) infoPromocionGapTO)) != null) {
                            if (this.validaCondicionesGap((PromocionCaTO) gapCaTO, planNuevo, telefonoTO.getMobileTO(), telefonoTO.getRegion(), adendumNuevo, marca, modelo)) {
                                ((InfoPromocionGapTO) gapCaTO).setNombrePromocion(((InfoPromocionGapTO) infoPromocionGapTO).getNombrePromocion());
                                ((InfoPromocionGapTO) gapCaTO).setAplicaEp(((InfoPromocionGapTO) infoPromocionGapTO).getAplicaEp());
                                if (((PromocionCaTO) gapCaTO).getBonoDescuento() != null && "SI".equals(((PromocionCaTO) gapCaTO).getBonoDescuento())) {
                                    descuentoTO.setBonoDescuentoPromocion(((PromocionCaTO) gapCaTO).getCantidadDescuento());
                                    this.aplicaBonoGap(telefonoTO.getCuenta().trim(), Integer.parseInt(telefonoTO.getSecuencia().trim()), telefonoTO.getRegion(), descuentoTO, formaRed.trim());
                                    if (descuentoTO.getAplicaDescuentoPromocion() == 0) {
                                        gapCaTO = null;
                                    }
                                }
                            } else {
                                gapCaTO = null;
                            }
                        }
                    }
                    catch (Exception exception) {
                        this.error.info((Object)"CatalogoDAO.consultaProductos:", (Throwable)exception);
                    }
                }
            }
            DescuentoTO descuentoTO = null;
			if (descuentoTO.getAplicaDescuentoAltoValor() == 1 && descuentoTO.getAplicaDescuentoPromocion() == 1) {
                if (descuentoTO.getBonoDescuentoPromocion().compareTo(descuentoTO.getBonoDescuentoAltoValor()) > 0) {
                    descuentoTO.setAplicaDescuentoAltoValor(0);
                } else {
                    descuentoTO.setAplicaDescuentoPromocion(0);
                    gapCaTO = null;
                }
            }
            BigDecimal fIVA = new BigDecimal(usuarioTO.getPuntoVentaTO().getPorcentajeIva());
            return this.obtieneProductos(tipoRed.trim(), formaRed.trim(), telefonoTO.getRegion(), marca.trim(), modelo.trim(), planNuevo.trim(), Integer.parseInt(telefonoTO.getIdGrupo().trim()), ptsExactos, Integer.toString(telefonoTO.getPuntosTO().getPtosTotalesTemp()), telefonoTO.getMobileTO(), telefonoTO.getBonoEquipo(), fIVA, descuentoTO, costoPuntos, cacsFzaVenta, todosFzaVenta, fzaVentasDistribuidores, mesAdendum, diaAdendum, diasMesAdendum, (PromocionCaTO) gapCaTO, aplicaSiebel);
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.consultaProductos[" + e.toString() + "]");
        }
    }

    private ArrayList<ProductosTO> obtienePromosTarjetas3G(String ptsExactos, String marca, String modelo, String disponibles, String porIVA, int region, double costoPuntos, String cacsFzaVenta, String todosFzaVenta, PromocionCaTO gapCaTO) throws CAException {
        BigDecimal fIVA = new BigDecimal(porIVA);
        BigDecimal precio = new BigDecimal(0.0);
        BigDecimal dPuntosMax = new BigDecimal(0.0);
        BigDecimal valorPuntos = new BigDecimal(0.0);
        BigDecimal precioIVA = new BigDecimal(0.0);
        ArrayList<ProductosTO> productos = null;
        ArrayList<ProductosTO> productosTarjetas3G = new ArrayList<ProductosTO>();
        if (Long.parseLong(ptsExactos) > Long.parseLong(disponibles)) {
            throw new CAException(-1, "Favor de capturar un valor en puntos menor o igual al numero de puntos disponibles que tiene la linea.");
        }
        if (Long.parseLong(ptsExactos) < 0) {
            throw new CAException(-1, "Favor de capturar un valor en puntos positivo.");
        }
        productos = region != 9 ? this.listaPromociones(region, 0, null, 1346, cacsFzaVenta, cacsFzaVenta, marca, modelo) : this.listaPromociones(region, 0, null, 1347, cacsFzaVenta, todosFzaVenta, marca, modelo);
        if (gapCaTO == null) {
            gapCaTO = new PromocionCaTO();
            gapCaTO.setAplicaPromoGap("NO");
            gapCaTO.setBonoDescuento("NO");
            gapCaTO.setProductoM2K("NO");
            gapCaTO.setNombrePromocion("");
        } else {
            gapCaTO.setAplicaPromoGap("SI");
        }
        Iterator<ProductosTO> list = productos.iterator();
        while (list.hasNext()) {
            ProductosTO productosT3G = new ProductosTO();
            productosT3G = list.next();
            dPuntosMax = productosT3G.getPrecioLista().divide(new BigDecimal(costoPuntos), 3, 4);
            if (dPuntosMax.compareTo(BigDecimal.valueOf(Long.parseLong(ptsExactos))) < 0) {
                valorPuntos = dPuntosMax.setScale(0, 4);
                new java.lang.String();
            } else {
                valorPuntos = BigDecimal.valueOf(Long.parseLong(ptsExactos)).setScale(0, 4);
            }
            precioIVA = dPuntosMax.subtract(BigDecimal.valueOf(Long.parseLong(ptsExactos)));
            precioIVA = precioIVA.multiply(new BigDecimal(costoPuntos)).setScale(3, 4);
            precio = precioIVA.divide(fIVA, 3);
            if (precioIVA.compareTo(BigDecimal.valueOf(0)) < 0) {
                precioIVA = new BigDecimal(0);
                precio = new BigDecimal(0);
            }
            productosT3G.setPuntos(ptsExactos);
            productosT3G.setPtosARedimir(Long.parseLong(String.valueOf(valorPuntos)));
            productosT3G.setPrecio(precio.setScale(2, 4));
            productosT3G.setPrecioIva(precioIVA.setScale(2, 4));
            productosT3G.setPrecioBD(productosT3G.getPrecio().setScale(2, 4));
            productosT3G.setDescuento(productosT3G.getDescuento().setScale(2, 4));
            productosT3G.setAplicaPromocionGap(gapCaTO.getAplicaPromoGap());
            productosT3G.setNombrePromocionGap(gapCaTO.getNombrePromocion());
            productosT3G.setBonoDescuentoGap(gapCaTO.getBonoDescuento());
            productosT3G.setProductoM2KGap(gapCaTO.getProductoM2K());
            productosT3G.setIdPromocionGap(gapCaTO.getIdPromocion());
            productosT3G.setIdPromocionGapCA(gapCaTO.getIdPromocionCA());
            productosT3G.setVerPromocionGap(gapCaTO.getVersionPromocion());
            productosTarjetas3G.add(productosT3G);
        }
        return productosTarjetas3G;
    }

    public void aplicaBonoRoext(String fechaSuspension, String cuenta, String secuencia, String plan, DescuentoTO descuentoTO) throws CAException {
    	PreparedStatement statement = null;
    	PreparedStatement statementDesc = null;
        ResultSet resultSet = null;
        ResultSet resultDescuento = null;
        Connection connection = null;
        String fechaBono = null;
        long difDias = 0;
        String fechahoy = Constantes.DATEFORMATyyyyMMdd.format(new java.util.Date()).toString();
        StringBuffer sQuery1 = new StringBuffer();
        sQuery1.append(" SELECT DESCUENTO ");
        sQuery1.append("   FROM ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION A, ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS B ");
        sQuery1.append("  WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
        sQuery1.append("    AND IDPLAN = ?");
        StringBuffer sQuery = new StringBuffer();
        sQuery.append(" SELECT FECHAOPER, BONOROEXT ");
        sQuery.append("   FROM  ").append(this.schema_database).append("PTO_TBLREDENCION ");
        sQuery.append("  WHERE CUENTA = ?");
        sQuery.append("    AND SECUENCIA =? ");
        sQuery.append("    AND ESTATUS ='A' AND BONOROEXT > 0 ");
        sQuery.append("    ORDER BY FECHAFOLIO DESC ");
        try {
            try {
                int anio = Integer.parseInt(fechaSuspension.substring(0, 4));
                int mes = Integer.parseInt(fechaSuspension.substring(5, 7)) - 1;
                int dia = Integer.parseInt(fechaSuspension.substring(8, 10));
                int anioH = Integer.parseInt(fechahoy.substring(0, 4));
                int mesH = Integer.parseInt(fechahoy.substring(4, 6)) - 1;
                int diaH = Integer.parseInt(fechahoy.substring(6, 8));
                GregorianCalendar fechasusp = new GregorianCalendar(anio, mes, dia);
                GregorianCalendar fechahoy1 = new GregorianCalendar(anioH, mesH, diaH);
                fechahoy1 = new GregorianCalendar(anioH, mesH, diaH);
                long nDias = Utils.diferenciaEnDias((Calendar)fechasusp, (Calendar)fechahoy1);
                if (nDias > -10) {
                    descuentoTO.setAplicaDescuentoRoext(0);
                } else {
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    statement = connection.prepareStatement(sQuery.toString());
                    statement.setString(1, cuenta);
                    statement.setString(2, secuencia);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        fechaBono = resultSet.getDate("FECHAOPER").toString();
                        descuentoTO.setNumBonosRoext(resultSet.getInt("BONOROEXT"));
                        if (fechaBono != null) {
                            int anioA = Integer.parseInt(fechaBono.substring(0, 4));
                            int mesA = Integer.parseInt(fechaBono.substring(5, 7)) - 1;
                            int diaA = Integer.parseInt(fechaBono.substring(8, 10));
                            GregorianCalendar fechaasig = new GregorianCalendar(anioA, mesA, diaA);
                            difDias = Utils.diferenciaEnDias((Calendar)fechaasig, (Calendar)fechahoy1);
                        }
                        if (descuentoTO.getNumBonosRoext() == 1) {
                            if (difDias > -365) {
                                descuentoTO.setAplicaDescuentoRoext(0);
                            } else {
                                descuentoTO.setAplicaDescuentoRoext(1);
                            }
                        } else if (descuentoTO.getNumBonosRoext() > 1) {
                            if (difDias > -730) {
                                descuentoTO.setAplicaDescuentoRoext(0);
                            } else {
                                descuentoTO.setAplicaDescuentoRoext(1);
                            }
                        } else if (descuentoTO.getNumBonosRoext() < 1) {
                            descuentoTO.setAplicaDescuentoRoext(1);
                        }
                    } else {
                        descuentoTO.setAplicaDescuentoRoext(1);
                    }
                    if (descuentoTO.getAplicaDescuentoRoext() == 1) {
                        statementDesc = connection.prepareStatement(sQuery1.toString());
                        statementDesc.setString(1, plan);
                        resultDescuento = statementDesc.executeQuery();
                        if (resultDescuento.next()) {
                            descuentoTO.setBonoDescuentoRoext(resultDescuento.getBigDecimal("DESCUENTO"));
                        }
                        if (descuentoTO.getBonoDescuentoRoext().compareTo(BigDecimal.valueOf(0)) > 0) {
                            descuentoTO.setAplicaDescuentoRoext(1);
                        } else {
                            descuentoTO.setAplicaDescuentoRoext(0);
                        }
                    } else {
                        descuentoTO.setAplicaDescuentoRoext(0);
                    }
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.aplicaBonoRoext[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.aplicaBonoRoext[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var32_37) {}
            }
            if (resultDescuento != null) {
                try {
                    resultDescuento.close();
                    resultDescuento = null;
                }
                catch (Exception var32_38) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var32_39) {}
            }
            if (statementDesc != null) {
                try {
                    statementDesc.close();
                    statementDesc = null;
                }
                catch (Exception var32_40) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var32_41) {}
            }
        }
    }

    public String validaPlanBonoAltoValor(String plan, int region) throws CAException {
    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer sQuery = new StringBuffer();
        sQuery.append(" SELECT DESCUENTOALTOVALOR ");
        sQuery.append(" FROM  ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION A,  ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS B ");
        sQuery.append(" WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
        sQuery.append(" AND B.IDREGION=? ");
        sQuery.append(" AND IDPLAN = ?");
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(sQuery.toString(), 1004, 1007);
            statement.setInt(1, region);
            statement.setString(2, plan);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String string = resultSet.getString("DESCUENTOALTOVALOR");
                return string;
            }
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.validaPlanBonoAltoValor[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.validaPlanBonoAltoValor[" + e.toString() + "]", e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_11) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_12) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_13) {}
            }
        }
        return null;
    }

    public void aplicaBonoAltoValor(String cuenta, int secuencia, int region, String promedio, int adendumNuevo, DescuentoTO descuentoTO) throws CAException {
        String fechaBono = null;
        long difDias = 0;
        BigDecimal promedioFactura = new BigDecimal(0.0);
        if (!("".equals(promedio) || promedio == null)) {
            promedioFactura = new BigDecimal(promedio);
        }
        String fechahoy = Constantes.DATEFORMATyyyyMMdd.format(new java.util.Date()).toString();
        int anioH = Integer.parseInt(fechahoy.substring(0, 4));
        int mesH = Integer.parseInt(fechahoy.substring(4, 6)) - 1;
        int diaH = Integer.parseInt(fechahoy.substring(6, 8));
        GregorianCalendar fechahoy1 = new GregorianCalendar(anioH, mesH, diaH);
        PreparedStatement statementBonoAlto = null;
        PreparedStatement statementDescuento = null;
        ResultSet resultSetBonoAlto = null;
        ResultSet resultSetDescuento = null;
        Connection connection = null;
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT FECHAOPER, BONOALTOVALOR ");
                sQuery.append("   FROM  ").append(this.schema_database).append("PTO_TBLREDENCION ");
                sQuery.append("  WHERE CUENTA = ?");
                sQuery.append("    AND SECUENCIA = ?");
                sQuery.append("    AND ESTATUS ='A' AND BONOALTOVALOR > 0 ");
                sQuery.append("    ORDER BY FECHAFOLIO DESC ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementBonoAlto = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                statementBonoAlto.setString(1, cuenta);
                statementBonoAlto.setInt(2, secuencia);
                resultSetBonoAlto = statementBonoAlto.executeQuery();
                if (resultSetBonoAlto.next()) {
                    fechaBono = resultSetBonoAlto.getDate("FECHAOPER").toString();
                    descuentoTO.setNumBonosAltoValor(resultSetBonoAlto.getInt("BONOALTOVALOR"));
                    if (fechaBono != null) {
                        int anioA = Integer.parseInt(fechaBono.substring(0, 4));
                        int mesA = Integer.parseInt(fechaBono.substring(5, 7)) - 1;
                        int diaA = Integer.parseInt(fechaBono.substring(8, 10));
                        GregorianCalendar fechaasig = new GregorianCalendar(anioA, mesA, diaA);
                        difDias = Utils.diferenciaEnDias((Calendar)fechaasig, (Calendar)fechahoy1);
                    }
                    if (descuentoTO.getNumBonosAltoValor() >= 1) {
                        if (difDias > -365) {
                            descuentoTO.setAplicaDescuentoAltoValor(0);
                        } else {
                            descuentoTO.setAplicaDescuentoAltoValor(1);
                        }
                    } else if (descuentoTO.getNumBonosAltoValor() < 1) {
                        descuentoTO.setAplicaDescuentoAltoValor(1);
                    }
                } else {
                    descuentoTO.setAplicaDescuentoAltoValor(1);
                }
                if (descuentoTO.getAplicaDescuentoAltoValor() == 1) {
                    StringBuffer sQuery1 = new StringBuffer();
                    sQuery1.append(" SELECT DESCUENTO ");
                    sQuery1.append("  FROM  ").append(this.schema_database).append("PTO_CTLDESCUENTOS ");
                    sQuery1.append("  WHERE IDREGION = ? ");
                    sQuery1.append("  AND  ? >= RANGOINF");
                    sQuery1.append("  AND ? <= RANGOSUP");
                    sQuery1.append("  AND PLAZORENOVACION = ?");
                    statementDescuento = connection.prepareStatement(sQuery1.toString(), 1004, 1007);
                    statementDescuento.setInt(1, region);
                    statementDescuento.setBigDecimal(2, promedioFactura);
                    statementDescuento.setBigDecimal(3, promedioFactura);
                    statementDescuento.setInt(4, adendumNuevo);
                    resultSetDescuento = statementDescuento.executeQuery();
                    if (resultSetDescuento.next()) {
                        BigDecimal bonoDescuentoAltoValor = resultSetDescuento.getBigDecimal("DESCUENTO");
                        descuentoTO.setBonoDescuentoAltoValor(bonoDescuentoAltoValor);
                        if (bonoDescuentoAltoValor.compareTo(BigDecimal.valueOf(0)) > 0) {
                            descuentoTO.setAplicaDescuentoAltoValor(1);
                        } else {
                            descuentoTO.setAplicaDescuentoAltoValor(0);
                        }
                    } else {
                        descuentoTO.setAplicaDescuentoAltoValor(0);
                    }
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.validaPlanBonoAltoValor[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.validaPlanBonoAltoValor[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetBonoAlto != null) {
                try {
                    resultSetBonoAlto.close();
                    resultSetBonoAlto = null;
                }
                catch (Exception var27_35) {}
            }
            if (resultSetDescuento != null) {
                try {
                    resultSetDescuento.close();
                    resultSetDescuento = null;
                }
                catch (Exception var27_36) {}
            }
            if (statementBonoAlto != null) {
                try {
                    statementBonoAlto.close();
                    statementBonoAlto = null;
                }
                catch (Exception var27_37) {}
            }
            if (statementDescuento != null) {
                try {
                    statementDescuento.close();
                    statementDescuento = null;
                }
                catch (Exception var27_38) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var27_39) {}
            }
        }
    }

    private void aplicaBonoGap(String cuenta, int secuencia, int region, DescuentoTO descuentoTO, String formaRed) throws CAException {
        String fechaBono = null;
        long difDias = 0;
        String fechahoy = Constantes.DATEFORMATyyyyMMdd.format(new java.util.Date()).toString();
        int anioH = Integer.parseInt(fechahoy.substring(0, 4));
        int mesH = Integer.parseInt(fechahoy.substring(4, 6)) - 1;
        int diaH = Integer.parseInt(fechahoy.substring(6, 8));
        GregorianCalendar fechahoy1 = new GregorianCalendar(anioH, mesH, diaH);
        PreparedStatement stmtBonoGapAplicado = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT FECHAOPER, BONOALTOVALOR, BONOGAP ");
                sQuery.append("   FROM  ").append(this.schema_database).append("PTO_TBLREDENCION ");
                sQuery.append("  WHERE CUENTA = ?");
                sQuery.append("    AND SECUENCIA = ?");
                sQuery.append("    AND ESTATUS ='A' AND (BONOGAP > 0 or BONOALTOVALOR > 0)");
                sQuery.append("    ORDER BY FECHAFOLIO DESC ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmtBonoGapAplicado = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                stmtBonoGapAplicado.setString(1, cuenta);
                stmtBonoGapAplicado.setInt(2, secuencia);
                resultSet = stmtBonoGapAplicado.executeQuery();
                if (resultSet.next()) {
                    fechaBono = resultSet.getDate("FECHAOPER").toString();
                    descuentoTO.setNumBonosAltoValor(resultSet.getInt("BONOALTOVALOR"));
                    descuentoTO.setNumBonosGap(resultSet.getInt("BONOGAP"));
                    if (fechaBono != null) {
                        int anioBono = Integer.parseInt(fechaBono.substring(0, 4));
                        int mesBono = Integer.parseInt(fechaBono.substring(5, 7)) - 1;
                        int diaBono = Integer.parseInt(fechaBono.substring(8, 10));
                        GregorianCalendar fechaasig = new GregorianCalendar(anioBono, mesBono, diaBono);
                        difDias = Utils.diferenciaEnDias((Calendar)fechaasig, (Calendar)fechahoy1);
                    }
                    if (descuentoTO.getNumBonosGap() >= 1 || descuentoTO.getNumBonosAltoValor() >= 1) {
                        if (difDias > -365 && ("PM".equals(formaRed) || "PC".equals(formaRed))) {
                            descuentoTO.setAplicaDescuentoPromocion(1);
                        } else if (difDias < -365) {
                            descuentoTO.setAplicaDescuentoPromocion(1);
                        } else {
                            descuentoTO.setAplicaDescuentoPromocion(0);
                        }
                    } else {
                        descuentoTO.setAplicaDescuentoPromocion(1);
                    }
                } else {
                    descuentoTO.setAplicaDescuentoPromocion(1);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.validaPlanBonoAltoValor[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.validaPlanBonoAltoValor[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var23_27) {}
            }
            if (stmtBonoGapAplicado != null) {
                try {
                    stmtBonoGapAplicado.close();
                    stmtBonoGapAplicado = null;
                }
                catch (Exception var23_28) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var23_29) {}
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Lifted jumps to return sites
     */
    private ArrayList<ProductosTO> obtieneProductos(String tipoRed, String formaRed, int region, String marca, String modelo, String planNvo, int idGrupo, String ptosExactos, String ptosDisponibles, MobileTO mobileTO, int bonoEquipo, BigDecimal fIVA, DescuentoTO descuentoTO, double costoPuntos, String cacsFzaVenta, String todosFzaVenta, String fzaVentasDistribuidores, int mesAdendum, int diaAdendum, int diasMesAdendum, PromocionCaTO gapCaTO, boolean aplicaSiebel) throws CAException {
        boolean aplicaDescuentoAVTmp = false;
        boolean aplicaDescuentoRTmp = false;
        boolean aplicaDescuentoGapTmp = false;
        PreparedStatement statementProductos = null;
        PreparedStatement statementPrecio = null;
        ResultSet resultSetProductos = null;
        ResultSet resultIva = null;
        Connection connection = null;
        ArrayList<ProductosTO> productos = new ArrayList<ProductosTO>();
        /*
        try {
            Object equipo;
			CatalogoTO[] marcaModelos;
			int i;
			try {
                if (gapCaTO == null) {
                    gapCaTO = new PromocionCaTO();
                    gapCaTO.setAplicaPromoGap("NO");
                    gapCaTO.setBonoDescuento("NO");
                    gapCaTO.setProductoM2K("NO");
                    gapCaTO.setNombrePromocion("");
                } else {
                    gapCaTO.setAplicaPromoGap("SI");
                }
                StringBuffer query = new StringBuffer("SELECT DISTINCT A.IDPRODUCTO, A.MARCA, A.MODELO,");
                query.append("\tA.PRECIOACTIVACION,  A.PRECIOLISTA,");
                query.append(" A.DESCRIPCION, A.URL, A.TECNOLOGIA,");
                query.append(" B.TIPOPROMOCION, A.INDICADOR ");
                query.append(" FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES A , ");
                query.append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B");
                if (!"SIN".equals(tipoRed.trim())) {
                    query.append(" ,").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C");
                    query.append("  WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
                    query.append("\t AND B.IDGRUPOPROMOCION = C.IDGRUPOPROMOCION ");
                    query.append("\t AND A.IDREGION = C.IDREGION ");
                    query.append("  AND C.IDPLAN ='").append(planNvo).append("'");
                    query.append("  AND A.ADENDUM = C.ADENDUM ");
                } else {
                    query.append("  WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
                }
                query.append(" AND A.IDGRUPOPROMOCION=").append(idGrupo);
                query.append(" AND A.IDREGION = ").append(region);
                if (region == 9) {
                    if (fzaVentasDistribuidores == null) {
                        query.append(" AND A.FZAVENTAS IN ('").append(cacsFzaVenta).append("'");
                        query.append(" ,'").append(todosFzaVenta).append("')");
                    } else {
                        query.append(" AND A.FZAVENTAS IN ('").append(fzaVentasDistribuidores).append("'");
                        query.append(" ,'").append(todosFzaVenta).append("')");
                    }
                } else {
                    query.append(" AND A.FZAVENTAS IN ('").append(cacsFzaVenta).append("')");
                }
                query.append(" AND A.ESTATUS = 'A' AND A.MARCA ='");
                query.append(marca).append("' ");
                if (!"TODOS".equals(modelo)) {
                    query.append("  AND MODELO ='").append(modelo).append("'");
                }
                query.append(" ORDER BY A.MARCA, A.MODELO");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementProductos = (PreparedStatement) connection.createStatement(1004, 1007);
                resultSetProductos = ((Statement) statementProductos).executeQuery(query.toString());
                BigDecimal precioLista = new BigDecimal(0.0);
                BigDecimal precioActivacion = new BigDecimal(0.0);
                BigDecimal precio = new BigDecimal(0.0);
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT PRECIOIVA");
                sQuery.append("   FROM  ").append(this.schema_database).append("PTO_CTLFACTORPRECIO");
                sQuery.append("  WHERE IDPRODUCTO =?");
                sQuery.append("    AND IDREGION =?");
                statementPrecio = ((Connection) connection).prepareStatement(sQuery.toString(), 1004, 1007);
                block28 : do {
                    if (!((ResultSet) resultSetProductos).next()) {
                        return productos;
                    }
                    ProductosTO productosTO = new ProductosTO();
                    productosTO.setMaterial(((ResultSet) resultSetProductos).getString("IDPRODUCTO"));
                    productosTO.setDescripcion(((ResultSet) resultSetProductos).getString("DESCRIPCION"));
                    productosTO.setMarca(((ResultSet) resultSetProductos).getString("MARCA"));
                    productosTO.setModelo(((ResultSet) resultSetProductos).getString("MODELO"));
                    productosTO.setTipoPromocion(((ResultSet) resultSetProductos).getString("TIPOPROMOCION"));
                    productosTO.setUrl(((ResultSet) resultSetProductos).getString("URL"));
                    productosTO.setTecnologia(((ResultSet) resultSetProductos).getString("TECNOLOGIA"));
                    productosTO.setIndicador(((ResultSet) resultSetProductos).getString("INDICADOR"));
                    long nPtsaRedimir = Long.parseLong(ptosExactos);
                    precioLista = ((ResultSet) resultSetProductos).getBigDecimal("PRECIOLISTA");
                    precioActivacion = ((ResultSet) resultSetProductos).getBigDecimal("PRECIOACTIVACION");
                    BigDecimal descPorPtos = new BigDecimal((double)nPtsaRedimir * costoPuntos);
                    BigDecimal precioIVA = new BigDecimal(0.0).setScale(2);
                    descuentoTO.setDescuentoUtilizado(new BigDecimal(0.0).setScale(2));
                    BigDecimal subsidio = new BigDecimal(0.0);
                    descuentoTO.setBonoDescuentoInbursa(new BigDecimal(0.0).setScale(2));
                    descuentoTO.setBonoDescuentoMarca(new BigDecimal(0.0).setScale(2));
                    descuentoTO.setDescuentoInbursaRestante(new BigDecimal(0.0).setScale(2));
                    descuentoTO.setDescuentoMarcaRestante(new BigDecimal(0.0).setScale(2));
                    productosTO.setPtosARedimir(nPtsaRedimir);
                    if (formaRed.equals("PR") || formaRed.equals("PC")) {
                        if (region == 9 && mesAdendum + Integer.parseInt(mobileTO.getMesesCareg()) < 4 || region != 9 && mesAdendum + Integer.parseInt(mobileTO.getMesesCareg()) < 6) {
                            precio = precioLista;
                        } else {
                            BigDecimal Y = new BigDecimal(this.utilizaProrrateo(mobileTO.getMesesCareg().trim(), mobileTO.getAddCareg().trim(), mesAdendum, diaAdendum, diasMesAdendum, formaRed, mobileTO.getAddM2K().trim()));
                            subsidio = precioLista.subtract(precioActivacion);
                            BigDecimal descPorProrrateo = subsidio.multiply(Y);
                            precio = precioLista.subtract(descPorProrrateo);
                        }
                    } else if (formaRed.equals("PM")) {
                        if (bonoEquipo == 100000) {
                            if (precioActivacion.compareTo(BigDecimal.valueOf(0)) > 0) {
                                productosTO.setSobBonoEquipo(0);
                                precio = precioActivacion;
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(2100)) > 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) == 0) {
                                productosTO.setSobBonoEquipo(0);
                                precio = new BigDecimal(0);
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(2100)) < 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) == 0) {
                                productosTO.setSobBonoEquipo((int)Math.floor((double)(2100 - precioLista.intValue()) / costoPuntos));
                                precio = new BigDecimal(0);
                            }
                        } else if (bonoEquipo > 100000) {
                            if (precioLista.compareTo(BigDecimal.valueOf(4200)) >= 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) == 0) {
                                productosTO.setSobBonoEquipo(0);
                                precio = new BigDecimal(0);
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(4200)) >= 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) > 0) {
                                subsidio = precioLista.subtract(precioActivacion);
                                productosTO.setSobBonoEquipo((int)Math.floor(4200 - subsidio.intValue()));
                                precio = precioActivacion.subtract(BigDecimal.valueOf(productosTO.getSobBonoEquipo()));
                                productosTO.setSobBonoEquipo(0);
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(2100)) < 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) == 0) {
                                productosTO.setSobBonoEquipo((int)Math.floor((double)(4200 - precioLista.intValue()) / costoPuntos));
                                precio = new BigDecimal(0);
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(2100)) < 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) > 0) {
                                productosTO.setSobBonoEquipo((int)Math.floor((double)(4200 - precioLista.intValue()) / costoPuntos));
                                precio = precioActivacion;
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(4200)) < 0 && precioLista.compareTo(BigDecimal.valueOf(2100)) > 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) == 0) {
                                productosTO.setSobBonoEquipo((int)Math.floor((double)(4200 - precioLista.intValue()) / costoPuntos));
                                precio = new BigDecimal(0);
                            }
                            if (precioLista.compareTo(BigDecimal.valueOf(4200)) < 0 && precioLista.compareTo(BigDecimal.valueOf(2100)) > 0 && precioActivacion.compareTo(BigDecimal.valueOf(0)) > 0) {
                                subsidio = precioLista.subtract(precioActivacion);
                                precio = descPorPtos.add(BigDecimal.valueOf((int)Math.floor(4200 - subsidio.intValue())));
                                productosTO.setSobBonoEquipo((int)Math.floor((double)precio.subtract(precioActivacion).intValue() / costoPuntos));
                                precio = new BigDecimal(0);
                            }
                        } else {
                            productosTO.setSobBonoEquipo(0);
                            precio = precioActivacion;
                        }
                    } else {
                        precio = precioActivacion;
                        descuentoTO.setAplicaDescuentoAltoValor(0);
                        descuentoTO.setAplicaDescuentoPromocion(0);
                    }
                    productosTO.setPrecioBD(precio);
                    this.obtienePrecioTotal(descPorPtos, productosTO, costoPuntos);
                    productosTO.setPrecioBD(productosTO.getPrecioBD().setScale(2, 4));
                    precioIVA = productosTO.getPrecioBD().multiply(fIVA).setScale(2, 4);
                    BigDecimal precioMinimo = new BigDecimal(0.0);
                    int totBonosRoext = 0;
                    int totBonosAltoValor = 0;
                    int totBonosInbursa = 0;
                    int totBonosGap = 0;
                    if (marca.equals("APPLE") && region == 9) {
                        statementPrecio.clearParameters();
                        statementPrecio.setString(1, ((ResultSet) resultSetProductos).getString("IDPRODUCTO"));
                        statementPrecio.setInt(2, region);
                        resultIva = statementPrecio.executeQuery();
                        if (((ResultSet) resultIva).next()) {
                            precioMinimo = ((ResultSet) resultIva).getBigDecimal("PRECIOIVA").setScale(2, 4);
                            if (precioIVA.compareTo(precioMinimo) < 0) {
                                BigDecimal nPuntosPrecio = productosTO.getPrecioBD().divide(new BigDecimal(costoPuntos), 3);
                                precioIVA = precioMinimo;
                                productosTO.setPrecioBD(precioIVA.divide(fIVA, 3, 4));
                                BigDecimal nPuntosMin = productosTO.getPrecioBD().divide(new BigDecimal(costoPuntos), 3);
                                if ((nPtsaRedimir-=(long)(nPuntosMin.intValue() - nPuntosPrecio.intValue())) < 0) {
                                    nPtsaRedimir = 0;
                                }
                            }
                            BigDecimal precioMenosBono = new BigDecimal(0);
                            if (descuentoTO.getAplicaDescuentoRoext() == 1) {
                                precioMenosBono = productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoRoext()).multiply(fIVA);
                                if (precioMenosBono.compareTo(precioMinimo) < 0) {
                                    descuentoTO.setDescuentoUtilizado(productosTO.getPrecioBD().subtract(precioMinimo.divide(fIVA, 3)));
                                    precioIVA = precioMinimo;
                                    aplicaDescuentoRTmp = true;
                                    if (descuentoTO.getDescuentoUtilizado().compareTo(new BigDecimal(0)) > 0) {
                                        totBonosRoext = descuentoTO.getNumBonosRoext() + 1;
                                    }
                                    descuentoTO.setAplicaDescuentoRoext(0);
                                } else {
                                    descuentoTO.setAplicaDescuentoRoext(1);
                                }
                            }
                            if (descuentoTO.getAplicaDescuentoAltoValor() == 1) {
                                precioMenosBono = productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoAltoValor()).multiply(fIVA);
                                if (precioMenosBono.compareTo(precioMinimo) < 0) {
                                    descuentoTO.setDescuentoUtilizado(productosTO.getPrecioBD().subtract(precioMinimo.divide(fIVA, 3)));
                                    precioIVA = precioMinimo;
                                    aplicaDescuentoAVTmp = true;
                                    if (descuentoTO.getDescuentoUtilizado().compareTo(new BigDecimal(0)) > 0) {
                                        totBonosAltoValor = descuentoTO.getNumBonosAltoValor() + 1;
                                    }
                                    descuentoTO.setAplicaDescuentoAltoValor(0);
                                } else {
                                    descuentoTO.setAplicaDescuentoAltoValor(1);
                                }
                            }
                            if (descuentoTO.getAplicaDescuentoPromocion() == 1) {
                                precioMenosBono = productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoPromocion()).multiply(fIVA);
                                if (precioMenosBono.compareTo(precioMinimo) < 0) {
                                    descuentoTO.setDescuentoUtilizado(productosTO.getPrecioBD().subtract(precioMinimo.divide(fIVA, 3)));
                                    precioIVA = precioMinimo;
                                    aplicaDescuentoGapTmp = true;
                                    if (descuentoTO.getDescuentoUtilizado().compareTo(new BigDecimal(0)) > 0) {
                                        totBonosGap = descuentoTO.getNumBonosGap() + 1;
                                    }
                                    descuentoTO.setAplicaDescuentoPromocion(0);
                                } else {
                                    descuentoTO.setAplicaDescuentoPromocion(1);
                                }
                            }
                        }
                        if (resultIva != null) {
                            try {
                                resultIva.close();
                                resultIva = null;
                            }
                            catch (Exception precioMenosBono) {
                                // empty catch block
                            }
                        }
                    }
                    if (descuentoTO.getAplicaDescuentoInbursa() == 1 && descuentoTO.getDescuentosInbursa() != null) {
                        Object productoInbursa = null;
                        productoInbursa = (ProductosTO)descuentoTO.getDescuentosInbursa().get(productosTO.getModelo());
                        if (productoInbursa == null) {
                            productoInbursa = (ProductosTO)descuentoTO.getDescuentosInbursa().get(planNvo);
                        }
                        if (productoInbursa != null) {
                            descuentoTO.setBonoDescuentoInbursa(((ProductosTO) productoInbursa).getDescuentoInbursa());
                            if (productosTO.getModelo().equals(((MobileTO) productoInbursa).getModelo())) {
                                descuentoTO.setBonoDescuentoMarca(((ProductosTO) productoInbursa).getDescuentoMarca());
                            }
                            if (productosTO.getPrecioBD().compareTo(new BigDecimal(0)) > 0) {
                                if (productosTO.getPrecioBD().compareTo(descuentoTO.getBonoDescuentoInbursa()) > 0) {
                                    BigDecimal descuentoInbursaMarca = descuentoTO.getBonoDescuentoInbursa().add(descuentoTO.getBonoDescuentoMarca());
                                    if (productosTO.getPrecioBD().compareTo(descuentoInbursaMarca) > 0) {
                                        descuentoTO.setBonoDescuentoInbursa(descuentoTO.getBonoDescuentoInbursa().setScale(2, 4));
                                        descuentoTO.setBonoDescuentoMarca(descuentoTO.getBonoDescuentoMarca().setScale(2, 4));
                                        descuentoTO.setDescuentoInbursaRestante(new BigDecimal(0).setScale(2, 4));
                                        descuentoTO.setDescuentoMarcaRestante(new BigDecimal(0).setScale(2, 4));
                                        precioIVA = productosTO.getPrecioBD().subtract(descuentoInbursaMarca).multiply(fIVA).setScale(2, 4);
                                        totBonosInbursa = descuentoTO.getNumBonosInbursa() + 1;
                                    } else {
                                        descuentoTO.setBonoDescuentoInbursa(descuentoTO.getBonoDescuentoInbursa().setScale(2, 4));
                                        descuentoTO.setDescuentoInbursaRestante(new BigDecimal(0).setScale(2, 4));
                                        BigDecimal descuentoMarcaTemp = descuentoTO.getBonoDescuentoMarca();
                                        descuentoTO.setBonoDescuentoMarca(productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoInbursa()).setScale(2, 4));
                                        descuentoTO.setDescuentoMarcaRestante(descuentoMarcaTemp.subtract(descuentoTO.getBonoDescuentoMarca()).setScale(2, 4));
                                        precioIVA = new BigDecimal(0.0).setScale(2, 4);
                                        totBonosInbursa = descuentoTO.getNumBonosInbursa() + 1;
                                    }
                                } else {
                                    BigDecimal descuentoInbursaTemp = descuentoTO.getBonoDescuentoInbursa();
                                    descuentoTO.setBonoDescuentoInbursa(productosTO.getPrecioBD().setScale(2, 4));
                                    descuentoTO.setDescuentoInbursaRestante(descuentoInbursaTemp.subtract(descuentoTO.getBonoDescuentoInbursa()).setScale(2, 4));
                                    descuentoTO.setDescuentoMarcaRestante(descuentoTO.getBonoDescuentoMarca().setScale(2, 4));
                                    descuentoTO.setBonoDescuentoMarca(new BigDecimal(0).setScale(2, 4));
                                    precioIVA = new BigDecimal(0.0).setScale(2, 4);
                                    totBonosInbursa = descuentoTO.getNumBonosInbursa() + 1;
                                }
                            } else {
                                descuentoTO.setDescuentoInbursaRestante(descuentoTO.getBonoDescuentoInbursa().setScale(2, 4));
                                descuentoTO.setDescuentoMarcaRestante(descuentoTO.getBonoDescuentoMarca().setScale(2, 4));
                                descuentoTO.setBonoDescuentoInbursa(new BigDecimal(0).setScale(2, 4));
                                descuentoTO.setBonoDescuentoMarca(new BigDecimal(0).setScale(2, 4));
                            }
                        }
                    }
                    BigDecimal precioDescuento;
					if (descuentoTO.getAplicaDescuentoRoext() == 1) {
                        precioDescuento = productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoInbursa()).subtract(descuentoTO.getBonoDescuentoMarca());
                        if (precioDescuento.compareTo(new BigDecimal(0)) > 0) {
                            if (precioDescuento.compareTo(descuentoTO.getBonoDescuentoRoext()) > 0) {
                                descuentoTO.setDescuentoUtilizado(descuentoTO.getBonoDescuentoRoext().setScale(2, 4));
                                precioIVA = precioDescuento.subtract(descuentoTO.getBonoDescuentoRoext()).multiply(fIVA).setScale(2, 4);
                                totBonosRoext = descuentoTO.getNumBonosRoext() + 1;
                            } else {
                                descuentoTO.setDescuentoUtilizado(precioDescuento);
                                precioIVA = new BigDecimal(0.0).setScale(2, 4);
                                totBonosRoext = descuentoTO.getNumBonosRoext() + 1;
                            }
                        } else {
                            descuentoTO.setDescuentoUtilizado(new BigDecimal(0.0).setScale(2, 4));
                        }
                    }
                    if (descuentoTO.getAplicaDescuentoAltoValor() == 1) {
                        precioDescuento = productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoInbursa()).subtract(descuentoTO.getBonoDescuentoMarca());
                        if (precioDescuento.compareTo(new BigDecimal(0)) > 0) {
                            if (precioDescuento.compareTo(descuentoTO.getBonoDescuentoAltoValor()) > 0) {
                                descuentoTO.setDescuentoUtilizado(descuentoTO.getBonoDescuentoAltoValor().setScale(2, 4));
                                precioIVA = precioDescuento.subtract(descuentoTO.getBonoDescuentoAltoValor()).multiply(fIVA).setScale(2, 4);
                                totBonosAltoValor = descuentoTO.getNumBonosAltoValor() + 1;
                            } else {
                                descuentoTO.setDescuentoUtilizado(precioDescuento);
                                precioIVA = new BigDecimal(0.0).setScale(2, 4);
                                totBonosAltoValor = descuentoTO.getNumBonosAltoValor() + 1;
                            }
                        } else {
                            descuentoTO.setDescuentoUtilizado(new BigDecimal(0.0).setScale(2, 4));
                            productosTO.setAplicaPaqueteSMS("0");
                        }
                    }
                    if (descuentoTO.getAplicaDescuentoPromocion() == 1) {
                        if (productosTO.getPrecioBD().compareTo(new BigDecimal(0)) > 0) {
                            if (productosTO.getPrecioBD().compareTo(descuentoTO.getBonoDescuentoPromocion()) > 0) {
                                descuentoTO.setDescuentoUtilizado(descuentoTO.getBonoDescuentoPromocion().setScale(2, 4));
                                precioIVA = productosTO.getPrecioBD().subtract(descuentoTO.getBonoDescuentoPromocion()).multiply(fIVA).setScale(2, 4);
                                totBonosGap = descuentoTO.getNumBonosGap() + 1;
                            } else {
                                descuentoTO.setDescuentoUtilizado(productosTO.getPrecioBD());
                                precioIVA = new BigDecimal(0.0).setScale(2, 4);
                                totBonosGap = descuentoTO.getNumBonosGap() + 1;
                            }
                        } else {
                            descuentoTO.setDescuentoUtilizado(new BigDecimal(0.0).setScale(2, 4));
                        }
                    }
                    if (aplicaDescuentoAVTmp && descuentoTO.getAplicaDescuentoAltoValor() == 0) {
                        descuentoTO.setAplicaDescuentoAltoValor(1);
                    }
                    if (aplicaDescuentoRTmp && descuentoTO.getAplicaDescuentoRoext() == 0) {
                        descuentoTO.setAplicaDescuentoRoext(1);
                    }
                    if (aplicaDescuentoGapTmp && descuentoTO.getAplicaDescuentoPromocion() == 0) {
                        descuentoTO.setAplicaDescuentoPromocion(1);
                    }
                    productosTO.setPuntos(String.valueOf(nPtsaRedimir));
                    productosTO.setPuntosSobrantes(Integer.toString(productosTO.getSobBonoEquipo()));
                    productosTO.setBonosRoext(totBonosRoext);
                    productosTO.setBonosAltoValor(totBonosAltoValor);
                    productosTO.setBonosGap(totBonosGap);
                    productosTO.setPrecio(productosTO.getPrecioBD());
                    productosTO.setPrecioIva(precioIVA);
                    productosTO.setBonosInbursa(totBonosInbursa);
                    productosTO.setDescuentoInbursa(descuentoTO.getBonoDescuentoInbursa());
                    productosTO.setDescuentoMarca(descuentoTO.getBonoDescuentoMarca());
                    productosTO.setDescuentoInbursaRestante(descuentoTO.getDescuentoInbursaRestante());
                    productosTO.setDescuentoMarcaRestante(descuentoTO.getDescuentoMarcaRestante());
                    if (!aplicaSiebel)  
                    	//goto lbl305;
                    equipo = null;
                    marcaModelos = gapCaTO.getEquipos();
                    productosTO.setDescuento(new BigDecimal("0.0"));
                    i = 0;
                    //** GOTO lbl327
lbl305: // 1 sources:
                    productosTO.setDescuento(descuentoTO.getDescuentoUtilizado());
lbl306: // 4 sources:
                    do {
                        productosTO.setAplicaPromocionGap(gapCaTO.getAplicaPromoGap());
                        productosTO.setBonoDescuentoGap(gapCaTO.getBonoDescuento());
                        productosTO.setProductoM2KGap(gapCaTO.getProductoM2K());
                        productosTO.setNombrePromocionGap(gapCaTO.getNombrePromocion());
                        productosTO.setIdPromocionGap(gapCaTO.getIdPromocion());
                        productosTO.setIdPromocionGapCA(gapCaTO.getIdPromocionCA());
                        productosTO.setVerPromocionGap(gapCaTO.getVersionPromocion());
                        productosTO.setAplicaEP(gapCaTO.getAplicaEp());
                        productos.add(productosTO);
                        continue block28;
                        break;
                    } while (true);
                    break;
                } while (true);
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtieneProductos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.obtieneProductos[" + e.toString() + "]", e);
            }
lbl-1000: // 1 sources:
            {
                equipo = marcaModelos[i];
                MobileTO productosTO;
				if (!productosTO.getMarca().equals(((CatalogoTO) equipo).getId()) || !productosTO.getModelo().equals(((MotivoTO) equipo).getDescripcion())) //** GOTO lbl325
                //productosTO.setDescuento(descuentoTO.getDescuentoUtilizado());
                //** GOTO lbl306
lbl325: // 1 sources:
                //productosTO.setDescuento(new BigDecimal("0.0"));
                ++i;
lbl327: // 2 sources:
                if (marcaModelos == null) //** GOTO lbl306
                 while (i < marcaModelos.length)
                	 ;
            }
lbl329: // 1 sources:
             continue;
        }
        finally {
            if (resultSetProductos != null) {
                try {
                    resultSetProductos.close();
                    resultSetProductos = null;
                }
                catch (Exception var53_61) {}
            }
            if (resultIva != null) {
                try {
                    resultIva.close();
                    resultIva = null;
                }
                catch (Exception var53_62) {}
            }
            if (statementProductos != null) {
                try {
                    statementProductos.close();
                    statementProductos = null;
                }
                catch (Exception var53_63) {}
            }
            if (statementPrecio != null) {
                try {
                    statementPrecio.close();
                    statementPrecio = null;
                }
                catch (Exception var53_64) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var53_65) {}
            }
        }*/
		return productos;
    }

    private ValoracionGapTO consultaValoracionGap(String endpointGap, String telefono, String idUsuario) throws CAException {
        ValoracionGapTO valoracionGapTO = null;
        boolean existenPromociones = false;
        RespuestaConsultasGap respuestaGap = new RespuestaConsultasGap("SolicitudInformacionValoracion", telefono.trim(), idUsuario);
        valoracionGapTO = respuestaGap.consultaValoracionLinea(endpointGap);
        if (valoracionGapTO != null) {
            if (!(valoracionGapTO.getCodigoErrorMensaje() == null || valoracionGapTO.getCodigoErrorMensaje().equals(""))) {
                throw new CAException(-1, "CA.consultaValoracionGap[" + valoracionGapTO.getCodigoErrorMensaje() + ": " + valoracionGapTO.getDescripcionErrorMensaje() + "]");
            }
            existenPromociones = "2".equals(valoracionGapTO.getContadorPromociones());
        }
        if (existenPromociones) {
            respuestaGap = new RespuestaConsultasGap("SolicitudInformacionPromocion", telefono.trim(), idUsuario);
            valoracionGapTO = respuestaGap.obtienePromocionesGap(endpointGap, valoracionGapTO);
        }
        if (!(valoracionGapTO == null || valoracionGapTO.getCodigoErrorMensaje() == null || valoracionGapTO.getCodigoErrorMensaje().equals(""))) {
            throw new CAException(-1, "CA.consultaValoracionGap[" + valoracionGapTO.getCodigoErrorMensaje() + ": " + valoracionGapTO.getDescripcionErrorMensaje() + "]");
        }
        return valoracionGapTO;
    }

    private InfoPromocionGapTO consutaPromocionGapCa(ArrayList<InfoPromocionGapTO> listPromociones) {
        InfoPromocionGapTO infoPromocionGapTO = null;
        if (listPromociones != null) {
            ListIterator<InfoPromocionGapTO> listIterator = listPromociones.listIterator();
            while (listIterator.hasNext()) {
                infoPromocionGapTO = listIterator.next();
                if (infoPromocionGapTO.getAplicaCA() != null && infoPromocionGapTO.getAplicaCA().equals("SI")) {
                    return infoPromocionGapTO;
                }
                infoPromocionGapTO = null;
            }
        }
        return infoPromocionGapTO;
    }

    private boolean validaCondicionesGap(PromocionCaTO gapCaTO, String planNuevo, MobileTO mobileTO, int region, int adendumNuevo, String marca, String modelo) throws CAException {
        boolean valido = true;
        List<String> lstPlanActual = null;
        List<String> lstPlanesRenovacion = null;
        if (!(gapCaTO.getMarca() == null || "".equals(gapCaTO.getMarca()) || gapCaTO.getMarca().equals(marca))) {
            return false;
        }
        if (!(gapCaTO.getModelo() == null || "".equals(gapCaTO.getModelo()) || gapCaTO.getModelo().equals(modelo))) {
            return false;
        }
        if (!(gapCaTO.getIdGrupoPlanAnterior() == 0 || (lstPlanActual = this.consultasGapDAO.getPlanesByIdGrupoPlan(gapCaTO.getIdGrupoPlanAnterior(), gapCaTO.getModoSuscripcionAnterior(), region)) == null || lstPlanActual.contains(mobileTO.getPlanM2K().trim()))) {
            return false;
        }
        if (gapCaTO.getPlazoFzoAnterior() != 0) {
            if (mobileTO.getAddM2K() == null || "".equals(mobileTO.getAddM2K())) {
                mobileTO.setAddM2K("0");
            }
            if (gapCaTO.getPlazoFzoAnterior() != Integer.parseInt(mobileTO.getAddM2K().trim())) {
                return false;
            }
        }
        if (!(gapCaTO.getIdGrupoPlanNuevo() == 0 || (lstPlanesRenovacion = this.consultasGapDAO.getPlanesByIdGrupoPlan(gapCaTO.getIdGrupoPlanNuevo(), gapCaTO.getModoSuscripcionNuevo(), region)) == null || lstPlanesRenovacion.contains(planNuevo.trim()))) {
            return false;
        }
        if (gapCaTO.getPlazoFzoNuevo() != 0 && gapCaTO.getPlazoFzoNuevo() != adendumNuevo) {
            return false;
        }
        return valido;
    }

    public double utilizaProrrateo(String sMeses, String sAdendumCAREG, int nMesActual, int nDiasActual, int iDiasMes, String lFormaRed, String lAddM2K) throws CAException {
        int nMeses = 0;
        nMeses = sMeses == null || sMeses.equals("null") || sMeses.length() == 0 ? 0 : Integer.parseInt(sMeses);
        if (sAdendumCAREG == null || sAdendumCAREG.equals("null") || sAdendumCAREG.length() == 0) {
            sAdendumCAREG = "0";
        }
        double porcDescuento = lFormaRed.equals("PC") ? this.getPtosProrrateo(sAdendumCAREG, nMesActual + nMeses, nDiasActual, iDiasMes) : this.getPtosProrrateo(lAddM2K, nMesActual, nDiasActual, iDiasMes);
        return porcDescuento;
    }

    private double getPtosProrrateo(String pzoFzo, int nMesCareg, int nDiasActual, int iDiasMes) throws CAException {
        double a = -16.1266668351641;
        double b = 1.00885030099803;
        double c = 0.00180292669748867;
        double y = 0.0;
        double z = 0.0;
        double A3 = 0.0;
        double Y = 0.0;
        y = ((double)nMesCareg + 1.0) / (double)Integer.parseInt(pzoFzo) - (double)nMesCareg / (double)Integer.parseInt(pzoFzo);
        z = (double)nDiasActual * y / (double)iDiasMes;
        A3 = ((double)nMesCareg / (double)Integer.parseInt(pzoFzo) + z) * 100.0;
        Y = (a + b * A3 + c * Math.pow(A3, 2.0)) / 100.0;
        return Y;
    }

    public boolean obtienePrecioTotal(BigDecimal descuento, ProductosTO productosTO, double costoPuntos) {
        if (productosTO.getPrecioBD().compareTo(BigDecimal.valueOf(0)) > 0) {
            long nPrecioenPuntos = (long)Math.floor(productosTO.getPrecioBD().doubleValue() / costoPuntos);
            if (nPrecioenPuntos > productosTO.getPtosARedimir()) {
                productosTO.setPrecioBD(productosTO.getPrecioBD().subtract(descuento));
            } else {
                productosTO.setPrecioBD(new BigDecimal(0));
                productosTO.setPtosARedimir(nPrecioenPuntos);
            }
        } else {
            productosTO.setPtosARedimir(0);
        }
        return true;
    }

    public ArrayList<com.claro.transfer.CatalogoTO> obtienePropiedades() throws CAException {
        ArrayList<com.claro.transfer.CatalogoTO> arrayList;
        block29 : {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            arrayList = null;
            try {
                try {
                    String sQuery = "SELECT IDVARIABLE,VALOR FROM " + this.schema_database + "PTO_CTLPROPIEDADES";
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sQuery);
                    arrayList = new ArrayList<com.claro.transfer.CatalogoTO>();
                    while (resultSet.next()) {
                        com.claro.transfer.CatalogoTO catalogoTO = new com.claro.transfer.CatalogoTO();
                        catalogoTO.setIdVariable(resultSet.getString(1));
                        catalogoTO.setValor(resultSet.getString(2));
                        arrayList.add(catalogoTO);
                    }
                }
                catch (Exception e) {
                    this.error.error((Object)"CatalogoDAO.obtienePropiedades:", (Throwable)e);
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                            resultSet = null;
                        }
                        catch (Exception var8_8) {
                            // empty catch block
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                            statement = null;
                        }
                        catch (Exception var8_9) {
                            // empty catch block
                        }
                    }
                    if (connection == null) break block29;
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var8_14) {}
                }
                if (statement != null) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var8_15) {}
                }
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_16) {}
                }
            }
        }
        return arrayList;
    }

    public ArrayList<com.claro.transfer.CatalogoTO> obtienePerfil() throws CAException {
        ArrayList<com.claro.transfer.CatalogoTO> arrayList;
        block29 : {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            arrayList = null;
            try {
                try {
                    StringBuffer sQuery = new StringBuffer();
                    sQuery.append("SELECT IDPERFILN, IDPUESTO, DESCRIPCION FROM ");
                    sQuery.append(this.schema_database).append("PTO_CTLPERFILN ORDER BY IDPUESTO, DESCRIPCION ");
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sQuery.toString());
                    arrayList = new ArrayList<com.claro.transfer.CatalogoTO>();
                    while (resultSet.next()) {
                        com.claro.transfer.CatalogoTO catalogoTO = new com.claro.transfer.CatalogoTO();
                        catalogoTO.setIdVariable("" + resultSet.getInt(1));
                        catalogoTO.setValor(resultSet.getString(2));
                        catalogoTO.setDescripcion(resultSet.getString(3));
                        arrayList.add(catalogoTO);
                    }
                }
                catch (Exception e) {
                    this.error.error((Object)"CatalogoDAO.obtienePerfil:", (Throwable)e);
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                            resultSet = null;
                        }
                        catch (Exception var8_8) {
                            // empty catch block
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                            statement = null;
                        }
                        catch (Exception var8_9) {
                            // empty catch block
                        }
                    }
                    if (connection == null) break block29;
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var8_14) {}
                }
                if (statement != null) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var8_15) {}
                }
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_16) {}
                }
            }
        }
        return arrayList;
    }

    public ArrayList<ProductosTO> listaPromociones(int iRegion, int iValorPuntos, String sPlan, int iGrupoPromo, String cacsFzaVenta, String todosFzaVenta, String marca, String modelo) throws CAException {
        ArrayList<ProductosTO> productos;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        productos = new ArrayList<ProductosTO>();
        query.append(" SELECT A.IDPRODUCTO, A.DESCRIPCION, A.VALORPUNTOS, A.PRECIOLISTA,");
        query.append(" A.PRECIOACTIVACION, B.TIPOPROMOCION, A.FZAVENTAS, A.MARCA, A.MODELO, ");
        query.append(" A.URL, A.TECNOLOGIA");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES A,  ");
        query.append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B ");
        query.append(" WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION  ");
        query.append(" AND A.IDGRUPOPROMOCION = ? AND A.FZAVENTAS IN (?,?)");
        query.append(" AND A.IDREGION = ?  AND A.ESTATUS='A'");
        if (iValorPuntos != 0) {
            query.append(" AND A.VALORPUNTOS <= ? ");
            if (sPlan != null) {
                query.append(" AND A.MODELO =  (SELECT B.BMIXTO  ");
                query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS B  ");
                query.append(" WHERE B.IDPLAN = ? AND B.IDREGION  = ? AND B.ESTATUS='A' )");
                query.append(" ORDER BY A.VALORPUNTOS DESC ");
            } else {
                query.append(" ORDER BY A.VALORPUNTOS DESC ");
            }
        } else if (marca != null) {
            query.append(" AND A.MARCA =? ");
            if (!"TODOS".equals(modelo)) {
                query.append(" AND A.MODELO=? ");
            }
            query.append(" ORDER BY A.MARCA, A.MODELO ");
        }
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setInt(1, iGrupoPromo);
                if (iRegion != 9) {
                    statement.setString(2, cacsFzaVenta);
                    statement.setString(3, cacsFzaVenta);
                } else {
                    statement.setString(2, cacsFzaVenta);
                    statement.setString(3, todosFzaVenta);
                }
                statement.setInt(4, iRegion);
                if (iValorPuntos != 0) {
                    statement.setInt(5, iValorPuntos);
                    if (sPlan != null) {
                        statement.setString(6, sPlan);
                        statement.setInt(7, iRegion);
                    }
                }
                if (marca != null) {
                    statement.setString(5, marca);
                    if (!"TODOS".equals(modelo)) {
                        statement.setString(6, modelo);
                    }
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ProductosTO productosTO = new ProductosTO();
                    productosTO.setMaterial(resultSet.getString("IDPRODUCTO"));
                    productosTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    productosTO.setMarca(resultSet.getString("MARCA"));
                    productosTO.setModelo(resultSet.getString("MODELO"));
                    productosTO.setValorPuntos(resultSet.getInt("VALORPUNTOS"));
                    productosTO.setPrecioActivacion(resultSet.getBigDecimal("PRECIOACTIVACION"));
                    productosTO.setPrecioLista(resultSet.getBigDecimal("PRECIOLISTA"));
                    productosTO.setTipoPromocion(resultSet.getString("TIPOPROMOCION"));
                    productosTO.setUrl(resultSet.getString("URL"));
                    productosTO.setTecnologia(resultSet.getString("TECNOLOGIA"));
                    productosTO.setPuntosSobrantes("0");
                    productosTO.setDescuento(new BigDecimal(0.0));
                    productosTO.setBonosRoext(0);
                    productosTO.setIndicador("1");
                    productosTO.setBonosAltoValor(0);
                    productosTO.setAplicaPaqueteSMS("0");
                    productosTO.setBonosGap(0);
                    productosTO.setFzaVentas(resultSet.getString("FZAVENTAS"));
                    productos.add(productosTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.listaPromociones [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.listaPromociones[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var16_21) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var16_22) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var16_23) {}
            }
        }
        return productos;
    }

    public ArrayList<AlianzasTO> consultaAlianzas(int cveAli, String sCuenta, int iSecuencia, String statusAli, String sCuentaAl) throws CAException {
        ArrayList<AlianzasTO> alianzas;
        PreparedStatement statement = null;
        PreparedStatement statementAlianza = null;
        ResultSet resultSet = null;
        ResultSet resultSetAlianza = null;
        Connection connection = null;
        String sEstatusUltimoCanje = "";
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.CUENTA, A.SECUENCIA, A.NOMBRE, A.APATERNO, A.AMATERNO, A.IDCUENTA, ");
        query.append("A.CTAALIANZA, C.LINEA, B.CVEALIANZA,A.STATUSALIANZA ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLDETALLEALIANZA A,  ").append(this.schema_database).append("PTO_CTLALIANZA B, ").append(this.schema_database).append("PTO_TBLLINEAS C ");
        query.append(" WHERE A.Cuenta = ? AND A.Secuencia = ? AND A.Statusalianza = ? ");
        if (cveAli != 0) {
            if (sCuentaAl != null) {
                query.append(" AND A.IDCUENTA=? AND A.CTAALIANZA = ? ");
            } else {
                query.append(" AND A.IDCUENTA=? ");
            }
        }
        query.append(" AND C.Cuenta = A.Cuenta AND C.Secuencia =A.Secuencia ");
        query.append("  AND A.IdCuenta = B.IdCuenta ");
        alianzas = new ArrayList<AlianzasTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, sCuenta);
                statement.setInt(2, iSecuencia);
                statement.setString(3, statusAli);
                if (cveAli != 0) {
                    if (sCuentaAl != null) {
                        statement.setInt(4, cveAli);
                        statement.setString(5, sCuentaAl);
                    } else {
                        statement.setInt(6, cveAli);
                    }
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AlianzasTO alianzasTO = new AlianzasTO();
                    alianzasTO.setCuenta(resultSet.getString(1));
                    alianzasTO.setSecuencia(resultSet.getInt(2));
                    alianzasTO.setNombre(resultSet.getString(3));
                    alianzasTO.setAPaterno(resultSet.getString(4));
                    alianzasTO.setAMaterno(resultSet.getString(5));
                    alianzasTO.setIdCuentaAlianza(resultSet.getInt(6));
                    alianzasTO.setCuentaAlianza(resultSet.getString(7));
                    alianzasTO.setCLinea(resultSet.getString(8));
                    alianzasTO.setCveAlianza(resultSet.getString(9));
                    alianzasTO.setStatusAlianza(resultSet.getString(10));
                    if (alianzasTO.getIdCuentaAlianza() == 1 && statusAli.equals(Character.valueOf('A'))) {
                        query = new StringBuffer();
                        query.append(" SELECT FechaOper, StatusTrans ");
                        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLALIANZAS  ");
                        query.append(" WHERE Cuenta = ? AND Secuencia = ?");
                        query.append("ORDER BY FechaOper desc");
                        statementAlianza = connection.prepareStatement(query.toString());
                        statementAlianza.setString(1, sCuenta);
                        statementAlianza.setInt(2, iSecuencia);
                        resultSetAlianza = statementAlianza.executeQuery();
                        if (resultSetAlianza.next()) {
                            int nStatusTrans = resultSetAlianza.getInt(2);
                            Date FechaOper = resultSetAlianza.getDate(1);
                            String nombreMes = Utils.nombreMesfecha((java.util.Date)FechaOper);
                            String sDia = resultSetAlianza.getString(1).substring(8, 10);
                            String sAnno = resultSetAlianza.getString(1).substring(0, 4);
                            sEstatusUltimoCanje = "El " + sDia + " de " + nombreMes + " del " + sAnno + " hubo un error en el traspaso de puntos a Mexicana ";
                            sEstatusUltimoCanje = nStatusTrans == 1 ? String.valueOf(sEstatusUltimoCanje) + "debido a que la cuenta Frecuenta no existia, por favor revise nuevamente que los datos estan correctos." : (nStatusTrans == 2 ? String.valueOf(sEstatusUltimoCanje) + "debido a que el socio se encontraba inactivo en Frecuenta, por favor revise nuevamente que los datos estan correctos." : (nStatusTrans == 3 ? String.valueOf(sEstatusUltimoCanje) + "debido a que el nombre del socio no era valido en Frecuenta, por favor revise nuevamente que los datos estan correctos." : ""));
                            alianzasTO.setMensajeEstatusUltimoCanje(sEstatusUltimoCanje);
                        }
                    }
                    alianzasTO.agregaMensaje(0, "Proceso Exitoso.");
                    alianzas.add(alianzasTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaAlianzas [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.consultaAlianzas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var21_28) {}
            }
            if (resultSetAlianza != null) {
                try {
                    resultSetAlianza.close();
                    resultSetAlianza = null;
                }
                catch (Exception var21_29) {}
            }
            if (statementAlianza != null) {
                try {
                    statementAlianza.close();
                    statementAlianza = null;
                }
                catch (Exception var21_30) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var21_31) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var21_32) {}
            }
        }
        return alianzas;
    }

    public AlianzasTO consultaAlianza(int alianza, String linea, int iSecuencia, String statusAli) throws CAException {
        PreparedStatement statement = null;
        PreparedStatement statementAlianza = null;
        ResultSet resultSet = null;
        ResultSet resultSetAlianza = null;
        Connection connection = null;
        String sEstatusUltimoCanje = "";
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.CUENTA, A.SECUENCIA, A.NOMBRE, A.APATERNO, A.AMATERNO, A.IDCUENTA, ");
        query.append("A.CTAALIANZA, C.LINEA, B.CVEALIANZA,A.STATUSALIANZA ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLDETALLEALIANZA A,  ").append(this.schema_database).append("PTO_CTLALIANZA B, ").append(this.schema_database).append("PTO_TBLLINEAS C ");
        query.append(" WHERE A.Secuencia = ? AND A.Statusalianza = ? ");
        query.append(" AND C.linea = ? and A.idcuenta = ?");
        query.append(" AND C.Cuenta = A.Cuenta AND C.Secuencia = A.Secuencia ");
        query.append("  AND A.IdCuenta = B.IdCuenta ");
        AlianzasTO alianzasTO = null;
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(query.toString());
            statement.setInt(1, iSecuencia);
            statement.setString(2, statusAli);
            statement.setString(3, linea);
            statement.setInt(4, alianza);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                alianzasTO = new AlianzasTO();
                alianzasTO.setCuenta(resultSet.getString(1));
                alianzasTO.setSecuencia(resultSet.getInt(2));
                alianzasTO.setNombre(resultSet.getString(3));
                alianzasTO.setAPaterno(resultSet.getString(4));
                alianzasTO.setAMaterno(resultSet.getString(5));
                alianzasTO.setIdCuentaAlianza(resultSet.getInt(6));
                alianzasTO.setCuentaAlianza(resultSet.getString(7));
                alianzasTO.setCLinea(resultSet.getString(8));
                alianzasTO.setCveAlianza(resultSet.getString(9));
                alianzasTO.setStatusAlianza(resultSet.getString(10));
                if (alianzasTO.getIdCuentaAlianza() == 1 && statusAli.equals(Character.valueOf('A'))) {
                    query = new StringBuffer();
                    query.append(" SELECT FechaOper, StatusTrans ");
                    query.append(" FROM  ").append(this.schema_database).append("PTO_TBLALIANZAS  ");
                    query.append(" WHERE Cuenta = ? AND Secuencia = ?");
                    query.append("ORDER BY FechaOper desc");
                    statementAlianza = connection.prepareStatement(query.toString());
                    statementAlianza.setString(1, alianzasTO.getCuenta());
                    statementAlianza.setInt(2, iSecuencia);
                    resultSetAlianza = statementAlianza.executeQuery();
                    if (resultSetAlianza.next()) {
                        int nStatusTrans = resultSetAlianza.getInt(2);
                        Date FechaOper = resultSetAlianza.getDate(1);
                        String nombreMes = Utils.nombreMesfecha((java.util.Date)FechaOper);
                        String sDia = resultSetAlianza.getString(1).substring(8, 10);
                        String sAnno = resultSetAlianza.getString(1).substring(0, 4);
                        sEstatusUltimoCanje = "El " + sDia + " de " + nombreMes + " del " + sAnno + " hubo un error en el traspaso de puntos a Mexicana ";
                        sEstatusUltimoCanje = nStatusTrans == 1 ? String.valueOf(sEstatusUltimoCanje) + "debido a que la cuenta Frecuenta no existia, por favor revise nuevamente que los datos estan correctos." : (nStatusTrans == 2 ? String.valueOf(sEstatusUltimoCanje) + "debido a que el socio se encontraba inactivo en Frecuenta, por favor revise nuevamente que los datos estan correctos." : (nStatusTrans == 3 ? String.valueOf(sEstatusUltimoCanje) + "debido a que el nombre del socio no era valido en Frecuenta, por favor revise nuevamente que los datos estan correctos." : ""));
                        alianzasTO.setMensajeEstatusUltimoCanje(sEstatusUltimoCanje);
                    }
                }
                alianzasTO.agregaMensaje(0, "Proceso Exitoso.");
            }
            AlianzasTO alianzasTO2 = alianzasTO;
            return alianzasTO2;
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.consultaAlianza [" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.consultaAlianzas[" + e.toString() + "]", e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var20_26) {}
            }
            if (resultSetAlianza != null) {
                try {
                    resultSetAlianza.close();
                    resultSetAlianza = null;
                }
                catch (Exception var20_27) {}
            }
            if (statementAlianza != null) {
                try {
                    statementAlianza.close();
                    statementAlianza = null;
                }
                catch (Exception var20_28) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var20_29) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var20_30) {}
            }
        }
    }

    public ArrayList<AlianzasTO> listaCertLealtad(String sCuenta, int iSecuencia) throws CAException {
        ArrayList<AlianzasTO> certificados;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT CUENTA, LINEA, FECHAOPER,");
        query.append("PORCANTIG, PORCARPU, PORCCOB, VCERTIF, VCENTIFEXTRA,FCADUCA, ESTATUS ");
        query.append(" FROM ").append(this.schema_database).append("PTO_TBLCERTIFICADOS ");
        query.append(" WHERE CUENTA = ? AND SECUENCIA = ?  AND ESTATUS IN ('A','L') ");
        certificados = new ArrayList<AlianzasTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, sCuenta);
                statement.setInt(2, iSecuencia);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AlianzasTO alianzasTO = new AlianzasTO();
                    alianzasTO.setCuenta(resultSet.getString(1));
                    alianzasTO.setCLinea(resultSet.getString(2));
                    alianzasTO.setFechaToper(resultSet.getTimestamp(3));
                    alianzasTO.setPorcAntig(Integer.valueOf(resultSet.getInt(4)));
                    alianzasTO.setPorcArpu(Integer.valueOf(resultSet.getInt(5)));
                    alianzasTO.setPorcCob(Integer.valueOf(resultSet.getInt(6)));
                    alianzasTO.setVCertif(Integer.valueOf(resultSet.getInt(7)));
                    alianzasTO.setVCentifExtra(Integer.valueOf(resultSet.getInt(8)));
                    alianzasTO.setVigenciaMax(resultSet.getDate(9));
                    alianzasTO.setEstatus(resultSet.getString(10));
                    certificados.add(alianzasTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.listaCertLealtad [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.listaCertLealtad[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_15) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_16) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_17) {}
            }
        }
        return certificados;
    }

    public ArrayList<CiudadEdoTO> getEstadoCiudad(String estado) throws CAException {
        ArrayList<CiudadEdoTO> estadoCiudad;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        if (estado == null) {
            query.append(" SELECT DISTINCT A.ESTADO");
        } else {
            query.append(" SELECT DISTINCT A.CIUDAD");
        }
        query.append(", A.COSTO, A.OPCION FROM  ").append(this.schema_database).append("PTO_CTLEDOCIUDAD A ");
        query.append(" WHERE A.STATUS = 'A' ");
        if (estado != null) {
            query.append(" AND A.ESTADO=?");
        }
        estadoCiudad = new ArrayList<CiudadEdoTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                if (estado != null) {
                    statement.setString(1, estado);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    CiudadEdoTO ciudadEdoTO = new CiudadEdoTO();
                    if (estado == null) {
                        ciudadEdoTO.setEstado(resultSet.getString(1));
                    } else {
                        ciudadEdoTO.setCiudad(resultSet.getString(1));
                    }
                    ciudadEdoTO.setCosto(resultSet.getInt(2));
                    ciudadEdoTO.setOpcion(resultSet.getInt(3));
                    estadoCiudad.add(ciudadEdoTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getEstados[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getEstados[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_14) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var9_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_16) {}
            }
        }
        return estadoCiudad;
    }

    public ArrayList<PromoBeneficiosTO> obtieneBeneficios(int region, String marca) throws CAException {
    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        PreparedStatement statementGpoBenef = null;
        ResultSet resultSetGpoBenef = null;
        PreparedStatement statementBenef = null;
        ResultSet resultSetBenef = null;
        Connection connection = null;
        ArrayList<PromoBeneficiosTO> beneficios = new ArrayList<PromoBeneficiosTO>();
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            String query = "select MARCA, MODELO, IDBENEFICIO,IDGPOBENEF  from  " + this.schema_database + "PTO_CTLPROMOBENEF ";
            query = marca != null ? String.valueOf(query) + " where (IDREGION = ? OR APLICAREGIONES = 'T') AND MARCA = ? AND ESTATUS = 'A' ORDER BY MARCA" : String.valueOf(query) + " where (IDREGION = ? OR APLICAREGIONES = 'T') AND ESTATUS = 'A' ORDER BY MARCA";
            statement = connection.prepareStatement(query);
            statement.setInt(1, region);
            if (marca != null) {
                statement.setString(2, marca);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PromoBeneficiosTO promoBeneficiosTO = new PromoBeneficiosTO();
                promoBeneficiosTO.setMarca(resultSet.getString("MARCA"));
                promoBeneficiosTO.setModelo(resultSet.getString("MODELO"));
                promoBeneficiosTO.setIdbeneficio(resultSet.getString("IDBENEFICIO"));
                promoBeneficiosTO.setIdGpoBeneficio(String.valueOf(resultSet.getInt("IDGPOBENEF")));
                beneficios.add(promoBeneficiosTO);
            }
            for (PromoBeneficiosTO promoBeneficiosTO : beneficios) {
                BeneficioTO beneficioTO;
                if (promoBeneficiosTO.getIdbeneficio().trim().equals("")) {
                    String gpoBenefQuery = "SELECT a.IDBENEFICIO, b.DESCRIPCION FROM " + this.schema_database + "PTO_CTLGPOBENEF a, " + this.schema_database + "PTO_CTLBENEFICIOS b " + "WHERE a.IDGPOBENEF = ? AND a.ESTATUS = 'A' AND a.IDBENEFICIO = b.IDBENEFICIO";
                    statementGpoBenef = connection.prepareStatement(gpoBenefQuery);
                    statementGpoBenef.setInt(1, Integer.parseInt(promoBeneficiosTO.getIdGpoBeneficio()));
                    resultSetGpoBenef = statementGpoBenef.executeQuery();
                    ArrayList<BeneficioTO> beneficiosTO = new ArrayList<BeneficioTO>();
                    while (resultSetGpoBenef.next()) {
                        beneficioTO = new BeneficioTO();
                        beneficioTO.setIdGpoBeneficio(promoBeneficiosTO.getIdGpoBeneficio());
                        beneficioTO.setIdBeneficio(resultSetGpoBenef.getString("IDBENEFICIO"));
                        beneficioTO.setDescBeneficio(resultSetGpoBenef.getString("DESCRIPCION"));
                        beneficiosTO.add(beneficioTO);
                    }
                    promoBeneficiosTO.setBeneficios(beneficiosTO);
                    continue;
                }
                ArrayList<BeneficioTO> beneficiosTO = new ArrayList<BeneficioTO>();
                String queryBenef = "SELECT DESCRIPCION FROM " + this.schema_database + "PTO_CTLBENEFICIOS where IDBENEFICIO = ?";
                statementBenef = connection.prepareStatement(queryBenef);
                statementBenef.setString(1, promoBeneficiosTO.getIdbeneficio());
                resultSetBenef = statementBenef.executeQuery();
                while (resultSetBenef.next()) {
                    beneficioTO = new BeneficioTO();
                    beneficioTO.setIdGpoBeneficio(promoBeneficiosTO.getIdGpoBeneficio());
                    beneficioTO.setIdBeneficio(promoBeneficiosTO.getIdbeneficio());
                    beneficioTO.setDescBeneficio(resultSetBenef.getString(1));
                    beneficiosTO.add(beneficioTO);
                }
                promoBeneficiosTO.setBeneficios(beneficiosTO);
            }
            ArrayList<PromoBeneficiosTO> arrayList = beneficios;
            return arrayList;
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.obtienePromocionesMarcas[" + e.getMessage() + "]");
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var19_26) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var19_27) {}
            }
            if (resultSetGpoBenef != null) {
                try {
                    resultSetGpoBenef.close();
                    resultSetGpoBenef = null;
                }
                catch (Exception var19_28) {}
            }
            if (statementGpoBenef != null) {
                try {
                    statementGpoBenef.close();
                    statementGpoBenef = null;
                }
                catch (Exception var19_29) {}
            }
            if (resultSetBenef != null) {
                try {
                    resultSetBenef.close();
                    resultSetBenef = null;
                }
                catch (Exception var19_30) {}
            }
            if (statementBenef != null) {
                try {
                    statementBenef.close();
                    statementBenef = null;
                }
                catch (Exception var19_31) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var19_32) {}
            }
        }
    }

    public ArrayList<MotivoTO> obtieneCatalogoRechazo(int tipoMotivo) throws CAException {
        Connection connection = null;
        ResultSet result = null;
        PreparedStatement statement = null;
        String sql = "select IDMOTIVO,DESCRIPCION  from " + this.schema_database + "PTO_CTLMOTRECHAZOS WHERE activo='1' AND idtipomotivo = ?";
        ArrayList<MotivoTO> motivos = new ArrayList<MotivoTO>();
        StringBuffer selectStatement = new StringBuffer();
        selectStatement.append(sql);
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, tipoMotivo);
            result = statement.executeQuery();
            while (result.next()) {
                MotivoTO motivoTO = new MotivoTO();
                motivoTO.setIdMotivo(result.getString("IDMOTIVO"));
                motivoTO.setDescripcion(result.getString("DESCRIPCION"));
                motivos.add(motivoTO);
            }
            ArrayList<MotivoTO> arrayList = motivos;
            return arrayList;
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.getCatalogoRechazo[" + e.toString() + "]");
        }
        finally {
            if (result != null) {
                try {
                    result.close();
                    result = null;
                }
                catch (Exception var11_14) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var11_16) {}
            }
        }
    }

    public ArrayList<com.claro.transfer.CatalogoTO> obtieneGrupoPromocion() throws CAException {
        ArrayList<com.claro.transfer.CatalogoTO> arrayList;
        block29 : {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            arrayList = null;
            try {
                try {
                    StringBuffer sQuery = new StringBuffer();
                    sQuery.append("SELECT IDGRUPOPROMOCION,TIPOPROMOCION,GRUPOPROMOCION FROM ");
                    sQuery.append(this.schema_database).append("PTO_CTLGRUPOPROMOCION order by IDGRUPOPROMOCION desc ");
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sQuery.toString());
                    arrayList = new ArrayList<com.claro.transfer.CatalogoTO>();
                    while (resultSet.next()) {
                        com.claro.transfer.CatalogoTO catalogoTO = new com.claro.transfer.CatalogoTO();
                        catalogoTO.setIdVariable("" + resultSet.getInt(1));
                        catalogoTO.setValor(resultSet.getString(2));
                        catalogoTO.setDescripcion(resultSet.getString(3));
                        arrayList.add(catalogoTO);
                    }
                }
                catch (Exception e) {
                    this.error.error((Object)"CatalogoDAO.obtieneGrupoPromocion:", (Throwable)e);
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                            resultSet = null;
                        }
                        catch (Exception var8_8) {
                            // empty catch block
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                            statement = null;
                        }
                        catch (Exception var8_9) {
                            // empty catch block
                        }
                    }
                    if (connection == null) break block29;
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var8_14) {}
                }
                if (statement != null) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var8_15) {}
                }
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_16) {}
                }
            }
        }
        return arrayList;
    }

    public ArrayList<PlanTO> getPlanesTarifarios(int region) throws CAException {
        ArrayList<PlanTO> listaPlanes = new ArrayList<PlanTO>();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.IDPLAN, B.SEGMENTO, A.IDGRUPOPROMOCION, A.TECNOLOGIA, A.ADENDUM");
        query.append(" FROM ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS A, PTO_CTLSEGMENTOS B");
        query.append(" WHERE A.IDREGION=?");
        query.append(" AND A.IDSEGMENTO=B.IDSEGMENTO AND A.IDREGION=B.IDREGION AND A.BREDENCION=1");
        query.append(" AND A.ESTATUS='A'");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setInt(1, region);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    PlanTO planTO = new PlanTO();
                    planTO.setIdPlanNuevo(resultSet.getString("IDPLAN"));
                    planTO.setDescSegmento(resultSet.getString("SEGMENTO"));
                    planTO.setIdGrupoPromocion(resultSet.getInt("IDGRUPOPROMOCION"));
                    planTO.setTecnologia(resultSet.getString("TECNOLOGIA"));
                    planTO.setAdendumNvo(resultSet.getInt("ADENDUM"));
                    listaPlanes.add(planTO);
                }
                if (listaPlanes.size() <= 0) {
                    throw new CAException(1, "No existen PLANES que mostrar para la region.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getPlanesDisponibles[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getPlanesDisponibles[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_14) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_16) {}
            }
        }
        return listaPlanes;
    }

    public PlanTO consultaPlan(String idPlan, int region) throws CAException {
        PlanTO planTO;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int contador = 0;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.IDPLAN, A.IDSEGMENTO, A.IDREGION, A.IDGRUPOPROMOCION, A.DESCRIPCION,");
        query.append(" A.TECNOLOGIA, A.BMIXTO, A.MODALIDAD, A.BSISACT, A.ADENDUM,");
        query.append(" A.RENTA, A.BREDENCION, A.ESTATUS, A.BREDENCIONANTC, A.TIPO_PLAN ");
        query.append(" FROM ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS A,");
        query.append(this.schema_database).append("PTO_CTLSEGMENTOS B");
        query.append(" WHERE A.IDPLAN= ? AND A.IDREGION=?");
        query.append(" AND A.IDSEGMENTO=B.IDSEGMENTO AND A.IDREGION=B.IDREGION");
        try {
            PlanTO planTO2 = new PlanTO();
            planTO2.agregaMensaje(0, "Proceso Exitoso");
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(++contador, idPlan);
            preparedStatement.setInt(++contador, region);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                planTO2.setIdPlanNuevo(resultSet.getString("IDPLAN"));
                planTO2.setSegmento(resultSet.getInt("IDSEGMENTO"));
                planTO2.setIdRegion(resultSet.getInt("IDREGION"));
                planTO2.setIdGrupoPromocion(resultSet.getInt("IDGRUPOPROMOCION"));
                planTO2.setDescripcion(resultSet.getString("DESCRIPCION"));
                planTO2.setTecnologia(resultSet.getString("TECNOLOGIA"));
                planTO2.setBanMixto(resultSet.getString("BMIXTO"));
                planTO2.setModalidad(resultSet.getString("MODALIDAD"));
                planTO2.setBanSisact(resultSet.getString("BSISACT"));
                planTO2.setAdendumNvo(resultSet.getInt("ADENDUM"));
                planTO2.setRenta(resultSet.getInt("RENTA"));
                planTO2.setBanRedencion(resultSet.getInt("BREDENCION"));
                planTO2.setEstatus(resultSet.getString("ESTATUS"));
                planTO2.setBanRedencionAnct(resultSet.getString("BREDENCIONANTC"));
                planTO2.setTipoPlan(resultSet.getString("TIPO_PLAN"));
                PlanTO planTO3 = planTO2;
                return planTO3;
            }
            planTO2.agregaMensaje(1, "No se encontraron PLANES que cumplan con los criterios indicados.");
            planTO = planTO2;
        }
        catch (SQLException e) {
            throw new CAException(-1, "CatalogoDAO.consultaPlan[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.consultaPlan[" + e.toString() + "]", e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_19) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var11_20) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var11_21) {}
            }
        }
        return planTO;
    }

    public ProductosSmsTO promocionxSms(String claveProducto, int puntosDispo) throws CAException {
        ProductosSmsTO productosSms;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        productosSms = new ProductosSmsTO();
        query.append(" SELECT CLAVEM2K, CLAVESMS, IDPRODUCTO, DESCRIPCION, TIPOPRODUCTO, ESTATUS, VALORPUNTOS ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS ");
        query.append(" WHERE CLAVESMS = ?  AND ESTATUS='A' AND VALORPUNTOS <= ? ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, claveProducto.toUpperCase());
                statement.setInt(2, puntosDispo);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    productosSms.setClaveM2k(resultSet.getString("CLAVEM2K"));
                    productosSms.setDescripcion(resultSet.getString("CLAVESMS"));
                    productosSms.setIdProducto(resultSet.getString("IDPRODUCTO"));
                    productosSms.setDescripcion(resultSet.getString("DESCRIPCION"));
                    productosSms.setTipoProducto(resultSet.getString("TIPOPRODUCTO"));
                    productosSms.setEstatus(resultSet.getString("ESTATUS"));
                    productosSms.setValorPuntos(resultSet.getInt("VALORPUNTOS"));
                } else {
                    productosSms.agregaMensaje(1, "No cuenta con el total de puntos requeridos.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.listaPromocionesxSms [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.listaPromocionesxSms[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_14) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_16) {}
            }
        }
        return productosSms;
    }

    public ArrayList<ProductosSmsTO> listaproductosonline() throws CAException {
    	PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        ArrayList<ProductosSmsTO> listaProductosOnline = new ArrayList<ProductosSmsTO>();
        query.append(" SELECT CLAVESMS, DESCRIPCION, VALORPUNTOS, IDPRODUCTO ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS ");
        query.append(" WHERE ESTATUS='A' ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ProductosSmsTO productosSms = new ProductosSmsTO();
                    productosSms.setClaveSms(resultSet.getString("CLAVESMS"));
                    productosSms.setDescripcion(resultSet.getString("DESCRIPCION"));
                    productosSms.setValorPuntos(resultSet.getInt("VALORPUNTOS"));
                    productosSms.setIdProducto(resultSet.getString("IDPRODUCTO"));
                    listaProductosOnline.add(productosSms);
                }
                if (listaProductosOnline.size() <= 0) {
                    throw new CAException(1, "No existen PRODUCTOS a mostrar.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.listaproductosonline [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.listaproductosonline[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_13) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var8_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_15) {}
            }
        }
        return listaProductosOnline;
    }

    public int valorPuntosOnline(String claveProducto) throws CAException {
        int valorPuntos;
        valorPuntos = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT VALORPUNTOS ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS ");
        query.append(" WHERE CLAVESMS = ?  AND ESTATUS='A' ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, claveProducto.toUpperCase());
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    valorPuntos = resultSet.getInt("VALORPUNTOS");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.valorPuntosOnline [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.valorPuntosOnline[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var9_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return valorPuntos;
    }

    public ArrayList<com.claro.transfer.CatalogoTO> getMarcasXPlan(int region, String idPlan, String fzaVentas) throws CAException {
        ArrayList<com.claro.transfer.CatalogoTO> marcas;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        com.claro.transfer.CatalogoTO catalogoTO = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT DISTINCT MARCA, IDGRUPOPROMOCION FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES A ");
        query.append(" WHERE A.ESTATUS = 'A' ");
        query.append(" AND A.IDREGION = ?");
        query.append(" AND A.IDGRUPOPROMOCION = (SELECT DISTINCT IDGRUPOPROMOCION ");
        query.append(" FROM ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS B WHERE B.idplan = ? AND B.IDREGION=").append(region).append(" AND B.ESTATUS = 'A') ");
        if (region == 9) {
            if (fzaVentas != null) {
                query.append(" AND A.FZAVENTAS IN ('TODOS','").append(fzaVentas).append("')");
            } else {
                query.append(" AND A.FZAVENTAS IN ('TELCEL','TODOS')");
            }
        } else {
            query.append(" AND A.FZAVENTAS IN ('TELCEL','TODOS')");
        }
        query.append(" ORDER BY MARCA ASC ");
        marcas = new ArrayList<com.claro.transfer.CatalogoTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setInt(1, region);
                statement.setString(2, idPlan);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    catalogoTO = new com.claro.transfer.CatalogoTO();
                    catalogoTO.setDescripcion(resultSet.getString(1));
                    catalogoTO.setValor(resultSet.getString(2));
                    marcas.add(catalogoTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getMarcasXPlan[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getMarcasXPlan[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_16) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_17) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var12_18) {}
            }
        }
        return marcas;
    }

    public void aplicaBonoInbursa(String marca, String modelo, String plan, DescuentoTO descuentoTO) throws CAException {
    	PreparedStatement statementBonoInbursa = null;
    	PreparedStatement statementDescuento = null;
        ResultSet resultSetBonoInbursa = null;
        ResultSet resultSetDescuento = null;
        Connection connection = null;
        HashMap<String, ProductosTO> descuentosInbursa = new HashMap<String, ProductosTO>();
        ProductosTO equipo = null;
        descuentoTO.setAplicaDescuentoInbursa(0);
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT IDPLAN,DESCUENTOINBURSA ");
                sQuery.append("   FROM  ").append(this.schema_database).append("PTO_CTLPLANESINBURSA ");
                sQuery.append("  WHERE IDPLAN = ? ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementBonoInbursa = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                statementBonoInbursa.setString(1, plan);
                resultSetBonoInbursa = statementBonoInbursa.executeQuery();
                if (resultSetBonoInbursa.next()) {
                    equipo = new ProductosTO();
                    equipo.setPlan(resultSetBonoInbursa.getString("IDPLAN"));
                    equipo.setDescuentoInbursa(resultSetBonoInbursa.getBigDecimal("DESCUENTOINBURSA"));
                    equipo.setDescuentoMarca(new BigDecimal(0));
                    descuentosInbursa.put(equipo.getPlan(), equipo);
                    descuentoTO.setAplicaDescuentoInbursa(1);
                }
                if (descuentoTO.getAplicaDescuentoInbursa() == 1) {
                    StringBuffer sQuery1 = new StringBuffer();
                    sQuery1.append(" SELECT MARCA,MODELO,DESCUENTOMARCA ");
                    sQuery1.append("  FROM  ").append(this.schema_database).append("PTO_CTLDESCUENTOINBURSA ");
                    sQuery1.append("  WHERE MARCA = ? ");
                    if (!modelo.equals("TODOS")) {
                        sQuery1.append(" AND MODELO = ? ");
                    }
                    statementDescuento = connection.prepareStatement(sQuery1.toString(), 1004, 1007);
                    statementDescuento.setString(1, marca);
                    if (!modelo.equals("TODOS")) {
                        statementDescuento.setString(2, modelo);
                    }
                    resultSetDescuento = statementDescuento.executeQuery();
                    while (resultSetDescuento.next()) {
                        ProductosTO equipo2 = new ProductosTO();
                        equipo2.setPlan(equipo.getPlan());
                        equipo2.setMarca(resultSetDescuento.getString("MARCA"));
                        equipo2.setModelo(resultSetDescuento.getString("MODELO"));
                        equipo2.setDescuentoInbursa(equipo.getDescuentoInbursa());
                        equipo2.setDescuentoMarca(resultSetDescuento.getBigDecimal("DESCUENTOMARCA"));
                        descuentosInbursa.put(equipo2.getModelo(), equipo2);
                    }
                }
                if (equipo != null) {
                    if (descuentosInbursa.size() == 0) {
                        descuentoTO.setAplicaDescuentoInbursa(0);
                    } else {
                        descuentoTO.setDescuentosInbursa(descuentosInbursa);
                    }
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.validaPlanBonoAltoValor[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.validaPlanBonoAltoValor[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetBonoInbursa != null) {
                try {
                    resultSetBonoInbursa.close();
                    resultSetBonoInbursa = null;
                }
                catch (Exception var16_23) {}
            }
            if (resultSetDescuento != null) {
                try {
                    resultSetDescuento.close();
                    resultSetDescuento = null;
                }
                catch (Exception var16_24) {}
            }
            if (statementBonoInbursa != null) {
                try {
                    statementBonoInbursa.close();
                    statementBonoInbursa = null;
                }
                catch (Exception var16_25) {}
            }
            if (statementDescuento != null) {
                try {
                    statementDescuento.close();
                    statementDescuento = null;
                }
                catch (Exception var16_26) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var16_27) {}
            }
        }
    }

    public String getPlanesInbursa() throws CAException {
        String planesInbursa;
        PreparedStatement statementInbursa = null;
        ResultSet resultSetInbursa = null;
        Connection connection = null;
        planesInbursa = "";
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT IDPLAN ");
                sQuery.append("   FROM  ").append(this.schema_database).append("PTO_CTLPLANESINBURSA ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementInbursa = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                resultSetInbursa = statementInbursa.executeQuery();
                while (resultSetInbursa.next()) {
                    planesInbursa = String.valueOf(planesInbursa) + resultSetInbursa.getString("IDPLAN") + ",";
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getPlanesInbursa[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getPlanesInbursa[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetInbursa != null) {
                try {
                    resultSetInbursa.close();
                    resultSetInbursa = null;
                }
                catch (Exception var7_12) {}
            }
            if (statementInbursa != null) {
                try {
                    statementInbursa.close();
                    statementInbursa = null;
                }
                catch (Exception var7_13) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_14) {}
            }
        }
        return planesInbursa;
    }

    public String getMarcasInbursa() throws CAException {
        String marcasInbursa;
        PreparedStatement statementInbursa = null;
        ResultSet resultSetInbursa = null;
        Connection connection = null;
        marcasInbursa = "";
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT DISTINCT MARCA ");
                sQuery.append("  FROM  ").append(this.schema_database).append("PTO_CTLDESCUENTOINBURSA ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementInbursa = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                resultSetInbursa = statementInbursa.executeQuery();
                while (resultSetInbursa.next()) {
                    marcasInbursa = String.valueOf(marcasInbursa) + resultSetInbursa.getString("MARCA") + ",";
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getMarcasInbursa[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getMarcasInbursa[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetInbursa != null) {
                try {
                    resultSetInbursa.close();
                    resultSetInbursa = null;
                }
                catch (Exception var7_12) {}
            }
            if (statementInbursa != null) {
                try {
                    statementInbursa.close();
                    statementInbursa = null;
                }
                catch (Exception var7_13) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_14) {}
            }
        }
        return marcasInbursa;
    }

    public String getModelosInbursa() throws CAException {
        String modelosInbursa;
        PreparedStatement statementInbursa = null;
        ResultSet resultSetInbursa = null;
        Connection connection = null;
        modelosInbursa = "";
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT DISTINCT MODELO ");
                sQuery.append("  FROM  ").append(this.schema_database).append("PTO_CTLDESCUENTOINBURSA ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementInbursa = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                resultSetInbursa = statementInbursa.executeQuery();
                while (resultSetInbursa.next()) {
                    modelosInbursa = String.valueOf(modelosInbursa) + resultSetInbursa.getString("MODELO") + ",";
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getModelosInbursa[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getModelosInbursa[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetInbursa != null) {
                try {
                    resultSetInbursa.close();
                    resultSetInbursa = null;
                }
                catch (Exception var7_12) {}
            }
            if (statementInbursa != null) {
                try {
                    statementInbursa.close();
                    statementInbursa = null;
                }
                catch (Exception var7_13) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_14) {}
            }
        }
        return modelosInbursa;
    }

    public String getLineasInbursa() throws CAException {
        String lineasInbursa;
        PreparedStatement statementInbursa = null;
        ResultSet resultSetInbursa = null;
        Connection connection = null;
        lineasInbursa = "";
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT TELEFONO ");
                sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLINBURSAPROMO ");
                sQuery.append(" WHERE PRODUCTO = 'D1000' ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementInbursa = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                resultSetInbursa = statementInbursa.executeQuery();
                while (resultSetInbursa.next()) {
                    lineasInbursa = String.valueOf(lineasInbursa) + resultSetInbursa.getString("TELEFONO") + ",";
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.getModelosInbursa[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.getModelosInbursa[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetInbursa != null) {
                try {
                    resultSetInbursa.close();
                    resultSetInbursa = null;
                }
                catch (Exception var7_12) {}
            }
            if (statementInbursa != null) {
                try {
                    statementInbursa.close();
                    statementInbursa = null;
                }
                catch (Exception var7_13) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_14) {}
            }
        }
        return lineasInbursa;
    }

    public boolean validaBonoInbursa(String cuenta) throws CAException {
        boolean bonoInbursa;
        PreparedStatement statementInbursa = null;
        ResultSet resultSetInbursa = null;
        Connection connection = null;
        bonoInbursa = false;
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append(" SELECT LINEA ");
                sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLBONOINBURSA ");
                sQuery.append(" WHERE CUENTA = ? ");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statementInbursa = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                statementInbursa.setString(1, cuenta);
                resultSetInbursa = statementInbursa.executeQuery();
                if (resultSetInbursa.next()) {
                    bonoInbursa = true;
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.validaBonoInbursa[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "CatalogoDAO.validaBonoInbursa[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetInbursa != null) {
                try {
                    resultSetInbursa.close();
                    resultSetInbursa = null;
                }
                catch (Exception var8_13) {}
            }
            if (statementInbursa != null) {
                try {
                    statementInbursa.close();
                    statementInbursa = null;
                }
                catch (Exception var8_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_15) {}
            }
        }
        return bonoInbursa;
    }
}

