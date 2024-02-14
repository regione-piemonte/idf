/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from "@angular/core";
import { Detail } from "src/app/shared/models/dettaglio";
import { MenuItem } from "primeng/components/common/menuitem";
import { ActivatedRoute, Router } from "@angular/router";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { Eventi } from "src/app/shared/models/eventi";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";
import { TipoEventi } from "src/app/shared/models/tipoEventi";
import { CONST } from "src/app/shared/constants";

@Component({
  selector: "app-event-step",
  templateUrl: "./event-step.component.html",
  styleUrls: ["./event-step.component.css"]
})
export class EventStepComponent implements OnInit {
  items: MenuItem[];
  denom: Detail;
  activeIndex = 0;
  unsubscribe$ = new Subject<void>();
  routeId: number;
  obj: any;
  eventi: Eventi[];
  eventoForm: FormGroup;
  tipoEventi: TipoEventi[];

  eventoId: number;
  eventiObj: any;
  editMode: boolean = false;
  editIdEventi: number;
  disabledFields: boolean = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private pfaService: PfaSampleService
  ) {}

  ngOnInit() {
    this.checkIfIsEditMode();
    this.createSteper();
    if(this.editMode){
      this.pfaService.getEventiById(this.editIdEventi).subscribe(
        data => (this.activeIndex = 1, this.items[this.activeIndex].command(data)),
        err => console.log("ERROR", err)
      )
    } else {
      this.activeIndex = 0;
      this.createEventi();
    }
  }

  createEventi() {
    this.pfaService
      .createEventi(this.routeId)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(resp => {
        this.eventiObj = resp;
        this.editIdEventi = resp.idEvento;
        this.getTipoEventi();
        this.datiTecniciForm();
      });
  }

  createSteper(){
    this.items = [{
      label: "Localizza un evento",
      command: (event: any) => {
        this.activeIndex = 0;
      },
      disabled: false
    },
      {
        label: "Dati tecnici",
        command: (event: any) => {
          if(this.editMode){
            if(event.nomeBreve && event.localita && event.descrEvento && event.percDanno && event.supInteressataHa){
              this.activeIndex = 1;
            }
            this.getDataForFirstStep(this.editIdEventi);
          }
          this.activeIndex = 1;
        },
        disabled: this.activeIndex === 0 ? true : false
      }


    ];
  }

  checkIfIsEditMode() {
    const data = this.activatedRoute.snapshot.params['viewMode'];
    const idEventi = this.activatedRoute.snapshot.params['idEvento'];
    const routeId = this.activatedRoute.snapshot.params['id'];
    idEventi ? this.editIdEventi = idEventi : undefined;
    data ? this.editMode = true : this.editMode = false;
    routeId ? this.routeId = routeId : this.routeId = undefined;
  }

  getDataForFirstStep(idEvento:number){
    this.getFirstStepEventi(idEvento, this.routeId);
        setTimeout(() => {
          this.datiTecniciForm();
          this.getTipoEventi();
        }, 1000);
  }

  datiTecniciForm() {
    this.eventoForm = this.fb.group({
      progressivoEventoPfa: [{ value: this.eventiObj ? this.eventiObj.progressivoEventoPfa : "", disabled: true }, Validators.required],
      nomeBreve: [{ value:  this.eventiObj ? this.eventiObj.nomeBreve : '', disabled: (this.editMode && this.disabledFields) ? true : false }],
      dataEvento: [ {value: this.eventiObj ? this.pfaService.formatDate(this.eventiObj.dataEvento) : '', disabled: (this.editMode && this.disabledFields) ? true : false}, Validators.required],
      idgeoPlParticelaForest: [{ value: this.eventiObj ? this.eventiObj.codParticelle : "", disabled: true }, Validators.required],
      tipoEvento: [{value:this.eventiObj.tipoEvento, disabled: (this.editMode && this.disabledFields) ? true : false }, Validators.required],
      descrEvento: [{ value: this.eventiObj ? this.eventiObj.descrEvento : "", disabled : (this.editMode && this.disabledFields) ? true : false }, Validators.required],
      localita: [{ value : this.eventiObj ? this.eventiObj.localita : '', disabled : (this.editMode && this.disabledFields) ? true : false }, Validators.required],
      supInteressataHa: [{ value : this.eventiObj ? this.eventiObj.supInteressataHa : '', disabled : (this.editMode && this.disabledFields) ? true : false},
        [Validators.required, Validators.pattern(CONST.PATTERN_MONETARY)]],
      percDanno: [{value:this.eventiObj ? this.eventiObj.percDanno : '', disabled: (this.editMode && this.disabledFields) ? true : false},
        [Validators.required,Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]]
    });
    this.createSteper();
  }

  backToTabs() {
    this.navigationToDettaglio();
  }

  proceed() {
    // se esiste l'evento ... puo andare avanti
    this.activeIndex++;
    this.createStep2();
  }

  navigationToDettaglio() {
    this.router.navigate(["pfa", "tabs", this.routeId,{tab:'eventi'}], {
      queryParams: this.obj
    });
  }

  saveEventiDatiTecnici() {
    let eventoId = this.editIdEventi?this.editIdEventi:this.eventoId
    let forma: any = this.colectFormValues(eventoId);
    this.pfaService.updateEventiDatiTecnici(forma, eventoId, this.routeId).
    subscribe(resp =>
      (this.navigationToDettaglio())
    );
    
  }

  createStep2(): void {
    this.createSteper();
    this.activatedRoute.params.subscribe(res => {
        this.routeId = res.id;
        this.activatedRoute.queryParams.subscribe(params => {
          this.obj = {
            idComune: params.idComune,
            idPopolamento: params.idPopolamento
          };
          this.pfaService
            .getDettaglioById(this.routeId, this.obj)
            .subscribe(resp => (this.denom = resp));
        });
      });
  }

  colectFormValues(idEvento: number): any {
    let forma: any = this.eventoForm.value;
    forma.progressivoEventoPfa = +this.eventoForm.get("progressivoEventoPfa").value;
    forma.idgeoPlParticelaForest = [];
    forma.idgeoPlParticelaForest.push(
      +this.eventoForm.get("idgeoPlParticelaForest").value
    );
    forma.tipoEvento = +this.eventoForm.get("tipoEvento").value;
    forma.supInteressataHa = +this.eventoForm.get("supInteressataHa").value;
    forma.percDanno = +this.eventoForm.get("percDanno").value;
    forma.dataEvento = this.pfaService.formatDate(forma.dataEvento);
    forma.idEvento = idEvento;
    return forma;
  }

  getTipoEventi() {
    this.pfaService
      .getTipoEventi()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(resp => (this.tipoEventi = resp));
  }

  getFirstStepEventi(eventiId: number, idGeoPfa) {
    this.pfaService
      .getFirstStepEventi(eventiId, idGeoPfa)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(resp => {
        this.eventiObj = resp;
      });
  }
  enableFields(){
    this.eventoForm.controls["nomeBreve"].enable();
    this.eventoForm.controls["dataEvento"].enable();
    this.eventoForm.controls["descrEvento"].enable();
    this.eventoForm.controls["tipoEvento"].enable();
    this.eventoForm.controls["localita"].enable();
    this.eventoForm.controls["supInteressataHa"].enable();
    this.eventoForm.controls["percDanno"].enable();
    this.disabledFields = false;
  }

}
