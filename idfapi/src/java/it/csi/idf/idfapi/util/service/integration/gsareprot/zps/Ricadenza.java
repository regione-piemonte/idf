/**
 * Ricadenza.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.zps;

public class Ricadenza  implements java.io.Serializable {
    private double areaIntersezione;

    private java.lang.String codiceAmministrativo;

    private boolean flagRicadenzaSignificativa;

    private java.lang.String nome;

    private int percentualeDiGeometriaOccupataDalParco;

    private int percentualeDiParcoOccupatoDaGeometria;

    public Ricadenza() {
    }

    public Ricadenza(
           double areaIntersezione,
           java.lang.String codiceAmministrativo,
           boolean flagRicadenzaSignificativa,
           java.lang.String nome,
           int percentualeDiGeometriaOccupataDalParco,
           int percentualeDiParcoOccupatoDaGeometria) {
           this.areaIntersezione = areaIntersezione;
           this.codiceAmministrativo = codiceAmministrativo;
           this.flagRicadenzaSignificativa = flagRicadenzaSignificativa;
           this.nome = nome;
           this.percentualeDiGeometriaOccupataDalParco = percentualeDiGeometriaOccupataDalParco;
           this.percentualeDiParcoOccupatoDaGeometria = percentualeDiParcoOccupatoDaGeometria;
    }


    /**
     * Gets the areaIntersezione value for this Ricadenza.
     * 
     * @return areaIntersezione
     */
    public double getAreaIntersezione() {
        return areaIntersezione;
    }


    /**
     * Sets the areaIntersezione value for this Ricadenza.
     * 
     * @param areaIntersezione
     */
    public void setAreaIntersezione(double areaIntersezione) {
        this.areaIntersezione = areaIntersezione;
    }


    /**
     * Gets the codiceAmministrativo value for this Ricadenza.
     * 
     * @return codiceAmministrativo
     */
    public java.lang.String getCodiceAmministrativo() {
        return codiceAmministrativo;
    }


    /**
     * Sets the codiceAmministrativo value for this Ricadenza.
     * 
     * @param codiceAmministrativo
     */
    public void setCodiceAmministrativo(java.lang.String codiceAmministrativo) {
        this.codiceAmministrativo = codiceAmministrativo;
    }


    /**
     * Gets the flagRicadenzaSignificativa value for this Ricadenza.
     * 
     * @return flagRicadenzaSignificativa
     */
    public boolean isFlagRicadenzaSignificativa() {
        return flagRicadenzaSignificativa;
    }


    /**
     * Sets the flagRicadenzaSignificativa value for this Ricadenza.
     * 
     * @param flagRicadenzaSignificativa
     */
    public void setFlagRicadenzaSignificativa(boolean flagRicadenzaSignificativa) {
        this.flagRicadenzaSignificativa = flagRicadenzaSignificativa;
    }


    /**
     * Gets the nome value for this Ricadenza.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Ricadenza.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the percentualeDiGeometriaOccupataDalParco value for this Ricadenza.
     * 
     * @return percentualeDiGeometriaOccupataDalParco
     */
    public int getPercentualeDiGeometriaOccupataDalParco() {
        return percentualeDiGeometriaOccupataDalParco;
    }


    /**
     * Sets the percentualeDiGeometriaOccupataDalParco value for this Ricadenza.
     * 
     * @param percentualeDiGeometriaOccupataDalParco
     */
    public void setPercentualeDiGeometriaOccupataDalParco(int percentualeDiGeometriaOccupataDalParco) {
        this.percentualeDiGeometriaOccupataDalParco = percentualeDiGeometriaOccupataDalParco;
    }


    /**
     * Gets the percentualeDiParcoOccupatoDaGeometria value for this Ricadenza.
     * 
     * @return percentualeDiParcoOccupatoDaGeometria
     */
    public int getPercentualeDiParcoOccupatoDaGeometria() {
        return percentualeDiParcoOccupatoDaGeometria;
    }


    /**
     * Sets the percentualeDiParcoOccupatoDaGeometria value for this Ricadenza.
     * 
     * @param percentualeDiParcoOccupatoDaGeometria
     */
    public void setPercentualeDiParcoOccupatoDaGeometria(int percentualeDiParcoOccupatoDaGeometria) {
        this.percentualeDiParcoOccupatoDaGeometria = percentualeDiParcoOccupatoDaGeometria;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Ricadenza)) return false;
        Ricadenza other = (Ricadenza) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.areaIntersezione == other.getAreaIntersezione() &&
            ((this.codiceAmministrativo==null && other.getCodiceAmministrativo()==null) || 
             (this.codiceAmministrativo!=null &&
              this.codiceAmministrativo.equals(other.getCodiceAmministrativo()))) &&
            this.flagRicadenzaSignificativa == other.isFlagRicadenzaSignificativa() &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            this.percentualeDiGeometriaOccupataDalParco == other.getPercentualeDiGeometriaOccupataDalParco() &&
            this.percentualeDiParcoOccupatoDaGeometria == other.getPercentualeDiParcoOccupatoDaGeometria();
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
        _hashCode += new Double(getAreaIntersezione()).hashCode();
        if (getCodiceAmministrativo() != null) {
            _hashCode += getCodiceAmministrativo().hashCode();
        }
        _hashCode += (isFlagRicadenzaSignificativa() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        _hashCode += getPercentualeDiGeometriaOccupataDalParco();
        _hashCode += getPercentualeDiParcoOccupatoDaGeometria();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ricadenza.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("zps", "Ricadenza"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaIntersezione");
        elemField.setXmlName(new javax.xml.namespace.QName("zps", "areaIntersezione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAmministrativo");
        elemField.setXmlName(new javax.xml.namespace.QName("zps", "codiceAmministrativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagRicadenzaSignificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("zps", "flagRicadenzaSignificativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("zps", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualeDiGeometriaOccupataDalParco");
        elemField.setXmlName(new javax.xml.namespace.QName("zps", "percentualeDiGeometriaOccupataDalParco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualeDiParcoOccupatoDaGeometria");
        elemField.setXmlName(new javax.xml.namespace.QName("zps", "percentualeDiParcoOccupatoDaGeometria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
