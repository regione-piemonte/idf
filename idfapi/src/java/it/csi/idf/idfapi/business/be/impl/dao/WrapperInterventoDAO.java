/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.InterventoRiepilogo;
import it.csi.idf.idfapi.dto.InterventoUtilizzatore;
import it.csi.idf.idfapi.dto.IstanzaRiepilogo;
import it.csi.idf.idfapi.dto.ParticelleInterventoDettaglio;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.ShootingMirrorDTO;
import it.csi.idf.idfapi.dto.SpecieInterventoDetails;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoInterventoDettaglio;
import it.csi.idf.idfapi.dto.UtilizzatoreDetails;
import it.csi.idf.idfapi.validation.StepValidationErrors;

@Transactional(rollbackFor = Exception.class)
public interface WrapperInterventoDAO {

	void saveSecondStep(InterventoDatiTehnici interventoDatiTehnici, ConfigUtente loggedConfig, Integer idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;
	
	PlainSezioneId saveDatiTecniciNEW(InterventoDatiTehnici interventoDatiTehnici, ConfigUtente loggedConfig, Integer idGeoPlPfa)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;

	void deleteIntervento(Integer idIntervento) throws RecordNotFoundException,ValidationException;

	StepNumber getNumberOfNextStep(Integer idIntervento);

	InterventoDatiTehnici getDatiTechiciForStep2(Integer idIntervento);

	void updateDatiTechici(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, RecordNotFoundException;

	List<ShootingMirrorDTO> getDataForShootingMirror(Integer idIntervento);

	List<StepValidationErrors> saveUtilizzatoreETecnico(InterventoUtilizzatore interventoUtilizzatore, ConfigUtente loggedConfig, Integer idIntervento)
			throws RecordNotUniqueException, ValidationException;

	IstanzaRiepilogo getDataForRiepilogo(Integer idIntervento);

	TSoggetto getDataForSavingGiurdicaAtUtilizzatoreStep(String codiceFiscale) throws RecordNotUniqueException;

	TSoggetto getDataForSavingFisicaAtUtilizzatoreStep(String codiceFiscale) throws RecordNotUniqueException;

	TipoInterventoDettaglio getTipoInterventoDettaglioByIdIntervento(Integer idIntervento);

	ParticelleInterventoDettaglio getParticelleInterventoDettaglioByIdIntervento(Integer idIntervento) throws ServiceException;

	SpecieInterventoDetails getSpecieInterventoDettaglioByIdIntervento(Integer idIntervento);

	UtilizzatoreDetails getUtilizzatoreForIntervento(Integer idIntervento);

	void chiudiIntervento(Integer idIntervento,InterventoRiepilogo interventoRiepilogo, ConfigUtente loggedConfig) throws ValidationException;
	
	void salvaIntervento(Integer idIntervento,InterventoRiepilogo interventoRiepilogo, ConfigUtente loggedConfig);

	InterventoRiepilogo getDataForChiusura(Integer idIntervento);

	PropCatasto retreiveDataForParticle(Integer idGeoPlPfa,Integer comune, String sezione, Integer foglio, String particella);

	void saveLocalizzaLottoNEW(Integer idIntervento, PfaLottoLocalizza body, ConfigUtente loggedConfig, Integer idGeoPlPfa) throws DuplicateKeyException;

	InterventoDatiTehnici getDatiTechiciForEditNEW(Integer idIntervento);

	List<StepValidationErrors> updateDatiTecniciNEW(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici, ConfigUtente loggedConfig, Integer idGeoPlPfa) throws RecordNotFoundException;

	InterventoUtilizzatore getUtilizzatoreETecnico(Integer idIntervento) throws RecordNotUniqueException;

	List<StepValidationErrors> updateUtilizzatoreETecnico(InterventoUtilizzatore interventoUtilizzatore, ConfigUtente loggedConfig,
			Integer idIntervento) throws RecordNotUniqueException;

	void trasmettiAPrimpa(Integer idIntervento) throws Exception;

	List<PlainParticelleCatastali> getCadastralParticlesForEdit(Integer idIntervento);
	
	List<PlainParticelleCatastali> getLocalizzaLottoFromGeeco(Integer idIntervento, ConfigUtente loggedConfig) throws ServiceException;

	void updateLocalizzaLotto(PfaLottoLocalizza body, ConfigUtente loggedConfig, Integer idIntervento, Integer idGeoPlPfa);

	void sendParticlesToGeeco(Integer idIntervento, List<PlainParticelleCatastali> particelleCatastali,
			ConfigUtente loggedConfig);

	List<PlainParticelleCatastali> retreiveDataFromGeeco(Integer idIntervento);

	void insertParticlesInPropcatastoIntervento(Integer idIntervento, PlainParticelleCatastali particella,
			ConfigUtente loggedConfig);
	
	PlainSecondoSezione getDataLotto(Integer idIntervento) throws RecordNotUniqueException, ValidationException;
	
	public PlainSecondoSezione getRicadenzePfa(Integer idIntervento) throws ServiceException;
	
	public List<StepValidationErrors> getErrorsInterventoPfa(Integer idIntervento) throws Exception;
	
	List<DrawedGeometryInfo> getDrawedGeometryPfaList(Integer idIntervento);
	
	void updateRipresaInterventoPfa(Integer idIntervento);

}
