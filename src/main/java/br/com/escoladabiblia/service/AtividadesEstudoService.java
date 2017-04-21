package br.com.escoladabiblia.service;

import br.com.escoladabiblia.util.dto.AtividadesEstudoEdicaoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

public interface AtividadesEstudoService {

	AtividadesEstudoEdicaoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException;

	void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial);

}
