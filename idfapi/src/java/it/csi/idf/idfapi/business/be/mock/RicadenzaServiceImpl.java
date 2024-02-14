/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import java.util.ArrayList;
import java.util.List;

import it.csi.idf.idfapi.business.be.impl.dao.RicadenzeDAO;
import it.csi.idf.idfapi.dto.RicadenzaPfa;
import it.csi.idf.idfapi.dto.RicadenzaPortaseme;
import it.csi.idf.idfapi.util.ProceduraEnum;
import org.opengis.geometry.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.RicadenzaIntervento;

@Component
public class RicadenzaServiceImpl implements RicadenzaService {

	@Autowired
	private RicadenzeDAO ricadenzeDAO;

	public RicadenzaServiceImpl() {
		RicadenzaInformazioni ricadenzaInformazioni1 = new RicadenzaInformazioni();
		ricadenzaInformazioni1.setCodiceAmministrativo("0057");
		ricadenzaInformazioni1.setNome("Pianna di Usseglio");
//		ricadenzaInformazioni1.setPercentualeRicadenza(10);
		
		RicadenzaInformazioni ricadenzaInformazioni2 = new RicadenzaInformazioni();
		ricadenzaInformazioni2.setCodiceAmministrativo("AB");
		ricadenzaInformazioni2.setNome("Abetine");
//		ricadenzaInformazioni2.setPercentualeRicadenza(10);
		
		allRicadenzas.add(ricadenzaInformazioni1);
		allRicadenzas.add(ricadenzaInformazioni2);
	}
	
	private final List<RicadenzaInformazioni> allRicadenzas = new ArrayList<>();

	@Override
	public List<RicadenzaInformazioni> getPopolamentiDaSemes(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzaInformations = new ArrayList<>();
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("0057");
		ricadenzaInformazioni.setNome("Pianna di Usseglio");
//		ricadenzaInformazioni.setPercentualeRicadenza(10);
		
		ricadenzaInformations.add(ricadenzaInformazioni);
		
		return ricadenzaInformations;
	}

	@Override
	public List<RicadenzaInformazioni> getCategoriesForestali(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzaInformations = new ArrayList<>();
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("AB");
		ricadenzaInformazioni.setNome("Abetine");
//		ricadenzaInformazioni.setPercentualeRicadenza(10);
		
		ricadenzaInformations.add(ricadenzaInformazioni);
		
		return ricadenzaInformations;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTuttiPopolamentiDaSeme() {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		return allRicadenzas;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTutteCategorieForestali() {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		return allRicadenzas;
	}


	@Override
	public List<RicadenzaInformazioni> getRicadenzeForestali(Integer idIntervento, ProceduraEnum procedura) {
		return ricadenzeDAO.getRicadenzeForestali(idIntervento, procedura);
	}

	@Override
	public List<RicadenzaInformazioni> getRicadenzeForestaliFromDB(Integer idIntervento, ProceduraEnum procedura) {
		return ricadenzeDAO.getRicadenzeForestaliFromDB(idIntervento, procedura);
	}

	@Override
	public List<RicadenzaInformazioni> getRicadenzePopolamentiSeme(Integer idIntervento, ProceduraEnum procedura) {
		return ricadenzeDAO.getRicadenzePopolamentiSeme(idIntervento,procedura);
	}

	@Override
	public List<RicadenzaInformazioni> getRicadenzeCategForestaliPfa(Integer idIntervento) {
		return ricadenzeDAO.getRicadenzeCategForestaliPfa(idIntervento);
	}

	@Override
	public List<RicadenzaPortaseme> getRicadenzePortaSeme(Integer idIntervento, ProceduraEnum procedura) {
		return  ricadenzeDAO.getRicadenzePortaSeme(idIntervento, procedura);
	}

	@Override
	public List<RicadenzaPfa> getRicadenzePFA(Integer idIntervento, ProceduraEnum procedura) {
		return ricadenzeDAO.getRicadenzePFA(idIntervento, procedura);
	}

	@Override
	public Double getRicadenzeVincoloIdrogeologico(Integer idIntervento, ProceduraEnum procedura) {
		return ricadenzeDAO.getRicadenzeVincoloIdrogeologico(idIntervento,procedura);
	}

	@Override
	public List<RicadenzaIntervento> getRicadenzaIntervento(Integer idIntervento) {
		return ricadenzeDAO.getRicadenzeIntervento(idIntervento);
		
	}
}
