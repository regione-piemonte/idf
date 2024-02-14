/**
 * ProspDisabSILP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ProspDisabSILP  implements java.io.Serializable {
    private java.lang.String dataRichiestaGradualita;

    private java.lang.String dataRichiestaEsonero;

    private java.lang.String numProvvRichiestaGradualita;

    private java.lang.String dataRichiestaCompensaz;

    private java.lang.String dataPrimaAssunzione;

    private java.lang.String dataUltAggiornam;

    private java.lang.String dataConcessioneEsonero;

    private java.lang.String idTipoProspDisab;

    private java.lang.String numProvvConcessioneCompensaz;

    private java.lang.String numProvvConcessioneSospensione;

    private java.lang.String flgRichiestaEsonero;

    private java.lang.String NConvenzioni;

    private java.lang.String flgRichiestaSospensione;

    private java.lang.String flgStipulaConvenzione;

    private java.lang.String numProvvConcessioneGradualita;

    private java.lang.String dataInizioSospensione;

    private java.lang.String dataClassificazione;

    private java.lang.String dataSecondaAssunzione;

    private java.lang.String NLavoratoriGradualita;

    private java.lang.String provCompensaz;

    private java.lang.String idSede;

    private java.lang.String flgRichiestaGradualita;

    private java.lang.String numProvvRichiestaEsonero;

    private java.lang.String flgConcessioneCompensaz;

    private java.lang.String percConcessioneEsonero;

    private java.lang.String flgConcessioneSospensione;

    private java.lang.String dataRichiestaSospensione;

    private java.lang.String descrTipoProspDisab;

    private java.lang.String dataInvio;

    private java.lang.String dataFineSospensione;

    private java.lang.String descrMotivoSospensione;

    private it.csi.idf.idfapi.util.service.integration.aaep.OrganicoProspDisabSILP[] organicoProspDisabSILP;

    private java.lang.String idProspDisab;

    private java.lang.String idMotivoSospensione;

    private java.lang.String flgRichiestaCompensaz;

    private java.lang.String percConcessioneGradualita;

    private java.lang.String percRichiestaEsonero;

    private java.lang.String dataConcessioneSospensione;

    private java.lang.String numClassificazione;

    private java.lang.String numProtocollo;

    private java.lang.String dataProtocollo;

    private java.lang.String annoRiferimento;

    private java.lang.String numProvvRichiestaCompensaz;

    private java.lang.String flgConcessioneEsonero;

    private java.lang.String siglaProv;

    private java.lang.String dataConcessioneCompensaz;

    private java.lang.String controparte;

    private java.lang.String numProvvConcessioneEsonero;

    private java.lang.String dataConcessioneGradualita;

    private java.lang.String dataUltAggiornamSILP;

    private java.lang.String numProvvRichiestaSospensione;

    private java.lang.String flgConcessioneGradualita;

    public ProspDisabSILP() {
    }

    public ProspDisabSILP(
           java.lang.String dataRichiestaGradualita,
           java.lang.String dataRichiestaEsonero,
           java.lang.String numProvvRichiestaGradualita,
           java.lang.String dataRichiestaCompensaz,
           java.lang.String dataPrimaAssunzione,
           java.lang.String dataUltAggiornam,
           java.lang.String dataConcessioneEsonero,
           java.lang.String idTipoProspDisab,
           java.lang.String numProvvConcessioneCompensaz,
           java.lang.String numProvvConcessioneSospensione,
           java.lang.String flgRichiestaEsonero,
           java.lang.String NConvenzioni,
           java.lang.String flgRichiestaSospensione,
           java.lang.String flgStipulaConvenzione,
           java.lang.String numProvvConcessioneGradualita,
           java.lang.String dataInizioSospensione,
           java.lang.String dataClassificazione,
           java.lang.String dataSecondaAssunzione,
           java.lang.String NLavoratoriGradualita,
           java.lang.String provCompensaz,
           java.lang.String idSede,
           java.lang.String flgRichiestaGradualita,
           java.lang.String numProvvRichiestaEsonero,
           java.lang.String flgConcessioneCompensaz,
           java.lang.String percConcessioneEsonero,
           java.lang.String flgConcessioneSospensione,
           java.lang.String dataRichiestaSospensione,
           java.lang.String descrTipoProspDisab,
           java.lang.String dataInvio,
           java.lang.String dataFineSospensione,
           java.lang.String descrMotivoSospensione,
           it.csi.idf.idfapi.util.service.integration.aaep.OrganicoProspDisabSILP[] organicoProspDisabSILP,
           java.lang.String idProspDisab,
           java.lang.String idMotivoSospensione,
           java.lang.String flgRichiestaCompensaz,
           java.lang.String percConcessioneGradualita,
           java.lang.String percRichiestaEsonero,
           java.lang.String dataConcessioneSospensione,
           java.lang.String numClassificazione,
           java.lang.String numProtocollo,
           java.lang.String dataProtocollo,
           java.lang.String annoRiferimento,
           java.lang.String numProvvRichiestaCompensaz,
           java.lang.String flgConcessioneEsonero,
           java.lang.String siglaProv,
           java.lang.String dataConcessioneCompensaz,
           java.lang.String controparte,
           java.lang.String numProvvConcessioneEsonero,
           java.lang.String dataConcessioneGradualita,
           java.lang.String dataUltAggiornamSILP,
           java.lang.String numProvvRichiestaSospensione,
           java.lang.String flgConcessioneGradualita) {
           this.dataRichiestaGradualita = dataRichiestaGradualita;
           this.dataRichiestaEsonero = dataRichiestaEsonero;
           this.numProvvRichiestaGradualita = numProvvRichiestaGradualita;
           this.dataRichiestaCompensaz = dataRichiestaCompensaz;
           this.dataPrimaAssunzione = dataPrimaAssunzione;
           this.dataUltAggiornam = dataUltAggiornam;
           this.dataConcessioneEsonero = dataConcessioneEsonero;
           this.idTipoProspDisab = idTipoProspDisab;
           this.numProvvConcessioneCompensaz = numProvvConcessioneCompensaz;
           this.numProvvConcessioneSospensione = numProvvConcessioneSospensione;
           this.flgRichiestaEsonero = flgRichiestaEsonero;
           this.NConvenzioni = NConvenzioni;
           this.flgRichiestaSospensione = flgRichiestaSospensione;
           this.flgStipulaConvenzione = flgStipulaConvenzione;
           this.numProvvConcessioneGradualita = numProvvConcessioneGradualita;
           this.dataInizioSospensione = dataInizioSospensione;
           this.dataClassificazione = dataClassificazione;
           this.dataSecondaAssunzione = dataSecondaAssunzione;
           this.NLavoratoriGradualita = NLavoratoriGradualita;
           this.provCompensaz = provCompensaz;
           this.idSede = idSede;
           this.flgRichiestaGradualita = flgRichiestaGradualita;
           this.numProvvRichiestaEsonero = numProvvRichiestaEsonero;
           this.flgConcessioneCompensaz = flgConcessioneCompensaz;
           this.percConcessioneEsonero = percConcessioneEsonero;
           this.flgConcessioneSospensione = flgConcessioneSospensione;
           this.dataRichiestaSospensione = dataRichiestaSospensione;
           this.descrTipoProspDisab = descrTipoProspDisab;
           this.dataInvio = dataInvio;
           this.dataFineSospensione = dataFineSospensione;
           this.descrMotivoSospensione = descrMotivoSospensione;
           this.organicoProspDisabSILP = organicoProspDisabSILP;
           this.idProspDisab = idProspDisab;
           this.idMotivoSospensione = idMotivoSospensione;
           this.flgRichiestaCompensaz = flgRichiestaCompensaz;
           this.percConcessioneGradualita = percConcessioneGradualita;
           this.percRichiestaEsonero = percRichiestaEsonero;
           this.dataConcessioneSospensione = dataConcessioneSospensione;
           this.numClassificazione = numClassificazione;
           this.numProtocollo = numProtocollo;
           this.dataProtocollo = dataProtocollo;
           this.annoRiferimento = annoRiferimento;
           this.numProvvRichiestaCompensaz = numProvvRichiestaCompensaz;
           this.flgConcessioneEsonero = flgConcessioneEsonero;
           this.siglaProv = siglaProv;
           this.dataConcessioneCompensaz = dataConcessioneCompensaz;
           this.controparte = controparte;
           this.numProvvConcessioneEsonero = numProvvConcessioneEsonero;
           this.dataConcessioneGradualita = dataConcessioneGradualita;
           this.dataUltAggiornamSILP = dataUltAggiornamSILP;
           this.numProvvRichiestaSospensione = numProvvRichiestaSospensione;
           this.flgConcessioneGradualita = flgConcessioneGradualita;
    }


    /**
     * Gets the dataRichiestaGradualita value for this ProspDisabSILP.
     * 
     * @return dataRichiestaGradualita
     */
    public java.lang.String getDataRichiestaGradualita() {
        return dataRichiestaGradualita;
    }


    /**
     * Sets the dataRichiestaGradualita value for this ProspDisabSILP.
     * 
     * @param dataRichiestaGradualita
     */
    public void setDataRichiestaGradualita(java.lang.String dataRichiestaGradualita) {
        this.dataRichiestaGradualita = dataRichiestaGradualita;
    }


    /**
     * Gets the dataRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @return dataRichiestaEsonero
     */
    public java.lang.String getDataRichiestaEsonero() {
        return dataRichiestaEsonero;
    }


    /**
     * Sets the dataRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @param dataRichiestaEsonero
     */
    public void setDataRichiestaEsonero(java.lang.String dataRichiestaEsonero) {
        this.dataRichiestaEsonero = dataRichiestaEsonero;
    }


    /**
     * Gets the numProvvRichiestaGradualita value for this ProspDisabSILP.
     * 
     * @return numProvvRichiestaGradualita
     */
    public java.lang.String getNumProvvRichiestaGradualita() {
        return numProvvRichiestaGradualita;
    }


    /**
     * Sets the numProvvRichiestaGradualita value for this ProspDisabSILP.
     * 
     * @param numProvvRichiestaGradualita
     */
    public void setNumProvvRichiestaGradualita(java.lang.String numProvvRichiestaGradualita) {
        this.numProvvRichiestaGradualita = numProvvRichiestaGradualita;
    }


    /**
     * Gets the dataRichiestaCompensaz value for this ProspDisabSILP.
     * 
     * @return dataRichiestaCompensaz
     */
    public java.lang.String getDataRichiestaCompensaz() {
        return dataRichiestaCompensaz;
    }


    /**
     * Sets the dataRichiestaCompensaz value for this ProspDisabSILP.
     * 
     * @param dataRichiestaCompensaz
     */
    public void setDataRichiestaCompensaz(java.lang.String dataRichiestaCompensaz) {
        this.dataRichiestaCompensaz = dataRichiestaCompensaz;
    }


    /**
     * Gets the dataPrimaAssunzione value for this ProspDisabSILP.
     * 
     * @return dataPrimaAssunzione
     */
    public java.lang.String getDataPrimaAssunzione() {
        return dataPrimaAssunzione;
    }


    /**
     * Sets the dataPrimaAssunzione value for this ProspDisabSILP.
     * 
     * @param dataPrimaAssunzione
     */
    public void setDataPrimaAssunzione(java.lang.String dataPrimaAssunzione) {
        this.dataPrimaAssunzione = dataPrimaAssunzione;
    }


    /**
     * Gets the dataUltAggiornam value for this ProspDisabSILP.
     * 
     * @return dataUltAggiornam
     */
    public java.lang.String getDataUltAggiornam() {
        return dataUltAggiornam;
    }


    /**
     * Sets the dataUltAggiornam value for this ProspDisabSILP.
     * 
     * @param dataUltAggiornam
     */
    public void setDataUltAggiornam(java.lang.String dataUltAggiornam) {
        this.dataUltAggiornam = dataUltAggiornam;
    }


    /**
     * Gets the dataConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @return dataConcessioneEsonero
     */
    public java.lang.String getDataConcessioneEsonero() {
        return dataConcessioneEsonero;
    }


    /**
     * Sets the dataConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @param dataConcessioneEsonero
     */
    public void setDataConcessioneEsonero(java.lang.String dataConcessioneEsonero) {
        this.dataConcessioneEsonero = dataConcessioneEsonero;
    }


    /**
     * Gets the idTipoProspDisab value for this ProspDisabSILP.
     * 
     * @return idTipoProspDisab
     */
    public java.lang.String getIdTipoProspDisab() {
        return idTipoProspDisab;
    }


    /**
     * Sets the idTipoProspDisab value for this ProspDisabSILP.
     * 
     * @param idTipoProspDisab
     */
    public void setIdTipoProspDisab(java.lang.String idTipoProspDisab) {
        this.idTipoProspDisab = idTipoProspDisab;
    }


    /**
     * Gets the numProvvConcessioneCompensaz value for this ProspDisabSILP.
     * 
     * @return numProvvConcessioneCompensaz
     */
    public java.lang.String getNumProvvConcessioneCompensaz() {
        return numProvvConcessioneCompensaz;
    }


    /**
     * Sets the numProvvConcessioneCompensaz value for this ProspDisabSILP.
     * 
     * @param numProvvConcessioneCompensaz
     */
    public void setNumProvvConcessioneCompensaz(java.lang.String numProvvConcessioneCompensaz) {
        this.numProvvConcessioneCompensaz = numProvvConcessioneCompensaz;
    }


    /**
     * Gets the numProvvConcessioneSospensione value for this ProspDisabSILP.
     * 
     * @return numProvvConcessioneSospensione
     */
    public java.lang.String getNumProvvConcessioneSospensione() {
        return numProvvConcessioneSospensione;
    }


    /**
     * Sets the numProvvConcessioneSospensione value for this ProspDisabSILP.
     * 
     * @param numProvvConcessioneSospensione
     */
    public void setNumProvvConcessioneSospensione(java.lang.String numProvvConcessioneSospensione) {
        this.numProvvConcessioneSospensione = numProvvConcessioneSospensione;
    }


    /**
     * Gets the flgRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @return flgRichiestaEsonero
     */
    public java.lang.String getFlgRichiestaEsonero() {
        return flgRichiestaEsonero;
    }


    /**
     * Sets the flgRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @param flgRichiestaEsonero
     */
    public void setFlgRichiestaEsonero(java.lang.String flgRichiestaEsonero) {
        this.flgRichiestaEsonero = flgRichiestaEsonero;
    }


    /**
     * Gets the NConvenzioni value for this ProspDisabSILP.
     * 
     * @return NConvenzioni
     */
    public java.lang.String getNConvenzioni() {
        return NConvenzioni;
    }


    /**
     * Sets the NConvenzioni value for this ProspDisabSILP.
     * 
     * @param NConvenzioni
     */
    public void setNConvenzioni(java.lang.String NConvenzioni) {
        this.NConvenzioni = NConvenzioni;
    }


    /**
     * Gets the flgRichiestaSospensione value for this ProspDisabSILP.
     * 
     * @return flgRichiestaSospensione
     */
    public java.lang.String getFlgRichiestaSospensione() {
        return flgRichiestaSospensione;
    }


    /**
     * Sets the flgRichiestaSospensione value for this ProspDisabSILP.
     * 
     * @param flgRichiestaSospensione
     */
    public void setFlgRichiestaSospensione(java.lang.String flgRichiestaSospensione) {
        this.flgRichiestaSospensione = flgRichiestaSospensione;
    }


    /**
     * Gets the flgStipulaConvenzione value for this ProspDisabSILP.
     * 
     * @return flgStipulaConvenzione
     */
    public java.lang.String getFlgStipulaConvenzione() {
        return flgStipulaConvenzione;
    }


    /**
     * Sets the flgStipulaConvenzione value for this ProspDisabSILP.
     * 
     * @param flgStipulaConvenzione
     */
    public void setFlgStipulaConvenzione(java.lang.String flgStipulaConvenzione) {
        this.flgStipulaConvenzione = flgStipulaConvenzione;
    }


    /**
     * Gets the numProvvConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @return numProvvConcessioneGradualita
     */
    public java.lang.String getNumProvvConcessioneGradualita() {
        return numProvvConcessioneGradualita;
    }


    /**
     * Sets the numProvvConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @param numProvvConcessioneGradualita
     */
    public void setNumProvvConcessioneGradualita(java.lang.String numProvvConcessioneGradualita) {
        this.numProvvConcessioneGradualita = numProvvConcessioneGradualita;
    }


    /**
     * Gets the dataInizioSospensione value for this ProspDisabSILP.
     * 
     * @return dataInizioSospensione
     */
    public java.lang.String getDataInizioSospensione() {
        return dataInizioSospensione;
    }


    /**
     * Sets the dataInizioSospensione value for this ProspDisabSILP.
     * 
     * @param dataInizioSospensione
     */
    public void setDataInizioSospensione(java.lang.String dataInizioSospensione) {
        this.dataInizioSospensione = dataInizioSospensione;
    }


    /**
     * Gets the dataClassificazione value for this ProspDisabSILP.
     * 
     * @return dataClassificazione
     */
    public java.lang.String getDataClassificazione() {
        return dataClassificazione;
    }


    /**
     * Sets the dataClassificazione value for this ProspDisabSILP.
     * 
     * @param dataClassificazione
     */
    public void setDataClassificazione(java.lang.String dataClassificazione) {
        this.dataClassificazione = dataClassificazione;
    }


    /**
     * Gets the dataSecondaAssunzione value for this ProspDisabSILP.
     * 
     * @return dataSecondaAssunzione
     */
    public java.lang.String getDataSecondaAssunzione() {
        return dataSecondaAssunzione;
    }


    /**
     * Sets the dataSecondaAssunzione value for this ProspDisabSILP.
     * 
     * @param dataSecondaAssunzione
     */
    public void setDataSecondaAssunzione(java.lang.String dataSecondaAssunzione) {
        this.dataSecondaAssunzione = dataSecondaAssunzione;
    }


    /**
     * Gets the NLavoratoriGradualita value for this ProspDisabSILP.
     * 
     * @return NLavoratoriGradualita
     */
    public java.lang.String getNLavoratoriGradualita() {
        return NLavoratoriGradualita;
    }


    /**
     * Sets the NLavoratoriGradualita value for this ProspDisabSILP.
     * 
     * @param NLavoratoriGradualita
     */
    public void setNLavoratoriGradualita(java.lang.String NLavoratoriGradualita) {
        this.NLavoratoriGradualita = NLavoratoriGradualita;
    }


    /**
     * Gets the provCompensaz value for this ProspDisabSILP.
     * 
     * @return provCompensaz
     */
    public java.lang.String getProvCompensaz() {
        return provCompensaz;
    }


    /**
     * Sets the provCompensaz value for this ProspDisabSILP.
     * 
     * @param provCompensaz
     */
    public void setProvCompensaz(java.lang.String provCompensaz) {
        this.provCompensaz = provCompensaz;
    }


    /**
     * Gets the idSede value for this ProspDisabSILP.
     * 
     * @return idSede
     */
    public java.lang.String getIdSede() {
        return idSede;
    }


    /**
     * Sets the idSede value for this ProspDisabSILP.
     * 
     * @param idSede
     */
    public void setIdSede(java.lang.String idSede) {
        this.idSede = idSede;
    }


    /**
     * Gets the flgRichiestaGradualita value for this ProspDisabSILP.
     * 
     * @return flgRichiestaGradualita
     */
    public java.lang.String getFlgRichiestaGradualita() {
        return flgRichiestaGradualita;
    }


    /**
     * Sets the flgRichiestaGradualita value for this ProspDisabSILP.
     * 
     * @param flgRichiestaGradualita
     */
    public void setFlgRichiestaGradualita(java.lang.String flgRichiestaGradualita) {
        this.flgRichiestaGradualita = flgRichiestaGradualita;
    }


    /**
     * Gets the numProvvRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @return numProvvRichiestaEsonero
     */
    public java.lang.String getNumProvvRichiestaEsonero() {
        return numProvvRichiestaEsonero;
    }


    /**
     * Sets the numProvvRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @param numProvvRichiestaEsonero
     */
    public void setNumProvvRichiestaEsonero(java.lang.String numProvvRichiestaEsonero) {
        this.numProvvRichiestaEsonero = numProvvRichiestaEsonero;
    }


    /**
     * Gets the flgConcessioneCompensaz value for this ProspDisabSILP.
     * 
     * @return flgConcessioneCompensaz
     */
    public java.lang.String getFlgConcessioneCompensaz() {
        return flgConcessioneCompensaz;
    }


    /**
     * Sets the flgConcessioneCompensaz value for this ProspDisabSILP.
     * 
     * @param flgConcessioneCompensaz
     */
    public void setFlgConcessioneCompensaz(java.lang.String flgConcessioneCompensaz) {
        this.flgConcessioneCompensaz = flgConcessioneCompensaz;
    }


    /**
     * Gets the percConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @return percConcessioneEsonero
     */
    public java.lang.String getPercConcessioneEsonero() {
        return percConcessioneEsonero;
    }


    /**
     * Sets the percConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @param percConcessioneEsonero
     */
    public void setPercConcessioneEsonero(java.lang.String percConcessioneEsonero) {
        this.percConcessioneEsonero = percConcessioneEsonero;
    }


    /**
     * Gets the flgConcessioneSospensione value for this ProspDisabSILP.
     * 
     * @return flgConcessioneSospensione
     */
    public java.lang.String getFlgConcessioneSospensione() {
        return flgConcessioneSospensione;
    }


    /**
     * Sets the flgConcessioneSospensione value for this ProspDisabSILP.
     * 
     * @param flgConcessioneSospensione
     */
    public void setFlgConcessioneSospensione(java.lang.String flgConcessioneSospensione) {
        this.flgConcessioneSospensione = flgConcessioneSospensione;
    }


    /**
     * Gets the dataRichiestaSospensione value for this ProspDisabSILP.
     * 
     * @return dataRichiestaSospensione
     */
    public java.lang.String getDataRichiestaSospensione() {
        return dataRichiestaSospensione;
    }


    /**
     * Sets the dataRichiestaSospensione value for this ProspDisabSILP.
     * 
     * @param dataRichiestaSospensione
     */
    public void setDataRichiestaSospensione(java.lang.String dataRichiestaSospensione) {
        this.dataRichiestaSospensione = dataRichiestaSospensione;
    }


    /**
     * Gets the descrTipoProspDisab value for this ProspDisabSILP.
     * 
     * @return descrTipoProspDisab
     */
    public java.lang.String getDescrTipoProspDisab() {
        return descrTipoProspDisab;
    }


    /**
     * Sets the descrTipoProspDisab value for this ProspDisabSILP.
     * 
     * @param descrTipoProspDisab
     */
    public void setDescrTipoProspDisab(java.lang.String descrTipoProspDisab) {
        this.descrTipoProspDisab = descrTipoProspDisab;
    }


    /**
     * Gets the dataInvio value for this ProspDisabSILP.
     * 
     * @return dataInvio
     */
    public java.lang.String getDataInvio() {
        return dataInvio;
    }


    /**
     * Sets the dataInvio value for this ProspDisabSILP.
     * 
     * @param dataInvio
     */
    public void setDataInvio(java.lang.String dataInvio) {
        this.dataInvio = dataInvio;
    }


    /**
     * Gets the dataFineSospensione value for this ProspDisabSILP.
     * 
     * @return dataFineSospensione
     */
    public java.lang.String getDataFineSospensione() {
        return dataFineSospensione;
    }


    /**
     * Sets the dataFineSospensione value for this ProspDisabSILP.
     * 
     * @param dataFineSospensione
     */
    public void setDataFineSospensione(java.lang.String dataFineSospensione) {
        this.dataFineSospensione = dataFineSospensione;
    }


    /**
     * Gets the descrMotivoSospensione value for this ProspDisabSILP.
     * 
     * @return descrMotivoSospensione
     */
    public java.lang.String getDescrMotivoSospensione() {
        return descrMotivoSospensione;
    }


    /**
     * Sets the descrMotivoSospensione value for this ProspDisabSILP.
     * 
     * @param descrMotivoSospensione
     */
    public void setDescrMotivoSospensione(java.lang.String descrMotivoSospensione) {
        this.descrMotivoSospensione = descrMotivoSospensione;
    }


    /**
     * Gets the organicoProspDisabSILP value for this ProspDisabSILP.
     * 
     * @return organicoProspDisabSILP
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.OrganicoProspDisabSILP[] getOrganicoProspDisabSILP() {
        return organicoProspDisabSILP;
    }


    /**
     * Sets the organicoProspDisabSILP value for this ProspDisabSILP.
     * 
     * @param organicoProspDisabSILP
     */
    public void setOrganicoProspDisabSILP(it.csi.idf.idfapi.util.service.integration.aaep.OrganicoProspDisabSILP[] organicoProspDisabSILP) {
        this.organicoProspDisabSILP = organicoProspDisabSILP;
    }


    /**
     * Gets the idProspDisab value for this ProspDisabSILP.
     * 
     * @return idProspDisab
     */
    public java.lang.String getIdProspDisab() {
        return idProspDisab;
    }


    /**
     * Sets the idProspDisab value for this ProspDisabSILP.
     * 
     * @param idProspDisab
     */
    public void setIdProspDisab(java.lang.String idProspDisab) {
        this.idProspDisab = idProspDisab;
    }


    /**
     * Gets the idMotivoSospensione value for this ProspDisabSILP.
     * 
     * @return idMotivoSospensione
     */
    public java.lang.String getIdMotivoSospensione() {
        return idMotivoSospensione;
    }


    /**
     * Sets the idMotivoSospensione value for this ProspDisabSILP.
     * 
     * @param idMotivoSospensione
     */
    public void setIdMotivoSospensione(java.lang.String idMotivoSospensione) {
        this.idMotivoSospensione = idMotivoSospensione;
    }


    /**
     * Gets the flgRichiestaCompensaz value for this ProspDisabSILP.
     * 
     * @return flgRichiestaCompensaz
     */
    public java.lang.String getFlgRichiestaCompensaz() {
        return flgRichiestaCompensaz;
    }


    /**
     * Sets the flgRichiestaCompensaz value for this ProspDisabSILP.
     * 
     * @param flgRichiestaCompensaz
     */
    public void setFlgRichiestaCompensaz(java.lang.String flgRichiestaCompensaz) {
        this.flgRichiestaCompensaz = flgRichiestaCompensaz;
    }


    /**
     * Gets the percConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @return percConcessioneGradualita
     */
    public java.lang.String getPercConcessioneGradualita() {
        return percConcessioneGradualita;
    }


    /**
     * Sets the percConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @param percConcessioneGradualita
     */
    public void setPercConcessioneGradualita(java.lang.String percConcessioneGradualita) {
        this.percConcessioneGradualita = percConcessioneGradualita;
    }


    /**
     * Gets the percRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @return percRichiestaEsonero
     */
    public java.lang.String getPercRichiestaEsonero() {
        return percRichiestaEsonero;
    }


    /**
     * Sets the percRichiestaEsonero value for this ProspDisabSILP.
     * 
     * @param percRichiestaEsonero
     */
    public void setPercRichiestaEsonero(java.lang.String percRichiestaEsonero) {
        this.percRichiestaEsonero = percRichiestaEsonero;
    }


    /**
     * Gets the dataConcessioneSospensione value for this ProspDisabSILP.
     * 
     * @return dataConcessioneSospensione
     */
    public java.lang.String getDataConcessioneSospensione() {
        return dataConcessioneSospensione;
    }


    /**
     * Sets the dataConcessioneSospensione value for this ProspDisabSILP.
     * 
     * @param dataConcessioneSospensione
     */
    public void setDataConcessioneSospensione(java.lang.String dataConcessioneSospensione) {
        this.dataConcessioneSospensione = dataConcessioneSospensione;
    }


    /**
     * Gets the numClassificazione value for this ProspDisabSILP.
     * 
     * @return numClassificazione
     */
    public java.lang.String getNumClassificazione() {
        return numClassificazione;
    }


    /**
     * Sets the numClassificazione value for this ProspDisabSILP.
     * 
     * @param numClassificazione
     */
    public void setNumClassificazione(java.lang.String numClassificazione) {
        this.numClassificazione = numClassificazione;
    }


    /**
     * Gets the numProtocollo value for this ProspDisabSILP.
     * 
     * @return numProtocollo
     */
    public java.lang.String getNumProtocollo() {
        return numProtocollo;
    }


    /**
     * Sets the numProtocollo value for this ProspDisabSILP.
     * 
     * @param numProtocollo
     */
    public void setNumProtocollo(java.lang.String numProtocollo) {
        this.numProtocollo = numProtocollo;
    }


    /**
     * Gets the dataProtocollo value for this ProspDisabSILP.
     * 
     * @return dataProtocollo
     */
    public java.lang.String getDataProtocollo() {
        return dataProtocollo;
    }


    /**
     * Sets the dataProtocollo value for this ProspDisabSILP.
     * 
     * @param dataProtocollo
     */
    public void setDataProtocollo(java.lang.String dataProtocollo) {
        this.dataProtocollo = dataProtocollo;
    }


    /**
     * Gets the annoRiferimento value for this ProspDisabSILP.
     * 
     * @return annoRiferimento
     */
    public java.lang.String getAnnoRiferimento() {
        return annoRiferimento;
    }


    /**
     * Sets the annoRiferimento value for this ProspDisabSILP.
     * 
     * @param annoRiferimento
     */
    public void setAnnoRiferimento(java.lang.String annoRiferimento) {
        this.annoRiferimento = annoRiferimento;
    }


    /**
     * Gets the numProvvRichiestaCompensaz value for this ProspDisabSILP.
     * 
     * @return numProvvRichiestaCompensaz
     */
    public java.lang.String getNumProvvRichiestaCompensaz() {
        return numProvvRichiestaCompensaz;
    }


    /**
     * Sets the numProvvRichiestaCompensaz value for this ProspDisabSILP.
     * 
     * @param numProvvRichiestaCompensaz
     */
    public void setNumProvvRichiestaCompensaz(java.lang.String numProvvRichiestaCompensaz) {
        this.numProvvRichiestaCompensaz = numProvvRichiestaCompensaz;
    }


    /**
     * Gets the flgConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @return flgConcessioneEsonero
     */
    public java.lang.String getFlgConcessioneEsonero() {
        return flgConcessioneEsonero;
    }


    /**
     * Sets the flgConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @param flgConcessioneEsonero
     */
    public void setFlgConcessioneEsonero(java.lang.String flgConcessioneEsonero) {
        this.flgConcessioneEsonero = flgConcessioneEsonero;
    }


    /**
     * Gets the siglaProv value for this ProspDisabSILP.
     * 
     * @return siglaProv
     */
    public java.lang.String getSiglaProv() {
        return siglaProv;
    }


    /**
     * Sets the siglaProv value for this ProspDisabSILP.
     * 
     * @param siglaProv
     */
    public void setSiglaProv(java.lang.String siglaProv) {
        this.siglaProv = siglaProv;
    }


    /**
     * Gets the dataConcessioneCompensaz value for this ProspDisabSILP.
     * 
     * @return dataConcessioneCompensaz
     */
    public java.lang.String getDataConcessioneCompensaz() {
        return dataConcessioneCompensaz;
    }


    /**
     * Sets the dataConcessioneCompensaz value for this ProspDisabSILP.
     * 
     * @param dataConcessioneCompensaz
     */
    public void setDataConcessioneCompensaz(java.lang.String dataConcessioneCompensaz) {
        this.dataConcessioneCompensaz = dataConcessioneCompensaz;
    }


    /**
     * Gets the controparte value for this ProspDisabSILP.
     * 
     * @return controparte
     */
    public java.lang.String getControparte() {
        return controparte;
    }


    /**
     * Sets the controparte value for this ProspDisabSILP.
     * 
     * @param controparte
     */
    public void setControparte(java.lang.String controparte) {
        this.controparte = controparte;
    }


    /**
     * Gets the numProvvConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @return numProvvConcessioneEsonero
     */
    public java.lang.String getNumProvvConcessioneEsonero() {
        return numProvvConcessioneEsonero;
    }


    /**
     * Sets the numProvvConcessioneEsonero value for this ProspDisabSILP.
     * 
     * @param numProvvConcessioneEsonero
     */
    public void setNumProvvConcessioneEsonero(java.lang.String numProvvConcessioneEsonero) {
        this.numProvvConcessioneEsonero = numProvvConcessioneEsonero;
    }


    /**
     * Gets the dataConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @return dataConcessioneGradualita
     */
    public java.lang.String getDataConcessioneGradualita() {
        return dataConcessioneGradualita;
    }


    /**
     * Sets the dataConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @param dataConcessioneGradualita
     */
    public void setDataConcessioneGradualita(java.lang.String dataConcessioneGradualita) {
        this.dataConcessioneGradualita = dataConcessioneGradualita;
    }


    /**
     * Gets the dataUltAggiornamSILP value for this ProspDisabSILP.
     * 
     * @return dataUltAggiornamSILP
     */
    public java.lang.String getDataUltAggiornamSILP() {
        return dataUltAggiornamSILP;
    }


    /**
     * Sets the dataUltAggiornamSILP value for this ProspDisabSILP.
     * 
     * @param dataUltAggiornamSILP
     */
    public void setDataUltAggiornamSILP(java.lang.String dataUltAggiornamSILP) {
        this.dataUltAggiornamSILP = dataUltAggiornamSILP;
    }


    /**
     * Gets the numProvvRichiestaSospensione value for this ProspDisabSILP.
     * 
     * @return numProvvRichiestaSospensione
     */
    public java.lang.String getNumProvvRichiestaSospensione() {
        return numProvvRichiestaSospensione;
    }


    /**
     * Sets the numProvvRichiestaSospensione value for this ProspDisabSILP.
     * 
     * @param numProvvRichiestaSospensione
     */
    public void setNumProvvRichiestaSospensione(java.lang.String numProvvRichiestaSospensione) {
        this.numProvvRichiestaSospensione = numProvvRichiestaSospensione;
    }


    /**
     * Gets the flgConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @return flgConcessioneGradualita
     */
    public java.lang.String getFlgConcessioneGradualita() {
        return flgConcessioneGradualita;
    }


    /**
     * Sets the flgConcessioneGradualita value for this ProspDisabSILP.
     * 
     * @param flgConcessioneGradualita
     */
    public void setFlgConcessioneGradualita(java.lang.String flgConcessioneGradualita) {
        this.flgConcessioneGradualita = flgConcessioneGradualita;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProspDisabSILP)) return false;
        ProspDisabSILP other = (ProspDisabSILP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataRichiestaGradualita==null && other.getDataRichiestaGradualita()==null) || 
             (this.dataRichiestaGradualita!=null &&
              this.dataRichiestaGradualita.equals(other.getDataRichiestaGradualita()))) &&
            ((this.dataRichiestaEsonero==null && other.getDataRichiestaEsonero()==null) || 
             (this.dataRichiestaEsonero!=null &&
              this.dataRichiestaEsonero.equals(other.getDataRichiestaEsonero()))) &&
            ((this.numProvvRichiestaGradualita==null && other.getNumProvvRichiestaGradualita()==null) || 
             (this.numProvvRichiestaGradualita!=null &&
              this.numProvvRichiestaGradualita.equals(other.getNumProvvRichiestaGradualita()))) &&
            ((this.dataRichiestaCompensaz==null && other.getDataRichiestaCompensaz()==null) || 
             (this.dataRichiestaCompensaz!=null &&
              this.dataRichiestaCompensaz.equals(other.getDataRichiestaCompensaz()))) &&
            ((this.dataPrimaAssunzione==null && other.getDataPrimaAssunzione()==null) || 
             (this.dataPrimaAssunzione!=null &&
              this.dataPrimaAssunzione.equals(other.getDataPrimaAssunzione()))) &&
            ((this.dataUltAggiornam==null && other.getDataUltAggiornam()==null) || 
             (this.dataUltAggiornam!=null &&
              this.dataUltAggiornam.equals(other.getDataUltAggiornam()))) &&
            ((this.dataConcessioneEsonero==null && other.getDataConcessioneEsonero()==null) || 
             (this.dataConcessioneEsonero!=null &&
              this.dataConcessioneEsonero.equals(other.getDataConcessioneEsonero()))) &&
            ((this.idTipoProspDisab==null && other.getIdTipoProspDisab()==null) || 
             (this.idTipoProspDisab!=null &&
              this.idTipoProspDisab.equals(other.getIdTipoProspDisab()))) &&
            ((this.numProvvConcessioneCompensaz==null && other.getNumProvvConcessioneCompensaz()==null) || 
             (this.numProvvConcessioneCompensaz!=null &&
              this.numProvvConcessioneCompensaz.equals(other.getNumProvvConcessioneCompensaz()))) &&
            ((this.numProvvConcessioneSospensione==null && other.getNumProvvConcessioneSospensione()==null) || 
             (this.numProvvConcessioneSospensione!=null &&
              this.numProvvConcessioneSospensione.equals(other.getNumProvvConcessioneSospensione()))) &&
            ((this.flgRichiestaEsonero==null && other.getFlgRichiestaEsonero()==null) || 
             (this.flgRichiestaEsonero!=null &&
              this.flgRichiestaEsonero.equals(other.getFlgRichiestaEsonero()))) &&
            ((this.NConvenzioni==null && other.getNConvenzioni()==null) || 
             (this.NConvenzioni!=null &&
              this.NConvenzioni.equals(other.getNConvenzioni()))) &&
            ((this.flgRichiestaSospensione==null && other.getFlgRichiestaSospensione()==null) || 
             (this.flgRichiestaSospensione!=null &&
              this.flgRichiestaSospensione.equals(other.getFlgRichiestaSospensione()))) &&
            ((this.flgStipulaConvenzione==null && other.getFlgStipulaConvenzione()==null) || 
             (this.flgStipulaConvenzione!=null &&
              this.flgStipulaConvenzione.equals(other.getFlgStipulaConvenzione()))) &&
            ((this.numProvvConcessioneGradualita==null && other.getNumProvvConcessioneGradualita()==null) || 
             (this.numProvvConcessioneGradualita!=null &&
              this.numProvvConcessioneGradualita.equals(other.getNumProvvConcessioneGradualita()))) &&
            ((this.dataInizioSospensione==null && other.getDataInizioSospensione()==null) || 
             (this.dataInizioSospensione!=null &&
              this.dataInizioSospensione.equals(other.getDataInizioSospensione()))) &&
            ((this.dataClassificazione==null && other.getDataClassificazione()==null) || 
             (this.dataClassificazione!=null &&
              this.dataClassificazione.equals(other.getDataClassificazione()))) &&
            ((this.dataSecondaAssunzione==null && other.getDataSecondaAssunzione()==null) || 
             (this.dataSecondaAssunzione!=null &&
              this.dataSecondaAssunzione.equals(other.getDataSecondaAssunzione()))) &&
            ((this.NLavoratoriGradualita==null && other.getNLavoratoriGradualita()==null) || 
             (this.NLavoratoriGradualita!=null &&
              this.NLavoratoriGradualita.equals(other.getNLavoratoriGradualita()))) &&
            ((this.provCompensaz==null && other.getProvCompensaz()==null) || 
             (this.provCompensaz!=null &&
              this.provCompensaz.equals(other.getProvCompensaz()))) &&
            ((this.idSede==null && other.getIdSede()==null) || 
             (this.idSede!=null &&
              this.idSede.equals(other.getIdSede()))) &&
            ((this.flgRichiestaGradualita==null && other.getFlgRichiestaGradualita()==null) || 
             (this.flgRichiestaGradualita!=null &&
              this.flgRichiestaGradualita.equals(other.getFlgRichiestaGradualita()))) &&
            ((this.numProvvRichiestaEsonero==null && other.getNumProvvRichiestaEsonero()==null) || 
             (this.numProvvRichiestaEsonero!=null &&
              this.numProvvRichiestaEsonero.equals(other.getNumProvvRichiestaEsonero()))) &&
            ((this.flgConcessioneCompensaz==null && other.getFlgConcessioneCompensaz()==null) || 
             (this.flgConcessioneCompensaz!=null &&
              this.flgConcessioneCompensaz.equals(other.getFlgConcessioneCompensaz()))) &&
            ((this.percConcessioneEsonero==null && other.getPercConcessioneEsonero()==null) || 
             (this.percConcessioneEsonero!=null &&
              this.percConcessioneEsonero.equals(other.getPercConcessioneEsonero()))) &&
            ((this.flgConcessioneSospensione==null && other.getFlgConcessioneSospensione()==null) || 
             (this.flgConcessioneSospensione!=null &&
              this.flgConcessioneSospensione.equals(other.getFlgConcessioneSospensione()))) &&
            ((this.dataRichiestaSospensione==null && other.getDataRichiestaSospensione()==null) || 
             (this.dataRichiestaSospensione!=null &&
              this.dataRichiestaSospensione.equals(other.getDataRichiestaSospensione()))) &&
            ((this.descrTipoProspDisab==null && other.getDescrTipoProspDisab()==null) || 
             (this.descrTipoProspDisab!=null &&
              this.descrTipoProspDisab.equals(other.getDescrTipoProspDisab()))) &&
            ((this.dataInvio==null && other.getDataInvio()==null) || 
             (this.dataInvio!=null &&
              this.dataInvio.equals(other.getDataInvio()))) &&
            ((this.dataFineSospensione==null && other.getDataFineSospensione()==null) || 
             (this.dataFineSospensione!=null &&
              this.dataFineSospensione.equals(other.getDataFineSospensione()))) &&
            ((this.descrMotivoSospensione==null && other.getDescrMotivoSospensione()==null) || 
             (this.descrMotivoSospensione!=null &&
              this.descrMotivoSospensione.equals(other.getDescrMotivoSospensione()))) &&
            ((this.organicoProspDisabSILP==null && other.getOrganicoProspDisabSILP()==null) || 
             (this.organicoProspDisabSILP!=null &&
              java.util.Arrays.equals(this.organicoProspDisabSILP, other.getOrganicoProspDisabSILP()))) &&
            ((this.idProspDisab==null && other.getIdProspDisab()==null) || 
             (this.idProspDisab!=null &&
              this.idProspDisab.equals(other.getIdProspDisab()))) &&
            ((this.idMotivoSospensione==null && other.getIdMotivoSospensione()==null) || 
             (this.idMotivoSospensione!=null &&
              this.idMotivoSospensione.equals(other.getIdMotivoSospensione()))) &&
            ((this.flgRichiestaCompensaz==null && other.getFlgRichiestaCompensaz()==null) || 
             (this.flgRichiestaCompensaz!=null &&
              this.flgRichiestaCompensaz.equals(other.getFlgRichiestaCompensaz()))) &&
            ((this.percConcessioneGradualita==null && other.getPercConcessioneGradualita()==null) || 
             (this.percConcessioneGradualita!=null &&
              this.percConcessioneGradualita.equals(other.getPercConcessioneGradualita()))) &&
            ((this.percRichiestaEsonero==null && other.getPercRichiestaEsonero()==null) || 
             (this.percRichiestaEsonero!=null &&
              this.percRichiestaEsonero.equals(other.getPercRichiestaEsonero()))) &&
            ((this.dataConcessioneSospensione==null && other.getDataConcessioneSospensione()==null) || 
             (this.dataConcessioneSospensione!=null &&
              this.dataConcessioneSospensione.equals(other.getDataConcessioneSospensione()))) &&
            ((this.numClassificazione==null && other.getNumClassificazione()==null) || 
             (this.numClassificazione!=null &&
              this.numClassificazione.equals(other.getNumClassificazione()))) &&
            ((this.numProtocollo==null && other.getNumProtocollo()==null) || 
             (this.numProtocollo!=null &&
              this.numProtocollo.equals(other.getNumProtocollo()))) &&
            ((this.dataProtocollo==null && other.getDataProtocollo()==null) || 
             (this.dataProtocollo!=null &&
              this.dataProtocollo.equals(other.getDataProtocollo()))) &&
            ((this.annoRiferimento==null && other.getAnnoRiferimento()==null) || 
             (this.annoRiferimento!=null &&
              this.annoRiferimento.equals(other.getAnnoRiferimento()))) &&
            ((this.numProvvRichiestaCompensaz==null && other.getNumProvvRichiestaCompensaz()==null) || 
             (this.numProvvRichiestaCompensaz!=null &&
              this.numProvvRichiestaCompensaz.equals(other.getNumProvvRichiestaCompensaz()))) &&
            ((this.flgConcessioneEsonero==null && other.getFlgConcessioneEsonero()==null) || 
             (this.flgConcessioneEsonero!=null &&
              this.flgConcessioneEsonero.equals(other.getFlgConcessioneEsonero()))) &&
            ((this.siglaProv==null && other.getSiglaProv()==null) || 
             (this.siglaProv!=null &&
              this.siglaProv.equals(other.getSiglaProv()))) &&
            ((this.dataConcessioneCompensaz==null && other.getDataConcessioneCompensaz()==null) || 
             (this.dataConcessioneCompensaz!=null &&
              this.dataConcessioneCompensaz.equals(other.getDataConcessioneCompensaz()))) &&
            ((this.controparte==null && other.getControparte()==null) || 
             (this.controparte!=null &&
              this.controparte.equals(other.getControparte()))) &&
            ((this.numProvvConcessioneEsonero==null && other.getNumProvvConcessioneEsonero()==null) || 
             (this.numProvvConcessioneEsonero!=null &&
              this.numProvvConcessioneEsonero.equals(other.getNumProvvConcessioneEsonero()))) &&
            ((this.dataConcessioneGradualita==null && other.getDataConcessioneGradualita()==null) || 
             (this.dataConcessioneGradualita!=null &&
              this.dataConcessioneGradualita.equals(other.getDataConcessioneGradualita()))) &&
            ((this.dataUltAggiornamSILP==null && other.getDataUltAggiornamSILP()==null) || 
             (this.dataUltAggiornamSILP!=null &&
              this.dataUltAggiornamSILP.equals(other.getDataUltAggiornamSILP()))) &&
            ((this.numProvvRichiestaSospensione==null && other.getNumProvvRichiestaSospensione()==null) || 
             (this.numProvvRichiestaSospensione!=null &&
              this.numProvvRichiestaSospensione.equals(other.getNumProvvRichiestaSospensione()))) &&
            ((this.flgConcessioneGradualita==null && other.getFlgConcessioneGradualita()==null) || 
             (this.flgConcessioneGradualita!=null &&
              this.flgConcessioneGradualita.equals(other.getFlgConcessioneGradualita())));
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
        if (getDataRichiestaGradualita() != null) {
            _hashCode += getDataRichiestaGradualita().hashCode();
        }
        if (getDataRichiestaEsonero() != null) {
            _hashCode += getDataRichiestaEsonero().hashCode();
        }
        if (getNumProvvRichiestaGradualita() != null) {
            _hashCode += getNumProvvRichiestaGradualita().hashCode();
        }
        if (getDataRichiestaCompensaz() != null) {
            _hashCode += getDataRichiestaCompensaz().hashCode();
        }
        if (getDataPrimaAssunzione() != null) {
            _hashCode += getDataPrimaAssunzione().hashCode();
        }
        if (getDataUltAggiornam() != null) {
            _hashCode += getDataUltAggiornam().hashCode();
        }
        if (getDataConcessioneEsonero() != null) {
            _hashCode += getDataConcessioneEsonero().hashCode();
        }
        if (getIdTipoProspDisab() != null) {
            _hashCode += getIdTipoProspDisab().hashCode();
        }
        if (getNumProvvConcessioneCompensaz() != null) {
            _hashCode += getNumProvvConcessioneCompensaz().hashCode();
        }
        if (getNumProvvConcessioneSospensione() != null) {
            _hashCode += getNumProvvConcessioneSospensione().hashCode();
        }
        if (getFlgRichiestaEsonero() != null) {
            _hashCode += getFlgRichiestaEsonero().hashCode();
        }
        if (getNConvenzioni() != null) {
            _hashCode += getNConvenzioni().hashCode();
        }
        if (getFlgRichiestaSospensione() != null) {
            _hashCode += getFlgRichiestaSospensione().hashCode();
        }
        if (getFlgStipulaConvenzione() != null) {
            _hashCode += getFlgStipulaConvenzione().hashCode();
        }
        if (getNumProvvConcessioneGradualita() != null) {
            _hashCode += getNumProvvConcessioneGradualita().hashCode();
        }
        if (getDataInizioSospensione() != null) {
            _hashCode += getDataInizioSospensione().hashCode();
        }
        if (getDataClassificazione() != null) {
            _hashCode += getDataClassificazione().hashCode();
        }
        if (getDataSecondaAssunzione() != null) {
            _hashCode += getDataSecondaAssunzione().hashCode();
        }
        if (getNLavoratoriGradualita() != null) {
            _hashCode += getNLavoratoriGradualita().hashCode();
        }
        if (getProvCompensaz() != null) {
            _hashCode += getProvCompensaz().hashCode();
        }
        if (getIdSede() != null) {
            _hashCode += getIdSede().hashCode();
        }
        if (getFlgRichiestaGradualita() != null) {
            _hashCode += getFlgRichiestaGradualita().hashCode();
        }
        if (getNumProvvRichiestaEsonero() != null) {
            _hashCode += getNumProvvRichiestaEsonero().hashCode();
        }
        if (getFlgConcessioneCompensaz() != null) {
            _hashCode += getFlgConcessioneCompensaz().hashCode();
        }
        if (getPercConcessioneEsonero() != null) {
            _hashCode += getPercConcessioneEsonero().hashCode();
        }
        if (getFlgConcessioneSospensione() != null) {
            _hashCode += getFlgConcessioneSospensione().hashCode();
        }
        if (getDataRichiestaSospensione() != null) {
            _hashCode += getDataRichiestaSospensione().hashCode();
        }
        if (getDescrTipoProspDisab() != null) {
            _hashCode += getDescrTipoProspDisab().hashCode();
        }
        if (getDataInvio() != null) {
            _hashCode += getDataInvio().hashCode();
        }
        if (getDataFineSospensione() != null) {
            _hashCode += getDataFineSospensione().hashCode();
        }
        if (getDescrMotivoSospensione() != null) {
            _hashCode += getDescrMotivoSospensione().hashCode();
        }
        if (getOrganicoProspDisabSILP() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrganicoProspDisabSILP());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrganicoProspDisabSILP(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdProspDisab() != null) {
            _hashCode += getIdProspDisab().hashCode();
        }
        if (getIdMotivoSospensione() != null) {
            _hashCode += getIdMotivoSospensione().hashCode();
        }
        if (getFlgRichiestaCompensaz() != null) {
            _hashCode += getFlgRichiestaCompensaz().hashCode();
        }
        if (getPercConcessioneGradualita() != null) {
            _hashCode += getPercConcessioneGradualita().hashCode();
        }
        if (getPercRichiestaEsonero() != null) {
            _hashCode += getPercRichiestaEsonero().hashCode();
        }
        if (getDataConcessioneSospensione() != null) {
            _hashCode += getDataConcessioneSospensione().hashCode();
        }
        if (getNumClassificazione() != null) {
            _hashCode += getNumClassificazione().hashCode();
        }
        if (getNumProtocollo() != null) {
            _hashCode += getNumProtocollo().hashCode();
        }
        if (getDataProtocollo() != null) {
            _hashCode += getDataProtocollo().hashCode();
        }
        if (getAnnoRiferimento() != null) {
            _hashCode += getAnnoRiferimento().hashCode();
        }
        if (getNumProvvRichiestaCompensaz() != null) {
            _hashCode += getNumProvvRichiestaCompensaz().hashCode();
        }
        if (getFlgConcessioneEsonero() != null) {
            _hashCode += getFlgConcessioneEsonero().hashCode();
        }
        if (getSiglaProv() != null) {
            _hashCode += getSiglaProv().hashCode();
        }
        if (getDataConcessioneCompensaz() != null) {
            _hashCode += getDataConcessioneCompensaz().hashCode();
        }
        if (getControparte() != null) {
            _hashCode += getControparte().hashCode();
        }
        if (getNumProvvConcessioneEsonero() != null) {
            _hashCode += getNumProvvConcessioneEsonero().hashCode();
        }
        if (getDataConcessioneGradualita() != null) {
            _hashCode += getDataConcessioneGradualita().hashCode();
        }
        if (getDataUltAggiornamSILP() != null) {
            _hashCode += getDataUltAggiornamSILP().hashCode();
        }
        if (getNumProvvRichiestaSospensione() != null) {
            _hashCode += getNumProvvRichiestaSospensione().hashCode();
        }
        if (getFlgConcessioneGradualita() != null) {
            _hashCode += getFlgConcessioneGradualita().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProspDisabSILP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ProspDisabSILP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRichiestaGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataRichiestaGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRichiestaEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataRichiestaEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvRichiestaGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvRichiestaGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRichiestaCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataRichiestaCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPrimaAssunzione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataPrimaAssunzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltAggiornam");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataUltAggiornam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConcessioneEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataConcessioneEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProspDisab");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idTipoProspDisab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvConcessioneCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvConcessioneCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvConcessioneSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvConcessioneSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgRichiestaEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgRichiestaEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NConvenzioni");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NConvenzioni"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgRichiestaSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgRichiestaSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgStipulaConvenzione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgStipulaConvenzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvConcessioneGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvConcessioneGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataClassificazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataClassificazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSecondaAssunzione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataSecondaAssunzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NLavoratoriGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NLavoratoriGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "provCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idSede"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgRichiestaGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgRichiestaGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvRichiestaEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvRichiestaEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgConcessioneCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgConcessioneCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percConcessioneEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "percConcessioneEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgConcessioneSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgConcessioneSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRichiestaSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataRichiestaSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrTipoProspDisab");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrTipoProspDisab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInvio");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataFineSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrMotivoSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrMotivoSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organicoProspDisabSILP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "organicoProspDisabSILP"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "OrganicoProspDisabSILP"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProspDisab");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idProspDisab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMotivoSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idMotivoSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgRichiestaCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgRichiestaCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percConcessioneGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "percConcessioneGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percRichiestaEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "percRichiestaEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConcessioneSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataConcessioneSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numClassificazione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numClassificazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoRiferimento");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoRiferimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvRichiestaCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvRichiestaCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgConcessioneEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgConcessioneEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConcessioneCompensaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataConcessioneCompensaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controparte");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "controparte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvConcessioneEsonero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvConcessioneEsonero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConcessioneGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataConcessioneGradualita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltAggiornamSILP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataUltAggiornamSILP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numProvvRichiestaSospensione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numProvvRichiestaSospensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgConcessioneGradualita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgConcessioneGradualita"));
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
