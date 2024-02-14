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
import { FormGroup, FormBuilder, FormArray, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Subject, combineLatest } from "rxjs";
import { CONST } from "src/app/shared/constants";

import { UtilService } from "src/app/shared/services/util.service";
import { ForestaliService } from "../../../services/forestali.service";

import { TagliService } from "./../../../services/tagli.service";

import { TipoIntervento } from "src/app/shared/models/tipo-intervento";
import { TipoEventi } from "src/app/shared/models/tipo-eventi";

import { takeUntil } from "rxjs/operators";
import { CategoriaSelvicolturale } from "./../../../../../shared/models/categoria-selvicolturale.model";
import { Proprieta } from "./../../../../../shared/models/proprieta.model";
import { Trasformazione } from "src/app/shared/models/trasformazione.model";
import { TagliStep1 } from "./tagli-step1.model";
import { DialogService } from "src/app/shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { Eventi } from "src/app/shared/models/eventi";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";

@Component({
  selector: "step1-tagli",
  templateUrl: "./step1-tagli.component.html",
  styleUrls: ["./step1-tagli.component.css"],
})
export class Step1TagliComponent implements OnInit, OnDestroy {
  @Input() editMode: number | null;
  @Input() idInterventoParameter: number | null;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;

  @Output() emitNextStep = new EventEmitter();
  @Output() emitIdIstanze = new EventEmitter<number>();

  datiGeneraliForm: FormGroup;
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  isUpdate: boolean = false;
  showPopUp: boolean = false;
  msgPopUp: string = "";
  goNextStep: boolean = false;

  unsubscribe$ = new Subject<void>();
  invalidForm: boolean = true;
  invalidHeadings: boolean;

  categorieSelvicolturali: CategoriaSelvicolturale[];
  proprietaSelvicolturali: Proprieta[];
  trasformazioni: Trasformazione[];
  trasformazione: Trasformazione;

  categorieInterventionCompliant: any[];
  categorieCorrelatedEvent: Eventi[];

  currentYear: number = new Date().getFullYear();
  silvanaInit: string =
    new Date().getFullYear() + "/" + (new Date().getFullYear() + 1);
  it: any = CONST.IT;
  step1: TagliStep1;

  constructor(
    private fb: FormBuilder,
    private forestaliService: ForestaliService,
    private dialogService: DialogService,
    private tagliService: TagliService,
    private activatedRoute: ActivatedRoute,
    private pfaService: PfaSampleService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    if(this.editMode){
      this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
       console.log(res.nextStep);
       
       this.isUpdate = (res.nextStep > 0 );
     });

    }
    combineLatest(
      this.tagliService.getCategorieSelvicolturali(),
      this.tagliService.getProprietaSelvicolturali(),
      this.tagliService.getEventiByPFA(
        JSON.parse(localStorage.getItem("tabIndex"))
      ),
      this.tagliService.getTipoInterventoConformeDeroga()
    )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        ([
          resCategorie,
          resProprieta,
          tipoEventi,
          tipoInterventoConformeDeroga,
        ]) => {
          this.categorieSelvicolturali = resCategorie;
          this.proprietaSelvicolturali = resProprieta;
          this.categorieCorrelatedEvent = tipoEventi.content;
          this.categorieInterventionCompliant = tipoInterventoConformeDeroga;
        },
        (e) => console.log(e),
        () => this.initForms()
      );
    if (this.editMode || this.idInterventoParameter) {
      console.log(this.editMode, this.idInterventoParameter);

      if (this.editMode) {
        console.log("editttt");
        let idInterventoParameter = parseInt(
          localStorage.getItem("idInterventoParameter"),
          10
        );
        this.tagliService
          .getStep1(this.editMode)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe(
            (result) => {
              this.step1 = result;
              if (this.step1.trasformazSelvicolturale) {
                let t = this.step1.trasformazSelvicolturale;
                t.displayItem = `N: ${t.nrIstanza} / ${t.anno} / ${t.richiedente} / ${t.comune} / ${t.compensazione}`;
                this.step1.trasformazSelvicolturale = t;
              }
            },
            (e) => console.log(e),
            () => this.fillForms()
          );
      } else {
        this.tagliService
          .getStep1(this.editMode || this.idInterventoParameter)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe(
            (result) => {
              this.step1 = result;
              if (this.step1.trasformazSelvicolturale) {
                let t = this.step1.trasformazSelvicolturale;
                t.displayItem = `N: ${t.nrIstanza} / ${t.anno} / ${t.richiedente} / ${t.comune} / ${t.compensazione}`;
                this.step1.trasformazSelvicolturale = t;
              }
            },
            (e) => console.log(e),
            () => this.fillForms()
          );
      }
    }
  }

  private initForms() {
    this.datiGeneraliForm = this.fb.group({
      categoriaIntervento: [
        {
          idCategoriaSelvicolturale: 1,
          descrCategoriaSelvicolturale: "IN BOSCO",
          mtdOrdinamento: 1,
          flgVisibile: 1,
        },
        [Validators.required],
      ],
      proprieta: [null, [Validators.required]],
      annataSilvana: [this.silvanaInit, [Validators.required]],
      dataPresaInCarico: ["", [Validators.required]],
      statoIntervento: [CONST.STATO_INTERVENTO[0].descrStatoIntervento],
      interventionNumber: [null],
      correlatedEvent: [this.categorieCorrelatedEvent],
      flgConformeDeroga: [this.categorieInterventionCompliant[0], [Validators.required]],
      idEvento: [null],
    });

    this.datiGeneraliForm.controls["categoriaIntervento"].disable();
    this.datiGeneraliForm.controls["interventionNumber"].disable();
  }

  private fillForms() {
    this.initForms();
    console.log(this.step1, "init");
    this.datiGeneraliForm.patchValue({
      categoriaIntervento: this.step1.categoriaSelvicolturale || {
        idCategoriaSelvicolturale: 1,
        descrCategoriaSelvicolturale: "IN BOSCO",
        mtdOrdinamento: 1,
        flgVisibile: 1,
      },
      proprieta: this.step1.proprieta,
      annataSilvana: this.step1.annataSilvana || this.silvanaInit,
      dataPresaInCarico: this.step1.dataPresaInCarico
        ? new Date(this.step1.dataPresaInCarico)
        : "",
      flgConformeDeroga: this.handleOption(),
      statoIntervento: CONST.STATO_INTERVENTO.filter(
        (el) => el.fkStatoIntervento == this.step1.fkStatoIntervento
      )[0].descrStatoIntervento,
      interventionNumber: this.step1.nrProgressivoInterv,
      idEvento: this.handleOptionsEvent(),
    });
  }

  private handleOptionsEvent() {
    if (this.categorieCorrelatedEvent != null) {
      const option = this.categorieCorrelatedEvent.find(
        (elem) => elem.idEvento === this.step1.idEvento
      );
      if (!option) return null;
      return option;
    } else {
      return null;
    }
  }

  private handleOption() {
    if(this.categorieInterventionCompliant != null){
      const option = this.categorieInterventionCompliant.find(
        (elem) => elem.Value === this.step1.flgConformeDeroga
      );
      if (!option) return null;
      return option;
    } else {
      return null;
    }
   
  }

  private searchTrasformazioni(event: any) {
    this.tagliService
      .searchTrasformazioni(event.query)
      .subscribe((res: Trasformazione[]) => {
        let transf = res;
        transf.map(
          (t) =>
            (t.displayItem = `N: ${t.nrIstanza} / ${t.anno} / ${t.richiedente} / ${t.comune} / ${t.compensazione}`)
        );
        this.trasformazioni = transf;
      });
  }

  save(nextStep?: boolean) {
    this.goNextStep = nextStep;

    let step1 = new TagliStep1();

    const { flgConformeDeroga, idEvento } = this.datiGeneraliForm.value;

    step1.annataSilvana = this.datiGeneraliForm.get("annataSilvana").value;
    step1.dataPresaInCarico = this.forestaliService.formatDate(
      this.datiGeneraliForm.get("dataPresaInCarico").value
    );

    let tipoIstanzaId: string = sessionStorage.getItem(
      CONST.TIPO_ISTANZA_ID_KEY_STORE
    );
    step1.tipoIstanzaId = Number(tipoIstanzaId);

    step1.categoriaSelvicolturale = this.datiGeneraliForm.get(
      "categoriaIntervento"
    ).value;
    step1.proprieta = this.datiGeneraliForm.get("proprieta").value;
    step1.trasformazSelvicolturale = null;

    step1.flgConformeDeroga = flgConformeDeroga
      ? flgConformeDeroga.Value
      : undefined;

    step1.idEvento = idEvento ? idEvento.idEvento : undefined;
    step1.fkGoverno = this.step1 ? this.step1.fkGoverno : 0;
    step1.fkTipoIntervento = this.step1
      ? this.step1.fkTipoIntervento
      : 0;
    console.log(step1);

    if (this.isUpdate) {
      this.tagliService
        .putStep1(step1, this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.emitIdIstanze.emit(this.editMode);
          nextStep && this.emitNextStep.emit();
        });
    } else {
      this.pfaService
        .creaIntervento(JSON.parse(localStorage.getItem("tabIndex")))
        .subscribe(
          (res) => {
            console.log("interventi: ", res);
            if (res["ERROR"]) {
              this.dialogService.showDialogMsg("Errore", res["ERROR"], true);
              console.log("interventi ERROR: ", res);
              this.editMode = res.istanzaId;
            } else {
              this.editMode = res.istanzaId;
              this.emitIdIstanze.emit(this.editMode);
              console.log("interventi: ", this.editMode);
              localStorage.setItem("interventoNew", res.istanzaId);
              //this.openMappa();
            }
          },
          (e) => console.log(e),
          () => {
            this.tagliService
              .putStep1(step1, this.editMode)
              .pipe(takeUntil(this.unsubscribe$))
              .subscribe((res) => {
                this.forestaliService
                  .getPraticaInviata(this.editMode)
                  .subscribe(
                    (res) => {
                      const message = `<span class="text-success font-weight-bold">Istanza generata correttamente con nr : ${new Date(
                        res.dataInserimento
                      ).getFullYear()}/${res.nrIstanzaForestale} </span>`;
                      this.showDialogMsg("Attenzione", message);
                      /* this.emitIdIstanze.emit(this.idInterventoParameter);
                nextStep && this.emitNextStep.emit(); */
                    },
                    (e) => console.log(e),
                    () => {
                      this.isUpdate = true;                      
                      nextStep && this.emitNextStep.emit();
                    }
                  );
              });
          }
        );
    }
    // return;
    // campos es step1.flgConformeDeroga
    /* if (this.editMode) {
      this.tagliService
        .putStep1(step1, this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          nextStep && this.emitNextStep.emit();
        });
    } else {
      this.tagliService
        .postStep1(step1)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(
          (response) => {
            if (response.errorMessage) {
              this.showPopUp = true;
              this.msgPopUp = response.errorMessage;
            } else {
              if (!this.editMode) {
                this.editMode = response.istanzaId;

                this.forestaliService
                  .getPraticaInviata(this.editMode)
                  .subscribe((res) => {
                    this.showDialogMsg(
                      "Attenzione",
                      `Istanza Generata correttamente con nr : ${new Date(
                        res.dataInserimento
                      ).getFullYear()}/${res.nrIstanzaForestale} `
                    );
                    this.emitIdIstanze.emit(response.istanzaId);
                    nextStep && this.emitNextStep.emit();
                  });
              }
            }
          },
          (err) => console.log("HTTP Error", err),
          () => console.log("HTTP request completed.")
        );
    }*/
  }

  continue() {
    this.save(true);
  }

  prosegui() {
    this.showPopUp = false;
    if (this.goNextStep) this.emitNextStep.emit();
  }

  closePopUp() {
    this.showPopUp = false;
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
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
}
