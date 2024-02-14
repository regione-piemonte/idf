/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'IdfCnfDelega' entity
 */
public class IdfCnfDelega implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : cf_delegante varchar 
    @JsonProperty(value="cf_delegante")
	private String cfDelegante;
    // DB : id_config_utente int4 
    @JsonProperty(value="id_config_utente")
	private Integer idConfigUtente;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : data_inizio date 
 	@JsonProperty(value="data_inizio")
    private Date dataInizio;

    // DB : data_fine date 
 	@JsonProperty(value="data_fine")
    private Date dataFine;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCfDelegante( String cfDelegante ) {
        this.cfDelegante = cfDelegante ;
    }

    public String getCfDelegante() {
        return this.cfDelegante;
    }

    public void setIdConfigUtente( Integer idConfigUtente ) {
        this.idConfigUtente = idConfigUtente ;
    }

    public Integer getIdConfigUtente() {
        return this.idConfigUtente;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDataInizio( Date dataInizio ) {
        this.dataInizio = dataInizio;
    }
    public Date getDataInizio() {
        return this.dataInizio;
    }

    public void setDataFine( Date dataFine ) {
        this.dataFine = dataFine;
    }
    public Date getDataFine() {
        return this.dataFine;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(cfDelegante);
        sb.append("|");
        sb.append(idConfigUtente);
        sb.append("|");
        sb.append(dataInizio);
        sb.append("|");
        sb.append(dataFine);
        return sb.toString(); 
    } 


}
