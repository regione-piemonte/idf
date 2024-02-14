/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.HashMap;
import java.util.List;

public interface StepValidator {
	
	public HashMap<String,String> validateFields(); 
	public int getStepNumber();
}
