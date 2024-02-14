/**
 * PrimpaforservIstanzaServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.primpaforserv;

public class PrimpaforservIstanzaServiceLocator extends org.apache.axis.client.Service implements it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanzaService {

    public PrimpaforservIstanzaServiceLocator() {
    }


    public PrimpaforservIstanzaServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PrimpaforservIstanzaServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for primpaforservIstanza
    private java.lang.String primpaforservIstanza_address = "http://tst-applogic.reteunitaria.piemonte.it/primpaforservApplIstanzaWsfad/services/primpaforservIstanza";

    public java.lang.String getprimpaforservIstanzaAddress() {
        return primpaforservIstanza_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String primpaforservIstanzaWSDDServiceName = "primpaforservIstanza";

    public java.lang.String getprimpaforservIstanzaWSDDServiceName() {
        return primpaforservIstanzaWSDDServiceName;
    }

    public void setprimpaforservIstanzaWSDDServiceName(java.lang.String name) {
        primpaforservIstanzaWSDDServiceName = name;
    }

    public it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanza_PortType getprimpaforservIstanza() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(primpaforservIstanza_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getprimpaforservIstanza(endpoint);
    }

    public it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanza_PortType getprimpaforservIstanza(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanzaSoapBindingStub _stub = new it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanzaSoapBindingStub(portAddress, this);
            _stub.setPortName(getprimpaforservIstanzaWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setprimpaforservIstanzaEndpointAddress(java.lang.String address) {
        primpaforservIstanza_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanza_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanzaSoapBindingStub _stub = new it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanzaSoapBindingStub(new java.net.URL(primpaforservIstanza_address), this);
                _stub.setPortName(getprimpaforservIstanzaWSDDServiceName());
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
        if ("primpaforservIstanza".equals(inputPortName)) {
            return getprimpaforservIstanza();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("primpa_istanze", "primpaforservIstanzaService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("primpa_istanze", "primpaforservIstanza"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("primpaforservIstanza".equals(portName)) {
            setprimpaforservIstanzaEndpointAddress(address);
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
