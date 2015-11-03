/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import com.claro.transfer.MobileTO;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobileServiceTO
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
/*     */   
/*     */   public MobileServiceTO() {}
/*     */   
/*     */   public MobileServiceTO(MobileTO mobileTO)
/*     */   {
/*  53 */     agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
/*  54 */     this.telefono = mobileTO.getTelefono();
/*  55 */     this.region = mobileTO.getRegion();
/*  56 */     this.cuenta = mobileTO.getCuenta();
/*  57 */     this.status = mobileTO.getStatus();
/*  58 */     this.motivo = mobileTO.getMotivo();
/*  59 */     this.secuencia = mobileTO.getSecuencia();
/*  60 */     this.addM2K = mobileTO.getAddM2K();
/*  61 */     this.ciclo = mobileTO.getCiclo();
/*  62 */     this.planM2K = mobileTO.getPlanM2K();
/*  63 */     this.fecAltaUser = mobileTO.getFecAltaUser();
/*  64 */     this.fecAddM2K = mobileTO.getFecAddM2K();
/*  65 */     this.estCobranza = mobileTO.getEstCobranza();
/*  66 */     this.nombre = mobileTO.getNombre();
/*  67 */     this.fecEfectiva = mobileTO.getFecEfectiva();
/*  68 */     this.claseCredit = mobileTO.getClaseCredit();
/*  69 */     this.mesesCareg = mobileTO.getMesesCareg();
/*  70 */     this.addCareg = mobileTO.getAddCareg();
/*  71 */     this.fechaSuspension = mobileTO.getFechaSuspension();
/*  72 */     this.firstName = mobileTO.getFirstName();
/*  73 */     this.lastName = mobileTO.getLastName();
/*  74 */     this.tecnologia = mobileTO.getTecnologia();
/*  75 */     this.idTecnologia = mobileTO.getIdTecnologia();
/*  76 */     this.esn = mobileTO.getEsn();
/*  77 */     this.imei = mobileTO.getImei();
/*  78 */     this.iccid = mobileTO.getIccid();
/*  79 */     this.cuentaPadre = mobileTO.getCuentaPadre();
/*  80 */     this.marca = mobileTO.getMarca();
/*  81 */     this.modelo = mobileTO.getModelo();
/*  82 */     this.TelContacto1 = mobileTO.getTelContacto1();
/*  83 */     this.TelContacto2 = mobileTO.getTelContacto2();
/*  84 */     this.ExtContacto1 = mobileTO.getExtContacto1();
/*  85 */     this.ExtContacto2 = mobileTO.getExtContacto2();
/*  86 */     this.cuentaCorreo = mobileTO.getCuentaCorreo();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getTelefono()
/*     */   {
/*  93 */     return this.telefono;
/*     */   }
/*     */   
/*     */   public void setTelefono(String telefono)
/*     */   {
/*  98 */     this.telefono = telefono;
/*     */   }
/*     */   
/*     */   public String getCuenta()
/*     */   {
/* 103 */     return this.cuenta;
/*     */   }
/*     */   
/*     */   public void setCuenta(String cuenta)
/*     */   {
/* 108 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/*     */   public String getStatus()
/*     */   {
/* 113 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status)
/*     */   {
/* 118 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getMotivo()
/*     */   {
/* 123 */     return this.motivo;
/*     */   }
/*     */   
/*     */   public void setMotivo(String motivo)
/*     */   {
/* 128 */     this.motivo = motivo;
/*     */   }
/*     */   
/*     */   public String getSecuencia()
/*     */   {
/* 133 */     return this.secuencia;
/*     */   }
/*     */   
/*     */   public void setSecuencia(String secuencia)
/*     */   {
/* 138 */     this.secuencia = secuencia;
/*     */   }
/*     */   
/*     */   public String getAddM2K()
/*     */   {
/* 143 */     return this.addM2K;
/*     */   }
/*     */   
/*     */   public void setAddM2K(String addM2K)
/*     */   {
/* 148 */     this.addM2K = addM2K;
/*     */   }
/*     */   
/*     */   public String getCiclo()
/*     */   {
/* 153 */     return this.ciclo;
/*     */   }
/*     */   
/*     */   public void setCiclo(String ciclo)
/*     */   {
/* 158 */     this.ciclo = ciclo;
/*     */   }
/*     */   
/*     */   public String getPlanM2K()
/*     */   {
/* 163 */     return this.planM2K;
/*     */   }
/*     */   
/*     */   public void setPlanM2K(String planM2K)
/*     */   {
/* 168 */     this.planM2K = planM2K;
/*     */   }
/*     */   
/*     */   public String getFecAltaUser()
/*     */   {
/* 173 */     return this.fecAltaUser;
/*     */   }
/*     */   
/*     */   public void setFecAltaUser(String fecAltaUser)
/*     */   {
/* 178 */     this.fecAltaUser = fecAltaUser;
/*     */   }
/*     */   
/*     */   public String getFecAddM2K()
/*     */   {
/* 183 */     return this.fecAddM2K;
/*     */   }
/*     */   
/*     */   public void setFecAddM2K(String fecAddM2K)
/*     */   {
/* 188 */     this.fecAddM2K = fecAddM2K;
/*     */   }
/*     */   
/*     */   public String getEstCobranza()
/*     */   {
/* 193 */     return this.estCobranza;
/*     */   }
/*     */   
/*     */   public void setEstCobranza(String estCobranza)
/*     */   {
/* 198 */     this.estCobranza = estCobranza;
/*     */   }
/*     */   
/*     */   public String getNombre()
/*     */   {
/* 203 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre)
/*     */   {
/* 208 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getFecEfectiva()
/*     */   {
/* 213 */     return this.fecEfectiva;
/*     */   }
/*     */   
/*     */   public void setFecEfectiva(String fecEfectiva)
/*     */   {
/* 218 */     this.fecEfectiva = fecEfectiva;
/*     */   }
/*     */   
/*     */   public String getClaseCredit()
/*     */   {
/* 223 */     return this.claseCredit;
/*     */   }
/*     */   
/*     */   public void setClaseCredit(String claseCredit)
/*     */   {
/* 228 */     this.claseCredit = claseCredit;
/*     */   }
/*     */   
/*     */   public String getMesesCareg()
/*     */   {
/* 233 */     return this.mesesCareg;
/*     */   }
/*     */   
/*     */   public void setMesesCareg(String mesesCareg)
/*     */   {
/* 238 */     this.mesesCareg = mesesCareg;
/*     */   }
/*     */   
/*     */   public String getAddCareg()
/*     */   {
/* 243 */     return this.addCareg;
/*     */   }
/*     */   
/*     */   public void setAddCareg(String addCareg)
/*     */   {
/* 248 */     this.addCareg = addCareg;
/*     */   }
/*     */   
/*     */   public String getFechaSuspension()
/*     */   {
/* 253 */     return this.fechaSuspension;
/*     */   }
/*     */   
/*     */   public void setFechaSuspension(String fechaSuspension)
/*     */   {
/* 258 */     this.fechaSuspension = fechaSuspension;
/*     */   }
/*     */   
/*     */   public String getFirstName()
/*     */   {
/* 263 */     return this.firstName;
/*     */   }
/*     */   
/*     */   public void setFirstName(String firstName)
/*     */   {
/* 268 */     this.firstName = firstName;
/*     */   }
/*     */   
/*     */   public String getLastName()
/*     */   {
/* 273 */     return this.lastName;
/*     */   }
/*     */   
/*     */   public void setLastName(String lastName)
/*     */   {
/* 278 */     this.lastName = lastName;
/*     */   }
/*     */   
/*     */   public String getTecnologia()
/*     */   {
/* 283 */     return this.tecnologia;
/*     */   }
/*     */   
/*     */   public void setTecnologia(String tecnologia)
/*     */   {
/* 288 */     this.tecnologia = tecnologia;
/*     */   }
/*     */   
/*     */   public String getIdTecnologia()
/*     */   {
/* 293 */     return this.idTecnologia;
/*     */   }
/*     */   
/*     */   public void setIdTecnologia(String idTecnologia)
/*     */   {
/* 298 */     this.idTecnologia = idTecnologia;
/*     */   }
/*     */   
/*     */   public String getEsn()
/*     */   {
/* 303 */     return this.esn;
/*     */   }
/*     */   
/*     */   public void setEsn(String esn)
/*     */   {
/* 308 */     this.esn = esn;
/*     */   }
/*     */   
/*     */   public String getImei()
/*     */   {
/* 313 */     return this.imei;
/*     */   }
/*     */   
/*     */   public void setImei(String imei)
/*     */   {
/* 318 */     this.imei = imei;
/*     */   }
/*     */   
/*     */   public String getIccid()
/*     */   {
/* 323 */     return this.iccid;
/*     */   }
/*     */   
/*     */   public void setIccid(String iccid)
/*     */   {
/* 328 */     this.iccid = iccid;
/*     */   }
/*     */   
/*     */   public String getCuentaPadre()
/*     */   {
/* 333 */     return this.cuentaPadre;
/*     */   }
/*     */   
/*     */   public void setCuentaPadre(String cuentaPadre)
/*     */   {
/* 338 */     this.cuentaPadre = cuentaPadre;
/*     */   }
/*     */   
/*     */   public String getMarca()
/*     */   {
/* 343 */     return this.marca;
/*     */   }
/*     */   
/*     */   public void setMarca(String marca)
/*     */   {
/* 348 */     this.marca = marca;
/*     */   }
/*     */   
/*     */   public String getModelo()
/*     */   {
/* 353 */     return this.modelo;
/*     */   }
/*     */   
/*     */   public void setModelo(String modelo)
/*     */   {
/* 358 */     this.modelo = modelo;
/*     */   }
/*     */   
/*     */   public String getTelContacto1()
/*     */   {
/* 363 */     return this.TelContacto1;
/*     */   }
/*     */   
/*     */   public void setTelContacto1(String telContacto1)
/*     */   {
/* 368 */     this.TelContacto1 = telContacto1;
/*     */   }
/*     */   
/*     */   public String getTelContacto2()
/*     */   {
/* 373 */     return this.TelContacto2;
/*     */   }
/*     */   
/*     */   public void setTelContacto2(String telContacto2)
/*     */   {
/* 378 */     this.TelContacto2 = telContacto2;
/*     */   }
/*     */   
/*     */   public String getExtContacto1()
/*     */   {
/* 383 */     return this.ExtContacto1;
/*     */   }
/*     */   
/*     */   public void setExtContacto1(String extContacto1)
/*     */   {
/* 388 */     this.ExtContacto1 = extContacto1;
/*     */   }
/*     */   
/*     */   public String getExtContacto2()
/*     */   {
/* 393 */     return this.ExtContacto2;
/*     */   }
/*     */   
/*     */   public void setExtContacto2(String extContacto2)
/*     */   {
/* 398 */     this.ExtContacto2 = extContacto2;
/*     */   }
/*     */   
/*     */   public String getCuentaCorreo()
/*     */   {
/* 403 */     return this.cuentaCorreo;
/*     */   }
/*     */   
/*     */   public void setCuentaCorreo(String cuentaCorreo)
/*     */   {
/* 408 */     this.cuentaCorreo = cuentaCorreo;
/*     */   }
/*     */   
/*     */   public String getRegion() {
/* 412 */     return this.region;
/*     */   }
/*     */   
/*     */   public void setRegion(String region) {
/* 416 */     this.region = region;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/MobileServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */