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

public class AreaDiSaggioDatiStazionaliOneValidator implements StepValidator{
	
	GeoPtAreaDiSaggio areaDiSaggio;	
	Combustibile combustibile;
	Superficie superficieNota;
	
	public AreaDiSaggioDatiStazionaliOneValidator(GeoPtAreaDiSaggio areaDiSaggio, Combustibile combustibile,
			Superficie superficieNota) {
		super();
		this.areaDiSaggio = areaDiSaggio;
		this.combustibile = combustibile;
		this.superficieNota = superficieNota;
	}

	public static final String ERROR_PREFIX = "DATI_ONE_";


	@Override
	public HashMap<String,String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<>();
		UtilValidator.checkNotNullFiledValidity("fk_asseto", errorMap, areaDiSaggio.getFkAssetto(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("fk_tipo_forestale", errorMap, areaDiSaggio.getFkGeoTipoForestale(), UtilValidator.MANDATORY_FIELD_MESSAGE);
				
		/* Validating superficia nota fields */
		UtilValidator.checkNotNullFiledValidity("densita", errorMap, superficieNota.getDensitaCampionamento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("raggioArea", errorMap, superficieNota.getRaggioMt(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("tipoStructurale", errorMap, superficieNota.getFkTipoStrutturalePrinc(),ISuperficieNotaDefaults.FK_TIPO_STRUTTURALE_PRINC_DEFAULT,UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("stadioDiSvilupe", errorMap, superficieNota.getCodStadioSviluppo(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		Integer classeFertilita = superficieNota.getFkClasseFertilita();
		if(classeFertilita != null && classeFertilita == 0) {
			classeFertilita = null;
		}
		UtilValidator.checkNotNullFiledValidity("classeFertilita", errorMap, classeFertilita, UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("nceppaie", errorMap, superficieNota.getNrCeppaie(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNegativeNumberValidity("nceppaie", errorMap, superficieNota.getNrCeppaie(), UtilValidator.NOT_NEGATIVE_FIELD_MESSAGE);
//		UtilValidator.checkNotNullFiledValidity("rinnovazione", errorMap, superficieNota.getRinnovazione(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNegativeNumberValidity("rinnovazione", errorMap, superficieNota.getRinnovazione(), UtilValidator.NOT_NEGATIVE_FIELD_MESSAGE);
		
		UtilValidator.checkNotNullFiledValidity("specPrevRinno", errorMap, superficieNota.getRinSpeciePrevalente(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("coperturaChiome", errorMap, superficieNota.getPercCoperturaChiome(), UtilValidator.MANDATORY_FIELD_MESSAGE);				
		
		
		/* Validating ads compustibile fields */
		UtilValidator.checkNotNullFiledValidity("coperturaLettiera", errorMap, combustibile.getPercCoperturaLettiera(),UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("coperturaCespugli", errorMap, combustibile.getPercCoperturaCespugli(),UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("coperturaErbacea", errorMap, combustibile.getPercCoperturaErbacea(),UtilValidator.MANDATORY_FIELD_MESSAGE);
		
		return errorMap;
	}


	@Override
	public int getStepNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
