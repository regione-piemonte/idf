/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class StepNumber {

	private int nextStep;

	public StepNumber() {
	}

	public StepNumber(int nextStep) {
		this.nextStep = nextStep;
	}

	public int getNextStep() {
		return nextStep;
	}

	public void setNextStep(int nextStep) {
		this.nextStep = nextStep;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StepNumber [nextStep=");
		builder.append(nextStep);
		builder.append("]");
		return builder.toString();
	}
}
