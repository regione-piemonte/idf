/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { DenominazioneComponent } from "./denominazione/denominazione.component";
import { PfaTableComponent } from "./pfa-table/pfa-table.component";
import { LoaderComponent } from "./loader/loader.component";
import { ErrorDialogComponent } from "./error-dialog/error-dialog.component";

export const components: any[] = [
  HeaderComponent,
  FooterComponent,
  DenominazioneComponent,
  PfaTableComponent,
  LoaderComponent,
  ErrorDialogComponent
];

export { HeaderComponent } from "./header/header.component";
export { FooterComponent } from "./footer/footer.component";
export { DenominazioneComponent } from "./denominazione/denominazione.component";
export { PfaTableComponent } from "./pfa-table/pfa-table.component";
export { LoaderComponent } from "./loader/loader.component";
export { ErrorDialogComponent } from "./error-dialog/error-dialog.component";
