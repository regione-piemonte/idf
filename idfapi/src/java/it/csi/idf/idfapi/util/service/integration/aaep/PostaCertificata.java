/**
 * PostaCertificata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class PostaCertificata  implements java.io.Serializable {
    private java.lang.String postaElettronicaCertificata;

    public PostaCertificata() {
    }

    public PostaCertificata(
           java.lang.String postaElettronicaCertificata) {
           this.postaElettronicaCertificata = postaElettronicaCertificata;
    }


    /**
     * Gets the postaElettronicaCertificata value for this PostaCertificata.
     * 
     * @return postaElettronicaCertificata
     */
    public java.lang.String getPostaElettronicaCertificata() {
        return postaElettronicaCertificata;
    }


    /**
     * Sets the postaElettronicaCertificata value for this PostaCertificata.
     * 
     * @param postaElettronicaCertificata
     */
    public void setPostaElettronicaCertificata(java.lang.String postaElettronicaCertificata) {
        this.postaElettronicaCertificata = postaElettronicaCertificata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PostaCertificata)) return false;
        PostaCertificata other = (PostaCertificata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.postaElettronicaCertificata==null && other.getPostaElettronicaCertificata()==null) || 
             (this.postaElettronicaCertificata!=null &&
              this.postaElettronicaCertificata.equals(other.getPostaElettronicaCertificata())));
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
        if (getPostaElettronicaCertificata() != null) {
            _hashCode += getPostaElettronicaCertificata().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PostaCertificata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "PostaCertificata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postaElettronicaCertificata");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "postaElettronicaCertificata"));
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
