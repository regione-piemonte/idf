/**
 * ListaDestinatari.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ListaDestinatari  implements java.io.Serializable {
    private java.lang.String destinatario;

    private java.lang.String prioritaDestinatario;

    public ListaDestinatari() {
    }

    public ListaDestinatari(
           java.lang.String destinatario,
           java.lang.String prioritaDestinatario) {
           this.destinatario = destinatario;
           this.prioritaDestinatario = prioritaDestinatario;
    }


    /**
     * Gets the destinatario value for this ListaDestinatari.
     * 
     * @return destinatario
     */
    public java.lang.String getDestinatario() {
        return destinatario;
    }


    /**
     * Sets the destinatario value for this ListaDestinatari.
     * 
     * @param destinatario
     */
    public void setDestinatario(java.lang.String destinatario) {
        this.destinatario = destinatario;
    }


    /**
     * Gets the prioritaDestinatario value for this ListaDestinatari.
     * 
     * @return prioritaDestinatario
     */
    public java.lang.String getPrioritaDestinatario() {
        return prioritaDestinatario;
    }


    /**
     * Sets the prioritaDestinatario value for this ListaDestinatari.
     * 
     * @param prioritaDestinatario
     */
    public void setPrioritaDestinatario(java.lang.String prioritaDestinatario) {
        this.prioritaDestinatario = prioritaDestinatario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaDestinatari)) return false;
        ListaDestinatari other = (ListaDestinatari) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.destinatario==null && other.getDestinatario()==null) || 
             (this.destinatario!=null &&
              this.destinatario.equals(other.getDestinatario()))) &&
            ((this.prioritaDestinatario==null && other.getPrioritaDestinatario()==null) || 
             (this.prioritaDestinatario!=null &&
              this.prioritaDestinatario.equals(other.getPrioritaDestinatario())));
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
        if (getDestinatario() != null) {
            _hashCode += getDestinatario().hashCode();
        }
        if (getPrioritaDestinatario() != null) {
            _hashCode += getPrioritaDestinatario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaDestinatari.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaDestinatari"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatario");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "destinatario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prioritaDestinatario");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "prioritaDestinatario"));
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
