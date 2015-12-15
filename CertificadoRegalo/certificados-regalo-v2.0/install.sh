#Shell para instalar y copiar los artefactos en otro lugar
# Si no se tiene acceso a la red se recomienda usar el -o en ves del -U
# -o = modo offline
# -U = Update todas las dependencias
DIR_DESTINO=/Users/nmontielh/tmp


echo mvn clean install -Dmaven.test.skip=true -U
mvn clean install -Dmaven.test.skip=true -U


# copiamos el artefacto a un directorio para desplegar
echo cp web-services/target/certificado-services.war $DIR_DESTINO
cp web-services/target/certificado-services.war $DIR_DESTINO
