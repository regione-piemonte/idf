/**
 * ListaSediSILP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ListaSediSILP  implements java.io.Serializable {
    private java.lang.String indirizzo;

    private java.lang.String descrStatoEstero;

    private java.lang.String descrComune;

    private java.lang.String descrTipoSede;

    private java.lang.String idSede;

    private java.lang.String siglaProv;

    private java.lang.String localita;

    private java.lang.String denominazioneSede;

    public ListaSediSILP() {
    }

    public ListaSediSILP(
           java.lang.String indirizzo,
           java.lang.String descrStatoEstero,
           java.lang.String descrComune,
           java.lang.String descrTipoSede,
           java.lang.String idSede,
           java.lang.String siglaProv,
           java.lang.String localita,
           java.lang.String denominazioneSede) {
           this.indirizzo = indirizzo;
           this.descrStatoEstero = descrStatoEstero;
           this.descrComune = descrComune;
           this.descrTipoSede = descrTipoSede;
           this.idSede = idSede;
           this.siglaProv = siglaProv;
           this.localita = localita;
           this.denominazioneSede = denominazioneSede;
    }


    /**
     * Gets the indirizzo value for this ListaSediSILP.
     * 
     * @return indirizzo
     */
    public java.lang.String getIndirizzo() {
        return indirizzo;
    }


    /**
     * Sets the indirizzo value for this ListaSediSILP.
     * 
     * @param indirizzo
     */
    public void setIndirizzo(java.lang.String indirizzo) {
        this.indirizzo = indirizzo;
    }


    /**
     * Gets the descrStatoEstero value for this ListaSediSILP.
     * 
     * @return descrStatoEstero
     */
    public java.lang.String getDescrStatoEstero() {
        return descrStatoEstero;
    }


    /**
     * Sets the descrStatoEstero value for this ListaSediSILP.
     * 
     * @param descrStatoEstero
     */
    public void setDescrStatoEstero(java.lang.String descrStatoEstero) {
        this.descrStatoEstero = descrStatoEstero;
    }


    /**
     * Gets the descrComune value for this ListaSediSILP.
     * 
     * @return descrComune
     */
    public java.lang.String getDescrComune() {
        return descrComune;
    }


    /**
     * Sets the descrComune value for this ListaSediSILP.
     * 
     * @param descrComune
     */
    public void setDescrComune(java.lang.String descrComune) {
        this.descrComune = descrComune;
    }


    /**
     * Gets the descrTipoSede value for this ListaSediSILP.
     * 
     * @return descrTipoSede
     */
    public java.lang.String getDescrTipoSede() {
        return descrTipoSede;
    }


    /**
     * Sets the descrTipoSede value for this ListaSediSILP.
     * 
     * @param descrTipoSede
     */
    public void setDescrTipoSede(java.lang.String descrTipoSede) {
        this.descrTipoSede = descrTipoSede;
    }


    /**
     * Gets the idSede value for this ListaSediSILP.
     * 
     * @return idSede
     */
    public java.lang.String getIdSede() {
        return idSede;
    }


    /**
     * Sets the idSede value for this ListaSediSILP.
     * 
     * @param idSede
     */
    public void setIdSede(java.lang.String idSede) {
        this.idSede = idSede;
    }


    /**
     * Gets the siglaProv value for this ListaSediSILP.
     * 
     * @return siglaProv
     */
    public java.lang.String getSiglaProv() {
        return siglaProv;
    }


    /**
     * Sets the siglaProv value for this ListaSediSILP.
     * 
     * @param siglaProv
     */
    public void setSiglaProv(java.lang.String siglaProv) {
        this.siglaProv = siglaProv;
    }


    /**
     * Gets the localita value for this ListaSediSILP.
     * 
     * @return localita
     */
    public java.lang.String getLocalita() {
        return localita;
    }


    /**
     * Sets the localita value for this ListaSediSILP.
     * 
     * @param localita
     */
    public void setLocalita(java.lang.String localita) {
        this.localita = localita;
    }


    /**
     * Gets the denominazioneSede value for this ListaSediSILP.
     * 
     * @return denominazioneSede
     */
    public java.lang.String getDenominazioneSede() {
        return denominazioneSede;
    }


    /**
     * Sets the denominazioneSede value for this ListaSediSILP.
     * 
     * @param denominazioneSede
     */
    public void setDenominazioneSede(java.lang.String denominazioneSede) {
        this.denominazioneSede = denominazioneSede;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaSediSILP)) return false;
        ListaSediSILP other = (ListaSediSILP) obj;
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
            ((this.descrStatoEstero==null && other.getDescrStatoEstero()==null) || 
             (this.descrStatoEstero!=null &&
              this.descrStatoEstero.equals(other.getDescrStatoEstero()))) &&
            ((this.descrComune==null && other.getDescrComune()==null) || 
             (this.descrComune!=null &&
              this.descrComune.equals(other.getDescrComune()))) &&
            ((this.descrTipoSede==null && other.getDescrTipoSede()==null) || 
             (this.descrTipoSede!=null &&
              this.descrTipoSede.equals(other.getDescrTipoSede()))) &&
            ((this.idSede==null && other.getIdSede()==null) || 
             (this.idSede!=null &&
              this.idSede.equals(other.getIdSede()))) &&
            ((this.siglaProv==null && other.getSiglaProv()==null) || 
             (this.siglaProv!=null &&
              this.siglaProv.equals(other.getSiglaProv()))) &&
            ((this.localita==null && other.getLocalita()==null) || 
             (this.localita!=null &&
              this.localita.equals(other.getLocalita()))) &&
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
        if (getDescrStatoEstero() != null) {
            _hashCode += getDescrStatoEstero().hashCode();
        }
        if (getDescrComune() != null) {
            _hashCode += getDescrComune().hashCode();
        }
        if (getDescrTipoSede() != null) {
            _hashCode += getDescrTipoSede().hashCode();
        }
        if (getIdSede() != null) {
            _hashCode += getIdSede().hashCode();
        }
        if (getSiglaProv() != null) {
            _hashCode += getSiglaProv().hashCode();
        }
        if (getLocalita() != null) {
            _hashCode += getLocalita().hashCode();
        }
        if (getDenominazioneSede() != null) {
            _hashCode += getDenominazioneSede().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaSediSILP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ListaSediSILP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "indirizzo"));
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
        elemField.setFieldName("descrComune");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrComune"));
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
        elemField.setFieldName("idSede");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idSede"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("siglaProv");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "siglaProv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localita");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "localita"));
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
