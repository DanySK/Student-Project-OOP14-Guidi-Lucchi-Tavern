package it.unibo.tavernproj.controller;

import it.unibo.tavernproj.view.Form;
import it.unibo.tavernproj.view.FormFrame;

public interface IFormController {

	/**
	 * Add a table button on the main view
	 * */
	void tableAdd();
	
	/**
	 * Aggiunge una nuova view al controller
	 * Adds a view to this controller
	 * 
	 * @param f
	 * 		la view da aggiungere
	 * 		the view to add
	 */
	void addView(FormFrame f);

	/**
	 * Elimina una view dal controller
	 * Removes a view from this controller
	 * 
	 * @param f
	 * 		la view da eliminare
	 *      the view to remove
	 */
	void removeView(FormFrame f);

	String getDate();
}
