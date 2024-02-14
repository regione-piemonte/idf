/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Java bean for 'IdfTIstanzaForest' entity
 */
public class IdfTIstanzaForest implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_istanza_intervento numeric 
    @JsonProperty(value="id_istanza_intervento")
	private BigDecimal idIstanzaIntervento;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : fk_sogg_settore_regionale numeric 
 	@JsonProperty(value="fk_sogg_settore_regionale")
    private BigDecimal fkSoggSettoreRegionale;

    // DB : fk_stato_istanza numeric 
 	@JsonProperty(value="fk_stato_istanza")
    private BigDecimal fkStatoIstanza;

    // DB : nr_istanza_forestale numeric 
 	@JsonProperty(value="nr_istanza_forestale")
    private BigDecimal nrIstanzaForestale;

    // DB : data_invio date 
 	@JsonProperty(value="data_invio")
    private Date dataInvio;

    // DB : data_verifica date 
 	@JsonProperty(value="data_verifica")
    private Date dataVerifica;

    // DB : data_protocollo date 
 	@JsonProperty(value="data_protocollo")
    private Date dataProtocollo;

    // DB : nr_protocollo varchar 
 	@JsonProperty(value="nr_protocollo")
    private String nrProtocollo;

    // DB : data_ult_agg date 
 	@JsonProperty(value="data_ult_agg")
    private Date dataUltAgg;

    // DB : data_inserimento date 
 	@JsonProperty(value="data_inserimento")
    private Date dataInserimento;

    // DB : data_aggiornamento date 
 	@JsonProperty(value="data_aggiornamento")
    private Date dataAggiornamento;

    // DB : fk_config_utente int4 
 	@JsonProperty(value="fk_config_utente")
    private Integer fkConfigUtente;

    // DB : fk_tipo_istanza numeric 
 	@JsonProperty(value="fk_tipo_istanza")
    private BigDecimal fkTipoIstanza;

    // DB : nr_determina_aut varchar 
 	@JsonProperty(value="nr_determina_aut")
    private String nrDeterminaAut;

    // DB : data_termine_aut date 
 	@JsonProperty(value="data_termine_aut")
    private Date dataTermineAut;

    // DB : motivazione_rifiuto varchar 
 	@JsonProperty(value="motivazione_rifiuto")
    private String motivazioneRifiuto;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdIstanzaIntervento( BigDecimal idIstanzaIntervento ) {
        this.idIstanzaIntervento = idIstanzaIntervento ;
    }

    public BigDecimal getIdIstanzaIntervento() {
        return this.idIstanzaIntervento;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFkSoggSettoreRegionale( BigDecimal fkSoggSettoreRegionale ) {
        this.fkSoggSettoreRegionale = fkSoggSettoreRegionale;
    }
    public BigDecimal getFkSoggSettoreRegionale() {
        return this.fkSoggSettoreRegionale;
    }

    public void setFkStatoIstanza( BigDecimal fkStatoIstanza ) {
        this.fkStatoIstanza = fkStatoIstanza;
    }
    public BigDecimal getFkStatoIstanza() {
        return this.fkStatoIstanza;
    }

    public void setNrIstanzaForestale( BigDecimal nrIstanzaForestale ) {
        this.nrIstanzaForestale = nrIstanzaForestale;
    }
    public BigDecimal getNrIstanzaForestale() {
        return this.nrIstanzaForestale;
    }

    public void setDataInvio( Date dataInvio ) {
        this.dataInvio = dataInvio;
    }
    public Date getDataInvio() {
        return this.dataInvio;
    }

    public void setDataVerifica( Date dataVerifica ) {
        this.dataVerifica = dataVerifica;
    }
    public Date getDataVerifica() {
        return this.dataVerifica;
    }

    public void setDataProtocollo( Date dataProtocollo ) {
        this.dataProtocollo = dataProtocollo;
    }
    public Date getDataProtocollo() {
        return this.dataProtocollo;
    }

    public void setNrProtocollo( String nrProtocollo ) {
        this.nrProtocollo = nrProtocollo;
    }
    public String getNrProtocollo() {
        return this.nrProtocollo;
    }

    public void setDataUltAgg( Date dataUltAgg ) {
        this.dataUltAgg = dataUltAgg;
    }
    public Date getDataUltAgg() {
        return this.dataUltAgg;
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

    public void setFkTipoIstanza( BigDecimal fkTipoIstanza ) {
        this.fkTipoIstanza = fkTipoIstanza;
    }
    public BigDecimal getFkTipoIstanza() {
        return this.fkTipoIstanza;
    }

    public void setNrDeterminaAut( String nrDeterminaAut ) {
        this.nrDeterminaAut = nrDeterminaAut;
    }
    public String getNrDeterminaAut() {
        return this.nrDeterminaAut;
    }

    public void setDataTermineAut( Date dataTermineAut ) {
        this.dataTermineAut = dataTermineAut;
    }
    public Date getDataTermineAut() {
        return this.dataTermineAut;
    }

    public void setMotivazioneRifiuto( String motivazioneRifiuto ) {
        this.motivazioneRifiuto = motivazioneRifiuto;
    }
    public String getMotivazioneRifiuto() {
        return this.motivazioneRifiuto;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idIstanzaIntervento);
        sb.append("|");
        sb.append(fkSoggSettoreRegionale);
        sb.append("|");
        sb.append(fkStatoIstanza);
        sb.append("|");
        sb.append(nrIstanzaForestale);
        sb.append("|");
        sb.append(dataInvio);
        sb.append("|");
        sb.append(dataVerifica);
        sb.append("|");
        sb.append(dataProtocollo);
        sb.append("|");
        sb.append(nrProtocollo);
        sb.append("|");
        sb.append(dataUltAgg);
        sb.append("|");
        sb.append(dataInserimento);
        sb.append("|");
        sb.append(dataAggiornamento);
        sb.append("|");
        sb.append(fkConfigUtente);
        sb.append("|");
        sb.append(fkTipoIstanza);
        sb.append("|");
        sb.append(nrDeterminaAut);
        sb.append("|");
        sb.append(dataTermineAut);
        sb.append("|");
        sb.append(motivazioneRifiuto);
        return sb.toString(); 
    } 


}
