/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.SpecieInterventoFinalita;

import java.util.List;

public interface SpecieInterventoFinalitaDAO {

	int createSpecieInterventoFinalita(SpecieInterventoFinalita specieIntervento, Integer idIntervento, Integer fkConfigUtente);
	
	void deleteSpecieInterventoFinalita(Integer idSpecie, Integer idIntervento, Integer idFinalita);
	
	void deleteAllByIdIntervento(Integer idIntervento);

	List<SpecieInterventoFinalita> getSpecieByInterventoId(Integer idIntervento);

	List<SpecieInterventoFinalita> getAllSpecieByInterventoAndIdSpecie(Integer idIntervento, Integer idSpecie);


/*
	Integer getSpeciaVolumeByInterventoId(Integer idIntervento);

	void updateSpeciePfaIntervento(SpeciePfaIntervento specieInterv, Integer idIntervento);

	int[] batchCreate(SpeciePfaIntervento[] specieIntervs, Integer idIntervento);


*/

}
