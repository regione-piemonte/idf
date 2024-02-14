/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.SoggettoResource;
import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.dto.TipoIstanzaResource;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class BOSearchResultMapper implements RowMapper<BOSearchResult> {

	@Override
	public BOSearchResult mapRow(ResultSet rs, int arg1) throws SQLException {

		BOSearchResult boSearchResult = new BOSearchResult();

		ObjectMapper mapper = new ObjectMapper();

		if (rs.getString("comune_info") != null) {
			String jsonstring = "[" + rs.getString("comune_info") + "]";
			List<ComuneResource> comuneList;
			try {
				comuneList = Arrays.asList(mapper.readValue(jsonstring, ComuneResource[].class));
				boSearchResult.setComuneIinfo(comuneList);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (rs.getString("transf_info") != null) {
			 boSearchResult.setTransfInfo(rs.getString("transf_info"));
		}
		boSearchResult.setIdIstanza(rs.getInt("id_intervento"));

		TipoIstanzaResource tipoIstanzaResource = new TipoIstanzaResource();
		tipoIstanzaResource.setIdTipoIstanza(rs.getInt("id_tipo_istanza"));
		tipoIstanzaResource.setDescrDettTipoIstanza(rs.getString("descr_dett_tipoistanza"));
		boSearchResult.setTipologiaIstanza(tipoIstanzaResource);

		boSearchResult.setAnnoIstanza(rs.getString("anno_istanza"));
		boSearchResult.setNumeroIstanza(rs.getInt("nr_istanza_forestale"));

		StatoIstanzaResource statoIstanzaResource = new StatoIstanzaResource();
		statoIstanzaResource.setIdStatoIstanza(rs.getInt("id_stato_istanza"));
		String descStato = StatoIstanzaResource.getDescrByTypeIstanza(rs.getString("descr_stato_istanza"), 
				TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA);
		statoIstanzaResource.setDescrStatoIstanza(descStato);
		boSearchResult.setStatoIstanza(statoIstanzaResource);

		boSearchResult
				.setDataPresentazione(rs.getDate("data_invio") == null ? null : rs.getDate("data_invio").toLocalDate());

		SoggettoResource soggettoResource = new SoggettoResource();
		soggettoResource.setIdSoggetto(rs.getInt("id_soggetto"));
		soggettoResource.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggettoResource.setPartitaIva(rs.getString("partita_iva"));
		soggettoResource.setNome(rs.getString("nome"));
		soggettoResource.setCognome(rs.getString("cognome"));
		soggettoResource.setDenominazione(rs.getString("denominazione"));
		boSearchResult.setRichiedente(soggettoResource);

		boSearchResult.setAreeProtette(rs.getInt("intervento_aapp") > 0);
		boSearchResult.setNatura2000(rs.getInt("intervento_rn") > 0);
		boSearchResult.setPopulamenti(rs.getInt("intervento_pop") > 0);
		boSearchResult.setVincIdrogeologico(rs.getInt("flg_vinc_idro") == 1);

		boSearchResult.setCompensazione((rs.getString("flg_compensazione")==null)? null : SuperficieCompensationEnum.valueOf(rs.getString("flg_compensazione")));
		boSearchResult.setEuro(rs.getBigDecimal("compensazione_euro_teorica") );

		return boSearchResult;
	}
}
