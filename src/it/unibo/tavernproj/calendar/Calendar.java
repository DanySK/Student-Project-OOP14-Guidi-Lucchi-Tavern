package it.unibo.tavernproj.calendar;
import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.FormController;
import it.unibo.tavernproj.controller.IFormController;
import it.unibo.tavernproj.view.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	java.util.Calendar localCalendar = java.util.Calendar.getInstance(TimeZone.getDefault());
	
	private int month = localCalendar.get(java.util.Calendar.MONTH);
	private int year = localCalendar.get(java.util.Calendar.YEAR);
	private final int currentDay = localCalendar.get(java.util.Calendar.DATE);
	private final int currentMonth =  localCalendar.get(java.util.Calendar.MONTH);

	
	JLabel l = new JLabel("", JLabel.CENTER);
	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];
	
	private ICalendarController ctrl;
	
	
	public Calendar(JFrame frame) {	
		d = new JDialog();
		d.setModal(true);
		String[] header = { "D", "L", "Ma", "Me", "G", "V", "S" };
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 120));
		
		for (int x = 0; x < button.length; x++) {
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			/**
			* modify the method
			* @author Giulia Lucchi
			* 
			*/
			if (x > 6){				
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {						
						day = button[selection].getActionCommand();
						d.dispose();								
					}
				});
			}
			
			if (x < 7) {
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
				//button[x].setEnabled(false);
			}
			p1.add(button[x]);
		}
		
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<< Precedente");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
				}
			});
		p2.add(previous);
		p2.add(l);
		
		JButton next = new JButton("Prossimo >>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
				}
			});
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(frame);
		displayDate();
		d.setVisible(true);
	}
	
	
	
	public void displayDate() {
		for (int x = 7; x < button.length; x++){
			button[x].setText("");
			button[x].setEnabled(false);
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		int x = 0, day;
		for (x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
			button[x].setText("" + day);
			button[x].setEnabled(true);
		}
		l.setText(sdf.format(cal.getTime()));
		d.setTitle("Calendar");
	}
	
	public String getPickedDate() {
		if (this.day.equals("")){
			return "Error";
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));		
		return sdf.format(cal.getTime());
	}
	
	public boolean isRight(){
		if (!this.day.equals("")){			
			if (currentMonth == this.month){
				return Integer.parseInt(this.day) >= this.currentDay; 
			}
		}
		if (currentMonth < this.month){
			return true;
		}
		return false;
	}

	public void attachViewObserver(ICalendarController calendarController) {
		this.ctrl = calendarController;
	}
	
}

