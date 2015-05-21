package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.model.IReservation;

import java.awt.event.ActionListener;

public interface INewReservationForm {
  
  /**
   * @return
   *      an ActionListener setting the button behavior.
   */
  ActionListener setOkListener();
  
  /**
   * @return
   *      true if the Form is been filled correctly.
   */
  boolean checkForm();
  
  /**
   * Fills the form with the reservation passed.
   * 
   * @param res
   *      the reservation.
   */
  void writeForm(IReservation res);

}
