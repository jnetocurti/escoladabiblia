-- Criando um novo usuário
CREATE USER escoladabiblia WITH PASSWORD 'escoladabiblia';

-- Criando uma nova base de dados
CREATE DATABASE escoladabiblia;

-- Concedendo todos previlégios para novo usuário na base de dados
GRANT ALL PRIVILEGES ON DATABASE escoladabiblia TO escoladabiblia;

-- Criando uma nova base de dados para testes
CREATE DATABASE escoladabiblia_teste;

-- Concedendo todos previlégios para novo usuário na base de dados de testes
GRANT ALL PRIVILEGES ON DATABASE escoladabiblia_teste TO escoladabiblia;