package it.unibo.tavernproj.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.tavernproj.model.IReservation;

/**
 * @author Eleonora Guidi
 *
 */
public class ModifiedTableForm extends NewReservationForm implements IModifiedTableForm{

  private static final long serialVersionUID = 1L;

  /**
   * Builds a new reservation form with the reservation 
   * passed as base to fill the fields, ready to be modified.
   * 
   * @param res
   *      the reservation passed.
   */
  public ModifiedTableForm(final IReservation res) {
    super();
    writeForm(res);
  }
  
  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent event) {
        ModifiedTableForm.this.modified();
      }     
    };    
  }

  /**
   * If the form is been modified, activate this method which 
   * saves the new reservation.
   */
  protected void modified() {
    ModifiedTableForm.this.setVisible(false); 
    try {
      controller.removeReservation(getTable(), controller.getDate());
    } catch (IllegalArgumentException e2) {
      /* Non segnala nulla perchè viene lanciata quando modifico il 
       * tavolo più di una volta. In questo caso devo solo intercettare
       * l'eccezione.
       */
    } 
    checkForm();
  }
  
  @Override
  public void writeForm(final IReservation res) {
    super.enableAll();
    super.setTable(res.getTable());     
    super.setName(res.getName());
    super.setH(res.getHour());
    super.setTel(res.getTel());
    super.setNum(res.getNumPers());
    super.setMenu(res.getMenu());    
  }

}
