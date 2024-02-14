/**
 * PersonaRIInfoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class PersonaRIInfoc  implements java.io.Serializable {
    private java.lang.String descrTipoPersona;

    private long indicPoteriFirma;

    private java.lang.String cognome;

    private java.lang.String EMail;

    private java.lang.String numRISocParif;

    private java.lang.String codComuneSedeSP;

    private java.lang.String codComuneRes;

    private long idAAEPFonteDato;

    private java.lang.String codFiscaleSP;

    private java.lang.String descrComuneRes;

    private java.lang.String descrStatoSedeSP;

    private java.util.Calendar dataNascita;

    private java.lang.String siglaProvResidenza;

    private java.lang.String codCittadinanza;

    private java.lang.String fax;

    private java.lang.String siglaCCIAASocParif;

    private java.lang.String descrToponimoResid;

    private java.lang.String denominazSP;

    private java.lang.String siglaProvNascita;

    private java.lang.String descrToponimoSedeSP;

    private java.lang.String descrStatoNascita;

    private java.lang.String altreIndicazResid;

    private java.lang.String viaSedeSP;

    private java.lang.String viaResidenza;

    private java.lang.String tipoPersona;

    private java.lang.String siglaProvSedeSP;

    private java.lang.String numCivicoResid;

    private java.lang.String descrComuneNascita;

    private java.util.Calendar dataCostSP;

    private long quotaPartecipaz;

    private java.lang.String codStatoSedeSP;

    private java.lang.String codLimitazCapacitaAgire;

    private long idAAEPazienda;

    private long progrPersona;

    private long progrOrdineVisura;

    private java.lang.String codStatoNascita;

    private java.lang.String codToponimoResid;

    private java.lang.String numTelefono;

    private java.lang.String capSedeSP;

    private java.lang.String nome;

    private long flagElettore;

    private java.lang.String codiceFiscale;

    private java.lang.String codToponimoSedeSP;

    private java.lang.String codComuneNascita;

    private java.lang.String descrIndicatoriPotereF;

    private java.lang.String capResidenza;

    private java.lang.String descrFlagElettore;

    private java.lang.String altreInfoSedeSP;

    private java.lang.String descrStatoRes;

    private java.lang.String numIscrReaSocParif;

    private java.lang.String descrComuneSedeSP;

    private java.lang.String numCivicoSedeSP;

    private long progrUnitaLocale;

    private java.lang.String codStatoRes;

    private long quotaPartecipazEuro;

    private java.lang.String valutaPartecip;

    private java.lang.String descrFrazioneRes;

    private it.csi.idf.idfapi.util.service.integration.aaep.CaricaPersonaInfoc[] listaCaricaPersInfoc;

    private it.csi.idf.idfapi.util.service.integration.aaep.FallimentoPersonaInfoc[] listaFallPersInfoc;

    private long percentPartecip;

    private java.lang.String sesso;

    private java.lang.String descrCittadinanza;

    private java.lang.String frazioneSP;

    public PersonaRIInfoc() {
    }

    public PersonaRIInfoc(
           java.lang.String descrTipoPersona,
           long indicPoteriFirma,
           java.lang.String cognome,
           java.lang.String EMail,
           java.lang.String numRISocParif,
           java.lang.String codComuneSedeSP,
           java.lang.String codComuneRes,
           long idAAEPFonteDato,
           java.lang.String codFiscaleSP,
           java.lang.String descrComuneRes,
           java.lang.String descrStatoSedeSP,
           java.util.Calendar dataNascita,
           java.lang.String siglaProvResidenza,
           java.lang.String codCittadinanza,
           java.lang.String fax,
           java.lang.String siglaCCIAASocParif,
           java.lang.String descrToponimoResid,
           java.lang.String denominazSP,
           java.lang.String siglaProvNascita,
           java.lang.String descrToponimoSedeSP,
           java.lang.String descrStatoNascita,
           java.lang.String altreIndicazResid,
           java.lang.String viaSedeSP,
           java.lang.String viaResidenza,
           java.lang.String tipoPersona,
           java.lang.String siglaProvSedeSP,
           java.lang.String numCivicoResid,
           java.lang.String descrComuneNascita,
           java.util.Calendar dataCostSP,
           long quotaPartecipaz,
           java.lang.String codStatoSedeSP,
           java.lang.String codLimitazCapacitaAgire,
           long idAAEPazienda,
           long progrPersona,
           long progrOrdineVisura,
           java.lang.String codStatoNascita,
           java.lang.String codToponimoResid,
           java.lang.String numTelefono,
           java.lang.String capSedeSP,
           java.lang.String nome,
           long flagElettore,
           java.lang.String codiceFiscale,
           java.lang.String codToponimoSedeSP,
           java.lang.String codComuneNascita,
           java.lang.String descrIndicatoriPotereF,
           java.lang.String capResidenza,
           java.lang.String descrFlagElettore,
           java.lang.String altreInfoSedeSP,
           java.lang.String descrStatoRes,
           java.lang.String numIscrReaSocParif,
           java.lang.String descrComuneSedeSP,
           java.lang.String numCivicoSedeSP,
           long progrUnitaLocale,
           java.lang.String codStatoRes,
           long quotaPartecipazEuro,
           java.lang.String valutaPartecip,
           java.lang.String descrFrazioneRes,
           it.csi.idf.idfapi.util.service.integration.aaep.CaricaPersonaInfoc[] listaCaricaPersInfoc,
           it.csi.idf.idfapi.util.service.integration.aaep.FallimentoPersonaInfoc[] listaFallPersInfoc,
           long percentPartecip,
           java.lang.String sesso,
           java.lang.String descrCittadinanza,
           java.lang.String frazioneSP) {
           this.descrTipoPersona = descrTipoPersona;
           this.indicPoteriFirma = indicPoteriFirma;
           this.cognome = cognome;
           this.EMail = EMail;
           this.numRISocParif = numRISocParif;
           this.codComuneSedeSP = codComuneSedeSP;
           this.codComuneRes = codComuneRes;
           this.idAAEPFonteDato = idAAEPFonteDato;
           this.codFiscaleSP = codFiscaleSP;
           this.descrComuneRes = descrComuneRes;
           this.descrStatoSedeSP = descrStatoSedeSP;
           this.dataNascita = dataNascita;
           this.siglaProvResidenza = siglaProvResidenza;
           this.codCittadinanza = codCittadinanza;
           this.fax = fax;
           this.siglaCCIAASocParif = siglaCCIAASocParif;
           this.descrToponimoResid = descrToponimoResid;
           this.denominazSP = denominazSP;
           this.siglaProvNascita = siglaProvNascita;
           this.descrToponimoSedeSP = descrToponimoSedeSP;
           this.descrStatoNascita = descrStatoNascita;
           this.altreIndicazResid = altreIndicazResid;
           this.viaSedeSP = viaSedeSP;
           this.viaResidenza = viaResidenza;
           this.tipoPersona = tipoPersona;
           this.siglaProvSedeSP = siglaProvSedeSP;
           this.numCivicoResid = numCivicoResid;
           this.descrComuneNascita = descrComuneNascita;
           this.dataCostSP = dataCostSP;
           this.quotaPartecipaz = quotaPartecipaz;
           this.codStatoSedeSP = codStatoSedeSP;
           this.codLimitazCapacitaAgire = codLimitazCapacitaAgire;
           this.idAAEPazienda = idAAEPazienda;
           this.progrPersona = progrPersona;
           this.progrOrdineVisura = progrOrdineVisura;
           this.codStatoNascita = codStatoNascita;
           this.codToponimoResid = codToponimoResid;
           this.numTelefono = numTelefono;
           this.capSedeSP = capSedeSP;
           this.nome = nome;
           this.flagElettore = flagElettore;
           this.codiceFiscale = codiceFiscale;
           this.codToponimoSedeSP = codToponimoSedeSP;
           this.codComuneNascita = codComuneNascita;
           this.descrIndicatoriPotereF = descrIndicatoriPotereF;
           this.capResidenza = capResidenza;
           this.descrFlagElettore = descrFlagElettore;
           this.altreInfoSedeSP = altreInfoSedeSP;
           this.descrStatoRes = descrStatoRes;
           this.numIscrReaSocParif = numIscrReaSocParif;
           this.descrComuneSedeSP = descrComuneSedeSP;
           this.numCivicoSedeSP = numCivicoSedeSP;
           this.progrUnitaLocale = progrUnitaLocale;
           this.codStatoRes = codStatoRes;
           this.quotaPartecipazEuro = quotaPartecipazEuro;
           this.valutaPartecip = valutaPartecip;
           this.descrFrazioneRes = descrFrazioneRes;
           this.listaCaricaPersInfoc = listaCaricaPersInfoc;
           this.listaFallPersInfoc = listaFallPersInfoc;
           this.percentPartecip = percentPartecip;
           this.sesso = sesso;
           this.descrCittadinanza = descrCittadinanza;
           this.frazioneSP = frazioneSP;
    }


    /**
     * Gets the descrTipoPersona value for this PersonaRIInfoc.
     * 
     * @return descrTipoPersona
     */
    public java.lang.String getDescrTipoPersona() {
        return descrTipoPersona;
    }


    /**
     * Sets the descrTipoPersona value for this PersonaRIInfoc.
     * 
     * @param descrTipoPersona
     */
    public void setDescrTipoPersona(java.lang.String descrTipoPersona) {
        this.descrTipoPersona = descrTipoPersona;
    }


    /**
     * Gets the indicPoteriFirma value for this PersonaRIInfoc.
     * 
     * @return indicPoteriFirma
     */
    public long getIndicPoteriFirma() {
        return indicPoteriFirma;
    }


    /**
     * Sets the indicPoteriFirma value for this PersonaRIInfoc.
     * 
     * @param indicPoteriFirma
     */
    public void setIndicPoteriFirma(long indicPoteriFirma) {
        this.indicPoteriFirma = indicPoteriFirma;
    }


    /**
     * Gets the cognome value for this PersonaRIInfoc.
     * 
     * @return cognome
     */
    public java.lang.String getCognome() {
        return cognome;
    }


    /**
     * Sets the cognome value for this PersonaRIInfoc.
     * 
     * @param cognome
     */
    public void setCognome(java.lang.String cognome) {
        this.cognome = cognome;
    }


    /**
     * Gets the EMail value for this PersonaRIInfoc.
     * 
     * @return EMail
     */
    public java.lang.String getEMail() {
        return EMail;
    }


    /**
     * Sets the EMail value for this PersonaRIInfoc.
     * 
     * @param EMail
     */
    public void setEMail(java.lang.String EMail) {
        this.EMail = EMail;
    }


    /**
     * Gets the numRISocParif value for this PersonaRIInfoc.
     * 
     * @return numRISocParif
     */
    public java.lang.String getNumRISocParif() {
        return numRISocParif;
    }


    /**
     * Sets the numRISocParif value for this PersonaRIInfoc.
     * 
     * @param numRISocParif
     */
    public void setNumRISocParif(java.lang.String numRISocParif) {
        this.numRISocParif = numRISocParif;
    }


    /**
     * Gets the codComuneSedeSP value for this PersonaRIInfoc.
     * 
     * @return codComuneSedeSP
     */
    public java.lang.String getCodComuneSedeSP() {
        return codComuneSedeSP;
    }


    /**
     * Sets the codComuneSedeSP value for this PersonaRIInfoc.
     * 
     * @param codComuneSedeSP
     */
    public void setCodComuneSedeSP(java.lang.String codComuneSedeSP) {
        this.codComuneSedeSP = codComuneSedeSP;
    }


    /**
     * Gets the codComuneRes value for this PersonaRIInfoc.
     * 
     * @return codComuneRes
     */
    public java.lang.String getCodComuneRes() {
        return codComuneRes;
    }


    /**
     * Sets the codComuneRes value for this PersonaRIInfoc.
     * 
     * @param codComuneRes
     */
    public void setCodComuneRes(java.lang.String codComuneRes) {
        this.codComuneRes = codComuneRes;
    }


    /**
     * Gets the idAAEPFonteDato value for this PersonaRIInfoc.
     * 
     * @return idAAEPFonteDato
     */
    public long getIdAAEPFonteDato() {
        return idAAEPFonteDato;
    }


    /**
     * Sets the idAAEPFonteDato value for this PersonaRIInfoc.
     * 
     * @param idAAEPFonteDato
     */
    public void setIdAAEPFonteDato(long idAAEPFonteDato) {
        this.idAAEPFonteDato = idAAEPFonteDato;
    }


    /**
     * Gets the codFiscaleSP value for this PersonaRIInfoc.
     * 
     * @return codFiscaleSP
     */
    public java.lang.String getCodFiscaleSP() {
        return codFiscaleSP;
    }


    /**
     * Sets the codFiscaleSP value for this PersonaRIInfoc.
     * 
     * @param codFiscaleSP
     */
    public void setCodFiscaleSP(java.lang.String codFiscaleSP) {
        this.codFiscaleSP = codFiscaleSP;
    }


    /**
     * Gets the descrComuneRes value for this PersonaRIInfoc.
     * 
     * @return descrComuneRes
     */
    public java.lang.String getDescrComuneRes() {
        return descrComuneRes;
    }


    /**
     * Sets the descrComuneRes value for this PersonaRIInfoc.
     * 
     * @param descrComuneRes
     */
    public void setDescrComuneRes(java.lang.String descrComuneRes) {
        this.descrComuneRes = descrComuneRes;
    }


    /**
     * Gets the descrStatoSedeSP value for this PersonaRIInfoc.
     * 
     * @return descrStatoSedeSP
     */
    public java.lang.String getDescrStatoSedeSP() {
        return descrStatoSedeSP;
    }


    /**
     * Sets the descrStatoSedeSP value for this PersonaRIInfoc.
     * 
     * @param descrStatoSedeSP
     */
    public void setDescrStatoSedeSP(java.lang.String descrStatoSedeSP) {
        this.descrStatoSedeSP = descrStatoSedeSP;
    }


    /**
     * Gets the dataNascita value for this PersonaRIInfoc.
     * 
     * @return dataNascita
     */
    public java.util.Calendar getDataNascita() {
        return dataNascita;
    }


    /**
     * Sets the dataNascita value for this PersonaRIInfoc.
     * 
     * @param dataNascita
     */
    public void setDataNascita(java.util.Calendar dataNascita) {
        this.dataNascita = dataNascita;
    }


    /**
     * Gets the siglaProvResidenza value for this PersonaRIInfoc.
     * 
     * @return siglaProvResidenza
     */
    public java.lang.String getSiglaProvResidenza() {
        return siglaProvResidenza;
    }


    /**
     * Sets the siglaProvResidenza value for this PersonaRIInfoc.
     * 
     * @param siglaProvResidenza
     */
    public void setSiglaProvResidenza(java.lang.String siglaProvResidenza) {
        this.siglaProvResidenza = siglaProvResidenza;
    }


    /**
     * Gets the codCittadinanza value for this PersonaRIInfoc.
     * 
     * @return codCittadinanza
     */
    public java.lang.String getCodCittadinanza() {
        return codCittadinanza;
    }


    /**
     * Sets the codCittadinanza value for this PersonaRIInfoc.
     * 
     * @param codCittadinanza
     */
    public void setCodCittadinanza(java.lang.String codCittadinanza) {
        this.codCittadinanza = codCittadinanza;
    }


    /**
     * Gets the fax value for this PersonaRIInfoc.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this PersonaRIInfoc.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the siglaCCIAASocParif value for this PersonaRIInfoc.
     * 
     * @return siglaCCIAASocParif
     */
    public java.lang.String getSiglaCCIAASocParif() {
        return siglaCCIAASocParif;
    }


    /**
     * Sets the siglaCCIAASocParif value for this PersonaRIInfoc.
     * 
     * @param siglaCCIAASocParif
     */
    public void setSiglaCCIAASocParif(java.lang.String siglaCCIAASocParif) {
        this.siglaCCIAASocParif = siglaCCIAASocParif;
    }


    /**
     * Gets the descrToponimoResid value for this PersonaRIInfoc.
     * 
     * @return descrToponimoResid
     */
    public java.lang.String getDescrToponimoResid() {
        return descrToponimoResid;
    }


    /**
     * Sets the descrToponimoResid value for this PersonaRIInfoc.
     * 
     * @param descrToponimoResid
     */
    public void setDescrToponimoResid(java.lang.String descrToponimoResid) {
        this.descrToponimoResid = descrToponimoResid;
    }


    /**
     * Gets the denominazSP value for this PersonaRIInfoc.
     * 
     * @return denominazSP
     */
    public java.lang.String getDenominazSP() {
        return denominazSP;
    }


    /**
     * Sets the denominazSP value for this PersonaRIInfoc.
     * 
     * @param denominazSP
     */
    public void setDenominazSP(java.lang.String denominazSP) {
        this.denominazSP = denominazSP;
    }


    /**
     * Gets the siglaProvNascita value for this PersonaRIInfoc.
     * 
     * @return siglaProvNascita
     */
    public java.lang.String getSiglaProvNascita() {
        return siglaProvNascita;
    }


    /**
     * Sets the siglaProvNascita value for this PersonaRIInfoc.
     * 
     * @param siglaProvNascita
     */
    public void setSiglaProvNascita(java.lang.String siglaProvNascita) {
        this.siglaProvNascita = siglaProvNascita;
    }


    /**
     * Gets the descrToponimoSedeSP value for this PersonaRIInfoc.
     * 
     * @return descrToponimoSedeSP
     */
    public java.lang.String getDescrToponimoSedeSP() {
        return descrToponimoSedeSP;
    }


    /**
     * Sets the descrToponimoSedeSP value for this PersonaRIInfoc.
     * 
     * @param descrToponimoSedeSP
     */
    public void setDescrToponimoSedeSP(java.lang.String descrToponimoSedeSP) {
        this.descrToponimoSedeSP = descrToponimoSedeSP;
    }


    /**
     * Gets the descrStatoNascita value for this PersonaRIInfoc.
     * 
     * @return descrStatoNascita
     */
    public java.lang.String getDescrStatoNascita() {
        return descrStatoNascita;
    }


    /**
     * Sets the descrStatoNascita value for this PersonaRIInfoc.
     * 
     * @param descrStatoNascita
     */
    public void setDescrStatoNascita(java.lang.String descrStatoNascita) {
        this.descrStatoNascita = descrStatoNascita;
    }


    /**
     * Gets the altreIndicazResid value for this PersonaRIInfoc.
     * 
     * @return altreIndicazResid
     */
    public java.lang.String getAltreIndicazResid() {
        return altreIndicazResid;
    }


    /**
     * Sets the altreIndicazResid value for this PersonaRIInfoc.
     * 
     * @param altreIndicazResid
     */
    public void setAltreIndicazResid(java.lang.String altreIndicazResid) {
        this.altreIndicazResid = altreIndicazResid;
    }


    /**
     * Gets the viaSedeSP value for this PersonaRIInfoc.
     * 
     * @return viaSedeSP
     */
    public java.lang.String getViaSedeSP() {
        return viaSedeSP;
    }


    /**
     * Sets the viaSedeSP value for this PersonaRIInfoc.
     * 
     * @param viaSedeSP
     */
    public void setViaSedeSP(java.lang.String viaSedeSP) {
        this.viaSedeSP = viaSedeSP;
    }


    /**
     * Gets the viaResidenza value for this PersonaRIInfoc.
     * 
     * @return viaResidenza
     */
    public java.lang.String getViaResidenza() {
        return viaResidenza;
    }


    /**
     * Sets the viaResidenza value for this PersonaRIInfoc.
     * 
     * @param viaResidenza
     */
    public void setViaResidenza(java.lang.String viaResidenza) {
        this.viaResidenza = viaResidenza;
    }


    /**
     * Gets the tipoPersona value for this PersonaRIInfoc.
     * 
     * @return tipoPersona
     */
    public java.lang.String getTipoPersona() {
        return tipoPersona;
    }


    /**
     * Sets the tipoPersona value for this PersonaRIInfoc.
     * 
     * @param tipoPersona
     */
    public void setTipoPersona(java.lang.String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }


    /**
     * Gets the siglaProvSedeSP value for this PersonaRIInfoc.
     * 
     * @return siglaProvSedeSP
     */
    public java.lang.String getSiglaProvSedeSP() {
        return siglaProvSedeSP;
    }


    /**
     * Sets the siglaProvSedeSP value for this PersonaRIInfoc.
     * 
     * @param siglaProvSedeSP
     */
    public void setSiglaProvSedeSP(java.lang.String siglaProvSedeSP) {
        this.siglaProvSedeSP = siglaProvSedeSP;
    }


    /**
     * Gets the numCivicoResid value for this PersonaRIInfoc.
     * 
     * @return numCivicoResid
     */
    public java.lang.String getNumCivicoResid() {
        return numCivicoResid;
    }


    /**
     * Sets the numCivicoResid value for this PersonaRIInfoc.
     * 
     * @param numCivicoResid
     */
    public void setNumCivicoResid(java.lang.String numCivicoResid) {
        this.numCivicoResid = numCivicoResid;
    }


    /**
     * Gets the descrComuneNascita value for this PersonaRIInfoc.
     * 
     * @return descrComuneNascita
     */
    public java.lang.String getDescrComuneNascita() {
        return descrComuneNascita;
    }


    /**
     * Sets the descrComuneNascita value for this PersonaRIInfoc.
     * 
     * @param descrComuneNascita
     */
    public void setDescrComuneNascita(java.lang.String descrComuneNascita) {
        this.descrComuneNascita = descrComuneNascita;
    }


    /**
     * Gets the dataCostSP value for this PersonaRIInfoc.
     * 
     * @return dataCostSP
     */
    public java.util.Calendar getDataCostSP() {
        return dataCostSP;
    }


    /**
     * Sets the dataCostSP value for this PersonaRIInfoc.
     * 
     * @param dataCostSP
     */
    public void setDataCostSP(java.util.Calendar dataCostSP) {
        this.dataCostSP = dataCostSP;
    }


    /**
     * Gets the quotaPartecipaz value for this PersonaRIInfoc.
     * 
     * @return quotaPartecipaz
     */
    public long getQuotaPartecipaz() {
        return quotaPartecipaz;
    }


    /**
     * Sets the quotaPartecipaz value for this PersonaRIInfoc.
     * 
     * @param quotaPartecipaz
     */
    public void setQuotaPartecipaz(long quotaPartecipaz) {
        this.quotaPartecipaz = quotaPartecipaz;
    }


    /**
     * Gets the codStatoSedeSP value for this PersonaRIInfoc.
     * 
     * @return codStatoSedeSP
     */
    public java.lang.String getCodStatoSedeSP() {
        return codStatoSedeSP;
    }


    /**
     * Sets the codStatoSedeSP value for this PersonaRIInfoc.
     * 
     * @param codStatoSedeSP
     */
    public void setCodStatoSedeSP(java.lang.String codStatoSedeSP) {
        this.codStatoSedeSP = codStatoSedeSP;
    }


    /**
     * Gets the codLimitazCapacitaAgire value for this PersonaRIInfoc.
     * 
     * @return codLimitazCapacitaAgire
     */
    public java.lang.String getCodLimitazCapacitaAgire() {
        return codLimitazCapacitaAgire;
    }


    /**
     * Sets the codLimitazCapacitaAgire value for this PersonaRIInfoc.
     * 
     * @param codLimitazCapacitaAgire
     */
    public void setCodLimitazCapacitaAgire(java.lang.String codLimitazCapacitaAgire) {
        this.codLimitazCapacitaAgire = codLimitazCapacitaAgire;
    }


    /**
     * Gets the idAAEPazienda value for this PersonaRIInfoc.
     * 
     * @return idAAEPazienda
     */
    public long getIdAAEPazienda() {
        return idAAEPazienda;
    }


    /**
     * Sets the idAAEPazienda value for this PersonaRIInfoc.
     * 
     * @param idAAEPazienda
     */
    public void setIdAAEPazienda(long idAAEPazienda) {
        this.idAAEPazienda = idAAEPazienda;
    }


    /**
     * Gets the progrPersona value for this PersonaRIInfoc.
     * 
     * @return progrPersona
     */
    public long getProgrPersona() {
        return progrPersona;
    }


    /**
     * Sets the progrPersona value for this PersonaRIInfoc.
     * 
     * @param progrPersona
     */
    public void setProgrPersona(long progrPersona) {
        this.progrPersona = progrPersona;
    }


    /**
     * Gets the progrOrdineVisura value for this PersonaRIInfoc.
     * 
     * @return progrOrdineVisura
     */
    public long getProgrOrdineVisura() {
        return progrOrdineVisura;
    }


    /**
     * Sets the progrOrdineVisura value for this PersonaRIInfoc.
     * 
     * @param progrOrdineVisura
     */
    public void setProgrOrdineVisura(long progrOrdineVisura) {
        this.progrOrdineVisura = progrOrdineVisura;
    }


    /**
     * Gets the codStatoNascita value for this PersonaRIInfoc.
     * 
     * @return codStatoNascita
     */
    public java.lang.String getCodStatoNascita() {
        return codStatoNascita;
    }


    /**
     * Sets the codStatoNascita value for this PersonaRIInfoc.
     * 
     * @param codStatoNascita
     */
    public void setCodStatoNascita(java.lang.String codStatoNascita) {
        this.codStatoNascita = codStatoNascita;
    }


    /**
     * Gets the codToponimoResid value for this PersonaRIInfoc.
     * 
     * @return codToponimoResid
     */
    public java.lang.String getCodToponimoResid() {
        return codToponimoResid;
    }


    /**
     * Sets the codToponimoResid value for this PersonaRIInfoc.
     * 
     * @param codToponimoResid
     */
    public void setCodToponimoResid(java.lang.String codToponimoResid) {
        this.codToponimoResid = codToponimoResid;
    }


    /**
     * Gets the numTelefono value for this PersonaRIInfoc.
     * 
     * @return numTelefono
     */
    public java.lang.String getNumTelefono() {
        return numTelefono;
    }


    /**
     * Sets the numTelefono value for this PersonaRIInfoc.
     * 
     * @param numTelefono
     */
    public void setNumTelefono(java.lang.String numTelefono) {
        this.numTelefono = numTelefono;
    }


    /**
     * Gets the capSedeSP value for this PersonaRIInfoc.
     * 
     * @return capSedeSP
     */
    public java.lang.String getCapSedeSP() {
        return capSedeSP;
    }


    /**
     * Sets the capSedeSP value for this PersonaRIInfoc.
     * 
     * @param capSedeSP
     */
    public void setCapSedeSP(java.lang.String capSedeSP) {
        this.capSedeSP = capSedeSP;
    }


    /**
     * Gets the nome value for this PersonaRIInfoc.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this PersonaRIInfoc.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the flagElettore value for this PersonaRIInfoc.
     * 
     * @return flagElettore
     */
    public long getFlagElettore() {
        return flagElettore;
    }


    /**
     * Sets the flagElettore value for this PersonaRIInfoc.
     * 
     * @param flagElettore
     */
    public void setFlagElettore(long flagElettore) {
        this.flagElettore = flagElettore;
    }


    /**
     * Gets the codiceFiscale value for this PersonaRIInfoc.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this PersonaRIInfoc.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the codToponimoSedeSP value for this PersonaRIInfoc.
     * 
     * @return codToponimoSedeSP
     */
    public java.lang.String getCodToponimoSedeSP() {
        return codToponimoSedeSP;
    }


    /**
     * Sets the codToponimoSedeSP value for this PersonaRIInfoc.
     * 
     * @param codToponimoSedeSP
     */
    public void setCodToponimoSedeSP(java.lang.String codToponimoSedeSP) {
        this.codToponimoSedeSP = codToponimoSedeSP;
    }


    /**
     * Gets the codComuneNascita value for this PersonaRIInfoc.
     * 
     * @return codComuneNascita
     */
    public java.lang.String getCodComuneNascita() {
        return codComuneNascita;
    }


    /**
     * Sets the codComuneNascita value for this PersonaRIInfoc.
     * 
     * @param codComuneNascita
     */
    public void setCodComuneNascita(java.lang.String codComuneNascita) {
        this.codComuneNascita = codComuneNascita;
    }


    /**
     * Gets the descrIndicatoriPotereF value for this PersonaRIInfoc.
     * 
     * @return descrIndicatoriPotereF
     */
    public java.lang.String getDescrIndicatoriPotereF() {
        return descrIndicatoriPotereF;
    }


    /**
     * Sets the descrIndicatoriPotereF value for this PersonaRIInfoc.
     * 
     * @param descrIndicatoriPotereF
     */
    public void setDescrIndicatoriPotereF(java.lang.String descrIndicatoriPotereF) {
        this.descrIndicatoriPotereF = descrIndicatoriPotereF;
    }


    /**
     * Gets the capResidenza value for this PersonaRIInfoc.
     * 
     * @return capResidenza
     */
    public java.lang.String getCapResidenza() {
        return capResidenza;
    }


    /**
     * Sets the capResidenza value for this PersonaRIInfoc.
     * 
     * @param capResidenza
     */
    public void setCapResidenza(java.lang.String capResidenza) {
        this.capResidenza = capResidenza;
    }


    /**
     * Gets the descrFlagElettore value for this PersonaRIInfoc.
     * 
     * @return descrFlagElettore
     */
    public java.lang.String getDescrFlagElettore() {
        return descrFlagElettore;
    }


    /**
     * Sets the descrFlagElettore value for this PersonaRIInfoc.
     * 
     * @param descrFlagElettore
     */
    public void setDescrFlagElettore(java.lang.String descrFlagElettore) {
        this.descrFlagElettore = descrFlagElettore;
    }


    /**
     * Gets the altreInfoSedeSP value for this PersonaRIInfoc.
     * 
     * @return altreInfoSedeSP
     */
    public java.lang.String getAltreInfoSedeSP() {
        return altreInfoSedeSP;
    }


    /**
     * Sets the altreInfoSedeSP value for this PersonaRIInfoc.
     * 
     * @param altreInfoSedeSP
     */
    public void setAltreInfoSedeSP(java.lang.String altreInfoSedeSP) {
        this.altreInfoSedeSP = altreInfoSedeSP;
    }


    /**
     * Gets the descrStatoRes value for this PersonaRIInfoc.
     * 
     * @return descrStatoRes
     */
    public java.lang.String getDescrStatoRes() {
        return descrStatoRes;
    }


    /**
     * Sets the descrStatoRes value for this PersonaRIInfoc.
     * 
     * @param descrStatoRes
     */
    public void setDescrStatoRes(java.lang.String descrStatoRes) {
        this.descrStatoRes = descrStatoRes;
    }


    /**
     * Gets the numIscrReaSocParif value for this PersonaRIInfoc.
     * 
     * @return numIscrReaSocParif
     */
    public java.lang.String getNumIscrReaSocParif() {
        return numIscrReaSocParif;
    }


    /**
     * Sets the numIscrReaSocParif value for this PersonaRIInfoc.
     * 
     * @param numIscrReaSocParif
     */
    public void setNumIscrReaSocParif(java.lang.String numIscrReaSocParif) {
        this.numIscrReaSocParif = numIscrReaSocParif;
    }


    /**
     * Gets the descrComuneSedeSP value for this PersonaRIInfoc.
     * 
     * @return descrComuneSedeSP
     */
    public java.lang.String getDescrComuneSedeSP() {
        return descrComuneSedeSP;
    }


    /**
     * Sets the descrComuneSedeSP value for this PersonaRIInfoc.
     * 
     * @param descrComuneSedeSP
     */
    public void setDescrComuneSedeSP(java.lang.String descrComuneSedeSP) {
        this.descrComuneSedeSP = descrComuneSedeSP;
    }


    /**
     * Gets the numCivicoSedeSP value for this PersonaRIInfoc.
     * 
     * @return numCivicoSedeSP
     */
    public java.lang.String getNumCivicoSedeSP() {
        return numCivicoSedeSP;
    }


    /**
     * Sets the numCivicoSedeSP value for this PersonaRIInfoc.
     * 
     * @param numCivicoSedeSP
     */
    public void setNumCivicoSedeSP(java.lang.String numCivicoSedeSP) {
        this.numCivicoSedeSP = numCivicoSedeSP;
    }


    /**
     * Gets the progrUnitaLocale value for this PersonaRIInfoc.
     * 
     * @return progrUnitaLocale
     */
    public long getProgrUnitaLocale() {
        return progrUnitaLocale;
    }


    /**
     * Sets the progrUnitaLocale value for this PersonaRIInfoc.
     * 
     * @param progrUnitaLocale
     */
    public void setProgrUnitaLocale(long progrUnitaLocale) {
        this.progrUnitaLocale = progrUnitaLocale;
    }


    /**
     * Gets the codStatoRes value for this PersonaRIInfoc.
     * 
     * @return codStatoRes
     */
    public java.lang.String getCodStatoRes() {
        return codStatoRes;
    }


    /**
     * Sets the codStatoRes value for this PersonaRIInfoc.
     * 
     * @param codStatoRes
     */
    public void setCodStatoRes(java.lang.String codStatoRes) {
        this.codStatoRes = codStatoRes;
    }


    /**
     * Gets the quotaPartecipazEuro value for this PersonaRIInfoc.
     * 
     * @return quotaPartecipazEuro
     */
    public long getQuotaPartecipazEuro() {
        return quotaPartecipazEuro;
    }


    /**
     * Sets the quotaPartecipazEuro value for this PersonaRIInfoc.
     * 
     * @param quotaPartecipazEuro
     */
    public void setQuotaPartecipazEuro(long quotaPartecipazEuro) {
        this.quotaPartecipazEuro = quotaPartecipazEuro;
    }


    /**
     * Gets the valutaPartecip value for this PersonaRIInfoc.
     * 
     * @return valutaPartecip
     */
    public java.lang.String getValutaPartecip() {
        return valutaPartecip;
    }


    /**
     * Sets the valutaPartecip value for this PersonaRIInfoc.
     * 
     * @param valutaPartecip
     */
    public void setValutaPartecip(java.lang.String valutaPartecip) {
        this.valutaPartecip = valutaPartecip;
    }


    /**
     * Gets the descrFrazioneRes value for this PersonaRIInfoc.
     * 
     * @return descrFrazioneRes
     */
    public java.lang.String getDescrFrazioneRes() {
        return descrFrazioneRes;
    }


    /**
     * Sets the descrFrazioneRes value for this PersonaRIInfoc.
     * 
     * @param descrFrazioneRes
     */
    public void setDescrFrazioneRes(java.lang.String descrFrazioneRes) {
        this.descrFrazioneRes = descrFrazioneRes;
    }


    /**
     * Gets the listaCaricaPersInfoc value for this PersonaRIInfoc.
     * 
     * @return listaCaricaPersInfoc
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.CaricaPersonaInfoc[] getListaCaricaPersInfoc() {
        return listaCaricaPersInfoc;
    }


    /**
     * Sets the listaCaricaPersInfoc value for this PersonaRIInfoc.
     * 
     * @param listaCaricaPersInfoc
     */
    public void setListaCaricaPersInfoc(it.csi.idf.idfapi.util.service.integration.aaep.CaricaPersonaInfoc[] listaCaricaPersInfoc) {
        this.listaCaricaPersInfoc = listaCaricaPersInfoc;
    }


    /**
     * Gets the listaFallPersInfoc value for this PersonaRIInfoc.
     * 
     * @return listaFallPersInfoc
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.FallimentoPersonaInfoc[] getListaFallPersInfoc() {
        return listaFallPersInfoc;
    }


    /**
     * Sets the listaFallPersInfoc value for this PersonaRIInfoc.
     * 
     * @param listaFallPersInfoc
     */
    public void setListaFallPersInfoc(it.csi.idf.idfapi.util.service.integration.aaep.FallimentoPersonaInfoc[] listaFallPersInfoc) {
        this.listaFallPersInfoc = listaFallPersInfoc;
    }


    /**
     * Gets the percentPartecip value for this PersonaRIInfoc.
     * 
     * @return percentPartecip
     */
    public long getPercentPartecip() {
        return percentPartecip;
    }


    /**
     * Sets the percentPartecip value for this PersonaRIInfoc.
     * 
     * @param percentPartecip
     */
    public void setPercentPartecip(long percentPartecip) {
        this.percentPartecip = percentPartecip;
    }


    /**
     * Gets the sesso value for this PersonaRIInfoc.
     * 
     * @return sesso
     */
    public java.lang.String getSesso() {
        return sesso;
    }


    /**
     * Sets the sesso value for this PersonaRIInfoc.
     * 
     * @param sesso
     */
    public void setSesso(java.lang.String sesso) {
        this.sesso = sesso;
    }


    /**
     * Gets the descrCittadinanza value for this PersonaRIInfoc.
     * 
     * @return descrCittadinanza
     */
    public java.lang.String getDescrCittadinanza() {
        return descrCittadinanza;
    }


    /**
     * Sets the descrCittadinanza value for this PersonaRIInfoc.
     * 
     * @param descrCittadinanza
     */
    public void setDescrCittadinanza(java.lang.String descrCittadinanza) {
        this.descrCittadinanza = descrCittadinanza;
    }


    /**
     * Gets the frazioneSP value for this PersonaRIInfoc.
     * 
     * @return frazioneSP
     */
    public java.lang.String getFrazioneSP() {
        return frazioneSP;
    }


    /**
     * Sets the frazioneSP value for this PersonaRIInfoc.
     * 
     * @param frazioneSP
     */
    public void setFrazioneSP(java.lang.String frazioneSP) {
        this.frazioneSP = frazioneSP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PersonaRIInfoc)) return false;
        PersonaRIInfoc other = (PersonaRIInfoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descrTipoPersona==null && other.getDescrTipoPersona()==null) || 
             (this.descrTipoPersona!=null &&
              this.descrTipoPersona.equals(other.getDescrTipoPersona()))) &&
            this.indicPoteriFirma == other.getIndicPoteriFirma() &&
            ((this.cognome==null && other.getCognome()==null) || 
             (this.cognome!=null &&
              this.cognome.equals(other.getCognome()))) &&
            ((this.EMail==null && other.getEMail()==null) || 
             (this.EMail!=null &&
              this.EMail.equals(other.getEMail()))) &&
            ((this.numRISocParif==null && other.getNumRISocParif()==null) || 
             (this.numRISocParif!=null &&
              this.numRISocParif.equals(other.getNumRISocParif()))) &&
            ((this.codComuneSedeSP==null && other.getCodComuneSedeSP()==null) || 
             (this.codComuneSedeSP!=null &&
              this.codComuneSedeSP.equals(other.getCodComuneSedeSP()))) &&
            ((this.codComuneRes==null && other.getCodComuneRes()==null) || 
             (this.codComuneRes!=null &&
              this.codComuneRes.equals(other.getCodComuneRes()))) &&
            this.idAAEPFonteDato == other.getIdAAEPFonteDato() &&
            ((this.codFiscaleSP==null && other.getCodFiscaleSP()==null) || 
             (this.codFiscaleSP!=null &&
              this.codFiscaleSP.equals(other.getCodFiscaleSP()))) &&
            ((this.descrComuneRes==null && other.getDescrComuneRes()==null) || 
             (this.descrComuneRes!=null &&
              this.descrComuneRes.equals(other.getDescrComuneRes()))) &&
            ((this.descrStatoSedeSP==null && other.getDescrStatoSedeSP()==null) || 
             (this.descrStatoSedeSP!=null &&
              this.descrStatoSedeSP.equals(other.getDescrStatoSedeSP()))) &&
            ((this.dataNascita==null && other.getDataNascita()==null) || 
             (this.dataNascita!=null &&
              this.dataNascita.equals(other.getDataNascita()))) &&
            ((this.siglaProvResidenza==null && other.getSiglaProvResidenza()==null) || 
             (this.siglaProvResidenza!=null &&
              this.siglaProvResidenza.equals(other.getSiglaProvResidenza()))) &&
            ((this.codCittadinanza==null && other.getCodCittadinanza()==null) || 
             (this.codCittadinanza!=null &&
              this.codCittadinanza.equals(other.getCodCittadinanza()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.siglaCCIAASocParif==null && other.getSiglaCCIAASocParif()==null) || 
             (this.siglaCCIAASocParif!=null &&
              this.siglaCCIAASocParif.equals(other.getSiglaCCIAASocParif()))) &&
            ((this.descrToponimoResid==null && other.getDescrToponimoResid()==null) || 
             (this.descrToponimoResid!=null &&
              this.descrToponimoResid.equals(other.getDescrToponimoResid()))) &&
            ((this.denominazSP==null && other.getDenominazSP()==null) || 
             (this.denominazSP!=null &&
              this.denominazSP.equals(other.getDenominazSP()))) &&
            ((this.siglaProvNascita==null && other.getSiglaProvNascita()==null) || 
             (this.siglaProvNascita!=null &&
              this.siglaProvNascita.equals(other.getSiglaProvNascita()))) &&
            ((this.descrToponimoSedeSP==null && other.getDescrToponimoSedeSP()==null) || 
             (this.descrToponimoSedeSP!=null &&
              this.descrToponimoSedeSP.equals(other.getDescrToponimoSedeSP()))) &&
            ((this.descrStatoNascita==null && other.getDescrStatoNascita()==null) || 
             (this.descrStatoNascita!=null &&
              this.descrStatoNascita.equals(other.getDescrStatoNascita()))) &&
            ((this.altreIndicazResid==null && other.getAltreIndicazResid()==null) || 
             (this.altreIndicazResid!=null &&
              this.altreIndicazResid.equals(other.getAltreIndicazResid()))) &&
            ((this.viaSedeSP==null && other.getViaSedeSP()==null) || 
             (this.viaSedeSP!=null &&
              this.viaSedeSP.equals(other.getViaSedeSP()))) &&
            ((this.viaResidenza==null && other.getViaResidenza()==null) || 
             (this.viaResidenza!=null &&
              this.viaResidenza.equals(other.getViaResidenza()))) &&
            ((this.tipoPersona==null && other.getTipoPersona()==null) || 
             (this.tipoPersona!=null &&
              this.tipoPersona.equals(other.getTipoPersona()))) &&
            ((this.siglaProvSedeSP==null && other.getSiglaProvSedeSP()==null) || 
             (this.siglaProvSedeSP!=null &&
              this.siglaProvSedeSP.equals(other.getSiglaProvSedeSP()))) &&
            ((this.numCivicoResid==null && other.getNumCivicoResid()==null) || 
             (this.numCivicoResid!=null &&
              this.numCivicoResid.equals(other.getNumCivicoResid()))) &&
            ((this.descrComuneNascita==null && other.getDescrComuneNascita()==null) || 
             (this.descrComuneNascita!=null &&
              this.descrComuneNascita.equals(other.getDescrComuneNascita()))) &&
            ((this.dataCostSP==null && other.getDataCostSP()==null) || 
             (this.dataCostSP!=null &&
              this.dataCostSP.equals(other.getDataCostSP()))) &&
            this.quotaPartecipaz == other.getQuotaPartecipaz() &&
            ((this.codStatoSedeSP==null && other.getCodStatoSedeSP()==null) || 
             (this.codStatoSedeSP!=null &&
              this.codStatoSedeSP.equals(other.getCodStatoSedeSP()))) &&
            ((this.codLimitazCapacitaAgire==null && other.getCodLimitazCapacitaAgire()==null) || 
             (this.codLimitazCapacitaAgire!=null &&
              this.codLimitazCapacitaAgire.equals(other.getCodLimitazCapacitaAgire()))) &&
            this.idAAEPazienda == other.getIdAAEPazienda() &&
            this.progrPersona == other.getProgrPersona() &&
            this.progrOrdineVisura == other.getProgrOrdineVisura() &&
            ((this.codStatoNascita==null && other.getCodStatoNascita()==null) || 
             (this.codStatoNascita!=null &&
              this.codStatoNascita.equals(other.getCodStatoNascita()))) &&
            ((this.codToponimoResid==null && other.getCodToponimoResid()==null) || 
             (this.codToponimoResid!=null &&
              this.codToponimoResid.equals(other.getCodToponimoResid()))) &&
            ((this.numTelefono==null && other.getNumTelefono()==null) || 
             (this.numTelefono!=null &&
              this.numTelefono.equals(other.getNumTelefono()))) &&
            ((this.capSedeSP==null && other.getCapSedeSP()==null) || 
             (this.capSedeSP!=null &&
              this.capSedeSP.equals(other.getCapSedeSP()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            this.flagElettore == other.getFlagElettore() &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.codToponimoSedeSP==null && other.getCodToponimoSedeSP()==null) || 
             (this.codToponimoSedeSP!=null &&
              this.codToponimoSedeSP.equals(other.getCodToponimoSedeSP()))) &&
            ((this.codComuneNascita==null && other.getCodComuneNascita()==null) || 
             (this.codComuneNascita!=null &&
              this.codComuneNascita.equals(other.getCodComuneNascita()))) &&
            ((this.descrIndicatoriPotereF==null && other.getDescrIndicatoriPotereF()==null) || 
             (this.descrIndicatoriPotereF!=null &&
              this.descrIndicatoriPotereF.equals(other.getDescrIndicatoriPotereF()))) &&
            ((this.capResidenza==null && other.getCapResidenza()==null) || 
             (this.capResidenza!=null &&
              this.capResidenza.equals(other.getCapResidenza()))) &&
            ((this.descrFlagElettore==null && other.getDescrFlagElettore()==null) || 
             (this.descrFlagElettore!=null &&
              this.descrFlagElettore.equals(other.getDescrFlagElettore()))) &&
            ((this.altreInfoSedeSP==null && other.getAltreInfoSedeSP()==null) || 
             (this.altreInfoSedeSP!=null &&
              this.altreInfoSedeSP.equals(other.getAltreInfoSedeSP()))) &&
            ((this.descrStatoRes==null && other.getDescrStatoRes()==null) || 
             (this.descrStatoRes!=null &&
              this.descrStatoRes.equals(other.getDescrStatoRes()))) &&
            ((this.numIscrReaSocParif==null && other.getNumIscrReaSocParif()==null) || 
             (this.numIscrReaSocParif!=null &&
              this.numIscrReaSocParif.equals(other.getNumIscrReaSocParif()))) &&
            ((this.descrComuneSedeSP==null && other.getDescrComuneSedeSP()==null) || 
             (this.descrComuneSedeSP!=null &&
              this.descrComuneSedeSP.equals(other.getDescrComuneSedeSP()))) &&
            ((this.numCivicoSedeSP==null && other.getNumCivicoSedeSP()==null) || 
             (this.numCivicoSedeSP!=null &&
              this.numCivicoSedeSP.equals(other.getNumCivicoSedeSP()))) &&
            this.progrUnitaLocale == other.getProgrUnitaLocale() &&
            ((this.codStatoRes==null && other.getCodStatoRes()==null) || 
             (this.codStatoRes!=null &&
              this.codStatoRes.equals(other.getCodStatoRes()))) &&
            this.quotaPartecipazEuro == other.getQuotaPartecipazEuro() &&
            ((this.valutaPartecip==null && other.getValutaPartecip()==null) || 
             (this.valutaPartecip!=null &&
              this.valutaPartecip.equals(other.getValutaPartecip()))) &&
            ((this.descrFrazioneRes==null && other.getDescrFrazioneRes()==null) || 
             (this.descrFrazioneRes!=null &&
              this.descrFrazioneRes.equals(other.getDescrFrazioneRes()))) &&
            ((this.listaCaricaPersInfoc==null && other.getListaCaricaPersInfoc()==null) || 
             (this.listaCaricaPersInfoc!=null &&
              java.util.Arrays.equals(this.listaCaricaPersInfoc, other.getListaCaricaPersInfoc()))) &&
            ((this.listaFallPersInfoc==null && other.getListaFallPersInfoc()==null) || 
             (this.listaFallPersInfoc!=null &&
              java.util.Arrays.equals(this.listaFallPersInfoc, other.getListaFallPersInfoc()))) &&
            this.percentPartecip == other.getPercentPartecip() &&
            ((this.sesso==null && other.getSesso()==null) || 
             (this.sesso!=null &&
              this.sesso.equals(other.getSesso()))) &&
            ((this.descrCittadinanza==null && other.getDescrCittadinanza()==null) || 
             (this.descrCittadinanza!=null &&
              this.descrCittadinanza.equals(other.getDescrCittadinanza()))) &&
            ((this.frazioneSP==null && other.getFrazioneSP()==null) || 
             (this.frazioneSP!=null &&
              this.frazioneSP.equals(other.getFrazioneSP())));
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
        if (getDescrTipoPersona() != null) {
            _hashCode += getDescrTipoPersona().hashCode();
        }
        _hashCode += new Long(getIndicPoteriFirma()).hashCode();
        if (getCognome() != null) {
            _hashCode += getCognome().hashCode();
        }
        if (getEMail() != null) {
            _hashCode += getEMail().hashCode();
        }
        if (getNumRISocParif() != null) {
            _hashCode += getNumRISocParif().hashCode();
        }
        if (getCodComuneSedeSP() != null) {
            _hashCode += getCodComuneSedeSP().hashCode();
        }
        if (getCodComuneRes() != null) {
            _hashCode += getCodComuneRes().hashCode();
        }
        _hashCode += new Long(getIdAAEPFonteDato()).hashCode();
        if (getCodFiscaleSP() != null) {
            _hashCode += getCodFiscaleSP().hashCode();
        }
        if (getDescrComuneRes() != null) {
            _hashCode += getDescrComuneRes().hashCode();
        }
        if (getDescrStatoSedeSP() != null) {
            _hashCode += getDescrStatoSedeSP().hashCode();
        }
        if (getDataNascita() != null) {
            _hashCode += getDataNascita().hashCode();
        }
        if (getSiglaProvResidenza() != null) {
            _hashCode += getSiglaProvResidenza().hashCode();
        }
        if (getCodCittadinanza() != null) {
            _hashCode += getCodCittadinanza().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getSiglaCCIAASocParif() != null) {
            _hashCode += getSiglaCCIAASocParif().hashCode();
        }
        if (getDescrToponimoResid() != null) {
            _hashCode += getDescrToponimoResid().hashCode();
        }
        if (getDenominazSP() != null) {
            _hashCode += getDenominazSP().hashCode();
        }
        if (getSiglaProvNascita() != null) {
            _hashCode += getSiglaProvNascita().hashCode();
        }
        if (getDescrToponimoSedeSP() != null) {
            _hashCode += getDescrToponimoSedeSP().hashCode();
        }
        if (getDescrStatoNascita() != null) {
            _hashCode += getDescrStatoNascita().hashCode();
        }
        if (getAltreIndicazResid() != null) {
            _hashCode += getAltreIndicazResid().hashCode();
        }
        if (getViaSedeSP() != null) {
            _hashCode += getViaSedeSP().hashCode();
        }
        if (getViaResidenza() != null) {
            _hashCode += getViaResidenza().hashCode();
        }
        if (getTipoPersona() != null) {
            _hashCode += getTipoPersona().hashCode();
        }
        if (getSiglaProvSedeSP() != null) {
            _hashCode += getSiglaProvSedeSP().hashCode();
        }
        if (getNumCivicoResid() != null) {
            _hashCode += getNumCivicoResid().hashCode();
        }
        if (getDescrComuneNascita() != null) {
            _hashCode += getDescrComuneNascita().hashCode();
        }
        if (getDataCostSP() != null) {
            _hashCode += getDataCostSP().hashCode();
        }
        _hashCode += new Long(getQuotaPartecipaz()).hashCode();
        if (getCodStatoSedeSP() != null) {
            _hashCode += getCodStatoSedeSP().hashCode();
        }
        if (getCodLimitazCapacitaAgire() != null) {
            _hashCode += getCodLimitazCapacitaAgire().hashCode();
        }
        _hashCode += new Long(getIdAAEPazienda()).hashCode();
        _hashCode += new Long(getProgrPersona()).hashCode();
        _hashCode += new Long(getProgrOrdineVisura()).hashCode();
        if (getCodStatoNascita() != null) {
            _hashCode += getCodStatoNascita().hashCode();
        }
        if (getCodToponimoResid() != null) {
            _hashCode += getCodToponimoResid().hashCode();
        }
        if (getNumTelefono() != null) {
            _hashCode += getNumTelefono().hashCode();
        }
        if (getCapSedeSP() != null) {
            _hashCode += getCapSedeSP().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        _hashCode += new Long(getFlagElettore()).hashCode();
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getCodToponimoSedeSP() != null) {
            _hashCode += getCodToponimoSedeSP().hashCode();
        }
        if (getCodComuneNascita() != null) {
            _hashCode += getCodComuneNascita().hashCode();
        }
        if (getDescrIndicatoriPotereF() != null) {
            _hashCode += getDescrIndicatoriPotereF().hashCode();
        }
        if (getCapResidenza() != null) {
            _hashCode += getCapResidenza().hashCode();
        }
        if (getDescrFlagElettore() != null) {
            _hashCode += getDescrFlagElettore().hashCode();
        }
        if (getAltreInfoSedeSP() != null) {
            _hashCode += getAltreInfoSedeSP().hashCode();
        }
        if (getDescrStatoRes() != null) {
            _hashCode += getDescrStatoRes().hashCode();
        }
        if (getNumIscrReaSocParif() != null) {
            _hashCode += getNumIscrReaSocParif().hashCode();
        }
        if (getDescrComuneSedeSP() != null) {
            _hashCode += getDescrComuneSedeSP().hashCode();
        }
        if (getNumCivicoSedeSP() != null) {
            _hashCode += getNumCivicoSedeSP().hashCode();
        }
        _hashCode += new Long(getProgrUnitaLocale()).hashCode();
        if (getCodStatoRes() != null) {
            _hashCode += getCodStatoRes().hashCode();
        }
        _hashCode += new Long(getQuotaPartecipazEuro()).hashCode();
        if (getValutaPartecip() != null) {
            _hashCode += getValutaPartecip().hashCode();
        }
        if (getDescrFrazioneRes() != null) {
            _hashCode += getDescrFrazioneRes().hashCode();
        }
        if (getListaCaricaPersInfoc() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCaricaPersInfoc());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCaricaPersInfoc(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaFallPersInfoc() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaFallPersInfoc());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaFallPersInfoc(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getPercentPartecip()).hashCode();
        if (getSesso() != null) {
            _hashCode += getSesso().hashCode();
        }
        if (getDescrCittadinanza() != null) {
            _hashCode += getDescrCittadinanza().hashCode();
        }
        if (getFrazioneSP() != null) {
            _hashCode += getFrazioneSP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonaRIInfoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "PersonaRIInfoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrTipoPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrTipoPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicPoteriFirma");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "indicPoteriFirma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cognome");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "cognome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMail");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "EMail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numRISocParif");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numRISocParif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codComuneSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codComuneSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codComuneRes");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codComuneRes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPFonteDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codFiscaleSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codFiscaleSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrComuneRes");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComuneRes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrStatoSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrStatoSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProvResidenza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProvResidenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCittadinanza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codCittadinanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaCCIAASocParif");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaCCIAASocParif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrToponimoResid");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrToponimoResid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denominazSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "denominazSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProvNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProvNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrToponimoSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrToponimoSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrStatoNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrStatoNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("altreIndicazResid");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "altreIndicazResid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viaSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "viaSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viaResidenza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "viaResidenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "tipoPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProvSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProvSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numCivicoResid");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numCivicoResid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrComuneNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComuneNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCostSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataCostSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quotaPartecipaz");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "quotaPartecipaz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStatoSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codStatoSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codLimitazCapacitaAgire");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codLimitazCapacitaAgire"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPazienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPazienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrOrdineVisura");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrOrdineVisura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStatoNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codStatoNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codToponimoResid");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codToponimoResid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTelefono");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numTelefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "capSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagElettore");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flagElettore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codToponimoSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codToponimoSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codComuneNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codComuneNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrIndicatoriPotereF");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrIndicatoriPotereF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capResidenza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "capResidenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrFlagElettore");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrFlagElettore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("altreInfoSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "altreInfoSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrStatoRes");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrStatoRes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numIscrReaSocParif");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numIscrReaSocParif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrComuneSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComuneSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numCivicoSedeSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numCivicoSedeSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrUnitaLocale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrUnitaLocale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codStatoRes");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codStatoRes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quotaPartecipazEuro");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "quotaPartecipazEuro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valutaPartecip");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "valutaPartecip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrFrazioneRes");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrFrazioneRes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCaricaPersInfoc");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaCaricaPersInfoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "CaricaPersonaInfoc"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaFallPersInfoc");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaFallPersInfoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "FallimentoPersonaInfoc"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentPartecip");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "percentPartecip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sesso");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "sesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrCittadinanza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrCittadinanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frazioneSP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "frazioneSP"));
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
