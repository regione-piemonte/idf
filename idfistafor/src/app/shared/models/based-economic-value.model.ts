/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SoggettoModel } from "./soggetto.model";

export class BasedEconomicValueModel {
  constructor(
  public baseValue?: number,
  public superficie?: number,
  public sceltiParametri?: EconomicCalculationTableModel[],
  public totale?: any,
  public valoreReale?: any,
  public note?: string,
  public soggettoProfess?: SoggettoModel
  ) {}
}

export class EconomicCalculationTableModel {
  constructor(
    public nome?: string,
    public valore?: any
  ) {}
}
