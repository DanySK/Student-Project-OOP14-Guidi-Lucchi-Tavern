package it.unibo.tavernproj.controller;


import java.util.Optional;
import java.util.Set;

import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.view.IReservationForm;
import it.unibo.tavernproj.view.NewReservationForm;

public interface IFormController {

	/**
	 * Adds a view to this controller
	 * 
	 * @param f
	 * 		the view to add
	 */
	void addView(IReservationForm f);

	/**
	 * Removes a view from this controller
	 * 
	 * @param f
	 *      the view to remove
	 */
	void removeView(NewReservationForm f);


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
	void save(String table, String name, String h, String tel, String numPers, Optional<String> menu);

	Set<IReservation> getRes(String date);
}
