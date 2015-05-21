package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.view.GUIutilities;
import it.unibo.tavernproj.view.IGUIutilities;
import it.unibo.tavernproj.view.calendar.Calendar;
import it.unibo.tavernproj.view.calendar.ICalendar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
public class Chooser extends BasicFrame{

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
  private final transient IController controller;
  private boolean choosedByDate;
  private int table;  
  private String date;   
  
  /**
   * build a new chooser view
   * 
   * @param controller
   *      the controller to use.
   */
  public Chooser(final IController controller) {
    super();
    this.controller = controller;   
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
    dateButton.addActionListener(e -> {
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

    personButton.addActionListener(e -> {
        dateButton.setEnabled(false);
        res.add(util.loadReservations());
        enableNameDate();
      });
  
    ok.addActionListener(e -> {
        Chooser.this.setVisible(false);
        if (choosedByDate) {          
          try {
            table = Integer.parseInt(tab.getText());
            controller.remove(table, date);
          } catch (NumberFormatException e1) {
            controller.displayException("Il numero del tavolo è sbagliato");
            Chooser.this.setVisible(true);
          } catch (IllegalArgumentException e2) {
            controller.displayException("Il numero e' sbagliato");
            Chooser.this.setVisible(true);
          }
        } else {
          try {
            this.checkDate();
            date = dat.getText();           
            table = controller.getReservation(date, name.getText());  
            controller.remove(table, date);
          } catch (IllegalArgumentException e1) {
            controller.displayException("Nessuna prenotazione disponibile con quel nome e data");
            Chooser.this.setVisible(true);
          } catch (ParseException e2) {
            controller.displayException("La data inserita e' errata");
            Chooser.this.setVisible(true);
          }
        } 
      });
  } 
  
  private void checkDate() throws ParseException {
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
}
