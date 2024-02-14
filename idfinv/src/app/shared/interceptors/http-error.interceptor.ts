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
  HttpErrorResponse
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { retry, tap } from "rxjs/operators";
import { ErrorService } from "../services/error.service";
import { ErrorCodes } from "../models/error-codes";

@Injectable({ providedIn: "root" })
export class HttpErrorInterceptor implements HttpInterceptor {

  skipErrorReportCodes = [ErrorCodes.ADS_CONSOLIDA_VALIDATION_ERROR]

  constructor(private errorService: ErrorService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(      
      tap(
        (event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            if(event.body) {
              if (event.body.codice) {
                if (
                  event.status === 200 &&
                  event.body.codice !== ErrorCodes.SUCCESS && this.skipErrorReportCodes.findIndex( errCode => event.body.codice == errCode)<0
                ) {
                  const testo = event.body? event.body.testo: "Errore generico";
                  this.errorService.emitError(this.getErrorResponseText(event.body));                 
                }
              }
            }
          }
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            this.errorService.emitError(err.statusText);            
          }
        }
      )
    );
  }

  private getErrorResponseText(error: {codice: ErrorCodes, testo: string}) {
    switch (error.codice) {
      
      case ErrorCodes.E00: return 'Si è verificato un errore generico.';

      case ErrorCodes.E01: return 'Mancano i dati.';

      case ErrorCodes.E02: return 'Errore dati.';

      case ErrorCodes.E03: return 'Errore di convalida.';

      case ErrorCodes.E05: return error.testo; // TODO: every error that comes with a message should be of this code.
      
      default: return 'Si è verificato un errore generico.';
    }
  }
}
