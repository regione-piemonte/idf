/**
 * SiforsrvCategorieforestali_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali;

public interface SiforsrvCategorieforestali_PortType extends java.rmi.Remote {
    public Ricadenza[] determinaRicadenzaSuCategoriaForestalePerGeometriaGML(java.lang.String in0) throws java.rmi.RemoteException, UnrecoverableException, CategoriaForestaleException, CSIException, SystemException;
    public CategoriaForestale[] cercaTutteCategorieForestali() throws java.rmi.RemoteException, UnrecoverableException, CategoriaForestaleException, CSIException, SystemException;
    public CategoriaForestale[] cercaCategoriaForestalePerFiltri(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException, UnrecoverableException, CategoriaForestaleException, CSIException, SystemException;
}
