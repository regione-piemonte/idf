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
import { Subject } from "rxjs";

import { UserChoiceModel } from "src/app/shared/models/user-choice.model";
import { ForestaliService } from "../../../services/forestali.service";
import {
  DocumentoAllegato,
  IstanzaTaglio,
  PlainSestoSezione,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { IdfistaforHeaderHelper } from "../../../idfistafor-header-helper";
import { IstanzaInviata } from "src/app/shared/models/view-instance";
import { DialogService } from "../../../../../shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { VincoloService } from "../../../services/vincolo.service";
import { Router } from "@angular/router";
import { InfoVarianteProrogaModel } from "src/app/shared/models/info-variante-proroga.model";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { CONST } from "src/app/shared/constants";
import { TableHeader } from "src/app/shared/models/table-header";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { InfoDocsPagamenti } from "src/app/shared/models/info-doc-pagamenti.model";
import { HomeModel } from "src/app/shared/models/home.model";

@Component({
  selector: "app-signature-vid",
  templateUrl: "./signature-vid.component.html",
  styleUrls: ["./signature-vid.component.css"],
})
export class SignatureVidComponent implements OnInit, OnChanges {
  @Output() emitCompleted = new EventEmitter();
  tableData: DocumentoAllegato[];
  allegatiEditData: DocumentoAllegato[] = [];
  @Input() editMode: number;
  @Input() activeIndex: number;
  @Input() viewMode: boolean;
  @Input() boMode: boolean;
  @Input() istanzaInviata: IstanzaInviata;
  @Input() boOperatingEnabled: boolean;
  @Input() user: HomeModel = {};

  localEditMode: number;
  unsubscribe$ = new Subject<void>();
  tipoAccreditamento = null;
  isCompleted = false;
  dataInvio = null;
  docType = null;
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

  infoDocsPagamenti: InfoDocsPagamenti;

  istanzeTaglioTable: IstanzaTaglio[] = [];
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  mapUrl: SafeResourceUrl;

  constructor(
    private forestaliService: ForestaliService,
    private saveFileService: SaveFileService,
    private dialogService: DialogService,
    private vincoloService: VincoloService,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {}

  ngOnInit() {
    this.it = CONST.IT;
    if (this.boMode === false) {
      this.forestaliService
        .getAdpfor()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res: UserChoiceModel) => {
          this.tipoAccreditamento = res.fkTipoAccreditamento;
        });
    }
    this.vincoloService
      .getIstanzeTaglioByIdIntervento(this.editMode)
      .subscribe((res) => {
        this.formatIstanzeTaglio(res);
        this.istanzeTaglioTable = res;
      });
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
    if (this.localEditMode != this.editMode) {
      this.onloadOrChange();
    }

    if (this.boMode) {
      if (
        this.istanzaInviata.fkStatoIstanza == 3 &&
        !this.istanzaInviata.dataFineIntervento &&
        !this.dataFineInterventoForm
      ) {
        this.initDataFineInterventoForm();
      }
    }
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
    this.vincoloService.updateDataFineIntervento(values).subscribe((res) => {
      this.dataFineInterventoForm = null;
      this.istanzaInviata.dataFineIntervento = values.dataFineIntervento;
    });
  }

  filterProfessionista(event) {
    if (!this.professionistiList) {
      this.vincoloService
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
    this.vincoloService
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

    this.vincoloService
      .getInfoVarianteProroga(this.editMode)
      .subscribe((res) => (this.infoVarianteProroga = res));

    this.vincoloService.getinfoCauzione(this.editMode).subscribe((res) => {
      this.infoDocsPagamenti = res;
      //this.isRicevutaCauzioneMancante = res.isCauzioneMancante
      this.isCompensazioneFisica = res.compensazioneType == "F";
    });

    this.isCompleted = this.activeIndex >= 6 ? true : false;
    this.loadDocuments();
  }

  isDocsPagamentiMisssing() {
    return (
      this.infoDocsPagamenti &&
      (this.infoDocsPagamenti.cauzioneMancante ||
        this.infoDocsPagamenti.compensazioneFisicaMancante ||
        this.infoDocsPagamenti.compensazioneMonetariaMancante)
    );
  }

  loadDocuments() {
    this.vincoloService
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
  }

  inserisciInElenco(event) {
    this.preparedDocuments.forEach((document) => {
      this.tableData.push(document);
    });
    this.preparedDocuments = [];
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
    const formData: FormData = new FormData();
    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    if (
      this.forestaliService.isNamePresent(
        event.files[0].name,
        this.tableData ? this.tableData : []
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
        });
    }
    let cmpUpload = document.getElementById(
      "fileUpload_" + (tipo == 24 ? tipo : 0)
    );
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  //FIX GP tck12b
  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  uploadRicevutaCauzione(event, tipo) {
    let file = event.files[0];
    this.docsRicevutePagamento[tipo] = file;

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

    this.allegatiEditData.push({
      idTipoAllegato: tipo,
      nomeAllegato: file.name,
      descrTipoAllegato: this.getDescrizioneDocPagamento(tipo),
      dimensioneAllegatoKB: Math.round(file.size / 1000),
    });
    let cmpUpload = document.getElementById("fileUpload_" + tipo);
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  getDescrizioneDocPagamento(idTipoDoc: number) {
    if (this.infoDocsPagamenti) {
      let listDocs = this.infoDocsPagamenti.listDocs.filter(
        (item) => item.idTipoAllegato == idTipoDoc
      );
      if (listDocs.length > 0) {
        return listDocs[0].descrTipoAllegato;
      }
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
      formData.append(
        "form_" + tipoAllegato,
        this.docsRicevutePagamento[tipoAllegato]
      );
      formData.append(
        "fileName_" + tipoAllegato,
        this.docsRicevutePagamento[tipoAllegato].name
      );
      elem = document.getElementById(
        "noteDocumento_" + tipoAllegato
      ) as HTMLInputElement;
      formData.append("note_" + tipoAllegato, encodeURIComponent(elem.value));
    }

    this.vincoloService
      .uploadRicevutePagamento(formData, this.editMode)
      .subscribe((response: DocumentoAllegato) => {
        this.tableData.push(response);
        this.vincoloService.getinfoCauzione(this.editMode).subscribe((res) => {
          this.infoDocsPagamenti = res;
          this.loadDocuments();
          this.dialogService.showDialog(
            true,
            "Integrazione inviata correttamente!",
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
    let items = document.getElementsByName(name);
    Array.from(items).forEach((item) => (item["disabled"] = value));
  }

  idDichiarazioneUploaded() {
    // console.log('dataTable dim:' + this.tableData?this.tableData.length:null);
    // if(this.tableData && this.tableData.length > 1){console.log(this.tableData)}
    for (let i in this.tableData ? this.tableData : []) {
      if (
        this.tableData[i]["idTipoAllegato"] === 22 ||
        this.tableData[i]["idTipoAllegato"] === 23
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
    if (this.docType == 22) {
      return true;
    } else if (this.docType == 23 && this.tableData && this.idDocPresent(24)) {
      return true;
    }
    return false;
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

  submit(event) {
    this.forestaliService
      .postDataVidInvio(this.editMode, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.loadDocuments();
          this.forestaliService
            .getDataInvio(this.editMode)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe((response) => {
              this.dataInvio = response.dataInvio;
              this.isCompleted = true;
              this.invioFinished = false;
              this.emitCompleted.emit("true");
              this.dialogService.showDialog(
                true,
                "Istanza inviata correttamente!",
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
        }
      });
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

  downloadDichiarazione(event, tipoAllegato: number) {
    this.downloadDichiarazioneRec(event, tipoAllegato, 1);
  }

  downloadDichiarazioneRec(event, tipoAllegato: number, rip: number) {
    this.isDownloadSuccess = false;
    this.forestaliService
      .downloadDichiarazione(this.editMode, tipoAllegato)
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
      this.vincoloService
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
    this.router.navigate(["modifica-vid/" + id, params]);
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
    this.creaVarianteProroga(this.istanzaInviata.fkTipoIstanza == 4);
  }

  creaVariante() {
    this.isVariante = true;
    this.showPopupVarianteProroga = true;
  }

  creaVarianteProroga(isCompetenzaRegionale: boolean) {
    this.showPopupVarianteProroga = false;
    this.vincoloService
      .creaVarianteProroga(
        this.editMode,
        this.isVariante,
        isCompetenzaRegionale
      )
      .subscribe((res) => {
        sessionStorage.setItem(
          "tipoIstanzaId",
          isCompetenzaRegionale ? "4" : "5"
        );
        this.router.navigate(["modifica-vid/" + res.istanzaId]);
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
        this.boOperatingEnabled ? 18 : 20,
        "" + this.editMode
      )
      .subscribe((response: any) => {
        this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          response["geecourl"]
        );
        if (this.boOperatingEnabled) {
          window.open(response["geecourl"], "_blank");
        } else {
          window.open(response["geecourl"], "_blank");
        }
      });
    return false;
  }
}
