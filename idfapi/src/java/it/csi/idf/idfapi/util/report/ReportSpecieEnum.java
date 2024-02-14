/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.report;

public enum ReportSpecieEnum {

	AA("Abies alba"),
	AC("Altre conifere spontanee"),
	AE("Altre latifoglie esotiche"),
	AG("Alnus glutinosa"),
	AI("Alnus incana"),
	AL("Altre latifoglie spontanee"),
	AM("Acer monspessulanum"),
	AN("Ailanthus altissima"),
	AO("Acer platanoides"),
	AP("Acer pseudoplatanus"),
	AR("Arbusti vari"),
	AT("Acer campestre"),
	AU("Acer opalus"),
	AX("Acer negundo"),
	BP("Betula alba,Betula pubescens"),
	CB("Carpinus betulus,Carpinus orientalis"),
	CG("Prunus padus"),
	CS("Castanea sativa"),
	CT("Prunus serotina"),
	DU("Pseudotsuga menziesii"),
	ES("Altre conifere esotiche"),
	FE("Fraxinus excelsor"),
	FO("Fraxinus ornus"),
	FS("Fagus sylvatica"),
	IA("Ilex aquifolium"),
	JR("Juglans regia"),
	LA("Laburnum anagyroides"),
	LD("Larix decidua"),
	MS("Malus sylvestris"),
	OC("Ostria carpinifolia"),
	ON("Alnus cordata"),
	OV("Alnus viridis"),
	PA("Picea abies"),
	PC("Pinus cembra"),
	PD("Platanus occidentalis"),
	PH("Pinus halepensis"),
	PL("Pyrus pyraster"),
	PM("Pinus pinaster"),
	PN("Pinus nigra"),
	PO("Pioppi clonali"),
	PP("Pinus pinea"),
	PR("Pinus pinaster"),
	PS("Pinus sylvestris"),
	PT("Populus tremula"),
	PU("Pinus uncinata"),
	PV("Prunus avium"),
	PW("Popolus nigra"),
	PX("Pinus strobus"),
	PY("Populus alba"),
	PZ("Paulownia tomentosa"),
	QC("Quercus cerris"),
	QD("altre querce"),
	QF("Quercus robur"),
	QI("Quercus ilex"),
	QP("Quercus pubescens"),
	QR("Quercus petrea"),
	QX("Quercus rubra"),
	RP("Robinia pseudoacacia"),
	SA("Sorbus aria"),
	SC("Salix caprea"),
	SD("Sorbus domestica"),
	SN("Sambucus nigra"),
	ST("Sorbus torminalis"),
	SU("Sorbus aucuparia"),
	SX("Salix alba"),
	TB("Taxus baccata"),
	TC("Tillia cordata"),
	TP("Tillia platyphyllos"),
	UC("Ulmus laevis"),
	UG("Ulmus glabra"),
	UM("Ulmus minor"),
	UP("Ulmus pumila");


	private String value;
	private ReportSpecieEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
