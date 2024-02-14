/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
    production: false,
    serviziSiforPrefix:'https://www.servizi.piemonte.it/srv/sifor/',
    siforPrefix:'http://dev-www.sistemapiemonte.it',
    beServerPrefix: 'http://localhost:10110',
    apiGWServerPrefix: 'https://tst-api-piemonte.ecosis.csi.it:443/api',
    apiAccessToken: '57f8c486-9390-3d5e-82f5-ebeeb6e729ec'
  };
  
  /*
   * In development mode, to ignore zone related error stack frames such as
   * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
   * import the following file, but please comment it out in production mode
   * because it will have performance impact when throw error
   */
  // import 'zone.js/dist/zone-error';  // Included with Angular CLI.
  