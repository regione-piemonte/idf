/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class TableHeader {
  field?: string;
  header?: string;
  tooltipHeader?: string;
  disabled?: boolean;
  visible?: boolean;
  fieldType?: FieldType;
  decimalPlaces?: number;
  fieldOfSummaryColumns?: string[];
  width?: string;
}

export enum FieldType {
  TEXT,
  NUMBER,
  DATE,
}
