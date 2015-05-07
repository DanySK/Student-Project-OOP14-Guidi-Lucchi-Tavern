package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.FormController;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.controller.IFormController;
import it.unibo.tavernproj.disegno.DrawButton;
import it.unibo.tavernproj.disegno.DrawPosition;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 *  @author Eleonora Guidi
 *
 */

/*  aggiungere: pulsante per consularte le prenotazioni in base al nome dei clienti
 *              pulsante per consultare le prenotazioni scegliendo la data dal calendario
 *          
 *              in entrambi gli elenchi aggungere un pulsante per eliminare le prenotazioni
 *              
 *              (oppure fare un pulsante unico elimina prenotazione e da lì, inserendo il nome del cliente
 *              fare scegliere qual'è quello giusto da cancellare e rimuovere dal sistema.)
 *
 */

//per adattare l'immagine allo sfondo! http://www.hwupgrade.it/forum/archive/index.php/t-2060553.html

public class View extends JFrame implements IView{

  private static final long serialVersionUID = 1L;
  private final Utilities build = new Utilities();
  private final JButton buttonNew = new JButton("Nuova Prenotazione"); 
  private final JButton buttonDelete = new JButton("Elimina Prenotazione");
  private final JButton cancelAll = new JButton("Cancella Tutto");
  private final JButton cancelTable = new JButton("Cancella Tavolo");
  private final JButton drawTable = new JButton("Disegna Tavolo ");
  private JPanel tablesButtons = build.buildPanel(new FlowLayout());
  private final JLabel date = build.dateLabel();
  private IController controller;  

  public View() {
    super();
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setLocationByPlatform(true);

  /* Set window dimension to the screen */
  /* Se si salva sempre sullo stesso pc funziona anche usando le dimensioni dello schermo,
   * se invece si apre il programma su un altro computer e le informazioni di salvataggio
   * non sono lì non va, serve settare le dimensioni fisse o fare apposite funzioni per
   * ricalcolare le posizioni.
   */ 
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth() * 4 / 5;
    final int sh = (int) screen.getHeight() * 3 / 4;

    this.setSize(sw, sh);
    this.setResizable(false);
    
    buildLayout();    

  /*Fare metodi a parte per cambiare lo stile dei bottoni 
   *e anche degli altri componenti che si rovano in icon builder
   *(tipo il pannello si può rendere bianco di là)*/
    buttonNew.setFont(new Font("Arial", Font.BOLD, 18));
    buttonNew.setBackground(Color.white);
    
    buttonDelete.setFont(new Font("Arial", Font.BOLD, 18));
    buttonDelete.setBackground(Color.white);

    this.setHandlers();
    this.setResizable(true); 
    this.setVisible(true);
    
  }


  private void buildLayout() {
    final JLabel map = build.buildMap("res" + System.getProperty("file.separator") + "map.png");
    final JPanel dx = build.buildPanel(new BorderLayout());
    final JPanel pNew = build.buildPanel(new GridBagLayout());
    final JLabel logo = build.buildLogo("res" + System.getProperty("file.separator") + "logo.jpg");
    
   
  // controller.setLabel(map);

    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(10, 10, 20, 10);
    gap.fill = GridBagConstraints.HORIZONTAL;
    buttonNew.setSize(pNew.getWidth(), pNew.getHeight() * 1 / 10);
    pNew.add(buttonNew, gap);
    gap.gridy++;
    pNew.add(buttonDelete, gap);

    dx.add(pNew, BorderLayout.CENTER);
    dx.add(logo, BorderLayout.NORTH);

    final JPanel north = build.buildPanel(new GridBagLayout());    
    north.add(date, gap);
    
    /*if (!Controller.getController().getRes(date.getText()).keySet().isEmpty()){
      for (Integer i: Controller.getController().getRes(date.getText()).keySet()){
        this.addTable(i, date.getText());
      }    
    }
    
    System.out.print("non c'è la prenotazione per quel giorno");*/

    final JPanel center = build.buildPanel(new BorderLayout());
    center.add(map, BorderLayout.CENTER);
    center.add(tablesButtons, BorderLayout.SOUTH);
    center.add(north, BorderLayout.NORTH);
    //aggiunta pannello
    JPanel panel = new JPanel();

    panel.setBackground(Color.white);

    pNew.add(panel);
    
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
    
    final DrawPosition cancel = new DrawPosition(map);
     
    this.drawTable.addActionListener(e->{
         map.addMouseListener(cancel);
    });
   
    this.cancelTable.addActionListener(e->{
        cancel.cancel(map.getGraphics());
    });
    
    this.cancelAll.addActionListener(e->{
        cancel.cancelAll(map.getGraphics());
    });
   


    
    final JPanel main = build.buildPanel(new BorderLayout(5, 5));
    main.add(center, BorderLayout.CENTER);
    main.add(dx, BorderLayout.EAST);

    this.getContentPane().add(main);

  }
  
  private void setHandlers() {

    this.buttonNew.addActionListener(new ActionListener(){

      public void actionPerformed(ActionEvent arg0) {
        JFrame frame = new JFrame("Calendar");
        Calendar calendar = new Calendar(frame);
        while (!calendar.getPickedDate().equals("Error") && !calendar.isRight()) {
          //FARE una finestra al posto del messaggio stampato
          System.out.println("Selezionare una data utile");
          calendar = new Calendar(frame);
        }
        
       

         //GESTIRE DIVERSAMENTE
        if (!calendar.getPickedDate().equals("Error")) {

          NewReservationForm form = new NewReservationForm(calendar.getPickedDate());
          final IFormController fc = FormController.getController();
          fc.addView(form);

          fc.setDate(calendar.getPickedDate());
          //fc.setModel(controller.getModel());
          form.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent ce) {
              try {
                controller.add(form.getTable(), form.getName(), fc.getDate(), form.getH(),
                    form.getTel(), form.getNum(), form.getMenu());
                //fc.save();
                if (fc.getDate().equals(View.this.date.getText())) {
                  controller.addTable(form.getTable(), fc.getDate());
                }
              } catch (NullPointerException e) {
                System.out.print("Riempire la form!");
                form.setVisible(true);
              } catch (NumberFormatException e1) {
                System.out.print("Riempire la form con dei numeri utili!");
                form.setVisible(true);
              } catch (IllegalArgumentException e2) {
                System.out.print("Il tavolo inserito e' gia' stato utilizzato");
                form.setVisible(true);
              }
            }
          });
          
          
          //fare un metodo nella form che tira fuori una finestra se la voglio chiudere senza salvare!!

        }
      }
    });
    
  
    this.buttonDelete.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent arg0) {
        Chooser c = new Chooser();
        
      }
      
    });
    
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent e) {
        quitHandler();
      }
    });
  }
  
  private void quitHandler() {
    final int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Quitting..", JOptionPane.YES_NO_OPTION);
    if (n == JOptionPane.YES_OPTION) {
      controller.commandQuit();
    }
  }

  @Override
  public void attachViewObserver(final IController listener) {
    this.controller = listener;
  }

  @Override
  public void addTable(Integer table, String date) {
    /* System.getProperty("user.home")+System.getProperty("file.separator")+
     */
    
    final JButton b = build.buildButton(table + "s.png");
    b.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0) {
        /* ATTACCARE LA PRENOTAZIONE
         * 
         * prenderla dalla mappa del modello in cui c'è la data giusta e il numero del tavolo
         * fare una form a parte ReservaionForm che sia modificabile!
         * 
         */
        


        IReservation res;

          try { 
            
            
            
            res = controller.getReservation(table, date);
            //res = new Reservation("1", "lino", "1", "pino", "1", 2, Optional.of("ciccia"));
            
            final TableReservationForm form = new TableReservationForm(date, res);
            //form.setTable(Integer.parseInt(b.getName()));
            final IFormController fc = FormController.getController();
            fc.addView(form);
            
            //fc.setModel(View.this.controller.getModel());
            fc.setDate(date);
            form.addComponentListener(new ComponentAdapter() {
              @Override
              public void componentHidden(final ComponentEvent ce) {
                if (form.isBeenModified()) {
                  try {
                    //fc.delete(table, date);
                    //PER ORA IL TAVOLO NON è MODIFICABILE PERCHè DAVA PROBLEMI!
                    
                    controller.add(table, form.getName(), fc.getDate(), form.getH(), form.getTel(), form.getNum(), form.getMenu());
                    b.setIcon(build.getButtonIcon(form.getTable() + "s.png"));
                  } catch (NullPointerException e) {
                    System.out.print("Riempire la form!");
                    form.setVisible(true);
                  } catch (NumberFormatException e1) {
                    System.out.print("Riempire la form con dei numeri!");
                    form.setVisible(true);
                  } catch (IllegalArgumentException e2) {
                    System.out.print("il numero del tavolo è sbagliato!");
                    form.setVisible(true);
                  }
                }
              }
            });
            
          } catch (NumberFormatException e1) {/*
            //caricare il modello esterno: SISTEMARE PERCHè SE USO FC SALVA SU UN ALTRO MODELLO
            try{
              res = View.this.controller.getExternalReservation(table, date);
              
              final TableReservationForm form = new TableReservationForm(date, res);
              final IFormController fc = FormController.getController();
              fc.addView(form);
              fc.setDate(date);
              form.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(final ComponentEvent ce) {
                  if (form.isBeenModified()) {
                    try {
                      fc.save(form.getTable(), form.getName(), form.getH(), form.getTel(), form.getNum(), form.getMenu());
                      b.setIcon(build.getButtonIcon(form.getTable() + "s.png"));
                    } catch (NullPointerException e) {
                      System.out.print("Riempire la form!");
                      form.setVisible(true);
                    } catch (NumberFormatException e1) {
                      System.out.print("Riempire la form con dei numeri!");
                      form.setVisible(true);
                    } catch (IllegalArgumentException e2) {
                      System.out.print("il numero del tavolo è sbagliato!");
                      form.setVisible(true);
                    }
                  }
                }
              });
              
            }catch (NumberFormatException e2){*/
              System.out.print("Prenotazione non disponibile!");
            }              
          //}
      }
    });
    tablesButtons.add(b);
    View.this.validate();
  }

}
