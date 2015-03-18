package it.unibo.tavernproj.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Giulia Lucchi
 *
 */
public class Reservation implements Serializable{
	
	private static final long serialVersionUID = -5126256178520079481L;
	private final String name;
	private final String date;
	private final String h;
	private final String tel;
	private final int numPers;
	private final boolean menu;
	
	/**
	 *
	 * @param name
	 *            the name
	 * @param date
	 *            the date
	 * @param h
	 *            the hours
	 * @param tel
	 *            the number of telephone
	 * @param numPers
	 *            the number of person
	 * @param men�
	 *            true if there is a fixed men�
	 */
	public Reservation(final String name, final String date, final String h, final String tel, final int numPers, final boolean menu) {
		Objects.requireNonNull(name);
		this.name = name;
		Objects.requireNonNull(date);
		this.date = date;
		Objects.requireNonNull(h);
		this.h = h;
		Objects.requireNonNull(tel);
		this.tel = tel;
		Objects.requireNonNull(numPers);
		this.numPers = numPers;
		Objects.requireNonNull(menu);
		this.menu = menu;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return h
	 */
	public String getHours() {
		return h;
	}

	/**
	 * @return the number of telephone
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * @return the number of person
	 */
	public int getNumPers() {
		return numPers;
	}


	/**
	 * @return true if there is a fixed men�
	 */
	public boolean isMenu() {
		return menu;
	}
}
