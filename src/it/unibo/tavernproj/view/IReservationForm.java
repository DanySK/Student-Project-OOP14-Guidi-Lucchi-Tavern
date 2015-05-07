package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IFormController;

import java.util.Optional;

public interface IReservationForm {
	
	/**Change current {@link IFormController}
	 * 
	 * @param listener
	 *      new {@link IFormController}.
	 */
	void attachViewObserver(IFormController listener);
	
	Integer getTable();
	
	String getName(); 

	String getH();
	
	String getTel();
	
	String getNum();
	
	Optional<String> getMenu();
	
	boolean isMenuSelected();

	void disableAll();

	void enableAll();

	void setTable(int srt);
	
	void setName(String srt);

	void setH(String srt);

	void setTel(String srt);

	void setNum(int srt);

	void setMenu(String srt);

	//void setMenuVisible();
}
