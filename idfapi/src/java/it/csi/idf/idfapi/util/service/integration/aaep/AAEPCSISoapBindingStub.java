/**
 * AAEPCSISoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class AAEPCSISoapBindingStub extends org.apache.axis.client.Stub implements it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[21];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isAlive");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "isAliveReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testResources");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "testResourcesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.CSIException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "CSIException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPuntualeSedeAAEP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "Sede"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.Sede.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPuntualeSedeAAEPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPuntualePersonaRI");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "PersonaRIInfoc"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPuntualePersonaRIReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerFiltri");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in5"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in6"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in7"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in8"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in9"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in10"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in11"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in12"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in13"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in14"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaAttEconProd"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerFiltriReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerFiltriCodFiscFonte");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersonaCaricaInfoc"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerFiltriCodFiscFonteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerCodiceFiscaleSILP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaSILP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleSILPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaUbicazioneSediSILP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ProvinciaSILP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaUbicazioneSediSILPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaSedeSILP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "SedeSILP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaSedeSILPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaProspDisabSILP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ProspDisabSILP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaProspDisabSILPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaNAddettiAAEP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:AAEPCSI", "FiltroRicerca"), it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "NAddettiAAEP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaNAddettiAAEPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPosizReaAAEP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:AAEPCSI", "FiltroRicerca"), it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "PosizReaAAEP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPosizReaAAEPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaMovimAddettiAAEP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:AAEPCSI", "FiltroRicerca"), it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "MovimAddettiAAEP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaMovimAddettiAAEPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaUbicazioneSediAAEP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:AAEPCSI", "FiltroRicerca"), it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ProvinciaAAEP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaUbicazioneSediAAEPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPostaElettronicaCertificata");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "PostaCertificata"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "getPostaElettronicaCertificataReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerCodiceFiscaleFormProf");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "OperatoreFormazioneProfessionale"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleFormProfReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerCodiceFiscaleAAEP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaAAEP"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleAAEPReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPuntualeSedeInfoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "SedeInfocamere"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPuntualeSedeInfocReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerCodiceFiscaleINFOCAMERE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ImpresaInfocamere"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleINFOCAMEREReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerCodiceFiscalePersConCaricaFonteInfoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersona"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscalePersConCaricaFonteInfocReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cercaPerCodiceFiscaleVariazioneAnagrafica");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:AAEPCSI", "in3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaVariazioneAnagrafica"));
        oper.setReturnClass(it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleVariazioneAnagraficaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:AAEPCSI", "fault1"),
                      "it.csi.idf.idfapi.util.service.integration.aaep.UserException",
                      new javax.xml.namespace.QName("urn:AAEPCSI", "UserException"), 
                      true
                     ));
        _operations[20] = oper;

    }

    public AAEPCSISoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AAEPCSISoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AAEPCSISoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AlboArtigInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AlboArtigInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AlboRuoloInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AlboRuoloInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAlboArtigInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AlboArtigInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AlboArtigInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAlboRuoloInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AlboRuoloInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AlboRuoloInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAtecoRI1991Infoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AtecoRI1991Infoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI1991Infoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAtecoRI2002Infoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AtecoRI2002Infoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI2002Infoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAtecoRI2007Infoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AtecoRI2007Infoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI2007Infoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAttivIstatInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AttivIstatInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AttivIstatInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfAttivitaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AttivitaInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AttivitaInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfCarica");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.Carica[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "Carica");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfCaricaPersonaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CaricaPersonaInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CaricaPersonaInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfCodiciATECO");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CodiciATECO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CodiciATECO");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfComponenteFormazioneProfessionale");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ComponenteFormazioneProfessionale[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ComponenteFormazioneProfessionale");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfComuneAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ComuneAAEP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ComuneAAEP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfComuneSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ComuneSILP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ComuneSILP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfCorsoFP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CorsoFP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CorsoFP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfDenAttivInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.DenAttivInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "DenAttivInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfFallimentoPersonaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.FallimentoPersonaInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "FallimentoPersonaInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaDestinatari");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaDestinatari[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaDestinatari");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaIndirizzi");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaIndirizzi[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaIndirizzi");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaPersoneRIInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaPersoneRIInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersoneRIInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaProspDisabSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaProspDisabSILP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaProspDisabSILP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaSediAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediAAEP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaSediInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaSediInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfListaSediSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaSediSILP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediSILP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfOggSocialeInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.OggSocialeInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "OggSocialeInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfOrganicoProspDisabSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.OrganicoProspDisabSILP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "OrganicoProspDisabSILP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfPersona");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.Persona[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "Persona");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfProcConcorsInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ProcConcorsInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ProcConcorsInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfReferenteSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ReferenteSILP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ReferenteSILP");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfSezSpecInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.SezSpecInfoc[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "SezSpecInfoc");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ArrayOfVariazioneAnagrafica");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.VariazioneAnagrafica[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "VariazioneAnagrafica");
            qName2 = new javax.xml.namespace.QName("urn:AAEPCSI", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI1991Infoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AtecoRI1991Infoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI2002Infoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AtecoRI2002Infoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI2007Infoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AtecoRI2007Infoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AttivIstatInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AttivIstatInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AttivitaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AttivitaInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "AziendaVariazioneAnagrafica");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "Carica");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.Carica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CaricaPersonaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CaricaPersonaInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CodiciATECO");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CodiciATECO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ComponenteFormazioneProfessionale");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ComponenteFormazioneProfessionale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ComuneAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ComuneAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ComuneSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ComuneSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CorsoFP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CorsoFP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "CSIException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.CSIException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "DatoCostitutivoInfocamere");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.DatoCostitutivoInfocamere.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "DenAttivInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.DenAttivInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "FallimentoPersonaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.FallimentoPersonaInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "FiltroRicerca");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ImpresaInfocamere");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaAttEconProd");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaDestinatari");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaDestinatari.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaIndirizzi");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaIndirizzi.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersona");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersonaCaricaInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersoneRIInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaPersoneRIInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaProspDisabSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaProspDisabSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaSediInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ListaSediSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "MovimAddettiAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "NAddettiAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "OggSocialeInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.OggSocialeInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "OperatoreFormazioneProfessionale");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "OrganicoProspDisabSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.OrganicoProspDisabSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "Persona");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.Persona.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "PersonaRIInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "PosizReaAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "PostaCertificata");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ProcConcorsInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ProcConcorsInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ProspDisabSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ProvinciaAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ProvinciaSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "RappresentanteLegaleAAEP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.RappresentanteLegaleAAEP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "ReferenteSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.ReferenteSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "Sede");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.Sede.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "SedeInfocamere");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "SedeOccasionale");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.SedeOccasionale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "SedeSILP");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "SezSpecInfoc");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.SezSpecInfoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "UserException");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.UserException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:AAEPCSI", "VariazioneAnagrafica");
            cachedSerQNames.add(qName);
            cls = it.csi.idf.idfapi.util.service.integration.aaep.VariazioneAnagrafica.class;
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

    public boolean isAlive() throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "isAlive"));

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
  throw axisFaultException;
}
    }

    public boolean testResources() throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.CSIException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "testResources"));

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
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.CSIException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.CSIException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.Sede cercaPuntualeSedeAAEP(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPuntualeSedeAAEP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.Sede) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.Sede) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.Sede.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc cercaPuntualePersonaRI(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPuntualePersonaRI"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.PersonaRIInfoc.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd[] cercaPerFiltri(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7, java.lang.String in8, java.lang.String in9, java.lang.String in10, java.lang.String in11, java.lang.String in12, java.lang.String in13, java.lang.String in14) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
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
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerFiltri"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc[] cercaPerFiltriCodFiscFonte(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerFiltriCodFiscFonte"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ListaPersonaCaricaInfoc[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP cercaPerCodiceFiscaleSILP(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleSILP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.AziendaSILP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP[] cercaUbicazioneSediSILP(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaUbicazioneSediSILP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaSILP[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP cercaSedeSILP(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaSedeSILP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.SedeSILP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP cercaProspDisabSILP(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaProspDisabSILP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ProspDisabSILP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP cercaNAddettiAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaNAddettiAAEP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.NAddettiAAEP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP[] cercaPosizReaAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPosizReaAAEP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.PosizReaAAEP[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP[] cercaMovimAddettiAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaMovimAddettiAAEP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.MovimAddettiAAEP[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP[] cercaUbicazioneSediAAEP(it.csi.idf.idfapi.util.service.integration.aaep.FiltroRicerca[] in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaUbicazioneSediAAEP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ProvinciaAAEP[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata getPostaElettronicaCertificata(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "getPostaElettronicaCertificata"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.PostaCertificata.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale cercaPerCodiceFiscaleFormProf(java.lang.String in0) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleFormProf"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.OperatoreFormazioneProfessionale.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP cercaPerCodiceFiscaleAAEP(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleAAEP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere cercaPuntualeSedeInfoc(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPuntualeSedeInfoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.SedeInfocamere.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere cercaPerCodiceFiscaleINFOCAMERE(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleINFOCAMERE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2, in3, in4});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ImpresaInfocamere.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona cercaPerCodiceFiscalePersConCaricaFonteInfoc(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscalePersConCaricaFonteInfoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.ListaPersona.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica cercaPerCodiceFiscaleVariazioneAnagrafica(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException, it.csi.idf.idfapi.util.service.integration.aaep.UserException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:AAEPCSI", "cercaPerCodiceFiscaleVariazioneAnagrafica"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {in0, in1, in2, in3});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica) org.apache.axis.utils.JavaUtils.convert(_resp, it.csi.idf.idfapi.util.service.integration.aaep.AziendaVariazioneAnagrafica.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof it.csi.idf.idfapi.util.service.integration.aaep.UserException) {
              throw (it.csi.idf.idfapi.util.service.integration.aaep.UserException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
