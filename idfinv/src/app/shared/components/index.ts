/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { ReusableTableComponent } from "./reusable-table/reusable-table.component";
import { ForestaleConfig } from "src/app/features/forestale/forestale-config";
import { ReusableNoSelectTableComponent } from "./reusable-no-select-table/reusable-no-select-table.component";
import { TipologiaPanelComponent } from "./tipologia-panel/tipologia-panel.component";
import { LoaderComponent } from "./loader/loader.component";
import { ErrorDialogComponent } from "./error-dialog/error-dialog.component";

export const components: any[] = [ 
    HeaderComponent,
    FooterComponent,
    ReusableTableComponent,
    ForestaleConfig,
    ReusableNoSelectTableComponent,
    TipologiaPanelComponent,
    LoaderComponent,
    ErrorDialogComponent
];

export { HeaderComponent } from "./header/header.component";
export { FooterComponent } from "./footer/footer.component";
export { ReusableTableComponent } from "./reusable-table/reusable-table.component";
export { ForestaleConfig } from "src/app/features/forestale/forestale-config";
export { ReusableNoSelectTableComponent } from "./reusable-no-select-table/reusable-no-select-table.component";
export { TipologiaPanelComponent } from "./tipologia-panel/tipologia-panel.component";
export { LoaderComponent } from "./loader/loader.component";
export { ErrorDialogComponent } from "./error-dialog/error-dialog.component";

