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
  void attachViewObserver(final IController listener);

  /**
   * Adds a new table button. 
   * 
   * @param number
   *      the table number
   */
  void addTable(final Integer number);
  
  /**
   * Removes a table button.
   * 
   * @param table
   *      the table number
   */
  void removeTable(final Integer table);  

  /**
   * Displays a message window with an error message.
   * 
   * @param message
   *      the message to be displayed
   */
  void commandFailed(final String message);
  
  
  // DA FARE
  
  void addDraw(final Pair<Integer, Integer> pair, final int i);

}
