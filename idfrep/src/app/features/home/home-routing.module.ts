/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Routes, RouterModule } from "@angular/router";
import {
  HomeShellComponent,
  HomePageComponent,
  RisultatoComponent
} from "./pages";


const routes: Routes = [
  {
    path: "",
    component: HomeShellComponent,
    children: [
      { path: "", redirectTo: "index" },
      { path: "index", component: HomePageComponent },
      { path: "calcola", component: RisultatoComponent }
    ]
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  declarations: [],
  exports: [RouterModule]
})
export class HomeRoutingModule {}
