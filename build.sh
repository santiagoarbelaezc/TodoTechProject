#!/bin/bash
echo "Verificando java en el sistema..."
which java

echo "Versión de Java instalada:"
java -version || echo "java no encontrado"

chmod +x gradlew
./gradlew build
echo "Build finalizado."
