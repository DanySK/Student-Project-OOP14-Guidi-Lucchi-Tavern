package it.unibo.tavernproj.controller;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JLabel;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.view.*;

/**
 * @author Eleonora Guidi
 *
 */

public interface IController {
  
  
	
	/**
	 * Loads the map of reservations from the local file system. 
	 */
  void setModel();
  void setModel(IModel model);

	/**
	 * Saves the reservations map in the local file system.
	 */
	void saveModel();

	/**
	 * Adds a new table icon.
	 * 
	 * @param table
	 * 		the table number
	 */
	void addTable(Integer table);
	
	/**
	 * Remove a reservation from the model.
	 * 
	 * @param number
	 *     table number
	 * @param date
	 *     the date
	 */
	void remove(int number, String date);
	
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
	
	IReservation getReservation(int table, String date);

	/**
	 * Adds a view to this controller
	 * 
	 * @param v
	 * 		the view to add
	 */
	void addView(IView v);

	/**
	 * Removes a view from this controller
	 * 
	 * @param v
	 *      the view to remove
	 */
	void removeView(IView v);
	

	Map<Integer,IReservation> getReservation(final String date);

  void load(final String date);

  void commandQuit();

  void saveDisegno();

  void LoadDisegno();

  void add(Integer table, String name, String date, String h, String tel, String num, String menu);

  void setFileName(String string);

  void removeTable(Integer table);

  Set<String> getDates();

  int getReservation(String date, String name);

  void displayException(String string);
  
  /**
   * 
   * @return
   * 
   * @throws IllegalArgumentException if none date is set
   */
  String getDate();
  
  
  /**
   * Sets the date the user picked
   * */
  void setDate(String date);
  
  


}
