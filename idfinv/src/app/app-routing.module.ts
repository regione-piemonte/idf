/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/forestale/links', pathMatch: 'full' },
  { path: 'forestale', loadChildren: './features/forestale/forestale.module#ForestaleModule'}
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