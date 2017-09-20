#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    
    CREATE USER escoladabiblia WITH PASSWORD 'escoladabiblia';

    CREATE DATABASE escoladabiblia;

    GRANT ALL PRIVILEGES ON DATABASE escoladabiblia TO escoladabiblia;

    \connect escoladabiblia escoladabiblia

    \i /tmp/scripts/create-schema.sql 

    \i /tmp/scripts/insert-data.sql

EOSQL
