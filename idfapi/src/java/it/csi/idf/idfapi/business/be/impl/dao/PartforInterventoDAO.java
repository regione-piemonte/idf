/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import it.csi.idf.idfapi.dto.ChiusuraInformazioni;
import it.csi.idf.idfapi.dto.InterventoRiepilogo;
import it.csi.idf.idfapi.dto.PartforRipresaInformazioni;

public interface PartforInterventoDAO {
	
	int createParforInterv(Integer idParticelaForestale, Integer idIntervento) throws DuplicateKeyException;
	
	void deletePartforIntervento(Integer idIntervento);
	
	List<PartforRipresaInformazioni> getPartforRipresaInformazioniForChiusura(Integer idIntervento);

	ChiusuraInformazioni getChiusuraInformazioniByIdIntervento(Integer idIntervento);
	
	void updatePartforInterventoWithChiusuraDates(Integer idIntervento,InterventoRiepilogo interventoRiepilogo);
	
	void updatePartforInterventoWithRipresaIntervento(Integer idIntervento, Integer idGeoParticellaForest,BigDecimal ripresaIntervento);

	void updatePartforInterventoAtCompleta(Integer idIntervento, Integer idGeoParticellaForest, BigDecimal ripresaAttuale);
	
	void updatePartforInterventoAfterEditing(Integer idIntervento);
	
	void updateRipresaInterventoPfa(Integer idIntervento, Double areaInterventoHa);
	
	
	
}
