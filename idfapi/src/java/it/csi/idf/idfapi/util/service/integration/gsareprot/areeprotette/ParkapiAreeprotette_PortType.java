/**
 * ParkapiAreeprotette_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.areeprotette;

public interface ParkapiAreeprotette_PortType extends java.rmi.Remote {
    public AreaProtetta[] cercaAreeProtettePerFiltri(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, UnrecoverableException, CSIException, SystemException, AreeProtetteException;
    public AreaProtetta[] cercaTutteLeAreeProtette() throws java.rmi.RemoteException, UnrecoverableException, CSIException, SystemException, AreeProtetteException;
    public Ricadenza[] determinaRicadenzaSuAreeProtettePerGeometriaGML(java.lang.String in0) throws java.rmi.RemoteException, UnrecoverableException, CSIException, SystemException, AreeProtetteException;
}
