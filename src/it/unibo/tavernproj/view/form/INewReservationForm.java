package it.unibo.tavernproj.view.form;

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

}
