package br.com.escoladabiblia.service;

import br.com.escoladabiblia.util.dto.EdicaoAtividadesEstudoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

public interface AtividadesEstudoService {

	EdicaoAtividadesEstudoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException;

	void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial);

}
