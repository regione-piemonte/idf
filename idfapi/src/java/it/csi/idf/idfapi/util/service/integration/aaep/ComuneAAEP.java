/**
 * ComuneAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ComuneAAEP  implements java.io.Serializable {
    private java.lang.String siglaStatoEstero;

    private java.lang.String codISTATComune;

    private java.lang.String descrComune;

    private java.lang.String nomeCittaEstera;

    private java.lang.String flgSedeLegale;

    private java.lang.String NSedi;

    private java.lang.String descrStatoEstero;

    public ComuneAAEP() {
    }

    public ComuneAAEP(
           java.lang.String siglaStatoEstero,
           java.lang.String codISTATComune,
           java.lang.String descrComune,
           java.lang.String nomeCittaEstera,
           java.lang.String flgSedeLegale,
           java.lang.String NSedi,
           java.lang.String descrStatoEstero) {
           this.siglaStatoEstero = siglaStatoEstero;
           this.codISTATComune = codISTATComune;
           this.descrComune = descrComune;
           this.nomeCittaEstera = nomeCittaEstera;
           this.flgSedeLegale = flgSedeLegale;
           this.NSedi = NSedi;
           this.descrStatoEstero = descrStatoEstero;
    }


    /**
     * Gets the siglaStatoEstero value for this ComuneAAEP.
     * 
     * @return siglaStatoEstero
     */
    public java.lang.String getSiglaStatoEstero() {
        return siglaStatoEstero;
    }


    /**
     * Sets the siglaStatoEstero value for this ComuneAAEP.
     * 
     * @param siglaStatoEstero
     */
    public void setSiglaStatoEstero(java.lang.String siglaStatoEstero) {
        this.siglaStatoEstero = siglaStatoEstero;
    }


    /**
     * Gets the codISTATComune value for this ComuneAAEP.
     * 
     * @return codISTATComune
     */
    public java.lang.String getCodISTATComune() {
        return codISTATComune;
    }


    /**
     * Sets the codISTATComune value for this ComuneAAEP.
     * 
     * @param codISTATComune
     */
    public void setCodISTATComune(java.lang.String codISTATComune) {
        this.codISTATComune = codISTATComune;
    }


    /**
     * Gets the descrComune value for this ComuneAAEP.
     * 
     * @return descrComune
     */
    public java.lang.String getDescrComune() {
        return descrComune;
    }


    /**
     * Sets the descrComune value for this ComuneAAEP.
     * 
     * @param descrComune
     */
    public void setDescrComune(java.lang.String descrComune) {
        this.descrComune = descrComune;
    }


    /**
     * Gets the nomeCittaEstera value for this ComuneAAEP.
     * 
     * @return nomeCittaEstera
     */
    public java.lang.String getNomeCittaEstera() {
        return nomeCittaEstera;
    }


    /**
     * Sets the nomeCittaEstera value for this ComuneAAEP.
     * 
     * @param nomeCittaEstera
     */
    public void setNomeCittaEstera(java.lang.String nomeCittaEstera) {
        this.nomeCittaEstera = nomeCittaEstera;
    }


    /**
     * Gets the flgSedeLegale value for this ComuneAAEP.
     * 
     * @return flgSedeLegale
     */
    public java.lang.String getFlgSedeLegale() {
        return flgSedeLegale;
    }


    /**
     * Sets the flgSedeLegale value for this ComuneAAEP.
     * 
     * @param flgSedeLegale
     */
    public void setFlgSedeLegale(java.lang.String flgSedeLegale) {
        this.flgSedeLegale = flgSedeLegale;
    }


    /**
     * Gets the NSedi value for this ComuneAAEP.
     * 
     * @return NSedi
     */
    public java.lang.String getNSedi() {
        return NSedi;
    }


    /**
     * Sets the NSedi value for this ComuneAAEP.
     * 
     * @param NSedi
     */
    public void setNSedi(java.lang.String NSedi) {
        this.NSedi = NSedi;
    }


    /**
     * Gets the descrStatoEstero value for this ComuneAAEP.
     * 
     * @return descrStatoEstero
     */
    public java.lang.String getDescrStatoEstero() {
        return descrStatoEstero;
    }


    /**
     * Sets the descrStatoEstero value for this ComuneAAEP.
     * 
     * @param descrStatoEstero
     */
    public void setDescrStatoEstero(java.lang.String descrStatoEstero) {
        this.descrStatoEstero = descrStatoEstero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComuneAAEP)) return false;
        ComuneAAEP other = (ComuneAAEP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.siglaStatoEstero==null && other.getSiglaStatoEstero()==null) || 
             (this.siglaStatoEstero!=null &&
              this.siglaStatoEstero.equals(other.getSiglaStatoEstero()))) &&
            ((this.codISTATComune==null && other.getCodISTATComune()==null) || 
             (this.codISTATComune!=null &&
              this.codISTATComune.equals(other.getCodISTATComune()))) &&
            ((this.descrComune==null && other.getDescrComune()==null) || 
             (this.descrComune!=null &&
              this.descrComune.equals(other.getDescrComune()))) &&
            ((this.nomeCittaEstera==null && other.getNomeCittaEstera()==null) || 
             (this.nomeCittaEstera!=null &&
              this.nomeCittaEstera.equals(other.getNomeCittaEstera()))) &&
            ((this.flgSedeLegale==null && other.getFlgSedeLegale()==null) || 
             (this.flgSedeLegale!=null &&
              this.flgSedeLegale.equals(other.getFlgSedeLegale()))) &&
            ((this.NSedi==null && other.getNSedi()==null) || 
             (this.NSedi!=null &&
              this.NSedi.equals(other.getNSedi()))) &&
            ((this.descrStatoEstero==null && other.getDescrStatoEstero()==null) || 
             (this.descrStatoEstero!=null &&
              this.descrStatoEstero.equals(other.getDescrStatoEstero())));
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
        if (getSiglaStatoEstero() != null) {
            _hashCode += getSiglaStatoEstero().hashCode();
        }
        if (getCodISTATComune() != null) {
            _hashCode += getCodISTATComune().hashCode();
        }
        if (getDescrComune() != null) {
            _hashCode += getDescrComune().hashCode();
        }
        if (getNomeCittaEstera() != null) {
            _hashCode += getNomeCittaEstera().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComuneAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ComuneAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaStatoEstero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaStatoEstero"));
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
        elemField.setFieldName("nomeCittaEstera");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "nomeCittaEstera"));
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
