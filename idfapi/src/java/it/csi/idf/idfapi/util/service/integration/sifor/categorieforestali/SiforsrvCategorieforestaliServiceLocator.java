/**
 * SiforsrvCategorieforestaliServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali;

public class SiforsrvCategorieforestaliServiceLocator extends org.apache.axis.client.Service implements SiforsrvCategorieforestaliService {

    public SiforsrvCategorieforestaliServiceLocator() {
    }


    public SiforsrvCategorieforestaliServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SiforsrvCategorieforestaliServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for siforsrvCategorieforestali
    private java.lang.String siforsrvCategorieforestali_address = "http://tst-applogic.reteunitaria.piemonte.it/siforsrvApplCategorieforestaliWsfad/services/siforsrvCategorieforestali";

    public java.lang.String getsiforsrvCategorieforestaliAddress() {
        return siforsrvCategorieforestali_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String siforsrvCategorieforestaliWSDDServiceName = "siforsrvCategorieforestali";

    public java.lang.String getsiforsrvCategorieforestaliWSDDServiceName() {
        return siforsrvCategorieforestaliWSDDServiceName;
    }

    public void setsiforsrvCategorieforestaliWSDDServiceName(java.lang.String name) {
        siforsrvCategorieforestaliWSDDServiceName = name;
    }

    public SiforsrvCategorieforestali_PortType getsiforsrvCategorieforestali() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(siforsrvCategorieforestali_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsiforsrvCategorieforestali(endpoint);
    }

    public SiforsrvCategorieforestali_PortType getsiforsrvCategorieforestali(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SiforsrvCategorieforestaliSoapBindingStub _stub = new SiforsrvCategorieforestaliSoapBindingStub(portAddress, this);
            _stub.setPortName(getsiforsrvCategorieforestaliWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsiforsrvCategorieforestaliEndpointAddress(java.lang.String address) {
        siforsrvCategorieforestali_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SiforsrvCategorieforestali_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                SiforsrvCategorieforestaliSoapBindingStub _stub = new SiforsrvCategorieforestaliSoapBindingStub(new java.net.URL(siforsrvCategorieforestali_address), this);
                _stub.setPortName(getsiforsrvCategorieforestaliWSDDServiceName());
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
        if ("siforsrvCategorieforestali".equals(inputPortName)) {
            return getsiforsrvCategorieforestali();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("categorieForestali", "siforsrvCategorieforestaliService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("categorieForestali", "siforsrvCategorieforestali"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("siforsrvCategorieforestali".equals(portName)) {
            setsiforsrvCategorieforestaliEndpointAddress(address);
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
