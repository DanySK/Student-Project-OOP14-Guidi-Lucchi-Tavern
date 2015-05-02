package it.unibo.tavernproj.disegno;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *  @author Giulia Lucchi
 *
 */

public interface IDrawTable {
	
	int getX();
	int getY();


	void paint(Graphics g);

  void cancel(Graphics g1);

	
	

}
