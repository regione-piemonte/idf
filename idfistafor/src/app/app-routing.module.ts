/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: './features/istanze-forestali/istanze-forestali.module#IstanzeForestaliModule'
  },
  { path: '**', redirectTo: '' },
];

// @NgModule({
//   declarations: [],
//   imports: [ RouterModule.forRoot(routes, {
//     preloadingStrategy: PreloadAllModules,
//     scrollPositionRestoration: 'enabled'}) ],
//   exports: [ RouterModule ]
// })
// export class AppRoutingModule {}


@NgModule({
  imports: [ RouterModule.forRoot(routes,
    {
      useHash: true,
      scrollPositionRestoration: 'enabled'
    }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}