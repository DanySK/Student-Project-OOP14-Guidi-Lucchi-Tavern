package it.unibo.tavernproj.controller;
import it.unibo.tavernproj.disegno.DrawMap;
import it.unibo.tavernproj.disegno.DrawPosition;
import it.unibo.tavernproj.disegno.Pair;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.IView;
import it.unibo.tavernproj.view.Utilities;

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

import javax.swing.JLabel;

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
	private final Utilities utilies = new Utilities();
	private JLabel label = new JLabel();
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
	public void removeTable(final int table, final String date) {
		model.remove(date, table);		
	}
	
  public void loadTables (final String date) {
    this.setModel();
    if (!model.isEmpty()) {
      try {
        for (Integer i: model.getTableRes(date).keySet()) {
          this.addTable(i, date);
        }
      } catch (NullPointerException e) {
        System.out.print("non ci sono tavoli quel giorno");
      }
    } else {
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
            out.writeObject(model.getMap().get(s).get(i).getMenu().toString());
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
                 (String) in.readObject(), (String) in.readObject(), Optional.ofNullable((String) in.readObject()));
             
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
      outMap = new ObjectOutputStream(new FileOutputStream("disegno.txt"));
      outMap.writeObject(utilies.getCurrentDate());
      outMap.writeObject(draw.getMap());
      outMap.close();
	  }catch (IOException e){
      System.out.print("non salva  sul file nel disegno");
    }
	}
	
//	@Override
//	public void setLabel(JLabel label){
//	  this.label=label;
//	}
	
	
	@Override
	public void LoadDisegno(){
    Map<Integer,Pair<Integer,Integer>> map;
	    try{
        inMap = new ObjectInputStream(new FileInputStream("disegno.txt"));
        
    
      if(utilies.getCurrentDate().equals(inMap.readObject())){
            map=(Map<Integer,Pair<Integer,Integer>>)inMap.readObject();
            inMap.close();
            if(!map.isEmpty()){
            final DrawPosition pos = new DrawPosition(label);
            for(Integer i : map.keySet()){
              System.out.println("disegno"+i);
              Pair<Integer,Integer> p = map.get(i);
              pos.paint(label.getGraphics(),p.getX(),p.getY());
            }
         }else{
           System.out.println("\ndisegno non tirato su--->mappa vuota");
         }
       }
    } catch (ClassNotFoundException e) {
      System.out.println("\nnon trova la classe");
    }catch(IOException e){
        System.out.println("\nnon carica il disegno");
      }
	}

/*  @Override
  public IReservation getExternalReservation(Integer table, String date) {
    this.setModel();    
    return this.getReservation(table, date);
  }

*/
  @Override
  public IModel getModel() {
    return this.model;
  }


  @Override
  public void commandQuit() {
    this.saveDisegno();
    this.saveModel();
    System.exit(0);
  }


  @Override
  public void setLabel(JLabel label) {
    this.label=label;
  }


  @Override
  public void add(Integer table, String name, String date, String h, String tel, String num,
      Optional<String> menu) throws IllegalArgumentException{
    if (Double.parseDouble(h)<= 0 || Double.parseDouble(h) > 24){
      throw new NumberFormatException();
    }
    Reservation res = new Reservation(table, name, date, h, tel, num, menu);
    model.add(date, res);
  }
  
  /*private void add(Integer table, String name, String date, String h, String tel, String num,
      String menu) throws IllegalArgumentException{
    if (Double.parseDouble(h)<= 0 || Double.parseDouble(h) > 24){
      throw new NumberFormatException();
    }
    Reservation res = new Reservation(table, name, date, h, tel, num, Optional.ofNullable(menu));
    model.add(date, res);
    
  }*/


  @Override
  public void setFileName(String string) {
    this.fileName = string;
  }

  /*
   * @param file
   *            the new file name
   
  public void setFileName(final String file) {
    this.fileName = file;
  }*/

}

