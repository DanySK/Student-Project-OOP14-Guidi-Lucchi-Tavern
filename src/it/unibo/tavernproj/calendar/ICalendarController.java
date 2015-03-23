package it.unibo.tavernproj.calendar;

import it.unibo.tavernproj.view.View;

public interface ICalendarController {
	
	public String getCurrentDate();

	void addView(Calendar c);

	void removeView(Calendar c);

}
