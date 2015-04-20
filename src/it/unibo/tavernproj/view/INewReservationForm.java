package it.unibo.tavernproj.view;

import java.util.Optional;

import it.unibo.tavernproj.controller.IFormController;

public interface INewReservationForm {
	
	/**Change current {@link IFormController}
	 * 
	 * @param listener
	 *      new {@link IFormController}.
	 */
	void attachViewObserver(IFormController listener);
	
}
