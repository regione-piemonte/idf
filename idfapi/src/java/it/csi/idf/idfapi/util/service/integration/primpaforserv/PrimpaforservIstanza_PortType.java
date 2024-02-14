/**
 * PrimpaforservIstanza_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.primpaforserv;

public interface PrimpaforservIstanza_PortType extends java.rmi.Remote {
    public it.csi.idf.idfapi.util.service.integration.primpaforserv.Istanza[] cercaIstanzeForestaPerCodFiscaleTipoComunicazioneStatoAnno(java.lang.String in0, java.lang.Integer in1, java.lang.Integer in2, java.lang.Integer in3) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.primpaforserv.UnrecoverableException, it.csi.idf.idfapi.util.service.integration.primpaforserv.CSIException, it.csi.idf.idfapi.util.service.integration.primpaforserv.SystemException, it.csi.idf.idfapi.util.service.integration.primpaforserv.CampoObbligatorioException;
    public it.csi.idf.idfapi.util.service.integration.primpaforserv.Istanza cercaIstanzePerNumeroIstanza(java.lang.Integer in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.primpaforserv.UnrecoverableException, it.csi.idf.idfapi.util.service.integration.primpaforserv.CSIException, it.csi.idf.idfapi.util.service.integration.primpaforserv.SystemException, it.csi.idf.idfapi.util.service.integration.primpaforserv.CampoObbligatorioException;
}
