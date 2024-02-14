/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.StatoInterventoApi;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.StatoInterventoDAO;
import it.csi.idf.idfapi.dto.StatoIntervento;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class StatoInterventoApiServiceImpl extends SpringSupportedResource implements StatoInterventoApi {

	@Autowired
	private StatoInterventoDAO statoInterventoDAO;
	
	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;
	
	@Override
	public List<StatoIntervento> getStatoList() {
		return statoInterventoDAO.getStatiInterventi();
	}

	@Override
	public Map<String,Object> getStatoByIdIntervento(Integer idIntervento) {
		Map<String, Object> res = new HashMap<String,Object>();
		res.put("stato",statoInterventoDAO.getStatoByIdIntervento(idIntervento));
		res.put("idPfa", intervSelvicoulturaleDAO.getIdgeoPlPfaByIdIntervento(idIntervento));
		return res;
	}

}
