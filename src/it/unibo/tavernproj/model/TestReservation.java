package it.unibo.tavernproj.model;

import static org.junit.Assert.*;

import java.util.Map;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;

public class TestReservation {
	
	private final IModel model = new Model();
	private final IController ctr = Controller.getController();
	
	@org.junit.Test
	public void test(){
		
		final IReservation r1 = new Reservation("1", "Giulia", "30-04-2015", "21.30","054751083" , 5, null);
		final IReservation r2 = new Reservation("3", "Andrea", "30-04-2015", "21.30","054751042" , 5, null);
		final IReservation r3 = new Reservation("2", "Pirlo", "14-06-2015", "21.30","054751267" , 5, null);
		final IReservation r4 = new Reservation("1", "Eleonora", "1-05-2015", "21.30","054751323" , 5, null);
		final IReservation r5 = new Reservation("1", "Federico", "14-06-2015", "21.30","054751876" , 5, null);
		final IReservation r6 = new Reservation("1", "Enrico", "3-07-2015", "21.30","054751376" , 5, null);
		
		model.add(r1.getDate(), r1);
		model.add(r2.getDate(), r2);
		model.add(r3.getDate(), r3);
		model.add(r4.getDate(), r4);
		model.add(r5.getDate(), r5);
		model.add(r6.getDate(), r6);
		
		assertEquals(model.getSize(),4);
		
		model.remove(r4.getDate(), r4);
		assertEquals(model.getSize(),3);
	
		Map<Integer,IReservation> mapP = model.getTableRes(r1.getDate());
		
		assertEquals(mapP.size(),2);
		model.remove(r1.getDate(), r1);
		assertEquals(mapP.size(),1);
		
		ctr.setTables(model.getMap());		
		
		ctr.getTables(model);

	}
	
}
