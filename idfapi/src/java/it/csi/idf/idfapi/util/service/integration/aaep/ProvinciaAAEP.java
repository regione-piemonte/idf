/**
 * ProvinciaAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ProvinciaAAEP  implements java.io.Serializable {
    private java.lang.String siglaProv;

    private java.lang.String siglaStatoEstero;

    private java.lang.String descrProv;

    private java.lang.String flgSedeLegale;

    private java.lang.String NSedi;

    private java.lang.String descrStatoEstero;

    private it.csi.idf.idfapi.util.service.integration.aaep.ComuneAAEP[] listaComuniAAEP;

    public ProvinciaAAEP() {
    }

    public ProvinciaAAEP(
           java.lang.String siglaProv,
           java.lang.String siglaStatoEstero,
           java.lang.String descrProv,
           java.lang.String flgSedeLegale,
           java.lang.String NSedi,
           java.lang.String descrStatoEstero,
           it.csi.idf.idfapi.util.service.integration.aaep.ComuneAAEP[] listaComuniAAEP) {
           this.siglaProv = siglaProv;
           this.siglaStatoEstero = siglaStatoEstero;
           this.descrProv = descrProv;
           this.flgSedeLegale = flgSedeLegale;
           this.NSedi = NSedi;
           this.descrStatoEstero = descrStatoEstero;
           this.listaComuniAAEP = listaComuniAAEP;
    }


    /**
     * Gets the siglaProv value for this ProvinciaAAEP.
     * 
     * @return siglaProv
     */
    public java.lang.String getSiglaProv() {
        return siglaProv;
    }


    /**
     * Sets the siglaProv value for this ProvinciaAAEP.
     * 
     * @param siglaProv
     */
    public void setSiglaProv(java.lang.String siglaProv) {
        this.siglaProv = siglaProv;
    }


    /**
     * Gets the siglaStatoEstero value for this ProvinciaAAEP.
     * 
     * @return siglaStatoEstero
     */
    public java.lang.String getSiglaStatoEstero() {
        return siglaStatoEstero;
    }


    /**
     * Sets the siglaStatoEstero value for this ProvinciaAAEP.
     * 
     * @param siglaStatoEstero
     */
    public void setSiglaStatoEstero(java.lang.String siglaStatoEstero) {
        this.siglaStatoEstero = siglaStatoEstero;
    }


    /**
     * Gets the descrProv value for this ProvinciaAAEP.
     * 
     * @return descrProv
     */
    public java.lang.String getDescrProv() {
        return descrProv;
    }


    /**
     * Sets the descrProv value for this ProvinciaAAEP.
     * 
     * @param descrProv
     */
    public void setDescrProv(java.lang.String descrProv) {
        this.descrProv = descrProv;
    }


    /**
     * Gets the flgSedeLegale value for this ProvinciaAAEP.
     * 
     * @return flgSedeLegale
     */
    public java.lang.String getFlgSedeLegale() {
        return flgSedeLegale;
    }


    /**
     * Sets the flgSedeLegale value for this ProvinciaAAEP.
     * 
     * @param flgSedeLegale
     */
    public void setFlgSedeLegale(java.lang.String flgSedeLegale) {
        this.flgSedeLegale = flgSedeLegale;
    }


    /**
     * Gets the NSedi value for this ProvinciaAAEP.
     * 
     * @return NSedi
     */
    public java.lang.String getNSedi() {
        return NSedi;
    }


    /**
     * Sets the NSedi value for this ProvinciaAAEP.
     * 
     * @param NSedi
     */
    public void setNSedi(java.lang.String NSedi) {
        this.NSedi = NSedi;
    }


    /**
     * Gets the descrStatoEstero value for this ProvinciaAAEP.
     * 
     * @return descrStatoEstero
     */
    public java.lang.String getDescrStatoEstero() {
        return descrStatoEstero;
    }


    /**
     * Sets the descrStatoEstero value for this ProvinciaAAEP.
     * 
     * @param descrStatoEstero
     */
    public void setDescrStatoEstero(java.lang.String descrStatoEstero) {
        this.descrStatoEstero = descrStatoEstero;
    }


    /**
     * Gets the listaComuniAAEP value for this ProvinciaAAEP.
     * 
     * @return listaComuniAAEP
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.ComuneAAEP[] getListaComuniAAEP() {
        return listaComuniAAEP;
    }


    /**
     * Sets the listaComuniAAEP value for this ProvinciaAAEP.
     * 
     * @param listaComuniAAEP
     */
    public void setListaComuniAAEP(it.csi.idf.idfapi.util.service.integration.aaep.ComuneAAEP[] listaComuniAAEP) {
        this.listaComuniAAEP = listaComuniAAEP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProvinciaAAEP)) return false;
        ProvinciaAAEP other = (ProvinciaAAEP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.siglaProv==null && other.getSiglaProv()==null) || 
             (this.siglaProv!=null &&
              this.siglaProv.equals(other.getSiglaProv()))) &&
            ((this.siglaStatoEstero==null && other.getSiglaStatoEstero()==null) || 
             (this.siglaStatoEstero!=null &&
              this.siglaStatoEstero.equals(other.getSiglaStatoEstero()))) &&
            ((this.descrProv==null && other.getDescrProv()==null) || 
             (this.descrProv!=null &&
              this.descrProv.equals(other.getDescrProv()))) &&
            ((this.flgSedeLegale==null && other.getFlgSedeLegale()==null) || 
             (this.flgSedeLegale!=null &&
              this.flgSedeLegale.equals(other.getFlgSedeLegale()))) &&
            ((this.NSedi==null && other.getNSedi()==null) || 
             (this.NSedi!=null &&
              this.NSedi.equals(other.getNSedi()))) &&
            ((this.descrStatoEstero==null && other.getDescrStatoEstero()==null) || 
             (this.descrStatoEstero!=null &&
              this.descrStatoEstero.equals(other.getDescrStatoEstero()))) &&
            ((this.listaComuniAAEP==null && other.getListaComuniAAEP()==null) || 
             (this.listaComuniAAEP!=null &&
              java.util.Arrays.equals(this.listaComuniAAEP, other.getListaComuniAAEP())));
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
        if (getSiglaProv() != null) {
            _hashCode += getSiglaProv().hashCode();
        }
        if (getSiglaStatoEstero() != null) {
            _hashCode += getSiglaStatoEstero().hashCode();
        }
        if (getDescrProv() != null) {
            _hashCode += getDescrProv().hashCode();
        }
        if (getFlgSedeLegale() != null) {
            _hashCode += getFlgSedeLegale().hashCode();
        }
        if (getNSedi() != null) {
            _hashCode += getNSedi().hashCode();
        }
        if (getDescrStatoEstero() != null) {
            _hashCode += getDescrStatoEstero().hashCode();
        }
        if (getListaComuniAAEP() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaComuniAAEP());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaComuniAAEP(), i);
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
        new org.apache.axis.description.TypeDesc(ProvinciaAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ProvinciaAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaStatoEstero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaStatoEstero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrProv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrProv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flgSedeLegale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flgSedeLegale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NSedi");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "NSedi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrStatoEstero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrStatoEstero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaComuniAAEP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaComuniAAEP"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ComuneAAEP"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
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
