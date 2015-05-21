package it.unibo.tavernproj.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @author Giulia Lucchi
 *
 */

public class Reservation implements Serializable, IReservation{

  private static final long serialVersionUID = -5126256178520079481L;
  private final int table;
  private final String name;
  private final String date;
  private final Double hour;
  private final String tel;
  private final int numPers;
  private final String menu;

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
                               final Double hour, final String tel, final Integer numPers, 
                                                 final String menu) {
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
  public Double getHour() {
    return hour;
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
  public String getMenu() {
    return menu;
  }
  
  @Override
  public String toString() {
    return "Tavolo:" + table + ", Nome:" + name + ", Data:" + date + ", Ora:" + hour;
  }
  
  public String toString(final String date) {
    return "Tavolo:" + table + ", Nome:" + name + ", Ora:" + hour;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((hour == null) ? 0 : hour.hashCode());
    result = prime * result + ((menu == null) ? 0 : menu.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + numPers;
    result = prime * result + table;
    result = prime * result + ((tel == null) ? 0 : tel.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Reservation other = (Reservation) obj;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (hour == null) {
      if (other.hour != null)
        return false;
    } else if (!hour.equals(other.hour))
      return false;
    if (menu == null) {
      if (other.menu != null)
        return false;
    } else if (!menu.equals(other.menu))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (numPers != other.numPers)
      return false;
    if (table != other.table)
      return false;
    if (tel == null) {
      if (other.tel != null)
        return false;
    } else if (!tel.equals(other.tel))
      return false;
    return true;
  }
}
