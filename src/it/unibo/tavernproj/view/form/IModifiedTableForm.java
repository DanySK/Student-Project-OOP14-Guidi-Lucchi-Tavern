package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.model.IReservation;

/**
 * @author Eleonora Guidi
 *
 */
public interface IModifiedTableForm {

  
  /**
   * Fills the form with the reservation passed.
   * 
   * @param res
   *      the reservation.
   */
  void writeForm(IReservation res);
}
