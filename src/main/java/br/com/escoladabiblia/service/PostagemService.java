package br.com.escoladabiblia.service;

import br.com.escoladabiblia.model.Postagem;
import br.com.escoladabiblia.util.exception.BusinessException;

public interface PostagemService {

	void salvar(Postagem postagem) throws BusinessException;

}
