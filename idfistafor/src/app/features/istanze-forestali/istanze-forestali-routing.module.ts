/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { ForestalShellComponent } from './pages/forestal-shell.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { VisualizzaComponent } from './pages/visualizza/visualizza.component';
import { InserisciNuovaComponent } from './pages/inserisci-nuova/inserisci-nuova.component';
import { SearchInArhiveComponent } from './pages/search-in-arhive/search-in-arhive.component';
import { AuthGuard } from 'src/app/shared//auth/auth.guard';
import { InserisciNuovaVidComponent } from './pages/inserisci-nuova-vid/inserisci-nuova-vid.component';
import { VisualizzaVidComponent } from './pages/visualizza-vid/visualizza-vid.component';
import { VisualizzaTagliComponent } from './pages/visualizza-tagli/visualizza-tagli.component';
import { InserisciNuovaTagliComponent } from './pages/inserisci-nuova-tagli/inserisci-nuova-tagli.component';
import { RicercaInArchivioTagliComponent } from './pages/ricerca-in-archivio-tagli/ricerca-in-archivio-tagli.component';
import { RicercaGestoreSportelloComponent } from './pages/ricerca-gestore-sportello/ricerca-gestore-sportello.component';

const routes: Routes = [
  {
    path: '',
    component: ForestalShellComponent,
    children: [
      { path: ``, component: HomePageComponent, pathMatch: 'full'},
      { path: `visualizza`, component: VisualizzaComponent, canActivate:[AuthGuard]},
      { path: `visualizza-vid`, component: VisualizzaVidComponent, canActivate:[AuthGuard]},
      { path: `visualizza-tagli`, component: VisualizzaTagliComponent, canActivate:[AuthGuard]},
      { path: `inserisci-nuova`, component: InserisciNuovaComponent, canActivate:[AuthGuard]},
      { path: `inserisci-nuova-vid`, component: InserisciNuovaVidComponent, canActivate:[AuthGuard]},
      { path: `inserisci-nuova-tagli`, component: InserisciNuovaTagliComponent, canActivate:[AuthGuard]},
      { path: `ricerca-archivio-tagli`, component: RicercaInArchivioTagliComponent, canActivate:[AuthGuard]},
      { path: `ricerca-gestore-sportello-tagli`, component: RicercaGestoreSportelloComponent, canActivate:[AuthGuard]},
      { path: `modifica/:idIntervento`, component: InserisciNuovaComponent, canActivate:[AuthGuard]},
      { path: `modifica-vid/:idIntervento`, component: InserisciNuovaVidComponent, canActivate:[AuthGuard]},
      { path: `modifica-tagli/:idIntervento`, component: InserisciNuovaTagliComponent, canActivate:[AuthGuard]},
      { path: `return-modifica/:idIntervento`, component: InserisciNuovaComponent, canActivate:[AuthGuard]},
      { path: `return-modifica-vid/:idIntervento`, component: InserisciNuovaVidComponent, canActivate:[AuthGuard]},
      { path: `return-modifica-tagli/:idIntervento`, component: InserisciNuovaTagliComponent, canActivate:[AuthGuard]},
      { path: `ricerca`, component: SearchInArhiveComponent, canActivate:[AuthGuard]},
      { path: '**', redirectTo: './', }
      //${NormativaGroupName.ORP_CIT}/${NormativaType.GOV}/:id
    ],
  },
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IstanzeForestaliRoutingModule { }
