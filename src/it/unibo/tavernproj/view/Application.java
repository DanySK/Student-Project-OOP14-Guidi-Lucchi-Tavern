package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;

public final class Application {
  
  private static final String HOME = System.getProperty("user.home");
  private static final String SEPARATOR = System.getProperty("file.separator");
  private static final String DEFAULT_FILE = "dat.txt";
  
  private Application(){    
  }

  /**
   * @param args
   *      ignored.
   */
  public static void main(final String[] args) {
    final IUtilities util = new Utilities();
    final IController c = Controller.getController();  
    c.setFileName(HOME + SEPARATOR + DEFAULT_FILE);    
    final View v = new View();
    c.addView(v);    
    c.load(util.getCurrentDate());    
  }
}
