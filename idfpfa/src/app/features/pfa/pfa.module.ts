/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PfaRoutingModule } from "./pfa-routing.module";
import { SharedModule } from "src/app/shared";

import * as fromPages from "./pages";
import * as fromComponents from "./components";
import { PfaConfigComponent } from "./pfa-config";
import { AddEntryComponent } from "./components/genera-lotto/geometry-result/add-entry/add-entry.component";
import { ShootingMirrorComponent } from "./components/dati-tecnici/shooting-mirror/shooting-mirror.component";
import { UtilizzatoreDettaglioComponent } from "./components/utilizzatore-dettaglio/utilizzatore-dettaglio.component";
import { PGFormTagliComponent } from "src/app/shared/components/pg-form-tagli/pg-form-tagli.component";
import { InserisciNuovaPfaComponent } from "./pages/inserisci-nuova-pfa/inserisci-nuova-pfa.component";

const modules: any[] = [SharedModule, CommonModule, PfaRoutingModule];

@NgModule({
  imports: [...modules],
  declarations: [
    ...fromPages.pages,
    ...fromComponents.components,
    PfaConfigComponent,
    AddEntryComponent,
    ShootingMirrorComponent,
    UtilizzatoreDettaglioComponent,
    PGFormTagliComponent,
    PfaConfigComponent,
    InserisciNuovaPfaComponent,
  ],
})
export class PfaModule {}
