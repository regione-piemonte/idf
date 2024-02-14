/**
 * JavaServiceDesc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public interface JavaServiceDesc extends java.rmi.Remote {
    public boolean isAlive() throws java.rmi.RemoteException;
    public boolean testResources() throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.CSIException;
    public it.csi.idf.idfapi.util.service.integration.aaep.Sede cercaPuntualeSedeAAEP(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc cercaPuntualePersonaRI(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd[] cercaPerFiltri(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7, java.lang.String in8, java.lang.String in9, java.lang.String in10, java.lang.String in11, java.lang.String in12, java.lang.String in13, java.lang.String in14) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc[] cercaPerFiltriCodFiscFonte(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP cercaPerCodiceFiscaleSILP(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP[] cercaUbicazioneSediSILP(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP cercaSedeSILP(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP cercaProspDisabSILP(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP cercaNAddettiAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP[] cercaPosizReaAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP[] cercaMovimAddettiAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP[] cercaUbicazioneSediAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata getPostaElettronicaCertificata(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale cercaPerCodiceFiscaleFormProf(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP cercaPerCodiceFiscaleAAEP(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere cercaPuntualeSedeInfoc(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere cercaPerCodiceFiscaleINFOCAMERE(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona cercaPerCodiceFiscalePersConCaricaFonteInfoc(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
    public it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica cercaPerCodiceFiscaleVariazioneAnagrafica(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException;
}
