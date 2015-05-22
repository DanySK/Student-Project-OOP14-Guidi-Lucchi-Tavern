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
      public void actionPerformed(final ActionEvent e) {
        ModifiedChooser.this.setVisible(false);
        ModifiedChooser.this.showForm();     
      };
    };    
  }
    
  protected void showForm() {
    new ModifiedTableForm(getReservation(super.getTable(), super.getDate()));
  }    
}
