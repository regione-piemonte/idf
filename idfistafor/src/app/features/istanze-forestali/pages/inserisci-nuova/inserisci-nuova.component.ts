/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { MenuItem } from "primeng/api";
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { Subject, Observable } from "rxjs";
import { takeUntil } from "rxjs/operators";

import { ForestaliService } from "../../services/forestali.service";
import { Step4Model } from "src/app/shared/models/step4.model";
import { CheckboxAndRadio } from "src/app/shared/models/checkbox-and-radio";
import { StepPosition } from "src/app/shared/models/step-position.model";
import { ShowParcel } from "src/app/shared/models/particle-cadastral";
import {
  PlainSestoSezione,
  DocumentoAllegato,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { TableHeader } from "src/app/shared/models/table-header";
import { IstanzaInviata } from "src/app/shared/models/view-instance";
import { e } from "@angular/core/src/render3";
import { HomeModel } from "src/app/shared/models/home.model";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { DialogService } from "src/app/shared/services/dialog.service";
import { CONST } from "src/app/shared/constants";
import { SaveFileService } from "src/app/shared/services/save-file.service";

@Component({
  selector: "app-inserisci-nuova",
  templateUrl: "./inserisci-nuova.component.html",
  styleUrls: ["./inserisci-nuova.component.css"],
})
export class InserisciNuovaComponent implements OnInit, OnDestroy {
  maxLengthMotivoRifiuto = 1990;
  editMode: number;
  completed: boolean = false;

  tipoAccreditamento = null;
  delegates = [];

  datiDelProfessionistaForm: FormGroup;
  descriptionOfTheForestForm: FormGroup;
  fourthStepForm: FormGroup;
  sixthStepForm: FormGroup;
  polygonForm: FormGroup;
  invalidMessage = "il modulo non è valido";
  unsubscribe$ = new Subject<void>();

  activeIndex = -1;
  numberOfSteps = 6;
  secondStepTable: ShowParcel[] = [];
  secondStepDataToSend: any = {
    particelleCatastali: [],
    ricadenzaVincoloIdrogeologico: false,
  };
  isTableEmpty: boolean;
  transferSecondFormData: any;
  sixthStepData: PlainSestoSezione;
  formBuilt: boolean;
  isCompleted = false;
  isGeecoCallBack = false;
  transferSixFormDocumento: DocumentoAllegato[];

  /* descriptionOfTheForest: CheckboxAndRadio; */

  items: MenuItem[] = [];

  secondFormData: any;
  thirdFormData: CheckboxAndRadio;
  fourthFormData: Step4Model;
  newPlan: boolean = false;
  isFinished: boolean;

  istanzaInviata: IstanzaInviata = {};
  secondFormData$: Observable<any>;
  thirdFormData$: Observable<CheckboxAndRadio>;
  fourthFormData$: Observable<Step4Model>;
  sixthStepData$: Observable<PlainSestoSezione>;
  isNoNecessaria: boolean = null;
  viewModeOn: boolean = false;
  modificaModeOn: boolean = false;
  boModificaOn: boolean = false;
  instanzaOwnership: any;
  personaOwner: any;
  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    //{ field: "note", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb" },
  ];
  tableData: DocumentoAllegato[] = [];
  activateInserci: boolean = true;
  boFinishedAssociareDocumenti: boolean = true;
  allProfessionals = [];
  editProfessionista: boolean = true;
  filePopUp: boolean;
  rifiutoPupUp: boolean;
  motivoRifiuto: string = "";
  user: HomeModel = {};
  isIstanzaInviata: boolean = false;
  showGeeco: boolean = false;
  mapUrl: SafeResourceUrl;

  constructor(
    private router: Router,
    private forestaliService: ForestaliService,
    private fb: FormBuilder,
    private saveFileService: SaveFileService,
    public route: ActivatedRoute,
    public dialogService: DialogService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.checkUriParams();
    if (this.boModificaOn === false) {
      this.forestaliService
        .getAdpfor()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.tipoAccreditamento = res.fkTipoAccreditamento;
          if (this.tipoAccreditamento === "Professionista") {
            this.forestaliService
              .getDropdownCodiceFiscale()
              .pipe(takeUntil(this.unsubscribe$))
              .subscribe((delegates) => {
                delegates.forEach((element, index) => {
                  if (
                    element !== null &&
                    element === sessionStorage.getItem("codiceFiscale")
                  ) {
                    this.delegates.push(element);
                  }
                });
              });
          }
        });
    }

    if (this.router.url !== "/inserisci-nuova") {
      this.route.paramMap.subscribe((paramMap: ParamMap) => {
        this.editMode = +paramMap.get("idIntervento");
        this.isFinished = this.editMode >= 6 ? true : false;
        if (!this.editMode) {
          this.router.navigate(["visualizza"]);
        }
        this.loadPraticaInviata();
        this.forestaliService.utente3.idIntervento = this.editMode;
        this.modificaModeOn = !this.modificaModeOn;
        if (this.viewModeOn) {
          this.modificaModeOn = !this.modificaModeOn;
        }
      });
    } else {
      this.route.paramMap.subscribe((paramMap: ParamMap) => {
        if (paramMap.get("idIntervento") === null) {
          this.newPlan = true;
        }
      });
    }

    if ("" + this.boModificaOn === "true") {
      this.forestaliService.getDocGestore(this.editMode).subscribe((res) => {
        this.tableData = res;
      });
    }

    if (window.location.href.indexOf("return-modifica") > -1) {
      this.isGeecoCallBack = true;
      this.forestaliService
        .recalculateParticelle(this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response) => {
          this.setActiveIndex();
        });
    } else {
      this.setActiveIndex();
    }

    if (this.boModificaOn) {
      this.checkIstanzaOwner(this.editMode);
    }
  }

  loadPraticaInviata() {
    this.forestaliService
      .getPraticaInviata(this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        this.istanzaInviata = response;
        this.istanzaInviata.annoInserimento = new Date(
          this.istanzaInviata.dataInserimento
        ).getFullYear();
        this.istanzaInviata.dataInserimento = this.getFormatDateIT(
          this.istanzaInviata.dataInserimento
        );
        this.istanzaInviata.dataProtocollo = this.getFormatDateIT(
          this.istanzaInviata.dataProtocollo
        );
        this.istanzaInviata.dataInvioFormated = this.getFormatDateIT(
          this.istanzaInviata.dataInvio
        );
        this.istanzaInviata.dataVerifica = this.getFormatDateIT(
          this.istanzaInviata.dataVerifica
        );
        this.istanzaInviata.dataAggiornamento = this.getFormatDateIT(
          this.istanzaInviata.dataAggiornamento
        );
        this.isIstanzaInviata =
          this.istanzaInviata.dataInvio &&
          this.istanzaInviata.dataInvio.length > 0;
      });
  }

  toDelete() {
    switch (this.user.ruolo) {
      case CONST.ROLE_GESTORE || CONST.ROLE_SPORTELLO_GESTORE: {
        return true;
      }
      default: {
        return false;
      }
    }
  }

  getFormatDateIT(date: string) {
    if (date != null && date.length == 10) {
      let today = new Date(date);
      let dd = String(today.getDate()).padStart(2, "0");
      let mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
      let yyyy = today.getFullYear();
      return dd + "/" + mm + "/" + yyyy;
    }
    return "n.d.";
  }

  checkIstanzaOwner(idIntervento: number) {
    this.forestaliService
      .getUtenteForIstanza(idIntervento)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.personaOwner = res.body;
        if (
          this.personaOwner.tipoTitolarita === "RG" ||
          this.personaOwner.tipoTitolarita === "RF"
        ) {
          this.instanzaOwnership = "Richiedente";
        } else if (
          this.personaOwner.tipoTitolarita === "PF" ||
          this.personaOwner.tipoTitolarita === "PG"
        ) {
          this.instanzaOwnership = "Professionista";
          let option = {
            label: this.personaOwner.richCodiceFiscale,
            value: this.personaOwner.richCodiceFiscale,
          };
          this.allProfessionals.push(option);
        }
      });
  }
  getAllProfessionals(event: any) {
    this.allProfessionals = [];
    this.personaOwner = {};
    this.forestaliService
      .getStep8Professionista(this.istanzaInviata.fkTipoIstanza)
      .subscribe((res) => {
        let tempArray = res;
        this.editProfessionista = false;
        this.fillSelect(tempArray);
      });
  }

  fillSelect(soggetos: any) {
    soggetos.forEach((element) => {
      let option = { label: element.codiceFiscale, value: element };
      this.allProfessionals.push(option);
    });
  }

  changeOwner(event) {
    this.personaOwner = {
      richNome: event.value.nome,
      richCognome: event.value.cognome,
      richCodiceFiscale: event.value.codiceFiscale,
    };
    //TODO
  }

  checkUriParams() {
    const data = this.route.snapshot.params["viewMode"];
    data ? (this.viewModeOn = data) : (this.viewModeOn = false);
    const boModifica = this.route.snapshot.params["boModifica"];
    boModifica ? (this.boModificaOn = boModifica) : (this.boModificaOn = false);
  }

  setIdIstanze(event) {
    if (!this.editMode) {
      this.editMode = event;
    }
  }
  setNoNecessaria(event) {
    this.isNoNecessaria = event;
  }

  setActiveIndex() {
    if (this.editMode) {
      this.forestaliService
        .getStepNumber(this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: StepPosition) => {
          if (this.isGeecoCallBack) {
            this.isGeecoCallBack = false;
            this.backward();
            this.activeIndex = 1;
          } else {
            this.activeIndex = response.nextStep;
          }
          if (this.activeIndex == 6) {
            //this.backward();
            this.modificaModeOn = false;
          }
          this.createStepper();
          if (this.activeIndex >= 4) {
            this.fourthFormData$ = this.forestaliService.getStep4(
              this.editMode
            );
            //this.fourthFormData$.subscribe(res => {console.log('res',res); });
            this.sixthStepData$ = this.forestaliService.getStep6(this.editMode);
            this.sixthStepData$.subscribe((res) => {
              this.transferSixFormDocumento = res.allegati;
            });
          }
          if (this.activeIndex !== 6) {
            this.items[this.activeIndex].command();
          }
        });
    } else {
      this.activeIndex++;
      this.createStepper();
    }
  }

  createStepper() {
    if (this.isNoNecessaria != null || this.activeIndex < 4) {
      this.createStepperImpl();
    } else {
      this.forestaliService
        .getStep4(this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: Step4Model) => {
          this.fourthFormData = response;
          this.isNoNecessaria =
            response.nonNecessaria || response.nonNecessaria21;
          this.createStepperImpl();
        });
    }
  }

  createStepperImpl() {
    this.items = [
      {
        label: "Richiedente",
        command: (event: any) => {
          this.activeIndex = 0;
        },
        disabled: this.activeIndex >= 0 ? false : true,
        styleClass: this.activeIndex >= 0 ? "green-step" : "yellow-step",
      },
      {
        label: "Localizzazione bosco",
        command: (event: any) => {
          this.activeIndex = 1;
          this.secondFormData$ = this.forestaliService.getSecondStepTable(
            this.editMode
          );
        },
        disabled: this.activeIndex >= 1 ? false : true,
        badgeStyleClass: this.activeIndex >= 1 ? "green-step" : "yellow-step",
      },
      {
        label: "Descrizione bosco",
        command: (event: any) => {
          this.activeIndex = 2;
          this.thirdFormData$ = this.forestaliService.getStep3(this.editMode);
        },
        disabled: this.activeIndex >= 2 ? false : true,
        badgeStyleClass: this.activeIndex >= 2 ? "green-step" : "yellow-step",
      },
      {
        label: "Compensazione",
        command: (event: any) => {
          this.activeIndex = 3;
          this.fourthFormData$ = this.forestaliService.getStep4(this.editMode);
        },
        disabled: this.activeIndex >= 3 ? false : true,
        badgeStyleClass: this.activeIndex >= 3 ? "green-step" : "yellow-step",
      },
      {
        label: "Tecnico forestale",
        command: (event: any) => {
          // this.datiDelProfessionistaForm.reset();
          this.activeIndex = 4;
          /* this.fourthFormData.pipe(takeUntil(this.unsubscribe$))
          .subscribe((response: Step4Model) => {
            this.fourthFormData = response;
          }); */
        },
        disabled:
          this.isNoNecessaria === false && this.activeIndex >= 4 ? false : true,
        badgeStyleClass: this.activeIndex >= 4 ? "green-step" : "yellow-step",
      },
      {
        label: "Dichiarazioni",
        command: (event: any) => {
          // this.isNoNecessaria === false;
          this.activeIndex = 5;
          this.sixthStepData$ = this.forestaliService.getStep6(this.editMode);
        },
        disabled: this.activeIndex >= 5 ? false : true,
        badgeStyleClass: this.activeIndex >= 5 ? "green-step" : "yellow-step",
      },
    ];
    console.log("Steper obj:");
    console.log(this.items);
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  onChangeStep(activeIndex) {}

  goHome() {
    if (this.boModificaOn) {
      this.forestaliService.clearSearchStorage();
      this.router.navigate([""]);
    } else {
      this.router.navigate([""]);
    }
  }

  goVisualizza() {
    this.router.navigate(["visualizza"]);
  }

  backward() {
    this.modificaModeOn = !this.modificaModeOn;
  }

  returnToArhiveList() {
    this.router.navigate([""]);
  }

  setCompleted(event) {
    this.isCompleted = event;
    this.loadPraticaInviata();
    this.viewModeOn = true;
  }

  //FIX GP tck12b
  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  upload(event, tipo) {
    let dataAllegati;
    const formData: FormData = new FormData();

    //FIX GP tck12b
    console.log(
      "fileName",
      this.contieneCaracteresEspecialesItaliano(event.files[0].name)
    );
    if (this.contieneCaracteresEspecialesItaliano(event.files[0].name)) {
      this.dialogService.showDialog(
        true,
        "I caratteri speciali non sono accettati nel nome del file. Modificare il nome del file",
        "Attenzione"
      );
      return;
    }

    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    if (
      this.forestaliService.isNamePresent(event.files[0].name, this.tableData)
    ) {
      this.filePopUp = true;
    } else if (
      this.forestaliService.isNamePresent(
        event.files[0].name,
        this.transferSixFormDocumento
      )
    ) {
      this.filePopUp = true;
    } else {
      this.filePopUp = false;
      this.forestaliService
        .uploadFileStep6(formData, this.editMode, tipo)
        .subscribe((response: any) => {
          if (response.error) {
            this.dialogService.showDialog(true, response.error, "Errore");
            return;
          }
          this.tableData.push(response);
          this.activateInserci = false;
        });
    }
  }
  closeDialog() {
    this.filePopUp = false;
  }

  associareDocumenti() {
    this.forestaliService
      .associareDocumentiBo(this.editMode, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.activateInserci = !this.activateInserci;
          this.tableData = [];
          this.boFinishedAssociareDocumenti = false;
        }
      });
  }

  fieldToDelete(event, table) {
    if (table === "elenco") {
      this.forestaliService
        .deleteDocumentoAllegato(event.rowIndex)
        .subscribe((res) => {
          this.tableData.forEach((item, index) => {
            if (item.idDocumentoAllegato === event.rowIndex) {
              item.deleted = true;
              this.tableData.splice(event.index, 1);
            }
          });
        });
    }
  }

  verificataIstanza() {
    this.forestaliService
      .verificataIstanza(this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        this.completed = true;
        this.loadPraticaInviata();
      });
  }

  rifiutataIstanza() {
    this.rifiutoPupUp = false;
    this.forestaliService
      .rifiutataIstanza(this.editMode, this.motivoRifiuto)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.completed = true;
          this.loadPraticaInviata();
        }
      });
  }

  onRifiutataClick() {
    this.rifiutoPupUp = true;
  }

  onMotivoRifiutoChange(event) {
    this.motivoRifiuto = event.target.value;
    if (this.motivoRifiuto.length > this.maxLengthMotivoRifiuto) {
      this.motivoRifiuto = this.motivoRifiuto.substr(
        0,
        this.maxLengthMotivoRifiuto
      );
    }
  }

  disabledRifiuto() {
    return this.motivoRifiuto.length < 20;
  }

  mostraDettaglioFinale() {
    this.activeIndex = 6;
  }

  openMappa() {
    //window.scrollTo(0, 0);
    this.forestaliService
      .getCartograficoPointsUrl(this.boModificaOn ? 4 : 7, ["" + this.editMode])
      .subscribe((response: any) => {
        this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          response["geecourl"]
        );
        window.open(response["geecourl"], "_blank");
      });
    return false;
  }
}
