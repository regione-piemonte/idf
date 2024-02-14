/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.HashMap;
import java.util.List;

import it.csi.idf.idfapi.dto.TipoIntervento;

public interface TipoInterventoDAO {
	List<TipoIntervento> findAllTipoIntervento();
	Integer findConfigIplaByTipoIntervento(Integer tipoIntervento);
	List<TipoIntervento> findAllTipoInterventoByFkConfigIpla(Integer fkConfigIpla);
	List<TipoIntervento> findAllTipoInterventoByGovernoAndFkConfigIpla(Integer fkConfigIpla, Integer idGoverno);
	List<TipoIntervento>  findAllTipoInterventoCompetenzaRegionale();

	List<TipoIntervento> findAllByGovernoAndFkConfigIplaAndMacroIntervento(Integer fkConfigIpla, Integer idGoverno, Integer idMacroIntervento);

	List<TipoIntervento> findAllByFkConfigIplaAndCategoriaIntervento(Integer fkConfigIpla, Integer idCategoriaIntervento);
	List<HashMap> getTipoInterventoConformeDeroga();	
}
