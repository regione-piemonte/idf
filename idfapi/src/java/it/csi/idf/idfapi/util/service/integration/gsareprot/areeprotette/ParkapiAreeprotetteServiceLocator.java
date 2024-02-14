/**
 * ParkapiAreeprotetteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.areeprotette;

public class ParkapiAreeprotetteServiceLocator extends org.apache.axis.client.Service implements ParkapiAreeprotetteService {

    public ParkapiAreeprotetteServiceLocator() {
    }


    public ParkapiAreeprotetteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ParkapiAreeprotetteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for parkapiAreeprotette
    private java.lang.String parkapiAreeprotette_address = "http://tst-applogic.reteunitaria.piemonte.it/parkapiApplAreeprotetteWsfad/services/parkapiAreeprotette";

    public java.lang.String getparkapiAreeprotetteAddress() {
        return parkapiAreeprotette_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String parkapiAreeprotetteWSDDServiceName = "parkapiAreeprotette";

    public java.lang.String getparkapiAreeprotetteWSDDServiceName() {
        return parkapiAreeprotetteWSDDServiceName;
    }

    public void setparkapiAreeprotetteWSDDServiceName(java.lang.String name) {
        parkapiAreeprotetteWSDDServiceName = name;
    }

    public ParkapiAreeprotette_PortType getparkapiAreeprotette() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(parkapiAreeprotette_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getparkapiAreeprotette(endpoint);
    }

    public ParkapiAreeprotette_PortType getparkapiAreeprotette(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ParkapiAreeprotetteSoapBindingStub _stub = new ParkapiAreeprotetteSoapBindingStub(portAddress, this);
            _stub.setPortName(getparkapiAreeprotetteWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setparkapiAreeprotetteEndpointAddress(java.lang.String address) {
        parkapiAreeprotette_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ParkapiAreeprotette_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ParkapiAreeprotetteSoapBindingStub _stub = new ParkapiAreeprotetteSoapBindingStub(new java.net.URL(parkapiAreeprotette_address), this);
                _stub.setPortName(getparkapiAreeprotetteWSDDServiceName());
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
        if ("parkapiAreeprotette".equals(inputPortName)) {
            return getparkapiAreeprotette();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("areeProtette", "parkapiAreeprotetteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("areeProtette", "parkapiAreeprotette"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("parkapiAreeprotette".equals(portName)) {
            setparkapiAreeprotetteEndpointAddress(address);
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
