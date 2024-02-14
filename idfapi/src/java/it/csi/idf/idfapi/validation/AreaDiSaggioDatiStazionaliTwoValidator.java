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

public class AreaDiSaggioDatiStazionaliTwoValidator implements StepValidator {
	
	GeoPtAreaDiSaggio areaDiSaggio;
	Superficie superficieNota;
	
	public AreaDiSaggioDatiStazionaliTwoValidator(GeoPtAreaDiSaggio areaDiSaggio, Superficie superficieNota) {
		super();
		this.areaDiSaggio = areaDiSaggio;
		this.superficieNota = superficieNota;
	}

	public static final String ERROR_PREFIX = "DATI_ONE_";
	

	@Override
	public HashMap<String, String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<String, String>();
		
		UtilValidator.checkNotNullFiledValidity("fk_destinazione", errorMap, areaDiSaggio.getFkDestinazione(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("fk_tipo_intervento", errorMap, areaDiSaggio.getFkTipoIntervento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("fk_priorita", errorMap, areaDiSaggio.getFkPriorita(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("fk_danno", errorMap, areaDiSaggio.getFkDanno(), UtilValidator.MANDATORY_FIELD_MESSAGE);
				
		/* Validating superficia nota fields */		
		UtilValidator.checkNotNullFiledValidity("cod_ebosco", errorMap, superficieNota.getCodEsbosco(), UtilValidator.MANDATORY_FIELD_MESSAGE);
//		UtilValidator.checkNotNullFiledValidity("dist_esbosco_fuori_pista", errorMap, superficieNota.getDistEsboscoFuoriPista(), UtilValidator.MANDATORY_FIELD_MESSAGE);
//		UtilValidator.checkNotNullFiledValidity("dist_esbosco_su_pista", errorMap, superficieNota.getDistEsboscoSuPista(),UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("min_distanza_planimetrica", errorMap, superficieNota.getMinDistanzaPlanimetrica(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("danni_perc", errorMap, superficieNota.getDanniPerc(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("nr_piante_morte", errorMap, superficieNota.getNrPianteMorte(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("perc_defogliazione", errorMap, superficieNota.getPercDefogliazione(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("perc_ingiallimento", errorMap, superficieNota.getPercIngiallimento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		UtilValidator.checkNotNullFiledValidity("flg_pascolamento", errorMap, superficieNota.getFlgPascolamento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
		return errorMap;
	}

	@Override
	public int getStepNumber() {
		// TODO Auto-generated method stub
		return 1;
	}



}
