/**
 * SezSpecInfoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class SezSpecInfoc  implements java.io.Serializable {
    private long idAAEPAzienda;

    private java.lang.String codSezione;

    private java.util.Calendar dataInizio;

    private java.lang.String codiceSezSpec;

    private java.util.Calendar dataFine;

    private java.lang.String codAlbo;

    private long idAAEPFonteDato;

    private long progrSpe;

    private java.lang.String flColtDir;

    public SezSpecInfoc() {
    }

    public SezSpecInfoc(
           long idAAEPAzienda,
           java.lang.String codSezione,
           java.util.Calendar dataInizio,
           java.lang.String codiceSezSpec,
           java.util.Calendar dataFine,
           java.lang.String codAlbo,
           long idAAEPFonteDato,
           long progrSpe,
           java.lang.String flColtDir) {
           this.idAAEPAzienda = idAAEPAzienda;
           this.codSezione = codSezione;
           this.dataInizio = dataInizio;
           this.codiceSezSpec = codiceSezSpec;
           this.dataFine = dataFine;
           this.codAlbo = codAlbo;
           this.idAAEPFonteDato = idAAEPFonteDato;
           this.progrSpe = progrSpe;
           this.flColtDir = flColtDir;
    }


    /**
     * Gets the idAAEPAzienda value for this SezSpecInfoc.
     * 
     * @return idAAEPAzienda
     */
    public long getIdAAEPAzienda() {
        return idAAEPAzienda;
    }


    /**
     * Sets the idAAEPAzienda value for this SezSpecInfoc.
     * 
     * @param idAAEPAzienda
     */
    public void setIdAAEPAzienda(long idAAEPAzienda) {
        this.idAAEPAzienda = idAAEPAzienda;
    }


    /**
     * Gets the codSezione value for this SezSpecInfoc.
     * 
     * @return codSezione
     */
    public java.lang.String getCodSezione() {
        return codSezione;
    }


    /**
     * Sets the codSezione value for this SezSpecInfoc.
     * 
     * @param codSezione
     */
    public void setCodSezione(java.lang.String codSezione) {
        this.codSezione = codSezione;
    }


    /**
     * Gets the dataInizio value for this SezSpecInfoc.
     * 
     * @return dataInizio
     */
    public java.util.Calendar getDataInizio() {
        return dataInizio;
    }


    /**
     * Sets the dataInizio value for this SezSpecInfoc.
     * 
     * @param dataInizio
     */
    public void setDataInizio(java.util.Calendar dataInizio) {
        this.dataInizio = dataInizio;
    }


    /**
     * Gets the codiceSezSpec value for this SezSpecInfoc.
     * 
     * @return codiceSezSpec
     */
    public java.lang.String getCodiceSezSpec() {
        return codiceSezSpec;
    }


    /**
     * Sets the codiceSezSpec value for this SezSpecInfoc.
     * 
     * @param codiceSezSpec
     */
    public void setCodiceSezSpec(java.lang.String codiceSezSpec) {
        this.codiceSezSpec = codiceSezSpec;
    }


    /**
     * Gets the dataFine value for this SezSpecInfoc.
     * 
     * @return dataFine
     */
    public java.util.Calendar getDataFine() {
        return dataFine;
    }


    /**
     * Sets the dataFine value for this SezSpecInfoc.
     * 
     * @param dataFine
     */
    public void setDataFine(java.util.Calendar dataFine) {
        this.dataFine = dataFine;
    }


    /**
     * Gets the codAlbo value for this SezSpecInfoc.
     * 
     * @return codAlbo
     */
    public java.lang.String getCodAlbo() {
        return codAlbo;
    }


    /**
     * Sets the codAlbo value for this SezSpecInfoc.
     * 
     * @param codAlbo
     */
    public void setCodAlbo(java.lang.String codAlbo) {
        this.codAlbo = codAlbo;
    }


    /**
     * Gets the idAAEPFonteDato value for this SezSpecInfoc.
     * 
     * @return idAAEPFonteDato
     */
    public long getIdAAEPFonteDato() {
        return idAAEPFonteDato;
    }


    /**
     * Sets the idAAEPFonteDato value for this SezSpecInfoc.
     * 
     * @param idAAEPFonteDato
     */
    public void setIdAAEPFonteDato(long idAAEPFonteDato) {
        this.idAAEPFonteDato = idAAEPFonteDato;
    }


    /**
     * Gets the progrSpe value for this SezSpecInfoc.
     * 
     * @return progrSpe
     */
    public long getProgrSpe() {
        return progrSpe;
    }


    /**
     * Sets the progrSpe value for this SezSpecInfoc.
     * 
     * @param progrSpe
     */
    public void setProgrSpe(long progrSpe) {
        this.progrSpe = progrSpe;
    }


    /**
     * Gets the flColtDir value for this SezSpecInfoc.
     * 
     * @return flColtDir
     */
    public java.lang.String getFlColtDir() {
        return flColtDir;
    }


    /**
     * Sets the flColtDir value for this SezSpecInfoc.
     * 
     * @param flColtDir
     */
    public void setFlColtDir(java.lang.String flColtDir) {
        this.flColtDir = flColtDir;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SezSpecInfoc)) return false;
        SezSpecInfoc other = (SezSpecInfoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idAAEPAzienda == other.getIdAAEPAzienda() &&
            ((this.codSezione==null && other.getCodSezione()==null) || 
             (this.codSezione!=null &&
              this.codSezione.equals(other.getCodSezione()))) &&
            ((this.dataInizio==null && other.getDataInizio()==null) || 
             (this.dataInizio!=null &&
              this.dataInizio.equals(other.getDataInizio()))) &&
            ((this.codiceSezSpec==null && other.getCodiceSezSpec()==null) || 
             (this.codiceSezSpec!=null &&
              this.codiceSezSpec.equals(other.getCodiceSezSpec()))) &&
            ((this.dataFine==null && other.getDataFine()==null) || 
             (this.dataFine!=null &&
              this.dataFine.equals(other.getDataFine()))) &&
            ((this.codAlbo==null && other.getCodAlbo()==null) || 
             (this.codAlbo!=null &&
              this.codAlbo.equals(other.getCodAlbo()))) &&
            this.idAAEPFonteDato == other.getIdAAEPFonteDato() &&
            this.progrSpe == other.getProgrSpe() &&
            ((this.flColtDir==null && other.getFlColtDir()==null) || 
             (this.flColtDir!=null &&
              this.flColtDir.equals(other.getFlColtDir())));
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
        _hashCode += new Long(getIdAAEPAzienda()).hashCode();
        if (getCodSezione() != null) {
            _hashCode += getCodSezione().hashCode();
        }
        if (getDataInizio() != null) {
            _hashCode += getDataInizio().hashCode();
        }
        if (getCodiceSezSpec() != null) {
            _hashCode += getCodiceSezSpec().hashCode();
        }
        if (getDataFine() != null) {
            _hashCode += getDataFine().hashCode();
        }
        if (getCodAlbo() != null) {
            _hashCode += getCodAlbo().hashCode();
        }
        _hashCode += new Long(getIdAAEPFonteDato()).hashCode();
        _hashCode += new Long(getProgrSpe()).hashCode();
        if (getFlColtDir() != null) {
            _hashCode += getFlColtDir().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SezSpecInfoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "SezSpecInfoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAAEPAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAAEPAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codSezione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codSezione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizio");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceSezSpec");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceSezSpec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFine");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataFine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codAlbo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codAlbo"));
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
        elemField.setFieldName("progrSpe");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrSpe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flColtDir");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flColtDir"));
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
