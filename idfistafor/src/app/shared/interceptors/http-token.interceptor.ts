/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: "root" })
export class HttpTokenInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (environment.irideToken != ''){
      req = req.clone({
        params: req.params.append("Shib-Iride-IdentitaDigitale", encodeURI(environment.irideToken))
      });
    }


    return next.handle(req);
  }
}