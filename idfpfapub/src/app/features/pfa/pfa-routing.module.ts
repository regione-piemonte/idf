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
  PfaTabsComponent,
  RicercaPfaComponent,
} from "./pages";

const routes: Routes = [
  {
    path: "",
    component: PfaShellComponent,
    children: [
      { path: "", redirectTo: "ricerca", pathMatch: "full" },
      { path: "dettaglio/:id", component: DettaglioComponent },
      { path: "tabs/:id", component: PfaTabsComponent },
      { path: "ricerca", component: RicercaPfaComponent }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PfaRoutingModule {}
