package it.unibo.tavernproj.view;
import it.unibo.tavernproj.controller.*;

public interface IView {
	
	/**Cambia {@link IController} corrente.
	 * 
	 * @param listener
	 *            nuovo {@link IController}.
	 * */
	void attachViewObserver(IController listener);
	
	

}
