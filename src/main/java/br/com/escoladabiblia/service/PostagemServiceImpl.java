package br.com.escoladabiblia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.dto.PeriodoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

@Service
public class PostagemServiceImpl implements PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Override
	public void salvar(Postagem postagem) throws BusinessException {

		if (this.obterPostagemEmAberto() != null) {

			throw new BusinessException("erro.postagem.ja.existe.aberta");
		}

		postagemRepository.save(postagem);
	}

	@Override
	public Postagem obterPostagemEmAberto() {
		return postagemRepository.findLastOpenPostagem();
	}

	@Override
	public List<Postagem> listarPorPeriodo(PeriodoDTO periodo) {

		return postagemRepository.findByPeriod(periodo.getDataDe(), periodo.getDataAte());
	}

	@Override
	public byte[] processarPostagem(Long id, boolean encerrar) {
		
		return null;
	}

}
