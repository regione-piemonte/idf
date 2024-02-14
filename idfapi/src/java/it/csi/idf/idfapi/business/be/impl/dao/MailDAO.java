/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.DatiMailInvoIstanza;
import it.csi.idf.idfapi.util.mail.EmailDTO;

public interface MailDAO {
	EmailDTO getConfEmail(int idMail, Integer idconf);
	EmailDTO getConfEmail(int idMail);
	DatiMailInvoIstanza getDatiInvioIstanzaTrasformazioni(Integer idIstanza);
}
