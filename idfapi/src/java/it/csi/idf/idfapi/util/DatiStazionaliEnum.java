/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum DatiStazionaliEnum {
	
	COMUNE("Comune"), 
	UTM_EST("UTM EST"),
	UTM_NORD("UTM NORD"),
	QUOTA("Quota"),
	ESPOSIZIONE("Esposizione"),
	INCLINAZIONE("Inclinazione"),
	DENSITA_CAMP("Densita camp"),
	RAGGIO_AREA("Raggio area"),
	PARTICELLA_FORESTALE("Particella forestale"),
	PROPRIETA("Proprieta'"),
	CATEGORIA_FORESTALE("Categoria forestale"),
	TIPO_FORESTALE("Tipo forestale"),
	ASSETTO_EVOLUTIVO_COLTULARE("Assetto evolutivo-colturale"),
	TIPO_STRUTTURALE("Tipo strutturale"),
	STADIO_DI_SVILUPPO("Stadio di sviluppo"),
	CLASSE_DI_FERTILITA("Classe di fertilita"),
	N_CEPPAIE("N Ceppaie"),
	RINNOVAZIONE("Rinnovazione"),
	SPECIE_PREVALENTE_RINNOVAZIONE("Specie prevalente rinnovazione"),
	COPERTURA_CHIOME("Copertura chiome"),
	COPERTURA_CESPUGLI_SUFFRUTICI("Copertura cespugli suffrutici"),
	COPERTURA_ERBACEA("Copertura erbacea"),
	LETTIERA("Lettiera"),
	DESTINAZIONE("Destinazione"),
	INTERVENTO("Intervento"),
	PRIORITA_INTERVENTO("Priorita intervento"),
	ESBOSCO("Esbosco"),
	DEFP("DEFP"),
	DESP("DESP"),
	MDP("MDP"),
	DANNO_PREVALENTE("Danno prevalente"),
	INTENSITA_DANNI("Intensita danni"),
	N_PIANTE_MORTE("N Piante Morte"),
	DEFOGLIAZIONE("Defogliazione"),
	INGIALLIMENTO("Ingiallimento"),
	PASCOLAMENTO("Pascolamento"),
	COMBUSTIBILE_PRINCIPALE("Combustibile principale"),
	COMBUSTIBILE_SECONDARIO("Combustibile secondario"),
	TAVOLA("Tavola");
	
	private String value;
	private DatiStazionaliEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
