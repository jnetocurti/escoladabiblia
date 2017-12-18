ALTER TABLE materiais_estudo ADD COLUMN corrigible boolean default true not null;
ALTER TABLE materiais_estudo ADD COLUMN ativo boolean default true not null;
ALTER TABLE materiais_estudo DROP CONSTRAINT numero_ordem_uk;