package it.unibo.tavernproj.view.disegno;

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
  void paint(final Graphics gr, final int x0, final int y0);

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
  void paintCancel(final Graphics gr, final int x0, final int y0);
  
  /**
   * It removes the last rectangle.
   * 
   * @param g1
   *      the graphic context
   */
  void cancel(final Graphics g1);
  
  /**
   * It removes all rectangles. 
   * 
   * @param g1
   *      the graphic context
   */
  void cancelAll(final Graphics g1);

  /**
   * @return
   *      the number of tables on the map.
   */
  int size();

}
