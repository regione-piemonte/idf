/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util;

import static it.csi.idf.idfbo.util.builder.ToStringBuilder.objectToString;

import java.text.MessageFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 *
 * @author  1346 (Enrico Fusaro)
 */
public final class Message implements Cloneable {

	protected static final Logger logger         = Logger
		      .getLogger(Constants.LOGGING.LOGGER_NAME + ".util");

    /**
     * Segnaposto delle parti variabili dei messaggi
     */
    private static final String VALUE_PLACEHOLDER = "##value##";
    
    private static final String VALUE_PLACEHOLDER_NUMERO_ISTANZA = "##valueNroIstanza##";   
    private static final String VALUE_PLACEHOLDER_TIPO_ISTANZA = "##valueTipoIstanza##";
    
    private static final String VALUE_PLACEHOLDER_SOGGETTOGESTORE_DENOMINAZIONE = "##valueSoggettogestoreDenominazione##";
    private static final String VALUE_PLACEHOLDER_SOGGETTOGESTORE_TELEFONO = "##valueSoggettogestoreTelefono##";
    private static final String VALUE_PLACEHOLDER_SOGGETTOGESTORE_MAIL = "##valueSoggettogestoreMail##";
    private static final String VALUE_PLACEHOLDER_SOGGETTOGESTORE_PEC = "##valueSoggettogestorePec##";
    
      
    
    /**
     

    /**
     * Testo del messaggio
     */
    private String text = null;
    
    /**
     * Restituisce il testo del messaggio
     *
     * @return Testo del messaggio
     */
    public String getText() {
        return this.text;
    }

    /**
     * Imposta il testo del messaggio
     *
     * @param text Testo del messaggio
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Inizializza un'istanza della classe
     */
    public Message() {
        /*NOP */
    }

    

    /**
     * Sostituisce un valore al primo segnaposto del messaggio
     *
     * @param value Valore da sostituire
     * @return Messaggio
     */
    public Message replacePlaceholder(String value) {
    	this.text = this.text.replaceFirst(VALUE_PLACEHOLDER, value);
         return this;
    }

    public Message replaceFormat(Object... values) {
        this.text = MessageFormat.format(this.text, values);
        return this;
    }

   
    
   

    /**
     * Inizializza un'istanza della classe con 5 parametri di sostituzione
     *
     * @param text Testo del messaggio
     * @param value1 Valore da sostituire
     * @param value2 Valore da sostituire
     * @param value3 Valore da sostituire
     * @param value4 Valore da sostituire
     * @param value5 Valore da sostituire
     */
    public Message(String text, String value1, String value2,
                   String value3, String value4, String value5, String value6) {
        this.text = text;
        
        this.replacePlaceholder(value1, value2, value3, value4, value5, value6);
    }

    

    /**
     * Sostituisce un valore al primo segnaposto del messaggio
     *
     * @param value Valore da sostituire
     * @return Messaggio
     */
    public Message replacePlaceholder(long value) {
        this.text = this.text.replaceFirst(VALUE_PLACEHOLDER, Long.toString(value));

        return this;
    }

    /**
     * Sostituisce un valore al primo segnaposto del messaggio
     *
     * @param value Valore da sostituire
     * @return Messaggio
     */
    public Message replacePlaceholder(Date value) {
        this.text = this.text.replaceFirst(VALUE_PLACEHOLDER, ConvertUtil.convertToString(value));

        return this;
    }

    

    

    /**
     * Sostituisce due valori ai segnaposto del messaggio
     *
     * @param value Valore da sostituire
     * @return Messaggio
     */
    public Message replacePlaceholder(String numeroIstanza, String soggettoDenominazione, String soggettoTelefono,
                                      String soggettoMail, String soggettoPec, String tipoIstanza) {
    	
    		logger.info("REPLACE 1 "+this.text);
            this.text = this.text.replaceFirst(VALUE_PLACEHOLDER_NUMERO_ISTANZA, IdfBoUtils.FORMAT.preventNull(numeroIstanza));
            logger.info("REPLACE 2 "+this.text);
            this.text = this.text.replaceFirst(VALUE_PLACEHOLDER_SOGGETTOGESTORE_DENOMINAZIONE, IdfBoUtils.FORMAT.preventNull(soggettoDenominazione));
            logger.info("REPLACE 3 "+this.text);
            this.text = this.text.replaceFirst(VALUE_PLACEHOLDER_SOGGETTOGESTORE_TELEFONO, IdfBoUtils.FORMAT.preventNull(soggettoTelefono));
            logger.info("REPLACE 4 "+this.text);
            this.text = this.text.replaceFirst(VALUE_PLACEHOLDER_SOGGETTOGESTORE_MAIL, IdfBoUtils.FORMAT.preventNull(soggettoMail));
            logger.info("REPLACE 5 "+this.text);
            this.text = this.text.replaceFirst(VALUE_PLACEHOLDER_SOGGETTOGESTORE_PEC, IdfBoUtils.FORMAT.preventNull(soggettoPec));
            logger.info("REPLACE 6 "+this.text);
            this.text = this.text.replaceFirst(VALUE_PLACEHOLDER_TIPO_ISTANZA, IdfBoUtils.FORMAT.preventNull(tipoIstanza));
            logger.info("REPLACE 7 "+this.text);
            
        return this;
    }

    

    /**
     * Sostituisce un valore al primo segnaposto del messaggio
     *
     * @param value Valore da sostituire
     * @return Messaggio
     */
    public Message replacePlaceholder(int value) {
        this.text = this.text.replaceFirst(VALUE_PLACEHOLDER, Integer.toString(value));

        return this;
    }

   
    

}
