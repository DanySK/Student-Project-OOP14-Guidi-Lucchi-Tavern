package it.unibo.tavernproj.model;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * 
 * @author Giulia Lucchi
 *
 */

public class Reservation implements Serializable, IReservation{

  @Override
  public String toString() {
    return "Tavolo:" + table + ", Nome:" + name + ", Data:" + date + ", Ora:" + hour;
  }
  
  public String toString(String date) {
    return "Tavolo:" + table + ", Nome:" + name + ", Ora:" + hour;
  }

  private static final long serialVersionUID = -5126256178520079481L;
  private final int table;
  private final String name;
  private final String date;
  private final String hour;
  private final String tel;
  private final String numPers;
  //guardare cosa significa????
  private final transient Optional<String> menu;

  /**
   * Build the constructor.
   * 
   * @param table
   *            the table
   * @param name
   *            the name
   * @param date
   *            the date
   * @param hour
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
                               final String hour, final String tel, final String numPers, 
                                                 final Optional<String> menu) {
    Objects.requireNonNull(table);
    this.table = table;
    Objects.requireNonNull(name);
    this.name = name;
    Objects.requireNonNull(date);
    this.date = date;
    Objects.requireNonNull(hour);
    this.hour = hour;
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
    return hour;
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
  public String getMenu() {
    try {
      menu.get();
    } catch (NoSuchElementException e) {
      return "";
    }
    return menu.get();
  }

}
