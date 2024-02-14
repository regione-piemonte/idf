/**
 * ListaIndirizzi.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ListaIndirizzi  implements java.io.Serializable {
    private java.lang.String indirizzo;

    private java.lang.String prioritaIndirizzo;

    public ListaIndirizzi() {
    }

    public ListaIndirizzi(
           java.lang.String indirizzo,
           java.lang.String prioritaIndirizzo) {
           this.indirizzo = indirizzo;
           this.prioritaIndirizzo = prioritaIndirizzo;
    }


    /**
     * Gets the indirizzo value for this ListaIndirizzi.
     * 
     * @return indirizzo
     */
    public java.lang.String getIndirizzo() {
        return indirizzo;
    }


    /**
     * Sets the indirizzo value for this ListaIndirizzi.
     * 
     * @param indirizzo
     */
    public void setIndirizzo(java.lang.String indirizzo) {
        this.indirizzo = indirizzo;
    }


    /**
     * Gets the prioritaIndirizzo value for this ListaIndirizzi.
     * 
     * @return prioritaIndirizzo
     */
    public java.lang.String getPrioritaIndirizzo() {
        return prioritaIndirizzo;
    }


    /**
     * Sets the prioritaIndirizzo value for this ListaIndirizzi.
     * 
     * @param prioritaIndirizzo
     */
    public void setPrioritaIndirizzo(java.lang.String prioritaIndirizzo) {
        this.prioritaIndirizzo = prioritaIndirizzo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaIndirizzi)) return false;
        ListaIndirizzi other = (ListaIndirizzi) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.indirizzo==null && other.getIndirizzo()==null) || 
             (this.indirizzo!=null &&
              this.indirizzo.equals(other.getIndirizzo()))) &&
            ((this.prioritaIndirizzo==null && other.getPrioritaIndirizzo()==null) || 
             (this.prioritaIndirizzo!=null &&
              this.prioritaIndirizzo.equals(other.getPrioritaIndirizzo())));
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
        if (getIndirizzo() != null) {
            _hashCode += getIndirizzo().hashCode();
        }
        if (getPrioritaIndirizzo() != null) {
            _hashCode += getPrioritaIndirizzo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaIndirizzi.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaIndirizzi"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "indirizzo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prioritaIndirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "prioritaIndirizzo"));
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
