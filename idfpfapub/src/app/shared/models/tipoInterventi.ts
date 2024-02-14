/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class TipoInterventi {
  
  idTipoIntervento: number;
  descrTipoIntervento: string;
  codTipoIntervento: string;
  mtdOrdinamento: number;
  fkMacroIntervento: number;
  fkConfigIpla: number;
  flgVisibile: number;
}
