/**
 * SiforsrvCategorieforestaliSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali;

public class SiforsrvCategorieforestaliSoapBindingStub extends org.apache.axis.client.Stub implements SiforsrvCategorieforestali_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("determinaRicadenzaSuCategoriaForestalePerGeometriaGML");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("categorieForestali", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("categorieForestali", "Ricadenza"));
        oper.setReturnClass(Ricadenza[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("categorieForestali", "determinaRicadenzaSuCategoriaForestalePerGeometriaGMLReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault2"),
                      "UnrecoverableException",
                      new javax.xml.namespace.QName("categorieForestali", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault3"),
                      "CategoriaForestaleException",
                      new javax.xml.namespace.QName("categorieForestali", "CategoriaForestaleException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault"),
                      "CSIException",
                      new javax.xml.namespace.QName("categorieForestali", "CSIException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault1"),
                      "SystemException",
                      new javax.xml.namespace.QName("categorieForestali", "SystemException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaTutteCategorieForestali");
        oper.setReturnType(new javax.xml.namespace.QName("categorieForestali", "CategoriaForestale"));
        oper.setReturnClass(CategoriaForestale[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("categorieForestali", "cercaTutteCategorieForestaliReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault2"),
                      "UnrecoverableException",
                      new javax.xml.namespace.QName("categorieForestali", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault3"),
                      "CategoriaForestaleException",
                      new javax.xml.namespace.QName("categorieForestali", "CategoriaForestaleException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault"),
                      "CSIException",
                      new javax.xml.namespace.QName("categorieForestali", "CSIException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault1"),
                      "SystemException",
                      new javax.xml.namespace.QName("categorieForestali", "SystemException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaCategoriaForestalePerFiltri");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("categorieForestali", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("categorieForestali", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("categorieForestali", "CategoriaForestale"));
        oper.setReturnClass(CategoriaForestale[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("categorieForestali", "cercaCategoriaForestalePerFiltriReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault2"),
                      "UnrecoverableException",
                      new javax.xml.namespace.QName("categorieForestali", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault3"),
                      "CategoriaForestaleException",
                      new javax.xml.namespace.QName("categorieForestali", "CategoriaForestaleException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault"),
                      "CSIException",
                      new javax.xml.namespace.QName("categorieForestali", "CSIException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("categorieForestali", "fault1"),
                      "SystemException",
                      new javax.xml.namespace.QName("categorieForestali", "SystemException"), 
                      true
                     ));
        _operations[2] = oper;

    }

    public SiforsrvCategorieforestaliSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SiforsrvCategorieforestaliSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SiforsrvCategorieforestaliSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("categorieForestali", "CategoriaForestale");
            cachedSerQNames.add(qName);
            cls = CategoriaForestale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("categorieForestali", "CategoriaForestaleException");
            cachedSerQNames.add(qName);
            cls = CategoriaForestaleException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("categorieForestali", "CSIException");
            cachedSerQNames.add(qName);
            cls = CSIException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("categorieForestali", "Ricadenza");
            cachedSerQNames.add(qName);
            cls = Ricadenza.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("categorieForestali", "SystemException");
            cachedSerQNames.add(qName);
            cls = SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("categorieForestali", "UnrecoverableException");
            cachedSerQNames.add(qName);
            cls = UnrecoverableException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("categorieForestali", "UserException");
            cachedSerQNames.add(qName);
            cls = UserException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public Ricadenza[] determinaRicadenzaSuCategoriaForestalePerGeometriaGML(java.lang.String in0) throws java.rmi.RemoteException, UnrecoverableException, CategoriaForestaleException, CSIException, SystemException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("categorieForestali", "determinaRicadenzaSuCategoriaForestalePerGeometriaGML"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (Ricadenza[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (Ricadenza[]) org.apache.axis.utils.JavaUtils.convert(_resp, Ricadenza[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof UnrecoverableException) {
              throw (UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof CategoriaForestaleException) {
              throw (CategoriaForestaleException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof CSIException) {
              throw (CSIException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof SystemException) {
              throw (SystemException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public CategoriaForestale[] cercaTutteCategorieForestali() throws java.rmi.RemoteException, UnrecoverableException, CategoriaForestaleException, CSIException, SystemException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("categorieForestali", "cercaTutteCategorieForestali"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (CategoriaForestale[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (CategoriaForestale[]) org.apache.axis.utils.JavaUtils.convert(_resp, CategoriaForestale[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof UnrecoverableException) {
              throw (UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof CategoriaForestaleException) {
              throw (CategoriaForestaleException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof CSIException) {
              throw (CSIException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof SystemException) {
              throw (SystemException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public CategoriaForestale[] cercaCategoriaForestalePerFiltri(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, UnrecoverableException, CategoriaForestaleException, CSIException, SystemException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("categorieForestali", "cercaCategoriaForestalePerFiltri"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (CategoriaForestale[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (CategoriaForestale[]) org.apache.axis.utils.JavaUtils.convert(_resp, CategoriaForestale[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof UnrecoverableException) {
              throw (UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof CategoriaForestaleException) {
              throw (CategoriaForestaleException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof CSIException) {
              throw (CSIException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof SystemException) {
              throw (SystemException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
