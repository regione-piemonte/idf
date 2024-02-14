/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.mail;

/**
 * The type Invalid parameter exception.
 *
 * @author CSI PIEMONTE
 */
public class InvalidParameterException extends Exception {

    /**
     * Instantiates a new Invalid parameter exception.
     *
     * @param msg msg
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Invalid parameter exception.
     *
     * @param arg0 arg0
     */
    public InvalidParameterException(Throwable arg0) {
        super(arg0);

    }

}
