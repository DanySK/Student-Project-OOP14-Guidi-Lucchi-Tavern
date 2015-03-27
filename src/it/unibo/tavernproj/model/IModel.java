package it.unibo.tavernproj.model;

import java.util.Map;

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
	void add(final Reservation pren) ;
	
	/**
	 * Removes the reservation.
	 */
	void remove(final Reservation pren);

	Map<String, Reservation> getMap();
	
}
