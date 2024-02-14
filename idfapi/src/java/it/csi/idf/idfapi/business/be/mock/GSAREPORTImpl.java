/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import java.util.ArrayList;
import java.util.List;

import org.opengis.geometry.Geometry;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

@Component
public class GSAREPORTImpl implements GSAREPORT {
	
	private final List<RicadenzaInformazioni> allRicadenzas = new ArrayList<>();
	
	public GSAREPORTImpl() {	
		RicadenzaInformazioni ricadenzaInformazioni1 = new RicadenzaInformazioni();
		ricadenzaInformazioni1.setCodiceAmministrativo("111");
		ricadenzaInformazioni1.setNome("sicSita");
//		ricadenzaInformazioni1.setPercentualeRicadenza(11);
		
		RicadenzaInformazioni ricadenzaInformazioni2 = new RicadenzaInformazioni();
		ricadenzaInformazioni2.setCodiceAmministrativo("112");
		ricadenzaInformazioni2.setNome("area");
//		ricadenzaInformazioni2.setPercentualeRicadenza(12);
		
		RicadenzaInformazioni ricadenzaInformazioni3 = new RicadenzaInformazioni();
		ricadenzaInformazioni3.setCodiceAmministrativo("113");
		ricadenzaInformazioni3.setNome("zpsSita");
//		ricadenzaInformazioni3.setPercentualeRicadenza(13);
		
		allRicadenzas.add(ricadenzaInformazioni1);
		allRicadenzas.add(ricadenzaInformazioni2);
		allRicadenzas.add(ricadenzaInformazioni3);
	}
	
	@Override
	public List<RicadenzaInformazioni> determinaRicadenzaSuAreeProtettePerGeometriaGML(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzas = new ArrayList<>();
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("112");
		ricadenzaInformazioni.setNome("area");
//		ricadenzaInformazioni.setPercentualeRicadenza(12);
		
		ricadenzas.add(ricadenzaInformazioni);

		return ricadenzas;
	}
	
	@Override
	public List<RicadenzaInformazioni> determinaRicadenzaSuSicPerGeometriaGML(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzas = new ArrayList<>();
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("111");
		ricadenzaInformazioni.setNome("sicSita");
//		ricadenzaInformazioni.setPercentualeRicadenza(11);
		
		ricadenzas.add(ricadenzaInformazioni);
		
		return ricadenzas;
	}

	@Override
	public List<RicadenzaInformazioni> determinaRicadenzaSuZpsPerGeometriaGML(Geometry mergedGeometry) {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		List<RicadenzaInformazioni> ricadenzas = new ArrayList<>();
		
		RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni();
		ricadenzaInformazioni.setCodiceAmministrativo("113");
		ricadenzaInformazioni.setNome("zpsSita");
//		ricadenzaInformazioni.setPercentualeRicadenza(13);
		
		ricadenzas.add(ricadenzaInformazioni);
		
		return ricadenzas;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTutteLePotettePerFiltri() {
		return allRicadenzas;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTuttiSic() {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		return allRicadenzas;
	}

	@Override
	public List<RicadenzaInformazioni> cercaTuttiZps() {
		//TODO Parse from response of external service(String or List<String>) to List<RicadenzaInformazioni>
		return allRicadenzas;
	}
}
