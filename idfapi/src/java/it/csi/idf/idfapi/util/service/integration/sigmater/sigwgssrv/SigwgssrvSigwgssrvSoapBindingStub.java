/**
 * SigwgssrvSigwgssrvSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public class SigwgssrvSigwgssrvSoapBindingStub extends org.apache.axis.client.Stub implements it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrv_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaParticelleByEnvelope");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "InfoParticella"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "cercaParticelleByEnvelopeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault2"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault7"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "PermissionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault3"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "AutorizzMancanteEnteException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault6"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ParInputValNonCorrettoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault4"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "OutputException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault5"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ParInputObblMancantiException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("hasSelfCheck");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "hasSelfCheckReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testResources");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "testResourcesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("selfCheck");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "CalledResource"), it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CalledResource[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "InvocationNode"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "selfCheckReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaParticelleByFilter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in5"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "in6"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "InfoParticella"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "cercaParticelleByFilterReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault2"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "UnrecoverableException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault7"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "PermissionException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault3"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "AutorizzMancanteEnteException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "SystemException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault6"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ParInputValNonCorrettoException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault4"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "OutputException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "fault5"),
                      "it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException",
                      new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ParInputObblMancantiException"), 
                      true
                     ));
        _operations[4] = oper;

    }

    public SigwgssrvSigwgssrvSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SigwgssrvSigwgssrvSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SigwgssrvSigwgssrvSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "CalledResource");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CalledResource.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "InvocationNode");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "Outcome");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.Outcome.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "ResourceType");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ResourceType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ArrayOf_tns1_InvocationNode");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://api.coopdiag.csi.it", "InvocationNode");
            qName2 = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "AutorizzMancanteEnteException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CommunicationException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CommunicationException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "InfoParticella");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "OutputException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ParInputObblMancantiException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "ParInputValNonCorrettoException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "PermissionException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "SystemException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "UnrecoverableException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "UserException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UserException.class;
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

    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] cercaParticelleByEnvelope(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "cercaParticelleByEnvelope"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean hasSelfCheck() throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "hasSelfCheck"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean testResources() throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "testResources"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode selfCheck(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CalledResource[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "selfCheck"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InvocationNode.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] cercaParticelleByFilter(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "cercaParticelleByFilter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2, in3, in4, in5, in6});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException) {
              throw (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
