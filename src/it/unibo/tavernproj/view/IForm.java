package it.unibo.tavernproj.view;

import java.util.Optional;

import it.unibo.tavernproj.controller.IFormController;

public interface IForm {
	
	/**Change current {@link IFormController}
	 * 
	 * @param listener
	 *      new {@link IFormController}.
	 */
	void attachViewObserver(IFormController listener);
	
	String getTable();
	
	String getName(); 

	String getH();
	
	String getTel();
	
	String getNum();
	
	Optional<String> getMenu();
	
	boolean isMenuSelected();
	
}
