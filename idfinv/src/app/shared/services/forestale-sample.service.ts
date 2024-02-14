/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { Observable, from } from "rxjs";
import { SelectItem } from "primeng/components/common/selectitem";
import { AreaSearch } from "../models/area-search";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { CONST } from "../constants";
import { Specie } from "../models/specie";
import { map, timeout, retryWhen, catchError } from "rxjs/operators";
import { SrvError } from "../models/test/error";
import { handleError, selectiveRetryStrategy } from "src/app/service.utils";
import { ConfigService } from "./config.service";
import { Soggetto } from "../models/soggetto";
import { Comune } from "../models/comune";
import { Provincia } from "../models/provincia";
import { Dettaglio } from "../models/dettaglio";
import { AmbitoRilievo } from "../models/ambito-rilievo";
import { CategoriaForestale } from "../models/categoria-forestale";
import { DatiStazionaliField } from "../models/dati-stazionali-field";
import { TipoForestale } from "../models/tipo-forestale";
import { AreaDiSaggio } from "../models/area-di-saggio";
import { User } from "../models/user";
import { Content } from "../models/table-object";

@Injectable({
  providedIn: "root"
})
export class ForestaleSampleService {
  private baseUrl = "http://localhost:8080";

  private fillTableTest = [
    {
      name: "marko",
      lastName: "catic",
      age: 23
    },
    {
      name: "asdas",
      lastName: "asdasd",
      age: 23
    },
    {
      name: "ddd",
      lastName: "catdddic",
      age: 23
    },
    {
      name: "ddd",
      lastName: "catdddic",
      age: 23
    },
    {
      name: "ddd",
      lastName: "catdddic",
      age: 23
    },
    {
      name: "aaa",
      lastName: "caaaatic",
      age: 23
    }
  ];

  private fillDropdownTest: SelectItem[] = [
    {
      label: "test",
      value: 1
    },
    {
      label: "test2",
      value: 2
    },
    {
      label: "test3",
      value: 3
    }
  ];

  constructor(private http: HttpClient, private config: ConfigService) {}

  getConfigationByName(configName: string): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }

  getTableTest(): Observable<any> {
    return from([this.fillTableTest]);
  }

  getDropdownTest(): Observable<SelectItem[]> {
    return from([this.fillDropdownTest]);
  }

  getErrorSteps(codiceAds: string): Observable<AreaDiSaggio> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/adsrelSpecie/allStepErrors/${codiceAds}`);
  }

  getUserData(userId: number): Observable<Soggetto> {
    return this.http.get<Soggetto>(
      `${this.config.getBERootUrl(false)}/soggetti/${userId}`
    );
  }
  getUser(userId: number): Observable<User> {
    return this.http.get<User>(
      `${this.config.getBERootUrl(false)}/utenti`
    );
  }

  putUserData(userData: Soggetto) {
    // let errorHandler = handleError.bind(this);
    return this.http
      .put<any>(`${this.config.getBERootUrl(false)}/soggetti`, userData, {
        observe: "response"
      })
      // .pipe(
      //   timeout(50000),
      //   retryWhen(
      //     selectiveRetryStrategy({
      //       scalingDuration: 10,
      //       excludedStatusCodes: [302, 428, 0],
      //       maxRetryAttempts: 2
      //     })
      //   ),
      //   catchError(errorHandler)
      // );
  }

  postUser(user: User) {
   
    return this.http.get<any>(`${this.config.getBERootUrl(
      false
    )}/utenti?codiceFiscale=${user.codiceFiscale}&nome=${user.nome}&cognome=${
      user.cognome
    }&ruolo=${user.ruolo}&provider=${user.provider}
    `);
  }
  
  changeUser(user: User): Observable<User> {
    if (user.idSoggetto === 0 || user.idSoggetto === null) {
      return this.http.post<User>(
        `${this.config.getBERootUrl(false)}/utenti`,
        user
      );
    } else {
      return this.http.put<User>(
        `${this.config.getBERootUrl(false)}/utenti`,
        user
      );
    }
  }

  getSearchedData(
    page: number,
    
    limit: number,
    searchForm?: AreaSearch,
    sortOrder?: string
  ): Observable<Content<AreaDiSaggio[]>> {
    let errorHandler = handleError.bind(this);
    let queryString: string;
    let sortQuery: string;
    
    queryString = this.createQueryString(searchForm)
    
    

    if(sortOrder) {
      sortQuery = `&sort=${sortOrder}`;
    }
    return this.http
      .get<Content<AreaDiSaggio[]>>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search?${queryString ? queryString : ''}&page=${page}&limit=${limit}${sortQuery ? sortQuery : ''}`)
      .pipe(
        map((response: Content<AreaDiSaggio[]>) => {
          return response;
        }),
        catchError(errorHandler)
      );
  }

  createQueryString(searchForm?: AreaSearch) : string {
    let queryString: string;
    if (searchForm) {
      for(let i = 0;i < searchForm.formDates.length; i++) {
        let datesValue = searchForm.formDates[i];
          if (datesValue.fromDate instanceof Date && datesValue.fromDate) { searchForm.formDates[i].fromDate= this.formatDate(datesValue.fromDate);}
          if (datesValue.toDate instanceof Date && datesValue.toDate) { searchForm.formDates[i].toDate= this.formatDate(datesValue.toDate);}          
      }
        
       
      queryString = Object.keys(searchForm)
      .filter(key => {    
        return searchForm[key].length > 0 })
      .map(key => this.getValuesFromArray(searchForm[key]))
      .filter(val => val && val.length >0)
      .join("&");
    }

    return queryString;
  }

  getValuesFromArray(arr: any []) {
    let queryStry="";
    arr.forEach(
      obj => queryStry = 
      Object.keys(obj).filter(key => { 
       
        return (obj[key]!=null && obj[key]!=undefined) && (typeof obj[key]!=='string' || (typeof obj[key]==='string' && obj[key]!=""))}).length>0 ? 
      (queryStry.length>0? queryStry+"&":"") + Object.keys(obj).map(key => { 
        
        return  `${key}=${obj[key]!=null && obj[key]!=undefined ? obj[key]:''}`;
      }).join("&") : queryStry
    );
    
    return queryStry;
  }

  getAreaDiSaggioByCodiceAds(codiceAds: string): Observable<any> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/${codiceAds}`).pipe(
      map(
        (response: AreaDiSaggio) => {

          const obj = {
            group1: {
              color: "#F8F8F8",
              items: [
                {
                  description: "Comune",
                  value: response.comune
                },
                {
                  description: "UTM EST",
                  value: response.utmEst
                },
                {
                  description: "UTM NORD",
                  value: response.utmNord
                },
                {
                  description: "Quota ( m s.l.m.)",
                  value: response.quota
                },
                {
                  description: "Esposizione",
                  value: response.espozione
                },
                {
                  description: "Inclinazione (gradi)",
                  value: response.inclinazione
                },
                {
                  description: "Densità di campionamento (ettari)",
                  value: response.densitaCamp
                },
                {
                  description: "Raggio area (metri)",
                  value: response.raggioArea ? response.raggioArea : ""
                },
                {
                  description: "Particella forestale",
                  value: response.particellaForestale
                },
                {
                  description: "Proprietà",
                  value: response.proprieta ? response.proprieta : ""
                },
                {
                  description: "",
                  value: ""
                },
                {
                  description: "",
                  value: ""
                }
              ]
            },
            group2: {
              color: "#F5F5F5",
              items: [
                {
                  description: "Categoria forestale",
                  value: response.categoriaForestale
                },
                {
                  description: "Tipo forestale",
                  value: response.tipoForestale
                },
                {
                  description: "Assetto evolutivo colturale",
                  value: response.assettoEvolutivoColturale
                },
                {
                  description: "Tipo strutturale",
                  value: response.descrTipoStrutturale
                },
                {
                  description: "Stadio di sviluppo",
                  value: response.stadioDiSviluppo ? response.stadioDiSviluppo : ""
                },
                {
                  description: "Classe di fertilità",
                  value: response.classeDiFertilita
                },
                {
                  description: "N° ceppaie",
                  value: response.nCepaie
                },
                {
                  description: "Rinnovazione (decine)",
                  value: response.rinnovazione
                },
                {
                  description: "Specie prevalente rinnovazione",
                  value: response.speciePrevalenteRinnovDescr
                },
                {
                  description: "Copertura chiome (%)",
                  value: response.coperturaChiome
                },
                {
                  description: "Copertura cespugli/ suffrutici (%)",
                  value: response.coperturaCespugli
                },
                {
                  description: "Copertura erbacea (%)",
                  value: response.coperturaErbacea
                },
                {
                  description: "Lettiera (%)",
                  value: response.lettiera
                },
                {
                  description: "",
                  value: ""
                },
                {
                  description: "",
                  value: ""
                },
                {
                  description: "",
                  value: ""
                }
              ]
            },
            group3: {
              color: "#F0F0F0",
              items: [
                {
                  description: "Destinazione",
                  value: response.destinazione
                },
                {
                  description: "Intervento",
                  value: response.intervento
                },
                {
                  description: "Priorità intervento",
                  value: response.priorita
                },
                {
                  description: "Esbosco",
                  value: response.esboscoDescr
                },
                {
                  description: "DEFP (metri)",
                  value: response.defp
                },
                {
                  description: "DESP (metri)",
                  value: response.desp
                },
                {
                  description: "MDP (metri)",
                  value: response.mdp
                },
                {
                  description: "Danno prevalente",
                  value: response.dannoPrevalente
                },
                {
                  description: "Intensità danni (%)",
                  value: response.intesitaDanni
                },
                {
                  description: "N° piante morte",
                  value: response.nPianteMorte
                },
                {
                  description: "Defogliazione (%)",
                  value: response.defogliazione
                },
                {
                  description: "Ingiallimento (%)",
                  value: response.ingiallimento
                },
                {
                  description: "Pascolamento",
                  value: response.pascolamento
                },
                {
                  description: "Note",
                  value: response.note
                }
                // {
                //   description: "Combustibile principale",
                //   value: response.combustibilePrincipale
                // }
                // ,
                // {
                //   description: "Combustibile secondario",
                //   value: response.comune
                // }
              ]
            }
          };

          return obj;
        }
      )
    )
  }

  getTipoForestale(): Observable<SelectItem[]> {
    return this.http.get<TipoForestale[]>(`${this.config.getBERootUrl(false)}/tipoForestale/inventari`)
    .pipe(
      map(
        (response: TipoForestale[]) => {
          const tipoForestaleDropdown: SelectItem[] = [];
            response.forEach((item, i) => {
              tipoForestaleDropdown.push({
                label: item.codiceAmministrativo + ' - ' + item.tipo,
                value: item.idTipoForestale
              });
            });
          return tipoForestaleDropdown;
        }
      )
    );
  }

  getTabsTable() {
    let errorHandler = handleError.bind(this);
    return this.http
      .get<Specie[]>(`${this.config.getBERootUrl(false)}/specie/inventari`)
      .pipe(
        map((response: Specie[]) => {
          const changedSpecie: Specie[] = [];
          response.forEach(item => {
            changedSpecie.push({
              codice: item.codice,
              codicePignatti: item.codicePignatti,
              flg386: item.flg386,
              gruppo: item.gruppo,
              idSpecie: item.idSpecie,
              latino: item.latino,
              linkFoto: item.linkFoto,
              rAdsSpecie: item.latino,
              // rAdsSpecie: item.rAdsSpecie["diametro"],
              linkScheda: item.linkScheda,
              volgare: item.volgare,
              tIdfInveAlberi: item.latino
              // tIdfInveAlberi: item.tIdfInveAlberi["flgPolloneSeme"]
            });
          });
          return changedSpecie;
        }),
        catchError(errorHandler)
      );
  }

  getAlberiCavallettiTable() {
    let errorHandler = handleError.bind(this);
    return this.http
      .get<Specie[]>(`${this.config.getBERootUrl(false)}/specie/inventari`)
      .pipe(
        map((response: Specie[]) => {
          const changedSpecie: Specie[] = [];
          response.forEach(item => {
            changedSpecie.push({
              codice: item.codice,
              codicePignatti: item.codicePignatti,
              flg386: item.flg386,
              gruppo: item.gruppo,
              idSpecie: item.idSpecie,
              latino: item.latino,
              linkFoto: item.linkFoto,
              rAdsSpecie: item.latino,
              // rAdsSpecie: item.rAdsSpecie["diametro"],
              linkScheda: item.linkScheda,
              volgare: item.volgare,
              tIdfInveAlberi: item.latino,
              altezza: 1,
              flgTipoSpecie: item.latino,
              incremento: 1,
              qualita: item.latino
              // tIdfInveAlberi: item.tIdfInveAlberi["flgPolloneSeme"],
              // altezza: item.rAdsSpecie['altezza'],
              // flgTipoSpecie: item.rAdsSpecie['flgTipoSpecie'],
              // incremento: item.rAdsSpecie['incremento'],
              // qualita: item.rAdsSpecie['qualita']
            });
          });
          return changedSpecie;
        }),
        catchError(errorHandler)
      );
  }

  getAmbitoRilievo(): Observable<SelectItem[]> {
    
    return this.http
      .get<AmbitoRilievo[]>(`${this.config.getBERootUrl(false)}/ambitoRilievo`)
      .pipe(
        map((response: AmbitoRilievo[]) => {
          const ambitoToDropdown: SelectItem[] = [];
          response.forEach((item, i) => {
            ambitoToDropdown.push({
              label: item.descrAmbito,
              value: item.idAmbito,
             });
          });
          return ambitoToDropdown;
        })
      );
  }
  
  getAmbitoRilievoRaw(): Observable<AmbitoRilievo[]> {
    return this.http
      .get<AmbitoRilievo[]>(`${this.config.getBERootUrl(false)}/ambitoRilievo`);
  }

  getAmbitoRilievoSpecificareRaw(): Observable<AmbitoRilievo[]> {
    return this.http
      .get<AmbitoRilievo[]>(`${this.config.getBERootUrl(false)}/ambitoRilievo/specificare`);
  }

  getDettaglioAmbitoRaw(idAmbito:number): Observable<AmbitoRilievo[]> {
    
    return this.http
      .get<AmbitoRilievo[]>(`${this.config.getBERootUrl(false)}/ambitoRilievo/${idAmbito}/dettaglio`);
  }

  getAllComuni(): Observable<Comune[]> {
    return this.http.get<Comune[]>(`${this.config.getBERootUrl(false)}/comuni`);
  }

  getComuniByProvincia(istatCode: string): Observable<SelectItem[]> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/comuni/provincia/${istatCode}`).pipe(
      map((response: Comune[]) => {
        const comuneToDropdown: SelectItem[] = [];
        response.forEach((item, i) => {
          comuneToDropdown.push({
            label: item.denominazioneComune,
            value: item.idComune
          });
        });
        return comuneToDropdown;
      })
    );
  }
  getDettaglioAmbitoByIdAmbito(idAmbito: string): Observable<SelectItem[]> {
    
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/ambitoRilievo/${idAmbito}/dettaglio`).pipe(
      map((response: AmbitoRilievo[]) => {
        const dettagglioAmbitoDropdown: SelectItem[] = [];
        
        response.forEach((item, i) => {
          dettagglioAmbitoDropdown.push({
            label: item.descrAmbito,
            value: item.idAmbito
            //value: item
          });
        });
        return dettagglioAmbitoDropdown;
      })
    );
  }

  getPanelData(id: number) {
    return this.http
      .get<Dettaglio>(`${this.config.getBERootUrl(false)}/geoPlPfi/1`)
      .pipe(
        timeout(50000),
        retryWhen(
          selectiveRetryStrategy({
            scalingDuration: 10,
            excludedStatusCodes: [302, 0],
            maxRetryAttempts: 2
          })
        )
      );
  }

  getGrayPanelSteps(codiceAds: string, isSteps?: boolean): Observable<AreaDiSaggio> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/searchADS/${codiceAds}${isSteps ? '?step=true' : ''}`);
  }

  getGrayPanel(codiceAds: string): Observable<AreaDiSaggio> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/${codiceAds}`);
  }

  getDettaglioData(codiceAds: string): Observable<any> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/${codiceAds}`)
    .pipe(
      map((response: AreaDiSaggio) => {
            const obj: DatiStazionaliField[] = [
              {
                description: "Comune",
                value: response.comune
              },
              {
                description: "UTM EST",
                value: response.utmEst?response.utmEst.toString():''
              },
              {
                description: "UTM NORD",
                value: response.utmNord?response.utmNord.toString():''
              },
              {
                description: "Quota ( m s.l.m.)",
                value: response.quota
              },
              {
                description: "Esposizione",
                value: response.espozione
              },
              {
                description: "Inclinazione (gradi)",
                value: response.inclinazione
              },
              {
                description: "Proprietà",
                value: response.proprieta
              },
              {
                description: "Fattore di numerazione relascopica",
                value: response.fattoreNumerazione
              }
            ];
            return {datiStazionali: obj, tipoAds: response.tipoAds};
          })
        );
  }

  getDetaglioData(id: number) {
    return this.http
      .get<Dettaglio>(`${this.config.getBERootUrl(false)}/geoPlPfi/1`)
      .pipe(
        map((response: Dettaglio) => {
          const obj: DatiStazionaliField[] = [
            {
              description: "Comune",
              value: response.denominazione
            },
            {
              description: "Particella",
              value: response.denominazione
            },
            {
              description: "UTM EST",
              value: response.denominazione
            },
            {
              description: "UTM NORD",
              value: response.denominazione
            },
            {
              description: "Quota ( m s.l.m.)",
              value: response.denominazione
            },
            {
              description: "Esposizione",
              value: response.denominazione
            },
            {
              description: "Inclinazione (gradi)",
              value: response.denominazione
            },
            {
              description: "Fattore di numerazione relascopica",
              value: response.denominazione
            },
            {
              description: "Categoria forestale",
              value: response.denominazione
            },
            {
              description: "Tipo forestale",
              value: response.denominazione
            },
            {
              description: "Proprietà",
              value: response.denominazione
            },
            {
              description: "Tipo Strutturale",
              value: response.denominazione
            }
          ]
          return obj;
        })
      );
  }

  getDatiStazionali(id: number) {
    return this.http
      .get<Dettaglio>(`${this.config.getBERootUrl(false)}/geoPlPfi/1`)
      .pipe(
        map((response: Dettaglio) => {
          const obj = {
            group1: {
              color: "#3498db",
              items: [
                {
                  description: "Comune",
                  value: response.denominazione
                },
                {
                  description: "UTM EST",
                  value: response.denominazione
                },
                {
                  description: "UTM NORD",
                  value: response.denominazione
                },
                {
                  description: "Quota ( m s.l.m.)",
                  value: response.denominazione
                },
                {
                  description: "Esposizione",
                  value: response.denominazione
                },
                {
                  description: "Inclinazione (gradi)",
                  value: response.denominazione
                },
                {
                  description: "Densità di campionamento (ettari)",
                  value: response.denominazione
                },
                {
                  description: "Raggio area (metri)",
                  value: response.denominazione
                },
                {
                  description: "Particella forestale",
                  value: response.denominazione
                },
                {
                  description: "Proprietà",
                  value: response.denominazione
                },
                {
                  description: "Comune",
                  value: response.denominazione
                },
                {
                  description: "Comune",
                  value: response.denominazione
                }
              ]
            },
            group2: {
              color: "#f1c40f",
              items: [
                {
                  description: "Categoria forestale",
                  value: response.denominazione
                },
                {
                  description: "Tipo forestale",
                  value: response.denominazione
                },
                {
                  description: "Assetto evolutivo colturale",
                  value: response.denominazione
                },
                {
                  description: "Tipo strutturale",
                  value: response.denominazione
                },
                {
                  description: "Stadio di sviluppo",
                  value: response.denominazione
                },
                {
                  description: "Classe di fertilità",
                  value: response.denominazione
                },
                {
                  description: "N° ceppaie",
                  value: response.denominazione
                },
                {
                  description: "Rinnovazione (decine)",
                  value: response.denominazione
                },
                {
                  description: "Specie prevalente rinnovazione",
                  value: response.denominazione
                },
                {
                  description: "Copertura chiome (%)",
                  value: response.denominazione
                },
                {
                  description: "Copertura cespugli/ suffrutici (%)",
                  value: response.denominazione
                },
                {
                  description: "Copertura erbacea (%)",
                  value: response.denominazione
                },
                {
                  description: "Lettiera (%)",
                  value: response.denominazione
                },
                {
                  description: "Comune",
                  value: response.denominazione
                },
                {
                  description: "Comune",
                  value: response.denominazione
                },
                {
                  description: "Comune",
                  value: response.denominazione
                }
              ]
            },
            group3: {
              color: "#e67e22",
              items: [
                {
                  description: "Destinazione",
                  value: response.denominazione
                },
                {
                  description: "Intervento",
                  value: response.denominazione
                },
                {
                  description: "Priorità intervento",
                  value: response.denominazione
                },
                {
                  description: "Esbosco",
                  value: response.denominazione
                },
                {
                  description: "DEFP (metri)",
                  value: response.denominazione
                },
                {
                  description: "DESP (metri)",
                  value: response.denominazione
                },
                {
                  description: "MDP (metri)",
                  value: response.denominazione
                },
                {
                  description: "Danno prevalente",
                  value: response.denominazione
                },
                {
                  description: "Intensità danni (%)",
                  value: response.denominazione
                },
                {
                  description: "N° piante morte",
                  value: response.denominazione
                },
                {
                  description: "Defogliazione (%)",
                  value: response.denominazione
                },
                {
                  description: "Ingiallimento (%)",
                  value: response.denominazione
                },
                {
                  description: "Pascolamento",
                  value: response.denominazione
                },
                {
                  description: "Note",
                  value: response.note
                }
                // {
                //   description: "Combustibile principale",
                //   value: response.denominazione
                // },
                // {
                //   description: "Combustibile secondario",
                //   value: response.denominazione
                // },
                // {
                //   description: "Tavola",
                //   value: "testtestestadasdadadasdasdasdasdasdasd"
                // }
              ]
            }
          };
          return obj;
        })
      );
  }

  getAllCategoriaForestale(): Observable<SelectItem[]> {
    return this.http
      .get<CategoriaForestale[]>(
        `${this.config.getBERootUrl(false)}/categoriaForestale`
      )
      .pipe(
        map((response: CategoriaForestale[]) => {
          const categoriaForestaleDropdown: SelectItem[] = [];
          response.forEach(item => {
            categoriaForestaleDropdown.push({
              label: item.codiceAmministrativo + ' - ' + item.descrizione,
              value: item.idCategoriaForestale
            });
          });
          return categoriaForestaleDropdown;
        })
      );
  }

  getAllProvincia(): Observable<SelectItem[]> {
    return this.http
      .get<Provincia[]>(`${this.config.getBERootUrl(false)}/provincia/search`)
      .pipe(
        map((response: Provincia[]) => {
          const provinciaDropdown: SelectItem[] = [];
          response.forEach(item => {
            provinciaDropdown.push({
              label: item.denominazioneProv,
              value: item.istatProv
            });
          });
          return provinciaDropdown;
        })
      );
  }

  formatDate(date) {
    if (date === null || date === "" || date === undefined) {
      return "";
    } else {
      var d = new Date(date),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;
      return [year, month, day].join("-");
    }
  }
}
