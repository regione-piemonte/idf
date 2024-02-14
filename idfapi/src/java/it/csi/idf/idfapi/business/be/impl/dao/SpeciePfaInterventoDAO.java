/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.SpecieInterventoVolumes;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.dto.TSpecie;

public interface SpeciePfaInterventoDAO {

	int createSpeciePfaIntervento(SpeciePfaIntervento speciePfaIntervento, Integer idIntervento);
	
	void deleteSpeciePfaIntervento(Integer idSpecie, Integer idIntervento);
	
	Integer getSpeciaVolumeByInterventoId(Integer idIntervento);
	
	void updateSpeciePfaIntervento(SpeciePfaIntervento specieInterv, Integer idIntervento);

	int[] batchCreate(SpeciePfaIntervento[] specieIntervs, Integer idIntervento);

	void deleteAllSpeciePfaInterventoByIdIntervento(Integer idIntervento);

	List<SpecieInterventoVolumes> getSpecieByInterventoId(Integer idIntervento);	
		
}
