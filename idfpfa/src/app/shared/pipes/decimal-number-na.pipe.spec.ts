/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { DecimalNumberNAPipe } from './decimal-number-na.pipe';

describe('DecimalNumberNAPipe', () => {
  it('create an instance', () => {
    const pipe = new DecimalNumberNAPipe();
    expect(pipe).toBeTruthy();
  });
});
