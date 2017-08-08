package br.com.escoladabiblia.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escoladabiblia.model.Parametros;
import br.com.escoladabiblia.model.TipoEnvelope;
import br.com.escoladabiblia.repository.AtividadeEstudoRepository;
import br.com.escoladabiblia.repository.ParametrosRepository;
import br.com.escoladabiblia.util.dto.AtividadeEstudoImpressaoDTO;
import br.com.escoladabiblia.util.impressao.Destinatario;
import br.com.escoladabiblia.util.impressao.DestinatarioFactory;
import br.com.escoladabiblia.util.impressao.JasperUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EnvelopeServiceImpl implements EnvelopeService {

	@Autowired
	private ParametrosRepository parametrosRepository;

	@Autowired
	private AtividadeEstudoRepository atividadeEstudoRepository;

	@Override
	public byte[] obterEnvelopesPostagem(Long idPostagem) throws JRException, IOException {

		final List<JasperPrint> jasperPrints = new ArrayList<>();

		final List<AtividadeEstudoImpressaoDTO> atividadesEstudoPostagem = atividadeEstudoRepository
				.obterAtividadesEstudoAlunosParaImpressao(idPostagem);

		for (TipoEnvelope tipoEnvelope : TipoEnvelope.values()) {

			List<Destinatario> destinatarios = this.obterDestinatariosPorTipoEnvelope(atividadesEstudoPostagem,
					tipoEnvelope);

			if (!destinatarios.isEmpty()) {

				jasperPrints.add(this.getJasperPrintEnvelopes(destinatarios, tipoEnvelope));
			}
		}

		return JasperUtil.exportReport(jasperPrints);
	}

	/**
	 * 
	 * Filtra a lista de {@code AtividadeEstudoImpressaoDTO} de acordo com o
	 * tipo de envelope e retorna uma lista de {@code Destinatario} para
	 * envelopes correspondentes.
	 * 
	 * @param atividadesEstudoPostagem
	 * @param tipoEnvelope
	 * 
	 * @return Lista de {@code Destinatario} para envelopes correspondentes
	 */
	private List<Destinatario> obterDestinatariosPorTipoEnvelope(
			final List<AtividadeEstudoImpressaoDTO> atividadesEstudoPostagem, final TipoEnvelope tipoEnvelope) {

		final List<Destinatario> destinatarios = new ArrayList<>();

		atividadesEstudoPostagem.stream().filter(a -> a.getTipoEnvelope().equals(tipoEnvelope))
				.forEach(a -> destinatarios.add(DestinatarioFactory.getDestinatario(a.getAluno())));

		return destinatarios;
	}

	/**
	 * 
	 * Cria e retorna uma instância de {@code JasperPrint} correspondente ao
	 * tipo de envelope recebido.
	 * 
	 * @param destinatarios
	 *            - datasource do relatório a ser gerado
	 * @param tipoEnvelope
	 *            - contém o caminho para o template do relatório
	 * 
	 * @return {@code JasperPrint} conforme o tipo de envelope
	 * @throws JRException
	 * @throws IOException
	 */
	private JasperPrint getJasperPrintEnvelopes(final List<Destinatario> destinatarios, TipoEnvelope tipoEnvelope)
			throws JRException, IOException {

		final InputStream jasper = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(tipoEnvelope.jasperFile);

		return JasperFillManager.fillReport(jasper, this.obterParametrosRelatorio(),
				new JRBeanCollectionDataSource(destinatarios));
	}

	/**
	 * 
	 * Instancia e retorna um {@code Map<String, Object>} preenchido com os
	 * parâmetros necessários para a geração dos envelopes.
	 * 
	 * @return Parâmetros para a geração dos envelopes
	 * @throws IOException
	 */
	private Map<String, Object> obterParametrosRelatorio() throws IOException {

		final Parametros parametros = parametrosRepository.findOne(1L);

		final Map<String, Object> parameters = new HashMap<>();

		parameters.put("carimboImpressoImg", JasperUtil.getFilePath("images/carimbo-impresso.png"));

		parameters.put("showCarimboImpressoImg", parametros.isExibirCarimboImpresso());

		parameters.put("carimboMalaDiretaImg", JasperUtil.getFilePath("images/carimbo-mala.png"));

		parameters.put("showCarimboMalaDiretaImg", parametros.isExibirCarimboMalaDireta());

		parameters.put("remetenteImg", JasperUtil.getFilePath("images/remetente.png"));

		parameters.put("showRemetenteImg", parametros.isExibirRemetente());

		return parameters;
	}

}
