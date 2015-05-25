package it.unibo.tavernproj.view.form;

import java.awt.event.ActionListener;
import java.text.ParseException;

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
   *      
   * @throws ParseException 
   *      if he date added to the form is wrong.
   * 
   * @throws NumberFormatexception
   *      if it's added a string or a wrong table the form.
   *      
   * @throws IllegalArgumentException 
   *      if there is no reservation linked to that date and table.
   */
  IReservation getReservation() throws IllegalArgumentException, ParseException, NumberFormatException;

}
