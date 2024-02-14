/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class UserChoiceModel {
  constructor(
  public tipoIstanzaId?: number,
  public tipoIstanzaDescr?: string,
  public fkTipoAccreditamento?: string,
  public idConfigUtente?: number,
  public fkSoggettoSportello?: number,

/*   public idSoggetto?: number,
  public idConfigUtente?: number, */
  public categoriaProfessionale?: number,

  public  partitaIva?: string,
  public pec?: string,
  public provinciaOrdine?: string,
  public numeroIscrizione?: string,
  public codiceFiscaleDelega?: string,
  public fkProfilo?: number
  ) {}
}



