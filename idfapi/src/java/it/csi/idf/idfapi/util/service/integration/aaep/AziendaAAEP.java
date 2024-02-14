/**
 * AziendaAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class AziendaAAEP  implements java.io.Serializable {
    private java.lang.String idNaturaGiuridica;

    private java.lang.String numeroCCIAA;

    private java.lang.String partitaIva;

    private java.lang.String NRegistroImpreseCCIAA;

    private java.lang.String ragioneSociale;

    private java.lang.String idFonteDato;

    private java.lang.String annoCCIAA;

    private it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP[] listaSediAAEP;

    private java.lang.String descrizioneFonteDato;

    private java.lang.String descrizioneStatoAttiv;

    private java.lang.String descrizioneCausaleCessazione;

    private java.lang.String provinciaCCIAA;

    private java.lang.String provinciaIscrAlboArtigiano;

    private java.lang.String dataDlbIscrAlboArtigiano;

    private java.lang.String codATECO91;

    private java.lang.String codiceCausaleCessazione;

    private java.lang.String numeroIscrAlboArtigiano;

    private java.lang.String descrizioneIterIscrAlboArt;

    private java.lang.String codATECO2007;

    private java.lang.String dataCostituzione;

    private java.lang.String idAAEPSedeSL;

    private java.lang.String descrATECO2007;

    private java.lang.String codiceFiscale;

    private java.lang.String codATECO2002;

    private java.lang.String descrATECO2002;

    private java.lang.String descrizioneNaturaGiuridica;

    private java.lang.String flgIterIscrAlboArt;

    private java.lang.String tribunaleCCIAA;

    private java.lang.String descrATECO91;

    private it.csi.idf.idfapi.util.service.integration.aaep.RappresentanteLegaleAAEP rappresentanteLegaleAAEP;

    private java.lang.String aziendaCessata;

    private java.lang.String dataInizioValSL;

    private java.lang.String oggettoSociale;

    private java.lang.String idAAEPAziendaSL;

    private java.lang.String idStatoAttiv;

    private java.lang.String dataCessazione;

    public AziendaAAEP() {
    }

    public AziendaAAEP(
           java.lang.String idNaturaGiuridica,
           java.lang.String numeroCCIAA,
           java.lang.String partitaIva,
           java.lang.String NRegistroImpreseCCIAA,
           java.lang.String ragioneSociale,
           java.lang.String idFonteDato,
           java.lang.String annoCCIAA,
           it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP[] listaSediAAEP,
           java.lang.String descrizioneFonteDato,
           java.lang.String descrizioneStatoAttiv,
           java.lang.String descrizioneCausaleCessazione,
           java.lang.String provinciaCCIAA,
           java.lang.String provinciaIscrAlboArtigiano,
           java.lang.String dataDlbIscrAlboArtigiano,
           java.lang.String codATECO91,
           java.lang.String codiceCausaleCessazione,
           java.lang.String numeroIscrAlboArtigiano,
           java.lang.String descrizioneIterIscrAlboArt,
           java.lang.String codATECO2007,
           java.lang.String dataCostituzione,
           java.lang.String idAAEPSedeSL,
           java.lang.String descrATECO2007,
           java.lang.String codiceFiscale,
           java.lang.String codATECO2002,
           java.lang.String descrATECO2002,
           java.lang.String descrizioneNaturaGiuridica,
           java.lang.String flgIterIscrAlboArt,
           java.lang.String tribunaleCCIAA,
           java.lang.String descrATECO91,
           it.csi.idf.idfapi.util.service.integration.aaep.RappresentanteLegaleAAEP rappresentanteLegaleAAEP,
           java.lang.String aziendaCessata,
           java.lang.String dataInizioValSL,
           java.lang.String oggettoSociale,
           java.lang.String idAAEPAziendaSL,
           java.lang.String idStatoAttiv,
           java.lang.String dataCessazione) {
           this.idNaturaGiuridica = idNaturaGiuridica;
           this.numeroCCIAA = numeroCCIAA;
           this.partitaIva = partitaIva;
           this.NRegistroImpreseCCIAA = NRegistroImpreseCCIAA;
           this.ragioneSociale = ragioneSociale;
           this.idFonteDato = idFonteDato;
           this.annoCCIAA = annoCCIAA;
           this.listaSediAAEP = listaSediAAEP;
           this.descrizioneFonteDato = descrizioneFonteDato;
           this.descrizioneStatoAttiv = descrizioneStatoAttiv;
           this.descrizioneCausaleCessazione = descrizioneCausaleCessazione;
           this.provinciaCCIAA = provinciaCCIAA;
           this.provinciaIscrAlboArtigiano = provinciaIscrAlboArtigiano;
           this.dataDlbIscrAlboArtigiano = dataDlbIscrAlboArtigiano;
           this.codATECO91 = codATECO91;
           this.codiceCausaleCessazione = codiceCausaleCessazione;
           this.numeroIscrAlboArtigiano = numeroIscrAlboArtigiano;
           this.descrizioneIterIscrAlboArt = descrizioneIterIscrAlboArt;
           this.codATECO2007 = codATECO2007;
           this.dataCostituzione = dataCostituzione;
           this.idAAEPSedeSL = idAAEPSedeSL;
           this.descrATECO2007 = descrATECO2007;
           this.codiceFiscale = codiceFiscale;
           this.codATECO2002 = codATECO2002;
           this.descrATECO2002 = descrATECO2002;
           this.descrizioneNaturaGiuridica = descrizioneNaturaGiuridica;
           this.flgIterIscrAlboArt = flgIterIscrAlboArt;
           this.tribunaleCCIAA = tribunaleCCIAA;
           this.descrATECO91 = descrATECO91;
           this.rappresentanteLegaleAAEP = rappresentanteLegaleAAEP;
           this.aziendaCessata = aziendaCessata;
           this.dataInizioValSL = dataInizioValSL;
           this.oggettoSociale = oggettoSociale;
           this.idAAEPAziendaSL = idAAEPAziendaSL;
           this.idStatoAttiv = idStatoAttiv;
           this.dataCessazione = dataCessazione;
    }


    /**
     * Gets the idNaturaGiuridica value for this AziendaAAEP.
     * 
     * @return idNaturaGiuridica
     */
    public java.lang.String getIdNaturaGiuridica() {
        return idNaturaGiuridica;
    }


    /**
     * Sets the idNaturaGiuridica value for this AziendaAAEP.
     * 
     * @param idNaturaGiuridica
     */
    public void setIdNaturaGiuridica(java.lang.String idNaturaGiuridica) {
        this.idNaturaGiuridica = idNaturaGiuridica;
    }


    /**
     * Gets the numeroCCIAA value for this AziendaAAEP.
     * 
     * @return numeroCCIAA
     */
    public java.lang.String getNumeroCCIAA() {
        return numeroCCIAA;
    }


    /**
     * Sets the numeroCCIAA value for this AziendaAAEP.
     * 
     * @param numeroCCIAA
     */
    public void setNumeroCCIAA(java.lang.String numeroCCIAA) {
        this.numeroCCIAA = numeroCCIAA;
    }


    /**
     * Gets the partitaIva value for this AziendaAAEP.
     * 
     * @return partitaIva
     */
    public java.lang.String getPartitaIva() {
        return partitaIva;
    }


    /**
     * Sets the partitaIva value for this AziendaAAEP.
     * 
     * @param partitaIva
     */
    public void setPartitaIva(java.lang.String partitaIva) {
        this.partitaIva = partitaIva;
    }


    /**
     * Gets the NRegistroImpreseCCIAA value for this AziendaAAEP.
     * 
     * @return NRegistroImpreseCCIAA
     */
    public java.lang.String getNRegistroImpreseCCIAA() {
        return NRegistroImpreseCCIAA;
    }


    /**
     * Sets the NRegistroImpreseCCIAA value for this AziendaAAEP.
     * 
     * @param NRegistroImpreseCCIAA
     */
    public void setNRegistroImpreseCCIAA(java.lang.String NRegistroImpreseCCIAA) {
        this.NRegistroImpreseCCIAA = NRegistroImpreseCCIAA;
    }


    /**
     * Gets the ragioneSociale value for this AziendaAAEP.
     * 
     * @return ragioneSociale
     */
    public java.lang.String getRagioneSociale() {
        return ragioneSociale;
    }


    /**
     * Sets the ragioneSociale value for this AziendaAAEP.
     * 
     * @param ragioneSociale
     */
    public void setRagioneSociale(java.lang.String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }


    /**
     * Gets the idFonteDato value for this AziendaAAEP.
     * 
     * @return idFonteDato
     */
    public java.lang.String getIdFonteDato() {
        return idFonteDato;
    }


    /**
     * Sets the idFonteDato value for this AziendaAAEP.
     * 
     * @param idFonteDato
     */
    public void setIdFonteDato(java.lang.String idFonteDato) {
        this.idFonteDato = idFonteDato;
    }


    /**
     * Gets the annoCCIAA value for this AziendaAAEP.
     * 
     * @return annoCCIAA
     */
    public java.lang.String getAnnoCCIAA() {
        return annoCCIAA;
    }


    /**
     * Sets the annoCCIAA value for this AziendaAAEP.
     * 
     * @param annoCCIAA
     */
    public void setAnnoCCIAA(java.lang.String annoCCIAA) {
        this.annoCCIAA = annoCCIAA;
    }


    /**
     * Gets the listaSediAAEP value for this AziendaAAEP.
     * 
     * @return listaSediAAEP
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP[] getListaSediAAEP() {
        return listaSediAAEP;
    }


    /**
     * Sets the listaSediAAEP value for this AziendaAAEP.
     * 
     * @param listaSediAAEP
     */
    public void setListaSediAAEP(it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP[] listaSediAAEP) {
        this.listaSediAAEP = listaSediAAEP;
    }


    /**
     * Gets the descrizioneFonteDato value for this AziendaAAEP.
     * 
     * @return descrizioneFonteDato
     */
    public java.lang.String getDescrizioneFonteDato() {
        return descrizioneFonteDato;
    }


    /**
     * Sets the descrizioneFonteDato value for this AziendaAAEP.
     * 
     * @param descrizioneFonteDato
     */
    public void setDescrizioneFonteDato(java.lang.String descrizioneFonteDato) {
        this.descrizioneFonteDato = descrizioneFonteDato;
    }


    /**
     * Gets the descrizioneStatoAttiv value for this AziendaAAEP.
     * 
     * @return descrizioneStatoAttiv
     */
    public java.lang.String getDescrizioneStatoAttiv() {
        return descrizioneStatoAttiv;
    }


    /**
     * Sets the descrizioneStatoAttiv value for this AziendaAAEP.
     * 
     * @param descrizioneStatoAttiv
     */
    public void setDescrizioneStatoAttiv(java.lang.String descrizioneStatoAttiv) {
        this.descrizioneStatoAttiv = descrizioneStatoAttiv;
    }


    /**
     * Gets the descrizioneCausaleCessazione value for this AziendaAAEP.
     * 
     * @return descrizioneCausaleCessazione
     */
    public java.lang.String getDescrizioneCausaleCessazione() {
        return descrizioneCausaleCessazione;
    }


    /**
     * Sets the descrizioneCausaleCessazione value for this AziendaAAEP.
     * 
     * @param descrizioneCausaleCessazione
     */
    public void setDescrizioneCausaleCessazione(java.lang.String descrizioneCausaleCessazione) {
        this.descrizioneCausaleCessazione = descrizioneCausaleCessazione;
    }


    /**
     * Gets the provinciaCCIAA value for this AziendaAAEP.
     * 
     * @return provinciaCCIAA
     */
    public java.lang.String getProvinciaCCIAA() {
        return provinciaCCIAA;
    }


    /**
     * Sets the provinciaCCIAA value for this AziendaAAEP.
     * 
     * @param provinciaCCIAA
     */
    public void setProvinciaCCIAA(java.lang.String provinciaCCIAA) {
        this.provinciaCCIAA = provinciaCCIAA;
    }


    /**
     * Gets the provinciaIscrAlboArtigiano value for this AziendaAAEP.
     * 
     * @return provinciaIscrAlboArtigiano
     */
    public java.lang.String getProvinciaIscrAlboArtigiano() {
        return provinciaIscrAlboArtigiano;
    }


    /**
     * Sets the provinciaIscrAlboArtigiano value for this AziendaAAEP.
     * 
     * @param provinciaIscrAlboArtigiano
     */
    public void setProvinciaIscrAlboArtigiano(java.lang.String provinciaIscrAlboArtigiano) {
        this.provinciaIscrAlboArtigiano = provinciaIscrAlboArtigiano;
    }


    /**
     * Gets the dataDlbIscrAlboArtigiano value for this AziendaAAEP.
     * 
     * @return dataDlbIscrAlboArtigiano
     */
    public java.lang.String getDataDlbIscrAlboArtigiano() {
        return dataDlbIscrAlboArtigiano;
    }


    /**
     * Sets the dataDlbIscrAlboArtigiano value for this AziendaAAEP.
     * 
     * @param dataDlbIscrAlboArtigiano
     */
    public void setDataDlbIscrAlboArtigiano(java.lang.String dataDlbIscrAlboArtigiano) {
        this.dataDlbIscrAlboArtigiano = dataDlbIscrAlboArtigiano;
    }


    /**
     * Gets the codATECO91 value for this AziendaAAEP.
     * 
     * @return codATECO91
     */
    public java.lang.String getCodATECO91() {
        return codATECO91;
    }


    /**
     * Sets the codATECO91 value for this AziendaAAEP.
     * 
     * @param codATECO91
     */
    public void setCodATECO91(java.lang.String codATECO91) {
        this.codATECO91 = codATECO91;
    }


    /**
     * Gets the codiceCausaleCessazione value for this AziendaAAEP.
     * 
     * @return codiceCausaleCessazione
     */
    public java.lang.String getCodiceCausaleCessazione() {
        return codiceCausaleCessazione;
    }


    /**
     * Sets the codiceCausaleCessazione value for this AziendaAAEP.
     * 
     * @param codiceCausaleCessazione
     */
    public void setCodiceCausaleCessazione(java.lang.String codiceCausaleCessazione) {
        this.codiceCausaleCessazione = codiceCausaleCessazione;
    }


    /**
     * Gets the numeroIscrAlboArtigiano value for this AziendaAAEP.
     * 
     * @return numeroIscrAlboArtigiano
     */
    public java.lang.String getNumeroIscrAlboArtigiano() {
        return numeroIscrAlboArtigiano;
    }


    /**
     * Sets the numeroIscrAlboArtigiano value for this AziendaAAEP.
     * 
     * @param numeroIscrAlboArtigiano
     */
    public void setNumeroIscrAlboArtigiano(java.lang.String numeroIscrAlboArtigiano) {
        this.numeroIscrAlboArtigiano = numeroIscrAlboArtigiano;
    }


    /**
     * Gets the descrizioneIterIscrAlboArt value for this AziendaAAEP.
     * 
     * @return descrizioneIterIscrAlboArt
     */
    public java.lang.String getDescrizioneIterIscrAlboArt() {
        return descrizioneIterIscrAlboArt;
    }


    /**
     * Sets the descrizioneIterIscrAlboArt value for this AziendaAAEP.
     * 
     * @param descrizioneIterIscrAlboArt
     */
    public void setDescrizioneIterIscrAlboArt(java.lang.String descrizioneIterIscrAlboArt) {
        this.descrizioneIterIscrAlboArt = descrizioneIterIscrAlboArt;
    }


    /**
     * Gets the codATECO2007 value for this AziendaAAEP.
     * 
     * @return codATECO2007
     */
    public java.lang.String getCodATECO2007() {
        return codATECO2007;
    }


    /**
     * Sets the codATECO2007 value for this AziendaAAEP.
     * 
     * @param codATECO2007
     */
    public void setCodATECO2007(java.lang.String codATECO2007) {
        this.codATECO2007 = codATECO2007;
    }


    /**
     * Gets the dataCostituzione value for this AziendaAAEP.
     * 
     * @return dataCostituzione
     */
    public java.lang.String getDataCostituzione() {
        return dataCostituzione;
    }


    /**
     * Sets the dataCostituzione value for this AziendaAAEP.
     * 
     * @param dataCostituzione
     */
    public void setDataCostituzione(java.lang.String dataCostituzione) {
        this.dataCostituzione = dataCostituzione;
    }


    /**
     * Gets the idAAEPSedeSL value for this AziendaAAEP.
     * 
     * @return idAAEPSedeSL
     */
    public java.lang.String getIdAAEPSedeSL() {
        return idAAEPSedeSL;
    }


    /**
     * Sets the idAAEPSedeSL value for this AziendaAAEP.
     * 
     * @param idAAEPSedeSL
     */
    public void setIdAAEPSedeSL(java.lang.String idAAEPSedeSL) {
        this.idAAEPSedeSL = idAAEPSedeSL;
    }


    /**
     * Gets the descrATECO2007 value for this AziendaAAEP.
     * 
     * @return descrATECO2007
     */
    public java.lang.String getDescrATECO2007() {
        return descrATECO2007;
    }


    /**
     * Sets the descrATECO2007 value for this AziendaAAEP.
     * 
     * @param descrATECO2007
     */
    public void setDescrATECO2007(java.lang.String descrATECO2007) {
        this.descrATECO2007 = descrATECO2007;
    }


    /**
     * Gets the codiceFiscale value for this AziendaAAEP.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this AziendaAAEP.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the codATECO2002 value for this AziendaAAEP.
     * 
     * @return codATECO2002
     */
    public java.lang.String getCodATECO2002() {
        return codATECO2002;
    }


    /**
     * Sets the codATECO2002 value for this AziendaAAEP.
     * 
     * @param codATECO2002
     */
    public void setCodATECO2002(java.lang.String codATECO2002) {
        this.codATECO2002 = codATECO2002;
    }


    /**
     * Gets the descrATECO2002 value for this AziendaAAEP.
     * 
     * @return descrATECO2002
     */
    public java.lang.String getDescrATECO2002() {
        return descrATECO2002;
    }


    /**
     * Sets the descrATECO2002 value for this AziendaAAEP.
     * 
     * @param descrATECO2002
     */
    public void setDescrATECO2002(java.lang.String descrATECO2002) {
        this.descrATECO2002 = descrATECO2002;
    }


    /**
     * Gets the descrizioneNaturaGiuridica value for this AziendaAAEP.
     * 
     * @return descrizioneNaturaGiuridica
     */
    public java.lang.String getDescrizioneNaturaGiuridica() {
        return descrizioneNaturaGiuridica;
    }


    /**
     * Sets the descrizioneNaturaGiuridica value for this AziendaAAEP.
     * 
     * @param descrizioneNaturaGiuridica
     */
    public void setDescrizioneNaturaGiuridica(java.lang.String descrizioneNaturaGiuridica) {
        this.descrizioneNaturaGiuridica = descrizioneNaturaGiuridica;
    }


    /**
     * Gets the flgIterIscrAlboArt value for this AziendaAAEP.
     * 
     * @return flgIterIscrAlboArt
     */
    public java.lang.String getFlgIterIscrAlboArt() {
        return flgIterIscrAlboArt;
    }


    /**
     * Sets the flgIterIscrAlboArt value for this AziendaAAEP.
     * 
     * @param flgIterIscrAlboArt
     */
    public void setFlgIterIscrAlboArt(java.lang.String flgIterIscrAlboArt) {
        this.flgIterIscrAlboArt = flgIterIscrAlboArt;
    }


    /**
     * Gets the tribunaleCCIAA value for this AziendaAAEP.
     * 
     * @return tribunaleCCIAA
     */
    public java.lang.String getTribunaleCCIAA() {
        return tribunaleCCIAA;
    }


    /**
     * Sets the tribunaleCCIAA value for this AziendaAAEP.
     * 
     * @param tribunaleCCIAA
     */
    public void setTribunaleCCIAA(java.lang.String tribunaleCCIAA) {
        this.tribunaleCCIAA = tribunaleCCIAA;
    }


    /**
     * Gets the descrATECO91 value for this AziendaAAEP.
     * 
     * @return descrATECO91
     */
    public java.lang.String getDescrATECO91() {
        return descrATECO91;
    }


    /**
     * Sets the descrATECO91 value for this AziendaAAEP.
     * 
     * @param descrATECO91
     */
    public void setDescrATECO91(java.lang.String descrATECO91) {
        this.descrATECO91 = descrATECO91;
    }


    /**
     * Gets the rappresentanteLegaleAAEP value for this AziendaAAEP.
     * 
     * @return rappresentanteLegaleAAEP
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.RappresentanteLegaleAAEP getRappresentanteLegaleAAEP() {
        return rappresentanteLegaleAAEP;
    }


    /**
     * Sets the rappresentanteLegaleAAEP value for this AziendaAAEP.
     * 
     * @param rappresentanteLegaleAAEP
     */
    public void setRappresentanteLegaleAAEP(it.csi.idf.idfapi.util.service.integration.aaep.RappresentanteLegaleAAEP rappresentanteLegaleAAEP) {
        this.rappresentanteLegaleAAEP = rappresentanteLegaleAAEP;
    }


    /**
     * Gets the aziendaCessata value for this AziendaAAEP.
     * 
     * @return aziendaCessata
     */
    public java.lang.String getAziendaCessata() {
        return aziendaCessata;
    }


    /**
     * Sets the aziendaCessata value for this AziendaAAEP.
     * 
     * @param aziendaCessata
     */
    public void setAziendaCessata(java.lang.String aziendaCessata) {
        this.aziendaCessata = aziendaCessata;
    }


    /**
     * Gets the dataInizioValSL value for this AziendaAAEP.
     * 
     * @return dataInizioValSL
     */
    public java.lang.String getDataInizioValSL() {
        return dataInizioValSL;
    }


    /**
     * Sets the dataInizioValSL value for this AziendaAAEP.
     * 
     * @param dataInizioValSL
     */
    public void setDataInizioValSL(java.lang.String dataInizioValSL) {
        this.dataInizioValSL = dataInizioValSL;
    }


    /**
     * Gets the oggettoSociale value for this AziendaAAEP.
     * 
     * @return oggettoSociale
     */
    public java.lang.String getOggettoSociale() {
        return oggettoSociale;
    }


    /**
     * Sets the oggettoSociale value for this AziendaAAEP.
     * 
     * @param oggettoSociale
     */
    public void setOggettoSociale(java.lang.String oggettoSociale) {
        this.oggettoSociale = oggettoSociale;
    }


    /**
     * Gets the idAAEPAziendaSL value for this AziendaAAEP.
     * 
     * @return idAAEPAziendaSL
     */
    public java.lang.String getIdAAEPAziendaSL() {
        return idAAEPAziendaSL;
    }


    /**
     * Sets the idAAEPAziendaSL value for this AziendaAAEP.
     * 
     * @param idAAEPAziendaSL
     */
    public void setIdAAEPAziendaSL(java.lang.String idAAEPAziendaSL) {
        this.idAAEPAziendaSL = idAAEPAziendaSL;
    }


    /**
     * Gets the idStatoAttiv value for this AziendaAAEP.
     * 
     * @return idStatoAttiv
     */
    public java.lang.String getIdStatoAttiv() {
        return idStatoAttiv;
    }


    /**
     * Sets the idStatoAttiv value for this AziendaAAEP.
     * 
     * @param idStatoAttiv
     */
    public void setIdStatoAttiv(java.lang.String idStatoAttiv) {
        this.idStatoAttiv = idStatoAttiv;
    }


    /**
     * Gets the dataCessazione value for this AziendaAAEP.
     * 
     * @return dataCessazione
     */
    public java.lang.String getDataCessazione() {
        return dataCessazione;
    }


    /**
     * Sets the dataCessazione value for this AziendaAAEP.
     * 
     * @param dataCessazione
     */
    public void setDataCessazione(java.lang.String dataCessazione) {
        this.dataCessazione = dataCessazione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AziendaAAEP)) return false;
        AziendaAAEP other = (AziendaAAEP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idNaturaGiuridica==null && other.getIdNaturaGiuridica()==null) || 
             (this.idNaturaGiuridica!=null &&
              this.idNaturaGiuridica.equals(other.getIdNaturaGiuridica()))) &&
            ((this.numeroCCIAA==null && other.getNumeroCCIAA()==null) || 
             (this.numeroCCIAA!=null &&
              this.numeroCCIAA.equals(other.getNumeroCCIAA()))) &&
            ((this.partitaIva==null && other.getPartitaIva()==null) || 
             (this.partitaIva!=null &&
              this.partitaIva.equals(other.getPartitaIva()))) &&
            ((this.NRegistroImpreseCCIAA==null && other.getNRegistroImpreseCCIAA()==null) || 
             (this.NRegistroImpreseCCIAA!=null &&
              this.NRegistroImpreseCCIAA.equals(other.getNRegistroImpreseCCIAA()))) &&
            ((this.ragioneSociale==null && other.getRagioneSociale()==null) || 
             (this.ragioneSociale!=null &&
              this.ragioneSociale.equals(other.getRagioneSociale()))) &&
            ((this.idFonteDato==null && other.getIdFonteDato()==null) || 
             (this.idFonteDato!=null &&
              this.idFonteDato.equals(other.getIdFonteDato()))) &&
            ((this.annoCCIAA==null && other.getAnnoCCIAA()==null) || 
             (this.annoCCIAA!=null &&
              this.annoCCIAA.equals(other.getAnnoCCIAA()))) &&
            ((this.listaSediAAEP==null && other.getListaSediAAEP()==null) || 
             (this.listaSediAAEP!=null &&
              java.util.Arrays.equals(this.listaSediAAEP, other.getListaSediAAEP()))) &&
            ((this.descrizioneFonteDato==null && other.getDescrizioneFonteDato()==null) || 
             (this.descrizioneFonteDato!=null &&
              this.descrizioneFonteDato.equals(other.getDescrizioneFonteDato()))) &&
            ((this.descrizioneStatoAttiv==null && other.getDescrizioneStatoAttiv()==null) || 
             (this.descrizioneStatoAttiv!=null &&
              this.descrizioneStatoAttiv.equals(other.getDescrizioneStatoAttiv()))) &&
            ((this.descrizioneCausaleCessazione==null && other.getDescrizioneCausaleCessazione()==null) || 
             (this.descrizioneCausaleCessazione!=null &&
              this.descrizioneCausaleCessazione.equals(other.getDescrizioneCausaleCessazione()))) &&
            ((this.provinciaCCIAA==null && other.getProvinciaCCIAA()==null) || 
             (this.provinciaCCIAA!=null &&
              this.provinciaCCIAA.equals(other.getProvinciaCCIAA()))) &&
            ((this.provinciaIscrAlboArtigiano==null && other.getProvinciaIscrAlboArtigiano()==null) || 
             (this.provinciaIscrAlboArtigiano!=null &&
              this.provinciaIscrAlboArtigiano.equals(other.getProvinciaIscrAlboArtigiano()))) &&
            ((this.dataDlbIscrAlboArtigiano==null && other.getDataDlbIscrAlboArtigiano()==null) || 
             (this.dataDlbIscrAlboArtigiano!=null &&
              this.dataDlbIscrAlboArtigiano.equals(other.getDataDlbIscrAlboArtigiano()))) &&
            ((this.codATECO91==null && other.getCodATECO91()==null) || 
             (this.codATECO91!=null &&
              this.codATECO91.equals(other.getCodATECO91()))) &&
            ((this.codiceCausaleCessazione==null && other.getCodiceCausaleCessazione()==null) || 
             (this.codiceCausaleCessazione!=null &&
              this.codiceCausaleCessazione.equals(other.getCodiceCausaleCessazione()))) &&
            ((this.numeroIscrAlboArtigiano==null && other.getNumeroIscrAlboArtigiano()==null) || 
             (this.numeroIscrAlboArtigiano!=null &&
              this.numeroIscrAlboArtigiano.equals(other.getNumeroIscrAlboArtigiano()))) &&
            ((this.descrizioneIterIscrAlboArt==null && other.getDescrizioneIterIscrAlboArt()==null) || 
             (this.descrizioneIterIscrAlboArt!=null &&
              this.descrizioneIterIscrAlboArt.equals(other.getDescrizioneIterIscrAlboArt()))) &&
            ((this.codATECO2007==null && other.getCodATECO2007()==null) || 
             (this.codATECO2007!=null &&
              this.codATECO2007.equals(other.getCodATECO2007()))) &&
            ((this.dataCostituzione==null && other.getDataCostituzione()==null) || 
             (this.dataCostituzione!=null &&
              this.dataCostituzione.equals(other.getDataCostituzione()))) &&
            ((this.idAAEPSedeSL==null && other.getIdAAEPSedeSL()==null) || 
             (this.idAAEPSedeSL!=null &&
              this.idAAEPSedeSL.equals(other.getIdAAEPSedeSL()))) &&
            ((this.descrATECO2007==null && other.getDescrATECO2007()==null) || 
             (this.descrATECO2007!=null &&
              this.descrATECO2007.equals(other.getDescrATECO2007()))) &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.codATECO2002==null && other.getCodATECO2002()==null) || 
             (this.codATECO2002!=null &&
              this.codATECO2002.equals(other.getCodATECO2002()))) &&
            ((this.descrATECO2002==null && other.getDescrATECO2002()==null) || 
             (this.descrATECO2002!=null &&
              this.descrATECO2002.equals(other.getDescrATECO2002()))) &&
            ((this.descrizioneNaturaGiuridica==null && other.getDescrizioneNaturaGiuridica()==null) || 
             (this.descrizioneNaturaGiuridica!=null &&
              this.descrizioneNaturaGiuridica.equals(other.getDescrizioneNaturaGiuridica()))) &&
            ((this.flgIterIscrAlboArt==null && other.getFlgIterIscrAlboArt()==null) || 
             (this.flgIterIscrAlboArt!=null &&
              this.flgIterIscrAlboArt.equals(other.getFlgIterIscrAlboArt()))) &&
            ((this.tribunaleCCIAA==null && other.getTribunaleCCIAA()==null) || 
             (this.tribunaleCCIAA!=null &&
              this.tribunaleCCIAA.equals(other.getTribunaleCCIAA()))) &&
            ((this.descrATECO91==null && other.getDescrATECO91()==null) || 
             (this.descrATECO91!=null &&
              this.descrATECO91.equals(other.getDescrATECO91()))) &&
            ((this.rappresentanteLegaleAAEP==null && other.getRappresentanteLegaleAAEP()==null) || 
             (this.rappresentanteLegaleAAEP!=null &&
              this.rappresentanteLegaleAAEP.equals(other.getRappresentanteLegaleAAEP()))) &&
            ((this.aziendaCessata==null && other.getAziendaCessata()==null) || 
             (this.aziendaCessata!=null &&
              this.aziendaCessata.equals(other.getAziendaCessata()))) &&
            ((this.dataInizioValSL==null && other.getDataInizioValSL()==null) || 
             (this.dataInizioValSL!=null &&
              this.dataInizioValSL.equals(other.getDataInizioValSL()))) &&
            ((this.oggettoSociale==null && other.getOggettoSociale()==null) || 
             (this.oggettoSociale!=null &&
              this.oggettoSociale.equals(other.getOggettoSociale()))) &&
            ((this.idAAEPAziendaSL==null && other.getIdAAEPAziendaSL()==null) || 
             (this.idAAEPAziendaSL!=null &&
              this.idAAEPAziendaSL.equals(other.getIdAAEPAziendaSL()))) &&
            ((this.idStatoAttiv==null && other.getIdStatoAttiv()==null) || 
             (this.idStatoAttiv!=null &&
              this.idStatoAttiv.equals(other.getIdStatoAttiv()))) &&
            ((this.dataCessazione==null && other.getDataCessazione()==null) || 
             (this.dataCessazione!=null &&
              this.dataCessazione.equals(other.getDataCessazione())));
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
        if (getIdNaturaGiuridica() != null) {
            _hashCode += getIdNaturaGiuridica().hashCode();
        }
        if (getNumeroCCIAA() != null) {
            _hashCode += getNumeroCCIAA().hashCode();
        }
        if (getPartitaIva() != null) {
            _hashCode += getPartitaIva().hashCode();
        }
        if (getNRegistroImpreseCCIAA() != null) {
            _hashCode += getNRegistroImpreseCCIAA().hashCode();
        }
        if (getRagioneSociale() != null) {
            _hashCode += getRagioneSociale().hashCode();
        }
        if (getIdFonteDato() != null) {
            _hashCode += getIdFonteDato().hashCode();
        }
        if (getAnnoCCIAA() != null) {
            _hashCode += getAnnoCCIAA().hashCode();
        }
        if (getListaSediAAEP() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSediAAEP());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSediAAEP(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescrizioneFonteDato() != null) {
            _hashCode += getDescrizioneFonteDato().hashCode();
        }
        if (getDescrizioneStatoAttiv() != null) {
            _hashCode += getDescrizioneStatoAttiv().hashCode();
        }
        if (getDescrizioneCausaleCessazione() != null) {
            _hashCode += getDescrizioneCausaleCessazione().hashCode();
        }
        if (getProvinciaCCIAA() != null) {
            _hashCode += getProvinciaCCIAA().hashCode();
        }
        if (getProvinciaIscrAlboArtigiano() != null) {
            _hashCode += getProvinciaIscrAlboArtigiano().hashCode();
        }
        if (getDataDlbIscrAlboArtigiano() != null) {
            _hashCode += getDataDlbIscrAlboArtigiano().hashCode();
        }
        if (getCodATECO91() != null) {
            _hashCode += getCodATECO91().hashCode();
        }
        if (getCodiceCausaleCessazione() != null) {
            _hashCode += getCodiceCausaleCessazione().hashCode();
        }
        if (getNumeroIscrAlboArtigiano() != null) {
            _hashCode += getNumeroIscrAlboArtigiano().hashCode();
        }
        if (getDescrizioneIterIscrAlboArt() != null) {
            _hashCode += getDescrizioneIterIscrAlboArt().hashCode();
        }
        if (getCodATECO2007() != null) {
            _hashCode += getCodATECO2007().hashCode();
        }
        if (getDataCostituzione() != null) {
            _hashCode += getDataCostituzione().hashCode();
        }
        if (getIdAAEPSedeSL() != null) {
            _hashCode += getIdAAEPSedeSL().hashCode();
        }
        if (getDescrATECO2007() != null) {
            _hashCode += getDescrATECO2007().hashCode();
        }
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getCodATECO2002() != null) {
            _hashCode += getCodATECO2002().hashCode();
        }
        if (getDescrATECO2002() != null) {
            _hashCode += getDescrATECO2002().hashCode();
        }
        if (getDescrizioneNaturaGiuridica() != null) {
            _hashCode += getDescrizioneNaturaGiuridica().hashCode();
        }
        if (getFlgIterIscrAlboArt() != null) {
            _hashCode += getFlgIterIscrAlboArt().hashCode();
        }
        if (getTribunaleCCIAA() != null) {
            _hashCode += getTribunaleCCIAA().hashCode();
        }
        if (getDescrATECO91() != null) {
            _hashCode += getDescrATECO91().hashCode();
        }
        if (getRappresentanteLegaleAAEP() != null) {
            _hashCode += getRappresentanteLegaleAAEP().hashCode();
        }
        if (getAziendaCessata() != null) {
            _hashCode += getAziendaCessata().hashCode();
        }
        if (getDataInizioValSL() != null) {
            _hashCode += getDataInizioValSL().hashCode();
        }
        if (getOggettoSociale() != null) {
            _hashCode += getOggettoSociale().hashCode();
        }
        if (getIdAAEPAziendaSL() != null) {
            _hashCode += getIdAAEPAziendaSL().hashCode();
        }
        if (getIdStatoAttiv() != null) {
            _hashCode += getIdStatoAttiv().hashCode();
        }
        if (getDataCessazione() != null) {
            _hashCode += getDataCessazione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AziendaAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idNaturaGiuridica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idNaturaGiuridica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numeroCCIAA"));
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
        elemField.setFieldName("NRegistroImpreseCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NRegistroImpreseCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ragioneSociale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "ragioneSociale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idFonteDato"));
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
        elemField.setFieldName("listaSediAAEP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaSediAAEP"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediAAEP"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneFonteDato"));
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
        elemField.setFieldName("descrizioneCausaleCessazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneCausaleCessazione"));
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
        elemField.setFieldName("provinciaIscrAlboArtigiano");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "provinciaIscrAlboArtigiano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDlbIscrAlboArtigiano");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataDlbIscrAlboArtigiano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codATECO91");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codATECO91"));
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
        elemField.setFieldName("numeroIscrAlboArtigiano");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numeroIscrAlboArtigiano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneIterIscrAlboArt");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneIterIscrAlboArt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codATECO2007");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codATECO2007"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCostituzione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataCostituzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPSedeSL");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPSedeSL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrATECO2007");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrATECO2007"));
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
        elemField.setFieldName("codATECO2002");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codATECO2002"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrATECO2002");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrATECO2002"));
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
        elemField.setFieldName("flgIterIscrAlboArt");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgIterIscrAlboArt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tribunaleCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "tribunaleCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrATECO91");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrATECO91"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rappresentanteLegaleAAEP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "rappresentanteLegaleAAEP"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "RappresentanteLegaleAAEP"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aziendaCessata");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "aziendaCessata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioValSL");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioValSL"));
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
        elemField.setFieldName("idAAEPAziendaSL");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPAziendaSL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idStatoAttiv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idStatoAttiv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCessazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataCessazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
