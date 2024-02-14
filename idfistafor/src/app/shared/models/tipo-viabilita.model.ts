/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export interface TipoViabilita {
    idTipoViabilita?:     number;
    codiceTipoViabilita?: string;
    tipoViabilita?:       string;
    fkConfigIpla?:        number;
    mtdOrdinamento?:      number;
    flgVisible?:          number;
}
