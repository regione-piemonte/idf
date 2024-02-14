/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AllStepsValidator {
	
	List<StepValidator> validatorsList;
	List<StepValidationErrors> stepValidatorErrors;
		
	public AllStepsValidator() {
		super();
		this.validatorsList = new ArrayList<StepValidator>();
		this.stepValidatorErrors = new ArrayList<>();
	}



	public void addStepValidator(StepValidator stepValidator) {
		this.validatorsList.add(stepValidator);
	}
	
	public boolean validateAllSteps() {
		int totalNoOfErrors = 0;
		for (Iterator<StepValidator> iterator = validatorsList.iterator(); iterator.hasNext();) {
			StepValidator stepValidator = iterator.next();
			HashMap<String, String> errorMap = stepValidator.validateFields();
			int noOfErrors = errorMap.keySet().size();
			totalNoOfErrors += noOfErrors;
			stepValidatorErrors.add(new StepValidationErrors(stepValidator.getStepNumber(), noOfErrors, errorMap));
		}
		
		return totalNoOfErrors == 0;
	}



	public List<StepValidationErrors> getStepValidatorErrors() {
		return stepValidatorErrors;
	}


}
