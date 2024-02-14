/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.util;

public class Constants {

	public final static String COMPONENT_NAME = "idfgeoapi";

	public static final String GEECO_GEOMETRY = "geometry";
	public static final String GEECO_ADS_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_ADS_LABEL_N_ADS = "N. ADS";
	
	public static final String GEECO_TRS_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_TRS_LABEL_INTERVENTO = "Id intervento";
	public static final String GEECO_TRS_LABEL_DATA_INVIO_ISTANZA = "Data invio istanza";
	public static final String GEECO_TRS_LABEL_SUPERFICIE = "Superficie (ha)";
	
	public static final String GEECO_VINC_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_VINC_LABEL_INTERVENTO = "Id intervento";
	public static final String GEECO_VINC_LABEL_DATA_INVIO_ISTANZA = "Data invio istanza";
	public static final String GEECO_VINC_LABEL_SUPERFICIE = "Superficie (ha)";
	
	public static final String GEECO_TAGLI_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_TAGLI_LABEL_INTERVENTO = "Id intervento";
	public static final String GEECO_TAGLI_LABEL_DATA_INVIO_ISTANZA = "Data invio istanza";
	public static final String GEECO_TAGLI_LABEL_SUPERFICIE = "Superficie (ha)";
	
	public static final String GEECO_PFA_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_PFA_LABEL_CODICE_EVENTO = "Codice Evento";
	public static final String GEECO_PFA_LABEL_CODICE_INTERVENTO = "Codice Intervento";
	public static final String GEECO_PFA_LABEL_DENOMINAZIONE = "Denominazione PFA";
	public static final String GEECO_PFA_LABEL_ID_INTERVENTO = "Id intervento";
	public static final String GEECO_PFA_LABEL_ID_EVENTO = "Id evento";
	public static final String GEECO_PFA_LABEL_DESCRIZIONE = "Descrizione";
	
	public static final String GEECO_EPSG_PIEMONTE = "EPSG:32632";
	public static final String GEECO_TYPE_FEATURE = "Feature";
	public static final String GEECO_ID_DEFAULT_VALUE = "-";

	public final static int TIPO_PROCEDURA_IDF = 0;
	public final static int TIPO_PROCEDURA_PFA = 1;
	public final static int TIPO_PROCEDURA_INV = 2;
	public final static int TIPO_PROCEDURA_ISTAFOR = 3;

	// Generic
	public final static String UNO = "1";
	public final static String ZERO = "0";
	public final static String FLAG_TRUE = "1";
	public final static String FLAG_FALSE = "0";

	// layer PFA
	public final static String LAYER_PFA_EVENTO_PUNTI = "30";
	public final static String LAYER_PFA_EVENTO_LINEE = "31";
	public final static String LAYER_PFA_EVENTO_POLIGONI = "32";
	public final static String LAYER_PFA_INTERVENTO_PUNTI = "27";
	public final static String LAYER_PFA_INTERVENTO_LINEE = "28";
	public final static String LAYER_PFA_INTERVENTO_POLIGONI = "29";
	
	// layer ISTAFOR
	public final static String LAYER_TRASFORMAZIONI_CITTADINO = "16";
	public final static String LAYER_VINCOLO_CITTADINO = "38";
	public final static String LAYER_TAGLI_CITTADINO = "51";
	
	

}
