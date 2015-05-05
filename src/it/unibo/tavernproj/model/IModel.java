package it.unibo.tavernproj.model;

import java.util.Map;
import java.util.Set;

/**
 * @author Giulia Lucchi
 */


public interface IModel{
  
  void setModel(Map<String, Map <Integer, IReservation>> map);
	
	/**
	 * Adds a new reservation.
	 *
	 * @param pren
	 *            the reservation
	 */
	void add(final String date, final IReservation pren) ;
	
	/**
	 * Removes the reservation.
	 * 
	 * @param pren
	 *            the reservation
	 * @param date
	 * 			  the date of the reservation to remove
	 */
	void remove(final String date,final IReservation pren);

	/**
	 * Create a Set of the reservations of the specific date.
	 *
	 * @param date
	 *            the date
	 */
	Set<IReservation> getRes(final String date);

	/**
	 * @return
	 * 			the main Map 
	 */
	Map<String, Map<Integer, IReservation>> getMap();

	Map<Integer, IReservation> getTableRes(String date);

	int getSize();

  boolean isEmpty();

  Set<IReservation> getNameRes(String name);

	
	
}
