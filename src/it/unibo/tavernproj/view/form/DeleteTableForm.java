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
public class DeleteTableForm extends ModifiedTableForm{
  
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
  public DeleteTableForm(final IReservation res) {
    super(res);
    setHandlers();
    super.getContentPane().add(util.buildGridPanel(
        util.getList(modifyButton, deleteButton), 5), BorderLayout.EAST);
    this.disableAll();
  }
  
  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent event) {
        if (DeleteTableForm.this.modified) {
          DeleteTableForm.this.isBeenModified();
        }
        DeleteTableForm.this.setVisible(false);
      }      
    };
  }

  private void setHandlers() {
    this.modifyButton.addActionListener(e -> {
        DeleteTableForm.this.enableAll();
        DeleteTableForm.this.modified = true;
        deleteButton.setEnabled(false);
      });

    this.deleteButton.addActionListener(e -> {
        controller.remove(getTable(), controller.getDate());
        DeleteTableForm.this.setVisible(false);
      });
  }
  
  private void isBeenModified() {
    super.modified();
  }
}
