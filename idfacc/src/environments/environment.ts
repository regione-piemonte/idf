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
  siforPrefix:'https://tst-www.servizi.piemonte.it',
  //beServerPrefix: 'http://localhost:10110',
  beServerPrefix: 'https://tst-idf.preprod.nivolapiemonte.it',
  apiGWServerPrefix: 'https://tst-api-piemonte.ecosis.csi.it:443/api',
  apiAccessToken: '57f8c486-9390-3d5e-82f5-ebeeb6e729ec',
  redirectionUrl: 'https://tst-idf.preprod.nivolapiemonte.it',
  baseHref: '/idf'
};
