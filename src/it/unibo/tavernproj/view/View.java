package it.unibo.tavernproj.view;

import it.unibo.tavernproj.calendar.Calendar;
import it.unibo.tavernproj.calendar.CalendarController;
import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.FormController;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.controller.IFormController;
import it.unibo.tavernproj.disegno.DrawButton;
import it.unibo.tavernproj.disegno.DrawCancel;
import it.unibo.tavernproj.disegno.DrawPosition;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Reservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *  @author Eleonora Guidi
 *
 */

//per adattare l'immagine allo sfondo! http://www.hwupgrade.it/forum/archive/index.php/t-2060553.html

public class View extends JFrame implements IView{

  private static final long serialVersionUID = 1L;
  private final IconBuilder build = new IconBuilder();
  private final JButton buttonNew = new JButton("Nuova Prenotazione"); 
  private final JButton cancelTable = new JButton("Cancella Tavolo");
  private final JButton drawTable = new JButton("Disegna Tavolo ");
  private JPanel tablesButtons = build.buildPanel(new FlowLayout());
  private IController controller;  

  public View() {
    super();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    this.setHandlers();
    this.setResizable(true); 
    this.setVisible(true);
  }


  private void buildLayout() {
    final JLabel map = build.buildMap("res" + System.getProperty("file.separator") + "map.png");
    final JPanel dx = build.buildPanel(new BorderLayout());
    final JPanel pNew = build.buildPanel(new GridBagLayout());
    final JLabel logo = build.buildLogo("res" + System.getProperty("file.separator") + "logo.jpg");

    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(10, 10, 20, 10);
    gap.fill = GridBagConstraints.HORIZONTAL;
    buttonNew.setSize(pNew.getWidth(), pNew.getHeight() * 1 / 10);
    pNew.add(buttonNew, gap);
    //gap.gridy++;

    dx.add(pNew, BorderLayout.CENTER);
    dx.add(logo, BorderLayout.NORTH);

    final JPanel north = build.buildPanel(new GridBagLayout());
    final JLabel date = build.dateLabel();
    north.add(date, gap);

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

    final DrawButton dDrawTable = new DrawButton(this.drawTable,map,new DrawPosition(map)) ;
    dDrawTable.setting();
    final DrawButton dCancelTable = new DrawButton(this.cancelTable,map,new DrawCancel(map)) ;
    dCancelTable.setting();

    
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
        CalendarController ctrl = new CalendarController();
        ctrl.addView(calendar);
        while (!calendar.getPickedDate().equals("Error") && !calendar.isRight()) {
          //FARE una finestra al posto del messaggio stampato
          System.out.println("Selezionare una data utile");
          calendar = new Calendar(frame);
          ctrl.addView(calendar);
        }

         //GESTIRE DIVERSAMENTE
        if (!calendar.getPickedDate().equals("Error")) {

          NewReservationForm form = new NewReservationForm(calendar.getPickedDate());
          final IFormController fc = new FormController();
          fc.addView(form);

          fc.setDate(calendar.getPickedDate());
          form.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(final ComponentEvent ce) {
              try {
                fc.save(form.getTable(), form.getName(), form.getH(), form.getTel(), form.getNum(), form.getMenu());
                controller.addTable(form.getTable(), fc.getDate());
              } catch (NullPointerException e) {
                System.out.print("Riempire la form!");
                form.setVisible(true);
              } catch (NumberFormatException e1) {
                System.out.print("Riempire la form con dei numeri!");
                form.setVisible(true);
              }
            }
          });

          //fare un metodo nella form che tira fuori una finestra se la voglio chiudere senza salvare!!

        }
      }
    });
  }

  @Override
  public void attachViewObserver(final IController listener) {
    this.controller = listener;
  }

  @Override
  public void addTable(String number, String date) {
    /* System.getProperty("user.home")+System.getProperty("file.separator")+
     */

    final JButton b = build.buildButton(number + "s.png");
    b.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0) {
        /* ATTACCARE LA PRENOTAZIONE
         * 
         * prenderla dalla mappa del modello in cui c'è la data giusta e il numero del tavolo
         * fare una form a parte ReservaionForm che sia modificabile!
         * 
         */

        final IReservation res;

          try {
            //res = View.this.controller.getReservation(Integer.parseInt(number), date);

            res = new Reservation("1", "lino", "1", "pino", "1", 2, Optional.of("ciccia"));

            final TableReservationForm form = new TableReservationForm(date, res);
            final IFormController fc = new FormController();
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
          } catch (NumberFormatException e1) {
            System.out.print("Prenotazione non disponibile!");
          }
      }
    });
    tablesButtons.add(b);
    View.this.validate();
  }

  public static void main(final String[] argv) {
    final IController c = new Controller();
    final View v = new View();
    c.addView(v);
  }
}
