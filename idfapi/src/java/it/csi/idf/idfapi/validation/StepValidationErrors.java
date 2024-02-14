/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.HashMap;

public class StepValidationErrors {
	
	private Integer stepNumber;
	private Integer noOfErrors;
	private HashMap<String, String> errorMessages;
	
	public StepValidationErrors(Integer stepNumber, Integer noOfErrors, HashMap<String, String> errorMessages) {
		super();
		this.stepNumber = stepNumber;
		this.noOfErrors = noOfErrors;
		this.errorMessages = errorMessages;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

	public Integer getNoOfErrors() {
		return noOfErrors;
	}

	public void setNoOfErrors(Integer noOfErrors) {
		this.noOfErrors = noOfErrors;
	}

	public HashMap<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(HashMap<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StepValidationErrors [stepNumber=");
		builder.append(stepNumber);
		builder.append(", noOfErrors=");
		builder.append(noOfErrors);
		builder.append(", errorMessages=");
		builder.append(errorMessages);
		builder.append("]");
		return builder.toString();
	}
	
		
}
