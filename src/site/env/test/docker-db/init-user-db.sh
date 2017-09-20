#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    
    CREATE USER escoladabiblia WITH PASSWORD 'escoladabiblia';

    CREATE DATABASE escoladabiblia_teste;

    GRANT ALL PRIVILEGES ON DATABASE escoladabiblia_teste TO escoladabiblia;

EOSQL
