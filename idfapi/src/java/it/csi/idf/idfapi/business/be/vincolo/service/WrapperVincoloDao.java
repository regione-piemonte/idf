/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloDto;

public interface WrapperVincoloDao {

	List<VincoloDto> findAll();

	VincoloDto findById(Integer idVincolo);

	boolean exists(VincoloDto vincolo);

	VincoloDto create(VincoloDto vincolo);

	boolean update(VincoloDto vincoloDto);

	VincoloDto save(VincoloDto vincoloDto);

	boolean deleteById(Integer idVincolo);




}
