# Project Title
Infrastruttura Dati Forestali

# Project Description
L'Infrastruttura Dati Forestali (in seguito denominata IDF) è un insieme di banche dati e servizi informatici tra loro interconnessi e inseriti nel contesto del Sistema Informativo Forestale Regionale (SIFOR), definito dalla L.R. 4/2009, art. 34, che permette la condivisione di informazioni aggiornate sul comparto forestale piemontese. IDF è rivolta agli operatori del settore forestale: imprenditori, addetti del settore forestale (compresi i tecnici liberi professionisti) e gestori del territorio agroforestale operanti su tutto il territorio regionale (Consorzi, Associazioni forestali che gestiscono superfici pubbliche o private, nonché proprietari di vaste superfici boscate (che si sono dotate di Piani Forestali Aziendali per la gestione delle stesse). Essa comprende le seguenti componenti:

1) Piani di Gestione Forestale (ex Piani Forestali Aziendali) che permette la consultazione dei Piani di Gestione Forestale (PGF ex PFA) approvati e gestione del relativo registro degli interventi ed eventi. Il Piano di Gestione Forestale (PGF) è lo strumento che permette la programmazione, le richieste di comunicazione e autorizzazioni e la gestione degli interventi selvicolturali e delle opere connesse. La Legge forestale (l.r. 4/2009) riconosce la funzione dei PGF all'interno del sistema della pianificazione forestale regionale e attribuisce l'iniziativa della redazione dei PGF ai proprietari boschivi pubblici o privati. Ha una durata da 10 a 15 anni, con programma degli interventi selvicolturali flessibile su base triennale o quinquennale. Interessa una superficie di estensione minima indicativa di almeno 50 ettari boscati con gestione attiva, appartenenti anche a proprietà diverse associate o con unico soggetto gestore;

2) Inventari forestali (produttività dei boschi piemontesi) che permette la gestione, la consultazione alfanumerica e cartografica e le elaborazioni dendrometriche degli inventari forestali. Il ruolo e l'importanza dell'Inventario Forestale Regionale è definito dalla Legge forestale del Piemonte (L.R. 4/09). Esso è una componente essenziale del Piano Forestale Regionale (PFR) (L.R. 4/09 art. 9) e in Piemonte deriva dagli studi per i PFT, che sono stati redatti sull'intera superficie regionale e che costituiscono la fonte di dati rilevati con metodologia omogenea e codificata che ha permesso l'elaborazione dell'inventario. La procedura consente ai gestori del territorio agro-forestale e le imprese boschive di elaborare autonomamente i parametri fondamentali (aree basimetriche, altezze, volumi, incrementi) per definire la produttività dei boschi piemontesi con indicazioni della loro valenza statistica in funzione del campione o delle selezioni territoriali prese in considerazione.

3) Sistema di gestione delle istanze ai sensi della L.R. 4/2009 (legge forestale) che permette la presentazione e consultazione delle autocertificazioni e dei progetti di compensazione fisica relativi a interventi di trasformazione del bosco, delle comunicazioni semplici e delle richieste di autorizzazione per gli interventi selvicolturali e delle istanze di autorizzazione al recupero di castagneti e noccioleti da frutto. In particolare, la procedura riguarda la compilazione e l'invio ai soggetti regionali competenti di istanze forestali relative ai seguenti procedimenti:

* Trasformazione del bosco: autocertificazione e dichiarazione d'atto notorio ai sensi del DPR 445/2000
    
* Interventi selvicolturali: comunicazioni semplici e autorizzazioni con progetto ai sensi dell'articolo 14 della Legge Regionale n° 4 del 10 Febbraio 2009 ‘'Gestione e promozione economica delle foreste''; presentazione dei progetti di compensazione fisica relativi a interventi di trasformazione del bosco e delle istanze di autorizzazione al recupero di castagneti e noccioleti da frutto.
    

IDF rappresenta un valido supporto alle attività di Pianificazione Forestale, di programmazione e verifica dell'ammissibilità degli interventi nell'ambito delle misure di interesse forestale del PSR , facilitando la diffusione delle informazioni relative alla gestione del patrimonio silvo-pastorale, armonizzando i dati territoriali con la base dati geografica di riferimento per il territorio piemontese BDTRE e integrando le funzionalità offerte dalle diverse componenti del sistema, nell'ottica di migliorare la gestione e la fruibilità delle informazioni trattate.


# Getting Started
Il prodotto IDF è diviso nelle seguenti componenti:

[IDFAPI](https://github.com/regione-piemonte/idf/tree/master/idfapi) (Componente di api rest per l'esposizione verso il frontend)
[IDFBO](https://github.com/regione-piemonte/idf/tree/master/idfbo) (Componente di back office dei procedimenti)
[IDFGEOAPI](https://github.com/regione-piemonte/idf/tree/master/idfgeoapi) (Componente di esposizione api di callback verso la componente geografica)
[IDF](https://github.com/regione-piemonte/idf/tree/master/idf) (script per creazione e mantenimento DB proprietario)
[IDFACC](https://github.com/regione-piemonte/idf/tree/master/idfacc) (componente angular di accesso ed accreditamento)
[IDFINV](https://github.com/regione-piemonte/idf/tree/master/idfinv) (componente angular di inventari forestali)
[IDFISTAFOR](https://github.com/regione-piemonte/idf/tree/master/idfistafor) (componente angular di presentazione istanze forestali)
[IDFPFA](https://github.com/regione-piemonte/idf/tree/master/idfpfa) (componente angular dei piani forestali aziendale)
[IDFPFAPUB](https://github.com/regione-piemonte/idf/tree/master/idfpfapub) (componente angular dei piani forestali aziendale ad accesso libero)
[IDFREP](https://github.com/regione-piemonte/idf/tree/master/idfrep) (componente angular di reportistica)


# Prerequisites
I prerequisiti per l'installazione del prodotto sono
## Software

- [JDK 8](https://www.apache.org/)
- [Apache 2.4](https://www.apache.org/)
- [RedHat JBoss 6.4 GA](https://developers.redhat.com/)
- [PostgreSQL 9.6.10](https://www.postgresql.org/download/)

# Versioning
Per la gestione del codice sorgente viene utilizzata Git. Per la gestione del versioning si fa riferimento alla metodologia [Semantic Versioning](https://semver.org/) 

# Copyrights
(C) Copyright 2023 Regione Piemonte

# License
Questo software è distribuito con licenza EUPL-1.2
Consultare il file EUPL v1_2 IT-LICENSE.txt e EUPL v1_2 EN-LICENSE.txt  per i dettagli sulla licenza.