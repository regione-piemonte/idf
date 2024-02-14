/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.*;

public interface IntervSelvicoulturaleDAO {
	
	void insertIntervSelvicolturale(IntervSelvicolturale intervSelvicolturale, Integer idIntervento);
	
	void insertIntervSelvicolturaleNEW(IntervSelvicolturale intervSelvicolturale, Integer idGeoPlPfa, ConfigUtente loggedConfig, Integer idIntervento);
	
	void updateIntervSelvicolturale(IntervSelvicolturale interventoSelvi, Integer idIntervento );

	void insertStep1(IntervSelvicolturale intervSelvicolturale, Integer idIntervento);
	int updateStep1(IntervSelvicolturale intervSelvicolturale, Integer idIntervento);

	void updateIntervSelvicolturaleWithChiusuraData(InterventoRiepilogo interventoRiepilogo, Integer idIntervento, ConfigUtente loggedConfig);
	
	void deleteIntervSelvicolturale(Integer idIntervento);
	
	void updateNoteFinaliRichiedente(Integer idIntervento, String oteFinaliRichiedente);
	
	int getProssimoNrProgInterv(int idTipoIntervento);
	
	IntervSelvicolturale findInterventoSelvicolturale(Integer idIntervento);
	IntervSelvicolturaleFat findInterventoSelvicolturaleTagli(Integer idIntervento);

	DettagliDiTaglio getDettaglioDiTaglioByInterventoId(Integer idIntervento);
	
	RicadenzaInfo getRicadenzaInfo(Integer idIntervento);

	void cambiamentoStato(Integer idIntervento, String statoIntervento);
	
	Integer getStimaMassaByIntervento(Integer idIntervento);
	
	String getStatoInterventoByIdIntervento(Integer idIntervento);
	
	Integer getIdgeoPlPfaByIdIntervento(Integer idIntervento);

	int updateIntervSelvicolturaleWithFasciaAltimetrica(Integer idFasciaAltimetrica, Integer idIntervento);

	int updateIntervSelvicolturaletagliStep4(IntervSelvicolturale dto, Integer idIntervento);


    Boolean isDrelMancante(Integer idIntervento);

	FatDocumentoAllegato getDrel(Integer idIntervento);

    void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente);

	Integer getFkProprietaByidIntervento(Integer idIntervento);

//	String getNoteFinaliRichiedente(Integer idIntervento);
}
