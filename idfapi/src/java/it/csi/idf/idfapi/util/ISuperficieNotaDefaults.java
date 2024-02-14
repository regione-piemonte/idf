/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public interface ISuperficieNotaDefaults {

	public static final String COD_STADIO_SVILUPPO_DEFAULT = "0";
	public static final String COD_ESBOSCO_DEFAULT = "NN"; // TODO: this have to be checked it is mandatory but no data non presente record
	public static final Integer NR_CEPPAIE_DEFAULT = 0;
	public static final Integer FLG_PASCOLAMENTO_DEFAULT = 0;
	public static final Integer FK_TIPO_STRUTTURALE_PRINC_DEFAULT = 0;
	public static final Integer FK_TIPO_STRUTTURALE_SECOND_DEFAULT = 0;
	public static final Integer COMBUST_P_DEFAULT = 0;
	public static final Integer FK_CLASSE_FERTILITA_DEFAULT = 0;
	
}
