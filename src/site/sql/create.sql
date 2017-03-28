create table alunos (id  bigserial not null, foi_batizado boolean, data_nascimento date, frequentou_igreja boolean, nome varchar(50) not null, observacao varchar(255), possui_biblia boolean, sexo int4, endereco_id int8, primary key (id))
create table caracterizacoes (id  bigserial not null, ativa boolean not null, data_registro date not null, tipo_caracterizacao varchar(50) not null, aluno_id int8, primary key (id))
create table enderecos (id  bigserial not null, bairro varchar(100), cep varchar(9), cidade varchar(100) not null, complemento varchar(100), logradouro varchar(100) not null, numero integer default 0 not null, estado_id int8 not null, primary key (id))
create table estados (id  bigserial not null, descricao varchar(50) not null, uf varchar(2) not null, primary key (id))
create table presidiarios (cela int4, complemento varchar(50), matricula varchar(30), raio int4, id int8 not null, presidio_id int8 not null, primary key (id))
create table presidios (id  bigserial not null, nome varchar(100) not null, endereco_id int8 not null, primary key (id))
alter table alunos add constraint endereco_fk foreign key (endereco_id) references enderecos
alter table caracterizacoes add constraint aluno_fk foreign key (aluno_id) references alunos
alter table enderecos add constraint estado_fk foreign key (estado_id) references estados
alter table presidiarios add constraint presidio_fk foreign key (presidio_id) references presidios
alter table presidiarios add constraint FKb461e6vmkuh9n54ldbpoo4yoq foreign key (id) references caracterizacoes
alter table presidios add constraint endereco_fk foreign key (endereco_id) references enderecos
