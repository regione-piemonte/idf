/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import {ChartModule} from 'primeng/primeng';
import { AppComponent } from "./app.component";

import { RouterModule } from "@angular/router";
import { AppRoutingModule } from "./app-routing.module";

import { ConfigService } from "./shared/services/config.service";

import { TextMaskModule } from "angular2-text-mask";
import { SharedModule } from "./shared";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { LoaderInterceptor } from "./shared/interceptors/loader.interceptor";
import { HttpXsrfInterceptor} from './shared/interceptors/http-xrsf.interceptor';
import {PrintService} from './shared/services/print.service';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    ChartModule,
    RouterModule,
    ReactiveFormsModule,
    AppRoutingModule,
    TextMaskModule,
    SharedModule
  ],
  providers: [
    ConfigService,PrintService,
    { 
      provide: HTTP_INTERCEPTORS, 
      useClass: LoaderInterceptor, 
      multi: true },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpXsrfInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent,]
})
export class AppModule {}
