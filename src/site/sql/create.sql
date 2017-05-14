create table alunos (id  bigserial not null, foi_batizado boolean, data_nascimento date, frequentou_igreja boolean, nome varchar(50) not null, observacao varchar(255), possui_biblia boolean, sexo varchar(1), endereco_id int8, primary key (id))
create table atividades_estudo (id  bigserial not null, atividade_encerrada boolean, data_retorno_estudo date, nota float4, aluno_id int8 not null, material_id int8 not null, postagem_id int8 not null, primary key (id))
create table biblias_enviadas (id  bigserial not null, atividade_estudo_id int8 not null, postagem_id int8 not null, primary key (id))
create table caracterizacoes (id  bigserial not null, ativa boolean not null, data_registro date not null, tipo_caracterizacao varchar(50) not null, aluno_id int8, primary key (id))
create table certificados_enviados (id  bigserial not null, atividade_estudo_id int8 not null, postagem_id int8 not null, primary key (id))
create table controle_importacoes (id  bigserial not null, tipo_importacao varchar(20) not null, primary key (id))
create table enderecos (id  bigserial not null, bairro varchar(100), cep varchar(9), cidade varchar(100) not null, complemento varchar(100), logradouro varchar(100) not null, numero integer default 0 not null, estado_id int8 not null, primary key (id))
create table estados (id  bigserial not null, descricao varchar(50) not null, uf varchar(2) not null, primary key (id))
create table materiais_estudo (id  bigserial not null, nome varchar(100) not null, numero_ordem int4 not null, tipo_envelope int4 not null, primary key (id))
create table postagens (id  bigserial not null, data_efetiva_envio date, data_prevista_envio date not null, primary key (id))
create table presidiarios (cela int4, complemento varchar(50), matricula varchar(30), raio int4, id int8 not null, presidio_id int8 not null, primary key (id))
create table presidios (id  bigserial not null, nome varchar(100) not null, endereco_id int8 not null, primary key (id))
alter table controle_importacoes add constraint tipo_importacao_uk unique (tipo_importacao)
alter table materiais_estudo add constraint numero_ordem_uk unique (numero_ordem)
alter table alunos add constraint endereco_fk foreign key (endereco_id) references enderecos
alter table atividades_estudo add constraint aluno_fk foreign key (aluno_id) references alunos
alter table atividades_estudo add constraint material_fk foreign key (material_id) references materiais_estudo
alter table atividades_estudo add constraint postagem_fk foreign key (postagem_id) references postagens
alter table biblias_enviadas add constraint atividade_estudo_fk foreign key (atividade_estudo_id) references atividades_estudo
alter table biblias_enviadas add constraint postagem_fk foreign key (postagem_id) references postagens
alter table caracterizacoes add constraint aluno_fk foreign key (aluno_id) references alunos
alter table certificados_enviados add constraint atividade_estudo_fk foreign key (atividade_estudo_id) references atividades_estudo
alter table certificados_enviados add constraint postagem_fk foreign key (postagem_id) references postagens
alter table enderecos add constraint estado_fk foreign key (estado_id) references estados
alter table presidiarios add constraint presidio_fk foreign key (presidio_id) references presidios
alter table presidiarios add constraint caracterizacao_fk foreign key (id) references caracterizacoes
alter table presidios add constraint endereco_fk foreign key (endereco_id) references enderecos
