package it.unibo.tavernproj.model;

/**
 * @author Giulia Lucchi
 *
 */

public interface IReservation{

  /**
   * @return
   *       the table of reservation
   */
  int getTable();

  /**
   * @return
   *      the name of reservation
   */
  String getName();

  /**
   * @return
   *      the date of reservation
   */
  String getDate();
  
  /**
   * @return
   *       hour of reservation
   */
  Double getHour();

  /**
   * @return 
   *      the number of telephone
   */
  String getTel();

  /**
   * @return
   *      the number of people
   */
  int getNumPers();

  /**
   * @return
   *     the menu, if selected and added
   */
  String getMenu();

}
