package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;

public class Application {
  
  //private static final String HOME = System.getProperty("user.home");
  //private static final String SEPARATOR = System.getProperty("file.separator");
  //private static final String DEFAULT_FILE = "1.dat";
  
  //private 
  
  public static void main(final String[] argv) {
    final IUtilities utilities = new Utilities();
    final IController c = Controller.getController();  
    //c.setFileName(/*HOME + SEPARATOR + */DEFAULT_FILE);
          
    c.loadTables(utilities.getCurrentDate());
    
    final View v = new View();
    c.addView(v);   
    
    
  }

}
