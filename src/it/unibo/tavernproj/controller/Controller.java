package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.disegno.DrawMap;
import it.unibo.tavernproj.disegno.Pair;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.IGUIutilities;
import it.unibo.tavernproj.view.IUtilities;
import it.unibo.tavernproj.view.IView;
import it.unibo.tavernproj.view.GUIutilities;
import it.unibo.tavernproj.view.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.swing.JLabel;

/**
 * @author Eleonora Guidi
 * 
 * modify by @author Giulia Lucchi
 *
 */

//HO USATO IL PATTERN SINGLETON

public class Controller implements IController,Serializable{
  
  private static final long serialVersionUID = 1L;

  private static final Controller SINGLETON = new Controller();  
	
	private final Set<IView> view = new HashSet<>();
	private final IGUIutilities util = new GUIutilities();
  private final Map<Integer, Pair<Integer, Integer>> draw = DrawMap.getMap();
	private IModel model = new Model();
	private ObjectOutput outMap;
	private ObjectInput  inMap; 
	private String fileName = "file.txt";
	
	private Optional<String> date = Optional.empty();

	private Controller(){};
	
	
	/*usato nel test junit per caricare un modello a piacere*/
	public void setModel(IModel model){
	  this.model = model;
	}
	
	public static Controller getController(){
	  return SINGLETON;
	}

	@Override
	public void addTable(Integer table) {	  
		for (final IView v: view){
			v.addTable(table);
		}
		this.LoadDisegno();
	}

  @Override
  public void removeReservation(Integer table, String date) {
    model.remove(date, table);
  }
	
	@Override
	public void remove(final int table, final String date) {
		this.removeReservation(table, date);
		if (date.equals(util.getCurrentDate())){
		  this.removeTable(table);
		}
	}
	
  public void load (final String date) {
    this.setModel();
    if (!model.isEmpty()) {
      try {
        for (Integer i: model.getTableRes(util.getCurrentDate()).keySet()) {
          this.addTable(i);
        }
      } catch (NullPointerException e) {
        //System.out.print("non ci sono tavoli quel giorno");
      }
    } else {
      //è vuoto il model
    }
  }


  @Override
	  public Map<Integer,IReservation> getReservation(final String date) {	
	   if (model.getRes(date).isEmpty()){
	     return new HashMap<>();
	   }
     return model.getTableRes(date);
	  } 
	

	@Override
	public IReservation getReservation(int table, String date) {
		for (IReservation r: model.getRes(date)){
			if (r.getTable() == table){
				return r;
			}
		}
		throw new NumberFormatException();
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


	/*
	 * Save and Load
	 * @author Giulia Lucchi
	 * 
	 */
	@Override
  public void saveModel(){    
    
      try{
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(model.getMap().keySet().size());
        for (String s: model.getMap().keySet()){
          out.writeObject(s);
          out.writeObject(model.getMap().get(s).keySet().size());
          for (Integer i: model.getMap().get(s).keySet()){
            out.writeObject(i);
            //System.out.println(model.getMap().get(s).get(i).toString());
            out.writeObject(model.getMap().get(s).get(i).getName().toString());
            out.writeObject(model.getMap().get(s).get(i).getHours().toString());
            out.writeObject(model.getMap().get(s).get(i).getTel().toString());
            out.writeObject(model.getMap().get(s).get(i).getNumPers().toString());
            try{
              out.writeObject(model.getMap().get(s).get(i).getMenu().toString());
            }catch(NoSuchElementException e){
              out.writeObject("");
            }catch(NullPointerException e1){
              out.writeObject("");
            }
          }     
        }       
        out.close();
      }catch (IOException e){
        System.out.print("non salva sul file");
      }
  }


  /*per caricare il modello da file system all'accensione*/
   @Override
    public void setModel(){
     Map<String, Map <Integer, IReservation>> temp = new HashMap<>();
     Map<Integer, IReservation> tempMap = new HashMap<>();
      try{
         final ObjectInput in = new ObjectInputStream(new FileInputStream(fileName));
         int size = (int) in.readObject();
         for (int i = 1; i <= size; i++) {

           String date = (String) in.readObject();

           int max = (int) in.readObject();

           for (int j = 1; j <= max; j++){
             /*add(String table, String name, String date, String h, String tel, String num,
                 Optional<String> menu)*/
                 
             this.add((Integer) in.readObject(), (String) in.readObject(), date, (String) in.readObject(),
                 (String) in.readObject(), (String) in.readObject(), (String) in.readObject());
             
             //IReservation tempres = new Reservation(, , date, , 
                 
             //tempMap.put(j, tempres);
           }
           //temp.put(date, tempMap);
         } 
         
         //model.setModel(temp);
         in.close();
      }catch(IOException e){
        System.out.print("non prende il file");
      
      } catch (ClassNotFoundException e) {
        System.out.print("non carica il modello");
      }
    }
	


  //SALVARE TUTTO IL MODELLO SU FILESYSTEM (QUINDI LA MAPPA PRINCIPALE) E RISETTARLO AL CARICAMENTO

	@Override
	public void saveDisegno(){
	  try{
      outMap = new ObjectOutputStream(new FileOutputStream("disegno.dat"));
      outMap.writeObject(util.getCurrentDate());
      outMap.writeObject(draw);
      System.out.print(draw);
      outMap.close();
	  }catch (IOException e){
      System.out.print("non salva  sul file nel disegno");
    }
	}
	
	@Override
	public void LoadDisegno(){
	   try{
        inMap = new ObjectInputStream(new FileInputStream("disegno.dat"));
      if(util.getCurrentDate().equals(inMap.readObject())){
        System.out.println("data giusta per caricare");
        Map<Integer,Pair<Integer,Integer>> map = (Map<Integer,Pair<Integer,Integer>>)inMap.readObject();
           System.out.println("assegnato mappa"+ map);
            inMap.close();
            int index=0;
            for(Integer i : map.keySet()){
              Pair<Integer,Integer> p = map.get(i);
              for (final IView v: view){
                v.addDraw(p, index);
                index++;
              }
               
            }
       }
         
    } catch (ClassNotFoundException e) {
      System.out.println("\nnon trova la classe");
    }catch(IOException e){
        System.out.println("\nnon carica il disegno");
    }
	
	}



  @Override
  public void commandQuit() {
    this.saveDisegno();
    this.saveModel();
    System.exit(0);
  }



  @Override
  public void add(Integer table, String name, String date, String h, String tel, String num,
      String menu) throws IllegalArgumentException, NumberFormatException {
    if (name.isEmpty() || name.equals(" ")){
      throw new NullPointerException();
    }

    Reservation res;
    if (menu.equals("")){
      res = new Reservation(table, name, date, h, tel, num, Optional.empty());
    }
    else {
      res = new Reservation(table, name, date, h, tel, num, Optional.of(menu));
    }
    model.add(date, res);
  }


  @Override
  public void setFileName(String string) {
    this.fileName = string;
  }


  @Override
  public void removeTable(Integer table) {
    for (final IView v: view){
      v.removeTable(table);
    }
  }


  @Override
  public Set<String> getDates() {
    return model.getDates();
  }


  @Override
  public int getReservation(String date, String name){
    for (IReservation r: model.getNameRes(name)){
      if (r.getDate().equals(date)){
        return r.getTable();
      }
    }
    throw new IllegalArgumentException();
  }

  public void displayException(final String e) {
    for (final IView v : view) {
      v.commandFailed(e);
    }
  }


  @Override
  public String getDate() {
    if (!date.isPresent()){
      throw new IllegalArgumentException();
    }
    return this.date.get();
  }


  @Override
  public void setDate(String date) {
    this.date = Optional.of(date);
  }



  


}

