package it.unibo.tavernproj.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @author Giulia Lucchi
 *
 */

public class SaveReservation implements ISaveReservation {
	
	//http://www.disi.unige.it/person/MagilloP/P2_SMID07/lez10.html 
	
	private final IModel model;
	private String[] files;
	private ObjectOutput out;
	private ObjectInput  in;
	
	public SaveReservation(){
		this.model = new Model();
	}
	
	@Override
	public void save(){
		int i = 0;
		for(String date : model.getMap().keySet()){
			files[i]=date;
			try{
				out = new ObjectOutputStream(new FileOutputStream(date));
				//out.writeUTF(date); c'è già il nome del file con la data non gli scriverei anche la data
				Map<Integer, IReservation> temp = model.getMap().get(date);
				for(Integer table : temp.keySet()){
					out.writeObject(temp.get(table));
				}
				out.close();
				
			}catch (Exception e){
				
			}
			i++;
		}
		
		
	}
	
	@Override
	public void load(){
		
		for(String date : files){
			
			try{
				in = new ObjectInputStream(new FileInputStream(date));
				model.add(date,(IReservation) in.readObject());
				in.close();
			}catch(Exception e){
				
			}
		}
	}
	
}
