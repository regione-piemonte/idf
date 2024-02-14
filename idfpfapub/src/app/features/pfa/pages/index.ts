/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { DettaglioComponent } from "./dettaglio/dettaglio.component";
import { PfaShellComponent } from "./pfa-shell/pfa-shell.component";
import { GestitiPfaComponent } from "./gestiti-pfa/gestiti-pfa.component";
import { PfaTabsComponent } from "./pfa-tabs/pfa-tabs.component";
import { RicercaPfaComponent } from "./ricerca-pfa/ricerca-pfa.component";

export const pages: any[] = [
  DettaglioComponent,
  PfaShellComponent,
  GestitiPfaComponent,
  PfaTabsComponent,
  RicercaPfaComponent
];

export { DettaglioComponent } from "./dettaglio/dettaglio.component";
export { PfaShellComponent } from "./pfa-shell/pfa-shell.component";
export { GestitiPfaComponent } from "./gestiti-pfa/gestiti-pfa.component";
export { PfaTabsComponent } from "./pfa-tabs/pfa-tabs.component";
export { RicercaPfaComponent } from "./ricerca-pfa/ricerca-pfa.component";