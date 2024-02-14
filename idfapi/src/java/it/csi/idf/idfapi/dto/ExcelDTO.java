/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class ExcelDTO {
	
	private List<Long> idgeoPtAdsList;
	
	private List<String> codiceADSList; // old - should be deleted
	
	private List<GeoPlPfaExcelDTO> pfaExcelDto;
	
	private List<Integer> idEvento;
	
	private List<Integer> intervento;
	
	private Integer idGeoPfaEventi;
	
	private Integer idGeoPfaInterventi;
	
	
	public List<Long> getIdgeoPtAdsList() {
		return idgeoPtAdsList;
	}

	public void setIdgeoPtAdsList(List<Long> idgeoPtAdsList) {
		this.idgeoPtAdsList = idgeoPtAdsList;
	}

	public List<String> getCodiceADSList() {
		return codiceADSList;
	}

	public void setCodiceADSList(List<String> codiceADSList) {
		this.codiceADSList = codiceADSList;
	}

	public List<GeoPlPfaExcelDTO> getPfaExcelDto() {
		return pfaExcelDto;
	}

	public void setPfaExcelDto(List<GeoPlPfaExcelDTO> pfaExcelDto) {
		this.pfaExcelDto = pfaExcelDto;
	}

	public List<Integer> getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(List<Integer> idEvento) {
		this.idEvento = idEvento;
	}

	public List<Integer> getIntervento() {
		return intervento;
	}

	public void setIntervento(List<Integer> intervento) {
		this.intervento = intervento;
	}

	public Integer getIdGeoPfaEventi() {
		return idGeoPfaEventi;
	}

	public Integer getIdGeoPfaInterventi() {
		return idGeoPfaInterventi;
	}

	public void setIdGeoPfaEventi(Integer idGeoPfaEventi) {
		this.idGeoPfaEventi = idGeoPfaEventi;
	}

	public void setIdGeoPfaInterventi(Integer idGeoPfaInterventi) {
		this.idGeoPfaInterventi = idGeoPfaInterventi;
	}

	
	
}
