package it.unibo.tavernproj.disegno;

import java.awt.Graphics;

/**
 *  @author Giulia Lucchi
 *
 */

public interface IDrawTable {
	
	void paintComponent(Graphics g);
	void calcel(Graphics g1, int x1, int x2);
	
	int getX();
	int getY();

	
	

}
