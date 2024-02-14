/**
 * MovimAddettiAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class MovimAddettiAAEP  implements java.io.Serializable {
    private java.lang.String movimAddetti;

    private java.lang.String formatoMovimAddetti;

    private java.lang.String descrFonteMovimAddetti;

    private java.lang.String codISTATComune;

    private java.lang.String descrComune;

    private java.lang.String idFonteMovimAddetti;

    public MovimAddettiAAEP() {
    }

    public MovimAddettiAAEP(
           java.lang.String movimAddetti,
           java.lang.String formatoMovimAddetti,
           java.lang.String descrFonteMovimAddetti,
           java.lang.String codISTATComune,
           java.lang.String descrComune,
           java.lang.String idFonteMovimAddetti) {
           this.movimAddetti = movimAddetti;
           this.formatoMovimAddetti = formatoMovimAddetti;
           this.descrFonteMovimAddetti = descrFonteMovimAddetti;
           this.codISTATComune = codISTATComune;
           this.descrComune = descrComune;
           this.idFonteMovimAddetti = idFonteMovimAddetti;
    }


    /**
     * Gets the movimAddetti value for this MovimAddettiAAEP.
     * 
     * @return movimAddetti
     */
    public java.lang.String getMovimAddetti() {
        return movimAddetti;
    }


    /**
     * Sets the movimAddetti value for this MovimAddettiAAEP.
     * 
     * @param movimAddetti
     */
    public void setMovimAddetti(java.lang.String movimAddetti) {
        this.movimAddetti = movimAddetti;
    }


    /**
     * Gets the formatoMovimAddetti value for this MovimAddettiAAEP.
     * 
     * @return formatoMovimAddetti
     */
    public java.lang.String getFormatoMovimAddetti() {
        return formatoMovimAddetti;
    }


    /**
     * Sets the formatoMovimAddetti value for this MovimAddettiAAEP.
     * 
     * @param formatoMovimAddetti
     */
    public void setFormatoMovimAddetti(java.lang.String formatoMovimAddetti) {
        this.formatoMovimAddetti = formatoMovimAddetti;
    }


    /**
     * Gets the descrFonteMovimAddetti value for this MovimAddettiAAEP.
     * 
     * @return descrFonteMovimAddetti
     */
    public java.lang.String getDescrFonteMovimAddetti() {
        return descrFonteMovimAddetti;
    }


    /**
     * Sets the descrFonteMovimAddetti value for this MovimAddettiAAEP.
     * 
     * @param descrFonteMovimAddetti
     */
    public void setDescrFonteMovimAddetti(java.lang.String descrFonteMovimAddetti) {
        this.descrFonteMovimAddetti = descrFonteMovimAddetti;
    }


    /**
     * Gets the codISTATComune value for this MovimAddettiAAEP.
     * 
     * @return codISTATComune
     */
    public java.lang.String getCodISTATComune() {
        return codISTATComune;
    }


    /**
     * Sets the codISTATComune value for this MovimAddettiAAEP.
     * 
     * @param codISTATComune
     */
    public void setCodISTATComune(java.lang.String codISTATComune) {
        this.codISTATComune = codISTATComune;
    }


    /**
     * Gets the descrComune value for this MovimAddettiAAEP.
     * 
     * @return descrComune
     */
    public java.lang.String getDescrComune() {
        return descrComune;
    }


    /**
     * Sets the descrComune value for this MovimAddettiAAEP.
     * 
     * @param descrComune
     */
    public void setDescrComune(java.lang.String descrComune) {
        this.descrComune = descrComune;
    }


    /**
     * Gets the idFonteMovimAddetti value for this MovimAddettiAAEP.
     * 
     * @return idFonteMovimAddetti
     */
    public java.lang.String getIdFonteMovimAddetti() {
        return idFonteMovimAddetti;
    }


    /**
     * Sets the idFonteMovimAddetti value for this MovimAddettiAAEP.
     * 
     * @param idFonteMovimAddetti
     */
    public void setIdFonteMovimAddetti(java.lang.String idFonteMovimAddetti) {
        this.idFonteMovimAddetti = idFonteMovimAddetti;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MovimAddettiAAEP)) return false;
        MovimAddettiAAEP other = (MovimAddettiAAEP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.movimAddetti==null && other.getMovimAddetti()==null) || 
             (this.movimAddetti!=null &&
              this.movimAddetti.equals(other.getMovimAddetti()))) &&
            ((this.formatoMovimAddetti==null && other.getFormatoMovimAddetti()==null) || 
             (this.formatoMovimAddetti!=null &&
              this.formatoMovimAddetti.equals(other.getFormatoMovimAddetti()))) &&
            ((this.descrFonteMovimAddetti==null && other.getDescrFonteMovimAddetti()==null) || 
             (this.descrFonteMovimAddetti!=null &&
              this.descrFonteMovimAddetti.equals(other.getDescrFonteMovimAddetti()))) &&
            ((this.codISTATComune==null && other.getCodISTATComune()==null) || 
             (this.codISTATComune!=null &&
              this.codISTATComune.equals(other.getCodISTATComune()))) &&
            ((this.descrComune==null && other.getDescrComune()==null) || 
             (this.descrComune!=null &&
              this.descrComune.equals(other.getDescrComune()))) &&
            ((this.idFonteMovimAddetti==null && other.getIdFonteMovimAddetti()==null) || 
             (this.idFonteMovimAddetti!=null &&
              this.idFonteMovimAddetti.equals(other.getIdFonteMovimAddetti())));
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
        if (getMovimAddetti() != null) {
            _hashCode += getMovimAddetti().hashCode();
        }
        if (getFormatoMovimAddetti() != null) {
            _hashCode += getFormatoMovimAddetti().hashCode();
        }
        if (getDescrFonteMovimAddetti() != null) {
            _hashCode += getDescrFonteMovimAddetti().hashCode();
        }
        if (getCodISTATComune() != null) {
            _hashCode += getCodISTATComune().hashCode();
        }
        if (getDescrComune() != null) {
            _hashCode += getDescrComune().hashCode();
        }
        if (getIdFonteMovimAddetti() != null) {
            _hashCode += getIdFonteMovimAddetti().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MovimAddettiAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "MovimAddettiAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movimAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "movimAddetti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatoMovimAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "formatoMovimAddetti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrFonteMovimAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrFonteMovimAddetti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codISTATComune");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codISTATComune"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrComune");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComune"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFonteMovimAddetti");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idFonteMovimAddetti"));
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
