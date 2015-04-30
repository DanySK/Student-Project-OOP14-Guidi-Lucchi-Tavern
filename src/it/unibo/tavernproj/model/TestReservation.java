package it.unibo.tavernproj.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;

public class TestReservation {
	
	private final IModel model = new Model();
	private final IController ctr = Controller.getController();
	
	@org.junit.Test
	public void test(){
	  
	  ctr.setModel(model);
		
		final IReservation r1 = new Reservation("1", "Giulia", "30-04-2015", "21.30","054751083" , 5, null);
		final IReservation r2 = new Reservation("3", "Andrea", "30-04-2015", "21.30","054751042" , 5, null);
		final IReservation r3 = new Reservation("2", "Pirlo", "14-06-2015", "21.30","054751267" , 5, null);
		final IReservation r4 = new Reservation("1", "Eleonora", "1-05-2015", "21.30","054751323" , 5, null);
		final IReservation r5 = new Reservation("1", "Federico", "14-06-2015", "21.30","054751876" , 5, null);
		final IReservation r6 = new Reservation("1", "Enrico", "3-07-2015", "21.30","054751376" , 5, null);
		
		//Controllo che l'aggiunta delle prenotazioni sia avvenuta correttamente.
		model.add(r1.getDate(), r1);
		model.add(r2.getDate(), r2);
		model.add(r3.getDate(), r3);
		model.add(r4.getDate(), r4);
		model.add(r5.getDate(), r5);
		model.add(r6.getDate(), r6);
		assertEquals(model.getSize(),4);
		
		//Controllo la rimozione delle prenotazioni sia avvenuta correttamente.
		model.remove(r4.getDate(), r4);
		assertEquals(model.getSize(),3);
		assertEquals(model.getTableRes(r1.getDate()).size(),2);
		model.remove(r1.getDate(), r1);
		assertEquals(model.getTableRes(r1.getDate()).size(),1);
		
		ctr.saveModel();		

		//map.putAll(model.getMap());
		model.getMap().clear();
		
		/*
		 * Controllo che la mappa che la mappa da cui ho salvato sia vuota.
		 * Controllo che la mappa di confronto alla quale gli ho aggiunto le prenotazioni non sia vuota.
		 */
    assertTrue(model.getMap().isEmpty());
    //assertFalse(map.isEmpty());
    
		ctr.setModel();
		
		//Controllo se il caricamento � avvenuto correttamente
		assertEquals(model.getTableRes("14-06-2015").get(2).getName(), "Pirlo");
    assertEquals(model.getTableRes("3-07-2015").get(1).getName(), "Enrico");
    assertEquals(model.getTableRes("30-04-2015").get(3).getTel(), "054751042");
		

	}
	
}
