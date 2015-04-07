package it.unibo.tavernproj.disegno;

import java.awt.Graphics;

public interface IDrawTable {
	
	void paintComponent(Graphics g);
	
	int getX();
	int getY();

	void calcel(Graphics g1, int x1, int x2);
	

}
