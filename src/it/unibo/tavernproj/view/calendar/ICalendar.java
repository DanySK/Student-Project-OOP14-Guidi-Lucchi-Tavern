package it.unibo.tavernproj.view.calendar;

public interface ICalendar {
  
  /**
   * @return
   *      the date picked from the calendar.
   * @throws NumberFormatException
   *      if the day picked isn't right.
   */
  String getPickedDate() throws NumberFormatException;
  
  /**
   * @return
   *      a boolean explaining if the date picked can be correct
   */
  boolean isRight();

}
