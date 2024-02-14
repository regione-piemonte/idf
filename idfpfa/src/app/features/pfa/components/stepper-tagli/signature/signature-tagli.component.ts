/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  Output,
  EventEmitter,
  Input,
  OnChanges,
} from "@angular/core";
import { takeUntil } from "rxjs/operators";
import { Subject, combineLatest, Observable } from "rxjs";

import { UserChoiceModel } from "src/app/shared/models/user-choice.model";
import { ForestaliService } from "../../../services/forestali.service";
import {
  DocumentoAllegato,
  IstanzaTaglio,
  PlainSestoSezione,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { IdfistaforHeaderHelper } from "../../../../idfistafor-header-helper";
import { IstanzaInviata } from "src/app/shared/models/view-instance";
import { DialogService } from "../../../../../shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { VincoloService } from "../../../services/vincolo.service";
import { Router } from "@angular/router";
import { InfoVarianteProrogaModel } from "src/app/shared/models/info-variante-proroga.model";
import {
  FormControl,
  FormGroup,
  Validators,
  FormBuilder,
} from "@angular/forms";
import { CONST } from "src/app/shared/constants";
import { TableHeader } from "src/app/shared/models/table-header";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { InfoDocsPagamenti } from "src/app/shared/models/info-doc-pagamenti.model";
import { TagliService } from "../../../services/tagli.service";
import { TipoAllegatoEnum } from "src/app/shared/models/tipo-allegato.enum";
import { TipoIstanzaEnum } from "src/app/shared/models/tipo-istanza.enum";
import { InfoDocsMancanti } from "./../../../../../shared/models/info-docs-mancanti.model";
import { TagliStep2 } from "./../step2-tagli/tagli-step2.model";
import { Utilizzatore } from "src/app/shared/models/utilizzatore.model";
import { TipoUtilizzatoreEnum } from "src/app/shared/models/tipo-utilizzatore.enum";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { SelectItem } from "primeng/api";
import { UserCompanyDataModel } from "src/app/shared/models/user-company-data.model";
import { HomeModel } from "src/app/shared/models/home.model";

@Component({
  selector: "app-signature-tagli",
  templateUrl: "./signature-tagli.component.html",
  styleUrls: ["./signature-tagli.component.css"],
})
export class SignatureTagliComponent implements OnInit, OnChanges {
  @Output() emitCompleted = new EventEmitter();
  tableData: DocumentoAllegato[] = [];
  allegatiEditData: DocumentoAllegato[] = [];
  @Input() editMode: number;
  @Input() activeIndex: number;
  @Input() viewMode: boolean;
  @Input() boMode: boolean;
  @Input() istanzaInviata: IstanzaInviata;
  @Input() boOperatingEnabled: boolean;
  @Input() idTipoRichiedente: number;
  @Input() user: HomeModel = {};

  localEditMode: number;
  unsubscribe$ = new Subject<void>();
  tipoAccreditamento = null;
  isCompleted = false;
  dataInvio = null;
  docType = null;
  docTypeUtil = null;
  invioFinished = true;
  preparedDocuments: DocumentoAllegato[] = [];
  isDownloadSuccess = false;
  allegatiTableHeaders = IdfistaforHeaderHelper.getAllegatiTableHeaders();
  istanzeTaglioHeaders: TableHeader[] = [
    { field: "numIstanza", header: "Numero istanza" },
    { field: "dataPresentIstanzaFormat", header: "Data Presentazione istanza" },
    { field: "stato", header: "Stato" },
    { field: "tipoIstanza", header: "Tipo istanza" },
    { field: "descIntervento", header: "Descrizione Intervento" },
    { field: "specieCoinvolte", header: "Specie coinvolte" },
    { field: "stimaMassaRetraibile", header: "Stima massa retraibile" },
    { field: "comuniInteressati", header: "Comuni interessati" },
    { field: "tipoIntervento", header: "Tipo Intervento" },
  ];
  filePopUp: boolean;
  titolaritaPopUp: boolean;
  istanzeTaglioPopUp: boolean;

  isCompensazioneFisica: boolean = false;
  docsRicevutePagamento: any = {};
  docsDrel: any = {};

  infoVarianteProroga: InfoVarianteProrogaModel;
  isVariante: boolean;
  showPopupVarianteProroga: boolean = false;
  titolaritaForm: FormGroup;
  istanzeTaglioForm: FormGroup;
  dataFineInterventoForm: FormGroup;
  showListVarintiProroghe: any;

  professionistiList;
  professionistiFilteredList;
  currentYear: number = new Date().getFullYear();
  it: any;
  statoIntervento = {
    1: "PROGRAMMATO",
  };

  infoDocsPagamenti: InfoDocsPagamenti;
  infoDocsMancanti: InfoDocsMancanti;

  istanzeTaglioTable: IstanzaTaglio[] = [];
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  mapUrl: SafeResourceUrl;
  tipoAllegatoEnum: any = TipoAllegatoEnum;
  tipoIstanzaDescr: TipoIstanzaEnum;
  tipoIstanzaEnum: any = TipoIstanzaEnum;

  annullaPupUp: boolean;
  motivoAnnullamento: string = "";
  maxLengthMotivoAnnullamento = 1990;

  // sezione cambio utilizzatore
  mainContent: boolean = true;
  showCambioUtilizzatore: boolean = false;
  showCaricaDocumenti: boolean = false;
  step2Form: TagliStep2;
  utilizzatoriOptions: Utilizzatore[] = CONST.TIPO_UTILIZZATORE_OPTIONS.filter(
    (i) => i.id !== 2
  );
  currentUtilizzatore: string;
  tipoUtilizzatoreSelected: number = null;
  utilizzatorePersonaForm: FormGroup;
  utilizzatoreCompanyForm: FormGroup;
  utilizzatorePersonalOrCompanyForm: FormGroup;
  TipoAccreditamentoEnum: any = TipoAccreditamento;
  isUtilizzatoreEntePubblico: boolean = false;
  codiceFiscaleSelectItem: SelectItem[] = [];
  codiceFiscaleSONOIO: SelectItem[] = [];
  companies: UserCompanyDataModel[] = [];
  ownerOfInstance: string = sessionStorage.getItem("codiceFiscale");
  canChangeUtilizzatore: boolean = false;
  showDocChangeUtilizzatore: boolean = false;

  annullaVisible: boolean = true;
  invioemail: boolean = false;

  constructor(
    private fb: FormBuilder,
    private forestaliService: ForestaliService,
    private saveFileService: SaveFileService,
    private dialogService: DialogService,
    private vincoloService: VincoloService,
    private tagliService: TagliService,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {}

  ngOnInit() {
    this.it = CONST.IT;
    this.onloadOrChange();
  }

  setTitolarita() {
    switch (this.user.ruolo) {
      case CONST.ROLE_CITADINO || CONST.ROLE_CONSULTAZIONE: {
        return true;
      }
      default: {
        return false;
      }
    }
  }

  ngOnChanges() {
    if (this.istanzaInviata && this.istanzaInviata.fkTipoIstanza) {
      this.forestaliService
        .getAdpforByTipoIstanzaId(this.istanzaInviata.fkTipoIstanza)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res: UserChoiceModel) => {
          this.tipoAccreditamento = res.fkTipoAccreditamento;
          this.tipoIstanzaDescr = TipoIstanzaEnum[res.tipoIstanzaDescr];
          this.canChangeUtilizzatore = this.idTipoRichiedente !== 4;
          sessionStorage.setItem("tipoIstanzaId", res.tipoIstanzaId + "");
        });
    }

    if (this.localEditMode != this.editMode) {
      this.onloadOrChange();
    }

    //if(this.boMode){
    if (
      this.istanzaInviata.fkStatoIstanza == 3 &&
      !this.istanzaInviata.dataFineIntervento &&
      !this.dataFineInterventoForm
    ) {
      this.initDataFineInterventoForm();
    }
    //}
  }

  onloadOrChange() {
    this.localEditMode = this.editMode;
    this.showListVarintiProroghe = null;
    window.scrollTo(0, 0);

    if (!this.istanzaInviata) {
      this.istanzaInviata = {};
    }

    if (this.istanzaInviata && this.istanzaInviata.dataInvio) {
      this.emitCompleted.emit("true");
    }

    combineLatest(
      this.tagliService.getInfoVarianteProroga(this.editMode),
      this.tagliService.getinfoDrel(this.editMode)
    )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(([resProroga, resDrel]) => {
        this.infoVarianteProroga = resProroga;
        this.infoDocsMancanti = resDrel;
      });

    this.isCompleted = this.activeIndex >= 5 ? true : false;
    this.loadDocuments();
  }

  initTitolaritaForm() {
    this.titolaritaForm = new FormGroup({
      professionista: new FormControl("", [Validators.required]),
    });
  }

  initDataFineInterventoForm() {
    this.dataFineInterventoForm = new FormGroup({
      dataFineIntervento: new FormControl("", [Validators.required]),
    });
  }

  salvaDataFineIntervento() {
    if (this.istanzaInviata.dataTermineAutorizzazione) {
      let dataTermineAutorizzazione = new Date(
        this.istanzaInviata.dataTermineAutorizzazione
      );
      if (
        dataTermineAutorizzazione &&
        this.getNumByData(dataTermineAutorizzazione) <=
          this.getNumByData(
            this.dataFineInterventoForm.get("dataFineIntervento").value
          )
      ) {
        this.showDialogError(
          "La data deve essere antecedente al " +
            this.getFormatDate(dataTermineAutorizzazione) +
            " scadenza del procedimento"
        );
        return;
      }
    }
    let values = this.dataFineInterventoForm.value;
    values.idIntervento = this.editMode;
    this.tagliService.updateDataFineIntervento(values).subscribe((res) => {
      this.dataFineInterventoForm = null;
      this.istanzaInviata.dataFineIntervento = values.dataFineIntervento;
    });
  }

  filterProfessionista(event) {
    if (!this.professionistiList) {
      this.tagliService
        .getProfessionistiList()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.professionistiList = res;
          this.professionistiFilteredList =
            this.forestaliService.autocompleteFilter(
              event,
              this.professionistiList,
              "Professionista"
            );
        });
    } else {
      this.professionistiFilteredList =
        this.forestaliService.autocompleteFilter(
          event,
          this.professionistiList,
          "Professionista"
        );
    }
  }

  salvaCambioTitolarita() {
    let soggetto = this.titolaritaForm.get("professionista").value;
    this.tagliService
      .putTitolarita(this.editMode, soggetto.fkConfigUtente)
      .subscribe((res) => {
        this.istanzaInviata.utenteCompilatore = res.utenteCompilatore;
        this.titolaritaPopUp = false;
      });
  }

  cambiaTitolarita() {
    this.initTitolaritaForm();
    this.titolaritaPopUp = true;
  }

  closeTitolarita() {
    this.titolaritaPopUp = false;
  }

  isDocsMisssing() {
    return this.infoDocsMancanti && this.infoDocsMancanti.missing;
  }

  loadDocuments() {
    this.tagliService
      .getAllDocuments(this.editMode)
      .subscribe((response: [DocumentoAllegato]) => {
        this.tableData = response;
        if (this.idDocPresent(2)) {
          this.docType = 2;
        } else if (this.idDocPresent(3)) {
          this.docType = 3;
        }
      });
  }

  changeDocType(docType) {
    this.docType = docType;
    this.docTypeUtil = docType;
  }

  inserisciInElenco(event) {
    this.preparedDocuments.forEach((document) => {
      this.tableData.push(document);
    });
    this.preparedDocuments = [];
  }

  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  uploadDocumento(event, tipo) {
    if (tipo == 0) {
      if (!this.docType) {
        this.dialogService.showDialog(
          true,
          "Selezionare la tipologia di allegato!",
          "Attenzione",
          DialogButtons.OK,
          "",
          (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              console.log("BUTTON WORKS");
            }
          }
        );
        return;
      } else {
        tipo = this.docType;
      }
    }
    if (this.contieneCaracteresEspecialesItaliano(event.files[0].name)) {
      this.dialogService.showDialog(
        true,
        "I caratteri speciali non sono accettati nel nome del file. Modificare il nome del file",
        "Attenzione"
      );
      return;
    }
    const formData: FormData = new FormData();
    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    if (
      this.tagliService.isNamePresent(
        event.files[0].name,
        this.tableData ? this.tableData : []
      )
    ) {
      this.filePopUp = true;
    } else {
      this.filePopUp = false;
      this.tagliService
        .uploadAllegato(formData, this.editMode, tipo)
        .subscribe((response: any) => {
          if (response.error) {
            this.dialogService.showDialog(true, response.error, "Errore");
            return;
          }
          this.tableData.push(response);
        });
    }
    const cmpUpload = document.getElementById(
      "fileUpload_" + (tipo == 38 ? tipo : 0)
    );
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  uploadDocumentoUtil(event, tipo) {
    if (tipo == 0) {
      if (!this.docTypeUtil) {
        this.dialogService.showDialog(
          true,
          "Selezionare la tipologia di allegato!",
          "Attenzione",
          DialogButtons.OK,
          "",
          (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              console.log("BUTTON WORKS");
            }
          }
        );
        return;
      } else {
        tipo = this.docTypeUtil;
      }
    }
    const formData: FormData = new FormData();
    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    this.tagliService
      .uploadAllegato(formData, this.editMode, tipo)
      .subscribe((response: any) => {
        if (response.error) {
          this.dialogService.showDialog(true, response.error, "Errore");
          return;
        }
        this.tableData.push(response);
      });

    const cmpUpload = document.getElementById(
      "fileUpload_" + (tipo == 38 ? tipo : 0)
    );
    cmpUpload.getElementsByTagName("button")[2].click();
    this.loadDocuments();
  }

  uploadDrel(event, tipo) {
    let file = event.files[0];
    this.docsDrel[tipo] = file;
    this.allegatiEditData.push({
      idTipoAllegato: tipo,
      nomeAllegato: file.name,
      descrTipoAllegato: this.getDescrizioneDocMancante(),
      dimensioneAllegatoKB: Math.round(file.size / 1000),
    });
    let cmpUpload = document.getElementById("fileUpload_" + tipo);
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  getDescrizioneDocMancante() {
    if (this.infoDocsMancanti) {
      return this.infoDocsMancanti.docDescription;
    }
    return "-";
  }

  deleteCauzione(event) {
    this.allegatiEditData.splice(event.index, 1);
  }

  inviaIntegrazione() {
    let formData: FormData = new FormData();
    let elem: HTMLInputElement;
    let tipoAllegato;
    for (let id in this.allegatiEditData) {
      tipoAllegato = this.allegatiEditData[id].idTipoAllegato;
      formData.append("form_" + tipoAllegato, this.docsDrel[tipoAllegato]);
      formData.append(
        "fileName_" + tipoAllegato,
        this.docsDrel[tipoAllegato].name
      );
      elem = document.getElementById(
        "noteDocumento_" + tipoAllegato
      ) as HTMLInputElement;
      formData.append("note_" + tipoAllegato, encodeURIComponent(elem.value));
    }

    this.tagliService
      .uploadDrel(formData, this.editMode)
      .subscribe((response: DocumentoAllegato) => {
        this.tableData.push(response);
        this.tagliService.getinfoDrel(this.editMode).subscribe((res) => {
          this.infoDocsMancanti = res;
          this.loadDocuments();
          this.dialogService.showDialog(
            true,
            "Documenti caricati correttamente!",
            "Info",
            DialogButtons.OK,
            "",
            (buttonId: number): void => {
              if (buttonId === ButtonType.OK_BUTTON) {
                console.log("BUTTON WORKS");
              }
            }
          );
        });
      });
  }

  disableRadio(name: string, value: boolean) {
    const items = document.getElementsByName(name);
    Array.from(items).forEach((item) => (item["disabled"] = value));
  }

  idDichiarazioneUploaded() {
    for (let i in this.tableData ? this.tableData : []) {
      if (
        this.tableData[i]["idTipoAllegato"] === 25 ||
        this.tableData[i]["idTipoAllegato"] === 26 ||
        this.tableData[i]["idTipoAllegato"] === 36 ||
        this.tableData[i]["idTipoAllegato"] === 37
      ) {
        this.docType = this.tableData[i]["idTipoAllegato"];
        this.disableRadio("radio-docType", true);
        return true;
      }
    }
    this.disableRadio("radio-docType", false);
    return false;
  }

  idDocIdentitaOk() {
    if (this.docType == 25 || this.docType == 26) {
      return true;
    }

    if (
      (this.docType == 36 || this.docType == 37) &&
      this.tableData &&
      this.idDocPresent(38)
    ) {
      return true;
    } else return false;
  }

  idDocPresent(tocType: number) {
    return (
      this.tableData.filter((elem) => elem.idTipoAllegato == tocType).length > 0
    );
  }

  idDocEditPresent(tocType: number) {
    return (
      this.allegatiEditData &&
      this.allegatiEditData.filter((elem) => elem.idTipoAllegato == tocType)
        .length > 0
    );
  }

  showDialogMsg(msgType: string, msg: string) {
    this.dialogService.showDialog(
      true,
      msg,
      msgType,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
        }
      }
    );
  }

  submit(event) {
    this.tagliService
      .postDataInvio(this.editMode, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.loadDocuments();
          this.tagliService
            .getDataInvio(this.editMode)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe((response) => {
              this.dataInvio = response.dataInvio;
              this.isCompleted = true;
              this.invioFinished = false;
              this.showDialogMsg(
                "Attenzione",
                `L’istanza è stata inviata con successo all’Autorità competente`
              )
              this.emitCompleted.emit("true");
              this.router.navigate(["pfa", "tabs", JSON.parse(localStorage.getItem("tabIndex")),{tab:'interventi'}]);
            });
        }
      });
  }

  fieldToDownload(event) {
    this.tagliService
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

  downloadDichiarazione(event, tipoAllegato: string) {
    this.downloadDichiarazioneRec(event, tipoAllegato, 1);
  }

  downloadDichiarazioneRec(event, tipoAllegato: string, rip: number) {
    this.isDownloadSuccess = false;
    this.forestaliService
      .downloadDichiarazioneXdoc(this.editMode, tipoAllegato)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          //se il file è vuoto fa fino a 5 tentativi
          if (response.body.size > 5 || rip > 5) {
            let contentDisposition = response.headers.get(
              "content-disposition"
            );
            let filename = contentDisposition
              .split(";")[1]
              .split("filename")[1]
              .split("=")[1];
            this.saveFileService.saveFile(response, filename);
            this.isDownloadSuccess = true;
          } else {
            console.log(
              rip +
                " - Retry document download. Document size: " +
                response.body.size
            );
            this.downloadDichiarazioneRec(event, tipoAllegato, rip + 1);
          }
        }
      });
  }

  fieldToDelete(event, table) {
    if (table === "elenco") {
      this.tagliService
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

  closeDialog() {
    this.filePopUp = false;
  }

  visualizzaVarianti() {
    if (
      this.showListVarintiProroghe &&
      this.showListVarintiProroghe.type == "variante"
    ) {
      this.showListVarintiProroghe = null;
    } else {
      this.showListVarintiProroghe = {
        type: "variante",
        idPadre: this.editMode,
      };
    }
  }

  visualizzaInterventoAutorizzato() {
    let id =
      this.infoVarianteProroga.idPadreProroga > 0
        ? this.infoVarianteProroga.idPadreProroga
        : this.infoVarianteProroga.idPadreVariante;

    let params = { viewMode: true };
    if (this.boMode) {
      params["boModifica"] = this.boMode;
    }
    this.router.navigate(["modifica-tagli/" + id, params]);
  }

  visualizzaProroghe() {
    if (
      this.showListVarintiProroghe &&
      this.showListVarintiProroghe.type == "proroga"
    ) {
      this.showListVarintiProroghe = null;
    } else {
      this.showListVarintiProroghe = {
        type: "proroga",
        idPadre: this.editMode,
      };
    }
  }

  creaProroga() {
    this.isVariante = false;
    this.creaVarianteProroga();
  }

  creaVariante() {
    this.isVariante = true;
    this.creaVarianteProroga();
  }

  creaVarianteProroga() {
    this.showPopupVarianteProroga = false;
    this.tagliService
      .creaVarianteProroga(this.editMode, this.isVariante)
      .subscribe((res) => {
        sessionStorage.setItem(
          "tipoIstanzaId",
          this.istanzaInviata.fkTipoIstanza + ""
        );
        this.router.navigate(["modifica-tagli/" + res.istanzaId]).then(() => {
          window.location.reload();
        });
      });
  }

  openAnnullaIstanza() {
    this.annullaPupUp = true;
  }

  onMotivoAnnullamentoChange(event) {
    this.motivoAnnullamento = event.target.value;
    if (this.motivoAnnullamento.length > this.maxLengthMotivoAnnullamento) {
      this.motivoAnnullamento = this.motivoAnnullamento.substr(
        0,
        this.maxLengthMotivoAnnullamento
      );
    }
  }

  disabledAnnullamento() {
    return this.motivoAnnullamento.length < 20;
  }

  annullaIstanza() {
    this.annullaPupUp = false;
    let data = {
      idIntervento: this.editMode,
      motivazione: this.motivoAnnullamento,
    };
    this.tagliService
      .annullaIstanza(data)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        this.annullaVisible = false;
        this.isCompleted = true;
        this.router.navigate(["modifica-tagli/" + this.editMode]).then(() => {
          window.location.reload();
        });
      });
  }

  openIstanzeTaglio() {
    this.initIstanzeTaglioForm();
    this.istanzeTaglioPopUp = true;
  }

  closeIstanzeTaglio() {
    this.istanzeTaglioPopUp = false;
  }

  initIstanzeTaglioForm() {
    this.istanzeTaglioForm = new FormGroup({
      istanzaTaglio: new FormControl("", [
        Validators.required,
        Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
      ]),
    });
  }

  salvaIstanzaTaglio() {
    if (this.istanzeTaglioForm.valid) {
      let numIstanza = this.istanzeTaglioForm.get("istanzaTaglio").value;
      for (let i in this.istanzeTaglioTable) {
        if (this.istanzeTaglioTable[i].numIstanza == numIstanza) {
          this.showDialogError("Numero istanza già inserita!");
          return;
        }
      }
      this.forestaliService
        .searchIstanzaTaglio(numIstanza)
        .subscribe((response: IstanzaTaglio) => {
          if (!response.numIstanza) {
            this.showDialogError(
              "Nessuna dato trovato con numero istanza: " + numIstanza + "!"
            );
            return;
          }
          if (response.idIstanza && response.idIstanza != this.editMode) {
            this.showDialogError("Istanza già utilizzata su altro intervento!");
            return;
          }
          this.vincoloService
            .saveOrDeleteIstanzaTaglio(this.editMode, response)
            .subscribe((res) => {
              this.formatIstanzeTaglio(res);
              this.istanzeTaglioTable = res;
            });
        });
    }
  }

  showDialogError(msg) {
    this.dialogService.showDialog(
      true,
      msg,
      "Errore",
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log("BUTTON WORKS");
        }
      }
    );
  }

  getFormatDate(date) {
    if (date) {
      if (date["dayOfMonth"]) {
        return (
          this.doubleDigit("" + date["dayOfMonth"]) +
          "-" +
          this.doubleDigit("" + date["monthValue"]) +
          "-" +
          date["year"]
        );
      } else if (date.getDate) {
        return (
          this.doubleDigit("" + date.getDate()) +
          "-" +
          this.doubleDigit("" + (date.getMonth() + 1)) +
          "-" +
          date.getFullYear()
        );
      } else if (date.indexOf) {
        let vetData = date.split("-");
        return vetData[2] + "/" + vetData[1] + "/" + vetData[0];
      }
    }
  }

  doubleDigit(val: string) {
    if (val.length == 1) {
      return "0" + val;
    }
    return val;
  }

  formatIstanzeTaglio(listIstanze: IstanzaTaglio[]) {
    if (listIstanze && listIstanze.length > 0) {
      listIstanze.forEach((elem) => {
        elem.dataPresentIstanzaFormat = this.getFormatDate(
          elem.dataPresentazioneIstanza
        );
      });
    }
  }

  getNumByData(date: Date) {
    return date.getFullYear() * 10000 + date.getMonth() * 100 + date.getDate();
  }

  deleteIstanzaTaglio($event, aux) {
    let item = this.istanzeTaglioTable[$event.index];
    item.deleted = true;
    delete item.dataPresentIstanzaFormat;
    this.vincoloService
      .saveOrDeleteIstanzaTaglio(this.editMode, item)
      .subscribe((res) => {
        this.formatIstanzeTaglio(res);
        this.istanzeTaglioTable = res;
      });
  }

  getDataTermineAutorizzazione() {
    if (this.istanzaInviata) {
      let dataAut = this.istanzaInviata.dataTermineAutorizzazione;
      return dataAut != null
        ? this.getFormatDate(dataAut)
        : "definita nel provvedimento unico finale";
    }
  }

  openMappa() {
    //window.scrollTo(0, 0);
    this.forestaliService
      .getCartograficoByIdUrl(
        this.boOperatingEnabled ? 24 : 23,
        "" + this.editMode
      )
      .subscribe((response: any) => {
        this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          response["geecourl"]
        );
        if (window.location.href.indexOf("http://localhost:") == -1) {
          window.location.href = response["geecourl"];
        } else {
          //this.showGeeco = true;
          window.open(response["geecourl"], "_blank");
        }
      });
    return false;
  }

  salvaUtilizzatore() {
    // case utilizzatore
    let utilizzatore = null;
    switch (Number(this.tipoUtilizzatoreSelected)) {
      case 1:
        utilizzatore = { ...this.step2Form.soggetto };
        console.log("UTILIZZATORE ", this.tipoUtilizzatoreSelected);
        this.invioEmail();
        break;
      case 2:
        this.invioEmail();
        break;
      case 3:
        utilizzatore = {
          ...this.utilizzatorePersonaForm.getRawValue(),
          idSoggetto: 0,
        };
        console.log("UTILIZZATORE ", this.tipoUtilizzatoreSelected);
        this.utilizzatorePersonalOrCompanyForm = this.utilizzatorePersonaForm;
        this.invioEmail();
        console.log(this.showCaricaDocumenti);
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
          };
          console.log("UTILIZZATORE ", this.tipoUtilizzatoreSelected);
          this.invioEmail();
        }
        this.utilizzatorePersonalOrCompanyForm = this.utilizzatoreCompanyForm;
        break;
      default:
        break;
    }

    const step2Form: TagliStep2 = {
      tipoIstanzaId: this.step2Form.tipoIstanzaId,
      tipoAccreditamento: this.tipoAccreditamento,
      idIntervento: this.editMode,
      tipoRichiedenteId: this.step2Form.tipoRichiedenteId,
      soggetto: this.step2Form.soggetto,
      gestoreId: this.step2Form.gestoreId,
      tipoUtilizzatore: TipoUtilizzatoreEnum[this.tipoUtilizzatoreSelected],
      utilizzatore: utilizzatore,
    };

    if (utilizzatore !== null) {
      // salva utilizzatore

      this.tagliService
        .cambioUtilizzatore(this.editMode, step2Form)
        .subscribe((res) => {
          //this.showCaricaDocumenti = true;
          console.log("====================================");
          console.log("CAMBIO UTLIZZATORE ", res);
          console.log("====================================");
        });
    }
  }

  cambiaUtilizzatore() {
    this.tagliService.getStep2(this.editMode).subscribe((res) => {
      let utilizzatoreOption = CONST.TIPO_UTILIZZATORE_OPTIONS.find(
        (l) => l.label === res.tipoUtilizzatore
      );
      this.tipoUtilizzatoreSelected = utilizzatoreOption.id;
      this.currentUtilizzatore = utilizzatoreOption.descr;

      this.step2Form = res;
      this.mainContent = false;
      this.showCambioUtilizzatore = true;
      this.changeUtilizzatoreOption(this.tipoUtilizzatoreSelected);
    });
  }

  closeCambioUtilizzatore() {
    this.mainContent = true;
    this.showCambioUtilizzatore = false;
  }

  changeUtilizzatoreOption(opt) {
    this.tipoUtilizzatoreSelected = Number(opt);
    if (this.tipoUtilizzatoreSelected === 3) {
      if (this.step2Form.utilizzatore.cognome != null) {
        this.initUtilizzatorePersonalForm(this.step2Form.utilizzatore);
      } else {
        this.initUtilizzatorePersonalForm({});
      }
    }
    if (this.tipoUtilizzatoreSelected === 4) {
      if (this.step2Form.utilizzatore.denominazione != null) {
        this.initUtilizzatoreCompanyForm(this.step2Form.utilizzatore);
      } else {
        this.initUtilizzatoreCompanyForm({});
      }
    }
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

  initUtilizzatoreCompanyForm(companyData) {
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

      soggettoTaif: { value: companyData ? companyData.soggettoTaif : "" },
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

  changeTipoPGUtil(tipoPersonaGiuridica) {
    if (this.tipoUtilizzatoreSelected === 4) {
      this.isUtilizzatoreEntePubblico = tipoPersonaGiuridica === "EN";

      if (!this.boMode) {
        this.codiceFiscaleSelectItem = [];
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
                this.codiceFiscaleSelectItem.push({
                  value: element.codiceFiscale,
                  label: element.codiceFiscale,
                });
              }
            });
          });
      }
    }
  }

  chooseCompany(el) {
    console.log("====================================");
    console.log("event ", el);
    console.log("event ", el.event.value);
    console.log("event ", el.isPubblic);
    console.log("====================================");
  }

  clear(el) {
    console.log("Clear called....");
  }

  invioEmail() {
    console.log("INVIO EMAIL");
    this.invioemail = true;
    this.showCaricaDocumenti = false;
    this.tagliService
      .postDataInvioMail(this.editMode, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.loadDocuments();
          this.tagliService
            .getDataInvio(this.editMode)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe((response) => {
              this.dataInvio = response.dataInvio;
              this.isCompleted = true;
              this.invioFinished = false;
              this.emitCompleted.emit("true");
            });
        }
      });
    this.router.navigate(["visualizza-tagli"]);
  }
}
