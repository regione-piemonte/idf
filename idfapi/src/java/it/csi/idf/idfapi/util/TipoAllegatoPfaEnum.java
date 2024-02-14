/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoAllegatoPfaEnum {
	COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT(25),
	RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT(26),
	CARTOGRAFIA_AREA_DI_INTERVENTO(27),
	CARTOGRAFIA_AREA_DI_INTERVENTO_DIGITALE(160),
	CARTOGRAFIA_AREA_DI_INTERVENTO_E_PIANO_PLURIENNALE_DEI_TAGLI(28),
	PROGETTO_DI_INTERVENTO(29),
	PROGETTO_DI_INTERVENTO_DIGITALE(161),
	ELABORATI_PROGETTUALI_STRADE_E_PISTE_FORESTALI(30),
	ELABORATI_PROGETTUALI_STRADE_E_PISTE_FORESTALI_DIGITALE(162),
	VERBALE_DI_SOPRALLUOGO_PREVENTIVO(31),
	VERBALE_DI_SOPRALLUOGO_PREVENTIVO_DIGITALE(163),
	ALLEGATO_LIBERO_1(32),
	ALLEGATO_LIBERO_2(33),
	ALLEGATO_LIBERO_1_DIGITALE(164),
	ALLEGATO_LIBERO_2_DIGITALE(165),
	PIEDILISTA(34),
	PIEDILISTA_DIGITALE(166),
	DICHIARAZIONE_DREL(35),
	COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG(36),
	RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG(37),
	SCANSIONE_DOC_IDENTITA(38),
	ALTRO(39);

	private int value;
	
	private TipoAllegatoPfaEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}


	public static TipoAllegatoPfaEnum getEnumByValue(int value) {
		for (TipoAllegatoPfaEnum item : TipoAllegatoPfaEnum.values()) {
			if(item.getValue() == value) {
				return item;
			}
		}
		return null;
	}
}
