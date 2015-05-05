package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.disegno.DrawMap;
import it.unibo.tavernproj.disegno.DrawPosition;
import it.unibo.tavernproj.disegno.Pair;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
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
import java.util.Set;

/**
 * @author Eleonora Guidi
 * 
 * modify by @author Giulia Lucchi
 *
 */

//HO USATO IL PATTERN SINGLETON

public class Controller implements IController{
  
	private static final Controller SINGLETON = new Controller();  
	
	
	private final Set<IView> view = new HashSet<>();
	private DrawMap draw = new DrawMap();
	private IModel model = new Model();
	private ObjectOutput outMap;
	private ObjectInput  inMap; 
	
	private String fileName = "file.txt";

	private Controller(){};
	
	
	/*usato nel test junit per caricare un modello a piacere*/
	public void setModel(IModel model){
	  this.model = model;
	}
	
	public static Controller getController(){
	  return SINGLETON;
	}

	@Override
	public void addTable(Integer table, String date) {
		for (final IView v: view){	
			v.addTable(table, date);
		}
	}

	@Override
	public void removeTable(final int number) {
		// TODO Auto-generated method stub
		
	}
	
	
	 public void loadTables(String date) {
	   this.setModel();
      if (!model.isEmpty()){

	   //try{
	      for (Integer i: model.getTableRes(date).keySet()){
    	    this.addTable(i, date);
  	     }
	   /*}catch(NullPointerException e){
	     System.out.print("non ci sono tavoli quel giorno");
	   }*/
	    }	
      else {
        System.out.print("sticazzi è vuoto il model");
      }
    }


  @Override
	  public Map<Integer,IReservation> getRes(final String date) {	
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
			  final ObjectOutput out = new ObjectOutputStream(new FileOutputStream("map.txt"));
				out.writeObject(this.model.getMap());
				out.close();
			}catch (IOException e){
			  System.out.print("non salva  sul file");
			}
	}
	
	/*per caricare il modello da file system all'accensione*/
	 @Override
	  public void setModel(){
      try{
         final ObjectInput in = new ObjectInputStream(new FileInputStream("map.txt"));
         model.setModel((Map<String, Map <Integer, IReservation>>) in.readObject());
         in.close();
      }catch(IOException e){
        System.out.print("non prende il file");
      
      } catch (ClassNotFoundException e) {
        System.out.print("non carica il modello");
      }
	  }
	//SALVARE TUTTO IL MODELLO SU FILESYSTEM (QUINDI LA MAPPA PRINCIPALE) E RISETTARLO AL CARICAMENTO

	
	public void saveDisegno(){
	  try{
      outMap = new ObjectOutputStream(new FileOutputStream("disegno.txt"));
      outMap.writeObject(draw.getMap());
      outMap.close();
    }catch (Exception e){
      
    }
	}
	
	public void LoadDisegno(){
    Map<Integer,Pair<Integer,Integer>> map = null;
	    try{
        inMap = new ObjectInputStream(new FileInputStream("map.txt"));
        map=((Map<Integer,Pair<Integer,Integer>>)inMap.readObject());
        inMap.close();
      }catch(Exception e){
        
      }
      
	}

  @Override
  public IReservation getExternalReservation(Integer table, String date) {
    this.setModel();    
    return this.getReservation(table, date);
  }


  @Override
  public IModel getModel() {
    return this.model;
  }


  @Override
  public void commandQuit() {
    this.saveModel();
    System.exit(0);
  }

  /*
   * @param file
   *            the new file name
   
  public void setFileName(final String file) {
    this.fileName = file;
  }*/

}

