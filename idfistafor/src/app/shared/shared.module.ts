/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import * as fromComponents from './components';
import * as fromDirectives from './directives';
import * as fromPipes from './pipes';
import { FileUploadModule } from 'primeng/fileupload';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { TabViewModule } from 'primeng/tabview';
import { RadioButtonModule } from 'primeng/radiobutton';
import { KeyFilterModule } from 'primeng/keyfilter';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { MultiSelectModule } from 'primeng/multiselect';
// import { DataTableModule } from 'angular7-data-table';
// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonModule } from 'primeng/button';
import { ChartModule } from 'primeng/chart';
import { MenuModule } from 'primeng/menu';
// import { TranslateModule } from "@ngx-translate/core";
import { PaginatorModule } from 'primeng/paginator';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {AccordionModule} from 'primeng/accordion';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {TooltipModule} from 'primeng/tooltip';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {StepsModule} from 'primeng/steps';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import { ReusableFrontTableComponent } from './components/reusable-front-table/reusable-front-table.component';
import { AuthGuard } from './auth/auth.guard';
import { FooterComponent } from './components/footer/footer.component';
import { GestoreNoSelectTableComponent } from './components/gestore-no-select-table/gestore-no-select-table.component';
import { PersonaFormVidComponent } from './components/persona-form-vid/persona-form-vid.component';
import { SelezionatoVidComponent } from './components/selezionato-vid/selezionato-vid.component';
import { VariantiProrogheTableComponent } from './components/varianti-proroghe-table/varianti-proroghe-table.component';
import {ChipsModule} from 'primeng/chips';

const modules: any = [
 CommonModule,
 TableModule,
 ButtonModule,
 DialogModule,
 DropdownModule,
 FormsModule,
 CalendarModule,
 TabViewModule,
 RadioButtonModule,
 FileUploadModule,
 KeyFilterModule,
 AutoCompleteModule,
 MultiSelectModule,
 ChartModule,
 MenuModule,
 // DataTableModule,
 // TranslateModule,
 NgbModule,
 // FontAwesomeModule,
 ReactiveFormsModule,
 PaginatorModule,
 DropdownModule,
 InputTextareaModule,
 DialogModule,
 AccordionModule,
 BreadcrumbModule,
 TooltipModule,
 OverlayPanelModule,
 StepsModule,
 ChipsModule
];

@NgModule({
  imports: [...modules],

  declarations: [
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes,
    FooterComponent,
    GestoreNoSelectTableComponent,
    PersonaFormVidComponent,
    SelezionatoVidComponent,
    VariantiProrogheTableComponent,
  ],

  exports: [
    ...modules,
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes
  ],

  providers:[DatePipe, AuthGuard]
})
export class SharedModule { }
