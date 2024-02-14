/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ShortNamePipe } from './short-name.pipe';

describe('ShortNamePipe', () => {
  it('create an instance', () => {
    const pipe = new ShortNamePipe();
    expect(pipe).toBeTruthy();
  });
});
