package br.com.escoladabiblia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.exception.BusinessException;

@Service
public class PostagemServiceImpl implements PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Override
	public void salvar(Postagem postagem) throws BusinessException {

		if (postagemRepository.findLastOpenPostagem() != null) {

			throw new BusinessException("erro.postagem.ja.existe.aberta");
		}

		postagemRepository.save(postagem);
	}

}
