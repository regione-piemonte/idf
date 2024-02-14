/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Dettaglio {
    fonte_finanziamento: string;
    data_inizio_validita: Data;
    data_fine_validita: Data;
    data_approvazione: Data;
    data_revisione: Data;
    sup_pianificata_totale: number;
    sup_forestale_gest_attiva: number;
    note: string;
    data_aggiornamento: Data;
    utente_aggiornamento: string;
    n_delibera: string;
    durata_pfa_anni: number;
    flg_revisione: string;
    proprieta_silvopast_ha: number;
    proprieta_forestale_ha: number;
    superf_tot_boscata_ha: number;
    superf_bosc_gest_attiva_ha: number;
    superf_gest_non_attiva_tot_ha: number;
    superf_gest_non_attiva_mon_ha: number;
    superf_gest_non_attiva_evl_ha: number;
    superf_altri_usi_ha: number;
    nome_breve_pfa: string;
    proponente_nome: ProponenteNome;
    denominazione: string;
    IDGEO_PL_PFA: number;
  }
  export class Data {
    year: number;
    month: string;
    era: string;
    dayOfMonth: number;
    dayOfWeek: string;
    dayOfYear: number;
    leapYear: boolean;
    monthValue: number;
    chronology: Chronology;
  }
  export class Chronology {
    id: string;
    calendarType: string;
  }
  export class ProponenteNome {
    id_proponente: number;
    denom_proponente: string;
    codice_fiscale: string;
    indirizzo: string;
    nr_telefonico: string;
    e_mail: string;
  }