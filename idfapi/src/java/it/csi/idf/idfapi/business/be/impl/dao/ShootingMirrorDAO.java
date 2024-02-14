/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.ShootingMirrorDTO;

public interface ShootingMirrorDAO {

	List<ShootingMirrorDTO> getAllParticlesForShootingMirror(Integer idIntervento);
	List<ShootingMirrorDTO> getAllParticlesForShootingMirrorNEW(Integer idIntervento);
}
