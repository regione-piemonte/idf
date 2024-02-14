----------------
-- TAVOLE E PK |
----------------

create table idf_cnf_config_utente
(
	id_config_utente      integer  not null ,
	fk_profilo_utente     integer  not null ,
	fk_tipo_accreditamento  numeric(3)  not null ,
	nr_accessi            integer  not null ,
	data_primo_accesso    timestamp  not null ,
	data_ultimo_accesso   timestamp  not null ,
	flg_privacy           numeric(1)  not null  default 0 constraint  dom_01 check (flg_privacy in (0,1)),
	fk_soggetto           numeric(5)  not null 
);

alter table idf_cnf_config_utente
	add constraint  pk_idf_cnf_config_utente primary key (id_config_utente);




CREATE TABLE idf_cnf_delega
(
	cf_delegante          character varying(16) NOT NULL,
	id_config_utente      INTEGER NOT NULL,
	data_inizio           DATE NULL,
	data_fine             DATE NULL
)
;

ALTER TABLE idf_cnf_delega
	ADD  PRIMARY KEY (cf_delegante,id_config_utente)
;



CREATE TABLE idf_cnf_param_apimgr
(
  id_config_param_apimgr integer NOT NULL,
  consumer_key character varying(200),
  consumer_secret character varying(200),
  data_inizio_validita date,
  data_fine_validita date
);

ALTER TABLE  idf_cnf_param_apimgr
ADD CONSTRAINT PK_idf_cnf_param_apimgr PRIMARY KEY (id_config_param_apimgr);



CREATE TABLE idf_cnf_param_acta
(
  id_config_param_acta integer NOT NULL,
  codice character varying(50) NOT NULL,
  valore character varying(500),
  fk_ambito_istanza numeric(3) NOT NULL
);
  
ALTER TABLE  idf_cnf_param_acta
  ADD CONSTRAINT PK_idf_cnf_param_acta PRIMARY KEY (id_config_param_acta);



create table idf_cnf_parametro_appl
(
	id_parametro_appl     integer  not null ,
	fk_tipo_param_appl    integer  not null ,
	parametro_appl_num    numeric  null ,
	parametro_appl_char   character varying(400)  null 
);

alter table idf_cnf_parametro_appl
	add constraint  pk_idf_cnf_parametro_appl primary key (id_parametro_appl);



create table idf_cnf_procedura
(
	id_procedura          integer  not null ,
	nome_procedura        character varying(100)  not null 
);

alter table idf_cnf_procedura
	add constraint  pk_idf_cnf_procedura primary key (id_procedura);



create table idf_cnf_profilo_utente
(
	id_profilo_utente     integer  not null ,
	profilo_utente        character varying(100)  not null ,
	mtd_flg_attivo        numeric(1)  not null  default 0  constraint  attiv_0_1 check (mtd_flg_attivo in (0,1)),
	fk_procedura          integer  not null 
);

alter table idf_cnf_profilo_utente
	add constraint  pk_idf_cnf_profilo_utente primary key (id_profilo_utente);



create table idf_cnf_sk_ok_ads
(
	idgeo_pt_ads          numeric(9)  not null ,
	flg_sez1              numeric(1)  not null  default 0  constraint  sa1_0_1 check (flg_sez1 in (0,1)),
	flg_sez2              numeric(1)  not null  default 0  constraint  sa2_0_1 check (flg_sez2 in (0,1)),
	flg_sez3              numeric(1)  not null  default 0  constraint  sa3_0_1 check (flg_sez3 in (0,1)),
	flg_sez4              numeric(1)  not null  default 0  constraint  sa4_0_1 check (flg_sez4 in (0,1))
);

alter table idf_cnf_sk_ok_ads
	add constraint  pk_idf_cnf_sk_ok_ads primary key (idgeo_pt_ads);



create table idf_cnf_sk_ok_selv
(
	id_intervento         numeric(9,0)  not null ,
	flg_sez1              numeric(1)  not null  default 0  constraint  sez_1a_0_1 check (flg_sez1 in (0,1)),
	flg_sez2              numeric(1)  not null  default 0  constraint  sez_2a_0_1 check (flg_sez2 in (0,1)),
	flg_sez3              numeric(1)  not null  default 0  constraint  sez_3a_0_1 check (flg_sez3 in (0,1)),
	flg_sez4              numeric(1)  not null  default 0  constraint  sez_4a_0_1 check (flg_sez4 in (0,1)),
	flg_sez5              numeric(1)  not null  default 0  constraint  sez_5a_0_1 check (flg_sez5 in (0,1))
);

alter table idf_cnf_sk_ok_selv
	add constraint  pk_idf_cnf_sk_ok_selv primary key (id_intervento);



create table idf_cnf_sk_ok_trasf
(
	id_intervento         numeric(9,0)  not null ,
	flg_sez1              numeric(1)  not null  default 0  constraint  sez1_0_1 check (flg_sez1 in (0,1)),
	flg_sez2              numeric(1)  not null  default 0  constraint  sez2_0_1 check (flg_sez2 in (0,1)),
	flg_sez3              numeric(1)  not null  default 0  constraint  sez3_0_1 check (flg_sez3 in (0,1)),
	flg_sez4              numeric(1)  not null  default 0  constraint  sez4_0_1 check (flg_sez4 in (0,1)),
	flg_sez5              numeric(1)  not null  default 0  constraint  sez5_0_1 check (flg_sez5 in (0,1)),
	flg_sez6              numeric(1)  not null  default 0  constraint  sez6_0_1 check (flg_sez6 in (0,1))
);

alter table idf_cnf_sk_ok_trasf
	add constraint  pk_idf_cnf_sk_ok_trasf primary key (id_intervento);



create table idf_cnf_tipo_accreditamento
(
	id_tipo_accreditamento  numeric(3)  not null ,
	descr_tipo_accreditamento  character varying(50)  null 
);

alter table idf_cnf_tipo_accreditamento
	add constraint  pk_idf_cnf_tipo_accreditamento primary key (id_tipo_accreditamento);



create table idf_cnf_tipo_param_appl
(
	id_tipo_param_appl    integer  not null ,
	descr_tipo_param_appl  character varying(200)  null 
);

alter table idf_cnf_tipo_param_appl
	add constraint  pk_idf_cnf_tipo_param_appl primary key (id_tipo_param_appl);



create table idf_d_ambito_istanza
(
	id_ambito_istanza     numeric(3)  not null ,
	descr_ambito_istanza  character varying(500)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  dom_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_ambito_istanza
	add constraint  pk_idf_d_ambito_istanza primary key (id_ambito_istanza);



create table idf_d_ambito_rilievo
(
	id_ambito             integer  not null ,
	descr_ambito          character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis27_0_1 check (flg_visibile in (0,1)),
	fk_padre 							integer NOT NULL DEFAULT 0
);



alter table idf_d_ambito_rilievo
	add constraint  pk_idf_d_ambito_rilievo primary key (id_ambito);



create table idf_d_assetto
(
	id_assetto            numeric(3)  not null ,
	cod_assetto           character varying(3)  null ,
	descr_assetto         character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis20_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_assetto
	add constraint  pk_idf_d_assetto primary key (id_assetto);



create table idf_d_attitudine
(
	id_attitudine         numeric(3)  not null ,
	descr_attitudine      character varying(50)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis24_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_attitudine
	add constraint  pk_idf_d_attitudine primary key (id_attitudine);



create table idf_d_categoria_forestale
(
	id_categoria_forestale  numeric(2)  not null ,
	fk_param_macro_catfor  numeric(3)  not null ,
	codice_amministrativo  character varying(3)  null ,
	descrizione           character varying(300)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis31_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_categoria_forestale
	add constraint  pk_idf_d_categoria_forestale primary key (id_categoria_forestale);



CREATE TABLE idf_d_classe_fertilita
(
	id_classe_fertilita   numeric(3) NOT NULL,
	descr_classe_fertilita  character varying(100) NULL,
	mtd_ordinamento       numeric(2) NULL,
	flg_visibile          numeric(1) NULL
		CONSTRAINT  flg_visibile CHECK (flg_visibile in (0,1))
);

ALTER TABLE idf_d_classe_fertilita
	ADD CONSTRAINT pk_idf_d_classe_fertilita PRIMARY KEY (id_classe_fertilita);
	
	
	
create table idf_d_combustibile
(
	cod_combustibile      character varying(1)  not null ,
	descr_combustibile    character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis_52_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_combustibile
	add constraint  pk_idf_d_combustibile primary key (cod_combustibile);



create table idf_d_composizione_specie
(
	id_composizione_specie  numeric(3)  not null ,
	cod_composizione_specie  character varying(5)  null ,
	descr_composizione_specie  character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis14_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_composizione_specie
	add constraint  pk_idf_d_composizione_specie primary key (id_composizione_specie);



create table idf_d_compresa
(
	id_compresa           numeric(3)  not null ,
	fk_padre              numeric(3)  null ,
	cod_compresa          character varying(5)  null ,
	descr_compresa        character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis19_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_compresa
	add constraint  pk_idf_d_compresa primary key (id_compresa);



create table idf_d_comunita_montana
(
	id_comunita           numeric(4)  not null ,
	descr_comunita_montana  character varying(500)  null ,
	codice_comunita_montana  character varying(2)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis60_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_comunita_montana
	add constraint  pk_idf_d_comunita_montana primary key (id_comunita);



create table idf_d_config_ipla
(
	id_config_ipla        integer  not null ,
	descrizione_config_ipla  character varying(20)  not null ,
	fk_ambito             integer   default  0 not null 
);



alter table idf_d_config_ipla
	add constraint  pk_idf_d_config_ipla primary key (id_config_ipla);



create table idf_d_danno
(
	id_danno              numeric(4)  not null ,
	cod_danno             character varying(3)  null ,
	descr_danno           character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis22_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_danno
	add constraint  pk_idf_d_danno primary key (id_danno);



create table idf_d_dest_legname
(
	id_dest_legname       numeric(3)  not null ,
	descr_dest_legname    character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  dlegn_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_dest_legname
	add constraint  pk_idf_d_dest_legname primary key (id_dest_legname);



create table idf_d_destinazione
(
	id_destinazione       numeric(4)  not null ,
	descr_destinazione    character varying(100)  null ,
	cod_destinazione      character varying(3)  null ,
	fk_config_ipla        integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis45_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_destinazione
	add constraint  pk_idf_d_destinazione primary key (id_destinazione);



create table idf_d_esbosco
(
	cod_esbosco           character varying(2)  not null ,
	descr_esbosco         character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  esb_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_esbosco
	add constraint  pk_idf_d_esbosco primary key (cod_esbosco);



create table idf_d_esposizione
(
	id_esposizione        numeric(4)  not null ,
	descr_esposizione     character varying(100)  null ,
	cod_esposizione       character varying(3)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis36_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_esposizione
	add constraint  pk_idf_d_esposizione primary key (id_esposizione);



create table idf_d_fascia_altimetrica
(
	id_fascia_altimetrica  numeric(3)  not null ,
	fascia_altimetica_min  numeric(6)  null ,
	fascia_altimetrica_max  numeric(6)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  falt_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_fascia_altimetrica
	add constraint  pk_idf_d_fascia_altimetrica primary key (id_fascia_altimetrica);



create table idf_d_finalita_taglio
(
	id_finalita_taglio    numeric(3)  not null ,
	descr_finalita_taglio  character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  fint_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_finalita_taglio
	add constraint  pk_idf_d_finalita_taglio primary key (id_finalita_taglio);



CREATE TABLE idf_d_governo
(
	id_governo            integer NULL,
	descr_governo         character varying(50) NULL,
	mtd_ordinamento       numeric(2) NULL,
	flg_visibile          numeric(1) NULL
		CONSTRAINT  flg_visibile CHECK (flg_visibile in (0,1))
);

ALTER TABLE idf_d_governo
	ADD  constraint pk_idf_d_governo PRIMARY KEY (id_governo);
	
	
	
create table idf_d_gruppo
(
	cod_gruppo            character varying(2)  not null ,
	descr_gruppo          character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis59_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_gruppo
	add constraint  pk_idf_d_gruppo primary key (cod_gruppo);



create table idf_d_macro_catfor
(
	id_param_macro_catfor  numeric(3)  not null ,
	descr_catfor          character varying(300)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis25_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_macro_catfor
	add constraint  pk_idf_d_macro_catfor primary key (id_param_macro_catfor);



create table idf_d_macrointervento
(
	id_macrointervento    numeric(3)  not null ,
	descr_macrointervento  character varying(50)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis50_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_macrointervento
	add constraint  pk_idf_d_macrointervento primary key (id_macrointervento);



create table idf_d_origine_allegato
(
	id_origine_allegato   integer  not null ,
	descr_origine_allegato  character varying(200)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis17_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_origine_allegato
	add constraint  pk_idf_d_origine_allegato primary key (id_origine_allegato);



create table idf_d_origine_viabilita
(
	id_origine_viabilita  numeric(2)  not null ,
	origine_viabilita     character varying(3)  null ,
	descrizione_origine   character varying(100)  null ,
	fk_config_ipla        integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis44_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_origine_viabilita
	add constraint  pk_idf_d_origine_viabilita primary key (id_origine_viabilita);



create table idf_d_parametro_trasf
(
	id_parametro_trasf    numeric(3)  not null ,
	fk_tipo_paramero_trasf  numeric(2)  not null ,
	desc_parametro_trasf  character varying(200)  null ,
	valore                numeric(2,1)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  dom_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_parametro_trasf
	add constraint  pk_idf_d_parametro_trasf primary key (id_parametro_trasf);



create table idf_d_priorita
(
	id_priorita           numeric(4)  not null ,
	descr_priorita        character varying(100)  null ,
	cod_priorita          character varying(3)  null ,
	fk_config_ipla        integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis41_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_priorita
	add constraint  pk_idf_d_priorita primary key (id_priorita);



create table idf_d_proprieta
(
	id_proprieta          numeric(4)  not null ,
	descr_proprieta       character varying(100)  null ,
	cod_proprieta         character varying(3)  null ,
	fk_config_ipla        integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis42_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_proprieta
	add constraint  pk_idf_d_proprieta primary key (id_proprieta);



CREATE TABLE idf_d_sottotipo_sup_nota
(
	id_sottotipo_sup_nota  numeric(4) NOT NULL,
	cod_sottotipo_sup_nota  character varying(3) NULL,
	descr_sottotipo_sup_nota  character varying(100) NULL,
	mtd_ordinamento       numeric(2) NULL,
	flg_visibile          numeric(1) NULL
		CONSTRAINT vis78_0_1 CHECK flg_visibile CHECK (flg_visibile in (0,1))
);

ALTER TABLE idf_d_sottotipo_sup_nota
	ADD CONSTRAINT pk_idf_d_sottotipo_sup_nota PRIMARY KEY (id_sottotipo_sup_nota);
	
	
	
	create table idf_d_stadio_sviluppo
(
	cod_stadio_sviluppo   character varying(2)  not null ,
	descr_stadio_sviluppo  character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis12_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_stadio_sviluppo
	add constraint  pk_idf_d_stadio_sviluppo primary key (cod_stadio_sviluppo);



CREATE TABLE idf_d_stato_ads
(
	id_stato_ads          numeric(3) NOT NULL,
	descr_stato_ads       character varying(100) NULL,
	mtd_ordinamento       numeric(2) NULL,
	flg_visibile          numeric(1) NULL
		CONSTRAINT  flg_visibile CHECK (flg_visibile in (0,1))
);

ALTER TABLE idf_d_stato_ads
	ADD constraint pk_idf_d_stato_ads PRIMARY KEY (id_stato_ads);
	
	
	
create table idf_d_stato_intervento
(
	id_stato_intervento   numeric(3)  not null ,
	cod_stato_intervento  character varying(3)  null ,
	descr_stato_intervento  character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  stint_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_stato_intervento
	add constraint  pk_idf_d_stato_intervento primary key (id_stato_intervento);



create table idf_d_stato_istanza
(
	id_stato_istanza      numeric(2)  not null ,
	descr_stato_istanza   character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis1_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_stato_istanza
	add constraint  pk_idf_d_stato_istanza primary key (id_stato_istanza);



create table idf_d_tipo_ads
(
	id_tipo_ads           numeric(4)  not null ,
	cod_tipo_ads          character varying(3)  null ,
	descr_tipo_ads        character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis56_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_ads
	add constraint  pk_idf_d_tipo_ads primary key (id_tipo_ads);



create table idf_d_tipo_allegato
(
	id_tipo_allegato      integer  not null ,
	descr_tipo_allegato   character varying(200)  null ,
	fk_origine_allegato   integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis2_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_allegato
	add constraint  pk_idf_d_tipo_allegato primary key (id_tipo_allegato);



create table idf_d_tipo_campione
(
	cod_tipo_campione     character varying(3)  not null ,
	descr_tipo_campione   character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis62_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_campione
	add constraint  pk_idf_d_tipo_campione primary key (cod_tipo_campione);



create table idf_d_tipo_evento
(
	id_tipo_evento        numeric(3)  not null ,
	descr_tipo_evento     character varying(500)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  tipev_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_evento
	add constraint  pk_idf_d_idf_d_tipo_evento primary key (id_tipo_evento);



create table idf_d_tipo_formazione
(
	id_tipo_formazione    numeric(3)  not null ,
	cod_tipo_formazione   character varying(5)  null ,
	descr_tipo_formazione  character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis15_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_formazione
	add constraint  pk_idf_d_tipo_formazione primary key (id_tipo_formazione);



create table idf_d_tipo_intervento
(
	id_tipo_intervento    numeric(4)  not null ,
	descr_intervento      character varying(100)  null ,
	cod_intervento        character varying(3)  null ,
	fk_macrointervento    numeric(3)  not null ,
	fk_config_ipla        integer  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis49_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_intervento
	add constraint  pk_idf_d_tipo_intervento primary key (id_tipo_intervento);



create table idf_d_tipo_istanza
(
	id_tipo_istanza       numeric(3)  not null ,
	fk_ambito_istanza     numeric(3)  not null ,
	descr_dett_tipoistanza  character varying(500)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  tipoi_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_istanza
	add constraint  pk_idf_d_tipo_istanza primary key (id_tipo_istanza);



create table idf_d_tipo_parametro_trasf
(
	id_tipo_paramero_trasf  numeric(2)  not null ,
	tipo_paremetro_trasf  character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  dom_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_parametro_trasf
	add constraint  pk_idf_d_tipo_parametro_trasf primary key (id_tipo_paramero_trasf);



create table idf_d_tipo_soggetto
(
	id_tipo_soggetto      numeric(2)  not null ,
	tipo_soggetto         character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  dom_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_soggetto
	add constraint  pk_idf_d_tipo_soggetto primary key (id_tipo_soggetto);



create table idf_d_tipo_strutturale
(
	id_tipo_strutturale   numeric(3)  not null ,
	cod_tipo_strutturale  character varying(5)  null ,
	descr_tipo_strutturale  character varying(100)  null ,
	fk_config_ipla        integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis43_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_strutturale
	add constraint  pk_idf_d_tipo_strutturale primary key (id_tipo_strutturale);



create table idf_d_tipo_viabilita
(
	id_tipo_viabilita     numeric(2)  not null ,
	cod_tipo_viabilita    character varying(3)  null ,
	tipo_viabilita        character varying(100)  null ,
	fk_config_ipla        integer  not null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  tviab_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_tipo_viabilita
	add constraint  pk_idf_d_tipo_viabilita primary key (id_tipo_viabilita);



create table idf_d_udm
(
	id_udm                numeric(3)  not null ,
	descr_udm             character varying(10)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  udm_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_udm
	add constraint  pk_idf_d_udm primary key (id_udm);



create table idf_d_uso_viabilita
(
	id_uso_viabilita      numeric(2)  not null ,
	descr_uso_viabilita   character varying(100)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  usov_0_1 check (flg_visibile in (0,1))
);



alter table idf_d_uso_viabilita
	add constraint  pk_idf_d_uso_viabilita primary key (id_uso_viabilita);



create table idf_d_versione
(
	id_versione           integer  not null ,
	descr_versione        character varying(200)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0  constraint  vis13_0_1 check (flg_visibile in (0,1))
);

alter table idf_d_versione
	add constraint  pk_idf_d_versione primary key (id_versione);



CREATE TABLE idf_geo_pl_area_forestale
(
	ID_AREA_FORESTALE     numeric(8) NOT NULL,
	GEOMETRIA             geometry(Geometry,32632) NOT NULL
);

ALTER TABLE idf_geo_pl_area_forestale
	ADD CONSTRAINT pk_idf_geo_pl_area_forestale PRIMARY KEY (ID_AREA_FORESTALE);
	
	
	
create table idf_geo_pl_area_vocazione_tartufi
(
	id_area               integer  not null ,
	fk_attitudine         numeric(3)  not null ,
	tipo_tartufo          character varying(10)  null ,
	geometria             geometry(geometry,32632)  null 
);

alter table idf_geo_pl_area_vocazione_tartufi
	add constraint  pk_idf_geo_area_vocazione_tart primary key (id_area);



CREATE TABLE idf_geo_pl_interv_trasf
(
	idgeo_pl_interv_trasf  INTEGER NULL,
	fk_intervento         numeric(9,0) NOT NULL,
	data_inserimento      DATE NULL,
	geometria             geometry(Geometry,32632) NULL
);

ALTER TABLE idf_geo_pl_interv_trasf
	ADD CONSTRAINT pk_idf_geo_pl_interv_trasf PRIMARY KEY (idgeo_pl_interv_trasf);



create table idf_geo_ln_filare
(
	id_filare             numeric(8)  not null ,
	fk_assetto            numeric(3)  not null ,
	fk_tipo_strutturale   numeric(3)  not null ,
	fk_tipo_composizione  numeric(3)  not null ,
	fk_tipo_formazione    numeric(3)  not null ,
	metri                 numeric(7)  null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_ln_filare
	add constraint  pk_idf_geo_ln_filare primary key (id_filare);



create table idf_geo_ln_evento
(
	idgeo_ln_evento       integer  not null ,
	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	fk_evento             numeric(6)  not null 
);



alter table idf_geo_ln_evento
	add constraint  pk_idf_geo_ln_evento primary key (idgeo_ln_evento);



create table idf_geo_ln_lotto_intervento
(
	idgeo_ln_lotto_intervento  integer  not null ,
	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	fk_intervento         numeric(9,0)  not null 
);



alter table idf_geo_ln_lotto_intervento
	add constraint  pk_idf_geo_ln_lotto_intervento primary key (idgeo_ln_lotto_intervento);



create table idf_geo_ln_pfa_viabilita
(
	idgeo_ln_pfa_viabilita  numeric(9)  not null ,
	fk_tipo_viabilita     numeric(2)  not null ,
	fk_origine_viabilita  numeric(2)  not null ,
	codice_viabilita      character varying(10)  null ,
	lunghezza_metri       integer  null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_ln_pfa_viabilita
	add constraint  pk_idf_geo_ln_pfa_viabilita primary key (idgeo_ln_pfa_viabilita);



create table idf_geo_pl_atomic_pfa
(
	idgeo_pl_atomic_pfa   numeric(10)  not null ,
	idgeo_pl_pfa          numeric(10)  not null ,
	idgeo_superficie_forestale  integer  null ,
	fk_destinazione       numeric(4)  null ,
	fk_tipo_forestale     numeric(8)  null ,
	fk_tipo_strutturale   numeric(3)  null ,
	fk_tipo_intervento    numeric(4)  null ,
	fk_priorita           numeric(4)  null ,
	ettari_sup_forestale  numeric(9,4)  null ,
	flg_tf_us             character varying(2)  null  constraint  dom_tf_us check (flg_tf_us in ('TF','US')),
	idgeo_pl_particella_forestale  integer  null ,
	idgeo_pl_compartimentazione  numeric(10)  null ,
	fk_compresa           numeric(3)  null ,
	denominazione_particella  character varying(25)  null ,
	ettari_partic_forestale  numeric(9,4)  null ,
	cod_particella_for    numeric(3)  null ,
	idgeo_pl_prop_catasto  integer  null ,
	istat_comune          character varying(6)  null ,
	sezione               character varying(2)  null ,
	foglio                numeric(5)  null ,
	allegato_catasto      character varying(1)  null ,
	sviluppo_catasto      character varying(1)  null ,
	particella            character varying(10)  null ,
	sup_catastale_mq      numeric(14,4)  null ,
	sup_cartografica_ha   numeric(9,4)  null ,
	fk_proprieta          numeric(4)  null ,
	intestato             character varying(100)  null ,
	qualita_coltura       character varying(15)  null ,
	flg_usi_civici        numeric(1)  not null  default 0  constraint  usic_01 check (flg_usi_civici in (0,1)),
	flg_possessi_contest  numeric(1)  not null  default 0  constraint  posc_01 check (flg_possessi_contest in (0,1)),
	flg_livellari         numeric(1)  not null  default 0  constraint  livll_01 check (flg_livellari in (0,1)),
	data_aggiornamento_datocatast  date  null ,
	idgeo_pl_zona_servita  numeric(9)  not null ,
	flg_zs                 numeric(1)  not null  default 0  constraint  zs_01 check (flg_zs in (0,1)),
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	note                  character varying(200)  null ,
	superficie_geo        numeric(9,4)  null ,
	provvigione           numeric(9,4)  null ,
	tasso_ripresa_perc    numeric(3)  null ,
	ripresa               numeric(9,4)  null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_pl_atomic_pfa
	add constraint  pk_idf_geo_pl_atomic_pfa primary key (idgeo_pl_atomic_pfa);



create table idf_geo_pl_compartimentazione
(
	idgeo_pl_compartimentazione  numeric(10)  not null ,
	fk_area_forestale     numeric(8)  not null ,
	fk_proprieta          numeric(4)  not null ,
	proprietario          character varying(3)  null ,
	istat                 character varying(6)  null ,
	flg_uc                numeric(1)  not null  default 0 constraint  uc_01 check (flg_uc in (0,1)),
	flg_li                numeric(1)  not null  default 0 constraint  li_01 check (flg_li in (0,1)),
	flg_pc                numeric(1)  not null  default 0 constraint  pc_01 check (flg_pc in (0,1)),
	note                  character varying(100)  null ,
	area                  numeric(11,4)  null ,
	codice_classe_compartiment  character varying(2)  null ,
	geometria             geometry(geometry,32632)  not null 
);



alter table idf_geo_pl_compartimentazione
	add constraint  pk_idf_geo_pl_compartimentazio primary key (idgeo_pl_compartimentazione);



create table idf_geo_pl_comprensorio
(
	id_comprensorio       numeric(9)  not null ,
	geometria             geometry(geometry,32632)  not null 
);



alter table idf_geo_pl_comprensorio
	add constraint  pk_idf_geo_pl_comprensorio primary key (id_comprensorio);



create table idf_geo_pl_comune
(
	id_comune             integer  not null ,
	istat_comune          character varying(6)  not null ,
	istat_prov            character varying(3)  not null ,
	denominazione_comune  character varying(50)  null ,
	fk_area_forestale     numeric(8)  not null ,
	codice_belfiore       character varying(4)  null ,
	geometria             geometry(geometry,32632)  null ,
	data_inizio_validita  date  null ,
	data_fine_validita    date  null 
);



alter table idf_geo_pl_comune
	add constraint  pk_idf_geo_pl_comune primary key (id_comune);



create table idf_geo_pl_evento
(
	idgeo_pl_evento       integer  not null ,
	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	fk_evento             numeric(6)  not null 
);



alter table idf_geo_pl_evento
	add constraint  pk_idf_geo_pl_evento primary key (idgeo_pl_evento);



create table idf_geo_pl_lotto_intervento
(
	idgeo_pl_lotto_intervento  integer  not null ,
	fk_intervento         numeric(9,0)  not null ,	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	sup_tagliata_ha				numeric(9,4)
);



alter table idf_geo_pl_lotto_intervento
	add constraint  pk_idf_geo_pl_lotto_intervento primary key (idgeo_pl_lotto_intervento);



create table idf_geo_pl_particella_forest
(
	idgeo_pl_particella_forest  integer  not null ,
	fk_padre              integer  null ,
	idgeo_pl_pfa          numeric(10)  not null ,
	fk_compresa           numeric(3)  not null ,
	denominazione_particella  character varying(25)  null ,
	ettari                numeric(9,4)  null ,
	cod_particella_for    numeric(3)  null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	geometria             geometry(geometry,32632)  not null ,
	provvigione_ha        numeric(9,4)  null ,
	provvigione_reale     numeric(9,4)  null ,
	perc_tasso_ripresa_pot  integer  null ,
	perc_tara             integer  null ,
	ripresa_tot_ha        numeric(9,4)  null ,
	ripresa_attuale       numeric(9,4)  null 
);



alter table idf_geo_pl_particella_forest
	add constraint  pk_idf_geo_pl_particella_fores primary key (idgeo_pl_particella_forest);



create table idf_geo_pl_pfa
(
	idgeo_pl_pfa          				 numeric(10)  not null ,
	denominazione         				 character varying(250)  null ,
	fonte_finanziamento   				 character varying(100)  null ,
	geometria             				 geometry(geometry,32632)  not null ,
	data_inizio_validita  				 date  not null ,
	data_fine_validita    				 date  null ,
	data_approvazione     				 date  null ,
	data_revisione        				 date  null ,
	sup_pianificata_totale  			 numeric  null ,
	sup_forestale_gest_attiva  		 numeric  null ,
	note                  				 character varying(200)  null ,
	utente_aggiornamento  				 character varying(50)  null ,
	n_delibera            				 character varying(50)  null ,
	durata_pfa_anni       				 integer  null ,
	flg_revisione         				 numeric(1) not null default 0 constraint revis_01 check (flg_revisione in (0,1)),
	proprieta_silvopast_ha  			 numeric(9,4)  null ,
	proprieta_forestale_ha  			 numeric(9,4)  null ,
	superf_tot_boscata_ha  				 numeric(9,4)  null ,
	superf_bosc_gest_attiva_ha  	 numeric(9,4)  null ,
	superf_gest_non_attiva_tot_ha  numeric(9,4)  null ,
	superf_gest_non_attiva_mon_ha  numeric(9,4)  null ,
	superf_gest_non_attiva_evl_ha  numeric(9,4)  null ,
	superf_altri_usi_ha   				 numeric(9,4)  null ,
	nome_breve_pfa        				 character varying(200)  null ,
	fk_versione           				 integer  not null ,
	data_inserimento      				 date  null ,
	data_aggiornamento    				 date  null ,
	prop_non_forestale_ha  				 numeric(9,4)  null ,
	sup_pianif_non_forestale_ha    numeric(9,4)  null ,
	sup_pianif_forestale_ha  			 numeric(9,4)  null ,
	fk_proprieta_primpa						 numeric(4)
);

alter table idf_geo_pl_pfa
	add constraint  pk_idf_geo_pl_pfa primary key (idgeo_pl_pfa);



create table idf_geo_pl_prop_catasto
(
	idgeo_pl_prop_catasto  integer  not null ,
	idgeo_pl_pfa          numeric(10)  null ,
	fk_comune             integer  null ,
	sezione               character varying(2)  null ,
	foglio                numeric(5)  null ,
	allegato_catasto      character varying(1)  null ,
	sviluppo_catasto      character varying(1)  null ,
	particella            character varying(10)  null ,
	sup_catastale_mq      numeric(14,4)  null ,
	sup_cartografica_ha   numeric(9,4)  null ,
	fk_proprieta          numeric(4)  not null ,
	intestato             character varying(100)  null ,
	qualita_coltura       character varying(15)  null ,
	flg_usi_civici        numeric(1)  not null  default 0 constraint  usic_01 check (flg_usi_civici in (0,1)),
	flg_possessi_contest  numeric(1)  not null  default 0 constraint  pss_01 check (flg_possessi_contest in (0,1)),
	flg_livellari         numeric(1)  not null  default 0 constraint  lvlr_01 check (flg_livellari in (0,1)),
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	note                  character varying(200)  null ,
	data_aggiornamento_datocatast  date  null ,
	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	fk_config_utente      integer  null 
);

alter table idf_geo_pl_prop_catasto
	add constraint  pk_idf_geo_pl_prop_catasto primary key (idgeo_pl_prop_catasto);



create table idf_geo_pl_provincia
(
	istat_prov            character varying(3)  not null ,
	fk_regione 						numeric(2) not null,
	sigla_prov            character varying(5)  null ,
	denominazione_prov    character varying(50)  null ,
	geometria             geometry(geometry,32632)  null 
);

alter table idf_geo_pl_provincia
	add constraint  pk_idf_geo_pl_provincia primary key (istat_prov);



create table idf_geo_pl_settore
(
	id_settore            numeric(9)  not null ,
	geometria             geometry(geometry,32632)  not null 
);

alter table idf_geo_pl_settore
	add constraint  pk_idf_geo_pl_settore primary key (id_settore);



create table idf_geo_pl_sup_forestale
(
	idgeo_superficie_forestale  integer  not null ,
	idgeo_pl_pfa          numeric(10)  not null ,
	fk_destinazione       numeric(4)  not null ,
	fk_tipo_forestale     numeric(8)  null ,
	fk_tipo_strutturale   numeric(3)  null ,
	fk_compresa           numeric(3)  not null ,
	fk_priorita           numeric(4)  not null ,
	ettari                numeric(9,4)  null ,
	flg_tf_us             character varying(2)  null  constraint  dom_tf_us check (flg_tf_us in ('TF','US')),
	geometria             geometry(geometry,32632)  null ,
	fk_tipo_intervento    numeric(4)  null 
);

alter table idf_geo_pl_sup_forestale
	add constraint  pk_idf_geo_pl_sup_forestale primary key (idgeo_superficie_forestale);



create table idf_geo_pl_zona_altimetrica
(
	idgeo_pl_zona_altimetrica  		integer  not null ,
	fk_comune             				integer  null ,
	sezione               				character varying(2)  null ,
	foglio                				numeric(5)  null ,
	data_inizio_validita  				date  not null ,
	data_fine_validita    				date  null ,
	note                  				character varying(200)  null ,
	geometria             				geometry(geometry,32632)  null ,
	fk_paramtrasf_zona_altimetrica  numeric(3)  null ,
	data_inserimento      				date  null ,
	data_aggiornamento    				date  null 
);

alter table idf_geo_pl_zona_altimetrica
	add constraint  pk_idf_geo_pl_zona_altimetrica primary key (idgeo_pl_zona_altimetrica);



create table idf_geo_pl_zona_servita
(
	idgeo_pl_zona_servita numeric(9)  not null ,
	idgeo_pl_pfa          numeric(10)  not null ,
	ettari                numeric(9,4)  null ,
	flg_zs                numeric(1)  not null  default 0 constraint  zs_01 check (flg_zs in (0,1)),
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	geometria             geometry(geometry,32632)  not null ,
	note                  character varying(500)  null 
);

alter table idf_geo_pl_zona_servita
	add constraint  pk_idf_geo_pl_zona_servita primary key (idgeo_pl_zona_servita);



create table idf_geo_pl_popolamento_seme
(
	id_popolamento_seme   numeric(8)  not null ,
	codice_amministrativo character varying(4)  not null ,
	denominazione         character varying(300)  null ,
	specie_idonee         character varying(1000)  null ,
	data_modifica         timestamp  null ,
	data_fine_validita    timestamp  null ,
	geometria             geometry(geometry,32632)  null 
);

alter table idf_geo_pl_popolamento_seme
	add constraint  pk_idf_geo_pl_popolamento_seme primary key (id_popolamento_seme);



create table idf_geo_pt_area_di_saggio
(
	idgeo_pt_ads          numeric(9)  not null ,
	fk_comune             integer  null ,
	fk_soggetto_pg        numeric(5)  null ,
	data_ril              date  null DEFAULT now(),
	codice_ads            character varying(20)  not null ,
	fk_settore            numeric(9)  not null DEFAULT 0,
	fk_proprieta          numeric(4)  not null DEFAULT 0,
	fk_tipo_ads           numeric(4)  not null DEFAULT 0,
	fk_stato_ads          numeric(3)  not null DEFAULT 0,
	fk_assetto            numeric(3)  not null DEFAULT 0,
	fk_esposizione        numeric(4)  not null DEFAULT 0,
	fk_comunita_montana   numeric(4)  null DEFAULT 1,
	fk_tipo_forestale  		numeric(8)  not null DEFAULT 0,
	fk_destinazione       numeric(4)  not null DEFAULT 0,
	fk_priorita           numeric(4)  not null DEFAULT 0,
	flg_dia               character varying(1)  null  constraint  dia_b_i_s check (flg_dia in ('B','I','S')),
	fk_soggetto           numeric(5)  null ,
	quota                 numeric(4)  null ,
	inclinazione          numeric(2)  null ,
	data_inizio_validita  date  not null DEFAULT now(),
	data_fine_validita    date  null ,
	note                  character varying(2000)  null ,
	fk_ambito             integer  not null DEFAULT 0,
	idgeo_pl_particella_forest  integer  null ,
	fk_danno              numeric(4)  not null DEFAULT 0,
	fk_tipo_intervento    numeric(4)  not null DEFAULT 0,
	geometria             geometry(geometry,32632)  null 
);

alter table idf_geo_pt_area_di_saggio
	add constraint  pk_idf_geo_pt_area_di_saggio primary key (idgeo_pt_ads);



create table idf_geo_pt_evento
(
	idgeo_pt_evento       integer  not null ,
	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	fk_evento             numeric(6)  not null 
);



alter table idf_geo_pt_evento
	add constraint  pk_idf_geo_pt_evento primary key (idgeo_pt_evento);



create table idf_geo_pt_lotto_intervento
(
	idgeo_pt_lotto_intervento  integer  not null ,
	data_inserimento      date  null ,
	geometria             geometry(geometry,32632)  null ,
	id_intervento         numeric(9,0)  not null 
);



alter table idf_geo_pt_lotto_intervento
	add constraint  pk_idf_geo_pt_lotto_intervento primary key (idgeo_pt_lotto_intervento);



create table idf_geo_pl_tipo_arboricoltura
(
	id_geo_tipo_arboricoltura  numeric(8)  not null ,
	fk_tipo_forestale     numeric(8)  not null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_pl_tipo_arboricoltura
	add constraint  pk_idf_geo_pl_tipo_arboricoltura primary key (id_geo_tipo_arboricoltura);



create table idf_geo_pl_tipo_forestale
(
	id_geo_tipo_forestale  numeric(8)  not null ,
	fk_tipo_forestale     numeric(8)  not null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_pl_tipo_forestale
	add constraint  pk_idf_geo_pl_tipo_forestale primary key (id_geo_tipo_forestale);



create table idf_geo_pl_tipo_forestale_10
(
	id_geo_tipo_forestale_10  numeric(8)  not null ,
	fk_tipo_forestale     numeric(8)  not null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_pl_tipo_forestale_10
	add constraint  pk_idf_geo_pl_tipo_forestale_10 primary key (id_geo_tipo_forestale_10);



create table idf_geo_pl_vinc_idro10k
(
	id_vinc_idro10k       integer  not null ,
	geometria             geometry(geometry,32632)  null 
);



alter table idf_geo_pl_vinc_idro10k
	add constraint  pk_idf_geo_pl_vinc_idro10k primary key (id_vinc_idro10k);



create table idf_r_ads_combustibile
(
	idgeo_pt_ads          numeric(9)  not null ,
	cod_combustibile      character varying(1)  not null ,
	flg_principale        numeric(1)  not null  default 0 constraint  prnc_01 check (flg_principale in (0,1)),
	perc_copertura_lettiera  numeric(3)  null ,
	perc_copertura_erbacea  numeric(3)  null ,
	perc_copertura_cespugli  numeric(3)  null 
);



alter table idf_r_ads_combustibile
	add constraint  pk_idf_r_ads_combustibile primary key (idgeo_pt_ads,cod_combustibile,flg_principale);



create table idf_r_adsrel_specie
(
	idgeo_pt_ads          numeric(9)  not null ,
	id_specie             numeric(5)  not null ,
	data_inizio_validita  timestamp  not null ,
	cod_tipo_campione     character varying(3)  not null ,
	flg_pollone_seme      character varying(1)  null  constraint  dom_p_s check (flg_pollone_seme in ('S','P')),
	qualita               character varying(1)  null ,
	diametro              numeric(3)  null ,
	altezza               numeric(2)  null ,
	incremento            numeric(2)  null ,
	eta                   numeric(3)  null ,
	nr_alberi_seme        numeric(6)  null ,
	nr_alberi_pollone     numeric(6)  null ,
	data_fine_validita    date  null ,
	note                  character varying(200)  null 
);



alter table idf_r_adsrel_specie
	add constraint  pk_idf_r_adsrel_specie primary key (idgeo_pt_ads,id_specie,data_inizio_validita);



create table idf_r_comune_pfa
(
	idgeo_pl_pfa          numeric(10)  not null ,
	id_comune             integer  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null 
);



alter table idf_r_comune_pfa
	add constraint  pk_idf_r_comune_pfa primary key (idgeo_pl_pfa,id_comune);



create table idf_r_evento_partfor
(
	id_evento             numeric(6)  not null ,
	idgeo_pl_particella_forest  integer  not null ,
	perc_danno            integer  null 
);



alter table idf_r_evento_partfor
	add constraint  pk_idf_r_evento_partfor primary key (id_evento,idgeo_pl_particella_forest);



create table idf_r_intervento_aapp
(
	id_intervento         numeric(9,0)  not null ,
	codice_aapp           character varying(10)  not null 
);



alter table idf_r_intervento_aapp
	add constraint  pk_idf_r_intervento_aapp primary key (id_intervento,codice_aapp);



create table idf_r_intervento_catfor
(
	id_intervento         numeric(9,0)  not null ,
	id_categoria_forestale  numeric(2)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null 
);



alter table idf_r_intervento_catfor
	add constraint  pk_idf_r_intervento_catfor primary key (id_intervento,id_categoria_forestale);



create table idf_r_intervento_pop_seme
(
	id_intervento         numeric(9,0)  not null ,
	id_popolamento_seme   numeric(8)  not null 
);



alter table idf_r_intervento_pop_seme
	add constraint  pk_idf_r_intervento_pop_seme primary key (id_intervento,id_popolamento_seme);



create table idf_r_intervento_rn_2000
(
	id_intervento         numeric(9,0)  not null ,
	codice_rn_2000        character varying(9)  not null 
);



alter table idf_r_intervento_rn_2000
	add constraint  pk_idf_r_intervento_rn_2000 primary key (id_intervento,codice_rn_2000);



create table idf_r_interventoselv_usoviab
(
	id_intervento         numeric(9,0)  not null ,
	fk_uso_viabilita      numeric(2)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  null 
);



alter table idf_r_interventoselv_usoviab
	add constraint  pk_idf_r_interventoselv_usovia primary key (id_intervento,fk_uso_viabilita);



create table idf_r_intervselv_esbosco
(
	id_intervento         numeric(9,0)  not null ,
	cod_esbosco           character varying(2)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  null 
);



alter table idf_r_intervselv_esbosco
	add constraint  pk_idf_r_intervselv_esbosco primary key (id_intervento,cod_esbosco);



create table idf_r_intervselv_evento
(
	id_evento             numeric(6)  not null ,
	id_intervento         numeric(9,0)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  null 
);



alter table idf_r_intervselv_evento
	add constraint  pk_idf_r_intervselv_evento primary key (id_evento,id_intervento);



create table idf_r_paramtrasf_trasformazion
(
	id_paramero_trasf  		numeric(2)  not null ,
	id_intervento         numeric(9,0)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null 
);



alter table idf_r_paramtrasf_trasformazion
	add constraint  pk_idf_r_paramtrasf_trasformaz primary key (id_paramero_trasf,id_intervento);



create table idf_r_partfor_intervento
(
	id_intervento         			numeric(9,0)  not null ,
	idgeo_pl_particella_forest  integer  not null ,
	data_inizio_validita  			date  not null ,
	data_fine_validita    			date  null ,
	ripresa_attuale					 		numeric(9,4)
);

alter table idf_r_partfor_intervento
	add constraint  pk_idf_r_partfor_intervento primary key (id_intervento,idgeo_pl_particella_forest);



create table idf_r_pf_pg
(
	id_soggetto_pf        numeric(5)  not null ,
	id_soggetto_pg        numeric(5)  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null 
);



alter table idf_r_pf_pg
	add constraint  pk_idf_r_pf_pg primary key (id_soggetto_pf,id_soggetto_pg);



create table idf_r_pfa_area_protetta
(
	idgeo_pl_pfa          numeric(10)  not null ,
	id_area_protetta      integer  not null ,
	data_inizio_validita  date  not null ,
	nome_area_protetta    character varying(500)  null ,
	data_fine_validita    date  null ,
	sup_ricadenza_ha      numeric(9,4)  null ,
	perc_ricadenza 				numeric(3,0)
);



alter table idf_r_pfa_area_protetta
	add constraint  pk_idf_r_pfa_area_protetta primary key (idgeo_pl_pfa,id_area_protetta,data_inizio_validita);



create table idf_r_pfa_pg
(
	idgeo_pl_pfa          numeric(10)  not null ,
	id_soggetto_coinvolto  numeric(5)  not null ,
	id_tipo_soggetto      numeric(2)  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null 
);



alter table idf_r_pfa_pg
	add constraint  pk_idf_r_pfa_pg primary key (idgeo_pl_pfa,id_soggetto_coinvolto,id_tipo_soggetto);



create table idf_r_pfa_popseme
(
	idgeo_pl_pfa          numeric(10)  not null ,
	id_popolamento_seme   numeric(8)  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	sup_ricadenza_ha      numeric(9,4)  null 
);



alter table idf_r_pfa_popseme
	add constraint  pk_idf_r_pfa_popseme primary key (idgeo_pl_pfa,id_popolamento_seme);



create table idf_r_pfa_rn_2000
(
	idgeo_pl_pfa          numeric(10)  not null ,
	id_rn_2000            integer  not null ,
	data_inizio_validita  date  not null ,
	nome_rn_2000          character varying(500)  null ,
	sup_ricadenza_ha      numeric(9,4)  null ,
	data_fine_validita    date  null 
);



alter table idf_r_pfa_rn_2000
	add constraint  pk_idf_r_pfa_rn_2000 primary key (idgeo_pl_pfa,id_rn_2000,data_inizio_validita);



create table idf_r_pfa_viabilita
(
	idgeo_pl_pfa          numeric(10)  not null ,
	idgeo_ln_pfa_viabilita  numeric(9)  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null 
);



alter table idf_r_pfa_viabilita
	add constraint  pk_idf_r_pfa_viabilita primary key (idgeo_pl_pfa,idgeo_ln_pfa_viabilita);



create table idf_r_pfainterv_geosupfor
(
	idgeo_superficie_forestale  integer  not null ,
	id_intervento         numeric(9,0)  not null ,
	data_inizio_validita  date  null ,
	data_fine_validita    date  null 
);



alter table idf_r_pfainterv_geosupfor
	add constraint  pk_idf_r_pfainterv_geosupfor primary key (idgeo_superficie_forestale,id_intervento);



create table idf_r_propcatasto_intervento
(
	idgeo_pl_prop_catasto  integer  not null ,
	id_intervento         numeric(9,0)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null 
);



alter table idf_r_propcatasto_intervento
	add constraint  pk_idf_r_propcatasto_intervent primary key (idgeo_pl_prop_catasto,id_intervento);



create table idf_r_soggetto_intervento
(
	id_intervento         numeric(9,0)  not null ,
	id_soggetto           numeric(5)  not null ,
	id_tipo_soggetto      numeric(2)  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null 
);



alter table idf_r_soggetto_intervento
	add constraint  pk_idf_r_soggetto_intervento primary key (id_intervento,id_soggetto,id_tipo_soggetto);



create table idf_r_specie_pfa_intervento
(
	id_specie             numeric(5)  not null ,
	id_intervento         numeric(9,0)  not null ,
	volume_specie         numeric  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	flg_specie_priorita   character varying(1)  null  constraint  speciep_p_s check (flg_specie_priorita in ('S','P')),
	fk_config_utente      integer  null ,
	fk_udm                numeric(3)  not null 
);



alter table idf_r_specie_pfa_intervento
	add constraint  pk_idf_r_specie_pfa_intervento primary key (id_specie,id_intervento);



create table idf_r_sup_tipo_forestale
(
	id_geo_tipo_forestale  numeric(8)  not null ,
	idgeo_superficie_forestale  integer  not null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null 
);



alter table idf_r_sup_tipo_forestale
	add constraint  pk_idf_r_sup_tipo_forestale primary key (id_geo_tipo_forestale,idgeo_superficie_forestale);



create table idf_r_tipoint_cnfipla
(
	id_config_ipla        integer  not null ,
	id_tipo_intervento    numeric(4)  not null 
);



alter table idf_r_tipoint_cnfipla
	add constraint  pk_idf_r_tipoint_cnfipla primary key (id_config_ipla,id_tipo_intervento);



create table idf_s_geo_ln_viabilita
(
	idgeo_ln_viabilita    numeric(9)  not null ,
	data_storicizzazione  date  not null ,
	fk_viabilita          numeric(6)  not null ,
	geometria             geometry(geometry,32632)  not null ,
	origine               character varying(1)  null ,
	d_breve_origine       character varying(25)  null ,
	d_estesa_origine      character varying(100)  null 
);



alter table idf_s_geo_ln_viabilita
	add constraint  pk_idf_s_geo_ln_viabilita primary key (idgeo_ln_viabilita,data_storicizzazione);



create table idf_s_viabilita
(
	id_viabilita          numeric(6)  not null ,
	data_storicizzazione  date  not null ,
	fk_tipo_viabilita     numeric(2)  not null ,
	strada                character varying(4)  null ,
	progres               character varying(4)  null ,
	comune                character varying(6)  null ,
	rilievo               character varying(1)  null ,
	d_rilievo             character varying(100)  null ,
	lunghezza             numeric(5)  null ,
	partenza              character varying(100)  null ,
	tipo                  character varying(2)  null ,
	funzione              character varying(1)  null ,
	d_funzione            character varying(100)  null ,
	regola                character varying(1)  null ,
	d_regola              character varying(100)  null ,
	regola_l              character varying(20)  null ,
	stabilita             character varying(1)  null ,
	d_stabilita           character varying(100)  null ,
	fondo                 character varying(1)  null ,
	d_fondo               character varying(100)  null ,
	piazzol               numeric(2)  null ,
	cunettel              character varying(1)  null ,
	d_cunette_l           character varying(100)  null ,
	cunettet              numeric(3)  null ,
	tomboni               character varying(5)  null ,
	sostegno              character varying(1)  null ,
	d_sostegno            character varying(100)  null ,
	ponti                 numeric(2)  null ,
	ctr                   numeric(6)  null ,
	data_ril              date  null ,
	tombonin              numeric(2)  null ,
	fk_area_forestale     numeric(8)  null ,
	idgeo_pl_pfa          numeric(10)  not null 
);



alter table idf_s_viabilita
	add constraint  pk_idf_s_viabilita primary key (id_viabilita,data_storicizzazione);



create table idf_t_ads_relascopica
(
	idgeo_pt_ads          			numeric(9)  not null ,
	fk_tipo_strutturale_princ  	numeric(3)  not null ,
	fk_tipo_strutturale_second  numeric(3)  null ,
	fattore_numerazione   			numeric(6)  null ,
	fk_classe_fertilita					numeric(3)	null,
	note 												character varying(200)
);



alter table idf_t_ads_relascopica
	add constraint  pk_idf_t_ads_relascopica primary key (idgeo_pt_ads);



create table idf_t_ads_superficie_nota
(
	idgeo_pt_ads          numeric(9)  not null ,
	densita_campionamento  numeric(4,1)  null ,
	raggio_mt             numeric(4)  null ,
	cod_stadio_sviluppo   character varying(2)  not null ,
	perc_copertura_chiome  numeric(3)  null ,
	attitudine_produttiva  character varying(1)  null ,
	cod_esbosco           character varying(2)  not null ,
	dist_esbosco_fuori_pista  numeric(4)  null ,
	dist_esbosco_su_pista  numeric(4)  null ,
	min_distanza_planimetrica  numeric(4)  null ,
	nr_ceppaie            numeric(2)  not null ,
	nr_piante_morte       numeric(2)  null ,
	rinnovazione          numeric(2)  null ,
	rin_specie_prevalente  character varying(2)  null ,
	danni_perc            numeric(3)  null ,
	flg_pascolamento      numeric(1)  not null  default 0 constraint  pscl_01 check (flg_pascolamento in (0,1)),
	perc_defogliazione    numeric(3)  null ,
	perc_ingiallimento    numeric(3)  null ,
	fk_tipo_strutturale_princ  numeric(3)  not null ,
	fk_tipo_strutturale_second  numeric(3)  not null ,
	combust_p             numeric(1)  not null 
	note									character varying(200),
	fk_sottotipo_sup_nota numeric(4,0)
);



alter table idf_t_ads_superficie_nota
	add constraint  pk_idf_t_ads_superficie_nota primary key (idgeo_pt_ads);



create table idf_t_area_forestale
(
	id_area_forestale     numeric(8)  not null ,
	af_cod                character varying(4)  null ,
	af_nome               character varying(100)  null ,
	descrizione           character varying(2000)  null ,
	sup_totale            numeric(11,4)  null ,
	sup_forestale         numeric(11,4)  null ,
	indice_boscosita      numeric(7,4)  null 
);



alter table idf_t_area_forestale
	add constraint  pk_idf_t_area_forestale primary key (id_area_forestale);



create table idf_t_comprensorio
(
	id_comprensorio       numeric(9)  not null ,
	fk_area_forestale     numeric(8)  not null ,
	fk_proprieta          numeric(4)  not null ,
	istat_comune          character varying(6)  null ,
	codice                character varying(4)  null ,
	nome                  character varying(100)  null ,
	sup_totale            numeric(6)  null ,
	sup_tot_pastorale     numeric(6)  null ,
	anno                  numeric(4)  null ,
	alpeggi               character varying(2000)  null 
);



alter table idf_t_comprensorio
	add constraint  pk_idf_t_comprensorio primary key (id_comprensorio);



create table idf_t_config
(
	id_config             numeric(2)  not null ,
	codice                character varying(20)  null ,
	valore                character varying(500)  null 
);



alter table idf_t_config
	add constraint  pk_idf_t_config primary key (id_config);



create table idf_t_documento_allegato
(
	id_documento_allegato  integer  not null ,
	idgeo_pl_pfa          numeric(10)  not null ,
	fk_intervento         numeric(9,0)  null ,
	fk_tipo_allegato      integer  not null ,
	nome_allegato         character varying(200)  null ,
	dimensione_allegato_kb  numeric(10)  null ,
	data_inizio_validita  date  not null ,
	data_fine_validita    date  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null ,
	note                  character varying(2000)  null,
	uid_index             	character varying(500) null,
	id_doc_doqui          	character varying(500) null,
	ud_classificazione    	character varying(500) null 
);



alter table idf_t_documento_allegato
	add constraint  pk_idf_t_documento_allegato primary key (id_documento_allegato);



create table idf_t_evento
(
	id_evento             numeric(6)  not null ,
	fk_tipo_evento        numeric(3)  not null ,
	nome_breve            character varying(50)  null ,
	data_evento           date  null ,
	progressivo_evento_pfa  numeric(4)  null ,
	descr_evento          character varying(1000)  null ,
	localita              character varying(100)  null ,
	sup_interessata_ha    numeric(9,4)  null ,
	annata_silvana        numeric(4,0)  null 
);



alter table idf_t_evento
	add constraint  pk_idf_t_evento primary key (id_evento);



create table idf_t_idf_all_tav
(
	id_specie             numeric(5)  not null ,
	num_tavola_coeff_cub  integer  not null ,
--	flg_coniflatif        character varying(1)  not null constraint  cnfl_cl check (flg_coniflatif in ('C','L')),
	descrizione           character varying(16)  null ,
	t0                    numeric(9,6)  null ,
	t1                    numeric(9,6)  null ,
	t2                    numeric(9,6)  null ,
	t3                    numeric(9,6)  null 
);

alter table idf_t_idf_all_tav
	add constraint  pk_idf_t_idf_all_tav primary key (id_specie,num_tavola_coeff_cub);



CREATE TABLE idf_t_interv_selvicolturale
(
	id_intervento         numeric(9,0) NOT NULL,
	fk_tipo_INTERVENTO    numeric(4) NOT NULL,
	IDGEO_PL_PFA          numeric(10) NULL,
	fk_tipo_forestale_prevalente  numeric(8) NULL,
	fk_stato_intervento   numeric(3) NOT NULL,
	fk_finalita_taglio    numeric(3) NOT NULL,
	fk_dest_legname       numeric(3) NOT NULL,
	fk_fascia_altimetrica  numeric(3) NOT NULL,
	flg_interv_non_previsto  character varying(1) NULL
		CONSTRAINT  flg_interv_non_previsto CHECK (flg_interv_non_previsto in ('S','N')),
	fk_config_ipla        integer NOT NULL,
	nr_piante             numeric(3) NULL,
	stima_massa_retraibile_m3  numeric(8) NULL,
	m3_prelevati          numeric(8) NULL,
	volume_ramaglia_m3    numeric(5) NULL,
	data_presa_in_carico  DATE NULL,
	annata_silvana        character varying(20) NULL,
	nr_progressivo_interv  INTEGER NULL,
	flg_istanza_taglio    numeric(1) NULL
		CONSTRAINT  flg_istanza_taglio CHECK (flg_istanza_taglio in (0,1)),
	flg_piedilista        numeric(1) NULL
		CONSTRAINT  flg_piedilista CHECK (flg_piedilista in (0,1)),
	flg_conforme_deroga   character varying(1) NULL
		CONSTRAINT  flg_conforme_deroga CHECK (flg_conforme_deroga in ('C','D')),
	note_esbosco          character varying(1000) NULL,
	data_inserimento      DATE NULL,
	data_aggiornamento    DATE NULL,
	fk_config_utente      INTEGER NOT NULL,
	ripresa_prevista_mc   numeric(9,4) NULL,
	ripresa_reale_fine_interv_mc  numeric(9,4) NULL,
	data_inizio_intervento  DATE NULL,
	data_fine_intervento  DATE NULL,
	stima_valore_lotto    numeric NULL,
	valore_aggiudicazione_asta  numeric NULL,
	fk_governo            integer NOT NULL
);

alter table idf_t_interv_selvicolturale
	add constraint  pk_idf_t_interv_selvicolturale primary key (id_intervento);



CREATE TABLE idf_t_interv_trasformazione
(
	id_intervento         numeric(9,0) NOT NULL,
	flg_compensazione     character varying(1) 
		CONSTRAINT  flg_compensazione CHECK (flg_compensazione in ('F','M','N')),
	flg_art7_a            numeric(1) NULL
		CONSTRAINT  flg_art7_a CHECK (flg_art7_a in (0,1)),
	flg_art7_b            numeric(1) NULL
		CONSTRAINT  flg_art7_b CHECK (flg_art7_b in (0,1)),
	flg_art7_c            numeric(1) NULL
		CONSTRAINT  flg_art7_c CHECK (flg_art7_c in (0,1)),
	flg_art7_d            numeric(1) NULL
		CONSTRAINT  flg_art7_d CHECK (flg_art7_d in (0,1)),
	flg_art7_d_bis        numeric(1) NULL
		CONSTRAINT  flg_art7_d_bis CHECK (flg_art7_d_bis in (0,1)),
	flg_proprieta         numeric(1) NULL
		CONSTRAINT  flg_proprieta CHECK (flg_proprieta in (0,1)),
	flg_dissensi          numeric(1) NULL
		CONSTRAINT  flg_dissensi CHECK (flg_dissensi in (0,1)),
	flg_aut_paesaggistica  numeric(1) NULL
		CONSTRAINT  flg_aut_paesaggistica CHECK (flg_aut_paesaggistica in (0,1)),
	data_aut_paesaggistica  DATE NULL,
	nr_aut_paesaggistica  character varying(50) NULL,
	ente_aut_paesaggistica  character varying(200) NULL,
	flg_vinc_idro         numeric(1) NULL
		CONSTRAINT  flg_vinc_idro CHECK (flg_vinc_idro in (0,1)),
	flg_aut_vidro         numeric(1) NULL
		CONSTRAINT  flg_aut_vidro CHECK (flg_aut_vidro in (0,1)),
	data_aut_vidro        DATE NULL,
	nr_aut_vidro          character varying(50) NULL,
	flg_aut_incidenza     numeric(1) NULL
		CONSTRAINT  flg_aut_incidenza CHECK (flg_aut_incidenza in (0,1)),
	data_aut_incidenza    DATE NULL,
	nr_aut_incidenza      character varying(50) NULL,
	ente_aut_incidenza    character varying(200) NULL,
	ente_aut_vidro        character varying(200) NULL,
	flg_cauzione_cf       numeric(1) NULL
		CONSTRAINT  flg_cauzione_cf CHECK (flg_cauzione_cf in (0,1)),
	flg_versamento_cm     numeric(1) NULL
		CONSTRAINT  flg_versamento_cm CHECK (flg_versamento_cm in (0,1)),
	altri_pareri          character varying(1000) NULL,
	note_dichiarazione    character varying(1000) NULL,
	compensazione_euro_teorica    numeric(9,2) NULL,
	data_inserimento      DATE NULL,
	data_aggiornamento    DATE NULL,
	fk_config_utente      INTEGER NULL,
	compensazione_euro_reale numeric(9,2) NULL,
	compensazione_note    character varying(1000) NULL
);



alter table idf_t_interv_trasformazione
	add constraint  pk_idf_t_interv_trasformazione primary key (id_intervento);



create table idf_t_intervento
(
	id_intervento         numeric(9,0)  not null ,
	superficie_interessata  numeric(5)  null ,
	localita              character varying(100)  null ,
	descrizione_intervento  character varying(200)  null ,
	fk_soggetto_profess   integer  null ,
	data_inizio_intervento  date  null ,
	data_fine_intervento  date  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente_compilatore  integer  not null 
);



alter table idf_t_intervento
	add constraint  pk_idf_t_intervento primary key (id_intervento);



create table idf_t_istanza_forest
(
	id_istanza_intervento  numeric(9,0)  not null ,
	fk_sogg_settore_regionale  numeric(5)  null ,
	fk_stato_istanza      numeric(2)  not null ,
	nr_istanza_forestale  numeric(9)  null ,
	data_invio            date  null ,
	data_verifica         date  null ,
	data_protocollo       date  null ,
	nr_protocollo         character varying(50)  null ,
	data_ult_agg          date  null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  not null ,
	fk_tipo_istanza       numeric(3)  not null 
);



alter table idf_t_istanza_forest
	add constraint  pk_idf_t_istanza_forest primary key (id_istanza_intervento);



create table idf_t_settore
(
	id_settore            numeric(9)  not null ,
	fk_area_forestale     numeric(8)  not null ,
	num_settore           character varying(3)  null ,
	nome_settore          character varying(100)  null ,
	pendenza_media        numeric(4,1)  null ,
	sup_totale            numeric(9)  null ,
	pendenza_min          numeric(4,1)  null ,
	pendenza_max          numeric(4,1)  null ,
	quota_min             numeric(4)  null ,
	quota_max             numeric(4)  null ,
	quota_med             numeric(5,1)  null 
);



alter table idf_t_settore
	add constraint  pk_idf_t_settore primary key (id_settore);



create table idf_t_soggetto
(
	id_soggetto           numeric(5)  not null ,
	fk_comune             integer  null ,
	nome                  character varying(50)  null ,
	cognome						    character varying(100)  null ,
	codice_fiscale        character varying(16)  null ,
	partita_iva           character varying(11)  null ,
	denominazione         character varying(200)  null ,
	indirizzo             character varying(50)  null ,	
	civico                character varying(20) NULL,
	cap                   character varying(5) NULL,

	nr_telefonico         character varying(20)  null ,
	e_mail                character varying(50)  null ,
	pec                   character varying(50)  null ,
	n_iscrizione_ordine   character varying(20)  null ,
	istat_prov_iscrizione_ordine  character varying(3)  null ,
	istat_prov_competenza_terr  character varying(3)  null ,
	flg_settore_regionale  numeric(1)  not null default 0 constraint sett_reg_0_1 check (flg_settore_regionale in (0,1)),
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  null 
);



alter table idf_t_soggetto
	add constraint  pk_idf_t_soggetto primary key (id_soggetto);



create table idf_t_specie
(
	id_specie             numeric(5)  not null ,
	codice                character varying(4)  null ,
	cod_gruppo            character varying(16)  not null ,
	codice_pignatti       character varying(50)  null ,
	latino                character varying(200)  null ,
	volgare               character varying(200)  null ,
	link_foto             character varying(400)  null ,
	flg_386               numeric(1)  not null  default 0 constraint  f386 check (flg_386 in (0,1)),
	link_scheda           character varying(200)  null ,
	mtd_ordinamento       numeric(2)  null ,
	flg_visibile          numeric(1)  not null  default 0 constraint  vis77_01 check (flg_visibile in (0,1)),
	densita 							numeric(7,4),
	fk_config_ipla        integer NOT NULL  default 0,
	flg_coniflatif        character varying(1) NOT NULL  default 'C' constraint  cnfl_cl check (flg_coniflatif in ('C','L'))
);



alter table idf_t_specie
	add constraint  pk_idf_t_specie primary key (id_specie);



create table idf_t_sub_comprensorio
(
	id_sub_cmp            numeric(3)  not null ,
	fk_comprensorio       numeric(9)  not null ,
	subcp                 character varying(4)  null 
);



alter table idf_t_sub_comprensorio
	add constraint  pk_idf_t_sub_comprensorio primary key (id_sub_cmp);



create table idf_t_sub_settore
(
	id_sub_settore        numeric(3)  not null ,
	fk_settore            numeric(9)  not null ,
	nome                  character varying(250)  null ,
	num_sub_settore       character varying(4)  null ,
	sup_sub_settore       numeric(8,4)  null 
);



alter table idf_t_sub_settore
	add constraint  pk_idf_t_sub_settore primary key (id_sub_settore);



create table idf_t_tipo_forestale
(
	id_tipo_forestale     numeric(8)  not null ,
	fk_categoria_forestale  numeric(2)  not null ,
	codice_amministrativo  character varying(5)  not null ,
	tipo                  character varying(150)  null ,
	sottotipo             character varying(100)  null ,
	variante              character varying(150)  null ,
	data_modifica         timestamp  null ,
	data_fine_validita    timestamp  null ,
	cod_clc               character varying(3)  null ,
	habitat_n2000         character varying(200)  null ,
	fk_config_ipla        integer  not null 
);



alter table idf_t_tipo_forestale
	add constraint  pk_idf_t_tipo_forestale primary key (id_tipo_forestale);



create table idf_temp_istanza_compensazione
(
	num_istanza_compensazione  numeric(9)  not null ,
	fk_intervento         numeric(9,0)  not null ,
	data_inserimento      date  null ,
	data_aggiornamento    date  null ,
	fk_config_utente      integer  null,
	data_presentazione    date  null ,
	data_autorizzazione   date  null 
);



alter table idf_temp_istanza_compensazione
	add constraint  pk_idf_temp_istanza_compensazi primary key (num_istanza_compensazione);



--25/06/2020 – Mail di Gabriella/Fernanda - Eseguite su Niv DEV e Niv TST (script + erwin aggiornati)

ALTER TABLE idf_geo_pl_lotto_intervento ADD COLUMN sup_tagliata_ha numeric(9,4);
ALTER TABLE idf_r_partfor_intervento ADD COLUMN ripresa_intervento numeric(9,4);
ALTER TABLE idf_r_partfor_intervento ADD COLUMN perc_intervento numeric(3,2);





---------------
-- FOREIGN KEY|
---------------

alter table idf_cnf_config_utente
	add  constraint  fk_idf_cnf_profilo_utente_01 foreign key (fk_profilo_utente) references idf_cnf_profilo_utente(id_profilo_utente);



alter table idf_cnf_config_utente
	add  constraint  fk_idf_cnf_tipo_accreditamento foreign key (fk_tipo_accreditamento) references idf_cnf_tipo_accreditamento(id_tipo_accreditamento);



alter table idf_cnf_config_utente
	add  constraint  fk_idf_t_soggetto_01 foreign key (fk_soggetto) references idf_t_soggetto(id_soggetto);



ALTER TABLE idf_cnf_delega
	ADD constraint fk_idf_cnf_config_utente_20 foreign key (id_config_utente) REFERENCES idf_cnf_config_utente(id_config_utente);



ALTER TABLE  idf_cnf_param_acta 
  ADD CONSTRAINT fk_ambito_istanza_01 FOREIGN KEY (fk_ambito_istanza) REFERENCES  idf_d_ambito_istanza (id_ambito_istanza);



alter table idf_cnf_parametro_appl
	add  constraint  fk_idf_cnf_tipo_param_appl_01 foreign key (fk_tipo_param_appl) references idf_cnf_tipo_param_appl(id_tipo_param_appl);



alter table idf_cnf_profilo_utente
	add  constraint  fk_idf_cnf_procedura_01 foreign key (fk_procedura) references idf_cnf_procedura(id_procedura);



alter table idf_cnf_sk_ok_ads
	add  constraint  fk_idf_geo_pt_ads_04 foreign key (idgeo_pt_ads) references idf_geo_pt_area_di_saggio(idgeo_pt_ads);



alter table idf_cnf_sk_ok_selv
	add  constraint  fk_idf_t_interv_selvicolt_01 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_cnf_sk_ok_trasf
	add  constraint  fk_idf_t_interv_trasf_01 foreign key (id_intervento) references idf_t_interv_trasformazione(id_intervento);


ALTER TABLE idf_d_ambito_rilievo
	ADD CONSTRAINT fk_idf_d_ambito_rilievo_03 FOREIGN KEY(fk_padre) REFERENCES idf_d_ambito_rilievo(id_ambito);
	
	
	
alter table idf_d_categoria_forestale
	add  constraint  fk_idf_d_macro_catfor_01 foreign key (fk_param_macro_catfor) references idf_d_macro_catfor(id_param_macro_catfor);



alter table idf_d_compresa
	add  constraint  fk_idf_d_compresa_01 foreign key (fk_padre) references idf_d_compresa(id_compresa)  ;



alter table idf_d_config_ipla
	add  constraint  fk_idf_d_ambito_rilievo_01 foreign key (fk_ambito) references idf_d_ambito_rilievo(id_ambito);



alter table idf_d_destinazione
	add  constraint  fk_idf_d_config_ipla_01 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_d_macro_catfor
	add  constraint  fk_idf_d_parametro_trasf_01 foreign key (id_param_macro_catfor) references idf_d_parametro_trasf(id_parametro_trasf);



alter table idf_d_origine_viabilita
	add  constraint  fk_idf_d_config_ipla_02 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_d_parametro_trasf
	add  constraint  fk_idf_d_tipo_param_trasf_01 foreign key (fk_tipo_paramero_trasf) references idf_d_tipo_parametro_trasf(id_tipo_paramero_trasf);



alter table idf_d_priorita
	add  constraint  fk_idf_d_config_ipla_03 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_d_proprieta
	add  constraint  fk_idf_d_config_ipla_04 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_d_tipo_allegato
	add  constraint  fk_idf_d_origine_allegato_01 foreign key (fk_origine_allegato) references idf_d_origine_allegato(id_origine_allegato);



alter table idf_d_tipo_intervento
	add  constraint  fk_idf_d_macrointervento_01 foreign key (fk_macrointervento) references idf_d_macrointervento(id_macrointervento);



alter table idf_d_tipo_intervento
	add  constraint  fk_idf_d_config_ipla_05 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla)  ;



alter table idf_d_tipo_istanza
	add  constraint  fk_idf_d_ambito_istanza_01 foreign key (fk_ambito_istanza) references idf_d_ambito_istanza(id_ambito_istanza);



alter table idf_d_tipo_strutturale
	add  constraint  fk_idf_d_config_ipla_06 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_d_tipo_viabilita
	add  constraint  fk_idf_d_config_ipla_07 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



ALTER TABLE idf_geo_pl_area_forestale
	ADD CONSTRAINT  fk_idf_t_area_forestale_05 FOREIGN KEY (ID_AREA_FORESTALE) REFERENCES idf_t_area_forestale(ID_AREA_FORESTALE);



alter table idf_geo_pl_area_vocazione_tartufi
	add  constraint  fk_idf_d_attitudine_01 foreign key (fk_attitudine) references idf_d_attitudine(id_attitudine);



alter table idf_geo_ln_filare
	add  constraint  fk_idf_d_assetto_01 foreign key (fk_assetto) references idf_d_assetto(id_assetto);



alter table idf_geo_ln_filare
	add  constraint  fk_idf_d_tipo_strutturale_03 foreign key (fk_tipo_strutturale) references idf_d_tipo_strutturale(id_tipo_strutturale);



alter table idf_geo_ln_filare
	add  constraint  fk_idf_d_compos_specie_01 foreign key (fk_tipo_composizione) references idf_d_composizione_specie(id_composizione_specie);



alter table idf_geo_ln_filare
	add  constraint  fk_idf_d_tipo_formazione_01 foreign key (fk_tipo_formazione) references idf_d_tipo_formazione(id_tipo_formazione);



alter table idf_geo_ln_evento
	add  constraint  fk_idf_t_evento_01 foreign key (fk_evento) references idf_t_evento(id_evento);



alter table idf_geo_ln_lotto_intervento
	add  constraint  fk_idf_t_interv_selvicolt_02 foreign key (fk_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_geo_ln_pfa_viabilita
	add  constraint  fk_idf_d_tipo_viabilita_01 foreign key (fk_tipo_viabilita) references idf_d_tipo_viabilita(id_tipo_viabilita);



alter table idf_geo_ln_pfa_viabilita
	add  constraint  fk_idf_d_origine_viabilita_01 foreign key (fk_origine_viabilita) references idf_d_origine_viabilita(id_origine_viabilita);



alter table idf_geo_pl_compartimentazione
	add  constraint  fk_idf_d_proprieta_01 foreign key (fk_proprieta) references idf_d_proprieta(id_proprieta);



alter table idf_geo_pl_compartimentazione
	add  constraint  fk_idf_t_area_forestale_01 foreign key (fk_area_forestale) references idf_t_area_forestale(id_area_forestale);



alter table idf_geo_pl_comprensorio
	add  constraint  fk_idf_t_comprensorio_01 foreign key (id_comprensorio) references idf_t_comprensorio(id_comprensorio);



alter table idf_geo_pl_comune
	add  constraint  fk_idf_t_area_forestale_02 foreign key (fk_area_forestale) references idf_t_area_forestale(id_area_forestale);



alter table idf_geo_pl_comune
	add  constraint  fk_idf_geo_pl_provincia_01 foreign key (istat_prov) references idf_geo_pl_provincia(istat_prov);



alter table idf_geo_pl_evento
	add  constraint  fk_idf_t_evento_02 foreign key (fk_evento) references idf_t_evento(id_evento);



alter table idf_geo_pl_lotto_intervento
	add  constraint  fk_idf_t_interv_selvicolt_03 foreign key (fk_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_geo_pl_particella_forest
	add  constraint  fk_idf_geo_pl_pfa_01 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);

alter table idf_geo_pl_particella_forest
	add  constraint  fk_idf_d_compresa_02 foreign key (fk_compresa) references idf_d_compresa(id_compresa);

alter table idf_geo_pl_particella_forest
	add  constraint  fk_idf_geo_pl_part_for_04 foreign key (fk_padre) references idf_geo_pl_particella_forest(idgeo_pl_particella_forest)  ;



ALTER TABLE idf_geo_pl_pfa
	ADD CONSTRAINT fk_idf_d_proprieta_05  FOREIGN KEY (fk_PROPRIETA_primpa) REFERENCES idf_d_proprieta(ID_PROPRIETA);	

alter table idf_geo_pl_pfa
	add  constraint  fk_idf_d_versione_01 foreign key (fk_versione) references idf_d_versione(id_versione);



alter table idf_geo_pl_prop_catasto
	add  constraint  fk_idf_geo_pl_pfa_02 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa)  ;



alter table idf_geo_pl_prop_catasto
	add  constraint  fk_idf_geo_pl_comune_01 foreign key (fk_comune) references idf_geo_pl_comune(id_comune)  ;



alter table idf_geo_pl_prop_catasto
	add  constraint  fk_idf_d_proprieta_02 foreign key (fk_proprieta) references idf_d_proprieta(id_proprieta);



alter table idf_geo_pl_prop_catasto
	add  constraint  fk_idf_cnf_config_utente_02 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



alter table idf_geo_pl_settore
	add  constraint  fk_idf_t_settore_01 foreign key (id_settore) references idf_t_settore(id_settore);



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_geo_pl_pfa_03 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_d_destinazione_01 foreign key (fk_destinazione) references idf_d_destinazione(id_destinazione);



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_d_tipo_strutturale_04 foreign key (fk_tipo_strutturale) references idf_d_tipo_strutturale(id_tipo_strutturale)  ;



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_d_compresa_03 foreign key (fk_compresa) references idf_d_compresa(id_compresa);



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_d_tipo_intervento_01 foreign key (fk_tipo_intervento) references idf_d_tipo_intervento(id_tipo_intervento)  ;



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_d_priorita_01 foreign key (fk_priorita) references idf_d_priorita(id_priorita);



alter table idf_geo_pl_sup_forestale
	add  constraint  fk_idf_t_tipo_forestale_01 foreign key (fk_tipo_forestale) references idf_t_tipo_forestale(id_tipo_forestale)  ;



alter table idf_geo_pl_zona_altimetrica
	add  constraint  fk_idf_d_parametro_trasf_02 foreign key (fk_paramtrasf_zona_altimetrica) references idf_d_parametro_trasf(id_parametro_trasf)  ;



alter table idf_geo_pl_zona_altimetrica
	add  constraint  fk_idf_geo_pl_comune_02 foreign key (fk_comune) references idf_geo_pl_comune(id_comune)  ;



alter table idf_geo_pl_zona_servita
	add  constraint  fk_idf_geo_pl_pfa_04 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_priorita_02 foreign key (fk_priorita) references idf_d_priorita(id_priorita);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_assetto_02 foreign key (fk_assetto) references idf_d_assetto(id_assetto);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_comunita_montana_01 foreign key (fk_comunita_montana) references idf_d_comunita_montana(id_comunita);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_danno_01 foreign key (fk_danno) references idf_d_danno(id_danno);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_geo_pl_comune_03 foreign key (fk_comune) references idf_geo_pl_comune(id_comune)  ;

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_r_pf_pg_01 foreign key (fk_soggetto,fk_soggetto_pg) references idf_r_pf_pg(id_soggetto_pf,id_soggetto_pg)  ;

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_geo_pl_part_for_01 foreign key (idgeo_pl_particella_forest) references idf_geo_pl_particella_forest(idgeo_pl_particella_forest)  ;

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_t_settore_02 foreign key (fk_settore) references idf_t_settore(id_settore);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_ambito_rilievo_02 foreign key (fk_ambito) references idf_d_ambito_rilievo(id_ambito);

ALTER TABLE idf_geo_pt_area_di_saggio
	ADD CONSTRAINT fk_idf_d_stato_ads_01 FOREIGN KEY (fk_stato_ads) REFERENCES idf_d_stato_ads(id_stato_ads);  

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_tipo_ads_01 foreign key (fk_tipo_ads) references idf_d_tipo_ads(id_tipo_ads);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_proprieta_03 foreign key (fk_proprieta) references idf_d_proprieta(id_proprieta);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_esposizione_01 foreign key (fk_esposizione) references idf_d_esposizione(id_esposizione);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_t_tipo_forestale_06 foreign key (fk_tipo_forestale) references idf_t_tipo_forestale(id_tipo_forestale);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_destinazione_02 foreign key (fk_destinazione) references idf_d_destinazione(id_destinazione);

alter table idf_geo_pt_area_di_saggio
	add  constraint  fk_idf_d_tipo_intervento_02 foreign key (fk_tipo_intervento) references idf_d_tipo_intervento(id_tipo_intervento);



alter table idf_geo_pt_evento
	add  constraint  fk_idf_t_evento_03 foreign key (fk_evento) references idf_t_evento(id_evento);



alter table idf_geo_pt_lotto_intervento
	add  constraint  fk_idf_t_interv_selvicolt_04 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



ALTER TABLE idf_geo_pl_interv_trasf
	ADD CONSTRAINT fk_idf_t_interv_trasf_04 FOREIGN KEY (fk_intervento) REFERENCES idf_t_interv_trasformazione(id_intervento);	



alter table idf_geo_pl_tipo_arboricoltura
	add  constraint  fk_idf_t_tipo_forestale_02 foreign key (fk_tipo_forestale) references idf_t_tipo_forestale(id_tipo_forestale);



alter table idf_geo_pl_tipo_forestale
	add  constraint  fk_idf_t_tipo_forestale_03 foreign key (fk_tipo_forestale) references idf_t_tipo_forestale(id_tipo_forestale);



alter table idf_geo_pl_tipo_forestale_10
	add  constraint  fk_idf_t_tipo_forestale_04 foreign key (fk_tipo_forestale) references idf_t_tipo_forestale(id_tipo_forestale);



alter table idf_r_ads_combustibile
	add  constraint  fk_idf_t_ads_superficie_nota_0 foreign key (idgeo_pt_ads) references idf_t_ads_superficie_nota(idgeo_pt_ads);



alter table idf_r_ads_combustibile
	add  constraint  fk_idf_d_combustibile_01 foreign key (cod_combustibile) references idf_d_combustibile(cod_combustibile);



alter table idf_r_adsrel_specie
	add  constraint  fk_idf_t_specie_01 foreign key (id_specie) references idf_t_specie(id_specie);



alter table idf_r_adsrel_specie
	add  constraint  fk_idf_d_tipo_campione_01 foreign key (cod_tipo_campione) references idf_d_tipo_campione(cod_tipo_campione);



alter table idf_r_adsrel_specie
	add  constraint  fk_idf_geo_pt_ads_01 foreign key (idgeo_pt_ads) references idf_geo_pt_area_di_saggio(idgeo_pt_ads);



alter table idf_r_comune_pfa
	add  constraint  fk_idf_geo_pl_comune_04 foreign key (id_comune) references idf_geo_pl_comune(id_comune);



alter table idf_r_comune_pfa
	add  constraint  fk_idf_geo_pl_pfa_05 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_r_evento_partfor
	add  constraint  fk_idf_t_evento_04 foreign key (id_evento) references idf_t_evento(id_evento);



alter table idf_r_evento_partfor
	add  constraint  fk_idf_geo_pl_part_for_02 foreign key (idgeo_pl_particella_forest) references idf_geo_pl_particella_forest(idgeo_pl_particella_forest);



alter table idf_r_intervento_aapp
	add  constraint  fk_idf_t_intervento_01 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_r_intervento_catfor
	add  constraint  fk_idf_t_intervento_02 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_r_intervento_catfor
	add  constraint  fk_idf_d_categoria_forest_01 foreign key (id_categoria_forestale) references idf_d_categoria_forestale(id_categoria_forestale);



alter table idf_r_intervento_catfor
	add  constraint  fk_idf_cnf_config_utente_05 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_r_intervento_pop_seme
	add  constraint  fk_idf_t_intervento_03 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_r_intervento_pop_seme
	add  constraint  fk_idf_geo_pop_seme_01 foreign key (id_popolamento_seme) references idf_geo_pl_popolamento_seme(id_popolamento_seme);



alter table idf_r_intervento_rn_2000
	add  constraint  fk_idf_t_intervento_04 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_r_interventoselv_usoviab
	add  constraint  fk_idf_t_interv_selvicolt_05 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_r_interventoselv_usoviab
	add  constraint  fk_idf_cnf_config_utente_03 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



alter table idf_r_interventoselv_usoviab
	add  constraint  fk_idf_d_uso_viabilita_01 foreign key (fk_uso_viabilita) references idf_d_uso_viabilita(id_uso_viabilita);



alter table idf_r_intervselv_esbosco
	add  constraint  fk_idf_t_interv_selvicolt_06 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_r_intervselv_esbosco
	add  constraint  fk_idf_d_esbosco_01 foreign key (cod_esbosco) references idf_d_esbosco(cod_esbosco);



alter table idf_r_intervselv_esbosco
	add  constraint  fk_idf_cnf_config_utente_06 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



alter table idf_r_intervselv_evento
	add  constraint  fk_idf_t_evento_05 foreign key (id_evento) references idf_t_evento(id_evento);



alter table idf_r_intervselv_evento
	add  constraint  fk_idf_t_interv_selvicolt_07 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_r_intervselv_evento
	add  constraint  fk_idf_cnf_config_utente_07 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



ALTER TABLE idf_r_paramtrasf_trasformazion
	ADD CONSTRAINT fk_idf_d_parametro_trasf_03 FOREIGN KEY (id_parametro_trasf) REFERENCES idf_d_parametro_trasf(id_parametro_trasf);


alter table idf_r_paramtrasf_trasformazion
	add  constraint  fk_idf_t_interv_trasf_02 foreign key (id_intervento) references idf_t_interv_trasformazione(id_intervento);



alter table idf_r_paramtrasf_trasformazion
	add  constraint  fk_idf_cnf_config_utente_08 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_r_partfor_intervento
	add  constraint  fk_idf_t_interv_selvicolt_08 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_r_partfor_intervento
	add  constraint  fk_idf_geo_pl_part_for_03 foreign key (idgeo_pl_particella_forest) references idf_geo_pl_particella_forest(idgeo_pl_particella_forest);



alter table idf_r_pf_pg
	add  constraint  fk_idf_t_soggetto_03 foreign key (id_soggetto_pf) references idf_t_soggetto(id_soggetto);



alter table idf_r_pf_pg
	add  constraint  fk_idf_t_soggetto_02 foreign key (id_soggetto_pg) references idf_t_soggetto(id_soggetto);



alter table idf_r_pf_pg
	add  constraint  fk_idf_cnf_config_utente_09 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_r_pfa_area_protetta
	add  constraint  fk_idf_geo_pl_pfa_06 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_r_pfa_pg
	add  constraint  fk_idf_geo_pl_pfa_07 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_r_pfa_pg
	add  constraint  fk_idf_t_soggetto_04 foreign key (id_soggetto_coinvolto) references idf_t_soggetto(id_soggetto);



alter table idf_r_pfa_pg
	add  constraint  fk_idf_d_tipo_soggetto_01 foreign key (id_tipo_soggetto) references idf_d_tipo_soggetto(id_tipo_soggetto);



alter table idf_r_pfa_pg
	add  constraint  fk_idf_cnf_config_utente_10 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_r_pfa_popseme
	add  constraint  fk_idf_geo_pl_pfa_08 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_r_pfa_popseme
	add  constraint  fk_idf_geo_pop_seme_02 foreign key (id_popolamento_seme) references idf_geo_pl_popolamento_seme(id_popolamento_seme);



alter table idf_r_pfa_rn_2000
	add  constraint  fk_idf_geo_pl_pfa_09 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_r_pfa_viabilita
	add  constraint  fk_idf_geo_pl_pfa_10 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);



alter table idf_r_pfa_viabilita
	add  constraint  fk_idf_geo_ln_pfa_viab_01 foreign key (idgeo_ln_pfa_viabilita) references idf_geo_ln_pfa_viabilita(idgeo_ln_pfa_viabilita);



alter table idf_r_pfainterv_geosupfor
	add  constraint  fk_idf_t_interv_selvicolt_09 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_r_pfainterv_geosupfor
	add  constraint  fk_idf_geo_pl_sup_for_01 foreign key (idgeo_superficie_forestale) references idf_geo_pl_sup_forestale(idgeo_superficie_forestale);



alter table idf_r_propcatasto_intervento
	add  constraint  fk_idf_geo_pl_prop_catasto_01 foreign key (idgeo_pl_prop_catasto) references idf_geo_pl_prop_catasto(idgeo_pl_prop_catasto);



alter table idf_r_propcatasto_intervento
	add  constraint  fk_idf_t_intervento_05 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_r_propcatasto_intervento
	add  constraint  fk_idf_cnf_config_utente_11 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_r_soggetto_intervento
	add  constraint  fk_idf_t_intervento_06 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_r_soggetto_intervento
	add  constraint  fk_idf_t_soggetto_05 foreign key (id_soggetto) references idf_t_soggetto(id_soggetto);



alter table idf_r_soggetto_intervento
	add  constraint  fk_idf_d_tipo_soggetto_02 foreign key (id_tipo_soggetto) references idf_d_tipo_soggetto(id_tipo_soggetto);



alter table idf_r_soggetto_intervento
	add  constraint  fk_idf_cnf_config_utente_12 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_r_specie_pfa_intervento
	add  constraint  fk_idf_t_specie_02 foreign key (id_specie) references idf_t_specie(id_specie);



alter table idf_r_specie_pfa_intervento
	add  constraint  fk_idf_t_interv_selvicolt_10 foreign key (id_intervento) references idf_t_interv_selvicolturale(id_intervento);



alter table idf_r_specie_pfa_intervento
	add  constraint  fk_idf_cnf_config_utente_13 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



alter table idf_r_specie_pfa_intervento
	add  constraint  fk_idf_d_udm_01 foreign key (fk_udm) references idf_d_udm(id_udm);



alter table idf_r_sup_tipo_forestale
	add  constraint  fk_idf_geo_pl_tipo_forestale_02 foreign key (id_geo_tipo_forestale) references idf_geo_pl_tipo_forestale(id_geo_tipo_forestale);



alter table idf_r_sup_tipo_forestale
	add  constraint  fk_idf_geo_pl_sup_for_02 foreign key (idgeo_superficie_forestale) references idf_geo_pl_sup_forestale(idgeo_superficie_forestale);



alter table idf_r_tipoint_cnfipla
	add  constraint  fk_idf_d_config_ipla_08 foreign key (id_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_r_tipoint_cnfipla
	add  constraint  fk_idf_d_tipo_intervento_03 foreign key (id_tipo_intervento) references idf_d_tipo_intervento(id_tipo_intervento);



alter table idf_s_geo_ln_viabilita
	add  constraint  fk_idf_s_viabilita_01 foreign key (fk_viabilita,data_storicizzazione) references idf_s_viabilita(id_viabilita,data_storicizzazione);



ALTER TABLE idf_t_ads_relascopica
	ADD CONSTRAINT fk_idf_d_classe_fertilita_01 FOREIGN KEY (fk_classe_fertilita) REFERENCES idf_d_classe_fertilita(id_classe_fertilita);

alter table idf_t_ads_relascopica
	add  constraint  fk_idf_geo_pt_ads_02 foreign key (idgeo_pt_ads) references idf_geo_pt_area_di_saggio(idgeo_pt_ads);

alter table idf_t_ads_relascopica
	add  constraint  fk_idf_d_tipo_strutturale_06 foreign key (fk_tipo_strutturale_princ) references idf_d_tipo_strutturale(id_tipo_strutturale);

alter table idf_t_ads_relascopica
	add  constraint  fk_idf_d_tipo_strutturale_05 foreign key (fk_tipo_strutturale_second) references idf_d_tipo_strutturale(id_tipo_strutturale)  ;



alter table idf_t_ads_superficie_nota
	add  constraint  fk_idf_geo_pt_ads_03 foreign key (idgeo_pt_ads) references idf_geo_pt_area_di_saggio(idgeo_pt_ads);

alter table idf_t_ads_superficie_nota
	add  constraint  fk_idf_d_stadio_sviluppo_01 foreign key (cod_stadio_sviluppo) references idf_d_stadio_sviluppo(cod_stadio_sviluppo);

alter table idf_t_ads_superficie_nota
	add  constraint  fk_idf_d_esbosco_02 foreign key (cod_esbosco) references idf_d_esbosco(cod_esbosco);

alter table idf_t_ads_superficie_nota
	add  constraint  fk_idf_d_tipo_strutturale_01 foreign key (fk_tipo_strutturale_princ) references idf_d_tipo_strutturale(id_tipo_strutturale);

alter table idf_t_ads_superficie_nota
	add  constraint  fk_idf_d_tipo_strutturale_02 foreign key (fk_tipo_strutturale_second) references idf_d_tipo_strutturale(id_tipo_strutturale);

ALTER TABLE idf_t_ads_superficie_nota
	ADD CONSTRAINT fk_idf_d_sottotip_sup_nota_01 FOREIGN KEY (fk_sottotipo_sup_nota) REFERENCES idf_d_sottotipo_sup_nota(id_sottotipo_sup_nota);



alter table idf_t_comprensorio
	add  constraint  fk_idf_d_proprieta_04 foreign key (fk_proprieta) references idf_d_proprieta(id_proprieta);

alter table idf_t_comprensorio
	add  constraint  fk_idf_t_area_forestale_03 foreign key (fk_area_forestale) references idf_t_area_forestale(id_area_forestale);



alter table idf_t_documento_allegato
	add  constraint  fk_idf_d_tipo_allegato_01 foreign key (fk_tipo_allegato) references idf_d_tipo_allegato(id_tipo_allegato);

alter table idf_t_documento_allegato
	add  constraint  fk_idf_geo_pl_pfa_11 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa);

alter table idf_t_documento_allegato
	add  constraint  fk_idf_t_intervento_07 foreign key (fk_intervento) references idf_t_intervento(id_intervento)  ;

alter table idf_t_documento_allegato
	add  constraint  fk_idf_cnf_config_utente_14 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);


--zz
alter table idf_t_evento
	add  constraint  fk_idf_d_tipo_evento_01 foreign key (fk_tipo_evento) references idf_d_tipo_evento(id_tipo_evento);



alter table idf_t_idf_all_tav
	add  constraint  fk_idf_t_specie_03 foreign key (id_specie) references idf_t_specie(id_specie);



ALTER TABLE idf_t_interv_selvicolturale
	ADD CONSTRAINT fk_d_governo_01 FOREIGN KEY (fk_governo) REFERENCES idf_d_governo(id_governo) ;	

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_r_tipoint_cnfipla_01 foreign key (fk_config_ipla,fk_tipo_intervento) references idf_r_tipoint_cnfipla(id_config_ipla,id_tipo_intervento);

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_t_intervento_08 foreign key (id_intervento) references idf_t_intervento(id_intervento);

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_geo_pl_pfa_12 foreign key (idgeo_pl_pfa) references idf_geo_pl_pfa(idgeo_pl_pfa)  ;

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_d_finalita_taglio_01 foreign key (fk_finalita_taglio) references idf_d_finalita_taglio(id_finalita_taglio);

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_d_dest_legname_01 foreign key (fk_dest_legname) references idf_d_dest_legname(id_dest_legname);

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_d_fascia_altimet_01 foreign key (fk_fascia_altimetrica) references idf_d_fascia_altimetrica(id_fascia_altimetrica);

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_cnf_config_utente_15 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_t_tipo_forestale_05 foreign key (fk_tipo_forestale_prevalente) references idf_t_tipo_forestale(id_tipo_forestale)  ;

alter table idf_t_interv_selvicolturale
	add  constraint  fk_idf_d_stato_intervento_01 foreign key (fk_stato_intervento) references idf_d_stato_intervento(id_stato_intervento);



alter table idf_t_interv_trasformazione
	add  constraint  fk_idf_t_intervento_09 foreign key (id_intervento) references idf_t_intervento(id_intervento);



alter table idf_t_interv_trasformazione
	add  constraint  fk_idf_cnf_config_utente_16 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



alter table idf_t_intervento
	add  constraint  fk_idf_cnf_config_utente_17 foreign key (fk_config_utente_compilatore) references idf_cnf_config_utente(id_config_utente);



alter table idf_t_istanza_forest
	add  constraint  fk_idf_t_intervento_10 foreign key (id_istanza_intervento) references idf_t_intervento(id_intervento);



alter table idf_t_istanza_forest
	add  constraint  fk_idf_d_stato_istanza_01 foreign key (fk_stato_istanza) references idf_d_stato_istanza(id_stato_istanza);



alter table idf_t_istanza_forest
	add  constraint  fk_idf_t_soggetto_06 foreign key (fk_sogg_settore_regionale) references idf_t_soggetto(id_soggetto)  ;



alter table idf_t_istanza_forest
	add  constraint  fk_idf_cnf_config_utente_18 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente);



alter table idf_t_istanza_forest
	add  constraint  fk_idf_d_tipo_istanza_01 foreign key (fk_tipo_istanza) references idf_d_tipo_istanza(id_tipo_istanza);



alter table idf_t_settore
	add  constraint  fk_idf_t_area_forestale_04 foreign key (fk_area_forestale) references idf_t_area_forestale(id_area_forestale);



alter table idf_t_soggetto
	add  constraint  fk_idf_geo_pl_comune_05 foreign key (fk_comune) references idf_geo_pl_comune(id_comune)  ;

alter table idf_t_soggetto
	add  constraint  fk_idf_geo_pl_provincia_03 foreign key (istat_prov_iscrizione_ordine) references idf_geo_pl_provincia(istat_prov)  ;

alter table idf_t_soggetto
	add  constraint  fk_idf_geo_pl_provincia_02 foreign key (istat_prov_competenza_terr) references idf_geo_pl_provincia(istat_prov)  ;

alter table idf_t_soggetto
	add  constraint  fk_idf_cnf_config_utente_19 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;



alter table idf_t_specie
	add  constraint  fk_idf_d_gruppo_01 foreign key (cod_gruppo) references idf_d_gruppo(cod_gruppo);

alter table idf_t_specie
	add  constraint fk_idf_d_config_ipla_10 FOREIGN KEY (fk_config_ipla) REFERENCES idf_d_config_ipla(id_config_ipla);



alter table idf_t_sub_comprensorio
	add  constraint  fk_idf_t_comprensorio_02 foreign key (fk_comprensorio) references idf_t_comprensorio(id_comprensorio);



alter table idf_t_sub_settore
	add  constraint  fk_idf_t_settore_03 foreign key (fk_settore) references idf_t_settore(id_settore);



alter table idf_t_tipo_forestale
	add  constraint  fk_idf_d_categoria_forest_02 foreign key (fk_categoria_forestale) references idf_d_categoria_forestale(id_categoria_forestale);

alter table idf_t_tipo_forestale
	add  constraint  fk_idf_d_config_ipla_09 foreign key (fk_config_ipla) references idf_d_config_ipla(id_config_ipla);



alter table idf_temp_istanza_compensazione
	add  constraint  fk_idf_t_interv_trasf_03 foreign key (fk_intervento) references idf_t_interv_trasformazione(id_intervento);

alter table idf_temp_istanza_compensazione
	add  constraint  fk_idf_cnf_config_utente_01 foreign key (fk_config_utente) references idf_cnf_config_utente(id_config_utente)  ;




-------------------
-- INDICI SPAZIALI|
-------------------
  
CREATE INDEX idf_geo_pl_area_vocazione_tartufi_gidx
  ON idf_geo_pl_area_vocazione_tartufi
  USING gist
  (geometria);
  
CREATE INDEX idf_geo_ln_filare_gidx
  ON idf_geo_ln_filare
  USING gist
  (geometria);
  
CREATE INDEX idf_geo_ln_evento_gidx
  ON idf_geo_ln_evento
  USING gist
  (geometria);
  
 
CREATE INDEX idf_geo_ln_lotto_intervento_gidx
  ON idf_geo_ln_lotto_intervento
  USING gist
  (geometria);
  
CREATE INDEX idf_geo_ln_pfa_viabilita_gidx
  ON idf_geo_ln_pfa_viabilita
  USING gist
  (geometria);

  
CREATE INDEX idf_geo_pl_atomic_pfa_gidx
  ON idf_geo_pl_atomic_pfa
  USING gist
  (geometria);
  
CREATE INDEX idf_geo_pl_compartimentazione_gidx
  ON idf_geo_pl_compartimentazione
  USING gist
  (geometria);
  
CREATE INDEX idf_geo_pl_comprensorio_gidx
  ON idf_geo_pl_comprensorio
  USING gist
  (geometria); 
  
CREATE INDEX idf_geo_pl_comune_gidx
  ON idf_geo_pl_comune
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_evento_gidx
  ON idf_geo_pl_evento
  USING gist
  (geometria);  
  
  
CREATE INDEX idf_geo_pl_lotto_intervento_gidx
  ON idf_geo_pl_lotto_intervento
  USING gist
  (geometria);  
  
		CREATE INDEX idf_geo_pl_particella_forest_gidx
  		ON idf_geo_pl_particella_forest
  		USING gist
  		(geometria);  
  
CREATE INDEX idf_geo_pl_pfa_gidx
  ON idf_geo_pl_pfa
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_prop_catasto_gidx
  ON idf_geo_pl_prop_catasto
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_provincia_gidx
  ON idf_geo_pl_provincia
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_settore_gidx
  ON idf_geo_pl_settore
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_sup_forestale_gidx
  ON idf_geo_pl_sup_forestale
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_zona_altimetrica_gidx
  ON idf_geo_pl_zona_altimetrica
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_zona_servita_gidx
  ON idf_geo_pl_zona_servita
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pl_popolamento_seme_gidx
  ON idf_geo_pl_popolamento_seme
  USING gist
  (geometria);   
  
  
CREATE INDEX idf_geo_pt_area_di_saggio_gidx
  ON idf_geo_pt_area_di_saggio
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pt_evento_gidx
  ON idf_geo_pt_evento
  USING gist
  (geometria);  
  
CREATE INDEX idf_geo_pt_lotto_intervento_gidx
  ON idf_geo_pt_lotto_intervento
  USING gist
  (geometria);        
  
CREATE INDEX idf_geo_pl_tipo_arboricoltura_gidx
  ON idf_geo_pl_tipo_arboricoltura
  USING gist
  (geometria);    

CREATE INDEX idf_geo_pl_tipo_forestale_gidx
  ON idf_geo_pl_tipo_forestale
  USING gist
  (geometria);  

CREATE INDEX idf_geo_pl_tipo_forestale_10_gidx
  ON idf_geo_pl_tipo_forestale_10
  USING gist
  (geometria); 
  
CREATE INDEX idf_geo_pl_vinc_idro10k_gidx
  ON idf_geo_pl_vinc_idro10k
  USING gist
  (geometria); 
  
CREATE INDEX idf_geo_pl_interv_trasf_gidx
  ON idf_geo_pl_interv_trasf
  USING gist
  (geometria);    
  
CREATE INDEX idf_geo_pl_area_forestale_gidx
  ON idf_geo_pl_area_forestale
  USING gist
  (geometria);        
  
  
  
  
  
-------------------
-- SEQUENCE       |
-------------------

CREATE SEQUENCE seq_idf_cnf_config_utente
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 1
  CACHE 1;
ALTER TABLE seq_idf_cnf_config_utente
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_cnf_config_utente TO idf;
GRANT ALL ON SEQUENCE seq_idf_cnf_config_utente TO idf_rw;

CREATE SEQUENCE seq_idf_geo_ln_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 4
  CACHE 1;
ALTER TABLE seq_idf_geo_ln_evento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_evento TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_evento TO idf_rw;

CREATE SEQUENCE seq_idf_geo_ln_filare
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_ln_filare
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_filare TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_filare TO idf_rw;

CREATE SEQUENCE seq_idf_geo_ln_lotto_intervento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 3
  CACHE 1;
ALTER TABLE seq_idf_geo_ln_lotto_intervento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_lotto_intervento TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_lotto_intervento TO idf_rw;

CREATE SEQUENCE seq_idf_geo_ln_pfa_viabilita
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_ln_pfa_viabilita
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_pfa_viabilita TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_ln_pfa_viabilita TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_area_forestale
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_area_forestale
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_area_forestale TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_area_forestale TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_area_vocazione_tartufi
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_area_vocazione_tartufi
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_area_vocazione_tartufi TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_area_vocazione_tartufi TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_atomic_pfa
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_atomic_pfa
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_atomic_pfa TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_atomic_pfa TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_compartimentazione
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_compartimentazione
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_compartimentazione TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_compartimentazione TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_comprensorio
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_comprensorio
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_comprensorio TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_comprensorio TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 3
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_evento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_evento TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_evento TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_interv_trasf
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 70
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_interv_trasf
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_interv_trasf TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_interv_trasf TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_lotto_intervento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 7
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_lotto_intervento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_lotto_intervento TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_lotto_intervento TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_particella_forest
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_particella_forest
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_particella_forest TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_particella_forest TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_pfa
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_pfa
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_pfa TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_pfa TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_popolamento_seme
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_popolamento_seme
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_popolamento_seme TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_popolamento_seme TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_prop_catasto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 6880
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_prop_catasto
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_prop_catasto TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_prop_catasto TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_settore
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_settore
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_settore TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_settore TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_sup_forestale
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_sup_forestale
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_sup_forestale TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_sup_forestale TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_tipo_arboricoltura
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_tipo_arboricoltura
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_tipo_arboricoltura TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_tipo_arboricoltura TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_tipo_forestale
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_tipo_forestale
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_tipo_forestale TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_tipo_forestale TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_tipo_forestale_10
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_tipo_forestale_10
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_tipo_forestale_10 TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_tipo_forestale_10 TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_vinc_idro10k
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_vinc_idro10k
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_vinc_idro10k TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_vinc_idro10k TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_zona_altimetrica
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_zona_altimetrica
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_zona_altimetrica TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_zona_altimetrica TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pl_zona_servita
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pl_zona_servita
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_zona_servita TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pl_zona_servita TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pt_area_di_saggio
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 25
  CACHE 1;
ALTER TABLE seq_idf_geo_pt_area_di_saggio
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pt_area_di_saggio TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pt_area_di_saggio TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pt_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pt_evento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pt_evento TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pt_evento TO idf_rw;

CREATE SEQUENCE seq_idf_geo_pt_lotto_intervento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 1
  CACHE 1;
ALTER TABLE seq_idf_geo_pt_lotto_intervento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pt_lotto_intervento TO idf;
GRANT ALL ON SEQUENCE seq_idf_geo_pt_lotto_intervento TO idf_rw;

CREATE SEQUENCE seq_idf_t_area_forestale
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_area_forestale
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_area_forestale TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_area_forestale TO idf_rw;

CREATE SEQUENCE seq_idf_t_comprensorio
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_comprensorio
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_comprensorio TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_comprensorio TO idf_rw;

CREATE SEQUENCE seq_idf_t_config
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_config
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_config TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_config TO idf_rw;

CREATE SEQUENCE seq_idf_t_documento_allegato
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_documento_allegato
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_documento_allegato TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_documento_allegato TO idf_rw;

CREATE SEQUENCE seq_idf_t_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 3
  CACHE 1;
ALTER TABLE seq_idf_t_evento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_evento TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_evento TO idf_rw;

CREATE SEQUENCE seq_idf_t_intervento
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 13
  CACHE 1;
ALTER TABLE seq_idf_t_intervento
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_intervento TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_intervento TO idf_rw;

CREATE SEQUENCE seq_idf_t_settore
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_settore
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_settore TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_settore TO idf_rw;

CREATE SEQUENCE seq_idf_t_soggetto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999
  START 5
  CACHE 1;
ALTER TABLE seq_idf_t_soggetto
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_soggetto TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_soggetto TO idf_rw;

CREATE SEQUENCE seq_idf_t_specie
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_specie
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_specie TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_specie TO idf_rw;

CREATE SEQUENCE seq_idf_t_sub_comprensorio
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_sub_comprensorio
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_sub_comprensorio TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_sub_comprensorio TO idf_rw;

CREATE SEQUENCE seq_idf_t_sub_settore
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_sub_settore
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_sub_settore TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_sub_settore TO idf_rw;

CREATE SEQUENCE seq_idf_t_tipo_forestale
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_t_tipo_forestale
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_tipo_forestale TO idf;
GRANT ALL ON SEQUENCE seq_idf_t_tipo_forestale TO idf_rw;

CREATE SEQUENCE seq_idf_temp_istanza_compensazione
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_idf_temp_istanza_compensazione
  OWNER TO idf;
GRANT ALL ON SEQUENCE seq_idf_temp_istanza_compensazione TO idf;
GRANT ALL ON SEQUENCE seq_idf_temp_istanza_compensazione TO idf_rw;




-------------------
-- DATI           |
-------------------

INSERT INTO idf_r_tipoint_cnfipla(
            id_config_ipla, id_tipo_intervento)
    VALUES (0, 0);
    
    
    
    
----------------------------------------
-- Richiesta da Fernanda  - 20/07/2020 |
-- Eseguite in SVI e TST               |
----------------------------------------
INSERT INTO idf_d_classe_fertilita(
            id_classe_fertilita, descr_classe_fertilita, mtd_ordinamento, 
            flg_visibile)
    VALUES (0, 'Non definita', 0, 
            1);
    
ALTER TABLE idf_t_ads_relascopica DROP COLUMN fk_classe_fertilita;

ALTER TABLE idf_t_ads_superficie_nota ADD COLUMN fk_classe_fertilita numeric(3,0) not null default 0;

ALTER TABLE idf_t_ads_superficie_nota
  ADD CONSTRAINT fk_idf_d_classe_fertilita_01 FOREIGN KEY (fk_classe_fertilita)
      REFERENCES idf_d_classe_fertilita (id_classe_fertilita) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;  
      
      
      
      
      
----------------------------------------
-- Richiesta da Enrico  - 20/07/2020    |
-- Eseguite in SVI e TST                |
----------------------------------------

CREATE TABLE idf_asr_r_geeco_profilo_layer
(
	id_geeco_profilo            INTEGER NOT NULL,
	id_geeco_layer              character varying(100) NOT NULL,
	flg_tipo_accesso_scrittura  NUMERIC(1) NULL
		CONSTRAINT  flg_tipo_accesso_scrittura CHECK (flg_tipo_accesso_scrittura in (0,1)),
	flg_tipo_accesso_canc  			NUMERIC(1) NULL
		CONSTRAINT  flg_tipo_accesso_canc CHECK (flg_tipo_accesso_canc in (0,1))	
);

ALTER TABLE idf_asr_r_geeco_profilo_layer
  ADD CONSTRAINT pk_idf_asr_r_geeco_profilo_layer PRIMARY KEY(id_geeco_profilo, id_geeco_layer);



CREATE TABLE idf_asr_t_geeco_layer
(
	id_geeco_layer         character varying(100) NULL,
	descr_asr_geeco_layer  character varying(100) NULL
);

ALTER TABLE idf_asr_t_geeco_layer
  ADD CONSTRAINT pk_idf_asr_t_geeco_layer PRIMARY KEY(id_geeco_layer);



CREATE TABLE idf_asr_t_geeco_profilo
(
	id_geeco_profilo      INTEGER NULL,
	descr_geeco_profilo   character varying(100) NULL,
	fk_procedura          INTEGER NOT NULL,
	url_ritorno           character varying(100) NULL,
	env_info              character varying(20) NULL
);

ALTER TABLE idf_asr_t_geeco_profilo
  ADD CONSTRAINT pk_idf_asr_t_geeco_profilo PRIMARY KEY(id_geeco_profilo);
	
	
	
ALTER TABLE idf_asr_r_geeco_profilo_layer
	ADD CONSTRAINT fk_idf_asr_geeco_profilo_01 FOREIGN KEY (id_geeco_profilo) REFERENCES idf_asr_t_geeco_profilo(id_geeco_profilo);

ALTER TABLE idf_asr_r_geeco_profilo_layer
	ADD CONSTRAINT fk_idf_asr_geeco_layer_01 FOREIGN KEY (id_geeco_layer) REFERENCES idf_asr_t_geeco_layer(id_geeco_layer);


ALTER TABLE idf_asr_t_geeco_profilo
	ADD CONSTRAINT fk_idf_cnf_procedura_02 FOREIGN KEY (fk_procedura) REFERENCES idf_cnf_procedura(id_procedura);



----------------------------------------
-- Richiesta da Fernanda  - 22/07/2020 |
-- Non ancora Eseguite in SVI e TST               |
----------------------------------------

-- creata in svi e tst
CREATE TABLE idf_d_num_tavola_coeff
(
	num_tavola_coeff_cub  INTEGER NULL,
	des_tavola_coeff_cub  character varying(20) NULL
);

ALTER TABLE idf_d_num_tavola_coeff
	ADD CONSTRAINT pk_idf_d_num_tavola_coeff PRIMARY KEY (num_tavola_coeff_cub);
	
	
alter table idf_t_idf_all_tav add column nome_coefficiente character varying(20) ;
	
alter table idf_t_idf_all_tav add column valore numeric(9,6) ;


ALTER TABLE idf_t_idf_all_tav DROP CONSTRAINT pk_idf_t_idf_all_tav;
ALTER TABLE idf_t_idf_all_tav
  ADD CONSTRAINT pk_idf_t_idf_all_tav PRIMARY KEY(id_specie, num_tavola_coeff_cub,nome_coefficiente );


/* ho fatto
-- create table idf_t_idf_all_tav_20200723 as select * from idf_t_idf_all_tav;
--ALTER TABLE idf_t_idf_all_tav DROP CONSTRAINT pk_idf_t_idf_all_tav;


*/

update idf_t_idf_all_tav set (nome_coefficiente, valore) values ('t0', t0);
select id_specie, num_tavola_coeff_cub, flg_coniflatif,'t3',t3 from idf_t_idf_all_tav where nome_coefficiente = 't0';

insert into idf_t_idf_all_tav (id_specie, num_tavola_coeff_cub,flg_coniflatif, nome_coefficiente, valore) 
select id_specie, num_tavola_coeff_cub, flg_coniflatif,'t3',t3 from idf_t_idf_all_tav where nome_coefficiente = 't0';


--ALTER TABLE idf_t_idf_all_tav ADD CONSTRAINT pk_idf_t_idf_all_tav PRIMARY KEY(id_specie, num_tavola_coeff_cub,nome_coefficiente );


----------------------------------------------------------------------------------

--fatto in svi e tst
ALTER TABLE idf_r_adsrel_specie
   ADD COLUMN num_tavola_coeff_cub integer not null default 0;    

   
ALTER TABLE idf_r_adsrel_specie
	ADD CONSTRAINT fk_idf_d_num_tavola_coeff_02 FOREIGN KEY (num_tavola_coeff_cub) REFERENCES idf_d_num_tavola_coeff(num_tavola_coeff_cub);


ALTER TABLE idf_t_idf_all_tav
	ADD CONSTRAINT fk_idf_d_num_tavola_coeff_01 FOREIGN KEY (num_tavola_coeff_cub) REFERENCES idf_d_num_tavola_coeff(num_tavola_coeff_cub);




    
    
-- da fare alla fine del popolamento    
alter table idf_t_idf_all_tav drop column flg_coniflatif ;

alter table idf_t_idf_all_tav drop column descrizione;   
alter table idf_t_idf_all_tav drop column t0;    
alter table idf_t_idf_all_tav drop column t1;  
alter table idf_t_idf_all_tav drop column t2;  
alter table idf_t_idf_all_tav drop column t3;    

alter table idf_t_specie drop column densita ;   



----------------------------------------
-- Richiesta da Fernanda  - 24/07/2020 |
-- Eseguite in SVI e TST               |
----------------------------------------
ALTER TABLE idf_geo_pt_area_di_saggio
   ADD COLUMN ambito_specifico character varying(100);
   
 
-- Script ing  10/11/2020
CREATE TABLE idf_report_tipo_ads
(
    id integer NOT NULL,
    denominazione character varying,
    CONSTRAINT pk_idf_report_tipo_ads PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);



INSERT  INTO idf_report_tipo_ads(id, denominazione)VALUES(1,'Superficie nota');
INSERT INTO idf_report_tipo_ads(id, denominazione)VALUES(2,'Relascopica semplice');
INSERT INTO idf_report_tipo_ads(id, denominazione)VALUES(3,'Relascopica completa');
	
ALTER TABLE idf_report_tipologia
ADD COLUMN fk_report_tipo_ads integer not null default 1;

ALTER TABLE idf_report_tipologia ADD CONSTRAINT report_tipo_ads_fkey FOREIGN KEY (fk_report_tipo_ads)
        REFERENCES idf_report_tipo_ads (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
		

INSERT INTO idf_report_tipologia(id, denominazione, fk_report_tipo_ads) VALUES (6,'BASIMETRIA',2);		
INSERT INTO idf_report_tipologia(id, denominazione, fk_report_tipo_ads) VALUES (7,'BASIMETRIA',3);
INSERT INTO idf_report_tipologia(id, denominazione, fk_report_tipo_ads) VALUES (8,'IPSOMETRIA',3);
INSERT INTO idf_report_tipologia(id, denominazione, fk_report_tipo_ads) VALUES (9,'DIAMETRI',3);
INSERT INTO idf_report_tipologia(id, denominazione, fk_report_tipo_ads) VALUES (10,'VOLUMI',3);
INSERT INTO idf_report_tipologia(id, denominazione, fk_report_tipo_ads) VALUES (11,'INCREMENTI',3);

-- Script 29/04/2021
CREATE TABLE idf_r_governo_tipointerv (
    id_tipo_intervento NUMERIC(4)  NOT NULL,
    id_governo INTEGER  NOT NULL,
    rif_interv_primpa NUMERIC(5),
    note CHARACTER VARYING(250),
    CONSTRAINT PK_idf_r_governo_tipointerv PRIMARY KEY (id_tipo_intervento, id_governo)
);

GRANT ALL ON TABLE idf_r_governo_tipointerv TO idf;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE idf_r_governo_tipointerv TO idf_rw;


ALTER TABLE idf_r_governo_tipointerv ADD CONSTRAINT fk_idf_d_tipo_intervento_06 
    FOREIGN KEY (id_tipo_intervento) REFERENCES idf.idf_d_tipo_intervento (id_tipo_intervento);
ALTER TABLE idf_r_governo_tipointerv ADD CONSTRAINT fk_idf_d_governo_02 
    FOREIGN KEY (id_governo) REFERENCES idf.idf_d_governo (id_governo);
    

    
ALTER TABLE idf_t_specie
   ADD COLUMN rif_specie_primpa numeric(5,0);
   
ALTER TABLE idf_d_esbosco
   ADD COLUMN rif_esbosco_primpa numeric(5,0);
   
   
   
  
-- Script 31/05/2021 - Richiesta di Enrico  

ALTER TABLE idf_asr_t_geeco_layer ADD COLUMN flg_tipo_layer character varying(2);

ALTER TABLE idf.idf_asr_t_geeco_layer ADD
    CHECK (flg_tipo_layer in ('PL','PT', 'LN', 'MP'));   
   
 
   
   
            