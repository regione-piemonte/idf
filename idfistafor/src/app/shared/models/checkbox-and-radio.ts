/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class CheckboxAndRadio {
  constructor(
  public headings?: Heading[]
  ) {}
}

export class Heading {
  constructor(
  public id?: number,
  public name?: string,
  public visibility?: boolean,
  public type?: string,
  public subheadings?: Subheading[]
  ) {}
}
export class Subheading {
  constructor(
  public id?: number,
  public name?: string,
  public visibility?: boolean,
  public valore?: number,
  public checked?: boolean
  ) {}
}
