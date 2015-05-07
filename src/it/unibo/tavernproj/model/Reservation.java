package it.unibo.tavernproj.model;

import it.unibo.tavernproj.view.NewReservationForm;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Giulia Lucchi
 * 
 * modified by @author Eleonora Guidi
 *
 */
public class Reservation implements Serializable, IReservation{
	
	@Override
  public String toString() {
    return "Tavolo:" + table + ", Nome:" + name + ", Ora:" + h;
  }

  private static final long serialVersionUID = -5126256178520079481L;
	private final int table;
	private final String name;
	private final String date;
	private final String h;
	private final String tel;
	private final String numPers;
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
	public Reservation(final Integer table, final String name, final String date, 
			final String h, final String tel, final String numPers, final String menu) {
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
		this.menu = Optional.ofNullable(menu);
	}

  public Reservation(final Integer table, final String name, final String date, 
      final String h, final String tel, final String numPers, final Optional<String> menu) {
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
	public int getTable() {
		return table;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public String getHours() {
		return h;
	}

	@Override
	public String getTel() {
		return tel;
	}
	
	@Override
	public String getNumPers() {
		return numPers;
	}
	
	@Override 
	public Optional<String> getMenu(){
	  /*if (!menu.isPresent()) {
	    throw new NoSuchElementException();
	  }*/
		return menu;
	}	
	
}
