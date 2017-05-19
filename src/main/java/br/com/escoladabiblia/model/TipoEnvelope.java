package br.com.escoladabiblia.model;

/**
 * Representa os tipos de envelopes para impressão suportados pelo sistema
 * <p>
 * O atributo {@code size} define o tamanho em "pontos" para o envelope sendo
 * também seu identificador. Ex: <b>OFICIO_114_X_229</b> é um envelope menor que
 * <b>ENVELOPE_162_X_229</b>
 * <p>
 * O atributo {@code jasperFile} define o jasper template correspondente ao tipo
 * de envelope
 * 
 * @author José Cataldo Curti Neto
 * 
 * @see {@link br.com.escoladabiblia.model.TipoEnvelopeConverter}
 */
public enum TipoEnvelope {

	OFICIO_114_X_229(1, "jasper/envelope-oficio-frente.jasper"), 
	ENVELOPE_162_X_229(2, "jasper/envelope-medio-frente.jasper"), 
	ENVELOPE_185_X_248(3, "jasper/envelope-medio-2-frente.jasper");

	public final Integer size;

	public final String jasperFile;

	private TipoEnvelope(Integer size, String jasperFile) {
		this.size = size;
		this.jasperFile = jasperFile;
	}

	public static TipoEnvelope valueOf(Integer size) {

		for (TipoEnvelope tipoEnvelope : TipoEnvelope.values()) {

			if (tipoEnvelope.size.equals(size)) {
				return tipoEnvelope;
			}
		}

		throw new IllegalArgumentException("TipoEnvelope não suportado para size " + size);
	}

}
