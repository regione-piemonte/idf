/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Routes, RouterModule } from "@angular/router";
import { HomeShellComponent, HomePageComponent, UserFormComponent } from "./pages";

const routes: Routes = [
  {
    path: "", component: HomeShellComponent, children: [
      { path: "", redirectTo: "edit", pathMatch: 'full' },
      { path: "index", component: HomePageComponent },
      { path: "edit", component: UserFormComponent },
    ]
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  declarations: [],
  exports: [RouterModule]
})
export class HomeRoutingModule {}
