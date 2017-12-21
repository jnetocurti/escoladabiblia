ALTER TABLE alunos ADD COLUMN ultima_atividade date;

UPDATE alunos al SET ultima_atividade = (
 SELECT max(p.data_prevista_envio)
   FROM postagens p
   JOIN atividades_estudo a ON a.postagem_id = p.id
  WHERE a.aluno_id = al.id
 );