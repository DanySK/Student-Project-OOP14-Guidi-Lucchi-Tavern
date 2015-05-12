package it.unibo.tavernproj.view;

import java.util.List;

import javax.swing.JComponent;

public interface IUtilities { 

  /**
   * @return
   *      the current date.
   */
  String getCurrentDate();
  
  List<JComponent> getList();  

  List<JComponent> getList(JComponent one, JComponent two);

  void add(JComponent c);



}
