#!/bin/bash

echo "Verificando java en el sistema..."
which java

echo "Versión de Java instalada:"
java -version

echo "JAVA_HOME actual:"
echo $JAVA_HOME

echo "Contenido de /usr/lib/jvm/:"
ls -l /usr/lib/jvm/

echo "Asignando permisos de ejecución a gradlew..."
chmod +x gradlew

echo "Iniciando compilación con Gradle..."
./gradlew build

echo "Build finalizado."
