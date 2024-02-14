/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
// imports from shared folder (directives, pipes, components)
import { NgModule } from "@angular/core";
import { CommonModule, DecimalPipe } from "@angular/common";
import * as fromComponents from "./components";
import * as fromDirectives from "./directives";
import * as fromPipes from "./pipes";
// import for primeng
import { CheckboxModule } from "primeng/checkbox";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { FileUploadModule } from "primeng/fileupload";
import { TableModule } from "primeng/table";
import { DialogModule } from "primeng/dialog";
import { DropdownModule } from "primeng/dropdown";
import { CalendarModule } from "primeng/calendar";
import { TabViewModule } from "primeng/tabview";
import { RadioButtonModule } from "primeng/radiobutton";
import { KeyFilterModule } from "primeng/keyfilter";
import { AutoCompleteModule } from "primeng/autocomplete";
import { MultiSelectModule } from "primeng/multiselect";
// import { DataTableModule } from "angular7-data-table";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
// import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { ButtonModule } from "primeng/button";
import { ChartModule } from "primeng/chart";
import { MenuModule } from "primeng/menu";
// import { TranslateModule } from "@ngx-translate/core";
import { PaginatorModule } from "primeng/paginator";
import { InputTextareaModule } from "primeng/inputtextarea";
import { AccordionModule } from "primeng/accordion";
import { BreadcrumbModule } from "primeng/breadcrumb";
import { TooltipModule } from "primeng/tooltip";
import { OverlayPanelModule } from "primeng/overlaypanel";
import { StepsModule } from "primeng/steps";
import { DeepFieldValuePipe } from './pipes/deep-field-value.pipe';
import { DecimalNumberNAPipe } from './pipes/decimal-number-na.pipe';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { VerticalTableComponent } from './components/vertical-table/vertical-table.component';
import { TableTransformPipe } from './pipes/table-transform.pipe';
import { PropCatastoPipe } from './pipes/prop-catasto.pipe';
import { FooterComponent } from './components/footer/footer.component';
import { StepsWithErrorsComponent } from './components/steps-with-errors/steps-with-errors.component';
import { ListOfGeometriesComponent } from './components/list-of-geometries/list-of-geometries.component';

const modules: any[] = [
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
  CheckboxModule,
  StepsModule,
  ConfirmDialogModule
];

@NgModule({
  imports: [...modules],
  declarations: [
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes,
    DeepFieldValuePipe,
    DecimalNumberNAPipe,
    VerticalTableComponent,
    TableTransformPipe,
    PropCatastoPipe,
    FooterComponent,
    StepsWithErrorsComponent,
    ListOfGeometriesComponent,
  ],
  exports: [
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes,
    ...modules,
    VerticalTableComponent,
    TableTransformPipe,
    PropCatastoPipe
  ],
  providers: [
    DecimalPipe,
    ConfirmationService,
    TableTransformPipe,
    PropCatastoPipe,
  ]
})
export class SharedModule {}
