package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.disegno.DrawMap;
import it.unibo.tavernproj.model.disegno.IPair;
import it.unibo.tavernproj.model.disegno.Pair;
import it.unibo.tavernproj.view.disegno.DrawPosition;
import it.unibo.tavernproj.view.form.DeleteTableForm;
import it.unibo.tavernproj.view.utilities.GUIutilities;
import it.unibo.tavernproj.view.utilities.IGUIutilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
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

/*
 * Si potrebbero aggiungere altri due pulsanti:
 *    - viisualizza prenotazioni (in cui stampa tutte le prenotazioni presenti ome nel chooser)
 *    - modifica prenotazione (in cui apre il calendario e una TableReservationForm facendo 
 *    modificare una prenotazione anche se non è del giorno corrente)
 * 
 */

public class View extends JFrame implements IView{

  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities();
  private final JButton cancelAll = util.getDefaultButton("Cancella Tutto", 12);
  private final JButton cancelTable = util.getDefaultButton("Cancella Tavolo", 12);
  private final JPanel tablesButtons = util.getDefaultPanel(new FlowLayout());  
  private final JLabel map = util.getDefaultMap("map.png");
  private final Map<Integer, IPair<Integer, Integer>> draw = DrawMap.getMap(); 
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
    final JPanel east = new ButtonPanel();   
    try {      
      east.add(util.getDefaultLogo("logo.jpg"), BorderLayout.NORTH);  
    } catch (IOException e) {
      controller.displayException("Le risorse non sono al momento raggiungibili");
    }

    final GridBagConstraints gap = new GridBagConstraints();
    final JPanel datePanel = util.getDefaultPanel(new GridBagLayout());    
    datePanel.add(util.getDateLabel(), gap);    

    final JLabel label = new JLabel("Clicca sulla mappa per disegnare i tavoli");

    if (!draw.isEmpty()) {
      this.setButtons(false);
    } else {
      this.setButtons(true);
    }

    util.add(label);
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

    final JPanel main = util.getDefaultPanel(new BorderLayout(5, 5));
    main.add(center, BorderLayout.CENTER);
    main.add(east, BorderLayout.EAST);
    this.getContentPane().add(main);
  }

  private void setHandlers() {
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent event) {
        quitHandler();
      }
    });

    map.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(final MouseEvent e1) {
          if (tablesButtons.getComponents().length <= pos.size()) {
            controller.displayException("Non ci sono altri tavoli prenotati.");
          } else {
            pos.paint(map.getGraphics(), e1.getX(), e1.getY());
            cancelAll.setEnabled(true);
            cancelTable.setEnabled(true);
          }
        }

    });
  
    this.cancelTable.addActionListener(e-> {
        pos.cancel(map.getGraphics());
        if (draw.isEmpty()) {
          this.setButtons(false);
        }
      });

    this.cancelAll.addActionListener(e-> {
        pos.cancelAll(map.getGraphics());
        this.setButtons(false);
      });
  }
  
  @Override
  public void addDraw(final IPair<Integer, Integer> pt) {
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
        try { 
          controller.setDate(util.getCurrentDate());
          new DeleteTableForm(controller.getReservation(table, util.getCurrentDate()));
        } catch (NumberFormatException e1) {
          controller.displayException("Prenotazione non disponibile!");
        }      
      });
    tablesButtons.add(button);
    mapButtons.setVisible(true);
    View.this.validate();
  }

  @Override
  public void removeTable(final Integer table) {
    for (final Component c: tablesButtons.getComponents()) {
      if (c.getName().equals(table.toString())) {
        tablesButtons.remove(c);
        tablesButtons.repaint();
      }
      if (tablesButtons.getComponents().length == 0) {
        pos.cancelAll(map.getGraphics());
        mapButtons.setVisible(false);
      }
    }
  }

  @Override
  public void commandFailed(final String message) {
    JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setButtons(final boolean bool) {
    cancelTable.setEnabled(bool);
    cancelAll.setEnabled(bool);

  }
}
