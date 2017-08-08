package br.com.escoladabiblia.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.repository.PostagemRepository;
import br.com.escoladabiblia.util.dto.PeriodoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;
import net.sf.jasperreports.engine.JRException;

@Service
@Transactional(readOnly = true)
public class PostagemServiceImpl implements PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private EnvelopeService envelopeService;

	@Override
	@Transactional(readOnly = false)
	public void salvar(Postagem postagem) throws BusinessException {

		if (this.obterPostagemEmAberto() != null) {

			throw new BusinessException("erro.postagem.ja.existe.aberta");
		}

		postagemRepository.save(postagem);
	}

	@Override
	public Postagem obterPostagemEmAberto() {

		return postagemRepository.findLastOpen();
	}

	@Override
	public List<Postagem> listarPorPeriodo(PeriodoDTO periodo) {

		return postagemRepository.findByPeriod(periodo.getDataDe(), periodo.getDataAte());
	}

	@Override
	@Transactional(readOnly = false)
	public byte[] processarPostagem(Long id, boolean encerrar) throws JRException, IOException {

		if (encerrar) {
			this.finalizarPostagem(id);
		}

		return envelopeService.obterEnvelopesPostagem(id);

	}

	private void finalizarPostagem(Long id) {

		Postagem postagem = postagemRepository.findOne(id);

		postagem.setDataEfetivaEnvio(Calendar.getInstance());

		postagemRepository.save(postagem);
	}

}
