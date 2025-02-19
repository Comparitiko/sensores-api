#!/bin/bash
set -e

# Lista de variables obligatorias
REQUIRED_VARS=(
"INFLUXDB_PORT"
"INFLUXDB_DB"
"INFLUXDB_ADMIN_USER"
"INFLUXDB_ADMIN_PASSWORD"
"INFLUXDB_HOST"
"JWT_EXPIRATION"
"INFLUXDB_ORG"
"INFLUXDB_BUCKET"
"INFLUXDB_TOKEN"
"DB_HOST"
"MARIADB_PORT"
"MARIADB_DATABASE"
"MARIADB_USER"
"MARIADB_PASSWORD"
"JWT_SECRET"
"SHOW_SQL"
"HIBERNATE_DDL_AUTO"
"ALLOWED_ORIGIN"
)

# Verificar que todas las variables obligatorias estén definidas
for env_var in "${REQUIRED_VARS[@]}"; do
  if [ -z "${!env_var}" ]; then
    echo "❌ The environment variable $env_var is required but is not set."
    exit 1
  fi
done

echo "✅ All ok, app is ready to run"

# Ejecuta el comando original (Java en este caso)
exec "$@"