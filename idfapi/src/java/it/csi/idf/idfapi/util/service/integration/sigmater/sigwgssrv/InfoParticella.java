/**
 * InfoParticella.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv;

public class InfoParticella  implements java.io.Serializable {
    private java.lang.String allegato;

    private double area;

    private java.lang.String codBelfioreComune;

    private java.lang.String codIstatComune;

    private java.lang.String dataAggiornamento;

    private java.lang.String foglio;

    private java.lang.String geometriaGML;

    private java.lang.String numero;

    private java.lang.String sezione;

    private java.lang.String sviluppo;

    public InfoParticella() {
    }

    public InfoParticella(
           java.lang.String allegato,
           double area,
           java.lang.String codBelfioreComune,
           java.lang.String codIstatComune,
           java.lang.String dataAggiornamento,
           java.lang.String foglio,
           java.lang.String geometriaGML,
           java.lang.String numero,
           java.lang.String sezione,
           java.lang.String sviluppo) {
           this.allegato = allegato;
           this.area = area;
           this.codBelfioreComune = codBelfioreComune;
           this.codIstatComune = codIstatComune;
           this.dataAggiornamento = dataAggiornamento;
           this.foglio = foglio;
           this.geometriaGML = geometriaGML;
           this.numero = numero;
           this.sezione = sezione;
           this.sviluppo = sviluppo;
    }


    /**
     * Gets the allegato value for this InfoParticella.
     * 
     * @return allegato
     */
    public java.lang.String getAllegato() {
        return allegato;
    }


    /**
     * Sets the allegato value for this InfoParticella.
     * 
     * @param allegato
     */
    public void setAllegato(java.lang.String allegato) {
        this.allegato = allegato;
    }


    /**
     * Gets the area value for this InfoParticella.
     * 
     * @return area
     */
    public double getArea() {
        return area;
    }


    /**
     * Sets the area value for this InfoParticella.
     * 
     * @param area
     */
    public void setArea(double area) {
        this.area = area;
    }


    /**
     * Gets the codBelfioreComune value for this InfoParticella.
     * 
     * @return codBelfioreComune
     */
    public java.lang.String getCodBelfioreComune() {
        return codBelfioreComune;
    }


    /**
     * Sets the codBelfioreComune value for this InfoParticella.
     * 
     * @param codBelfioreComune
     */
    public void setCodBelfioreComune(java.lang.String codBelfioreComune) {
        this.codBelfioreComune = codBelfioreComune;
    }


    /**
     * Gets the codIstatComune value for this InfoParticella.
     * 
     * @return codIstatComune
     */
    public java.lang.String getCodIstatComune() {
        return codIstatComune;
    }


    /**
     * Sets the codIstatComune value for this InfoParticella.
     * 
     * @param codIstatComune
     */
    public void setCodIstatComune(java.lang.String codIstatComune) {
        this.codIstatComune = codIstatComune;
    }


    /**
     * Gets the dataAggiornamento value for this InfoParticella.
     * 
     * @return dataAggiornamento
     */
    public java.lang.String getDataAggiornamento() {
        return dataAggiornamento;
    }


    /**
     * Sets the dataAggiornamento value for this InfoParticella.
     * 
     * @param dataAggiornamento
     */
    public void setDataAggiornamento(java.lang.String dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }


    /**
     * Gets the foglio value for this InfoParticella.
     * 
     * @return foglio
     */
    public java.lang.String getFoglio() {
        return foglio;
    }


    /**
     * Sets the foglio value for this InfoParticella.
     * 
     * @param foglio
     */
    public void setFoglio(java.lang.String foglio) {
        this.foglio = foglio;
    }


    /**
     * Gets the geometriaGML value for this InfoParticella.
     * 
     * @return geometriaGML
     */
    public java.lang.String getGeometriaGML() {
        return geometriaGML;
    }


    /**
     * Sets the geometriaGML value for this InfoParticella.
     * 
     * @param geometriaGML
     */
    public void setGeometriaGML(java.lang.String geometriaGML) {
        this.geometriaGML = geometriaGML;
    }


    /**
     * Gets the numero value for this InfoParticella.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this InfoParticella.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the sezione value for this InfoParticella.
     * 
     * @return sezione
     */
    public java.lang.String getSezione() {
        return sezione;
    }


    /**
     * Sets the sezione value for this InfoParticella.
     * 
     * @param sezione
     */
    public void setSezione(java.lang.String sezione) {
        this.sezione = sezione;
    }


    /**
     * Gets the sviluppo value for this InfoParticella.
     * 
     * @return sviluppo
     */
    public java.lang.String getSviluppo() {
        return sviluppo;
    }


    /**
     * Sets the sviluppo value for this InfoParticella.
     * 
     * @param sviluppo
     */
    public void setSviluppo(java.lang.String sviluppo) {
        this.sviluppo = sviluppo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InfoParticella)) return false;
        InfoParticella other = (InfoParticella) obj;
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
            this.area == other.getArea() &&
            ((this.codBelfioreComune==null && other.getCodBelfioreComune()==null) || 
             (this.codBelfioreComune!=null &&
              this.codBelfioreComune.equals(other.getCodBelfioreComune()))) &&
            ((this.codIstatComune==null && other.getCodIstatComune()==null) || 
             (this.codIstatComune!=null &&
              this.codIstatComune.equals(other.getCodIstatComune()))) &&
            ((this.dataAggiornamento==null && other.getDataAggiornamento()==null) || 
             (this.dataAggiornamento!=null &&
              this.dataAggiornamento.equals(other.getDataAggiornamento()))) &&
            ((this.foglio==null && other.getFoglio()==null) || 
             (this.foglio!=null &&
              this.foglio.equals(other.getFoglio()))) &&
            ((this.geometriaGML==null && other.getGeometriaGML()==null) || 
             (this.geometriaGML!=null &&
              this.geometriaGML.equals(other.getGeometriaGML()))) &&
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
        _hashCode += new Double(getArea()).hashCode();
        if (getCodBelfioreComune() != null) {
            _hashCode += getCodBelfioreComune().hashCode();
        }
        if (getCodIstatComune() != null) {
            _hashCode += getCodIstatComune().hashCode();
        }
        if (getDataAggiornamento() != null) {
            _hashCode += getDataAggiornamento().hashCode();
        }
        if (getFoglio() != null) {
            _hashCode += getFoglio().hashCode();
        }
        if (getGeometriaGML() != null) {
            _hashCode += getGeometriaGML().hashCode();
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
        new org.apache.axis.description.TypeDesc(InfoParticella.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "InfoParticella"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allegato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "allegato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("area");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "area"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
        elemField.setFieldName("dataAggiornamento");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "dataAggiornamento"));
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
        elemField.setFieldName("geometriaGML");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:sigwgssrvSigwgssrv", "geometriaGML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
