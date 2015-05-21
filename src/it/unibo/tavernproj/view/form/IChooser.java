package it.unibo.tavernproj.view.form;

import java.awt.event.ActionListener;

import it.unibo.tavernproj.model.IReservation;

public interface IChooser {
  
  
  /**
   * Sets the behavior of the ok button.
   * 
   * @return
   *      the ok Action Listener
   */
  ActionListener setOkListener();

  /**
   * @return
   *      the reservation linked to the table and date chose from the user.
   */
  //IReservation getReservation();

  IReservation getReservation(int table, String date) throws NumberFormatException;

}
