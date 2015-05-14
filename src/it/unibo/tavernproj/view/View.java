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
  private final IGUIutilities util = new GUIutilities();
  private final JButton buttonNew = util.getDefaultButton("Nuova Prenotazione");
  private final JButton buttonDelete = util.getDefaultButton("Elimina Prenotazione");
  private final JButton cancelAll = new JButton("Cancella Tutto");
  private final JButton cancelTable = new JButton("Cancella Tavolo");
  private final JButton drawTable = new JButton("Disegna Tavolo ");
  private final JPanel tablesButtons = util.getDefaultPanel(new FlowLayout());
  private final JLabel date = util.getDateLabel(); 
  private final JLabel map = util.getDefaultMap("map.png");
  private final Map<Integer, Pair<Integer, Integer>> draw = DrawMap.getMap();  
  private IController controller;  
  
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
    
    //??
    final GridBagConstraints gap = new GridBagConstraints();
    final JPanel north = util.getDefaultPanel(new GridBagLayout());    
    north.add(date, gap);
//
    
    final JPanel center = util.getDefaultPanel(new BorderLayout());
    center.add(map, BorderLayout.CENTER);
    center.add(tablesButtons, BorderLayout.SOUTH);
    center.add(north, BorderLayout.NORTH);
    
    //aggiunta pannello
    JPanel panel = new JPanel();

    panel.setBackground(Color.white);

    reservPanel.add(panel);
    
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(drawTable);
    panel.add(cancelTable);
    panel.add(cancelAll);
    
    final DrawButton bDrawTable = new DrawButton(this.drawTable,map);
    bDrawTable.setting();
    final DrawButton bCancelTable = new DrawButton(this.cancelTable,map);
    bCancelTable.setting();
    final DrawButton bCancelAll = new DrawButton(this.cancelAll,map);
    bCancelAll.setting();
    
    
     
    this.drawTable.addActionListener(e->{
      map.addMouseListener(pos);
      cancelAll.setEnabled(true);
      cancelTable.setEnabled(true);
     });
    
     this.cancelTable.addActionListener(e->{
           pos.cancel(map.getGraphics());
           if(draw.isEmpty()){
            cancelTable.setEnabled(false); 
            cancelAll.setEnabled(false);
           }
     });
     
     this.cancelAll.addActionListener(e->{
       
         pos.cancelAll(map.getGraphics());
         cancelAll.setEnabled(false); 
         cancelTable.setEnabled(false);
     });
   //
    
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
          final NewReservationForm form = new NewReservationForm(calendar.getPickedDate(), 
              controller.getReservation(calendar.getPickedDate()));
          controller.setDate(calendar.getPickedDate());
          form.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent ce) {
              try {
                controller.add(form.getTable(), form.getName(), controller.getDate(), form.getH(),
                    form.getTel(), form.getNum(), form.getMenu());
                if (controller.getDate().equals(View.this.date.getText())) {
                  controller.addTable(form.getTable());
                }
              } catch (NullPointerException e) {
                controller.displayException("Riempire la form!");
                form.setVisible(true);
              } catch (NumberFormatException e1) {
                controller.displayException("Riempire la form con dei numeri utili!");
                form.setVisible(true);
              } catch (IllegalArgumentException e2) {
                controller.displayException("Il tavolo inserito e' gia' stato utilizzato");
                form.setVisible(true);
              }
            }          
          });
        }
      });    
  
    this.buttonDelete.addActionListener(e -> {      
        final Chooser chooser = new Chooser(controller);
        chooser.addComponentListener(new ComponentAdapter(){          
          @Override
          public void componentHidden(final ComponentEvent ce) {
             //non va!
            if (chooser.isBeenRemoved()) {
              controller.removeTable(chooser.getTable());
            } 
          }
        });            
      });
    
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent event) {
        quitHandler();
      }
    });
  }
  
  @Override
  public void addDraw(Pair<Integer, Integer> p, int index) {
    //final DrawPosition pos = new DrawPosition(map);
    pos.setIndex(index);
    pos.paint(map.getGraphics(),p.getX(),p.getY());
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
    /* System.getProperty("user.home")+System.getProperty("file.separator")+
     */
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
          final TableReservationForm form = new TableReservationForm(date.getText(), res);
          controller.setDate(date.getText());
          form.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent ce) {
              if (form.isBeenDeleted()) {
                //problemi
                controller.remove(form.getTable(), controller.getDate());
                controller.removeTable(form.getTable());
              }
              if (form.isBeenModified()) {
                try {
                  controller.remove(form.getOld().getTable(), controller.getDate());
                } catch (IllegalArgumentException e2) {
                  /* Non segnala nulla perchè viene lanciata quando modifico il 
                   * tavolo più di una volta. In questo caso devo solo intercettare
                   * l'eccezione.
                   */
                }  
                try {
                  controller.add(form.getTable(), form.getName(), controller.getDate(), form.getH(),
                      form.getTel(), form.getNum(), form.getMenu());
                } catch (NullPointerException e) {
                  controller.displayException("Riempire la form!");
                  form.setVisible(true);
                } catch (NumberFormatException e1) {
                  controller.displayException("Riempire la form con dei numeri!");
                  form.setVisible(true);
                }
              }
            }
          });          
        } catch (NumberFormatException e1) {
          controller.displayException("Prenotazione non disponibile!");
        }      
      });
    tablesButtons.add(button);
    /*  SE LO COMMENTO FUNZIONA LA MAPPA DEI TAVOLI MA NON MI CARICA IL BOTTONE SOTTO!
     */
    View.this.validate();
    //controller.LoadDisegno();
  }
  
  @Override
  public void removeTable(final Integer table) {
    for (final Component c: tablesButtons.getComponents()) {
      if (c.getName().equals(table.toString())) {
        tablesButtons.remove(c);
        tablesButtons.repaint();
      }
    }
  }
  
  @Override
  public void commandFailed(final String message) {
    JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);
  }

}
