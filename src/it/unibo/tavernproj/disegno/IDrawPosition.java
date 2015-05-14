package it.unibo.tavernproj.disegno;

/**
 *  @author Giulia Lucchi
 *
 */

import java.awt.Graphics;

public interface IDrawPosition {
  
  /**
   * It draws the rectangle in the JLabel, in which there is a image of tavern's map.
   * 
   * @param gr
   *      the graphic context
   * @param x0
   *      the coordinate x
   * @param y0
   *      the coordinate y
   */
  void paint(Graphics gr, int x0, int y0);
  
  /**
   * It sets the index of Map to add the rectangle of loading.
   * 
   * @param index
   *      the index of rectangle's Map
   */
  void setIndex(int index);

  /**
   * It removes a rectangle from drawing.
   * 
   * @param gr
   *      the graphic context
   * @param x0
   *      the coordinate x
   * @param y0
   *      the coordinate y
   */
  void paintCancel(Graphics gr, int x0, int y0);
  
  /**
   * It removes the last rectangle.
   * 
   * @param g1
   *      the graphic context
   */
  void cancel(Graphics g1);
  
  /**
   * It removes all rectangles. 
   * 
   * @param g1
   *      the graphic context
   */
  void cancelAll(Graphics g1);

}
