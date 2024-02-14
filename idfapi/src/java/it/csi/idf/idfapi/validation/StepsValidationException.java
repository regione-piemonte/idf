/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.List;

import it.csi.idf.idfapi.util.CodiceEnum;

public class StepsValidationException extends BaseException {

	{
		setCodice(CodiceEnum.ADS_CONSOLIDA_VALIDATION_ERROR);
		setTesto("Validation Errore");
	}
	
	List<StepValidationErrors> stepsErrors;

	public StepsValidationException(List<StepValidationErrors> stepsErrors) {
		super();		
		this.stepsErrors = stepsErrors;
	}

	public List<StepValidationErrors> getStepsErrors() {
		return stepsErrors;
	}

	public void setStepsErrors(List<StepValidationErrors> stepsErrors) {
		this.stepsErrors = stepsErrors;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return stepsErrors;
	}
	
	
	
	
}
