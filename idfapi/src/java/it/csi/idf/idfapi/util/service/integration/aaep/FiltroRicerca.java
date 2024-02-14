/**
 * FiltroRicerca.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class FiltroRicerca  implements java.io.Serializable {
    private java.lang.String valore;

    private boolean _null;

    private java.lang.String chiave;

    private boolean obbligatorio;

    public FiltroRicerca() {
    }

    public FiltroRicerca(
           java.lang.String valore,
           boolean _null,
           java.lang.String chiave,
           boolean obbligatorio) {
           this.valore = valore;
           this._null = _null;
           this.chiave = chiave;
           this.obbligatorio = obbligatorio;
    }


    /**
     * Gets the valore value for this FiltroRicerca.
     * 
     * @return valore
     */
    public java.lang.String getValore() {
        return valore;
    }


    /**
     * Sets the valore value for this FiltroRicerca.
     * 
     * @param valore
     */
    public void setValore(java.lang.String valore) {
        this.valore = valore;
    }


    /**
     * Gets the _null value for this FiltroRicerca.
     * 
     * @return _null
     */
    public boolean is_null() {
        return _null;
    }


    /**
     * Sets the _null value for this FiltroRicerca.
     * 
     * @param _null
     */
    public void set_null(boolean _null) {
        this._null = _null;
    }


    /**
     * Gets the chiave value for this FiltroRicerca.
     * 
     * @return chiave
     */
    public java.lang.String getChiave() {
        return chiave;
    }


    /**
     * Sets the chiave value for this FiltroRicerca.
     * 
     * @param chiave
     */
    public void setChiave(java.lang.String chiave) {
        this.chiave = chiave;
    }


    /**
     * Gets the obbligatorio value for this FiltroRicerca.
     * 
     * @return obbligatorio
     */
    public boolean isObbligatorio() {
        return obbligatorio;
    }


    /**
     * Sets the obbligatorio value for this FiltroRicerca.
     * 
     * @param obbligatorio
     */
    public void setObbligatorio(boolean obbligatorio) {
        this.obbligatorio = obbligatorio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FiltroRicerca)) return false;
        FiltroRicerca other = (FiltroRicerca) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valore==null && other.getValore()==null) || 
             (this.valore!=null &&
              this.valore.equals(other.getValore()))) &&
            this._null == other.is_null() &&
            ((this.chiave==null && other.getChiave()==null) || 
             (this.chiave!=null &&
              this.chiave.equals(other.getChiave()))) &&
            this.obbligatorio == other.isObbligatorio();
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
        if (getValore() != null) {
            _hashCode += getValore().hashCode();
        }
        _hashCode += (is_null() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChiave() != null) {
            _hashCode += getChiave().hashCode();
        }
        _hashCode += (isObbligatorio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FiltroRicerca.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "FiltroRicerca"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valore");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "valore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_null");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "null"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chiave");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "chiave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obbligatorio");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "obbligatorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
