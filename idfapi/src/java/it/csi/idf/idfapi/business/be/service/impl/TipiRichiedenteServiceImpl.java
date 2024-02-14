/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.impl;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.*;
import it.csi.idf.idfapi.business.be.service.TagliSelvicolturaliService;
import it.csi.idf.idfapi.business.be.service.TipiRichiedenteService;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Component
public class TipiRichiedenteServiceImpl extends SpringSupportedResource implements TipiRichiedenteService {

    @Autowired
    private TipoRichiedenteDAO tipoRichiedenteDAO;


    @Override
    public TipoRichiedente getTipoRichiedenteById(int idTipo) {
        return tipoRichiedenteDAO.getTipoRichiedenteById(idTipo);
    }

    @Override
    public List<TipoRichiedente> getAll() {
        return tipoRichiedenteDAO.getTipoAll();
    }




}
