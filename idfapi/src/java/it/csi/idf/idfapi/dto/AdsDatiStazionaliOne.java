/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class AdsDatiStazionaliOne {

		// AreaDiSaggio
		Long idgeoPtAds;
		String codiceADS;
		Integer tipoForestale;
		Integer assettoEvolutivoColturale;
		// Superficia Nota		
		Double densitaCamp;
		Integer raggioArea;
		Integer tipoStrutturale;
		String stadioDiSviluppo;
		Integer nCepaie;		
		Integer rinnovazione;
		String speciePrevalenteRinnovazione;
		Integer coperturaChiome;
		Integer idClasseDiFertilita;		
		//Combustibile
		Integer coperturaErbacea;		
		Integer coperturaCespugli;	
		Integer lettiera;
		
		Integer categoriaForestale;
		String tipologia;
		
		public Long getIdgeoPtAds() {
			return idgeoPtAds;
		}
		public void setIdgeoPtAds(Long idgeoPtAds) {
			this.idgeoPtAds = idgeoPtAds;
		}
		public String getCodiceADS() {
			return codiceADS;
		}
		public void setCodiceADS(String codiceADS) {
			this.codiceADS = codiceADS;
		}
		public Integer getTipoForestale() {
			return tipoForestale;
		}
		public void setTipoForestale(Integer tipoForestale) {
			this.tipoForestale = tipoForestale;
		}
		public Integer getAssettoEvolutivoColturale() {
			return assettoEvolutivoColturale;
		}
		public void setAssettoEvolutivoColturale(Integer assettoEvolutivoColturale) {
			this.assettoEvolutivoColturale = assettoEvolutivoColturale;
		}
		public Double getDensitaCamp() {
			return densitaCamp;
		}
		public void setDensitaCamp(Double densitaCamp) {
			this.densitaCamp = densitaCamp;
		}
		public Integer getRaggioArea() {
			return raggioArea;
		}
		public void setRaggioArea(Integer raggioArea) {
			this.raggioArea = raggioArea;
		}
		public Integer getTipoStrutturale() {
			return tipoStrutturale;
		}
		public void setTipoStrutturale(Integer tipoStrutturale) {
			this.tipoStrutturale = tipoStrutturale;
		}
		public String getStadioDiSviluppo() {
			return stadioDiSviluppo;
		}
		public void setStadioDiSviluppo(String stadioDiSviluppo) {
			this.stadioDiSviluppo = stadioDiSviluppo;
		}
		public Integer getnCepaie() {
			return nCepaie;
		}
		public void setnCepaie(Integer nCepaie) {
			this.nCepaie = nCepaie;
		}
		public Integer getRinnovazione() {
			return rinnovazione;
		}
		public void setRinnovazione(Integer rinnovazione) {
			this.rinnovazione = rinnovazione;
		}
		public String getSpeciePrevalenteRinnovazione() {
			return speciePrevalenteRinnovazione;
		}
		public void setSpeciePrevalenteRinnovazione(String speciePrevalenteRinnovazione) {
			this.speciePrevalenteRinnovazione = speciePrevalenteRinnovazione;
		}
		public Integer getCoperturaChiome() {
			return coperturaChiome;
		}
		public void setCoperturaChiome(Integer coperturaChiome) {
			this.coperturaChiome = coperturaChiome;
		}
		public Integer getIdClasseDiFertilita() {
			return idClasseDiFertilita;
		}
		public void setIdClasseDiFertilita(Integer idClasseDiFertilita) {
			this.idClasseDiFertilita = idClasseDiFertilita;
		}
		public Integer getCoperturaErbacea() {
			return coperturaErbacea;
		}
		public void setCoperturaErbacea(Integer coperturaErbacea) {
			this.coperturaErbacea = coperturaErbacea;
		}
		public Integer getCoperturaCespugli() {
			return coperturaCespugli;
		}
		public void setCoperturaCespugli(Integer coperturaCespugli) {
			this.coperturaCespugli = coperturaCespugli;
		}
		public Integer getLettiera() {
			return lettiera;
		}
		public void setLettiera(Integer lettiera) {
			this.lettiera = lettiera;
		}
		
		
		
		
		public Integer getCategoriaForestale() {
			return categoriaForestale;
		}
		public void setCategoriaForestale(Integer categoriaForestale) {
			this.categoriaForestale = categoriaForestale;
		}
		public String getTipologia() {
			return tipologia;
		}
		public void setTipologia(String tipologia) {
			this.tipologia = tipologia;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("AdsDAtiStazionaliOne [idgeoPtAds=");
			builder.append(idgeoPtAds);
			builder.append(", codiceADS=");
			builder.append(codiceADS);
			builder.append(", tipoForestale=");
			builder.append(tipoForestale);
			builder.append(", assettoEvolutivoColturale=");
			builder.append(assettoEvolutivoColturale);
			builder.append(", densitaCamp=");
			builder.append(densitaCamp);
			builder.append(", raggioArea=");
			builder.append(raggioArea);
			builder.append(", tipoStrutturale=");
			builder.append(tipoStrutturale);
			builder.append(", stadioDiSviluppo=");
			builder.append(stadioDiSviluppo);
			builder.append(", nCepaie=");
			builder.append(nCepaie);
			builder.append(", rinnovazione=");
			builder.append(rinnovazione);
			builder.append(", speciePrevalenteRinnovazione=");
			builder.append(speciePrevalenteRinnovazione);
			builder.append(", coperturaChiome=");
			builder.append(coperturaChiome);
			builder.append(", idClasseDiFertilita=");
			builder.append(idClasseDiFertilita);
			builder.append(", coperturaErbacea=");
			builder.append(coperturaErbacea);
			builder.append(", coperturaCespugli=");
			builder.append(coperturaCespugli);
			builder.append(", lettiera=");
			builder.append(lettiera);
			builder.append("]");
			return builder.toString();
		}
		
}
