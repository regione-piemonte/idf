/**
 * AreaProtetta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.gsareprot.areeprotette;

public class AreaProtetta  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4005969085229213365L;

	private java.lang.String codiceAmministrativo;

    private java.util.Calendar dataIstituzione;

    private java.util.Calendar dataModificaIstituzionale;

    private java.lang.String geometriaGML;

    private java.lang.String idInspire;

    private java.lang.String nome;

    private double perimetroAmministrativo;

    private double superficieAmministrativa;

    private java.lang.String[] url;

    public AreaProtetta() {
    }

    public AreaProtetta(
           java.lang.String codiceAmministrativo,
           java.util.Calendar dataIstituzione,
           java.util.Calendar dataModificaIstituzionale,
           java.lang.String geometriaGML,
           java.lang.String idInspire,
           java.lang.String nome,
           double perimetroAmministrativo,
           double superficieAmministrativa,
           java.lang.String[] url) {
           this.codiceAmministrativo = codiceAmministrativo;
           this.dataIstituzione = dataIstituzione;
           this.dataModificaIstituzionale = dataModificaIstituzionale;
           this.geometriaGML = geometriaGML;
           this.idInspire = idInspire;
           this.nome = nome;
           this.perimetroAmministrativo = perimetroAmministrativo;
           this.superficieAmministrativa = superficieAmministrativa;
           this.url = url;
    }


    /**
     * Gets the codiceAmministrativo value for this AreaProtetta.
     * 
     * @return codiceAmministrativo
     */
    public java.lang.String getCodiceAmministrativo() {
        return codiceAmministrativo;
    }


    /**
     * Sets the codiceAmministrativo value for this AreaProtetta.
     * 
     * @param codiceAmministrativo
     */
    public void setCodiceAmministrativo(java.lang.String codiceAmministrativo) {
        this.codiceAmministrativo = codiceAmministrativo;
    }


    /**
     * Gets the dataIstituzione value for this AreaProtetta.
     * 
     * @return dataIstituzione
     */
    public java.util.Calendar getDataIstituzione() {
        return dataIstituzione;
    }


    /**
     * Sets the dataIstituzione value for this AreaProtetta.
     * 
     * @param dataIstituzione
     */
    public void setDataIstituzione(java.util.Calendar dataIstituzione) {
        this.dataIstituzione = dataIstituzione;
    }


    /**
     * Gets the dataModificaIstituzionale value for this AreaProtetta.
     * 
     * @return dataModificaIstituzionale
     */
    public java.util.Calendar getDataModificaIstituzionale() {
        return dataModificaIstituzionale;
    }


    /**
     * Sets the dataModificaIstituzionale value for this AreaProtetta.
     * 
     * @param dataModificaIstituzionale
     */
    public void setDataModificaIstituzionale(java.util.Calendar dataModificaIstituzionale) {
        this.dataModificaIstituzionale = dataModificaIstituzionale;
    }


    /**
     * Gets the geometriaGML value for this AreaProtetta.
     * 
     * @return geometriaGML
     */
    public java.lang.String getGeometriaGML() {
        return geometriaGML;
    }


    /**
     * Sets the geometriaGML value for this AreaProtetta.
     * 
     * @param geometriaGML
     */
    public void setGeometriaGML(java.lang.String geometriaGML) {
        this.geometriaGML = geometriaGML;
    }


    /**
     * Gets the idInspire value for this AreaProtetta.
     * 
     * @return idInspire
     */
    public java.lang.String getIdInspire() {
        return idInspire;
    }


    /**
     * Sets the idInspire value for this AreaProtetta.
     * 
     * @param idInspire
     */
    public void setIdInspire(java.lang.String idInspire) {
        this.idInspire = idInspire;
    }


    /**
     * Gets the nome value for this AreaProtetta.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this AreaProtetta.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the perimetroAmministrativo value for this AreaProtetta.
     * 
     * @return perimetroAmministrativo
     */
    public double getPerimetroAmministrativo() {
        return perimetroAmministrativo;
    }


    /**
     * Sets the perimetroAmministrativo value for this AreaProtetta.
     * 
     * @param perimetroAmministrativo
     */
    public void setPerimetroAmministrativo(double perimetroAmministrativo) {
        this.perimetroAmministrativo = perimetroAmministrativo;
    }


    /**
     * Gets the superficieAmministrativa value for this AreaProtetta.
     * 
     * @return superficieAmministrativa
     */
    public double getSuperficieAmministrativa() {
        return superficieAmministrativa;
    }


    /**
     * Sets the superficieAmministrativa value for this AreaProtetta.
     * 
     * @param superficieAmministrativa
     */
    public void setSuperficieAmministrativa(double superficieAmministrativa) {
        this.superficieAmministrativa = superficieAmministrativa;
    }


    /**
     * Gets the url value for this AreaProtetta.
     * 
     * @return url
     */
    public java.lang.String[] getUrl() {
        return url;
    }


    /**
     * Sets the url value for this AreaProtetta.
     * 
     * @param url
     */
    public void setUrl(java.lang.String[] url) {
        this.url = url;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AreaProtetta)) return false;
        AreaProtetta other = (AreaProtetta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceAmministrativo==null && other.getCodiceAmministrativo()==null) || 
             (this.codiceAmministrativo!=null &&
              this.codiceAmministrativo.equals(other.getCodiceAmministrativo()))) &&
            ((this.dataIstituzione==null && other.getDataIstituzione()==null) || 
             (this.dataIstituzione!=null &&
              this.dataIstituzione.equals(other.getDataIstituzione()))) &&
            ((this.dataModificaIstituzionale==null && other.getDataModificaIstituzionale()==null) || 
             (this.dataModificaIstituzionale!=null &&
              this.dataModificaIstituzionale.equals(other.getDataModificaIstituzionale()))) &&
            ((this.geometriaGML==null && other.getGeometriaGML()==null) || 
             (this.geometriaGML!=null &&
              this.geometriaGML.equals(other.getGeometriaGML()))) &&
            ((this.idInspire==null && other.getIdInspire()==null) || 
             (this.idInspire!=null &&
              this.idInspire.equals(other.getIdInspire()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            this.perimetroAmministrativo == other.getPerimetroAmministrativo() &&
            this.superficieAmministrativa == other.getSuperficieAmministrativa() &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              java.util.Arrays.equals(this.url, other.getUrl())));
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
        if (getCodiceAmministrativo() != null) {
            _hashCode += getCodiceAmministrativo().hashCode();
        }
        if (getDataIstituzione() != null) {
            _hashCode += getDataIstituzione().hashCode();
        }
        if (getDataModificaIstituzionale() != null) {
            _hashCode += getDataModificaIstituzionale().hashCode();
        }
        if (getGeometriaGML() != null) {
            _hashCode += getGeometriaGML().hashCode();
        }
        if (getIdInspire() != null) {
            _hashCode += getIdInspire().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        _hashCode += new Double(getPerimetroAmministrativo()).hashCode();
        _hashCode += new Double(getSuperficieAmministrativa()).hashCode();
        if (getUrl() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUrl());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUrl(), i);
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
        new org.apache.axis.description.TypeDesc(AreaProtetta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("areeProtette", "AreaProtetta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAmministrativo");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "codiceAmministrativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataIstituzione");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "dataIstituzione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataModificaIstituzionale");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "dataModificaIstituzionale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("geometriaGML");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "geometriaGML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idInspire");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "idInspire"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perimetroAmministrativo");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "perimetroAmministrativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("superficieAmministrativa");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "superficieAmministrativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("areeProtette", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("areeProtette", "item"));
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
