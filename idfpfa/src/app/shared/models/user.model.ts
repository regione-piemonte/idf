/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class UserModel {
  constructor(
  public codiceFiscale?: string,
  public cognome?: string,
  public nome?: string,
  public ruolo?: string,
  public provider?: string) {}
}
