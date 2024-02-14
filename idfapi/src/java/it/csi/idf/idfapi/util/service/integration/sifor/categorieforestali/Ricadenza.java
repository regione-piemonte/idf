/**
 * Ricadenza.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali;

public class Ricadenza  implements java.io.Serializable {
    private double areaIntersezione;

    private java.lang.String codiceAmministrativo;

    private java.lang.String descrizione;

    private boolean flagRicadenzaSignificativa;

    private int percantualeDiForestaOccupataDallaGeometria;

    private int percantualeDiGeometriaOccupataDallaForesta;

    public Ricadenza() {
    }

    public Ricadenza(
           double areaIntersezione,
           java.lang.String codiceAmministrativo,
           java.lang.String descrizione,
           boolean flagRicadenzaSignificativa,
           int percantualeDiForestaOccupataDallaGeometria,
           int percantualeDiGeometriaOccupataDallaForesta) {
           this.areaIntersezione = areaIntersezione;
           this.codiceAmministrativo = codiceAmministrativo;
           this.descrizione = descrizione;
           this.flagRicadenzaSignificativa = flagRicadenzaSignificativa;
           this.percantualeDiForestaOccupataDallaGeometria = percantualeDiForestaOccupataDallaGeometria;
           this.percantualeDiGeometriaOccupataDallaForesta = percantualeDiGeometriaOccupataDallaForesta;
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
     * Gets the descrizione value for this Ricadenza.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this Ricadenza.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
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
     * Gets the percantualeDiForestaOccupataDallaGeometria value for this Ricadenza.
     * 
     * @return percantualeDiForestaOccupataDallaGeometria
     */
    public int getPercantualeDiForestaOccupataDallaGeometria() {
        return percantualeDiForestaOccupataDallaGeometria;
    }


    /**
     * Sets the percantualeDiForestaOccupataDallaGeometria value for this Ricadenza.
     * 
     * @param percantualeDiForestaOccupataDallaGeometria
     */
    public void setPercantualeDiForestaOccupataDallaGeometria(int percantualeDiForestaOccupataDallaGeometria) {
        this.percantualeDiForestaOccupataDallaGeometria = percantualeDiForestaOccupataDallaGeometria;
    }


    /**
     * Gets the percantualeDiGeometriaOccupataDallaForesta value for this Ricadenza.
     * 
     * @return percantualeDiGeometriaOccupataDallaForesta
     */
    public int getPercantualeDiGeometriaOccupataDallaForesta() {
        return percantualeDiGeometriaOccupataDallaForesta;
    }


    /**
     * Sets the percantualeDiGeometriaOccupataDallaForesta value for this Ricadenza.
     * 
     * @param percantualeDiGeometriaOccupataDallaForesta
     */
    public void setPercantualeDiGeometriaOccupataDallaForesta(int percantualeDiGeometriaOccupataDallaForesta) {
        this.percantualeDiGeometriaOccupataDallaForesta = percantualeDiGeometriaOccupataDallaForesta;
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
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            this.flagRicadenzaSignificativa == other.isFlagRicadenzaSignificativa() &&
            this.percantualeDiForestaOccupataDallaGeometria == other.getPercantualeDiForestaOccupataDallaGeometria() &&
            this.percantualeDiGeometriaOccupataDallaForesta == other.getPercantualeDiGeometriaOccupataDallaForesta();
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
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        _hashCode += (isFlagRicadenzaSignificativa() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getPercantualeDiForestaOccupataDallaGeometria();
        _hashCode += getPercantualeDiGeometriaOccupataDallaForesta();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Ricadenza.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("categorieForestali", "Ricadenza"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaIntersezione");
        elemField.setXmlName(new javax.xml.namespace.QName("categorieForestali", "areaIntersezione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAmministrativo");
        elemField.setXmlName(new javax.xml.namespace.QName("categorieForestali", "codiceAmministrativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("categorieForestali", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagRicadenzaSignificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("categorieForestali", "flagRicadenzaSignificativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percantualeDiForestaOccupataDallaGeometria");
        elemField.setXmlName(new javax.xml.namespace.QName("categorieForestali", "percantualeDiForestaOccupataDallaGeometria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percantualeDiGeometriaOccupataDallaForesta");
        elemField.setXmlName(new javax.xml.namespace.QName("categorieForestali", "percantualeDiGeometriaOccupataDallaForesta"));
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
