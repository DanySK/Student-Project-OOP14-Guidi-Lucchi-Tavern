package it.unibo.tavernproj.controller;

import java.util.Map;
import java.util.Set;

import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.view.Form;

public interface IFormController {

	/**
	 * Adds a view to this controller
	 * 
	 * @param f
	 * 		the view to add
	 */
	void addView(Form f);

	/**
	 * Removes a view from this controller
	 * 
	 * @param f
	 *      the view to remove
	 */
	void removeView(Form f);


	String getDate();
	
	
	/**
	 * Sets the date the user picked
	 * */
	void setDate(String date);
	
	/**
	 * Saves the reservation
	 * @param table
	 *            the table
	 * @param name
	 *            the name
	 * @param h
	 *            the hours
	 * @param tel
	 *            the number of telephone
	 * @param numPers
	 *            the number of people
	 * @param menu
	 *            the selected menu.
	 */
	void save(String table, String name, String h, String tel, String numPers,String menu);

	/**
	 * Saves the reservation without a selected menu
	 * @param table
	 *            the table
	 * @param name
	 *            the name
	 * @param h
	 *            the hours
	 * @param tel
	 *            the number of telephone
	 * @param numPers
	 *            the number of people
	 */
	void save(String table, String name, String h, String tel, String numPers);

	Set<IReservation> getRes(String date);
}
