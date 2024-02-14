/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {
  DettaglioComponent,
  PfaShellComponent,
  GestitiPfaComponent,
  PfaTabsComponent,
  StepComponent,
  RicercaPfaComponent,
  EventStepComponent,
  ChiudiInterventoComponent,
  InterventoEventoDettaglioComponent,
} from "./pages";
import { InserisciNuovaPfaComponent } from "./pages/inserisci-nuova-pfa/inserisci-nuova-pfa.component";

const routes: Routes = [
  {
    path: "",
    component: PfaShellComponent,
    children: [
      { path: "", redirectTo: "ricerca", pathMatch: "full" },
      { path: "dettaglio/:id", component: InserisciNuovaPfaComponent },
      { path: "tabs/:id", component: PfaTabsComponent },
     //{ path: "step/:id", component: StepComponent },
      { path: "inserisciNuovaPfa", component: InserisciNuovaPfaComponent },
      { path: "return-modifica-tagli/:id", component: InserisciNuovaPfaComponent },
      { path: "ricerca", component: RicercaPfaComponent },
      { path: "newEvent/:id", component: EventStepComponent },
      { path: "chiudi/:id", component: ChiudiInterventoComponent },
      {
        path: "interventoEventoDettaglio/:id",
        component: InterventoEventoDettaglioComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PfaRoutingModule {}
