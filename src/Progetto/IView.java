package Progetto;

public interface IView {
	
	/**Cambia {@link IController} corrente.
	 * 
	 * @param listener
	 *            nuovo {@link IController}.
	 * */
	void attachViewObserver(IController listener);
	
	

}
