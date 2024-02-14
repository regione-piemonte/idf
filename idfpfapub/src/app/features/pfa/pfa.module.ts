/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PfaRoutingModule } from "./pfa-routing.module";
import { SharedModule } from "src/app/shared";

import * as fromPages from "./pages";
import * as fromComponents from "./components";
import { PfaConfigComponent } from "./pfa-config";
const modules: any[] = [SharedModule, CommonModule, PfaRoutingModule];

@NgModule({
  imports: [...modules],
  declarations: [
    ...fromPages.pages,
    ...fromComponents.components,
    PfaConfigComponent
  ]
})
export class PfaModule {}
