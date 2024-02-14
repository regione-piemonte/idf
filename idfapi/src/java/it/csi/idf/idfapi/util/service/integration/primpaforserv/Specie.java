/**
 * Specie.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.primpaforserv;

public class Specie  implements java.io.Serializable {
    private java.lang.String nomeVolgare;

    private java.lang.String prevalenza;

    private java.lang.Integer volume;

    public Specie() {
    }

    public Specie(
           java.lang.String nomeVolgare,
           java.lang.String prevalenza,
           java.lang.Integer volume) {
           this.nomeVolgare = nomeVolgare;
           this.prevalenza = prevalenza;
           this.volume = volume;
    }


    /**
     * Gets the nomeVolgare value for this Specie.
     * 
     * @return nomeVolgare
     */
    public java.lang.String getNomeVolgare() {
        return nomeVolgare;
    }


    /**
     * Sets the nomeVolgare value for this Specie.
     * 
     * @param nomeVolgare
     */
    public void setNomeVolgare(java.lang.String nomeVolgare) {
        this.nomeVolgare = nomeVolgare;
    }


    /**
     * Gets the prevalenza value for this Specie.
     * 
     * @return prevalenza
     */
    public java.lang.String getPrevalenza() {
        return prevalenza;
    }


    /**
     * Sets the prevalenza value for this Specie.
     * 
     * @param prevalenza
     */
    public void setPrevalenza(java.lang.String prevalenza) {
        this.prevalenza = prevalenza;
    }


    /**
     * Gets the volume value for this Specie.
     * 
     * @return volume
     */
    public java.lang.Integer getVolume() {
        return volume;
    }


    /**
     * Sets the volume value for this Specie.
     * 
     * @param volume
     */
    public void setVolume(java.lang.Integer volume) {
        this.volume = volume;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Specie)) return false;
        Specie other = (Specie) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nomeVolgare==null && other.getNomeVolgare()==null) || 
             (this.nomeVolgare!=null &&
              this.nomeVolgare.equals(other.getNomeVolgare()))) &&
            ((this.prevalenza==null && other.getPrevalenza()==null) || 
             (this.prevalenza!=null &&
              this.prevalenza.equals(other.getPrevalenza()))) &&
            ((this.volume==null && other.getVolume()==null) || 
             (this.volume!=null &&
              this.volume.equals(other.getVolume())));
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
        if (getNomeVolgare() != null) {
            _hashCode += getNomeVolgare().hashCode();
        }
        if (getPrevalenza() != null) {
            _hashCode += getPrevalenza().hashCode();
        }
        if (getVolume() != null) {
            _hashCode += getVolume().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Specie.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("primpa_istanze", "Specie"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeVolgare");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "nomeVolgare"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prevalenza");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "prevalenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volume");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "volume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
