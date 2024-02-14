/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.idf.idfapi.business.be.CategoriaSelvicolturaleApi;
import it.csi.idf.idfapi.business.be.impl.*;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfCnfDelegaApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfCnfSkOkVincIdroApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfCnfTipoallTipointApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfDCategoriaProfessionaleApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfGeoPlIntervVincidroApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfRIntervincidroGovernoApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfTIntervVincIdroApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfTIstanzaForestApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.IdfTSoggettoApiImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.PlainAdpVincoloHomeApiServiceImpl;
import it.csi.idf.idfapi.business.be.vincolo.api.impl.VincoloStepsApiImpl;
import it.csi.idf.idfapi.business.be.impl.TagliPfaApiImpl;
import it.csi.idf.idfapi.util.SpringInjectorInterceptor;
import it.csi.idf.idfapi.util.SpringSupportedResource;

@ApplicationPath("restfacade/be")
public class LgspawebRestApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();
    public LgspawebRestApplication(){
         //singletons.add(new LgspawclBE());
         singletons.add(new SpringInjectorInterceptor());
         singletons.add(new GeoPlPfaApiServiceImpl());
         singletons.add(new ComuneApiServiceImpl());
         singletons.add(new SpecieApiServiceImpl());
         singletons.add(new SoggettoApiServiceImpl());
         singletons.add(new ProvinciaApiServiceImpl());
         singletons.add(new CategoriaForestaleApiServiceImpl());
         singletons.add(new AmbitoRilievoApiServiceImpl());
         singletons.add(new InterventoApiServiceImpl());
         singletons.add(new DocumentoAllegatoApiServiceImp());
         singletons.add(new PropCatastoServiceApiImpl());
         singletons.add(new EventoApiServiceImpl());
         singletons.add(new TipoEventiApiServiceImpl());
         singletons.add(new FinalitaTaglioApiImpl());
         singletons.add(new DestLegnameApiImpl());
         singletons.add(new FasciaAltimetricaApiImpl());
         singletons.add(new UdmSpecieApiImpl());
         singletons.add(new UsoViabilitaApiImpl());
         singletons.add(new InterventoInsericiApiServisImpl());
         singletons.add(new EventoDatiTehniciApiImpl());
         singletons.add(new GovernoApiImpl());
         singletons.add(new SezioneApiServiceImpl()); //BDTRE
         singletons.add(new FoglioApiServiceImpl()); //BDTRE
         singletons.add(new ParticellaApiServiceImpl()); //BDTRES
         
         //STEFAN SERVICES
         singletons.add(new PlainAdpforHomeApiServiceImpl());
         singletons.add(new PlainUtenteApiServiceImpl());
         singletons.add(new PlainSezioniApiServiceImpl());
         singletons.add(new IstanzaForestAPIServiceImpl());
         singletons.add(new StatoIstanzaApiServiceImpl());
         singletons.add(new ParametroTrasfApiServiceImpl());
         singletons.add(new RicadenzApiServiceImpl());
         singletons.add(new TipoIstanzaApiServiceImpl());
         singletons.add(new DelegaApiServiceImpl());
         singletons.add(new PublicGeoPlPfaApiServiceImpl());
         singletons.add(new SigmaterApiServiceImpl());
         singletons.add(new StatoInterventoApiServiceImpl());
         singletons.add(new TestApiServiceImpl());
         //IVANA SERVICES
         singletons.add(new TipoForestaleApiServiceImpl());
         singletons.add(new GeoPtAreaDiSaggioApiServiceImpl());
         singletons.add(new TipoAdsApiServiceImpl());
         singletons.add(new DestinazioneApiServiceImpl());
         singletons.add(new TipoInterventoApiServiceImpl());
         singletons.add(new CategoriaProfessionaleApiServiceImpl());
         singletons.add(new TipoStrutturaleApiServiceImpl());
         singletons.add(new StadioSviluppoApiServiceImpl());
         singletons.add(new EsposizioneApiServiceImpl());
         singletons.add(new ProprietaApiServiceImpl());
         singletons.add(new EsboscoApiServiceImpl());
         singletons.add(new AssettoApiServiceImpl());
         singletons.add(new AdsrelSpecieServiceImpl());
         singletons.add(new RelascopicadetailsApiServiceImpl());
         singletons.add(new RelascopicaCompletaApiServiceImpl());
         singletons.add(new StatoAdsApiServiceImpl());
         singletons.add(new DannoApiServiceImpl());
         singletons.add(new PrioritaApiServiceImpl());
         singletons.add(new TipoCampioneApiImpl());
         singletons.add(new ClasseFertilitaApiServiceImpl());
         
         singletons.add(new RiepilogoApiServiceImpl());
         singletons.add(new TipoAllegatoApiServiceImpl());
         singletons.add(new DettaglioInterventiApiServiceImpl());		
         singletons.add(new ReportByCsvApiServiceImp());
        
         singletons.add(new PlainAdpVincoloHomeApiServiceImpl());
         singletons.add(new VincoloStepsApiImpl());
         
         singletons.add(new IdfCnfDelegaApiImpl());
         singletons.add(new IdfTSoggettoApiImpl());
         singletons.add(new IdfCnfSkOkVincIdroApiImpl());
         singletons.add(new IdfCnfTipoallTipointApiImpl());
         singletons.add(new IdfDCategoriaProfessionaleApiImpl());
         singletons.add(new IdfGeoPlIntervVincidroApiImpl());
         singletons.add(new IdfRIntervincidroGovernoApiImpl());
         singletons.add(new IdfTIntervVincIdroApiImpl());
         singletons.add(new IdfTIstanzaForestApiImpl());
         
         singletons.add(new ConfigurationApiServiceImpl());
         
         // Geeco
         singletons.add(new GeecoApiServiceImpl());
         singletons.add(new GeecoPublicApiServiceImpl());


         //Tagli selvicolturali
         singletons.add(new CategoriaSelvicolturaleApiServiceImpl());
         singletons.add(new TrasformazioniApiServiceImpl());
         singletons.add(new TagliSelvicolturaliApiImpl());
         singletons.add(new TagliPfaApiImpl());
         singletons.add(new TAIFApiImpl());

         for (Object c : singletons) {
 			if (c instanceof SpringSupportedResource) {
 				SpringApplicationContextHelper.registerRestEasyController(c);
 			}
 		}
    }
    @Override
    public Set<Class<?>> getClasses() {
         return empty;
    }
    @Override
    public Set<Object> getSingletons() {
         return singletons;
    }
}
