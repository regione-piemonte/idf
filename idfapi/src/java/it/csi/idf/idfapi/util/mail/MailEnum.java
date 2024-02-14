/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.mail;

public enum MailEnum {
	INVIO_ISTANZA_TRASFORMAZIONI(1),
	CONFERMA_PROTOCOLLAZIONE_TRASFORMAZIONI(2),
	INVIO_ISTANZA_VINCOLO(3),
	CONFERMA_PROTOCOLLAZIONE_VINCOLO(4),
	RICEVUTA_VERSAMENTO_VINCOLO(5),

	INVIO_ISTANZA_TAGLI(7),
	CONFERMA_PROTOCOLLAZIONE_TAGLI(8),
	CONFERMA_INVIO_INTEGRAZIONE_TAGLI(9),
	CONFERMA_PROTOCOLLAZIONE_INTEGRAZIONE_TAGLI(10),
	ANNULLAMENTO_ISTANZA_TAGLI(11),
	MODIFICA_UTILIZZATORE_TAGLI(12);

	private int value;
	private MailEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
