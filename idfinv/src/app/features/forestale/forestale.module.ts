/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForestaleRoutingModule } from './forestale-routing.module';
import { SharedModule } from 'src/app/shared';

import * as fromPages from './pages';
import * as fromComponents from './components';
import { StepperComponent } from './pages/stepper/stepper.component';

const modules: any[] = [
  CommonModule,
  ForestaleRoutingModule,
  SharedModule
]
@NgModule({
  imports: [
    ...modules,
  ],
  declarations: [
    ...fromPages.pages,
    ...fromComponents.components,
    StepperComponent
  ]
})
export class ForestaleModule { }
