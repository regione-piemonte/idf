/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.dto.InterventoRiepilogo;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.PaginatedList;


public interface InterventoDAO {

	List<Intervento> findAllInterventi();
	
	InterventoPiano findInterventoPianoByID(Integer idIntervento);

	String findDenominazionePianoByID(Integer idGeoPlPfa);
	
	int createInterventoWithConfigUtente(int fkConfigUtente);
	int createInterventoTagliWithConfigUtente(ConfigUtente configUtente);

	int createIntervento(TipoInterventoDatiTecnici interventoDatiTehnici) throws DuplicateRecordException;
	
	int createInterventoNEW(TipoInterventoDatiTecnici interventoDatiTehnici, ConfigUtente loggedConfig);
	
    void updateIntervento(TipoInterventoDatiTecnici interventoDatiTehnici, Integer idIntervento, int fkConfigUtente) throws RecordNotFoundException;
    
    Intervento findInterventoByIdIntervento(int idIntervento);
    
    void updateInterventoAtDatiTecnici(TipoInterventoDatiTecnici interventoDatiTehnici, Integer idIntervento, int fkConfigUtente) throws RecordNotFoundException;
    
    void updateInterventoWithSuperficieInteressata(Integer idIntervento, BigDecimal superficieInteressata);
    
    void updateInterventoWithFkSoggettoProfess(Integer idIntervento, Integer fkSoggettoProfess);
    
    void deleteIntervento(Integer idIntervento);

	PaginatedList<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa, int page, int limit, String sort);
	
	List<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa);

	void updateInterventoWithChiusuraData(InterventoRiepilogo interventoRiepilogo, Integer idIntervento);
	
	void updateDataFineIntervento(Date dataFineIntervento, Integer idIntervento);
	
	TSoggetto getUserCompilatoreByIdIntervento(int idIntervento);

	void updateInterventoDescrizione(String descrizione, Integer idIntervento, int fkConfigUtente);
	void updateInterventoSoggettoProfess(Integer idSoggetto, Integer idIntervento, int fkConfigUtente);

	int getCountLocalita(int idIntervento);
	
	List<DrawedGeometryInfo> getDrawedGeometryPfaList(Integer idIntervento);

	String getStringParticelleForestaliByIntervento(Integer idIntervento);
	
}
