/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.PlainPrimaPfaEvento;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;

@Transactional(rollbackFor=Exception.class)
public interface WrapperEventoDAO {
	
	EventoDTO savePrimaPfaEvento(PlainPrimaPfaEvento body, int idGeoPlPfa);

	void saveSecondoPfaEvento(PlainSecondoPfaEvento evento, Integer idEvento)throws RecordNotFoundException;
	
	void deleteEventi(Integer idEvento) throws RecordNotFoundException;
	List<DrawedGeometryInfo> getDrawedGeometryList(Integer idEvento);
	
	void ricalcolaSuperficiFromGeeco(Integer idEvento);
}
