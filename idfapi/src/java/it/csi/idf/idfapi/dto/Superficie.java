/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Superficie {
	
	private Long idgeoPtAds;
	private Double densitaCampionamento;
	private Integer raggioMt;
	private String codStadioSviluppo;
	private Integer percCoperturaChiome;
	private String attitudineProduttiva;
	private String codEsbosco;
	private Integer distEsboscoFuoriPista;
	private Integer distEsboscoSuPista;
	private Integer minDistanzaPlanimetrica;
	private Integer nrCeppaie;
	private Integer nrPianteMorte;
	private Integer rinnovazione;
	private String rinSpeciePrevalente;
	private Integer danniPerc;
	private Byte flgPascolamento;
	private Integer percDefogliazione;
	private Integer percIngiallimento;
	private Integer fkTipoStrutturalePrinc;
	private Integer fkTipoStrutturaleSecond;
	private Byte combustP;
	private String note;
	private Integer fkClasseFertilita;

	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}

	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}

	public Double getDensitaCampionamento() {
		return densitaCampionamento;
	}

	public void setDensitaCampionamento(Double densitaCampionamento) {
		this.densitaCampionamento = densitaCampionamento;
	}

	public Integer getRaggioMt() {
		return raggioMt;
	}

	public void setRaggioMt(Integer raggioMt) {
		this.raggioMt = raggioMt;
	}

	public String getCodStadioSviluppo() {
		return codStadioSviluppo;
	}

	public void setCodStadioSviluppo(String codStadioSviluppo) {
		this.codStadioSviluppo = codStadioSviluppo;
	}

	public Integer getPercCoperturaChiome() {
		return percCoperturaChiome;
	}

	public void setPercCoperturaChiome(Integer percCoperturaChiome) {
		this.percCoperturaChiome = percCoperturaChiome;
	}

	public String getAttitudineProduttiva() {
		return attitudineProduttiva;
	}

	public void setAttitudineProduttiva(String attitudineProduttiva) {
		this.attitudineProduttiva = attitudineProduttiva;
	}

	public String getCodEsbosco() {
		return codEsbosco;
	}

	public void setCodEsbosco(String codEsbosco) {
		this.codEsbosco = codEsbosco;
	}

	public Integer getDistEsboscoFuoriPista() {
		return distEsboscoFuoriPista;
	}

	public void setDistEsboscoFuoriPista(Integer distEsboscoFuoriPista) {
		this.distEsboscoFuoriPista = distEsboscoFuoriPista;
	}

	public Integer getDistEsboscoSuPista() {
		return distEsboscoSuPista;
	}

	public void setDistEsboscoSuPista(Integer distEsboscoSuPista) {
		this.distEsboscoSuPista = distEsboscoSuPista;
	}

	public Integer getMinDistanzaPlanimetrica() {
		return minDistanzaPlanimetrica;
	}

	public void setMinDistanzaPlanimetrica(Integer minDistanzaPlanimetrica) {
		this.minDistanzaPlanimetrica = minDistanzaPlanimetrica;
	}

	public Integer getNrCeppaie() {
		return nrCeppaie;
	}

	public void setNrCeppaie(Integer nrCeppaie) {
		this.nrCeppaie = nrCeppaie;
	}

	public Integer getNrPianteMorte() {
		return nrPianteMorte;
	}

	public void setNrPianteMorte(Integer nrPianteMorte) {
		this.nrPianteMorte = nrPianteMorte;
	}

	public Integer getRinnovazione() {
		return rinnovazione;
	}

	public void setRinnovazione(Integer rinnovazione) {
		this.rinnovazione = rinnovazione;
	}

	public String getRinSpeciePrevalente() {
		return rinSpeciePrevalente;
	}

	public void setRinSpeciePrevalente(String rinSpeciePrevalente) {
		this.rinSpeciePrevalente = rinSpeciePrevalente;
	}

	public Integer getDanniPerc() {
		return danniPerc;
	}

	public void setDanniPerc(Integer danniPerc) {
		this.danniPerc = danniPerc;
	}

	public Byte getFlgPascolamento() {
		return flgPascolamento;
	}

	public void setFlgPascolamento(Byte flgPascolamento) {
		this.flgPascolamento = flgPascolamento;
	}

	public Integer getPercDefogliazione() {
		return percDefogliazione;
	}

	public void setPercDefogliazione(Integer percDefogliazione) {
		this.percDefogliazione = percDefogliazione;
	}

	public Integer getPercIngiallimento() {
		return percIngiallimento;
	}

	public void setPercIngiallimento(Integer percIngiallimento) {
		this.percIngiallimento = percIngiallimento;
	}

	public Integer getFkTipoStrutturalePrinc() {
		return fkTipoStrutturalePrinc;
	}

	public void setFkTipoStrutturalePrinc(Integer fkTipoStrutturalePrinc) {
		this.fkTipoStrutturalePrinc = fkTipoStrutturalePrinc;
	}

	public Integer getFkTipoStrutturaleSecond() {
		return fkTipoStrutturaleSecond;
	}

	public void setFkTipoStrutturaleSecond(Integer fkTipoStrutturaleSecond) {
		this.fkTipoStrutturaleSecond = fkTipoStrutturaleSecond;
	}

	public Byte getCombustP() {
		return combustP;
	}

	public void setCombustP(Byte combustP) {
		this.combustP = combustP;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getFkClasseFertilita() {
		return fkClasseFertilita;
	}

	public void setFkClasseFertilita(Integer fk_classe_fertilita) {
		this.fkClasseFertilita = fk_classe_fertilita;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Superficie [idgeoPtAds=");
		builder.append(idgeoPtAds);
		builder.append(", densitaCampionamento=");
		builder.append(densitaCampionamento);
		builder.append(", raggioMt=");
		builder.append(raggioMt);
		builder.append(", codStadioSviluppo=");
		builder.append(codStadioSviluppo);
		builder.append(", percCoperturaChiome=");
		builder.append(percCoperturaChiome);
		builder.append(", attitudineProduttiva=");
		builder.append(attitudineProduttiva);
		builder.append(", codEsbosco=");
		builder.append(codEsbosco);
		builder.append(", distEsboscoFuoriPista=");
		builder.append(distEsboscoFuoriPista);
		builder.append(", distEsboscoSuPista=");
		builder.append(distEsboscoSuPista);
		builder.append(", minDistanzaPlanimetrica=");
		builder.append(minDistanzaPlanimetrica);
		builder.append(", nrCeppaie=");
		builder.append(nrCeppaie);
		builder.append(", nrPianteMorte=");
		builder.append(nrPianteMorte);
		builder.append(", rinnovazione=");
		builder.append(rinnovazione);
		builder.append(", rinSpeciePrevalente=");
		builder.append(rinSpeciePrevalente);
		builder.append(", danniPerc=");
		builder.append(danniPerc);
		builder.append(", flgPascolamento=");
		builder.append(flgPascolamento);
		builder.append(", percDefogliazione=");
		builder.append(percDefogliazione);
		builder.append(", percIngiallimento=");
		builder.append(percIngiallimento);
		builder.append(", fkTipoStrutturalePrinc=");
		builder.append(fkTipoStrutturalePrinc);
		builder.append(", fkTipoStrutturaleSecond=");
		builder.append(fkTipoStrutturaleSecond);
		builder.append(", combustP=");
		builder.append(combustP);
		builder.append(", note=");
		builder.append(note);
		builder.append(", fk_classe_fertilita=");
		builder.append(fkClasseFertilita);
		builder.append("]");
		return builder.toString();
	}

}
