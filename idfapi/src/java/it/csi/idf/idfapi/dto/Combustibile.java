/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Combustibile {
	private Long idgeoPtAds;
	private String codCombustibile;	
	private Integer percCoperturaLettiera;
	private Integer percCoperturaErbacea;
	private Integer percCoperturaCespugli;
	private Byte flgPrincipale;
	
	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	public String getCodCombustibile() {
		return codCombustibile;
	}
	public void setCodCombustibile(String codCombustibile) {
		this.codCombustibile = codCombustibile;
	}
	public Integer getPercCoperturaLettiera() {
		return percCoperturaLettiera;
	}
	public void setPercCoperturaLettiera(Integer percCoperturaLettiera) {
		this.percCoperturaLettiera = percCoperturaLettiera;
	}
	public Integer getPercCoperturaErbacea() {
		return percCoperturaErbacea;
	}
	public void setPercCoperturaErbacea(Integer percCoperturaErbacea) {
		this.percCoperturaErbacea = percCoperturaErbacea;
	}
	public Integer getPercCoperturaCespugli() {
		return percCoperturaCespugli;
	}
	public void setPercCoperturaCespugli(Integer percCoperturaCespugli) {
		this.percCoperturaCespugli = percCoperturaCespugli;
	}
	public Byte getFlgPrincipale() {
		return flgPrincipale;
	}
	public void setFlgPrincipale(Byte flgPrincipale) {
		this.flgPrincipale = flgPrincipale;
	}
}
