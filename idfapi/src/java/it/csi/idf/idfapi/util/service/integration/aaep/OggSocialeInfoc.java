/**
 * OggSocialeInfoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class OggSocialeInfoc  implements java.io.Serializable {
    private long idAAEPFonteDato;

    private java.lang.String rigaTesto;

    private long idAAEPAzienda;

    private long progrRiga;

    public OggSocialeInfoc() {
    }

    public OggSocialeInfoc(
           long idAAEPFonteDato,
           java.lang.String rigaTesto,
           long idAAEPAzienda,
           long progrRiga) {
           this.idAAEPFonteDato = idAAEPFonteDato;
           this.rigaTesto = rigaTesto;
           this.idAAEPAzienda = idAAEPAzienda;
           this.progrRiga = progrRiga;
    }


    /**
     * Gets the idAAEPFonteDato value for this OggSocialeInfoc.
     * 
     * @return idAAEPFonteDato
     */
    public long getIdAAEPFonteDato() {
        return idAAEPFonteDato;
    }


    /**
     * Sets the idAAEPFonteDato value for this OggSocialeInfoc.
     * 
     * @param idAAEPFonteDato
     */
    public void setIdAAEPFonteDato(long idAAEPFonteDato) {
        this.idAAEPFonteDato = idAAEPFonteDato;
    }


    /**
     * Gets the rigaTesto value for this OggSocialeInfoc.
     * 
     * @return rigaTesto
     */
    public java.lang.String getRigaTesto() {
        return rigaTesto;
    }


    /**
     * Sets the rigaTesto value for this OggSocialeInfoc.
     * 
     * @param rigaTesto
     */
    public void setRigaTesto(java.lang.String rigaTesto) {
        this.rigaTesto = rigaTesto;
    }


    /**
     * Gets the idAAEPAzienda value for this OggSocialeInfoc.
     * 
     * @return idAAEPAzienda
     */
    public long getIdAAEPAzienda() {
        return idAAEPAzienda;
    }


    /**
     * Sets the idAAEPAzienda value for this OggSocialeInfoc.
     * 
     * @param idAAEPAzienda
     */
    public void setIdAAEPAzienda(long idAAEPAzienda) {
        this.idAAEPAzienda = idAAEPAzienda;
    }


    /**
     * Gets the progrRiga value for this OggSocialeInfoc.
     * 
     * @return progrRiga
     */
    public long getProgrRiga() {
        return progrRiga;
    }


    /**
     * Sets the progrRiga value for this OggSocialeInfoc.
     * 
     * @param progrRiga
     */
    public void setProgrRiga(long progrRiga) {
        this.progrRiga = progrRiga;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OggSocialeInfoc)) return false;
        OggSocialeInfoc other = (OggSocialeInfoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAAEPFonteDato == other.getIdAAEPFonteDato() &&
            ((this.rigaTesto==null && other.getRigaTesto()==null) || 
             (this.rigaTesto!=null &&
              this.rigaTesto.equals(other.getRigaTesto()))) &&
            this.idAAEPAzienda == other.getIdAAEPAzienda() &&
            this.progrRiga == other.getProgrRiga();
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
        _hashCode += new Long(getIdAAEPFonteDato()).hashCode();
        if (getRigaTesto() != null) {
            _hashCode += getRigaTesto().hashCode();
        }
        _hashCode += new Long(getIdAAEPAzienda()).hashCode();
        _hashCode += new Long(getProgrRiga()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OggSocialeInfoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "OggSocialeInfoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPFonteDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rigaTesto");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "rigaTesto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrRiga");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrRiga"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
