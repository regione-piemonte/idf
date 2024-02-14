/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  Input,
  Output,
  EventEmitter,
} from "@angular/core";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Subject, combineLatest } from "rxjs";
import { takeUntil } from "rxjs/operators";
import { SelectItem } from "primeng/api";

import { CONST } from "src/app/shared/constants";
import { ForestaliService } from "../../../services/forestali.service";
import { UserCompanyDataModel } from "src/app/shared/models/user-company-data.model";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { TagliService } from "./../../../services/tagli.service";
import { TipoRichiedente } from "./../../../../../shared/models/tipo-richiedente.model";
import { Utilizzatore } from "./../../../../../shared/models/utilizzatore.model";
import { TipoRichiedenteEnum } from "src/app/shared/models/tipo-richiedente.enum";
import { TagliStep2 } from "./tagli-step2.model";
import { SoggettoModel } from "src/app/shared/models/soggetto.model";
import { TipoUtilizzatoreEnum } from "src/app/shared/models/tipo-utilizzatore.enum";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";

@Component({
  selector: "step2-tagli",
  templateUrl: "./step2-tagli.component.html",
  styleUrls: ["./step2-tagli.component.css"],
})
export class Step2TagliComponent implements OnInit, OnDestroy {
  @Input() editMode: number | null;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Input() secondFormData: any;

  @Output() emitNextStep = new EventEmitter();
  @Output() emitSecondFormData = new EventEmitter<void>();

  userData: UserCompanyDataModel;
  companyData: UserCompanyDataModel;
  userCompanyData: UserCompanyDataModel;
  personaForm: FormGroup;
  companyForm: FormGroup;
  delegatoForm: FormGroup;
  personalOrCompanyForm: FormGroup;
  unsubscribe$ = new Subject<void>();
  cfOwner: string;
  loading: boolean = false;

  companies: UserCompanyDataModel[] = [];

  tipoAccreditamento = null; // Richiedente or Professionista or Sportello

  personaDatiOption: string = null; // RF, RG, PF or PG
  qui = false;
  selectedCompany = false;
  codiceFiscaleSelectItem: SelectItem[] = [];
  codiceFiscaleUtlizzatoreSelectItem: SelectItem[] = [];
  codiceFiscaleSONOIO: SelectItem[] = [];
  codiceFiscaleDelegati: SelectItem[] = [];
  ownerOfInstance: string = sessionStorage.getItem("codiceFiscale");
  companyInfoIVA: string = "";
  companyInfoDenominazione: string = "";
  numAlboForestale: string = "";
  onlyAzienda: boolean = false;
  personaFields = [
    "codiceFiscale",
    "cognome",
    "nome",
    "comune",
    "indirizzo",
    "civico",
    "cap",
    "nrTelefonico",
    "eMail",
  ];
  companyFields = [
    "codiceFiscale",
    "partitaIva",
    "denominazione",
    "comune",
    "cap",
    "indirizzo",
    "civico",
    "pec",
    "eMail",
    "nrTelefonico",
  ];

  idTIpoIstanza: string = sessionStorage.getItem(
    CONST.TIPO_ISTANZA_ID_KEY_STORE
  );

  tipiRichiedente: TipoRichiedente[] = [];
  tipoRichiedenteSelected: TipoRichiedente = {};
  idTipoRichiedente: number = null;
  utilizzatoriOptions: Utilizzatore[] = [];
  tipoUtilizzatoreSelected: number = 0;
  gestoriOptions = [];
  TipoAccreditamentoEnum: any = TipoAccreditamento;
  isUpdate: boolean = false;
  tipoGestoreSelected: any = null;
  gestoreId: number = null;
  isPGEntePubblico: boolean = false;
  isUtilizzatoreEntePubblico: boolean = false;

  utilizzatorePersonaForm: FormGroup;
  utilizzatoreCompanyForm: FormGroup;
  utilizzatorePersonalOrCompanyForm: FormGroup;
  idCategoriaIntervento: number;
  loadFromAnagrafica: boolean = false;
  isRichiedenteSelected: boolean = false;
  disabledOptions: boolean = true;
  disabledOptionsGestore: boolean = true;
  currentUtilizzatore: string;
  constructor(
    private fb: FormBuilder,
    private pfaService: PfaSampleService,
    private forestaliService: ForestaliService,
    private tagliService: TagliService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);

    this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
      this.isUpdate = (res.nextStep > 1 || this.secondFormData.tipoRichiedenteId != null);
    });

    // get home user and codici fiscali
    this.tipoAccreditamento = this.TipoAccreditamentoEnum.GESTORE
    this.utilizzatoriOptions = CONST.TIPO_UTILIZZATORE_OPTIONS.filter(
      (i) => i.id !== 2
    );
    this.idCategoriaIntervento = this.secondFormData.fkCategoriaIntervento;
    combineLatest(
      this.pfaService.getDettaglio(JSON.parse(localStorage.getItem("tabIndex"))),
      this.tagliService.getSoggettiGestori(),
      this.forestaliService.getDropdownCodiceFiscale()
    ).pipe(takeUntil(this.unsubscribe$))    
    .subscribe(([gestore,listaGestori,resultDropdownCodiceFiscale])=> {
      const list = listaGestori.filter(item =>gestore.gestori.includes(item.denominazione))
      this.gestoriOptions = list      
      if(list.length>1){        
        this.disabledOptionsGestore = false
        if(this.secondFormData.soggetto != null){
          this.tipoGestoreSelected = list.filter(el=> this.secondFormData.soggetto.idSoggetto === el.idSoggetto)[0];
          this.gestoreId = this.tipoGestoreSelected != null? this.tipoGestoreSelected.idSoggetto:null;
        }
      } else {
        this.tipoGestoreSelected = list[0]
        this.gestoreId = this.tipoGestoreSelected.idSoggetto;
      }
      
    },
      (e)=> console.log(e),
      ()=>{
        this.tipiRichiedente = [{
          idTipoRichiedente: 5,
          descrTipoRichiedente: "Gestore",
          mtdOrdinamento: 5,
        }];
        this.tipoRichiedenteSelected = this.tipiRichiedente[0]        
        this.idTipoRichiedente = this.tipoRichiedenteSelected.idTipoRichiedente
        this.isRichiedenteSelected = true;
        const soggetto = this.secondFormData.soggetto;
          if (
            soggetto.personaDatiOption === "RF" ||
            soggetto.personaDatiOption === "PF" ||
            soggetto.personaDatiOption === "SF"
          ) {
            this.codiceFiscaleSONOIO[0] = {
              value: soggetto.codiceFiscale,
              label: soggetto.codiceFiscale,
            };
            this.userData = soggetto;
          } else {
            this.companyData = soggetto;
          }

          this.cfOwner = soggetto.ownerCodiceFiscale;

          if(this.secondFormData.tipoUtilizzatore != "NO_DATA" && this.secondFormData.tipoUtilizzatore != "DA_INDIVIDUARE"){
            this.tipoUtilizzatoreSelected = this.utilizzatoriOptions.filter(opt => opt.label == this.secondFormData.tipoUtilizzatore)[0].id
            this.currentUtilizzatore = this.secondFormData.tipoUtilizzatore
            console.log(this.secondFormData.tipoUtilizzatore);
            
            console.log(this.tipoUtilizzatoreSelected);
            
            this.changeUtilizzatore(this.tipoUtilizzatoreSelected);
          }

        
      }
      )
    /* combineLatest(
      this.tagliService.getTipiRichiedente(),
      this.forestaliService.getDropdownCodiceFiscale()
    )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(([resultTipiRichiedente, resultDropdownCodiceFiscale]) => {
        this.tipiRichiedente = resultTipiRichiedente;

        resultDropdownCodiceFiscale.sort().forEach((element, index) => {
          if (
            element !== null &&
            (element === this.ownerOfInstance ||
              this.tipoAccreditamento == TipoAccreditamento.PROFESSIONISTA)
          )
            this.codiceFiscaleSelectItem.push({
              value: element,
              label: element,
            });
        });

        if (this.isUpdate) {
          const soggetto = this.secondFormData.soggetto;
          if (
            soggetto.personaDatiOption === "RF" ||
            soggetto.personaDatiOption === "PF" ||
            soggetto.personaDatiOption === "SF"
          ) {
            this.codiceFiscaleSONOIO[0] = {
              value: soggetto.codiceFiscale,
              label: soggetto.codiceFiscale,
            };
            this.userData = soggetto;
          } else {
            this.companyData = soggetto;
            this.entePubblicoChanged(soggetto.flgEntePubblico);
          }

          this.cfOwner = soggetto.ownerCodiceFiscale;
          this.optionUpdate(soggetto.personaDatiOption);

          this.tipoRichiedenteSelected = this.tipiRichiedente.find(
            (i) => i.idTipoRichiedente == this.secondFormData.tipoRichiedenteId
          );
          this.changeTipoRichiedente(this.tipoRichiedenteSelected, false);

          this.tipoUtilizzatoreSelected = CONST.TIPO_UTILIZZATORE_OPTIONS.find(
            (l) => l.label === this.secondFormData.tipoUtilizzatore
          ).id;
          this.changeUtilizzatore(this.tipoUtilizzatoreSelected);
        } else {
          const soggetto = this.secondFormData.soggetto;
          if (
            soggetto.personaDatiOption === "RF" ||
            soggetto.personaDatiOption === "PF" ||
            soggetto.personaDatiOption === "SF"
          ) {
            this.codiceFiscaleSONOIO[0] = {
              value: soggetto.codiceFiscale,
              label: soggetto.codiceFiscale,
            };
            this.cfOwner = soggetto.ownerCodiceFiscale;
          }
          const row = {
            idTipoRichiedente: 5,
            descrTipoRichiedente: "Gestore",
            ordinamento: 5,
          };
          this.secondFormData.soggetto.idSoggetto = 5;

          let selected = {}

          if(this.tipoAccreditamento == TipoAccreditamento.GESTORE ){
            selected = row
            this.disabledOptions = true;
          }
          console.log("sezione giusta");
          
          console.log(this.tipoAccreditamento);
          
          console.log((soggetto.personaDatiOption === "RF" || soggetto.personaDatiOption === "PF" ||  soggetto.personaDatiOption === "SF" ));
          console.log(selected);
          
          this.changeTipoRichiedente(selected, true);
        }
        console.log(this.isUpdate);
        this.checkDaIndividuare();
      }); */
  }

  initPersonalForm(personaData) {
    this.personaForm = this.fb.group({
      codiceFiscale: [
        {
          value: personaData ? personaData.codiceFiscale : "",
          disabled: this.personaDatiOption === "RF" ? true : false,
        },
        [Validators.required, Validators.maxLength(16)],
      ],
      comune: [
        { value: personaData ? personaData.comune : "", disabled: false },
        Validators.required,
      ],
      cap: [
        { value: personaData ? personaData.cap : "", disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)],
      ],
      indirizzo: [
        { value: personaData ? personaData.indirizzo : "", disabled: false },
        [Validators.required, Validators.maxLength(50)],
      ],
      civico: [
        { value: personaData ? personaData.civico : "", disabled: false },
        [Validators.required, Validators.maxLength(20)],
      ],
      eMail: [
        { value: personaData ? personaData.eMail : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_MAIL),
          Validators.maxLength(50),
        ],
      ],
      nrTelefonico: [
        { value: personaData ? personaData.nrTelefonico : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
          Validators.maxLength(20),
        ],
      ],
      nome: [
        {
          value: personaData ? personaData.nome : "",
          disabled: this.personaDatiOption === "RF" ? true : false,
        },
        [Validators.required, Validators.maxLength(50)],
      ],
      cognome: [
        {
          value: personaData ? personaData.cognome : "",
          disabled: this.personaDatiOption === "RF" ? true : false,
        },
        [Validators.required, Validators.maxLength(100)],
      ],
    });
  }

  initUtilizzatorePersonalForm(personaData) {
    this.utilizzatorePersonaForm = this.fb.group({
      codiceFiscale: [
        {
          value: personaData ? personaData.codiceFiscale : "",
          disabled: false,
        },
        [Validators.required, Validators.maxLength(16)],
      ],
      comune: [
        { value: personaData ? personaData.comune : "", disabled: false },
        Validators.required,
      ],
      cap: [
        { value: personaData ? personaData.cap : "", disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)],
      ],
      indirizzo: [
        { value: personaData ? personaData.indirizzo : "", disabled: false },
        [Validators.required, Validators.maxLength(50)],
      ],
      civico: [
        { value: personaData ? personaData.civico : "", disabled: false },
        [Validators.required, Validators.maxLength(20)],
      ],
      eMail: [
        { value: personaData ? personaData.eMail : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_MAIL),
          Validators.maxLength(50),
        ],
      ],
      nrTelefonico: [
        { value: personaData ? personaData.nrTelefonico : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
          Validators.maxLength(20),
        ],
      ],
      nome: [
        { value: personaData ? personaData.nome : "", disabled: false },
        [Validators.required, Validators.maxLength(50)],
      ],
      cognome: [
        { value: personaData ? personaData.cognome : "", disabled: false },
        [Validators.required, Validators.maxLength(100)],
      ],
    });
  }

  initCompanyForm(companyData) {
    this.companyForm = this.fb.group({
      codiceFiscale: [
        {
          value: companyData ? companyData.codiceFiscale : "",
          disabled: this.loadFromAnagrafica,
        },
        [Validators.required, Validators.maxLength(16)],
      ],

      partitaIva: [
        {
          value: companyData ? companyData.partitaIva : "",
          disabled: this.loadFromAnagrafica,
        },
      ],
      //this.isPGEntePubblico ? [] : [Validators.required, Validators.maxLength(11)]   ],

      denominazione: [
        {
          value: companyData ? companyData.denominazione : "",
          disabled: this.loadFromAnagrafica,
        },
        [Validators.required, Validators.maxLength(200)],
      ],
      comune: [
        { value: companyData ? companyData.comune : "", disabled: false },
        Validators.required,
      ],
      cap: [
        { value: companyData ? companyData.cap : "", disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)],
      ],
      indirizzo: [
        { value: companyData ? companyData.indirizzo : "", disabled: false },
        [Validators.required, Validators.maxLength(50)],
      ],
      civico: [
        { value: companyData ? companyData.civico : "", disabled: false },
        [Validators.required, Validators.maxLength(20)],
      ],
      pec: [
        { value: companyData ? companyData.pec : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_MAIL),
          Validators.maxLength(50),
        ],
      ],
      eMail: [
        { value: companyData ? companyData.eMail : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_MAIL),
          Validators.maxLength(50),
        ],
      ],
      nrTelefonico: [
        { value: companyData ? companyData.nrTelefonico : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
          Validators.maxLength(20),
        ],
      ],
      numAlboForestale: [
        {
          value: companyData ? companyData.nrAlboForestale : "",
          disabled: true,
        },
      ],
    });
  }

  initUtilizzatoreCompanyForm(companyData) {
    console.log(companyData);
    
    this.utilizzatoreCompanyForm = this.fb.group({
      codiceFiscale: [
        {
          value: companyData ? companyData.codiceFiscale : "",
          disabled: false,
        },
        [Validators.required, Validators.maxLength(16)],
      ],

      partitaIva: [
        { value: companyData ? companyData.partitaIva : "", disabled: false },
      ],
      //companyData.flgEntePubblico ? [] : [Validators.required, Validators.maxLength(11)] ],

      denominazione: [
        {
          value: companyData ? companyData.denominazione : "",
          disabled: false,
        },
        [Validators.required, Validators.maxLength(200)],
      ],
      comune: [
        { value: companyData ? companyData.comune : "", disabled: false },
        Validators.required,
      ],
      cap: [
        { value: companyData ? companyData.cap : "", disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)],
      ],
      indirizzo: [
        { value: companyData ? companyData.indirizzo : "", disabled: false },
        [Validators.required, Validators.maxLength(50)],
      ],
      civico: [
        { value: companyData ? companyData.civico : "", disabled: false },
        [Validators.required, Validators.maxLength(20)],
      ],
      pec: [
        { value: companyData ? companyData.pec : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_MAIL),
          Validators.maxLength(50),
        ],
      ],
      eMail: [
        { value: companyData ? companyData.eMail : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_MAIL),
          Validators.maxLength(50),
        ],
      ],
      nrTelefonico: [
        { value: companyData ? companyData.nrTelefonico : "", disabled: false },
        [
          Validators.required,
          Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
          Validators.maxLength(20),
        ],
      ],

      soggettoTaif: {
        value: companyData ? companyData.soggettoTaif : undefined,
      },
      flgEntePubblico: [
        {
          value: companyData ? companyData.flgEntePubblico : "",
          disabled: false,
        },
      ],
      taif: [{ value: companyData ? companyData.taif : "", disabled: false }],
      numAlboForestale: [
        {
          value: companyData ? companyData.numAlboForestale : "",
          disabled: true,
        },
      ],
    });
  }

  initDelegatiForm(delegato: string) {
    this.delegatoForm = this.fb.group({
      delegatoId: [{ value: delegato, disabled: false }],
    });
  }

  optionUpdate(personaDatiOption) {
    this.personaDatiOption = personaDatiOption;
    if (personaDatiOption === "RF") {
      this.forestaliService
        .getSoggettiIo()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          if (!this.userData) {
            this.codiceFiscaleSONOIO[0] = {
              value: res.codiceFiscale,
              label: res.codiceFiscale,
            };
            this.userData = res;
          }
          this.restartForm(personaDatiOption);
        });
    } else if (personaDatiOption === "RG") {
      this.codiceFiscaleSelectItem = [];
      this.initCompanyForm(this.companyData);
      this.forestaliService.getSoggettiIo().subscribe((res) => {
        if (!this.userData) {
          this.codiceFiscaleSONOIO[0] = {
            value: res.codiceFiscale,
            label: res.codiceFiscale,
          };
          this.userData = res;
        }
      });
    } else if (personaDatiOption === "PG") {
      this.codiceFiscaleDelegati = this.codiceFiscaleSelectItem;
      this.initCompanyForm(this.companyData);
    } else if (personaDatiOption === "SF") {
      this.restartForm(personaDatiOption);
    } else if (personaDatiOption === "SG") {
      this.codiceFiscaleDelegati = this.codiceFiscaleSelectItem;
      this.initCompanyForm(this.companyData);
    } else {
      this.restartForm(personaDatiOption);
    }
  }

  option(personaDatiOption) {
    this.personaDatiOption = personaDatiOption;
    if (personaDatiOption === "RF") {
      this.forestaliService
        .getSoggettiIo()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.codiceFiscaleSONOIO[0] = {
            value: res.codiceFiscale,
            label: res.codiceFiscale,
          };
          this.userData = res;
          this.restartForm(personaDatiOption);
        });
    } else if (personaDatiOption === "RG") {
      this.codiceFiscaleSelectItem = [];
      this.initCompanyForm({});
      this.forestaliService.getSoggettiIo().subscribe((res) => {
        this.codiceFiscaleSONOIO[0] = {
          value: res.codiceFiscale,
          label: res.codiceFiscale,
        };
        this.userData = res;
      });
    } else if (personaDatiOption === "PG") {
      this.codiceFiscaleDelegati = this.codiceFiscaleSelectItem;
      this.initCompanyForm(this.companyData);
    } else if (personaDatiOption === "SF") {
      this.restartForm(personaDatiOption);
    } else if (personaDatiOption === "SG") {
      this.codiceFiscaleDelegati = this.codiceFiscaleSelectItem;
      this.initCompanyForm(this.companyData);
    } else {
      this.restartForm(personaDatiOption);
    }
  }

  changeTipoRichiedente(opt?: TipoRichiedente, clearForm?: boolean) {
    this.tipoRichiedenteSelected = opt;
    this.idTipoRichiedente = opt.idTipoRichiedente;
    // caso 1 - richiedente
    // proprietario / acquirente / possessore
    switch (opt.idTipoRichiedente) {
      case TipoRichiedenteEnum.ACQUIRENTE:
      case TipoRichiedenteEnum.PROPRIETARIO:
      case TipoRichiedenteEnum.POSSESSORE:
        this.gestoriOptions = [];
        this.utilizzatoriOptions = CONST.TIPO_UTILIZZATORE_OPTIONS.filter(
          (i) => i.id !== 2
        );
        this.isRichiedenteSelected = true;
        this.checkDaIndividuare();
        if (clearForm) {
          this.personaDatiOption = null;
        }

        break;
      case TipoRichiedenteEnum.GESTORE:
        this.tagliService.getSoggettiGestori().subscribe((res) => {
          this.gestoriOptions = res;
          this.tipoGestoreSelected = res.find(
            (i) => i.idSoggetto === this.secondFormData.soggetto.idSoggetto
          );
          if (this.tipoGestoreSelected) {
            this.gestoreId = this.tipoGestoreSelected.idSoggetto;
          }
        });
        this.utilizzatoriOptions = CONST.TIPO_UTILIZZATORE_OPTIONS.filter(
          (i) => i.id !== 2
        );
        this.isPGEntePubblico = false;
        this.isRichiedenteSelected = false;
        this.initPersonalForm({});
        this.initCompanyForm({});
        this.checkDaIndividuare();
        break;
      case TipoRichiedenteEnum.UTILIZZATORE:
        this.utilizzatoriOptions = CONST.TIPO_UTILIZZATORE_OPTIONS.filter(
          (i) => i.id === 1
        );
        this.gestoriOptions = [];
        this.isPGEntePubblico = false;
        this.isRichiedenteSelected = true;
        if (clearForm) {
          this.personaDatiOption = null;
          this.initPersonalForm({});
          this.initCompanyForm({});
        }
        this.initUtilizzatorePersonalForm({});
        this.initUtilizzatoreCompanyForm({});
        this.changeUtilizzatore(1);

        break;
      default:
        this.utilizzatoriOptions = CONST.TIPO_UTILIZZATORE_OPTIONS.filter(
          (i) => i.id !== 2
        );
        this.checkDaIndividuare();
        break;
    }
  }

  entePubblicoChanged(isPubblico) {
    this.isPGEntePubblico = isPubblico;
    this.checkDaIndividuare();
  }

  checkDaIndividuare() {
    // check if enable "Da individuare"
    if (this.idCategoriaIntervento === 3 || this.isPGEntePubblico) {
      console.debug("====================================");
      console.debug(
        "da individuare (solo in caso di tipo di intervento selvicolturale = Noccioleto / Castagneto)"
      );
      console.debug("o nel caso di Richiedente = ENTE PUBBLICO)");
      console.debug("====================================");

      if (
        this.utilizzatoriOptions.findIndex((i) => i.id === 2) === -1 &&
        this.idTipoRichiedente !== TipoRichiedenteEnum.UTILIZZATORE
      ) {
        this.utilizzatoriOptions.push(
          CONST.TIPO_UTILIZZATORE_OPTIONS.find((i) => i.id === 2)
        );
      }
    } else {
      if (this.utilizzatoriOptions.findIndex((i) => i.id === 2) !== -1) {
        this.utilizzatoriOptions = this.utilizzatoriOptions.filter(
          (i) => i.id !== 2
        );
      }
    }
  }

  changeUtilizzatore(opt) {
    console.log(opt);
    this.tipoUtilizzatoreSelected = Number(opt);
    if (this.tipoUtilizzatoreSelected === 3) {
      if(this.secondFormData.utilizzatore == null){
        this.initUtilizzatorePersonalForm(null);
      } else if (this.secondFormData.tipoUtilizzatore != "DA_INDIVIDUARE" && this.secondFormData.utilizzatore.cognome != null && this.currentUtilizzatore != "IN_PROPRIO") {
        this.initUtilizzatorePersonalForm(this.secondFormData.utilizzatore);
      } else {
        this.initUtilizzatorePersonalForm(null);
      }
    }
    if (this.tipoUtilizzatoreSelected === 4) {
      if(this.secondFormData.utilizzatore == null){
        this.initUtilizzatoreCompanyForm(null);
      }else if (this.secondFormData.tipoUtilizzatore != "DA_INDIVIDUARE" && this.secondFormData.utilizzatore.denominazione != null && this.currentUtilizzatore != "IN_PROPRIO") {
        this.isUtilizzatoreEntePubblico = this.secondFormData.utilizzatore.flgEntePubblico
        this.initUtilizzatoreCompanyForm(this.secondFormData.utilizzatore);
      } else {
        this.initUtilizzatoreCompanyForm(null);
      }
    }
   /*  this.tipoUtilizzatoreSelected = Number(opt);
    if (this.tipoUtilizzatoreSelected === 3) {
      this.initUtilizzatorePersonalForm(this.secondFormData.utilizzatore ? this.secondFormData.utilizzatore : null);
    }
    if (this.tipoUtilizzatoreSelected === 4) {
      if(this.secondFormData.utilizzatore){
        this.isUtilizzatoreEntePubblico = this.secondFormData.utilizzatore.flgEntePubblico
      }
      this.initUtilizzatoreCompanyForm(this.secondFormData.utilizzatore ? this.secondFormData.utilizzatore : null);
    } */
  }

  changeGestore(opt) {
    this.tipoGestoreSelected = opt;
    this.gestoreId = opt.idSoggetto;
  }

  changeTipoPG(tipoPersonaGiuridica) {
    if (
      this.personaDatiOption === "RG" ||
      this.personaDatiOption === "PG" ||
      this.personaDatiOption === "SG"
    ) {
      this.isPGEntePubblico = tipoPersonaGiuridica === "EN";

      /* if (!this.boMode) {
        this.loading = true;
        this.codiceFiscaleSelectItem = [];
        this.forestaliService
          .getMyAziendePubblichePrivate(
            this.codiceFiscaleSONOIO[0]
              ? this.codiceFiscaleSONOIO[0].value
              : this.ownerOfInstance,
            this.isPGEntePubblico
          )
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((response) => {
            this.companies = response.body.sort((a, b) =>
              a.codiceFiscale > b.codiceFiscale ? 1 : -1
            );
            this.companies.forEach((element, index) => {
              if (element !== null) {
                this.codiceFiscaleSelectItem.push({
                  value: element.codiceFiscale,
                  label: element.codiceFiscale + " - " + element.denominazione,
                });
              }
            });
            this.loading = false;
          });
      } */
    }
    this.checkDaIndividuare();
  }

  changeTipoPGUtil(tipoPersonaGiuridica) {
    if (this.tipoUtilizzatoreSelected === 4) {
      this.isUtilizzatoreEntePubblico = tipoPersonaGiuridica === "EN";

     /*  if (!this.boMode) {
        this.loading = true;
        this.codiceFiscaleUtlizzatoreSelectItem = [];
        this.forestaliService
          .getMyAziendePubblichePrivate(
            this.codiceFiscaleSONOIO[0]
              ? this.codiceFiscaleSONOIO[0].value
              : this.ownerOfInstance,
            this.isUtilizzatoreEntePubblico
          )
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((response) => {
            this.companies = response.body;
            this.companies.forEach((element, index) => {
              if (element !== null) {
                this.codiceFiscaleUtlizzatoreSelectItem.push({
                  value: element.codiceFiscale,
                  label: element.codiceFiscale,
                });
              }
            });
            this.loading = false;
          });
      } */
    }
  }

  checkEditModePersonaOptions(result: UserCompanyDataModel) {
    if (result.cognome === undefined) {
      this.companyData = result;
      this.personaDatiOption = "RG";
      this.restartForm(this.personaDatiOption);
    } else if (result.cognome !== undefined) {
      this.userData = result;
      this.personaDatiOption = "RF";
      this.restartForm(this.personaDatiOption);
    }
  }

  restartForm(personaDatiOption) {
    switch (personaDatiOption) {
      case "RF":
        this.initPersonalForm(
          this.editMode
            ? this.userData
            : personaDatiOption === "RF"
            ? this.userData
            : null
        );
        this.codiceFiscaleSONOIO[0] = {
          value: this.userData.codiceFiscale,
          label: this.userData.codiceFiscale,
        };
        break;
      case "PF":
        this.initPersonalForm(
          this.editMode
            ? this.userData
            : personaDatiOption === "PF"
            ? this.userData
            : null
        );
        break;
      case "SF":
        this.initPersonalForm(
          this.editMode
            ? this.userData
            : personaDatiOption === "SF"
            ? this.userData
            : null
        );
        break;
      case "RG":
        if (this.editMode) {
          this.companies.push(this.companyData);
          if (this.companyData) {
            this.codiceFiscaleSelectItem.push({
              value: this.companyData.codiceFiscale,
              label: this.companyData.codiceFiscale,
            });
          }

          this.initCompanyForm(
            this.editMode
              ? this.companyData
              : personaDatiOption === "RG"
              ? this.companyData
              : null
          );
        } else {
          this.initCompanyForm(
            this.editMode
              ? this.companyData
              : personaDatiOption === "RG"
              ? this.companyData
              : null
          );
          this.companyForm.reset();
        }
        break;
      case "PG":
        this.initDelegatiForm(this.cfOwner);
        this.initCompanyForm(this.editMode ? this.companyData : null);
        break;
      case "SG":
        this.initDelegatiForm(this.cfOwner);
        this.initCompanyForm(this.editMode ? this.companyData : null);
        break;
    }
  }

  getCodiceFiscale() {}

  fillFormFields(event) {
    this.forestaliService
      .getfillFormFields(event.value)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: SoggettoModel) => {
        this.companyForm.reset();
        if (res) {
          this.companyForm.patchValue(res);
        }
      });
  }

  continue() {
    this.save(true);
  }

  save(nextStep?: boolean) {
    if (
      this.personaDatiOption === "RF" ||
      this.personaDatiOption === "PF" ||
      this.personaDatiOption === "SF"
    )
      // Persona fisica.
      this.personalOrCompanyForm = this.personaForm;

    if (
      this.personaDatiOption === "RG" ||
      this.personaDatiOption === "PG" ||
      this.personaDatiOption === "SG"
    )
      // Persona giuridica.
      this.personalOrCompanyForm = this.companyForm;
    if (this.idTipoRichiedente === TipoRichiedenteEnum.GESTORE) {
      if (this.idTipoRichiedente !== TipoRichiedenteEnum.GESTORE) {
        this.userCompanyData = this.personalOrCompanyForm.getRawValue();
        this.userCompanyData.personaDatiOption = this.personaDatiOption;

        if (this.personaDatiOption == "PG" && this.cfOwner) {
          this.userCompanyData.ownerCodiceFiscale = this.cfOwner;
        }

        this.codiceFiscaleSONOIO.length !== 0
          ? (this.userCompanyData.ownerCodiceFiscale =
              this.codiceFiscaleSONOIO[0].value)
          : (this.userCompanyData.ownerCodiceFiscale = this.ownerOfInstance);
        this.userCompanyData.flgEntePubblico = this.isPGEntePubblico;
      }

      // case utilizzatore
      let utilizzatore = {};
      switch (Number(this.tipoUtilizzatoreSelected)) {
        case 1:
          utilizzatore = { ...this.userCompanyData };
          console.log("UTILIZZATORE ", this.tipoUtilizzatoreSelected);

          break;
        case 2:
          break;
        case 3:
          utilizzatore = {
            ...this.utilizzatorePersonaForm.getRawValue(),
            idSoggetto: 0,
            flgEntePubblico:false
          };
          console.log("UTILIZZATORE ", this.tipoUtilizzatoreSelected);
          break;
        case 4:
          if (this.utilizzatoreCompanyForm.get("taif").value === true) {
            const soggettotaif =
              this.utilizzatoreCompanyForm.get("soggettoTaif").value;
            utilizzatore = {
              soggettoTaif: soggettotaif,
              taif: true,
              idSoggetto: 0,
            };
          } else {
            
              utilizzatore = {
                ...this.utilizzatoreCompanyForm.getRawValue(),
                idSoggetto: 0,
                flgEntePubblico:this.isUtilizzatoreEntePubblico
              }
              
            
            console.log("UTILIZZATORE ", this.tipoUtilizzatoreSelected);
          }
          break;
        default:
          break;
      }

      const step2Form: TagliStep2 = {
        tipoIstanzaId: Number(this.idTIpoIstanza),
        tipoAccreditamento: this.tipoAccreditamento,
        idIntervento: this.editMode,

        tipoRichiedenteId: this.idTipoRichiedente,
        soggetto: this.userCompanyData,

        gestoreId: this.gestoreId,

        tipoUtilizzatore: TipoUtilizzatoreEnum[this.tipoUtilizzatoreSelected],
        utilizzatore: utilizzatore,
      };

      if (this.isUpdate) {
        // PUT STEP 2
        this.tagliService
          .putStep2(step2Form, this.editMode)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((res) => nextStep ? this.emitNextStep.emit() : this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
            this.isUpdate = (res.nextStep > 1 || this.secondFormData.tipoRichiedenteId != null);
          }));
      } else {
        // POST STEP 2
        this.tagliService
          .postStep2(step2Form)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((res) => {
            nextStep ? this.emitNextStep.emit() : this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
              this.isUpdate = (res.nextStep > 1 || this.secondFormData.tipoRichiedenteId != null);
            });
          });
      }
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  enterCompanyData() {
    this.restartForm(this.personaDatiOption);
    this.loadFromAnagrafica = false;
    this.initCompanyForm({});
  }

  chooseCompany(el) {
    this.loadFromAnagrafica = true;
    if (this.personaDatiOption === "PG") {
      this.tagliService
        .getAzienda(el.event.value, el.isPubblic)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response) => {
          this.companies = response.body ? [response.body] : [];
          this.loading = false;
          this.fillFormForCodicaFiscale(el.event.value);
        });
    } else {
      this.fillFormForCodicaFiscale(el.event.value);
    }
  }

  chooseDelegato(event) {
    if (this.companyForm) {
      this.companyForm.reset();
    }
    this.cfOwner = event.value;
    this.fillAzienda(event);
  }

  fillAzienda(event) {
    this.loading = true;
    this.forestaliService
      .getMyAziende(event.value)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        this.codiceFiscaleSelectItem = [];
        this.companies = response.body;
        this.companies.forEach((element, index) => {
          if (element !== null) {
            this.codiceFiscaleSelectItem.push({
              value: element.codiceFiscale,
              label: element.codiceFiscale,
            });
          }
        });
        this.loading = false;
      });
  }

  fillFormForCodicaFiscale(codiceFiscale: string) {
    this.companyData = this.companies.find(
      (comp) => comp.codiceFiscale === codiceFiscale
    );
    if (this.companyData) {
      this.loadFromAnagrafica = true;
      this.companyInfoIVA = this.companyData.partitaIva;
      this.companyInfoDenominazione = this.companyData.denominazione;
      this.numAlboForestale = this.companyData.numAlboForestale;
      if (this.companyData.fkComune) {
        this.forestaliService
          .getComuniById(this.companyData.fkComune)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((resComune: ComuneModel) => {
            this.companyForm.get("comune").patchValue(resComune);
          });
      }
    }
    this.initCompanyForm(this.companyData);
  }

  isButtonDisabled() {
    //tipo richiedetne non selezionato o gestore non selezionato
    const a =
      this.tipoRichiedenteSelected.idTipoRichiedente == null ||
      (this.tipoRichiedenteSelected.idTipoRichiedente === 5 &&
        this.gestoreId === null);

    // tipo richiedente non gestore e PF not valid
    const b =
      this.tipoRichiedenteSelected.idTipoRichiedente !== 5 &&
      (this.personaDatiOption === "RF" ||
      this.personaDatiOption === "PF" ||
      this.personaDatiOption === "SF"
        ? this.personaForm && this.personaForm.invalid
        : false);

    // tipo richiedente non gestore e PF not valid
    const c =
      this.tipoRichiedenteSelected.idTipoRichiedente !== 5 &&
      (this.personaDatiOption === "RG" ||
      this.personaDatiOption === "PG" ||
      this.personaDatiOption === "SG"
        ? this.companyForm && this.companyForm.invalid
        : false);

    // tipo utilizzatore null
    const d = this.tipoUtilizzatoreSelected === 0 || this.tipoUtilizzatoreSelected === null;

    // utlizzatore pf not valid
    const e =
      this.tipoUtilizzatoreSelected === 3 &&
      (this.utilizzatorePersonaForm
        ? this.utilizzatorePersonaForm.invalid
        : false);

    // utlizzatore pg not valid
    let f = false;

    if (this.tipoUtilizzatoreSelected === 4) {
      //taif - verifico se selezionato
      if (this.utilizzatoreCompanyForm.get("taif").value === true) {
        f = this.utilizzatoreCompanyForm.get("soggettoTaif").value === null;
      } else {
        f =
          this.utilizzatoreCompanyForm && this.utilizzatoreCompanyForm.invalid;
      }
    }

    /*     console.log('====================================');
    console.log("a " , a);
    console.log("b " , b);
    console.log("c " , c);
    console.log("d " , d, this.tipoUtilizzatoreSelected);
    console.log("e " , e);
    console.log("f " , f, this.utilizzatoreCompanyForm);
    console.log('===================================='); */

    return a || b || c || d || e || f;
  }

  companyFillFields(index: number) {
    /* this.qui = true;
    this.selectedCompany = true;
    this.disabledField();
    this.forestaliService
    .getCompanyData(index)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: HomeModel) => {
        this.personalOrCompanyForm.patchValue(res);
        this.changeValidators(this.companyFields);
      }); */
  }
}
