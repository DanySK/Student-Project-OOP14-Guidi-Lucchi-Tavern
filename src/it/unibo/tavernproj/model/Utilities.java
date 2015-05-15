package it.unibo.tavernproj.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

/**
 * @author Eleonora Guidi
 *
 */
public class Utilities implements IUtilities {  
  
  private List<JComponent> list;
  
  public Utilities() {
    this.list = new ArrayList<>();
  }

  @Override
  public String getCurrentDate() {
    final java.util.Calendar localCalendar = java.util.Calendar.getInstance();
    final int month = localCalendar.get(java.util.Calendar.MONTH);
    final int year = localCalendar.get(java.util.Calendar.YEAR);
    final int day = localCalendar.get(java.util.Calendar.DATE);   
    final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
    localCalendar.set(year, month, day);
    return sdf.format(localCalendar.getTime());
  }
  
  @Override
  public List<JComponent> getList() {
    final List<JComponent> temp = list;
    list = new ArrayList<>();
    return temp;
  }

  @Override
  public List<JComponent> getList(final JComponent one, final JComponent two) {
    final List<JComponent> temp = new ArrayList<>();
    temp.add(one);
    temp.add(two);
    return temp;
  }
  
  @Override
  public void add(final JComponent comp) {
    list.add(comp);
  }
}
