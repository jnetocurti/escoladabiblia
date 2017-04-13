package br.com.escoladabiblia.service;

import java.util.Calendar;

import br.com.escoladabiblia.util.dto.EdicaoAtividadesEstudoDTO;

public interface AtividadesEstudoService {

	EdicaoAtividadesEstudoDTO obterAtividadesEstudoAlunoParaEdicao(Long id);

	void adicionarAtividade(Long idAluno, Calendar dataProximoEnvio, Long idMaterial);

}
