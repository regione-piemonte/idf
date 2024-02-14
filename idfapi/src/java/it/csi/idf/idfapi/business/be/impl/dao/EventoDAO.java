/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;
import it.csi.idf.idfapi.dto.ProgressivoNomeBreve;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.TipoEventoEnum;

public interface EventoDAO {

	List<EventoDTO> findAllEventi();
	
	EventoDTO findEventoById (Integer idEvento) throws RecordNotFoundException,EmptyResultDataAccessException;
	
	int createEvento(EventoDTO evento);
	
	void updateEvento(PlainSecondoPfaEvento evento, Integer idEvento) throws RecordNotFoundException;
	
	void deleteEvento(Integer idEvento) throws RecordNotFoundException;
	
	PaginatedList<EventoPiano> findEventiPianoDTOByIdGeoPlPfa(int idGeoPlPfa, int page, int limit, String sort);
	
	List<EventoPiano> findEventiPianoDTOByIdGeoPlPfa(int idGeoPlPfa);
	
	int getProssimoProgressivoEventoPfa(int idGeoPlPfa);
	
	PlainSecondoPfaEvento findSecondoPfaEventoById(Integer idEvento);

	List<ProgressivoNomeBreve> findProgressiviNomeBreve(int idGeoPlPfa);
	
	Integer getIdgeoPlPfaByIdEvento(Integer idEvento);
	
	List<DrawedGeometryInfo> getDrawedGeometryList(Integer idEvento);
}
