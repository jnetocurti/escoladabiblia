package br.com.escoladabiblia.service;

import java.util.List;

import br.com.escoladabiblia.model.MaterialEstudo;

public interface MateriaisEstudoService {

	List<MaterialEstudo> findAll();

	void salvarTodos(List<MaterialEstudo> materias);

	void salvar(MaterialEstudo material);

}
