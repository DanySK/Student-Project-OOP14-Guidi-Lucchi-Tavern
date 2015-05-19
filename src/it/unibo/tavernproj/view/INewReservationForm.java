package it.unibo.tavernproj.view;

import java.awt.event.ActionListener;

public interface INewReservationForm {
  
  /**
   * @return
   *      an ActionListener setting the button behavior.
   */
  ActionListener setOkListener();
  
  /**
   * Checks if the Form is been filled correctly.
   */
  void checkForm();

}
