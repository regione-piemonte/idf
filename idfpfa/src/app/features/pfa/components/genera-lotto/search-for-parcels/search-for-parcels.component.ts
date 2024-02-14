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
  OnDestroy,
  Input,
  OnChanges,
} from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Subject } from "rxjs";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { ComuneModel, ComuniModel } from "src/app/shared/models/comune.model";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { takeUntil } from "rxjs/operators";
import { CONST } from "src/app/shared/constants";
import { PropCatastos } from "src/app/shared/models/data-taglio";
import { PropCatastoPipe } from "src/app/shared/pipes/prop-catasto.pipe";
import {
  ParticleCadastral,
  ShowParcel,
} from "src/app/shared/models/particle-cadastral";
import { PfaInterventoService } from "src/app/shared/services/pfa-intervento.service";
import { DialogService } from "src/app/shared/services/dialog.service";

@Component({
  selector: "app-search-for-parcels",
  templateUrl: "./search-for-parcels.component.html",
  styleUrls: ["./search-for-parcels.component.css"],
})
export class SearchForParcelsComponent implements OnInit, OnDestroy {
  searchForParcelsForm: FormGroup;
  @Output() emitShowParcels = new EventEmitter();
  @Output() emitIdInterventoForEdit = new EventEmitter();
  @Input() pfaPlanId: number;
  @Input() formDisabled: boolean = true;
  @Input() idIntervento: number;
  @Input() idInterventoForEdit: number;
  @Input() editMode: number | null;
  @Input() parcelleTable: ShowParcel[];
  unsubscribe$ = new Subject<void>();
  provinciaFull: ProvinciaModel[];
  provincia: ProvinciaModel[];
  comune: ComuniModel[];
  propCatastosData: any[];
  sezioneData: any[];
  foglioData: any[];
  particellaData: any[];
  particelleCadastral: ParticleCadastral;
  visibleSearchForParcels: boolean = true;

  constructor(
    private pfaService: PfaSampleService,
    private fb: FormBuilder,
    private propCatastoPipe: PropCatastoPipe,
    private dialogService: DialogService,
    private pfaInterventoService: PfaInterventoService
  ) {}

  ngOnInit() {
    this.initForm();
    this.pfaInterventoService.polygonoSubject.subscribe((polygonoEvento) => {
      //this.visibleSearchForParcels = !polygonoEvento.payload.visibleSearchForParcels;
    });
  }

  private initForm() {
    this.searchForParcelsForm = this.fb.group({
      comune: [{ value: "" }, [Validators.required, Validators.maxLength(50)]],
      sezione: [{ value: null, disabled: true }, Validators.required],
      foglio: [{ value: null, disabled: true }, Validators.required],
      particella: [{ value: null, disabled: true }, Validators.required],
    });
    this.formDisabled = false;
  }

  onSelectProvincia(event) {
    this.searchForParcelsForm.get("comuneCatastale").reset();
    this.searchForParcelsForm.get("comuneCatastale").enable();
  }
  onClearProvincia(event) {
    this.searchForParcelsForm.get("comuneCatastale").reset();
    this.searchForParcelsForm.get("comuneCatastale").disable();
  }

  inserisciInElenco() {
    /* if (!this.idIntervento && !this.idInterventoForEdit) {
      let tabIndex = parseInt(localStorage.getItem("tabIndex"), 10);
      console.log("inserisciInElenco", tabIndex);
      this.pfaService.creaIntervento(tabIndex).subscribe((res) => {
        if (res["ERROR"]) {
          this.dialogService.showDialogMsg("Errore", res["ERROR"], true);
        } else {
          console.log("pasas", res.istanzaId);
          this.idIntervento = res.istanzaId;
          this.emitIdInterventoForEdit.emit(this.idIntervento);
          this.inserisciParticella();
        }
      });
    } else { */
      this.inserisciParticella();
    /* } */
  }

  getParticellaStr(item: ShowParcel) {
    return (
      item.comune +
      "-" +
      item.sezione +
      "-" +
      item.foglio +
      "-" +
      item.particella
    );
  }

  inserisciParticella() {
    console.log("inserisciParticella");
    let uniqueParticela: PropCatastos = this.findUniqueParticela(
      this.searchForParcelsForm.get("comune").value,
      this.searchForParcelsForm.get("sezione").value.field,
      this.searchForParcelsForm.get("foglio").value.field,
      this.searchForParcelsForm.get("particella").value.field
    );
    let isDuplicated = false;
    if (this.parcelleTable && this.parcelleTable.length > 0) {
      for (let i = 0; i < this.parcelleTable.length && !isDuplicated; i++) {
        if (
          this.getParticellaStr(uniqueParticela) ===
          this.getParticellaStr(this.parcelleTable[i])
        ) {
          isDuplicated = true;
        }
      }
    }
    if (isDuplicated) {
      this.dialogService.showDialogMsg(
        "Errore",
        "Particella selezionata già inserita!",
        true
      );
      return;
    }
    this.particelleCadastral = {
      id: uniqueParticela.idGeoPlPropCatasto,
      sezione: uniqueParticela.sezione,
      foglio: uniqueParticela.foglio,
      particella: uniqueParticela.particella,
      supCatastale: uniqueParticela.supCartograficaHa,
      supIntervento: uniqueParticela.supCartograficaHa,
    };
    this.idInterventoForEdit =  parseInt(localStorage.getItem("idInterventoParameter"), 10);
    if (this.idInterventoForEdit != undefined) {
      console.log(
        "pfaService",
        this.idInterventoForEdit,
        this.particelleCadastral
      );
      this.pfaService
        .insericiInElenco(this.editMode || this.idInterventoForEdit, this.particelleCadastral)
        .subscribe((res) => {
          this.emitShowParcels.emit(uniqueParticela);
        });
    } else {
      console.log(
        "pfaServicefalse",
        this.idInterventoForEdit,
        this.particelleCadastral
      );
      this.pfaService
        .insericiInElenco(this.editMode || this.idIntervento, this.particelleCadastral)
        .subscribe((res) => {
          this.emitShowParcels.emit(uniqueParticela);
        });
    }
  }
  findUniqueParticela(
    comune: any,
    sezione: number,
    foglio: number,
    particella: number
  ): PropCatastos {
    let items = this.propCatastosData.filter((item) => {
      if (
        item.fkComune === comune.idComune &&
        item.sezione === sezione.toString() &&
        item.foglio === foglio &&
        item.particella === particella.toString()
      ) {
        return item;
      } else {
        return null;
      }
    });
    let unique: PropCatastos = items[0];
    unique.comune = this.comune[0];
    return unique;
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
  getProvincia() {
    return this.searchForParcelsForm.get("provincia").value.istatProv;
  }

  filterComune(event) {
    this.searchForParcelsForm.reset();
    let tabIndex = parseInt(localStorage.getItem("tabIndex"), 10);
    console.log("createInterventoAndOpenGeeco", tabIndex);
    this.pfaService
      .getComuniForPfaID(tabIndex)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ComuneModel[]) => {
        this.comune = this.pfaService.autocompleteFilter(event, res, "Comune");
      });
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").disable();
  }

  onSelectComune(event: any) {
    let tabIndex = parseInt(localStorage.getItem("tabIndex"), 10);
    console.log("createInterventoAndOpenGeeco", tabIndex);
    this.pfaService
      .getCatastiByPfaAndComune(tabIndex, event.idComune)
      .subscribe((res: PropCatastos[]) => {
        this.propCatastosData = res;
        this.sezioneData = this.propCatastoPipe.transform(
          this.propCatastosData,
          CONST.SEZIONE
        );
        // this.searchForParcelsForm.patchValue({
        //   sezione: { field: this.sezioneData[0].field },
        // });
      });
    this.searchForParcelsForm.get("sezione").enable();
  }

  onClearComune(event: any) {
    this.searchForParcelsForm.get("sezione").disable();
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").disable();
    this.initForm();
  }

  filterSezione(event: any) {
    this.sezioneData = this.pfaService.autocompleteFilter(
      event,
      this.sezioneData,
      CONST.SEZIONE
    );
    this.searchForParcelsForm.get("particella").disable();
    this.searchForParcelsForm.get("particella").reset();
    this.formDisabled = true;
  }

  onSelectSezione(event: any) {
    this.foglioData = this.propCatastoPipe.transform(
      this.propCatastosData,
      CONST.FOGLIO,
      this.searchForParcelsForm.value
    );

    this.foglioData.sort((a, b) => (a.id > b.id ? 1 : -1));

    // this.searchForParcelsForm.patchValue({
    //   foglio: { field: this.foglioData[0].field },
    // });
    this.searchForParcelsForm.get("foglio").enable();
    this.searchForParcelsForm.get("foglio").patchValue("");
    this.formDisabled = true;
  }

  filterFoglio(event: any) {
    this.foglioData = this.pfaService.autocompleteFilter(
      event,
      this.foglioData,
      CONST.FOGLIO
    );
  }

  onSelectFoglio(event: any) {
    this.particellaData = this.propCatastoPipe.transform(
      this.propCatastosData,
      CONST.PARTICELLA,
      this.searchForParcelsForm.value
    );
    // this.searchForParcelsForm.patchValue({
    //   particella: { field: this.particellaData[0].field },
    // });
    this.particellaData.sort((a, b) => (a.id > b.id ? 1 : -1));
    this.searchForParcelsForm.get("particella").enable();
    this.searchForParcelsForm.get("particella").patchValue("");
    this.formDisabled = true;
  }

  filterParticella(event: any) {
    this.particellaData = this.pfaService.autocompleteFilter(
      event,
      this.particellaData,
      CONST.PARTICELLA
    );
  }

  onSelectParticela(event: any) {
    this.formDisabled = false;
  }
}
