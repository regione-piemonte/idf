/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as fromComponents from './components';
import * as fromDirectives from './directives';
import * as fromPipes from './pipes';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ButtonModule } from "primeng/button";
import { ChartModule } from "primeng/chart";
import { MenuModule } from "primeng/menu";
import { PaginatorModule } from "primeng/paginator";
import {InputTextareaModule} from 'primeng/inputtextarea';
import {AccordionModule} from 'primeng/accordion';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {TooltipModule} from 'primeng/tooltip';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {CheckboxModule} from 'primeng/checkbox';
import {StepsModule} from 'primeng/steps';
import {ToastModule} from 'primeng/toast';
import { FooterComponent } from './components/footer/footer.component';

const modules: any[] = [
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
  StepsModule,
  ToastModule
];

@NgModule({
  imports: [
    ...modules
  ],
  declarations: [
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes,
    FooterComponent,
    
  ],
  exports: [
    ...modules,
    ...fromComponents.components,
    ...fromDirectives.directives,
    ...fromPipes.pipes
  ]
})
export class SharedModule { }
