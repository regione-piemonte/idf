/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.csi.idf.idfapi.dto.AreaProtetta;
import it.csi.idf.idfapi.dto.Popseme;
import it.csi.idf.idfapi.dto.RN2000;
import it.csi.idf.idfapi.dto.RicadenzaHolder;

public class RicadenzaHolderMapper {
	
	private RicadenzaHolderMapper() {}
	
	public static List<RicadenzaHolder> areeToRicadenza(List<AreaProtetta> aree) {
		
		if (aree == null || aree.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<RicadenzaHolder> response = new ArrayList<>();
		
		for(AreaProtetta area : aree) {
			RicadenzaHolder ricadenzaHolder = new RicadenzaHolder();
			ricadenzaHolder.setNome(area.getNomeAreaProtetta());
			ricadenzaHolder.setSupHa(area.getSupRicadenzaHa());
			ricadenzaHolder.setPercRicadenza(area.getPercRicadenza());
			
			response.add(ricadenzaHolder);
		}
		
		return response;
	}
	
	public static List<RicadenzaHolder> rnsToRicadenza(List<RN2000> rns) {
		
		if (rns == null || rns.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<RicadenzaHolder> response = new ArrayList<>();
		
		for(RN2000 rn : rns) {
			RicadenzaHolder ricadenzaHolder = new RicadenzaHolder();
			ricadenzaHolder.setNome(rn.getNomeRn2000());
			ricadenzaHolder.setSupHa(rn.getSupRicadenzaHa());
			ricadenzaHolder.setPercRicadenza(rn.getPercRicadenza());
			
			response.add(ricadenzaHolder);
		}
		
		return response;
	}
	
	public static List<RicadenzaHolder> popsemesToRicadenza(List<Popseme> popsemes) {
		
		if (popsemes == null || popsemes.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<RicadenzaHolder> response = new ArrayList<>();
		
		for(Popseme popseme : popsemes) {
			RicadenzaHolder ricadenzaHolder = new RicadenzaHolder();
			ricadenzaHolder.setNome(popseme.getDenominazione());
			ricadenzaHolder.setSupHa(popseme.getSupRicadenzaHa());
			ricadenzaHolder.setPercRicadenza(popseme.getPercRicadenza());
			
			response.add(ricadenzaHolder);
		}
		
		return response;
	}
}
