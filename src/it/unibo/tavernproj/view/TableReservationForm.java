package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Reservation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 */
public class TableReservationForm extends NewReservationForm{
  
  private static final long serialVersionUID = 1L;
  private final transient IReservation res;  
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
    this.res = res;
    setHandlers();
    writeForm();
    super.getContentPane().add(util.buildGridPanel(
        util.getList(modifyButton, deleteButton), 5), BorderLayout.EAST);
    this.disableAll();
  }

  private void writeForm() {
    super.setTable(res.getTable());
    super.setName(res.getName());
    super.setH(res.getHours());
    super.setTel(res.getTel());
    super.setNum(Integer.parseInt(res.getNumPers()));
    super.setMenu(res.getMenu());
  }
  
  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent e) {
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
