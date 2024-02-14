/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SpecieForm {
  constructor(
    public idSpecie?: number,
    public idIntervento?: number,
    public flgSpeciePriorita?: any,
    public volumeSpecia?: number,
    public dataInserimento?: any,
    public dataAggiornamento?: any,
    public fkUdm?: any
  ) {}
}
