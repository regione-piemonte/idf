/**
 * JavaServiceDescServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class JavaServiceDescServiceLocator extends org.apache.axis.client.Service implements it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDescService {

    public JavaServiceDescServiceLocator() {
    }


    public JavaServiceDescServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public JavaServiceDescServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AAEPCSI
    private java.lang.String AAEPCSI_address = "http://tst-api-ent.ecosis.csi.it:80/api/aaepsv_wsfad_wsi/1.0";

    public java.lang.String getAAEPCSIAddress() {
        return AAEPCSI_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AAEPCSIWSDDServiceName = "AAEPCSI";

    public java.lang.String getAAEPCSIWSDDServiceName() {
        return AAEPCSIWSDDServiceName;
    }

    public void setAAEPCSIWSDDServiceName(java.lang.String name) {
        AAEPCSIWSDDServiceName = name;
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc getAAEPCSI() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
        	
            endpoint = new java.net.URL(AAEPCSI_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAAEPCSI(endpoint);
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc getAAEPCSI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.idf.idfapi.util.service.integration.aaep.AAEPCSISoapBindingStub _stub = new it.csi.idf.idfapi.util.service.integration.aaep.AAEPCSISoapBindingStub(portAddress, this);
            _stub.setPortName(getAAEPCSIWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAAEPCSIEndpointAddress(java.lang.String address) {
        AAEPCSI_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.idf.idfapi.util.service.integration.aaep.AAEPCSISoapBindingStub _stub = new it.csi.idf.idfapi.util.service.integration.aaep.AAEPCSISoapBindingStub(new java.net.URL(AAEPCSI_address), this);
                _stub.setPortName(getAAEPCSIWSDDServiceName());
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
        if ("AAEPCSI".equals(inputPortName)) {
            return getAAEPCSI();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:AAEPCSI", "JavaServiceDescService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:AAEPCSI", "AAEPCSI"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AAEPCSI".equals(portName)) {
            setAAEPCSIEndpointAddress(address);
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
