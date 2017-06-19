package br.com.escoladabiblia.util.impressao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CertificadosPostagemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String certificado;

	private Long quantidade;

	private List<AlunosCertificadosPostagemVO> alunos = new ArrayList<>();

	public CertificadosPostagemVO(String certificado, Long quantidade) {
		this.certificado = certificado;
		this.quantidade = quantidade;
	}

	public String getCertificado() {
		return certificado;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public List<AlunosCertificadosPostagemVO> getAlunos() {
		return alunos;
	}

}
