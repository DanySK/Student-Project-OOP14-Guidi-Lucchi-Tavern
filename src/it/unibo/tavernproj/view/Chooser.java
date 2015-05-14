package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Chooser extends BasicFrame{

  private static final long serialVersionUID = 1L;
  private final IGUIutilities util = new GUIutilities(); 
  private final JButton dateButton = util.getDefaultButton("Scegli per data");
  private final JButton personButton = util.getDefaultButton("Scegli per nome");
  private final JButton ok = util.getDefaultButton("OK", 12); 
  
  private final JLabel dateLabel = new JLabel("Data (formato gg-mm-aaa): ");
  private final JTextField dat = new JTextField(20);
  private final JLabel nameLabel = new JLabel("Nome: ");
  private final JTextField name = new JTextField(20);
  private final JLabel tableLabel = new JLabel("Tavolo: ");
  private final JTextField tab = new JTextField(20);

  private boolean choosedByDate;
  private boolean removed;
  private int table;
   
  private JPanel res = util.getDefaultPanel(new FlowLayout());  
  private String date;  
  private final IController controller;
  
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
    dateLabel.setVisible(false);
    dat.setVisible(false);
    tab.setVisible(false);
    tableLabel.setVisible(false);
    name.setVisible(false);
    nameLabel.setVisible(false);    
    ok.setVisible(false);       
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
        Calendar calendar = new Calendar(frame);
        while (!calendar.getPickedDate().equals("Error") && !calendar.isRight()) {
          controller.displayException("Selezionare una data utile");
          calendar = new Calendar(frame);
        }
        if (!calendar.getPickedDate().equals("Error")) {
          date = calendar.getPickedDate();
          if (controller.getReservation(date).size() == 0) {
            controller.displayException("Nessuna prenotazione per la data selezionata.");
            calendar = new Calendar(frame);
          } else {
            loadReservation(date);
            tableLabel.setVisible(true);
            tab.setVisible(true);
            ok.setVisible(true);
          }
        }
      });

    personButton.addActionListener(e -> {
        dateButton.setEnabled(false);
        this.loadReservations();
        dateLabel.setVisible(true);
        dat.setVisible(true);
        nameLabel.setVisible(true);
        name.setVisible(true);
        ok.setVisible(true);
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
            date = dat.getText();
            table = controller.getReservation(date, name.getText());  
            controller.remove(table, date);
          } catch (IllegalArgumentException e1) {
            controller.displayException("Il nome o la data inseriti sono sbagliati");
            Chooser.this.setVisible(true);
          }
        }        
        /*setto la variabile rimossa solo se è il giorno corrente, 
         * così modifico la view solo se necessario */
        if (date.equals(util.getCurrentDate())) {
          removed = true;  
        }
      });
  }
  
  private void loadReservation(final String date) {
    for (final Integer i: controller.getReservation(date).keySet()) {
      util.add(new JLabel(controller.getReservation(date).get(i).toString()));  
    }
    res.add(util.buildGridPanel(util.getList(), 10));
  }

  private void loadReservations() {
    final Set<String> dates = controller.getDates();
    //LAMBDA??
    for (final String s: dates) {
      for (final Integer i: controller.getReservation(s).keySet()) {
        util.add(new JLabel(controller.getReservation(s).get(i).toString()));  
      }         
    }        
    res.add(util.buildGridPanel(util.getList(), 10));
  }  
  
  public boolean isBeenRemoved() {
    return this.removed;
  }  
  
  public int getTable() {
    return this.table;
  }
}
