package it.unibo.tavernproj.view;

public interface ICalendar {
  
  /**
   * @return
   *      the date picked from the calendar.
   */
  String getPickedDate();
  
  /**
   * @return
   *      a boolean explaining if the date picked can be correct
   */
  boolean isRight();

}
