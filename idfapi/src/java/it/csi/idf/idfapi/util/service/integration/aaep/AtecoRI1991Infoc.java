/**
 * AtecoRI1991Infoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class AtecoRI1991Infoc  implements java.io.Serializable {
    private java.lang.String codImportanza;

    private long idAAEPAzienda;

    private java.lang.String codAttivita;

    private java.lang.String descrImportanza;

    private java.lang.String descrAttivita;

    private long idAAEPFonteDato;

    private java.util.Calendar dataCessazAttivita;

    private java.util.Calendar dataInizioAttivita;

    private long progrSede;

    public AtecoRI1991Infoc() {
    }

    public AtecoRI1991Infoc(
           java.lang.String codImportanza,
           long idAAEPAzienda,
           java.lang.String codAttivita,
           java.lang.String descrImportanza,
           java.lang.String descrAttivita,
           long idAAEPFonteDato,
           java.util.Calendar dataCessazAttivita,
           java.util.Calendar dataInizioAttivita,
           long progrSede) {
           this.codImportanza = codImportanza;
           this.idAAEPAzienda = idAAEPAzienda;
           this.codAttivita = codAttivita;
           this.descrImportanza = descrImportanza;
           this.descrAttivita = descrAttivita;
           this.idAAEPFonteDato = idAAEPFonteDato;
           this.dataCessazAttivita = dataCessazAttivita;
           this.dataInizioAttivita = dataInizioAttivita;
           this.progrSede = progrSede;
    }


    /**
     * Gets the codImportanza value for this AtecoRI1991Infoc.
     * 
     * @return codImportanza
     */
    public java.lang.String getCodImportanza() {
        return codImportanza;
    }


    /**
     * Sets the codImportanza value for this AtecoRI1991Infoc.
     * 
     * @param codImportanza
     */
    public void setCodImportanza(java.lang.String codImportanza) {
        this.codImportanza = codImportanza;
    }


    /**
     * Gets the idAAEPAzienda value for this AtecoRI1991Infoc.
     * 
     * @return idAAEPAzienda
     */
    public long getIdAAEPAzienda() {
        return idAAEPAzienda;
    }


    /**
     * Sets the idAAEPAzienda value for this AtecoRI1991Infoc.
     * 
     * @param idAAEPAzienda
     */
    public void setIdAAEPAzienda(long idAAEPAzienda) {
        this.idAAEPAzienda = idAAEPAzienda;
    }


    /**
     * Gets the codAttivita value for this AtecoRI1991Infoc.
     * 
     * @return codAttivita
     */
    public java.lang.String getCodAttivita() {
        return codAttivita;
    }


    /**
     * Sets the codAttivita value for this AtecoRI1991Infoc.
     * 
     * @param codAttivita
     */
    public void setCodAttivita(java.lang.String codAttivita) {
        this.codAttivita = codAttivita;
    }


    /**
     * Gets the descrImportanza value for this AtecoRI1991Infoc.
     * 
     * @return descrImportanza
     */
    public java.lang.String getDescrImportanza() {
        return descrImportanza;
    }


    /**
     * Sets the descrImportanza value for this AtecoRI1991Infoc.
     * 
     * @param descrImportanza
     */
    public void setDescrImportanza(java.lang.String descrImportanza) {
        this.descrImportanza = descrImportanza;
    }


    /**
     * Gets the descrAttivita value for this AtecoRI1991Infoc.
     * 
     * @return descrAttivita
     */
    public java.lang.String getDescrAttivita() {
        return descrAttivita;
    }


    /**
     * Sets the descrAttivita value for this AtecoRI1991Infoc.
     * 
     * @param descrAttivita
     */
    public void setDescrAttivita(java.lang.String descrAttivita) {
        this.descrAttivita = descrAttivita;
    }


    /**
     * Gets the idAAEPFonteDato value for this AtecoRI1991Infoc.
     * 
     * @return idAAEPFonteDato
     */
    public long getIdAAEPFonteDato() {
        return idAAEPFonteDato;
    }


    /**
     * Sets the idAAEPFonteDato value for this AtecoRI1991Infoc.
     * 
     * @param idAAEPFonteDato
     */
    public void setIdAAEPFonteDato(long idAAEPFonteDato) {
        this.idAAEPFonteDato = idAAEPFonteDato;
    }


    /**
     * Gets the dataCessazAttivita value for this AtecoRI1991Infoc.
     * 
     * @return dataCessazAttivita
     */
    public java.util.Calendar getDataCessazAttivita() {
        return dataCessazAttivita;
    }


    /**
     * Sets the dataCessazAttivita value for this AtecoRI1991Infoc.
     * 
     * @param dataCessazAttivita
     */
    public void setDataCessazAttivita(java.util.Calendar dataCessazAttivita) {
        this.dataCessazAttivita = dataCessazAttivita;
    }


    /**
     * Gets the dataInizioAttivita value for this AtecoRI1991Infoc.
     * 
     * @return dataInizioAttivita
     */
    public java.util.Calendar getDataInizioAttivita() {
        return dataInizioAttivita;
    }


    /**
     * Sets the dataInizioAttivita value for this AtecoRI1991Infoc.
     * 
     * @param dataInizioAttivita
     */
    public void setDataInizioAttivita(java.util.Calendar dataInizioAttivita) {
        this.dataInizioAttivita = dataInizioAttivita;
    }


    /**
     * Gets the progrSede value for this AtecoRI1991Infoc.
     * 
     * @return progrSede
     */
    public long getProgrSede() {
        return progrSede;
    }


    /**
     * Sets the progrSede value for this AtecoRI1991Infoc.
     * 
     * @param progrSede
     */
    public void setProgrSede(long progrSede) {
        this.progrSede = progrSede;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AtecoRI1991Infoc)) return false;
        AtecoRI1991Infoc other = (AtecoRI1991Infoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codImportanza==null && other.getCodImportanza()==null) || 
             (this.codImportanza!=null &&
              this.codImportanza.equals(other.getCodImportanza()))) &&
            this.idAAEPAzienda == other.getIdAAEPAzienda() &&
            ((this.codAttivita==null && other.getCodAttivita()==null) || 
             (this.codAttivita!=null &&
              this.codAttivita.equals(other.getCodAttivita()))) &&
            ((this.descrImportanza==null && other.getDescrImportanza()==null) || 
             (this.descrImportanza!=null &&
              this.descrImportanza.equals(other.getDescrImportanza()))) &&
            ((this.descrAttivita==null && other.getDescrAttivita()==null) || 
             (this.descrAttivita!=null &&
              this.descrAttivita.equals(other.getDescrAttivita()))) &&
            this.idAAEPFonteDato == other.getIdAAEPFonteDato() &&
            ((this.dataCessazAttivita==null && other.getDataCessazAttivita()==null) || 
             (this.dataCessazAttivita!=null &&
              this.dataCessazAttivita.equals(other.getDataCessazAttivita()))) &&
            ((this.dataInizioAttivita==null && other.getDataInizioAttivita()==null) || 
             (this.dataInizioAttivita!=null &&
              this.dataInizioAttivita.equals(other.getDataInizioAttivita()))) &&
            this.progrSede == other.getProgrSede();
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
        if (getCodImportanza() != null) {
            _hashCode += getCodImportanza().hashCode();
        }
        _hashCode += new Long(getIdAAEPAzienda()).hashCode();
        if (getCodAttivita() != null) {
            _hashCode += getCodAttivita().hashCode();
        }
        if (getDescrImportanza() != null) {
            _hashCode += getDescrImportanza().hashCode();
        }
        if (getDescrAttivita() != null) {
            _hashCode += getDescrAttivita().hashCode();
        }
        _hashCode += new Long(getIdAAEPFonteDato()).hashCode();
        if (getDataCessazAttivita() != null) {
            _hashCode += getDataCessazAttivita().hashCode();
        }
        if (getDataInizioAttivita() != null) {
            _hashCode += getDataInizioAttivita().hashCode();
        }
        _hashCode += new Long(getProgrSede()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AtecoRI1991Infoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "AtecoRI1991Infoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codImportanza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codImportanza"));
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
        elemField.setFieldName("codAttivita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codAttivita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrImportanza");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrImportanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrAttivita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrAttivita"));
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
        elemField.setFieldName("dataCessazAttivita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataCessazAttivita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioAttivita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioAttivita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrSede"));
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
