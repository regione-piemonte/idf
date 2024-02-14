/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.math.BigDecimal;

/**
 * Java bean for 'IdfCnfTipoallTipoint' entity
 */
public class IdfCnfTipoallTipoint implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_tipo_allegato int4 
    @JsonProperty(value="id_tipo_allegato")
	private Integer idTipoAllegato;
    // DB : id_tipo_intervento numeric 
    @JsonProperty(value="id_tipo_intervento")
	private BigDecimal idTipoIntervento;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : flg_obbligatorio numeric 
 	@JsonProperty(value="flg_obbligatorio")
    private BigDecimal flgObbligatorio;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdTipoAllegato( Integer idTipoAllegato ) {
        this.idTipoAllegato = idTipoAllegato ;
    }

    public Integer getIdTipoAllegato() {
        return this.idTipoAllegato;
    }

    public void setIdTipoIntervento( BigDecimal idTipoIntervento ) {
        this.idTipoIntervento = idTipoIntervento ;
    }

    public BigDecimal getIdTipoIntervento() {
        return this.idTipoIntervento;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFlgObbligatorio( BigDecimal flgObbligatorio ) {
        this.flgObbligatorio = flgObbligatorio;
    }
    public BigDecimal getFlgObbligatorio() {
        return this.flgObbligatorio;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idTipoAllegato);
        sb.append("|");
        sb.append(idTipoIntervento);
        sb.append("|");
        sb.append(flgObbligatorio);
        return sb.toString(); 
    } 


}
