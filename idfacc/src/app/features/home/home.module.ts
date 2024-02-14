/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import * as fromPages from './pages';
import * as fromComponents from './components';
import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from 'src/app/shared';

const modules : any[] = [
  CommonModule,
  HomeRoutingModule,
  SharedModule
];

@NgModule({
  imports: [
    ...modules
  ],
  declarations: [
    ...fromComponents.components,
    ...fromPages.pages
  ]
})
export class HomeModule { }
