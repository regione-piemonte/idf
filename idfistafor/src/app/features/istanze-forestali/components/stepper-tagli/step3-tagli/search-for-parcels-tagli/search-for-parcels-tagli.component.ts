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
import { ForestaliService } from "src/app/features/istanze-forestali/services/forestali.service";
import { takeUntil, map, catchError } from "rxjs/operators";
import { Observable, Subject, of } from "rxjs";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { ParticleCadastral } from "src/app/shared/models/particle-cadastral";
import { ShowParcel } from "src/app/shared/models/particle-cadastral";
import { CONST } from "src/app/shared/constants";
import { VincoloService } from "src/app/features/istanze-forestali/services/vincolo.service";
import { TagliService } from "src/app/features/istanze-forestali/services/tagli.service";
import { SezioneModel } from "src/app/shared/models/sezione.model";
import {
  FoglioModel,
  PropertiesModel,
} from "src/app/shared/models/foglio.model";
import {
  ParticellaModel,
  PropertiesModelParticella,
} from "src/app/shared/models/particella.model";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { DialogService } from "src/app/shared/services/dialog.service";

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
  data: FoglioModel[];
  data_particella: ParticellaModel[];
  res_passaggio: PropertiesModel[] = [];
  res_particella: PropertiesModelParticella[] = [];
  codice_belfiore_selezionato: string;
  sezione_selezionata: string;
  foglio_selezionato: string;
  geometria_selezionata;
  particellaObj: any[] = [];

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    // private vincoloService: VincoloService,
    private fb: FormBuilder,
    private dialogService: DialogService
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
      sezione: [{ value: null, disabled: true }, [Validators.maxLength(2)]],
      foglio: [
        { value: null, disabled: true },
        [Validators.required, Validators.maxLength(5)],
      ],
      particella: [
        { value: null, disabled: true },
        [Validators.required, Validators.maxLength(10)],
      ],
    });
    /* this.forestaliService.getProvincia().pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: ProvinciaModel[]) => { this.provinciaFull = res; } ); */
  }

  onSelectProvincia(event: any) {
    this.searchForParcelsForm.get("comuneCatastale").reset();
    this.searchForParcelsForm.get("comuneCatastale").enable();
    this.searchForParcelsForm.get("sezione").reset();
    this.searchForParcelsForm.get("sezione").disable();
    this.searchForParcelsForm.get("foglio").reset();
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").disable();
  }

  onClearProvincia(event: any) {
    this.searchForParcelsForm.get("provincia").reset();
    this.searchForParcelsForm.get("comuneCatastale").reset();
    this.searchForParcelsForm.get("comuneCatastale").disable();
    this.searchForParcelsForm.get("sezione").reset();
    this.searchForParcelsForm.get("sezione").disable();
    this.searchForParcelsForm.get("foglio").reset();
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").disable();
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
        console.log(res);
        this.comune = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Comune"
        );
      });
  }

  onSelectComune(event) {
    this.searchForParcelsForm.get("sezione").enable();
    this.codice_belfiore_selezionato = event.codiceBelfiore;
    this.searchForParcelsForm.get("foglio").reset();
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").disable();
  }

  onClearComune(event: any) {
    this.searchForParcelsForm.get("sezione").reset();
    this.searchForParcelsForm.get("sezione").disable();
    this.searchForParcelsForm.get("foglio").reset();
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").disable();
  }

  inserisciInElenco() {
    this.duplicatedItem = false;
    this.particellaNotFound = false;
    var particella: ParticleCadastral = {};
    particella.comune = this.searchForParcelsForm.get("comuneCatastale").value;
    particella.sezione = this.searchForParcelsForm.get("sezione").value.sezione;
    particella.foglio = this.searchForParcelsForm.get("foglio").value.foglio;
    particella.particella =
      this.searchForParcelsForm.get("particella").value.particella;
    particella.geometry = this.geometria_selezionata;

    let particellaCode =
      particella.comune.denominazioneComune +
      "-" +
      particella.sezione +
      "-" +
      particella.foglio +
      "-" +
      particella.particella;
    console.log("this.listParticelle", this.listParticelle);
    if (this.listParticelle && this.listParticelle.length > 0) {
      this.listParticelle.forEach((item, index) => {
        let pCode =
          item.comune +
          "-" +
          (item.sezione ? item.sezione : "") +
          "-" +
          item.foglio +
          "-" +
          item.particella;
        console.log("pCode: ", pCode);
        console.log("particellaCode ", particellaCode);
        if (pCode == particellaCode) {
          this.duplicatedItem = true;
          return;
        }
      });
    }

    if (!this.duplicatedItem) {
      this.createGeometry(this.idIntervento, particella).subscribe((res) => {
        if (!res) {
          console.log("working");
          const { foglio, comune, sezione } = particella;
          this.showDialogMsg(
            "Attenzione",
            `La particella nr ${particella.particella}, foglio ${foglio}, sezione ${sezione} , comune ${comune.denominazioneComune} ha una geometria 
            con errori topologici e non è gestibile dal sistema. Si consiglia di 
            eliminarla e di segnalare il problema nel campo 'note del richiedente' nell'ultima schermata di inserimento.`
          );
          return;
        }
        if (res["error"]) this.particellaNotFound = true;
        else {
          particella.supCatastale = res["supCatastale"];
          particella.id = res["id"];
          console.log("particelleCatastali ", res);

          //FIX GP
          // Convertir el objeto particella en una cadena JSON
          let particellaObtmp = particella;
          this.particellaObj.push(particellaObtmp);
          const particellaJSON = JSON.stringify(this.particellaObj);
          // Guardar la cadena JSON en el Local Storage
          localStorage.setItem("particellaGuardada", particellaJSON);
          //FIX GP

          this.emitShowParcels.emit(particella);
        }
      });
    }
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
  createGeometry(idIntervento, particella): Observable<any> {
    return this.tagliService
      .insertGeometryFromSingleParticella(idIntervento, particella)
      .pipe(
        map((resp: any) => {
          return resp;
        }),
        catchError((err) => {
          return of(undefined);
        })
      );
  }

  filtertSezione(event: any) {
    this.forestaliService
      .getSezioni(this.codice_belfiore_selezionato)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: SezioneModel[]) => {
        console.log(res);
        this.sezione = this.forestaliService.autocompleteFilter(
          event,
          res,
          "SezioneComune"
        );
      });

    console.log("AA");
    console.log(this.searchForParcelsForm.get("comuneCatastale").value);
  }

  onSelectSezione(event) {
    console.log(event.sezione);
    this.searchForParcelsForm.get("foglio").reset();
    this.searchForParcelsForm.get("foglio").enable();
    this.sezione_selezionata = event.sezione;
  }

  onClearSezione(event: any) {
    this.searchForParcelsForm.get("foglio").reset();
    this.searchForParcelsForm.get("foglio").disable();
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").disable();
  }

  //IL Json arriva non formattato
  filterFoglio(event) {
    this.res_passaggio = []; // svuoto select
    this.forestaliService
      .getFoglio(this.codice_belfiore_selezionato, this.sezione_selezionata)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: FoglioModel) => {
        this.data = res.features as FoglioModel[];
        for (const value of this.data) {
          this.res_passaggio.push(value.properties);
          if (this.res_passaggio.length > 0) {
            this.res_passaggio.sort((a, b) => (+a.foglio > +b.foglio ? 1 : -1)); //SORT ASC, per DESC invertire 1 con -1
          }
        }
        this.foglio = this.forestaliService.autocompleteFilter(
          event,
          this.res_passaggio,
          "FoglioComune"
        );
      });
  }

  onSelectFoglio(event) {
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").enable();
    this.foglio_selezionato = event.foglio;
  }

  onClearFoglio(event: any) {
    this.searchForParcelsForm.get("particella").reset();
    this.searchForParcelsForm.get("particella").disable();
  }

  filterParticella(event) {
    this.res_particella = [];
    this.forestaliService
      .getParticella(
        this.codice_belfiore_selezionato,
        this.sezione_selezionata,
        this.foglio_selezionato
      )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ParticellaModel) => {
        console.log(res);
        this.data_particella = res.features as ParticellaModel[];
        console.log(this.data_particella);
        for (const value of this.data_particella) {
          this.res_particella.push(value.properties);
          if (this.res_particella.length > 0) {
            this.res_particella.sort((a, b) =>
              +a.particella > +b.particella ? 1 : -1
            ); //SORT ASC, per DESC invertire 1 con -1
          }
        }
        this.particella = this.forestaliService.autocompleteFilter(
          event,
          this.res_particella,
          "ParticellaComune"
        );
      });
    console.log("AA");
  }

  onSelectParticella(event) {
    this.forestaliService
      .getParticellaById(
        this.codice_belfiore_selezionato,
        this.sezione_selezionata,
        this.foglio_selezionato,
        event.particella
      )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.geometria_selezionata = res.geometry;
        console.log(res);
      });
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
