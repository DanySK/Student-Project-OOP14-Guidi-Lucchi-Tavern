package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Reservation;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 */
public class TableReservationForm extends ReservationForm{
  
  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities();
  private final transient IReservation res;  
  private final JButton modifyButton = util.getDefaultButton("Modifica", 12);
  private final JButton deleteButton = util.getDefaultButton("Elimina", 12); 
  private final JButton okButton = util.getDefaultButton("OK", 12);
  private final transient IController controller = Controller.getController();  
  private final JLabel date = new JLabel(controller.getDate());
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
    buildLayout();
    setHandlers();
    writeForm();
    this.setVisible(true);
  }

  private void writeForm() {
    super.setTable(res.getTable());
    super.setName(res.getName());
    super.setH(res.getHours());
    super.setTel(res.getTel());
    super.setNum(Integer.parseInt(res.getNumPers()));
    super.setMenu(res.getMenu());
  }

  private void buildLayout() {
    final JPanel east = util.buildGridPanel(util.getList(modifyButton, deleteButton), 5);
    final JPanel north = util.getDefaultPanel(new BorderLayout());
    north.add(date, BorderLayout.WEST);
    north.add(east, BorderLayout.EAST);
    super.getContentPane().add(north, BorderLayout.NORTH);
    super.getContentPane().add(okButton, BorderLayout.SOUTH);
    this.disableAll();
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
    
    this.okButton.addActionListener(e -> { 
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
          try {
            if (controller.isPresent(getName(), controller.getDate())) {
              controller.displayException("Il nome inserito e' gia' stato utilizzato");
              TableReservationForm.this.setVisible(true);
            } else {
              IReservation res = new Reservation(getTable(), getName(), 
                  controller.getDate(), getH(), getTel(), getNum(), getMenu());
              controller.add(res, controller.getDate());
            }
          } catch (NullPointerException e1) {
            controller.displayException("Riempire la form!");
            TableReservationForm.this.setVisible(true);
          } catch (NumberFormatException e2) {
            controller.displayException("Riempire la form con dei numeri!");
            TableReservationForm.this.setVisible(true);
          }
        }      
      });
  }
  
  private boolean isBeenModified() {
    return this.modified;
  }
}
