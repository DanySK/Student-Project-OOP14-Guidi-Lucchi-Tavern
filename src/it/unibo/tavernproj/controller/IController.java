package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.view.*;

/**
 * @author Eleonora Guidi
 *
 */

public interface IController {
	
	/**
	 * Loads the list of tables in the map from the local file system. 
	 */
	void tablesLoad();

	/**
	 * Saves the tables status of the map to the local file system.
	 */
	void tablesSave();

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
	 * Adds a new reservation.
	 */
	void addReservation();
	
	/**
	 * Remove a reservation.
	 */
	void removeReservation();

	/**
	 * Refreshes the status of each attached view.
	 */
	void resend();

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
