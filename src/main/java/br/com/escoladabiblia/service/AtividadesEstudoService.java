package br.com.escoladabiblia.service;

import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.util.dto.AtividadeEstudoEdicaoDTO;
import br.com.escoladabiblia.util.dto.AtividadesEstudoEdicaoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

public interface AtividadesEstudoService {

	AtividadesEstudoEdicaoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException;

	void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial);

	AtividadeEstudo obterAtividadePorId(Long id);

	AtividadeEstudo atualizarAtividade(AtividadeEstudoEdicaoDTO atividade);

	void deletarAtividade(AtividadeEstudo atividadeEstudo) throws BusinessException;

}
