/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SoggettoResource } from "./data-taglio";

export class InterventoRiepilogo {
   public chiusuraInformazioni?: ChiusuraInformazioni;
   public utilizzatore?: SoggettoResource 
}

export class ChiusuraInformazioni {
    public dataInizio?: string;
    public dataFine?: string;
    public superficieRealeTagliata?: number;
    public stimaValoreLotto?: number;
    public valoreDellAsta?: number;
}