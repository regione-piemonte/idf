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
 * Java bean for 'IdfTIntervVincIdro' entity
 */
public class IdfTIntervVincIdro implements Serializable {

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
    // DB : fk_intervento_padre_variante numeric 
 	@JsonProperty(value="fk_intervento_padre_variante")
    private BigDecimal fkInterventoPadreVariante;

    // DB : fk_intervento_padre_proroga numeric 
 	@JsonProperty(value="fk_intervento_padre_proroga")
    private BigDecimal fkInterventoPadreProroga;

    // DB : fk_tipo_intervento numeric 
 	@JsonProperty(value="fk_tipo_intervento")
    private BigDecimal fkTipoIntervento;

    // DB : desc_tipo_interv_altro varchar 
 	@JsonProperty(value="desc_tipo_interv_altro")
    private String descTipoIntervAltro;

    // DB : movimenti_terra_mc int4 
 	@JsonProperty(value="movimenti_terra_mc")
    private Integer movimentiTerraMc;

    // DB : movimenti_terra_vincidro_mc int4 
 	@JsonProperty(value="movimenti_terra_vincidro_mc")
    private Integer movimentiTerraVincidroMc;

    // DB : mesi_intervento bpchar 
 	@JsonProperty(value="mesi_intervento")
    private String mesiIntervento;

    // DB : flg_aree_dissesto numeric 
 	@JsonProperty(value="flg_aree_dissesto")
    private BigDecimal flgAreeDissesto;

    // DB : flg_aree_esondazione numeric 
 	@JsonProperty(value="flg_aree_esondazione")
    private BigDecimal flgAreeEsondazione;

    // DB : flg_cauzione_vi numeric 
 	@JsonProperty(value="flg_cauzione_vi")
    private BigDecimal flgCauzioneVi;

    // DB : flg_compensazione varchar 
 	@JsonProperty(value="flg_compensazione")
    private String flgCompensazione;

    // DB : cm_bosc_euro numeric 
 	@JsonProperty(value="cm_bosc_euro")
    private BigDecimal cmBoscEuro;

    // DB : cm_nobosc_euro numeric 
 	@JsonProperty(value="cm_nobosc_euro")
    private BigDecimal cmNoboscEuro;

    // DB : flg_art7_a numeric 
 	@JsonProperty(value="flg_art7_a")
    private BigDecimal flgArt7A;

    // DB : flg_art7_b numeric 
 	@JsonProperty(value="flg_art7_b")
    private BigDecimal flgArt7B;

    // DB : flg_art7_c numeric 
 	@JsonProperty(value="flg_art7_c")
    private BigDecimal flgArt7C;

    // DB : flg_art7_d numeric 
 	@JsonProperty(value="flg_art7_d")
    private BigDecimal flgArt7D;

    // DB : flg_art7_d_bis numeric 
 	@JsonProperty(value="flg_art7_d_bis")
    private BigDecimal flgArt7DBis;

    // DB : flg_art9_a numeric 
 	@JsonProperty(value="flg_art9_a")
    private BigDecimal flgArt9A;

    // DB : flg_art9_b numeric 
 	@JsonProperty(value="flg_art9_b")
    private BigDecimal flgArt9B;

    // DB : flg_art9_c numeric 
 	@JsonProperty(value="flg_art9_c")
    private BigDecimal flgArt9C;

    // DB : flg_spese_istruttoria numeric 
 	@JsonProperty(value="flg_spese_istruttoria")
    private BigDecimal flgSpeseIstruttoria;

    // DB : flg_esenzione_marca_bollo numeric 
 	@JsonProperty(value="flg_esenzione_marca_bollo")
    private BigDecimal flgEsenzioneMarcaBollo;

    // DB : n_marca_bollo varchar 
 	@JsonProperty(value="n_marca_bollo")
    private String nMarcaBollo;

    // DB : flg_importo numeric 
 	@JsonProperty(value="flg_importo")
    private BigDecimal flgImporto;

    // DB : flg_copia_conforme numeric 
 	@JsonProperty(value="flg_copia_conforme")
    private BigDecimal flgCopiaConforme;

    // DB : flg_conf_servizi numeric 
 	@JsonProperty(value="flg_conf_servizi")
    private BigDecimal flgConfServizi;

    // DB : flg_suap numeric 
 	@JsonProperty(value="flg_suap")
    private BigDecimal flgSuap;

    // DB : annotazioni varchar 
 	@JsonProperty(value="annotazioni")
    private String annotazioni;

    // DB : data_inserimento date 
 	@JsonProperty(value="data_inserimento")
    private LocalDate dataInserimento;

    // DB : data_aggiornamento date 
 	@JsonProperty(value="data_aggiornamento")
    private Date dataAggiornamento;

    // DB : fk_config_utente int4 
 	@JsonProperty(value="fk_config_utente")
    private Integer fkConfigUtente;

    // DB : flg_proprieta numeric 
 	@JsonProperty(value="flg_proprieta")
    private BigDecimal flgProprieta;

    // DB : flg_dissensi numeric 
 	@JsonProperty(value="flg_dissensi")
    private BigDecimal flgDissensi;
 	
 	
    // DB : cm_supbosc_ha 
 	@JsonProperty(value="cm_supbosc_ha")
    private BigDecimal cmSupboscHa;
 	
    // DB : cm_supbosc_ha 
 	@JsonProperty(value="cm_supnobosc_ha")
    private BigDecimal cmSupnoboscHa;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdIntervento( BigDecimal idIntervento ) {
        this.idIntervento = idIntervento;
    }

    public BigDecimal getIdIntervento() {
        return this.idIntervento;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFkInterventoPadreVariante( BigDecimal fkInterventoPadreVariante ) {
        this.fkInterventoPadreVariante = fkInterventoPadreVariante;
    }
    public BigDecimal getFkInterventoPadreVariante() {
        return this.fkInterventoPadreVariante;
    }

    public void setFkInterventoPadreProroga( BigDecimal fkInterventoPadreProroga ) {
        this.fkInterventoPadreProroga = fkInterventoPadreProroga;
    }
    public BigDecimal getFkInterventoPadreProroga() {
        return this.fkInterventoPadreProroga;
    }

    public void setFkTipoIntervento( BigDecimal fkTipoIntervento ) {
        this.fkTipoIntervento = fkTipoIntervento;
    }
    public BigDecimal getFkTipoIntervento() {
        return this.fkTipoIntervento;
    }

    public void setDescTipoIntervAltro( String descTipoIntervAltro ) {
        this.descTipoIntervAltro = descTipoIntervAltro;
    }
    public String getDescTipoIntervAltro() {
        return this.descTipoIntervAltro;
    }

    public void setMovimentiTerraMc( Integer movimentiTerraMc ) {
        this.movimentiTerraMc = movimentiTerraMc;
    }
    public Integer getMovimentiTerraMc() {
        return this.movimentiTerraMc;
    }

    public void setMovimentiTerraVincidroMc( Integer movimentiTerraVincidroMc ) {
        this.movimentiTerraVincidroMc = movimentiTerraVincidroMc;
    }
    public Integer getMovimentiTerraVincidroMc() {
        return this.movimentiTerraVincidroMc;
    }

    public void setMesiIntervento( String mesiIntervento ) {
        this.mesiIntervento = mesiIntervento;
    }
    public String getMesiIntervento() {
        return this.mesiIntervento;
    }

    public void setFlgAreeDissesto( BigDecimal flgAreeDissesto ) {
        this.flgAreeDissesto = flgAreeDissesto;
    }
    public BigDecimal getFlgAreeDissesto() {
        return this.flgAreeDissesto;
    }

    public void setFlgAreeEsondazione( BigDecimal flgAreeEsondazione ) {
        this.flgAreeEsondazione = flgAreeEsondazione;
    }
    public BigDecimal getFlgAreeEsondazione() {
        return this.flgAreeEsondazione;
    }

    public void setFlgCauzioneVi( BigDecimal flgCauzioneVi ) {
        this.flgCauzioneVi = flgCauzioneVi;
    }
    public BigDecimal getFlgCauzioneVi() {
        return this.flgCauzioneVi;
    }

    public void setFlgCompensazione( String flgCompensazione ) {
        this.flgCompensazione = flgCompensazione;
    }
    public String getFlgCompensazione() {
        return this.flgCompensazione;
    }

    public void setCmBoscEuro( BigDecimal cmBoscEuro ) {
        this.cmBoscEuro = cmBoscEuro;
    }
    public BigDecimal getCmBoscEuro() {
        return this.cmBoscEuro;
    }

    public void setCmNoboscEuro( BigDecimal cmNoboscEuro ) {
        this.cmNoboscEuro = cmNoboscEuro;
    }
    public BigDecimal getCmNoboscEuro() {
        return this.cmNoboscEuro;
    }

    public void setFlgArt7A( BigDecimal flgArt7A ) {
        this.flgArt7A = flgArt7A;
    }
    public BigDecimal getFlgArt7A() {
        return this.flgArt7A;
    }

    public void setFlgArt7B( BigDecimal flgArt7B ) {
        this.flgArt7B = flgArt7B;
    }
    public BigDecimal getFlgArt7B() {
        return this.flgArt7B;
    }

    public void setFlgArt7C( BigDecimal flgArt7C ) {
        this.flgArt7C = flgArt7C;
    }
    public BigDecimal getFlgArt7C() {
        return this.flgArt7C;
    }

    public void setFlgArt7D( BigDecimal flgArt7D ) {
        this.flgArt7D = flgArt7D;
    }
    public BigDecimal getFlgArt7D() {
        return this.flgArt7D;
    }

    public void setFlgArt7DBis( BigDecimal flgArt7DBis ) {
        this.flgArt7DBis = flgArt7DBis;
    }
    public BigDecimal getFlgArt7DBis() {
        return this.flgArt7DBis;
    }

    public void setFlgArt9A( BigDecimal flgArt9A ) {
        this.flgArt9A = flgArt9A;
    }
    public BigDecimal getFlgArt9A() {
        return this.flgArt9A;
    }

    public void setFlgArt9B( BigDecimal flgArt9B ) {
        this.flgArt9B = flgArt9B;
    }
    public BigDecimal getFlgArt9B() {
        return this.flgArt9B;
    }

    public void setFlgArt9C( BigDecimal flgArt9C ) {
        this.flgArt9C = flgArt9C;
    }
    public BigDecimal getFlgArt9C() {
        return this.flgArt9C;
    }

    public void setFlgSpeseIstruttoria( BigDecimal flgSpeseIstruttoria ) {
        this.flgSpeseIstruttoria = flgSpeseIstruttoria;
    }
    public BigDecimal getFlgSpeseIstruttoria() {
        return this.flgSpeseIstruttoria;
    }

    public void setFlgEsenzioneMarcaBollo( BigDecimal flgEsenzioneMarcaBollo ) {
        this.flgEsenzioneMarcaBollo = flgEsenzioneMarcaBollo;
    }
    public BigDecimal getFlgEsenzioneMarcaBollo() {
        return this.flgEsenzioneMarcaBollo;
    }

    public void setNMarcaBollo( String nMarcaBollo ) {
        this.nMarcaBollo = nMarcaBollo;
    }
    public String getNMarcaBollo() {
        return this.nMarcaBollo;
    }

    public void setFlgImporto( BigDecimal flgImporto ) {
        this.flgImporto = flgImporto;
    }
    public BigDecimal getFlgImporto() {
        return this.flgImporto;
    }

    public void setFlgCopiaConforme( BigDecimal flgCopiaConforme ) {
        this.flgCopiaConforme = flgCopiaConforme;
    }
    public BigDecimal getFlgCopiaConforme() {
        return this.flgCopiaConforme;
    }

    public void setFlgConfServizi( BigDecimal flgConfServizi ) {
        this.flgConfServizi = flgConfServizi;
    }
    public BigDecimal getFlgConfServizi() {
        return this.flgConfServizi;
    }

    public void setFlgSuap( BigDecimal flgSuap ) {
        this.flgSuap = flgSuap;
    }
    public BigDecimal getFlgSuap() {
        return this.flgSuap;
    }

    public void setAnnotazioni( String annotazioni ) {
        this.annotazioni = annotazioni;
    }
    public String getAnnotazioni() {
        return this.annotazioni;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
    public LocalDate getDataInserimento() {
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

    public void setFlgProprieta( BigDecimal flgProprieta ) {
        this.flgProprieta = flgProprieta;
    }
    public BigDecimal getFlgProprieta() {
        return this.flgProprieta;
    }

    public void setFlgDissensi( BigDecimal flgDissensi ) {
        this.flgDissensi = flgDissensi;
    }
    public BigDecimal getFlgDissensi() {
        return this.flgDissensi;
    }

    public String getnMarcaBollo() {
		return nMarcaBollo;
	}

	public void setnMarcaBollo(String nMarcaBollo) {
		this.nMarcaBollo = nMarcaBollo;
	}

	public BigDecimal getCmSupboscHa() {
		return cmSupboscHa;
	}

	public void setCmSupboscHa(BigDecimal cmSupboscHa) {
		this.cmSupboscHa = cmSupboscHa;
	}

	public BigDecimal getCmSupnoboscHa() {
		return cmSupnoboscHa;
	}

	public void setCmSupnoboscHa(BigDecimal cmSupnoboscHa) {
		this.cmSupnoboscHa = cmSupnoboscHa;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IdfTIntervVincIdro [idIntervento=");
		builder.append(idIntervento);
		builder.append(", fkInterventoPadreVariante=");
		builder.append(fkInterventoPadreVariante);
		builder.append(", fkInterventoPadreProroga=");
		builder.append(fkInterventoPadreProroga);
		builder.append(", fkTipoIntervento=");
		builder.append(fkTipoIntervento);
		builder.append(", descTipoIntervAltro=");
		builder.append(descTipoIntervAltro);
		builder.append(", movimentiTerraMc=");
		builder.append(movimentiTerraMc);
		builder.append(", movimentiTerraVincidroMc=");
		builder.append(movimentiTerraVincidroMc);
		builder.append(", mesiIntervento=");
		builder.append(mesiIntervento);
		builder.append(", flgAreeDissesto=");
		builder.append(flgAreeDissesto);
		builder.append(", flgAreeEsondazione=");
		builder.append(flgAreeEsondazione);
		builder.append(", flgCauzioneVi=");
		builder.append(flgCauzioneVi);
		builder.append(", flgCompensazione=");
		builder.append(flgCompensazione);
		builder.append(", cmBoscEuro=");
		builder.append(cmBoscEuro);
		builder.append(", cmNoboscEuro=");
		builder.append(cmNoboscEuro);
		builder.append(", flgArt7A=");
		builder.append(flgArt7A);
		builder.append(", flgArt7B=");
		builder.append(flgArt7B);
		builder.append(", flgArt7C=");
		builder.append(flgArt7C);
		builder.append(", flgArt7D=");
		builder.append(flgArt7D);
		builder.append(", flgArt7DBis=");
		builder.append(flgArt7DBis);
		builder.append(", flgArt9A=");
		builder.append(flgArt9A);
		builder.append(", flgArt9B=");
		builder.append(flgArt9B);
		builder.append(", flgArt9C=");
		builder.append(flgArt9C);
		builder.append(", flgSpeseIstruttoria=");
		builder.append(flgSpeseIstruttoria);
		builder.append(", flgEsenzioneMarcaBollo=");
		builder.append(flgEsenzioneMarcaBollo);
		builder.append(", nMarcaBollo=");
		builder.append(nMarcaBollo);
		builder.append(", flgImporto=");
		builder.append(flgImporto);
		builder.append(", flgCopiaConforme=");
		builder.append(flgCopiaConforme);
		builder.append(", flgConfServizi=");
		builder.append(flgConfServizi);
		builder.append(", flgSuap=");
		builder.append(flgSuap);
		builder.append(", annotazioni=");
		builder.append(annotazioni);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", flgProprieta=");
		builder.append(flgProprieta);
		builder.append(", flgDissensi=");
		builder.append(flgDissensi);
		builder.append(", cmSupboscHa=");
		builder.append(cmSupboscHa);
		builder.append(", cmSupnoboscHa=");
		builder.append(cmSupnoboscHa);
		builder.append("]");
		return builder.toString();
	}

	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    


}
