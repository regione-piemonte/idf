/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.impl;

import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.service.TAIFService;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.PersonaFisGiu;
import it.csi.idf.idfapi.dto.TAIFAnagraficaAzienda;
import it.csi.idf.idfapi.dto.TSoggetto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.util.*;

@Component
public class TAIFServiceImpl implements TAIFService {

    public static Logger logger = Logger.getLogger(TAIFServiceImpl.class);
    private String apiUrl;

    public TAIFServiceImpl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public TAIFServiceImpl() { }

    @Autowired
    private ComuneDAO comuneDAO;


    @Override
    @Cacheable("aziendeTAIF")
    public List<TAIFAnagraficaAzienda> findAll() throws Exception {

        List<TAIFAnagraficaAzienda> aziende = new ArrayList<>();

        ClientRequest request = new ClientRequest(this.apiUrl);
        logger.info("CALL TAIF URL: -- "+ this.apiUrl + " --");

        request.accept(MediaType.APPLICATION_JSON);
        ClientResponse response = request.get();
        int apiResponseCode = response.getResponseStatus().getStatusCode();
        if(response.getResponseStatus().getStatusCode() != 200)
        {
            logger.info("ResponseCode ------->" + apiResponseCode);
            return new ArrayList<>();
            //throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
        }
        TAIFAnagraficaAzienda[] array = (TAIFAnagraficaAzienda[])response.getEntity(TAIFAnagraficaAzienda[].class);
        Collections.addAll(aziende, array);
        return aziende;
    }

    @Override
    public TAIFAnagraficaAzienda findByCodiceFiscale(String codiceFiscale) throws Exception {
        List<TAIFAnagraficaAzienda> aziende = this.findAll();

        CollectionUtils.filter(aziende, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TAIFAnagraficaAzienda p = (TAIFAnagraficaAzienda) o;
                return p.getCodiceFiscale().equalsIgnoreCase(codiceFiscale);
            }
        });
        return aziende.isEmpty() ? null : aziende.get(0);
    }

    @Override
    public List<TAIFAnagraficaAzienda> findByCodiceFiscaleTitolare(String codiceFiscale) throws Exception {
        List<TAIFAnagraficaAzienda> aziende = this.findAll();

        CollectionUtils.filter(aziende, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TAIFAnagraficaAzienda p = (TAIFAnagraficaAzienda) o;
                return p.getCodiceFiscaleTitolare().equalsIgnoreCase(codiceFiscale);
            }
        });
        return aziende;
    }

    @Override
    public List<TAIFAnagraficaAzienda> searchByCodiceFiscaleDenominazionePartitaIVa(String search) throws Exception {
        List<TAIFAnagraficaAzienda> aziende = this.findAll();

        CollectionUtils.filter(aziende, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                TAIFAnagraficaAzienda p = (TAIFAnagraficaAzienda) o;

                return p.getCodiceFiscale().toLowerCase().contains(search.toLowerCase()) ||
                p.getPartitaIva().toLowerCase().contains(search.toLowerCase()) ||
                p.getRagioneSociale().toLowerCase().contains(search.toLowerCase()) ;
            }
        });
        return aziende;
    }


    @Override
    public PersonaFisGiu fillPersonaGiuridicaByTaifService(TAIFAnagraficaAzienda taif) {
        PersonaFisGiu azienda = new PersonaFisGiu();
        azienda.setCodiceFiscale(taif.getCodiceFiscale());
        azienda.setPartitaIva(taif.getPartitaIva());
        azienda.setDenominazione(taif.getRagioneSociale());
        azienda.setIndirizzo(taif.getIndirizzo());
        azienda.setCivico(taif.getCivico());
        azienda.setCap(taif.getCap());
        azienda.setNrTelefonico(taif.getTelefono());
        azienda.seteMail(taif.getEmail());
        azienda.setNumAlboForestale(taif.getNumeroAlbo());
        azienda.setFlgEntePubblico(false);
        azienda.setPec(taif.getPec());

        try {
            ComuneResource comune = comuneDAO.findComuneResourceByIstatComune(taif.getIstatComune());
            azienda.setComune(comune);
        }catch(Exception ex) {
            logger.error("Comune not found per istat: " + taif.getIstatComune());
        }
        return azienda;
    }


    @Override
    public TSoggetto fillAziendaByTaifService(TAIFAnagraficaAzienda taif) {
        TSoggetto azienda = new TSoggetto();
        azienda.setCodiceFiscale(taif.getCodiceFiscale());
        azienda.setPartitaIva(taif.getPartitaIva());
        azienda.setDenominazione(taif.getRagioneSociale());
        azienda.setIndirizzo(taif.getIndirizzo());
        azienda.setCivico(taif.getCivico());
        azienda.setCap(taif.getCap());
        azienda.setNrTelefonico(taif.getTelefono());
        azienda.seteMail(taif.getEmail());
        azienda.setNrAlboForestale(taif.getNumeroAlbo());
        azienda.setFlgEntePubblco((byte)0);
        azienda.setPec(taif.getPec());

        try {
            ComuneResource comune = comuneDAO.findComuneResourceByIstatComune(taif.getIstatComune());
            azienda.setFkComune(comune != null?comune.getIdComune():null);
        }catch(Exception ex) {
            logger.error("Comune not found per istat: " + taif.getIstatComune());
        }
        return azienda;
    }
}
