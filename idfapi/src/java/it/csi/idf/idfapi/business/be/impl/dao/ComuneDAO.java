/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;

public interface ComuneDAO {

	List<ComuneResource> findAllComune();

	List<ComuneResource> findComuneByNome(String comune);

	Comune findComuneByID(Integer idComune) throws RecordNotFoundException;
	Comune findComuneByName(String name) throws RecordNotUniqueException;
	int createComune(Comune comune);

	ComuneResource findComuneResourceByID(Integer idComune) throws RecordNotUniqueException;
	List<ComuneResource> findAllComuneByProvincia(String istatProv);
	ComuneResource findComuneResourceByIstatComune(String istatComune) throws RecordNotUniqueException;
	
	List<ComuneResource> findAllComuneByPfa(Integer idGeoPlPfa);

	List<ComuneResource> findAllComuneByIntervento(Integer idIntervento);
	
	List<ComuneResource> findComuneBoEnabled(int idSoggetto, String istatProv);

	ComuneResource findComuneResourceByCodiceBelfiore(String codiceBelfiore) throws RecordNotUniqueException;

}
