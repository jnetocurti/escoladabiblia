# From a latest official postgres image 
# https://hub.docker.com/_/postgres/
FROM postgres

ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_PASSWORD postgres

# Set a different locale
RUN localedef -i pt_BR -c -f UTF-8 -A /usr/share/locale/locale.alias pt_BR.utf8
ENV LANG pt_BR.utf8

# Create Users and Databases to Escola da Biblia
ADD create-users-and-databases.sql /docker-entrypoint-initdb.d/create-users-and-databases.sql

# Expose the PostgreSQL port
EXPOSE 5432