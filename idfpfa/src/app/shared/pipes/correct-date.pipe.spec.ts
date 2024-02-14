/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { CorrectDatePipe } from './correct-date.pipe';

describe('CorrectDatePipe', () => {
  it('create an instance', () => {
    const pipe = new CorrectDatePipe();
    expect(pipe).toBeTruthy();
  });
});
