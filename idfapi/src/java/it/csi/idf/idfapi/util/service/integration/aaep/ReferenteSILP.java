/**
 * ReferenteSILP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class ReferenteSILP  implements java.io.Serializable {
    private java.lang.String cognomeReferente;

    private java.lang.String ruoloReferente;

    private java.lang.String idReferente;

    private java.lang.String fax;

    private java.lang.String dataUltAggiornam;

    private java.lang.String nomeReferente;

    private java.lang.String dataUltAggiornamSILP;

    private java.lang.String telefono;

    private java.lang.String EMail;

    public ReferenteSILP() {
    }

    public ReferenteSILP(
           java.lang.String cognomeReferente,
           java.lang.String ruoloReferente,
           java.lang.String idReferente,
           java.lang.String fax,
           java.lang.String dataUltAggiornam,
           java.lang.String nomeReferente,
           java.lang.String dataUltAggiornamSILP,
           java.lang.String telefono,
           java.lang.String EMail) {
           this.cognomeReferente = cognomeReferente;
           this.ruoloReferente = ruoloReferente;
           this.idReferente = idReferente;
           this.fax = fax;
           this.dataUltAggiornam = dataUltAggiornam;
           this.nomeReferente = nomeReferente;
           this.dataUltAggiornamSILP = dataUltAggiornamSILP;
           this.telefono = telefono;
           this.EMail = EMail;
    }


    /**
     * Gets the cognomeReferente value for this ReferenteSILP.
     * 
     * @return cognomeReferente
     */
    public java.lang.String getCognomeReferente() {
        return cognomeReferente;
    }


    /**
     * Sets the cognomeReferente value for this ReferenteSILP.
     * 
     * @param cognomeReferente
     */
    public void setCognomeReferente(java.lang.String cognomeReferente) {
        this.cognomeReferente = cognomeReferente;
    }


    /**
     * Gets the ruoloReferente value for this ReferenteSILP.
     * 
     * @return ruoloReferente
     */
    public java.lang.String getRuoloReferente() {
        return ruoloReferente;
    }


    /**
     * Sets the ruoloReferente value for this ReferenteSILP.
     * 
     * @param ruoloReferente
     */
    public void setRuoloReferente(java.lang.String ruoloReferente) {
        this.ruoloReferente = ruoloReferente;
    }


    /**
     * Gets the idReferente value for this ReferenteSILP.
     * 
     * @return idReferente
     */
    public java.lang.String getIdReferente() {
        return idReferente;
    }


    /**
     * Sets the idReferente value for this ReferenteSILP.
     * 
     * @param idReferente
     */
    public void setIdReferente(java.lang.String idReferente) {
        this.idReferente = idReferente;
    }


    /**
     * Gets the fax value for this ReferenteSILP.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this ReferenteSILP.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the dataUltAggiornam value for this ReferenteSILP.
     * 
     * @return dataUltAggiornam
     */
    public java.lang.String getDataUltAggiornam() {
        return dataUltAggiornam;
    }


    /**
     * Sets the dataUltAggiornam value for this ReferenteSILP.
     * 
     * @param dataUltAggiornam
     */
    public void setDataUltAggiornam(java.lang.String dataUltAggiornam) {
        this.dataUltAggiornam = dataUltAggiornam;
    }


    /**
     * Gets the nomeReferente value for this ReferenteSILP.
     * 
     * @return nomeReferente
     */
    public java.lang.String getNomeReferente() {
        return nomeReferente;
    }


    /**
     * Sets the nomeReferente value for this ReferenteSILP.
     * 
     * @param nomeReferente
     */
    public void setNomeReferente(java.lang.String nomeReferente) {
        this.nomeReferente = nomeReferente;
    }


    /**
     * Gets the dataUltAggiornamSILP value for this ReferenteSILP.
     * 
     * @return dataUltAggiornamSILP
     */
    public java.lang.String getDataUltAggiornamSILP() {
        return dataUltAggiornamSILP;
    }


    /**
     * Sets the dataUltAggiornamSILP value for this ReferenteSILP.
     * 
     * @param dataUltAggiornamSILP
     */
    public void setDataUltAggiornamSILP(java.lang.String dataUltAggiornamSILP) {
        this.dataUltAggiornamSILP = dataUltAggiornamSILP;
    }


    /**
     * Gets the telefono value for this ReferenteSILP.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this ReferenteSILP.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the EMail value for this ReferenteSILP.
     * 
     * @return EMail
     */
    public java.lang.String getEMail() {
        return EMail;
    }


    /**
     * Sets the EMail value for this ReferenteSILP.
     * 
     * @param EMail
     */
    public void setEMail(java.lang.String EMail) {
        this.EMail = EMail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReferenteSILP)) return false;
        ReferenteSILP other = (ReferenteSILP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cognomeReferente==null && other.getCognomeReferente()==null) || 
             (this.cognomeReferente!=null &&
              this.cognomeReferente.equals(other.getCognomeReferente()))) &&
            ((this.ruoloReferente==null && other.getRuoloReferente()==null) || 
             (this.ruoloReferente!=null &&
              this.ruoloReferente.equals(other.getRuoloReferente()))) &&
            ((this.idReferente==null && other.getIdReferente()==null) || 
             (this.idReferente!=null &&
              this.idReferente.equals(other.getIdReferente()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.dataUltAggiornam==null && other.getDataUltAggiornam()==null) || 
             (this.dataUltAggiornam!=null &&
              this.dataUltAggiornam.equals(other.getDataUltAggiornam()))) &&
            ((this.nomeReferente==null && other.getNomeReferente()==null) || 
             (this.nomeReferente!=null &&
              this.nomeReferente.equals(other.getNomeReferente()))) &&
            ((this.dataUltAggiornamSILP==null && other.getDataUltAggiornamSILP()==null) || 
             (this.dataUltAggiornamSILP!=null &&
              this.dataUltAggiornamSILP.equals(other.getDataUltAggiornamSILP()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.EMail==null && other.getEMail()==null) || 
             (this.EMail!=null &&
              this.EMail.equals(other.getEMail())));
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
        if (getCognomeReferente() != null) {
            _hashCode += getCognomeReferente().hashCode();
        }
        if (getRuoloReferente() != null) {
            _hashCode += getRuoloReferente().hashCode();
        }
        if (getIdReferente() != null) {
            _hashCode += getIdReferente().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getDataUltAggiornam() != null) {
            _hashCode += getDataUltAggiornam().hashCode();
        }
        if (getNomeReferente() != null) {
            _hashCode += getNomeReferente().hashCode();
        }
        if (getDataUltAggiornamSILP() != null) {
            _hashCode += getDataUltAggiornamSILP().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getEMail() != null) {
            _hashCode += getEMail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReferenteSILP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "ReferenteSILP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cognomeReferente");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "cognomeReferente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ruoloReferente");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "ruoloReferente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idReferente");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idReferente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltAggiornam");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataUltAggiornam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeReferente");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "nomeReferente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltAggiornamSILP");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "dataUltAggiornamSILP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMail");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "EMail"));
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
