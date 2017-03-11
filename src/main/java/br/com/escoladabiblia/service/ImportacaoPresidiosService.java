package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImportacaoPresidiosService {

	void importPresidiosFromXLSXFile(InputStream stream) throws IOException;

}
