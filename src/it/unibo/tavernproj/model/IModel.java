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
	 */
	void remove(final String date,final IReservation pren);

	Map<String, Set<IReservation>> getMap();

	Set<IReservation> getRes(String date);
	
}
