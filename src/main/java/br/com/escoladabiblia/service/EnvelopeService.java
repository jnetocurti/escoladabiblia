package br.com.escoladabiblia.service;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public interface EnvelopeService {

	byte[] obterEnvelopesPostagem(Long idPostagem) throws JRException, IOException;

}
