package br.com.escoladabiblia.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.repository.AlunoRepository;
import br.com.escoladabiblia.repository.MaterialEstudoRepository;
import br.com.escoladabiblia.util.dto.EdicaoAtividadesEstudoDTO;

@Service
public class AtividadesEstudoServiceImpl implements AtividadesEstudoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private MaterialEstudoRepository materialEstudoRepository;

	@Override
	public EdicaoAtividadesEstudoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) {

		final EdicaoAtividadesEstudoDTO edicao = new EdicaoAtividadesEstudoDTO(alunoRepository.findOne(id),
				Calendar.getInstance());

		edicao.getMateriais().addAll(materialEstudoRepository.findAll());

		return edicao;
	}

}
