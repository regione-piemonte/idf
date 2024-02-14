/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.HashMap;

import it.csi.idf.idfapi.dto.Combustibile;
import it.csi.idf.idfapi.dto.GeoPtAreaDiSaggio;
import it.csi.idf.idfapi.dto.Superficie;
import it.csi.idf.idfapi.util.ISuperficieNotaDefaults;

public class AreaDiSaggioAlberiCDValidator implements StepValidator{
	
	
	public AreaDiSaggioAlberiCDValidator() {
		super();
		
	}

	public static final String ERROR_PREFIX = "DATI_ONE_";


	@Override
	public HashMap<String,String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<>();
		
		
		return errorMap;
	}


	@Override
	public int getStepNumber() {
		// TODO Auto-generated method stub
		return 2;
	}
}
