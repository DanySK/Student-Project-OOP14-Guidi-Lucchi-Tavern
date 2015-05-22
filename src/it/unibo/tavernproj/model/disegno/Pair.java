package it.unibo.tavernproj.model.disegno;

import java.io.Serializable;

public class Pair<X, Y> implements Serializable, IPair {
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
  public Pair(final X x0, final Y y0) {
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

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pair other = (Pair) obj;
    if (x0 == null) {
      if (other.x0 != null)
        return false;
    } else if (!x0.equals(other.x0))
      return false;
    if (y0 == null) {
      if (other.y0 != null)
        return false;
    } else if (!y0.equals(other.y0))
      return false;
    return true;
  }
  
}

