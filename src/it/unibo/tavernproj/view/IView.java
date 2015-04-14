package it.unibo.tavernproj.view;
import it.unibo.tavernproj.controller.*;

/**
 * @author Eleonora Guidi
 *
 */

public interface IView {
	
	/**Change current {@link IController}
	 * 
	 * @param listener
	 *      new {@link IController}.
	 */
	void attachViewObserver(IController listener);
	
	/**Adds a new table button. 
	 * 
	 * @param number
	 * 		the table number
	 */
	void addTable(String number, String date);
	
	

}
