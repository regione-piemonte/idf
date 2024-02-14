/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class TipoForestale {
    idTipoForestale: number;
    fkCategoriaForestale: number;
    codClc: string;
    codiceAmministrativo: string;
    dataFineValidita: string;
    dataModifica: string;
    habitatN2000: string;
    tipo: string;
    sottotipo: string;
    variante: string;
    fkConfigIpla: number;
}