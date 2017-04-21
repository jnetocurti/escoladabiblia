package br.com.escoladabiblia.service;

import java.util.List;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.util.dto.PeriodoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;
import net.sf.jasperreports.engine.JRException;

public interface PostagemService {

	void salvar(Postagem postagem) throws BusinessException;

	Postagem obterPostagemEmAberto();

	List<Postagem> listarPorPeriodo(PeriodoDTO periodo);

	byte[] processarPostagem(Long id, boolean encerrar) throws JRException;

}
