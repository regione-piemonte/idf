/**
 * CorsoFP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class CorsoFP  implements java.io.Serializable {
    private java.lang.String progrAccorpamento;

    private java.lang.String descrSettore;

    private it.csi.idf.idfapi.util.service.integration.aaep.SedeOccasionale sedeOccasionale;

    private java.lang.String oreStage;

    private java.lang.String numeroAllievi;

    private it.csi.idf.idfapi.util.service.integration.aaep.ListaIndirizzi[] listaIndirizzi;

    private java.lang.String annoGestione;

    private java.lang.String orePratica;

    private java.lang.String annoComparto;

    private java.lang.String oreTeoria;

    private java.lang.String numTotPartiIter;

    private java.lang.String dataInizioCorso;

    private java.lang.String annoInizioCorso;

    private java.lang.String descrCausaleSoppressione;

    private java.lang.String descrLineaInterventoPOR;

    private java.lang.String denominazione;

    private java.lang.String descrDocRegionale;

    private java.lang.String parteIterAttuale;

    private java.lang.String descrComparto;

    private java.lang.String statoAvanzam;

    private java.lang.String totaleOreIter;

    private java.lang.String codComparto;

    private java.lang.String codCausaleSoppressione;

    private java.lang.String codSettore;

    private it.csi.idf.idfapi.util.service.integration.aaep.ListaDestinatari[] listaDestinatari;

    private java.lang.String codDocRegionale;

    private java.lang.String tipoAttivita;

    private java.lang.String annoSettore;

    private java.lang.String codAzionePOR;

    private java.lang.String descrAzionePOR;

    private java.lang.String dataFineCorso;

    private java.lang.String codLineaInterventoPOR;

    private java.lang.String tipoEntita;

    private java.lang.String progrCorso;

    private java.lang.String codMisuraPOR;

    private java.lang.String descrMisuraPOR;

    public CorsoFP() {
    }

    public CorsoFP(
           java.lang.String progrAccorpamento,
           java.lang.String descrSettore,
           it.csi.idf.idfapi.util.service.integration.aaep.SedeOccasionale sedeOccasionale,
           java.lang.String oreStage,
           java.lang.String numeroAllievi,
           it.csi.idf.idfapi.util.service.integration.aaep.ListaIndirizzi[] listaIndirizzi,
           java.lang.String annoGestione,
           java.lang.String orePratica,
           java.lang.String annoComparto,
           java.lang.String oreTeoria,
           java.lang.String numTotPartiIter,
           java.lang.String dataInizioCorso,
           java.lang.String annoInizioCorso,
           java.lang.String descrCausaleSoppressione,
           java.lang.String descrLineaInterventoPOR,
           java.lang.String denominazione,
           java.lang.String descrDocRegionale,
           java.lang.String parteIterAttuale,
           java.lang.String descrComparto,
           java.lang.String statoAvanzam,
           java.lang.String totaleOreIter,
           java.lang.String codComparto,
           java.lang.String codCausaleSoppressione,
           java.lang.String codSettore,
           it.csi.idf.idfapi.util.service.integration.aaep.ListaDestinatari[] listaDestinatari,
           java.lang.String codDocRegionale,
           java.lang.String tipoAttivita,
           java.lang.String annoSettore,
           java.lang.String codAzionePOR,
           java.lang.String descrAzionePOR,
           java.lang.String dataFineCorso,
           java.lang.String codLineaInterventoPOR,
           java.lang.String tipoEntita,
           java.lang.String progrCorso,
           java.lang.String codMisuraPOR,
           java.lang.String descrMisuraPOR) {
           this.progrAccorpamento = progrAccorpamento;
           this.descrSettore = descrSettore;
           this.sedeOccasionale = sedeOccasionale;
           this.oreStage = oreStage;
           this.numeroAllievi = numeroAllievi;
           this.listaIndirizzi = listaIndirizzi;
           this.annoGestione = annoGestione;
           this.orePratica = orePratica;
           this.annoComparto = annoComparto;
           this.oreTeoria = oreTeoria;
           this.numTotPartiIter = numTotPartiIter;
           this.dataInizioCorso = dataInizioCorso;
           this.annoInizioCorso = annoInizioCorso;
           this.descrCausaleSoppressione = descrCausaleSoppressione;
           this.descrLineaInterventoPOR = descrLineaInterventoPOR;
           this.denominazione = denominazione;
           this.descrDocRegionale = descrDocRegionale;
           this.parteIterAttuale = parteIterAttuale;
           this.descrComparto = descrComparto;
           this.statoAvanzam = statoAvanzam;
           this.totaleOreIter = totaleOreIter;
           this.codComparto = codComparto;
           this.codCausaleSoppressione = codCausaleSoppressione;
           this.codSettore = codSettore;
           this.listaDestinatari = listaDestinatari;
           this.codDocRegionale = codDocRegionale;
           this.tipoAttivita = tipoAttivita;
           this.annoSettore = annoSettore;
           this.codAzionePOR = codAzionePOR;
           this.descrAzionePOR = descrAzionePOR;
           this.dataFineCorso = dataFineCorso;
           this.codLineaInterventoPOR = codLineaInterventoPOR;
           this.tipoEntita = tipoEntita;
           this.progrCorso = progrCorso;
           this.codMisuraPOR = codMisuraPOR;
           this.descrMisuraPOR = descrMisuraPOR;
    }


    /**
     * Gets the progrAccorpamento value for this CorsoFP.
     * 
     * @return progrAccorpamento
     */
    public java.lang.String getProgrAccorpamento() {
        return progrAccorpamento;
    }


    /**
     * Sets the progrAccorpamento value for this CorsoFP.
     * 
     * @param progrAccorpamento
     */
    public void setProgrAccorpamento(java.lang.String progrAccorpamento) {
        this.progrAccorpamento = progrAccorpamento;
    }


    /**
     * Gets the descrSettore value for this CorsoFP.
     * 
     * @return descrSettore
     */
    public java.lang.String getDescrSettore() {
        return descrSettore;
    }


    /**
     * Sets the descrSettore value for this CorsoFP.
     * 
     * @param descrSettore
     */
    public void setDescrSettore(java.lang.String descrSettore) {
        this.descrSettore = descrSettore;
    }


    /**
     * Gets the sedeOccasionale value for this CorsoFP.
     * 
     * @return sedeOccasionale
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.SedeOccasionale getSedeOccasionale() {
        return sedeOccasionale;
    }


    /**
     * Sets the sedeOccasionale value for this CorsoFP.
     * 
     * @param sedeOccasionale
     */
    public void setSedeOccasionale(it.csi.idf.idfapi.util.service.integration.aaep.SedeOccasionale sedeOccasionale) {
        this.sedeOccasionale = sedeOccasionale;
    }


    /**
     * Gets the oreStage value for this CorsoFP.
     * 
     * @return oreStage
     */
    public java.lang.String getOreStage() {
        return oreStage;
    }


    /**
     * Sets the oreStage value for this CorsoFP.
     * 
     * @param oreStage
     */
    public void setOreStage(java.lang.String oreStage) {
        this.oreStage = oreStage;
    }


    /**
     * Gets the numeroAllievi value for this CorsoFP.
     * 
     * @return numeroAllievi
     */
    public java.lang.String getNumeroAllievi() {
        return numeroAllievi;
    }


    /**
     * Sets the numeroAllievi value for this CorsoFP.
     * 
     * @param numeroAllievi
     */
    public void setNumeroAllievi(java.lang.String numeroAllievi) {
        this.numeroAllievi = numeroAllievi;
    }


    /**
     * Gets the listaIndirizzi value for this CorsoFP.
     * 
     * @return listaIndirizzi
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.ListaIndirizzi[] getListaIndirizzi() {
        return listaIndirizzi;
    }


    /**
     * Sets the listaIndirizzi value for this CorsoFP.
     * 
     * @param listaIndirizzi
     */
    public void setListaIndirizzi(it.csi.idf.idfapi.util.service.integration.aaep.ListaIndirizzi[] listaIndirizzi) {
        this.listaIndirizzi = listaIndirizzi;
    }


    /**
     * Gets the annoGestione value for this CorsoFP.
     * 
     * @return annoGestione
     */
    public java.lang.String getAnnoGestione() {
        return annoGestione;
    }


    /**
     * Sets the annoGestione value for this CorsoFP.
     * 
     * @param annoGestione
     */
    public void setAnnoGestione(java.lang.String annoGestione) {
        this.annoGestione = annoGestione;
    }


    /**
     * Gets the orePratica value for this CorsoFP.
     * 
     * @return orePratica
     */
    public java.lang.String getOrePratica() {
        return orePratica;
    }


    /**
     * Sets the orePratica value for this CorsoFP.
     * 
     * @param orePratica
     */
    public void setOrePratica(java.lang.String orePratica) {
        this.orePratica = orePratica;
    }


    /**
     * Gets the annoComparto value for this CorsoFP.
     * 
     * @return annoComparto
     */
    public java.lang.String getAnnoComparto() {
        return annoComparto;
    }


    /**
     * Sets the annoComparto value for this CorsoFP.
     * 
     * @param annoComparto
     */
    public void setAnnoComparto(java.lang.String annoComparto) {
        this.annoComparto = annoComparto;
    }


    /**
     * Gets the oreTeoria value for this CorsoFP.
     * 
     * @return oreTeoria
     */
    public java.lang.String getOreTeoria() {
        return oreTeoria;
    }


    /**
     * Sets the oreTeoria value for this CorsoFP.
     * 
     * @param oreTeoria
     */
    public void setOreTeoria(java.lang.String oreTeoria) {
        this.oreTeoria = oreTeoria;
    }


    /**
     * Gets the numTotPartiIter value for this CorsoFP.
     * 
     * @return numTotPartiIter
     */
    public java.lang.String getNumTotPartiIter() {
        return numTotPartiIter;
    }


    /**
     * Sets the numTotPartiIter value for this CorsoFP.
     * 
     * @param numTotPartiIter
     */
    public void setNumTotPartiIter(java.lang.String numTotPartiIter) {
        this.numTotPartiIter = numTotPartiIter;
    }


    /**
     * Gets the dataInizioCorso value for this CorsoFP.
     * 
     * @return dataInizioCorso
     */
    public java.lang.String getDataInizioCorso() {
        return dataInizioCorso;
    }


    /**
     * Sets the dataInizioCorso value for this CorsoFP.
     * 
     * @param dataInizioCorso
     */
    public void setDataInizioCorso(java.lang.String dataInizioCorso) {
        this.dataInizioCorso = dataInizioCorso;
    }


    /**
     * Gets the annoInizioCorso value for this CorsoFP.
     * 
     * @return annoInizioCorso
     */
    public java.lang.String getAnnoInizioCorso() {
        return annoInizioCorso;
    }


    /**
     * Sets the annoInizioCorso value for this CorsoFP.
     * 
     * @param annoInizioCorso
     */
    public void setAnnoInizioCorso(java.lang.String annoInizioCorso) {
        this.annoInizioCorso = annoInizioCorso;
    }


    /**
     * Gets the descrCausaleSoppressione value for this CorsoFP.
     * 
     * @return descrCausaleSoppressione
     */
    public java.lang.String getDescrCausaleSoppressione() {
        return descrCausaleSoppressione;
    }


    /**
     * Sets the descrCausaleSoppressione value for this CorsoFP.
     * 
     * @param descrCausaleSoppressione
     */
    public void setDescrCausaleSoppressione(java.lang.String descrCausaleSoppressione) {
        this.descrCausaleSoppressione = descrCausaleSoppressione;
    }


    /**
     * Gets the descrLineaInterventoPOR value for this CorsoFP.
     * 
     * @return descrLineaInterventoPOR
     */
    public java.lang.String getDescrLineaInterventoPOR() {
        return descrLineaInterventoPOR;
    }


    /**
     * Sets the descrLineaInterventoPOR value for this CorsoFP.
     * 
     * @param descrLineaInterventoPOR
     */
    public void setDescrLineaInterventoPOR(java.lang.String descrLineaInterventoPOR) {
        this.descrLineaInterventoPOR = descrLineaInterventoPOR;
    }


    /**
     * Gets the denominazione value for this CorsoFP.
     * 
     * @return denominazione
     */
    public java.lang.String getDenominazione() {
        return denominazione;
    }


    /**
     * Sets the denominazione value for this CorsoFP.
     * 
     * @param denominazione
     */
    public void setDenominazione(java.lang.String denominazione) {
        this.denominazione = denominazione;
    }


    /**
     * Gets the descrDocRegionale value for this CorsoFP.
     * 
     * @return descrDocRegionale
     */
    public java.lang.String getDescrDocRegionale() {
        return descrDocRegionale;
    }


    /**
     * Sets the descrDocRegionale value for this CorsoFP.
     * 
     * @param descrDocRegionale
     */
    public void setDescrDocRegionale(java.lang.String descrDocRegionale) {
        this.descrDocRegionale = descrDocRegionale;
    }


    /**
     * Gets the parteIterAttuale value for this CorsoFP.
     * 
     * @return parteIterAttuale
     */
    public java.lang.String getParteIterAttuale() {
        return parteIterAttuale;
    }


    /**
     * Sets the parteIterAttuale value for this CorsoFP.
     * 
     * @param parteIterAttuale
     */
    public void setParteIterAttuale(java.lang.String parteIterAttuale) {
        this.parteIterAttuale = parteIterAttuale;
    }


    /**
     * Gets the descrComparto value for this CorsoFP.
     * 
     * @return descrComparto
     */
    public java.lang.String getDescrComparto() {
        return descrComparto;
    }


    /**
     * Sets the descrComparto value for this CorsoFP.
     * 
     * @param descrComparto
     */
    public void setDescrComparto(java.lang.String descrComparto) {
        this.descrComparto = descrComparto;
    }


    /**
     * Gets the statoAvanzam value for this CorsoFP.
     * 
     * @return statoAvanzam
     */
    public java.lang.String getStatoAvanzam() {
        return statoAvanzam;
    }


    /**
     * Sets the statoAvanzam value for this CorsoFP.
     * 
     * @param statoAvanzam
     */
    public void setStatoAvanzam(java.lang.String statoAvanzam) {
        this.statoAvanzam = statoAvanzam;
    }


    /**
     * Gets the totaleOreIter value for this CorsoFP.
     * 
     * @return totaleOreIter
     */
    public java.lang.String getTotaleOreIter() {
        return totaleOreIter;
    }


    /**
     * Sets the totaleOreIter value for this CorsoFP.
     * 
     * @param totaleOreIter
     */
    public void setTotaleOreIter(java.lang.String totaleOreIter) {
        this.totaleOreIter = totaleOreIter;
    }


    /**
     * Gets the codComparto value for this CorsoFP.
     * 
     * @return codComparto
     */
    public java.lang.String getCodComparto() {
        return codComparto;
    }


    /**
     * Sets the codComparto value for this CorsoFP.
     * 
     * @param codComparto
     */
    public void setCodComparto(java.lang.String codComparto) {
        this.codComparto = codComparto;
    }


    /**
     * Gets the codCausaleSoppressione value for this CorsoFP.
     * 
     * @return codCausaleSoppressione
     */
    public java.lang.String getCodCausaleSoppressione() {
        return codCausaleSoppressione;
    }


    /**
     * Sets the codCausaleSoppressione value for this CorsoFP.
     * 
     * @param codCausaleSoppressione
     */
    public void setCodCausaleSoppressione(java.lang.String codCausaleSoppressione) {
        this.codCausaleSoppressione = codCausaleSoppressione;
    }


    /**
     * Gets the codSettore value for this CorsoFP.
     * 
     * @return codSettore
     */
    public java.lang.String getCodSettore() {
        return codSettore;
    }


    /**
     * Sets the codSettore value for this CorsoFP.
     * 
     * @param codSettore
     */
    public void setCodSettore(java.lang.String codSettore) {
        this.codSettore = codSettore;
    }


    /**
     * Gets the listaDestinatari value for this CorsoFP.
     * 
     * @return listaDestinatari
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.ListaDestinatari[] getListaDestinatari() {
        return listaDestinatari;
    }


    /**
     * Sets the listaDestinatari value for this CorsoFP.
     * 
     * @param listaDestinatari
     */
    public void setListaDestinatari(it.csi.idf.idfapi.util.service.integration.aaep.ListaDestinatari[] listaDestinatari) {
        this.listaDestinatari = listaDestinatari;
    }


    /**
     * Gets the codDocRegionale value for this CorsoFP.
     * 
     * @return codDocRegionale
     */
    public java.lang.String getCodDocRegionale() {
        return codDocRegionale;
    }


    /**
     * Sets the codDocRegionale value for this CorsoFP.
     * 
     * @param codDocRegionale
     */
    public void setCodDocRegionale(java.lang.String codDocRegionale) {
        this.codDocRegionale = codDocRegionale;
    }


    /**
     * Gets the tipoAttivita value for this CorsoFP.
     * 
     * @return tipoAttivita
     */
    public java.lang.String getTipoAttivita() {
        return tipoAttivita;
    }


    /**
     * Sets the tipoAttivita value for this CorsoFP.
     * 
     * @param tipoAttivita
     */
    public void setTipoAttivita(java.lang.String tipoAttivita) {
        this.tipoAttivita = tipoAttivita;
    }


    /**
     * Gets the annoSettore value for this CorsoFP.
     * 
     * @return annoSettore
     */
    public java.lang.String getAnnoSettore() {
        return annoSettore;
    }


    /**
     * Sets the annoSettore value for this CorsoFP.
     * 
     * @param annoSettore
     */
    public void setAnnoSettore(java.lang.String annoSettore) {
        this.annoSettore = annoSettore;
    }


    /**
     * Gets the codAzionePOR value for this CorsoFP.
     * 
     * @return codAzionePOR
     */
    public java.lang.String getCodAzionePOR() {
        return codAzionePOR;
    }


    /**
     * Sets the codAzionePOR value for this CorsoFP.
     * 
     * @param codAzionePOR
     */
    public void setCodAzionePOR(java.lang.String codAzionePOR) {
        this.codAzionePOR = codAzionePOR;
    }


    /**
     * Gets the descrAzionePOR value for this CorsoFP.
     * 
     * @return descrAzionePOR
     */
    public java.lang.String getDescrAzionePOR() {
        return descrAzionePOR;
    }


    /**
     * Sets the descrAzionePOR value for this CorsoFP.
     * 
     * @param descrAzionePOR
     */
    public void setDescrAzionePOR(java.lang.String descrAzionePOR) {
        this.descrAzionePOR = descrAzionePOR;
    }


    /**
     * Gets the dataFineCorso value for this CorsoFP.
     * 
     * @return dataFineCorso
     */
    public java.lang.String getDataFineCorso() {
        return dataFineCorso;
    }


    /**
     * Sets the dataFineCorso value for this CorsoFP.
     * 
     * @param dataFineCorso
     */
    public void setDataFineCorso(java.lang.String dataFineCorso) {
        this.dataFineCorso = dataFineCorso;
    }


    /**
     * Gets the codLineaInterventoPOR value for this CorsoFP.
     * 
     * @return codLineaInterventoPOR
     */
    public java.lang.String getCodLineaInterventoPOR() {
        return codLineaInterventoPOR;
    }


    /**
     * Sets the codLineaInterventoPOR value for this CorsoFP.
     * 
     * @param codLineaInterventoPOR
     */
    public void setCodLineaInterventoPOR(java.lang.String codLineaInterventoPOR) {
        this.codLineaInterventoPOR = codLineaInterventoPOR;
    }


    /**
     * Gets the tipoEntita value for this CorsoFP.
     * 
     * @return tipoEntita
     */
    public java.lang.String getTipoEntita() {
        return tipoEntita;
    }


    /**
     * Sets the tipoEntita value for this CorsoFP.
     * 
     * @param tipoEntita
     */
    public void setTipoEntita(java.lang.String tipoEntita) {
        this.tipoEntita = tipoEntita;
    }


    /**
     * Gets the progrCorso value for this CorsoFP.
     * 
     * @return progrCorso
     */
    public java.lang.String getProgrCorso() {
        return progrCorso;
    }


    /**
     * Sets the progrCorso value for this CorsoFP.
     * 
     * @param progrCorso
     */
    public void setProgrCorso(java.lang.String progrCorso) {
        this.progrCorso = progrCorso;
    }


    /**
     * Gets the codMisuraPOR value for this CorsoFP.
     * 
     * @return codMisuraPOR
     */
    public java.lang.String getCodMisuraPOR() {
        return codMisuraPOR;
    }


    /**
     * Sets the codMisuraPOR value for this CorsoFP.
     * 
     * @param codMisuraPOR
     */
    public void setCodMisuraPOR(java.lang.String codMisuraPOR) {
        this.codMisuraPOR = codMisuraPOR;
    }


    /**
     * Gets the descrMisuraPOR value for this CorsoFP.
     * 
     * @return descrMisuraPOR
     */
    public java.lang.String getDescrMisuraPOR() {
        return descrMisuraPOR;
    }


    /**
     * Sets the descrMisuraPOR value for this CorsoFP.
     * 
     * @param descrMisuraPOR
     */
    public void setDescrMisuraPOR(java.lang.String descrMisuraPOR) {
        this.descrMisuraPOR = descrMisuraPOR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CorsoFP)) return false;
        CorsoFP other = (CorsoFP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.progrAccorpamento==null && other.getProgrAccorpamento()==null) || 
             (this.progrAccorpamento!=null &&
              this.progrAccorpamento.equals(other.getProgrAccorpamento()))) &&
            ((this.descrSettore==null && other.getDescrSettore()==null) || 
             (this.descrSettore!=null &&
              this.descrSettore.equals(other.getDescrSettore()))) &&
            ((this.sedeOccasionale==null && other.getSedeOccasionale()==null) || 
             (this.sedeOccasionale!=null &&
              this.sedeOccasionale.equals(other.getSedeOccasionale()))) &&
            ((this.oreStage==null && other.getOreStage()==null) || 
             (this.oreStage!=null &&
              this.oreStage.equals(other.getOreStage()))) &&
            ((this.numeroAllievi==null && other.getNumeroAllievi()==null) || 
             (this.numeroAllievi!=null &&
              this.numeroAllievi.equals(other.getNumeroAllievi()))) &&
            ((this.listaIndirizzi==null && other.getListaIndirizzi()==null) || 
             (this.listaIndirizzi!=null &&
              java.util.Arrays.equals(this.listaIndirizzi, other.getListaIndirizzi()))) &&
            ((this.annoGestione==null && other.getAnnoGestione()==null) || 
             (this.annoGestione!=null &&
              this.annoGestione.equals(other.getAnnoGestione()))) &&
            ((this.orePratica==null && other.getOrePratica()==null) || 
             (this.orePratica!=null &&
              this.orePratica.equals(other.getOrePratica()))) &&
            ((this.annoComparto==null && other.getAnnoComparto()==null) || 
             (this.annoComparto!=null &&
              this.annoComparto.equals(other.getAnnoComparto()))) &&
            ((this.oreTeoria==null && other.getOreTeoria()==null) || 
             (this.oreTeoria!=null &&
              this.oreTeoria.equals(other.getOreTeoria()))) &&
            ((this.numTotPartiIter==null && other.getNumTotPartiIter()==null) || 
             (this.numTotPartiIter!=null &&
              this.numTotPartiIter.equals(other.getNumTotPartiIter()))) &&
            ((this.dataInizioCorso==null && other.getDataInizioCorso()==null) || 
             (this.dataInizioCorso!=null &&
              this.dataInizioCorso.equals(other.getDataInizioCorso()))) &&
            ((this.annoInizioCorso==null && other.getAnnoInizioCorso()==null) || 
             (this.annoInizioCorso!=null &&
              this.annoInizioCorso.equals(other.getAnnoInizioCorso()))) &&
            ((this.descrCausaleSoppressione==null && other.getDescrCausaleSoppressione()==null) || 
             (this.descrCausaleSoppressione!=null &&
              this.descrCausaleSoppressione.equals(other.getDescrCausaleSoppressione()))) &&
            ((this.descrLineaInterventoPOR==null && other.getDescrLineaInterventoPOR()==null) || 
             (this.descrLineaInterventoPOR!=null &&
              this.descrLineaInterventoPOR.equals(other.getDescrLineaInterventoPOR()))) &&
            ((this.denominazione==null && other.getDenominazione()==null) || 
             (this.denominazione!=null &&
              this.denominazione.equals(other.getDenominazione()))) &&
            ((this.descrDocRegionale==null && other.getDescrDocRegionale()==null) || 
             (this.descrDocRegionale!=null &&
              this.descrDocRegionale.equals(other.getDescrDocRegionale()))) &&
            ((this.parteIterAttuale==null && other.getParteIterAttuale()==null) || 
             (this.parteIterAttuale!=null &&
              this.parteIterAttuale.equals(other.getParteIterAttuale()))) &&
            ((this.descrComparto==null && other.getDescrComparto()==null) || 
             (this.descrComparto!=null &&
              this.descrComparto.equals(other.getDescrComparto()))) &&
            ((this.statoAvanzam==null && other.getStatoAvanzam()==null) || 
             (this.statoAvanzam!=null &&
              this.statoAvanzam.equals(other.getStatoAvanzam()))) &&
            ((this.totaleOreIter==null && other.getTotaleOreIter()==null) || 
             (this.totaleOreIter!=null &&
              this.totaleOreIter.equals(other.getTotaleOreIter()))) &&
            ((this.codComparto==null && other.getCodComparto()==null) || 
             (this.codComparto!=null &&
              this.codComparto.equals(other.getCodComparto()))) &&
            ((this.codCausaleSoppressione==null && other.getCodCausaleSoppressione()==null) || 
             (this.codCausaleSoppressione!=null &&
              this.codCausaleSoppressione.equals(other.getCodCausaleSoppressione()))) &&
            ((this.codSettore==null && other.getCodSettore()==null) || 
             (this.codSettore!=null &&
              this.codSettore.equals(other.getCodSettore()))) &&
            ((this.listaDestinatari==null && other.getListaDestinatari()==null) || 
             (this.listaDestinatari!=null &&
              java.util.Arrays.equals(this.listaDestinatari, other.getListaDestinatari()))) &&
            ((this.codDocRegionale==null && other.getCodDocRegionale()==null) || 
             (this.codDocRegionale!=null &&
              this.codDocRegionale.equals(other.getCodDocRegionale()))) &&
            ((this.tipoAttivita==null && other.getTipoAttivita()==null) || 
             (this.tipoAttivita!=null &&
              this.tipoAttivita.equals(other.getTipoAttivita()))) &&
            ((this.annoSettore==null && other.getAnnoSettore()==null) || 
             (this.annoSettore!=null &&
              this.annoSettore.equals(other.getAnnoSettore()))) &&
            ((this.codAzionePOR==null && other.getCodAzionePOR()==null) || 
             (this.codAzionePOR!=null &&
              this.codAzionePOR.equals(other.getCodAzionePOR()))) &&
            ((this.descrAzionePOR==null && other.getDescrAzionePOR()==null) || 
             (this.descrAzionePOR!=null &&
              this.descrAzionePOR.equals(other.getDescrAzionePOR()))) &&
            ((this.dataFineCorso==null && other.getDataFineCorso()==null) || 
             (this.dataFineCorso!=null &&
              this.dataFineCorso.equals(other.getDataFineCorso()))) &&
            ((this.codLineaInterventoPOR==null && other.getCodLineaInterventoPOR()==null) || 
             (this.codLineaInterventoPOR!=null &&
              this.codLineaInterventoPOR.equals(other.getCodLineaInterventoPOR()))) &&
            ((this.tipoEntita==null && other.getTipoEntita()==null) || 
             (this.tipoEntita!=null &&
              this.tipoEntita.equals(other.getTipoEntita()))) &&
            ((this.progrCorso==null && other.getProgrCorso()==null) || 
             (this.progrCorso!=null &&
              this.progrCorso.equals(other.getProgrCorso()))) &&
            ((this.codMisuraPOR==null && other.getCodMisuraPOR()==null) || 
             (this.codMisuraPOR!=null &&
              this.codMisuraPOR.equals(other.getCodMisuraPOR()))) &&
            ((this.descrMisuraPOR==null && other.getDescrMisuraPOR()==null) || 
             (this.descrMisuraPOR!=null &&
              this.descrMisuraPOR.equals(other.getDescrMisuraPOR())));
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
        if (getProgrAccorpamento() != null) {
            _hashCode += getProgrAccorpamento().hashCode();
        }
        if (getDescrSettore() != null) {
            _hashCode += getDescrSettore().hashCode();
        }
        if (getSedeOccasionale() != null) {
            _hashCode += getSedeOccasionale().hashCode();
        }
        if (getOreStage() != null) {
            _hashCode += getOreStage().hashCode();
        }
        if (getNumeroAllievi() != null) {
            _hashCode += getNumeroAllievi().hashCode();
        }
        if (getListaIndirizzi() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIndirizzi());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIndirizzi(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAnnoGestione() != null) {
            _hashCode += getAnnoGestione().hashCode();
        }
        if (getOrePratica() != null) {
            _hashCode += getOrePratica().hashCode();
        }
        if (getAnnoComparto() != null) {
            _hashCode += getAnnoComparto().hashCode();
        }
        if (getOreTeoria() != null) {
            _hashCode += getOreTeoria().hashCode();
        }
        if (getNumTotPartiIter() != null) {
            _hashCode += getNumTotPartiIter().hashCode();
        }
        if (getDataInizioCorso() != null) {
            _hashCode += getDataInizioCorso().hashCode();
        }
        if (getAnnoInizioCorso() != null) {
            _hashCode += getAnnoInizioCorso().hashCode();
        }
        if (getDescrCausaleSoppressione() != null) {
            _hashCode += getDescrCausaleSoppressione().hashCode();
        }
        if (getDescrLineaInterventoPOR() != null) {
            _hashCode += getDescrLineaInterventoPOR().hashCode();
        }
        if (getDenominazione() != null) {
            _hashCode += getDenominazione().hashCode();
        }
        if (getDescrDocRegionale() != null) {
            _hashCode += getDescrDocRegionale().hashCode();
        }
        if (getParteIterAttuale() != null) {
            _hashCode += getParteIterAttuale().hashCode();
        }
        if (getDescrComparto() != null) {
            _hashCode += getDescrComparto().hashCode();
        }
        if (getStatoAvanzam() != null) {
            _hashCode += getStatoAvanzam().hashCode();
        }
        if (getTotaleOreIter() != null) {
            _hashCode += getTotaleOreIter().hashCode();
        }
        if (getCodComparto() != null) {
            _hashCode += getCodComparto().hashCode();
        }
        if (getCodCausaleSoppressione() != null) {
            _hashCode += getCodCausaleSoppressione().hashCode();
        }
        if (getCodSettore() != null) {
            _hashCode += getCodSettore().hashCode();
        }
        if (getListaDestinatari() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaDestinatari());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaDestinatari(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodDocRegionale() != null) {
            _hashCode += getCodDocRegionale().hashCode();
        }
        if (getTipoAttivita() != null) {
            _hashCode += getTipoAttivita().hashCode();
        }
        if (getAnnoSettore() != null) {
            _hashCode += getAnnoSettore().hashCode();
        }
        if (getCodAzionePOR() != null) {
            _hashCode += getCodAzionePOR().hashCode();
        }
        if (getDescrAzionePOR() != null) {
            _hashCode += getDescrAzionePOR().hashCode();
        }
        if (getDataFineCorso() != null) {
            _hashCode += getDataFineCorso().hashCode();
        }
        if (getCodLineaInterventoPOR() != null) {
            _hashCode += getCodLineaInterventoPOR().hashCode();
        }
        if (getTipoEntita() != null) {
            _hashCode += getTipoEntita().hashCode();
        }
        if (getProgrCorso() != null) {
            _hashCode += getProgrCorso().hashCode();
        }
        if (getCodMisuraPOR() != null) {
            _hashCode += getCodMisuraPOR().hashCode();
        }
        if (getDescrMisuraPOR() != null) {
            _hashCode += getDescrMisuraPOR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CorsoFP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "CorsoFP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrAccorpamento");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrAccorpamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrSettore");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrSettore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sedeOccasionale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "sedeOccasionale"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "SedeOccasionale"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oreStage");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "oreStage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAllievi");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numeroAllievi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIndirizzi");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaIndirizzi"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaIndirizzi"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoGestione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoGestione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orePratica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "orePratica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoComparto");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoComparto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oreTeoria");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "oreTeoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numTotPartiIter");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numTotPartiIter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioCorso");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioCorso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoInizioCorso");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoInizioCorso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrCausaleSoppressione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrCausaleSoppressione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrLineaInterventoPOR");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrLineaInterventoPOR"));
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
        elemField.setFieldName("descrDocRegionale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrDocRegionale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteIterAttuale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "parteIterAttuale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrComparto");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComparto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statoAvanzam");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "statoAvanzam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totaleOreIter");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "totaleOreIter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codComparto");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codComparto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCausaleSoppressione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codCausaleSoppressione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codSettore");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codSettore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDestinatari");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaDestinatari"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaDestinatari"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codDocRegionale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codDocRegionale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoAttivita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "tipoAttivita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoSettore");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoSettore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codAzionePOR");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codAzionePOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrAzionePOR");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrAzionePOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineCorso");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataFineCorso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codLineaInterventoPOR");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codLineaInterventoPOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEntita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "tipoEntita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrCorso");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrCorso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codMisuraPOR");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codMisuraPOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrMisuraPOR");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrMisuraPOR"));
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
