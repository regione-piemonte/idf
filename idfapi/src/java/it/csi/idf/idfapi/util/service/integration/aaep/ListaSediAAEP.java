/**
 * ListaSediAAEP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ListaSediAAEP  implements java.io.Serializable {
    private java.lang.String indirizzo;

    private java.lang.String dataInizioVal;

    private java.lang.String idAAEPSede;

    private java.lang.String idAAEPAzienda;

    private java.lang.String siglaProvUL;

    private java.lang.String descrComuneUL;

    private java.lang.String descrTipoSede;

    private java.lang.String denominazioneSede;

    public ListaSediAAEP() {
    }

    public ListaSediAAEP(
           java.lang.String indirizzo,
           java.lang.String dataInizioVal,
           java.lang.String idAAEPSede,
           java.lang.String idAAEPAzienda,
           java.lang.String siglaProvUL,
           java.lang.String descrComuneUL,
           java.lang.String descrTipoSede,
           java.lang.String denominazioneSede) {
           this.indirizzo = indirizzo;
           this.dataInizioVal = dataInizioVal;
           this.idAAEPSede = idAAEPSede;
           this.idAAEPAzienda = idAAEPAzienda;
           this.siglaProvUL = siglaProvUL;
           this.descrComuneUL = descrComuneUL;
           this.descrTipoSede = descrTipoSede;
           this.denominazioneSede = denominazioneSede;
    }


    /**
     * Gets the indirizzo value for this ListaSediAAEP.
     * 
     * @return indirizzo
     */
    public java.lang.String getIndirizzo() {
        return indirizzo;
    }


    /**
     * Sets the indirizzo value for this ListaSediAAEP.
     * 
     * @param indirizzo
     */
    public void setIndirizzo(java.lang.String indirizzo) {
        this.indirizzo = indirizzo;
    }


    /**
     * Gets the dataInizioVal value for this ListaSediAAEP.
     * 
     * @return dataInizioVal
     */
    public java.lang.String getDataInizioVal() {
        return dataInizioVal;
    }


    /**
     * Sets the dataInizioVal value for this ListaSediAAEP.
     * 
     * @param dataInizioVal
     */
    public void setDataInizioVal(java.lang.String dataInizioVal) {
        this.dataInizioVal = dataInizioVal;
    }


    /**
     * Gets the idAAEPSede value for this ListaSediAAEP.
     * 
     * @return idAAEPSede
     */
    public java.lang.String getIdAAEPSede() {
        return idAAEPSede;
    }


    /**
     * Sets the idAAEPSede value for this ListaSediAAEP.
     * 
     * @param idAAEPSede
     */
    public void setIdAAEPSede(java.lang.String idAAEPSede) {
        this.idAAEPSede = idAAEPSede;
    }


    /**
     * Gets the idAAEPAzienda value for this ListaSediAAEP.
     * 
     * @return idAAEPAzienda
     */
    public java.lang.String getIdAAEPAzienda() {
        return idAAEPAzienda;
    }


    /**
     * Sets the idAAEPAzienda value for this ListaSediAAEP.
     * 
     * @param idAAEPAzienda
     */
    public void setIdAAEPAzienda(java.lang.String idAAEPAzienda) {
        this.idAAEPAzienda = idAAEPAzienda;
    }


    /**
     * Gets the siglaProvUL value for this ListaSediAAEP.
     * 
     * @return siglaProvUL
     */
    public java.lang.String getSiglaProvUL() {
        return siglaProvUL;
    }


    /**
     * Sets the siglaProvUL value for this ListaSediAAEP.
     * 
     * @param siglaProvUL
     */
    public void setSiglaProvUL(java.lang.String siglaProvUL) {
        this.siglaProvUL = siglaProvUL;
    }


    /**
     * Gets the descrComuneUL value for this ListaSediAAEP.
     * 
     * @return descrComuneUL
     */
    public java.lang.String getDescrComuneUL() {
        return descrComuneUL;
    }


    /**
     * Sets the descrComuneUL value for this ListaSediAAEP.
     * 
     * @param descrComuneUL
     */
    public void setDescrComuneUL(java.lang.String descrComuneUL) {
        this.descrComuneUL = descrComuneUL;
    }


    /**
     * Gets the descrTipoSede value for this ListaSediAAEP.
     * 
     * @return descrTipoSede
     */
    public java.lang.String getDescrTipoSede() {
        return descrTipoSede;
    }


    /**
     * Sets the descrTipoSede value for this ListaSediAAEP.
     * 
     * @param descrTipoSede
     */
    public void setDescrTipoSede(java.lang.String descrTipoSede) {
        this.descrTipoSede = descrTipoSede;
    }


    /**
     * Gets the denominazioneSede value for this ListaSediAAEP.
     * 
     * @return denominazioneSede
     */
    public java.lang.String getDenominazioneSede() {
        return denominazioneSede;
    }


    /**
     * Sets the denominazioneSede value for this ListaSediAAEP.
     * 
     * @param denominazioneSede
     */
    public void setDenominazioneSede(java.lang.String denominazioneSede) {
        this.denominazioneSede = denominazioneSede;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaSediAAEP)) return false;
        ListaSediAAEP other = (ListaSediAAEP) obj;
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
            ((this.dataInizioVal==null && other.getDataInizioVal()==null) || 
             (this.dataInizioVal!=null &&
              this.dataInizioVal.equals(other.getDataInizioVal()))) &&
            ((this.idAAEPSede==null && other.getIdAAEPSede()==null) || 
             (this.idAAEPSede!=null &&
              this.idAAEPSede.equals(other.getIdAAEPSede()))) &&
            ((this.idAAEPAzienda==null && other.getIdAAEPAzienda()==null) || 
             (this.idAAEPAzienda!=null &&
              this.idAAEPAzienda.equals(other.getIdAAEPAzienda()))) &&
            ((this.siglaProvUL==null && other.getSiglaProvUL()==null) || 
             (this.siglaProvUL!=null &&
              this.siglaProvUL.equals(other.getSiglaProvUL()))) &&
            ((this.descrComuneUL==null && other.getDescrComuneUL()==null) || 
             (this.descrComuneUL!=null &&
              this.descrComuneUL.equals(other.getDescrComuneUL()))) &&
            ((this.descrTipoSede==null && other.getDescrTipoSede()==null) || 
             (this.descrTipoSede!=null &&
              this.descrTipoSede.equals(other.getDescrTipoSede()))) &&
            ((this.denominazioneSede==null && other.getDenominazioneSede()==null) || 
             (this.denominazioneSede!=null &&
              this.denominazioneSede.equals(other.getDenominazioneSede())));
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
        if (getDataInizioVal() != null) {
            _hashCode += getDataInizioVal().hashCode();
        }
        if (getIdAAEPSede() != null) {
            _hashCode += getIdAAEPSede().hashCode();
        }
        if (getIdAAEPAzienda() != null) {
            _hashCode += getIdAAEPAzienda().hashCode();
        }
        if (getSiglaProvUL() != null) {
            _hashCode += getSiglaProvUL().hashCode();
        }
        if (getDescrComuneUL() != null) {
            _hashCode += getDescrComuneUL().hashCode();
        }
        if (getDescrTipoSede() != null) {
            _hashCode += getDescrTipoSede().hashCode();
        }
        if (getDenominazioneSede() != null) {
            _hashCode += getDenominazioneSede().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaSediAAEP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediAAEP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "indirizzo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioVal");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioVal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPSede"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProvUL");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProvUL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrComuneUL");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComuneUL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrTipoSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrTipoSede"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denominazioneSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "denominazioneSede"));
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
