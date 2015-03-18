package it.unibo.tavernproj.model;

import java.util.Objects;

/**
 * @author Giulia Lucchi
 *
 */

public interface IReservation {

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @return the date
	 */
	public String getDate();

	/**
	 * @return h
	 */
	public String getHours();

	/**
	 * @return the number of telephone
	 */
	public String getTel();
	
	/**
	 * @return the number of person
	 */
	public int getNumPers();


	/**
	 * @return true if there is a fixed men�
	 */
	public boolean isMenu();
}
