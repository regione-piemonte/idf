/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import it.csi.idf.idfapi.dto.PersonaFisGiu;
import it.csi.idf.idfapi.dto.TAIFAnagraficaAzienda;
import it.csi.idf.idfapi.dto.TSoggetto;

import java.util.List;

public interface TAIFService {
	
	List<TAIFAnagraficaAzienda> findAll() throws Exception;
	TAIFAnagraficaAzienda findByCodiceFiscale(String codiceFiscale) throws Exception;
	List<TAIFAnagraficaAzienda> findByCodiceFiscaleTitolare(String codiceFiscale) throws Exception;

	List<TAIFAnagraficaAzienda> searchByCodiceFiscaleDenominazionePartitaIVa(String search) throws Exception;

	PersonaFisGiu fillPersonaGiuridicaByTaifService(TAIFAnagraficaAzienda taif);

	TSoggetto fillAziendaByTaifService(TAIFAnagraficaAzienda taif);
}
