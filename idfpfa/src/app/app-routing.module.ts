/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

const routes: Routes = [
  { path: "", redirectTo: "pfa", pathMatch: "full" },
  { path: "pfa", loadChildren: "./features/pfa/pfa.module#PfaModule" }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes,
    {
      useHash: true,
      scrollPositionRestoration: 'enabled'
    }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
