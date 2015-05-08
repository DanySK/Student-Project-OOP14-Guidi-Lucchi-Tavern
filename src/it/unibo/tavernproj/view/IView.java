package it.unibo.tavernproj.view;
import javax.swing.JLabel;

import it.unibo.tavernproj.controller.*;
import it.unibo.tavernproj.disegno.DrawPosition;
import it.unibo.tavernproj.disegno.Pair;

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
	void addTable(Integer number);

  void addDraw(Pair<Integer, Integer> p);

  void removeTable(Integer table);


	

}
