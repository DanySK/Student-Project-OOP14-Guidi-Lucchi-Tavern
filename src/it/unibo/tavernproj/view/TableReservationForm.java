package it.unibo.tavernproj.view;

import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableReservationForm extends ReservationForm{
  
  private static final long serialVersionUID = 1L;
  private final JButton modifyButton = new JButton("Modifica");
  private final JButton deleteButton = new JButton("Elimina");
  private final IUtilities utilities = new Utilities();
  private final JLabel date;
  private final IReservation res;
  private boolean modified;
  private boolean deleted;  

  /**
   * It builds a new Table Reservation Form whenever we need to see the reservation
   * from the table button.
   * 
   * @param date
   *      the reservation date.
   * @param res
   *      the reservation.
   */
  public TableReservationForm(final String date, final IReservation res) {
    super();
    this.date = new JLabel(date);
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
    final JPanel east = utilities.buildGridPanel(modifyButton, deleteButton, 5);
    final JPanel north = new JPanel(new BorderLayout());
    north.add(date, BorderLayout.WEST);
    north.add(east, BorderLayout.EAST);
    super.getContentPane().add(north, BorderLayout.NORTH);
    this.disableAll();
  }

  private void setHandlers() {
    this.modifyButton.addActionListener(e -> {
        TableReservationForm.this.enableAll();
        TableReservationForm.this.modified = true;
      });

    this.deleteButton.addActionListener(e -> {
        TableReservationForm.this.deleted = true;
        TableReservationForm.this.setVisible(false);
      });
  }

  public boolean isBeenModified() {
    return this.modified;
  }

  public boolean isBeenDeleted() {
    return this.deleted ;
  }

  public IReservation getOld() {
    return this.res;
  }
}
