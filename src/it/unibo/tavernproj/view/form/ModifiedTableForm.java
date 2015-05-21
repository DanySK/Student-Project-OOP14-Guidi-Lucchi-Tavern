package it.unibo.tavernproj.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.tavernproj.model.IReservation;

public class ModifiedTableForm extends NewReservationForm{

  private static final long serialVersionUID = 1L;

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

}
