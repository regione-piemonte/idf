/**
 * CSIPolygon.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public class CSIPolygon  implements java.io.Serializable {
    private int SRID;

    private it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing[] holes;

    private it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing shell;

    public CSIPolygon() {
    }

    public CSIPolygon(
           int SRID,
           it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing[] holes,
           it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing shell) {
           this.SRID = SRID;
           this.holes = holes;
           this.shell = shell;
    }


    /**
     * Gets the SRID value for this CSIPolygon.
     * 
     * @return SRID
     */
    public int getSRID() {
        return SRID;
    }


    /**
     * Sets the SRID value for this CSIPolygon.
     * 
     * @param SRID
     */
    public void setSRID(int SRID) {
        this.SRID = SRID;
    }


    /**
     * Gets the holes value for this CSIPolygon.
     * 
     * @return holes
     */
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing[] getHoles() {
        return holes;
    }


    /**
     * Sets the holes value for this CSIPolygon.
     * 
     * @param holes
     */
    public void setHoles(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing[] holes) {
        this.holes = holes;
    }


    /**
     * Gets the shell value for this CSIPolygon.
     * 
     * @return shell
     */
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing getShell() {
        return shell;
    }


    /**
     * Sets the shell value for this CSIPolygon.
     * 
     * @param shell
     */
    public void setShell(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSILinearRing shell) {
        this.shell = shell;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CSIPolygon)) return false;
        CSIPolygon other = (CSIPolygon) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.SRID == other.getSRID() &&
            ((this.holes==null && other.getHoles()==null) || 
             (this.holes!=null &&
              java.util.Arrays.equals(this.holes, other.getHoles()))) &&
            ((this.shell==null && other.getShell()==null) || 
             (this.shell!=null &&
              this.shell.equals(other.getShell())));
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
        if (getHoles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHoles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHoles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getShell() != null) {
            _hashCode += getShell().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CSIPolygon.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIPolygon"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SRID");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "SRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("holes");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "holes"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSILinearRing"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shell");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "shell"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSILinearRing"));
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
