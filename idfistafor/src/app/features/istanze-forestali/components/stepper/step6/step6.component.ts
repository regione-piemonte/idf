/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { TableHeader } from "src/app/shared/models/table-header";
import {
  PlainSestoSezione,
  DocumentoAllegato,
  IstanzaTaglio,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { ForestaliService } from "../../../services/forestali.service";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { CONST } from "src/app/shared/constants";
import { CloneVisitor } from "@angular/compiler/src/i18n/i18n_ast";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { DialogService } from "src/app/shared/services/dialog.service";
import { VincoloService } from "../../../services/vincolo.service";

@Component({
  selector: "app-step6",
  templateUrl: "./step6.component.html",
  styleUrls: ["./step6.component.css"],
})
export class Step6Component implements OnInit {
  sixthStepForm: FormGroup;
  currentYear: number = new Date().getFullYear();
  @Input() sixthStepData: PlainSestoSezione;
  @Input() editMode: number;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  label1: string =
    "di aver acquisito l’autorizzazione paesaggistica, comprensiva degli eventuali interventi di mitigazione degli impatti sul paesaggio.";
  label2: string = "di aver acquisito l’autorizzazione idrogeologica.";
  label3: string = "di aver acquisito la valutazione di incidenza.";
  allegatiLabel1: string =
    'di impegnarsi a versare il deposito cauzionale di Euro XXXX previsto per la compensazione fisica, con le modalità comunicate dal Settore competente; nel caso si sia in possesso della ricevuta di pagamento è possibile caricarla a sistema cliccando su "Scegli file" (non obbligatorio)';
  allegatiLabel2: string =
    'di impegnarsi a versare il corrispettivo in denaro di Euro XXXX previsto per la compensazione monetaria, con le modalità comunicate dal Settore competente; nel caso si sia in possesso della ricevuta di pagamento è possibile caricarla a sistema cliccando su "Scegli file" (non obbligatorio)';
  realLabel: string;
  realAllegatiLabel: string;
  tableData: DocumentoAllegato[] = [];
  elencoTableData: IstanzaTaglio[] = [];
  filteredTable: DocumentoAllegato[] = [];
  filteredIstanzi: IstanzaTaglio[] = [];
  @Output() emitActiveIndex = new EventEmitter<void>();

  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    //{ field: "note", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb" },
  ];

  elencoTableHeaders: TableHeader[] = [
    { field: "numIstanza", header: "Numero istanza" },
    { field: "dataPresentIstanzaFormat", header: "Data Presentazione istanza" },
    { field: "stato", header: "Stato" },
    { field: "tipoIstanza", header: "Tipo istanza" },
    { field: "descIntervento", header: "Descrizione Intervento" },
    { field: "specieCoinvolte", header: "Specie coinvolte" },
    { field: "massa", header: "Stima massa retraibile" },
    { field: "comuniInteressati", header: "Comuni interessati" },
    { field: "tipoIntervento", header: "Tipo Intervento" },
  ];
  isSame: string;
  filePopUp: boolean;

  compensazioneNonNecessariaType: string;

  constructor(
    private fb: FormBuilder,
    private forestaliService: ForestaliService,
    private dialogService: DialogService,
    private saveFileService: SaveFileService,
    private vincoloService: VincoloService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.realLabel = this.label1;
    this.realAllegatiLabel = this.allegatiLabel1;
    this.sixStepForm();
    this.tableData = this.sixthStepData.allegati
      ? this.sixthStepData.allegati
      : [];
    this.elencoTableData = this.sixthStepData.istanzi.istanziList
      ? this.sixthStepData.istanzi.istanziList
      : [];
    this.filteredTable = [...this.tableData];
    this.elencoTableData.forEach((item, index) => {
      this.filteredIstanzi.push(this.getElabClone(item));
    });

    this.forestaliService.getStep4(this.editMode).subscribe((res) => {
      this.compensazioneNonNecessariaType = res.nonNecessaria21
        ? "nc21"
        : res.nonNecessaria
        ? "nc"
        : null;
    });
  }

  onPaesaggisticaDataChange() {
    this.isDataPaesagisticaInvalid();
  }

  isDataPaesagisticaInvalid() {
    let dataAutPaesag = this.sixthStepForm.get("paesaggisticaData").value;
    if (
      ("nc21" == this.compensazioneNonNecessariaType &&
        new Date("2021-10-21") > dataAutPaesag) ||
      ("nc" == this.compensazioneNonNecessariaType &&
        new Date("2021-10-20") < dataAutPaesag)
    ) {
      this.showDialogError(
        "La data impostata va in conflitto con la scelta della compensazione NON NECESSARIA selezionata nella sezione 4",
        "ATTENZIONE"
      );
      return true;
    }
    if (
      this.sixthStepForm.get("istanzi").value &&
      (!this.filteredIstanzi || this.filteredIstanzi.length == 0)
    ) {
      this.showDialogError(
        "E' obbligatorio inserire almeno un riferimento di istanza di taglio presentata",
        "ATTENZIONE"
      );
      return true;
    }
    return false;
  }

  private sixStepForm() {
    this.sixthStepForm = this.fb.group({
      proprieta: [
        this.sixthStepData ? this.sixthStepData.proprieta.isOwner : false,
      ],
      dissensi: [
        this.sixthStepData ? this.sixthStepData.dissensi.haDissenso : false,
      ],
      paesaggistica: [
        this.sixthStepData ? this.sixthStepData.paesaggistica.checked : false,
        this.sixthStepData.paesaggistica.required ? Validators.required : "",
      ],
      paesaggisticaNumero: [
        this.sixthStepData
          ? this.sixthStepData.paesaggistica.numeroAutorizzazione
          : "",
        this.sixthStepData.paesaggistica.checked ? Validators.required : "",
      ],
      paesaggisticaData: [
        this.sixthStepData.paesaggistica.dataAutorizzazione
          ? new Date(
              this.sixthStepData.paesaggistica.dataAutorizzazione.toString()
            )
          : "",
        this.sixthStepData.paesaggistica.checked ? Validators.required : "",
      ],
      paesaggisticaRilasciata: [
        this.sixthStepData
          ? this.sixthStepData.paesaggistica.rilasciataAutorizzazione
          : "",
        this.sixthStepData.paesaggistica.checked ? Validators.required : "",
      ],
      vincIdrogeologico: [
        this.sixthStepData
          ? this.sixthStepData.vincIdrogeologico.checked
          : false,
        this.sixthStepData.vincIdrogeologico.required
          ? Validators.required
          : "",
      ],
      vincIdrogeologicoNumero: [
        this.sixthStepData
          ? this.sixthStepData.vincIdrogeologico.numeroAutorizzazione
          : "",
        this.sixthStepData.vincIdrogeologico.checked ? Validators.required : "",
      ],
      vincIdrogeologicoData: [
        this.sixthStepData.vincIdrogeologico.dataAutorizzazione
          ? new Date(
              this.sixthStepData.vincIdrogeologico.dataAutorizzazione.toString()
            )
          : "",
        this.sixthStepData.vincIdrogeologico.checked ? Validators.required : "",
      ],
      vincIdrogeologicoRilasciata: [
        this.sixthStepData
          ? this.sixthStepData.vincIdrogeologico.rilasciataAutorizzazione
          : "",
        this.sixthStepData.vincIdrogeologico.checked ? Validators.required : "",
      ],
      valIncidenza: [
        this.sixthStepData ? this.sixthStepData.valIncidenza.checked : false,
        this.sixthStepData.valIncidenza.required ? Validators.required : "",
      ],
      valIncidenzaNumero: [
        this.sixthStepData
          ? this.sixthStepData.valIncidenza.numeroAutorizzazione
          : "",
        this.sixthStepData.valIncidenza.checked ? Validators.required : "",
      ],
      valIncidenzaData: [
        this.sixthStepData.valIncidenza.dataAutorizzazione
          ? new Date(
              this.sixthStepData.valIncidenza.dataAutorizzazione.toString()
            )
          : "",
        this.sixthStepData.valIncidenza.checked ? Validators.required : "",
      ],
      valIncidenzaRilasciata: [
        this.sixthStepData
          ? this.sixthStepData.valIncidenza.rilasciataAutorizzazione
          : "",
        this.sixthStepData.valIncidenza.checked ? Validators.required : "",
      ],
      altriPareri: [
        this.sixthStepData ? this.sixthStepData.altriPareri.checked : false,
      ],
      altriPareriTesto: [
        this.sixthStepData ? this.sixthStepData.altriPareri.testo : "",
      ],
      compFisica: [
        this.sixthStepData ? this.sixthStepData.compFisica.checked : false,
      ],
      compMonetaria: [
        this.sixthStepData ? this.sixthStepData.compMonetaria.checked : false,
      ],
      istanzi: [
        this.sixthStepData ? this.sixthStepData.istanzi.checked : false,
      ],
      notaTesto: [
        this.sixthStepData.nota.testo ? this.sixthStepData.nota.testo : "",
      ],
      numeroInstanza: ["", Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)],
    });
  }

  get elencoAllegatiForm() {
    return this.fb.group({
      tipoAllegato: [""],
      nomeFile: [""],
      note: [""],
      kb: [""],
    });
  }

  get elencoFisicaForm() {
    return this.fb.group({
      numeroIstanza: [""],
      dataInvio: [""],
      richiedente: [""],
      comune: [""],
      intervento: [""],
      stato: [""],
    });
  }

  onChangeCheckAutorizzazione(evt) {
    let ckeck = evt.target.checked;
    let id = evt.target.id;
    console.log("onChangeCheckAutorizzazione change:");
    console.log(evt);
    let item = this.sixthStepForm.get(id + "Numero");
    item.setValidators(ckeck ? [Validators.required] : []);
    item.updateValueAndValidity();
    item = this.sixthStepForm.get(id + "Data");
    item.setValidators(ckeck ? [Validators.required] : []);
    item.updateValueAndValidity();
    item = this.sixthStepForm.get(id + "Rilasciata");
    item.setValidators(ckeck ? [Validators.required] : []);
    item.updateValueAndValidity();
  }

  fieldToDownload(event) {
    this.forestaliService.downloadStep6(event).subscribe((response) => {
      let contentDisposition = response.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
      this.saveFileService.saveFile(response, filename);
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
              this.filteredTable.splice(event.index, 1);
              this.tableData.splice(event.index, 1);
            }
          });
        });
    } else if (table === "istanza") {
      this.elencoTableData.forEach((item, index) => {
        if (item.numIstanza === event.rowIndex) {
          if (item.idIstanza) {
            item.deleted = true;
          } else {
            this.elencoTableData.splice(index, 1);
          }
        }
      });
      this.filteredIstanzi.forEach((item, index) => {
        if (item.numIstanza === event.rowIndex) {
          this.filteredIstanzi.splice(index, 1);
        }
      });
    }
  }

  //FIX GP tck12b
  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  uploadImage(event, tipo) {
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

    const formData: FormData = new FormData();
    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    if (
      this.forestaliService.isNamePresent(event.files[0].name, this.tableData)
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
          this.filteredTable.push(response);
        });
    }
    let cmpUpload = document.getElementById("fileUpload_" + tipo);
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  checkAllegatoByType(type) {
    for (let i in this.filteredTable) {
      if (this.filteredTable[i]["descrTipoAllegato"] === type) {
        return true;
      }
    }
    return false;
  }

  closeDialog() {
    this.filePopUp = false;
  }

  ricerca() {
    let numIstanza = this.sixthStepForm.get("numeroInstanza").value;
    for (let i in this.filteredIstanzi) {
      if (this.filteredIstanzi[i].numIstanza == numIstanza) {
        this.showDialogError("Numero istanza già inserita!", "Errore");
        return;
      }
    }
    this.forestaliService
      .searchIstanzaTaglio(numIstanza)
      .subscribe((response: IstanzaTaglio) => {
        if (!response.numIstanza) {
          this.showDialogError(
            "Nessuna dato trovato con numero istanza: " + numIstanza + "!",
            "Errore"
          );
          return;
        }
        if (response.idIstanza && response.idIstanza != this.editMode) {
          this.showDialogError(
            "Istanza già utilizzata su altro intervento!",
            "Errore"
          );
          return;
        }
        this.elencoTableData.push(response);
        this.filteredIstanzi.push(this.getElabClone(response));
      });
  }

  showDialogError(msg, type) {
    this.dialogService.showDialog(
      true,
      msg,
      type,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log("BUTTON WORKS");
        }
      }
    );
  }

  getElabClone(elem: IstanzaTaglio) {
    let clone = JSON.parse(JSON.stringify(elem));
    clone.dataPresentIstanzaFormat = this.getFormatDate(
      elem.dataPresentazioneIstanza
    );
    clone["massa"] = elem.stimaMassaRetraibile + " " + elem.unita;
    return clone;
  }

  getFormatDate(date) {
    return (
      this.doubleDigit("" + date["dayOfMonth"]) +
      "-" +
      this.doubleDigit("" + date["monthValue"]) +
      "-" +
      date["year"]
    );
  }

  doubleDigit(val: string) {
    if (val.length == 1) {
      return "0" + val;
    }
    return val;
  }

  save(nextStep?: boolean) {
    if (this.isDataPaesagisticaInvalid()) {
      return;
    }
    const postStep6: any = {
      proprieta: {
        isOwner: this.sixthStepForm.get("proprieta").value,
      },
      dissensi: {
        haDissenso: this.sixthStepForm.get("dissensi").value,
      },
      paesaggistica: {
        checked: this.sixthStepForm.get("paesaggistica").value,
        numeroAutorizzazione: this.sixthStepForm.get("paesaggisticaNumero")
          .value,
        dataAutorizzazione: this.forestaliService.formatDate(
          this.sixthStepForm.get("paesaggisticaData").value
        ),
        // dataAutorizzazione: this.forestaliService.formatDate(new Date()),
        rilasciataAutorizzazione: this.sixthStepForm.get(
          "paesaggisticaRilasciata"
        ).value,
      },
      vincIdrogeologico: {
        checked: this.sixthStepForm.get("vincIdrogeologico").value,
        numeroAutorizzazione: this.sixthStepForm.get("vincIdrogeologicoNumero")
          .value,
        dataAutorizzazione: this.forestaliService.formatDate(
          this.sixthStepForm.get("vincIdrogeologicoData").value
        ),
        // dataAutorizzazione: this.forestaliService.formatDate(new Date()),
        rilasciataAutorizzazione: this.sixthStepForm.get(
          "vincIdrogeologicoRilasciata"
        ).value,
      },
      valIncidenza: {
        checked: this.sixthStepForm.get("valIncidenza").value,
        numeroAutorizzazione:
          this.sixthStepForm.get("valIncidenzaNumero").value,
        dataAutorizzazione: this.forestaliService.formatDate(
          this.sixthStepForm.get("valIncidenzaData").value
        ),
        // dataAutorizzazione: this.forestaliService.formatDate(new Date()),
        rilasciataAutorizzazione: this.sixthStepForm.get(
          "valIncidenzaRilasciata"
        ).value,
      },
      altriPareri: {
        checked: this.sixthStepForm.get("altriPareri").value,
        testo: this.sixthStepForm.get("altriPareriTesto").value,
      },
      compFisica: {
        checked: this.sixthStepForm.get("compFisica").value,
      },
      compMonetaria: {
        checked: this.sixthStepForm.get("compMonetaria").value,
      },
      // altroAllega: {
      //   checked: this.sixthStepForm.get('altroAllega').value
      // },
      allegati: this.tableData,
      istanzi: {
        checked: this.sixthStepForm.get("istanzi").value,
        istanziList: this.sixthStepData.istanzi.visible
          ? this.elencoTableData
          : [],
      },
      nota: {
        testo: this.sixthStepForm.get("notaTesto").value,
      },
    };
    this.forestaliService
      .saveStep6(this.editMode, postStep6)
      .subscribe((response: any) => {
        nextStep && this.emitActiveIndex.emit();
      });
  }

  onChangeNumeroIstanza() {
    this.sixthStepForm.get("numeroInstanza").updateValueAndValidity();
  }

  disabledSave() {
    // if(this.sixthStepForm.get('compFisica').value &&
    //   (!this.checkAllegatoByType('Ricevuta deposito cauzionale compensazione fisica')
    //   || this.filteredIstanzi.length == 0)){
    //   return true;
    // }
    // if(this.sixthStepForm.get('compMonetaria').value &&
    //   !this.checkAllegatoByType('Ricevuta versamento compensazione monetaria')){
    //   return true;
    // }

    return this.sixthStepForm.invalid; //|| this.tableData.length === 0
    //||(this.sixthStepData.istanzi.visible ? (this.elencoTableData.length === 0 ? true : false) : false )
  }

  continue() {
    this.save(true);
  }
}
