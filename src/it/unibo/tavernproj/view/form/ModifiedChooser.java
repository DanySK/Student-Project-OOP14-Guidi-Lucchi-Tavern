package it.unibo.tavernproj.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * @author Eleonora Guidi
 *
 */

//DECORATOR su setOkListener
public class ModifiedChooser extends Chooser{

  private static final long serialVersionUID = 1L;
  
  @Override
  public ActionListener setOkListener() {
    return new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent ev) {
        ModifiedChooser.this.setVisible(false);
        ModifiedChooser.this.showForm();
        
      };
    };    
  }

  protected void showForm() {
    try {
      new ModifiedTableForm(getReservation());
    } catch (NumberFormatException e){
      controller.displayException("Inserisci un numero utile");
      ModifiedChooser.this.setVisible(true);
    } catch (ParseException e1){
      controller.displayException("Inserisci una data utile");
      ModifiedChooser.this.setVisible(true);
    } catch (IllegalArgumentException e2) {
      showMessage("Nessuna prenotazione disponibile con quel nome e data");
      ModifiedChooser.this.setVisible(true);
    }
  }    
}
