package it.unibo.tavernproj.model;

import java.util.Map;
import java.util.Set;

/**
 * @author Giulia Lucchi
 */


public interface IModel{
  
  /**
   * It sets the Map of Model.
   * 
   * @param map
   *      the map of reservation
   */

  void setModel(final Map<String, Map<Integer, IReservation>> map);

  /**
   * It adds a new reservation.
   *
   * @param pren
   *            the reservation
   */
  void add(final String date, final IReservation pren) ;

  /**
   * It removes the reservation.
   * 
   * @param pren
   *      the reservation
   * @param date
   *      the date of the reservation
   */
  void remove(final String date, final IReservation pren);

  /**
   * It removes the reservation.
   * 
   * @param date
   *      the date of the reservation
   * @param table
   *      the table's number of the reservation
   */
  void remove(final String date, final Integer table);
  
  /**
   * It create a Set of the reservations of the specific date.
   *
   * @param date
   *            the date
   *            
   * @return 
   *        Set of reservation of a specific date 
   */
  Set<IReservation> getRes(final String date);

  /**
   *  
   * @return
   *      the main Map 
   */
  Map<String, Map<Integer, IReservation>> getMap();
  
  /**
   * It returns a reservation's Map, that has the table's number as key 
   * and entire reservation as value.
   *
   * @param date
   *      the specific date
   * @return
   *      Reservation's Map
   */
  Map<Integer, IReservation> getTableRes(final String date);
  
  /**
   * @return 
   *      the size of Map
   */
  int getSize();
  
  /**
   * @return
   *      true if the map is empty 
   *      false if the map is full
   */
  boolean isEmpty();
  
  /** 
   * @param name
   *          name of reservation 
   *          
   * @return
   *      the reservations' Set
   */
  Set<IReservation> getNameRes(final String name);
  
  /**
   * @return
   *      the date's Set
   */
  Set<String> getDates();
  
  /**
   * @return
   *      the map of model
   */
  Map<String, Map<Integer, IReservation>> getModel();

}
