/**
 * Istanza.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.primpaforserv;

public class Istanza  implements java.io.Serializable {
    private java.lang.Integer anno;

    private java.lang.String categoriaIntervento;

    private it.csi.idf.idfapi.util.service.integration.primpaforserv.Comune[] comuniInteressati;

    private java.util.Calendar dataAutorizzazioneIstanza;

    private java.util.Calendar dataPresentazioneIstanza;

    private java.lang.String descIntervento;

    private java.lang.String governo;

    private java.lang.Integer numIstanza;

    private it.csi.idf.idfapi.util.service.integration.primpaforserv.Specie[] specieCoinvolte;

    private java.lang.String stato;

    private java.lang.Integer stimaMassaRetraibile;

    private java.lang.String tipoComunicazione;

    private java.lang.String tipoIntervento;

    private java.lang.String unitaMisura;

    private String strElencoComuniInteressati;
    private String strElencoSpecieCoinvolte;
    
    
    /**
	 * @return the strElencoComuniInteressati
	 */
	public String getStrElencoComuniInteressati() {
		return strElencoComuniInteressati;
	}

	/**
	 * @param strElencoComuniInteressati the strElencoComuniInteressati to set
	 */
	public void setStrElencoComuniInteressati(String strElencoComuniInteressati) {
		this.strElencoComuniInteressati = strElencoComuniInteressati;
	}

	/**
	 * @return the strElencoSpecieCoinvolte
	 */
	public String getStrElencoSpecieCoinvolte() {
		return strElencoSpecieCoinvolte;
	}

	/**
	 * @param strElencoSpecieCoinvolte the strElencoSpecieCoinvolte to set
	 */
	public void setStrElencoSpecieCoinvolte(String strElencoSpecieCoinvolte) {
		this.strElencoSpecieCoinvolte = strElencoSpecieCoinvolte;
	}

	public Istanza() {
    }

    public Istanza(
           java.lang.Integer anno,
           java.lang.String categoriaIntervento,
           it.csi.idf.idfapi.util.service.integration.primpaforserv.Comune[] comuniInteressati,
           java.util.Calendar dataAutorizzazioneIstanza,
           java.util.Calendar dataPresentazioneIstanza,
           java.lang.String descIntervento,
           java.lang.String governo,
           java.lang.Integer numIstanza,
           it.csi.idf.idfapi.util.service.integration.primpaforserv.Specie[] specieCoinvolte,
           java.lang.String stato,
           java.lang.Integer stimaMassaRetraibile,
           java.lang.String tipoComunicazione,
           java.lang.String tipoIntervento,
           java.lang.String unitaMisura) {
           this.anno = anno;
           this.categoriaIntervento = categoriaIntervento;
           this.comuniInteressati = comuniInteressati;
           this.dataAutorizzazioneIstanza = dataAutorizzazioneIstanza;
           this.dataPresentazioneIstanza = dataPresentazioneIstanza;
           this.descIntervento = descIntervento;
           this.governo = governo;
           this.numIstanza = numIstanza;
           this.specieCoinvolte = specieCoinvolte;
           this.stato = stato;
           this.stimaMassaRetraibile = stimaMassaRetraibile;
           this.tipoComunicazione = tipoComunicazione;
           this.tipoIntervento = tipoIntervento;
           this.unitaMisura = unitaMisura;
    }


    /**
     * Gets the anno value for this Istanza.
     * 
     * @return anno
     */
    public java.lang.Integer getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this Istanza.
     * 
     * @param anno
     */
    public void setAnno(java.lang.Integer anno) {
        this.anno = anno;
    }


    /**
     * Gets the categoriaIntervento value for this Istanza.
     * 
     * @return categoriaIntervento
     */
    public java.lang.String getCategoriaIntervento() {
        return categoriaIntervento;
    }


    /**
     * Sets the categoriaIntervento value for this Istanza.
     * 
     * @param categoriaIntervento
     */
    public void setCategoriaIntervento(java.lang.String categoriaIntervento) {
        this.categoriaIntervento = categoriaIntervento;
    }


    /**
     * Gets the comuniInteressati value for this Istanza.
     * 
     * @return comuniInteressati
     */
    public it.csi.idf.idfapi.util.service.integration.primpaforserv.Comune[] getComuniInteressati() {
        return comuniInteressati;
    }


    /**
     * Sets the comuniInteressati value for this Istanza.
     * 
     * @param comuniInteressati
     */
    public void setComuniInteressati(it.csi.idf.idfapi.util.service.integration.primpaforserv.Comune[] comuniInteressati) {
        this.comuniInteressati = comuniInteressati;
    }


    /**
     * Gets the dataAutorizzazioneIstanza value for this Istanza.
     * 
     * @return dataAutorizzazioneIstanza
     */
    public java.util.Calendar getDataAutorizzazioneIstanza() {
        return dataAutorizzazioneIstanza;
    }


    /**
     * Sets the dataAutorizzazioneIstanza value for this Istanza.
     * 
     * @param dataAutorizzazioneIstanza
     */
    public void setDataAutorizzazioneIstanza(java.util.Calendar dataAutorizzazioneIstanza) {
        this.dataAutorizzazioneIstanza = dataAutorizzazioneIstanza;
    }


    /**
     * Gets the dataPresentazioneIstanza value for this Istanza.
     * 
     * @return dataPresentazioneIstanza
     */
    public java.util.Calendar getDataPresentazioneIstanza() {
        return dataPresentazioneIstanza;
    }


    /**
     * Sets the dataPresentazioneIstanza value for this Istanza.
     * 
     * @param dataPresentazioneIstanza
     */
    public void setDataPresentazioneIstanza(java.util.Calendar dataPresentazioneIstanza) {
        this.dataPresentazioneIstanza = dataPresentazioneIstanza;
    }


    /**
     * Gets the descIntervento value for this Istanza.
     * 
     * @return descIntervento
     */
    public java.lang.String getDescIntervento() {
        return descIntervento;
    }


    /**
     * Sets the descIntervento value for this Istanza.
     * 
     * @param descIntervento
     */
    public void setDescIntervento(java.lang.String descIntervento) {
        this.descIntervento = descIntervento;
    }


    /**
     * Gets the governo value for this Istanza.
     * 
     * @return governo
     */
    public java.lang.String getGoverno() {
        return governo;
    }


    /**
     * Sets the governo value for this Istanza.
     * 
     * @param governo
     */
    public void setGoverno(java.lang.String governo) {
        this.governo = governo;
    }


    /**
     * Gets the numIstanza value for this Istanza.
     * 
     * @return numIstanza
     */
    public java.lang.Integer getNumIstanza() {
        return numIstanza;
    }


    /**
     * Sets the numIstanza value for this Istanza.
     * 
     * @param numIstanza
     */
    public void setNumIstanza(java.lang.Integer numIstanza) {
        this.numIstanza = numIstanza;
    }


    /**
     * Gets the specieCoinvolte value for this Istanza.
     * 
     * @return specieCoinvolte
     */
    public it.csi.idf.idfapi.util.service.integration.primpaforserv.Specie[] getSpecieCoinvolte() {
        return specieCoinvolte;
    }


    /**
     * Sets the specieCoinvolte value for this Istanza.
     * 
     * @param specieCoinvolte
     */
    public void setSpecieCoinvolte(it.csi.idf.idfapi.util.service.integration.primpaforserv.Specie[] specieCoinvolte) {
        this.specieCoinvolte = specieCoinvolte;
    }


    /**
     * Gets the stato value for this Istanza.
     * 
     * @return stato
     */
    public java.lang.String getStato() {
        return stato;
    }


    /**
     * Sets the stato value for this Istanza.
     * 
     * @param stato
     */
    public void setStato(java.lang.String stato) {
        this.stato = stato;
    }


    /**
     * Gets the stimaMassaRetraibile value for this Istanza.
     * 
     * @return stimaMassaRetraibile
     */
    public java.lang.Integer getStimaMassaRetraibile() {
        return stimaMassaRetraibile;
    }


    /**
     * Sets the stimaMassaRetraibile value for this Istanza.
     * 
     * @param stimaMassaRetraibile
     */
    public void setStimaMassaRetraibile(java.lang.Integer stimaMassaRetraibile) {
        this.stimaMassaRetraibile = stimaMassaRetraibile;
    }


    /**
     * Gets the tipoComunicazione value for this Istanza.
     * 
     * @return tipoComunicazione
     */
    public java.lang.String getTipoComunicazione() {
        return tipoComunicazione;
    }


    /**
     * Sets the tipoComunicazione value for this Istanza.
     * 
     * @param tipoComunicazione
     */
    public void setTipoComunicazione(java.lang.String tipoComunicazione) {
        this.tipoComunicazione = tipoComunicazione;
    }


    /**
     * Gets the tipoIntervento value for this Istanza.
     * 
     * @return tipoIntervento
     */
    public java.lang.String getTipoIntervento() {
        return tipoIntervento;
    }


    /**
     * Sets the tipoIntervento value for this Istanza.
     * 
     * @param tipoIntervento
     */
    public void setTipoIntervento(java.lang.String tipoIntervento) {
        this.tipoIntervento = tipoIntervento;
    }


    /**
     * Gets the unitaMisura value for this Istanza.
     * 
     * @return unitaMisura
     */
    public java.lang.String getUnitaMisura() {
        return unitaMisura;
    }


    /**
     * Sets the unitaMisura value for this Istanza.
     * 
     * @param unitaMisura
     */
    public void setUnitaMisura(java.lang.String unitaMisura) {
        this.unitaMisura = unitaMisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Istanza)) return false;
        Istanza other = (Istanza) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.categoriaIntervento==null && other.getCategoriaIntervento()==null) || 
             (this.categoriaIntervento!=null &&
              this.categoriaIntervento.equals(other.getCategoriaIntervento()))) &&
            ((this.comuniInteressati==null && other.getComuniInteressati()==null) || 
             (this.comuniInteressati!=null &&
              java.util.Arrays.equals(this.comuniInteressati, other.getComuniInteressati()))) &&
            ((this.dataAutorizzazioneIstanza==null && other.getDataAutorizzazioneIstanza()==null) || 
             (this.dataAutorizzazioneIstanza!=null &&
              this.dataAutorizzazioneIstanza.equals(other.getDataAutorizzazioneIstanza()))) &&
            ((this.dataPresentazioneIstanza==null && other.getDataPresentazioneIstanza()==null) || 
             (this.dataPresentazioneIstanza!=null &&
              this.dataPresentazioneIstanza.equals(other.getDataPresentazioneIstanza()))) &&
            ((this.descIntervento==null && other.getDescIntervento()==null) || 
             (this.descIntervento!=null &&
              this.descIntervento.equals(other.getDescIntervento()))) &&
            ((this.governo==null && other.getGoverno()==null) || 
             (this.governo!=null &&
              this.governo.equals(other.getGoverno()))) &&
            ((this.numIstanza==null && other.getNumIstanza()==null) || 
             (this.numIstanza!=null &&
              this.numIstanza.equals(other.getNumIstanza()))) &&
            ((this.specieCoinvolte==null && other.getSpecieCoinvolte()==null) || 
             (this.specieCoinvolte!=null &&
              java.util.Arrays.equals(this.specieCoinvolte, other.getSpecieCoinvolte()))) &&
            ((this.stato==null && other.getStato()==null) || 
             (this.stato!=null &&
              this.stato.equals(other.getStato()))) &&
            ((this.stimaMassaRetraibile==null && other.getStimaMassaRetraibile()==null) || 
             (this.stimaMassaRetraibile!=null &&
              this.stimaMassaRetraibile.equals(other.getStimaMassaRetraibile()))) &&
            ((this.tipoComunicazione==null && other.getTipoComunicazione()==null) || 
             (this.tipoComunicazione!=null &&
              this.tipoComunicazione.equals(other.getTipoComunicazione()))) &&
            ((this.tipoIntervento==null && other.getTipoIntervento()==null) || 
             (this.tipoIntervento!=null &&
              this.tipoIntervento.equals(other.getTipoIntervento()))) &&
            ((this.unitaMisura==null && other.getUnitaMisura()==null) || 
             (this.unitaMisura!=null &&
              this.unitaMisura.equals(other.getUnitaMisura())));
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
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getCategoriaIntervento() != null) {
            _hashCode += getCategoriaIntervento().hashCode();
        }
        if (getComuniInteressati() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComuniInteressati());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComuniInteressati(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDataAutorizzazioneIstanza() != null) {
            _hashCode += getDataAutorizzazioneIstanza().hashCode();
        }
        if (getDataPresentazioneIstanza() != null) {
            _hashCode += getDataPresentazioneIstanza().hashCode();
        }
        if (getDescIntervento() != null) {
            _hashCode += getDescIntervento().hashCode();
        }
        if (getGoverno() != null) {
            _hashCode += getGoverno().hashCode();
        }
        if (getNumIstanza() != null) {
            _hashCode += getNumIstanza().hashCode();
        }
        if (getSpecieCoinvolte() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSpecieCoinvolte());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSpecieCoinvolte(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getStato() != null) {
            _hashCode += getStato().hashCode();
        }
        if (getStimaMassaRetraibile() != null) {
            _hashCode += getStimaMassaRetraibile().hashCode();
        }
        if (getTipoComunicazione() != null) {
            _hashCode += getTipoComunicazione().hashCode();
        }
        if (getTipoIntervento() != null) {
            _hashCode += getTipoIntervento().hashCode();
        }
        if (getUnitaMisura() != null) {
            _hashCode += getUnitaMisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Istanza.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("primpa_istanze", "Istanza"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoriaIntervento");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "categoriaIntervento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comuniInteressati");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "comuniInteressati"));
        elemField.setXmlType(new javax.xml.namespace.QName("primpa_istanze", "Comune"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("primpa_istanze", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAutorizzazioneIstanza");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "dataAutorizzazioneIstanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPresentazioneIstanza");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "dataPresentazioneIstanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descIntervento");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "descIntervento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("governo");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "governo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numIstanza");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "numIstanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specieCoinvolte");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "specieCoinvolte"));
        elemField.setXmlType(new javax.xml.namespace.QName("primpa_istanze", "Specie"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("primpa_istanze", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stato");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "stato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stimaMassaRetraibile");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "stimaMassaRetraibile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoComunicazione");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "tipoComunicazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIntervento");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "tipoIntervento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitaMisura");
        elemField.setXmlName(new javax.xml.namespace.QName("primpa_istanze", "unitaMisura"));
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
