/**
 * ParkapiZpsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.zps;

public interface ParkapiZpsService extends javax.xml.rpc.Service {
    public java.lang.String getparkapiZpsAddress();

    public ParkapiZps_PortType getparkapiZps() throws javax.xml.rpc.ServiceException;

    public ParkapiZps_PortType getparkapiZps(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
