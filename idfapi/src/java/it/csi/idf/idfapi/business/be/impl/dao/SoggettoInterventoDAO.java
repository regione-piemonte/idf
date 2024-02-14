/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.SoggettoIntervento;
import it.csi.idf.idfapi.dto.SoggettoResource;

public interface SoggettoInterventoDAO {
	
	int createSoggettoIntervento(SoggettoIntervento soggettoIntervento);

	SoggettoIntervento getSoggettoInterventoById(Integer idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException;
	
	SoggettoIntervento getSoggettoInterventoByIdInterventoAndIdTipoSoggetto(Integer idIntervento, Integer idTipoSoggetto)	throws RecordNotFoundException, RecordNotUniqueException;

	void updateWithIdSoggetto(int idSoggetto, int idIntervento);
	
	void updateSoggetoInterventoAtChiusura(Integer idIntervento, SoggettoResource utillizatore);

	List<SoggettoIntervento> getAllSoggettosByInterventoId(Integer idIntervento);

	int updateSoggetoIntervento(Integer idIntervento, Integer idTipoSoggetto, Integer idSoggetto);
	
	void deleteByIdIntervento(Integer idIntervento);
	void deleteUtilizzatoreByIdIntervento(Integer idIntervento);
	
	
}
