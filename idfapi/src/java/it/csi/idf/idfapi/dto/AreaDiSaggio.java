/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.codehaus.jackson.annotate.JsonProperty;

public class AreaDiSaggio {

	private Integer lastCompletedStep;
	private Long idgeoPtAds;
	private String codiceADS;
	private String comune;
	private Integer tipoAds;
	private String descrTipoAds;
	private String categoriaForestale;
	private String ambitoDiRilevamento;
	private String dettaglioAmbito;
	private Integer idDettaglioAmbito;
	private String ambitoSpecifico;
	private String tipologia;
	private String dataRilevamento;
	private String quota;
	private String espozione;
	private String inclinazione;
	private String densitaCamp;
	private String raggioArea;
	private String particellaForestale;
	private String proprieta;
	private String tipoForestale;
	private String assettoEvolutivoColturale;
	private String stadioDiSviluppo;
	private String nCepaie;
	private String rinnovazione;
	private String speciePrevalenteRinnovazione;
	private String speciePrevalenteRinnovDescr;
	private String coperturaChiome;
	private String coperturaCespugli;
	private String coperturaErbacea;
	private String lettiera;
	private String destinazione;
	private String intervento;
	private String esbosco;
	private String esboscoDescr;
	private String defp;
	private String desp;
	private String mdp;
	private String intesitaDanni;
	private String nPianteMorte;
	private String defogliazione;
	private String ingiallimento;
	private String pascolamento;
	private Byte combustibileSecondario; // non compilato?
	private Byte combustibilePrincipale;
	private String note;
	private Integer tavola;
	private BigDecimal utmNord;
	private BigDecimal utmEst;
	private Integer tipoStrutturale;
	private String descrTipoStrutturale;
	private String soggettoPg;
	private String soggettoPf;
	private String priorita;
	private String classeDiFertilita;
	private Integer idClasseDiFertilita;
	private String dannoPrevalente;
	private StatoAds statoScheda;
	
	private Integer fkAssetto;
	private Integer fkDestinazione;
	private Integer fkTipoIntervento;
	private Integer fkPriorita;
	private Integer fkDanno;
	private Long fattoreNumerazione;
	private String relascopicaDescrTipoStructurale;
	
	
	public Integer getLastCompletedStep() {
		return lastCompletedStep;
	}

	public void setLastCompletedStep(Integer lastCompletedStep) {
		this.lastCompletedStep = lastCompletedStep;
	}

	public String getSoggettoPg() {
		return soggettoPg;
	}

	public void setSoggettoPg(String soggettoPg) {
		this.soggettoPg = soggettoPg;
	}

	public String getSoggettoPf() {
		return soggettoPf;
	}

	public void setSoggettoPf(String soggettoPf) {
		this.soggettoPf = soggettoPf;
	}

	public String getPriorita() {
		return priorita;
	}

	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}

	public Integer getTipoStrutturale() {
		return tipoStrutturale;
	}

	public void setTipoStrutturale(Integer tipoStrutturale) {
		this.tipoStrutturale = tipoStrutturale;
	}
	
	

	public String getDescrTipoStrutturale() {
		return descrTipoStrutturale;
	}

	public void setDescrTipoStrutturale(String descr_tipo_strutturale) {
		this.descrTipoStrutturale = descr_tipo_strutturale;
	}

	public BigDecimal getUtmNord() {
		//richiesta in visualizzazione solo parte intera
		return utmNord==null?null:utmNord.setScale(0, RoundingMode.HALF_UP);
	}

	public void setUtmNord(BigDecimal utmNord) {
		this.utmNord = utmNord;
	}

	public BigDecimal getUtmEst() {
		//richiesta in visualizzazione solo parte intera
		return utmEst==null?null:utmEst.setScale(0, RoundingMode.HALF_UP);
	}

	public void setUtmEst(BigDecimal utmEst) {
		this.utmEst = utmEst;
	}

	public Integer getTavola() {
		return tavola;
	}

	public void setTavola(Integer tavola) {
		this.tavola = tavola;
	}

	public Byte getCombustibilePrincipale() {
		return combustibilePrincipale;
	}

	public void setCombustibilePrincipale(Byte combustibilePrincipale) {
		this.combustibilePrincipale = combustibilePrincipale;
	}

	public Byte getCombustibileSecondario() {
		return combustibileSecondario;
	}

	public void setCombustibileSecondario(Byte combustibileSecondario) {
		this.combustibileSecondario = combustibileSecondario;
	}

	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}

	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}

	@JsonProperty("codiceADS")
	public String getCodiceADS() {
		return codiceADS;
	}

	public void setCodiceADS(String codiceADS) {
		this.codiceADS = codiceADS;
	}

	@JsonProperty("comune")
	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}
		

	public Integer getTipoAds() {
		return tipoAds;
	}

	public void setTipoAds(Integer tipoAds) {
		this.tipoAds = tipoAds;
	}

	@JsonProperty("categoriaForestale")
	public String getCategoriaForestale() {
		return categoriaForestale;
	}

	public void setCategoriaForestale(String categoriaForestale) {
		this.categoriaForestale = categoriaForestale;
	}

	@JsonProperty("ambitoDiRilevamento")
	public String getAmbitoDiRilevamento() {
		return ambitoDiRilevamento;
	}

	public void setAmbitoDiRilevamento(String ambitoDiRilevamento) {
		this.ambitoDiRilevamento = ambitoDiRilevamento;
	}

	@JsonProperty("dettaglioAmbito")
	public String getDettaglioAmbito() {
		return dettaglioAmbito;
	}

	public void setDettaglioAmbito(String dettaglioAmbito) {
		this.dettaglioAmbito = dettaglioAmbito;
	}
	
	public Integer getIdDettaglioAmbito() {
		return idDettaglioAmbito;
	}

	public void setIdDettaglioAmbito(Integer idDettaglioAmbito) {
		this.idDettaglioAmbito = idDettaglioAmbito;
	}

	public String getAmbitoSpecifico() {
		return ambitoSpecifico;
	}

	public void setAmbitoSpecifico(String ambitoSpecifico) {
		this.ambitoSpecifico = ambitoSpecifico;
	}

	@JsonProperty("tipologia")
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	@JsonProperty("dataRilevamento")
	public String getDataRilevamento() {
		return dataRilevamento;
	}

	public void setDataRilevamento(String dataRilevamento) {
		this.dataRilevamento = dataRilevamento;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public String getEspozione() {
		return espozione;
	}

	public void setEspozione(String espozione) {
		this.espozione = espozione;
	}

	public String getInclinazione() {
		return inclinazione;
	}

	public void setInclinazione(String inclinazione) {
		this.inclinazione = inclinazione;
	}

	public String getDensitaCamp() {
		return densitaCamp;
	}

	public void setDensitaCamp(String densitaCamp) {
		this.densitaCamp = densitaCamp;
	}

	public String getRaggioArea() {
		return raggioArea;
	}

	public void setRaggioArea(String raggioArea) {
		this.raggioArea = raggioArea;
	}

	public String getParticellaForestale() {
		return particellaForestale;
	}

	public void setParticellaForestale(String particellaForestale) {
		this.particellaForestale = particellaForestale;
	}

	public String getProprieta() {
		return proprieta;
	}

	public void setProprieta(String proprieta) {
		this.proprieta = proprieta;
	}

	public String getTipoForestale() {
		return tipoForestale;
	}

	public void setTipoForestale(String tipoForestale) {
		this.tipoForestale = tipoForestale;
	}

	public String getAssettoEvolutivoColturale() {
		return assettoEvolutivoColturale;
	}

	public void setAssettoEvolutivoColturale(String assettoEvolutivoColturale) {
		this.assettoEvolutivoColturale = assettoEvolutivoColturale;
	}

	public String getStadioDiSviluppo() {
		return stadioDiSviluppo;
	}

	public void setStadioDiSviluppo(String stadioDiSviluppo) {
		this.stadioDiSviluppo = stadioDiSviluppo;
	}

	public String getnCepaie() {
		return nCepaie;
	}

	public void setnCepaie(String nCepaie) {
		this.nCepaie = nCepaie;
	}

	public String getRinnovazione() {
		return rinnovazione;
	}

	public void setRinnovazione(String rinnovazione) {
		this.rinnovazione = rinnovazione;
	}

	public String getSpeciePrevalenteRinnovazione() {
		return speciePrevalenteRinnovazione;
	}

	public void setSpeciePrevalenteRinnovazione(String speciePrevalenteRinnovazione) {
		this.speciePrevalenteRinnovazione = speciePrevalenteRinnovazione;
	}

	public String getCoperturaChiome() {
		return coperturaChiome;
	}

	public void setCoperturaChiome(String coperturaChiome) {
		this.coperturaChiome = coperturaChiome;
	}

	public String getCoperturaCespugli() {
		return coperturaCespugli;
	}

	public void setCoperturaCespugli(String coperturaCespugli) {
		this.coperturaCespugli = coperturaCespugli;
	}

	public String getCoperturaErbacea() {
		return coperturaErbacea;
	}

	public void setCoperturaErbacea(String coperturaErbacea) {
		this.coperturaErbacea = coperturaErbacea;
	}

	public String getLettiera() {
		return lettiera;
	}

	public void setLettiera(String lettiera) {
		this.lettiera = lettiera;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public String getIntervento() {
		return intervento;
	}

	public void setIntervento(String intervento) {
		this.intervento = intervento;
	}

	public String getEsbosco() {
		return esbosco;
	}

	public void setEsbosco(String esbosco) {
		this.esbosco = esbosco;
	}

	public String getDefp() {
		return defp;
	}

	public void setDefp(String defp) {
		this.defp = defp;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getIntesitaDanni() {
		return intesitaDanni;
	}

	public void setIntesitaDanni(String intesitaDanni) {
		this.intesitaDanni = intesitaDanni;
	}

	public String getnPianteMorte() {
		return nPianteMorte;
	}

	public void setnPianteMorte(String nPianteMorte) {
		this.nPianteMorte = nPianteMorte;
	}

	public String getDefogliazione() {
		return defogliazione;
	}

	public void setDefogliazione(String defogliazione) {
		this.defogliazione = defogliazione;
	}

	public String getIngiallimento() {
		return ingiallimento;
	}

	public void setIngiallimento(String ingiallimento) {
		this.ingiallimento = ingiallimento;
	}

	public String getPascolamento() {
		return pascolamento;
	}

	public void setPascolamento(String pascolamento) {
		this.pascolamento = pascolamento;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDescrTipoAds() {
		return descrTipoAds;
	}

	public void setDescrTipoAds(String descrTipoAds) {
		this.descrTipoAds = descrTipoAds;
	}

	public String getClasseDiFertilita() {
		return classeDiFertilita;
	}

	public void setClasseDiFertilita(String classeDiFertilita) {
		this.classeDiFertilita = classeDiFertilita;
	}
	
	public Integer getIdClasseDiFertilita() {
		return idClasseDiFertilita;
	}

	public void setIdClasseDiFertilita(Integer idClasseDiFertilita) {
		this.idClasseDiFertilita = idClasseDiFertilita;
	}

	public String getDannoPrevalente() {
		return dannoPrevalente;
	}

	public void setDannoPrevalente(String dannoPrevalente) {
		this.dannoPrevalente = dannoPrevalente;
	}
	
	public StatoAds getStatoScheda() {
		return statoScheda;
	}
	
	public void setStatoScheda(StatoAds statoScheda) {
		this.statoScheda = statoScheda;
	}	
	

	public Long getFattoreNumerazione() {
		return fattoreNumerazione;
	}

	public void setFattoreNumerazione(Long fattoreNumerazione) {
		this.fattoreNumerazione = fattoreNumerazione;
	}

	public String getRelascopicaDescrTipoStructurale() {
		return relascopicaDescrTipoStructurale;
	}
	
	

	public String getSpeciePrevalenteRinnovDescr() {
		return speciePrevalenteRinnovDescr;
	}

	public void setSpeciePrevalenteRinnovDescr(String speciePrevalenteRinnovDescr) {
		this.speciePrevalenteRinnovDescr = speciePrevalenteRinnovDescr;
	}

	public String getEsboscoDescr() {
		return esboscoDescr;
	}

	public void setEsboscoDescr(String esboscoDescr) {
		this.esboscoDescr = esboscoDescr;
	}

	public void setRelascopicaDescrTipoStructurale(String relascopicaDescrTipoStructurale) {
		this.relascopicaDescrTipoStructurale = relascopicaDescrTipoStructurale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaDiSaggio [idgeoPtAds=");
		builder.append(idgeoPtAds);
		builder.append(", codiceADS=");
		builder.append(codiceADS);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", descrTipoAds=");
		builder.append(descrTipoAds);
		builder.append(", categoriaForestale=");
		builder.append(categoriaForestale);
		builder.append(", ambitoDiRilevamento=");
		builder.append(ambitoDiRilevamento);
		builder.append(", dettaglioAmbito=");
		builder.append(dettaglioAmbito);
		builder.append(", tipologia=");
		builder.append(tipologia);
		builder.append(", dataRilevamento=");
		builder.append(dataRilevamento);
		builder.append(", quota=");
		builder.append(quota);
		builder.append(", espozione=");
		builder.append(espozione);
		builder.append(", inclinazione=");
		builder.append(inclinazione);
		builder.append(", densitaCamp=");
		builder.append(densitaCamp);
		builder.append(", raggioArea=");
		builder.append(raggioArea);
		builder.append(", particellaForestale=");
		builder.append(particellaForestale);
		builder.append(", proprieta=");
		builder.append(proprieta);
		builder.append(", tipoForestale=");
		builder.append(tipoForestale);
		builder.append(", assettoEvolutivoColturale=");
		builder.append(assettoEvolutivoColturale);
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
		builder.append(", coperturaCespugli=");
		builder.append(coperturaCespugli);
		builder.append(", coperturaErbacea=");
		builder.append(coperturaErbacea);
		builder.append(", lettiera=");
		builder.append(lettiera);
		builder.append(", destinazione=");
		builder.append(destinazione);
		builder.append(", intervento=");
		builder.append(intervento);
		builder.append(", esbosco=");
		builder.append(esbosco);
		builder.append(", defp=");
		builder.append(defp);
		builder.append(", desp=");
		builder.append(desp);
		builder.append(", mdp=");
		builder.append(mdp);
		builder.append(", intesitaDanni=");
		builder.append(intesitaDanni);
		builder.append(", nPianteMorte=");
		builder.append(nPianteMorte);
		builder.append(", defogliazione=");
		builder.append(defogliazione);
		builder.append(", ingiallimento=");
		builder.append(ingiallimento);
		builder.append(", pascolamento=");
		builder.append(pascolamento);
		builder.append(", combustibileSecondario=");
		builder.append(combustibileSecondario);
		builder.append(", combustibilePrincipale=");
		builder.append(combustibilePrincipale);
		builder.append(", tavola=");
		builder.append(tavola);
		builder.append(", utmNord=");
		builder.append(utmNord);
		builder.append(", utmEst=");
		builder.append(utmEst);
		builder.append(", tipoStrutturale=");
		builder.append(tipoStrutturale);
		builder.append(", soggettoPg=");
		builder.append(soggettoPg);
		builder.append(", soggettoPf=");
		builder.append(soggettoPf);
		builder.append(", priorita=");
		builder.append(priorita);
		builder.append(", classeDiFertilita=");
		builder.append(classeDiFertilita);
		builder.append(", dannoPrevalente=");
		builder.append(dannoPrevalente);
		builder.append("]");
		return builder.toString();
	}

}
