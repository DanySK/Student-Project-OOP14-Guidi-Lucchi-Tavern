package it.unibo.tavernproj.test;

/**
 * @author Giulia Lucchi
 */



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;

public class TestReservation {

  private final IModel model = new Model();
  private final IController ctr = Controller.getController();

  @org.junit.Test
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
                                           .tel("054751042")
                                           .numPers(12)
                                           .menu(null)
                                           .build();
    final IReservation r4 = new Reservation.Builder()
                                           .table(1)
                                           .name("Eleonora")
                                           .date("01-05-2015")
                                           .hour(21.30)
                                           .tel("054751042")
                                           .numPers(1)
                                           .menu(null)
                                           .build();
    final IReservation r5 = new Reservation.Builder()
                                           .table(1)
                                           .name("Federico")
                                           .date("14-06-2015")
                                           .hour(21.30)
                                           .tel("054751042")
                                           .numPers(4)
                                           .menu(null)
                                           .build();
    final IReservation r6 = new Reservation.Builder()
                                           .table(1)
                                           .name("Enrico")
                                           .date("03-07-2015")
                                           .hour(21.30)
                                           .tel("054751042")
                                           .numPers(4)
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
    assertEquals(model.getSize(),4);

    /*
     * Check that the removal of reservations was successful.
     */
    model.remove(r4.getDate(), r4);
    assertEquals(model.getSize(),3);
    assertEquals(model.getTableRes(r1.getDate()).size(),2);
    model.remove(r1.getDate(), r1);
    assertEquals(model.getTableRes(r1.getDate()).size(),1);
    
    /*
     * Save the reservations.
     * Remove the elements from Map to check the loading. 
     */
    ctr.saveModel();
    model.getMap().clear();

    /*
     * Check that the map that the map from which I saved is empty .
     * Check that the map of comparison to which I added the bookings is not empty.
     */
    assertTrue(model.getMap().isEmpty());
    ctr.setModel();

    /*
     * Check that the loading of reservations was successful.
     */
    assertEquals(model.getTableRes("14-06-2015").get(2).getName(), "Pirlo");
    assertEquals(model.getTableRes("03-07-2015").get(1).getName(), "Enrico");
    assertEquals(model.getTableRes("30-04-2015").get(3).getTel(), "054751042");

  }

}
