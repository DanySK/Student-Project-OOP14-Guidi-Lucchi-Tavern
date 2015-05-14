package it.unibo.tavernproj.view;

/**
 * @author Eleonora Guidi
 *
 */
public interface IChooser {
  
  /**
   * @return
   *      if is been removed a table in the current day.
   */
  boolean isBeenRemoved();
  
  /**
   * @return
   *      the table removed.
   */
  int getTable();
}
