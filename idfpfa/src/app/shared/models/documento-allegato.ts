/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
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
    fkTipoAllegato?: number;
  }
  