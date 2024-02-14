/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CONST } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    let url: string = state.url;
    return this.checkIsAuth(url, next);
  }

  checkIsAuth(url: string, next : ActivatedRouteSnapshot): boolean {
    this.authService.redirectUrl = url;
    if (this.authService.checkPermision()) {
      switch (this.authService.user.ruolo) {
        case CONST.ROLE_CITADINO:
          //let numberOfParams = Object.keys(next.params);
          //next.routeConfig.path = numberOfParams.length > 2 ? next.routeConfig.path = '' : next.routeConfig.path;
          let isCitadinoRoute = CONST.CITADINO_ROUTES.some(route => {
            return route === next.routeConfig.path;
          })
          if (isCitadinoRoute) {
            return true;
          } else {
            this.router.navigate(['/']);
            return false;
          }
        case CONST.ROLE_GESTORE:
          let isGestoreRoute = CONST.GESTORE_ROUTES.some(route => {
            return route === this.splitRedirctionURI(url);
          })
          if (isGestoreRoute) {
            return true;
          } else {
            this.router.navigate(['/']);
            return false;
          }
        case CONST.ROLE_UF_TERRIRORIALE:
            let isUfTerritorialeRoute = CONST.UF_TERRITORIALE_ROUTES.some(route => {
              return route === this.splitRedirctionURI(url);
            })
            if (isUfTerritorialeRoute) {
              return true;
            } else {
              this.router.navigate(['/']);
              return false;
            }
        case CONST.ROLE_COMUNE:
            let isComuneRoute = CONST.COMUNE_ROUTES.some(route => {
              return route === this.splitRedirctionURI(url);
            })
            if (isComuneRoute) {
              return true;
            } else {
              this.router.navigate(['/']);
              return false;
            }
        case CONST.ROLE_CONSULTAZIONE:
            let isConsultazioneRoute = CONST.CONSULTAZIONE_ROUTES.some(route => {
              return route === this.splitRedirctionURI(url);
            })
            if (isConsultazioneRoute) {
              return true;
            } else {
              this.router.navigate(['/']);
              return false;
            }
        case CONST.ROLE_SPORTELLO:
          let isSportelloRoute = CONST.SPORTELLO_ROUTES.some(route => {
            return route === next.routeConfig.path;
          })
          if (isSportelloRoute) {
            return true;
          } else {
            this.router.navigate(['/']);
            return false;
          }
        case CONST.ROLE_SPORTELLO_GESTORE:
          let isSportelloGestRoute = CONST.SPORTELLO_GESTORE_ROUTES.some(route => {
            return route === next.routeConfig.path;
          })
          if (isSportelloGestRoute) {
            return true;
          } else {
            this.router.navigate(['/']);
            return false;
          }
        default:
          this.router.navigate(['/'])
          return false;
      }
    }
    this.router.navigate(['/']);
    return false;
  }

  splitRedirctionURI(url: string) {
    let onlyModifica: string[] = url.split(';', 3);
    return onlyModifica[2];
  }
}
