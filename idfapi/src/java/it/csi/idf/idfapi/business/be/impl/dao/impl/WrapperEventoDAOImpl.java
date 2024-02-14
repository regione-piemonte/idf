/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.EventoPartforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoLnEventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlEventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtEventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperEventoDAO;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.PlainEventoId;
import it.csi.idf.idfapi.dto.PlainPrimaPfaEvento;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;
import it.csi.idf.idfapi.util.TipoEventoEnum;

@Component
public class WrapperEventoDAOImpl implements WrapperEventoDAO {

	@Autowired
	private EventoDAO eventoDao;
	
	@Autowired
	private EventoPartforDAO eventoPartforDao;
	
	@Autowired
	private GeoPlEventoDAO geoPlEventoDao;
	
	@Autowired
	private GeoPtEventoDAO geoPtEventoDao;
	
	@Autowired
	private GeoLnEventoDAO geoLnEventoDao;
	
	@Autowired
	private GeoPlParticellaForestDAO geoPlParticellaForestDao;
	
	private static Integer ID_PARTFOR_DEFAULT = 0;
	
	
	/*@Override
	public PlainEventoId savePrimaPfaEvento(PlainPrimaPfaEvento body, int idGeoPlPfa) {
		
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setTipoEvento(TipoEventoEnum.VALANGA.getTypeValue()); //TODO mocked
		eventoDTO.setDataEvento(LocalDate.now());
		eventoDTO.setProgressivoEventoPfa(eventoDao.getProssimoProgressivoEventoPfa(TipoEventoEnum.VALANGA)); //TODO type should be the same as above
		int idEvento = eventoDao.createEvento(eventoDTO);
		
		// TODO taking randomly found id from geo_pl_particella_forest, should be depending on geometry
		
		eventoPartforDao.createEventoPartfor(idEvento, geoPlParticellaForestDao.getIdForestParticlesByIdGeoPfa(idGeoPlPfa));
		
		geoPlEventoDao.insertGeoPlEvento(idEvento, null);
		geoPtEventoDao.insertGeoPtEvento(idEvento, null);
		geoLnEventoDao.insertGeoLnEvento(idEvento, null);
		
		return new PlainEventoId(idEvento);
	}
*/
	@Override
    public EventoDTO savePrimaPfaEvento(PlainPrimaPfaEvento body, int idGeoPlPfa) {
       
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setIdPfa(idGeoPlPfa);
        eventoDTO.setTipoEvento(TipoEventoEnum.VALANGA.getTypeValue());
        eventoDTO.setDataEvento(LocalDate.now());
        eventoDTO.setProgressivoEventoPfa(eventoDao.getProssimoProgressivoEventoPfa(idGeoPlPfa));
        int idEvento = eventoDao.createEvento(eventoDTO);
        eventoDTO.setIdEvento(idEvento);
       
        //temp code
        List<Integer> list = geoPlParticellaForestDao.getIdForestParticlesByIdGeoPfaWithSize(idGeoPlPfa, 1);
        if(list!=null && list.size()> 0) {
        	eventoPartforDao.createEventoPartfor(idEvento, list.get(0));
        }
        return eventoDTO;
	}
	
	@Override
	public void saveSecondoPfaEvento(PlainSecondoPfaEvento evento, Integer idEvento) throws RecordNotFoundException {
		eventoDao.updateEvento(evento, idEvento);
		
		eventoPartforDao.updateEventoPartfor(idEvento, evento.getPercDanno());			
		
	}

	@Override
	public void deleteEventi(Integer idEvento) throws RecordNotFoundException {
		
		geoPlEventoDao.deleteGeoPlEvento(idEvento);
		geoPtEventoDao.deleteGeoPtEvento(idEvento);
		geoLnEventoDao.deleteGeoLnEvento(idEvento);
		eventoPartforDao.deleteEventoPartfor(idEvento);
		eventoDao.deleteEvento(idEvento);
	}

	@Override
	public List<DrawedGeometryInfo> getDrawedGeometryList(Integer idEvento) {
		return eventoDao.getDrawedGeometryList(idEvento);
	}

	@Override
	public void ricalcolaSuperficiFromGeeco(Integer idEvento) {
		geoPlEventoDao.updateSuperficieHaByEvento(idEvento);
		eventoPartforDao.deleteEventoPartfor(idEvento);
		List<Integer> listIdsPartFor = geoPlParticellaForestDao.getIdsForestParticlesByIdEvento(idEvento);
		if(listIdsPartFor != null && listIdsPartFor.size() > 0) {
			for(Integer idPartFor:listIdsPartFor) {
				eventoPartforDao.createEventoPartfor(idEvento, idPartFor);
			}
		}else {
			eventoPartforDao.createEventoPartfor(idEvento, ID_PARTFOR_DEFAULT);
		}
	}
}
