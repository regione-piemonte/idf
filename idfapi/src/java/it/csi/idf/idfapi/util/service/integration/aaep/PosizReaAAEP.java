/**
 * PosizReaAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class PosizReaAAEP  implements java.io.Serializable {
    private java.lang.String descrFontePosizRea;

    private java.lang.String NSediPosizRea;

    private java.lang.String siglaProvCCIAA;

    private java.lang.String idFontePosizRea;

    private java.lang.String numeroCCIAA;

    private java.lang.String flgSedeLegalePosizRea;

    public PosizReaAAEP() {
    }

    public PosizReaAAEP(
           java.lang.String descrFontePosizRea,
           java.lang.String NSediPosizRea,
           java.lang.String siglaProvCCIAA,
           java.lang.String idFontePosizRea,
           java.lang.String numeroCCIAA,
           java.lang.String flgSedeLegalePosizRea) {
           this.descrFontePosizRea = descrFontePosizRea;
           this.NSediPosizRea = NSediPosizRea;
           this.siglaProvCCIAA = siglaProvCCIAA;
           this.idFontePosizRea = idFontePosizRea;
           this.numeroCCIAA = numeroCCIAA;
           this.flgSedeLegalePosizRea = flgSedeLegalePosizRea;
    }


    /**
     * Gets the descrFontePosizRea value for this PosizReaAAEP.
     * 
     * @return descrFontePosizRea
     */
    public java.lang.String getDescrFontePosizRea() {
        return descrFontePosizRea;
    }


    /**
     * Sets the descrFontePosizRea value for this PosizReaAAEP.
     * 
     * @param descrFontePosizRea
     */
    public void setDescrFontePosizRea(java.lang.String descrFontePosizRea) {
        this.descrFontePosizRea = descrFontePosizRea;
    }


    /**
     * Gets the NSediPosizRea value for this PosizReaAAEP.
     * 
     * @return NSediPosizRea
     */
    public java.lang.String getNSediPosizRea() {
        return NSediPosizRea;
    }


    /**
     * Sets the NSediPosizRea value for this PosizReaAAEP.
     * 
     * @param NSediPosizRea
     */
    public void setNSediPosizRea(java.lang.String NSediPosizRea) {
        this.NSediPosizRea = NSediPosizRea;
    }


    /**
     * Gets the siglaProvCCIAA value for this PosizReaAAEP.
     * 
     * @return siglaProvCCIAA
     */
    public java.lang.String getSiglaProvCCIAA() {
        return siglaProvCCIAA;
    }


    /**
     * Sets the siglaProvCCIAA value for this PosizReaAAEP.
     * 
     * @param siglaProvCCIAA
     */
    public void setSiglaProvCCIAA(java.lang.String siglaProvCCIAA) {
        this.siglaProvCCIAA = siglaProvCCIAA;
    }


    /**
     * Gets the idFontePosizRea value for this PosizReaAAEP.
     * 
     * @return idFontePosizRea
     */
    public java.lang.String getIdFontePosizRea() {
        return idFontePosizRea;
    }


    /**
     * Sets the idFontePosizRea value for this PosizReaAAEP.
     * 
     * @param idFontePosizRea
     */
    public void setIdFontePosizRea(java.lang.String idFontePosizRea) {
        this.idFontePosizRea = idFontePosizRea;
    }


    /**
     * Gets the numeroCCIAA value for this PosizReaAAEP.
     * 
     * @return numeroCCIAA
     */
    public java.lang.String getNumeroCCIAA() {
        return numeroCCIAA;
    }


    /**
     * Sets the numeroCCIAA value for this PosizReaAAEP.
     * 
     * @param numeroCCIAA
     */
    public void setNumeroCCIAA(java.lang.String numeroCCIAA) {
        this.numeroCCIAA = numeroCCIAA;
    }


    /**
     * Gets the flgSedeLegalePosizRea value for this PosizReaAAEP.
     * 
     * @return flgSedeLegalePosizRea
     */
    public java.lang.String getFlgSedeLegalePosizRea() {
        return flgSedeLegalePosizRea;
    }


    /**
     * Sets the flgSedeLegalePosizRea value for this PosizReaAAEP.
     * 
     * @param flgSedeLegalePosizRea
     */
    public void setFlgSedeLegalePosizRea(java.lang.String flgSedeLegalePosizRea) {
        this.flgSedeLegalePosizRea = flgSedeLegalePosizRea;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PosizReaAAEP)) return false;
        PosizReaAAEP other = (PosizReaAAEP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descrFontePosizRea==null && other.getDescrFontePosizRea()==null) || 
             (this.descrFontePosizRea!=null &&
              this.descrFontePosizRea.equals(other.getDescrFontePosizRea()))) &&
            ((this.NSediPosizRea==null && other.getNSediPosizRea()==null) || 
             (this.NSediPosizRea!=null &&
              this.NSediPosizRea.equals(other.getNSediPosizRea()))) &&
            ((this.siglaProvCCIAA==null && other.getSiglaProvCCIAA()==null) || 
             (this.siglaProvCCIAA!=null &&
              this.siglaProvCCIAA.equals(other.getSiglaProvCCIAA()))) &&
            ((this.idFontePosizRea==null && other.getIdFontePosizRea()==null) || 
             (this.idFontePosizRea!=null &&
              this.idFontePosizRea.equals(other.getIdFontePosizRea()))) &&
            ((this.numeroCCIAA==null && other.getNumeroCCIAA()==null) || 
             (this.numeroCCIAA!=null &&
              this.numeroCCIAA.equals(other.getNumeroCCIAA()))) &&
            ((this.flgSedeLegalePosizRea==null && other.getFlgSedeLegalePosizRea()==null) || 
             (this.flgSedeLegalePosizRea!=null &&
              this.flgSedeLegalePosizRea.equals(other.getFlgSedeLegalePosizRea())));
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
        if (getDescrFontePosizRea() != null) {
            _hashCode += getDescrFontePosizRea().hashCode();
        }
        if (getNSediPosizRea() != null) {
            _hashCode += getNSediPosizRea().hashCode();
        }
        if (getSiglaProvCCIAA() != null) {
            _hashCode += getSiglaProvCCIAA().hashCode();
        }
        if (getIdFontePosizRea() != null) {
            _hashCode += getIdFontePosizRea().hashCode();
        }
        if (getNumeroCCIAA() != null) {
            _hashCode += getNumeroCCIAA().hashCode();
        }
        if (getFlgSedeLegalePosizRea() != null) {
            _hashCode += getFlgSedeLegalePosizRea().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PosizReaAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "PosizReaAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrFontePosizRea");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrFontePosizRea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NSediPosizRea");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NSediPosizRea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProvCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProvCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFontePosizRea");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idFontePosizRea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCCIAA");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "numeroCCIAA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgSedeLegalePosizRea");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgSedeLegalePosizRea"));
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
