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
  
  void setModel();
	
	/**
	 * Loads the map of reservations from the local file system. 
	 */

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
	void addTable(Integer table, String date);
	
	/**
	 * Remove a table previously added both as a button and from the map.
	 * 
	 * @param number
	 *     table number
	 */
	void removeTable(int number);
	
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
	

	Map<Integer,IReservation> getRes(final String date);

  void loadTables(String format);

  IReservation getExternalReservation(Integer table, String date);

  IModel getModel();

  void commandQuit();

  void saveDisegno();

  void setLabel(JLabel label);

  void LoadDisegno();

  void add(Integer table, String name, String date, String h, String tel, String num, Optional<String> menu);

  //void setFileName(String string);


}
