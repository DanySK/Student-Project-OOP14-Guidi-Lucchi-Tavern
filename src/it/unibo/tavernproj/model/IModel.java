package it.unibo.tavernproj.model;

import java.util.Map;
import java.util.Set;

/**
 * @author Giulia Lucchi
 */


public interface IModel{
	
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

	
	
}
