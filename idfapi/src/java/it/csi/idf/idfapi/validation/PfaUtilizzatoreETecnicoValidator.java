/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.HashMap;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.InterventoUtilizzatore;
import it.csi.idf.idfapi.util.TipoDiUtilizzatoreEnum;
import it.csi.idf.idfapi.util.TipoUtilizzatoreRicercaEnum;

public class PfaUtilizzatoreETecnicoValidator implements StepValidator {
	private InterventoUtilizzatore interventoUtilizzatore;
	

	public PfaUtilizzatoreETecnicoValidator(InterventoUtilizzatore interventoUtilizzatore) {
		super();
		this.interventoUtilizzatore = interventoUtilizzatore;
	}

	@Override
	public HashMap<String, String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<>();
		if(interventoUtilizzatore.getTipoDiUtilizzatore() == TipoDiUtilizzatoreEnum.RICERCA.getValue()){
			if(interventoUtilizzatore.getTipoDiUtilizzatore() == TipoUtilizzatoreRicercaEnum.PERSONA_FISICA.getValue()) {
				UtilValidator.checkNotNullFiledValidity("nome", errorMap, interventoUtilizzatore.getNome(), UtilValidator.MANDATORY_FIELD_MESSAGE);
				UtilValidator.checkNotNullFiledValidity("cognome", errorMap, interventoUtilizzatore.getCognome(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			}else if(interventoUtilizzatore.getTipoDiUtilizzatore() == TipoUtilizzatoreRicercaEnum.PERSONA_GIURIDICA.getValue()) {
				UtilValidator.checkNotNullFiledValidity("denominazione", errorMap, interventoUtilizzatore.getDenominazione(), UtilValidator.MANDATORY_FIELD_MESSAGE);
				UtilValidator.checkNotNullFiledValidity("pec", errorMap, interventoUtilizzatore.getPec(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			}
		if(!isComuneValid(interventoUtilizzatore.getComune())){
			interventoUtilizzatore.setComune(null);
		}
		UtilValidator.checkNotNullFiledValidity("comuneResidenza", errorMap, interventoUtilizzatore.getComune(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("indirizzo", errorMap, interventoUtilizzatore.getIndirizzo(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("civico", errorMap, interventoUtilizzatore.getCivico(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("cap", errorMap, interventoUtilizzatore.getCap(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("numTelefono", errorMap, interventoUtilizzatore.getNrTelefonico(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("email", errorMap, interventoUtilizzatore.geteMail(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		}
		return errorMap;
	}
	
	private boolean isComuneValid(ComuneResource comune) {
		if(comune != null && comune.getDenominazioneComune() != null && comune.getDenominazioneComune().length() > 0) {
			return true;
		}
		return false;
			
	}

	@Override
	public int getStepNumber() {
		return 2;
	}
}
