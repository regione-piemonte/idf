/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse,
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { retry, tap } from "rxjs/operators";
import { ErrorService } from "../services/error.service";
import { ErrorCode } from "../models/error.model";

const valuesError = [
  "Particella geometry invalid",
  "GeecoApiServiceImpl:getGeecoConfiguration - Errore nella generazione del JSON per geeco [Errore GEECO getGeecoConfiguration] null",
];
@Injectable({ providedIn: "root" })
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor(private errorService: ErrorService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      retry(0),
      tap(
        (event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            if (event.body) {
              if (event.body.codice) {
                if (
                  event.status === 200 &&
                  event.body.codice !== ErrorCode.SUCCESS
                ) {
                  console.log(event.body.codice);
                  this.errorService.emitError(event.body.codice);
                }
              }
            }
          }
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (
              //err.error.includes(valuesError)
              valuesError.includes(err.error)
            ) {
              return throwError(() => err);
            } else {
              this.errorService.emitError(err.statusText);
            }
          }
        }
      )
    );
  }
}
