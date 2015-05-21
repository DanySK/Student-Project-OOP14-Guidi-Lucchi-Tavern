package it.unibo.tavernproj.view;

import java.awt.BorderLayout;
import java.awt.Color;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.view.calendar.Calendar;
import it.unibo.tavernproj.view.calendar.ICalendar;
import it.unibo.tavernproj.view.form.Chooser;
import it.unibo.tavernproj.view.form.NewReservationForm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * @author Eleonora Guidi
 *
 */
public class ButtonPanel extends JPanel{
  
  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities();
  private final transient IController controller = Controller.getController();
  private final JButton buttonNew = util.getDefaultButton("Nuova Prenotazione");
  private final JButton buttonDelete = util.getDefaultButton("Elimina Prenotazione");
 
  /**
   * builds a new button panel for the east side of the view.
   */
  public ButtonPanel() {
    super();
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    setHandlers();
    util.add(buttonNew);
    util.add(buttonDelete);
    this.add(util.buildGridPanel(util.getList(), 10), BorderLayout.CENTER);
  }
  
  private void setHandlers() {
    this.buttonNew.addActionListener(e -> {      
        final JFrame frame = new JFrame("Calendar");
        ICalendar calendar = new Calendar(frame);
        try {
          while (!calendar.isRight()) {
            controller.displayException("Selezionare una data utile");
            calendar = new Calendar(frame);
          }
          controller.setDate(calendar.getPickedDate());
        } catch (NumberFormatException e1) {
          controller.displayException("Selezionare una data utile");
          calendar = new Calendar(frame);
        }          
        new NewReservationForm();
      });    
  
    this.buttonDelete.addActionListener(e -> {      
        new Chooser();
      });
  }
  

}
