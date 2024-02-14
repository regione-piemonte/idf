/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class PlainSestoSezione {
  etichetta?: string;
  proprieta?: DichProprieta;
  dissensi?: DichDissensi;
  paesaggistica?: DichAutorizzazione;
  vincIdrogeologico?: DichAutorizzazione;
  valIncidenza?: DichAutorizzazione;
  altriPareri?: DichAltriPareri;
  compFisica?: DichCompensazione;
  compMonetaria?: DichCompensazione;
  altroAllega?: DichCompensazione;
  allegati?: DocumentoAllegato[];
  istanzi?: DichIstanzaTaglio;
  nota?: DichNota;
}

export class DichDissensi {
  etichetta?: string;
  haDissenso?: boolean;
}

export class DichNota {
  etichetta?: string;
  testo?: string;
}

export class DichIstanzaTaglio {
  etichetta?: string;
  visible?: boolean;
  checked?: boolean;
  required?: boolean;
  istanziList?: IstanzaTaglio[];
}

export class IstanzaTaglio {
  idIstanza?: number;
  anno: Date;
  numIstanza?: number;
  dataPresentazioneIstanza: Date;
  dataPresentIstanzaFormat: String;
  dataAutorizzazioneIstanza: Date;
  descIntervento?: string;
  comuniInteressati?: string;
  stimaMassaRetraibile?: number;
  unita?: string;
  tipoComunicazione?: string;
  specieCoinvolte?: string;
  stato?: string;
  governo?: string;
  tipoIstanza?: string;
  tipoIntervento?: string;
  categoriaIntervento?: number;
  deleted?: boolean;
}

export class DocumentoAllegato {
  idDocumentoAllegato?: number;
  idGeoPlPfa?: number;
  fkIntervento?: number;
  idTipoAllegato?: number;
  descrTipoAllegato?: string;
  nomeAllegato?: string;
  dimensioneAllegatoKB?: number;
  dataIniziValidita?: Date;
  dataFineValidita?: Date;
  dataInserimento?: Date;
  dataAggiornamento?: Date;
  fkConfigUtente?: number;
  note?: string;
  deleted?: boolean;
  uidIndex?: string;
}

//FIX GP

export class TipoSelezionatoTipiAllegatoOnSelectDocumentes {
  idTipoAllegato?: number;
}

export class DichProprieta {
  etichetta1?: string;
  etichetta2?: string;
  isOwner?: boolean;
}

export class DichAutorizzazione {
  etichetta?: string;
  checked?: boolean;
  required?: boolean;
  visible?: boolean;
  numeroAutorizzazione?: string;
  dataAutorizzazione?: Date | string;
  rilasciataAutorizzazione?: string;
}

export class DichAltriPareri {
  etichetta?: string;
  checked?: boolean;
  testo?: string;
}

export class DichCompensazione {
  etichetta?: string;
  visible?: boolean;
  checked?: boolean;
  required?: boolean;
}
