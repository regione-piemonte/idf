/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { PropCatastoPipe } from './prop-catasto.pipe';

describe('PropCatastoPipe', () => {
  it('create an instance', () => {
    const pipe = new PropCatastoPipe();
    expect(pipe).toBeTruthy();
  });
});
