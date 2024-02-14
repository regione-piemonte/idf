/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TipoAllegatoEnum } from "./tipo-allegato.enum"

export interface InfoDocsMancanti {
  missing: boolean
  docType?: TipoAllegatoEnum
  docDescription?: string
}
