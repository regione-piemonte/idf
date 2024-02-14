/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ForestaleShellComponent, LinksComponent, SearchFormComponent, ForestaleTabsComponent, DettaglioComponent, DatiGeneraliComponent, StepsComponent, InserimentModificaComponent } from './pages';
import { PersonaliComponent } from './pages/personali/personali.component';

const routes: Routes = [
   { path: '', component: ForestaleShellComponent, children: [
      {path: '', redirectTo: 'links', pathMatch: 'full'},
      {path: 'personali', component: PersonaliComponent},
      {path: 'links', component: LinksComponent},
      {path: 'modifica-consolida', component: SearchFormComponent, data: {reuse: true}},
      {path: 'search', component: SearchFormComponent, data: {reuse: true}},
      {path: 'search/:torna', component: SearchFormComponent, data: {reuse: true}},
      {path: 'tabs/:id', component: ForestaleTabsComponent},
      {path: 'dettaglio/:id', component: DettaglioComponent},
      {path: 'dati-generali/:id', component: DatiGeneraliComponent},
      {path: 'dati-generali', component: DatiGeneraliComponent},
      {path: 'steps/:id', component: StepsComponent},
      {path: 'modifica/:id', component: InserimentModificaComponent}
   ]}
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  declarations: [],
  exports: [RouterModule]
})
export class ForestaleRoutingModule { }
