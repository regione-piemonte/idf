/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.GeoPlParticellaForest;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

public interface GEECO {

	public BigDecimal retrieveSupInterventoForParticelleCatastali(PlainParticelleCatastali plainParticelleCatastali);
	public void editSupInterventoValues(Integer idIntervento, List<PlainParticelleCatastali> particelle, ConfigUtente loggedConfig);
	public void insertSupInterventoValue(Integer idIntervento, PlainParticelleCatastali plainParticelleCatastali,
			ConfigUtente loggedConfig);
	public List<GeoPlParticellaForest> determinaForestParticellePerGeometriaGML(PfaLottoLocalizza body,
			Integer idGeoPlPfa);
	
}
