package it.unibo.tavernproj.view;

import java.util.List;

import javax.swing.JComponent;

/**
 * @author Eleonora Guidi
 *
 */
public interface IUtilities { 

  /**
   * @return
   *      the current date.
   */
  String getCurrentDate();
  
  /**
   * @return
   *      the list of component previously added.
   */
  List<JComponent> getList();  

  /**
   * @param one.
   *      the first component to add in the list.
   * @param two
   *      the second component to add in the list.
   * @return
   *      a list with the two components.
   */
  List<JComponent> getList(final JComponent one, final JComponent two);

  /**
   * Adds a component to the list.
   * 
   * @param comp
   *      the component to add.
   */
  void add(JComponent comp);



}
