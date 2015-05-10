package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;

public interface INewReservationForm {
	
	/**Change current {@link IFormController}
	 * 
	 * @param listener
	 *      new {@link IFormController}.
	 */
	void attachViewObserver(IController listener);
	
}
