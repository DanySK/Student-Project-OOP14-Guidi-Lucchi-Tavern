package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.disegno.DrawButton;
import it.unibo.tavernproj.disegno.DrawMap;
import it.unibo.tavernproj.disegno.DrawPosition;
import it.unibo.tavernproj.disegno.Pair;
import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *  @author Eleonora Guidi
 *  @author Giulia Lucchi
 *
 */

//per adattare l'immagine allo sfondo! http://www.hwupgrade.it/forum/archive/index.php/t-2060553.html

public class View extends JFrame implements IView{

  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities();
  private final JButton buttonNew = util.getDefaultButton("Nuova Prenotazione");
  private final JButton buttonDelete = util.getDefaultButton("Elimina Prenotazione");
  private final JButton cancelAll = util.getDefaultButton("Cancella Tutto", 12);
  private final JButton cancelTable = util.getDefaultButton("Cancella Tavolo", 12);
  private final JButton drawTable = util.getDefaultButton("Disegna Tavolo", 12);
  private final JPanel tablesButtons = util.getDefaultPanel(new FlowLayout());  
  private final JLabel date = util.getDateLabel(); 
  private final JLabel map = util.getDefaultMap("map.png");
  private final Map<Integer, Pair<Integer, Integer>> draw = DrawMap.getMap(); 
  private JPanel mapButtons;
  private transient IController controller;  
  
  private final DrawPosition pos = new DrawPosition(map);

  /**
   * Builds the main view
   */
  public View() {
    super();
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setLocationByPlatform(true);    
    this.setSize(util.getDefaultWidth(), util.getDefaultHeight());
    this.setResizable(false);    
    this.buildLayout();
    this.setHandlers();    
    this.setVisible(true);
  }
  
  private void buildLayout() {
    final JPanel reservPanel = util.buildGridPanel(util.getList(buttonNew, buttonDelete), 10);
    final JPanel east = util.getDefaultPanel(new BorderLayout());
    east.add(reservPanel, BorderLayout.CENTER);    
    try {      
      east.add(util.getDefaultLogo("logo.jpg"), BorderLayout.NORTH);  
    } catch (IOException e) {
      controller.displayException("Le risorse non sono al momento raggiungibili");
    }

    final GridBagConstraints gap = new GridBagConstraints();
    final JPanel datePanel = util.getDefaultPanel(new GridBagLayout());    
    datePanel.add(date, gap);    
    
    util.add(drawTable);
    util.add(cancelTable);
    util.add(cancelAll);
    mapButtons = util.buildOrizzontalGridPanel(util.getList(), 10);
    mapButtons.setVisible(false);
    
    final JPanel north = util.getDefaultPanel(new BorderLayout());
    north.add(datePanel, BorderLayout.NORTH);
    north.add(mapButtons, BorderLayout.CENTER);
    
    final JPanel center = util.getDefaultPanel(new BorderLayout());
    center.add(map, BorderLayout.CENTER);
    center.add(tablesButtons, BorderLayout.SOUTH);
    center.add(north, BorderLayout.NORTH);
       
    final DrawButton bDrawTable = new DrawButton(this.drawTable, map);
    bDrawTable.setting();
    final DrawButton bCancelTable = new DrawButton(this.cancelTable, map);
    bCancelTable.setting();
    final DrawButton bCancelAll = new DrawButton(this.cancelAll, map);
    bCancelAll.setting();
    
    final JPanel main = util.getDefaultPanel(new BorderLayout(5, 5));
    main.add(center, BorderLayout.CENTER);
    main.add(east, BorderLayout.EAST);
    this.getContentPane().add(main);
  }
  
  private void setHandlers() {
    this.buttonNew.addActionListener(e -> {      
        final JFrame frame = new JFrame("Calendar");
        Calendar calendar = new Calendar(frame);
        while (!calendar.getPickedDate().equals("Error") && !calendar.isRight()) {
          controller.displayException("Selezionare una data utile");
          calendar = new Calendar(frame);
        }
        if (!calendar.getPickedDate().equals("Error")) {
          controller.setDate(calendar.getPickedDate());
          new NewReservationForm(calendar.getPickedDate(), 
              controller.getReservation(calendar.getPickedDate()), controller);
        }
      });    
  
    this.buttonDelete.addActionListener(e -> {      
        new Chooser(controller);
      });
    
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent event) {
        quitHandler();
      }
    });
    
    this.drawTable.addActionListener(e-> {
        map.addMouseListener(pos);
        cancelAll.setEnabled(true);
        cancelTable.setEnabled(true);
        /* fare un controllo per disabilitare il bottone se si sono già 
         * aggiunti tanti ttavoli in mappa quanti sono prenotati
         * 
         * quindi con tablesButtons.getComponentCount o qualcosa di simile
         */
      });

    this.cancelTable.addActionListener(e-> {
        pos.cancel(map.getGraphics());
        if (draw.isEmpty()) {
          cancelTable.setEnabled(false); 
          cancelAll.setEnabled(false);
        }
      });

    this.cancelAll.addActionListener(e-> {
        pos.cancelAll(map.getGraphics());
        cancelAll.setEnabled(false); 
        cancelTable.setEnabled(false);
      });
  }
  
  @Override
  public void addDraw(Pair<Integer, Integer> pt, int index) {
    pos.paint(map.getGraphics(),pt.getX(),pt.getY());
    this.validate();
  }   
  
  private void quitHandler() {
    final int n = JOptionPane.showConfirmDialog(this, "Vuoi davvero uscire?", 
        "Chiusura...", JOptionPane.YES_NO_OPTION);
    if (n == JOptionPane.YES_OPTION) {
      controller.commandQuit();
    }
  }

  @Override
  public void attachViewObserver(final IController listener) {
    this.controller = listener;
  }

  @Override
  public void addTable(final Integer table) {
    JButton button = util.getDefaultButton(table.toString());
    try {
      button = util.getPicButton(table + "s.png");
    } catch (IOException e3) {
      controller.displayException("Le risorse non sono al momento raggiungibili");
    }
    button.setName(table.toString());
    button.addActionListener(e -> {
        IReservation res;
        try {
          res = controller.getReservation(table, date.getText());    
          controller.setDate(date.getText());
          new TableReservationForm(date.getText(), res, controller);         
        } catch (NumberFormatException e1) {
          controller.displayException("Prenotazione non disponibile!");
        }      
      });
    tablesButtons.add(button);
    mapButtons.setVisible(true);
    /* disabilitare i bottoni se non c'è niente nella mappa
     * cancelAll.setEnabled(false);
      cancelTable.setEnabled(false);
     * */
    View.this.validate();

    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    controller.LoadDisegno();  


  }
  
  @Override
  public void removeTable(final Integer table) {
    for (final Component c: tablesButtons.getComponents()) {
      if (c.getName().equals(table.toString())) {
        tablesButtons.remove(c);
        tablesButtons.repaint();
        /*controllare disabilitzione bottoni sopra alla mappa*/
      }
    }
  }
  
  @Override
  public void commandFailed(final String message) {
    JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);
  }

}
