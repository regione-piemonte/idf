/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import java.util.List;

import it.csi.idf.idfapi.dto.RicadenzaPfa;
import it.csi.idf.idfapi.dto.RicadenzaPortaseme;
import it.csi.idf.idfapi.util.ProceduraEnum;
import org.opengis.geometry.Geometry;

import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.RicadenzaIntervento;

public interface RicadenzaService {
	
	List<RicadenzaInformazioni> getPopolamentiDaSemes(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> getCategoriesForestali(Geometry mergedGeometry);
	
	List<RicadenzaInformazioni> cercaTuttiPopolamentiDaSeme();
	List<RicadenzaInformazioni> cercaTutteCategorieForestali();


	List<RicadenzaInformazioni> getRicadenzeForestali(Integer idIntervento, ProceduraEnum procedura);

	List<RicadenzaInformazioni> getRicadenzeForestaliFromDB(Integer idIntervento, ProceduraEnum procedura);
	List<RicadenzaInformazioni> getRicadenzePopolamentiSeme(Integer idIntervento, ProceduraEnum procedura);
	List<RicadenzaInformazioni> getRicadenzeCategForestaliPfa(Integer idIntervento);

	List<RicadenzaPortaseme> getRicadenzePortaSeme(Integer idIntervento, ProceduraEnum procedura);

	List<RicadenzaPfa> getRicadenzePFA(Integer idIntervento, ProceduraEnum procedura);
	Double getRicadenzeVincoloIdrogeologico(Integer idIntervento, ProceduraEnum procedura);

	List<RicadenzaIntervento>getRicadenzaIntervento(Integer idIntervento);
}
