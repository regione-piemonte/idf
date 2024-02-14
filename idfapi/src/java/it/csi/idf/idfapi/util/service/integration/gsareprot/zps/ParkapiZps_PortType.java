/**
 * ParkapiZps_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.zps;

public interface ParkapiZps_PortType extends java.rmi.Remote {
    public Zps[] cercaTutteLeZPS() throws java.rmi.RemoteException, UnrecoverableException, CSIException, SystemException, ZPSException;
    public Ricadenza[] determinaRicadenzaSuZpsPerGeometriaGML(java.lang.String in0) throws java.rmi.RemoteException, UnrecoverableException, CSIException, SystemException, ZPSException;
    public Zps[] cercaZpsPerFiltri(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, UnrecoverableException, CSIException, SystemException, ZPSException;
}
