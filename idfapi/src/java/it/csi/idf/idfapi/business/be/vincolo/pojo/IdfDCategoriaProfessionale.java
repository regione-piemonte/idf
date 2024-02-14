/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.math.BigDecimal;

/**
 * Java bean for 'IdfDCategoriaProfessionale' entity
 */
public class IdfDCategoriaProfessionale implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_categoria_professionale numeric 
    @JsonProperty(value="id_categoria_professionale")
	private BigDecimal idCategoriaProfessionale;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : descr_categoria_professionale varchar 
 	@JsonProperty(value="descr_categoria_professionale")
    private String descrCategoriaProfessionale;

    // DB : mtd_ordinamento numeric 
 	@JsonProperty(value="mtd_ordinamento")
    private BigDecimal mtdOrdinamento;

    // DB : flg_visibile numeric 
 	@JsonProperty(value="flg_visibile")
    private BigDecimal flgVisibile;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdCategoriaProfessionale( BigDecimal idCategoriaProfessionale ) {
        this.idCategoriaProfessionale = idCategoriaProfessionale ;
    }

    public BigDecimal getIdCategoriaProfessionale() {
        return this.idCategoriaProfessionale;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDescrCategoriaProfessionale( String descrCategoriaProfessionale ) {
        this.descrCategoriaProfessionale = descrCategoriaProfessionale;
    }
    public String getDescrCategoriaProfessionale() {
        return this.descrCategoriaProfessionale;
    }

    public void setMtdOrdinamento( BigDecimal mtdOrdinamento ) {
        this.mtdOrdinamento = mtdOrdinamento;
    }
    public BigDecimal getMtdOrdinamento() {
        return this.mtdOrdinamento;
    }

    public void setFlgVisibile( BigDecimal flgVisibile ) {
        this.flgVisibile = flgVisibile;
    }
    public BigDecimal getFlgVisibile() {
        return this.flgVisibile;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idCategoriaProfessionale);
        sb.append("|");
        sb.append(descrCategoriaProfessionale);
        sb.append("|");
        sb.append(mtdOrdinamento);
        sb.append("|");
        sb.append(flgVisibile);
        return sb.toString(); 
    } 


}
