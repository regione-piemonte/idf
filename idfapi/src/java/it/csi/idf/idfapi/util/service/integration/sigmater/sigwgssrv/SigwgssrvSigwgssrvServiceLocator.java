/**
 * SigwgssrvSigwgssrvServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public class SigwgssrvSigwgssrvServiceLocator extends org.apache.axis.client.Service implements it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrvService {

    public SigwgssrvSigwgssrvServiceLocator() {
    }


    public SigwgssrvSigwgssrvServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SigwgssrvSigwgssrvServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for sigwgssrvSigwgssrv
    private java.lang.String sigwgssrvSigwgssrv_address = "http://tst-applogic.reteunitaria.piemonte.it/sigwgssrvApplSigwgssrvWsfad/services/sigwgssrvSigwgssrv";

    public java.lang.String getsigwgssrvSigwgssrvAddress() {
        return sigwgssrvSigwgssrv_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String sigwgssrvSigwgssrvWSDDServiceName = "sigwgssrvSigwgssrv";

    public java.lang.String getsigwgssrvSigwgssrvWSDDServiceName() {
        return sigwgssrvSigwgssrvWSDDServiceName;
    }

    public void setsigwgssrvSigwgssrvWSDDServiceName(java.lang.String name) {
        sigwgssrvSigwgssrvWSDDServiceName = name;
    }

    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrv_PortType getsigwgssrvSigwgssrv() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(sigwgssrvSigwgssrv_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsigwgssrvSigwgssrv(endpoint);
    }

    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrv_PortType getsigwgssrvSigwgssrv(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrvSoapBindingStub _stub = new it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrvSoapBindingStub(portAddress, this);
            _stub.setPortName(getsigwgssrvSigwgssrvWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsigwgssrvSigwgssrvEndpointAddress(java.lang.String address) {
        sigwgssrvSigwgssrv_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrv_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrvSoapBindingStub _stub = new it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrvSoapBindingStub(new java.net.URL(sigwgssrvSigwgssrv_address), this);
                _stub.setPortName(getsigwgssrvSigwgssrvWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("sigwgssrvSigwgssrv".equals(inputPortName)) {
            return getsigwgssrvSigwgssrv();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "sigwgssrvSigwgssrvService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "sigwgssrvSigwgssrv"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("sigwgssrvSigwgssrv".equals(portName)) {
            setsigwgssrvSigwgssrvEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
