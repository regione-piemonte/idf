/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.errors;

public class ErrorConstants {
	
	private ErrorConstants() {}
	
	public static final int SUCCESS_000 = 0;
	public static final String SUCCESS = "OPERAZIONE EFFETTUATA CON SUCCESSO";
	public static final int STRINGA_DIGITATA_ERROR_001 = 1;
	public static final String STRINGA_DIGITATA_ERROR = "LA STRINGA DIGITATA E TROPPO LUNGA";
	public static final int OGGETTO_OBBLIGATORIO_002 = 2;
	public static final String OGGETTO_OBBLIGATORIO = "CAMPO OBBLIGATORIO";
	public static final int CAMPO_OBBLIGATORIO_003 = 3;
	public static final String CAMPO_OBBLIGATORIO = "CAMPO OBBLIGATORIO";
	public static final int MAIL_NON_VALIDO_004 = 4;
	public static final String MAIL_NON_VALIDO = "INDIRIZZO MAIL NON VALIDO";
	public static final int ID_ESISTE_IN_DB_005 = 5;
	public static final String ID_ESISTE_IN_DB = "ID ESISTE IN DB";
	public static final int NON_TROVATO_404 = 404;
	public static final String NON_TROVATO = "NON TROVATO IN ARCHIVIO ";
	public static final int BAD_INPUT_PARAMETERS_400 = 400;
	public static final String BAD_INPUT_PARAMETERS = "BAD INPUT PARAMETERS";
	public static final String FISCAL_CODE_ERROR = "CODICE FISCALE ERRATO";
	public static final int DB_INTEGRITY_19 = 19;
	public static final String DB_INTEGRITY_DISTURBED = "INTEGRITA DEL DATABASE DISTURBATA";
	public static final String COMPETENZA_NON_CONGRUENTE = "Risulta un'incongruenza tra i dati inseriti e il tipo di competenza selezionata inizialmente. Verificare le superfici, i volumi, il numero di comuni in cui esso ricade e/o le tipologie di intervento.";
}
