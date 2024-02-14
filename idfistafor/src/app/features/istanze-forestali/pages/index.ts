/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ForestalShellComponent } from './forestal-shell.component';
import { HomePageComponent } from './home-page/home-page.component';
import { VisualizzaComponent } from './visualizza/visualizza.component';
import { InserisciNuovaComponent } from './inserisci-nuova/inserisci-nuova.component';
import { SearchInArhiveComponent } from './search-in-arhive/search-in-arhive.component';
import { InserisciNuovaTagliComponent } from './inserisci-nuova-tagli/inserisci-nuova-tagli.component';
import { RicercaInArchivioTagliComponent } from './ricerca-in-archivio-tagli/ricerca-in-archivio-tagli.component';
import { RicercaGestoreSportelloComponent } from './ricerca-gestore-sportello/ricerca-gestore-sportello.component';

export const pages: any[] = [
  ForestalShellComponent,
  HomePageComponent,
  VisualizzaComponent,
  InserisciNuovaComponent,
  SearchInArhiveComponent,
  RicercaInArchivioTagliComponent,
  RicercaGestoreSportelloComponent
];
