/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.IntervTrasf;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public interface IntervTrasfDAO {
	void insertIntervTrasf(IntervTrasf intervTrasf);
	
	int deleteIntervTrasfByFkIntervento(Integer idIntervento);
	
	IntervTrasf getIntervTrasfByid(int idgeo_pl_interv_trasf);
	
	List<String> getGeometrieGmlByFkIntervento(Integer idIntervento);

	//RicadenzaInfo getRicadenzaInfo(Integer idIntervento);
	
	void addGeometria(Integer idIntervento, String geometriGml);

	void addGeometriaGML(Integer idIntervento, Object geometriGml);	

	void removeGeometria(Integer idIntervento, Integer idGeoPlPropCatasto);	
	
	Double getAreaTrasformazioneByFkIntervento(Integer idIntervento);
	
	Double getAreaItersecWithParticella(Integer idIntervento, Integer idgeo_pl_prop_catasto);
	
	TipoIstanzaEnum getTipoIntervento(Integer idIntervento);
}
