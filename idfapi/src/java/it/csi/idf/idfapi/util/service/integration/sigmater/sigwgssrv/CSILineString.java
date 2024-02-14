/**
 * CSILineString.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public class CSILineString  implements java.io.Serializable {
    private int SRID;

    private it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSICoordinate[] coords;

    public CSILineString() {
    }

    public CSILineString(
           int SRID,
           it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSICoordinate[] coords) {
           this.SRID = SRID;
           this.coords = coords;
    }


    /**
     * Gets the SRID value for this CSILineString.
     * 
     * @return SRID
     */
    public int getSRID() {
        return SRID;
    }


    /**
     * Sets the SRID value for this CSILineString.
     * 
     * @param SRID
     */
    public void setSRID(int SRID) {
        this.SRID = SRID;
    }


    /**
     * Gets the coords value for this CSILineString.
     * 
     * @return coords
     */
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSICoordinate[] getCoords() {
        return coords;
    }


    /**
     * Sets the coords value for this CSILineString.
     * 
     * @param coords
     */
    public void setCoords(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSICoordinate[] coords) {
        this.coords = coords;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CSILineString)) return false;
        CSILineString other = (CSILineString) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.SRID == other.getSRID() &&
            ((this.coords==null && other.getCoords()==null) || 
             (this.coords!=null &&
              java.util.Arrays.equals(this.coords, other.getCoords())));
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
        _hashCode += getSRID();
        if (getCoords() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCoords());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCoords(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CSILineString.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSILineString"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SRID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "SRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coords");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "coords"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSICoordinate"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "item"));
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
