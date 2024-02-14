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
 * Java bean for 'IdfRIntervincidroGoverno' entity
 */
public class IdfRIntervincidroGoverno implements Serializable {
	private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_intervento numeric 
    @JsonProperty(value="id_intervento")
	private BigDecimal idIntervento;
    // DB : id_governo int4 
    @JsonProperty(value="id_governo")
	private Integer idGoverno;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : data_inserimento date 
 	@JsonProperty(value="data_inserimento")
    private Date dataInserimento;

    // DB : fk_config_utente int4 
 	@JsonProperty(value="fk_config_utente")
    private Integer fkConfigUtente;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdIntervento( BigDecimal idIntervento ) {
        this.idIntervento = idIntervento ;
    }

    public BigDecimal getIdIntervento() {
        return this.idIntervento;
    }

    public void setIdGoverno( Integer idGoverno ) {
        this.idGoverno = idGoverno ;
    }

    public Integer getIdGoverno() {
        return this.idGoverno;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDataInserimento( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }
    public Date getDataInserimento() {
        return this.dataInserimento;
    }

    public void setFkConfigUtente( Integer fkConfigUtente ) {
        this.fkConfigUtente = fkConfigUtente;
    }
    public Integer getFkConfigUtente() {
        return this.fkConfigUtente;
    }
    
    public IdfRIntervincidroGoverno() {
		super();
	}

	public IdfRIntervincidroGoverno(BigDecimal idIntervento, Integer idGoverno, Date dataInserimento, Integer fkConfigUtente) {
		super();
		this.idIntervento = idIntervento;
		this.idGoverno = idGoverno;
		this.dataInserimento = dataInserimento;
		this.fkConfigUtente = fkConfigUtente;
	}

	public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idIntervento);
        sb.append("|");
        sb.append(idGoverno);
        sb.append("|");
        sb.append(dataInserimento);
        sb.append("|");
        sb.append(fkConfigUtente);
        return sb.toString(); 
    }
}
