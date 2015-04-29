package it.unibo.tavernproj.controller;
import java.util.Map;

import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.view.*;

/**
 * @author Eleonora Guidi
 *
 */

public interface IController {
	
	/**
	 * Loads the map of reservations from the local file system. 
	 */
  Map<Integer,IReservation> getTables(String date);

	/**
	 * Saves the reservations map in the local file system.
	 */
	void setTables();

	/**
	 * Adds a new table icon.
	 * 
	 * @param table
	 * 		the table number
	 */
	void addTable(String table, String date);
	
	/**
	 * Remove a table previously added both as a button and from the map.
	 * 
	 * @param number
	 *     table number
	 */
	void removeTable(int number);
	
	/**
	 * @param table
	 * 		table number
	 * @param date
	 * 		reservation date
	 * @return IReservation 
	 * 		the reservation we want
	 * 
	 * @throws NumberFormatException 
	 * 		if the table number is incorrect
	 */
	
	IReservation getReservation(int table, String date);

	/**
	 * Adds a view to this controller
	 * 
	 * @param v
	 * 		the view to add
	 */
	void addView(IView v);

	/**
	 * Removes a view from this controller
	 * 
	 * @param v
	 *      the view to remove
	 */
	void removeView(IView v);

}
