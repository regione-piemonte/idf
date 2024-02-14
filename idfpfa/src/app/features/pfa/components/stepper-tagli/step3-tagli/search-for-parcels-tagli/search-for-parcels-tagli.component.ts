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
  EventEmitter,
  Output,
} from "@angular/core";
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from "@angular/forms";
import { ForestaliService } from "src/app/features/pfa/services/forestali.service";
import { takeUntil, map } from "rxjs/operators";
import { Subject } from "rxjs";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { ParticleCadastral } from "src/app/shared/models/particle-cadastral";
import { ShowParcel } from "src/app/shared/models/particle-cadastral";
import { CONST } from "src/app/shared/constants";
import { VincoloService } from "src/app/features/pfa/services/vincolo.service";
import { TagliService } from "src/app/features/pfa/services/tagli.service";

@Component({
  selector: "search-for-parcels-tagli",
  templateUrl: "./search-for-parcels-tagli.component.html",
  styleUrls: ["./search-for-parcels-tagli.component.css"],
})
export class SearchForParcelsTagliComponent implements OnInit, OnDestroy {
  searchForParcelsForm: FormGroup;
  @Output() emitShowParcels = new EventEmitter<ParticleCadastral>();
  @Input() idIntervento: number;
  @Input() listParticelle: ShowParcel[];
  unsubscribe$ = new Subject<void>();
  provinciaFull: ProvinciaModel[];
  provincia: ProvinciaModel[];
  comune: ComuneModel[];
  sezione: any[];
  foglio: any[];
  particella: any[];
  duplicatedItem: boolean = false;
  particellaNotFound: boolean = false;
  selectedPlainParticella: ParticleCadastral[];
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    // private vincoloService: VincoloService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.initForm();
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  private initForm() {
    this.searchForParcelsForm = this.fb.group({
      provincia: [null, [Validators.required, Validators.maxLength(50)]],
      comuneCatastale: [
        { value: null, disabled: true },
        [Validators.required, Validators.maxLength(50)],
      ],
      sezione: ["", [Validators.maxLength(2)]],
      foglio: ["", [Validators.required, Validators.maxLength(5)]],
      particella: ["", [Validators.required, Validators.maxLength(10)]],
    });
    /* this.forestaliService.getProvincia().pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: ProvinciaModel[]) => { this.provinciaFull = res; } ); */
  }

  onSelectProvincia(event: any) {
    this.searchForParcelsForm.get("comuneCatastale").reset();
    this.searchForParcelsForm.get("comuneCatastale").enable();
  }

  onClearProvincia(event: any) {
    this.searchForParcelsForm.get("provincia").reset();
    this.searchForParcelsForm.get("comuneCatastale").reset();
    this.searchForParcelsForm.get("comuneCatastale").disable();
  }

  filterProvincia(event: any) {
    this.forestaliService
      .getProvincia()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ProvinciaModel[]) => {
        this.provincia = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Provincia"
        );
      });
  }

  filterComune(event: any) {
    this.forestaliService
      .getComuneByProvincia(this.getProvincia())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ComuneModel[]) => {
        this.comune = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Comune"
        );
      });
  }

  inserisciInElenco() {
    this.duplicatedItem = false;
    this.particellaNotFound = false;
    var particella: ParticleCadastral = {};
    particella.comune = this.searchForParcelsForm.get("comuneCatastale").value;
    particella.sezione = this.searchForParcelsForm.get("sezione").value;
    particella.foglio = this.searchForParcelsForm.get("foglio").value;
    particella.particella = this.searchForParcelsForm.get("particella").value;

    let particellaCode =
      particella.comune.denominazioneComune +
      "-" +
      particella.sezione +
      "-" +
      particella.foglio +
      "-" +
      particella.particella;

    this.listParticelle.forEach((item, index) => {
      let pCode =
        item.comune +
        "-" +
        (item.sezione ? item.sezione : "") +
        "-" +
        item.foglio +
        "-" +
        item.particella;
      if (pCode == particellaCode) {
        this.duplicatedItem = true;
        return;
      }
    });
    console.log({ particella });
    // if(!this.duplicatedItem) {
    this.tagliService
      .insertGeometryFromSingleParticella(this.idIntervento, particella)
      .subscribe((res) => {
        if (res["error"]) this.particellaNotFound = true;
        else {
          particella.supCatastale = res["supCatastale"];
          particella.id = res["id"];
          this.emitShowParcels.emit(particella);
        }
      });
    //}
  }

  getProvincia() {
    return this.searchForParcelsForm.get("provincia").value.istatProv;
  }

  getComuneCatastale() {
    return this.searchForParcelsForm.get("comuneCatastale").value.idComune;
  }

  getSezione() {
    return this.searchForParcelsForm.get("sezione").value.value;
  }

  getFoglio() {
    return this.searchForParcelsForm.get("foglio").value.value;
  }

  getParticellla() {
    return this.searchForParcelsForm.get("particella").value.value;
  }
}
