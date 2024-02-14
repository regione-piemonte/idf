/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.math.BigDecimal;

/**
 * Java bean for 'IdfCnfSkOkVincIdro' entity
 */
public class IdfCnfSkOkVincIdro implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_intervento numeric 
    @JsonProperty(value="id_intervento")
	private BigDecimal idIntervento;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : flg_sez1 int4 
 	@JsonProperty(value="flg_sez1")
    private Integer flgSez1;

    // DB : flg_sez2 int4 
 	@JsonProperty(value="flg_sez2")
    private Integer flgSez2;

    // DB : flg_sez3 int4 
 	@JsonProperty(value="flg_sez3")
    private Integer flgSez3;

    // DB : flg_sez4 int4 
 	@JsonProperty(value="flg_sez4")
    private Integer flgSez4;

    // DB : flg_sez5 int4 
 	@JsonProperty(value="flg_sez5")
    private Integer flgSez5;

    // DB : flg_sez6 int4 
 	@JsonProperty(value="flg_sez6")
    private Integer flgSez6;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdIntervento( BigDecimal idIntervento ) {
        this.idIntervento = idIntervento ;
    }

    public BigDecimal getIdIntervento() {
        return this.idIntervento;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFlgSez1( Integer flgSez1 ) {
        this.flgSez1 = flgSez1;
    }
    public Integer getFlgSez1() {
        return this.flgSez1;
    }

    public void setFlgSez2( Integer flgSez2 ) {
        this.flgSez2 = flgSez2;
    }
    public Integer getFlgSez2() {
        return this.flgSez2;
    }

    public void setFlgSez3( Integer flgSez3 ) {
        this.flgSez3 = flgSez3;
    }
    public Integer getFlgSez3() {
        return this.flgSez3;
    }

    public void setFlgSez4( Integer flgSez4 ) {
        this.flgSez4 = flgSez4;
    }
    public Integer getFlgSez4() {
        return this.flgSez4;
    }

    public void setFlgSez5( Integer flgSez5 ) {
        this.flgSez5 = flgSez5;
    }
    public Integer getFlgSez5() {
        return this.flgSez5;
    }

    public void setFlgSez6( Integer flgSez6 ) {
        this.flgSez6 = flgSez6;
    }
    public Integer getFlgSez6() {
        return this.flgSez6;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idIntervento);
        sb.append("|");
        sb.append(flgSez1);
        sb.append("|");
        sb.append(flgSez2);
        sb.append("|");
        sb.append(flgSez3);
        sb.append("|");
        sb.append(flgSez4);
        sb.append("|");
        sb.append(flgSez5);
        sb.append("|");
        sb.append(flgSez6);
        return sb.toString(); 
    } 


}
