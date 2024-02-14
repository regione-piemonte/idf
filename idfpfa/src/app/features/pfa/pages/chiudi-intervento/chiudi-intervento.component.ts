/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { Dettaglio, Detail } from "src/app/shared/models/dettaglio";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { ActivatedRoute, Router } from "@angular/router";
import { PfaConfigComponent } from "../../pfa-config";
import { TableHeader } from "src/app/shared/models/table-header";
import {
  ChiusuraInformazioni,
  InterventoRiepilogo,
} from "src/app/shared/models/interventoRiepilogo";
import { SoggettoResource } from "src/app/shared/models/data-taglio";
import { ShootingMirrorModel } from "src/app/shared/models/shooting-mirror";
import { FormUtil } from "src/app/utils/form-util";
import { TipoAllegato } from "src/app/shared/models/tipo-allegato";
import { Observable, Subject } from "rxjs";
import { CONST } from "src/app/shared/constants";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";
import { DialogService } from "src/app/shared/services/dialog.service";
import { TagliStep2 } from "../../components/stepper-tagli/step2-tagli/tagli-step2.model";
import { Utilizzatore } from "src/app/shared/models/utilizzatore.model";
import { SelectItem } from "primeng/components/common/selectitem";
import { UserCompanyDataModel } from "src/app/shared/models/user-company-data.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { TagliService } from "../../services/tagli.service";
import { TipoUtilizzatoreEnum } from "src/app/shared/models/tipo-utilizzatore.enum";
import { ForestaliService } from "../../services/forestali.service";
import { takeUntil } from "rxjs/operators";
import { DocumentoAllegato } from "src/app/shared/models/documento-allegato";

@Component({
  selector: "app-chiudi-intervento",
  templateUrl: "./chiudi-intervento.component.html",
  styleUrls: ["./chiudi-intervento.component.css"],
})
export class ChiudiInterventoComponent implements OnInit {
  completaForm: FormGroup;
  denom: Detail;
  routeId: number;
  obj: any;
  tableHeaders: TableHeader[];
  idIntervento: number;
  pfaPlanId: number;
  interventoRiepilogoData: InterventoRiepilogo;
  data: InterventoRiepilogo;
  utilizzatore: SoggettoResource;
  chiusuraInformazioni: ChiusuraInformazioni;
  shootingMirrorInformazioni: ShootingMirrorModel[];
  currentYear: number = new Date().getFullYear();
  shootingMirrorForm: FormGroup;
  tipoAllegato: TipoAllegato[];
  enableSalvaButton: boolean = false;
  superficieRealeTagliata: number;
  idInterventoForEdit: number;
  mirrorFormValues:any;

  it: any = CONST.IT;


  docType = null;
  unsubscribe$ = new Subject<void>();
  showCambioUtilizzatore:boolean = false;
  tipoAccreditamento = null;
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
  tableData: DocumentoAllegato[] = [];
  disabilitaCompleta:boolean = false;

  annullaVisible: boolean = true;
  invioemail: boolean = false;
  viewMode:boolean = false;
  minDate:Date;
  isDrell: boolean = false;
  isAutorizzazione:boolean;
  allComuni$:any;
  editMode:number;

  constructor(
    private fb: FormBuilder,
    private pfaService: PfaSampleService,
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    private activatedRoute: ActivatedRoute,
    private routeService: Router,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((res) => {
      this.pfaPlanId = res.id;
      this.routeId = res.id;
      this.idIntervento = res.idInterventi;
      if(res.viewMode != null){
        this.viewMode = true; 
      }
    });
    this.pfaService.getAllDocumentiByIntervento(this.idIntervento).subscribe((resp) => {
      this.isDrell = resp.filter(el=> el.descrTipoAllegato == CONST.NOME_ALLEGATO_DRELL).length>0 ? true : false;      
    });
    this.pfaService.getDataForCompleta(this.idIntervento).subscribe((res) => {
      this.data = res;
      this.minDate = new Date(res.chiusuraInformazioni.dataInizio);
      this.minDate.setDate(this.minDate.getDate()+1);
      this.superficieRealeTagliata = (res && res.chiusuraInformazioni 
        && res.chiusuraInformazioni.superficieRealeTagliata) ? res.chiusuraInformazioni.superficieRealeTagliata: null;
      this.buildForm(this.data);
      this.isAutorizzazione = res.chiusuraInformazioni.flgConformeDeroga == "D"
      if(res.chiusuraInformazioni.flgConformeDeroga == "D" && !this.isDrell){
        this.disabilitaCompleta = true;
        let msg = `Attenzione! la presentazione del DREL è obbligatoria entro 60 gg dalla conclusione degli interventi soggetti ad autorizzazione (art.6 c.7 del Regolamento forestale).`
        if(res.chiusuraInformazioni.dataFine != null){
          let fine = new Date(res.chiusuraInformazioni.dataFine);
          fine.setDate(fine.getDate()+60)
          msg += `<br><br><strong>Allegare il Drel entro il ${fine.toLocaleDateString()}</strong>`
        }
        this.showMsg("Attenzione",msg)
      }
    });

    this.buildShootingCompleta();
    this.pfaService.allTipoAllegatoIntervento().subscribe((res) => {
      this.tipoAllegato = res.filter(el => el.idTipoAllegato == 35);
    });
  }
  

  checkDrell(event){
    this.isDrell = event  
    if(this.data.chiusuraInformazioni.flgConformeDeroga == "D"){
      this.disabilitaCompleta = (this.data.chiusuraInformazioni.flgConformeDeroga == "D" && !event);
    }
    /* if(this.data.chiusuraInformazioni.flgConformeDeroga == "D" && !this.isDrell){
      let msg = `Attenzione! la presentazione del DREL è obbligatoria entro 60 gg dalla conclusione degli interventi soggetti ad autorizzazione (art.6 c.7 del Regolamento forestale).`
      if(this.data.chiusuraInformazioni.dataFine != null){
        let fine = new Date(this.data.chiusuraInformazioni.dataFine);
        fine.setDate(fine.getDate()+60)
        msg += `<br><br><strong>Allegare il Drel entro il ${fine.toLocaleDateString()}</strong>`
      }
      this.showMsg("Attenzione",msg)
    }   */
  }

  goToTabs() {
    this.routeService.navigate(["pfa", "tabs", this.pfaPlanId,{tab:'interventi'}]);
  }
  buildForm(data: InterventoRiepilogo) { 
    this.completaForm = this.fb.group({
      dataInizio: [{value: data && data.chiusuraInformazioni? new Date(data.chiusuraInformazioni.dataInizio): "", 
        disabled: false }, [Validators.required]],
      dataFine: [{value: data && data.chiusuraInformazioni.dataFine ? new Date(data.chiusuraInformazioni.dataFine):"",
        disabled: false},[Validators.required]],
      utilizzatore: [{value:  data?this.getUtilizzatoreDesc(data.utilizzatore):'',   disabled: true}],
      supRealeTagliata: [{value: data && data.chiusuraInformazioni && data.chiusuraInformazioni.superficieTagliataInRiduzione
              ? this.getFormatedNumber(data.chiusuraInformazioni.superficieTagliataInRiduzione): this.getFormatedNumber(this.superficieRealeTagliata),disabled: false}, 
              [Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL_COMMA)]],
      stimaDelValore: [{value: data && data.chiusuraInformazioni ? this.getFormatedNumber(data.chiusuraInformazioni.stimaValoreLotto)
              : "",disabled: false},[Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL_COMMA)]],
      valoreAggiu: [{value: data && data.chiusuraInformazioni ? this.getFormatedNumber(data.chiusuraInformazioni.valoreDellAsta): "",
          disabled: false},[Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL_COMMA)]],
    });
    this.completaForm.get("dataInizio").valueChanges.subscribe(()=>{
      this.minDate.setDate(new Date(this.completaForm.get("dataInizio").value).getDate()+1);
      this.completaForm.get("dataFine").setValue("");
    })
  }

  getUtilizzatoreDesc(utilizzatore: SoggettoResource){
    return utilizzatore ? utilizzatore.nome
    ? utilizzatore.nome + " " + utilizzatore.cognome + " - " + utilizzatore.codiceFiscale
    : utilizzatore.denominazione  + " - " + utilizzatore.codiceFiscale : ""
  }

  getFormatedNumber(val){
    return val? (val + '').replace('.',','):'';
  }

  createDataForSend() {
    this.shootingMirrorInformazioni = this.shootingMirrorForm.getRawValue().columnForm;
    this.utilizzatore = {
      idSoggetto: this.data.utilizzatore ? this.data.utilizzatore.idSoggetto : null
    };
    this.chiusuraInformazioni = {

      dataFine: this.pfaService.formatDate(
        this.completaForm.get("dataFine").value
      ),
      dataInizio: this.pfaService.formatDate(
        this.completaForm.get("dataInizio").value
      ),
      superficieRealeTagliata: this.completaForm.get("supRealeTagliata").value.replace(',','.'),
      valoreDellAsta: this.completaForm.get("valoreAggiu").value,
      stimaValoreLotto: this.completaForm.get("stimaDelValore").value,
    };

   /*  if(+this.chiusuraInformazioni.superficieRealeTagliata > this.superficieRealeTagliata){
      this.showMsg('Errore','La superficie tagliata può essere modificata solo' 
      + ' in riduzione. Non può essere maggiore di: ' 
      + (''+ this.superficieRealeTagliata).replace('.',','));
      return false;
    } */
    

    this.interventoRiepilogoData = {
      utilizzatore: this.utilizzatore,
      chiusuraInformazioni: this.chiusuraInformazioni,
      shootingMirrorInformazioni: this.shootingMirrorInformazioni,
    };
    return true;
  }
  salvaIntervento() {
    if(this.createDataForSend()){
      this.pfaService
        .salvaIntervento(this.interventoRiepilogoData, this.idIntervento)
        .subscribe();
    }
  
  }

  completaIntervento() {
    if(this.createDataForSend()){
      this.pfaService
        .completaIntervento(this.interventoRiepilogoData, this.idIntervento)
        .subscribe();
        this.routeService.navigate(["pfa", "tabs", this.pfaPlanId,{tab:'interventi'}]);
    }
  }

  buildShootingCompleta() {
    this.tableHeaders = PfaConfigComponent.getShootingCompletaHeader();
    if (this.idIntervento) {
      this.pfaService
        .getDataForShootingMirror(this.idIntervento)
        .subscribe((res) => {
          this.createShootingCompletaForm(res);
        });
    }
  }

  createShootingCompletaForm(data: any[]) {
    let _form = this.fb.array([]);
    
    data.forEach(dataObj => {
      if(dataObj.ripresaResidua<0){
        this.showMsg("Attenzione",`Attenzione! Si sta tagliando più del possibile nella particella n. ${dataObj.codParticellaFor}`)
       }
      _form.push(FormUtil.addFormFromDataObject(dataObj, this.fb, this.tableHeaders));
    });
    this.shootingMirrorForm = this.fb.group({
      columnForm: _form
    })
    this.mirrorFormValues = this.shootingMirrorForm.value;
    this.shootingMirrorForm.valueChanges.subscribe(
      () =>  { 
        if(this.shootingMirrorForm.valid){         
          this.mirrorFormValues = this.shootingMirrorForm.value;
         (this.shootingMirrorForm.get('columnForm') as FormArray).controls.forEach( (fg: FormGroup) => this.calculateRipresaResidua(fg));
        }else{
          this.shootingMirrorForm.patchValue(this.mirrorFormValues);
        }
      }
    );
  }
  calculateRipresaResidua(pfShootingMirror: FormGroup): void {
    console.log(pfShootingMirror);
    
     let ripresaResidua  = 0;
     const ripresaAttuale = pfShootingMirror.get('ripresaAttuale').value ? pfShootingMirror.get('ripresaAttuale').value : 0;
     const ripresaIntervento =  pfShootingMirror.get('ripresaIntervento').value ? pfShootingMirror.get('ripresaIntervento').value : 0;     
     ripresaResidua = ripresaAttuale - this.getFloat(ripresaIntervento);
     if(ripresaResidua<0){
      this.showMsg("Attenzione",`Attenzione! Si sta tagliando più del possibile nella particella n. ${pfShootingMirror.get('codParticellaForest').value}`)
     }
     pfShootingMirror.get('ripresaResidua').patchValue(this.getFormatedNumber(ripresaResidua), {emitEvent:false});
  }

  getFloat(value){
    return  parseFloat(('' + value).replace(',','.'));
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
        //console.log(this.showCaricaDocumenti);
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
            flgEntePubblico : this.isUtilizzatoreEntePubblico, 
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
        .cambioUtilizzatore(this.idIntervento, step2Form)
        .subscribe((res) => {
          //this.showCaricaDocumenti = true;
          console.log("====================================");
          console.log("CAMBIO UTLIZZATORE ", res);
          console.log("====================================");
          this.showDialogMsg('Attenzione', 'utilizzatore modificato correttamente');
        });
    }
  }

  cambiaUtilizzatore() {
    this.tagliService.getStep2(this.idIntervento).subscribe((res) => {
      let utilizzatoreOption = CONST.TIPO_UTILIZZATORE_OPTIONS.find(
        (l) => l.label === res.tipoUtilizzatore
      );
      this.tipoUtilizzatoreSelected = utilizzatoreOption.id;
      this.currentUtilizzatore = utilizzatoreOption.descr;
      this.tipoAccreditamento = this.TipoAccreditamentoEnum.GESTORE

      this.step2Form = res;
      this.showCambioUtilizzatore = true;
      this.changeUtilizzatoreOption(this.tipoUtilizzatoreSelected);
    });
  }

  closeCambioUtilizzatore() {
    this.showCambioUtilizzatore = false;
    this.ngOnInit()
  }

  changeUtilizzatoreOption(opt) {
    this.tipoUtilizzatoreSelected = Number(opt);
    if (this.tipoUtilizzatoreSelected === 3) {
      if (this.step2Form.tipoUtilizzatore != "DA_INDIVIDUARE" && this.step2Form.utilizzatore.cognome != null && this.currentUtilizzatore != "In proprio") {
        this.initUtilizzatorePersonalForm(this.step2Form.utilizzatore);
      } else {
        this.initUtilizzatorePersonalForm({});
      }
    }
    if (this.tipoUtilizzatoreSelected === 4) {
      if (this.step2Form.tipoUtilizzatore != "DA_INDIVIDUARE" && this.step2Form.utilizzatore.denominazione != null && this.currentUtilizzatore != "In proprio") {
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
  loadDocuments() {
    this.tagliService
      .getAllDocuments(this.idIntervento)
      .subscribe((response: [DocumentoAllegato]) => {
        this.tableData = response;
        if (this.idDocPresent(2)) {
          this.docType = 2;
        } else if (this.idDocPresent(3)) {
          this.docType = 3;
        }
      });
  }
  idDocPresent(tocType: number) {
    return (
      this.tableData.filter((elem) => elem.idTipoAllegato == tocType).length > 0
    );
  }


  invioEmail() {
    console.log("INVIO EMAIL");
    this.invioemail = true;
    //this.showCaricaDocumenti = false;
    this.tagliService
      .postDataInvioMail(this.idIntervento, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.loadDocuments();
          this.tagliService
            .getDataInvio(this.idIntervento)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe((response) => {
             /*  this.dataInvio = response.dataInvio;
              this.isCompleted = true;
              this.invioFinished = false;
              this.emitCompleted.emit("true"); */
            });
        }
      });
    // this.router.navigate(["visualizza-tagli"]);
  }

  showDialogMsg(msgType:string, msg: string){
    this.dialogService.showDialog(true, msg, msgType, DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) { this.closeCambioUtilizzatore() }
    });
  }

  isButtonDisabled() {
      // tipo utilizzatore null
    const d = this.tipoUtilizzatoreSelected === null;

    // utlizzatore pf not valid
    const e =
      this.tipoUtilizzatoreSelected === 3 &&
      (this.utilizzatorePersonaForm
        ? this.utilizzatorePersonaForm.invalid
        : false);

    // utlizzatore pg not valid
    let f = false;

    if (this.tipoUtilizzatoreSelected === 4) {
      //taif - verifico se selezionato
      if (this.utilizzatoreCompanyForm.get("taif").value === true) {
        f = this.utilizzatoreCompanyForm.get("soggettoTaif").value === null;
      } else {
        f =
          this.utilizzatoreCompanyForm && this.utilizzatoreCompanyForm.invalid;
      }
    }

    /*     console.log('====================================');
    console.log("a " , a);
    console.log("b " , b);
    console.log("c " , c);
    console.log("d " , d, this.tipoUtilizzatoreSelected);
    console.log("e " , e);
    console.log("f " , f, this.utilizzatoreCompanyForm);
    console.log('===================================='); */

    return  d || e || f;
  }

  
  openMappa(){
    //window.scrollTo(0, 0);
    this.pfaService.getCartograficoByIdUrl(8,''+this.idIntervento)
    .subscribe(
      (response: any) => {
        if(window.location.href.indexOf('http://localhost:') == -1 ){
          window.location.href = response['geecourl'];
        }else{
          window.open(response['geecourl'], "_blank");
        }
      }
    );
    return false;
  }

  /* openDialogUtilizzatore(){
    this.dialogUtilizzatore = false;
  }

  closeDialogUtilizzatore(){
    this.dialogUtilizzatore = false;
  } */

  showMsg(msgType,msg){
    this.dialogService.showDialog(
      true,
      msg,
      msgType,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log("BUTTON WORKS");
        }
      }
    );
  }
}


/* openModificaUtilizzatore(){
    this.dialogUtilizzatore=true;
  }

  updateUtilizzatore(){
    this.pfaService.getDataForCompleta(this.idIntervento).subscribe((res) => {
      this.data = res;
      this.utilizzatore = {
        idSoggetto: this.data.utilizzatore ? this.data.utilizzatore.idSoggetto : null
      };
      this.completaForm.get('utilizzatore').patchValue(this.getUtilizzatoreDesc(this.data.utilizzatore));
      this.dialogUtilizzatore = false;
    })
  } */