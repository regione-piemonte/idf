/**
 * CodiciATECO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class CodiciATECO  implements java.io.Serializable {
    private java.lang.String descrizioneAteco;

    private java.lang.String annoDiRiferimento;

    private java.lang.String codiceAteco;

    public CodiciATECO() {
    }

    public CodiciATECO(
           java.lang.String descrizioneAteco,
           java.lang.String annoDiRiferimento,
           java.lang.String codiceAteco) {
           this.descrizioneAteco = descrizioneAteco;
           this.annoDiRiferimento = annoDiRiferimento;
           this.codiceAteco = codiceAteco;
    }


    /**
     * Gets the descrizioneAteco value for this CodiciATECO.
     * 
     * @return descrizioneAteco
     */
    public java.lang.String getDescrizioneAteco() {
        return descrizioneAteco;
    }


    /**
     * Sets the descrizioneAteco value for this CodiciATECO.
     * 
     * @param descrizioneAteco
     */
    public void setDescrizioneAteco(java.lang.String descrizioneAteco) {
        this.descrizioneAteco = descrizioneAteco;
    }


    /**
     * Gets the annoDiRiferimento value for this CodiciATECO.
     * 
     * @return annoDiRiferimento
     */
    public java.lang.String getAnnoDiRiferimento() {
        return annoDiRiferimento;
    }


    /**
     * Sets the annoDiRiferimento value for this CodiciATECO.
     * 
     * @param annoDiRiferimento
     */
    public void setAnnoDiRiferimento(java.lang.String annoDiRiferimento) {
        this.annoDiRiferimento = annoDiRiferimento;
    }


    /**
     * Gets the codiceAteco value for this CodiciATECO.
     * 
     * @return codiceAteco
     */
    public java.lang.String getCodiceAteco() {
        return codiceAteco;
    }


    /**
     * Sets the codiceAteco value for this CodiciATECO.
     * 
     * @param codiceAteco
     */
    public void setCodiceAteco(java.lang.String codiceAteco) {
        this.codiceAteco = codiceAteco;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodiciATECO)) return false;
        CodiciATECO other = (CodiciATECO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descrizioneAteco==null && other.getDescrizioneAteco()==null) || 
             (this.descrizioneAteco!=null &&
              this.descrizioneAteco.equals(other.getDescrizioneAteco()))) &&
            ((this.annoDiRiferimento==null && other.getAnnoDiRiferimento()==null) || 
             (this.annoDiRiferimento!=null &&
              this.annoDiRiferimento.equals(other.getAnnoDiRiferimento()))) &&
            ((this.codiceAteco==null && other.getCodiceAteco()==null) || 
             (this.codiceAteco!=null &&
              this.codiceAteco.equals(other.getCodiceAteco())));
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
        if (getDescrizioneAteco() != null) {
            _hashCode += getDescrizioneAteco().hashCode();
        }
        if (getAnnoDiRiferimento() != null) {
            _hashCode += getAnnoDiRiferimento().hashCode();
        }
        if (getCodiceAteco() != null) {
            _hashCode += getCodiceAteco().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodiciATECO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "CodiciATECO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneAteco");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrizioneAteco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoDiRiferimento");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "annoDiRiferimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAteco");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceAteco"));
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
