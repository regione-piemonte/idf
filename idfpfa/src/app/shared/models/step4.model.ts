/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Step4Model {
  constructor(
    public nonNecessaria?: boolean,
    public nonNecessaria21?: boolean,
    public flgA?: boolean,
    public flgB?: boolean,
    public flgC?: boolean,
    public flgD?: boolean,
    public flgA21?: boolean,
    public flgB21?: boolean,
    public flgC21?: boolean,
    public flgD21?: boolean,
    public flgDter21?: boolean,
    public flgDquater21?: boolean,
    public flgDquinquies21?: boolean,
    public necessaria?: boolean,
    public compFisica?: boolean,
    public compMonetaria?: boolean
  ) {}
}
