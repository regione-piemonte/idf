/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.RicadenzaIntervento;
import it.csi.idf.idfapi.dto.RicadenzaPfa;
import it.csi.idf.idfapi.dto.RicadenzaPortaseme;
import it.csi.idf.idfapi.util.ProceduraEnum;

public interface RicadenzeDAO {
	
	public List<RicadenzaInformazioni> getRicadenzeForestali(Integer idIntervento, ProceduraEnum procedura);
	public List<RicadenzaInformazioni> getRicadenzeForestaliFromDB(Integer idIntervento, ProceduraEnum procedura);
	public List<RicadenzaInformazioni> getRicadenzePopolamentiSeme(Integer idIntervento, ProceduraEnum procedura);
	public List<RicadenzaInformazioni> getRicadenzeCategForestaliPfa(Integer idIntervento);
	public Double getRicadenzeVincoloIdrogeologico(Integer idIntervento, ProceduraEnum procedura);

	List<RicadenzaPortaseme> getRicadenzePortaSeme(Integer idIntervento, ProceduraEnum procedura);

	List<RicadenzaPfa> getRicadenzePFA(Integer idIntervento, ProceduraEnum procedura);
	
	public List<RicadenzaIntervento>getRicadenzeIntervento(Integer idIntervento);
}
