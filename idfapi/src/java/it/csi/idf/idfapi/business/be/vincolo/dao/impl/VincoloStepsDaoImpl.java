/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.vincolo.dao.VincoloStepsDao;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.BoscoHeadings;
import it.csi.idf.idfapi.dto.BoscoSubHeadings;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.InvioIstanzaDTO;
import it.csi.idf.idfapi.dto.ParametroTrasf;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.PlainTerzaSezione;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoParametroTrasf;
@Component
public class VincoloStepsDaoImpl implements VincoloStepsDao {

	@Override
	public List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto tSoggetto)
			throws RecordNotFoundException, RecordNotUniqueException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PlainSezioneId saveLocalizzaLotto(PfaLottoLocalizza body, ConfigUtente configUtente, Integer idGeoPlPfa)
			throws RecordNotUniqueException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIstanzaRifiutata(Integer idIntervento, TSoggetto soggetoFromUser)
			throws RecordNotFoundException, RecordNotUniqueException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoOwnerIstanze getUtenteForIstanza(Integer idIntervento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneratedFileTrasfParameters getCeoIstanza(Integer idIntervento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente loggedConfig)
			throws RecordNotFoundException, RecordNotUniqueException {
		// TODO Auto-generated method stub
		
	}
}
