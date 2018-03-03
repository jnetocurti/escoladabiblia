# https://hub.docker.com/_/postgres/
FROM postgres:9.4

ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_PASSWORD postgres

# Set a different locale
RUN localedef -i pt_BR -c -f UTF-8 -A /usr/share/locale/locale.alias pt_BR.utf8
ENV LANG pt_BR.utf8

# Creation User and Database to Escola da Biblia
ADD init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh

# Creation schema and insert data
ADD ./scripts/create-schema.sql /tmp/scripts/create-schema.sql
ADD ./scripts/insert-data.sql /tmp/scripts/insert-data.sql

