/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public class Constants {
	// Generic
	public final static String UNO = "1";
	public final static String ZERO = "0";
	public final static String FLAG_TRUE = "1";
	public final static String FLAG_FALSE = "0";
	public final static Integer NO_DATA = 0;

	// Applicativo
	public final static String COMPONENT_NAME = "idfapi";
	public final static int TIPO_PROCEDURA_IDF = 0;
	public final static int TIPO_PROCEDURA_PFA = 1;
	public final static int TIPO_PROCEDURA_INV = 2;
	public final static int TIPO_PROCEDURA_ISTAFOR = 3;

	// Index
	public static final String INDEX_USERNAME_ADMIN = "admin@primpa";
	public static final String INDEX_PSW = "primpa";
	public static final String INDEX_UTENTE = "Utente Primpa";
	public static final String INDEX_FRUITORE = "primpa";
	public static final String INDEX_REPOSITORY = "primary";
	public static final String INDEX_PREFIX_PRIMPA = "primpa:";
	public static final String INDEX_PREFIX_PFA = "pfa:";
	public static final String INDEX_PREFIX_TRASFORMAZIONI = "trasformazioni:";
	public static final String INDEX_PREFIX_VINCOLO = "vincoloIdrogeologico:";
	public static final String INDEX_PREFIX_DELEGHE = "deleghe:";
	public static final String INDEX_PREFIX_NAME = "cm:content";
	public static final String INDEX_PREFIX_NAME_SHORT = "cm:name";
	public static final String INDEX_PREFIX_CONTAINS = "cm:contains";
	public static final String INDEX_ROOT_PRIMPA = "/app:company_home/cm:primpa";
	public static final String INDEX_ROOT_TAGLI = "idf_tagli_boschivi";
	public static final String INDEX_ROOT_PFA = "/app:company_home/cm:pfa";
	public static final String INDEX_ROOT_TRASFORMAZIONI = "/app:company_home/cm:trasformazioni";
	public static final String INDEX_ROOT_VINCOLO = "/app:company_home/cm:vincoloIdrogeologico";
	public static final String INDEX_ROOT_DELEGHE = "/app:company_home/cm:deleghe";
	public static final String INDEX_DEFAULT_PREFIX = "cm:";
	public static final String INDEX_PREFIX_MODEL = "cm:contentmodel";
	public static final String INDEX_PREFIX_FOLDER = "cm:folder";
	public static final String INDEX_TYPE_TEXT = "d:text";
	public static final String INDEX_FOLDER_SUFFIX = "/cm:";
	public static final String INDEX_METADATO_SUFFIX = "@cm\\:";

	public static final String INDEX_TYPE_ALLEGATO_PRIMPA = "primpa:allegato";
	public static final String INDEX_TYPE_ALLEGATO_PFA = "pfa:allegatoPfa";
	public static final String INDEX_TYPE_ALLEGATO_TRASFORMAZIONI = "trasformazioni:allegato";
	public static final String INDEX_TYPE_ALLEGATO_VINCOLO = "vincoloIdrogeologico:allegato";
	public static final String INDEX_TYPE_ALLEGATO_DELEGHE = "deleghe:allegato";
	public static final String INDEX_ENCODING = "UTF-8";

	public static final String INDEX_PREFIX_MODEL_PRIMPA = "primpa:PrimpaContent";
	public static final String INDEX_PREFIX_MODEL_PFA = "pfa:PFAContent";
	public static final String INDEX_PREFIX_MODEL_TRASFORMAZIONI = "primpa:TrasformazioniContent";
	public static final String INDEX_PREFIX_MODEL_VINCOLO = "primpa:VincoloIdrogeologicoContent";
	public static final String INDEX_PREFIX_MODEL_DELEGHE = "primpa:DelegheContent";

	public static final String INDEX_CM_NAME_PRIMPA = "@primpa";
	public static final String INDEX_CM_NAME_PFA = "@pfa";
	public static final String INDEX_CM_NAME_TRASFORMAZIONI = "@trasformazioni";
	public static final String INDEX_CM_NAME_VINCOLO = "@vincoloIdrogeologico";

	public static final String MIMETYPE_DEFAULT = null;

	// Geeco
	public static final String GEECO_GEOMETRY = "geometry";
	public static final String GEECO_TOKEN_ID = "[TOKEN_ID]";

	// Geeco - Aree di Saggio
	public static final String GEECO_ADS_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_ADS_LABEL_N_ADS = "N. ADS";
	// Geeco - Trasformazioni del bosco
	public static final String GEECO_TRS_LABEL_IDENTIFICATIVO = "Identificativo";
	public static final String GEECO_TRS_LABEL_COD_ISTANZA = "Id intervento";

	// public static final String GEECO_TRS_LABEL_DATA_INVIO_ISTANZA = "Data invio
	// istanza";
	public static final String GEECO_TRS_LABEL_SUPERFICIE = "Superficie (ha)";
	//public static final String GEECO_TRS_LABEL_ID = "id";
	// Geeco - Piani forestali
	public static final String GEECO_PFA_LABEL_CODICE_EVENTO = "N. Evento";
	public static final String GEECO_PFA_LABEL_DENOMINAZIONE = "Denominazione PFA";
	public static final String GEECO_PFA_LABEL_ID_EVENTO = "Id evento";
	public static final String GEECO_PFA_LABEL_CODICE_INTERVENTO = "N. Intervento";
	public static final String GEECO_PFA_LABEL_ID_INTERVENTO = "Id intervento";
	public static final String GEECO_PFA_LABEL_COMUNE = "Comune";
	public static final String GEECO_PFA_LABEL_CODICE = "Codice PFA";
	public static final String GEECO_PFA_LABEL_DESCRIZIONE = "Descrizione";
	
	
	// profili
	public static final int GEECO_PROFILO_ADS_CARTOGRAFIA = 1;
	public static final int GEECO_PROFILO_ADS_INSERIMENTO_MODIFICA = 2;
	public static final int GEECO_PROFILO_ADS_ELENCO_RICERCA = 3;
	public static final int GEECO_PROFILO_TRASFORMAZIONI_GESTORE_CONSULTAZIONE = 4;
	public static final int GEECO_PROFILO_TRASFORMAZIONI_CITTADINO_INSERIMENTO_MODIFICA = 5;
	public static final int GEECO_PROFILO_ADS_DETTAGLIO = 6;
	public static final int GEECO_PROFILO_TRASFORMAZIONI_DETTAGLIO = 7;
	public static final int GEECO_PROFILO_PFA_GESTORE_INTERVENTO_INSERIMENTO_MODIFICA = 8;
	public static final int GEECO_PROFILO_PFA_GESTORE_EVENTO_INSERIMENTO_MODIFICA = 9;
	public static final int GEECO_PROFILO_PFA_GESTORE_INTERVENTO_DETTAGLIO = 10;
	public static final int GEECO_PROFILO_PFA_GESTORE_EVENTO_DETTAGLIO = 11;
	public static final int GEECO_PROFILO_PFA_GESTORE_CARTOGRAFIA = 12;
	public static final int GEECO_PROFILO_PFA_GESTORE_ELENCO = 13;
	public static final int GEECO_PROFILO_PFA_GESTORE_DETTAGLIO = 14;
	public static final int GEECO_PROFILO_PFA_CITTADINO_CARTOGRAFIA = 15;
	public static final int GEECO_PROFILO_PFA_CITTADINO_ELENCO = 16;
	public static final int GEECO_PROFILO_PFA_CITTADINO_DETTAGLIO = 17;
	public static final int GEECO_PROFILO_VINCOLO_GESTORE_CONSULTAZIONE = 18;
	public static final int GEECO_PROFILO_VINCOLO_CITTADINO_INSERIMENTO_MODIFICA  = 19;
	public static final int GEECO_PROFILO_VINCOLO_DETTAGLIO = 20;
	public static final int GEECO_PROFILO_PFA_GESTORE_INTERVENTO_COMPLETA = 21;
	//fixGP
	public static final int GEECO_PROFILO_TAGLI_CITTADINO_INSERIMENTO_MODIFICA = 22;
	public static final int GEECO_PROFILO_TAGLI_DETTAGLIO = 23;
	public static final int GEECO_PROFILO_TAGLI_GESTORE_CONSULTAZIONE = 24;
	

	
	
	// layer PFA
	public final static String GEECO_LAYER_PFA_EVENTO_PUNTI = "30";
	public final static String GEECO_LAYER_PFA_EVENTO_LINEE = "31";
	public final static String GEECO_LAYER_PFA_EVENTO_POLIGONI = "32";
	public final static String GEECO_LAYER_PFA_INTERVENTO_PUNTI = "27";
	public final static String GEECO_LAYER_PFA_INTERVENTO_LINEE = "28";
	public final static String GEECO_LAYER_PFA_INTERVENTO_POLIGONI = "29";
	public final static String GEECO_LAYER_PFA_AREALI = "33";
	
	//FixGP
	public final static String GEECO_LAYER_TAGLI_INTERVENTO_POLIGONI = "51";
	
	public static final String GEECO_PUNTO = "PT";
	public static final String GEECO_LINEA = "LN";
	public static final String GEECO_POLIGONO = "PL";

	public static final String BASE_CALCOLO_EURO_CAUZIONE_VINCOLO_IDROGEOLOGICO = "BASE_CALCOLO_EURO_CAUZIONE_VINCOLO_IDROGEOLOGICO";
	public static final String VALORE_MARCA_DA_BOLLO = "VALORE_MARCA_DA_BOLLO";
	public static final String COEFF_CALCOLO_EURO_SUP_NON_BOSCATA_LR45 = "COEFF_CALCOLO_EURO_SUP_NON_BOSCATA_LR45";

	
	 public static final class LOGGING
	  {
	    public static final String LOGGER_NAME = "idfapi";
	  }
	
}
