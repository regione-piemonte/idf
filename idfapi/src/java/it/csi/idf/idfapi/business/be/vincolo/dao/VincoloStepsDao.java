/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.InvioIstanzaDTO;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.TSoggetto;

public interface VincoloStepsDao {
	List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento);

	InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento);

	void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto tSoggetto) throws RecordNotFoundException, RecordNotUniqueException;
	
	PlainSezioneId saveLocalizzaLotto(PfaLottoLocalizza body, ConfigUtente configUtente, Integer idGeoPlPfa) throws RecordNotUniqueException;

	void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException;

	void updateIstanzaRifiutata(Integer idIntervento, TSoggetto soggetoFromUser) throws RecordNotFoundException, RecordNotUniqueException;

	 BoOwnerIstanze getUtenteForIstanza(Integer idIntervento);

	GeneratedFileTrasfParameters getCeoIstanza(Integer idIntervento);
	
	void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente loggedConfig) throws RecordNotFoundException, RecordNotUniqueException;

}
