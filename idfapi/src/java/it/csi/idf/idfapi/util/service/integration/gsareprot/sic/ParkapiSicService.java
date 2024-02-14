/**
 * ParkapiSicService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.sic;

public interface ParkapiSicService extends javax.xml.rpc.Service {
    public java.lang.String getparkapiSicAddress();

    public ParkapiSic_PortType getparkapiSic() throws javax.xml.rpc.ServiceException;

    public ParkapiSic_PortType getparkapiSic(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
