/**
 * Carica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class Carica  implements java.io.Serializable {
    private java.util.Calendar dataFineCarica;

    private long idAaepAzienda;

    private long progrCarica;

    private long proPersona;

    private java.lang.String codiceCarica;

    private java.util.Calendar dataInizioCarica;

    private java.lang.String descrCarica;

    private long idAaepFonteDato;

    private java.lang.String flagRappresentanteLegale;

    public Carica() {
    }

    public Carica(
           java.util.Calendar dataFineCarica,
           long idAaepAzienda,
           long progrCarica,
           long proPersona,
           java.lang.String codiceCarica,
           java.util.Calendar dataInizioCarica,
           java.lang.String descrCarica,
           long idAaepFonteDato,
           java.lang.String flagRappresentanteLegale) {
           this.dataFineCarica = dataFineCarica;
           this.idAaepAzienda = idAaepAzienda;
           this.progrCarica = progrCarica;
           this.proPersona = proPersona;
           this.codiceCarica = codiceCarica;
           this.dataInizioCarica = dataInizioCarica;
           this.descrCarica = descrCarica;
           this.idAaepFonteDato = idAaepFonteDato;
           this.flagRappresentanteLegale = flagRappresentanteLegale;
    }


    /**
     * Gets the dataFineCarica value for this Carica.
     * 
     * @return dataFineCarica
     */
    public java.util.Calendar getDataFineCarica() {
        return dataFineCarica;
    }


    /**
     * Sets the dataFineCarica value for this Carica.
     * 
     * @param dataFineCarica
     */
    public void setDataFineCarica(java.util.Calendar dataFineCarica) {
        this.dataFineCarica = dataFineCarica;
    }


    /**
     * Gets the idAaepAzienda value for this Carica.
     * 
     * @return idAaepAzienda
     */
    public long getIdAaepAzienda() {
        return idAaepAzienda;
    }


    /**
     * Sets the idAaepAzienda value for this Carica.
     * 
     * @param idAaepAzienda
     */
    public void setIdAaepAzienda(long idAaepAzienda) {
        this.idAaepAzienda = idAaepAzienda;
    }


    /**
     * Gets the progrCarica value for this Carica.
     * 
     * @return progrCarica
     */
    public long getProgrCarica() {
        return progrCarica;
    }


    /**
     * Sets the progrCarica value for this Carica.
     * 
     * @param progrCarica
     */
    public void setProgrCarica(long progrCarica) {
        this.progrCarica = progrCarica;
    }


    /**
     * Gets the proPersona value for this Carica.
     * 
     * @return proPersona
     */
    public long getProPersona() {
        return proPersona;
    }


    /**
     * Sets the proPersona value for this Carica.
     * 
     * @param proPersona
     */
    public void setProPersona(long proPersona) {
        this.proPersona = proPersona;
    }


    /**
     * Gets the codiceCarica value for this Carica.
     * 
     * @return codiceCarica
     */
    public java.lang.String getCodiceCarica() {
        return codiceCarica;
    }


    /**
     * Sets the codiceCarica value for this Carica.
     * 
     * @param codiceCarica
     */
    public void setCodiceCarica(java.lang.String codiceCarica) {
        this.codiceCarica = codiceCarica;
    }


    /**
     * Gets the dataInizioCarica value for this Carica.
     * 
     * @return dataInizioCarica
     */
    public java.util.Calendar getDataInizioCarica() {
        return dataInizioCarica;
    }


    /**
     * Sets the dataInizioCarica value for this Carica.
     * 
     * @param dataInizioCarica
     */
    public void setDataInizioCarica(java.util.Calendar dataInizioCarica) {
        this.dataInizioCarica = dataInizioCarica;
    }


    /**
     * Gets the descrCarica value for this Carica.
     * 
     * @return descrCarica
     */
    public java.lang.String getDescrCarica() {
        return descrCarica;
    }


    /**
     * Sets the descrCarica value for this Carica.
     * 
     * @param descrCarica
     */
    public void setDescrCarica(java.lang.String descrCarica) {
        this.descrCarica = descrCarica;
    }


    /**
     * Gets the idAaepFonteDato value for this Carica.
     * 
     * @return idAaepFonteDato
     */
    public long getIdAaepFonteDato() {
        return idAaepFonteDato;
    }


    /**
     * Sets the idAaepFonteDato value for this Carica.
     * 
     * @param idAaepFonteDato
     */
    public void setIdAaepFonteDato(long idAaepFonteDato) {
        this.idAaepFonteDato = idAaepFonteDato;
    }


    /**
     * Gets the flagRappresentanteLegale value for this Carica.
     * 
     * @return flagRappresentanteLegale
     */
    public java.lang.String getFlagRappresentanteLegale() {
        return flagRappresentanteLegale;
    }


    /**
     * Sets the flagRappresentanteLegale value for this Carica.
     * 
     * @param flagRappresentanteLegale
     */
    public void setFlagRappresentanteLegale(java.lang.String flagRappresentanteLegale) {
        this.flagRappresentanteLegale = flagRappresentanteLegale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Carica)) return false;
        Carica other = (Carica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataFineCarica==null && other.getDataFineCarica()==null) || 
             (this.dataFineCarica!=null &&
              this.dataFineCarica.equals(other.getDataFineCarica()))) &&
            this.idAaepAzienda == other.getIdAaepAzienda() &&
            this.progrCarica == other.getProgrCarica() &&
            this.proPersona == other.getProPersona() &&
            ((this.codiceCarica==null && other.getCodiceCarica()==null) || 
             (this.codiceCarica!=null &&
              this.codiceCarica.equals(other.getCodiceCarica()))) &&
            ((this.dataInizioCarica==null && other.getDataInizioCarica()==null) || 
             (this.dataInizioCarica!=null &&
              this.dataInizioCarica.equals(other.getDataInizioCarica()))) &&
            ((this.descrCarica==null && other.getDescrCarica()==null) || 
             (this.descrCarica!=null &&
              this.descrCarica.equals(other.getDescrCarica()))) &&
            this.idAaepFonteDato == other.getIdAaepFonteDato() &&
            ((this.flagRappresentanteLegale==null && other.getFlagRappresentanteLegale()==null) || 
             (this.flagRappresentanteLegale!=null &&
              this.flagRappresentanteLegale.equals(other.getFlagRappresentanteLegale())));
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
        if (getDataFineCarica() != null) {
            _hashCode += getDataFineCarica().hashCode();
        }
        _hashCode += new Long(getIdAaepAzienda()).hashCode();
        _hashCode += new Long(getProgrCarica()).hashCode();
        _hashCode += new Long(getProPersona()).hashCode();
        if (getCodiceCarica() != null) {
            _hashCode += getCodiceCarica().hashCode();
        }
        if (getDataInizioCarica() != null) {
            _hashCode += getDataInizioCarica().hashCode();
        }
        if (getDescrCarica() != null) {
            _hashCode += getDescrCarica().hashCode();
        }
        _hashCode += new Long(getIdAaepFonteDato()).hashCode();
        if (getFlagRappresentanteLegale() != null) {
            _hashCode += getFlagRappresentanteLegale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Carica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "Carica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataFineCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAaepAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAaepAzienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("progrCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "progrCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "proPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataInizioCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrCarica");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrCarica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAaepFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAaepFonteDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagRappresentanteLegale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "flagRappresentanteLegale"));
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
