package it.unibo.tavernproj.controller;

import it.unibo.tavernproj.view.Form;

public interface IFormController {

	/**
	 * Aggiunge una nuova view al controller
	 * Adds a view to this controller
	 * 
	 * @param f
	 * 		la view da aggiungere
	 * 		the view to add
	 */
	void addView(Form f);

	/**
	 * Elimina una view dal controller
	 * Removes a view from this controller
	 * 
	 * @param f
	 * 		la view da eliminare
	 *      the view to remove
	 */
	void removeView(Form f);


	//String getDate();
	
	//void setDate(String date);
}
