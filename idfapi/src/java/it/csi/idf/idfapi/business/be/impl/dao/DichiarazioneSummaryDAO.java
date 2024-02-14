/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeneratedFileTagliParameters;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.GeneratedFileVincoloParameters;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;

public interface DichiarazioneSummaryDAO {
	
	GeneratedFileTrasfParameters getSummaryTrasformazioni(int idIntervento);
	GeneratedFileVincoloParameters getSummaryVincolo(int idIntervento);

	GeneratedFileTagliParameters getSummaryTagli(int idIntervento);
	TSoggetto getSoggettoCompetenteRegionale (int idIntervento,AmbitoIstanzaEnum ambito);
	TSoggetto getSoggettoCompetenteComunale (int idIntervento,AmbitoIstanzaEnum ambito);
}
