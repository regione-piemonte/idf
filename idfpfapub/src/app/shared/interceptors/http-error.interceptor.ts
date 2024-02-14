/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
  HttpErrorResponse
} from "@angular/common/http";
import { ErrorService } from "../services/error.service";
import { Observable } from "rxjs";
import { retry, tap } from "rxjs/operators";
import { ErrorCode } from "../models/error.model";

@Injectable({ providedIn: "root" })
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private errorService: ErrorService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(      
      tap(
        (event: HttpEvent<any>) => {
          
          if (event instanceof HttpResponse) {
            if (event.body) {
              if (event.body.codice) {
                if (
                  event.status === 200 &&
                  event.body.codice !== ErrorCode.SUCCESS
                ) {
                  this.errorService.emitError(event.body.codice);
                }
              }
            }
          }
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            this.errorService.emitError(err.statusText);
            console.log("ERROR 1");
          }
        }
      )
    );
  }
}
