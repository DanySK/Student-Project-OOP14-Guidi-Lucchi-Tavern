package it.unibo.tavernproj.model;

import it.unibo.tavernproj.view.Form;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Giulia Lucchi
 * 
 * modified by @author Eleonora Guidi
 *
 */
public class Reservation implements Serializable, IReservation{
	
	private static final long serialVersionUID = -5126256178520079481L;
	private final String table;
	private final String name;
	private final String date;
	private final String h;
	private final String tel;
	private final int numPers;
	private final Optional<String> menu;
	
	/**
	 * @param table
	 *            the table
	 * @param name
	 *            the name
	 * @param date
	 *            the date
	 * @param h
	 *            the hours
	 * @param tel
	 *            the number of telephone
	 * @param numPers
	 *            the number of people
	 * @param menu
	 *            an Optional containing the selected menu, if added.
	 *            Else an empty Optional.
	 */
	public Reservation(final String table, final String name, final String date, 
			final String h, final String tel, final int numPers, final Optional<String> menu) {
		Objects.requireNonNull(table);
		this.table = table;
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
		this.menu = menu;
	}
	
	@Override
	public String getTable() {
		return table;
	}

	@Override
	public String getName() {
		return name;
	}

	/*@Override
	public String getDate() {
		return date;
	}*/

	@Override
	public String getHours() {
		return h;
	}

	@Override
	public String getTel() {
		return tel;
	}
	
	@Override
	public int getNumPers() {
		return numPers;
	}
	
	@Override 
	public String getMenu(){
		return menu.get();
	}

	@Override
	public Form getReservation() {
		Form form = new Form(this.date);
		return null;
	}
	
	
}
