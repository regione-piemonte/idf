/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoAllegatoEnum {
	DICHIARAZIONE(1),
	DICHIARAZIONE_DIGITALE(2),
	DICHIARAZIONE_AUTOGRAFA(3),
	DOCUMENTO_IDENTITA(4),
	COMPENSAZIONE_FISICA(5),
	COMPENSAZIONE_MONETARIA(6),
	ALTRO(7),
	COMUNICAZIONE_TAGLIO(8),
	AUTORIZZAZIONE_TAGLIO(9),
	RELAZIONE_TECHINCA(10),
	PROGETTO_INTERVENTO(11),
	VERBALE_PREVENTIVO(12),
	PIEDILISTA(13),
	RICHIESTA_AUTORIZZAZIONE(21),
	RICHIESTA_AUTORIZZAZIONE_DIGITALE(22),
	RICHIESTA_AUTORIZZAZIONE_AUTOGRAFA(23),
	SCANSIONE_DOCUMENTO_IDENTITA(24),
	PROGETTO_DEFINITIVO(101),
	RELAZIONE_TECNICA(102),
	RELAZIONE_GEOLOGICA_E_GEOTECNICA(103),
	RELAZIONE_SPECIALISTICA_FORESTALE(104),
	PROGETTO_DI_RIMBOSCHIMENTO(105),
	RICEVUTA_VERSAMENTO_DEPOSITO_CAUZIONALE(106),
	DOCUMENTAZIONE_FOTOGRAFICA(107),
	RELAZIONE_NIVOLOGICA(108),
	ESTRATTO_PLANIMETRICO(109),
	SCHEDA_TECNICA(110),
	PLANIMETRIA_CATASTALE(111),
	ESTRATTO_AEROFOTOGRAMMETRICO(112),
	DICHIARAZIONE_SOSTITUTIVA_ATTO_DI_NOTORIETA(113),
	ALLEGATO_LIBERO_1(120),
	ALLEGATO_LIBERO_2(121),
	PROGETTO_DEFINITIVO_DIGITALE(131),
	RELAZIONE_TECNICA_DIGITALE(132),
	RELAZIONE_GEOLOGICA_E_GEOTECNICA_DIGITALE(133),
	RELAZIONE_SPECIALISTICA_FORESTALE_DIGITALE(134),
	PROGETTO_DI_RIMBOSCHIMENTO_DIGITALE(135),
	DOCUMENTAZIONE_FOTOGRAFICA_DIGITALE(137),
	RELAZIONE_NIVOLOGICA_DIGITALE(138),
	ESTRATTO_PLANIMETRICO_DIGITALE(139),
	SCHEDA_TECNICA_DIGITALE(140),
	PLANIMETRIA_CATASTALE_DIGITALE(141),
	ESTRATTO_AEROFOTOGRAMMETRICO_DIGITALE(142),
	DICHIARAZIONE_SOSTITUTIVA_ATTO_DI_NOTORIETA_DIGITALE(143),
	ALLEGATO_LIBERO_DIGITALE(150),
	RICEVUTA_PAGAMENTO_DIRITTI_ISTRUTTORIA(151),
	RICEVUTA_VERSAMENTO_COMPENSAZIONE_FISICA(152),
	RICEVUTA_VERSAMENTO_COMPENSAZIONE_MONETARIA(153),

	COMUNICAZIONE_TAGLIO_DIGITALE(154),
	AUTORIZZAZIONE_TAGLIO_DIGITALE(155);
	private int value;
	
	private TipoAllegatoEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static TipoAllegatoEnum getEnumByValue(int value) {
		for (TipoAllegatoEnum item : TipoAllegatoEnum.values()) { 
		    if(item.getValue() == value) {
		    	return item;
		    } 
		}
		return null;
	}
}