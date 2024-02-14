/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DichiarazioneSummaryDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoLnLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasformazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoAappDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoCatforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoPopSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoRn2000DAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.MacroCatforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParametroApplDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParamtrasfTrasformazionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PartforInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PfPgDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PopolamentoSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkSelvDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SupForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoParametroTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ZonaAltimetricaDAO;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.business.be.vincolo.dao.ParametroVincoloDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.SkOkVincoloDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloDto;
import it.csi.idf.idfapi.business.be.vincolo.service.WrapperVincoloDao;
@Component
//public class WrapperVincoloDaoImpl extends GenericDAO<VincoloDto> implements WrapperVincoloDao {
public class WrapperVincoloDaoImpl   implements WrapperVincoloDao {

	

	@Autowired
	DichiarazioneSummaryDAO dichiarazioneSummaryDAO;

	@Autowired
	private CategoriaForestaleDAO categoriaForestaleDAO;

	@Autowired
	private ComuneDAO comuneDAO;

	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	private GeoLnLottoInterventoDAO geoLnLottoInterventoDAO;

	@Autowired
	private GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;

	@Autowired
	private GeoPtLottoInterventoDAO geoPtLottoInterventoDAO;

	@Autowired
	private GSAREPORT gsareport;

	@Autowired
	private InterventoDAO interventoDAO;

	@Autowired
	private IntervTrasformazioneDAO intervTrasformazioneDAO;

	@Autowired
	private IntervTrasfDAO intervTrasfDAO;

	@Autowired
	private InterventoAappDAO interventoAappDAO;

	@Autowired
	private InterventoCatforDAO interventoCatforDAO;

	@Autowired
	private InterventoPopSemeDAO interventoPopSemeDAO;

	@Autowired
	private InterventoRn2000DAO interventoRn2000DAO;

	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;

	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@Autowired
	private ParametroApplDAO parametroApplDAO;

	@Autowired
	private ParametroVincoloDAO parametroVincoloDAO;

	@Autowired
	private ParamtrasfTrasformazionDAO paramtrasfTrasformazionDAO;

	@Autowired
	private PfPgDAO pfPgDAO;

	@Autowired
	private PopolamentoSemeDAO popolamentoSemeDAO;

	@Autowired
	private PropCatastoDAO propCatastoDAO;

	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;

	@Autowired
	private MacroCatforDAO macroCatforDAO;

	@Autowired
	private RicadenzaService ricadenzaService;

	@Autowired
	private SkOkVincoloDAO skOkVincoloDAO;

	@Autowired
	private SkOkSelvDAO skOkSelvDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private SoggettoInterventoDAO soggettoInterventoDAO;

	@Autowired
	private SupForestaleDAO supForestaleDAO;

	@Autowired
	private TipoForestaleDAO tipoForestaleDAO;

	@Autowired
	private TipoParametroTrasfDAO tipoParametroTrasfDAO;

	@Autowired
	private ZonaAltimetricaDAO zonaAltimetricaDAO;

	@Autowired
	private PartforInterventoDAO partforInterventoDAO;

//  TO DO  to get object SIGMATER
	@Autowired
	SIGMATER sigmater;

	@Override
	public List<VincoloDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VincoloDto findById(Integer idVincolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(VincoloDto vincolo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VincoloDto create(VincoloDto vincolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(VincoloDto vincoloDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VincoloDto save(VincoloDto vincoloDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Integer idVincolo) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
