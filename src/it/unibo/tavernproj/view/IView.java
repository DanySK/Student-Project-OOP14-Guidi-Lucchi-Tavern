package it.unibo.tavernproj.view;
import it.unibo.tavernproj.controller.*;

/**
 * @author Eleonora Guidi
 *
 */

public interface IView {
	
	/**Cambia {@link IController} corrente.
	 * Change current {@link IController}
	 * 
	 * @param listener
	 * 		nuovo {@link IController}.
	 *      new {@link IController}.
	 */
	void attachViewObserver(IController listener);
	
	/**Aggiunge un nuovo bottone per il tavolo.
	 * Adds a new table button. 
	 */
	void addTable();
	
	/**Nasconde il frame del calendario.
	 * It makes the Calendar invisible.
	 * */
	void disableCalendar();
	
	

}
