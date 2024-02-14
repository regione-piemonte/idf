/**
 * AziendaVariazioneAnagrafica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class AziendaVariazioneAnagrafica  implements java.io.Serializable {
    private it.csi.idf.idfapi.util.service.integration.aaep.CodiciATECO[] codici;

    private java.lang.String numeroCCIAA;

    private java.lang.String descrizioneNaturaGiuridica;

    private java.lang.String codiceCausaleCessazione;

    private java.lang.String codiceFiscale;

    private java.lang.String partitaIva;

    private java.lang.String descrizioneTipoAzienda;

    private java.lang.String descrizioneStatoAttiv;

    private java.lang.String provinciaCCIAA;

    private java.lang.String idTipoAzienda;

    private long idAzienda;

    private java.lang.String idStatoAttiv;

    private java.lang.String FIscrSezArtigiani;

    private java.lang.String descrizioneCausaleCessazione;

    private java.lang.String descrIscrSezArtigiani;

    private java.lang.String descrizioneFontedato;

    private java.lang.String annoCCIAA;

    private java.lang.String oggettoSociale;

    private long idFonteDato;

    private java.lang.String idNaturaGiuridica;

    private java.util.Calendar dataInizioValidita;

    private java.util.Calendar dataFineValidita;

    private java.lang.String NRegistroImpreseCCIAA;

    private java.lang.String denominazione;

    private java.util.Calendar dataCostituzione;

    private java.util.Calendar dataCessazione;

    private java.lang.String tribunaleCCIAA;

    private it.csi.idf.idfapi.util.service.integration.aaep.VariazioneAnagrafica[] listaVariazAnagrafica;

    public AziendaVariazioneAnagrafica() {
    }

    public AziendaVariazioneAnagrafica(
           it.csi.idf.idfapi.util.service.integration.aaep.CodiciATECO[] codici,
           java.lang.String numeroCCIAA,
           java.lang.String descrizioneNaturaGiuridica,
           java.lang.String codiceCausaleCessazione,
           java.lang.String codiceFiscale,
           java.lang.String partitaIva,
           java.lang.String descrizioneTipoAzienda,
           java.lang.String descrizioneStatoAttiv,
           java.lang.String provinciaCCIAA,
           java.lang.String idTipoAzienda,
           long idAzienda,
           java.lang.String idStatoAttiv,
           java.lang.String FIscrSezArtigiani,
           java.lang.String descrizioneCausaleCessazione,
           java.lang.String descrIscrSezArtigiani,
           java.lang.String descrizioneFontedato,
           java.lang.String annoCCIAA,
           java.lang.String oggettoSociale,
           long idFonteDato,
           java.lang.String idNaturaGiuridica,
           java.util.Calendar dataInizioValidita,
           java.util.Calendar dataFineValidita,
           java.lang.String NRegistroImpreseCCIAA,
           java.lang.String denominazione,
           java.util.Calendar dataCostituzione,
           java.util.Calendar dataCessazione,
           java.lang.String tribunaleCCIAA,
           it.csi.idf.idfapi.util.service.integration.aaep.VariazioneAnagrafica[] listaVariazAnagrafica) {
           this.codici = codici;
           this.numeroCCIAA = numeroCCIAA;
           this.descrizioneNaturaGiuridica = descrizioneNaturaGiuridica;
           this.codiceCausaleCessazione = codiceCausaleCessazione;
           this.codiceFiscale = codiceFiscale;
           this.partitaIva = partitaIva;
           this.descrizioneTipoAzienda = descrizioneTipoAzienda;
           this.descrizioneStatoAttiv = descrizioneStatoAttiv;
           this.provinciaCCIAA = provinciaCCIAA;
           this.idTipoAzienda = idTipoAzienda;
           this.idAzienda = idAzienda;
           this.idStatoAttiv = idStatoAttiv;
           this.FIscrSezArtigiani = FIscrSezArtigiani;
           this.descrizioneCausaleCessazione = descrizioneCausaleCessazione;
           this.descrIscrSezArtigiani = descrIscrSezArtigiani;
           this.descrizioneFontedato = descrizioneFontedato;
           this.annoCCIAA = annoCCIAA;
           this.oggettoSociale = oggettoSociale;
           this.idFonteDato = idFonteDato;
           this.idNaturaGiuridica = idNaturaGiuridica;
           this.dataInizioValidita = dataInizioValidita;
           this.dataFineValidita = dataFineValidita;
           this.NRegistroImpreseCCIAA = NRegistroImpreseCCIAA;
           this.denominazione = denominazione;
           this.dataCostituzione = dataCostituzione;
           this.dataCessazione = dataCessazione;
           this.tribunaleCCIAA = tribunaleCCIAA;
           this.listaVariazAnagrafica = listaVariazAnagrafica;
    }


    /**
     * Gets the codici value for this AziendaVariazioneAnagrafica.
     * 
     * @return codici
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.CodiciATECO[] getCodici() {
        return codici;
    }


    /**
     * Sets the codici value for this AziendaVariazioneAnagrafica.
     * 
     * @param codici
     */
    public void setCodici(it.csi.idf.idfapi.util.service.integration.aaep.CodiciATECO[] codici) {
        this.codici = codici;
    }


    /**
     * Gets the numeroCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @return numeroCCIAA
     */
    public java.lang.String getNumeroCCIAA() {
        return numeroCCIAA;
    }


    /**
     * Sets the numeroCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @param numeroCCIAA
     */
    public void setNumeroCCIAA(java.lang.String numeroCCIAA) {
        this.numeroCCIAA = numeroCCIAA;
    }


    /**
     * Gets the descrizioneNaturaGiuridica value for this AziendaVariazioneAnagrafica.
     * 
     * @return descrizioneNaturaGiuridica
     */
    public java.lang.String getDescrizioneNaturaGiuridica() {
        return descrizioneNaturaGiuridica;
    }


    /**
     * Sets the descrizioneNaturaGiuridica value for this AziendaVariazioneAnagrafica.
     * 
     * @param descrizioneNaturaGiuridica
     */
    public void setDescrizioneNaturaGiuridica(java.lang.String descrizioneNaturaGiuridica) {
        this.descrizioneNaturaGiuridica = descrizioneNaturaGiuridica;
    }


    /**
     * Gets the codiceCausaleCessazione value for this AziendaVariazioneAnagrafica.
     * 
     * @return codiceCausaleCessazione
     */
    public java.lang.String getCodiceCausaleCessazione() {
        return codiceCausaleCessazione;
    }


    /**
     * Sets the codiceCausaleCessazione value for this AziendaVariazioneAnagrafica.
     * 
     * @param codiceCausaleCessazione
     */
    public void setCodiceCausaleCessazione(java.lang.String codiceCausaleCessazione) {
        this.codiceCausaleCessazione = codiceCausaleCessazione;
    }


    /**
     * Gets the codiceFiscale value for this AziendaVariazioneAnagrafica.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this AziendaVariazioneAnagrafica.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the partitaIva value for this AziendaVariazioneAnagrafica.
     * 
     * @return partitaIva
     */
    public java.lang.String getPartitaIva() {
        return partitaIva;
    }


    /**
     * Sets the partitaIva value for this AziendaVariazioneAnagrafica.
     * 
     * @param partitaIva
     */
    public void setPartitaIva(java.lang.String partitaIva) {
        this.partitaIva = partitaIva;
    }


    /**
     * Gets the descrizioneTipoAzienda value for this AziendaVariazioneAnagrafica.
     * 
     * @return descrizioneTipoAzienda
     */
    public java.lang.String getDescrizioneTipoAzienda() {
        return descrizioneTipoAzienda;
    }


    /**
     * Sets the descrizioneTipoAzienda value for this AziendaVariazioneAnagrafica.
     * 
     * @param descrizioneTipoAzienda
     */
    public void setDescrizioneTipoAzienda(java.lang.String descrizioneTipoAzienda) {
        this.descrizioneTipoAzienda = descrizioneTipoAzienda;
    }


    /**
     * Gets the descrizioneStatoAttiv value for this AziendaVariazioneAnagrafica.
     * 
     * @return descrizioneStatoAttiv
     */
    public java.lang.String getDescrizioneStatoAttiv() {
        return descrizioneStatoAttiv;
    }


    /**
     * Sets the descrizioneStatoAttiv value for this AziendaVariazioneAnagrafica.
     * 
     * @param descrizioneStatoAttiv
     */
    public void setDescrizioneStatoAttiv(java.lang.String descrizioneStatoAttiv) {
        this.descrizioneStatoAttiv = descrizioneStatoAttiv;
    }


    /**
     * Gets the provinciaCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @return provinciaCCIAA
     */
    public java.lang.String getProvinciaCCIAA() {
        return provinciaCCIAA;
    }


    /**
     * Sets the provinciaCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @param provinciaCCIAA
     */
    public void setProvinciaCCIAA(java.lang.String provinciaCCIAA) {
        this.provinciaCCIAA = provinciaCCIAA;
    }


    /**
     * Gets the idTipoAzienda value for this AziendaVariazioneAnagrafica.
     * 
     * @return idTipoAzienda
     */
    public java.lang.String getIdTipoAzienda() {
        return idTipoAzienda;
    }


    /**
     * Sets the idTipoAzienda value for this AziendaVariazioneAnagrafica.
     * 
     * @param idTipoAzienda
     */
    public void setIdTipoAzienda(java.lang.String idTipoAzienda) {
        this.idTipoAzienda = idTipoAzienda;
    }


    /**
     * Gets the idAzienda value for this AziendaVariazioneAnagrafica.
     * 
     * @return idAzienda
     */
    public long getIdAzienda() {
        return idAzienda;
    }


    /**
     * Sets the idAzienda value for this AziendaVariazioneAnagrafica.
     * 
     * @param idAzienda
     */
    public void setIdAzienda(long idAzienda) {
        this.idAzienda = idAzienda;
    }


    /**
     * Gets the idStatoAttiv value for this AziendaVariazioneAnagrafica.
     * 
     * @return idStatoAttiv
     */
    public java.lang.String getIdStatoAttiv() {
        return idStatoAttiv;
    }


    /**
     * Sets the idStatoAttiv value for this AziendaVariazioneAnagrafica.
     * 
     * @param idStatoAttiv
     */
    public void setIdStatoAttiv(java.lang.String idStatoAttiv) {
        this.idStatoAttiv = idStatoAttiv;
    }


    /**
     * Gets the FIscrSezArtigiani value for this AziendaVariazioneAnagrafica.
     * 
     * @return FIscrSezArtigiani
     */
    public java.lang.String getFIscrSezArtigiani() {
        return FIscrSezArtigiani;
    }


    /**
     * Sets the FIscrSezArtigiani value for this AziendaVariazioneAnagrafica.
     * 
     * @param FIscrSezArtigiani
     */
    public void setFIscrSezArtigiani(java.lang.String FIscrSezArtigiani) {
        this.FIscrSezArtigiani = FIscrSezArtigiani;
    }


    /**
     * Gets the descrizioneCausaleCessazione value for this AziendaVariazioneAnagrafica.
     * 
     * @return descrizioneCausaleCessazione
     */
    public java.lang.String getDescrizioneCausaleCessazione() {
        return descrizioneCausaleCessazione;
    }


    /**
     * Sets the descrizioneCausaleCessazione value for this AziendaVariazioneAnagrafica.
     * 
     * @param descrizioneCausaleCessazione
     */
    public void setDescrizioneCausaleCessazione(java.lang.String descrizioneCausaleCessazione) {
        this.descrizioneCausaleCessazione = descrizioneCausaleCessazione;
    }


    /**
     * Gets the descrIscrSezArtigiani value for this AziendaVariazioneAnagrafica.
     * 
     * @return descrIscrSezArtigiani
     */
    public java.lang.String getDescrIscrSezArtigiani() {
        return descrIscrSezArtigiani;
    }


    /**
     * Sets the descrIscrSezArtigiani value for this AziendaVariazioneAnagrafica.
     * 
     * @param descrIscrSezArtigiani
     */
    public void setDescrIscrSezArtigiani(java.lang.String descrIscrSezArtigiani) {
        this.descrIscrSezArtigiani = descrIscrSezArtigiani;
    }


    /**
     * Gets the descrizioneFontedato value for this AziendaVariazioneAnagrafica.
     * 
     * @return descrizioneFontedato
     */
    public java.lang.String getDescrizioneFontedato() {
        return descrizioneFontedato;
    }


    /**
     * Sets the descrizioneFontedato value for this AziendaVariazioneAnagrafica.
     * 
     * @param descrizioneFontedato
     */
    public void setDescrizioneFontedato(java.lang.String descrizioneFontedato) {
        this.descrizioneFontedato = descrizioneFontedato;
    }


    /**
     * Gets the annoCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @return annoCCIAA
     */
    public java.lang.String getAnnoCCIAA() {
        return annoCCIAA;
    }


    /**
     * Sets the annoCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @param annoCCIAA
     */
    public void setAnnoCCIAA(java.lang.String annoCCIAA) {
        this.annoCCIAA = annoCCIAA;
    }


    /**
     * Gets the oggettoSociale value for this AziendaVariazioneAnagrafica.
     * 
     * @return oggettoSociale
     */
    public java.lang.String getOggettoSociale() {
        return oggettoSociale;
    }


    /**
     * Sets the oggettoSociale value for this AziendaVariazioneAnagrafica.
     * 
     * @param oggettoSociale
     */
    public void setOggettoSociale(java.lang.String oggettoSociale) {
        this.oggettoSociale = oggettoSociale;
    }


    /**
     * Gets the idFonteDato value for this AziendaVariazioneAnagrafica.
     * 
     * @return idFonteDato
     */
    public long getIdFonteDato() {
        return idFonteDato;
    }


    /**
     * Sets the idFonteDato value for this AziendaVariazioneAnagrafica.
     * 
     * @param idFonteDato
     */
    public void setIdFonteDato(long idFonteDato) {
        this.idFonteDato = idFonteDato;
    }


    /**
     * Gets the idNaturaGiuridica value for this AziendaVariazioneAnagrafica.
     * 
     * @return idNaturaGiuridica
     */
    public java.lang.String getIdNaturaGiuridica() {
        return idNaturaGiuridica;
    }


    /**
     * Sets the idNaturaGiuridica value for this AziendaVariazioneAnagrafica.
     * 
     * @param idNaturaGiuridica
     */
    public void setIdNaturaGiuridica(java.lang.String idNaturaGiuridica) {
        this.idNaturaGiuridica = idNaturaGiuridica;
    }


    /**
     * Gets the dataInizioValidita value for this AziendaVariazioneAnagrafica.
     * 
     * @return dataInizioValidita
     */
    public java.util.Calendar getDataInizioValidita() {
        return dataInizioValidita;
    }


    /**
     * Sets the dataInizioValidita value for this AziendaVariazioneAnagrafica.
     * 
     * @param dataInizioValidita
     */
    public void setDataInizioValidita(java.util.Calendar dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }


    /**
     * Gets the dataFineValidita value for this AziendaVariazioneAnagrafica.
     * 
     * @return dataFineValidita
     */
    public java.util.Calendar getDataFineValidita() {
        return dataFineValidita;
    }


    /**
     * Sets the dataFineValidita value for this AziendaVariazioneAnagrafica.
     * 
     * @param dataFineValidita
     */
    public void setDataFineValidita(java.util.Calendar dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }


    /**
     * Gets the NRegistroImpreseCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @return NRegistroImpreseCCIAA
     */
    public java.lang.String getNRegistroImpreseCCIAA() {
        return NRegistroImpreseCCIAA;
    }


    /**
     * Sets the NRegistroImpreseCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @param NRegistroImpreseCCIAA
     */
    public void setNRegistroImpreseCCIAA(java.lang.String NRegistroImpreseCCIAA) {
        this.NRegistroImpreseCCIAA = NRegistroImpreseCCIAA;
    }


    /**
     * Gets the denominazione value for this AziendaVariazioneAnagrafica.
     * 
     * @return denominazione
     */
    public java.lang.String getDenominazione() {
        return denominazione;
    }


    /**
     * Sets the denominazione value for this AziendaVariazioneAnagrafica.
     * 
     * @param denominazione
     */
    public void setDenominazione(java.lang.String denominazione) {
        this.denominazione = denominazione;
    }


    /**
     * Gets the dataCostituzione value for this AziendaVariazioneAnagrafica.
     * 
     * @return dataCostituzione
     */
    public java.util.Calendar getDataCostituzione() {
        return dataCostituzione;
    }


    /**
     * Sets the dataCostituzione value for this AziendaVariazioneAnagrafica.
     * 
     * @param dataCostituzione
     */
    public void setDataCostituzione(java.util.Calendar dataCostituzione) {
        this.dataCostituzione = dataCostituzione;
    }


    /**
     * Gets the dataCessazione value for this AziendaVariazioneAnagrafica.
     * 
     * @return dataCessazione
     */
    public java.util.Calendar getDataCessazione() {
        return dataCessazione;
    }


    /**
     * Sets the dataCessazione value for this AziendaVariazioneAnagrafica.
     * 
     * @param dataCessazione
     */
    public void setDataCessazione(java.util.Calendar dataCessazione) {
        this.dataCessazione = dataCessazione;
    }


    /**
     * Gets the tribunaleCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @return tribunaleCCIAA
     */
    public java.lang.String getTribunaleCCIAA() {
        return tribunaleCCIAA;
    }


    /**
     * Sets the tribunaleCCIAA value for this AziendaVariazioneAnagrafica.
     * 
     * @param tribunaleCCIAA
     */
    public void setTribunaleCCIAA(java.lang.String tribunaleCCIAA) {
        this.tribunaleCCIAA = tribunaleCCIAA;
    }


    /**
     * Gets the listaVariazAnagrafica value for this AziendaVariazioneAnagrafica.
     * 
     * @return listaVariazAnagrafica
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.VariazioneAnagrafica[] getListaVariazAnagrafica() {
        return listaVariazAnagrafica;
    }


    /**
     * Sets the listaVariazAnagrafica value for this AziendaVariazioneAnagrafica.
     * 
     * @param listaVariazAnagrafica
     */
    public void setListaVariazAnagrafica(it.csi.idf.idfapi.util.service.integration.aaep.VariazioneAnagrafica[] listaVariazAnagrafica) {
        this.listaVariazAnagrafica = listaVariazAnagrafica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AziendaVariazioneAnagrafica)) return false;
        AziendaVariazioneAnagrafica other = (AziendaVariazioneAnagrafica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codici==null && other.getCodici()==null) || 
             (this.codici!=null &&
              java.util.Arrays.equals(this.codici, other.getCodici()))) &&
            ((this.numeroCCIAA==null && other.getNumeroCCIAA()==null) || 
             (this.numeroCCIAA!=null &&
              this.numeroCCIAA.equals(other.getNumeroCCIAA()))) &&
            ((this.descrizioneNaturaGiuridica==null && other.getDescrizioneNaturaGiuridica()==null) || 
             (this.descrizioneNaturaGiuridica!=null &&
              this.descrizioneNaturaGiuridica.equals(other.getDescrizioneNaturaGiuridica()))) &&
            ((this.codiceCausaleCessazione==null && other.getCodiceCausaleCessazione()==null) || 
             (this.codiceCausaleCessazione!=null &&
              this.codiceCausaleCessazione.equals(other.getCodiceCausaleCessazione()))) &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.partitaIva==null && other.getPartitaIva()==null) || 
             (this.partitaIva!=null &&
              this.partitaIva.equals(other.getPartitaIva()))) &&
            ((this.descrizioneTipoAzienda==null && other.getDescrizioneTipoAzienda()==null) || 
             (this.descrizioneTipoAzienda!=null &&
              this.descrizioneTipoAzienda.equals(other.getDescrizioneTipoAzienda()))) &&
            ((this.descrizioneStatoAttiv==null && other.getDescrizioneStatoAttiv()==null) || 
             (this.descrizioneStatoAttiv!=null &&
              this.descrizioneStatoAttiv.equals(other.getDescrizioneStatoAttiv()))) &&
            ((this.provinciaCCIAA==null && other.getProvinciaCCIAA()==null) || 
             (this.provinciaCCIAA!=null &&
              this.provinciaCCIAA.equals(other.getProvinciaCCIAA()))) &&
            ((this.idTipoAzienda==null && other.getIdTipoAzienda()==null) || 
             (this.idTipoAzienda!=null &&
              this.idTipoAzienda.equals(other.getIdTipoAzienda()))) &&
            this.idAzienda == other.getIdAzienda() &&
            ((this.idStatoAttiv==null && other.getIdStatoAttiv()==null) || 
             (this.idStatoAttiv!=null &&
              this.idStatoAttiv.equals(other.getIdStatoAttiv()))) &&
            ((this.FIscrSezArtigiani==null && other.getFIscrSezArtigiani()==null) || 
             (this.FIscrSezArtigiani!=null &&
              this.FIscrSezArtigiani.equals(other.getFIscrSezArtigiani()))) &&
            ((this.descrizioneCausaleCessazione==null && other.getDescrizioneCausaleCessazione()==null) || 
             (this.descrizioneCausaleCessazione!=null &&
              this.descrizioneCausaleCessazione.equals(other.getDescrizioneCausaleCessazione()))) &&
            ((this.descrIscrSezArtigiani==null && other.getDescrIscrSezArtigiani()==null) || 
             (this.descrIscrSezArtigiani!=null &&
              this.descrIscrSezArtigiani.equals(other.getDescrIscrSezArtigiani()))) &&
            ((this.descrizioneFontedato==null && other.getDescrizioneFontedato()==null) || 
             (this.descrizioneFontedato!=null &&
              this.descrizioneFontedato.equals(other.getDescrizioneFontedato()))) &&
            ((this.annoCCIAA==null && other.getAnnoCCIAA()==null) || 
             (this.annoCCIAA!=null &&
              this.annoCCIAA.equals(other.getAnnoCCIAA()))) &&
            ((this.oggettoSociale==null && other.getOggettoSociale()==null) || 
             (this.oggettoSociale!=null &&
              this.oggettoSociale.equals(other.getOggettoSociale()))) &&
            this.idFonteDato == other.getIdFonteDato() &&
            ((this.idNaturaGiuridica==null && other.getIdNaturaGiuridica()==null) || 
             (this.idNaturaGiuridica!=null &&
              this.idNaturaGiuridica.equals(other.getIdNaturaGiuridica()))) &&
            ((this.dataInizioValidita==null && other.getDataInizioValidita()==null) || 
             (this.dataInizioValidita!=null &&
              this.dataInizioValidita.equals(other.getDataInizioValidita()))) &&
            ((this.dataFineValidita==null && other.getDataFineValidita()==null) || 
             (this.dataFineValidita!=null &&
              this.dataFineValidita.equals(other.getDataFineValidita()))) &&
            ((this.NRegistroImpreseCCIAA==null && other.getNRegistroImpreseCCIAA()==null) || 
             (this.NRegistroImpreseCCIAA!=null &&
              this.NRegistroImpreseCCIAA.equals(other.getNRegistroImpreseCCIAA()))) &&
            ((this.denominazione==null && other.getDenominazione()==null) || 
             (this.denominazione!=null &&
              this.denominazione.equals(other.getDenominazione()))) &&
            ((this.dataCostituzione==null && other.getDataCostituzione()==null) || 
             (this.dataCostituzione!=null &&
              this.dataCostituzione.equals(other.getDataCostituzione()))) &&
            ((this.dataCessazione==null && other.getDataCessazione()==null) || 
             (this.dataCessazione!=null &&
              this.dataCessazione.equals(other.getDataCessazione()))) &&
            ((this.tribunaleCCIAA==null && other.getTribunaleCCIAA()==null) || 
             (this.tribunaleCCIAA!=null &&
              this.tribunaleCCIAA.equals(other.getTribunaleCCIAA()))) &&
            ((this.listaVariazAnagrafica==null && other.getListaVariazAnagrafica()==null) || 
             (this.listaVariazAnagrafica!=null &&
              java.util.Arrays.equals(this.listaVariazAnagrafica, other.getListaVariazAnagrafica())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodici() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCodici());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCodici(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroCCIAA() != null) {
            _hashCode += getNumeroCCIAA().hashCode();
        }
        if (getDescrizioneNaturaGiuridica() != null) {
            _hashCode += getDescrizioneNaturaGiuridica().hashCode();
        }
        if (getCodiceCausaleCessazione() != null) {
            _hashCode += getCodiceCausaleCessazione().hashCode();
        }
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getPartitaIva() != null) {
            _hashCode += getPartitaIva().hashCode();
        }
        if (getDescrizioneTipoAzienda() != null) {
            _hashCode += getDescrizioneTipoAzienda().hashCode();
        }
        if (getDescrizioneStatoAttiv() != null) {
            _hashCode += getDescrizioneStatoAttiv().hashCode();
        }
        if (getProvinciaCCIAA() != null) {
            _hashCode += getProvinciaCCIAA().hashCode();
        }
        if (getIdTipoAzienda() != null) {
            _hashCode += getIdTipoAzienda().hashCode();
        }
        _hashCode += new Long(getIdAzienda()).hashCode();
        if (getIdStatoAttiv() != null) {
            _hashCode += getIdStatoAttiv().hashCode();
        }
        if (getFIscrSezArtigiani() != null) {
            _hashCode += getFIscrSezArtigiani().hashCode();
        }
        if (getDescrizioneCausaleCessazione() != null) {
            _hashCode += getDescrizioneCausaleCessazione().hashCode();
        }
        if (getDescrIscrSezArtigiani() != null) {
            _hashCode += getDescrIscrSezArtigiani().hashCode();
        }
        if (getDescrizioneFontedato() != null) {
            _hashCode += getDescrizioneFontedato().hashCode();
        }
        if (getAnnoCCIAA() != null) {
            _hashCode += getAnnoCCIAA().hashCode();
        }
        if (getOggettoSociale() != null) {
            _hashCode += getOggettoSociale().hashCode();
        }
        _hashCode += new Long(getIdFonteDato()).hashCode();
        if (getIdNaturaGiuridica() != null) {
            _hashCode += getIdNaturaGiuridica().hashCode();
        }
        if (getDataInizioValidita() != null) {
            _hashCode += getDataInizioValidita().hashCode();
        }
        if (getDataFineValidita() != null) {
            _hashCode += getDataFineValidita().hashCode();
        }
        if (getNRegistroImpreseCCIAA() != null) {
            _hashCode += getNRegistroImpreseCCIAA().hashCode();
        }
        if (getDenominazione() != null) {
            _hashCode += getDenominazione().hashCode();
        }
        if (getDataCostituzione() != null) {
            _hashCode += getDataCostituzione().hashCode();
        }
        if (getDataCessazione() != null) {
            _hashCode += getDataCessazione().hashCode();
        }
        if (getTribunaleCCIAA() != null) {
            _hashCode += getTribunaleCCIAA().hashCode();
        }
        if (getListaVariazAnagrafica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaVariazAnagrafica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaVariazAnagrafica(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AziendaVariazioneAnagrafica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaVariazioneAnagrafica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codici");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codici"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "CodiciATECO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numeroCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneNaturaGiuridica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneNaturaGiuridica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceCausaleCessazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceCausaleCessazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partitaIva");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "partitaIva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneTipoAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneTipoAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneStatoAttiv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneStatoAttiv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provinciaCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "provinciaCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idTipoAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idStatoAttiv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idStatoAttiv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FIscrSezArtigiani");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "FIscrSezArtigiani"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneCausaleCessazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneCausaleCessazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrIscrSezArtigiani");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrIscrSezArtigiani"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneFontedato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneFontedato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggettoSociale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "oggettoSociale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idFonteDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNaturaGiuridica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idNaturaGiuridica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataFineValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRegistroImpreseCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NRegistroImpreseCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denominazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "denominazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCostituzione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataCostituzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCessazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataCessazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tribunaleCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "tribunaleCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaVariazAnagrafica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaVariazAnagrafica"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "VariazioneAnagrafica"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
