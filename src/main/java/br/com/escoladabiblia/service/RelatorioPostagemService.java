package br.com.escoladabiblia.service;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public interface RelatorioPostagemService {

	byte[] obterRelatorioPostagem(Long idPostagem) throws JRException, IOException;

}
