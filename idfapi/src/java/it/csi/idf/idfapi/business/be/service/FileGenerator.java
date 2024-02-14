/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import java.io.File;

import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;

public interface FileGenerator {
	
	File generateDichiarazione(TipoAllegatoEnum tipoAllegato, int idIntervento) throws Exception;
	File generateDichiarazione(TipoAllegatoEnum tipoAllegato, int idIntervento, ConfigUtente confUtente) throws Exception;
    byte[] generateDichiarazioneXdoc(TipoAllegatoPfaEnum tipoAllegato, int idIntervento, ConfigUtente confUtente) throws Exception;
}
