package br.com.escoladabiblia.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoEnvelopeConverter implements AttributeConverter<TipoEnvelope, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoEnvelope tipoEnvelope) {

		switch (tipoEnvelope) {

		case OFICIO_114_X_229:
			return TipoEnvelope.OFICIO_114_X_229.size;

		case ENVELOPE_162_X_229:
			return TipoEnvelope.ENVELOPE_162_X_229.size;
			
		case ENVELOPE_185_X_248:
			return TipoEnvelope.ENVELOPE_185_X_248.size;

		default:
			throw new IllegalArgumentException("Case converter não implementado para: " + tipoEnvelope);
		}
	}

	@Override
	public TipoEnvelope convertToEntityAttribute(Integer dbData) {

		return TipoEnvelope.valueOf(dbData);
	}

}
