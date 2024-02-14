/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TableTransformPipe } from './table-transform.pipe';

describe('TableTransformPipe', () => {
  it('create an instance', () => {
    const pipe = new TableTransformPipe();
    expect(pipe).toBeTruthy();
  });
});
