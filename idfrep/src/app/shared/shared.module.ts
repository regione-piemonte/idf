/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import * as fromComponents from "./components";
import * as fromPipes from "./pipes";
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
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ButtonModule } from "primeng/button";
import { ChartModule } from "primeng/chart";
import { MenuModule } from "primeng/menu";
import { PaginatorModule } from "primeng/paginator";
import { InputTextareaModule } from "primeng/inputtextarea";
import { AccordionModule } from "primeng/accordion";
import { BreadcrumbModule } from "primeng/breadcrumb";
import { TooltipModule } from "primeng/tooltip";
import { OverlayPanelModule } from "primeng/overlaypanel";
import { CheckboxModule } from "primeng/checkbox";
import { StepsModule } from "primeng/steps";
import { ChartComponent } from './components/chart/chart.component';
import { TableComponent } from './components/table/table.component';
import { ScatterChartComponent } from './components/scatter-chart/scatter-chart.component';
import { GoogleChartsModule } from 'angular-google-charts';
import { GoogleChartComponent } from './components/google-chart/google-chart.component';
import { ComboChartComponent } from './components/combo-chart/combo-chart.component';
import { FooterComponent } from './components/footer/footer.component';
import { TableRowMergeComponent } from './components/table-row-merge/table-row-merge.component';
import { PaginatedTableComponent } from './components/paginated-table/paginated-table.component';

const modules: any[] = [
  CommonModule,
  ReactiveFormsModule,
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
  NgbModule,
  PaginatorModule,
  DropdownModule,
  InputTextareaModule,
  DialogModule,
  AccordionModule,
  BreadcrumbModule,
  TooltipModule,
  OverlayPanelModule,
  CheckboxModule,
  StepsModule
];

@NgModule({
  imports: [...modules,GoogleChartsModule],
  declarations: [...fromComponents.components, ...fromPipes.pipes, ChartComponent, TableComponent, ScatterChartComponent, GoogleChartComponent, ComboChartComponent, FooterComponent, TableRowMergeComponent, PaginatedTableComponent],
  exports: [...modules, ...fromComponents.components, ...fromPipes.pipes]
})
export class SharedModule {}
