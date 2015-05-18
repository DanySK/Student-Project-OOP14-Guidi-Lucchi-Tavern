package it.unibo.tavernproj.controller;

import it.unibo.tavernproj.disegno.DrawMap;
import it.unibo.tavernproj.disegno.Pair;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.GUIutilities;
import it.unibo.tavernproj.view.IGUIutilities;
import it.unibo.tavernproj.view.IView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * @author Eleonora Guidi
 * 
 * modify by @author Giulia Lucchi
 *
 */

//HO USATO IL PATTERN SINGLETON

public final class Controller implements IController {
  
  private static final long serialVersionUID = 1L;

  private static final Controller SINGLETON = new Controller();  

  private final Set<IView> view = new HashSet<>();
  private final IGUIutilities util = new GUIutilities();
  private final Map<Integer, Pair<Integer, Integer>> draw = DrawMap.getMap();
  private IModel model = new Model();
  private String fileName = "file.txt";
  private String fileDisegno = "disegno.dat";

  private Optional<String> date = Optional.empty();

  private Controller(){};
  
  public static Controller getController() {
    return SINGLETON;
  }
  
  @Override
  public void addView(final IView v) {
    v.attachViewObserver(this);
    view.add(v);
  }

  @Override
  public void removeView(final IView v) {
    view.remove(v);
  }
  
  @Override
  public void setFileName(final String string) {
    this.fileName = string;
  }
  
  @Override
  public void setFileDisegno(final String string) {
     this.fileDisegno = string;
  }
  
  @Override
  public void setDate(final String date) {
    this.date = Optional.of(date);
  }

  @Override
  public String getDate() throws IllegalArgumentException {
    if (!date.isPresent()) {
      throw new IllegalArgumentException();
    }
    return this.date.get();
  }

  @Override
  public Set<String> getDates() {
    return model.getDates();
  }
  
  @Override
  public void add(IReservation res, String date) {
    model.add(date, res);
  }
  
  @Override
  public void addTable(final Integer table) {
    for (final IView v: view) {
      v.addTable(table);
    }
  } 

  @Override
  public void remove(final int table, final String date) {
    this.removeReservation(table, date);
    if (date.equals(util.getCurrentDate())) {
      for (final IView v: view) {
        v.removeTable(table);
      }
    }
  }  
  
  @Override
  public void removeReservation(Integer table, String date) {
    model.remove(date, table);
  }

  @Override
  public Map<Integer,IReservation> getReservation(final String date) {
    if (model.getRes(date).isEmpty()) {
      return new HashMap<>();
    }
    return model.getTableRes(date);
  } 

  @Override
  public IReservation getReservation(final int table, final String date) 
      throws NumberFormatException {
    for (final Integer i: this.getReservation(date).keySet()) {
      if (i == table) {
        return this.getReservation(date).get(i);
      }
    }
    throw new NumberFormatException();
  }
  
  @Override
  public int getReservation(final String date, final String name) 
    throws IllegalArgumentException {
    for (final Integer i: this.getReservation(date).keySet()) {
      if (this.getReservation(i, date).getName().equals(name)) {
        return i;
      }
    }
    throw new IllegalArgumentException();
  }
  
  @Override
  public void commandQuit() {
    this.saveDisegno();
    this.saveModel();
    System.exit(0);
  }

  @Override
  public void displayException(final String str) {
    for (final IView v : view) {
      v.commandFailed(str);
    }
  }
  
  @Override
  public void load(final String date) {
    this.setModel();
    if (!model.isEmpty()) {
      try {
        for (final Integer i: model.getTableRes(util.getCurrentDate()).keySet()) {
          this.addTable(i);
        }       
      } catch (NullPointerException e) {
        //System.out.print("non ci sono tavoli quel giorno");
      }
      this.loadDisegno();
    }
  } 
  
  /*
   * Save and Load
   * @author Giulia Lucchi
   * 
   */
  @Override
  public void saveModel() {
    try {
      final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
      /*
       * out.writeObject(model);
       * out.close();
       * 
       * */
      out.writeObject(model.getMap().keySet().size());
      for (final String s: model.getMap().keySet()) {
        out.writeObject(s);
        out.writeObject(model.getMap().get(s).keySet().size());
        for (final Integer i: model.getMap().get(s).keySet()) {
          out.writeObject(i);
          out.writeObject(model.getMap().get(s).get(i).getName().toString());
          out.writeObject(model.getMap().get(s).get(i).getHours().toString());
          out.writeObject(model.getMap().get(s).get(i).getTel().toString());
          out.writeObject(model.getMap().get(s).get(i).getNumPers().toString());
          try {
            out.writeObject(model.getMap().get(s).get(i).getMenu().toString());
          } catch (NoSuchElementException e) {
            out.writeObject("");
          } catch (NullPointerException e1) {
            out.writeObject("");
          }
        }     
      }       
      out.close();
    } catch (IOException e) {
      //System.out.print("non salva sul file");
    }
  }

  /*per caricare il modello da file system all'accensione*/
  @Override
  public void setModel() {
    try {
      final ObjectInput in = new ObjectInputStream(new FileInputStream(fileName));
      /*
       * this.setModel(in.readObject());
       * in.close();
       * 
       * */
      final int size = (int) in.readObject();
      for (int i = 1; i <= size; i++) {
        final String date = (String) in.readObject();
        final int max = (int) in.readObject();
        for (int j = 1; j <= max; j++) {
          IReservation res = new Reservation((Integer) in.readObject(), (String) in.readObject(), 
              date, (String) in.readObject(), (String) in.readObject(), 
              (String) in.readObject(), Optional.ofNullable((String) in.readObject()));
          this.add(res, res.getDate());
        }
      }
      in.close();
    } catch (IOException e) {
      //System.out.print("non prende il file");
    } catch (ClassNotFoundException e) {
      //System.out.print("non carica il modello");
    }
  }

  @Override
  /*usato nel test junit per caricare un modello a piacere*/
  public void setModel(final IModel model) {
    this.model = model;
  }

  //SALVARE TUTTO IL MODELLO SU FILESYSTEM (QUINDI LA MAPPA PRINCIPALE) E RISETTARLO AL CARICAMENTO

  @Override
  public void saveDisegno() {
    try {
      final ObjectOutput outMap = new ObjectOutputStream(new FileOutputStream(fileDisegno));
      outMap.writeObject(util.getCurrentDate());
      outMap.writeObject(draw);
      System.out.print(draw);
      outMap.close();
    } catch (IOException e) {
      System.out.print("non salva  sul file nel disegno");
    }
  }

  @Override
  public void loadDisegno() {
    try {
      final ObjectInput inMap = new ObjectInputStream(new FileInputStream(fileDisegno));
      if (util.getCurrentDate().equals(inMap.readObject())) {
        System.out.println("data giusta per caricare");
        final Map<Integer,Pair<Integer,Integer>> map = 
            (Map<Integer,Pair<Integer,Integer>>) inMap.readObject();
        System.out.println("assegnato mappa" + map);
        inMap.close();
        int index = 0;
        for (final Integer i : map.keySet()) {
          final Pair<Integer,Integer> p = map.get(i);
          for (final IView v: view) {
            v.addDraw(p, index);
            index++;
            try {
              Thread.sleep(10);
            } catch (InterruptedException e) {
              //e.printStackTrace();
            }
          }
        }
      }
    } catch (ClassNotFoundException e) {
      System.out.println("\nnon trova la classe");
    } catch (IOException e) {
      System.out.println("\nnon carica il disegno");
    }
  }

  @Override
  public boolean isPresent(final String name, final String date) {
    try {
      this.getReservation(date, name);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}

