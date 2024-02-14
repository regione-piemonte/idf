/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.io.Serializable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Java bean for 'IdfGeoPlIntervVincidro' entity
 */
public class IdfGeoPlIntervVincidro implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_geo_pl_interv_vincidro int4 
    @JsonProperty(value="id_geo_pl_interv_vincidro")
	private Integer idGeoPlIntervVincidro;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : fk_intervento numeric 
 	@JsonProperty(value="fk_intervento")
    private Integer fkIntervento;

    // DB : data_inserimento date 
 	@JsonProperty(value="data_inserimento")
    private LocalDate dataInserimento;

    // DB : geometria geometry 
 	@JsonProperty(value="geometria")
    private Object geometria;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdGeoPlIntervVincidro( Integer idGeoPlIntervVincidro ) {
        this.idGeoPlIntervVincidro = idGeoPlIntervVincidro ;
    }

    public Integer getIdGeoPlIntervVincidro() {
        return this.idGeoPlIntervVincidro;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFkIntervento(Integer fkIntervento) {
        this.fkIntervento = fkIntervento;
    }
    public Integer getFkIntervento() {
        return this.fkIntervento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
    public LocalDate getDataInserimento() {
        return this.dataInserimento;
    }

    public void setGeometria(Object geometria) {
        this.geometria = geometria;
    }
    public Object getGeometria() {
        return this.geometria;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idGeoPlIntervVincidro);
        sb.append("|");
        sb.append(fkIntervento);
        sb.append("|");
        sb.append(dataInserimento);
        sb.append("|");
        sb.append(geometria);
        return sb.toString(); 
    } 


}
