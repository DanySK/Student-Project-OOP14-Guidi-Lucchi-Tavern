package it.unibo.tavernproj.model.disegno;

public interface IPair<X, Y> {
  
  /**
   * Return a value X of pair
   * 
   * @return X
   *      values x
   */
  X getX();
  
  /**
   *  Return a value  Y of pair
   * @return
   *     values y
   */
  Y getY();
}
