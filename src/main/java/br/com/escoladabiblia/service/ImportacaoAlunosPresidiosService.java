package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;

import br.com.escoladabiblia.util.exception.BusinessException;

public interface ImportacaoAlunosPresidiosService {

	void importAlunosPresidiosFromXLSXFile(InputStream stream, String fileName) throws IOException, BusinessException;

}
