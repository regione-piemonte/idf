/**
 * OrganicoProspDisabSILP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class OrganicoProspDisabSILP  implements java.io.Serializable {
    private java.lang.String dataUltAggiornamSILP;

    private java.lang.String idOrganicoProspDisab;

    private java.lang.String valoreOrganico;

    private java.lang.String dataUltAggiornam;

    private java.lang.String descrOrganicoProspDisab;

    public OrganicoProspDisabSILP() {
    }

    public OrganicoProspDisabSILP(
           java.lang.String dataUltAggiornamSILP,
           java.lang.String idOrganicoProspDisab,
           java.lang.String valoreOrganico,
           java.lang.String dataUltAggiornam,
           java.lang.String descrOrganicoProspDisab) {
           this.dataUltAggiornamSILP = dataUltAggiornamSILP;
           this.idOrganicoProspDisab = idOrganicoProspDisab;
           this.valoreOrganico = valoreOrganico;
           this.dataUltAggiornam = dataUltAggiornam;
           this.descrOrganicoProspDisab = descrOrganicoProspDisab;
    }


    /**
     * Gets the dataUltAggiornamSILP value for this OrganicoProspDisabSILP.
     * 
     * @return dataUltAggiornamSILP
     */
    public java.lang.String getDataUltAggiornamSILP() {
        return dataUltAggiornamSILP;
    }


    /**
     * Sets the dataUltAggiornamSILP value for this OrganicoProspDisabSILP.
     * 
     * @param dataUltAggiornamSILP
     */
    public void setDataUltAggiornamSILP(java.lang.String dataUltAggiornamSILP) {
        this.dataUltAggiornamSILP = dataUltAggiornamSILP;
    }


    /**
     * Gets the idOrganicoProspDisab value for this OrganicoProspDisabSILP.
     * 
     * @return idOrganicoProspDisab
     */
    public java.lang.String getIdOrganicoProspDisab() {
        return idOrganicoProspDisab;
    }


    /**
     * Sets the idOrganicoProspDisab value for this OrganicoProspDisabSILP.
     * 
     * @param idOrganicoProspDisab
     */
    public void setIdOrganicoProspDisab(java.lang.String idOrganicoProspDisab) {
        this.idOrganicoProspDisab = idOrganicoProspDisab;
    }


    /**
     * Gets the valoreOrganico value for this OrganicoProspDisabSILP.
     * 
     * @return valoreOrganico
     */
    public java.lang.String getValoreOrganico() {
        return valoreOrganico;
    }


    /**
     * Sets the valoreOrganico value for this OrganicoProspDisabSILP.
     * 
     * @param valoreOrganico
     */
    public void setValoreOrganico(java.lang.String valoreOrganico) {
        this.valoreOrganico = valoreOrganico;
    }


    /**
     * Gets the dataUltAggiornam value for this OrganicoProspDisabSILP.
     * 
     * @return dataUltAggiornam
     */
    public java.lang.String getDataUltAggiornam() {
        return dataUltAggiornam;
    }


    /**
     * Sets the dataUltAggiornam value for this OrganicoProspDisabSILP.
     * 
     * @param dataUltAggiornam
     */
    public void setDataUltAggiornam(java.lang.String dataUltAggiornam) {
        this.dataUltAggiornam = dataUltAggiornam;
    }


    /**
     * Gets the descrOrganicoProspDisab value for this OrganicoProspDisabSILP.
     * 
     * @return descrOrganicoProspDisab
     */
    public java.lang.String getDescrOrganicoProspDisab() {
        return descrOrganicoProspDisab;
    }


    /**
     * Sets the descrOrganicoProspDisab value for this OrganicoProspDisabSILP.
     * 
     * @param descrOrganicoProspDisab
     */
    public void setDescrOrganicoProspDisab(java.lang.String descrOrganicoProspDisab) {
        this.descrOrganicoProspDisab = descrOrganicoProspDisab;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrganicoProspDisabSILP)) return false;
        OrganicoProspDisabSILP other = (OrganicoProspDisabSILP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataUltAggiornamSILP==null && other.getDataUltAggiornamSILP()==null) || 
             (this.dataUltAggiornamSILP!=null &&
              this.dataUltAggiornamSILP.equals(other.getDataUltAggiornamSILP()))) &&
            ((this.idOrganicoProspDisab==null && other.getIdOrganicoProspDisab()==null) || 
             (this.idOrganicoProspDisab!=null &&
              this.idOrganicoProspDisab.equals(other.getIdOrganicoProspDisab()))) &&
            ((this.valoreOrganico==null && other.getValoreOrganico()==null) || 
             (this.valoreOrganico!=null &&
              this.valoreOrganico.equals(other.getValoreOrganico()))) &&
            ((this.dataUltAggiornam==null && other.getDataUltAggiornam()==null) || 
             (this.dataUltAggiornam!=null &&
              this.dataUltAggiornam.equals(other.getDataUltAggiornam()))) &&
            ((this.descrOrganicoProspDisab==null && other.getDescrOrganicoProspDisab()==null) || 
             (this.descrOrganicoProspDisab!=null &&
              this.descrOrganicoProspDisab.equals(other.getDescrOrganicoProspDisab())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDataUltAggiornamSILP() != null) {
            _hashCode += getDataUltAggiornamSILP().hashCode();
        }
        if (getIdOrganicoProspDisab() != null) {
            _hashCode += getIdOrganicoProspDisab().hashCode();
        }
        if (getValoreOrganico() != null) {
            _hashCode += getValoreOrganico().hashCode();
        }
        if (getDataUltAggiornam() != null) {
            _hashCode += getDataUltAggiornam().hashCode();
        }
        if (getDescrOrganicoProspDisab() != null) {
            _hashCode += getDescrOrganicoProspDisab().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrganicoProspDisabSILP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "OrganicoProspDisabSILP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltAggiornamSILP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataUltAggiornamSILP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganicoProspDisab");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idOrganicoProspDisab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoreOrganico");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "valoreOrganico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltAggiornam");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataUltAggiornam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrOrganicoProspDisab");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrOrganicoProspDisab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
