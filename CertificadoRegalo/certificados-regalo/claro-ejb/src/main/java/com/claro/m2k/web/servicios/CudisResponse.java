/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  org.jdom.Document
 *  org.jdom.Element
 *  org.jdom.input.SAXBuilder
 */
package com.claro.m2k.web.servicios;

import com.claro.m2k.web.servicios.ErrorCudisTO;
import com.claro.m2k.web.servicios.PlanDescuentoTO;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class CudisResponse {
    private String estatus;
    private String mensaje;
    private String cancelaCargoGenerado;
    private String clavePlan;
    private String confirmacion;
    private String cuenta;
    private List<ErrorCudisTO> errores;
    private String estatusCuenta;
    private String estatusUsuario;
    private String nombre;
    private String pdscto;
    private List<PlanDescuentoTO> planesDescuento;
    private String telefono;
    private String tipoCliente;

    public CudisResponse(String xml) {
        SAXBuilder builder = new SAXBuilder(false);
        try {
            Document document = builder.build((Reader)new StringReader(xml));
            Element raiz = document.getRootElement();
            if (raiz.getName().trim().equals("RespuestaOK")) {
                Element cancelaCargoGenerado;
                Element confirmacion;
                Element clavePlan;
                Element nombre;
                Element mensaje;
                Element pdscto;
                Element estatusUsuario;
                Element telefono;
                Element errs;
                Element cuenta;
                Element planesDescuento;
                Element estatusCuenta;
                Element tipoCliente;
                Element estatus = raiz.getChild("ESTATUS");
                if (estatus != null) {
                    this.estatus = estatus.getText();
                }
                if ((mensaje = raiz.getChild("MENSAJE")) != null) {
                    this.mensaje = mensaje.getText();
                }
                if ((cancelaCargoGenerado = raiz.getChild("CancelaCargoGenerado")) != null) {
                    this.cancelaCargoGenerado = cancelaCargoGenerado.getText();
                }
                if ((clavePlan = raiz.getChild("ClavePlan")) != null) {
                    this.clavePlan = clavePlan.getText();
                }
                if ((confirmacion = raiz.getChild("Confirmacion")) != null) {
                    this.confirmacion = confirmacion.getText();
                }
                if ((cuenta = raiz.getChild("Cuenta")) != null) {
                    this.cuenta = cuenta.getText();
                }
                if ((errs = raiz.getChild("Errores")) != null) {
                    ArrayList<ErrorCudisTO> erroresCudis = new ArrayList<ErrorCudisTO>();
                    List<Element> codigos = errs.getChildren("Codigo");
                    for (Element codigo : codigos) {
                        ErrorCudisTO errorCudisTO = new ErrorCudisTO();
                        errorCudisTO.setCodigo(codigo.getText());
                        erroresCudis.add(errorCudisTO);
                    }
                    List mensajes = errs.getChildren("Mensaje");
                    Iterator iteraMensajes = mensajes.iterator();
                    int i = 0;
                    while (iteraMensajes.hasNext()) {
                        Element msg = (Element)iteraMensajes.next();
                        erroresCudis.get(i).setMensaje(msg.getText());
                        ++i;
                    }
                    this.errores = erroresCudis;
                }
                if ((estatusCuenta = raiz.getChild("EstatusCuenta")) != null) {
                    this.estatusCuenta = estatusCuenta.getText();
                }
                if ((estatusUsuario = raiz.getChild("EstatusUsuario")) != null) {
                    this.estatusUsuario = estatusUsuario.getText();
                }
                if ((nombre = raiz.getChild("Nombre")) != null) {
                    this.nombre = nombre.getText();
                }
                if ((pdscto = raiz.getChild("Pdscto")) != null) {
                    this.pdscto = pdscto.getText();
                }
                if ((planesDescuento = raiz.getChild("Planesdescuento")) != null) {
                    ArrayList<PlanDescuentoTO> plsDescuento = new ArrayList<PlanDescuentoTO>();
                    List cantidadesNumeros = planesDescuento.getChildren("CantidadNumeros");
                    Iterator iteraCantidadesNumeros = cantidadesNumeros.iterator();
                    while (iteraCantidadesNumeros.hasNext()) {
                        PlanDescuentoTO planDescuentoTO = new PlanDescuentoTO();
                        Element cantidadNumeros = (Element)iteraCantidadesNumeros.next();
                        if (cantidadNumeros.getText() != null) {
                            planDescuentoTO.setCantidadNumeros(cantidadNumeros.getText());
                        } else {
                            planDescuentoTO.setCantidadNumeros("");
                        }
                        plsDescuento.add(planDescuentoTO);
                    }
                    int i = 0;
                    List descripciones = planesDescuento.getChildren("Descripcion");
                    Iterator iteraDescripciones = descripciones.iterator();
                    while (iteraDescripciones.hasNext()) {
                        Element descripcion;
                        ((PlanDescuentoTO)plsDescuento.get(i)).setDescripcion((descripcion = (Element)iteraDescripciones.next()).getText() != null ? descripcion.getText() : "");
                        ++i;
                    }
                    i = 0;
                    List status = planesDescuento.getChildren("Estatus");
                    Iterator iteraStatus = status.iterator();
                    while (iteraStatus.hasNext()) {
                        Element estat;
                        ((PlanDescuentoTO)plsDescuento.get(i)).setEstatus((estat = (Element)iteraStatus.next()).getText() != null ? estat.getText() : "");
                        ++i;
                    }
                    i = 0;
                    List fechasEfectivas = planesDescuento.getChildren("FechaEfectiva");
                    Iterator iteraFechasEfectivas = fechasEfectivas.iterator();
                    while (iteraFechasEfectivas.hasNext()) {
                        Element fechaEfectiva;
                        ((PlanDescuentoTO)plsDescuento.get(i)).setFechaEfectiva((fechaEfectiva = (Element)iteraFechasEfectivas.next()).getText() != null ? fechaEfectiva.getText() : "");
                        ++i;
                    }
                    i = 0;
                    List fechasExpiracion = planesDescuento.getChildren("FechaExpiracion");
                    Iterator iteraFechasExpiracion = fechasExpiracion.iterator();
                    while (iteraFechasExpiracion.hasNext()) {
                        Element fechaExpiracion;
                        ((PlanDescuentoTO)plsDescuento.get(i)).setFechaExpiracion((fechaExpiracion = (Element)iteraFechasExpiracion.next()).getText() != null ? fechaExpiracion.getText() : "");
                        ++i;
                    }
                    i = 0;
                    List planesDesc = planesDescuento.getChildren("PlanDescuento");
                    Iterator iteraPlanesDesc = planesDesc.iterator();
                    while (iteraPlanesDesc.hasNext()) {
                        Element planDesc;
                        ((PlanDescuentoTO)plsDescuento.get(i)).setPlanDescuento((planDesc = (Element)iteraPlanesDesc.next()).getText() != null ? planDesc.getText() : "");
                        ++i;
                    }
                    i = 0;
                    List plazosForzosos = planesDescuento.getChildren("PlazoForzoso");
                    Iterator iteraPlazosForzosos = plazosForzosos.iterator();
                    while (iteraPlazosForzosos.hasNext()) {
                        Element plazoForzoso;
                        plsDescuento.get(i).setPlazoForzoso((plazoForzoso = (Element)iteraPlazosForzosos.next()).getText() != null ? plazoForzoso.getText() : "");
                        ++i;
                    }
                    this.planesDescuento = plsDescuento;
                }
                if ((telefono = raiz.getChild("Telefono")) != null) {
                    this.telefono = telefono.getText();
                }
                if ((tipoCliente = raiz.getChild("TipoCliente")) != null) {
                    this.tipoCliente = tipoCliente.getText();
                }
            } else {
                ArrayList<ErrorCudisTO> erroresCudis = new ArrayList<ErrorCudisTO>();
                ErrorCudisTO errorCudisTO = new ErrorCudisTO();
                Element mensaje = raiz.getChild("MENSAJE");
                if (mensaje != null) {
                    this.mensaje = mensaje.getText();
                    errorCudisTO.setMensaje(this.mensaje);
                    erroresCudis.add(errorCudisTO);
                }
                this.errores = erroresCudis;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCancelaCargoGenerado() {
        return this.cancelaCargoGenerado;
    }

    public void setCancelaCargoGenerado(String cancelaCargoGenerado) {
        this.cancelaCargoGenerado = cancelaCargoGenerado;
    }

    public String getClavePlan() {
        return this.clavePlan;
    }

    public void setClavePlan(String clavePlan) {
        this.clavePlan = clavePlan;
    }

    public String getConfirmacion() {
        return this.confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public List<ErrorCudisTO> getErrores() {
        return this.errores;
    }

    public void setErrores(List<ErrorCudisTO> errores) {
        this.errores = errores;
    }

    public String getEstatusCuenta() {
        return this.estatusCuenta;
    }

    public void setEstatusCuenta(String estatusCuenta) {
        this.estatusCuenta = estatusCuenta;
    }

    public String getEstatusUsuario() {
        return this.estatusUsuario;
    }

    public void setEstatusUsuario(String estatusUsuario) {
        this.estatusUsuario = estatusUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPdscto() {
        return this.pdscto;
    }

    public void setPdscto(String pdscto) {
        this.pdscto = pdscto;
    }

    public List<PlanDescuentoTO> getPlanesDescuento() {
        return this.planesDescuento;
    }

    public void setPlanesDescuento(List<PlanDescuentoTO> planesDescuento) {
        this.planesDescuento = planesDescuento;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoCliente() {
        return this.tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("estatus: ").append(this.estatus);
        result.append(",mensaje: ").append(this.mensaje);
        result.append(",cancelaCargoGenerado: ").append(this.cancelaCargoGenerado);
        result.append(",clavePlan: ").append(this.clavePlan);
        result.append(",confirmacion: ").append(this.confirmacion);
        result.append(",cuenta: ").append(this.cuenta);
        if (this.errores != null) {
            result.append(",errores: ");
            for (ErrorCudisTO error : this.errores) {
                result.append(",codigo: ").append(error.getCodigo());
                result.append(",mensaje: ").append(error.getMensaje());
            }
        }
        result.append(",estatusCuenta: ").append(this.estatusCuenta);
        result.append(",estatusUsuario: ").append(this.estatusUsuario);
        result.append(",nombre: ").append(this.nombre);
        result.append(",pdscto: ").append(this.pdscto);
        if (this.planesDescuento != null) {
            for (PlanDescuentoTO planDescuentoTO : this.planesDescuento) {
                result.append(",planesDescuento: ");
                result.append(",cantidadNumeros: ").append(planDescuentoTO.getCantidadNumeros());
                result.append(",descripcion: ").append(planDescuentoTO.getDescripcion());
                result.append(",estatus: ").append(planDescuentoTO.getEstatus());
                result.append(",fechaEfectiva: ").append(planDescuentoTO.getFechaEfectiva());
                result.append(",fechaExpiracion: ").append(planDescuentoTO.getFechaExpiracion());
                result.append(",planDescuento: ").append(planDescuentoTO.getPlanDescuento());
                result.append(",plazoForzoso: ").append(planDescuentoTO.getPlazoForzoso());
            }
        }
        result.append(",telefono: ").append(this.telefono);
        result.append(",tipoCliente: ").append(this.tipoCliente);
        return result.toString();
    }
}

