/**
 * JavaServiceDescService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public interface JavaServiceDescService extends javax.xml.rpc.Service {
    public java.lang.String getAAEPCSIAddress();

    public it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc getAAEPCSI() throws javax.xml.rpc.ServiceException;

    public it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc getAAEPCSI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
