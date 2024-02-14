/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util;

public class Constants
{
  // FIXME
  public final static int ID_PROCEDIMENTO = 0;


  public static class FLAG
  {
    public final static String SI = "S";
    public final static String NO = "N";
  }

  public static class DATABASE
  {
    public final static String JNDINAME = "java:/idfbo/jdbc/idfboTxDS";
    //public final static String JNDINAME = "java:/idfDS";
  }

  public static class ESITO
  {
    public final static String POSITIVO        = "000";
    public final static String ERRORE_GENERICO = "001";
    
    public static final String KO = "KO";
    public static final String OK = "OK";
    
    public static class MESSAGGIO
    {
      public final static String MESSAGGIO_POSITIVO = null;
      public final static String MESSAGGIO_ERRORE   = "Si e' verificato un errore nell'accesso del servizio.";

    }
  }

  public static class URL
  {
    //public final static String URL_COSMO    = "URL_COSMO";
    public final static String URL_COSMO_PRATICA = "URL_COSMO_PRATICA";
    public final static String URL_COSMO_UPLOAD = "URL_COSMO_UPLOAD";
    public final static String URL_COSMO_DOCUMENTI = "URL_COSMO_DOCUMENTI";
    public final static String URL_COSMO_PROCESSO = "URL_COSMO_PROCESSO";
    public final static String URL_TOKEN = "URL_TOKEN";
    
    public final static String URL_INDEX = "index.ws.url";
    
  }
  
  public static class COSMO{
	  public final static String SEMAFORO_TRASFORMAZIONI ="COSMO_TRASFORMAZIONI";
	  public final static String SEMAFORO_VINCOLO = "COSMO_VINCOLO";
	  public final static String SEMAFORO_TAGLISELV= "COSMO_TAGLI";
  }
  
  public static class WS
  {
    public final static int POST_TIME_OUT    = 120000;
    
  }

  public static final class LOGGING
  {
    public static final String LOGGER_NAME = "idfbo";
  }

  public static class ERRORS
  {
    public final static int COD_ERRORE_INTERNO    = 999;
  }
  
  public static class STEP
  {
    public final static int STEP_1    = 1;
    public final static int STEP_2    = 2;
    public final static int STEP_3    = 3;
    public final static int STEP_4    = 4;
    public final static int STEP_5    = 5;
  }
  
  public static class AMBITO_ISTANZA
  {
    public final static int ISTANZA_FORESTE = 1;
    public final static int VINCOLO_IDRO    = 3;
    public final static int VINCOLO_TAGLISELV   = 2;
  
  }
  
  public static class PARAMETER
  {
    public final static int NUM_TENTATIVI    = 5;
    public final static String CODICEIPAENTE_COSMO = "CODICEIPAENTE_COSMO";
    public final static String OGGETTO_COSMO = "OGGETTO_COSMO";
    public final static String CODICETIPOLOGIA_COSMO = "CODICETIPOLOGIA_COSMO";
    public final static int INVIATO_COSMO_FILE    = 1;
    public final static int DA_INVIARE_COSMO    = 0;
    public final static int INVIATO_COSMO_PRATICA    = 2;
  }
  
  public static class INDEX{
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
		public static final String INDEX_PREFIX_NAME = "cm:content";
		public static final String INDEX_PREFIX_NAME_SHORT = "cm:name";
		public static final String INDEX_PREFIX_CONTAINS = "cm:contains";
		public static final String INDEX_ROOT_PRIMPA = "/app:company_home/cm:primpa/";
		public static final String INDEX_ROOT_PFA = "/app:company_home/cm:pfa";
		public static final String INDEX_ROOT_TRASFORMAZIONI = "/app:company_home/cm:trasformazioni";
		public static final String INDEX_ROOT_VINCOLO = "/app:company_home/cm:vincoloIdrogeologico";
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
		public static final String INDEX_ENCODING = "UTF-8";

		public static final String INDEX_PREFIX_MODEL_PRIMPA = "primpa:PrimpaContent";
		public static final String INDEX_PREFIX_MODEL_PFA = "pfa:PFAContent";
		public static final String INDEX_PREFIX_MODEL_TRASFORMAZIONI = "primpa:TrasformazioniContent";
		public static final String INDEX_PREFIX_MODEL_VINCOLO = "primpa:VincoloIdrogeologicoContent";

		public static final String INDEX_CM_NAME_PRIMPA = "@primpa";
		public static final String INDEX_CM_NAME_PFA = "@pfa";
		public static final String INDEX_CM_NAME_TRASFORMAZIONI = "@trasformazioni";
		public static final String INDEX_CM_NAME_VINCOLO = "@vincoloIdrogeologico";

		public static final String MIMETYPE_DEFAULT = null;
  }
  
  public static class MAIL{
	  public static final int MAIL_NO_PEC = 1;
	  public static final int PROTOCOLLO = 2;
	  public static final int VINCOLO_IDRO_PROTOCOLLO = 4;
	  public static final int VINCOLO_SELVICOLTURALI_1_PROTOCOLLO = 8;
	  public static final int VINCOLO_SELVICOLTURALI_2_PROTOCOLLO = 10;
	  public static final int VINCOLO_IDRO_ALTRO = 6;
  }

}
