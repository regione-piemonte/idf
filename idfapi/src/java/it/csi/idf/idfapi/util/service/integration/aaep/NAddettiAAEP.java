/**
 * NAddettiAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class NAddettiAAEP  implements java.io.Serializable {
    private java.lang.String NAddetti;

    private java.lang.String descrFonteNAddetti;

    private java.lang.String descrFonteAzienda;

    private java.lang.String idFonteAzienda;

    private java.lang.String formatoNAddetti;

    private java.lang.String idFonteNAddetti;

    public NAddettiAAEP() {
    }

    public NAddettiAAEP(
           java.lang.String NAddetti,
           java.lang.String descrFonteNAddetti,
           java.lang.String descrFonteAzienda,
           java.lang.String idFonteAzienda,
           java.lang.String formatoNAddetti,
           java.lang.String idFonteNAddetti) {
           this.NAddetti = NAddetti;
           this.descrFonteNAddetti = descrFonteNAddetti;
           this.descrFonteAzienda = descrFonteAzienda;
           this.idFonteAzienda = idFonteAzienda;
           this.formatoNAddetti = formatoNAddetti;
           this.idFonteNAddetti = idFonteNAddetti;
    }


    /**
     * Gets the NAddetti value for this NAddettiAAEP.
     * 
     * @return NAddetti
     */
    public java.lang.String getNAddetti() {
        return NAddetti;
    }


    /**
     * Sets the NAddetti value for this NAddettiAAEP.
     * 
     * @param NAddetti
     */
    public void setNAddetti(java.lang.String NAddetti) {
        this.NAddetti = NAddetti;
    }


    /**
     * Gets the descrFonteNAddetti value for this NAddettiAAEP.
     * 
     * @return descrFonteNAddetti
     */
    public java.lang.String getDescrFonteNAddetti() {
        return descrFonteNAddetti;
    }


    /**
     * Sets the descrFonteNAddetti value for this NAddettiAAEP.
     * 
     * @param descrFonteNAddetti
     */
    public void setDescrFonteNAddetti(java.lang.String descrFonteNAddetti) {
        this.descrFonteNAddetti = descrFonteNAddetti;
    }


    /**
     * Gets the descrFonteAzienda value for this NAddettiAAEP.
     * 
     * @return descrFonteAzienda
     */
    public java.lang.String getDescrFonteAzienda() {
        return descrFonteAzienda;
    }


    /**
     * Sets the descrFonteAzienda value for this NAddettiAAEP.
     * 
     * @param descrFonteAzienda
     */
    public void setDescrFonteAzienda(java.lang.String descrFonteAzienda) {
        this.descrFonteAzienda = descrFonteAzienda;
    }


    /**
     * Gets the idFonteAzienda value for this NAddettiAAEP.
     * 
     * @return idFonteAzienda
     */
    public java.lang.String getIdFonteAzienda() {
        return idFonteAzienda;
    }


    /**
     * Sets the idFonteAzienda value for this NAddettiAAEP.
     * 
     * @param idFonteAzienda
     */
    public void setIdFonteAzienda(java.lang.String idFonteAzienda) {
        this.idFonteAzienda = idFonteAzienda;
    }


    /**
     * Gets the formatoNAddetti value for this NAddettiAAEP.
     * 
     * @return formatoNAddetti
     */
    public java.lang.String getFormatoNAddetti() {
        return formatoNAddetti;
    }


    /**
     * Sets the formatoNAddetti value for this NAddettiAAEP.
     * 
     * @param formatoNAddetti
     */
    public void setFormatoNAddetti(java.lang.String formatoNAddetti) {
        this.formatoNAddetti = formatoNAddetti;
    }


    /**
     * Gets the idFonteNAddetti value for this NAddettiAAEP.
     * 
     * @return idFonteNAddetti
     */
    public java.lang.String getIdFonteNAddetti() {
        return idFonteNAddetti;
    }


    /**
     * Sets the idFonteNAddetti value for this NAddettiAAEP.
     * 
     * @param idFonteNAddetti
     */
    public void setIdFonteNAddetti(java.lang.String idFonteNAddetti) {
        this.idFonteNAddetti = idFonteNAddetti;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NAddettiAAEP)) return false;
        NAddettiAAEP other = (NAddettiAAEP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NAddetti==null && other.getNAddetti()==null) || 
             (this.NAddetti!=null &&
              this.NAddetti.equals(other.getNAddetti()))) &&
            ((this.descrFonteNAddetti==null && other.getDescrFonteNAddetti()==null) || 
             (this.descrFonteNAddetti!=null &&
              this.descrFonteNAddetti.equals(other.getDescrFonteNAddetti()))) &&
            ((this.descrFonteAzienda==null && other.getDescrFonteAzienda()==null) || 
             (this.descrFonteAzienda!=null &&
              this.descrFonteAzienda.equals(other.getDescrFonteAzienda()))) &&
            ((this.idFonteAzienda==null && other.getIdFonteAzienda()==null) || 
             (this.idFonteAzienda!=null &&
              this.idFonteAzienda.equals(other.getIdFonteAzienda()))) &&
            ((this.formatoNAddetti==null && other.getFormatoNAddetti()==null) || 
             (this.formatoNAddetti!=null &&
              this.formatoNAddetti.equals(other.getFormatoNAddetti()))) &&
            ((this.idFonteNAddetti==null && other.getIdFonteNAddetti()==null) || 
             (this.idFonteNAddetti!=null &&
              this.idFonteNAddetti.equals(other.getIdFonteNAddetti())));
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
        if (getNAddetti() != null) {
            _hashCode += getNAddetti().hashCode();
        }
        if (getDescrFonteNAddetti() != null) {
            _hashCode += getDescrFonteNAddetti().hashCode();
        }
        if (getDescrFonteAzienda() != null) {
            _hashCode += getDescrFonteAzienda().hashCode();
        }
        if (getIdFonteAzienda() != null) {
            _hashCode += getIdFonteAzienda().hashCode();
        }
        if (getFormatoNAddetti() != null) {
            _hashCode += getFormatoNAddetti().hashCode();
        }
        if (getIdFonteNAddetti() != null) {
            _hashCode += getIdFonteNAddetti().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NAddettiAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "NAddettiAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NAddetti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrFonteNAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrFonteNAddetti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrFonteAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrFonteAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFonteAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idFonteAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatoNAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "formatoNAddetti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFonteNAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idFonteNAddetti"));
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
