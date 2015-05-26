package it.unibo.tavernproj.test;

/**
 * @author Giulia Lucchi
 * 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;

public class TestModel {
  
  private final IModel model = new Model();
  private final IController ctr = Controller.getController();

  @Test
  public void test() {

    ctr.setModel(model);

    final IReservation r1 = new Reservation.Builder()
                                           .table(1)
                                           .name("Giulia")
                                           .date("30-04-2015")
                                           .hour(21.30)
                                           .tel("054751083")
                                           .numPers(2)
                                           .menu("Bruschette")
                                           .build();
    final IReservation r2 = new Reservation.Builder()
                                           .table(3)
                                           .name("Andrea")
                                           .date("30-04-2015")
                                           .hour(21.30)
                                           .tel("054751042")
                                           .numPers(8)
                                           .menu(null)
                                           .build();
    final IReservation r3 = new Reservation.Builder()
                                           .table(2)
                                           .name("Pirlo")
                                           .date("14-06-2015")
                                           .hour(21.30)
                                           .tel("054753152")
                                           .numPers(12)
                                           .menu(null)
                                           .build();
    final IReservation r4 = new Reservation.Builder()
                                           .table(1)
                                           .name("Eleonora")
                                           .date("01-05-2015")
                                           .hour(21.30)
                                           .tel("351564242")
                                           .numPers(1)
                                           .menu(null)
                                           .build();
    final IReservation r5 = new Reservation.Builder()
                                           .table(1)
                                           .name("Federico")
                                           .date("14-06-2015")
                                           .hour(21.30)
                                           .tel("05431524242")
                                           .numPers(4)
                                           .menu(null)
                                           .build();
    final IReservation r6 = new Reservation.Builder()
                                           .table(1)
                                           .name("Enrico")
                                           .date("03-07-2015")
                                           .hour(21.30)
                                           .tel("05425262")
                                           .numPers(4)
                                           .menu(null)
                                           .build();
    final IReservation r7 = new Reservation.Builder()
                                           .table(6)
                                           .name("Alessandro")
                                           .date("03-07-2015")
                                           .hour(21.30)
                                           .tel("05542624642")
                                           .numPers(2)
                                           .menu("grigliata")
                                           .build();
    final IReservation r8 = new Reservation.Builder()
                                           .table(2)
                                           .name("Lorenzo")
                                           .date("03-07-2015")
                                           .hour(21.30)
                                           .tel("052642642")
                                           .numPers(6)
                                           .menu(null)
                                           .build();
    final IReservation r9 = new Reservation.Builder()
                                           .table(1)
                                           .name("Federico")
                                           .date("08-07-2015")
                                           .hour(21.30)
                                           .tel("054264242")
                                           .numPers(2)
                                           .menu(null)
                                           .build();
    
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
