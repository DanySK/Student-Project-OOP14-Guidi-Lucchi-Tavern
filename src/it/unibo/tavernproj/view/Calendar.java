package it.unibo.tavernproj.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
* @see
* @author
* @author Seahawks
* @version 1.0
*/
public class Calendar {
  
  /*http://javarevisited.blogspot.it/2012/12/how-to-get-current-date-month-year-dayoweek-dayofmonth-java-example.html*/
  private final java.util.Calendar localCalendar = 
      java.util.Calendar.getInstance(TimeZone.getDefault());  
  private int month = localCalendar.get(java.util.Calendar.MONTH);
  private final int year = localCalendar.get(java.util.Calendar.YEAR);
  private final int currentDay = localCalendar.get(java.util.Calendar.DATE);
  private final int currentMonth =  localCalendar.get(java.util.Calendar.MONTH);  
  
  private final JLabel label = new JLabel("", JLabel.CENTER);
  private final JButton[] button = new JButton[49];
  private final JDialog jdg;
  private String day = "";   
  
  /**
   * Builds a new Calendar
   * 
   * @param frame
   *      the JFrame passed as base.
   */
  public Calendar(final JFrame frame) {
    jdg = new JDialog();
    jdg.setModal(true);
    final String[] header = { "D", "L", "Ma", "Me", "G", "V", "S" };
    final JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(430, 120));
    
    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(false);
      button[x].setBackground(Color.white);
      
      /**
       * Modify the method
       * 
       * @author Giulia Lucchi
       * 
       */
      if (x > 6) {
        button[x].addActionListener( e-> {
            day = button[selection].getActionCommand();
            jdg.dispose();
          });
      }      
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
  
    final JPanel p2 = new JPanel(new GridLayout(1, 3));
    final JButton previous = new JButton("<< Precedente");
    previous.addActionListener(e -> {
        month--;
        displayDate();
      });
    p2.add(previous);
    p2.add(label);
    final JButton next = new JButton("Prossimo >>");
    next.addActionListener(e -> {
        month++;
        displayDate();
      });
    p2.add(next);
    jdg.add(p1, BorderLayout.CENTER);
    jdg.add(p2, BorderLayout.SOUTH);
    jdg.pack();
    jdg.setLocationRelativeTo(frame);
    displayDate();
    jdg.setVisible(true);
  }

  private void displayDate() {
    for (int x = 7; x < button.length; x++) {
      button[x].setText("");
      button[x].setEnabled(false);
    }
    final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
    final java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, 1);
    final int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    final int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    int x = 0;
    int day;
    for (x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
      button[x].setText("" + day);
      button[x].setEnabled(true);
    }
    label.setText(sdf.format(cal.getTime()));
    jdg.setTitle("Calendar");
  }
  
  /**
   * @return
   *      the date picked from the calendar.
   */
  public String getPickedDate() {
    if (this.day.equals("")) {
      return "Error";
    }
    final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
    final java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    return sdf.format(cal.getTime());
  }
  
  /**
   * @return
   *      a boolean explaining if the date picked can be correct
   */
  public boolean isRight() {
    if (!this.day.equals("") && currentMonth == this.month) {
      return Integer.parseInt(this.day) >= this.currentDay; 
    }
    if (currentMonth < this.month) {
      return true;
    }
    return false;
  }
}

