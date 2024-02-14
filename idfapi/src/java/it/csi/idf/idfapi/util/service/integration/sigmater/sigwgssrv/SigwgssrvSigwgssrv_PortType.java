/**
 * SigwgssrvSigwgssrv_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public interface SigwgssrvSigwgssrv_PortType extends java.rmi.Remote {
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] cercaParticelleByEnvelope(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException;
    public boolean hasSelfCheck() throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException;
    public boolean testResources() throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException;
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode selfCheck(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CalledResource[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException;
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] cercaParticelleByFilter(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException;
}
