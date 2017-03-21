package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImportacaoAlunosPresidiosService {

	void importAlunosPresidiosFromXLSXFile(InputStream stream) throws IOException;

}
