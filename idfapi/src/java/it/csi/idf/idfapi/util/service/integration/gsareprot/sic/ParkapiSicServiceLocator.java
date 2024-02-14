/**
 * ParkapiSicServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.sic;

public class ParkapiSicServiceLocator extends org.apache.axis.client.Service implements ParkapiSicService {

    public ParkapiSicServiceLocator() {
    }


    public ParkapiSicServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ParkapiSicServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for parkapiSic
    private java.lang.String parkapiSic_address = "http://tst-applogic.reteunitaria.piemonte.it/parkapiApplSicWsfad/services/parkapiSic";

    public java.lang.String getparkapiSicAddress() {
        return parkapiSic_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String parkapiSicWSDDServiceName = "parkapiSic";

    public java.lang.String getparkapiSicWSDDServiceName() {
        return parkapiSicWSDDServiceName;
    }

    public void setparkapiSicWSDDServiceName(java.lang.String name) {
        parkapiSicWSDDServiceName = name;
    }

    public ParkapiSic_PortType getparkapiSic() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(parkapiSic_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getparkapiSic(endpoint);
    }

    public ParkapiSic_PortType getparkapiSic(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ParkapiSicSoapBindingStub _stub = new ParkapiSicSoapBindingStub(portAddress, this);
            _stub.setPortName(getparkapiSicWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setparkapiSicEndpointAddress(java.lang.String address) {
        parkapiSic_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ParkapiSic_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ParkapiSicSoapBindingStub _stub = new ParkapiSicSoapBindingStub(new java.net.URL(parkapiSic_address), this);
                _stub.setPortName(getparkapiSicWSDDServiceName());
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
        if ("parkapiSic".equals(inputPortName)) {
            return getparkapiSic();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("sic", "parkapiSicService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("sic", "parkapiSic"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("parkapiSic".equals(portName)) {
            setparkapiSicEndpointAddress(address);
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
