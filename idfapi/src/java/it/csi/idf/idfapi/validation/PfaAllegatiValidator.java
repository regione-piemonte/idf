/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;
import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;

public class PfaAllegatiValidator implements StepValidator {
	private static final String MANDATORY_ATTACHED_MESSAGE = "Allegato obbligatorio";
	
	List<FatDocumentoAllegato> listAllegati;
	TipoInterventoDatiTecnici tipoIntervDatiTechici;

	public PfaAllegatiValidator(List<FatDocumentoAllegato> listAllegati, TipoInterventoDatiTecnici tipoIntervDatiTechici) {
		super();
		this.listAllegati = listAllegati;
		this.tipoIntervDatiTechici = tipoIntervDatiTechici;
	}

	@Override
	public HashMap<String, String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<>();
		
		if(tipoIntervDatiTechici.getConformeDeroga() == ConformeDerogaEnum.D) {
			checkDocsType(errorMap, listAllegati, new TipoAllegatoPfaEnum[] {
					TipoAllegatoPfaEnum.CARTOGRAFIA_AREA_DI_INTERVENTO,
					TipoAllegatoPfaEnum.PROGETTO_DI_INTERVENTO
			});
			
		}
		return errorMap;
	}

	@Override
	public int getStepNumber() {
		return 3;
	}
	
	private void checkDocsType(HashMap<String,String> errorMap, List<FatDocumentoAllegato> listAllegati, TipoAllegatoPfaEnum[] listTipoAllegati ) throws ValidationException {
		if (listAllegati == null || listAllegati.size() == 0 ) {
			listAllegati = new ArrayList<FatDocumentoAllegato>();
			listAllegati.add(new FatDocumentoAllegato());
			listAllegati.get(0).setIdTipoAllegato(-1);
		}
		boolean isDocFound;
		for(TipoAllegatoPfaEnum tipo : listTipoAllegati) {
			isDocFound = false;
			for(FatDocumentoAllegato allegato : listAllegati) {
				if(allegato.getIdTipoAllegato() == tipo.getValue()) {
					isDocFound = true;
					break;
				}
			}
			if(!isDocFound) {
				errorMap.put(tipo.name(), MANDATORY_ATTACHED_MESSAGE);
			}
		}
		
	
	}

}
