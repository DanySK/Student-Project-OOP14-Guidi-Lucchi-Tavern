package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.view.calendar.Calendar;
import it.unibo.tavernproj.view.calendar.ICalendar;
import it.unibo.tavernproj.view.utilities.GUIutilities;
import it.unibo.tavernproj.view.utilities.IGUIutilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Eleonora Guidi
 *
 */

public class Chooser extends BasicFrame implements IChooser{

  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities(); 
  private final JPanel res = util.getDefaultPanel(new FlowLayout());  
  private final JButton dateButton = util.getDefaultButton("Scegli per data");
  private final JButton personButton = util.getDefaultButton("Scegli per nome");
  private final JButton ok = util.getDefaultButton("OK", 12);   
  private final JLabel dateLabel = new JLabel("Data (formato gg-mm-aaa): ");
  private final JTextField dat = new JTextField(20);
  private final JLabel nameLabel = new JLabel("Nome: ");
  private final JTextField name = new JTextField(20);
  private final JLabel tableLabel = new JLabel("Tavolo: ");
  private final JTextField tab = new JTextField(20);
  protected final transient IController controller = Controller.getController();
  private boolean choosedByDate;
  private int table;  
  private String date;   
  
  /**
   * build a new chooser view
   * 
   */
  public Chooser() {
    super(); 
    buildLayout();
    setHandlers();
    this.setVisible(true);
  }
  
  private void buildLayout() {    
    this.disableAll();
    util.add(util.getDefaultPanel(new FlowLayout(), tableLabel, tab));
    util.add(util.getDefaultPanel(new FlowLayout(), dateLabel, dat));
    util.add(util.getDefaultPanel(new FlowLayout(), nameLabel, name));
    util.add(ok);
    final JPanel main = util.getDefaultPanel(new BorderLayout());
    main.add(util.getDefaultPanel(new FlowLayout(), dateButton, personButton), BorderLayout.NORTH);
    main.add(res, BorderLayout.CENTER);
    main.add(util.buildGridPanel(util.getList(), 5), BorderLayout.SOUTH);    
    this.getContentPane().add(main);
  }
  
  private void setHandlers() {
    this.dateButton.addActionListener(e -> {
        choosedByDate = true;
        personButton.setEnabled(false);
        final JFrame frame = new JFrame("Calendar");
        ICalendar calendar = new Calendar(frame);
        try {
          while (!calendar.isRight()) {
            controller.displayException("Selezionare una data utile");
            calendar = new Calendar(frame);
          }
          date = calendar.getPickedDate();
        } catch (NumberFormatException e1) {
          controller.displayException("Selezionare una data utile");
          calendar = new Calendar(frame);
        }
        if (controller.getReservation(date).size() == 0) {
          controller.displayException("Nessuna prenotazione per la data selezionata.");
          calendar = new Calendar(frame);
        } else {
          res.add(util.loadReservation(date));
          enableTable();
        }
      });

    this.personButton.addActionListener(e -> {
        dateButton.setEnabled(false);
        res.add(util.loadReservations());
        enableNameDate();
      });
    
    this.ok.addActionListener(this.setOkListener());
  } 

  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent arg0) {
        Chooser.this.setVisible(false);
        if (choosedByDate){          
          try {
            table = Integer.parseInt(tab.getText());
            controller.remove(table, date);
          } catch (NumberFormatException e1) {
            showMessage("Il numero del tavolo è sbagliato");
          } catch (IllegalArgumentException e2) {
            showMessage("Il numero e' sbagliato");
          }
        } else {
          try {
            checkDate();
            date = dat.getText();           
            table = controller.getReservation(date, name.getText());
            controller.remove(table, date);
          } catch (IllegalArgumentException e1) {
            showMessage("Nessuna prenotazione disponibile con quel nome e data");
          } catch (ParseException e2) {
            showMessage("La data inserita e' errata");
          }
        }        
      }      
    };
  }
  
  @Override
  public IReservation getReservation() throws NumberFormatException, IllegalArgumentException, ParseException{
    if (getTable() < 0){
      throw new NumberFormatException();
    }
    return controller.getReservation(this.getTable(), this.getDate());
  }
  
  /**
   * Shows an error message with the string passed as text.
   * 
   * @param srt
   *      the string passed as message.
   */
  protected void showMessage(final String srt) {
    controller.displayException(srt);
    Chooser.this.setVisible(true);
  }
  
  /**
   * Checks if the rightness of the date written in the text field.
   * 
   * @throws ParseException
   *      if the date format is wrong.
   */
  protected void checkDate() throws ParseException {
    final DateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
    sdf.parse(dat.getText());
  }
  
  private void disableAll() {
    dateLabel.setVisible(false);
    dat.setVisible(false);
    tab.setVisible(false);
    tableLabel.setVisible(false);
    name.setVisible(false);
    nameLabel.setVisible(false);    
    ok.setVisible(false); 
  }
  
  private void enableTable() {
    tableLabel.setVisible(true);
    tab.setVisible(true);
    ok.setVisible(true);
  }
  
  private void enableNameDate() {
    dateLabel.setVisible(true);
    dat.setVisible(true);
    nameLabel.setVisible(true);
    name.setVisible(true);
    ok.setVisible(true);
  }

  private int getTable() throws NumberFormatException, IllegalArgumentException, ParseException{
    if (choosedByDate) {
      return Integer.parseInt(tab.getText());
    }
    return controller.getReservation(getDate(), name.getText());
  }

  private String getDate() throws ParseException {
    if (!choosedByDate) {
      checkDate();
      controller.setDate(dat.getText());
      return dat.getText();
    }
    controller.setDate(this.date);
    return this.date;
  }


}
