/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;

public interface PropcatastoInterventoDAO {
	List<PropcatastoIntervento> getAllPropcatastoByIdIntervento(int idIntervento);

	List<PropcatastoIntervento> getPropcatastoByIdInterventoAndByIdGeo(int idIntervento, int idGeo);

	List<PlainParticelleCatastali> getPfaAllPropcatastoByGeomGeeco(int idIntervento);
	void insertPropcatastoIntervento(PropcatastoIntervento propcatastoIntervento);
	void deletePropcatastoIntervento(int idgeoPlPropCatasto, int idIntervento);
	void insertParticelleInPropcatastoIntervento(Integer idIntervento, PlainParticelleCatastali plainParticelleCatastali,
			ConfigUtente loggedConfig) throws DuplicateKeyException;
	void deleteAllByIdIntervento(Integer idIntervento);
	
	Double getAreaCatastaleByIdIntervento(Integer idIntervento);

	Double getAreaInterventoByIdIntervento(Integer idIntervento);

}
