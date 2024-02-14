/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
} from "@angular/common/http";
import { LoadingService } from "../services/loading.service";
import { Observable } from "rxjs";
import { finalize } from "rxjs/operators";
import { Injectable } from "@angular/core";

@Injectable()
export class LoaderInterceptor implements HttpInterceptor {
  loaderCount: number = 0;

  constructor(private loadingService: LoadingService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    this.loadingService.emitLoading(true);
    this.loaderCount++;
    return next.handle(req).pipe(
      finalize(() => {
        this.loaderCount--;
        /* console.log("LOADER COUNT", this.loaderCount); */
        if (this.loaderCount === 0) {
          this.loadingService.emitLoading(false);
        }
      })
    );
  }
}
