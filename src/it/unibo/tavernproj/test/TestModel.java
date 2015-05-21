package it.unibo.tavernproj.test;

/**
 * @author Giulia Lucchi
 * 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;

public class TestModel {
  
  private final IModel model = new Model();
  private final IController ctr = Controller.getController();

  @org.junit.Test
  public void test() {

    ctr.setModel(model);

    final IReservation r1 = new Reservation(1, "Giulia", "30-04-2015",
        21.30,"054751083", 2, "Bruschette");
    final IReservation r2 = new Reservation(3, "Andrea", "30-04-2015",
        21.30, "054751042", 8, null);
    final IReservation r3 = new Reservation(2, "Pirlo", "14-06-2015",
        21.30, "054751267", 12, null);
    final IReservation r4 = new Reservation(1, "Eleonora", "1-05-2015",
        21.30, "054751323", 1, null);
    final IReservation r5 = new Reservation(1, "Federico", "14-06-2015",
        21.30, "054751876", 4, null);
    final IReservation r6 = new Reservation(1, "Enrico", "3-07-2015",
        21.30, "054751376", 4, null);
    final IReservation r7 = new Reservation(6, "Alessandro", "3-07-2015",
        21.30, "054751000", 2, "grigliata");
    final IReservation r8 = new Reservation(2, "Lorenzo", "3-07-2015",
        21.30, "054751222", 6, null);
    final IReservation r9 = new Reservation(1, "Federico", "8-07-2015",
        21.30, "054751333", 2, null);
    
    /* 
     * Add the reservations.
     * Check that the addition of reservations was successful.
     */
    model.add(r1.getDate(), r1);
    model.add(r2.getDate(), r2);
    model.add(r3.getDate(), r3);
    model.add(r4.getDate(), r4);
    model.add(r5.getDate(), r5);
    model.add(r6.getDate(), r6);
    model.add(r7.getDate(), r7);
    model.add(r8.getDate(), r8);
    model.add(r9.getDate(), r9);
    assertEquals(model.getSize(),5);
    
    /*
     * Remove the reservations.
     * Check that removing of reservations was successful.
     */
    model.remove(r4.getDate(), r4);
    assertEquals(model.getSize(),4);
    model.remove(r1.getDate(), r1);
    assertEquals(model.getTableRes(r1.getDate()).size(),1);
    model.remove(r8.getDate(), r8.getTable());
    assertEquals(model.getTableRes(r8.getDate()).size(),2);
    
    /*
     * Check the Model's method 
     */
    assertEquals(model.getTableRes(r8.getDate()).get(r8.getTable()), null);
    assertEquals(model.getTableRes(r9.getDate()).size(),1);
    assertEquals(model.getTableRes(r7.getDate()).get(6).getName(),"Alessandro");
    assertEquals(model.getNameRes("Federico").size(),2);
    assertEquals(model.getNameRes("Alessandro").size(),1);
    assertEquals(model.getRes(r2.getDate()).size(),1);
    assertFalse(model.getRes(r2.getDate()).contains(r1));
    assertFalse(model.getRes(r2.getDate()).contains(r8));
  }


}
