/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.PersonaFisica;

public interface PersonaFisicaDAO {
	PersonaFisica getPersonaFisica();
	
	int createPersonaFisica(PersonaFisica newPersonaFisica) throws ValidationException;
	
	public void savePersona(String codiceFiscale, PersonaFisica newPersonaFisica) throws RecordNotFoundException;
}
