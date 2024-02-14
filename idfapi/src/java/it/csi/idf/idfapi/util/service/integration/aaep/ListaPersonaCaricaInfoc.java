/**
 * ListaPersonaCaricaInfoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ListaPersonaCaricaInfoc  implements java.io.Serializable {
    private java.lang.String descrCarica;

    private java.lang.String codFiscalePersona;

    private java.lang.String descrAzienda;

    private java.util.Calendar dataFineCarica;

    private java.util.Calendar dataInizioCarica;

    private java.lang.String codiceCarica;

    private java.lang.String codFiscaleAzienda;

    public ListaPersonaCaricaInfoc() {
    }

    public ListaPersonaCaricaInfoc(
           java.lang.String descrCarica,
           java.lang.String codFiscalePersona,
           java.lang.String descrAzienda,
           java.util.Calendar dataFineCarica,
           java.util.Calendar dataInizioCarica,
           java.lang.String codiceCarica,
           java.lang.String codFiscaleAzienda) {
           this.descrCarica = descrCarica;
           this.codFiscalePersona = codFiscalePersona;
           this.descrAzienda = descrAzienda;
           this.dataFineCarica = dataFineCarica;
           this.dataInizioCarica = dataInizioCarica;
           this.codiceCarica = codiceCarica;
           this.codFiscaleAzienda = codFiscaleAzienda;
    }


    /**
     * Gets the descrCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @return descrCarica
     */
    public java.lang.String getDescrCarica() {
        return descrCarica;
    }


    /**
     * Sets the descrCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @param descrCarica
     */
    public void setDescrCarica(java.lang.String descrCarica) {
        this.descrCarica = descrCarica;
    }


    /**
     * Gets the codFiscalePersona value for this ListaPersonaCaricaInfoc.
     * 
     * @return codFiscalePersona
     */
    public java.lang.String getCodFiscalePersona() {
        return codFiscalePersona;
    }


    /**
     * Sets the codFiscalePersona value for this ListaPersonaCaricaInfoc.
     * 
     * @param codFiscalePersona
     */
    public void setCodFiscalePersona(java.lang.String codFiscalePersona) {
        this.codFiscalePersona = codFiscalePersona;
    }


    /**
     * Gets the descrAzienda value for this ListaPersonaCaricaInfoc.
     * 
     * @return descrAzienda
     */
    public java.lang.String getDescrAzienda() {
        return descrAzienda;
    }


    /**
     * Sets the descrAzienda value for this ListaPersonaCaricaInfoc.
     * 
     * @param descrAzienda
     */
    public void setDescrAzienda(java.lang.String descrAzienda) {
        this.descrAzienda = descrAzienda;
    }


    /**
     * Gets the dataFineCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @return dataFineCarica
     */
    public java.util.Calendar getDataFineCarica() {
        return dataFineCarica;
    }


    /**
     * Sets the dataFineCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @param dataFineCarica
     */
    public void setDataFineCarica(java.util.Calendar dataFineCarica) {
        this.dataFineCarica = dataFineCarica;
    }


    /**
     * Gets the dataInizioCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @return dataInizioCarica
     */
    public java.util.Calendar getDataInizioCarica() {
        return dataInizioCarica;
    }


    /**
     * Sets the dataInizioCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @param dataInizioCarica
     */
    public void setDataInizioCarica(java.util.Calendar dataInizioCarica) {
        this.dataInizioCarica = dataInizioCarica;
    }


    /**
     * Gets the codiceCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @return codiceCarica
     */
    public java.lang.String getCodiceCarica() {
        return codiceCarica;
    }


    /**
     * Sets the codiceCarica value for this ListaPersonaCaricaInfoc.
     * 
     * @param codiceCarica
     */
    public void setCodiceCarica(java.lang.String codiceCarica) {
        this.codiceCarica = codiceCarica;
    }


    /**
     * Gets the codFiscaleAzienda value for this ListaPersonaCaricaInfoc.
     * 
     * @return codFiscaleAzienda
     */
    public java.lang.String getCodFiscaleAzienda() {
        return codFiscaleAzienda;
    }


    /**
     * Sets the codFiscaleAzienda value for this ListaPersonaCaricaInfoc.
     * 
     * @param codFiscaleAzienda
     */
    public void setCodFiscaleAzienda(java.lang.String codFiscaleAzienda) {
        this.codFiscaleAzienda = codFiscaleAzienda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaPersonaCaricaInfoc)) return false;
        ListaPersonaCaricaInfoc other = (ListaPersonaCaricaInfoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descrCarica==null && other.getDescrCarica()==null) || 
             (this.descrCarica!=null &&
              this.descrCarica.equals(other.getDescrCarica()))) &&
            ((this.codFiscalePersona==null && other.getCodFiscalePersona()==null) || 
             (this.codFiscalePersona!=null &&
              this.codFiscalePersona.equals(other.getCodFiscalePersona()))) &&
            ((this.descrAzienda==null && other.getDescrAzienda()==null) || 
             (this.descrAzienda!=null &&
              this.descrAzienda.equals(other.getDescrAzienda()))) &&
            ((this.dataFineCarica==null && other.getDataFineCarica()==null) || 
             (this.dataFineCarica!=null &&
              this.dataFineCarica.equals(other.getDataFineCarica()))) &&
            ((this.dataInizioCarica==null && other.getDataInizioCarica()==null) || 
             (this.dataInizioCarica!=null &&
              this.dataInizioCarica.equals(other.getDataInizioCarica()))) &&
            ((this.codiceCarica==null && other.getCodiceCarica()==null) || 
             (this.codiceCarica!=null &&
              this.codiceCarica.equals(other.getCodiceCarica()))) &&
            ((this.codFiscaleAzienda==null && other.getCodFiscaleAzienda()==null) || 
             (this.codFiscaleAzienda!=null &&
              this.codFiscaleAzienda.equals(other.getCodFiscaleAzienda())));
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
        if (getDescrCarica() != null) {
            _hashCode += getDescrCarica().hashCode();
        }
        if (getCodFiscalePersona() != null) {
            _hashCode += getCodFiscalePersona().hashCode();
        }
        if (getDescrAzienda() != null) {
            _hashCode += getDescrAzienda().hashCode();
        }
        if (getDataFineCarica() != null) {
            _hashCode += getDataFineCarica().hashCode();
        }
        if (getDataInizioCarica() != null) {
            _hashCode += getDataInizioCarica().hashCode();
        }
        if (getCodiceCarica() != null) {
            _hashCode += getCodiceCarica().hashCode();
        }
        if (getCodFiscaleAzienda() != null) {
            _hashCode += getCodFiscaleAzienda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaPersonaCaricaInfoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaPersonaCaricaInfoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codFiscalePersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codFiscalePersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataFineCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codFiscaleAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codFiscaleAzienda"));
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
