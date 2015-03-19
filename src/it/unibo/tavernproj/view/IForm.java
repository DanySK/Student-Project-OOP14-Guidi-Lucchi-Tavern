package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.controller.IFormController;

public interface IForm {
	
	/**Cambia {@link IFormController} corrente.
	 * Change current {@link IFormController}
	 * 
	 * @param listener
	 * 		nuovo {@link IFormController}.
	 *      new {@link IFormController}.
	 */
	void attachViewObserver(IFormController listener);
}
