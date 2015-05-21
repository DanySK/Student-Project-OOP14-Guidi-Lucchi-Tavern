package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Eleonora Guidi
 *
 */
public class TableReservationForm extends NewReservationForm{
  
  private static final long serialVersionUID = 1L;  
  private final JButton modifyButton = util.getDefaultButton("Modifica", 12);
  private final JButton deleteButton = util.getDefaultButton("Elimina", 12); 
  private boolean modified;

  /**
   * It builds a new Table Reservation Form whenever we need to see the reservation
   * from the table button.
   * @param res
   *      the reservation.
   */
  public TableReservationForm(final IReservation res) {
    super();
    setHandlers();
    writeForm(res);
    super.getContentPane().add(util.buildGridPanel(
        util.getList(modifyButton, deleteButton), 5), BorderLayout.EAST);
    this.disableAll();
  }
  
  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent event) {
        TableReservationForm.this.setVisible(false); 
        if (isBeenModified()) {
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
    };
  }

  private void setHandlers() {
    this.modifyButton.addActionListener(e -> {
        TableReservationForm.this.enableAll();
        TableReservationForm.this.modified = true;
        deleteButton.setEnabled(false);
      });

    this.deleteButton.addActionListener(e -> {
        controller.remove(getTable(), controller.getDate());
        TableReservationForm.this.setVisible(false);
      });
  }
  
  private boolean isBeenModified() {
    return this.modified;
  }
}
