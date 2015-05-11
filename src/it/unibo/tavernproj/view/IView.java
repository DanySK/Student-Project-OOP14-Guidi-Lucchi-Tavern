package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.disegno.Pair;

/**
 * @author Eleonora Guidi
 * @author Giulia Lucchi
 *
 */

public interface IView {
  
  /**
   * Changes current {@link IController}
   * 
   * @param listener
   *      new {@link IController}.
   */
  void attachViewObserver(IController listener);

  /**
   * Adds a new table button. 
   * 
   * @param number
   *      the table number
   */
  void addTable(Integer number);
  
  /**
   * Removes a table button.
   * 
   * @param table
   *      the table number
   */
  void removeTable(Integer table);  

  /**
   * Displays a message window with an error message.
   * 
   * @param message
   *      the message to be displayed
   */
  void commandFailed(String message);
  
  
  // DA FARE
  
  void addDraw(Pair<Integer, Integer> pair, int i);

}
