/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import * as fromPages from "./pages";
import * as fromComponents from "./components";
import { HomeRoutingModule } from "./home-routing.module";
import { SharedModule } from "src/app/shared";
import { PfaConfigComponent } from "./pfa-config";
import { RisultatoComponent } from './components/risultato/risultato.component';
import { BasimetriaComponent } from './components/basimetria/basimetria.component';
import { IpsometriaComponent } from './components/ipsometria/ipsometria.component';
import { DiametriComponent } from './components/diametri/diametri.component';
import { VolumiComponent } from './components/volumi/volumi.component';
import { IncrementiComponent } from './components/incrementi/incrementi.component';

const modules: any[] = [CommonModule, HomeRoutingModule, SharedModule];

@NgModule({
  imports: [...modules],
  declarations: [
    ...fromComponents.components,
    ...fromPages.pages,
    PfaConfigComponent,
    RisultatoComponent,
    BasimetriaComponent,
    IpsometriaComponent,
    DiametriComponent,
    VolumiComponent,
    IncrementiComponent
  ]
})
export class HomeModule {}
