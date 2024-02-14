/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
/*
export const environment = {
  production: false,
  pathName: "/",
  projectName: "",
  siforPrefix: "http://tst-www.sistemapiemonte.it",
  // beServerPrefix: 'http://localhost:10110',
  beServerPrefix: "https://tst-idf.preprod.nivolapiemonte.it",
  apiGWServerPrefix: "https://tst-api-piemonte.ecosis.csi.it:443/api",
  apiAccessToken: "57f8c486-9390-3d5e-82f5-ebeeb6e729ec",
  irideToken: "",
};*/

export const environment = {
  production: false,
  pathName: "/",
  projectName: "",
  siforPrefix: "https://tst-www.servizi.piemonte.it",
  beServerPrefix: "https://tst-idf.preprod.nivolapiemonte.it",
  //beServerPrefix: "http://localhost:8080",

  apiGWServerPrefix: "https://tst-api-piemonte.ecosis.csi.it:443/api",
  apiAccessToken: "57f8c486-9390-3d5e-82f5-ebeeb6e729ec",
  //irideToken: "AAAAAA00A11S000A/CSI PIEMONTE/DEMO 36/SP//1//",
  //irideToken: "AAAAAA00A11E000M/CSI PIEMONTE/DEMO 24/SP//1//",
  //irideToken: "AAAAAA00A11D000L/CSI PIEMONTE/DEMO 23/SP//1//",
  //irideToken: "AAAAAA00B77B000F/CSI PIEMONTE/DEMO 20/SP//1//",
  //irideToken: "AAAAAA00A11G000O/CSI PIEMONTE/DEMO 26/SP//1//",
  //irideToken: "AAAAAA00A11K000S/CSI PIEMONTE/DEMO 30/SP//1//",
  irideToken: "",
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
