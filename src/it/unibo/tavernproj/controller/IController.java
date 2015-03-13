package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.view.*;

/**
 * @author Eleonora Guidi
 *
 */

public interface IController {
	
	/**
	 * Ricarica la lista dei tavoli in cartina dal file system. 
	 * Loads the list of tables in the map from the local file system. 
	 */
	void tablesLoad();

	/**
	 * Salva lo stato dei tavoli, che si trovano in cartina, nel file system.
	 * Saves the tables status of the map to the local file system.
	 */
	void tablesSave();

	/**
	 * Aggiunge un nuovo bottone per il tavolo sotto la cartina.
	 * Adds a new table icon.
	 * 
	 * @param number
	 * 		il numero del tavolo
	 *      the number of the table
	 */
	void tableAdd(int number);
	
	/**
	 * Rimuove il bottone per un tavolo precedentemente immesso e il tavolo sulla cartina.
	 * Remove a table previously added both as a button and from the map.
	 * 
	 * @param number
	 * 		il numero del tavolo
	 *      the number of the table
	 */
	void tableRemove(int number);
	
	/**
	 * Aggiunge una nuova prenotazione.
	 * Adds a new reservation.
	 */
	void reservationAdd();
	
	/**
	 * Cancella una prenotazione precedente.
	 * Remove a reservation.
	 */
	void reservationRemove();

	/**
	 * Aggiorna lo stato di ogni view attaccata.
	 * Refreshes the status of each attached view.
	 */
	void resend();

	/**
	 * Aggiunge una nuova view al controller
	 * Adds a view to this controller
	 * 
	 * @param v
	 * 		la view da aggiungere
	 * 		the view to add
	 */
	void addView(IView v);

	/**
	 * Elimina una view dal controller
	 * Removes a view from this controller
	 * 
	 * @param v
	 * 		la view da eliminare
	 *      the view to remove
	 */
	void removeView(IView v);

}
