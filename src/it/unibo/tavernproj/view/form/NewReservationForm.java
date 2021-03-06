package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Reservation;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.IllegalCharsetNameException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 * modified by @author Giulia Lucchi
 *
 */

//DECORATOR su queste classi per il metodo setOKListener
public class NewReservationForm extends ReservationForm implements INewReservationForm{

  private static final long serialVersionUID = 1L;
  private final JPanel res = util.getDefaultPanel(new GridBagLayout());
  private final JButton okButton = util.getDefaultButton("OK", 12);
  protected final IController controller = Controller.getController();
  private final JLabel date = new JLabel(controller.getDate());

  /**
   * It builds a new Reservation Form whenever we need to add a new Reservation.
   */
  public NewReservationForm() {
    super();
    res.add(util.loadReservation(controller.getDate()));
    this.buildLayout();
    this.setOkHandler();
    this.setVisible(true);
  }

  private void buildLayout() {
    final JPanel north = util.getDefaultPanel(new BorderLayout());
    north.add(date, BorderLayout.NORTH);
    north.add(res, BorderLayout.CENTER);
    super.getContentPane().add(okButton, BorderLayout.SOUTH);
    super.getContentPane().add(north, BorderLayout.NORTH);
  }

  private void setOkHandler() {
    this.okButton.addActionListener(setOkListener());
  } 
  
  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent arg0) {
        NewReservationForm.this.setVisible(false); 
        if (checkForm() && controller.getDate().equals(util.getCurrentDate())) {
          controller.addTable(getTable());
        }
      }      
    };
  }
  
  @Override
  public boolean checkForm() {
    boolean res = true;    
    try {
      if (controller.isPresent(getName(), controller.getDate())) {
        res = showMessage("Il nome inserito e' gia' stato utilizzato");
      } else {
        final IReservation reserv = new Reservation.Builder()
                                                   .table(getTable())
                                                   .name(getName())
                                                   .date(controller.getDate())
                                                   .hour(getH())
                                                   .tel(getTel())
                                                   .numPers(getNum())
                                                   .menu(getMenu())
                                                   .build();
        controller.add(reserv, controller.getDate());     
        res = true;
      }
    } catch (NullPointerException e1) {
      res = showMessage("Riempire la form!");      
    } catch (NumberFormatException e2) {
      res = showMessage("Riempire la form con dei numeri utili");
    } catch (IllegalCharsetNameException e4) {
      res = showMessage("Riempire la form con stringhe accettabili");
    } catch (IllegalArgumentException e3) {
      res = showMessage("Il tavolo inserito e' gia' stato utilizzato");
    } catch (IllegalStateException e5) {
      /* non dovremmo mai arrivare a tirare questa eccezione perchè i controlli
       * li faccio già nella ReservationForm
       */
      res = showMessage("Errore creazione prenotazione");
    }
    return res;
  }

  private boolean showMessage(final String srt) {
    controller.displayException(srt);
    NewReservationForm.this.setVisible(true);
    return false;
  }
}
