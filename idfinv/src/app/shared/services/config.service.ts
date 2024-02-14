/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";

@Injectable()
export class ConfigService {
    beRootPath = '/idf/restfacade/be/private';

    bePublishedRootPath = '/lgspa-demo/v1';

    getBERootUrl(publishedApi: boolean): string {
        if (publishedApi){
            return environment.apiGWServerPrefix + this.bePublishedRootPath;
        } else {
            return environment.beServerPrefix + this.beRootPath;
        }
    }

    getApiAccessToken(): string {
        return environment.apiAccessToken;
    }
}