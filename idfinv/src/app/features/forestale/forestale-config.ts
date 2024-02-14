/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";
import { StatoAds } from "src/app/shared/models/statoAds";

@Component({
    selector: 'app-forestale-config',
    template: ''
})
export class ForestaleConfig {
    stato:StatoAds;
    static testTableHeaders() {
        const testTableHeaders: TableHeader[] = [
            {
                field: 'name',
                header: 'First name'
            },
            {
                field: 'lastName',
                header: 'Last name'
            },
            {
                field: 'age',
                header: 'Age'
            }
        ];
        return testTableHeaders;
    }

    static alberiCavallettatiTableHeaders() {
        const alberiCavallettatiTableHeaders: TableHeader[] = [
            {
                field: 'specieLatino',
                header: 'Specie'
            },
            {
                field: 'tipo',
                header: 'Tipo (Pollone/Seme)'
            },
            {
                field: 'diametro',
                header: 'Diametro (cm)'
            },
            {
                field: 'altezza',
                header: 'Altezza (m)'
            },
            {
                field: 'incremento',
                header: 'Incremento (mm)'
            }
        ];
        return alberiCavallettatiTableHeaders;
    }

    static cavalettatiTableHeaders() {
        const cavalettatiTableHeaders: TableHeader[] = [
            // {
            //     field: 'codiceAds',
            //     header: 'Codice'
            // },
            {
                field: 'latino',
                header: 'Specie'
            },
            // {
            //     field: 'gruppo',
            //     header: 'Gruppo'
            // },
            {
                field: 'diametro',
                header: 'Diametro (cm a 1,30 m)'
            },
            {
                field: 'semePollone',
                header: 'seme/pollone'
            }
        ];
        return cavalettatiTableHeaders;
    }

    static searchHeaders() {
        const searchHeaders: TableHeader[] = [
            
            {
                field: 'codiceADS',
                header: 'Codice ADS'
            },
            {
                field: 'comune',
                header: 'Comune'
            },
            {
                field: 'categoriaForestale',
                header: 'Categoria forestale'
            },
            {
                field: 'ambitoDiRilevamento',
                header: 'Ambito di rilevamento'
            },
            {
                field: 'dettaglioAmbito',
                header: 'Dettaglio ambito'
            },
            {
                field: 'descrTipoAds',
                header: 'Tipologia'
            },
            {
                field: 'dataRilevamento',
                header: 'Data Rilevamento'
            },
            {
                field: 'statoScheda.descrStatoAds',
                header:'Stato'
            }
        ];
        return searchHeaders;
    }

    static conteggioAngolareTableHeaders() {
        const conteggioAngolareTableHeaders: TableHeader[] = [
            {
                field: 'specieLatino',
                header: 'Specie'
            },
            {
                field: 'alberiSeme',
                header: 'N. Alberi contati  seme'
            },
            {
                field: 'alberiPollone',
                header: 'N. Alberi contati polloni'
            }
        ];

        return conteggioAngolareTableHeaders;
    }

    static alberiCampioneTableHeaders() {
        const alberiCampioneTableHeaders: TableHeader[] = [
            {
                field: 'fixedFields',
                header: 'Alberi  dominanti e campione'
            },
            {
                field: 'latino',
                header: 'Dominante'
            },
            // {
            //     field: 'gruppo',
            //     header: 'Dominante'
            // },
            {
                field: 'rAdsSpecie',
                header: 'Campione principale'
            },
            {
                field: 'tIdfInveAlberi',
                header: 'Campione Specie 1'
            },
            {
                field: 'tIdfInveAlberi',
                header: 'Campione Specie 2'
            }
        ];
        return alberiCampioneTableHeaders;
    }

}