/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ConvertKBPipe } from './convert-kb.pipe';

describe('ConvertKBPipe', () => {
  it('create an instance', () => {
    const pipe = new ConvertKBPipe();
    expect(pipe).toBeTruthy();
  });
});
