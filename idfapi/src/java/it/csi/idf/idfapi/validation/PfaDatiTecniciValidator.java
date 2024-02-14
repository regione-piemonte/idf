/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.HashMap;

import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;

public class PfaDatiTecniciValidator implements StepValidator {
	
	private InterventoDatiTehnici intervDatiTehnici;
	Integer idGeoPlPfa;
	
	public PfaDatiTecniciValidator(InterventoDatiTehnici intervDatiTehnici) {
		super();
		this.intervDatiTehnici = intervDatiTehnici;
	}

	@Override
	public HashMap<String, String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<>();
		IntervSelvicolturale intSelv = intervDatiTehnici.getIntervSelvicolturale();
		SpeciePfaIntervento[] listSpecie = intervDatiTehnici.getSpeciePfaIntervento();
		TipoInterventoDatiTecnici tipoInterv = intervDatiTehnici.getTipoIntervento();
		
		UtilValidator.checkNotNullFiledValidity("conformeDeroga", errorMap, intSelv.getFlgConformeDeroga(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("dataPresuntaIntervento", errorMap, tipoInterv.getDataPresuntaIntervento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("annataSilvana", errorMap, tipoInterv.getAnnataSilvana(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		//UtilValidator.checkNotNullFiledValidity("eventoCorrelato", errorMap, tipoInterv.getIdEventoCorelato(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("governo", errorMap, tipoInterv.getFkGoverno(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("tipoIntervento", errorMap, tipoInterv.getFkTipoIntervento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("descrizione", errorMap, tipoInterv.getDescrizione(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("localita", errorMap, tipoInterv.getLocalita(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("fasciaAltimentrica", errorMap, intSelv.getFkFasciaAltimetrica(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("finalitaTaglio", errorMap, tipoInterv.getFkFinalitaTaglio(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("destLegname", errorMap, tipoInterv.getFkDestLegname(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		
		return errorMap;
	}

	@Override
	public int getStepNumber() {
		return 1;
	}

}
