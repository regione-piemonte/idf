/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'IdfTSoggetto' entity
 */
public class IdfTSoggetto implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_soggetto numeric 
    @JsonProperty(value="id_soggetto")
	private Integer idSoggetto;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : fk_comune int4 
 	@JsonProperty(value="fk_comune")
    private Integer fkComune;

    // DB : nome varchar 
 	@JsonProperty(value="nome")
    private String nome;

    // DB : cognome varchar 
 	@JsonProperty(value="cognome")
    private String cognome;

    // DB : codice_fiscale varchar 
 	@JsonProperty(value="codice_fiscale")
    private String codiceFiscale;

    // DB : partita_iva varchar 
 	@JsonProperty(value="partita_iva")
    private String partitaIva;

    // DB : denominazione varchar 
 	@JsonProperty(value="denominazione")
    private String denominazione;

    // DB : indirizzo varchar 
 	@JsonProperty(value="indirizzo")
    private String indirizzo;

    // DB : nr_telefonico varchar 
 	@JsonProperty(value="nr_telefonico")
    private String nrTelefonico;

    // DB : e_mail varchar 
 	@JsonProperty(value="e_mail")
    private String eMail;

    // DB : pec varchar 
 	@JsonProperty(value="pec")
    private String pec;

    // DB : n_iscrizione_ordine varchar 
 	@JsonProperty(value="n_iscrizione_ordine")
    private String nIscrizioneOrdine;

    // DB : istat_prov_iscrizione_ordine varchar 
 	@JsonProperty(value="istat_prov_iscrizione_ordine")
    private String istatProvIscrizioneOrdine;

    // DB : istat_prov_competenza_terr varchar 
 	@JsonProperty(value="istat_prov_competenza_terr")
    private String istatProvCompetenzaTerr;

    // DB : flg_settore_regionale numeric 
 	@JsonProperty(value="flg_settore_regionale")
    private Integer flgSettoreRegionale;

    // DB : data_inserimento date 
 	@JsonProperty(value="data_inserimento")
    private Date dataInserimento;

    // DB : data_aggiornamento date 
 	@JsonProperty(value="data_aggiornamento")
    private Date dataAggiornamento;

    // DB : fk_config_utente int4 
 	@JsonProperty(value="fk_config_utente")
    private Integer fkConfigUtente;

    // DB : civico varchar 
 	@JsonProperty(value="civico")
    private String civico;

    // DB : cap varchar 
 	@JsonProperty(value="cap")
    private String cap;

    // DB : fk_categoria_professionale numeric 
 	@JsonProperty(value="fk_categoria_professionale")
    private Integer fkCategoriaProfessionale;

    // DB : istat_comune_competenza_terr varchar 
 	@JsonProperty(value="istat_comune_competenza_terr")
    private String istatComuneCompetenzaTerr;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdSoggetto( Integer idSoggetto ) {
        this.idSoggetto = idSoggetto ;
    }

    public Integer getIdSoggetto() {
        return this.idSoggetto;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFkComune( Integer fkComune ) {
        this.fkComune = fkComune;
    }
    public Integer getFkComune() {
        return this.fkComune;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    public void setCognome( String cognome ) {
        this.cognome = cognome;
    }
    public String getCognome() {
        return this.cognome;
    }

    public void setCodiceFiscale( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    public void setPartitaIva( String partitaIva ) {
        this.partitaIva = partitaIva;
    }
    public String getPartitaIva() {
        return this.partitaIva;
    }

    public void setDenominazione( String denominazione ) {
        this.denominazione = denominazione;
    }
    public String getDenominazione() {
        return this.denominazione;
    }

    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }

    public void setNrTelefonico( String nrTelefonico ) {
        this.nrTelefonico = nrTelefonico;
    }
    public String getNrTelefonico() {
        return this.nrTelefonico;
    }

    public void setEMail( String eMail ) {
        this.eMail = eMail;
    }
    public String getEMail() {
        return this.eMail;
    }

    public void setPec( String pec ) {
        this.pec = pec;
    }
    public String getPec() {
        return this.pec;
    }

    public void setNIscrizioneOrdine( String nIscrizioneOrdine ) {
        this.nIscrizioneOrdine = nIscrizioneOrdine;
    }
    public String getNIscrizioneOrdine() {
        return this.nIscrizioneOrdine;
    }

    public void setIstatProvIscrizioneOrdine( String istatProvIscrizioneOrdine ) {
        this.istatProvIscrizioneOrdine = istatProvIscrizioneOrdine;
    }
    public String getIstatProvIscrizioneOrdine() {
        return this.istatProvIscrizioneOrdine;
    }

    public void setIstatProvCompetenzaTerr( String istatProvCompetenzaTerr ) {
        this.istatProvCompetenzaTerr = istatProvCompetenzaTerr;
    }
    public String getIstatProvCompetenzaTerr() {
        return this.istatProvCompetenzaTerr;
    }

    public void setFlgSettoreRegionale( Integer flgSettoreRegionale ) {
        this.flgSettoreRegionale = flgSettoreRegionale;
    }
    public Integer getFlgSettoreRegionale() {
        return this.flgSettoreRegionale;
    }

    public void setDataInserimento( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }
    public Date getDataInserimento() {
        return this.dataInserimento;
    }

    public void setDataAggiornamento( Date dataAggiornamento ) {
        this.dataAggiornamento = dataAggiornamento;
    }
    public Date getDataAggiornamento() {
        return this.dataAggiornamento;
    }

    public void setFkConfigUtente( Integer fkConfigUtente ) {
        this.fkConfigUtente = fkConfigUtente;
    }
    public Integer getFkConfigUtente() {
        return this.fkConfigUtente;
    }

    public void setCivico( String civico ) {
        this.civico = civico;
    }
    public String getCivico() {
        return this.civico;
    }

    public void setCap( String cap ) {
        this.cap = cap;
    }
    public String getCap() {
        return this.cap;
    }

    public void setFkCategoriaProfessionale( Integer fkCategoriaProfessionale ) {
        this.fkCategoriaProfessionale = fkCategoriaProfessionale;
    }
    public Integer getFkCategoriaProfessionale() {
        return this.fkCategoriaProfessionale;
    }

    public void setIstatComuneCompetenzaTerr( String istatComuneCompetenzaTerr ) {
        this.istatComuneCompetenzaTerr = istatComuneCompetenzaTerr;
    }
    public String getIstatComuneCompetenzaTerr() {
        return this.istatComuneCompetenzaTerr;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idSoggetto);
        sb.append("|");
        sb.append(fkComune);
        sb.append("|");
        sb.append(nome);
        sb.append("|");
        sb.append(cognome);
        sb.append("|");
        sb.append(codiceFiscale);
        sb.append("|");
        sb.append(partitaIva);
        sb.append("|");
        sb.append(denominazione);
        sb.append("|");
        sb.append(indirizzo);
        sb.append("|");
        sb.append(nrTelefonico);
        sb.append("|");
        sb.append(eMail);
        sb.append("|");
        sb.append(pec);
        sb.append("|");
        sb.append(nIscrizioneOrdine);
        sb.append("|");
        sb.append(istatProvIscrizioneOrdine);
        sb.append("|");
        sb.append(istatProvCompetenzaTerr);
        sb.append("|");
        sb.append(flgSettoreRegionale);
        sb.append("|");
        sb.append(dataInserimento);
        sb.append("|");
        sb.append(dataAggiornamento);
        sb.append("|");
        sb.append(fkConfigUtente);
        sb.append("|");
        sb.append(civico);
        sb.append("|");
        sb.append(cap);
        sb.append("|");
        sb.append(fkCategoriaProfessionale);
        sb.append("|");
        sb.append(istatComuneCompetenzaTerr);
        return sb.toString(); 
    } 


}
