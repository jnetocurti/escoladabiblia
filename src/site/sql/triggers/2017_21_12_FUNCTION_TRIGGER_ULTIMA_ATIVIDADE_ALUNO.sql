CREATE OR REPLACE FUNCTION ultima_atividade_aluno_function() RETURNS TRIGGER AS $BODY$
BEGIN
    IF(TG_OP = 'INSERT') THEN
    UPDATE alunos SET ultima_atividade =
	(
	  SELECT p.data_prevista_envio
	    FROM postagens p
	    JOIN atividades_estudo a ON a.postagem_id = p.id
	   WHERE a.id = NEW.id
	);
     ELSIF(TG_OP = 'DELETE') THEN
     UPDATE alunos SET ultima_atividade =
 	(
 	SELECT max(p.data_prevista_envio)
 	  FROM postagens p
 	  JOIN atividades_estudo a ON a.postagem_id = p.id
 	 WHERE a.aluno_id = OLD.aluno_id
 	 );
     END IF;
RETURN NULL;
END;
$BODY$ LANGUAGE PLPGSQL;

CREATE TRIGGER trigger_ultima_atividade_aluno AFTER
INSERT
OR
DELETE ON atividades_estudo
FOR EACH ROW EXECUTE PROCEDURE ultima_atividade_aluno_function();
