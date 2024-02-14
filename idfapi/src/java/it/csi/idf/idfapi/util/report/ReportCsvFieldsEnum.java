/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.report;

public enum ReportCsvFieldsEnum {

	ADS("codice_ads"),
	ADS_TYPE("fk_tipo_ads"),
	ALTEZZA("altezza"),
	INCREMENTO("incremento"),
	ASSETTO("assetto"),
	CEPPAIE("nr_ceppaie"),
	DIAMETRO("diametro_specie"),
	INCLINAZIO("inclinazione"),
	SPECIE_NAME("latino"),
	SPECIE_CODE("cod_specie"),
	POPOLAMENTO("categoria_forestale"),
	QUALITA("flg_pollone_seme"),
	RAGGIO("raggio_mt"),
	SPECIE_ID("id_specie"),
	FATTORE("fattore_numerazione"),
	NUM_ALBERI_SEME("nr_alberi_seme"),
	NUM_ALBERI_POLLONE("nr_alberi_pollone"),
	TIPO_FORESTALE("tipo_forestale"),
	DENSITA("densita_campionamento"),
	COD_TIPO_CAMPIONE("cod_tipo_campione");
	
	private String value;
	private ReportCsvFieldsEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
