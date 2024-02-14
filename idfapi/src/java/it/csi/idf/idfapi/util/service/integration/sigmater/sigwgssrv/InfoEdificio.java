/**
 * InfoEdificio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public class InfoEdificio  implements java.io.Serializable {
    private java.lang.String allegato;

    private java.lang.String codBelfioreComune;

    private java.lang.String codIstatComune;

    private java.lang.String foglio;

    private it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIPolygon geometria;

    private java.lang.String numero;

    private java.lang.String sezione;

    private java.lang.String sviluppo;

    public InfoEdificio() {
    }

    public InfoEdificio(
           java.lang.String allegato,
           java.lang.String codBelfioreComune,
           java.lang.String codIstatComune,
           java.lang.String foglio,
           it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIPolygon geometria,
           java.lang.String numero,
           java.lang.String sezione,
           java.lang.String sviluppo) {
           this.allegato = allegato;
           this.codBelfioreComune = codBelfioreComune;
           this.codIstatComune = codIstatComune;
           this.foglio = foglio;
           this.geometria = geometria;
           this.numero = numero;
           this.sezione = sezione;
           this.sviluppo = sviluppo;
    }


    /**
     * Gets the allegato value for this InfoEdificio.
     * 
     * @return allegato
     */
    public java.lang.String getAllegato() {
        return allegato;
    }


    /**
     * Sets the allegato value for this InfoEdificio.
     * 
     * @param allegato
     */
    public void setAllegato(java.lang.String allegato) {
        this.allegato = allegato;
    }


    /**
     * Gets the codBelfioreComune value for this InfoEdificio.
     * 
     * @return codBelfioreComune
     */
    public java.lang.String getCodBelfioreComune() {
        return codBelfioreComune;
    }


    /**
     * Sets the codBelfioreComune value for this InfoEdificio.
     * 
     * @param codBelfioreComune
     */
    public void setCodBelfioreComune(java.lang.String codBelfioreComune) {
        this.codBelfioreComune = codBelfioreComune;
    }


    /**
     * Gets the codIstatComune value for this InfoEdificio.
     * 
     * @return codIstatComune
     */
    public java.lang.String getCodIstatComune() {
        return codIstatComune;
    }


    /**
     * Sets the codIstatComune value for this InfoEdificio.
     * 
     * @param codIstatComune
     */
    public void setCodIstatComune(java.lang.String codIstatComune) {
        this.codIstatComune = codIstatComune;
    }


    /**
     * Gets the foglio value for this InfoEdificio.
     * 
     * @return foglio
     */
    public java.lang.String getFoglio() {
        return foglio;
    }


    /**
     * Sets the foglio value for this InfoEdificio.
     * 
     * @param foglio
     */
    public void setFoglio(java.lang.String foglio) {
        this.foglio = foglio;
    }


    /**
     * Gets the geometria value for this InfoEdificio.
     * 
     * @return geometria
     */
    public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIPolygon getGeometria() {
        return geometria;
    }


    /**
     * Sets the geometria value for this InfoEdificio.
     * 
     * @param geometria
     */
    public void setGeometria(it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIPolygon geometria) {
        this.geometria = geometria;
    }


    /**
     * Gets the numero value for this InfoEdificio.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this InfoEdificio.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the sezione value for this InfoEdificio.
     * 
     * @return sezione
     */
    public java.lang.String getSezione() {
        return sezione;
    }


    /**
     * Sets the sezione value for this InfoEdificio.
     * 
     * @param sezione
     */
    public void setSezione(java.lang.String sezione) {
        this.sezione = sezione;
    }


    /**
     * Gets the sviluppo value for this InfoEdificio.
     * 
     * @return sviluppo
     */
    public java.lang.String getSviluppo() {
        return sviluppo;
    }


    /**
     * Sets the sviluppo value for this InfoEdificio.
     * 
     * @param sviluppo
     */
    public void setSviluppo(java.lang.String sviluppo) {
        this.sviluppo = sviluppo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InfoEdificio)) return false;
        InfoEdificio other = (InfoEdificio) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.allegato==null && other.getAllegato()==null) || 
             (this.allegato!=null &&
              this.allegato.equals(other.getAllegato()))) &&
            ((this.codBelfioreComune==null && other.getCodBelfioreComune()==null) || 
             (this.codBelfioreComune!=null &&
              this.codBelfioreComune.equals(other.getCodBelfioreComune()))) &&
            ((this.codIstatComune==null && other.getCodIstatComune()==null) || 
             (this.codIstatComune!=null &&
              this.codIstatComune.equals(other.getCodIstatComune()))) &&
            ((this.foglio==null && other.getFoglio()==null) || 
             (this.foglio!=null &&
              this.foglio.equals(other.getFoglio()))) &&
            ((this.geometria==null && other.getGeometria()==null) || 
             (this.geometria!=null &&
              this.geometria.equals(other.getGeometria()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.sezione==null && other.getSezione()==null) || 
             (this.sezione!=null &&
              this.sezione.equals(other.getSezione()))) &&
            ((this.sviluppo==null && other.getSviluppo()==null) || 
             (this.sviluppo!=null &&
              this.sviluppo.equals(other.getSviluppo())));
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
        if (getAllegato() != null) {
            _hashCode += getAllegato().hashCode();
        }
        if (getCodBelfioreComune() != null) {
            _hashCode += getCodBelfioreComune().hashCode();
        }
        if (getCodIstatComune() != null) {
            _hashCode += getCodIstatComune().hashCode();
        }
        if (getFoglio() != null) {
            _hashCode += getFoglio().hashCode();
        }
        if (getGeometria() != null) {
            _hashCode += getGeometria().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getSezione() != null) {
            _hashCode += getSezione().hashCode();
        }
        if (getSviluppo() != null) {
            _hashCode += getSviluppo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InfoEdificio.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "InfoEdificio"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allegato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "allegato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codBelfioreComune");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "codBelfioreComune"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codIstatComune");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "codIstatComune"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foglio");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "foglio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("geometria");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "geometria"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "CSIPolygon"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sezione");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "sezione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sviluppo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "sviluppo"));
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
