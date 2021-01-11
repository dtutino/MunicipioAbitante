package it.prova.municipioAbitanteJPA.test;

import it.prova.municipioAbitanteJPA.dao.EntityManagerUtil;
import it.prova.municipioAbitanteJPA.model.Abitante;
import it.prova.municipioAbitanteJPA.model.Municipio;
import it.prova.municipioAbitanteJPA.service.MyServiceFactory;
import it.prova.municipioAbitanteJPA.service.abitante.AbitanteService;
import it.prova.municipioAbitanteJPA.service.municipio.MunicipioService;

public class TestMunicipioAbitante {

	public static void main(String[] args) {

		MunicipioService municipioService = MyServiceFactory.getMunicipioServiceInstance();
		AbitanteService abitanteService = MyServiceFactory.getAbitanteServiceInstance();

		try {

			// creo nuovo municipio
			Municipio nuovoMunicipio = new Municipio("Municipio III", "III", "Via dei Nani");
			// salvo
			//municipioService.inserisciNuovo(nuovoMunicipio);
			System.out.println("Municipio appena inserito: " + nuovoMunicipio);
			// da questa riga in poi municipio ha un nuovo id

			// creo nuovo abitante
			Abitante nuovoAbitante = new Abitante("Pluto", "Plutorum", 77, "Via Lecce");
			// lo lego al municipio appena inserito
			//nuovoAbitante.setMunicipio(nuovoMunicipio);
			// salvo il nuovo abitante
			//abitanteService.inserisciNuovo(nuovoAbitante);

			// ricarico il municipio per vederne gli aggiornamenti
			// questa, durante la system.out che richiede gli abitanti, solleverebbe
			// una LazyInitializationException in quanto il contesto di persistenza è chiuso
//			Municipio municipioInstance = municipioService.caricaSingoloMunicipio(nuovoMunicipio.getId());
//			// allora usiamo un caricamento EAGER sovrascrivendo municipioInstance
//			municipioInstance = municipioService.caricaSingoloMunicipioConAbitanti(nuovoMunicipio.getId());
//			System.out
//					.println("Stampo gli abitanti del municipio appena ricaricato:" + municipioInstance.getAbitanti());

//			System.out.println("########### RIMOZIONE ABITANTE ########################");
//			long idAbitanteEsistente = 19;
//			Abitante abitanteEsistente2 = abitanteService.caricaSingoloAbitante(idAbitanteEsistente);
//			if (abitanteEsistente2 != null) {
//				abitanteService.rimuovi(abitanteEsistente2);
//				//proviamo a vedere se è stato rimosso
//				abitanteEsistente2 = abitanteService.caricaSingoloAbitante(idAbitanteEsistente);
//				if (abitanteEsistente2 == null)
//					System.out.println("Cancellazione ok");
//				else
//					System.out.println("Cancellazione fallita!!!");
//			}
//			System.out.println("########### FINE RIMOZIONE ABITANTE ########################");

			// elencare i municipi
			System.out.println("Elenco i municipi:");
			for (Municipio municipioItem : municipioService.listAllMunicipi()) {
				System.out.println(municipioItem);
			}

			// elenca tutti i Pluto
			System.out.println("Ecco i Pluto....");
			for (Abitante abitanteItem : abitanteService.cercaTuttiGliAbitantiConNome("Pluto")) {
				System.out.println(abitanteItem);
			}
			
			// elenca tutti i municipi con minorenni
			System.out.println("Ecco i municipi con minorenni...");
			for (Municipio municipioItem : municipioService.cercaTuttiIMunicipiConMinorenni()) {
				System.out.println(municipioItem);
			}
			
			// elenca tutti i municipi con descrizione che inizia per 'mun'
			System.out.println("Ecco i municipi la cui descrizione inizia per 'mun'");
			for (Municipio municipioItem : municipioService.cercaTuttiIMunicipiConDescrizioneCheIniziaCon("mun")) {
				System.out.println(municipioItem);
			}
			
			// elenca tutti i plutorum
			System.out.println("Ecco i plutorum...");
			for (Abitante abitanteItem : abitanteService.cercaTuttiGliAbitantiConCognome("plutorum")) {
				System.out.println(abitanteItem);
			}
			
			// elenca abitanti con codice municipio che inizia con 'I'
			System.out.println("Ecco gli abitanti con codice municipio che inizia con 'I'");
			for (Abitante abitanteItem : abitanteService.cercaTuttiGliAbitantiConCodiceMunicipioCheIniziaCon("I")) {
				System.out.println(abitanteItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

}
