/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  Output,
  EventEmitter,
} from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Subject, combineLatest } from "rxjs";
import { takeUntil, map } from "rxjs/operators";
import { DatePipe } from "@angular/common";
import { ForestaliService } from "../../services/forestali.service";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { CONST } from "src/app/shared/constants";
import { TableHeader } from "src/app/shared/models/table-header";
import { Router } from "@angular/router";
import * as fileSaver from "file-saver";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { UserChoiceModel } from "src/app/shared/models/user-choice.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { AmbitoInstanza } from "src/app/shared/models/ambito-instanza.model";
import { TagliService } from "src/app/features/istanze-forestali/services/tagli.service";
import { Governo } from "src/app/shared/models/governo.model";
import { TipoRichiedente } from "./../../../../shared/models/tipo-richiedente.model";
import { Utilizzatore } from "src/app/shared/models/utilizzatore.model";
import { CategoriaForestale } from "src/app/shared/models/categoria-forestale.model";
import { HomeModel } from "src/app/shared/models/home.model";
import { environment } from "src/environments/environment";

@Component({
  selector: "app-search-in-arhive",
  templateUrl: "./search-in-arhive.component.html",
  styleUrls: ["./search-in-arhive.component.css"],
})
export class SearchInArhiveComponent implements OnInit, OnDestroy {
  @Output() deleteRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() homeNavigationEmitter: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();

  showGeeco: boolean = false;
  mapUrl: SafeResourceUrl;
  unsubscribe$ = new Subject<void>();
  currentYear: number = new Date().getFullYear();
  searchForm: FormGroup;
  calcoloEconomicoCompensiazioneValidation = true;
  dataPresentazioneValidation = true;
  dataConclusioneIntervValidation = true;
  dataScadenzaProvValidation = true;
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  provincia: ProvinciaModel[];
  comune: ComuneModel[];
  sezione;
  foglio;
  particella;

  tipologiaIstanza;
  statoIstanza;
  richiedentePF;
  richiedentePG;
  professionista;
  ricadenzaAAPP;
  ricadenzaRN2000;
  ricadenzaPopSeme;

  compensazione = [{ value: "NECESSARIA" }, { value: "NON NECESSARIA" }];

  formaGoverno;
  categoriaForestale;
  ubicazione;
  tipologiaTrasformazione;

  checkboxText = [
    `a) interessanti superfici inferiori ai 500 m2;`,
    `b) finalizzati alla conservazione del paesaggio o al ripristino degli habitat di interesse comunitario,
     se previste dagli strumenti di gestione   	o pianificazione di dettaglio vigenti;`,
    `c) volti al recupero a fini produttivi per l\'esercizio dell\'attività agro-pastorale svolte da coltivatori diretti
    e da imprenditori agricoli singoli 	o associati, di boschi di neoformazione insediatisi su ex coltivi,
     prati e pascoli abbandonati da non oltre trent\'anni;`,
    `d) per la realizzazione o adeguamento di opere di difesa dagli incendi,
     di opere pubbliche di difesa del suolo, se previsti dagli strumenti di 	gestione o pianificazione di dettaglio vigenti;`,
    //,`dbis) per la realizzazione di viabilità forestale in aree non servite.`
  ];

  removingField = [
    "richiedentePF",
    "richiedentePG",
    "professionista",
    "ricadenzaAAPP",
    "ricadenzaRN2000",
    "ricadenzaPopSeme",
  ];

  showArhive = false;
  selectSingleRecord = false;
  searchResult;
  sortedColumn = "numeroIstanza";
  tableData = [];
  tableHeaders: TableHeader[];
  tableHeadersTrasf: TableHeader[] = [
    { field: "tipologiaIstanza", header: "Tipologia di istanza" },
    { field: "annoIstanza", header: "Anno Istanza" },
    { field: "numeroIstanza", header: "Numero Istanza" },
    { field: "statoIstanza", header: "Stato Istanza" },
    { field: "dataPresentazione", header: "Data Presentazione" },
    { field: "richiedente", header: "Richiedente" },
    { field: "comune", header: "Comune" },
    { field: "areeProtette", header: "Ricadenza in Aree Protette" },
    { field: "natura2000", header: "Ricadenza in Rete Natura 2000" },
    // {field: 'populamenti', header: 'Popolamenti'},
    {
      field: "vincIdrogeologico",
      header: "Ricadenza in Vincolo Idrogeologico",
    },
    { field: "compensazione", header: "Compensazione" },
    { field: "euro", header: "Euro" },
  ];
  tableHeadersVinc: TableHeader[] = [
    { field: "numeroIstanza", header: "Numero istanza" },
    { field: "dataInvio", header: "Data invio" },
    { field: "competenza", header: "Competenza" },
    { field: "richiedente", header: "Richiedente" },
    { field: "comune", header: "Comune" },
    { field: "statoIstanza", header: "Stato" },
    { field: "statoCauzione", header: "Ricevute di versamento" },
    { field: "varianteProroga", header: "Con varianti/proroghe" },
    { field: "rimboschimento", header: "Rimboschimento" },
  ];
  tableHeadersTagli: TableHeader[] = [
    { field: "nrIstanza", header: "Numero istanza" },
    { field: "dataInserimento", header: "Data inserimento" },
    { field: "descrizioneTipoIstanza", header: "Tipo Istanza" },
    { field: "descrizioneIntervento", header: "Descrizione" },
    { field: "tipoIntervento", header: "Tipo Intervento" },
    { field: "richiedente", header: "Richiedente" },
    { field: "comune", header: "Comune" },
    { field: "statoIstanza", header: "Stato" },
  ];
  paging: any;
  totalCount: number;
  searchResultCount: number;
  tipoIstanza =
    parseInt(sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE)) || 1;
  lastSearchStr: string;
  lastDowloadStr: string;
  page = 1;
  limit = 5;
  sort = "-numeroIstanza";
  paginationParamsString: string =
    sessionStorage.getItem("paramsString") === null
      ? undefined
      : sessionStorage.getItem("paramsString");

  tipoAccreditamento: string;
  isIstanzaTagli: boolean = false;
  ambitoInstanza: AmbitoInstanza[] = CONST.AMBITO_OPZIONE;
  tipoIstanzaTaglio;
  categorieInterventoSelvicolturale;
  proprieta;
  tipoInterventoSelvicolturale;
  statoInterventoSelvicolturale;
  tipoRichiedente: TipoRichiedente[];
  utilizzatorePF: Utilizzatore;
  utilizzatorePG: Utilizzatore;
  sportello;
  isUtenteSportello = false;
  ricadenzaPFA;
  interventoCompensativo;
  utilizzatoreNonDefinito;

  isEnviromentProd: boolean;
  showRetrunToHome: boolean = false;

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    private fb: FormBuilder,
    private datePipe: DatePipe,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.isEnviromentProd =
      environment.beServerPrefix == "https://idf.sistemapiemonte.it";
    //this.checkLocalStorige();
    this.forestaliService
      .getAdpforByTipoIstanzaId(this.tipoIstanza.toString())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: UserChoiceModel) => {
        console.log({ res });
        this.tipoIstanza = res.tipoIstanzaId;
        this.tipoAccreditamento = res.fkTipoAccreditamento;
        if (res.fkProfilo === 8) {
          this.showRetrunToHome = true;
        }

        if (this.tipoAccreditamento === TipoAccreditamento.SPORTELLO) {
          this.isUtenteSportello = true;
          this.setSportello();
        }
        this.filterStatoIstanza();
      });

    this.initForm();
    this.filterTipologiaIstanza();
    this.filterTipoIstanzaTaglio();
    this.filterCategorieInterventoSelvicolturale();
    this.filterProprieta();
    let item = JSON.parse(sessionStorage.getItem("searchForm"));
    if (item) {
      this.searchForm.patchValue(item);
      if(item.comune != null){
        this.searchForm.get("comune").enable();
      }
      if(item.sezione != null){
        this.searchForm.get("sezione").enable();
      }
      if(item.foglio != null){
        this.searchForm.get("foglio").enable();
      }
      if(item.particella != null){
        this.searchForm.get("particella").enable();
      }      
    }
    /* this.forestaliService
      .getStep8Provincia()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ProvinciaModel[]) => {
        console.log(res);
        
        if (res.length === 1) {
          this.searchForm.get("provincia").patchValue(res[0]);
          this.searchForm.get("provincia").disable();
          this.onChangeDropdownValue("select", ["comune"]);
          this.forestaliService
            .getStep8ComuneByProvincia(res[0].istatProv)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe((result: ComuneModel[]) => {
              console.log("comune"+res);
              if (result.length === 1) {
                this.searchForm.get("comune").patchValue(result[0]);
                this.searchForm.get("comune").disable();
                this.onChangeDropdownValue("select", [
                  "sezione",
                  "foglio",
                  "particella",
                ]);
              }
            });
        }
      }); */
    
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  openGeeco() {
    let ids = [];
    for (let i in this.tableData) {
      ids.push(this.tableData[i].idIstanza);
    }
    let profilo: number = 0;
    switch (this.tipoIstanza) {
      case 1:
        profilo = 4;
        break;
      case 2:
        profilo = 24;
        break;
      case 4:
        profilo = 18;
        break;
      case 5:
        profilo = 18;
        break;
    }

    console.log("Antes de entar ala rsponse : ");
    console.log(
      "con subcrip",
      this.forestaliService
        .getCartograficoPointsUrl(profilo, ids)
        .subscribe((response: any) => {
          console.log("Response: ", response["geecourl"]);
          console.log("tipoIstanza: " + this.tipoIstanza);
          console.log("profilo: " + profilo);
          ///this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(response['geecourl']);
          /* if (window.location.href.indexOf("http://localhost:") == -1)           
          window.location.href = response["geecourl"];
          else {
            //this.showGeeco = true;           
          }*/
          window.open(response["geecourl"], "_blank");
        })
    );
    return false;
  }

  private initForm() {
    this.searchForm = this.fb.group({
      tipologiaIstanza: null,
      numeroIstanza: [
        null,
        [
          Validators.maxLength(30),
          Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
        ],
      ],
      statoIstanza: [{ value: null, disabled: false }],
      annoIstanza: [null, Validators.pattern(CONST.PATTERN_YEAR)],
      dataPresentazioneDa: null,
      dataPresentazioneA: null,

      provincia: [null, [Validators.maxLength(50)]],
      comune: [{ value: null, disabled: true }, [Validators.maxLength(50)]],
      sezione: [{ value: null, disabled: true }, [Validators.maxLength(2)]],
      foglio: [{ value: null, disabled: true }, [Validators.maxLength(5)]],
      particella: [{ value: null, disabled: true }, [Validators.maxLength(10)]],

      richiedentePF: null,
      richiedentePG: null,
      professionista: null,
      ricadenzaAAPP: null,
      ricadenzaRN2000: null,
      ricadenzaPopSeme: null,
      ricadenzaVincIdro: null,
      ricadenzaPFA: null,

      compensazione: "",
      flgA: null,
      flgB: null,
      flgC: null,
      flgD: null,
      flgDbis: null,

      formaGoverno: null,
      categoriaForestale: null,
      ubicazione: null,
      tipologiaTrasformazione: null,

      calcoloEconomicoCompensiazioneDa: [
        { value: null, disabled: false },
        Validators.pattern(CONST.PATTERN_MONETARY),
      ],
      calcoloEconomicoCompensiazioneA: [
        { value: null, disabled: false },
        Validators.pattern(CONST.PATTERN_MONETARY),
      ],
      //vincolo
      annoAutorizzazione: [null, Validators.pattern(CONST.PATTERN_YEAR)],
      numAutorizzazione: [null, [Validators.maxLength(30)]],
      dataScadenzaProvDa: null,
      dataScadenzaProvA: null,
      dataConclusioneIntervDa: null,
      dataConclusioneIntervA: null,
      varianti: "",
      proroghe: "",
      cauzione: "",
      compensazioneVincolo: "",

      //tagli
      tipoIstanzaTaglio: null,
      descrizione: [null, [Validators.minLength(3)]],
      categoriaInterventoSelvicolturale: null,
      proprieta: null,
      tipoInterventoSelvicolturale: null,
      statoInterventoSelvicolturale: null,
      utilizzatorePF: null,
      utilizzatorePG: null,
      tipoRichiedente: null,
      sportello: null,
      interventoCompensativo: null,
      utilizzatoreNonDefinito: null,
    });
  }

  goToHome() {
    this.homeNavigationEmitter.emit();
  }

  resetForm() {
    this.searchForm.reset();
    this.forestaliService.clearSearchStorage();

    if (this.isUtenteSportello) {
      this.setSportello();
    }
  }

  tipologiaIstanzaChange() {
    this.tipoIstanza =
      this.searchForm.get("tipologiaIstanza").value.idTipoIstanza;
    this.checkIstanzaTagli();
  }

  checkIstanzaTagli() {
    this.isIstanzaTagli = this.tipoIstanza === 2 || this.tipoIstanza === 3;
  }

  getIdTipoIstanza() {
    if (!this.tipoIstanza) {
      this.tipologiaIstanzaChange();
    }
    return this.tipoIstanza;
  }

  onChangeDropdownValue(modification, fields) {
    if (fields[0] === "comune") {
      if (modification === "clear") {
        this.searchForm.get("provincia").reset();
      }
      this.searchForm.get("comune").reset();
      this.onChangeDropdownValue("clear", ["sezione", "foglio", "particella"]);
    } else if (fields[0] === "sezione" && modification === "clear") {
      this.searchForm.get("comune").reset();
    }

    fields.forEach((element) => {
      this.searchForm.get(element).reset();
      if (modification === "select") {
        this.searchForm.get(element).enable();
      }
      if (modification === "clear") {
        this.searchForm.get(element).disable();
      }
    });
  }

  cleanFields(fields: string[]) {
    fields.forEach((element) => {
      this.searchForm.get(element).patchValue(null);
    });
  }

  filterTipologiaIstanza() {
    this.forestaliService
      .getTipologiaIstanzeAttive()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        const idAmbiti = res.map((e) => e.idTipoIstanza);
        this.tipologiaIstanza = [];
        this.ambitoInstanza.forEach((e) => {
          if (idAmbiti.includes(e.idAmbitoIstanza)) {
            /*  !this.isEnviromentProd ||
              (e.idAmbitoIstanza != 2 && e.idAmbitoIstanza != 5) */
            if (!this.isEnviromentProd || e.idAmbitoIstanza != 5) {
              this.tipologiaIstanza.push({
                idTipoIstanza: e.idAmbitoIstanza,
                descrDettTipoIstanza: e.descrAmbitoIstanza,
              });
            }
          }
        });
        this.tipologiaIstanza.sort((a, b) =>
          a.idTipoIstanza > b.idTipoIstanza ? 1 : -1
        );
        let val = res.filter(
          (item) => item.idTipoIstanza == this.tipoIstanza
        )[0];
        this.searchForm.get("tipologiaIstanza").patchValue(val);
        this.checkIstanzaTagli();
      });
  }

  filterTipoIstanzaTaglio() {
    this.forestaliService.getTipologiaIstanzeAmbito(2).subscribe((res) => {
      this.tipoIstanzaTaglio = res;
    });
  }

  filterStatoIstanza() {
    if (this.isUtenteSportello) {
      this.tagliService
        .getSportelloSeaarchStatiIstanza()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.statoIstanza = res;
        });
    } else {
      this.forestaliService
        .getStep8StatiIstanza()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.statoIstanza = res;
        });
    }
  }

  filterCategorieInterventoSelvicolturale() {
    this.tagliService.getCategorieSelvicolturali().subscribe((res) => {
      this.categorieInterventoSelvicolturale = res;
    });
  }

  filterProprieta() {
    this.tagliService.getProprietaSelvicolturali().subscribe((res) => {
      this.proprieta = res;
    });
  }

  filterGovernoSelv() {
    this.tagliService
      .getGoverni()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.formaGoverno = res;
      });
  }

  filterTipiInterventoSelv() {
    let gov = this.searchForm.get("formaGoverno").value;
    if (gov) {
      this.tagliService
        .getTipiInterventoGov(8, gov.idGoverno)
        .subscribe((res) => {
          this.tipoInterventoSelvicolturale = res;
        });
    } else {
      this.tagliService.getTipiIntervento(8).subscribe((res) => {
        this.tipoInterventoSelvicolturale = res;
      });
    }
  }

  changeGovernoSelv(event) {
    this.tipoInterventoSelvicolturale = null;
    this.searchForm.get("tipoInterventoSelvicolturale").setValue(null);
  }
  clearGovernoSelv(event) {
    this.searchForm.get("formaGoverno").setValue(null);
  }

  filterStatoInterventoSelv() {
    this.tagliService.getStatiIntervento().subscribe((res) => {
      this.statoInterventoSelvicolturale = res;
    });
  }

  filterTipoRichiedente() {
    this.tagliService.getTipiRichiedente().subscribe((res) => {
      this.tipoRichiedente = res;
    });
  }
  filterUtilizzatorePF(event) {
    this.tagliService.getUtilizzatoriPF(event.query).subscribe((res) => {
      this.utilizzatorePF = res;
    });
  }
  filterUtilizzatorePG(event) {
    this.tagliService.getUtilizzatoriPG(event.query).subscribe((res) => {
      this.utilizzatorePG = res;
    });
  }

  filterTecniciForestali(event) {
    this.tagliService.getTecniciForestali(event.query).subscribe((res) => {
      this.professionista = res;
    });
  }

  filterCategoriaForestaleTagli(event) {
    this.tagliService
      .getCategorieForestali()
      .pipe(
        map((usersArr) =>
          usersArr.map((user) => ({
            ...user,
            field: `${user.codiceAmministrativo} - ${user.descrizione}`,
          }))
        )
      )
      .subscribe((res) => {
        this.categoriaForestale = res;
      });
  }

  setSportello() {
    let sp: any = JSON.parse(
      sessionStorage.getItem(CONST.SPORTELLO_PG_KEY_STORE)
    );
    let sportelloId = sp.idSoggetto;

    this.tagliService.getSportelloCorrente().subscribe((res) => {
      this.sportello = [
        {
          ...res,
          field: `${res.codiceFiscale ? res.codiceFiscale + " - " : ""} ${
            res.denominazione
          }`,
        },
      ];
      this.searchForm.get("sportello").setValue(this.sportello[0]);
    });
  }

  filterSportello(event) {
    this.tagliService
      .getSoggettiSportello()
      .pipe(
        map((usersArr) =>
          usersArr.map((user) => ({
            ...user,
            field: `${user.codiceFiscale} - ${user.denominazione}`,
          }))
        )
      )
      .subscribe((res) => {
        this.sportello = res;
      });
  }

  filterProvincia(event) {
    this.forestaliService
      .getStep8Provincia()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ProvinciaModel[]) => {
        this.provincia = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Provincia"
        );
      });
  }
  getStep8Provincia() {
    return this.searchForm.get("provincia").value.istatProv;
  }
  filterComune(event) {
    this.forestaliService
      .getStep8ComuneByProvincia(this.getStep8Provincia())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ComuneModel[]) => {
        this.comune = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Comune"
        );
      });
  }
  getStep8Comune() {
    let comuneVal = this.searchForm.get("comune").value;
    return comuneVal ? comuneVal.idComune : null;
  }
  getStep8Sezione() {
    let sezioneVal = this.searchForm.get("sezione").value;
    return sezioneVal ? sezioneVal.value : null;
  }
  getStep8Foglio() {
    let foglioVal = this.searchForm.get("foglio").value;
    return foglioVal ? foglioVal.value : null;
  }

  filterSezione(event) {
    this.forestaliService
      .getDatiCatastiSezioneByTipoIstanza(
        this.getStep8Comune(),
        this.getIdTipoIstanza()
      )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.sezione = this.forestaliService.autocompleteFilter(
          event,
          res,
          "SezioneRicercaComune"
        );
      });
  }
  filterFoglio(event) {
    if (this.getStep8Comune() && this.getStep8Sezione()) {
      this.forestaliService
        .getDatiCatastiFoglioByTipoIstanza(
          this.getStep8Comune(),
          this.getStep8Sezione(),
          this.getIdTipoIstanza()
        )
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.foglio = this.forestaliService.autocompleteFilter(
            event,
            res,
            "FoglioRicercaComune"
          );
        });
    } else {
      this.foglio = this.forestaliService.autocompleteFilter(
        event,
        [],
        "FoglioComune"
      );
    }
  }
  filterParticella(event) {
    if (
      this.getStep8Comune() &&
      this.getStep8Sezione() &&
      this.getStep8Foglio()
    ) {
      this.forestaliService
        .getDatiCatastiParticellaByTipoIstanza(
          this.getStep8Comune(),
          this.getStep8Sezione(),
          this.getStep8Foglio(),
          this.getIdTipoIstanza()
        )
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.particella = this.forestaliService.autocompleteFilter(
            event,
            res,
            "ParticellaRicercaComune"
          );
        });
    } else {
      this.particella = this.forestaliService.autocompleteFilter(
        event,
        [],
        "ParticellaComune"
      );
    }
  }

  filterRichiedentePF(event) {
    this.forestaliService
      .getStep8RichiedentePF(this.getIdTipoIstanza())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.richiedentePF = this.forestaliService.autocompleteFilter(
          event,
          res,
          "RichiedentePF"
        );
      });
  }

  filterRichiedentePG(event) {
    this.forestaliService
      .getStep8RichiedentePG(this.getIdTipoIstanza())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.richiedentePG = this.forestaliService.autocompleteFilter(
          event,
          res,
          "RichiedentePG"
        );
      });
  }

  filterRichiedenteTagliPF(event) {
    this.tagliService
      .getStep8RichiedentePF(event.query)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.richiedentePF = res; //this.forestaliService.autocompleteFilter(event, res, 'RichiedentePF');
      });
  }

  filterRichiedenteTagliPG(event) {
    this.tagliService
      .getStep8RichiedentePG(event.query)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.richiedentePG = res; //this.forestaliService.autocompleteFilter(event, res, 'RichiedentePG');
      });
  }

  filterProfessionista(event) {
    this.forestaliService
      .getStep8Professionista(this.getIdTipoIstanza())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.professionista = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Professionista"
        );
      });
  }

  filterRicadenzaAAPP(event) {
    this.forestaliService
      .getStep8RicadenzeAreeProtete()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.ricadenzaAAPP = this.forestaliService.autocompleteFilter(
          event,
          res,
          "AreeProtete"
        );
        this.addTuttaOption(this.ricadenzaAAPP);
      });
  }

  filterRicadenzaRN2000(event) {
    this.forestaliService
      .getStep8RicadenzeNature2000()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.ricadenzaRN2000 = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Nature2000"
        );
        this.addTuttaOption(this.ricadenzaRN2000);
      });
  }

  filterRicadenzaPopSeme(event) {
    this.forestaliService
      .getStep8RicadenzePopulamenti()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.ricadenzaPopSeme = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Populamenti"
        );
        this.addTuttaOption(this.ricadenzaPopSeme);
      });
  }

  updateNecessery() {
    if (
      this.searchForm.get("compensazione").value &&
      this.searchForm.get("compensazione").value["value"] === "NECESSARIA"
    ) {
      /* this.disableFields(['flgA', 'flgB', 'flgC', 'flgD', 'flgDbis']); */
      this.uncheck(["flgA", "flgB", "flgC", "flgD", "flgDbis"]);
    }
    /* else if (radioButton === 'NON NECESSARIA')
    { this.enableFields(['flgA', 'flgB', 'flgC', 'flgD', 'flgDbis']); } */
  }
  uncheck(fields: string[]) {
    fields.forEach((item, i) => {
      this.searchForm.get(item).patchValue(null);
      this.searchForm.get(item).updateValueAndValidity();
    });
  }
  disableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.searchForm.get(item).disable();
    });
  }
  enableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.searchForm.get(item).enable();
    });
  }

  filterFormaGoverno(event) {
    this.forestaliService
      .getStep8ParametroTrasfGoverno()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.formaGoverno = this.forestaliService.autocompleteFilter(
          event,
          res,
          "FormaGoverno"
        );
      });
  }
  filterCategoriaForestale(event) {
    this.forestaliService
      .getStep8ParametriTrasfCategoriaForestale()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.categoriaForestale = this.forestaliService.autocompleteFilter(
          event,
          res,
          "CategoriaForestale"
        );
      });
  }
  filterUbicazione(event) {
    this.forestaliService
      .getStep8ParametriTrasfUbicazione()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.ubicazione = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Ubicazione"
        );
      });
  }
  filterTipologiaTrasformazione(event) {
    this.forestaliService
      .getStep8ParametriTrasfTrasformazione()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.tipologiaTrasformazione = this.forestaliService.autocompleteFilter(
          event,
          res,
          "TipologiaTrasformazione"
        );
      });
  }

  checkFromToValitation(name: string, type: string) {
    // if (type === 'moneta') { this.calcoloEconomicoCompensiazioneValidation = true; }
    if (
      this.searchForm.get(name + "A").value &&
      this.searchForm.get(name + "Da").value
    ) {
      let validation =
        this.searchForm.get(name + "A").value >=
        this.searchForm.get(name + "Da").value;
      // if (type === 'moneta') { this.calcoloEconomicoCompensiazioneValidation = false; }
      if (type === "date" && name == "dataPresentazione") {
        this.dataPresentazioneValidation = validation;
      }
      if (type === "date" && name == "dataConclusioneInterv") {
        this.dataConclusioneIntervValidation = validation;
      }
      if (type === "date" && name == "dataScadenzaProv") {
        this.dataScadenzaProvValidation = validation;
      }
    }
  }

  downloadExcel() {
    console.log(this.totalCount);
    console.log(this.lastSearchStr);
    this.forestaliService
      .downloadExcel(this.lastDowloadStr)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          let contentDisposition = response.headers.get("content-disposition");
          let filename = contentDisposition
            .split(";")[1]
            .split("filename")[1]
            .split("=")[1];
          fileSaver.saveAs(response.body, filename);
        }
      });
  }

  ricerca() {
    let prefixSearchString =
      "tipoIstanza=" +
      this.tipoIstanza +
      "&page=" +
      this.page +
      "&limit=" +
      this.limit +
      "&sort=" +
      this.sort;

    //UTILIZZATO PER IL DOWNLOAD EXCELL DI TUTTE LE ISTANZE
    let prefixDownloadString =
      "tipoIstanza=" +
      this.tipoIstanza +
      "&page=" +
      this.page +
      "&limit=" +
      this.totalCount +
      "&sort=" +
      this.sort;

    let searchString: string;
    if (this.paginationParamsString === undefined) {
      searchString =
        (this.searchForm.get("numeroIstanza").value
          ? "&numIstanza=" + this.searchForm.get("numeroIstanza").value
          : "") +
        (this.searchForm.get("statoIstanza").value
          ? "&statoIstanza=" +
            this.searchForm.get("statoIstanza").value.idStatoIstanza
          : "") +
        (this.searchForm.get("annoIstanza").value
          ? "&annoIstanza=" + this.searchForm.get("annoIstanza").value
          : "") +
        (this.searchForm.get("dataPresentazioneDa").value
          ? "&dataPresDa=" +
            this.datePipe.transform(
              new Date(this.searchForm.get("dataPresentazioneDa").value),
              "yyyy-MM-dd"
            )
          : "") +
        (this.searchForm.get("dataPresentazioneA").value
          ? "&dataPresA=" +
            this.datePipe.transform(
              new Date(this.searchForm.get("dataPresentazioneA").value),
              "yyyy-MM-dd"
            )
          : "") +
        (this.searchForm.get("provincia").value
          ? "&prov=" + this.searchForm.get("provincia").value.istatProv
          : "") +
        (this.searchForm.get("comune").value
          ? "&comune=" + this.searchForm.get("comune").value.idComune
          : "") +
        (this.searchForm.get("sezione").value
          ? "&sezione=" + this.searchForm.get("sezione").value.value
          : "") +
        (this.searchForm.get("foglio").value
          ? "&foglio=" + this.searchForm.get("foglio").value.value
          : "") +
        (this.searchForm.get("particella").value
          ? "&particella=" + this.searchForm.get("particella").value.value
          : "") +
        (this.searchForm.get("richiedentePF").value
          ? "&pf=" + this.searchForm.get("richiedentePF").value.idSoggetto
          : "") +
        (this.searchForm.get("richiedentePG").value
          ? "&pg=" + this.searchForm.get("richiedentePG").value.idSoggetto
          : "") +
        (this.searchForm.get("ricadenzaAAPP").value
          ? "&aapp=" + this.searchForm.get("ricadenzaAAPP").value
          : "") +
        (this.searchForm.get("ricadenzaRN2000").value
          ? "&rn2k=" + this.searchForm.get("ricadenzaRN2000").value
          : "") +
        (this.searchForm.get("ricadenzaPopSeme").value
          ? "&popSeme=" + this.searchForm.get("ricadenzaPopSeme").value
          : "");

      if (this.tipoIstanza == 1) {
        searchString +=
          (this.searchForm.get("professionista").value
            ? "&prof=" + this.searchForm.get("professionista").value.idSoggetto
            : "") +
          (this.searchForm.get("ricadenzaVincIdro").value
            ? "&vincIdro=" + this.searchForm.get("ricadenzaVincIdro").value
            : "") +
          (this.searchForm.get("compensazione").value
            ? "&compNec=" +
              (this.searchForm.get("compensazione").value.value === "NECESSARIA"
                ? "true"
                : "false")
            : "") +
          (this.searchForm.get("flgA").value
            ? "&compO1=" + this.searchForm.get("flgA").value
            : "") +
          (this.searchForm.get("flgB").value
            ? "&compO2=" + this.searchForm.get("flgB").value
            : "") +
          (this.searchForm.get("flgC").value
            ? "&compO3=" + this.searchForm.get("flgC").value
            : "") +
          (this.searchForm.get("flgD").value
            ? "&compO4=" + this.searchForm.get("flgD").value
            : "") +
          (this.searchForm.get("flgDbis").value
            ? "&compO5=" + this.searchForm.get("flgDbis").value
            : "") +
          (this.searchForm.get("formaGoverno").value
            ? "&govForm=" +
              this.searchForm.get("formaGoverno").value.fkTipoParametroTrasf
            : "") +
          (this.searchForm.get("categoriaForestale").value
            ? "&catFor=" +
              this.searchForm.get("categoriaForestale").value
                .fkTipoParametroTrasf
            : "") +
          (this.searchForm.get("ubicazione").value
            ? "&ubicazione=" +
              this.searchForm.get("ubicazione").value.fkTipoParametroTrasf
            : "") +
          (this.searchForm.get("tipologiaTrasformazione").value
            ? "&tipTrasf=" +
              this.searchForm.get("tipologiaTrasformazione").value
                .fkTipoParametroTrasf
            : "") +
          (this.searchForm.get("calcoloEconomicoCompensiazioneDa").value
            ? "&calcEconDa=" +
              this.searchForm.get("calcoloEconomicoCompensiazioneDa").value
            : "") +
          (this.searchForm.get("calcoloEconomicoCompensiazioneA").value
            ? "&calcEconA=" +
              this.searchForm.get("calcoloEconomicoCompensiazioneA").value
            : "");
      } else if (this.tipoIstanza == 4 || this.tipoIstanza == 5) {
        searchString +=
          (this.searchForm.get("annoAutorizzazione").value
            ? "&annoAutorizzazione=" +
              this.searchForm.get("annoAutorizzazione").value
            : "") +
          (this.searchForm.get("numAutorizzazione").value
            ? "&numAutorizzazione=" +
              this.searchForm.get("numAutorizzazione").value
            : "") +
          (this.searchForm.get("dataScadenzaProvDa").value
            ? "&dataScadenzaProvDa=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataScadenzaProvDa").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("dataScadenzaProvA").value
            ? "&dataScadenzaProvA=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataScadenzaProvA").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("dataConclusioneIntervDa").value
            ? "&dataConclusioneIntervDa=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataConclusioneIntervDa").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("dataConclusioneIntervA").value
            ? "&dataConclusioneIntervA=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataConclusioneIntervA").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("varianti").value
            ? "&varianti=" + this.searchForm.get("varianti").value
            : "") +
          (this.searchForm.get("proroghe").value
            ? "&proroghe=" + this.searchForm.get("proroghe").value
            : "") +
          (this.searchForm.get("compensazioneVincolo").value
            ? "&compensazioneVincolo=" +
              this.searchForm.get("compensazioneVincolo").value
            : "") +
          (this.searchForm.get("cauzione").value
            ? "&cauzione=" + this.searchForm.get("cauzione").value
            : "");
      } else if (this.isIstanzaTagli) {
        searchString +=
          (this.searchForm.get("tipoIstanzaTaglio").value
            ? "&tipoIstTaglio=" +
              this.searchForm.get("tipoIstanzaTaglio").value.idTipoIstanza
            : "") +
          (this.searchForm.get("descrizione").value
            ? "&descr=" + this.searchForm.get("descrizione").value
            : "") +
          (this.searchForm.get("annoAutorizzazione").value
            ? "&annoAutorizzazione=" +
              this.searchForm.get("annoAutorizzazione").value
            : "") +
          (this.searchForm.get("numAutorizzazione").value
            ? "&numAutorizzazione=" +
              this.searchForm.get("numAutorizzazione").value
            : "") +
          (this.searchForm.get("dataScadenzaProvDa").value
            ? "&dataScadenzaProvDa=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataScadenzaProvDa").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("dataScadenzaProvA").value
            ? "&dataScadenzaProvA=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataScadenzaProvA").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("dataConclusioneIntervDa").value
            ? "&dataConclusioneIntervDa=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataConclusioneIntervDa").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("dataConclusioneIntervA").value
            ? "&dataConclusioneIntervA=" +
              this.datePipe.transform(
                new Date(this.searchForm.get("dataConclusioneIntervA").value),
                "yyyy-MM-dd"
              )
            : "") +
          (this.searchForm.get("categoriaInterventoSelvicolturale").value
            ? "&catSelv=" +
              this.searchForm.get("categoriaInterventoSelvicolturale").value
                .idCategoriaSelvicolturale
            : "") +
          (this.searchForm.get("proprieta").value
            ? "&prop=" + this.searchForm.get("proprieta").value.idProprieta
            : "") +
          (this.searchForm.get("formaGoverno").value
            ? "&govForm=" + this.searchForm.get("formaGoverno").value.idGoverno
            : "") +
          (this.searchForm.get("tipoInterventoSelvicolturale").value
            ? "&tipoInt=" +
              this.searchForm.get("tipoInterventoSelvicolturale").value
                .idTipoIntervento
            : "") +
          (this.searchForm.get("statoInterventoSelvicolturale").value
            ? "&statoInt=" +
              this.searchForm.get("statoInterventoSelvicolturale").value
                .idStatoIntervento
            : "") +
          (this.searchForm.get("tipoRichiedente").value
            ? "&tipoRich=" +
              this.searchForm.get("tipoRichiedente").value.idTipoRichiedente
            : "") +
          (this.searchForm.get("utilizzatorePF").value
            ? "&upf=" + this.searchForm.get("utilizzatorePF").value.idSoggetto
            : "") +
          (this.searchForm.get("utilizzatorePG").value
            ? "&upg=" + this.searchForm.get("utilizzatorePG").value.idSoggetto
            : "") +
          (this.searchForm.get("utilizzatoreNonDefinito").value
            ? "&und=" + this.searchForm.get("utilizzatoreNonDefinito").value
            : "") +
          (this.searchForm.get("professionista").value
            ? "&prof=" + this.searchForm.get("professionista").value.idSoggetto
            : "") +
          (this.searchForm.get("ricadenzaPFA").value
            ? "&pfa=" + this.searchForm.get("ricadenzaPFA").value
            : "") +
          (this.searchForm.get("categoriaForestale").value
            ? "&catFor=" +
              this.searchForm.get("categoriaForestale").value
                .idCategoriaForestale
            : "") +
          (this.searchForm.get("varianti").value
            ? "&varianti=" + this.searchForm.get("varianti").value
            : "") +
          (this.searchForm.get("proroghe").value
            ? "&proroghe=" + this.searchForm.get("proroghe").value
            : "") +
          (this.searchForm.get("interventoCompensativo").value
            ? "&intComp=" + this.searchForm.get("interventoCompensativo").value
            : "") +
          (this.searchForm.get("sportello").value
            ? "&sport=" + this.searchForm.get("sportello").value.idSoggetto
            : "");
      }
    } else {
      searchString = this.paginationParamsString;
    }
    this.lastSearchStr = prefixSearchString + searchString;
    this.lastDowloadStr = prefixDownloadString + searchString;
    this.forestaliService
      .ricerca(this.lastSearchStr)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((searchResult) => {
        this.searchResult = searchResult;
        this.tableData = searchResult.content;
        this.totalCount = searchResult.totalElements;
        searchResult.tipoIstanza = this.tipoIstanza;
        this.paginationParamsString = searchString;
        sessionStorage.setItem("searchResults", JSON.stringify(searchResult));
        sessionStorage.setItem("paramsString", this.paginationParamsString);
        sessionStorage.setItem(
          "searchForm",
          JSON.stringify(this.searchForm.getRawValue())
        );
        this.searchResultCount = searchResult.content
          ? searchResult.content.length
          : 0;

        if (this.tipoIstanza == 1) {
          this.tableHeaders = this.tableHeadersTrasf;
          this.fillCompensazione(this.tableData);
          if (searchResult.content !== undefined) {
            this.fillTableColumns(searchResult);
          }
        } else if (this.isIstanzaTagli) {
          this.tableHeaders = this.tableHeadersTagli;
          if (searchResult.content !== undefined) {
            this.elaboraDatiRicercaTagli(searchResult);
          }
        } else {
          this.tableHeaders = this.tableHeadersVinc;
          if (searchResult.content !== undefined) {
            this.elaboraDatiRicercaVincolo(searchResult);
          }
        }
        this.showArhive = true;
        this.showAllSearchArhive();
      });
  }

  elaboraDatiRicercaVincolo(data: any) {
    data.content.forEach((element, index) => {
      if (!element.dataInvio) {
        element["dataInvio"] = {
          dayOfMonth: 0,
          month: "",
          year: 0,
        };
      }
    });

    this.tableData = [];
    data.content.forEach((element, index) => {
      //let richiedente =
      this.tableData.push({
        idIstanza: element.idIntervento,
        numeroIstanza: element.nrIstanzaForestale,
        dataInvio:
          element.dataInvio.dayOfMonth +
          "/" +
          element.dataInvio.monthValue +
          "/" +
          element.dataInvio.year,
        richiedente:
          (element.richiedente.nome ? element.richiedente.nome : "") +
          " " +
          (element.richiedente.cognome
            ? element.richiedente.cognome
            : element.richiedente.denominazione) +
          " " +
          element.richiedente.codiceFiscale +
          " " +
          (element.richiedente.partitaIva
            ? element.richiedente.partitaIva
            : ""),
        comune: element.comune.denominazioneComune,
        statoIstanza: element.stato,
        idStato: element.idStato,
        competenza: element.competenza,
        statoCauzione: element.statoCauzione,
        varianteProroga: element.varianteProroga,
        rimboschimento: element.rimboschimento,
      });
      if (element.dataInvio.year === 0) {
        this.tableData[index].dataInvio = "N/A";
      }
    });
  }

  elaboraDatiRicercaTagli(data: any) {
    data.content.forEach((element, index) => {
      if (!element.dataInserimento) {
        element["dataInserimento"] = {
          dayOfMonth: 0,
          month: "",
          year: 0,
        };
      }
    });

    this.tableData = [];
    data.content.forEach((element, index) => {
      this.tableData.push({
        idIstanza: element.idIntervento,
        nrIstanza: element.nrIstanza,
        dataInserimento:
          element.dataInserimento.dayOfMonth +
          "/" +
          element.dataInserimento.monthValue +
          "/" +
          element.dataInserimento.year,
        richiedente: element.richiedente,
        comune: element.comune.denominazioneComune,
        statoIstanza: element.stato,
        idStato: element.idStato,
        descrizioneTipoIstanza: element.descrizioneTipoIstanza,
        descrizioneIntervento: element.descrizioneIntervento,
        tipoIntervento: element.tipoIntervento,
      });
      if (element.dataInserimento.year === 0) {
        this.tableData[index].dataInserimento = "N/A";
      }
    });
  }

  fillCompensazione(tableData) {
    if (tableData) {
      for (let i in tableData) {
        if ("F" === tableData[i].compensazione) {
          tableData[i].compensazione = "FISICA";
        } else if ("M" === tableData[i].compensazione) {
          tableData[i].compensazione = "MONETARIA ";
        } else if ("N" === tableData[i].compensazione) {
          tableData[i].compensazione = "NON NECESSARIA";
        }
      }
    }
  }
  fillTableColumns(searchResult: any) {
    searchResult.content.forEach((element, index) => {
      let item = searchResult.content[index];
      this.tableData[index].tipologiaIstanza =
        item.tipologiaIstanza.descrDettTipoIstanza;
      this.tableData[index].idStato = item.statoIstanza.idStatoIstanza;
      this.tableData[index].statoIstanza = item.statoIstanza.descrStatoIstanza;
      this.tableData[index].richiedente =
        (item.richiedente.nome ? item.richiedente.nome : "") +
        " " +
        (item.richiedente.cognome
          ? item.richiedente.cognome
          : item.richiedente.denominazione) +
        " " +
        item.richiedente.codiceFiscale +
        " " +
        (item.richiedente.partitaIva ? item.richiedente.partitaIva : "");
      let comunes = item.comuneInfo;
      let comunesString = "";
      let comunesMap = {};
      if (comunes !== undefined) {
        comunes.forEach((element) => {
          if (!comunesMap[element.denominazioneComune]) {
            comunesString =
              comunesString +
              (comunesString.length == 0 ? "" : ", ") +
              element.denominazioneComune;
            comunesMap[element.denominazioneComune] = true;
          }
        });
      }
      this.tableData[index].comune = comunesString;
    });
  }
  showAllSearchArhive() {}

  returnToSearch() {
    this.page = 1;
    this.limit = 5;
    this.paginationParamsString = undefined;
    this.showArhive = !this.showArhive;
    this.selectSingleRecord = false;
    //this.forestaliService.clearSearchStorage();
    //this.resetForm();
  }

  returnToArhiveList() {
    this.selectSingleRecord = false;
  }

  getRowId() {}

  getUpdatedTable(event: any) {
    this.page = event.page;
    this.limit = event.limit;
    this.ricerca();
  }

  editRow(index: any) {
    this.editRowEmitter.emit(index);
    const viewMode: boolean = true;
    const boModifica: boolean = true;
    if (this.tipoIstanza == 1) {
      this.router.navigate([
        "modifica/" + index,
        { viewMode: viewMode, boModifica: boModifica },
      ]);
    } else if (this.tipoIstanza == 4 || this.tipoIstanza == 5) {
      this.router.navigate([
        "modifica-vid/" + index,
        { viewMode: viewMode, boModifica: boModifica },
      ]);
    } else if (this.isIstanzaTagli) {
      if (this.isUtenteSportello) {
        this.router.navigate([
          "modifica-tagli/" + index,
          { returnToSearchResult: true },
        ]);
      } else if (this.showRetrunToHome) {
        this.router.navigate([
          "modifica-tagli/" + index,
          {
            viewMode: viewMode,
            boModifica: boModifica,
            returnToSearchGestore: true,
          },
        ]);
      } else {
        this.router.navigate([
          "modifica-tagli/" + index,
          { viewMode: true, boModifica: boModifica },
        ]);
      }
    }
  }
  deleteRow(rowIndex) {
    this.deleteRowEmitter.emit(rowIndex);
  }

  viewRow(index) {
    const viewMode: boolean = true;
    const boModifica: boolean = true;
    if (this.tipoIstanza == 1) {
      this.router.navigate([
        "modifica/" + index,
        { viewMode: viewMode, boModifica: boModifica },
      ]);
    } else if (this.tipoIstanza == 4 || this.tipoIstanza == 5) {
      this.router.navigate([
        "modifica-vid/" + index,
        { viewMode: viewMode, boModifica: boModifica },
      ]);
    } else if (this.isIstanzaTagli) {
      if (this.isUtenteSportello) {
        this.router.navigate([
          "modifica-tagli/" + index,
          {
            viewMode: viewMode,
            boModifica: boModifica,
            returnToSearchResult: true,
          },
        ]);
      } else if (this.showRetrunToHome) {
        this.router.navigate([
          "modifica-tagli/" + index,
          {
            viewMode: viewMode,
            boModifica: boModifica,
            returnToSearchGestore: true,
          },
        ]);
      } else {
        this.router.navigate([
          "modifica-tagli/" + index,
          { viewMode: viewMode, boModifica: boModifica },
        ]);
      }
    }
  }

  checkLocalStorige() {
    let key = "searchResults";
    let item = JSON.parse(sessionStorage.getItem(key));
    if (item && item.content) {
      this.showArhive = true;
      this.tipoIstanza = item.tipoIstanza;
      this.tableData = item.content;
      this.totalCount = item.totalElements;
      if (item.content[0].idIstanza) {
        this.fillTableColumns(item);
      } else {
        this.elaboraDatiRicercaVincolo(item);
      }

      //this.forestaliService.clearSearchStorage();
    }
  }

  addTuttaOption(array: any): any {
    array.splice(0, 0, {
      codiceAmministrativo: "Tutta",
      field: "Tutta",
    });
  }

  isRicercaDisabled() {
    let res = this.searchForm.invalid || !this.dataPresentazioneValidation;
    if (this.tipoIstanza == 1) {
      return res || !this.calcoloEconomicoCompensiazioneValidation;
    } else if (this.tipoIstanza == 4 || this.tipoIstanza == 5) {
      return (
        res ||
        !this.dataConclusioneIntervValidation ||
        !this.dataScadenzaProvValidation
      );
    } else if (this.isIstanzaTagli) {
      return res; // todo: check if dasable
    }
  }

  clear(field) {
    this.searchForm.get(field).patchValue(null);
  }

  getField() {
    if (this.tipoIstanza == 1) {
      return "idIstanza";
    } else if (this.tipoIstanza == 4 || this.tipoIstanza == 5) {
      return "";
    } else if (this.isIstanzaTagli) {
      return "idIstanza";
    }
  }
}
