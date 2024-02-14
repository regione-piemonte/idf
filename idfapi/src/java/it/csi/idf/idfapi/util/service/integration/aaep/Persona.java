/**
 * Persona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.aaep;

public class Persona  implements java.io.Serializable {
    private java.lang.String codiceFiscale;

    private java.lang.String nome;

    private java.lang.String descrTipoPersona;

    private java.lang.String tipoPersona;

    private long idAaepAzienda;

    private long proPersona;

    private long idAaepFonteDato;

    private java.lang.String cognome;

    private it.csi.idf.idfapi.util.service.integration.aaep.Carica[] listaCariche;

    public Persona() {
    }

    public Persona(
           java.lang.String codiceFiscale,
           java.lang.String nome,
           java.lang.String descrTipoPersona,
           java.lang.String tipoPersona,
           long idAaepAzienda,
           long proPersona,
           long idAaepFonteDato,
           java.lang.String cognome,
           it.csi.idf.idfapi.util.service.integration.aaep.Carica[] listaCariche) {
           this.codiceFiscale = codiceFiscale;
           this.nome = nome;
           this.descrTipoPersona = descrTipoPersona;
           this.tipoPersona = tipoPersona;
           this.idAaepAzienda = idAaepAzienda;
           this.proPersona = proPersona;
           this.idAaepFonteDato = idAaepFonteDato;
           this.cognome = cognome;
           this.listaCariche = listaCariche;
    }


    /**
     * Gets the codiceFiscale value for this Persona.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this Persona.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the nome value for this Persona.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Persona.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the descrTipoPersona value for this Persona.
     * 
     * @return descrTipoPersona
     */
    public java.lang.String getDescrTipoPersona() {
        return descrTipoPersona;
    }


    /**
     * Sets the descrTipoPersona value for this Persona.
     * 
     * @param descrTipoPersona
     */
    public void setDescrTipoPersona(java.lang.String descrTipoPersona) {
        this.descrTipoPersona = descrTipoPersona;
    }


    /**
     * Gets the tipoPersona value for this Persona.
     * 
     * @return tipoPersona
     */
    public java.lang.String getTipoPersona() {
        return tipoPersona;
    }


    /**
     * Sets the tipoPersona value for this Persona.
     * 
     * @param tipoPersona
     */
    public void setTipoPersona(java.lang.String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }


    /**
     * Gets the idAaepAzienda value for this Persona.
     * 
     * @return idAaepAzienda
     */
    public long getIdAaepAzienda() {
        return idAaepAzienda;
    }


    /**
     * Sets the idAaepAzienda value for this Persona.
     * 
     * @param idAaepAzienda
     */
    public void setIdAaepAzienda(long idAaepAzienda) {
        this.idAaepAzienda = idAaepAzienda;
    }


    /**
     * Gets the proPersona value for this Persona.
     * 
     * @return proPersona
     */
    public long getProPersona() {
        return proPersona;
    }


    /**
     * Sets the proPersona value for this Persona.
     * 
     * @param proPersona
     */
    public void setProPersona(long proPersona) {
        this.proPersona = proPersona;
    }


    /**
     * Gets the idAaepFonteDato value for this Persona.
     * 
     * @return idAaepFonteDato
     */
    public long getIdAaepFonteDato() {
        return idAaepFonteDato;
    }


    /**
     * Sets the idAaepFonteDato value for this Persona.
     * 
     * @param idAaepFonteDato
     */
    public void setIdAaepFonteDato(long idAaepFonteDato) {
        this.idAaepFonteDato = idAaepFonteDato;
    }


    /**
     * Gets the cognome value for this Persona.
     * 
     * @return cognome
     */
    public java.lang.String getCognome() {
        return cognome;
    }


    /**
     * Sets the cognome value for this Persona.
     * 
     * @param cognome
     */
    public void setCognome(java.lang.String cognome) {
        this.cognome = cognome;
    }


    /**
     * Gets the listaCariche value for this Persona.
     * 
     * @return listaCariche
     */
    public it.csi.idf.idfapi.util.service.integration.aaep.Carica[] getListaCariche() {
        return listaCariche;
    }


    /**
     * Sets the listaCariche value for this Persona.
     * 
     * @param listaCariche
     */
    public void setListaCariche(it.csi.idf.idfapi.util.service.integration.aaep.Carica[] listaCariche) {
        this.listaCariche = listaCariche;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Persona)) return false;
        Persona other = (Persona) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.descrTipoPersona==null && other.getDescrTipoPersona()==null) || 
             (this.descrTipoPersona!=null &&
              this.descrTipoPersona.equals(other.getDescrTipoPersona()))) &&
            ((this.tipoPersona==null && other.getTipoPersona()==null) || 
             (this.tipoPersona!=null &&
              this.tipoPersona.equals(other.getTipoPersona()))) &&
            this.idAaepAzienda == other.getIdAaepAzienda() &&
            this.proPersona == other.getProPersona() &&
            this.idAaepFonteDato == other.getIdAaepFonteDato() &&
            ((this.cognome==null && other.getCognome()==null) || 
             (this.cognome!=null &&
              this.cognome.equals(other.getCognome()))) &&
            ((this.listaCariche==null && other.getListaCariche()==null) || 
             (this.listaCariche!=null &&
              java.util.Arrays.equals(this.listaCariche, other.getListaCariche())));
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
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getDescrTipoPersona() != null) {
            _hashCode += getDescrTipoPersona().hashCode();
        }
        if (getTipoPersona() != null) {
            _hashCode += getTipoPersona().hashCode();
        }
        _hashCode += new Long(getIdAaepAzienda()).hashCode();
        _hashCode += new Long(getProPersona()).hashCode();
        _hashCode += new Long(getIdAaepFonteDato()).hashCode();
        if (getCognome() != null) {
            _hashCode += getCognome().hashCode();
        }
        if (getListaCariche() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCariche());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCariche(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Persona.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "Persona"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrTipoPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "descrTipoPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "tipoPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAaepAzienda");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAaepAzienda"));
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
        elemField.setFieldName("idAaepFonteDato");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "idAaepFonteDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cognome");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "cognome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCariche");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:AAEPCSI", "listaCariche"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:AAEPCSI", "Carica"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("urn:AAEPCSI", "item"));
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
