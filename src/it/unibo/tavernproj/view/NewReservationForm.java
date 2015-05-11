package it.unibo.tavernproj.view;

import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 * modified by @author Giulia Lucchi
 *
 */

public class NewReservationForm extends ReservationForm {

  private static final long serialVersionUID = 1L;
  private final IUtilities util = new Utilities();
  private final JPanel res = util.getDefaultPanel(new GridBagLayout());
  private final Map<Integer, IReservation> map;
  private JLabel date;  

  /**
   * It builds a new Reservation Form whenever we need to add a new Reservation.
   * 
   * @param date
   *      the reservation date.
   * @param map
   *      the map containing all the tables and reservations
   */
  public NewReservationForm(final String date, final Map<Integer, IReservation> map) {
    super();
    this.date = new JLabel(date);
    this.map = map;
    loadReservation();
    buildLayout();
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
    super.getContentPane().add(north, BorderLayout.NORTH);
  }

}
