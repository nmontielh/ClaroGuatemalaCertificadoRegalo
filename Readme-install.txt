1. Bajar 1a version de maven
2. descomprimir y colocar en un directorio sin espacios en blanco
3. editar el archivo settings.xml, agregar el local_repository
4. configurar la variable de ambiente
configuring .bash_profile
psycho-punk-2:~$ echo $M2_HOME
/opt/apache/maven/apache-maven-3.3.9


------------
1. ir a un directorio
git clone https://github.com/nmontielh/ClaroGuatemalaCertificadoRegalo.git

/Users/nmontielh/Documents/hitss/claro-guatemala/ClaroGuatemalaCertificadoRegalo/CertificadoRegalo/certificados-regalo

3. instalar dependcias
install 
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/ws-AplicaProductoIOH_Cliente.jar -DgroupId=com.telcel     -DartifactId=crm-gscrm -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/MensajesSMS_Cliente.jar -DgroupId=com.telcel     -DartifactId=gscrm-dccrm-sms -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/clienteCRMM2K.jar -DgroupId=com.telcel     -DartifactId=gscrm-dccrm-wsm2k -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/ClienteGAPWebServiceApplication.jar -DgroupId=com.telcel     -DartifactId=promociones -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/gap-service.jar -DgroupId=com.telcel     -DartifactId=sds.gccrm.dccrm.ws.gap -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/ClienteACRWebService.jar -DgroupId=com.telcel     -DartifactId=gscrm.dccrm -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/com.ibm.mq.jar -DgroupId=com.ibm     -DartifactId=mq -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/com.ibm.mqbind.jar -DgroupId=com.ibm     -DartifactId=mqbind -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/connector.jar -DgroupId=javax.resource     -DartifactId=cci-spi -Dversion=1.0 -Dpackaging=jar
 mvn install:install-file -DgroupId=javax.jms -DartifactId=jms -Dversion=1.1 -Dpackaging=jar -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/jms-1.1.jar -DgeneratePom=true

 mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/ojdbc6.jar -DgeneratePom=true
 mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar -Dfile=/Users/nmontielh/Documents/hitss/claro-guatemala/librerias/ojdbc7.jar -DgeneratePom=true
 

 mvn clean install

2. desplazarse a 
cd /Users/nmontielh/Documents/hitss/claro-guatemala/ClaroGuatemalaCertificadoRegalo/CertificadoRegalo/certificados-regalo-v2.0
mvn clean install -Dmaven.test.skip=true



3. ejecutar cmd (baja las dependencias y compila)
mvn clean install -Dmaven.test.skip=true








4. configurar el eclipse