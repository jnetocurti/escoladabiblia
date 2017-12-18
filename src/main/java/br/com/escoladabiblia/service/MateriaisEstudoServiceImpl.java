package br.com.escoladabiblia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.MaterialEstudo;
import br.com.escoladabiblia.repository.MaterialEstudoRepository;

@Service
public class MateriaisEstudoServiceImpl implements MateriaisEstudoService {

	@Autowired
	private MaterialEstudoRepository materialEstudoRepository;

	@Override
	public List<MaterialEstudo> findAll() {
		return materialEstudoRepository.findAll(new Sort("numeroOrdem"));
	}

	@Override
	public void salvarTodos(List<MaterialEstudo> materias) {
		materialEstudoRepository.save(materias);
	}

	@Override
	public void salvar(MaterialEstudo material) {
		materialEstudoRepository.save(material);
	}

}
