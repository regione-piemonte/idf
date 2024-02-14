/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import * as fromComponents from "./components";
import * as fromPages from "./pages";
import { SharedModule } from "src/app/shared";
import { IstanzeForestaliRoutingModule } from "./istanze-forestali-routing.module";
import { InserisciNuovaVidComponent } from "./pages/inserisci-nuova-vid/inserisci-nuova-vid.component";
import { VisualizzaVidComponent } from "./pages/visualizza-vid/visualizza-vid.component";
import { VisualizzaTagliComponent } from "./pages/visualizza-tagli/visualizza-tagli.component";
import { InserisciNuovaTagliComponent } from "./pages/inserisci-nuova-tagli/inserisci-nuova-tagli.component";
import { GestioneDelegheComponent } from "./pages/gestione-deleghe/gestione-deleghe.component";
import { SearchCadastralParcelsComponent } from "./components/search-cadastral-parcels/search-cadastral-parcels.component";

@NgModule({
  imports: [CommonModule, SharedModule, IstanzeForestaliRoutingModule],
  declarations: [
    ...fromComponents.components,
    ...fromPages.pages,
    InserisciNuovaVidComponent,
    InserisciNuovaTagliComponent,
    VisualizzaVidComponent,
    VisualizzaTagliComponent,
    GestioneDelegheComponent,
    SearchCadastralParcelsComponent,
  ],
  exports: [...fromComponents.components, ...fromPages.pages],
})
export class IstanzeForestaliModule {}
