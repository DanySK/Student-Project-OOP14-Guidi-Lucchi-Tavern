package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Reservation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 * modified by @author Giulia Lucchi
 *
 */

public class NewReservationForm extends ReservationForm{

  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities();
  private final JPanel res = util.getDefaultPanel(new GridBagLayout());
  private final JButton okButton = util.getDefaultButton("OK", 12);
  private final transient Map<Integer, IReservation> map;
  private final JLabel date;  
  private final IController controller;

  /**
   * It builds a new Reservation Form whenever we need to add a new Reservation.
   * 
   * @param date
   *      the reservation date.
   * @param map
   *      the map containing all the tables and reservations
   */
  public NewReservationForm(final String date, IController controller) {
    super();
    this.date = new JLabel(date);
    this.map = controller.getReservation(date);
    this.controller = controller;
    this.loadReservation();
    this.buildLayout();
    this.setHandlers();
    this.setVisible(true);
  }
  
  private void loadReservation() {
    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(5, 5, 5, 5);
    gap.fill = GridBagConstraints.HORIZONTAL;
    for (final Integer i: map.keySet()) {
      res.add(new JLabel(map.get(i).toString(date.getText())), gap);
      gap.gridy++;
    }
    this.validate();
  }

  private void buildLayout() {
    final JPanel north = util.getDefaultPanel(new BorderLayout());
    north.add(date, BorderLayout.NORTH);
    north.add(res, BorderLayout.CENTER);
    super.getContentPane().add(okButton, BorderLayout.SOUTH);
    super.getContentPane().add(north, BorderLayout.NORTH);
  }
  
  private void setHandlers() {
    this.okButton.addActionListener(e -> { 
        NewReservationForm.this.setVisible(false); 
        try {
          if (controller.isPresent(getName(), controller.getDate())) {
            controller.displayException("Il nome inserito e' gia' stato utilizzato");
            NewReservationForm.this.setVisible(true);
          } else {
            IReservation res = new Reservation(getTable(), getName(), 
                controller.getDate(), getH(), getTel(), getNum(), getMenu());
            controller.add(res, controller.getDate());
            if (controller.getDate().equals(util.getCurrentDate())) {
              controller.addTable(getTable());
            }
          }
        } catch (NullPointerException e1) {
          controller.displayException("Riempire la form!");
          NewReservationForm.this.setVisible(true);
        } catch (NumberFormatException e2) {
          controller.displayException("Riempire la form con dei numeri utili!");
          NewReservationForm.this.setVisible(true);
        } catch (IllegalArgumentException e3) {
          controller.displayException("Il tavolo inserito e' gia' stato utilizzato");
          NewReservationForm.this.setVisible(true);
        }
      });
  } 
}
