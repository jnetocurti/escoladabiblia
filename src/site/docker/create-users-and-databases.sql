-- Criando um novo usu�rio
CREATE USER escoladabiblia WITH PASSWORD 'escoladabiblia';

-- Criando uma nova base de dados
CREATE DATABASE escoladabiblia;

-- Concedendo todos previl�gios para novo usu�rio na base de dados
GRANT ALL PRIVILEGES ON DATABASE escoladabiblia TO escoladabiblia;