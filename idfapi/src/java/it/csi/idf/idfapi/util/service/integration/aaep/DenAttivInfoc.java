/**
 * DenAttivInfoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class DenAttivInfoc  implements java.io.Serializable {
    private long progrDenuncia;

    private long progrSede;

    private java.lang.String descrEnte;

    private long idAAEPFonteDato;

    private java.lang.String codiceEnte;

    private java.util.Calendar dataDenInizioAttiv;

    private long idAAEPAzienda;

    public DenAttivInfoc() {
    }

    public DenAttivInfoc(
           long progrDenuncia,
           long progrSede,
           java.lang.String descrEnte,
           long idAAEPFonteDato,
           java.lang.String codiceEnte,
           java.util.Calendar dataDenInizioAttiv,
           long idAAEPAzienda) {
           this.progrDenuncia = progrDenuncia;
           this.progrSede = progrSede;
           this.descrEnte = descrEnte;
           this.idAAEPFonteDato = idAAEPFonteDato;
           this.codiceEnte = codiceEnte;
           this.dataDenInizioAttiv = dataDenInizioAttiv;
           this.idAAEPAzienda = idAAEPAzienda;
    }


    /**
     * Gets the progrDenuncia value for this DenAttivInfoc.
     * 
     * @return progrDenuncia
     */
    public long getProgrDenuncia() {
        return progrDenuncia;
    }


    /**
     * Sets the progrDenuncia value for this DenAttivInfoc.
     * 
     * @param progrDenuncia
     */
    public void setProgrDenuncia(long progrDenuncia) {
        this.progrDenuncia = progrDenuncia;
    }


    /**
     * Gets the progrSede value for this DenAttivInfoc.
     * 
     * @return progrSede
     */
    public long getProgrSede() {
        return progrSede;
    }


    /**
     * Sets the progrSede value for this DenAttivInfoc.
     * 
     * @param progrSede
     */
    public void setProgrSede(long progrSede) {
        this.progrSede = progrSede;
    }


    /**
     * Gets the descrEnte value for this DenAttivInfoc.
     * 
     * @return descrEnte
     */
    public java.lang.String getDescrEnte() {
        return descrEnte;
    }


    /**
     * Sets the descrEnte value for this DenAttivInfoc.
     * 
     * @param descrEnte
     */
    public void setDescrEnte(java.lang.String descrEnte) {
        this.descrEnte = descrEnte;
    }


    /**
     * Gets the idAAEPFonteDato value for this DenAttivInfoc.
     * 
     * @return idAAEPFonteDato
     */
    public long getIdAAEPFonteDato() {
        return idAAEPFonteDato;
    }


    /**
     * Sets the idAAEPFonteDato value for this DenAttivInfoc.
     * 
     * @param idAAEPFonteDato
     */
    public void setIdAAEPFonteDato(long idAAEPFonteDato) {
        this.idAAEPFonteDato = idAAEPFonteDato;
    }


    /**
     * Gets the codiceEnte value for this DenAttivInfoc.
     * 
     * @return codiceEnte
     */
    public java.lang.String getCodiceEnte() {
        return codiceEnte;
    }


    /**
     * Sets the codiceEnte value for this DenAttivInfoc.
     * 
     * @param codiceEnte
     */
    public void setCodiceEnte(java.lang.String codiceEnte) {
        this.codiceEnte = codiceEnte;
    }


    /**
     * Gets the dataDenInizioAttiv value for this DenAttivInfoc.
     * 
     * @return dataDenInizioAttiv
     */
    public java.util.Calendar getDataDenInizioAttiv() {
        return dataDenInizioAttiv;
    }


    /**
     * Sets the dataDenInizioAttiv value for this DenAttivInfoc.
     * 
     * @param dataDenInizioAttiv
     */
    public void setDataDenInizioAttiv(java.util.Calendar dataDenInizioAttiv) {
        this.dataDenInizioAttiv = dataDenInizioAttiv;
    }


    /**
     * Gets the idAAEPAzienda value for this DenAttivInfoc.
     * 
     * @return idAAEPAzienda
     */
    public long getIdAAEPAzienda() {
        return idAAEPAzienda;
    }


    /**
     * Sets the idAAEPAzienda value for this DenAttivInfoc.
     * 
     * @param idAAEPAzienda
     */
    public void setIdAAEPAzienda(long idAAEPAzienda) {
        this.idAAEPAzienda = idAAEPAzienda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DenAttivInfoc)) return false;
        DenAttivInfoc other = (DenAttivInfoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.progrDenuncia == other.getProgrDenuncia() &&
            this.progrSede == other.getProgrSede() &&
            ((this.descrEnte==null && other.getDescrEnte()==null) || 
             (this.descrEnte!=null &&
              this.descrEnte.equals(other.getDescrEnte()))) &&
            this.idAAEPFonteDato == other.getIdAAEPFonteDato() &&
            ((this.codiceEnte==null && other.getCodiceEnte()==null) || 
             (this.codiceEnte!=null &&
              this.codiceEnte.equals(other.getCodiceEnte()))) &&
            ((this.dataDenInizioAttiv==null && other.getDataDenInizioAttiv()==null) || 
             (this.dataDenInizioAttiv!=null &&
              this.dataDenInizioAttiv.equals(other.getDataDenInizioAttiv()))) &&
            this.idAAEPAzienda == other.getIdAAEPAzienda();
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
        _hashCode += new Long(getProgrDenuncia()).hashCode();
        _hashCode += new Long(getProgrSede()).hashCode();
        if (getDescrEnte() != null) {
            _hashCode += getDescrEnte().hashCode();
        }
        _hashCode += new Long(getIdAAEPFonteDato()).hashCode();
        if (getCodiceEnte() != null) {
            _hashCode += getCodiceEnte().hashCode();
        }
        if (getDataDenInizioAttiv() != null) {
            _hashCode += getDataDenInizioAttiv().hashCode();
        }
        _hashCode += new Long(getIdAAEPAzienda()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DenAttivInfoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "DenAttivInfoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrDenuncia");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrDenuncia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrSede"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrEnte");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrEnte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPFonteDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceEnte");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceEnte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDenInizioAttiv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataDenInizioAttiv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPAzienda"));
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
