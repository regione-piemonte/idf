/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { CorrectDatePipe } from "./correct-date.pipe";
import { ShortNamePipe} from "./short-name.pipe";
import { ConvertKBPipe} from "./convert-kb.pipe";
import { DeepFieldValuePipe } from "./deep-field-value.pipe";
import { DecimalNumberNAPipe } from "./decimal-number-na.pipe";
import { TableTransformPipe } from './table-transform.pipe';
 
export const pipes: any[] = [CorrectDatePipe, ShortNamePipe, ConvertKBPipe, DeepFieldValuePipe, DecimalNumberNAPipe, TableTransformPipe];

export { DeepFieldValuePipe } from "./deep-field-value.pipe";
export { CorrectDatePipe } from "./correct-date.pipe";
export { ShortNamePipe} from "./short-name.pipe";
export { ConvertKBPipe} from "./convert-kb.pipe";
export { DecimalNumberNAPipe } from "./decimal-number-na.pipe";
export { TableTransformPipe } from './table-transform.pipe';