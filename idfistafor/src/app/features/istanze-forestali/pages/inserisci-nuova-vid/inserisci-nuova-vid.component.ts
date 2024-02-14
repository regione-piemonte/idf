/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { MenuItem } from "primeng/api";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormControl,
} from "@angular/forms";
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
import { VincoloService } from "../../services/vincolo.service";
import { VincoloStep3 } from "../../components/stepper-vid/step3-vid/vincoloStep3";
import { CONST } from "src/app/shared/constants";
import { ThrowStmt } from "@angular/compiler";
import { DialogService } from "src/app/shared/services/dialog.service";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { SaveFileService } from "src/app/shared/services/save-file.service";

@Component({
  selector: "inserisci-nuova-vid",
  templateUrl: "./inserisci-nuova-vid.component.html",
  styleUrls: ["./inserisci-nuova-vid.component.css"],
})
export class InserisciNuovaVidComponent implements OnInit, OnDestroy {
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
  //sixthStepData: PlainSestoSezione;
  formBuilt: boolean;
  isCompleted = false;
  isGeecoCallBack = false;
  transferSixFormDocumento: DocumentoAllegato[];

  items: MenuItem[] = [];

  secondFormData: any;
  thirdFormData: CheckboxAndRadio;
  fourthFormData: Step4Model;
  newPlan: boolean = false;
  isFinished: boolean;

  istanzaInviata: IstanzaInviata;
  secondFormData$: Observable<any>;
  thirdFormData$: Observable<VincoloStep3>;
  fourthFormData$: Observable<Step4Model>;
  //sixthStepData$: Observable<PlainSestoSezione>;
  isNoNecessaria: boolean = false;
  viewModeOn: boolean = false;
  modificaModeOn: boolean = false;
  boModificaOn: boolean = false;
  instanzaOwnership: any;
  personaOwner: any;
  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    //{ field: "note", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb", width: "150px" },
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
  boOperatingEnabled: boolean = false;

  autorizzaPopUp: boolean;
  autorizzaForm: FormGroup;
  currentYear: number = new Date().getFullYear();
  it: any;

  mapUrl: SafeResourceUrl;

  constructor(
    private router: Router,
    private forestaliService: ForestaliService,
    private vincoloService: VincoloService,
    private saveFileService: SaveFileService,
    private fb: FormBuilder,
    private dialogService: DialogService,
    public route: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.it = CONST.IT;
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.checkUriParams();
    if (this.boModificaOn === false) {
      this.vincoloService
        .getAdpfor(sessionStorage.getItem("tipoIstanzaId"))
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.tipoAccreditamento = res.fkTipoAccreditamento;
          if (this.tipoAccreditamento === "Professionista")
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
        });
    }

    if (!this.router.url.startsWith("/inserisci-nuova-vid")) {
      this.route.paramMap.subscribe((paramMap: ParamMap) => {
        this.editMode = +paramMap.get("idIntervento");
        this.checkUriParams();
        this.isFinished = this.editMode >= 6 ? true : false;
        if (!this.editMode) {
          this.router.navigate(["visualizza-vid"]);
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

    if (this.boModificaOn === true) {
      this.vincoloService.getDocGestore(this.editMode).subscribe((res) => {
        this.tableData = res;
      });
    }

    if (window.location.href.indexOf("return-modifica-vid") > -1) {
      console.log("return-modifica-vid");
      this.isGeecoCallBack = true;
      this.vincoloService
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
        this.boOperatingEnabled =
          this.boModificaOn === true &&
          this.user.ruolo != 5 &&
          !(
            this.user.ruolo == CONST.ROLE_UF_TERRIRORIALE &&
            this.istanzaInviata.fkTipoIstanza == 5
          ) &&
          !(
            this.user.ruolo == CONST.ROLE_COMUNE &&
            this.istanzaInviata.fkTipoIstanza == 4
          );
      });
    console.log("boOperatingEnabled:" + this.boOperatingEnabled);
    console.log("user:");
    console.log(this.user);
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
    this.vincoloService
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
    this.boModificaOn = boModifica == "true";
  }

  getViewModificaOn() {
    this.checkUriParams();
    return this.viewModeOn;
  }

  setIdIstanze(event) {
    if (!this.editMode) {
      this.editMode = event;
    }
  }

  setNoNecessaria() {
    this.isNoNecessaria = !this.isNoNecessaria;
  }

  setActiveIndex() {
    if (this.editMode) {
      this.vincoloService
        .getStepNumber(this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: StepPosition) => {
          if (this.isGeecoCallBack) {
            this.isGeecoCallBack = false;
            this.backward();
            this.activeIndex = 1;
          } else this.activeIndex = response.nextStep;

          if (this.activeIndex == 6) this.modificaModeOn = false;

          this.createStepper();

          if (this.activeIndex >= 4) {
            // segnalibro
            //this.fourthFormData$ = this.vincoloService.getStep4(this.editMode);
            //this.sixthStepData$ = this.forestaliService.getStep6(this.editMode);
            //this.sixthStepData$.subscribe(res => { this.transferSixFormDocumento = res.allegati; });
            this.vincoloService
              .getAllDocuments(this.editMode)
              .subscribe((res) => {
                this.transferSixFormDocumento = res;
              });
          }

          if (this.activeIndex !== 6) this.items[this.activeIndex].command();
        });
    } else {
      this.activeIndex++;
      this.createStepper();
    }
  }

  createStepper() {
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
        label: "Localizzazione intervento",
        command: (event: any) => {
          this.activeIndex = 1;
          this.secondFormData$ = this.vincoloService.getStep2(this.editMode);
        },
        disabled: this.activeIndex >= 1 ? false : true,
        badgeStyleClass: this.activeIndex >= 1 ? "green-step" : "yellow-step",
      },
      {
        label: "Descrizione intervento",
        command: (event: any) => {
          this.activeIndex = 2;
          this.thirdFormData$ = this.vincoloService.getStep3(this.editMode);
        },
        disabled: this.activeIndex >= 2 ? false : true,
        badgeStyleClass: this.activeIndex >= 2 ? "green-step" : "yellow-step",
      },
      {
        label: "Cauzione",
        command: (event: any) => {
          this.activeIndex = 3;
        },
        disabled: this.activeIndex >= 3 ? false : true,
        badgeStyleClass: this.activeIndex >= 3 ? "green-step" : "yellow-step",
      },
      {
        label: "Rimboschimento e compensazione forestale",
        command: (event: any) => {
          this.activeIndex = 4;
        },
        disabled: this.activeIndex >= 4 ? false : true,
        badgeStyleClass: this.activeIndex >= 4 ? "green-step" : "yellow-step",
      },
      {
        label: "Dichiarazioni, comunicazioni, allegati",
        command: (event: any) => {
          this.activeIndex = 5;
        },
        disabled: this.activeIndex >= 5 ? false : true,
        badgeStyleClass: this.activeIndex >= 5 ? "green-step" : "yellow-step",
      },
    ];
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
    this.router.navigate(["visualizza-vid"]);
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
  }

  upload(event, tipo) {
    let dataAllegati;
    const formData: FormData = new FormData();
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
      this.vincoloService
        .uploadAllegato(formData, this.editMode, tipo)
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

  fieldToDownload(event) {
    this.vincoloService
      .downloadAllegato(event)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        let contentDisposition = response.headers.get("content-disposition");
        let filename = contentDisposition
          .split(";")[1]
          .split("filename")[1]
          .split("=")[1];
        this.saveFileService.saveFile(response, filename);
      });
  }

  // verificataIstanza() {
  //   this.forestaliService.verificataIstanza(this.editMode)
  //     .pipe(takeUntil(this.unsubscribe$))
  //     .subscribe(response => {
  //       this.completed = true;
  //       this.loadPraticaInviata();
  //     });
  // }

  autorizzaIstanza() {
    this.initAutorizzaForm();
    this.autorizzaPopUp = true;
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

  initAutorizzaForm() {
    this.autorizzaForm = new FormGroup({
      dataVerifica: new FormControl("", [Validators.required]),
      nrDeterminaAutoriz: new FormControl("", [Validators.required]),
      dataTermineAut: new FormControl(""),
      definitaNelProvvedimentoUnicoFinale: new FormControl(""),
      dataFineIntervento: new FormControl(""),
    });
  }

  isAutorizzaFormInvalid() {
    let dataTermineAut = this.autorizzaForm.get("dataTermineAut").value;
    let dataFineIntervento = this.autorizzaForm.get("dataFineIntervento").value;

    return (
      !this.autorizzaForm ||
      this.autorizzaForm.invalid ||
      (dataTermineAut == "" &&
        this.autorizzaForm.get("definitaNelProvvedimentoUnicoFinale").value !=
          true) ||
      (dataTermineAut != "" &&
        dataFineIntervento != "" &&
        dataFineIntervento >= dataTermineAut)
    );
  }

  closeAutorizzazione() {
    this.autorizzaPopUp = false;
  }

  onChangeCheckboxDefinitaData() {
    if (
      this.autorizzaForm.get("definitaNelProvvedimentoUnicoFinale").value ==
      true
    ) {
      this.autorizzaForm.get("dataTermineAut").patchValue("");
    }
  }

  onChangeDataTermineAut() {
    this.autorizzaForm
      .get("definitaNelProvvedimentoUnicoFinale")
      .patchValue(false);
  }

  confermaAutorizzazione() {
    let values = JSON.parse(JSON.stringify(this.autorizzaForm.value));
    delete values.definitaNelProvvedimentoUnicoFinale;
    values["idIntervento"] = this.editMode;
    this.vincoloService.autorizzataIstanza(values).subscribe((res) => {
      this.completed = true;
      this.loadPraticaInviata();
      this.closeAutorizzazione();
    });
  }

  openMappa() {
    //window.scrollTo(0, 0);
    this.forestaliService
      .getCartograficoPointsUrl(this.boModificaOn ? 18 : 20, [
        "" + this.editMode,
      ])
      .subscribe((response: any) => {
        this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          response["geecourl"]
        );
        window.open(response["geecourl"], "_blank");
      });
    return false;
  }
}
