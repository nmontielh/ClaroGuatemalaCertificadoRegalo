/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Constantes;

/*     */ import java.io.Serializable;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobileTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String telefono;
/*     */   private String region;
/*     */   private String cuenta;
/*     */   private String status;
/*     */   private String motivo;
/*     */   private String secuencia;
/*     */   private String addM2K;
/*     */   private String ciclo;
/*     */   private String planM2K;
/*     */   private String fecAltaUser;
/*     */   private String fecAddM2K;
/*     */   private String estCobranza;
/*     */   private String nombre;
/*     */   private String fecEfectiva;
/*     */   private String claseCredit;
/*     */   private String mesesCareg;
/*     */   private String addCareg;
/*     */   private String fechaSuspension;
/*     */   private String firstName;
/*     */   private String lastName;
/*     */   private String tecnologia;
/*     */   private String idTecnologia;
/*     */   private ArrayList<FacturaTO> facturas;
/*     */   private String esn;
/*     */   private String imei;
/*     */   private String iccid;
/*     */   private String cuentaPadre;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String TelContacto1;
/*     */   private String TelContacto2;
/*     */   private String ExtContacto1;
/*     */   private String ExtContacto2;
/*     */   private String cuentaCorreo;
/*     */   private String sPromFacturaAV;
/*     */   private double promedio;
/*     */   private int NoBajas;
/*     */   private String rfc;
/*     */   
/*     */   public int getNoBajas()
/*     */   {
/*  62 */     return this.NoBajas;
/*     */   }
/*     */   
/*     */   public void setNoBajas(int noBajas) {
/*  66 */     this.NoBajas = noBajas;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getPromedio()
/*     */   {
/*  73 */     if ((this.facturas != null) && (this.facturas.size() > 0)) {
/*     */       try {
/*  75 */         ListIterator<FacturaTO> iterator = this.facturas.listIterator();
/*  76 */         double suma = 0.0D;
/*  77 */         while (iterator.hasNext()) {
/*  78 */           FacturaTO facturaTO = (FacturaTO)iterator.next();
/*  79 */           suma += facturaTO.getMonto();
/*     */         }
/*  81 */         this.promedio = (suma / this.facturas.size());
/*     */       } catch (Exception localException) {}
/*     */     }
/*  84 */     return this.promedio;
/*     */   }
/*     */   
/*  87 */   public void setPromedio(double promedio) { this.promedio = promedio; }
/*     */   
/*     */   public String getFirstName() {
/*  90 */     return this.firstName;
/*     */   }
/*     */   
/*  93 */   public void setFirstName(String firstName) { this.firstName = firstName; }
/*     */   
/*     */   public String getLastName() {
/*  96 */     return this.lastName;
/*     */   }
/*     */   
/*  99 */   public void setLastName(String lastName) { this.lastName = lastName; }
/*     */   
/*     */   public String getFechaSuspension() {
/* 102 */     return this.fechaSuspension;
/*     */   }
/*     */   
/* 105 */   public void setFechaSuspension(String fechaSuspension) { this.fechaSuspension = fechaSuspension; }
/*     */   
/*     */   public String getCuenta() {
/* 108 */     return this.cuenta;
/*     */   }
/*     */   
/* 111 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public String getStatus() {
/* 114 */     return this.status;
/*     */   }
/*     */   
/* 117 */   public void setStatus(String status) { this.status = status; }
/*     */   
/*     */   public String getMotivo() {
/* 120 */     return this.motivo;
/*     */   }
/*     */   
/* 123 */   public void setMotivo(String motivo) { this.motivo = motivo; }
/*     */   
/*     */   public String getSecuencia() {
/* 126 */     return this.secuencia;
/*     */   }
/*     */   
/* 129 */   public void setSecuencia(String secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getAddM2K() {
/* 132 */     return this.addM2K;
/*     */   }
/*     */   
/* 135 */   public void setAddM2K(String addM2K) { this.addM2K = addM2K; }
/*     */   
/*     */   public String getCiclo() {
/* 138 */     return this.ciclo;
/*     */   }
/*     */   
/* 141 */   public void setCiclo(String ciclo) { this.ciclo = ciclo; }
/*     */   
/*     */   public String getPlanM2K() {
/* 144 */     return this.planM2K;
/*     */   }
/*     */   
/* 147 */   public void setPlanM2K(String planM2K) { this.planM2K = planM2K; }
/*     */   
/*     */   public String getFecAltaUser() {
/* 150 */     return this.fecAltaUser;
/*     */   }
/*     */   
/* 153 */   public void setFecAltaUser(String fecAltaUser) { this.fecAltaUser = fecAltaUser; }
/*     */   
/*     */   public String getFecAddM2K() {
/* 156 */     return this.fecAddM2K;
/*     */   }
/*     */   
/* 159 */   public void setFecAddM2K(String fecAddM2K) { this.fecAddM2K = fecAddM2K; }
/*     */   
/*     */   public String getEstCobranza() {
/* 162 */     return this.estCobranza;
/*     */   }
/*     */   
/* 165 */   public void setEstCobranza(String estCobranza) { this.estCobranza = estCobranza; }
/*     */   
/*     */   public String getNombre() {
/* 168 */     if (this.lastName != null) this.nombre = (this.lastName.trim() + " "); else
/* 169 */       this.nombre = "";
/* 170 */     if (this.firstName != null) { this.nombre = (this.nombre + this.firstName.trim() + " ");
/*     */     }
/* 172 */     return this.nombre.trim();
/*     */   }
/*     */   
/* 175 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*     */   
/*     */   public String getFecEfectiva() {
/* 178 */     return this.fecEfectiva;
/*     */   }
/*     */   
/* 181 */   public void setFecEfectiva(String fecEfectiva) { this.fecEfectiva = fecEfectiva; }
/*     */   
/*     */   public String getClaseCredit() {
/* 184 */     return this.claseCredit;
/*     */   }
/*     */   
/* 187 */   public void setClaseCredit(String claseCredit) { this.claseCredit = claseCredit; }
/*     */   
/*     */   public String getMesesCareg() {
/* 190 */     return this.mesesCareg;
/*     */   }
/*     */   
/* 193 */   public void setMesesCareg(String mesesCareg) { this.mesesCareg = mesesCareg; }
/*     */   
/*     */   public String getAddCareg() {
/* 196 */     return this.addCareg;
/*     */   }
/*     */   
/* 199 */   public void setAddCareg(String addCareg) { this.addCareg = addCareg; }
/*     */   
/*     */   public String getSPromFacturaAV() {
/* 202 */     return this.sPromFacturaAV;
/*     */   }
/*     */   
/* 205 */   public void setSPromFacturaAV(String promFacturaAV) { this.sPromFacturaAV = promFacturaAV; }
/*     */   
/*     */   public ArrayList<FacturaTO> getFacturas() {
/* 208 */     return this.facturas;
/*     */   }
/*     */   
/* 211 */   public void setFacturas(ArrayList<FacturaTO> facturas) { this.facturas = facturas; }
/*     */   
/*     */   public String getTelefono() {
/* 214 */     return this.telefono;
/*     */   }
/*     */   
/* 217 */   public void setTelefono(String telefono) { this.telefono = telefono; }
/*     */   
/*     */   public String getTecnologia() {
/* 220 */     if (this.idTecnologia.equals("0")) {
/* 221 */       this.tecnologia = "TDMA";
/*     */     } else
/* 223 */       this.tecnologia = "GSM";
/* 224 */     return this.tecnologia;
/*     */   }
/*     */   
/* 227 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/*     */   public String getIdTecnologia() {
/* 230 */     return this.idTecnologia;
/*     */   }
/*     */   
/* 233 */   public void setIdTecnologia(String idTecnologia) { this.idTecnologia = idTecnologia; }
/*     */   
/*     */   public String getEsn() {
/* 236 */     return this.esn;
/*     */   }
/*     */   
/* 239 */   public void setEsn(String esn) { this.esn = esn; }
/*     */   
/*     */   public String getImei() {
/* 242 */     return this.imei;
/*     */   }
/*     */   
/* 245 */   public void setImei(String imei) { this.imei = imei; }
/*     */   
/*     */   public String getIccid() {
/* 248 */     return this.iccid;
/*     */   }
/*     */   
/* 251 */   public void setIccid(String iccid) { this.iccid = iccid; }
/*     */   
/*     */   public String getCuentaPadre() {
/* 254 */     return this.cuentaPadre;
/*     */   }
/*     */   
/* 257 */   public void setCuentaPadre(String cuentaPadre) { this.cuentaPadre = cuentaPadre; }
/*     */   
/*     */   public String getMarca() {
/* 260 */     return this.marca;
/*     */   }
/*     */   
/* 263 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/*     */   public String getModelo() {
/* 266 */     return this.modelo;
/*     */   }
/*     */   
/* 269 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*     */   public Date getFechaAddendum()
/*     */   {
/*     */     try {
/* 274 */       return Constantes.DATEFORMATyyyy_MM_dd.parse(this.fecAddM2K); } catch (Exception e) {}
/* 275 */     return null;
/*     */   }
/*     */   
/*     */   public Date getFechaEfectiva() {
/*     */     try {
/* 280 */       return Constantes.DATEFORMATyyyy_MM_dd.parse(this.fecEfectiva); } catch (Exception e) {}
/* 281 */     return null;
/*     */   }
/*     */   
/*     */   public Date getFechaAltaUser() {
/*     */     try {
/* 286 */       return Constantes.DATEFORMATyyyy_MM_dd.parse(this.fecAltaUser); } catch (Exception e) {}
/* 287 */     return null;
/*     */   }
/*     */   
/* 290 */   public String getTelContacto1() { return this.TelContacto1; }
/*     */   
/*     */   public void setTelContacto1(String telContacto1) {
/* 293 */     this.TelContacto1 = telContacto1;
/*     */   }
/*     */   
/* 296 */   public String getTelContacto2() { return this.TelContacto2; }
/*     */   
/*     */   public void setTelContacto2(String telContacto2) {
/* 299 */     this.TelContacto2 = telContacto2;
/*     */   }
/*     */   
/* 302 */   public String getExtContacto1() { return this.ExtContacto1; }
/*     */   
/*     */   public void setExtContacto1(String extContacto1) {
/* 305 */     this.ExtContacto1 = extContacto1;
/*     */   }
/*     */   
/* 308 */   public String getExtContacto2() { return this.ExtContacto2; }
/*     */   
/*     */   public void setExtContacto2(String extContacto2) {
/* 311 */     this.ExtContacto2 = extContacto2;
/*     */   }
/*     */   
/* 314 */   public String getCuentaCorreo() { return this.cuentaCorreo; }
/*     */   
/*     */   public void setCuentaCorreo(String cuentaCorreo) {
/* 317 */     this.cuentaCorreo = cuentaCorreo;
/*     */   }
/*     */   
/* 320 */   public String getRegion() { return this.region; }
/*     */   
/*     */   public void setRegion(String region) {
/* 323 */     this.region = region;
/*     */   }
/*     */   
/*     */   public String getRfc() {
/* 327 */     return this.rfc;
/*     */   }
/*     */   
/* 330 */   public void setRfc(String rfc) { this.rfc = rfc; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/MobileTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */