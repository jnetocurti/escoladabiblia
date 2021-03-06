package br.com.escoladabiblia.service;

import br.com.escoladabiblia.model.AtividadeEstudo;
import br.com.escoladabiblia.util.dto.AtividadeEstudoEdicaoDTO;
import br.com.escoladabiblia.util.dto.AtividadeEstudoHistoricoDTO;
import br.com.escoladabiblia.util.dto.AtividadesEstudoEdicaoDTO;
import br.com.escoladabiblia.util.dto.AtividadesEstudoHistoricoDTO;
import br.com.escoladabiblia.util.exception.BusinessException;

public interface AtividadesEstudoService {

	AtividadesEstudoEdicaoDTO obterAtividadesEstudoAlunoParaEdicao(Long id) throws BusinessException;

	void adicionarAtividade(Long idAluno, Long idPostagem, Long idMaterial, boolean enviarBiblia);

	AtividadeEstudo obterAtividadePorId(Long id);

	AtividadeEstudo atualizarAtividade(AtividadeEstudoEdicaoDTO atividade);

	void deletarAtividade(AtividadeEstudo atividadeEstudo) throws BusinessException;

	AtividadesEstudoHistoricoDTO obterHistoricoAtividadesEstudoAluno(Long id);

	void adicionarAtividadeHistorico(AtividadeEstudoHistoricoDTO atividadeHistorico);

}
