/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { DeepFieldValuePipe } from './deep-field-value.pipe';

describe('DeepFieldValuePipe', () => {
  it('create an instance', () => {
    const pipe = new DeepFieldValuePipe();
    expect(pipe).toBeTruthy();
  });
});
