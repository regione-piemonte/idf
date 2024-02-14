/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges, Output, EventEmitter, Input, SimpleChanges } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../pfa-config";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { DataTaglio, PropCatastos } from "src/app/shared/models/data-taglio";
import { Router } from "@angular/router";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { Step } from "src/app/shared/components/steps-with-errors/steps-with-errors.component";
import { DialogService } from "src/app/shared/services/dialog.service";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";
import { PropAndRicandeza } from "src/app/shared/models/dettaglio-intervento";
import { Observable } from "rxjs";

@Component({
  selector: "app-taglio",
  templateUrl: "./taglio.component.html",
  styleUrls: ["./taglio.component.css"],
})
export class TaglioComponent implements OnInit, OnChanges {
  @Input() idIntervento: number;
  @Input() idInterventoForEdit: number;
  @Input() steps: Step[];
  @Input() ricadenzeObj: any;
  ricadenze: any;
  dataTaglioForPrint: DataTaglio;
  comuni: any;
  @Input() pfaPlanId: number;
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  firstPart: any;
  secondPart: any;
  thirdPart: any;
  fourthPart: any;
  fifthPart: any;
  taglioValue: any;
  taglioHeader: TableHeader[];
  object: any = {} ;

  constructor(
    private pfaService: PfaSampleService,
    private routerService: Router,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.taglioHeader = PfaConfigComponent.getTaglioHeader();
    this.setDataTaglio();
  }

  ngOnChanges(): void {
    this.setDataTaglio();
  }

  setDataTaglio(){
    let idIntervento = this.idIntervento?this.idIntervento:this.idInterventoForEdit;
    if(idIntervento){
      this.pfaService.getDataTaglio(idIntervento)
      .subscribe((response: any) => {
          this.dataTaglioForPrint = response;
          if (this.dataTaglioForPrint) {
            this.dataTaglioPrint();
            this.setRicadenze();
          }
        }
      );
    }
  }

  setRicadenze(){
    
    if(this.secondPart && this.ricadenzeObj){
      let ricadenze = this.ricadenzeObj;
      this.secondPart[2].value = '';
      if(ricadenze.ricadenzaAreeProtette && ricadenze.ricadenzaAreeProtette.length > 0){
        for(var i = 0;i < ricadenze.ricadenzaAreeProtette.length; i++){
          this.secondPart[2].value = this.secondPart[2].value + 
          this.getRicadenzaDescr(ricadenze.ricadenzaAreeProtette[i]);
        }
      }
      //natura200[3]
      this.secondPart[3].value = '';
      if(ricadenze.ricadenzaNatura2000 && ricadenze.ricadenzaNatura2000.length > 0){
        for(var i = 0;i < ricadenze.ricadenzaNatura2000.length; i++){
          this.secondPart[3].value = this.secondPart[3].value + 
          this.getRicadenzaDescr(ricadenze.ricadenzaNatura2000[i]);
        }
      }
      //bosco da seme[4]
      this.secondPart[4].value = '';
      if(ricadenze.ricadenzaPopolamentiDaSeme && ricadenze.ricadenzaPopolamentiDaSeme.length > 0){
        for(var i = 0;i < ricadenze.ricadenzaPopolamentiDaSeme.length; i++){
          this.secondPart[4].value = this.secondPart[4].value + 
          this.getRicadenzaDescr(ricadenze.ricadenzaPopolamentiDaSeme[i]);
        }
      }
      //categorie forestali PFA[5]
      this.secondPart[5].value = '';
      if(ricadenze.ricadenzaCategorieForestali && ricadenze.ricadenzaCategorieForestali.length > 0){
        for(var i = 0;i < ricadenze.ricadenzaCategorieForestali.length; i++){
          this.secondPart[5].value = this.secondPart[5].value + 
          this.getRicadenzaDescr(ricadenze.ricadenzaCategorieForestali[i]);
        }
      }

      

      //this.secondPart[3].value = '';
    }
  }

  getRicadenzaDescr(item:any){
    let perc = '';
    if(item.percentualeRicadenza){
      perc = ' - ' + this.formatTwoDecimal(item.percentualeRicadenza) + '%; ';
    }
    return item.nome + perc;
  }

  goToDettaglio() {
    this.emitBack.emit();
  }
  trasmetti() {
    let idIntervento = this.idIntervento?this.idIntervento:this.idInterventoForEdit;
    let errors = 0;
    for(let i in this.steps){
      errors += this.steps[i].noOfErrors;
    }
    if(errors > 0){
        this.showMsg('Errore','Per trasmettere  l\'intervento è necessario compilare i campi obbligatori dei vari passi');
        return;
    }
    this.pfaService.trasmettiIntervento(idIntervento, this.object).subscribe(
      (response: any) => {
      if(Array.isArray(response)){
        if(response.length > 0){
          this.showMsg('Errore','Per trasmettere  l\'intervento è necessario compilare i campi obbligatori dei vari passi');
        }
      }else{
        this.showMsg('Info','Intervento trasmesso correttamente');
        this.routerService.navigate(["pfa", "tabs", this.pfaPlanId,{tab:'interventi'}]);
      }
    }
    );
    //this.routerService.navigate(["pfa", "tabs", this.pfaPlanId]);
  }

  dataTaglioPrint() {
    this.print(this.dataTaglioForPrint);
  }
  print(data: any) {
    if (data && data.comuneResource) {
      this.comuni = data.comuneResource.map((element: ComuneModel) => {
        let container;
        if (data.comuneResource[0]) {
          container = element.denominazioneComune;
        } else {
          container = ", " + element.denominazioneComune;
        }

        return container;
      });
    }

    this.firstPart = [
      {
        name: "Tipo di comunicazione",
        value: data && data.istanzaDetails ? data.istanzaDetails.tipoDiComunicazione : " ",
      },
      {
        name: "Descrizione",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.descrizione : " ",
      },
      {
        name: "Tipo di bosco",
        value: data && data.istanzaDetails ? data.istanzaDetails.descProprietaPrimpa : " ",
      },
      { name: "Comuni intervento", value: this.getComuni(this.comuni) },
      {
        name: "Data prevista per inizio intervento",
        value: data && data.istanzaDetails ? data.istanzaDetails.dataPrevistaPerInizio : " ",
      },
      {
        name: "Governo",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.governo : " ",
      },
      {
        name: "Tipo di intervento",
        value: data && data.descrizioneIntervento && data.descrizioneIntervento.governo ? data.descrizioneIntervento.tipoDiIntervento : " ",
      },
      // {
      //   name: "Categorie forestali",
      //   value: this.secondPart && this.secondPart[5] ? data.descrizioneIntervento.categorieForestali : " ",
      // },
      {
        name: "Richiede piedilista",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.flgPiedilista : " ",
      },
      // {
      //   name: "Totale superficie intervento",
      //   value: data && data.descrizioneIntervento ? 
      //     this.formatNumber(data.descrizioneIntervento.supInteressata) : " ",
      // },
      {
        name: "Numero piante",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.numeroPiante : " ",
      },
      {
        name: "Finalità del taglio",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.finalitaDelTaglio : " ",
      },
      {
        name: "Destinazione del legname",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.destinazioneDelLegname : " ",
      },
      {
        name: "Specie interessate dal taglio",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.specieInteresate : " ",
      },
      {
        name: "Ramaglia",
        value: data && data.descrizioneIntervento && 
        (data.descrizioneIntervento.ramaglia || data.descrizioneIntervento.ramaglia == 0) ? 
        this.formatNumber(data.descrizioneIntervento.ramaglia) + " m³" : " ",
      },
      {
        name: "Stima massa retraibile",
        value: data && data.descrizioneIntervento && 
        (data.descrizioneIntervento.stimaMassaRetraibileM3 || data.descrizioneIntervento.stimaMassaRetraibileM3 == 0)
          ? this.formatNumber(data.descrizioneIntervento.stimaMassaRetraibileM3) + " m³"
          : " ",
      },
      {
        name: "Viabilità utilizzata in fase di esbosco",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.viabilita : " ",
      },
      {
        name: "Tipo di esbosco",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.tipoDiEsbosco : " ",
      },
      {
        name: "Note esbosco",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.noteEsbosco : " ",
      },
      {
        name: "Richiedenti",
        value: data && data.utilizzatoreDetails && data.utilizzatoreDetails.richiedente
          ? data.utilizzatoreDetails.richiedente.denominazione
            ? data.utilizzatoreDetails.richiedente.denominazione
            : data.utilizzatoreDetails.richiedente.nome +
              " " +
              data.utilizzatoreDetails.richiedente.cognome
          : " ",
      },
      {
        name: "Utilizzatori",

        value: data && data.utilizzatoreDetails
          ? data.utilizzatoreDetails.utilizzatore
            ? data.utilizzatoreDetails.utilizzatore.denominazione
              ? data.utilizzatoreDetails.utilizzatore.denominazione
              : data.utilizzatoreDetails.utilizzatore.nome +
                " " +
                data.utilizzatoreDetails.utilizzatore.cognome
            : "DA INDIVIDUARE"
          : "DA INDIVIDUARE",
      },
      {
        name: "Tecnico forestale",
        value: data && data.utilizzatoreDetails
          ? data.utilizzatoreDetails.tecnicoForestale
            ? data.utilizzatoreDetails.tecnicoForestale.nome +
              " " +
              data.utilizzatoreDetails.tecnicoForestale.cognome
            : " "
          : " ",
      }
    ];

    this.secondPart = [
      {
        name: "Località",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.localita : " ",
      },
      {
        name: "Fascia altimetrica",
        value: data && data.descrizioneIntervento ? data.descrizioneIntervento.fasciaAltimetrica : " ",
      },
      {
        name: "Ricadenza in Area Protette",
        value: data && data.ricadenzaInfo ? data.ricadenzaInfo.areeProtette : " ",
      },
      {
        name: "Ricadenza in Rete Natura 2000",
        value: data && data.ricadenzaInfo ? data.ricadenzaInfo.popolamentoSeme : " ",
      },
      {
        name: "Ricadenza in popolamenti da seme",
        value: data && data.ricadenzaInfo ? data.ricadenzaInfo.popolamentoSeme : " ",
      },
      {
        name: "Ricadenza in categorie forestali pfa",
        value: data && data.ricadenzaInfo ? data.ricadenzaInfo.popolamentoSeme : " ",
      }
  
    ];

    this.thirdPart = [
      {
        name: "Totale sup catastale (ha)",
        value: data && data.descrizioneIntervento ? 
          this.formatTwoDecimal(data.descrizioneIntervento.supCatastale) : " ",
      },
      {
        name: "Totale sup tagliata (ha)",
        value: data && data.descrizioneIntervento ? 
          this.formatTwoDecimal(data.descrizioneIntervento.supInteressata) : " ",
      },
    ];
    if (data && data.propCatastos) {
      this.taglioValue = data.propCatastos.map((element: PropCatastos) => {
        const container = {};
        container["comune"] = element.denominazioneComune;
        container["sezione"] = element.sezione;
        container["foglio"] = element.foglio;
        container["particella"] = element.particella;
        container["subalt"] = "";
        container["supPart"] = element.supCartograficaHa;
        container["supTagl"] = element.supIntervento;
        return container;
      });
    }
  }

  getComuni(listComuni:string[]){
    
    if(listComuni && listComuni.length > 0){
      let result = listComuni[0];
      let mapComuni = {[result]:true};

      for(let i in listComuni){
        if(!mapComuni[listComuni[i]]){
          mapComuni[listComuni[i]] = true;
          result+= ', ' + listComuni[i];
        }
      }
      return result;
    }
    return '';
  }

  formatNumber(value){
    if(typeof value == 'number'){
      return (value).toLocaleString('it-IT');
    }
    return value;
  }

  formatTwoDecimal(value){
    if(typeof value == 'number' && (value+'').indexOf('.') > 0){
      return value.toFixed(2).replace('.',',');
    }
    return value;
  }

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
