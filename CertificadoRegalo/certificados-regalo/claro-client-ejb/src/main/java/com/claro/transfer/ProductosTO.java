/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer;

import com.claro.util.Utils;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductosTO
implements Serializable {
    private static final long serialVersionUID = -5598682274188091814L;
    private BigDecimal precioBD;
    private long ptosARedimir;
    private int sobBonoEquipo;
    private String material;
    private String descripcion;
    private String marca;
    private String modelo;
    private String puntos;
    private String tipoPromocion;
    private String url;
    private String tecnologia;
    private String puntosSobrantes;
    private String indicador;
    private String aplicaPaqueteSMS;
    private String numeroSerieT;
    private String numeroSerieP;
    private String iccid;
    private BigDecimal precio;
    private BigDecimal precioIva;
    private String precioConFormato;
    private String precioIvaConFormato;
    private String descripcionDescuento;
    private BigDecimal descuento;
    private BigDecimal precioLista;
    private BigDecimal precioActivacion;
    private BigDecimal precioDescuento;
    private int bonosRoext;
    private int bonosAltoValor;
    private int bonosGap;
    private String fzaVentas;
    private String idProducto;
    private int valorPuntos;
    private int valorPuntosTmp;
    private Long valorMillas;
    private int difPesos;
    private String valorPuntosF;
    private String precioListaCF;
    private String precioActivacionCF;
    private String plan;
    private int bonosInbursa;
    private int bonosMarca;
    private BigDecimal descuentoInbursa = new BigDecimal(0);
    private BigDecimal descuentoMarca = new BigDecimal(0);
    private BigDecimal descuentoInbursaRestante = new BigDecimal(0);
    private BigDecimal descuentoMarcaRestante = new BigDecimal(0);
    private String aplicaPromocionGap;
    private int idPromocionGapCA;
    private int idPromocionGap;
    private int verPromocionGap;
    private String bonoDescuentoGap;
    private String productoM2KGap;
    private String nombrePromocionGap;
    private String aplicaEP;

    public String getPrecioActivacionCF() {
        if (this.precioActivacion != null) {
            try {
                this.precioActivacionCF = Utils.setFormatoDecimal(this.precioActivacion.toString());
            }
            catch (Exception e) {
                this.precioActivacionCF = this.precioActivacion.toString();
            }
        }
        return this.precioActivacionCF;
    }

    public String getPrecioListaCF() {
        if (this.precioLista != null) {
            try {
                this.precioListaCF = Utils.setFormatoDecimal(this.precioLista.toString());
            }
            catch (Exception e) {
                this.precioListaCF = this.precioLista.toString();
            }
        }
        return this.precioListaCF;
    }

    public int getValorPuntosTmp() {
        return this.valorPuntosTmp;
    }

    public void setValorPuntosTmp(int valorPuntosTmp) {
        this.valorPuntosTmp = valorPuntosTmp;
    }

    public String getValorPuntosF() {
        this.valorPuntosF = Utils.setFormatoPtos(this.valorPuntos);
        return this.valorPuntosF;
    }

    public String getPrecioConFormato() {
        return this.precioConFormato;
    }

    public void setPrecioConFormato(String precioConFormato) {
        this.precioConFormato = precioConFormato;
    }

    public String getPrecioIvaConFormato() {
        return this.precioIvaConFormato;
    }

    public void setPrecioIvaConFormato(String precioIvaConFormato) {
        this.precioIvaConFormato = precioIvaConFormato;
    }

    public int getDifPesos() {
        return this.difPesos;
    }

    public void setDifPesos(int difPesos) {
        this.difPesos = difPesos;
    }

    public BigDecimal getPrecioLista() {
        return this.precioLista;
    }

    public void setPrecioLista(BigDecimal precioLista) {
        this.precioLista = precioLista;
    }

    public BigDecimal getPrecioActivacion() {
        return this.precioActivacion;
    }

    public void setPrecioActivacion(BigDecimal precioActivacion) {
        this.precioActivacion = precioActivacion;
    }

    public static long getSerialVersionUID() {
        return -5598682274188091814L;
    }

    public String getAplicaPaqueteSMS() {
        if (this.aplicaPaqueteSMS == null) {
            return "0";
        }
        return this.aplicaPaqueteSMS;
    }

    public void setAplicaPaqueteSMS(String aplicaPaqueteSMS) {
        this.aplicaPaqueteSMS = aplicaPaqueteSMS;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPuntos() {
        return this.puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getTipoPromocion() {
        return this.tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getPuntosSobrantes() {
        return this.puntosSobrantes;
    }

    public void setPuntosSobrantes(String puntosSobrantes) {
        this.puntosSobrantes = puntosSobrantes;
    }

    public String getIndicador() {
        return this.indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public BigDecimal getPrecioBD() {
        return this.precioBD;
    }

    public void setPrecioBD(BigDecimal precioBD) {
        this.precioBD = precioBD;
    }

    public long getPtosARedimir() {
        return this.ptosARedimir;
    }

    public void setPtosARedimir(long ptosARedimir) {
        this.ptosARedimir = ptosARedimir;
    }

    public int getSobBonoEquipo() {
        return this.sobBonoEquipo;
    }

    public void setSobBonoEquipo(int sobBonoEquipo) {
        this.sobBonoEquipo = sobBonoEquipo;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecioIva() {
        return this.precioIva;
    }

    public void setPrecioIva(BigDecimal precioIva) {
        this.precioIva = precioIva;
    }

    public BigDecimal getDescuento() {
        return this.descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public int getBonosRoext() {
        return this.bonosRoext;
    }

    public void setBonosRoext(int bonosRoext) {
        this.bonosRoext = bonosRoext;
    }

    public int getBonosAltoValor() {
        return this.bonosAltoValor;
    }

    public void setBonosAltoValor(int bonosAltoValor) {
        this.bonosAltoValor = bonosAltoValor;
    }

    public String getNumeroSerieT() {
        if (this.numeroSerieT == null) {
            return "0";
        }
        return this.numeroSerieT;
    }

    public void setNumeroSerieT(String numeroSerieT) {
        this.numeroSerieT = numeroSerieT;
    }

    public String getNumeroSerieP() {
        if (this.numeroSerieP == null) {
            return "0";
        }
        return this.numeroSerieP;
    }

    public void setNumeroSerieP(String numeroSerieP) {
        this.numeroSerieP = numeroSerieP;
    }

    public String getIccid() {
        if (this.iccid == null) {
            return "0";
        }
        return this.iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getFzaVentas() {
        return this.fzaVentas;
    }

    public void setFzaVentas(String fzaVentas) {
        this.fzaVentas = fzaVentas;
    }

    public String getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getValorPuntos() {
        return this.valorPuntos;
    }

    public void setValorPuntos(int valorPuntos) {
        this.valorPuntos = valorPuntos;
    }

    public Long getValorMillas() {
        return this.valorMillas;
    }

    public void setValorMillas(Long valorMillas) {
        this.valorMillas = valorMillas;
    }

    public String getDescripcionDescuento() {
        return this.descripcionDescuento;
    }

    public void setDescripcionDescuento(String descripcionDescuento) {
        this.descripcionDescuento = descripcionDescuento;
    }

    public BigDecimal getPrecioDescuento() {
        return this.precioDescuento;
    }

    public void setPrecioDescuento(BigDecimal precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public int getBonosGap() {
        return this.bonosGap;
    }

    public void setBonosGap(int bonosGap) {
        this.bonosGap = bonosGap;
    }

    public String toString() {
        return "precioBD: " + this.precioBD + "ptosARedimir: " + "|ptosARedimir:" + this.ptosARedimir + "|" + "sobBonoEquipo: " + this.sobBonoEquipo + "material:" + this.material + "|" + "|ptosARedimir:" + this.descripcion + "|" + "|" + this.marca + "|" + "|modelo:" + this.modelo + "|" + "|puntos:" + this.puntos + "|" + "|tipoPromocion" + this.tipoPromocion + "|url: " + this.url + "|" + "|tecnologia:" + this.tecnologia + "|puntosSobrantes:" + this.puntosSobrantes + "|indicador:" + this.indicador + "|" + "|aplicaPaqueteSMS:" + this.aplicaPaqueteSMS + "|" + "|numeroSerieT:" + this.numeroSerieT + "|" + "|numeroSerieP:" + this.numeroSerieP + "|" + "|iccid:" + this.iccid + "|" + "|precio:" + this.precio + "|" + "|precioIva:" + this.precioIva + "|" + "|precioConFormato:" + this.precioConFormato + "|" + "|precioIvaConFormato:" + this.precioIvaConFormato + "|" + "|descripcionDescuento:" + this.descripcionDescuento + "|" + "|descuento: " + this.descuento + "|" + "|precioLista: " + this.precioLista + "|" + "|precioActivacion:" + this.precioActivacion + "|" + "|precioDescuento:" + this.precioDescuento + "|" + "|bonosRoext:" + this.bonosRoext + "|" + "|bonosAltoValor:" + this.bonosAltoValor + "|" + "|bonosGap:" + this.bonosGap + "|" + "|fzaVentas:" + this.fzaVentas + "|" + "|idProducto:" + this.idProducto + "|" + "|valorPuntos:" + this.valorPuntos + "|" + "|valorPuntosTmp:" + this.valorPuntosTmp + "|" + "|valorMillas: " + this.valorMillas + "|" + "|difPesos:" + this.difPesos + "|" + "|" + this.valorPuntosF + "|" + "|precioListaCF: " + this.precioListaCF + "|" + "|precioActivacionCF: " + this.precioActivacionCF + "|bonosInbursa: " + this.bonosInbursa + "|bonosMarca: " + this.bonosMarca;
    }

    public int getIdPromocionGapCA() {
        return this.idPromocionGapCA;
    }

    public void setIdPromocionGapCA(int idPromocionGapCA) {
        this.idPromocionGapCA = idPromocionGapCA;
    }

    public int getIdPromocionGap() {
        return this.idPromocionGap;
    }

    public void setIdPromocionGap(int idPromocionGap) {
        this.idPromocionGap = idPromocionGap;
    }

    public int getVerPromocionGap() {
        return this.verPromocionGap;
    }

    public void setVerPromocionGap(int verPromocionGap) {
        this.verPromocionGap = verPromocionGap;
    }

    public String getBonoDescuentoGap() {
        return this.bonoDescuentoGap;
    }

    public void setBonoDescuentoGap(String bonoDescuentoGap) {
        this.bonoDescuentoGap = bonoDescuentoGap;
    }

    public String getProductoM2KGap() {
        return this.productoM2KGap;
    }

    public void setProductoM2KGap(String productoM2KGap) {
        this.productoM2KGap = productoM2KGap;
    }

    public String getNombrePromocionGap() {
        return this.nombrePromocionGap;
    }

    public void setNombrePromocionGap(String nombrePromocionGap) {
        this.nombrePromocionGap = nombrePromocionGap;
    }

    public String getAplicaPromocionGap() {
        return this.aplicaPromocionGap;
    }

    public void setAplicaPromocionGap(String aplicaPromocionGap) {
        this.aplicaPromocionGap = aplicaPromocionGap;
    }

    public String getAplicaEP() {
        return this.aplicaEP;
    }

    public void setAplicaEP(String aplicaEP) {
        this.aplicaEP = aplicaEP;
    }

    public int getBonosInbursa() {
        return this.bonosInbursa;
    }

    public void setBonosInbursa(int bonosInbursa) {
        this.bonosInbursa = bonosInbursa;
    }

    public int getBonosMarca() {
        return this.bonosMarca;
    }

    public void setBonosMarca(int bonosMarca) {
        this.bonosMarca = bonosMarca;
    }

    public BigDecimal getDescuentoInbursa() {
        return this.descuentoInbursa;
    }

    public void setDescuentoInbursa(BigDecimal descuentoInbursa) {
        this.descuentoInbursa = descuentoInbursa;
    }

    public BigDecimal getDescuentoMarca() {
        return this.descuentoMarca;
    }

    public void setDescuentoMarca(BigDecimal descuentoMarca) {
        this.descuentoMarca = descuentoMarca;
    }

    public BigDecimal getDescuentoInbursaRestante() {
        return this.descuentoInbursaRestante;
    }

    public void setDescuentoInbursaRestante(BigDecimal descuentoInbursaRestante) {
        this.descuentoInbursaRestante = descuentoInbursaRestante;
    }

    public BigDecimal getDescuentoMarcaRestante() {
        return this.descuentoMarcaRestante;
    }

    public void setDescuentoMarcaRestante(BigDecimal descuentoMarcaRestante) {
        this.descuentoMarcaRestante = descuentoMarcaRestante;
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}

