package it.unibo.tavernproj.controller;

import java.util.Map;
import java.util.Set;

import it.unibo.tavernproj.view.IView;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;


/**
 * @author Eleonora Guidi
 *
 */

public interface IController { 
  
  /**
   * Adds a view to this controller
   * 
   * @param v
   * 		the view to add
   */
  void addView(final IView v);

  /**
   * Removes a view from this controller
   * 
   * @param v
   *      the view to remove
   */
  void removeView(final IView v);

  /**
   * Sets the file to save to or load from.
   * 
   * @param string
   *       the file name.
   */
  void setFileName(final String string);

  /**
   * Sets the date the user picked
   * 
   * @param date
   *      the date picked.
   */
  void setDate(final String date);
  
  /**
   * @return
   *      the date piked before.
   * 
   * @throws IllegalArgumentException if none date is set
   */
  String getDate() throws IllegalArgumentException; 
  
  /**
   * @return
   *      a set containing all the reservations dates.
   */
  Set<String> getDates();
  
  /**
   * Adds a new reservation to the model.
   * 
   * @param res
   *      the reservation.
   */
  void add(final IReservation res, String date);
  
  /**
   * Adds a new table button.
   *
   * @param table
   * 		the table number
   */
  void addTable(Integer table);

  /**
   * Removes the table reservation from the model
   * and from the view.
   * 
   * @param number
   *     table number
   * @param date
   *     the date
   */
  void remove(final int number, final String date);
  
  /**
   * Removes the table reservation from the model.
   *  
   * @param table
   *     table number
   * @param date
   *     the date
   */
  void removeReservation(final Integer table, final String date);
  
  /**
   * @param date
   *      the reservation date
   * @return
   *      a map containing the table number and the linked reservation.
   */
	Map<Integer,IReservation> getReservation(final String date); 
	
  /**
	 * @param table
	 * 		table number
	 * @param date
	 * 		reservation date
	 * @return IReservation 
	 * 		the reservation we want
	 * 
	 * @throws NumberFormatException 
	 * 		if the table number is incorrect
	 */	
	IReservation getReservation(int table, String date) throws NumberFormatException;
	
	/**
	 * @param date
	 *       the reservation date.
	 * @param name
	 *       the costumer name.
	 * @return
	 *       the table linked to that reservation.
	 */
	int getReservation(String date, String name);
	
	
	
  
  
  /**
   * Loads the current day reservations.
   * 
   * @param date
   *      the current day.
   */
  void load(final String date);
  
  /**
   * Loads the map of reservations from the local file system.
   */
  void setModel();
  
  void setModel(IModel model);

	/**
	 * Saves the reservations map in the local file system.
	 */
	void saveModel();


	

	


	
	





  void commandQuit();

  void saveDisegno();

  void loadDisegno();

  





  

  
  

  
  

  
  
  void displayException(final String e);

  boolean isPresent(String name, String date);

  /**
   * Sets the file to save to or load from.
   * 
   * @param string
   *       the file name.
   */
  void setFileDisegno(String string);


}
