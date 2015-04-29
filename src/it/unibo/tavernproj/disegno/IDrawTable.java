package it.unibo.tavernproj.disegno;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *  @author Giulia Lucchi
 *
 */

public interface IDrawTable {
	
	void calcel(Graphics g1, int x1, int x2);
	
	int getX();
	int getY();


	Rectangle paintComponent(Graphics2D g, int x, int y);

	
	

}
