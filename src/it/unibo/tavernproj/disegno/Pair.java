package it.unibo.tavernproj.disegno;

import java.io.Serializable;

public class Pair<X, Y> implements Serializable {
//esami vecchi viroli
  private static final long serialVersionUID = 4195385612271660319L;
  
  private final X x0;
  private final Y y0;

  /**
   * It sets the values
   * 
   * @param x0
   *      value x
   * @param y0
   *      value y
   */
  public Pair(X x0, Y y0) {
    super();
    this.x0 = x0;
    this.y0 = y0;
  }

  public X getX() {
    return x0;
  }

  public Y getY() {
    return y0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((x0 == null) ? 0 : x0.hashCode());
    result = prime * result + ((y0 == null) ? 0 : y0.hashCode());
    return result;
  }
}

